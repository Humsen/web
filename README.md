# web 此分支开箱即用

个人网站项目，前台包括博客、代码库、文件下载、留言、登录注册等功能，后台包括上传文件、博客、代码，编辑、删除文章，查看、修改个人资料及邮箱、头像等功能。

PC端网址：demo.hemingsheng.cn ，移动端网址：https://m.hemingsheng.cn

web端下载缓慢，可以到我的个人网站下载：https://www.hemingsheng.cn/module/download.hms ，记得翻到第二页。

交流答疑QQ群：951623031。

[点击加入：Java全栈开发学习交流](//shang.qq.com/wpa/qunwpa?idkey=41068b9adb14521cab1ebfea385e3e4aabf466115ba5278ca4d41a605506c096)

------------

## [点击跳转至postgresql分支开箱即用版](https://github.com/CrazyHusen/web/tree/web-psql) 

------------

## 主要文件结构如下

- `docs` -> 说明文档
- `web-core` -> 后端Java源代码
- `web-mobile` -> 响应式界面源码（在web和移动端是响应式布局）
- `web-pc` -> pc端界面源码（非响应式布局）

------------

## 快速配置方法：

- 1、下载项目源码或者打包好的war包

- 2、按照docs文件夹里面的数据库配置步骤，导入脚本，需提前安装MySQL数据库和创建一个数据库(自定义名称，如web)
    - 按照`docs/数据库部署文件夹`里面的步骤依次运行脚本即可
    
- 3、将项目导入Eclipse/IDEA，修改web-pc和web-mobile 下的config/mysql_connect_info.properties文件里的数据库配置
    - 如果是下载的zip压缩包，则不用导入，直接解压，得到`config`文件夹和war包，并修改db_connect_info.properties数据库配置

- 4、web-core打成jar包，Idea可参考https://hacpai.com/article/1571129510972 。然后放到pc和mobile的WebContent\WEB-INF\lib目录下

- 5、打包web-pc/web-mobile成war包，Idea可参考https://blog.csdn.net/github_38336924/article/details/82422888?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param

- 6、放在tomcat等容器下运行。并将config文件夹放在web目录的同级目录（和web项目在同一个文件夹下）

- 7、访问 localhost:8080即可（默认配置已去掉项目名称）

- 8、使用体验账户登录，用户名：husen，密码：admin123
