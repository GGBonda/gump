import React from 'react';

import { Col, Row, Button, message } from 'antd';
import CodeEditor from 'components/monaco/codeEditor.jsx';

import * as gumpRpcService from 'model/actions/gumpRpcService.js';

class DebugPanel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            input: {},
            output: {},
            jsonParseSuccess: true
        }
    }

    componentDidMount() {
        const {input} = this.props.rpc;
        this.setState({input: this.analyseInputParam(input)});
    }

    componentDidUpdate(prevProps) {
        const input = this.props.rpc.input;
        const oldInput = prevProps.rpc.input;

        if (JSON.stringify(input) != JSON.stringify(oldInput)) {
            this.setState({input: this.analyseInputParam(input)});
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
        const {rpc={}} = this.props;
        const {input, jsonParseSuccess} = this.state;

        if (!jsonParseSuccess) {
            message.error('请输入正确入参');
            return ;
        }

        gumpRpcService.debug(rpc.code, JSON.stringify(input), data => {
            
            this.setState({output: data});
        });
    }

    render() {
        const {input, output} = this.state;

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
                        <Button type="primary" onClick={this.debug}>调试</Button>
                    </Col>
                    <Col span={10}>
                        <CodeEditor language={"json"} value={output} showLineNumbers={false} readOnly={true}/>
                    </Col>
                </Row>
            </div>
        );
    }

}

export default DebugPanel;