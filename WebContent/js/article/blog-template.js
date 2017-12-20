/**
 * 博客模板
 * 
 * @author 何明胜
 *
 * 2017年12月20日
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
	/** 顶部导航栏 * */
	$.ajax({
		url : '/module/navigation/topbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});

	/** 登录控制 * */
	$.ajax({
		url : '/module/login/login.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});

	/** 左侧导航栏 * */
	$.ajax({
		url : '/module/navigation/leftbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#fh5co-main').before(data);
		}
	});

	/** 右侧导航栏 * */
	$.ajax({
		url : '/module/navigation/rightbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#fh5co-main').after(data);
		}
	});
});

$(function() {
	getJsonData();
});

/**
 * 加载文章细节
 * 
 * @returns
 */
function getJsonData() {
	$.ajax({
		type : 'POST',
		async : false,
		url : '/blog.hms',
		dataType : 'json',
		data : {
			blogId : $.getUrlParam('blogId') ? $.getUrlParam('blogId') : 0,
			type : 'json_return',
		},
		success : function(response) {
			loadDetail(response)
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '查询出错',
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
 * 加载详细数据
 * 
 * @param data
 * @returns
 */
function loadDetail(data) {
	var isSuperAdmin = true;

	var blogBody = '<nav>'
			+ ' 	<ul class="pager"> '
			+
			/*
			 * findPreviousBlog(bVo.getBlogId()) + findNextBlog(bVo.getBlogId()) +
			 */
			'   </ul>'
			+ '</nav>'
			+ '<div>'
			+ '	<h2 class="text-align-center"><input id="hiden_blogId" type="hidden" value="'
			+ data.blogId + '" />' + '		<a href=#>' + data.blogTitle + '</a>'
			+ '	</h2>' + '	<span class="fh5co-post-date">'
			+ new Date(data.blogDate.time).format('yyyy-MM-dd hh:mm:ss')
			+ '</span>' + '   <span class="fh5co-post-date">作者:'
			+ data.blogAuthor + '</span>'
			+ '   <span class="fh5co-post-date">浏览' + data.blogRead
			+ '次</span>'
			+ '   <span class="fh5co-post-date label-lowercase">关键字：'
			+ keywordsProcess(data.blogLabel) + '</span>';

	if (isSuperAdmin) {
		blogBody += '<a href="/module/upload/editor_article.jsp?blogId='
				+ data.blogId
				+ '" target="_blank" role="button" class="btn btn-default btn-sm">编辑</a>'
				+ '<a id="btn_deleteBlog" href="javascript:void(0)" role="button" class="btn btn-danger btn-sm">删除</a>';
	}

	blogBody += '<p>' + data.blogHtmlContent + "</p>" + '</div>';

	$('#content').append(blogBody);
}

/**
 * 是否是管理员
 * 
 * @returns
 */
function isSuperAdmin() {

}

/**
 * 文章标签处理
 * 
 * @param blogLabel
 * @returns
 */
function keywordsProcess(blogLabel) {
	var keyWordsStrBuf = [];
	var colorArray = ["primary" , "success", "info", "warning", "danger"];
	
	if (typeof blogLabel != 'undefined' && blogLabel != "") {
		var keyWordsArray;
		if (blogLabel.indexOf(',') != -1) {
			keyWordsArray = blogLabel.split(",");
		} else {
			keyWordsArray = blogLabel.split("\\s+");
		}

		for (var index = 0; index < keyWordsArray.length; index++) {
			keyWordsStrBuf += '<span class="label label-' + colorArray[index] + '">'
					+ keyWordsArray[index].trim() + '</span> ';
		}
	}

	return keyWordsStrBuf;
}