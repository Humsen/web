<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>下载</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="何明胜，个人网站，何明胜的个人网站，何明胜的博客，一格的程序人生" />
<meta name="keywords" content="何明胜，个人网站，何明胜的个人网站，何明胜的博客，一格的程序人生" />
<meta name="author" content="何明胜，一格">

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<%@include file="/plugins/plugins.jsp"%>

<!-- 自定义css -->
<link rel="stylesheet" href="/css/download/download.css">

<!-- 自定义脚本 -->
<script src="/js/download/download.js"></script>
<script src="/js/pagination.js"></script>

</head>
<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a> <a
			id="menuBarNo" style="display: none;">4</a>
		<%@include file="/login/login.jsp"%>

		<div id="fh5co-main">
			<%@include file="/navigation/topbar.jsp"%>

			<div class="fh5co-narrow-content download-div">
				<h2 class="fh5co-heading" data-animate-effect="fadeInLeft">下载分享区</h2>
				<input style="display: none" id="num_downloadPageSize" value="10">
				<div id="list_file" class="row"></div>
			</div>
		</div>
	</div>
</body>
</html>