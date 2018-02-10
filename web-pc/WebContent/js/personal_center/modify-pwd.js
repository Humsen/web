/**
 * @author 何明胜
 * 
 * 2017年10月26日
 */

/** 初始化 */
$(document).ready(function() {
	// 有效性校验
	modifyPwdValidator();

	// 提交
	$('#submitModifyPwd').click(submitModifyPwdForm);
});

/**
 * 提交修改密码表单
 * @returns
 */
function submitModifyPwdForm() {
	// 触发全部验证
	$('#modifyPwdForm').data('bootstrapValidator').validate();
	var flag = $('#modifyPwdForm').data('bootstrapValidator').isValid();

	if (!flag) {
		return;
	}

	// 如果旧密码不对
	if (!confirmOldPwd()) {
		//改变旧密码框校验状态
		$('#oldPassword').parent().parent().removeClass('has-success').addClass('has-error');
		$('#oldPassword').next().removeClass('glyphicon-ok').addClass('glyphicon-remove');
		$('#helpBlock').show();
		
		//旧密码框内容改变，隐藏校验
		$('#oldPassword').on('input', function(){
			$('#helpBlock').hide();
		});
		
		return;
	}

	$.ajax({
		url : '/userInfo.hms',
		async : false,// 同步，会阻塞操作
		type : 'POST',// PUT DELETE POST
		data : {
			type : 'modify_pwd',
			userName : $.cookie('username'),
			password : $.md5($('#newPassword').val())
		},
		success : function(response) {
			if(response == 1){
				//重置表单和验证规则
				$('#modifyPwdForm')[0].reset();
				$('#modifyPwdForm').data('bootstrapValidator').resetForm(true);
				//清空修改密码区
				$('#mainWindow').html('');
				
				//退出登录
				quitLoginClick();
				
				$.confirm({
					title : '修改密码成功',
					content : '是否需要重新登录？',
					autoClose : 'ok|3000',
					type : 'green',
					buttons : {
						ok : {
							text : '是，立即前往',
							btnClass : 'btn-primary',
							keys : [ 'enter', 'a' ],
							action : function() {
								//点击登录
								$('#loginBtn').click();
							}
						},
						cancel : {
							text : '否，不登录',
							btnClass : 'btn-primary',
							keys : [ 'ESC' ],
						}
					}
				});
			}else{
				$.confirm({
					title: false, 
				    content: '修改密码失败！',
				    autoClose: 'ok|2000',
				    type: 'red',
				    buttons: {
				    	ok: {
				            text: '确认',
				            btnClass: 'btn-primary',
				        },
				    }
				});
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '修改密码出错',
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
 * 确认旧密码
 * 
 * @returns
 */
function confirmOldPwd() {
	var result;

	$.ajax({
		url : '/userInfo.hms',
		async : false,
		type : 'POST',
		data : {
			type : 'auth_pwd',
			userName : $.cookie('username'),
			password : $.md5($('#oldPassword').val())
		},
		success : function(response) {
			if (response == 1) {
				result = true;
			} else {
				response = false;
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '验证旧密码出错',
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

	return result;
}

/**
 * 修改密码有效性验证
 * 
 * @returns
 */
function modifyPwdValidator() {
	$('#modifyPwdForm').bootstrapValidator({
		message : '输入无效!',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			oldPassword : {
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
				}
			},
			newPassword : {
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
						field : 'oldPassword',
						message : '新密码不能和旧密码相同!'
					}
				}
			},
			confirmPwd : {
				validators : {
					notEmpty : {
						message : '确认密码不能为空'
					},
					identical : {
						field : 'newPassword',
						message : '请输入相同的新密码!'
					}
				}
			},
		}
	});
}