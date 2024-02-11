import React from 'react';

import { Spin } from 'antd';

import FlowGraphEditor from 'components/apiDesign/x6/FlowGraphEditor.jsx';
import ApiDesignSideBar from './sideBar/apiDesignSideBar.jsx';
import MetadataDesignTab from 'components/metadataDesignTab.jsx';

import * as constants from '../../Constants.js';


export const ApiContext = React.createContext({});
class ApiDesignTab extends MetadataDesignTab {

    constructor(props) {
        super(props);
        this.state = {
            api: null,
            selectedStep: null
        };
    }

    componentDidMount() {
       const {fileNode} = this.props;
       const {id, type, code, desc} = fileNode;

       super.loadMetadata(id, metadata => {
           if (metadata === null || metadata === undefined) {
                metadata = {
                    appCode: new URLSearchParams(window.location.search).get('appCode'), 
                    nodeId: id, desc, code, type, 
                    editingStepCode: 'input',
                    steps: [
                        {code: 'input', type: 'input', comment:'input', next: 'output'},
                        {code: 'output', type: 'output', comment:'output'}
                    ]
                }
           }
           this.setState({api: {...metadata}});
       });
    }

    saveMetadata(savedCallback) {
        let {api} = this.state;
        let metadata = {...api}
        super.saveMetadata(metadata, savedCallback); 
    }

    onConfirmStepConfig = configValue => {
        const {api, selectedStep} = this.state;

        if (selectedStep === null) {
            return;
        }
        let newApi = {...api}
        let newSelectedStep = {
            ...selectedStep,
            ...configValue
        };

        let targetIndex = newApi.steps.findIndex(step => step.code === newSelectedStep.code);
        newApi.steps.splice(targetIndex, 1, newSelectedStep);

        this.setState({
            api: newApi,
            selectedStep: newSelectedStep
        });

        if (this.props.onMetadataChange != undefined && typeof this.props.onMetadataChange === 'function') {
            this.props.onMetadataChange(newApi);
        }
    }

    onStepsChange = steps => {
        const {api} = this.state;

        const newApi = {...api, steps};
        this.setState({api: newApi});

        if (this.props.onMetadataChange != undefined && typeof this.props.onMetadataChange === 'function') {
            this.props.onMetadataChange(newApi);
        }
    }

    render() {
        const {api, selectedStep} = this.state;

        if (api === null) {
            return (
                <div style={{height: '100vh', textAlign: 'center', paddingTop: '35vh'}}>
                    <Spin size="large"/>
                </div>
            );
        } else {
            return (
                <div style={{display: "flex", flexFlow: "row wrap", height: 'calc(100vh - 106px)'}}>
                    <div style={{width: 'calc(100% - 40px)', height: 'inherit'}}>
                        <FlowGraphEditor apiCode={api.code} steps={api.steps} 
                            onSelectStep={selectedStepCode => this.setState({selectedStep: api.steps.filter(step => step.code === selectedStepCode)[0]})} 
                            onStepsChange={this.onStepsChange}/>
                    </div>
                    <div style={{width:"40px", height: 'inherit'}}>
                        <ApiContext.Provider value={{api, step: selectedStep}}>
                            <ApiDesignSideBar onConfirmStepConfig={this.onConfirmStepConfig}/>
                        </ApiContext.Provider>
                    </div>
                </div>
            );
        }
    }

}

export default ApiDesignTab;