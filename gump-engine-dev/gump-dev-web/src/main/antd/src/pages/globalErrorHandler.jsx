import React from 'react';

import { Modal, Icon } from 'antd';

/**
 * 全局异常处理，如果在整个App加载的时候出现问题，就弹出报错框
 **/
class GlobalErrorHandler extends React.Component {
    constructor(props) {  
        super(props)
        this.state = { 
            hasError: false,
            errorMsg : "" 
        } 
    }

    componentDidCatch(error, info) {
        this.setState({
            hasError : true,
            errorMsg : error.message,
            info : info
        })
    }

    render () {
        if (this.state.hasError) {
            return (
                <Modal visible={true} title={<Icon type="frown-o" style={{color : "red"}}/>} footer={null} closable={false}>
                    <p style={{textAlign : "center"}}>
                        {this.state.errorMsg}
                    </p>
                </Modal>
            )
        } else {
            return <div>{this.props.children}</div>
        }
    }
}

export default GlobalErrorHandler;