import React from 'react';
import PropTypes from 'prop-types';

import { Tabs } from 'antd';

import ApiDesignTab from './apiDesign/apiDesignTab.jsx';
import RpcDesignTab from './rpcDesign/rpcDesignTab.jsx';


class MetadataDesignPanel extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            activeKey: "",
            metadataItems: []
        }
    }

    componentDidMount() {
        document.onkeydown = e => {
            if (e.key === 's' && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)) {
                e.preventDefault();
                this.saveMetadata();
            }
        }
    }

    saveMetadata = () => {
        const {activeKey, metadataItems} = this.state;
        let selectMetadatas = metadataItems.filter(metadata => metadata.key === activeKey);

        if (selectMetadatas === null || selectMetadatas.length < 1) {
            return;
        }

        let selectMetadata = selectMetadatas[0];
        const savedCallback = () => {
            if (selectMetadata.label.startsWith('*')) {
                selectMetadata.label = selectMetadata.label.substr(1);
                this.setState({metadataItems});
            }
        }

        selectMetadata.childrenRef.current.saveMetadata(savedCallback);
    }

    metadataNotSave = (code, type) => {
        const {metadataItems} = this.state;
        const metadata = metadataItems.filter(item => item.key.split("_")[0] === code && item.key.split("_")[1] === type)[0];

        if (metadata.label.indexOf('*') < 0) {
            metadata.label = '*' + metadata.label;
            this.setState({metadataItems});
        }
    }

    metadataSaved = (code, type) => {
        const {metadataItems} = this.state;
        const metadata = metadataItems.filter(item => item.key.split("_")[0] === code && item.key.split("_")[1] === type)[0];

        if (metadata.label.indexOf('*') < 0) {
            metadata.label = '*' + metadata.label;
            this.setState({metadataItems});
        }
    }

    pushMetadataItem = fileNode => {
        const {type, code} = fileNode;
        const {metadataItems} = this.state;

        const key = `${code}_${type}`;
        const targetMetadata = metadataItems.filter(item => item.key === key);
        if (targetMetadata.length > 0) {
            this.setState({activeKey: key});
            return ;
        }

        let childrenRef = React.createRef(null);

        if (type === 'flow') {
            metadataItems.push({
                key, label: code, childrenRef,
                children: <ApiDesignTab ref={childrenRef} fileNode={fileNode} onMetadataChange={() => this.metadataNotSave(code, type)}/>
            });
        }

        if (type === 'rpc') {
            metadataItems.push({
                key, label: code, childrenRef,
                children: <RpcDesignTab ref={childrenRef} fileNode={fileNode} onMetadataChange={() => this.metadataNotSave(code, type)}/>
            });
        }

        this.setState({
            activeKey: key,
            metadataItems: [...metadataItems]
        });
    }

    removeTab = targetKey => {
        let {activeKey, metadataItems} = this.state;
        const targetIndex = metadataItems.findIndex(item => item.key === targetKey);

        if (metadataItems.length > 1 && targetKey === activeKey) {
            let {key} = metadataItems[(targetIndex === metadataItems.length - 1) ? targetIndex - 1 : targetIndex + 1];
            activeKey = key;
        }

        metadataItems = metadataItems.filter(item => item.key != targetKey);
        this.setState({activeKey, metadataItems});
    }

    render() {
        const {metadataItems} = this.state;

        return (
            <Tabs hideAdd type="editable-card" activeKey={this.state.activeKey} items={metadataItems} 
                onChange={activeKey => this.setState({activeKey})}
                onEdit={(targetKey, action) => {
                    if (action === 'remove') {
                        this.removeTab(targetKey);
                    }
                }}/>
        );
    }

}

MetadataDesignPanel.contextTypes = {
    store : PropTypes.object
}

export default MetadataDesignPanel;