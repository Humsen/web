<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>何明胜的个人网站</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description"
	content="欢迎来到何明胜的个人网站.本站主要用于记录和分享本人的学习心得和编程经验,并分享常见可复用代码、推荐书籍以及软件等资源.本站源码已托管github,欢迎访问：https://github.com/HelloHusen/web" />
<meta name="keywords" content="何明胜,何明胜的个人网站,何明胜的博客,一格的程序人生" />
<meta name="author" content="何明胜,一格">

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<!-- jQuery -->
<script src="/plugins/jquery/js/jquery-3.2.1.min.js"></script>

<!-- 最新更新文章简介 -->
<link rel="stylesheet" href="/css/index/article-profile.css">
<!-- 最新更新文章简介 -->
<link rel="stylesheet" href="/css/index/index.css">

<!-- 加载最新3篇博客 -->
<script src="/js/index/latestblog.js"></script>
<!-- 加载最近3篇代码 -->
<script src="/js/index/latestcode.js"></script>
<!-- js文件  -->
<script src="/js/index/index.js"></script>

</head>
<body>
	<input id="menuBarNo" type="hidden" value="0" />

	<div id="fh5co-page">
		<!-- 左侧导航  -->
		<!-- 中间内容  -->
		<div id="fh5co-main">
			<!-- 首页轮播  -->
			<div id="indexCarousel" class="carousel slide carousel-height carousel-margin">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#indexCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#indexCarousel" data-slide-to="1"></li>
					<li data-target="#indexCarousel" data-slide-to="2"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item carousel-height active">
						<img src="/images/carousel/casel-1.jpg" alt="First slide">
					</div>
					<div class="item carousel-height">
						<img src="/images/carousel/casel-2.jpg" alt="Second slide">
					</div>
					<div class="item carousel-height">
						<img src="/images/carousel/casel-3.jpg" alt="Third slide">
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="left carousel-control" href="#indexCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#indexCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
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
			<!-- 每日一言  -->
			<script
				src="https://api.lwl12.com/hitokoto/main/get?encode=js&charset=utf-8"></script>
			<div id="lwlhitokoto">
				<script>
					lwlhitokoto();
				</script>
			</div>
		</div>
		<!-- 右侧导航  -->
	</div>
</body>
</html>