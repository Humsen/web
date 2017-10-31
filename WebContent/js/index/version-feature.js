/**
 * @author 何明胜
 *
 * 2017年9月27日
 */

$(function(){
	loadNewVersionFeature();//加载新版本特性
});

/**
 * 显示最新版本特性
 * @returns
 */
function loadNewVersionFeature(){
	$.ajax({
		type : 'POST',
		url : '/latestReleaseFeature',
		dataType : "json",
		success : function(response){
			$("#newVersionFeature").append('<div class="col-md-6 animate-box fadeInLeft animated version-width" data-animate-effect="fadeInLeft">'
					+ '<h2 class="fh5co-heading">新版特性</h2>'
					+ '<div class="version-content">'
					+ '<h5 class="version-heading">发布时间:' + new Date(response.releaseDate.time).format("yyyy-MM-dd hh:mm:ss") +'&nbsp; &nbsp; &nbsp; &nbsp;'
					+ '版本:' + response.releaseNumber + '</h5>'
					+ response.releaseContent
					+ '</div>'
					+ '</div>');
		},
		error : function(response, status){
			$.confirm({
			    title: '新版特性加载出错',
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