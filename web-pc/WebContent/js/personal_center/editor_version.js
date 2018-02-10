/**
 * 编辑版本特性
 * 
 * @author 何明胜
 * 
 * 2017年11月10日
 */

// 当前最新版本id
var latest_version_id;
// 当前编辑版本id
var curr_version_id;
//当前版本号
var curr_version_num;

$(function() {
	// 获取当前最新版本
	getLatestVersion();

	// 绑定加载上一个版本事件
	$('#btn_prevV').click(prevVersionClick);

	// 绑定加载下一个版本事件
	$('#btn_nextV').click(nextVersionClick);

	// 绑定清空当前编辑区
	$('#btn_clearCurr').click(clearCurrClick);

	// 绑定提交事件
	$('#btn_subEditVsion').bind('click', submitNewVerFea);
});

/**
 * 获取当前最新版本
 * 
 * @returns
 */
function getLatestVersion() {
	$.ajax({
		type : 'POST',
		url : '/latestRlseFetr.hms',
		dataType : 'json',
		success : function(response) {
			latest_version_id = response.releaseId;
			$('#txt_latestV').val(response.releaseNumber);
			fillVersionEditor(response);
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '加载新版特性出错',
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
 * 填充版本信息
 * @param versionArticle
 * @returns
 */
function fillVersionEditor(versionArticle){
	//先清空
	clearCurrClick();
	
	curr_version_id = versionArticle.releaseId;
	curr_version_num = versionArticle.releaseNumber;
	$('#txt_newV').val(versionArticle.releaseNumber);
	
	var relContent = versionArticle.releaseContent;
	relContent = relContent.match(/<p>(\S*)<\/p>/)[1];
	relContent = relContent.split('</p><p>');
	
	$('.version-input').each(function(i) {
		$(this).val(relContent[i].slice(relContent[i].indexOf('、')+1));
	});
}

/**
 * 绑定加载前一个版本点击事件
 * 
 * @returns
 */
function prevVersionClick() {
	//如果版本小于等于0，直接返回
	if(curr_version_id-1 <= 0){
		return;
	}
	
	$.ajax({
		type : 'POST',
		url : '/latestRlseFetr.hms',
		dataType : 'json',
		data : {
			releaseId : curr_version_id-1,
		},
		success : function(response) {
			fillVersionEditor(response);
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '加载新版特性出错',
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
 * 绑定加载后一个版本点击事件
 * 
 * @returns
 */
function nextVersionClick() {
	//如果版本超过最新，直接返回
	if(curr_version_id+1 > latest_version_id){
		return;
	}
	
	$.ajax({
		type : 'POST',
		url : '/latestRlseFetr.hms',
		dataType : 'json',
		data : {
			releaseId : curr_version_id+1,
		},
		success : function(response) {
			fillVersionEditor(response);
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '加载新版特性出错',
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
 * 绑定清空当前编辑区
 * 
 * @returns
 */
function clearCurrClick() {
	$('.version-input').each(function(i) {
		$(this).val('');
	});
	$('#txt_newV').val('');
}

/**
 * 新版本特性提交
 * 
 * @returns
 */
function submitNewVerFea() {
	// 获取文章细节
	var article_data = JSON.stringify(articleDetail());

	if (typeof (article_data) == 'undefined') {
		$.confirm({
			title : '收集新版特性出错',
			content : '新版特性内容不能为空',
			autoClose : 'ok|2000',
			type : 'red',
			buttons : {
				ok : {
					text : '确认',
					btnClass : 'btn-primary',
				},
			}
		});

		return;
	}

	$.ajax({
		type : 'POST',
		async : false,
		url : '/editRlseFetr.hms',
		dataType : 'json',
		data : {
			type : $.trim($('#txt_newV').val()) != curr_version_num ? 'create' : 'modify',
			releaseId : curr_version_id,
			newArticle : article_data,
		},
		success : function(response, status) {
			if (response == 1) {
				//如果是新建，最新版本号加1
				if($.trim($('#txt_newV').val()) != curr_version_num){
					getLatestVersion();
				} 
				$.confirm({
					title : '上传成功',
					content : '新版特性上传成功',
					autoClose : 'ok|2000',
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
 * 提交时获取新版特性细节
 * 
 * @returns
 */
function articleDetail() {
	var new_version_feature = {};

	var version_feature = '';
	$('.version-input').each(function(i) {
		if ($(this).val() != '') {
			version_feature += '<p>' + (i + 1) + '、' + $(this).val() + '</p>';
		}
	});
	// 如果新版特性为空，直接返回
	if (version_feature == '') {
		return;
	}

	new_version_feature.releaseAuthor = '何明胜';
	new_version_feature.releaseDate = $.nowDateHMS();
	new_version_feature.releaseNumber = $('#txt_newV').val();
	new_version_feature.releaseContent = version_feature;

	return new_version_feature;
}