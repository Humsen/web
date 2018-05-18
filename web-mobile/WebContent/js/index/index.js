/**
 * @desc 首页
 *
 * @author 何明胜
 *
 * @created 2018年1月12日 下午3:24:11
 */

/** 加载插件 */
$.ajax({
	url : '/plugins/plugins.html',
	async : false,
	type : 'GET',
	success : function(data) {
		$($('head')[0]).find('script:first').after(data);
	}
});

$(function() {
	/** 顶部导航栏 */
	$.ajax({
		url : '/module/navigation/topbar.html',
		async : false,
		type : 'GET',
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});

	/** 登录控制 */
	$.ajax({
		url : '/module/login/login.html', 
		async : false,
		type : 'GET', 
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});

	/** 右侧导航栏 */
	$.ajax({
		url : '/module/navigation/rightbar.html', 
		async : false,
		type : 'GET',
		success : function(data) {
			$('#fh5co-main').after(data);
		}
	});
});

$(document).ready(function() {
	// 每隔3秒自动轮播
	$('#indexCarousel').carousel({
		interval : 3000
	});
});