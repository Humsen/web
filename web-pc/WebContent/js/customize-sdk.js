/**
 * 自定义开发工具包
 * 
 * @author 何明胜
 *
 * 2017年10月23日
 */

/**
 * 重写日期格式
 * 
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 例子： (new
 * Date()).Format('yyyy-MM-dd hh:mm:ss.S') ==> 2006-07-02 08:09:04.423 (new
 * Date()).Format('yyyy-M-d h:m:s.S') ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function(fmt) {
    var o = {
        'M+' : this.getMonth() + 1, // 月份
        'd+' : this.getDate(), // 日
        'h+' : this.getHours(), // 小时
        'm+' : this.getMinutes(), // 分
        's+' : this.getSeconds(), // 秒
        'q+' : Math.floor((this.getMonth() + 3) / 3), // 季度
        'S' : this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '')
                .substr(4 - RegExp.$1.length));
    for ( var k in o)
        if (new RegExp('(' + k + ')').test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                    : (('00' + o[k]).substr(('' + o[k]).length)));
    return fmt;
} 

/**
 * 获取url 地址参数
 */
$.extend({
	'getUrlParam' : function(variable){
		var query = window.location.search.substring(1);
		var vars = query.split('&');
		
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split('=');
			if (pair[0] == variable) {
				return pair[1];
			}
		}
		
		return false;
	}
});

/**
 * 获取当前 HH:mm:ss 格式的时间
 */
$.extend({
	'nowDateHMS' : function() {
		var date = new Date();
		var seperator1 = '-';
		var seperator2 = ':';
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = '0' + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = '0' + strDate;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1
				+ strDate + ' ' + date.getHours() + seperator2 + date.getMinutes()
				+ seperator2 + date.getSeconds();
		
		return currentdate;
	}
});

/**
 * 判断是否是手机
 * 
 * @returns
 */
$.extend({
	'isMobile' : function() {
		var userAgentInfo = navigator.userAgent;
		
		var mobileAgents = [ 'Android', 'iPhone', 'SymbianOS', 'Windows Phone', 'iPad','iPod'];
		
		var mobile_flag = false;
		
		// 根据userAgent判断是否是手机
		for (var v = 0; v < mobileAgents.length; v++) {
			if (userAgentInfo.indexOf(mobileAgents[v]) > 0) {
				mobile_flag = true;
				break;
			}
		}
		
		 var screen_width = window.screen.width;
		 var screen_height = window.screen.height;    
		 
		 // 根据屏幕分辨率判断是否是手机
		 if(screen_width < 500 && screen_height < 800){
			 mobile_flag = true;
		 }
		 
		 return mobile_flag;
	}
});

/**
 * 验证码60s后可重发
 */
$.extend({
	'codeCountDown' : function ($btnSendCode) {
		$btnSendCode.html('重发(<label id="txt_secondsCount">60</label>s)');
		$btnSendCode.attr({'disabled':'disabled'});
		
		/**
		 * 验证码60s重发辅助内部函数
		 * @returns
		 */
		function codeCountDownHelper(){
			//验证码的id统一命名
			var nowCount = $('#txt_secondsCount').text();

			if (nowCount == 1) {
				setTimeout(function() {
					$btnSendCode.html('发送验证码');
					$btnSendCode.removeAttr('disabled');
				}, 1000);

				return;
			} else {
				$('#txt_secondsCount').text(nowCount - 1);
			}

			setTimeout(function(){
				codeCountDownHelper();
			}, 1000);
		}
		
		//开始倒计时
		codeCountDownHelper();
	}
});

/**
 * 表单转json
 */
$.fn.form2Json = function(){
    var json_obj = {};
    var array = this.serializeArray();
    
    $.each(array, function() {
        if (json_obj[this.name] !== undefined) {
            if (!json_obj[this.name].push) {
            	json_obj[this.name] = [json_obj[this.name]];
            }
            json_obj[this.name].push(this.value || '');
        } else {
        	json_obj[this.name] = this.value || '';
        }
    });
    return json_obj;
};