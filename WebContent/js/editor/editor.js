/**
 * 写新文章的js
 * 
 * @author 何明胜
 * 
 * 2017年9月28日
 */

var testEditor;
var article_id = -1;// 文章id,新文章默认为-1,编辑文章为相应的id

$(document).ready(function() {
	// 判断是否超级管理员，否则不能访问
	if ($.cookie('username') != 'super_admin') {
		window.location.replace('/error/error.jsp');
		return;
	}

	/** 判断是编辑还是新建文章，如果是编辑，加载内容 */
	initInputBox();
	
	// 发布按钮点击
	$('#btn_publish').click(btnPublishClick);
	
	//清空编辑区按钮点击
	$('#btn_clearEditor').click(btnClearClick);
});

/**
 * 判断是编辑还是新建文章 如果是编辑，加载内容
 * 
 * @returns
 */
function initInputBox() {
	var url;
	var jsonData;

	if ((article_id = $.getUrlParam('blogId')) != -1) {
		url = '/blogPerById';
		jsonData = 'blogId=' + article_id + '&type=json';
	} else if ((article_id = $.getUrlParam('codeId')) != -1) {
		url = '/codePerById';
		jsonData = 'codeId=' + article_id + '&type=json';
	}

	if (article_id != -1) {
		// 获取文章以供编辑
		$.ajax({
			type : 'POST',
			async : false,
			url : url,
			dataType : 'json',
			data : jsonData,
			success : function(response) {
				// var response = JSON.parse(response);
				// 填充编辑界面
				if ($.getUrlParam('blogId')) {
					$('#title').val(response.blogTitle);
					$('#author').val(response.blogAuthor);
					$('#summary').val(response.blogSummary);
					$('#in_editorMdContent').val(response.blogMdContent);
					$('input:radio[name="article"]:eq(0)').attr('checked',
							'checked');
				} else {
					$('#title').val(response.codeTitle);
					$('#author').val(response.codeAuthor);
					$('#summary').val(response.codeSummary);
					$('#in_editorMdContent').val(response.codeMdContent);
					$('input:radio[name="article"]:eq(1)').attr('checked',
							'checked');
				}
			},
			error : function(XMLHttpRequest, textStatus) {
				$.confirm({
					title : '查询出错',
					content : textStatus + ' : ' + XMLHttpRequest.status,
					autoClose : 'ok|2000',
					type : 'green',
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
}

testEditor = $(function() {
	editormd('div_editorMd', {
		width : '100%',
		height : 640,
		// markdown : md,
		codeFold : true,
		syncScrolling : 'single',
		// 你的lib目录的路径
		path : '/plugins/editormd/lib/',
		/*
		 * theme: 'dark',//工具栏主题 previewTheme: 'dark',//预览主题 editorTheme:
		 * 'pastel-on-dark',//编辑主题
		 */
		emoji : true,
		taskList : true,
		tocm : true, // Using [TOCM]
		tex : true, // 开启科学公式TeX语言支持，默认关闭
		flowChart : true, // 开启流程图支持，默认关闭
		sequenceDiagram : true, // 开启时序/序列图支持，默认关闭,
		// 这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
		saveHTMLToTextarea : true,
		// 启动本地图片上传功能
		imageUpload : true,
		imageFormats : [ 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'webp' ],
		imageUploadURL : '/imageUpload'
	});
});

/**
 * 发布按钮点击事件
 * 
 * @returns
 */
function btnPublishClick() {
	// 获取文章细节
	var articleData = JSON.stringify(articleDetail());
	var jsonData;

	$.ajax({
		type : 'POST',
		async : false,
		url : (isBlogOrCode() == 'blog') ? '/newBlogUpload' : '/newCodeUpload',
		data : {
			newArticle : articleData,
			type : (article_id != -1) ? 'modify' : 'create',
			articleId : article_id
		},
		success : function(response) {
			if (response != 0) {
				$.confirm({
					title : '上传成功',
					content : '上传成功',
					autoClose : 'ok|3000',
					type : 'green',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});
			} else {
				$.confirm({
					title : '上传失败',
					content : '上传失败',
					autoClose : 'ok|3000',
					type : 'red',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '上传出错',
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
 * 判断是博客还是代码
 * @returns
 */
function isBlogOrCode() {
	return $('input:radio[name="article"]:checked').val();
}

/**
 * 发布时获取文章内容详情
 * @returns
 */
function articleDetail() {
	var type = isBlogOrCode();
	var newArticle = {};

	// 获取第二个textarea的值，即生成的HTML代码 实际开发中此值存入后台数据库
	var editorHtml = $('#in_editorHtml').val();
	// alert('html文本'+editorHtml);
	// 获取第一个textarea的值，即md值 实际开发中此值存入后台数据库
	var editormarkdown = $('#in_editorMdContent').val();
	// alert('编辑器' +editormarkdown);

	if (type == 'blog') {
		newArticle.blogTitle = $('#title').val();
		newArticle.blogAuthor = $('#author').val();
		newArticle.blogSummary = $('#summary').val();
		newArticle.blogRead = 0;
		newArticle.blogDate = $.nowDateHMS();
		newArticle.blogHtmlContent = editorHtml;
		newArticle.blogMdContent = editormarkdown;
	} else if (type == 'code') {
		newArticle.codeTitle = $('#title').val();
		newArticle.codeAuthor = $('#author').val();
		newArticle.codeSummary = $('#summary').val();
		newArticle.codeRead = 0;
		newArticle.codeDate = $.nowDateHMS();
		newArticle.codeHtmlContent = editorHtml;
		newArticle.codeMdContent = editormarkdown;
	} else {
		$.confirm({
			title : '获取文章出错',
			content : '无法获取文章类型',
			type : 'red',
			buttons : {
				ok : {
					text : '确认',
					btnClass : 'btn-primary',
				},
			}
		});
	}

	return newArticle;
}

/**
 * 清空编辑区按钮点击事件
 * @returns
 */
function btnClearClick(){
	$('#title').val('');
	$('#author').val('');
	$('#summary').val('');
	$('.editormd-menu li a i[name="clear"]').click();
}