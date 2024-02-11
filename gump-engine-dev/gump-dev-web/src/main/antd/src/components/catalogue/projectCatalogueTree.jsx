import React from 'react';

import { Tree } from 'antd';
import {HomeOutlined, FlagOutlined} from '@ant-design/icons';

import * as gumpDirectoryNodeService from 'model/actions/gumpDirectoryNodeService.js';

import TreeNodeTitle from './treeNodeTitle.jsx';
import * as MetadataConstants from 'config/MetadataConstants.js';

class ProjectCatalogueTree extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            treeData:[],
            expendedTreeNodeKey: []
        }
    }

    transferToTreeNode (node) {
        const {openFile} = this.props;
        const {id, type, folder, parentId} = node;
        return {
            id, key: id.toString(), metadataType: type,
            title: <TreeNodeTitle node={node} 
                        onAddChildSuccess={() => this.refreshChildNodes(type, id)} 
                        onEditNodeSuccess={() => this.refreshChildNodes(type, parentId)}
                        onDoubleClickFileNode={openFile}/>,
            isLeaf: !folder
        }
    }

    refreshChildNodes (metadataType, nodeId) {
        gumpDirectoryNodeService.queryChildDirectoryNodes(metadataType, nodeId, (childNodes=[]) => {
            let {treeData} = this.state;
            let treeDataMap = this.floatTreeData(treeData);

            let treeNode = nodeId ? treeDataMap[metadataType + nodeId] : treeDataMap[metadataType];

            treeNode.children = childNodes.map(node => {
                let treeNode = this.transferToTreeNode(node);

                treeNode.children = treeDataMap[node.type + node.id] != null ? treeDataMap[node.type + node.id].children : null;
                return treeNode;
            })

            let newTreeData = treeData.map(item => ({...item}));

            this.setState({treeData: newTreeData});
        });
    }

    floatTreeData = treeData => {
        let treeDataMap = {};

        if (treeData === null || treeData === undefined || treeData.length === 0) {
            return treeDataMap;
        }
        
        treeData.forEach(item => {
            if (item.id) {
                treeDataMap[item.metadataType + item.id] = item;
            } else {
                treeDataMap[item.metadataType] = item;
            }
            let childTreeMap = this.floatTreeData(item.children);
            treeDataMap = {...treeDataMap, ...childTreeMap};
        });
        return treeDataMap;
    }

    componentDidMount() {
        this.setState({
            treeData: [
                {
                    title: 'TestApp',
                    key: '0',
                    icon: <HomeOutlined/>,
                    children: [
                        {
                            title: <TreeNodeTitle node={{code: "FLOW", type: MetadataConstants.FLOW_METADATA_TYPE, folder: true}} showEdit={false} showDetail={false} showDelete={false} icon={<FlagOutlined />}
                                                onAddChildSuccess={() => this.refreshChildNodes("flow", null)}/>,
                            key: '0-1',
                            metadataType: "flow"
                        },
                        {
                            title: <TreeNodeTitle node={{code: "RPC", type: MetadataConstants.RPC_METADATA_TYPE, folder: true}} showEdit={false} showDetail={false} showDelete={false} icon={<FlagOutlined />}
                                                onAddChildSuccess={() => this.refreshChildNodes("rpc", null)}/>,
                            key: '0-2',
                            metadataType: "rpc"
                        },
                    ]
                }
            ],
            expendedTreeNodeKey: ['0']
        });
    }

    render() {

        return (
            <div style={{zIndex: -1}}>
                <Tree showIcon blockNode
                    loadData={async ({id, metadataType, key, children}) => {
                        if (children) {
                            return;
                        }

                        this.refreshChildNodes(metadataType, id, key);
                    }}
                    onSelect={() => {}}
                    onExpand={() => {}}
                    treeData={this.state.treeData}
                    />
            </div>
        )
    }

}

export default ProjectCatalogueTree;