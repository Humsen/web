<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>写新文章</title>
</head>

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">
	
<%@include file="/plugins/plugins.jsp" %>

<!-- editormd start -->
<link rel="stylesheet" href="/plugins/editormd/css/editormd.min.css" />
<!-- jquery confirm -->
<link rel="stylesheet" href="/plugins/jqueryconfirm/css/jquery-confirm.min.css" />
<!-- 自定义css -->
<link rel="stylesheet" href="/css/upload/editor-article.css">


<script type="text/javascript" src="/plugins/jquery/js/jquery-3.2.1.min.js"></script>
<!-- JQuery cookie -->
<script type="text/javascript" src="/plugins/jquery/js/jquery.cookie.js"></script>
<!-- editormd -->
<script type="text/javascript" src="/plugins/editormd/js/editormd.min.js"></script>
<script type="text/javascript" src="/js/editor/editor.js"></script>
<!-- 自定开开发工具包  -->
<script type="text/javascript" src="/js/customize-sdk.js"></script>
<!-- jquery confirm -->
<script type="text/javascript" src="/plugins/jqueryconfirm/js/jquery-confirm.min.js"></script>

<body>
	<label class="table-label">题目:</label>
	<input id="title" class="title-input">
	
	<label class="table-label">作者:</label>
	<input id="author" class="author-input">
	
	<input type="radio" value="blog" name="article" checked="checked" class="blog-input"/>博客
	<input type="radio" value="code" name="article" />代码
	
	<button id="btn_clearEditor" class="btn btn-warning btn-clear">清空编辑区</button>
	<button id="btn_publish" class="btn btn-success btn-publish">发布</button>
	
	<label class="table-label summary-label">摘要:</label>
	<textarea id="summary" class="summary-input"></textarea>
	
	<hr class="dotted-line"/>
	<!-- editormd start -->
	<div class="editormd" id="div_editorMd">
		<textarea class="editormd-markdown-textarea" name="editorMd" id="in_editorMdContent"></textarea>
		<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
		<!-- html textarea 需要开启配置项 saveHTMLToTextarea == true -->
		<textarea class="editormd-html-textarea" name="editorHtml" id="in_editorHtml"></textarea>
	</div>
	<!-- editormd end -->
</body>
</html>