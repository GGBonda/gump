import axios from 'util/AxiosUtil';

import GumpMetadata from '../gumpMetadata';

const urlParams = new URLSearchParams(window.location.search);
const appCode = urlParams.get('appCode');


export const queryMetadataDetail = (code, type, callback) => {
    const request = {code, type, appCode};

    axios.post("/metadata/queryMetadataDetail", request).then(resp => {
        if (typeof callback === 'function') {
            const {data} = resp;
            if (data === '' || data === undefined) {
                callback(null);
            } else {
                callback(data);
            }
        }
    })
}

export const queryMetadataDetailByNodeId = (nodeId, callback) => {
    const request = {nodeId, appCode};

    axios.post("/metadata/queryMetadataDetailByNodeId", request).then(resp => {
        if (typeof callback === 'function') {
            const {data} = resp;
            if (data === '' || data === undefined) {
                callback(null);
            } else {
                callback(data);
            }
        }
    })
}

export const queryMetadataList = (type, callback) => {
    const request = {type, appCode};

    axios.post("/metadata/queryMetadataList", request).then(resp => {
        if (typeof callback === 'function') {
            const {data} = resp;
            if (data === '' || data === undefined) {
                callback(null);
            } else {
                callback(data);
            }
        }
    })
}

export const save = (metadata, callback) => {
    axios.post("/metadata/save", {...metadata, appCode}).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
}