/**
 * 博客模板
 * 
 * @author 何明胜
 *
 * 2017年12月20日
 */

/** 加载插件 * */
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

	/** 左侧导航栏 * */
	$.ajax({
		url : '/module/navigation/leftbar.html', 
		async : false,
		type : 'GET', 
		success : function(data) {
			$('#fh5co-main').before(data);
		}
	});

	/** 右侧导航栏 * */
	$.ajax({
		url : '/module/navigation/rightbar.html', 
		async : false,
		type : 'GET', 
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
	var blogBody = 
			'<div>'
			+ '	<h2 class="text-align-center"><input id="hiden_blogId" type="hidden" value="'
			+ 	data.blogId + '" />' 
			+ '		<a href=#>' + data.blogTitle + '</a>'
			+ '	</h2>' 
			+ '	<span class="fh5co-post-date">' + new Date(data.blogDate.time).format('yyyy-MM-dd hh:mm:ss') + '</span>' 
			+ ' <span class="fh5co-post-date">作者:' + data.userNickName + '</span>'
			+ ' <span class="fh5co-post-date">浏览' + data.blogRead + '次</span>'
			+ ' <span class="fh5co-post-date">关键字：' + keywordsProcess(data.blogLabel) + '</span>';

	if (isSuperAdminOrSelf(data.blogAuthor)) {
		blogBody += '<a href="/editor/article.hms?blogId='
				+ data.blogId
				+ '" target="_blank" role="button" class="btn btn-default btn-sm">编辑</a> '
				+ '<a id="btn_deleteBlog" href="javascript:void(0)" role="button" class="btn btn-danger btn-sm">删除</a>';
	}
	
	blogBody +=  data.blogHtmlContent + '</div>';
	
	$('#content').append(blogBody);
	loadBtnNeighbors(data.blogId);
}

/**
 * 是否是管理员
 * 
 * @returns
 */
function isSuperAdminOrSelf(author) {
	if ($.cookie('username') == 'husen') {
		return true;
	}

	if ($.cookie('username') == author) {
		return true;
	}
	
	return false;
}

/**
 * 文章标签处理
 * 
 * @param blogLabel
 * @returns
 */
function keywordsProcess(blogLabel) {
	var keyWordsStrBuf = [];
	var colorArray = [ "primary", "success", "info", "warning", "danger" ];

	if (typeof blogLabel != 'undefined' && blogLabel != "") {
		var keyWordsArray;
		if (blogLabel.indexOf(',') != -1) {
			keyWordsArray = blogLabel.split(",");
		} else {
			keyWordsArray = blogLabel.split(/\s+/);
		}

		for (var index = 0; index < keyWordsArray.length; index++) {
			keyWordsStrBuf += '<span class="label label-' + colorArray[index]
					+ '">' + keyWordsArray[index].trim() + '</span> ';
		}
	}

	return keyWordsStrBuf;
}

/**
 * 加载上下两篇按钮
 * 
 * @returns
 */
function loadBtnNeighbors(blogId) {
	var neighbors = '<nav>' + '	<ul class=\"pager\">'
			+ findPreviousBlog(blogId) + findNextBlog(blogId) + '	</ul>'
			+ '</nav>';

	$('#content').prepend(neighbors);
}

/**
 * 查找上一篇博客
 * 
 * @param blogId
 * @returns
 */
function findPreviousBlog(blogId) {
	var previousBlog = 0;
	$
			.ajax({
				type : 'POST',
				async : false,
				url : '/blog/query.hms',
				dataType : 'json',
				data : {
					type : 'query_previous',
					blogId : blogId
				},
				success : function(response) {
					if (response != 0) {
						previousBlog = '<li class="previous"><a href="/blog.hms?blogId='
								+ response
								+ '"><span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span> 上一篇</a></li>\r\n';
					} else {
						previousBlog = '<li class="previous disabled"><a href="#"><span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span> 上一篇</a></li>';
					}
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

	return previousBlog;
}

/**
 * 查找下一篇博客
 * 
 * @param blogId
 * @returns
 */
function findNextBlog(blogId) {
	var nextBlog = 0;
	$
			.ajax({
				type : 'POST',
				async : false,
				url : '/blog/query.hms',
				dataType : 'json',
				data : {
					type : 'query_next',
					blogId : blogId
				},
				success : function(response) {
					if (response != 0) {
						nextBlog = '<li class="next"><a href="/blog.hms?blogId='
								+ response
								+ '">下一篇 <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span></a></li>';
					} else {
						nextBlog = '<li class="next disabled"><a href="#">下一篇 <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span></a></li>';
					}
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

	return nextBlog;
}