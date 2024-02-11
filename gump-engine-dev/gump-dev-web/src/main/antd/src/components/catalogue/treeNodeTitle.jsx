import React, {useEffect, useState} from 'react';

import { Col, Row, Space, Dropdown, Modal, Form, Input, Button, Tooltip } from 'antd';
import {PlusOutlined, DeleteOutlined, EditOutlined, FolderOutlined, FileOutlined} from '@ant-design/icons';

import GumpDirectoryNode from 'model/gumpDirectoryNode.js';
import * as gumpDirectoryNodeService from 'model/actions/gumpDirectoryNodeService.js';

const CreateFolderModal = props => { 
    const [submitButtonLoading, setSubmitButtonLoading] = useState(false);

    const {visible, onCancel, onCreateSuccess} = props;
    const {treeNodeId, metadataType} = props;
    const [form] = Form.useForm();

    const closeModal = () => {
        onCancel();
        setSubmitButtonLoading(false);
        form.resetFields();
    }
    return (
        <Modal title="新建文件夹" maskClosable={false} open={visible} footer={null} onCancel={closeModal}>
            <Form form={form}>
                <Form.Item label="文件夹名" name="folderName" rules={[
                    {required: true, message: '请输入文件夹名'}, 
                    {max: 20, message: "不能超过20个字符"},
                    {validateTrigger: 'onBlur', validator: (rule, value, callback) => {
                        if (!value) {
                            callback();
                        }
                        gumpDirectoryNodeService.validateUniqueCode(value, treeNodeId, true, metadataType, id => {
                            if (id) {
                                setSubmitButtonLoading(false);
                                callback(`${value}已存在`);
                            } else {
                                callback();
                            }
                        });
                    }}
                ]}>
                    <Input placeholder="请输入文件夹名, 不超过20个字符"/>
                </Form.Item>

                <Form.Item style={{textAlign : 'right'}}>
                    <Button type="primary" loading={submitButtonLoading} onClick={() => {
                        setSubmitButtonLoading(true);

                        form.validateFields().then(values => {
                            let node = new GumpDirectoryNode();
                            node.code = values.folderName;
                            node.folder = true;
                            node.parentId = treeNodeId;
                            node.type = metadataType;

                            gumpDirectoryNodeService.save(node, () => {
                                closeModal();
                                onCreateSuccess(metadataType, treeNodeId);
                            });
                        }).catch(errorInfo => {
                            setSubmitButtonLoading(false);
                        });
                    }}>创建</Button>
                </Form.Item>
            </Form>
        </Modal>
    )
}

const CreateFileModal = props => { 
    const [submitButtonLoading, setSubmitButtonLoading] = useState(false);

    const {visible, onCancel, onCreateSuccess} = props;
    const {treeNodeId, metadataType} = props;
    const [form] = Form.useForm();

    const closeModal = () => {
        onCancel();
        setSubmitButtonLoading(false);
        form.resetFields();
    }
    return (
        <Modal title="新建文件" maskClosable={false} open={visible} footer={null} onCancel={closeModal}>   
            <Form form={form}>
                <Form.Item label="code" name="code" rules={[
                    {required: true,message: '请输入code'}, 
                    {max: 50, message: "不能超过50个字符"}, 
                    {pattern: /^[A-Za-z]+[0-9]*$/, message: "只允许英文字母、数字"},
                    {validateTrigger: 'onBlur', validator: (rule, value, callback) => {
                        if (!value) {
                            callback();
                        }
                        gumpDirectoryNodeService.validateUniqueCode(value, treeNodeId, false, metadataType, id => {
                            if (id) {
                                setSubmitButtonLoading(false);
                                callback(`${value}已存在`);
                            } else {
                                callback();
                            }
                        });
                    }}
                ]}>
                    <Input placeholder="输入code, 不能超过50个字符"/>
                </Form.Item>

                <Form.Item label="描述" name="desc" rules={[
                    {required: true,message: '请输入name'}, 
                    {max: 100, message: "不能超过100个字符"}
                ]}>
                    <Input placeholder="输入name"/>
                </Form.Item>

                <Form.Item style={{textAlign : 'right'}}>
                    <Button type="primary" loading={submitButtonLoading} onClick={() => {
                        setSubmitButtonLoading(true);

                        form.validateFields().then(values => {
                            let node = new GumpDirectoryNode();
                            node.code = values.code;
                            node.desc = values.desc;
                            node.folder = false;
                            node.parentId = treeNodeId;
                            node.type = metadataType;

                            gumpDirectoryNodeService.save(node, () => {
                                closeModal();
                                onCreateSuccess(metadataType, treeNodeId);
                            });
                        }).catch(errorInfo => {
                            setSubmitButtonLoading(false);
                        });
                    }}>创建</Button>
                </Form.Item>
            </Form>
        </Modal>
    )
}



const EditFolderNodeModal = props => { 
    const [submitButtonLoading, setSubmitButtonLoading] = useState(false);

    const {visible, onCancel, onEditSuccess} = props;
    const {node} = props;
    const [form] = Form.useForm();

    const closeModal = node => {
        onCancel();
        setSubmitButtonLoading(false);
        form.setFieldsValue({folderName: node.code});
    }

    /*useEffect(() => {
        if (!treeNodeId || !visible) {
            return;
        }
        gumpDirectoryNodeService.queryDirectoryNodeDetail(treeNodeId, node => form.setFieldValue("folderName", node.code));
    }, [visible]);*/
    
    return (
        <Modal title="编辑文件夹" maskClosable={false} open={visible} footer={null} onCancel={() => closeModal(node)}>
            <Form form={form} initialValues={{folderName: node.code}}>
                <Form.Item label="文件夹名" name="folderName" rules={[
                    {required: true, message: '请输入文件夹名'}, 
                    {max: 20, message: "不能超过20个字符"},
                    {validateTrigger: 'onBlur', validator: (rule, value, callback) => {
                        if (!value) {
                            callback();
                        }
                        gumpDirectoryNodeService.validateUniqueCode(value, node.parentId, true, node.type, id => {
                            if (id && id != node.id) {
                                setSubmitButtonLoading(false);
                                callback(`${value}已存在`);
                            } else {
                                callback();
                            }
                        });
                    }}
                ]}>
                    <Input placeholder="请输入文件夹名, 不超过20个字符"/>
                </Form.Item>

                <Form.Item style={{textAlign : 'right'}}>
                    <Button type="primary" loading={submitButtonLoading} onClick={() => {
                        setSubmitButtonLoading(true);

                        form.validateFields().then(values => {
                            if (values.folderName === node.code) {
                                closeModal(node);
                                onEditSuccess();
                            } else {
                                let editNode = new GumpDirectoryNode();
                                editNode.id = node.id;
                                editNode.code = values.folderName;
                                editNode.folder = node.folder;
                                editNode.parentId = node.parentId;
                                editNode.type = node.type;

                                gumpDirectoryNodeService.save(editNode, () => {
                                    closeModal(editNode);
                                    onEditSuccess();
                                });
                            }
                        }).catch(errorInfo => {
                            setSubmitButtonLoading(false);
                        });
                    }}>修改</Button>
                </Form.Item>
            </Form>
        </Modal>
    )
}

const EditFileNodeModal = props => { 
    const [submitButtonLoading, setSubmitButtonLoading] = useState(false);

    const {visible, onCancel, onEditSuccess} = props;
    const {node} = props;
    const [form] = Form.useForm();

    const closeModal = node => {
        onCancel();
        setSubmitButtonLoading(false);
        form.setFieldsValue(node);
    }

    return (
        <Modal title="编辑文件" maskClosable={false} open={visible} footer={null} onCancel={() => closeModal(node)}>   
            <Form form={form} initialValues={node}>
                <Form.Item label="code" name="code" rules={[
                    {required: true,message: '请输入code'}, 
                    {max: 50, message: "不能超过50个字符"}, 
                    {pattern: /^[A-Za-z]+[0-9]*$/, message: "只允许英文字母、数字"},
                    {validateTrigger: 'onBlur', validator: (rule, value, callback) => {
                        if (!value) {
                            callback();
                        }
                        gumpDirectoryNodeService.validateUniqueCode(value, node.parentId, false, node.type, id => {
                            if (id && id != node.id) {
                                setSubmitButtonLoading(false);
                                callback(`${value}已存在`);
                            } else {
                                callback();
                            }
                        });
                    }}
                ]}>
                    <Input placeholder="输入code, 不能超过50个字符"/>
                </Form.Item>

                <Form.Item label="描述" name="desc" rules={[
                    {required: true,message: '请输入name'}, 
                    {max: 100, message: "不能超过100个字符"}
                ]}>
                    <Input placeholder="输入name"/>
                </Form.Item>

                <Form.Item style={{textAlign : 'right'}}>
                    <Button type="primary" loading={submitButtonLoading} onClick={() => {
                        setSubmitButtonLoading(true);

                        form.validateFields().then(values => {
                            if (values.code === node.code && values.desc === node.desc) {
                                closeModal(node);
                                onEditSuccess();
                            } else {
                                let editNode = new GumpDirectoryNode();
                                editNode.id = node.id;
                                editNode.code = values.code;
                                editNode.desc = values.desc;
                                editNode.folder = node.folder;
                                editNode.parentId = node.parentId;
                                editNode.type = node.type;

                                gumpDirectoryNodeService.save(editNode, () => {
                                    closeModal(editNode);
                                    onEditSuccess();
                                });
                            }
                        }).catch(errorInfo => {
                            console.log("error", errorInfo)
                            setSubmitButtonLoading(false);
                        });
                    }}>修改</Button>
                </Form.Item>
            </Form>
        </Modal>
    )
}
class TreeNodeTitle extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showOperationIcon: false,
            createFolderModalVisible: false,
            createFileModalVisible: false,
            editFolderModalVisible: false,
            editFileModalVisible: false,
        }
    }

    render() {
        const {node={}, icon, showEdit=true, showDelete=true, showDetail=true, onAddChildSuccess, onEditNodeSuccess, onDoubleClickFileNode} = this.props;
        const {id, type, folder, code, desc} = node;

        const nodeIcon = icon ? icon : (folder ? <FolderOutlined /> : <FileOutlined />);

        const plusMenuProps = {
            items: [{
                key: "createFolder",
                label: "新建文件夹"
            }, {
                key: "createFile",
                label: "新建文件"
            }],
            onClick: item => {
                if (item.key === "createFolder") {
                    this.setState({createFolderModalVisible: true});
                }
                if (item.key === "createFile") {
                    this.setState({createFileModalVisible: true});
                }
            }
        }

        return (
            <div style={{display: 'inline-block', width: '150px'}} 
                    onMouseOver={() => this.setState({showOperationIcon:true})}
                    onMouseOut={() => this.setState({showOperationIcon:false})}>
                <Row justify="space-between">
                    <Col span={20}>
                        <Row>
                            <Col span={4}>{nodeIcon}</Col>
                            <Col span={20} style={{overflow: 'hidden', textOverflow: 'ellipsis'}} onDoubleClick={()=>{
                                if (folder) {
                                    return;
                                }
                                if (onDoubleClickFileNode) {
                                    onDoubleClickFileNode(node);
                                }
                            }}>
                                {
                                    showDetail ? <Tooltip title={folder ? code : `${code}(${desc})`} placement="bottom">{code}</Tooltip> : code
                                }
                            </Col>
                        </Row>
                    </Col>
                    
                    <Col span={4}>
                    {
                        this.state.showOperationIcon ? (
                            <Space>
                                {folder ? (
                                    <Dropdown menu={plusMenuProps} trigger={['click']} placement={"bottomLeft"}>
                                        <PlusOutlined />
                                    </Dropdown>
                                ) : null}
                                {showEdit ? (<EditOutlined onClick={e => {
                                    if (folder) {
                                        this.setState({editFolderModalVisible: true});
                                    } else {
                                        this.setState({editFileModalVisible: true});
                                    }
                                    e.stopPropagation();
                                }}/>) : null}
                                {showDelete ? <DeleteOutlined /> : null}
                            </Space>
                        ) : null
                    }
                    </Col>
                </Row>

                <CreateFolderModal treeNodeId={id} metadataType={type} 
                        visible={this.state.createFolderModalVisible} 
                        onCancel={() => this.setState({createFolderModalVisible: false})}
                        onCreateSuccess={onAddChildSuccess}/>

                <CreateFileModal treeNodeId={id} metadataType={type} 
                        visible={this.state.createFileModalVisible} 
                        onCancel={() => this.setState({createFileModalVisible: false})}
                        onCreateSuccess={onAddChildSuccess}/>

                <EditFolderNodeModal node={node}
                        visible={this.state.editFolderModalVisible} 
                        onCancel={() => this.setState({editFolderModalVisible: false})}
                        onEditSuccess={onEditNodeSuccess}/>

                <EditFileNodeModal node={node}
                        visible={this.state.editFileModalVisible} 
                        onCancel={() => this.setState({editFileModalVisible: false})}
                        onEditSuccess={onEditNodeSuccess}/>
            </div>
        )
    }

}

export default TreeNodeTitle;