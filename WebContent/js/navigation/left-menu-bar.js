/**
 * 初始化菜单栏
 * 
 * @author 何明胜
 *
 * 2017年9月27日
 */

$(function(){
	var menuBarNo = Number($('#menuBarNo').val());
	
	menuBarInit(menuBarNo);
});

function menuBarInit(menuBarNo){
	var menu_bar = '<aside id="fh5co-aside" role="complementary" class="border js-fullheight">'
		+'<h1 id="fh5co-logo">'
		+'<a href="/" class="husen-name">何明胜</a>'
		+'</h1>'
		+'<nav id="fh5co-main-menu" role="navigation">'
		+'<ul>'
		+'<li><a href="/">主页</a></li>'
		+'<li><a href="/module/blog.jsp">博客</a></li>'
		+'<li><a href="/module/code_library.jsp">代码库</a></li>'
		+'<li><a href="/module/message.jsp">留言区</a></li>'
		+'<li><a href="/module/download.jsp">下载</a></li>'
		+'<li><a href="/module/contact.jsp">联系站长</a></li>'
		+'</ul>'
		+'</nav>'
		+'<div class="fh5co-footer footer-div">'
		+'<p>'
		+'<small><span>渝ICP备16013250号 </span>' 
		+'<span>&copy;'
		+'2017 All Rights Reserved </span></small>'
		+'</p>'
		+'</div>'
		+'</aside>';
	
	$('#fh5co-page').prepend(menu_bar);
	$('#fh5co-aside').find('ul').children().eq(menuBarNo).addClass('fh5co-active');
}