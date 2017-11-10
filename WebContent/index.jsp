<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>何明胜的个人网站</title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="description"
	content="欢迎来到何明胜的个人网站.本站主要用于记录和分享本人的学习心得和编程经验,并分享常见可复用代码、推荐书籍以及软件等资源.本站源码已托管github,欢迎访问：https://github.com/HelloHusen/web" />
<meta name="keywords" content="何明胜,何明胜的个人网站,何明胜的博客,一格的程序人生" />
<meta name="author" content="何明胜,一格">

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<%@include file="/plugins/plugins.jsp"%>

<!-- 最新更新文章简介 -->
<link rel="stylesheet" href="/css/index/article-profile.css">
<!-- 最新更新文章简介 -->
<link rel="stylesheet" href="/css/index/index.css">

<!-- 加载最新3篇博客 -->
<script type="text/javascript" src="/js/index/latestblog.js"></script>
<!-- 加载最近3篇代码 -->
<script type="text/javascript" src="/js/index/latestcode.js"></script>

</head>
<body>
	<%@include file="/module/common.jsp"%>
	<input id="menuBarNo" type="hidden" value="0" />

	<div id="fh5co-page">
		<!-- 左侧导航  -->
		<%@include file="/module/navigation/leftbar.jsp"%>
		<!-- 中间内容  -->
		<div id="fh5co-main">
			<div class="fh5co-narrow-content article-box-div">
				<h2 class="fh5co-heading article-bar"
					data-animate-effect="fadeInLeft">最近更新的博客</h2>
				<a href="/module/blog.jsp" class="read-more-article"><span
					class="glyphicon glyphicon-hand-right"></span>&nbsp;阅读更多博客</a>
				<div id="latestBlog" class="row"></div>
			</div>
			<hr class="index-hr" />
			<div class="fh5co-narrow-content article-box-div">
				<h2 class="fh5co-heading article-bar"
					data-animate-effect="fadeInLeft">最近更新的代码</h2>
				<a href="/module/code_library.jsp" class="read-more-article"><span
					class="glyphicon glyphicon-hand-right"></span>&nbsp;阅读更多代码</a>
				<div id="latestCode" class="row"></div>
			</div>
			<!-- 每日一言  -->
			<script type="text/javascript"
				src="https://api.lwl12.com/hitokoto/main/get?encode=js&charset=utf-8"></script>
			<div id="lwlhitokoto">
				<script>
					lwlhitokoto()
				</script>
			</div>
		</div>
		<!-- 右侧导航  -->
		<%@include file="/module/navigation/rightbar.jsp"%>
	</div>
</body>
</html>