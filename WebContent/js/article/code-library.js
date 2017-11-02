/**
 * 加载博客
 */

var code_total_num = 0;

$(function(){
	queryCodeNum();
	queryCodeCatalog($('#num_codePageSize').text(), 1);
	//页面选择
	choosePageSize();
});

/**
 * 查询博客数量
 * 
 * @returns
 */
function queryCodeNum(){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/codeLibTotalCount',
		success : function(response){
			code_total_num = response;
		},
		error : function(response, status){
			$.confirm({
			    title: '代码库数量加载出错',
			    content: status + ' : ' + response,
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
 * 查询博客目录
 * 
 * @param pageSize
 * @param pageNo
 * @returns
 */
function queryCodeCatalog(pageSize, pageNo){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/codeLibPerPage',
		dataType : 'json',
		data : {
			pageSize : pageSize,
			pageNo : pageNo,
		},
		success : function(response){
			for(x in response){
				loadSimpleCode(response[x]);
			}
			showPagination(pageNo, 6, Math.ceil(code_total_num/pageSize));//显示分页
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '代码库加载出错',
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
 * 显示博客详细内容点击
 * @returns
 */
function getCodeDetailsClick(){
	//setTimeout(function(){
	$.each($('.fh5co-entry h2'), function(index, value){
		$(this).click(function(){
			$.ajax({
				type : 'POST',
				async: false,
				url : '/codePerById',
				dateType : 'json',
				data : {
					codeId : $(this).find('input[type="text"]').val(),
				},
				success : function(response){
					$('#list_code').html('<div class="fh5co-entry" id="content">'
							+ '<div>'
							+ '<h2 style="text-align: center;"><input type="hidden" value='+ response.codeId + ' /><a href=#>' + response.codeTitle + '</a></h2>'
							+ '<span class="fh5co-post-date">' + new Date(response.codeDate.time).format('yyyy-MM-dd hh:mm:ss') + '</span>'
							+ '<span class="fh5co-post-date">作者:' + response.codeAuthor + '</span>'
							+ '<span class="fh5co-post-date">浏览' + response.codeRead + '次</span>'
							+ '<p>' + response.codeContent +'</p>'
							+ '</div>'
						+ '</div>');
					showMarkdown();
				},
				error : function(XMLHttpRequest, textStatus){
					$.confirm({
					    title: '代码库加载出错',
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
		});
	});
}


/**
 * 加载目录形式的代码
 * 
 * @param code_data
 * @returns
 */
function loadSimpleCode(code_data){
	$('#list_code').append('<div class="fh5co-entry padding">'
		+ '<span class="fh5co-post-date">' + new Date(code_data.codeDate.time).format('yyyy-MM-dd hh:mm:ss') + '</span>'
		+ '<span class="fh5co-post-date">作者:' + code_data.codeAuthor + '</span>'
		+ '<span class="fh5co-post-date">浏览' + code_data.codeRead + '次</span>'
		+ '<h2 class="article-title"><input type="hidden" value=' + code_data.codeId + ' />'
		+ '<a href="/codePerById?codeId=' + code_data.codeId + '">' + code_data.codeTitle + '</a></h2>'
		+ '<p><b>摘要：</b>' + code_data.codeSummary + '</p>'
		+ '</div>'
		+ '</div>');
}

/** 
 * 解析markdown html
 * 
 * */
function showMarkdown() {
	editormd.markdownToHTML('content', {
		htmlDecode : 'style,script,iframe', // you can filter tags decode
		emoji : true,
		taskList : true,
		tex : true, // 默认不解析
		flowChart : true, // 默认不解析
		sequenceDiagram : true, // 默认不解析
	});
}

/**
 * 显示底部分页
 * 
 * @returns void
 */
function showPagination(currpagenum, paginationmaxlength, totalpages){
	$('#list_code').append('<hr />'
		+	'<div id=\'pagination\' style=\'text-align: center;\' pagination=\'pagination_new\' '
		+	' currpagenum=' + currpagenum + ' paginationmaxlength=' + paginationmaxlength + ' totalpages=' + totalpages
		+   ' onlyonepageshow=\'true\'> '
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
		$('#num_codePageSize').text($(this).attr('value'));
		$('#list_code').html('');
		queryCodeNum();
		queryCodeCatalog($('#num_codePageSize').text(), currentPageNum);
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
	$('#list_code').html('');
	queryCodeNum();
	queryCodeCatalog($('#num_codePageSize').text(), currentPageNum);
}