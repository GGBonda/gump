import React from 'react';
import {createRoot} from 'react-dom/client';

import { Graph,Markup } from '@antv/x6';
import { register } from "@antv/x6-react-shape";

import CustomLabel from 'components/apiDesign/x6/CustomLabel.jsx';
import CustomNode from 'components/apiDesign/x6/CustomNode.jsx';
import STEP_CONFIG from 'config/StepConfig.js';
import Item from 'antd/lib/list/Item';

const STEP_SPACE = 120;
const STEP_HEIGHT = 50;
const STEP_WIDTH = 100;
const CIRCLE_STEP_WIDTH = 60;
const START_STEP_TYPES = Object.keys(STEP_CONFIG).filter(stepType => STEP_CONFIG[stepType].startStep);
const END_STEP_TYPES = Object.keys(STEP_CONFIG).filter(stepType => STEP_CONFIG[stepType].endStep);

class FlowGraphEditor extends React.Component {

    constructor(props) {
        super(props);
        this.graph = null;
        this.state = {
            steps: this.props.steps.map(step => ({...step})),
            selectedStepCode: ''
        };
    }

    componentDidMount() {
        this.initGraph();
    }

    componentDidUpdate(prevProps) {
        this.state.steps = this.props.steps.map(step => ({...step}));

        let data = this.analyseGraphJSONData(this.state.steps);
        this.graph.fromJSON(data);
    }

    onStepsChange(steps) {
        if (this.props.onStepsChange != undefined && typeof this.props.onStepsChange === 'function') {
            this.props.onStepsChange(steps.map(item => {
                let step = {...item};
                delete step.x;
                delete step.y;
                delete step.nextPointer;
                delete step.byRoadNextPointer;
                return step;
            }));
        }
    }

    initGraph() {
        register({
            shape: "custom-react-node",
            width: STEP_WIDTH,
            height: STEP_HEIGHT,
            component: CustomNode,
        });

        this.graph = new Graph({
            container: document.getElementById(this.props.apiCode),
            onEdgeLabelRendered: ({ label, container, selectors }) => {
                const data = label.data;

                if (data != null && data != undefined) {
                    const content = selectors.foContent;
                    content.style.display = 'flex';
                    content.style.alignItems = 'center';
                    content.style.justifyContent = 'center';
                    createRoot(content).render(<CustomLabel onSelect={selectedStep => {
                        let steps = this.addStep(data.previousStepCode, selectedStep, data.isByRoad);
                        this.setState({steps});

                        this.onStepsChange(steps);
                    }}/>);
                }
            },
            autoResize: false,
            panning: true,
            background: {
                color: '#fffbe6', // 设置画布背景颜色
            },
            grid: {
                size: 10,      // 网格大小 10px
                visible: true, // 渲染网格背景
            }
        });

        let data = this.analyseGraphJSONData(this.state.steps);
        
        this.graph.fromJSON(data);
        this.graph.centerContent();
        this.graph.on("node:click", ({ e, x, y, node, view }) => {
            if (this.state.selectedStepCode != node.id) {
                this.setState({selectedStepCode: node.id});
                
                if (this.props.onSelectStep != undefined && typeof this.props.onSelectStep === 'function') {
                    this.props.onSelectStep(node.id);
                }
            }
            e.stopPropagation();
        });
    }

    defaultStepCode(addStepType) {
        let sameTypeSteps = this.state.steps.filter(step => step.type === addStepType);

        let maxSuffixNum = sameTypeSteps.length - 1;
        let regularStepCodeRegExp = new RegExp(`${addStepType}\\d+`);
        sameTypeSteps.forEach(sameTypeStep => {
            if (regularStepCodeRegExp.test(sameTypeStep.code)) {
                let suffixNum = parseInt(sameTypeStep.code.match(/\d+/)[0])
                if (maxSuffixNum < suffixNum) {
                    maxSuffixNum = suffixNum;
                }
            }
        });
        return addStepType + (maxSuffixNum + 1) + '';
    }

    addStep(previousStepCode, addStepType, isByRoad) {
        let addStepCode = this.defaultStepCode(addStepType);

        let addStep = {
            code: addStepCode,
            comment: addStepCode,
            type: addStepType 
        };

        let {steps} = this.state;
        let previousStep = steps.filter(step => step.code === previousStepCode)[0];
        if (isByRoad) {
            addStep.next = previousStep.byRoadNext;
            previousStep.byRoadNext = addStepCode;
        } else {
            addStep.next = previousStep.next;
            previousStep.next = addStepCode;
        }

        //steps.push(addStep);
        let newSteps = [...steps, addStep];

        if (STEP_CONFIG.hasOwnProperty(addStepType) && STEP_CONFIG[addStepType].byRoad) {
            if (STEP_CONFIG[addStepType].defaultByRoadNextStepType != undefined && STEP_CONFIG[addStepType].defaultByRoadNextStepType != null) {
                let addDefaultByRoadStepCode = this.defaultStepCode(STEP_CONFIG[addStepType].defaultByRoadNextStepType);
                addStep.byRoadNext = addDefaultByRoadStepCode;

                newSteps.push({
                    code: addDefaultByRoadStepCode,
                    comment: addDefaultByRoadStepCode,
                    type: STEP_CONFIG[addStepType].defaultByRoadNextStepType
                });
            }
        }

        return newSteps;
    }

    deleteStep(stepCode) {
        let {steps} = this.state;

        let stepMap = {};
        steps.forEach(step => stepMap[step.code] = step);
        steps.forEach(step => {
            stepMap[step.code].nextPointer = stepMap[step.next];
            stepMap[step.code].byRoadNextPointer = stepMap[step.byRoadNext];
        });

        let targetStep = stepMap[stepCode];

        let needDeleteStepCodes = [stepCode];
        if (targetStep.byRoadNextPointer != null && targetStep.byRoadNextPointer != undefined) {
            this.collectNeedDeleteSteps(stepMap, targetStep.byRoadNextPointer, needDeleteStepCodes);
        }
        
        steps.forEach(step => {
            if (step.next === stepCode) {
                step.next = targetStep.next;
            }
            if (step.byRoadNext === stepCode) {
                step.byRoadNext = targetStep.next;
            }
        });
        
        return steps.filter(step => needDeleteStepCodes.indexOf(step.code) < 0);
    }

    collectNeedDeleteSteps(stepMap, targetStep, needDeleteStepCodes) {
        needDeleteStepCodes.push(targetStep.code);
        if (targetStep.nextPointer != null && targetStep.nextPointer != undefined) {
            this.collectNeedDeleteSteps(stepMap, targetStep.nextPointer, needDeleteStepCodes);
        }
        if (targetStep.byRoadNextPointer != null && targetStep.byRoadNextPointer != undefined) {
            this.collectNeedDeleteSteps(stepMap, targetStep.byRoadNextPointer, needDeleteStepCodes);
        }
    }
    
    /**
     * [
     *  {'code': '***', 'type': 'input', 'comment': '***', next: '***', 'byRoadNext': '****'}
     * ]
     * 
    */
    analyseGraphJSONData(steps = []) {
        let flowStepObj = this.analyseFlowStepsJSON(steps);
        let startStepCode = Object.keys(flowStepObj).filter(key => START_STEP_TYPES.indexOf(flowStepObj[key].type) > -1)[0];
        return {
            nodes: this.generateNodes(flowStepObj),
            edges: this.generateEdges(flowStepObj[startStepCode])
        }
    }

    analyseFlowStepsJSON(steps = []) {
        let flowStepObj = {};
        steps.forEach(step => flowStepObj[step.code] = step);
        steps.forEach(step => {
            flowStepObj[step.code].nextPointer = flowStepObj[step.next];
            flowStepObj[step.code].byRoadNextPointer = flowStepObj[step.byRoadNext];
        });

        let startStep = steps.filter(step => START_STEP_TYPES.indexOf(step.type) > -1)[0];
        this.setStepCoordinate(startStep, -1, -1, true);
        return flowStepObj;
    }

    setStepCoordinate(step, lastX, lastY, isByRoadStep) {
        if (step === null || step === undefined) {
            return lastY;
        }

        if (isByRoadStep) {
            step.x = lastX + 1;
            step.y = lastY + 1;
        } else {
            step.x = lastX;
            step.y = lastY + 1;
        }
        
        let maxY = this.setStepCoordinate(step.byRoadNextPointer, step.x, step.y, true);
        maxY = this.setStepCoordinate(step.nextPointer, step.x, maxY, false);
        return maxY;
    }

    caculateNodeCoordinate(stepX, stepY, stepShape) {
        return {
            x: stepX * STEP_SPACE + (stepShape === 'circle' ? STEP_WIDTH - CIRCLE_STEP_WIDTH : 0)/2,
            y: stepY * STEP_SPACE,
        }
    }

    caculateByRoadVertices(stepX, stepY, stepShape) {
        return {
            x: (stepX + 1) * STEP_SPACE + STEP_WIDTH/2,
            y: stepY * STEP_SPACE + (stepShape === 'circle' ? CIRCLE_STEP_WIDTH : STEP_HEIGHT)/2,
        }
    }

    caculateByRoadNextCoordinate(stepX, stepY) {
        return {
            x: (stepX + 1) * STEP_SPACE + STEP_WIDTH/2, 
            y: (stepY + 1) * STEP_SPACE
        }
    }

    caculateNextCoordinate(stepX, stepY) {
        return {
            x: stepX * STEP_SPACE + STEP_WIDTH/2,
            y: (stepY + 1) * STEP_SPACE
        }
    }
    

    generateNodes(flowStepObj) {
        let nodes = [];
        for (let key in flowStepObj) {
            if (flowStepObj.hasOwnProperty(key)) {
                let step = flowStepObj[key];
                let nodeCoordinate = this.caculateNodeCoordinate(step.x, step.y, STEP_CONFIG[step.type].stepShape);
                nodes.push({
                    id: step.code,
                    shape: "custom-react-node",
                    data: {
                        comment: step.comment,
                        shape: STEP_CONFIG[step.type].stepShape,
                        chosen: this.state.selectedStepCode === step.code,
                        erasable: START_STEP_TYPES.indexOf(step.type) < 0 && END_STEP_TYPES.indexOf(step.type) < 0,
                        stepCode: step.code,
                        onDeleteStep: stepCode => {
                            let steps = this.deleteStep(stepCode);
                            this.setState({steps});

                            this.onStepsChange(steps);
                        }
                    },
                    width: STEP_CONFIG[step.type].stepShape === 'circle' ? CIRCLE_STEP_WIDTH : STEP_WIDTH,
                    height: STEP_CONFIG[step.type].stepShape === 'circle' ? CIRCLE_STEP_WIDTH : STEP_HEIGHT,
                    ...nodeCoordinate
                });
            }
        }
        return nodes;
    }

    generateEdges(step, edges=[]) {
        let nextTarget = null;
        if (step.nextPointer != null && step.nextPointer != undefined) {
            nextTarget = step.nextPointer.code;
        } else if (!STEP_CONFIG[step.type].endStep) {
            nextTarget = this.caculateNextCoordinate(step.x, step.y);
        }
        if (nextTarget != null) {
            edges.push({
                source: step.code,
                target: nextTarget,
                label: {
                    markup: Markup.getForeignObjectMarkup(),
                    attrs: {
                        fo: {
                            width: 120,
                            height: 30,
                            x: -60,
                            y: -15
                        }
                    },
                    data: {
                        previousStepCode: step.code,
                        isByRoad: false
                    }
                }
            });
        }

        let byRoadNextTarget = null;
        if (step.byRoadNextPointer != null && step.byRoadNextPointer != undefined) {
            byRoadNextTarget = step.byRoadNextPointer.code;
        } else if (STEP_CONFIG[step.type].byRoad) {
            byRoadNextTarget = this.caculateByRoadNextCoordinate(step.x, step.y)
        }

        if (byRoadNextTarget != null) {
            edges.push({
                source: step.code,
                target: byRoadNextTarget,
                vertices: [this.caculateByRoadVertices(step.x, step.y, STEP_CONFIG[step.type].stepShape)],
                label: {
                    markup: Markup.getForeignObjectMarkup(),
                    attrs: {
                        fo: {
                            width: 120,
                            height: 30,
                            x: -60,
                            y: -15
                        }
                    },
                    data: {
                        previousStepCode: step.code,
                        isByRoad: true
                    }
                }
            });
        }

        if (step.nextPointer != null && step.nextPointer != undefined) {
            this.generateEdges(step.nextPointer, edges);
        }

        if (step.byRoadNextPointer != null && step.byRoadNextPointer != undefined) {
            this.generateEdges(step.byRoadNextPointer, edges);
        }
        
        return edges;
    }

    render() {
        return (
            <div id={this.props.apiCode} style={{height: 'inherit'}}></div>
        );
    }

}

export default FlowGraphEditor;