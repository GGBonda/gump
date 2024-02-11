const path = require('path')
const glob = require('glob')
const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin');

const appFiles = glob.sync('./src/app/**/*.jsx')
const entries = {}
const appFileNames = []
const htmlWebpackPlugins = []
appFiles.forEach(currentValue => {
    let name = currentValue.replace('./src/app/', '').replace('.jsx', '')
    entries[name] = currentValue
    appFileNames.push(name)
    htmlWebpackPlugins.push(new HtmlWebpackPlugin({
        template : path.join(__dirname, './template.html'),
        filename : name + ".htm",
        chunks :  [name, 'common', 'vendor', 'webpackBootStrap'],
    }))
})

//第三方库chunk
entries['vendor'] = ['react', 'react-dom', 'antd', 'react-router-dom', 'redux', 'react-redux', 'redux-thunk', 'axios', 'prop-types', 'qs', 'jsencrypt', 'jssha', 'js-base64', 'react-monaco-editor']

const config = {
    entry : entries,
    output : {
        path : path.join(__dirname, '../resources/static/'),
        filename : '[name].[chunkhash:8].js'
    },
    resolve: {
        alias: {
            css: path.resolve(__dirname, "src/assets/css"),
            img: path.resolve(__dirname, "src/assets/img"),
            pages: path.resolve(__dirname, "src/pages"),
            components: path.resolve(__dirname, "src/components"),
            config: path.resolve(__dirname, "src/config"),
            util: path.resolve(__dirname, "src/util"),
            model: path.resolve(__dirname, "src/model")
        },
        extensions: ['.js', '.jsx', '.json', '.css']
    },
    module : {
        rules : [
            {
                test : /\.(jsx|js)$/,
                //exclude : /node_modules/,
                loader : 'babel-loader'
            },
            /* 如果只用css-loader和style-loader去加载jsx文件中引用的css文件，则编译出来的css代码会与引用它的js文件混合在一起，这样很不好。所以我们一般会搭配extract-text-webpack-plugin插件使用
            {
                test : /\.css$/,
                use : [
                    'style-loader',
                    'css-loader'
                ]
            }, */
            {
                test : /\.css$/i, 
                use : [MiniCssExtractPlugin.loader, 'css-loader']
            },
            {
                test : /\.(jpg|jpeg|gif|png|ttf|svg)$/,
                use : [
                    {
                        loader : 'url-loader',
                        options : {
                            name : '[name].[ext]',
                            outputPath : 'img/',
                            limit : 1024,//如果文件大小小于1024byte，则将图片转成base64字节码写到文件中
                        }
                    }
                ]
            }
        ]
    },
    plugins : [
        new MiniCssExtractPlugin({
            chunkFilename: 'styles.[contentHash:8].css'
        }),
        new MonacoWebpackPlugin({
            languages: ['json', 'javascript']
        }),
        ...htmlWebpackPlugins,
    ],
    optimization: {
        splitChunks: {
            chunks: 'async',
            minSize: 20000,
            minRemainingSize: 0,
            minChunks: 1,
            maxAsyncRequests: 30,
            maxInitialRequests: 30,
            enforceSizeThreshold: 50000,
            cacheGroups: {
                vendors: {
                    name: 'chunk-vendors',
                    test: /[\\/]node_modules[\\/]/,
                    priority: -10,
                    reuseExistingChunk: true,
                    chunks: 'initial'
                },
                common: {
                    name: `chunk-common`,
                    minChunks: 2,
                    priority: -20,
                    chunks: 'initial',
                    reuseExistingChunk: true,
                },
            },
        },
        moduleIds: 'named'
    }
}

if (process.env.NODE_ENV === 'development') {
    config.devtool = 'eval-cheap-source-map'
    config.plugins.push(
        //new webpack.HotModuleReplacementPlugin()
    )
    config.devServer = {
        contentBase : '../webapp/WEB-INF/antdResource',
        port : 8000,
        host : '0.0.0.0',//设置成0.0.0.0不仅能通过localhost访问项目，也能通过内网Ip访问项目，
        //hot : true,//启用 webpack 的模块热替换特性
        inline : true,//当入口chunk修改时，整个页面刷新 
        overlay : {
            errors : true//这个配置属性用来在编译出错的时候，在浏览器页面上显示错误
        }
    }
} else if (process.env.NODE_ENV === 'production') {
    config.devtool = false
    config.plugins.push(
        //在build开始前删除整个antdResource文件夹，以清除老的build文件
        new CleanWebpackPlugin(['antdResource'], {
            root : path.join(__dirname, '../webapp/WEB-INF')
        }),
    )
}

module.exports = config