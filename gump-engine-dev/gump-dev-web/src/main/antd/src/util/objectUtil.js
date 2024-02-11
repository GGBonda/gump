export const deepClone = (obj = {}) => {
    let newobj = null;  

    if (typeof (obj) === 'object' && obj != null) {
        newobj = obj instanceof Array ? [] : {};                
        for (var i in obj) {
            newobj[i] = deepClone(obj[i]);
        }               
    } else {
        newobj = obj;
    }            
    return newobj;
}