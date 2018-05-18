/**
 * @author 何明胜
 * 
 * 2017年10月25日
 */

// 图片是否修改
is_pic_modify = false;

$(function() {
	// 填充用户信息
	fillUserInfo();
	// 添加验证
	modifyValidator();
	// 用户头像更换点击事件
	userHeadChangeClick();
	// 提交事件
	$('#submitModify').click(submitForm);
	// 个人资料点击跳转修改邮箱
	$('#btn_goModifyEmail').click(gotoModifyEmailClick);
});

/**
 * 用户头像更新点击事件
 * 
 * @returns
 */
function userHeadChangeClick() {
	$('#upimg').on('change', function() {
		var objUrl = getObjectURL(this.files[0]); // 获取图片的路径，该路径不是图片在本地的路径

		if (objUrl) {
			$('#imgshow').attr('src', objUrl); // 将图片路径存入src中，显示出图片
			is_pic_modify = true;
		}
	});

	// 绑定点击事件
	$('#imgshow').click(function() {
		$('#upimg').click(); // 隐藏了input:file样式后，点击头像就可以本地上传
	});
	$('#changepic').click(function() {
		$('#upimg').click(); // 隐藏了input:file样式后，点击头像就可以本地上传
	});
}

/**
 * 填充用户信息
 * 
 * @returns
 */
function fillUserInfo() {
	// 加载用户信息
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
			$('#imgshow').attr('src', response.userHeadUrl);// 显示图片
			$('#headurl').val(response.userHeadUrl);// 放在input中
			$('#userid').val(response.userId);
			$('#username').val(response.userName);
			$('#txt_userNickName').val(response.userNickName);
			$('#txt_userEmail').val(response.userEmail);
			$('#phone').val(response.userPhone);
			$('#age').val(response.userAge);
			$('#address').val(response.userAddress);
		}
	});
}

/**
 * 提交修改的信息
 * 
 * @returns
 */
function submitForm() {
	// 如果图像修改，上传新头像
	if (is_pic_modify) {
		// 上传图片
		var url = uploadPic();

		if (typeof url == 'undefined') {
			return;
		} else {
			$('#headurl').val(url);
		}
	}

	var formDataJson = $('#modifyForm').form2Json();
	// 邮箱被禁用，单独添加
	formDataJson['userEmail'] = $('#txt_userEmail').val();
	formDataJson = JSON.stringify(formDataJson);

	var new_username = $('#username').val();

	$.ajax({
		type : 'POST',
		async : false,
		url : '/userInfo.hms',
		data : {
			type : 'modify_user_info',
			formdata : formDataJson
		},
		success : function(response) {
			if (response == 1) {
				$.confirm({
					title : '修改成功',
					content : '是否前往查看个人资料？',
					autoClose : 'ok|3000',
					type : 'green',
					buttons : {
						ok : {
							text : '是，立即前往',
							btnClass : 'btn-primary',
							keys : [ 'enter', 'a' ],
							action : function() {
								// $('#personaldata').click();
								$('#showuserinfo').click();
							}
						},
						cancel : {
							text : '否，继续修改',
							btnClass : 'btn-primary',
							keys : [ 'ESC' ]
						}
					}
				});
				// 如果修改了用户名，则修改cookie值
				if (new_username != $.cookie('username')) {
					$.cookie('username', new_username, {
						path : '/'
					});
				}
			} else {
				$.confirm({
					title : '修改个人资料失败',
					content : '请稍后重试',
					autoClose : 'ok|2000',
					type : 'red',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						}
					}
				});
			}

		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '修改个人资料出错',
				content : textStatus + ' : ' + XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'red',
				buttons : {
					ok : {
						text : '确认',
						btnClass : 'btn-primary',
					}
				}
			});
		}
	});
}

/**
 * 上传图片
 * 
 * @param piv
 * @returns
 */
function uploadPic() {
	var pic = $('#upimg')[0].files[0];
	var url;

	if (typeof (pic) == 'undefined') {
		return;
	}

	var fd = new FormData();
	fd.append('uploadFile', pic);

	$.ajax({
		url : '/imageUpload.hms',
		type : 'post',
		async : false,
		dataType : 'json',
		// Form数据
		data : fd,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			url = data.url;
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '上传图片出错',
				content : textStatus + ' : ' + XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'red',
				buttons : {
					ok : {
						text : '确认',
						btnClass : 'btn-primary',
					}
				}
			});
		}
	});

	return url;
}

/**
 * 获取file的url
 * 
 * @param file
 * @returns
 */
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

/**
 * 修改个人信息表单添加验证
 * 
 * @returns
 */
function modifyValidator() {
	$('#modifyForm')
			.bootstrapValidator(
					{
						message : '输入无效!',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							userName : {
								message : '用户名无效!',
								validators : {
									notEmpty : {
										message : '用户名不能为空!'
									},
									stringLength : {
										min : 5,
										max : 15,
										message : '用户名长度必须在5到15位之间!'
									},
									/*
									 * remote: { url: 'remote.php', message:
									 * 'The username is not available' },
									 */
									regexp : {
										regexp : /^[a-zA-Z0-9_\.]+$/,
										message : '用户名只能由字母，数字，点和下划线几种组成!'
									}
								}
							},
							userEmail : {
								validators : {
									notEmpty : {
										message : '邮箱为必填哦!'
									},
									emailAddress : {
										message : '输入邮件地址无效!'
									}
								}
							},
							userPhone : {
								validators : {
									regexp : {
										regexp : /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/,
										message : '手机号不合法!'
									}
								}
							},
							userAge : {
								validators : {
									lessThan : {
										value : 100,
										inclusive : true,
										message : '年龄不能大于100'
									},
									greaterThan : {
										value : 0,
										inclusive : false,
										message : '年龄不能小于1'
									}
								}
							}
						}
					});

}

/**
 * 人资料点击跳转修改邮箱
 * 
 * @returns
 */
function gotoModifyEmailClick() {
	$('#btn_modifyEmail').click();
}