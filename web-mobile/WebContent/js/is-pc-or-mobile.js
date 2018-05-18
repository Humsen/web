/**
 * 判断访问类型是电脑还是手机
 * 
 * @author 何明胜
 * 
 * 2017年9月27日
 */
$(function() {
	var mobile_flag = $.isMobile(); // false为PC端，true为手机端

	if (mobile_flag) {
		// 调整中间内容布局
		$('#fh5co-main').css({
			'width' : '100%',
			'float' : 'right'
		});

		// 左侧菜单栏
		$('.fh5co-footer.footer-div').css('margin-top', 0);

		// 顶部导航栏
		$('#accessToday').css({
			'margin-left' : '0'
		});

		// 博客和代码左边距
		$('#fh5co-main').css({
			'margin-left' : '0'
		});
		$('#list_code').css({
			'margin-left' : '0'
		});

		// 留言是动态加载的，需等待一会儿
		setTimeout(function() {
			// 右边导航栏隐藏
			$('#rightBar').hide();
			//导航栏加上边距
			$('#topBar').children('div').addClass('top-bar-div');

			if ($('#message_box').length > 0) {
				// 留言框
				$('#message_box').find('textarea').css('width', '42%');
				// 回复留言
				$('#message_box').children().children().find('button').css({
					'cssText' : 'margin-left: 20% !important'
				});
			}
		}, 300);
	}
});