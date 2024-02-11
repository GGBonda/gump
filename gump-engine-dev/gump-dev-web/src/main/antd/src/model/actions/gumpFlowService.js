import axios from 'util/AxiosUtil';

const urlParams = new URLSearchParams(window.location.search);
const appCode = urlParams.get('appCode');

export const debug = (flowCode, input, callback) => {

    axios.post("/flow/debug", {appCode, flowCode, input}).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
} 

export const debugLog = (traceId, callback) => {
    axios.post("/flow/debugLog", {traceId}).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
}