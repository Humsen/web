<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 上部导航栏 -->
<link rel="stylesheet" href="/css/navigation/topbar.css">
<script type="text/javascript" src="/js/navigation/topbar.js"></script>

<nav id="topBar" class="navbar navbar-default topbar-nav">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand navbar-brand-a" href="/"> <span
				id="trademark" class="glyphicon glyphicon-header" aria-hidden="true"></span>
			</a> <label id="accessToday" class="access-today"></label> &nbsp; <label
				id="accessTotal"></label>
		</div>

		<label id="onlineCurrent" class="online-current"></label> <a
			id="loginBtn" class="btn btn-warning btn-sm topbar-btn-login"
			href="#" role="button" data-toggle="modal" data-target="#login"
			href=""> <span class="glyphicon glyphicon-user"></span>登录
		</a> <a id="registerBtn" class="btn btn-info btn-sm topbar-btn-right"
			href="#" role="button" data-toggle="modal" data-target="#register"
			href=""> <span class="glyphicon glyphicon-log-in"></span>注册
		</a> <a id="persCenterBtn" class="btn btn-success btn-sm topbar-btn-pers"
			href="#" role="button" data-toggle="modal" data-target="#" href="">
			<span class="glyphicon glyphicon-header"></span>个人中心
		</a> <a id="quitLoginBtn" class="btn btn-primary btn-sm topbar-btn-right"
			href="#" role="button" data-toggle="modal" data-target="#" href="">
			<span class="glyphicon glyphicon-log-out"></span>退出
		</a>
	</div>
</nav>
<!-- 选择网站主题  -->

<div class="choose-theme"><button type="button" class="btn btn-success btn-xs">下一个主题&ensp;<span class="glyphicon glyphicon-forward"></span></button></div>