import axios from 'util/AxiosUtil';

const urlParams = new URLSearchParams(window.location.search);
const appCode = urlParams.get('appCode');

export const debug = (rpcCode, input, callback) => {

    axios.post("/rpc/debug", {appCode, rpcCode, input}).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
} 