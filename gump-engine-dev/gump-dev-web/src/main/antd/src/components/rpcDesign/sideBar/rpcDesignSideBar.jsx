import React from 'react';

import SideBar from 'components/sideBar/sideBar.jsx';
import DebugPanel from './debugPanel.jsx';

class RpcDesignSideBar extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
                  
        }
    }

    componentDidMount() {
    
    }

    render() {
        const {rpc} = this.props;
        return (
            <div>
                <SideBar items={[  
                    {
                        key: 'debugConfig', label: '调试', 
                        children: <DebugPanel rpc={rpc}/>
                    }
                ]}/>
            </div>
        )
    }

}

export default RpcDesignSideBar;