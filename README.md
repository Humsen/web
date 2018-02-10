# web项目         -- 此分支开箱即用，详见下面配置步骤

个人网站项目，前台包括博客、代码库、文件下载、留言、登录注册等功能，后台包括上传文件、博客、代码，编辑、删除文章，查看、修改个人资料及邮箱、头像等功能。

PC端网址：www.hemingsheng.cn ，移动端网址：https://m.hemingsheng.cn

------------

## 主要文件结构如下

- `docs` -> 说明文档
- `web-core` -> 后端Java源代码
- `web-mobile` -> 响应式界面源码（在web和移动端是响应式布局）
- `web-pc` -> pc端界面源码（非响应式布局）

------------

## 快速配置方法：

    1、下载项目
    2、按照docs文件夹里面的数据库配置步骤，导入脚本，需提前安装PostgreSQL数据库和创建一个数据库
    3、将项目导入Eclipse/IDEA，修改web-pc和web-mobile 下的config/db_connect_info.properties文件里的数据库配置
    4、放在tomcat等容器下运行。并将config文件夹放在web目录的同级目录（和web项目在同一个文件夹下）
    5、访问 localhost:8080即可（默认配置已去掉项目名称）
