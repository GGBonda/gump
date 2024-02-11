import React from 'react';
import 'css/x6/customNode.css';

import {CloseCircleOutlined, ExclamationCircleFilled} from '@ant-design/icons';
import { Button, Modal, Space } from 'antd';

const { confirm } = Modal;
export default class CustomNode extends React.Component {

    constructor(props) {
        super(props);
    }

    svgContent(comment, shape, chosen) {
        let backgroundColor = chosen ? "#7dc8f7" : "#fff";
        let textColor = chosen ? "#fff" : "#000";
        switch(shape) {
            case 'rect': 
                return (
                    <svg width="100%" height="100%" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 10">
                        <rect width="20" height="10" style={{fill: backgroundColor, stroke: "#000", strokeWidth: 0.9}}/>
                        <text x="10" y="5" style={{ fill: textColor, fontSize:"3", dominantBaseline:"middle", textAnchor: "middle"}}>{comment}</text>
                    </svg>
                );
            case 'polygon':
                return (
                    <svg width="100%" height="100%" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 10">
                        <polygon points="0.1,5 10,9.9 20,5 10,0.1" style={{fill: backgroundColor, stroke: "#000", strokeWidth: 0.5}}/>
                        <text x="10" y="5" style={{ fill: textColor, fontSize:"2.5", dominantBaseline:"middle", textAnchor: "middle"}}>{comment}</text>
                    </svg>
                );
            case 'ellipse':
                return (
                    <svg width="100%" height="100%" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 10">
                        <ellipse cx="10" cy="5" rx="9.8" ry="4.8" style={{fill: backgroundColor, stroke: "#000", strokeWidth: 0.5}}/>
                        <text x="10" y="5" style={{ fill: textColor, fontSize:"3", dominantBaseline:"middle", textAnchor: "middle"}}>{comment}</text>
                    </svg>
                );
            case 'circle':
                return (
                    <svg width="100%" height="100%" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                        <circle cx="10" cy="10" r="9" style={{fill: backgroundColor, stroke: "#000", strokeWidth: 0.8}}/>
                        <text x="10" y="10" style={{ fill: textColor, fontSize:"5", dominantBaseline:"middle", textAnchor: "middle"}}>{comment}</text>
                    </svg>
                );

        }
    }

    render() {
        let {node} = this.props;
        let {comment, shape, chosen, erasable, stepCode, onDeleteStep} = node.getData();

        return (
            <div>
                {
                    chosen && erasable ? 
                    <CloseCircleOutlined style={{cursor: "pointer", position: "absolute", right: 5, top: 5, zIndex: 9999, color: 'red', fontSize: '18px'}} onClick={() => {
                        confirm({
                            title: `删除确认`,
                            icon: <ExclamationCircleFilled />,
                            content: `确认删除 ${stepCode} 环节？`,
                            cancelText: "取消",
                            okText: "确认",
                            onOk() {
                                onDeleteStep(stepCode);
                            },
                            onCancel() {},
                        });
                    }}/> 
                    : null
                }
                {this.svgContent(comment, shape, chosen)}
            </div>
        );
    }
}