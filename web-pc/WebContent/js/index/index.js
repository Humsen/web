/**
 * 首页
 * 
 * @author 何明胜
 * 
 * 2017年12月15日
 */

/** 加载插件 * */
$.ajax({
	url : '/plugins/plugins.html', // 这里是静态页的地址
	async : false,
	type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
	success : function(data) {
		$($('head')[0]).find('script:first').after(data);
	}
});

$(function() {
	/** 顶部导航栏 **/
	$.ajax({
		url : '/module/navigation/topbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});

	/** 登录控制 **/
	$.ajax({
		url : '/module/login/login.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});
	
	/** 左侧导航栏 **/
	$.ajax({
		url : '/module/navigation/leftbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#fh5co-main').before(data);
		}
	});
	
	/** 右侧导航栏 **/
	$.ajax({
		url : '/module/navigation/rightbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#fh5co-main').after(data);
		}
	});
});

$(document).ready(function() {
	$('#indexCarousel').carousel({
		interval : 3000
	});// 每隔3秒自动轮播
});