import React from 'react';

import { Col, Row, Button, message, Table } from 'antd';
import CodeEditor from 'components/monaco/codeEditor.jsx';

import * as gumpFlowService from 'model/actions/gumpFlowService.js';

import STEP_CONFIG from 'config/StepConfig.js';
import { string } from 'prop-types';
import { stringify } from 'qs';
const START_STEP_TYPES = Object.keys(STEP_CONFIG).filter(stepType => STEP_CONFIG[stepType].startStep);

class DebugPanel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            input: {},
            output: {},
            jsonParseSuccess: true,
            loading: false,
            logData: [],
            logFilter:[]
        }
    }

    componentDidMount() {
        const {api={}} = this.props;
        const {steps=[]} = api;

        const inputStep = steps.filter(step => START_STEP_TYPES.indexOf(step.type) > -1)[0];
        this.setState({input: this.analyseInputParam(inputStep.input)});
    }

    componentDidUpdate(prevProps) {
        const inputStep = this.props.api.steps.filter(step => START_STEP_TYPES.indexOf(step.type) > -1)[0];
        const oldInputStep = prevProps.api.steps.filter(step => START_STEP_TYPES.indexOf(step.type) > -1)[0];

        if (JSON.stringify(inputStep) != JSON.stringify(oldInputStep)) {
            this.setState({input: this.analyseInputParam(inputStep.input)});
        }
    }

    analyseInputParam = (input=[]) => {
        let inputParam = {};
        input.forEach(paramItem => {
            const {code, type} = paramItem;
            let {defaultValue, children=[]} = paramItem;

            if (children === null) { children = []}
            switch (type) {
                case 'object': 
                    if (children.length > 0) {
                        defaultValue = this.analyseInputParam(children);
                    } else if (defaultValue === null || defaultValue === undefined || defaultValue === "") {
                        defaultValue = {};
                    } else {
                        try {
                            defaultValue = JSON.parse(defaultValue);
                        } catch (error) {
                            defaultValue = {};
                        }
                    }
                    break;
                case 'array': 
                    if (children.length > 0) {
                        defaultValue = [this.analyseInputParam(children)];
                    } else if (defaultValue === null || defaultValue === undefined || defaultValue === "") {
                        defaultValue = [];
                    } else {
                        try {
                            defaultValue = JSON.parse(defaultValue);
                        } catch (error) {
                            defaultValue = [];
                        }
                    }
                    break;
                case 'string': 
                    if (defaultValue === null || defaultValue === undefined) {
                        defaultValue = "";
                    }
                    break;
                case 'boolean':
                    if (defaultValue === null || defaultValue === undefined) {
                        defaultValue = true;
                    }
                    break;
                case 'number': 
                    if (defaultValue === null || defaultValue === undefined || defaultValue === "") {
                        defaultValue = 0;
                    } else {
                        defaultValue = parseFloat(defaultValue);
                    }
                    break;
            }
            inputParam[code] = defaultValue;
        });
        return inputParam;
    }

    debug = () => {
        const {api={}} = this.props;
        const {input, jsonParseSuccess} = this.state;

        if (!jsonParseSuccess) {
            message.error('请输入正确入参');
            return ;
        }
        this.setState({loading: true});

        gumpFlowService.debug(api.code, JSON.stringify(input), data => {
            this.setState({
                output: data,
                loading: false
            });

            gumpFlowService.debugLog(data.traceId, logData => {
                let logFilter = [];
                if (logData.length > 0) {
                    logData.forEach(item => logFilter.push({
                        text: item.stepCode,
                        value: item.stepCode
                    }))
                }
                this.setState({logData, logFilter});
            })
        });
    }

    render() {
        const {input, output, loading, logData=[], logFilter=[]} = this.state;

        return (
            <div>
                <Row justify="space-between" align="middle">
                    <Col span={10}>
                        <CodeEditor language={"json"} value={input} showLineNumbers={false}
                            onChange={value => {
                                try {
                                    let param = JSON.parse(value);
                                    this.setState({
                                        input: param,
                                        jsonParseSuccess: true
                                    });
                                } catch (error) {
                                    this.setState({jsonParseSuccess: false});
                                }
                            }}/>
                    </Col>
                    <Col span={2}>
                        <Button type="primary" loading={loading} onClick={this.debug}>调试</Button>
                    </Col>
                    <Col span={10}>
                        <CodeEditor language={"json"} value={output} showLineNumbers={false} readOnly={true}/>
                    </Col>
                </Row>

                <div style={{marginTop: "24px"}}>
                    <Table bordered={true} dataSource={logData} columns={[
                        {title: '环节', dataIndex: 'stepCode', key: 'stepCode', width: '15%', 
                            filters: logFilter, filterMode: 'tree', filterSearch: true, onFilter: (value, record) => record.stepCode === value},
                        {title: '输出', dataIndex: 'output', key: 'output', width: '75%', ellipsis: true, render: text => JSON.stringify(text)},
                        {title: '耗时(ms)', dataIndex: 'consumeTime', key: 'consumeTime', width:"10%"}
                    ]}/>
                </div>
            </div>
        );
    }

}

export default DebugPanel;