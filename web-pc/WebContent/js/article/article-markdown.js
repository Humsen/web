/**
 * @author 何明胜
 *
 * 2017年10月18日
 */

$(function(){
	// 加载文章细节后, 自动显示markdown
	showMarkdown();
});

/** 
 * 解析markdown html
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