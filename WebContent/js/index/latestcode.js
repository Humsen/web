/**
 * @author 何明胜
 *
 * 2017年9月30日
 */

/**
 * @author 何明胜
 *
 * 2017年9月27日
 */

$(function() {
	queryLatestCode(3);//加载代码
});

/**
 * 查询最新更新的3篇代码，如果小于3，则有多少查多少
 * 相当于按照时间排序后根据页面大小查询第1页
 * 
 * @param pageSize
 * @returns
 */
function queryLatestCode(pageSize){
	$.ajax({
		type : 'POST',
		async: true,
		url : '/codeLibPerPage',
		dataType : "json",
		data : {
			pageSize : pageSize,
			pageNo : 1,
		},
		success : function(response, status){
			for(x in response){
				loadSimpleCode(response[x]);
			}
		},
		error : function(response, status){
			$.confirm({
			    title: '代码库加载出错',
			    content: status + " : " +response,
			    autoClose: 'ok|1000',
			    type: 'green',
			    buttons: {
			    	ok: {
			            text: "确认",
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
 * @param codeData
 * @returns
 */
function loadSimpleCode(codeData){
	$("#latestCode").append('<div class="col-md-3 col-sm-6 col-padding article-box-div" >'
			+ '<div class="blog-entry">'
			+ '<div class="desc">'
			+ '<h3 class="article-title"><a href="/codePerById?codeId=' + codeData.codeId + '">' + codeData.codeTitle + '</a></h3>'
			+ '<span class="article-author">'+ '作者:'+ codeData.codeAuthor +'&nbsp; &nbsp; '
			+  new Date(codeData.codeDate.time).format("yyyy-MM-dd hh:mm:ss") + '&nbsp; '
			+ '<i class="icon-comment"></i>浏览' + codeData.codeRead + '次</span>'
			+ '<p><b>摘要：</b>' + codeData.codeSummary + '</p>'
			+ '<a href="/codePerById?codeId=' + codeData.codeId + '" class="lead read-more" >阅读更多 <i class="glyphicon glyphicon-arrow-right"></i></a>'
			+ '</div>'
			+ '</div>');
}