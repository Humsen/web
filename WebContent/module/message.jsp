<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>留言区</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="何明胜，个人网站，何明胜的个人网站，何明胜的博客，一格的程序人生" />
<meta name="keywords" content="何明胜，个人网站，何明胜的个人网站，何明胜的博客，一格的程序人生" />
<meta name="author" content="何明胜，一格">

<%@include file="/plugins/plugins.jsp"%>

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<!-- 留言区css -->
<link rel="stylesheet" href="/css/message/message.css">
<!-- 分页器 -->
<link rel="stylesheet" href="/css/message/pager.css" />

<!-- 留言区js -->
<script src="/js/message/message.js"></script>
<!-- 分页器 -->
<script src="/js/message/pager.js"></script>

</head>
<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a> <a
			id="menuBarNo" style="display: none;">3</a>
		<%@include file="/login/login.jsp"%>

		<div id="fh5co-main">
			<%@include file="/navigation/topbar.jsp"%>
			<div id="message_box"></div>
		</div>

		<div id="pager"></div>
	</div>
</body>
</html>