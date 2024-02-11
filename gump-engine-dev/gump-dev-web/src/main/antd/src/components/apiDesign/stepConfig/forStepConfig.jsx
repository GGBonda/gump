import React from 'react';

import { Form, InputNumber, Select } from 'antd';

import StepValueEditor from 'components/util/stepValueEditor';

class ForStepConfig extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            formRef: null,
            paramType: 'number'
        }
    }

    componentWillMount() {
        
    }

    validate = async () => {
        const {formRef, paramType} = this.state;
        const {cycleParam} = await formRef.validateFields();
        return {
            cycleParam:{
                code: "cycleParam",
                type: paramType,
                value: cycleParam
            }
        };
    }

    resetValues = () => {
        const {formRef} = this.state;
        formRef.resetFields();
    }

    render() {
        const {step={}} = this.props;
        const {cycleParam={}} = step;

        const {paramType} = this.state;
        
        return (
            <Form ref={node => this.state.formRef = node} initialValues={{cycleParam: cycleParam.value}}>
                <Form.Item label="参数类型" name="paramType">
                    <Select defaule={'number'} options={[
                        {label: "数字", value: "number"},
                        {label: "数组", value: "array"}
                    ]} onChange={value => this.setState({paramType: value})}/>
                </Form.Item>

                <Form.Item label="循环参数" name="cycleParam">
                    <StepValueEditor />
                </Form.Item>

                {
                    paramType === 'array' ? 
                    <Form.Item label="步长" name="step">
                        <InputNumber min={1} />
                    </Form.Item> : null
                }
            </Form>
        )
    }

}

export default ForStepConfig;