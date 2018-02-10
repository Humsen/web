/**
 * 加载博客目录
 * 
 * @author 何明胜
 * 
 * 2017年9月18日
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

//博客总数
var blog_total_num = 0;
var blog_page_size = 5;//默认每页显示5条

$(function(){
	queryBlogNum();
	queryBlogCatalog(1);
	//页面选择
	choosePageSize();
});

/**
 * 查询博客数量
 * @returns
 */
function queryBlogNum(){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/blog/query.hms',
		data : {
			type : 'query_total_num',
			keywords : $.getUrlParam('keywords') ? $.getUrlParam('keywords') : '',
			category : $.getUrlParam('category') ? $.getUrlParam('category') : '',
		},
		success : function(response){
			blog_total_num = response;
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '博客加载出错',
			    content: textStatus + ' : ' + XMLHttpRequest.status,
			    autoClose: 'ok|1000',
			    type: 'green',
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
 * 查询博客目录
 * 
 * @param pageSize
 * @param pageNo
 * @returns
 */
function queryBlogCatalog(pageNo){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/blog/query.hms',
		dataType : 'json',
		data : {
			type : 'query_one_page',
			keywords : $.getUrlParam('keywords') ? $.getUrlParam('keywords') : '',
			category : $.getUrlParam('category') ? $.getUrlParam('category') : '',
			pageSize : blog_page_size,
			pageNo : pageNo,
		},
		success : function(response){
			for(x in response){
				loadSimpleBlog(response[x]);
			}
			showPagination(pageNo, 6);//显示分页,6为最大显示6条分页页码
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '博客目录加载出错',
			    content: textStatus + ' : ' + XMLHttpRequest.status,
			    autoClose: 'ok|1000',
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
 * 加载目录形式的博客
 * 
 * @param blog_data
 * @returns
 */
function loadSimpleBlog(blog_data){
	$('#list_blog').append('<div class="fh5co-entry padding">'
		+ '<span class="fh5co-post-date">' + new Date(blog_data.blogDate.time).format('yyyy-MM-dd hh:mm:ss') + '</span>'
		+ '<span class="fh5co-post-date">作者:' + blog_data.userNickName + '</span>'
		+ '<span class="fh5co-post-date">浏览' + blog_data.blogRead + '次</span>'
		+ '<h2 class="article-title"><input type="hidden" value=' + blog_data.blogId + ' />'
		+ '<a href=/blog.hms?blogId=' + blog_data.blogId + '>' + blog_data.blogTitle + '</a></h2>'
		+ '<p><b>摘要：</b>' + blog_data.blogSummary + '</p>'
		+ '</div>'
		+ '</div><hr>');
}

/**
 * 显示底部分页
 * 
 * @returns void
 */
function showPagination(currPageNum, paginationMaxLength){
	$('#list_blog').append('<hr />'
		+	'<div id="pagination" class="text-align-center" pagination="pagination_new" '
		+	' currpagenum=' + currPageNum + ' paginationmaxlength=' + paginationMaxLength + ' totalpages=' + Math.ceil(blog_total_num/blog_page_size)
		+   ' onlyonepageshow="true"> '
		+ 	'</div>'
		+ 	'<hr />');
	
	//显示分页, 调用pagination.js
	PaginationHelper($('#pagination'), blog_page_size);
}

/**
 * 选择每页显示博客数量
 * 
 * @returns
 */
function choosePageSize(){
	$('#choose_page_size').find('.dropdown-menu').children('li').click(function() {
		blog_page_size = $(this).attr('value');
		$('#list_blog').html('');
		queryBlogNum();
		queryBlogCatalog(currentPageNum);
		choosePageSize();
	});
}

/**
 * 实现的点击事件，参数为分页容器的id
 * pagination.js调用这里
 * 
 * @param currentPageNum
 * @returns
 */
function paginationClick(currentPageNum) {
	$('#list_blog').html('');
	queryBlogNum();
	queryBlogCatalog(currentPageNum);
}