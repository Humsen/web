/**
 * 上传文件
 * 
 * @author 何明胜
 * 
 * 2017年9月29日
 */

//判断是否超级管理员，否则不能访问
$(function(){
	if($.cookie('username') != "super_admin"){
		window.location.replace("/error/error.jsp");
	}
});

/**
 * 暂未使用
 */
/*$(function(){
	$("#submitFile").click(function(){
		$.ajax({
			url : "/fileUpload",
			data : $("#fileForm").serialize(),
			success: function(data){
				if(data == 0){
					//alert("上传失败");
				}else{
					alert("上传成功");
				}
			},
			error : function(){
				alert("上传错误")
			}
		});
	});
});*/