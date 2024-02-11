import React from 'react';

import * as gumpMetadataService from 'model/actions/gumpMetadataService.js';
import GumpMetadata from 'model/gumpMetadata';

class MetadataDesignTab extends React.Component {

    constructor(props) {
        super(props);
        this.metadata = null;
    }

    timeout = ms => new Promise(resolve => setTimeout(resolve, ms));

    loadMetadata (id, callback) {
        gumpMetadataService.queryMetadataDetailByNodeId(id, callback);
        /*await this.timeout(1000);
        this.metadata = {
            name: "测试test",
            code: "test01",
            editingStepCode: 'input',
            steps: [
                {code: 'input', type: 'input', comment:'起始', next: 'output'},
                {code: 'output', type: 'output', comment:'结束'}
            ]
        };
        return this.metadata;*/
    }

    saveMetadata (metadata, callback) {
        gumpMetadataService.save(metadata, callback);
    }
}

export default MetadataDesignTab;