/**
 * 加载某篇详细的博客的js
 * 
 * @author 何明胜
 * 
 * 2017年11月8日
 */

$(document).ready(function() {
	// 如果删除博客按钮存在,绑定点击事件
	var $btnDeleteBlog = $('#btn_deleteBlog');
	if ($btnDeleteBlog.length > 0) {
		$btnDeleteBlog.click(deleteBlogClick);
	}
});

/**
 * 删除博客按钮点击
 * 
 * @returns
 */
function deleteBlogClick() {
	$.confirm({
		title : '删除博客确认',
		content : '是否确定删除这篇博客?',
		type : 'green',
		buttons : {
			ok : {
				text : '确定',
				btnClass : 'btn-primary',
				keys : [ 'enter' ],
				action : function() {
					deleteRemoteBlogLogic();
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
 * 逻辑删除某篇博客
 * 
 * @returns
 */
function deleteRemoteBlogLogic() {
	$.post('/article/delete.hms', {
		type : 'logic_delete_blog',
		blogId : $('#hiden_blogId').val()
	}, function(response) {
		if (response == 1) {
			// 删除成功，跳转到博客目录界面
			window.location = "/module/blog.hms";
		}
	}, 'json');
}