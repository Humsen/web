<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>联系站长</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="何明胜，个人网站，何明胜的个人网站，何明胜的博客，一格的程序人生" />
<meta name="keywords" content="何明胜，个人网站，何明胜的个人网站，何明胜的博客，一格的程序人生" />
<meta name="author" content="何明胜，一格">

<!-- 网站图标 -->
<link rel="shortcut icon" href="/images/favicon.ico">

<%@include file="/plugins/plugins.jsp"%>
<!-- 自定义css -->
<link rel="stylesheet" href="/css/contact/contact.css">
<!-- 异步发邮件 -->
<script src="/js/contact/contact.js"></script>

</head>
<body>
	<div id="fh5co-page">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a> <a
			id="menuBarNo" style="display: none;">5</a>
		<%@include file="/login/login.jsp"%>


		<div id="fh5co-main">
			<%@include file="/navigation/topbar.jsp"%>

			<div class="fh5co-narrow-content animate-box contact-div"
				data-animate-effect="fadeInLeft">
				<div class="row">
					<div class="col-md-4">
						<h2>联系站长</h2>
					</div>
				</div>
				<form id="form_contactAdmin">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="姓名"
											name="contactName">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="邮箱"
											name="contactEmail">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="电话"
											name="contactPhone">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<textarea id="message" cols="30" rows="7" class="form-control"
											placeholder="你想说..." name="contactContent"></textarea>
									</div>
									<div class="form-group">
										<input id="btn_sendEmail" type="button"
											class="btn btn-primary btn-md" value="发送邮件">
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>