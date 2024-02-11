export default class GumpMetadata {

    constructor() {
        const urlParams = new URLSearchParams(window.location.search);
        this.appCode = urlParams.get('appCode');
    }

    get nodeId() {return this._nodeId;}
    set nodeId(nodeId) {this._nodeId = nodeId;}

    get code() {return this._code;}
    set code(code) {this._code = code;}

    get content() {return this._content;}
    set content(content) {this._content = content;}

    get desc() {return this._desc;}
    set desc(desc) {this._desc = desc;}

    get type() {return this._type;}
    set type(type) {this._type = type;}

}