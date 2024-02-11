import React from 'react';

import { Button, Drawer } from 'antd';

import * as objectUtil from 'util/objectUtil.js'; 
import * as STEP_CONFIG from 'config/StepConfig.js';
import {ApiContext} from '../apiDesignTab';
import SideBar from '../../sideBar/sideBar.jsx';

import InputStepConfig from '../stepConfig/inputStepConfig.jsx';
import OutputStepConfig from '../stepConfig/outputStepConfig.jsx';
import IfStepConfig from '../stepConfig/ifStepConfig.jsx';
import ForStepConfig from '../stepConfig/forStepConfig.jsx';
import CallRpcStepConfig from '../stepConfig/CallRpcStepConfig.jsx';

import CodeEditor from 'components/monaco/codeEditor.jsx';
import DebugPanel from './debugPanel.jsx';

class ApiDesignSideBar extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            configRef: null,
            activeKey: null        
        }
    }

    componentDidMount() {
    
    }

    stepConfig = step => {
        if (step === null) {
            return null;
        }
        const {code, type} = step;
        switch (type) {
            case STEP_CONFIG.INPUT_STEP_TYPE: return <InputStepConfig key={code} ref={node => this.state.configRef = node} step={step}/>;
            case STEP_CONFIG.OUTPUT_STEP_TYPE: return <OutputStepConfig key={code} ref={node => this.state.configRef = node} step={step}/>;
            case STEP_CONFIG.FOR_STEP_TYPE: return <ForStepConfig key={code} ref={node => this.state.configRef = node} step={step}/>;
            case STEP_CONFIG.IF_STEP_TYPE: return <IfStepConfig key={code} ref={node => this.state.configRef = node} step={step}/>;
            case STEP_CONFIG.RPC_STEP_TYPE: return <CallRpcStepConfig key={code} ref={node => this.state.configRef = node} step={step}/>;
            default: return <div></div>
        }
    }

    onConfirmStepConfig = async () => {
        const {configRef} = this.state;
        
        let configValue = null;
        if (configRef != null) {
            configValue = await configRef.validate();

            this.setState({activeKey: null});

            if (this.props.onConfirmStepConfig != undefined && typeof this.props.onConfirmStepConfig === 'function') {
                this.props.onConfirmStepConfig(objectUtil.deepClone(configValue));
            }
        }
    }

    resetStepConfig = () => {
        const {configRef} = this.state;
        if (configRef != null) {
            configRef.resetValues();
        }
    }

    onStepConfigDrawerClose = () => {
        this.resetStepConfig();
        this.setState({activeKey: null});
    }

    onDrawerClose = () => {
        this.setState({activeKey: null});
    }

    render() {
        const {activeKey} = this.state;

        return (
            <ApiContext.Consumer>
                 {
                     ({api, step}) => {
                         return <div>
                            <SideBar activeKey={activeKey} items={[
                                {
                                    key: 'stepConfig', label: '环节', drawerTitle: step === null ? null : step.code, onClose: this.onStepConfigDrawerClose,
                                    children: this.stepConfig(step),
                                    footer: (
                                        <div style={{textAlign:'center'}}>
                                            <Button type="primary" onClick={this.onConfirmStepConfig}>确认</Button>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <Button onClick={this.onStepConfigDrawerClose}>取消</Button>
                                        </div>
                                    )
                                }, 
                                {
                                    key: 'debugConfig', label: '调试', onClose: this.onDrawerClose, 
                                    children: <DebugPanel api={objectUtil.deepClone(api)}/>
                                }, 
                                {
                                    key: 'apiJson', label: 'json', onClose: this.onDrawerClose,
                                    children: <CodeEditor height={"70vh"} language={"json"} readOnly={true} value={api}/>
                                }
                            ]} onClick={key => {
                                if (activeKey === 'stepConfig') {
                                    this.resetStepConfig();
                                }
                                this.setState({activeKey: key === activeKey ? null : key});
                            }}/>
                        </div>
                     }
                 }
            </ApiContext.Consumer>
        )
    }

}

export default ApiDesignSideBar;