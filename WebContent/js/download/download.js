/**
 * 加载下载区
 * 
 * @author 何明胜
 * 
 * 2017年9月29日
 */

var file_total_num = 0;

$(function(){
	queryFileNum();
	queryFileCatalog(Number($('#num_downloadPageSize').attr('value')), 1);//默认一页10条
});

/**
 * 查询可供下载的文件数量
 * 
 * @returns
 */
function queryFileNum(){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/fileDownTotalCount',
		success : function(response){
			file_total_num = response;
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '下载出错',
			    content: textStatus + ' : ' + XMLHttpRequest.status,
			    autoClose: 'ok|2000',
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
 * 查询下载文件目录
 * 
 * @param pageSize
 * @param pageNo
 * @returns
 */
function queryFileCatalog(pageSize, pageNo){
	$.ajax({
		type : 'POST',
		async: false,
		url : '/fileDownPerPage',
		dataType : 'json',
		data : {
			pageSize : pageSize,
			pageNo : pageNo
		},
		success : function(response){
			for(x in response){
				getSimpleFile(response[x]);
			}
			showPagination(pageNo, 2, Math.ceil(file_total_num/pageSize));// 显示分页
		},
		error : function(XMLHttpRequest, textStatus){
			$.confirm({
			    title: '文件下载加载出错',
			    content: textStatus + ' : ' + XMLHttpRequest.status,
			    autoClose: 'ok|2000',
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
 * 加载目录形式的下载区
 * 
 * @param file_data
 * @returns
 */
function getSimpleFile(file_data){
	$('#list_file').append('<div class="col-md-6">'
		+ '<div class="fh5co-feature" data-animate-effect="fadeInLeft">'
		+ '<div class="fh5co-icon">'
		+ '<i class="glyphicon glyphicon-download-alt"></i>'
		+ '</div>'
		+ '<div class="fh5co-text">'
		+ '<h3 style="letter-spacing: .05em;">' + file_data.fileName + '</h3>'
		+ '<p><span>下载' + file_data.fileDownloadCount + '次</span>&nbsp;/&nbsp;'
		+ '<span>' + new Date(file_data.fileUploadDate.time).format('yyyy-MM-dd hh:mm:ss') +'上传</span></p>'
		+ '<a href="' + '/fileDownload?filename=' + file_data.fileUrl +'">点击下载</a>'
		+ '</div>'
		+ '</div>'
		+ '</div>');
} 

/**
 * 显示底部分页
 * 
 * @returns void
 */
function showPagination(currpagenum, paginationmaxlength, totalpages){
	$('#list_file').append('<hr />'
		+	'<div id=\'pagination\' style=\'text-align: center;\' pagination=\'pagination_new\' '
		+	' currpagenum=' + currpagenum + ' paginationmaxlength=' + paginationmaxlength + ' totalpages=' + totalpages
		+   ' onlyonepageshow=\'false\'> '
		+ 	'</div>'
		+ 	'<hr />');
	
	PaginationHelper($('#pagination'));// 显示分页
}

/**
 * 实现的点击事件，参数为分页容器的id
 * pagination.js调用这里
 * 
 * @param pagination_id
 * @returns
 */
function paginationClick(currentPageNum) {
	$('#list_file').html('');
	queryFileNum();
	queryFileCatalog(Number($('#num_downloadPageSize').attr('value')), currentPageNum);
}