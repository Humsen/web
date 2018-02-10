/**
 * @author 何明胜
 *
 * 2017年11月1日
 */

/** 初始化 */
$(document).ready(function() {
	// 有效性校验
	modifyEmailValidator();

	//认证
	if($('#btn_modifyEmailAuth').length > 0){
		// 绑定发送验证码事件
		$('#btn_sendValidateCode').click(sendAuthValidateCodeClick);
		
		//初始化下一步按钮
		initNextStepBtn();
	}
	
	//绑定
	if($('#btn_modifyEmailBind').length > 0){
		// 绑定发送验证码事件
		$('#btn_sendValidateCode').click(sendBindValidateCodeClick);
		
		//初始化提交按钮
		initSubmitBtn();
	}
});

/********************************************* 认证区 **********************************************/

/**
 * 初始化下一步按钮
 * @returns
 */
function initNextStepBtn(){
	var btnModifyEmailAuth = $('#btn_modifyEmailAuth');
	btnModifyEmailAuth.attr('disabled', 'true');
	
	//绑定邮箱和输入验证码监听
	$('#txt_modifyEmail,#txt_validateCode2').on('input propertychange', function() {
		//点击下一步，先看表单是否合法
		var $formModifyEmail = $('#form_modifyEmail').data('bootstrapValidator');
		$formModifyEmail.validate();

		if ($formModifyEmail.isValid()) {
			btnModifyEmailAuth.removeAttr('disabled');
			btnModifyEmailAuth.addClass('btn-success');
		}
	});
	
	//下一步按钮点击事件
	$('#btn_modifyEmailAuth').click(nextStepClick);
}

/**
 * 修改邮箱, 旧邮箱认证, 下一步点击事件
 * @returns
 */
function nextStepClick(){
	$.ajax({
		url : '/userInfo/code.hms',
		async : true,
		type : 'POST',
		data : {
			type : 'auth_code_old_email',
			randomCode : $('#txt_validateCode2').val(),
		},
		success : function(response) {
			if (response != 0) {
				$('#mainWindow').html('');
				
				$.ajax( {
				    url: '/personal_center/modify_email1.html', //这里是静态页的地址
				    async : false,
				    type: 'GET', //静态页用get方法，否则服务器会抛出405错误
				    success: function(data){
				    	$('#mainWindow').html(data);
				    }
				});
			} else {
				$.confirm({
					title : '验证失败',
					content : '请稍后再尝试',
					autoClose : 'ok|3000',
					type : 'red',
					buttons : {
						ok : {
							text : '确定',
							btnClass : 'btn-primary',
						}
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '验证码校验发送失败',
				content : textStatus + ' : '
						+ XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'red',
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
 * 修改邮箱, 点击发送验证码事件
 * @returns
 */
function sendAuthValidateCodeClick() {
	// 判断邮箱格式是否正确
	var $emailValidate = $('#form_modifyEmail').data('bootstrapValidator');
	$emailValidate.validateField('email');

	var isEmailValid = $emailValidate.isValidField('email');
	if (!isEmailValid) {
		return;
	}

	// 修改邮箱, 发送验证码到旧邮箱
	$.ajax({
		url : '/userInfo/code.hms',
		async : true,// 异步，启动倒计时
		type : 'POST',
		data : {
			type : 'send_code_old_email',
			email : $('#txt_modifyEmail').val(),
		},
		success : function(response) {
			if (response != 1) {
				$.confirm({
					title : '验证码发送失败',
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
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '验证码发送失败',
				content : textStatus + ' : ' + XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'red',
				buttons : {
					ok : {
						text : '确认',
						btnClass : 'btn-primary',
					},
				}
			});

		}
	});
	// 60s倒计时
	$.codeCountDown($('#btn_sendValidateCode'));
}

/********************************************* 绑定区 **********************************************/

/**
 * 初始化提交按钮
 * @returns
 */
function initSubmitBtn(){
	var btnModifyEmailBind= $('#btn_modifyEmailBind');
	btnModifyEmailBind.attr('disabled', 'true');
	
	//绑定邮箱和输入验证码监听
	$('#txt_modifyEmail,#txt_validateCode2').on('input propertychange', function() {
		//点击下一步，先看表单是否合法
		var $formModifyEmail = $('#form_modifyEmail').data('bootstrapValidator');
		$formModifyEmail.validate();
		
		if ($formModifyEmail.isValid()) {
			btnModifyEmailBind.removeAttr('disabled');
			btnModifyEmailBind.addClass('btn-success');
		}
	});
	
	//提交按钮点击事件
	$('#btn_modifyEmailBind').click(submitModifyEmailClick);
}

/**
 * 修改邮箱, 绑定新邮箱提交事件
 * @returns
 */
function submitModifyEmailClick(){
	$.ajax({
		url : '/userInfo/code.hms',
		async : false,// 同步，会阻塞操作
		type : 'POST',// PUT DELETE POST
		data : {
			type : 'auth_code_bind_email',
			randomCode : $('#txt_validateCode2').val(),
			userName : $.cookie('username'),
			email : $('#txt_modifyEmail').val(),
		},
		success : function(response) {
			if (response != 0) {
				$.confirm({
					title : '邮箱修改成功',
					content : '你的邮箱已成功修改为：' + $('#txt_modifyEmail').val(),
					autoClose : 'ok|4000',
					type : 'green',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});
				
				//清空修改区
				$('#mainWindow').html('');
			} else {
				$.confirm({
					title : '验证失败',
					content : '请稍后再尝试',
					autoClose : 'ok|3000',
					type : 'red',
					buttons : {
						ok : {
							text : '确定',
							btnClass : 'btn-primary',
						}
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '验证码校验发送失败',
				content : textStatus + ' : '
						+ XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'red',
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
 * 绑定新邮箱, 发送验证码
 * @returns
 */
function sendBindValidateCodeClick(){
	// 判断邮箱格式是否正确
	var $emailValidate = $('#form_modifyEmail').data('bootstrapValidator');
	$emailValidate.validateField('email');

	var isEmailValid = $emailValidate.isValidField('email');
	if (!isEmailValid) {
		return;
	}

	// 发送ajax请求
	$.ajax({
		url : '/userInfo/code.hms',
		async : true,// 异步，启动倒计时
		type : 'POST',
		data : {
			type : 'send_code_bind_email',
			email : $('#txt_modifyEmail').val(),
		},
		success : function(response) {
			if (response != 1) {
				$.confirm({
					title : '验证码发送失败',
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
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '验证码发送失败',
				content : textStatus + ' : ' + XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'red',
				buttons : {
					ok : {
						text : '确认',
						btnClass : 'btn-primary',
					},
				}
			});

		}
	});
	// 60s倒计时
	$.codeCountDown($('#btn_sendValidateCode'));
}

/********************************************* 公共区 **********************************************/

/**
 * 添加修改邮箱校验
 * 
 * @returns
 */
function modifyEmailValidator() {
	$('#form_modifyEmail')
			.bootstrapValidator(
					{
						message : '输入无效!',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							email : {
								message : '用户名无效!',
								validators : {
									notEmpty : {
										message : '邮箱不能为空!'
									},
									regexp : {
										regexp : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
										message : '邮箱格式不正确'
									}
								}
							},
							validateCode : {
								message : '验证码无效!',
								validators : {
									notEmpty : {
										message : '验证码不能为空!'
									},
									regexp : {
										regexp : /^\d{6}$/,
										message : '验证码为6位数字'
									}
								}
							}
						}
					});
}