import React, {useContext, useEffect, useState} from 'react';
import { Tag, Popover, Modal, Button, Row, Col, Tree, Tabs } from 'antd';
import { GoNumber } from "react-icons/go";
import { MdSegment, MdDataArray, MdDataObject, MdTextFields } from "react-icons/md";

import CodeEditor from 'components/monaco/codeEditor.jsx';

import {ApiContext} from 'components/apiDesign/apiDesignTab.jsx';

import * as gumpFunctionService from 'model/actions/gumpFunctionService.js';

const StepsContext = props => {
    const {onSelect} = props;

    const {api, step} = useContext(ApiContext);

    const {steps = []} = api;
    let sonParentMap = {};
    steps.forEach(item => {
        sonParentMap[item.next] = item;
        sonParentMap[item.byRoadNext] = item;
    });

    let ancestorsSteps = [];
    const recursionSearchAncestorsSteps = stepCode => {
        if (stepCode === null || stepCode === undefined || stepCode === "" || sonParentMap[stepCode] === null || sonParentMap[stepCode] === undefined) {
            return;
        }
        ancestorsSteps.unshift(sonParentMap[stepCode]);
        recursionSearchAncestorsSteps(sonParentMap[stepCode].code);
    }

    recursionSearchAncestorsSteps(step.code);

    const recursionTransParamToTreeNode = (parentKey, params=[]) => {
        let treeNodes = [];
        params.forEach(param => {
            const { code, type, children=[] } = param;
            
            let icon = null;
            switch (type) {
                case 'object': icon = <MdDataObject />; break;
                case 'array': icon = <MdDataArray />; break;
                case 'string': icon = <MdTextFields />; break;
                case 'number': icon = <GoNumber />; break;
            }

            const key = parentKey +'.'+ code;
            let node = {
                title: <TreeNode title={code} onDoubleClick={() => onSelect('$.' + key)}/>,
                key, icon
            };
            if (type === 'object' && children != null && children.length > 0) {
                node.children = recursionTransParamToTreeNode(node.key, children);
            }
            treeNodes.push(node);
        });
        return treeNodes;
    }

    let treeNodes = [];
    ancestorsSteps.forEach(step => {
        const {code, type} = step;
        let node = {
            title: <TreeNode title={code} onDoubleClick={() => onSelect("$." + code)}/>,
            key: code,
            icon: <MdSegment />
        };
        switch (type) {
            case 'input': 
                node.children = recursionTransParamToTreeNode(node.key, step.input);
                break;
            case 'for':
                node.children = [
                    {title: <TreeNode title={'index'} onDoubleClick={() => onSelect(`$.${node.key}.index`)}/>, key: `${node.key}.index`, icon: <GoNumber />},
                    {title: <TreeNode title={'items'} onDoubleClick={() => onSelect(`$.${node.key}.items`)}/>, key: `${node.key}.items`, icon: <MdDataArray />},
                    {title: <TreeNode title={'results'} onDoubleClick={() => onSelect(`$.${node.key}.results`)}/>, key: `${node.key}.results`, icon: <MdDataArray />},
                ];
                break;
            default: 
        }
        treeNodes.push(node);
    });

    return (
        <div>
            <Tree showIcon  treeData={treeNodes} 
             style={{height: "500px", background: "#fafafa", border: "1px solid #ccc", borderRadius: "5px"}}/>
        </div>
    )
}

const TreeNode = props => {
    const {title, onDoubleClick} = props;
    return (
        <span onDoubleClick={onDoubleClick}>
            {title}
        </span>
    );
}

const GumpFunctionTabs = props => {
    const {onSelect} = props;
    const [items, setItems] = useState(null);
    useEffect(() => {
        gumpFunctionService.queryGumpFunctions((gumpFunctions = []) => {
            setItems(gumpFunctions.map((fc, index) => {
                const {code, desc, functionItems=[]} = fc;
                return {
                    key: index, label: desc,
                    children: (
                        <div style={{height: "170px", border: "1px solid #ccc", background: "#fafafa", borderRadius: "5px", padding: "5px"}}>
                            {
                                functionItems.map(functionItem => 
                                    <Tag color="green" style={{cursor: "pointer", userSelect: "none"}}
                                        onDoubleClick={() => onSelect(functionItem.defaultInsertCode)}>
                                        {functionItem.code}
                                    </Tag>
                                )
                            }
                        </div>
                    )
                }
            }))
        });
    }, [])
    
    return (
        <Tabs size='small' type="card" items={items} />
    )
}

class StepValueEditor extends React.Component {
    constructor(props) {
        super(props);
        const {value} = this.props;
        this.state = {
            value,
            modalVisible: false,
            codeEditorRef: null
        };
    }

    render() {
        let {value, modalVisible} = this.state;
        const {onChange} = this.props; 
        
        let trigger = null;
        if (value === null || value === undefined || value === "") {
            trigger = <Button shape="round" size="small" type="primary" ghost onClick={() => this.setState({modalVisible: true})}>赋值</Button>
        } else {
            trigger = (
                <Popover content={value}>
                    <Tag color="green" style={{textOverflow: 'ellipsis', overflow: 'hidden', maxWidth: "80px"}} onClick={() => this.setState({modalVisible: true})}>{value}</Tag>
                </Popover>
            );
        }

        return (
            <div>
                {trigger}

                <Modal title="编辑表达式" open={modalVisible} width={900} cancelText={"取消"} okText={"确认"} 
                        onOk={() => {
                            onChange(value);
                            this.setState({modalVisible: false})
                        }}
                        onCancel={() => this.setState({modalVisible: false})}>
                    <Row gutter={16}>
                        <Col span={6}><StepsContext onSelect={code => this.state.codeEditorRef.insertText(code)}/></Col>
                        <Col span={12}>
                            <div>
                                <GumpFunctionTabs onSelect={code => this.state.codeEditorRef.insertText(code)}/>
                            </div>

                            <div style={{marginTop: "16px"}}>
                                <CodeEditor value={value} ref={node => this.state.codeEditorRef = node} height="260" showLineNumbers={false}
                                            onChange={(v) => this.setState({value: v})}/>
                            </div>
                        </Col>
                        <Col span={6}>
                            <div style={{height: "500px", border: "1px solid #ccc", background: "#fafafa", borderRadius: "5px", padding: "5px"}}></div>
                        </Col>
                    </Row>
                </Modal>
            </div>
        )
    }
}

export default StepValueEditor;