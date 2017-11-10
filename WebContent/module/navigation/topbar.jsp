<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 此处修改记得更新 后端通用模板  -->

<!-- 顶部固定导航栏 -->
<link rel="stylesheet" href="/css/navigation/topbar.css">
<script src="/js/navigation/topbar.js"></script>

<!-- 导航区  -->
<nav class="navbar navbar-default navbar-fixed-top">
	<!-- logo -->
	<div class="navbar-header">
		<a class="navbar-brand" href="/"> <span id="trademark"
			class="glyphicon glyphicon-header"></span>
		</a>
	</div>
	<!-- 访问统计  -->
	<div class="access-statistics">
		<label id="txt_accessToday"></label> <label id="txt_accessTotal"></label>
		<label id="txt_onlineCurrent"></label>
	</div>
	<div class="colmun-nav">
		<ul class="nav nav-pills topbar-nav">
			<li role="presentation"><a href="/">首页</a></li>
			<li role="presentation"><a href="/topic/blog/">博客</a></li>
			<li role="presentation"><a href="/topic/code/">代码库</a></li>
			<li role="presentation"><a href="/topic/message/">留言</a></li>
			<li role="presentation"><a href="/topic/download/">下载</a></li>
			<li role="presentation"><a href="/topic/contact/">联系站长</a></li>
		</ul>
	</div>
	<!-- 登录注册 -->
	<div class="login-register">
		<a id="loginBtn" class="btn btn-warning btn-sm" href="#" role="button"
			data-toggle="modal" data-target="#login" href=""> <span
			class="glyphicon glyphicon-user"></span>登录
		</a> <a id="registerBtn" class="btn btn-info btn-sm" href="#"
			role="button" data-toggle="modal" data-target="#register" href="">
			<span class="glyphicon glyphicon-log-in"></span>注册
		</a> <a id="persCenterBtn" class="btn btn-success btn-sm" href="#"
			role="button" data-toggle="modal" data-target="#" href=""> <span
			class="glyphicon glyphicon-header"></span>个人中心
		</a> <a id="quitLoginBtn" class="btn btn-primary btn-sm" href="#"
			role="button" data-toggle="modal" data-target="#" href=""> <span
			class="glyphicon glyphicon-log-out"></span>退出
		</a>
	</div>
	<!-- 其他 -->
	<div class="nav-others">
		<a class="btn btn-default btn-sm" href="https://m.hemingsheng.cn/" role="button"><span
			class="glyphicon glyphicon glyphicon-phone"></span> 手机版</a>
	</div>
</nav>