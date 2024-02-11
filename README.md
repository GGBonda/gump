项目运行需要同时启动前后端。

前端启动方式

(第零步：下载并安装 nodejs)

第一步：进入gump/gump-engine-dev/gump-dev-web/src/main/antd；

第二步：运行 npm install 命令，初始化 node 模块依赖；

第三步：运行 npm run start 命令，启动 node dev服务器；

后端启动方式

（第零步：打开org.junhui.gump.rpc.dubbo.DubboRpcInvoker，修改 dubbo 注册中心地址）

第一步：运行 org.junhui.gump.dev.starter.AppMain类；

前后端启动后打开浏览器，访问地址：http://localhost:8000/jarvis.htm?appCode=TestApp 即可。

