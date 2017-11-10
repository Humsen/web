<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>博客</title>
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
<script src="/js/article/blog.js"></script>
<script src="/js/pagination.js"></script>

</head>
<body>
	<%@include file="/module/common.jsp"%>
	<input id="menuBarNo" type="hidden" value="1" />
	
	<div id="fh5co-page">
		<!-- 左侧导航  -->
		<%@include file="/module/navigation/leftbar.jsp"%>
		<!-- 中间内容  -->
		<div id="fh5co-main">
			<!-- 选择每页显示的条数 -->
			<div class="collapse navbar-collapse" id="choose_page_size">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"
						class="dropdown-toggle choose-page-size" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"> 每页显示<span
							id="num_blogPageSize">5</span>条<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li value="5"><a href="#">每页显示5条</a></li>
							<li value="10"><a href="#">每页显示10条</a></li>
							<li value="20"><a href="#">每页显示20条</a></li>
						</ul></li>
				</ul>
			</div>

			<div id="list_blog" class="fh5co-post">
				<!-- js脚本动态添加内容 -->
			</div>
		</div>
		<!-- 右侧导航  -->
		<%@include file="/module/navigation/rightbar.jsp"%>
	</div>
</body>
</html>