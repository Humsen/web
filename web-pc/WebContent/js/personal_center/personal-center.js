/**
 * 个人中心的js
 * 
 * @author 何明胜
 * 
 * 2017年10月17日
 */

$(function() {
	/** 顶部导航栏 **/
	$.ajax({
		url : '/module/navigation/topbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$(document.body).prepend(data);
		}
	});

	/** 登录控制 **/
	$.ajax({
		url : '/module/login/login.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$(document.body).prepend(data);
		}
	});	
});

/** 初始化 */
$(function() {
	// 如果没有登录
	/*
	 * if(!$.cookie('username')){
	 * window.location.replace('/module/error/error.html'); }
	 */

	// 调整顶部导航的宽度
	$('#topBar').css('width', '100%');

	/** 手机隐藏右边导航 */
	var mobile_flag = $.isMobile(); // false为PC端，true为手机端
	if (mobile_flag) {
		// 右边导航栏隐藏
		$('#rightBar').hide();
		// 顶部调节排版
		$('.access-today').css('margin-left', 0);
		$('.online-current').css('margin-left', '32px');
	}
	// 添加返回首页按钮
	$('.choose-theme')
			.append(
					'<a class="btn btn-warning btn-sm return-index" href="/" role="button">返回首页</a>');

	// 加载网站管理
	loadAdminManageMenu();
});

/**
 * 注册点击事件
 * 
 * @returns
 */
$(function() {
	$('#editorVerFeature').click(editVerFeature);
	$('#showuserinfo').click(showUserInfo);
	$('#edituserinfo').click(editUserInfo);
	$('#modifypassword').click(modifyPassword);
	$('#btn_modifyEmail').click(modifyEmail);
	$('#btn_uploadFile').click(uploadFile);
	$('#btn_showUsers').click(showUsers);
});

/**
 * 判断是否加载超级管理员的网站管理
 * 
 * @returns
 */
function loadAdminManageMenu() {
	if ($.cookie('username') == 'husen') {
		$('#menunav')
				.append(
						'<!-- 站长才有 -->'
								+ '<li><a href="#webManagement" class="nav-header collapsed"'
								+ 'data-toggle="collapse"> <i class="glyphicon glyphicon-cog"></i> 网站管理<span'
								+ ' class="pull-right glyphicon glyphicon-chevron-down"></span>'
								+ '</a>'
								+ '<ul id="webManagement" class="nav nav-list collapse secondmenu">'
								+ '<li><a href="/editor/article.hms" target="_blank"><i class="glyphicon glyphicon-user"></i> 写新博客</a></li>'
								+ '<li><a href="/editor/article.hms" target="_blank"><i class="glyphicon glyphicon-th-list"></i> 写新代码库</a></li>'
								+ '<li><a href="#" id="btn_uploadFile"><i class="glyphicon glyphicon-asterisk"></i> 上传新文件</a></li>'
								+ '<li><a href="#" id="editorVerFeature"><i class="glyphicon glyphicon-edit"></i> 编辑新版特性</a></li>'
								+ '<li><a href="#" id="btn_showUsers"><i class="glyphicon glyphicon-eye-open"></i> 查看所有用户</a></li>'
								+ '</ul>' + '</li>');
	} else {
		$('#menunav')
				.append(
						'<!-- 用户个人管理 -->'
								+ '<li><a href="#webManagement" class="nav-header collapsed"'
								+ 'data-toggle="collapse"> <i class="glyphicon glyphicon-cog"></i> 新文章 & 新文件<span'
								+ ' class="pull-right glyphicon glyphicon-chevron-down"></span>'
								+ '</a>'
								+ '<ul id="webManagement" class="nav nav-list collapse secondmenu">'
								+ '<li><a href="/editor/article.hms" target="_blank"><i class="glyphicon glyphicon-user"></i> 写新博客</a></li>'
								+ '<li><a href="/editor/article.hms" target="_blank"><i class="glyphicon glyphicon-th-list"></i> 写新代码库</a></li>'
								+ '<li><a href="#" id="btn_uploadFile"><i class="glyphicon glyphicon-asterisk"></i> 上传新文件</a></li>'
								+ '</ul>' + '</li>');
	}
}

/**
 * 修改密码点击
 * 
 * @returns
 */
function modifyPassword() {
	if (isNotLogin()) {
		return;
	}

	$('#mainWindow').html('');

	$.ajax({
		url : '/personal_center/modify_pwd.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#mainWindow').html(data);
		}
	});
}

/**
 * 判断是否登录
 * 
 * @returns
 */
function isNotLogin() {
	if (!$.cookie('username')) {
		$.confirm({
			title : false,
			content : '请先登录！',
			autoClose : 'ok|2000',
			type : 'red',
			buttons : {
				ok : {
					text : '确认',
					btnClass : 'btn-primary',
				},
			}
		});

		return true;
	}

	return false;
}

/**
 * 显示用户信息
 * 
 * @returns
 */
function showUserInfo() {
	if (isNotLogin()) {
		return;
	}

	$.ajax({
		type : 'POST',
		async : false,
		url : '/userInfo.hms',
		dataType : 'json',
		data : {
			type : 'query_user_info',
			userName : $.cookie('username')
		},
		success : function(response) {
			$('#mainWindow').html('');
			$('#mainWindow')
					.append(
							'<form class="form-horizontal form-show-userinfo">'
									+ '<div class="form-group">'
									+ '	<label class="col-sm-4 control-label">头像</label>'
									+ '	<div class="col-sm-8">'
									+ '<img src="'
									+ response.userHeadUrl
									+ '" alt=""'
									+ 'class="img-circle img-user-head">'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="col-sm-4 control-label">用户名（用作登录）</label>'
									+ '<div class="col-sm-8">'
									+ '<p class="form-control-static form-show-p">'
									+ response.userName
									+ '</p>'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="col-sm-4 control-label">用户昵称（用作显示）</label>'
									+ '<div class="col-sm-8">'
									+ '<p class="form-control-static form-show-p">'
									+ response.userNickName
									+ '</p>'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="col-sm-4 control-label">邮箱</label>'
									+ '<div class="col-sm-8">'
									+ '<p class="form-control-static form-show-p">'
									+ response.userEmail
									+ '</p>'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="col-sm-4 control-label">手机</label>'
									+ '<div class="col-sm-8">'
									+ '<p class="form-control-static form-show-p">'
									+ response.userPhone
									+ '</p>'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="col-sm-4 control-label">年龄</label>'
									+ '<div class="col-sm-8">'
									+ '<p class="form-control-static form-show-p">'
									+ response.userAge
									+ '</p>'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="col-sm-4 control-label">地址</label>'
									+ '<div class="col-sm-8">'
									+ '<p class="form-control-static form-show-p">'
									+ response.userAddress
									+ '</p>'
									+ '</div>' + '</div>' + '</form>');
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '用户信息加载出错',
				content : textStatus + ' : ' + XMLHttpRequest.status,
				autoClose : 'ok|1000',
				type : 'green',
				buttons : {
					ok : {
						text : '确认',
						btnClass : 'btn-primary',
					},
				}
			});
		}
	});
}

/**
 * 编辑用户信息
 * 
 * @returns
 */
function editUserInfo() {
	if (isNotLogin()) {
		return;
	}

	$('#mainWindow').html('');

	$.ajax({
		url : '/personal_center/modify_userinfo.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#mainWindow').html(data);
		}
	});
}

/**
 * 编辑新版特性点击事件
 * 
 * @returns
 */
function editVerFeature() {
	$('#mainWindow').html('');

	$.ajax({
		url : '/personal_center/editor_version.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#mainWindow').html(data);
		}
	});
}

/**
 * 修改邮箱点击事件
 * 
 * @returns
 */
function modifyEmail() {
	if (isNotLogin()) {
		return;
	}

	$('#mainWindow').html('');

	$.ajax({
		url : '/personal_center/modify_email.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#mainWindow').html(data);
		}
	});
}

/**
 * 上传新文件点击事件
 * 
 * @returns
 */
function uploadFile() {
	$('#mainWindow').html('');
	
	$.ajax({
		url : '/personal_center/upload_file.html',
		async : false,
		type : 'GET',
		success : function(data) {
			$('#mainWindow').html(data);
		}
	});
}

/**
 * 显示所有用户点击事件
 * @returns
 */
function showUsers(){
	$('#mainWindow').html('');

	$.ajax({
		url : '/personal_center/show_users.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#mainWindow').html(data);
		}
	});
}