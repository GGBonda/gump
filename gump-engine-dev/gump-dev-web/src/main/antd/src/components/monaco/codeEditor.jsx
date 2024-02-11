import React from 'react';

import {FullscreenOutlined} from '@ant-design/icons';

import * as monaco from 'monaco-editor/esm/vs/editor/editor.api'
import MonacoEditor from 'react-monaco-editor';

class CodeEditor extends React.Component {

    constructor(props) {
        super(props);
    }

    insertText = text => {
        const p = this.editor.getPosition();
        this.editor.executeEdits('',
            [
                {
                    range: new monaco.Range(p.lineNumber, p.column, p.lineNumber, p.column),
                    text
                }
            ]
        );
    }

    render () {
        const {
            language="javascript", 
            height="300",
            readOnly=false,
            showLineNumbers=true,
            onChange,
            value
        } = this.props;

        const options = {
            selectOnLineNumbers: false,
            lineNumbersMinChars: 1,
            lineNumbers: showLineNumbers ? 'on' : 'off',
            renderSideBySide: false,
            overviewRulerBorder: true,
            automaticLayout: true,
            readOnly,
            contextmenu: false,//取消右键菜单
            formatOnPaste: true,
            formatOnType: true,
        };
        return (
            <div style={{border: "1px solid #D3D3D3"}}>
                <MonacoEditor height={height} language={language} options={options}
                    value={typeof value === 'string' || value === null || value === undefined  ? value : JSON.stringify(value, null, '\t')}
                    onChange={value => {
                        if (onChange) {
                            onChange(value);
                        }
                    }}
                    editorDidMount={(editor, monaco)=>{
                        this.editor = editor;
                    }}
                />
            </div>
        )
    }
}

export default CodeEditor;