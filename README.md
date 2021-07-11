# web 此分支为非响应式布局分支，相对稳定，不再维护

个人网站项目，前台包括博客、代码库、文件下载、留言、登录注册等功能，后台包括上传文件、博客、代码，编辑、删除文章，查看、修改个人资料及邮箱、头像等功能。

------------

## 主要文件结构如下

- `WebContent` -> 前端源文件 （前端三方插件在`/WebContent/plugins`下）
- `config` -> 配置文件
- `docs` -> 说明文档
- `libs` -> 后端jar包
- `src` -> 后端源文件
- `download` -> 下载文件存在的文件夹
- `images` -> 博客、代码图片，用户头像存放的文件夹
- `logs` -> 日志文件夹

------------

### web工程目录结构如下：
    

    ├─config
    │      log4j2.xml
    │      
    ├─docs
    │      web后端命名规范.txt
    │      web项目前端命名规范.txt
    │      网站logo图片.png
    │      
    ├─download
    ├─images
    ├─libs
    │  ├─file
    │  │      commons-fileupload-1.3.3.jar
    │  │      commons-io-2.5.jar
    │  │      
    │  ├─javamail
    │  │      javax.mail.jar
    │  │      
    │  ├─json
    │  │      commons-beanutils-1.7.0.jar
    │  │      commons-collections-3.1.jar
    │  │      commons-lang-2.5.jar
    │  │      commons-logging.jar
    │  │      ezmorph-1.0.3.jar
    │  │      json-lib-2.4-jdk15.jar
    │  │      
    │  ├─log4j
    │  │      log4j-api-2.8.2.jar
    │  │      log4j-core-2.8.2.jar
    │  │      
    │  ├─psql
    │  │      postgresql-42.1.4.jar
    │  │      
    │  └─servlet
    │          servlet-api.jar
    │          
    ├─logs
    │      debug.log
    │      error.log
    │      info.log
    │      trace.log
    │      warn.log
    │      
    ├─src
    │  │  rebel.xml
    │  │  
    │  └─pers
    │      └─husen
    │          └─web
    │              │  package-info.java
    │              │  
    │              ├─bean
    │              │  │  package-info.java
    │              │  │  
    │              │  ├─po
    │              │  │      AccessAtatisticsPo.java
    │              │  │      ImageUploadPo.java
    │              │  │      package-info.java
    │              │  │      
    │              │  └─vo
    │              │          BlogArticleVo.java
    │              │          CodeLibraryVo.java
    │              │          FileDownloadVo.java
    │              │          ImageUploadVo.java
    │              │          MessageAreaVo.java
    │              │          package-info.java
    │              │          ReleaseFeatureVo.java
    │              │          UserInfoVo.java
    │              │          VisitTotalVo.java
    │              │          
    │              ├─common
    │              │  │  package-info.java
    │              │  │  
    │              │  ├─constants
    │              │  │      BootstrapConstans.java
    │              │  │      CommonConstants.java
    │              │  │      DbConstans.java
    │              │  │      RequestConstants.java
    │              │  │      ResponseConstants.java
    │              │  │      
    │              │  ├─handler
    │              │  │      FileDownloadHandler.java
    │              │  │      FileUploadHandler.java
    │              │  │      ImageDownloadHandler.java
    │              │  │      ImageUploadHandler.java
    │              │  │      package-info.java
    │              │  │      
    │              │  ├─helper
    │              │  │      DateFormatHelper.java
    │              │  │      JudgeIsMobile.java
    │              │  │      package-info.java
    │              │  │      RandomCodeHelper.java
    │              │  │      SendEmailHelper.java
    │              │  │      StackTrace2Str.java
    │              │  │      TypeConvertHelper.java
    │              │  │      
    │              │  └─template
    │              │      └─html
    │              │              BlogTemplate.java
    │              │              CodeTemplate.java
    │              │              GenericTemplate.java
    │              │              
    │              ├─config
    │              │  │  Log4j2Config.java
    │              │  │  package-info.java
    │              │  │  ProjectDeployConfig.java
    │              │  │  
    │              │  ├─filter
    │              │  │      ExceptionFilter.java
    │              │  │      package-info.java
    │              │  │      
    │              │  └─listener
    │              │          OnlineCountListener.java
    │              │          package-info.java
    │              │          WebInitConfigListener.java
    │              │          
    │              ├─dao
    │              │  │  BlogArticleDao.java
    │              │  │  CodeLibraryDao.java
    │              │  │  FileDownloadDao.java
    │              │  │  ImageUploadDao.java
    │              │  │  MessageAreaDao.java
    │              │  │  ReleaseFeatureDao.java
    │              │  │  UserInfoDao.java
    │              │  │  VisitTotalDao.java
    │              │  │  
    │              │  └─impl
    │              │          BlogArticleDaoImpl.java
    │              │          CodeLibraryDaoImpl.java
    │              │          FileDownloadDaoImpl.java
    │              │          ImageUploadDaoImpl.java
    │              │          MessageAreaDaoImpl.java
    │              │          ReleaseFeatureDaoImpl.java
    │              │          UserInfoDaoImpl.java
    │              │          VisitTotalDaoImpl.java
    │              │          
    │              ├─dbutil
    │              │  │  DbManipulationUtils.java
    │              │  │  DbQueryUtils.java
    │              │  │  
    │              │  ├─assist
    │              │  │      DbConnectUtils.java
    │              │  │      package-info.java
    │              │  │      SetPsParamUtils.java
    │              │  │      TypeTransformUtils.java
    │              │  │      
    │              │  └─mappingdb
    │              │          BlogDetailsMapping.java
    │              │          CodeLibraryMapping.java
    │              │          FileDownloadMapping.java
    │              │          ImageUploadMapping.java
    │              │          MessageAreaMapping.java
    │              │          package-info.java
    │              │          ReleaseFeatureMapping.java
    │              │          UserInfoMapping.java
    │              │          VisitTotalMapping.java
    │              │          
    │              ├─service
    │              │      BlogArticleSvc.java
    │              │      CodeLibrarySvc.java
    │              │      FileDownloadSvc.java
    │              │      ImageUploadSvc.java
    │              │      MessageAreaSvc.java
    │              │      ReleaseFeatureSvc.java
    │              │      UserInfoSvc.java
    │              │      VisitTotalSvc.java
    │              │      
    │              └─servlet
    │                  │  ArticleDeleteSvt.java
    │                  │  
    │                  ├─article
    │                  │      BlogQuerySvt.java
    │                  │      BlogSvt.java
    │                  │      BlogUploadSvt.java
    │                  │      CodeQuerySvt.java
    │                  │      CodeSvt.java
    │                  │      CodeUploadSvt.java
    │                  │      
    │                  ├─common
    │                  │      AccessAtatisticsSvt.java
    │                  │      
    │                  ├─contact
    │                  │      SendEmailServlet.java
    │                  │      
    │                  ├─download
    │                  │      FileDownloadSvt.java
    │                  │      FileUploadSvt.java
    │                  │      
    │                  ├─image
    │                  │      ImageDownloadSvt.java
    │                  │      ImageUploadSvt.java
    │                  │      
    │                  ├─message
    │                  │      MessageSvt.java
    │                  │      
    │                  ├─releasefea
    │                  │      LatestReleaseFeatureSvt.java
    │                  │      NewReleaseFeatureSvt.java
    │                  │      
    │                  └─userinfo
    │                          UserInfoCodeSvt.java
    │                          UserInfoSvt.java
    │                          
    └─WebContent
        │  index.jsp
        │  
        ├─css
        │  ├─article
        │  │      article.css
        │  │      
        │  ├─contact
        │  │      contact.css
        │  │      
        │  ├─download
        │  │      download.css
        │  │      
        │  ├─error
        │  │      error.css
        │  │      
        │  ├─index
        │  │      article-profile.css
        │  │      index.css
        │  │      version-feature.css
        │  │      
        │  ├─login
        │  │      email-check.css
        │  │      login.css
        │  │      retrive-pwd.css
        │  │      
        │  ├─message
        │  │      message.css
        │  │      pager.css
        │  │      
        │  ├─navigation
        │  │      left-menu-bar.css
        │  │      rightbar.css
        │  │      topbar.css
        │  │      
        │  ├─personal_center
        │  │      modify-email.css
        │  │      modify-pwd.css
        │  │      modify-userinfo.css
        │  │      mycenter.css
        │  │      
        │  └─upload
        │          editor-article.css
        │          
        ├─error
        │      error.html
        │      
        ├─images
        │  │  favicon.ico
        │  │  mainbg.png
        │  │  
        │  ├─background
        │  │      bg-1.jpg
        │  │      bg-2.jpg
        │  │      bg-3.jpg
        │  │      bg-4.jpg
        │  │      
        │  └─message
        │          head-0.jpg
        │          head-1.jpg
        │          head-2.jpg
        │          head-3.jpg
        │          head-4.jpg
        │          head-5.jpg
        │          head-6.jpg
        │          head-7.jpg
        │          head-8.jpg
        │          
        ├─js
        │  │  customize-sdk.js
        │  │  pagination.js
        │  │  
        │  ├─article
        │  │      article-markdown.js
        │  │      blog-details.js
        │  │      blog.js
        │  │      code-details.js
        │  │      code-library.js
        │  │      
        │  ├─contact
        │  │      contact.js
        │  │      
        │  ├─download
        │  │      download.js
        │  │      
        │  ├─editor
        │  │      editor.js
        │  │      
        │  ├─index
        │  │      latestblog.js
        │  │      latestcode.js
        │  │      version-feature.js
        │  │      
        │  ├─login
        │  │      formvalidator.js
        │  │      
        │  ├─message
        │  │      message.js
        │  │      pager.js
        │  │      
        │  ├─navigation
        │  │      left-menu-bar.js
        │  │      topbar.js
        │  │      
        │  └─personal_center
        │          modify-email.js
        │          modify-pwd.js
        │          modify-userinfo.js
        │          personal-center.js
        │          
        ├─login
        │      email_check.html
        │      login.jsp
        │      retrive_pwd.html
        │      
        ├─META-INF
        │      MANIFEST.MF
        │      
        ├─module
        │      blog.jsp
        │      code_library.jsp
        │      contact.jsp
        │      download.jsp
        │      message.jsp
        │      
        ├─navigation
        │      rightbar.jsp
        │      topbar.jsp
        │      
        ├─personal_center
        │      modify_email.html
        │      modify_email1.html
        │      modify_pwd.html
        │      modify_userinfo.html
        │      mycenter.jsp
        │      
        ├─plugins
        │  │  plugins.jsp
        │  │  
        │  ├─bootstrap
        │  ├─editormd
        │  ├─jquery
        │  ├─jqueryconfirm
        │  ├─json
        │  ├─template
        │  └─validator
        ├─upload
        │      editor_article.jsp
        │      upload_file.jsp
        │      
        └─WEB-INF
            │  web.xml
            │  
            └─lib
    
    **注：** 
    其中`web/WebContent/plugins/`为存放第三方插件的文件夹，由于文件比较多，在这里省略了。详情可前往至具体文件夹查看。
