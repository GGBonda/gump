export default class GumpDirectoryNode {

    constructor() {
        const urlParams = new URLSearchParams(window.location.search);
        this.appCode = urlParams.get('appCode');
    }

    get id() {return this._id;}
    set id(id) {this._id = id;}

    get folder() {return this._folder;}
    set folder(folder) {this._folder = folder;}

    get code() {return this._code;}
    set code(code) {this._code = code;}

    get parentId() {return this._parentId;}
    set parentId(parentId) {this._parentId = parentId;}

    get desc() {return this._desc;}
    set desc(desc) {this._desc = desc;}

    get type() {return this._type;}
    set type(type) {this._type = type;}

}