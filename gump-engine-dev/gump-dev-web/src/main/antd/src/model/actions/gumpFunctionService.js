import axios from 'util/AxiosUtil';

const urlParams = new URLSearchParams(window.location.search);
const appCode = urlParams.get('appCode');

export const queryGumpFunctions = callback => {

    axios.get("/gf/queryGumpFunctionInfo").then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
} 