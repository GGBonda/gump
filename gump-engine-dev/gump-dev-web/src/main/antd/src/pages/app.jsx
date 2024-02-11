import React from 'react';
import PropTypes from 'prop-types';

import SplitPane from 'react-split-pane'

import 'css/jarvis.css';
import MetadataDesignPanel from 'components/metadataDesignPanel.jsx';
import ProjectCatalogueTree from 'components/catalogue/projectCatalogueTree.jsx';

class FrameWork extends React.Component {
    
    constructor(props) {
        super(props)
        this.state = {
            appCode: '',
            metadataDesignPanelRef: null
        }
    }

    componentWillMount () {
        const {store} = this.context;
        //this.unSubscribe = store.subscribe(() => this.forceUpdate());
    }

    componentWillUnmount () {
        //this.unSubscribe();
    }

    render() {
        return (
            <SplitPane split="horizontal" allowResize={false}>
                <div>Header</div>

                <SplitPane split="vertical" minSize={250}>
                    <div>
                        <ProjectCatalogueTree openFile={fileNode => {
                            this.state.metadataDesignPanelRef.pushMetadataItem(fileNode);
                        }}/>
                    </div>
                    <div>
                        <MetadataDesignPanel ref={node => this.state.metadataDesignPanelRef = node}/>
                    </div>
                </SplitPane>
            </SplitPane>
        )
    }
}

FrameWork.contextTypes = {
    store : PropTypes.object
}

export default FrameWork;