import React from 'react';

import { Spin } from 'antd';

import MetadataDesignTab from 'components/metadataDesignTab.jsx';

import RpcEditor from './rpcEditor.jsx';
import RpcDesignSideBar from './sideBar/rpcDesignSideBar.jsx';
import * as MetadataConstants from 'config/MetadataConstants.js';


const DEFAULT_GROOVY_SCRIPT = "package org.junhui.gump.rpc.core.groovy\n" +
                                "\n" +
                                "import org.junhui.gump.rpc.core.groovy.RpcInvokeResponseHandler;\n" +
                                "import org.junhui.gump.rpc.core.groovy.RpcResponseContext;\n" +
                                "\n" +
                                "class RpcInvokeResponseHandlerImpl implements RpcInvokeResponseHandler {\n" +
                                "\n" +
                                "   public Object handle(RpcResponseContext context) {\n" +
                                "       Object response = context.getParam();\n" +
                                "       \n" +
                                "       return response;\n" +
                                "   }\n" +
                                "}\n";

class RpcDesignTab extends MetadataDesignTab {
    constructor(props) {
        super(props);
        this.state = {
            rpc: null,
            editorRef: null
        }
    }

    componentDidMount() {
        const {fileNode} = this.props;
        const {id, type, code, desc} = fileNode;
 
        super.loadMetadata(id, metadata => {
            if (metadata === null || metadata === undefined) {
                metadata = {
                    appCode: new URLSearchParams(window.location.search).get('appCode'), 
                    desc, code, type,
                    script: DEFAULT_GROOVY_SCRIPT
                }
            }
            this.setState({rpc: {...metadata}});
        });
     }

    saveMetadata(savedCallback) {
        const {fileNode} = this.props;
        const {id, type, code, desc} = fileNode;

        const res = this.state.editorRef.validate();
        res.then(values => {
            let metadata = {
                nodeId: id, desc, code, type, 
                ...values
            }
            super.saveMetadata(metadata, savedCallback); 
        }).catch(errorInfo => {
            console.log("fail", errorInfo)
        })
    }

    onRpcChange = rpc => {
        this.setState({rpc: {...rpc}});
        if (this.props.onMetadataChange != undefined && typeof this.props.onMetadataChange === 'function') {
            this.props.onMetadataChange();
        }
    }

    render() {
        const {rpc} = this.state;

        if (rpc === null) {
            return (
                <div style={{height: '100vh', textAlign: 'center', paddingTop: '35vh'}}>
                    <Spin size="large"/>
                </div>
            );
        } else {
            return (
                <div style={{display: "flex", flexFlow: "row wrap", height: 'calc(100vh - 106px)'}}>
                    <div style={{width: 'calc(100% - 40px)', height: 'inherit'}}>
                        <RpcEditor ref={node => this.state.editorRef = node} rpc={rpc} onValueChange={rpc => this.onRpcChange(rpc)}/>
                    </div>

                    <div style={{width:"40px", height: 'inherit'}}>
                        <RpcDesignSideBar rpc={rpc}/>
                    </div>
                </div>
            );
        }
    }
}

export default RpcDesignTab;