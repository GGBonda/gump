import axios from 'util/AxiosUtil';

import GumpDirectoryNode from '../gumpDirectoryNode';

const urlParams = new URLSearchParams(window.location.search);
const appCode = urlParams.get('appCode');

export const save = (gumpDirectoryNode, callback) => {
    if (!gumpDirectoryNode instanceof GumpDirectoryNode) {
        throw 'gumpDirectoryNodeService save can not accept non-GumpDirectoryNode param';
    }

    const {id, folder, code, parentId, desc, type} = gumpDirectoryNode;

    axios.post("/directory/saveDirectoryNode", {id, folder, code, parentId, desc, type, appCode}).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
} 

export const queryChildDirectoryNodes = (type, parentId, callback) => {
    const request = {appCode, type, parentId};

    axios.post("/directory/queryChildDirectoryNodes", request).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
} 

export const queryDirectoryNodeDetail = (nodeId, callback) => {
    const request = {appCode, nodeId};

    axios.post("/directory/queryDirectoryNodeDetail", request).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    })
} 

export const validateUniqueCode = async (code, parentId, folder, type, callback) => {
    const request = {appCode, code, type, parentId, folder};

    axios.post("/directory/validateUniqueCode", request).then(resp => {
        if (typeof callback === 'function') {
            callback(resp.data);
        }
    });
}