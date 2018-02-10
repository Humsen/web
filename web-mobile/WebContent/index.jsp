<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>何明胜的个人网站</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="欢迎来到何明胜的个人网站.本站主要用于记录和分享本人的学习心得和编程经验,并分享常见可复用代码、推荐书籍以及软件等资源.本站源码已托管github,欢迎访问：https://github.com/HelloHusen/web" />
<meta name="keywords" content="何明胜,何明胜的个人网站,何明胜的博客,一格的程序人生" />
<meta name="author" content="何明胜,一格">

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<!-- jQuery -->
<script src="/plugins/jquery/js/jquery-3.2.1.min.js"></script>

<!-- 新版特性css -->
<link rel="stylesheet" href="/css/index/version-feature.css">
<!-- 最新更新文章简介 -->
<link rel="stylesheet" href="/css/index/article-profile.css">
<!-- 最新更新文章简介 -->
<link rel="stylesheet" href="/css/index/index.css">

<!-- js文件  -->
<script src="/js/index/index.js"></script>
<!-- 加载新版本特性 -->
<script type="text/javascript" src="/js/index/version-feature.js"></script>
<!-- 加载最新3篇博客 -->
<script type="text/javascript" src="/js/index/latestblog.js"></script>
<!-- 加载最近3篇代码 -->
<script type="text/javascript" src="/js/index/latestcode.js"></script>

</head>
<body>
	<input id="menuBarNo" type="hidden" value="0" />
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a> 

		<div id="fh5co-main">

			<!-- 加载新版特性 -->
			<div class="fh5co-narrow-content version-div">
				<div id="newVersionFeature" class="row"></div>
			</div>
			<hr class="index-hr" />
			<div class="fh5co-narrow-content article-box-div">
				<h2 class="fh5co-heading article-bar"
					data-animate-effect="fadeInLeft">最近更新的博客</h2>
				<a href="/module/blog.hms" class="read-more-article"><span
					class="glyphicon glyphicon-hand-right"></span>&nbsp;阅读更多博客</a>
				<div id="latestBlog" class="row"></div>
			</div>
			<hr class="index-hr" />
			<div class="fh5co-narrow-content article-box-div">
				<h2 class="fh5co-heading article-bar"
					data-animate-effect="fadeInLeft">最近更新的代码</h2>
				<a href="/module/code.hms" class="read-more-article"><span
					class="glyphicon glyphicon-hand-right"></span>&nbsp;阅读更多代码</a>
				<div id="latestCode" class="row"></div>
			</div>
			<script type="text/javascript"
				src="https://api.lwl12.com/hitokoto/main/get?encode=js&charset=utf-8"></script>
			<div id="lwlhitokoto">
				<script>
					lwlhitokoto();
				</script>
			</div>
		</div>
	</div>
</body>
</html>