import React from 'react';

import { Form } from 'antd';

import ParamEditTable from '../../util/paramEditTable.jsx';


class OnputStepConfig extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            formRef: null
        }
    }

    componentWillMount() {
        
    }

    validate = async () => {
        const {formRef} = this.state;
        return await formRef.validateFields();
    }

    resetValues = () => {
        const {formRef} = this.state;
        formRef.resetFields();
    }

    render() {
        const {step={}} = this.props;

        return (
            <Form ref={node => this.state.formRef = node} initialValues={step}>
                <Form.Item label="入参" name="output">
                    <ParamEditTable/>
                </Form.Item>
            </Form>
        )
    }

}

export default OnputStepConfig;