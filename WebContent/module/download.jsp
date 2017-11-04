<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>下载</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="欢迎来到何明胜的个人网站.本站主要用于记录和分享本人的学习心得和编程经验,并分享常见可复用代码、推荐书籍以及软件等资源.本站源码已托管github,欢迎访问：https://github.com/HelloHusen/web" />
<meta name="keywords" content="何明胜,何明胜的个人网站,何明胜的博客,一格的程序人生" />
<meta name="author" content="何明胜,一格">

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
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
		<input id="menuBarNo" type="hidden" value="4"/>
		<%@include file="/login/login.jsp"%>

		<div id="fh5co-main">
			<%@include file="/navigation/topbar.jsp"%>

			<div class="fh5co-narrow-content download-div">
				<h2 class="fh5co-heading" data-animate-effect="fadeInLeft">下载分享区</h2>
				<input type="hidden" id="num_downloadPageSize" value="10">
				<div id="list_file" class="row"></div>
			</div>
		</div>
	</div>
</body>
</html>