/**
 * 加载某篇详细的代码的js
 * 
 * @author 何明胜
 *
 * 2017年11月8日
 */

$(document).ready(function() {
	// 如果删除代码按钮存在，绑定点击事件
	var $btnDeleteCode = $('#btn_deleteCode');
	if ($btnDeleteCode.length > 0) {
		$btnDeleteCode.click(deleteCodeClick);
	}
});

/**
 * 删除代码按钮点击
 * 
 * @returns
 */
function deleteCodeClick() {
	$.confirm({
		title : '删除代码确认',
		content : '是否确定删除这篇代码?',
		type : 'green',
		buttons : {
			ok : {
				text : '确定',
				btnClass : 'btn-primary',
				keys : [ 'enter' ],
				action : function() {
					deleteRemoteCodeLogic();
				}
			},
			cancel : {
				text : '否，点错了',
				btnClass : 'btn-primary',
				keys : [ 'ESC' ],
			}
		}
	});
}

/**
 * 逻辑删除某篇代码
 * 
 * @returns
 */
function deleteRemoteCodeLogic() {
	$.post('/article/delete.hms', {
		type : 'logic_delete_code',
		codeId : $('#hiden_codeId').val()
	}, function(response) {
		if (response == 1) {
			// 删除成功，跳转到博客目录界面
			window.location = "/module/code.hms";
		}
	}, 'json');
}