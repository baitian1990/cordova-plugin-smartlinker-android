# cordova-plugin-smartlinker-android
A cordova plugin for platform of android,it is about smartlinker

### 一个小玩意儿，导入了hyflying的smartlinker的jar包，自己添加了点代码，做成了h5可以用的cordova插件。
 
### 使用方式：
- 安装插件就不用多说了，两种方式：

1.下载zip包，解压,用命令行cd进入你的cordova项目的根目录，然后使用命令：
```
  cordova plugin add 插件包的全路径
```
2.直接用命令行安装，cd进入你的cordova项目的根目录，然后在命令行输入：
```
 cordova plugin add cordova-plugin-smartlinker-android
```
 插件会自动从网上下载安装
3.如果你想删除已经安装的插件，直接使用命令：
```
 cordova plugin remove org-apache-cordova-smartlinker
```
 就可以直接移除插件
- 该插件提供了两个函数供h5调用
1.获取所连接的wifi的账号。
```
  cordova.plugins.SmartLinker.getSSID();
```
  成功后会返回wifi账号
  2.启动smartlinker将范围内符合条件的硬件设备连接到wifi
```
  cordova.plugins.SmartLinker.startSmartLinker(wifi密码);
```
  这里需要传入wifi密码，成功连接会返回设备的mac地址，连接超时或者连接完成都会返回相应的信息，具体的回调写在smartlinker.js里面
