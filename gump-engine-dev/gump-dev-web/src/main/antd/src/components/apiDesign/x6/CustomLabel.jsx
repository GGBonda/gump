import React from 'react';

import stepMenu from 'config/stepMenu.js';
import { Button, Dropdown } from 'antd';
import { PlusOutlined } from '@ant-design/icons';

export default class CustomLabel extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const menuProps = {
            items: stepMenu,
            onClick: item => this.props.onSelect(item.key)
        }
        return (
            <Dropdown menu={menuProps} trigger={['click']} placement={"bottomLeft"}>
                <Button size="small" shape="circle" icon={<PlusOutlined />}/>
            </Dropdown>
        );
    }
}