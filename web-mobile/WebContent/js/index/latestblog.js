/**
 * @author 何明胜
 * 
 * 2017年9月27日
 */

$(function() {
	queryLatestBlog(3);// 加载博客
});

/**
 * 查询最新更新的3篇博客，如果小于3，则有多少查多少 相当于按照时间排序后根据页面大小查询第1页
 * 
 * @param pageSize
 * @returns
 */
function queryLatestBlog(pageSize){
	$.ajax({
		type : 'POST',
		async: true,
		url : '/blog/query.hms',
		dataType : 'json',
		data : {
			type : 'query_one_page',
			pageSize : pageSize,
			pageNo : 1,
		},
		success : function(response, status){
			for(x in response){
				loadSimpleBlog(response[x]);
			}
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '博客加载出错',
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
 * 加载简介形式的博客
 * 
 * @param blogData
 * @returns
 */
function loadSimpleBlog(blogData){
	$('#latestBlog').append('<div class="col-md-3 col-sm-6 col-padding article-box-div" >'
		+ '<div class="blog-entry">'
		+ '<div class="desc">'
		+ '<h3 class="article-title"><a href="/blog.hms?blogId=' + blogData.blogId +'">' + blogData.blogTitle + '</a></h3>'
		+ '<span class="article-author">'+ '作者:'+ blogData.userNickName +'&nbsp;'
		+  new Date(blogData.blogDate.time).format('yyyy-MM-dd hh:mm:ss') + '&nbsp;'
		+ '<i class="icon-comment"></i>浏览' + blogData.blogRead + '次</span>'
		+ '<p><b>摘要：</b>' + blogData.blogSummary + '</p>'
		+ '<a href="/blog.hms?blogId='+ blogData.blogId +'" class="lead read-more">阅读更多 <i class="glyphicon glyphicon-arrow-right"></i></a>'
		+ '</div>'
		+ '</div>');
}