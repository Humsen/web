/**
 * @author 何明胜
 * 
 * 2017年12月26日
 */

/** 显示所有用户 * */
$(document).ready(function() {
	$('#tbl_showUsers').DataTable({
		serverSide : true,
		autoWidth : false,
		ordering : true,
		processing : true,
		ajax : {
			url : '/users/query.hms',
			type : 'POST'
		},
		/*
		 * "columnDefs" : [ { "width" : "200px", "targets" : 0 } ],
		 */
		columns : [ {
			"title" : "用户id",
			data : 'userId',
			'class' : 'user-id-width'
		}, {
			"title" : "用户名",
			data : 'userName',
		}, {
			"title" : "用户昵称",
			data : 'userNickName',
			'class' : 'user-nick-name-width'
		}, {
			"title" : "用户密码",
			data : 'userPassword',
			'class' : 'user-nick-name-width'
		}, {
			"title" : "邮箱",
			data : 'userEmail',
		}, {
			"title" : "手机号",
			data : 'userPhone',
			'class' : 'user-nick-name-width'
		}, {
			"title" : "年龄",
			data : 'userAge',
			'class' : 'user-common-width'
		}, {
			"title" : "性别",
			data : 'userSex',
			'class' : 'user-common-width'
		}, {
			"title" : "地址",
			data : 'userAddress',
			'class' : 'user-common-width'
		}, {
			"title" : "头像链接",
			data : 'userHeadUrl',
			"class" : "center",
			'class' : 'user-nick-name-width'
		} ]
	});
});