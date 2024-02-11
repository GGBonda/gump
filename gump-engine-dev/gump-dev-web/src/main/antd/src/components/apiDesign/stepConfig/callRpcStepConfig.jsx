import React from 'react';

import { Form } from 'antd';

import MetadataSelect from 'components/util/metadataSelect.jsx';
import ParamEditTable from 'components/util/paramEditTable.jsx';

import * as MetadataConstants from 'config/MetadataConstants.js';
import * as gumpMetadataService from 'model/actions/gumpMetadataService.js';

class CallRpcStepConfig extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            formRef: null,
            rpcInfo: {}
        }
    }

    componentDidMount() {
        const {step={}} = this.props;
        if (step.rpcId === undefined || step.rpcId === null) {
            return;
        }

        const rpcId = step.rpcId.id;
        gumpMetadataService.queryMetadataDetailByNodeId(rpcId, data => {
            const {application, service, method} = data;
            this.setState({
                rpcInfo: {application, service, method}
            })
        });
    }

    validate = async () => {
        const {formRef} = this.state;
        const value = await formRef.validateFields();
        return {
            ...value,
            rpcId: {
                id: value.rpcId
            }
        };
    }

    resetValues = () => {
        const {formRef} = this.state;
        formRef.resetFields();
        this.setState({rpcInfo: {}});
    }

    onRpcSelected = (rpcId, {content = {}}) => {
        const {input} = content;
        const {formRef} = this.state;

        formRef.setFieldValue("input", input);
        this.setState({rpcInfo: content});
    }

    render() {
        const {rpcInfo={}} = this.state;
        const {step={}} = this.props;
        const {rpcId={}} = step;

        return (
            <Form ref={node => this.state.formRef = node} initialValues={{...step, rpcId: rpcId.id}}>
                <Form.Item label="rpc" name="rpcId">
                    <MetadataSelect type={MetadataConstants.RPC_METADATA_TYPE} onChange={this.onRpcSelected}/>
                </Form.Item>

                <div style={{height: "170px", border: "1px solid #ccc", background: "#fafafa", borderRadius: "5px", padding: "10px 20px", marginBottom: "30px", color: "#00000073"}}>
                    <p><span>应用: </span><span>{rpcInfo.application}</span></p>
                    <p><span>服务: </span><span>{rpcInfo.service}</span></p>
                    <p><span>方法: </span><span>{rpcInfo.method}</span></p>
                </div>

                <Form.Item label="入参" name="input">
                    <ParamEditTable showColumns={['code', 'type', 'value', 'comment']} forBidEditColumns={['code', 'type']}/>
                </Form.Item>
            </Form>
        )
    }

}

export default CallRpcStepConfig;