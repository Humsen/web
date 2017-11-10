/**
 * 网站顶部导航栏
 * 
 * @author 何明胜
 * 
 * 2017年10月18日
 */

$(document).ready(function(){
	//选择的栏目增加active
	var menuBarNo = Number($('#menuBarNo').val());
	$('.nav.nav-pills.topbar-nav').children().removeClass('active').eq(menuBarNo).addClass('active');
	
	//加载当前网站访问统计
	loadAccessStatistics();
});

/**
 * 加载当前网站访问统计
 * 
 * @returns
 */
function loadAccessStatistics(){
	$.ajax({
		type : 'POST',
		url : '/accessAtatistics.hms',
		dataType : 'json',
		success : function(response, ststus){
			$('#txt_accessToday').html('今日访问量：' + response.accessToday + ',');
			$('#txt_accessTotal').html('总访问量：' + response.accessTotal + ',');
			$('#txt_onlineCurrent').html('当前在线人数：' + response.onlineCurrent);
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '网站访问统计加载出错',
			    content: textStatus + ' : ' + XMLHttpRequest.status,
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
	});
}

/**
 * 点击加载下一个主题
 * 
 * @returns
 */
function nextThemeClick(){
	var curr_theme_no = 0;
	if(typeof $.cookie('theme_no') == 'undefined'){
		$.cookie('theme_no', 0);
	}else{
		curr_theme_no = Number($.cookie('theme_no'));
		$.cookie('theme_no', curr_theme_no+1);
	}

	curr_theme_no = (curr_theme_no+1)%5;
	
	var img = '/images/background/bg-' + curr_theme_no + '.jpg';
	$('body').css('background-image','url(' + img + ')');
}