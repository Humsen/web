/**
 * 写新文章的js
 * 
 * @author 何明胜
 * 
 * 2017年9月28日
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

var article_id = -1;// 文章id,新文章默认为-1,编辑文章为相应的id
var article_type = -1; //文章类型，默认-1，博客=blog，代码=code

$(document).ready(function() {
	// 判断是否超级管理员，否则不能访问
	/*if ($.cookie('username') != 'husen') {
		window.location.replace('/module/error/error.html');
		return;
	}*/
	
	//初始化Markdown编辑区
	initMarkdownEditor();
	
	/** 判断是编辑还是新建文章，如果是编辑，加载内容 */
	initInputBox();
	
	//加载文章分类
	loadCategory();
	
	// 发布按钮点击事件
	$('#btn_publish').click(btnPublishClick);
	
	//清空编辑区按钮点击事件
	$('#btn_clearEditor').click(btnClearClick);
	
	//选择文章分类 点击事件
	chooseCategory();
	
	//添加文章分类 点击事件
	addCategory();
});

/**
 * 判断是编辑还是新建文章 如果是编辑，加载内容
 * 
 * @returns
 */
function initInputBox() {
	var username = $.cookie('username');
	var url;
	var jsonData;

	if (article_id = $.getUrlParam('blogId')) {
		url = '/blog.hms';
		article_type = 'blog';
		jsonData = 'blogId=' + article_id + '&type=json_return';
	} else if ((article_id = $.getUrlParam('codeId'))) {
		url = '/code.hms';
		article_type = 'code';
		jsonData = 'codeId=' + article_id + '&type=json_return';
	}

	if (article_id) {// 获取文章以供编辑
		$.ajax({
			type : 'POST',
			async : false,
			url : url,
			dataType : 'json',
			data : jsonData,
			success : function(response) {
				// 填充编辑界面
				if ($.getUrlParam('blogId')) {
					$('#txt_title').val(response.blogTitle);
					$('#txt_summary').val(response.blogSummary);
					$('#txt_editorMdContent').val(response.blogMdContent);
					$('#txt_articleLabel').val(response.blogLabel);
					$('input:radio[name="article"]:eq(0)').prop('checked',true);
					$('#txt_curCategory').text(response.categoryName);
					$('#txt_curCtegy').val(response.blogCategory);
					
					username = response.blogAuthor;
				} else {
					$('#txt_title').val(response.codeTitle);
					$('#txt_summary').val(response.codeSummary);
					$('#txt_editorMdContent').val(response.codeMdContent);
					$('#txt_articleLabel').val(response.codeLabel);
					$('input:radio[name="article"]:eq(1)').prop('checked',true);
					$('#txt_curCategory').text(response.categoryName);
					$('#txt_curCtegy').val(response.codeCategory);
					
					response.blogAuthor = response.codeAuthor;
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
	}
	
	/** 填充作者信息 **/
	$.ajax({
		type : 'POST',
		async : false,
		url : '/userInfo.hms',
		dataType : 'json',
		data : {
			type : 'query_user_info',
			userName : username
		},
		success : function(response) {
			$('#txt_author').val(response.userNickName);
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
 * 初始化markdown编辑区
 * @returns
 */
function initMarkdownEditor() {
	editormd('div_editorMd', {
		width : '100%',
		height : 640,
		// markdown : md,
		codeFold : true,
		syncScrolling : 'single',
		path : '/plugins/editormd/lib/',// lib目录的路径
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
		saveHTMLToTextarea : true,//构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单
		imageUpload : true,// 启动本地图片上传功能
		imageFormats : [ 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'webp' ],
		imageUploadURL : '/imageUpload.hms'
	});
}

/**
 * 校验编辑区合法性
 * @returns
 */
function isEditorValid(){
	var flag = true;
	//标题不能为空
	if($('#txt_title').val() == ""){
		$('#txt_title').parent('div').parent('div').addClass('has-error');
		flag =  false;
	}else{
		$('#txt_title').parent('div').parent('div').removeClass('has-error');
	}
	//标签不能为空
	if($('#txt_articleLabel').val() == ""){
		$('#txt_articleLabel').parent('div').addClass('has-error'); 
		flag =  false;
	}else{
		$('#txt_articleLabel').parent('div').removeClass('has-error');
	}
	
	return flag;
}

/**
 * 发布按钮点击事件
 * 
 * @returns
 */
function btnPublishClick() {
	//检验合法性
	if(!isEditorValid()){
		return;
	}
	
	//判断文章类型是否改变
	if(article_type != -1 && article_type != isBlogOrCode()){
		$.confirm({
			title : '文章类型改变',
			content : '您在编辑过程中改变了文章类型，是否确定？',
			type : 'red',
			buttons : {
				ok : {
					text : '确定',
					btnClass : 'btn btn-danger',
					keys : [ 'enter' ],
					action : function() {
						submitActInfo('create');
					}
				},
				cancel : {
					text : '返回修改',
					btnClass : 'btn-success',
					keys : [ 'ESC' ],
				}
			}
		});
	}else{
		submitActInfo(article_id ? 'modify' : 'create');
	}
	
	
}

/**
 * 提交文章信息
 * @param type
 * @returns
 */
function submitActInfo(type){
	// 获取文章细节
	var articleData = JSON.stringify(articleDetail());
	var jsonData;

	$.ajax({
		type : 'POST',
		async : false,
		url : (isBlogOrCode() == 'blog') ? '/blog/upload.hms' : '/code/upload.hms',
		data : {
			newArticle : articleData,
			type : type ,
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
	var editorHtml = $('#txt_editorHtml').val();
	// 获取第一个textarea的值，即md值 实际开发中此值存入后台数据库
	var editormarkdown = $('#txt_editorMdContent').val();
	
	if (type == 'blog') {
		newArticle.blogTitle = $('#txt_title').val();
		newArticle.blogAuthor = $.cookie('username') == '' ? 'husen' : $.cookie('username');
		newArticle.blogSummary = $('#txt_summary').val() == '' ? '无摘要' : $('#txt_summary').val();
		newArticle.blogRead = 0;
		newArticle.blogDate = $.nowDateHMS();
		newArticle.blogHtmlContent = editorHtml == '' ? '暂无内容' : editorHtml;
		newArticle.blogMdContent = editormarkdown;
		newArticle.blogLabel = $('#txt_articleLabel').val();
		newArticle.blogCategory = $('#txt_curCtegy').val();
		
	} else if (type == 'code') {
		newArticle.codeTitle = $('#txt_title').val();
		newArticle.codeAuthor = $.cookie('username') == '' ? 'husen' : $.cookie('username');
		newArticle.codeSummary = $('#txt_summary').val() == '' ? '无摘要' : $('#txt_summary').val();
		newArticle.codeRead = 0;
		newArticle.codeDate = $.nowDateHMS();
		newArticle.codeHtmlContent = editorHtml == '' ? '暂无内容' : editorHtml;
		newArticle.codeMdContent = editormarkdown;
		newArticle.codeLabel = $('#txt_articleLabel').val();
		newArticle.codeCategory = $('#txt_curCtegy').val();
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
	$('#txt_title').val('');
	$('#txt_author').val('');
	$('#txt_summary').val('');
	$('#txt_articleLabel').val('');
	$('input:radio[name="article"]:eq(0)').prop('checked',true);
	//清空内容
	$('.editormd-menu li a i[name="clear"]').click();
	$('#txt_curCtegy').val('');
	$('#txt_curCategory').text('所有文章');
}

/**
 * 选择文章分类
 * @returns
 */
function chooseCategory(){
	$('.dropdown-menu.category-width').children('li').click(
			function() {
				if(typeof $(this).attr('value') != 'undefined'){
					$('#txt_curCategory').text($(this).children('a').text());
					$('#txt_curCtegy').val($(this).attr('value'));
				}
			});
}

/**
 * 添加文章分类
 * @returns
 */
function addCategory(){
	$('#tbl_addCategory').find('button').click(function(){
		var newCategory = $('#tbl_addCategory').find('input').val();
		var value = 0;
		
		if(newCategory != ''){
			//插入数据库，返回id
			$.ajax({
				type : 'POST',
				async : false,
				url : '/category.hms',
				dataType : 'json',
				data : {
					type : 'create',
					cateName : newCategory,
				},
				success : function(response) {
					value = response;
				},
				error : function(XMLHttpRequest, textStatus) {
					$.confirm({
						title : '添加分类出错',
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
				
			$('.dropdown-menu.category-width').children('.divider').before('<li value="' + value + '"><a href="#">' + newCategory + '</a></li>');
			//重新注册点击事件
			chooseCategory();
		}
	});
}

/**
 * 加载文章分类
 * @returns
 */
function loadCategory(){
	$.ajax({
		type : 'POST',
		async : false,
		url : '/category.hms',
		dataType : 'json',
		data : {
			type : 'query_all',
			'class' : isBlogOrCode(),
		},
		success : function(response) {
			for(x in response){
				var curCategory = response[x];
				$('.dropdown-menu.category-width').children('.divider').before('<li value="' + curCategory.categoryId + '"><a href="#">' + curCategory.categoryName + '</a></li>');
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '查询分类出错',
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