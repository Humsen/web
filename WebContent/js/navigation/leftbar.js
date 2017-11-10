/**
 * @author 何明胜
 *
 * 2017年9月27日
 */

$(function(){
	//加载新版本特性
	loadNewVersionFeature();
});

/**
 * 显示最新版本特性
 * @returns
 */
function loadNewVersionFeature(){
	$.ajax({
		type : 'POST',
		url : '/latestRlseFetr.hms',
		dataType : 'json',
		success : function(response){
			$('#txt_versionFeature').append(''
					+'<h4>新版特性<span class="latest-version">版本:' + response.releaseNumber + '</span></h4>'
					+ '<h5>发布时间:' + new Date(response.releaseDate.time).format('yyyy-MM-dd hh:mm:ss') +'</h5>'
					+'<ol class="list-unstyled">'
					+'<li>' + response.releaseContent + '</li>'
					+'</ol>');
		},
		error : function(response, status){
			$.confirm({
			    title: '新版特性加载出错',
			    content: status + ' : ' +response,
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