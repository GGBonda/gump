import React from 'react';

import { Select } from 'antd';

import * as gumpMetadataService from 'model/actions/gumpMetadataService.js';


class MetadataSelect extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            options: []
        }
    }

    componentDidMount() {
        const {type} = this.props;
        gumpMetadataService.queryMetadataList(type, (data=[]) => {
            let options = data.map(item => ({
                value: item.nodeId,
                label: `${item.code}(${item.desc})`,
                content: JSON.parse(item.content)
            }));
            this.setState({options})
        });
    }

    render() {
        const {options} = this.state;
        const {value, onChange} = this.props;

        return (
            <Select options={options} onChange={onChange} value={value}/>
        )
    }

}

export default MetadataSelect;