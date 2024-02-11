import React from 'react';

import { Drawer } from 'antd';

const SideBarMenu = ({items=[], activeKey, onClick}) => {
    const itemStyle = {textAlign: 'center', fontWeight: 'bolder', padding: '10px 0', cursor: 'pointer', border: "1px solid #f0f0f0",};
    const selectedItemStyle = {...itemStyle, marginLeft: "-1px", background: '#fff', borderLeft: '1px solid #fff'}
    return (
        <div style={{height: 'inherit', position: 'relative', border: "1px solid #f0f0f0"}}>
            {
                items.map(item => (
                    <div style={item.key === activeKey ? selectedItemStyle : itemStyle} key={item.key}>
                        <span style={{writingMode: 'vertical-lr'}} onClick={() => onClick(item.key)}>{item.label}</span>
                    </div>
                ))
            }
        </div>
    );
}
class SideBar extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            activeKey: null        
        }
    }

    componentDidMount() {
    
    }

    render() {
        const {items=[], onClick, activeKey:activeKeyProp} = this.props;
        const {activeKey} = this.state;

        return (
            <div>
                <SideBarMenu items={items} activeKey={activeKey}
                            onClick={key => {
                                this.setState({activeKey: key === activeKey ? null : key});
                                if (onClick != undefined && typeof onClick === 'function') {
                                    onClick(key);
                                }
                            }}/>

                {items.map(item => {
                    const {key, label, drawerTitle, children, footer, onClose} = item;
                    
                    return (
                        <Drawer key={key} maskClosable={false} placement="right" width={800} getContainer={false} style={{marginRight: '40px'}}
                                title={drawerTitle != null && drawerTitle != undefined ? drawerTitle : label} 
                                open={activeKeyProp === undefined ? activeKey === key : activeKeyProp === key} 
                                footer={footer}
                                onClose={() => {
                                    this.setState({activeKey: null});
                                    if (onClose != undefined && typeof onClose === 'function') {
                                        onClose();
                                    }
                                }}>
                            {children}
                        </Drawer>
                    );
                })}
            </div>
        )
    }

}

export default SideBar;