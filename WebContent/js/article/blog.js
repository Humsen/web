/**
 * 加载博客目录
 * 
 * @author 何明胜
 * 
 * 2017年9月18日
 */

//博客总数
var blog_total_num = 0;

$(function(){
	queryBlogNum();
	queryBlogCatalog($('#num_blogPageSize').text(), 1);
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
			type : 'query_total_num'
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
function queryBlogCatalog(pageSize, pageNo){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/blog/query.hms',
		dataType : 'json',
		data : {
			type : 'query_one_page',
			pageSize : pageSize,
			pageNo : pageNo,
		},
		success : function(response){
			for(x in response){
				loadSimpleBlog(response[x]);
			}
			showPagination(pageNo, 6, Math.ceil(blog_total_num/pageSize));//显示分页
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
		+ '<span class="fh5co-post-date">作者:' + blog_data.blogAuthor + '</span>'
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
function showPagination(currPageNum, paginationMaxLength, totalPages){
	$('#list_blog').append('<hr />'
		+	'<div id="pagination" class="text-align-center" pagination="pagination_new" '
		+	' currpagenum=' + currPageNum + ' paginationmaxlength=' + paginationMaxLength + ' totalpages=' + totalPages
		+   ' onlyonepageshow="true"> '
		+ 	'</div>'
		+ 	'<hr />');
	
	PaginationHelper($('#pagination'));//显示分页
}

/**
 * 选择每页显示博客数量
 * 
 * @returns
 */
function choosePageSize(){
	$('#choose_page_size').find('.dropdown-menu').children('li').click(function() {
		$('#num_blogPageSize').text($(this).attr('value'));
		$('#list_blog').html('');
		queryBlogNum();
		queryBlogCatalog($('#num_blogPageSize').text(), currentPageNum);
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
	queryBlogCatalog($('#num_blogPageSize').text(), currentPageNum);
}