<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>代码库</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="欢迎来到何明胜的个人网站.本站主要用于记录和分享本人的学习心得和编程经验,并分享常见可复用代码、推荐书籍以及软件等资源.本站源码已托管github,欢迎访问：https://github.com/HelloHusen/web" />
<meta name="keywords" content="何明胜,何明胜的个人网站,何明胜的博客,一格的程序人生" />
<meta name="author" content="何明胜,一格">

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<%@include file="/plugins/plugins.jsp"%>

<!-- editor.md -->
<link rel="stylesheet"
	href="/plugins/editormd/css/editormd.preview.min.css" />
<link rel="stylesheet" href="/plugins/editormd/css/editormd.min.css" />

<!-- editor.md -->
<script src="/plugins/editormd/lib/marked.min.js"></script>
<script src="/plugins/editormd/lib/prettify.min.js"></script>
<script src="/plugins/editormd/js/editormd.min.js"></script>

<!-- 自定义CSS -->
<link rel="stylesheet" href="/css/article/article.css">
<!-- 自定义脚本 -->
<script src="/js/article/code-library.js"></script>
<script src="/js/pagination.js"></script>

</head>
<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a> <input
			id="menuBarNo" type="hidden" value="2" />
		<%@include file="/login/login.jsp"%>

		<div id="fh5co-main">
			<%@include file="/navigation/topbar.jsp"%>
			<div id="list_code" class="fh5co-post">
				<!-- js脚本动态添加内容 -->
			</div>
		</div>
	</div>
</body>
</html>