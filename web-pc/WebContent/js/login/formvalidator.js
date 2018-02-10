/**
 * @author 何明胜
 * 
 * 2017年9月26日
 */

/**
 * 按钮初始化
 */
$(function() {
	showLoginStatus();// 显示登录状态
	enterKeyClick();// 登录框和注册框绑定回车事件
});

/** 初始化 */
$(document).ready(function() {
	// 登录框添加校验
	loginValidator();
	// 注册表单添加验证
	registerValidator();

	// 重置登录表单
	if($('#loginForm').length > 0){
		$('#loginForm').clearForm();
		$('#loginForm').data('bootstrapValidator').resetForm(true);
	}
	
	// 重置注册表单
	if($('#registerForm').length > 0){
		$('#registerForm').clearForm();
		$('#registerForm').data('bootstrapValidator').resetForm(true);
	}
});

/** 绑定点击事件 */
$(function() {
	/** 登录按钮点击 */
	$('#btnLogin').click(submitLoginForm);

	/** 忘记密码点击 */
	$('#btn_forgetPwd').click(retrievePassword);

	/** 退出登录按钮点击 */
	$('#quitLoginBtn').click(quitLoginClick);

	/** 点击个人中心按钮事件,判断是否登录，否则不能访问 */
	$('#persCenterBtn').click(persCenterBtnClick);

	/** 注册提交按钮点击 */
	$('#btn_submitRegister').click(submitRegisterForm);

	/** 注册取消按钮点击 */
	$('#btnCancel').click(function() {
		$('#registerForm').clearForm();
		$('#registerForm').data('bootstrapValidator').resetForm(true);
	});
});

/**
 * 显示登录状态
 * 
 * @returns
 */
function showLoginStatus() {
	if ($.cookie('username')) {
		$('#loginBtn').hide();
		$('#registerBtn').hide();
		$('#persCenterBtn').show();
		$('#quitLoginBtn').show();
	} else {
		$('#loginBtn').show();
		$('#registerBtn').show();
		$('#persCenterBtn').hide();
		$('#quitLoginBtn').hide();
	}

	// 重置登录表单
	$('#loginForm').clearForm();
	// 重置注册表单
	$('#registerForm').clearForm();
}

/**
 * 个人中心点击
 * 
 * @returns
 */
function persCenterBtnClick() {
	// 如果没有登录，跳转到错误页面
	if (!$.cookie('username')) {
		window.open('/module/error/error.html');
	} else {
		window.open('/usercenter.hms');
	}
}

/**
 * 退出登录点击
 * 
 * @returns
 */
function quitLoginClick() {
	// 删除 cookie
	$.cookie('username', null, {
		path : '/',
		expires : -1
	});
	// 判断是否在后台
	if (window.location.href.indexOf('usercenter') > 0) {
		showLoginStatus();
	} else {
		// 刷新页面
		window.location.reload();
	}
}

/**
 * 绑定回车键
 * 
 * @returns
 */
function enterKeyClick() {
	$('#loginForm').keydown(function(e) {
		if (e.keyCode == 13) {
			// 模拟点击登陆按钮，触发上面的 Click 事件
			$('#btnLogin').click();
		}
	});

	$('#registerForm').keydown(function(e) {
		if (e.keyCode == 13) {
			// 模拟点击登陆按钮，触发上面的 Click 事件
			$('#btn_submitRegister').click();
		}
	});
}

/**
 * 提交登录表单
 * 
 * @returns
 */
function submitLoginForm() {
	// 进行表单验证
	var $loginValidate = $('#loginForm').data('bootstrapValidator');
	$loginValidate.validate();
	
	if ($loginValidate.isValid()) {
		$.ajax({
			url : '/userInfo.hms',
			async : false,// 同步，会阻塞操作
			type : 'POST',
			data : {
				type : 'auth_login',
				userName : $('#txt_userNameLogin').val(),
				password : $.md5($('#txt_userPwdLogin').val())
			},
			success : function(result) {
				if (result == 1) {
					$.confirm({
						title : '登录成功',
						content : '登录成功',
						autoClose : 'ok|2000',
						type : 'green',
						buttons : {
							ok : {
								text : '确认',
								btnClass : 'btn-primary',
							},
						}
					});

					showLoginStatus();
					$('#login').modal('hide');
					$('#loginForm')[0].reset();
					// cookie 放在后端设置
				} else {
					$.confirm({
						title : '登录失败',
						content : '登录失败',
						autoClose : 'ok|2000',
						type : 'red',
						buttons : {
							ok : {
								text : '确认',
								btnClass : 'btn-primary',
							},
						}
					});

					$('#txt_userPwdLogin').val('');
				}
			},
			error : function(XMLHttpRequest, textStatus) {
				$.confirm({
					title : '登录出错',
					content : textStatus + ' : ' + XMLHttpRequest.status,
					autoClose : 'ok|2000',
					type : 'green',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});

				$('#txt_userPwdLogin').val('');
			}
		});
	}
}

/**
 * 提交注册表单
 * 
 * @returns
 */
function submitRegisterForm() {
	// 进行表单验证
	var $registerValidate = $('#registerForm').data('bootstrapValidator');
	$registerValidate.validate();

	if ($registerValidate.isValid()) {
		$('#register').modal('hide');
		var email = $('#txt_userEmailRegister').val();

		// 验证邮箱
		var validateEmail = $.confirm({
			title : false,
			closeIcon : true,
			content : 'url:/module/login/email_check.html',
			// theme: 'supervan',
			onContentReady : function() {
				// 显示邮件
				$('#txt_emailShow').text(email);
				// 修改邮件点击
				this.$content.find('a').click(function() {
					validateEmail.close();
					$('#register').modal('show');
				});
				// 如果是手机，调整排版
				if ($.isMobile()) {
					$('#txt_emailShow').css('float', 'left');
					$('#form_emailValidate').find('.modify-email').css({
						'float' : 'left',
						'margin-top' : '6px'
					});
					$('#btn_sendCode').css('margin-top', '10px');
				}
				// 禁用Ok按钮
				validateEmail.buttons.ok.disable();
				validateEmail.buttons.ok.removeClass('btn-blue');
				// 添加表单校验
				registerEmailCodeValidator();

				// 获取验证码点击
				$('#btn_sendCode').click(
						function() {
							// 注册发送验证码
							$.ajax({
								url : '/userInfo/code.hms',
								async : true,// 同步，会阻塞操作
								type : 'POST',// PUT DELETE POST
								data : {
									type : 'send_code_register',
									email : email,
								},
								success : function(response) {
									if (response != 1) {
										$.confirm({
											title : false,
											content : '验证码发送失败',
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
								},
								error : function(XMLHttpRequest, textStatus) {
									$.confirm({
										title : '验证码发送失败',
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

							// 60s倒计时
							$.codeCountDown($('#btn_sendCode'));
						});

				// 绑定表单监听
				$('#txt_validateCode').on(
						'input propertychange',
						function() {
							// 点击下一步，先看表单是否合法
							var $formRetrivePwd = $('#form_emailValidate')
									.data('bootstrapValidator');
							$formRetrivePwd.validate();

							if ($formRetrivePwd.isValid()) {
								validateEmail.buttons.ok.enable();
								validateEmail.buttons.ok.addClass('btn-blue');
							}
						});
			},
			buttons : {
				ok : {
					text : '下一步',
					btnClass : 'btn-blue',
					keys : [ 'enter' ],
					action : function() {
						// 注册校验验证码
						$.ajax({
							url : '/userInfo/code.hms',
							async : true,// 同步，会阻塞操作
							type : 'POST',// PUT DELETE POST
							data : {
								type : 'auth_code_register',
								randomCode : $('#txt_validateCode').val(),
							},
							success : function(response) {
								if (response == 1) {
									// 发送注册信息
									sendRegisterInfo();
								} else {
									// 按钮设置重新发送
									$.confirm({
										title : '验证失败',
										content : '是否需要重新尝试？',
										autoClose : 'ok|3000',
										type : 'green',
										buttons : {
											ok : {
												text : '是，立即前往',
												btnClass : 'btn-primary',
												keys : [ 'enter' ],
												action : function() {
													// 重新打开该对话框
													submitRegisterForm();
												}
											},
											cancel : {
												text : '否，不再尝试',
												btnClass : 'btn-primary',
												keys : [ 'ESC' ],
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
				}
			}
		});
	}
}

/**
 * 邮箱验证通过后发送注册信息
 * 
 * @returns
 */
function sendRegisterInfo() {
	// 发送ajax请求
	$.ajax({
		url : '/userInfo.hms',
		async : false,// 同步，会阻塞操作
		type : 'POST',
		data : registerInfo2Json(),
		success : function(result) {
			if (result == 1) {
				$.confirm({
					title : '注册成功',
					content : '注册成功',
					autoClose : 'ok|1500',
					type : 'green',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});

				$('#btnCancel').click();
			} else {
				$.confirm({
					title : '注册失败',
					content : '注册失败',
					autoClose : 'ok|3000',
					type : 'red',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '注册错误',
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
}

/**
 * 注册用户信息2json
 * 
 * @returns
 */
function registerInfo2Json() {
	var newUserInfo = {};

	newUserInfo.type = 'create_user_info';
	newUserInfo.userName = $('#txt_userNameRegister').val();
	newUserInfo.password = $.md5($('#txt_userPwdRegister').val());
	newUserInfo.email = $('#txt_userEmailRegister').val();

	return newUserInfo;
}

/**
 * 找回密码点击事件
 * 
 * @returns
 */
function retrievePassword() {
	// 验证邮箱
	var retrivePwdConfirm = $
			.confirm({
				title : false,
				closeIcon : true,
				content : 'url:/module/login/retrive_pwd.html',
				// theme: 'supervan',
				onContentReady : function() {
					// 如果是手机，调整排版
					if ($.isMobile()) {
						$('#form_retrivePwd').find('.form-div-label').css(
								'width', '100%');
						$('#btn_sendValidateCode').css('margin-top', '10px');
					}

					// 禁用Ok按钮
					retrivePwdConfirm.buttons.ok.disable();
					retrivePwdConfirm.buttons.ok.removeClass('btn-blue');
					// 添加表单检验
					retrievePwdValidator();

					$('#btn_sendValidateCode')
							.click(
									function() {
										// 判断邮箱格式是否正确
										var $emailValidate = $(
												'#form_retrivePwd').data(
												'bootstrapValidator');
										$emailValidate.validateField('email');

										var isEmailValid = $emailValidate
												.isValidField('email');
										if (!isEmailValid) {
											return;
										}

										// 找回密码, 发送验证码
										$
												.ajax({
													url : '/userInfo/code.hms',
													async : true,// 异步，启动倒计时
													type : 'POST',
													data : {
														type : 'send_code_retrive_pwd',
														email : $(
																'#txt_emailInput')
																.val(),
													},
													success : function(response) {
														if (response != 1) {
															$
																	.confirm({
																		title : '验证码发送失败',
																		content : textStatus
																				+ ' : '
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
													},
													error : function(
															XMLHttpRequest,
															textStatus) {
														$
																.confirm({
																	title : '验证码发送失败',
																	content : textStatus
																			+ ' : '
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

										// 60s倒计时
										$
												.codeCountDown($('#btn_sendValidateCode'));
									});

					// 绑定表单监听
					$('#form_retrivePwd').find('input').on(
							'input propertychange',
							function() {
								// 点击下一步，先看表单是否合法
								var $formRetrivePwd = $('#form_retrivePwd')
										.data('bootstrapValidator');
								$formRetrivePwd.validate();

								if ($formRetrivePwd.isValid()) {
									retrivePwdConfirm.buttons.ok.enable();
									retrivePwdConfirm.buttons.ok
											.addClass('btn-blue');
								}
							});
				},
				buttons : {
					ok : {
						text : '下一步',
						btnClass : 'btn-blue',
						keys : [ 'enter' ],
						action : function() {
							// 先判断有没有发送过验证码，有可能关闭后又开
							// 验证码有效期10分钟
							// 暂时不考虑这个

							// 找回密码, 校验验证码
							$
									.ajax({
										url : '/userInfo/code.hms',
										async : false,
										type : 'POST',
										data : {
											type : 'auth_code_retrive_pwd',
											randomCode : $('#txt_validateCode1')
													.val(),
											userName : $('#txt_username').val(),
											email : $('#txt_emailInput').val(),
										},
										success : function(response) {
											if (response != 0) {
												$
														.confirm({
															title : '密码重置成功',
															content : '您的密码已成功重置为123456，请尽快修改！',
															autoClose : 'ok|4000',
															type : 'green',
															buttons : {
																ok : {
																	text : '确认',
																	btnClass : 'btn-primary',
																},
															}
														});
											} else {
												$
														.confirm({
															title : '验证失败',
															content : '是否需要重新尝试？',
															autoClose : 'ok|3000',
															type : 'green',
															buttons : {
																ok : {
																	text : '是，立即前往',
																	btnClass : 'btn-primary',
																	keys : [
																			'enter',
																			'a' ],
																	action : function() {
																		// 重新打开该对话框
																		retrievePassword();
																	}
																},
																cancel : {
																	text : '否，不再尝试',
																	btnClass : 'btn-primary',
																	keys : [ 'ESC' ],
																}
															}
														});
											}
										},
										error : function(XMLHttpRequest,
												textStatus) {
											$
													.confirm({
														title : '验证码校验发送失败',
														content : textStatus
																+ ' : '
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
					}
				}
			});
}

/**
 * 登录表单添加验证
 * 
 * @returns
 */
function loginValidator() {
	$('#loginForm').bootstrapValidator({
		message : '输入无效!',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			username : {
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
					regexp : {
						regexp : /^[a-zA-Z0-9_\.]+$/,
						message : '用户名只能由字母，数字，点和下划线几种组成!'
					}
				}
			},
			password : {
				message : '密码无效!',
				validators : {
					notEmpty : {
						message : '密码不能为空!'
					},
					stringLength : {
						min : 6,
						max : 15,
						message : '密码长度必须在6到15位之间!'
					}
				}
			}
		}
	});
}

/**
 * 注册表单添加验证
 * 
 * @returns
 */
function registerValidator() {
	$('#registerForm')
			.bootstrapValidator(
					{
						message : '输入无效!',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							username : {
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
							password : {
								message : '密码无效!',
								validators : {
									notEmpty : {
										message : '密码不能为空!'
									},
									stringLength : {
										min : 6,
										max : 15,
										message : '密码长度必须在6到15位之间!'
									},
									different : {
										field : 'username',
										message : '密码不能和用户名相同!'
									}
								}
							},
							confirmPassword : {
								message : '确认密码无效!',
								validators : {
									notEmpty : {
										message : '密码不能为空!'
									},
									identical : {
										field : 'password',
										message : '两次输入密码不一致!'
									},
									different : {
										field : 'username',
										message : '密码不能和用户名相同!'
									}
								}
							},
							email : {
								message1 : '邮件输入无效!',
								validators : {
									notEmpty : {
										message : '邮箱为必填哦!'
									},
									regexp : {
										regexp : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
										message : '邮箱格式不正确'
									}
								}
							}
						}
					});
}

/**
 * 找回密码表单添加验证
 * 
 * @returns
 */
function retrievePwdValidator() {
	$('#form_retrivePwd')
			.bootstrapValidator(
					{
						message : '输入无效!',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							username : {
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
									regexp : {
										regexp : /^[a-zA-Z0-9_\.]+$/,
										message : '用户名只能由字母，数字，点和下划线几种组成!'
									}
								}
							},
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

/**
 * 注册时 验证码合法校验
 * 
 * @returns
 */
function registerEmailCodeValidator() {
	$('#form_emailValidate').bootstrapValidator({
		message : '输入无效!',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
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