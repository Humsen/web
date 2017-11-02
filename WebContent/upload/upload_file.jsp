<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>文件上传</title>

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<script type="text/javascript" src="/plugins/jquery/js/jquery-3.2.1.min.js"></script>
<!-- JQuery cookie -->
<script type="text/javascript" src="/plugins/jquery/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/download/upload.js"></script>

</head>
<body>
	<form action="/fileUpload" enctype="multipart/form-data" method="post">
		上传用户：<input type="text" name="username"><br /> 上传文件1：<input
			type="file" name="file1"><br /> <input type="submit"
			value="提交">
	</form>
</body>
</html>