/**
 * @author 何明胜
 * 
 * 2017年12月1日
 */

var $fileList;
var $btnUpload;
var state = 'pending';
var web_uploader;

$(function() {
	$fileList = $('#file_list');
	$btnUpload = $('#btn_uploadStart');
	
	initWebLoader();
	showFileList();
	uploadProgress();
	callbackFun();
	uploadClick();
});

/**
 * 初始化Web Uploader
 * 
 * @returns
 */
function initWebLoader() {
	web_uploader = WebUploader.create({
		// swf文件路径
		swf : '/plugins/webuploader/Uploader.swf',

		// 文件接收服务端。
		server : '/fileUpload.hms',

		// 选择文件的按钮。可选。
		// 内部根据当前运行时创建，可能是input元素，也可能是flash.
		pick : '#file_choose',

		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize : false
	});
}

/**
 * 显示用户选择
 * 
 * @returns
 */
function showFileList() {
	// 当有文件被添加进队列的时候
	web_uploader.on('fileQueued', function(file) {
		$fileList.append('<div id="' + file.id + '" class="item">'
				+ '<h4 class="info">' + file.name + '</h4>'
				+ '<p class="state">等待上传...</p>' + '</div>');
	});
}

/**
 * 文件上传进度显示
 * @returns
 */
function uploadProgress(){
	// 文件上传过程中创建进度条实时显示。
	web_uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id );
	    var $percent = $li.find('.progress .progress-bar');

	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<div class="progress progress-striped active">' +
	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	          '</div>' +
	        '</div>').appendTo( $li ).find('.progress-bar');
	    }

	    $li.find('p.state').text('上传中');

	    $percent.css( 'width', percentage * 100 + '%' );
	});
}

/**
 * 回调函数
 * @returns
 */
function callbackFun(){
	/** 文件上传成功 **/
	web_uploader.on('uploadSuccess', function( file ) {
	    $('#'+file.id).find('p.state').text('已上传');
	});

	/** 文件上传失败 **/
	web_uploader.on('uploadError', function( file ) {
	    $('#'+file.id).find('p.state').text('上传出错');
	});

	/** 不管成功或者失败，在文件上传完后都会触发uploadComplete事件 **/
	web_uploader.on('uploadComplete', function( file ) {
	    $('#'+file.id).find('.progress').fadeOut();
	});
	
	/** 所有的事件触发都会响应到，改变当前状态 **/
	web_uploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
        }

        if ( state === 'uploading' ) {
        	$btnUpload.text('暂停上传');
        } else {
        	$btnUpload.text('开始上传');
        }
    });
}

/**
 * 开始上传点击事件
 * @returns
 */
function uploadClick(){
	$btnUpload.click(function() {
        if (state === 'uploading') {
        	web_uploader.stop();
        } else {
        	web_uploader.upload();
        }
    });
}