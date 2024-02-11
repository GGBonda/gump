import axios from 'axios';
import {Modal} from 'antd';
import * as Constants from '../Constants.js';

axios.interceptors.request.use(
    config => {
        /**
         * 拦截request做一些操作
        */
       config.cancelToken = axios.CancelToken.source().token;
        return config;
    }
);

axios.interceptors.response.use(
    //正常的200 response会进入这个方法被处理
    response => {
        return response;
    },
    /**
     * 非200的response会被当成error进入这个方法处理
     * 如果这里不设置对非200 response进行拦截，那么就会调用axios返回promise的then中定义的reject
     * 我在这里定义好统一的非200 response处理，后续promise的reject将不会被调用，只会调用resolve，而调用resolve的传入参数即是我这里的返回值
     **/
    error => {
        Modal.error({
            title : "请求失败",
            content : error.response ? error.response.data.msg : "后台服务器挂了，没办法没办法"
        })
        /** 
         * 这里返回什么，后续then里的resolve的参数就是什么，返回error，resolve接到的参数就是error，返回error.response，resolve接到的参数就是response的信息
         * 弹出失败原因的modal框之后，我们还是要把response返回给action，让action有机会根据response做相应的处理
         **/
        return error.response;
    }
);

//全局的 axios 默认配置
axios.defaults.baseURL = Constants.baseUrl;

export default axios;