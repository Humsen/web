/**
 * @desc 判断后台是否是手机
 *
 * @author 何明胜
 *
 * @created 2018年1月15日 下午1:05:01
 */

$(function() {
	var mobile_flag = $.isMobile(); // false为PC端，true为手机端

	if (mobile_flag) {
		// 顶部导航栏
		$('.header-nav').css({
			'margin-left': '0 !important'
		})
		$('#accessToday').css({
			'margin-left' : '0'
		});
		$('#onlineCurrent').css({
			'margin-left' : '5px'
		});
		
		setTimeout(function() {
			alert(1)
			//导航栏加上边距
			$('#topBar').children('div').addClass('top-bar-div');
		}, 300);
	}
});