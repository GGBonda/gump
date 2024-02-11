import React from 'react';

import 'css/jarvis.css';

import { Form, Input, Col, Row } from 'antd';

import ParamEditTable from 'components/util/paramEditTable.jsx';
import CodeEditor from 'components/monaco/codeEditor.jsx';


class RpcEditor extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            formRef: null
        }
    }

    componentDidMount() {
    
    }

    validate = async () => {
        const {formRef} = this.state;
        return await formRef.validateFields();
    }

    render() {
        const {rpc={}} = this.props;

        return (
            <div style={{height: 'inherit', overflow: "auto", padding:"0 1.5%"}}>
            <Form ref={node => this.state.formRef = node} initialValues={rpc} onValuesChange={(changedValues, allValues) => {
                if (this.props.onValueChange != undefined && typeof this.props.onValueChange === 'function') {
                    this.props.onValueChange(allValues);
                }
            }}>
                
                <div><h3 className="titleDecoration">基本信息</h3></div>
                <div style={{marginLeft:"30px", marginBottom:"2%"}}>
                    <Row>
                        <Col span={8}>
                            <Form.Item label="code" name="code">
                                <Input disabled={true}/>
                            </Form.Item>
                        </Col>
                        <Col span={8} offset={4}>
                            <Form.Item label="描述" name="desc">
                                <Input disabled={true}/>
                            </Form.Item>
                        </Col>
                    </Row>
                
                    <Row>
                        <Col span={8}>
                            <Form.Item label="应用" name="application" rules={[{required: true,message: '请输入应用名'}]}>
                                <Input />
                            </Form.Item>
                        </Col>

                        <Col span={8} offset={4}>
                            <Form.Item label="接口" name="service" rules={[{required: true,message: '请输入接口'}]}>
                                <Input />
                            </Form.Item>
                        </Col>
                    </Row>

                    <Row><Col span={8}>
                        <Form.Item label="方法" name="method" rules={[{required: true,message: '请输入方法'}]}>
                            <Input />
                        </Form.Item>
                    </Col></Row>
                </div>

                <div><h3 className="titleDecoration">入参</h3></div>
                <div style={{marginLeft:"30px", marginBottom:"2%"}}>
                    <Form.Item label="入参结构" name="input">
                        <ParamEditTable showColumns={['code', 'type', 'defaultValue', 'operation', 'comment']}/>
                    </Form.Item>
                </div>

                <div><h3 className="titleDecoration">出参</h3></div>
                <div style={{marginLeft:"30px", marginBottom:"5%"}}>
                    <Form.Item label="预处理" name="script">
                        <CodeEditor height={"50vh"} language={"java"}/>
                    </Form.Item>

                    <Form.Item label="出参结构" name="output">
                        <ParamEditTable valueEditType='input'/>
                    </Form.Item>
                </div>
            </Form>
            </div>
        )
    }

}

export default RpcEditor;