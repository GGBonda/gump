import React from 'react';
import {createRoot} from 'react-dom/client';

//import store from 'model/index.js';
import {Provider} from 'react-redux';

import {MemoryRouter} from 'react-router-dom';

import App from 'pages/app';
import GlobalErrorHandler from 'pages/globalErrorHandler'


createRoot(document.querySelector("#content")).render(
    <GlobalErrorHandler>
        <Provider store={{}}>
        {/* MemoryRouter将“URL”的历史记录保存在内存中（不读取或写入地址栏） */}
            <MemoryRouter>
                <App/>
            </MemoryRouter>
        </Provider>
    </GlobalErrorHandler>
);