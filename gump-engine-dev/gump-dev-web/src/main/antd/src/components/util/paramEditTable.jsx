import React, { useContext, useEffect, useRef, useState } from 'react';

import { Table, Form, Space, Input, Select, ConfigProvider} from 'antd';
import {CloseOutlined, PlusOutlined, PlusCircleOutlined} from '@ant-design/icons';

import StepValueEditor from './stepValueEditor';

import * as DataType from 'config/DataType.js';

const RowFormContext = React.createContext(null);

const EditableRow = ({ index, ...props }) => {
  const [form] = Form.useForm();
  return (
    <Form form={form} component={false}>
      <RowFormContext.Provider value={form}>
        <tr {...props} />
      </RowFormContext.Provider>
    </Form>
  );
};

const EditableCell = ({title, children, dataIndex, valueEditType, editable, record, modifyParam, checkDuplicatedParamCode, ...restProps}) => {
    const form = useContext(RowFormContext);
    const cellValueChanged = (resolve, reject) => {
        form.validateFields().then(formValues => {
            modifyParam(record.key, dataIndex, formValues[dataIndex]);
            if (resolve && typeof resolve === 'function') {
                resolve();
            }
        }).catch(errorInfo => {
            if (reject && typeof reject === 'function') {
                reject();
            }
        });
    }

    let childNode = children;
    if (editable) {
        if (dataIndex === 'code' || dataIndex === 'comment') {
            const [editing, setEditing] = useState(false);
            const inputRef = useRef(null);
            const toggleEdit = () => {
                setEditing(!editing);
            };
    
            useEffect(() => {
                if (editing) {
                    inputRef.current.focus();
                }
            }, [editing]);
    
            if (dataIndex === 'code') {
                childNode = editing ? 
                <Form.Item initialValue={record[dataIndex]} name={dataIndex} style={{margin: 0}}
                            rules={[
                                {required: true, message: `${title} 必填`},
                                {validateTrigger: 'onChange', validator: (rule, value, callback) => {
                                    if (!value) {
                                        callback();
                                    }
                                    let exist = checkDuplicatedParamCode(record.key, value);
                                    if (exist) {
                                        callback("参数名重复");
                                    } else {
                                        callback();
                                    }
                                }}
                            ]}>
                    <Input ref={inputRef} size="small" onBlur={() => cellValueChanged(toggleEdit, toggleEdit)} />
                </Form.Item> : 
                <div onClick={toggleEdit}>{children}</div>;
            }
    
            if (dataIndex === 'comment') {
                childNode = editing ? 
                <Form.Item initialValue={record[dataIndex]} name={dataIndex} style={{margin: 0}}>
                    <Input ref={inputRef} size="small" onBlur={() => cellValueChanged(toggleEdit, toggleEdit)} />
                </Form.Item> : 
                <div onClick={toggleEdit}>{children}</div>;
            }
        } 
        
        if (dataIndex === 'type') {
            childNode =  
                    <Form.Item initialValue={record[dataIndex]} name={dataIndex} style={{margin: 0}}>
                        <Select onSelect={cellValueChanged} options={Object.keys(DataType.DATA_TYPE).map(item => ({value: item, label: DataType.DATA_TYPE[item]}))} size="small"/>
                    </Form.Item>;
        }
    
        if (dataIndex === 'value') {
            childNode = 
                    <Form.Item initialValue={record[dataIndex]} name={dataIndex} style={{margin: 0}}>
                        {
                            valueEditType === 'stepValueEditor' ? 
                            <StepValueEditor onChange={() => cellValueChanged()}/> : 
                            <Input onBlur={cellValueChanged} size="small"/>
                        }
                    </Form.Item>;
        }
    
        if (dataIndex === 'defaultValue') {
            childNode = 
                    <Form.Item initialValue={record[dataIndex]} name={dataIndex} style={{margin: 0}}>
                        <Input onBlur={cellValueChanged} size="small"/>
                    </Form.Item>;
        }
    } else {
        if (dataIndex === 'type') {
            childNode = <div>{DataType.DATA_TYPE[children[1]]}</div>;
        } else {
            childNode = <div>{children}</div>;
        }
    }
    
    return <td {...restProps}>{childNode}</td>;
}
  
class ParamEditTable extends React.Component {
    constructor(props) {
        super(props);
        const {value=[]} = this.props;
        this.state = {
            value
        };
    }

    componentDidUpdate(prevProps, prevState) {
        if (JSON.stringify(prevProps.value) != JSON.stringify(this.props.value)) {
            this.setState({value: this.props.value});
        }
    }

    locateParentParam = (key, value) => {
        const paramCoordinate = key.split('_');
        let parentParam = null;
        
        for (let i = 0; i < paramCoordinate.length - 1; i++) {
            if (parentParam === null) {
                parentParam = value[parseInt(paramCoordinate[i])];
            } else {
                parentParam = parentParam.children[parseInt(paramCoordinate[i])];
            }
        }
        return parentParam;
    }

    locateParam = (key, value) => {
        const paramCoordinate = key.split('_');
        let parentParam = null;
        
        for (let i = 0; i < paramCoordinate.length; i++) {
            if (parentParam === null) {
                parentParam = value[parseInt(paramCoordinate[i])];
            } else {
                parentParam = parentParam.children[parseInt(paramCoordinate[i])];
            }
        }
        return parentParam;
    }

    generateKeyForTableData(data, parentKey) {
        return data.map((item, index) => {
            item = {
                ...item,
                key: parentKey === undefined ? index + '' : parentKey + '_' + index
            }
            if (item.children != undefined && item.children != null && item.children.length > 0) {
                item.children = this.generateKeyForTableData(item.children, item.key);
            }
            return item;
        });
    }

    addChildParam(key) {
        const {value} = this.state;

        if (key === null || key === undefined) {
            value.push(this.defaultAddParam());
        } else {
            let targetParam = this.locateParam(key, value);
            
            if (targetParam.children === undefined || targetParam.children === null) {
                targetParam.children = [];
            }
            targetParam.children.push(this.defaultAddParam());
        }

        this.setState({value});

        if (this.props.onChange != undefined && typeof this.props.onChange === 'function') {
            this.props.onChange(value);
        }
    }

    addBroParam(key) {
        const {value} = this.state;

        const paramCoordinate = key.split('_');
        let targetParam = this.locateParentParam(key, value);
        
        if (targetParam === null) {
            value.splice(parseInt(paramCoordinate[paramCoordinate.length - 1]) + 1, 0, this.defaultAddParam());
        } else {
            targetParam.children.splice(parseInt(paramCoordinate[paramCoordinate.length - 1]) + 1, 0, this.defaultAddParam());
        }

        this.setState({value});

        if (this.props.onChange != undefined && typeof this.props.onChange === 'function') {
            this.props.onChange(value);
        }
    }

    defaultAddParam() {
        return {
            code: Date.now().toString(36),
            type: "object",
            value: "",
            defaultValue: "",
            comment: "默认添加参数"
        }
    }

    deleteParam(key) {
        let {value} = this.state;
        let targetParam = this.locateParentParam(key, value);

        const paramCoordinate = key.split('_');
        if (targetParam === null) {
            value.splice(paramCoordinate[paramCoordinate.length - 1], 1);
        } else {
            targetParam.children.splice(paramCoordinate[paramCoordinate.length - 1], 1);
        }

        this.setState({value});

        if (this.props.onChange != undefined && typeof this.props.onChange === 'function') {
            this.props.onChange(value);
        }
    }

    modifyParam = (key, dataIndex, dataValue) => {
        const {value} = this.state;

        let targetParam = this.locateParam(key, value);
        targetParam[dataIndex] = dataValue;

        if (dataIndex === 'type' && dataValue != 'object' && dataValue != 'array') {
            targetParam.children = null;
        }

        this.setState({value});
        
        if (this.props.onChange != undefined && typeof this.props.onChange === 'function') {
            this.props.onChange(value);
        }
    }

    checkDuplicatedParamCode = (key, code) => {
        const {value} = this.state;

        let parentParam = this.locateParentParam(key, value);

        let childParams = [];
        if (parentParam == null) {
            childParams = value;
        } else {
            childParams = parentParam.children;
        }

        return childParams.some(param => param.key != key && param.code === code);
    }

    render() {
        const {value} = this.state;
        const {showColumns=[], forBidEditColumns=[], valueEditType='stepValueEditor'} = this.props;

        const components = {
            body: {row: EditableRow, cell: EditableCell}
        };
        
        let defaultColumns = [
            {
                title: "参数名",
                dataIndex: "code",
                width: "25%",
                editable: forBidEditColumns.indexOf('code') < 0
            },
            {
                title: "类型",
                dataIndex: "type",
                editable: forBidEditColumns.indexOf('type') < 0
            },
            {
                title: "值",
                dataIndex: "value",
                valueEditType,
                editable: forBidEditColumns.indexOf('value') < 0
            },
            {
                title: "默认值",
                dataIndex: "defaultValue",
                editable: forBidEditColumns.indexOf('defaultValue') < 0
            },
            {
                title: "操作",
                dataIndex: "operation",
                render: (text, record) => (
                    <Space>
                        <CloseOutlined onClick={() => this.deleteParam(record.key, record.code)}/>
                        <PlusOutlined onClick={() => this.addBroParam(record.key)}/>
                        {
                            (record.type === 'object' || record.type === 'array') && (record.children === undefined || record.children === null || record.children.length === 0) ? 
                            <PlusCircleOutlined style={{fontSize: 15}} onClick={() => this.addChildParam(record.key)}/> 
                            : null
                        }
                    </Space>
                )
            },
            {
                title: "备注",
                dataIndex: "comment",
                editable: forBidEditColumns.indexOf('comment') < 0
            }
        ];

        if (showColumns.length > 0) {
            defaultColumns = defaultColumns.filter(item => showColumns.indexOf(item.dataIndex) > -1);   
        }

        const columns = defaultColumns.map(col => {
            if (col.dataIndex === 'operation') {
                return col;
            }
            return {
                ...col,
                onCell: record => ({
                    record,
                    dataIndex: col.dataIndex,
                    title: col.title,
                    modifyParam: this.modifyParam,
                    checkDuplicatedParamCode: this.checkDuplicatedParamCode,
                    valueEditType: col.valueEditType,
                    editable: col.editable
                })
            }
        })
        return (
            <ConfigProvider renderEmpty={() => <PlusOutlined onClick={() => this.addChildParam(null)}/>}>
                <Table components={components} pagination={false} size="small" columns={columns}
                        expandable={{defaultExpandAllRows: true}} 
                        dataSource={this.generateKeyForTableData(value)}/>
            </ConfigProvider>
        )
    }

}

export default ParamEditTable;