/**
 * 加载代码目录
 * 
 * @author 何明胜
 * 
 * 2017年9月18日
 */

var code_total_num = 0;
var code_page_size = 5;//默认每页显示5条

$(function(){
	queryCodeNum();
	queryCodeCatalog(1);
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
		url : '/code/query.hms',
		data : {
			type : 'query_total_num',
			keywords : $.getUrlParam('keywords') ? $.getUrlParam('keywords') : '',
		},
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
function queryCodeCatalog(pageNo){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/code/query.hms',
		dataType : 'json',
		data : {
			type : 'query_one_page',
			keywords : $.getUrlParam('keywords') ? $.getUrlParam('keywords') : '',
			pageSize : code_page_size,
			pageNo : pageNo,
		},
		success : function(response){
			for(x in response){
				loadSimpleCode(response[x]);
			}
			showPagination(pageNo, 6);//显示分页
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
		+ '<a href="/code.hms?codeId=' + code_data.codeId + '">' + code_data.codeTitle + '</a></h2>'
		+ '<p><b>摘要：</b>' + code_data.codeSummary + '</p>'
		+ '</div>'
		+ '</div>');
}

/**
 * 显示底部分页
 * 
 * @returns void
 */
function showPagination(currpagenum, paginationmaxlength){
	$('#list_code').append('<hr />'
		+	'<div id="pagination" class="text-align-center" pagination="pagination_new" '
		+	' currpagenum=' + currpagenum + ' paginationmaxlength=' + paginationmaxlength + ' totalpages=' + Math.ceil(code_total_num/code_page_size)
		+   ' onlyonepageshow="true"> '
		+ 	'</div>'
		+ 	'<hr />');
	
	PaginationHelper($('#pagination'), code_page_size);//显示分页
}

/**
 * 选择每页显示博客数量
 * 
 * @returns
 */
function choosePageSize(){
	$('#choose_page_size').find('.dropdown-menu').children('li').click(function() {
		code_page_size = $(this).attr('value');
		$('#list_code').html('');
		queryCodeNum();
		queryCodeCatalog(currentPageNum);
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
	$('#list_code').html('');
	queryCodeNum();
	queryCodeCatalog(currentPageNum);
}