import React from 'react';

import { Form } from 'antd';

import StepValueEditor from 'components/util/stepValueEditor';

class IfStepConfig extends React.Component {

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
        const {condition} = await formRef.validateFields();
        return {
            condition:{
                code: "condition",
                type: "boolean",
                value: condition
            }
        };
    }

    resetValues = () => {
        const {formRef} = this.state;
        formRef.resetFields();
    }

    render() {
        const {step={}} = this.props;
        const {condition={}} = step;
        
        return (
            <Form ref={node => this.state.formRef = node} initialValues={{condition: condition.value}}>
                <Form.Item label="判断条件" name="condition">
                    <StepValueEditor />
                </Form.Item>
            </Form>
        )
    }

}

export default IfStepConfig;