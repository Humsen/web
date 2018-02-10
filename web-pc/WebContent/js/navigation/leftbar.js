/**
 * @author 何明胜
 *
 * 2017年9月27日
 */

$(function(){
	//加载新版本特性
	loadNewVersionFeature();
	//加载文章类别
	loadCategory();
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

/**
 * 加载文章类别
 * @returns
 */
function loadCategory(){
	var urlAddrPath = window.location.pathname;
	
	if(urlAddrPath.indexOf('blog') > 0){
		getCategory3Num('blog');
	}
	if(urlAddrPath.indexOf('code') > 0){
		getCategory3Num('code');
	}
}

/**
 * 从数据库获取类别和数量
 * @returns
 */
function getCategory3Num(type){
	$('#txt_articleCategory').show();
	
	$.ajax({
		type : 'POST',
		async : false,
		url : '/category.hms',
		dataType : 'json',
		data : {
			type : 'query_category',
			'class' : type,
		},
		success : function(response) {
			var $category = $('#txt_articleCategory').children(".list-unstyled");
			
			for(var x in response){
				if(x == 0){
					$category.append('<li>'
							+'<a href="/module/' + type + '.hms">'
							+ response[x].categoryName
							+'&emsp;<span class="badge">'
							+ response[x].categoryNum
							+ '</span></a></li>');
				}
				else{
					$category.append('<li>'
							+'<a href="/module/' + type + '.hms?category=' + response[x].categoryId + '">'
							+ response[x].categoryName
							+'&emsp;<span class="badge">'
							+ response[x].categoryNum
							+ '</span></a></li>');
				}
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