# web -- 此分支为响应式布局分支，相对稳定，不再维护

个人网站项目，包括博客、代码库、文件下载、留言、登录注册等功能

网址：www.hemingsheng.cn

------------

## 主要文件结构如下

- `WebContent` -> 前端源文件 （前端三方插件在`/WebContent/plugins`下）
- `config` -> 配置文件
- `docs` -> 说明文档
- `libs` -> 后端jar包
- `src` -> 后端源文件

------------

### web工程目录结构如下：
    
    web:
    │  .classpath
    │  .gitattributes
    │  .gitignore
    │  .project
    │  README.md
    │  
    ├─config
    │      db_connect_info.properties
    │      log4j2.xml
    │      
    ├─docs
    │      web后端命名规范.txt
    │      web项目前端命名规范.txt
    │      网站logo图片.png
    │      
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
    │              │  │      package-info.java
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
    │              │      DeployPathConfig.java
    │              │      Log4j2Config.java
    │              │      package-info.java
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
    │              │  │  DbDeleteUtils.java
    │              │  │  DbInsertUtils.java
    │              │  │  DbQueryUtils.java
    │              │  │  DbUpdateUtils.java
    │              │  │  
    │              │  ├─assist
    │              │  │      AssistUtils.java
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
    │              ├─listener
    │              │      OnlineCountListener.java
    │              │      package-info.java
    │              │      WebInitConfigListener.java
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
    │                  │  package-info.java
    │                  │  
    │                  ├─blog
    │                  │      BlogArticlePerPageServlet.java
    │                  │      BlogArticleServlet.java
    │                  │      BlogPerByIdServlet.java
    │                  │      BlogTotalCountServlet.java
    │                  │      NewBlogUploadServlet.java
    │                  │      
    │                  ├─codelib
    │                  │      CodeLibPerPageServlet.java
    │                  │      CodeLibraryServlet.java
    │                  │      CodeLibTotalCountServlet.java
    │                  │      CodePerByIdServlet.java
    │                  │      NewCodeUploadServlet.java
    │                  │      
    │                  ├─common
    │                  │      AccessAtatisticsServlet.java
    │                  │      
    │                  ├─contact
    │                  │      SendEmailServlet.java
    │                  │      
    │                  ├─download
    │                  │      FileDownloadServlet.java
    │                  │      FileDownPerPageServlet.java
    │                  │      FileDownTotalCountServlet.java
    │                  │      FileUploadServlet.java
    │                  │      
    │                  ├─image
    │                  │      ImageDownloadServlet.java
    │                  │      ImageUploadServlet.java
    │                  │      package-info.java
    │                  │      
    │                  ├─message
    │                  │      MessageGetServlet.java
    │                  │      MessageUploadServlet.java
    │                  │      
    │                  ├─releasefea
    │                  │      LatestReleaseFeatureServlet.java
    │                  │      NewReleaseFeatureServlet.java
    │                  │      
    │                  └─userinfo
    │                          UserInfoModifyServlet.java
    │                          UserInfoQueryServlet.java
    │                          UserInfoRegisterServlet.java
    │                          UserLoginValidateServlet.java
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
        │      error.jsp
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
        │  │  is-pc-or-mobile.js
        │  │  pagination.js
        │  │  
        │  ├─article
        │  │      article-markdown.js
        │  │      blog.js
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
        │  │  ├─css
        │  │  │      bootstrap.css
        │  │  │      bootstrap.css.map
        │  │  │      bootstrap.min.css
        │  │  │      bootstrap.min.css.map
        │  │  │      
        │  │  ├─fonts
        │  │  │      glyphicons-halflings-regular.eot
        │  │  │      glyphicons-halflings-regular.svg
        │  │  │      glyphicons-halflings-regular.ttf
        │  │  │      glyphicons-halflings-regular.woff
        │  │  │      glyphicons-halflings-regular.woff2
        │  │  │      
        │  │  └─js
        │  │          bootstrap.js
        │  │          bootstrap.min.js
        │  │          
        │  ├─editormd
        │  ├─jquery
        │  │  └─js
        │  │          jquery-3.2.1.min.js
        │  │          jquery.cookie.js
        │  │          jquery.easing.1.3.js
        │  │          jquery.flexslider-min.js
        │  │          jquery.form.min.js
        │  │          jquery.waypoints.min.js
        │  │          
        │  ├─jqueryconfirm
        │  │  ├─css
        │  │  │      jquery-confirm.min.css
        │  │  │      
        │  │  └─js
        │  │          jquery-confirm.min.js
        │  │          
        │  ├─json
        │  │  └─js
        │  │          json2.js
        │  │          
        │  ├─template
        │  │  ├─css
        │  │  │      animate.css
        │  │  │      flexslider.css
        │  │  │      icomoon.css
        │  │  │      style.css
        │  │  │      style.css.map
        │  │  │      
        │  │  └─js
        │  │          main.js
        │  │          modernizr-2.6.2.min.js
        │  │          
        │  └─validator
        │      ├─css
        │      │      bootstrapValidator.css
        │      │      bootstrapValidator.min.css
        │      │      
        │      └─js
        │          │  bootstrapValidator.js
        │          │  bootstrapValidator.min.js
        │          │  
        │          └─language
        │                  ar_MA.js
        │                  be_FR.js
        │                  be_NL.js
        │                  bg_BG.js
        │                  cs_CZ.js
        │                  da_DK.js
        │                  de_DE.js
        │                  en_US.js
        │                  es_CL.js
        │                  es_ES.js
        │                  fa_IR.js
        │                  fr_FR.js
        │                  gr_EL.js
        │                  he_IL.js
        │                  hu_HU.js
        │                  id_ID.js
        │                  it_IT.js
        │                  ja_JP.js
        │                  nl_NL.js
        │                  no_NO.js
        │                  pl_PL.js
        │                  pt_BR.js
        │                  pt_PT.js
        │                  ro_RO.js
        │                  ru_RU.js
        │                  sq_AL.js
        │                  sr_RS.js
        │                  sv_SE.js
        │                  th_TH.js
        │                  tr_TR.js
        │                  ua_UA.js
        │                  vi_VN.js
        │                  zh_CN.js
        │                  zh_TW.js
        │                  
        ├─upload
        │      editor_article.jsp
        │      upload_file.jsp
        │      
        └─WEB-INF
            │  web.xml
            │  
            └─lib
    
