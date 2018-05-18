/**
 * 留言区函数
 * 
 * @author 何明胜
 * 
 * 2017年9月25日
 */

/** 加载插件 * */
$.ajax({
	url : '/plugins/plugins.html', // 这里是静态页的地址
	async : false,
	type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
	success : function(data) {
		$($('head')[0]).find('script:first').after(data);
	}
});

$(function() {
	/** 顶部导航栏 **/
	$.ajax({
		url : '/module/navigation/topbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});

	/** 登录控制 **/
	$.ajax({
		url : '/module/login/login.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#menuBarNo').before(data);
		}
	});
	
	/** 左侧导航栏 **/
	$.ajax({
		url : '/module/navigation/leftbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#fh5co-main').before(data);
		}
	});
	
	/** 右侧导航栏 **/
	$.ajax({
		url : '/module/navigation/rightbar.html', // 这里是静态页的地址
		async : false,
		type : 'GET', // 静态页用get方法，否则服务器会抛出405错误
		success : function(data) {
			$('#fh5co-main').after(data);
		}
	});
});

// 初始化
$(function() {
	Comment.allocate({
		parent : $('#message_box'), // 你想要将这个评论放到页面哪个元素中
		id : 0,
		getCmtUrl : '/message.hms',
		setCmtUrl : '/message.hms'
	})

});

/**
 * 执行初始化的函数
 */
Comment.allocate = function(options) {
	// 构造函数
	var oCmt = new Comment(options);
	if (oCmt.belong == undefined || !oCmt.getCmtUrl || !oCmt.setCmtUrl) {
		return null;
	}
	;
	// 初始化
	oCmt.init(options);

	return oCmt;
};

/**
 * 构造函数
 * 
 * @param options
 * @returns
 */
function Comment(options) {
	this.belong = options.id;
	this.getCmtUrl = options.getCmtUrl;
	this.setCmtUrl = options.setCmtUrl;
	this.lists = [];
	this.keys = {};
	this.offset = 5;
}

var fn = Comment.prototype;

/**
 * 初始化函数
 */
fn.init = function(options) {
	// 初始化node
	this.initNode(options);
	// 将内容放进容器
	this.parent.html(this.body);
	// 初始化事件
	this.initEvent();
	// 获取列表
	this.getList();
};

/**
 * 初始化结点或者缓存DOM
 */
fn.initNode = function(options) {
	// init wrapper box
	if (!!options.parent) {
		this.parent = options.parent[0].nodeType == 1 ? options.parent : $('#'
				+ options.parent);
	}

	if (!this.parent) {
		this.parent = $('div');
		$('body').append(this.parent);
	}

	// init content
	this.body = (function() {
		var strHtml = '<div class="m-comment">'
				+ '<div class="cmt-form" >'
				+ '<textarea class="cmt-text form-control meessage-input" placeholder="请输入留言..."></textarea>'
				+ '<button class="btn btn-success btn-sm u-button btn-submit-commit">提交评论</button>'
				+ '<hr class="commit-hr">' + '</div>'
				+ '<div class="cmt-content">'
				+ '<div class="u-loading1"></div>'
				+ '<div class="no-cmt">暂时没有评论</div>'
				+ '<ul class="cmt-list"></ul>' + '<div class="f-clear">'
				+ '<div class="pager-box"></div>' + '</div>' + '</div>'
				+ '</div>';

		return $(strHtml);
	})();

	// init other node
	this.text = this.body.find('.cmt-text').eq(0);
	this.cmtBtn = this.body.find('.u-button').eq(0);
	this.noCmt = this.body.find('.no-cmt').eq(0);
	this.cmtList = this.body.find('.cmt-list').eq(0);
	this.loading = this.body.find('.u-loading1').eq(0);
	this.pagerBox = this.body.find('.pager-box').eq(0);
};

/**
 * 初始化列表
 */
fn.resetList = function() {
	this.loading.css('display', 'block')
	this.noCmt.css('display', 'none');
	this.cmtList.html('');
};

/**
 * ajax获取
 */
fn.getList = function() {
	var self = this;
	this.resetList();

	$.ajax({
		url : self.getCmtUrl,
		type : 'get',
		dataType : 'json',
		data : {
			type : 'query_all',
			messageId : self.belong
		},
		success : function(data) {
			if (!data) {
				$.confirm({
					content : '获取评论列表失败',
					autoClose : 'ok|2000',
					type : 'red',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});

				return !1;
			}
			// 增加reponse字段和处理时间
			for ( var i in data) {
				data[i].response = [];
				data[i].messageDate = new Date(data[i].messageDate.time)
						.format('yyyy-MM-dd hh:mm:ss')
			}
			// 整理评论列表
			self.initList(data);
			self.loading.css('display', 'none');
			// 显示评论列表
			if (self.lists.length == 0) {
				$.confirm({
					content : '暂时没有评论',
					autoClose : 'ok|2000',
					type : 'red',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});
				// 暂时没有评论
				self.noCmt.css('display', 'block');
			} else {
				// 设置分页器
				var total = Math.ceil(self.lists.length / self.offset);

				self.pager = new Pager({
					index : 1,
					total : total,
					parent : self.pagerBox[0],
					onchange : self.doChangePage.bind(self),
					label : {
						prev : '<',
						next : '>'
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus) {
			$.confirm({
				title : '获取评论列表出错',
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
};

/**
 * 初始化节点
 * 
 * this.lists放的都是评论（parent为0的留言） 通过遍历获取的数据，如果parent为0，就push进this.lists；
 * 否则parent不为0表示这是个回复，就找到对应的评论，把该回复push进那条评论的response中。
 * 但是还有个问题，就是因为id是不断增长的，可能中间有些评论被删除了，
 * 所以id和index并不一定匹配，所以借助this.keys保存id和index的对应关系。
 */
fn.initList = function(data) {
	this.lists = []; // 保存评论列表
	this.keys = {}; // 保存评论id和index对应表

	var index = 0;
	// 遍历处理
	for (var i = 0, len = data.length; i < len; i++) {
		var t = data[i], id = t['messageId'];

		if (t['messageParent'] == 0) {
			this.keys[id] = index++;
			this.lists.push(t);
		} else {
			var parentId = t['messageParent'], parentIndex = this.keys[parentId];
			this.lists[parentIndex]['response'].push(t);// 这里response可能会有问题
		}
	}
};

/**
 * onchange函数，默认页数为1，保存在参数obj.index中
 */
fn.doChangePage = function(obj) {
	this.showList(obj.index);
};

/**
 * 显示列表函数 代码自动运行
 */
fn.showList = (function() {
	/* 生成一条评论字符串 */
	function oneLi(_obj) {
		var str1 = '';
		// 处理回复
		for (var i = 0, len = _obj.response.length; i < len; i++) {
			var t = _obj.response[i];
			t.messageContent = t.messageContent.replace(/\<\;/g, '<');
			t.messageContent = t.messageContent.replace(/\>\;/g, '>');
			str1 += '<li class="f-clear"><table><tbody><tr><td>'
					+ '<span class="username">' + t.messageUsername
					+ '：</span></td><td>' + '<span class="child-content">'
					+ t.messageContent + '</span></td></tr></tbody></table>'
					+ '</li>'
		}
		// 处理评论
		var headImg = '';
		if (_obj.messageUsername == 'kang') {
			headImg = 'kang_head.jpg';
		} else {
			var index = Math.floor(Math.random() * 9);
			headImg = 'head-' + index + '.jpg'
		}
		_obj.messageContent = _obj.messageContent.replace(/\<\;/g, '<');
		_obj.messageContent = _obj.messageContent.replace(/\>\;/g, '>');
		var str2 = '<li class="f-clear">' + '<hr class="commit-hr1"/>'
				+ '<div class="user-head">' + '<img src="/images/message/'
				+ headImg
				+ '"	 class="message-head-img"/>'
				+ '</div>'
				+ '<div class="content c-float-left">'
				+ '<div class="f-clear">'
				+ '<span class="username user-float-left">'
				+ _obj.messageUsername
				+ '</span>'
				+ '<span class="time user-float-left">'
				+ _obj.messageDate
				+ '</span>'
				+ '</div>'
				+ '<span class="parent-content">'
				+ _obj.messageContent
				+ '</span>'
				+ '<ul class="child-comment">'
				+ str1
				+ '</ul>'
				+ '</div>'
				+ '<div class="respone-box g-col-2 f-float-right">'
				+ '<a href="javascript:void(0);" class="f-show response" data-id="'
				+ _obj.messageId + '">[回复]</a>' + '</div>' + '</li>';

		return str2;
	}

	var page = 1;
	return function(page) {
		var len = this.lists.length, end = len - (page - 1) * this.offset, start = end
				- this.offset < 0 ? 0 : end - this.offset, current = this.lists
				.slice(start, end);
		var cmtList = '';
		for (var i = current.length - 1; i >= 0; i--) {
			var t = current[i], index = this.keys[t['messageId']];
			current[i]['index'] = index;
			cmtList += oneLi(t);
		}
		this.cmtList.html(cmtList);
	};

})();

/**
 * 初始化按钮点击事件
 */
fn.initEvent = function() {
	// 提交按钮点击
	this.cmtBtn.on('click', this.addCmt.bind(this, this.cmtBtn, this.text, 0));
	// 点击回复，点击取消回复，点击回复中的提交评论按钮
	this.cmtList.on('click', this.doClickResponse.bind(this));
};

fn.addCmt = function(_btn, _text, _parent) {
	// 防止多次点击
	if (_btn.attr('data-disabled') == 'true') {
		return !1;
	}
	// 处理提交空白
	var value = _text.val().replace(/^\s+|\s+$/g, '');
	value = value.replace(/[\r\n]/g, '<br >');
	if (!value) {
		$.confirm({
			title : '',
			content : '内容不能为空',
			autoClose : 'ok|2000',
			type : 'red',
			buttons : {
				ok : {
					text : '确认',
					btnClass : 'btn-primary',
				},
			}
		});
		return !1;
	}
	// 禁止点击
	_btn.attr('data-disabled', 'true');
	_btn.html('评论提交中...');
	// 提交处理
	/**
	 * todo 暂时全部设置为游客
	 */
	var self = this;
	var email = '940706904@qq.com';
	var username = '游客';

	/*
	 * username = $.cookie('user'); if (!username) { alert('游客') username =
	 * '游客'; } email = $.cookie('email'); if (!email) { email =
	 * 'default@163.com'; }
	 */

	var now = $.nowDateHMS();
	// 将参数封装到对象里
	var new_message = {};
	new_message.messageBelong = self.belong;
	new_message.messageParent = _parent;
	new_message.messageEmail = email;
	new_message.messageUsername = username;
	new_message.messageContent = value;
	new_message.messageDate = now;

	$.ajax({
		type : 'get',
		dataType : 'json',
		url : this.setCmtUrl,
		data : {
			type : 'create_one',
			newMessage : JSON.stringify(new_message),
		/*
		 * belong: self.belong, parent: _parent, email: email, username:
		 * username, content: value
		 */
		},
		success : function(_data) {
			// 解除禁止点击
			_btn.attr('data-disabled', '');
			_btn.html('提交评论');
			if (!_data) {
				$.confirm({
					content : '评论失败，请重新评论',
					autoClose : 'ok|2000',
					type : 'green',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});

				return !1;
			}
			if (_data['result'] == 1) {
				// 评论成功
				$.confirm({
					title : false,
					content : '评论成功',
					autoClose : 'ok|1500',
					type : 'green',
					buttons : {
						ok : {
							text : '确认',
							btnClass : 'btn-primary',
						},
					}
				});

				var id = _data['messageId'];
				var time = now;
				/*
				 * time = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' +
				 * now.getDate() + ' ' + now.getHours() + ':' + now.getMinutes() +
				 * ':' + now.getSeconds();
				 */
				if (_parent == 0) {
					var index = self.lists.length;

					if (!self.pager) {
						// 设置分页器
						self.noCmt.css('display', 'none');
						var total = Math.ceil(self.lists.length / self.offset);

						self.pager = new Pager({
							index : 1,
							total : total,
							parent : self.pagerBox[0],
							onchange : self.doChangePage.bind(self),
							label : {
								prev : '<',
								next : '>'
							}
						});
					}

					self.keys[id] = index;
					self.lists.push({
						'messageId' : id,
						'messageUsername' : username,
						'messageDate' : time,
						'messageContent' : value,
						'response' : []
					});
					self.showList(1);
					self.pager._$setIndex(1);
				} else {
					var index = self.keys[_parent], page = self.pager.__index;
					self.lists[index]['response'].push({
						'messageId' : id,
						'messageUsername' : username,
						'messageDate' : time,
						'messageContent' : value
					});
					self.showList(page);
				}

				self.text.val('');
			} else {
				$.confirm({
					title : '',
					content : '评论失败，请重新评论',
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
				title : '评论出错，请重新评论',
				content : textStatus + ' : ' + XMLHttpRequest.status,
				autoClose : 'ok|2000',
				type : 'green',
				buttons : {
					ok : {
						text : '确认',
						btnClass : 'btn-primary',
					},
				}
			});

			// 解除禁止点击
			_btn.attr('data-disabled', '');
			_btn.html('提交评论');
		}
	});
}

/**
 * doClickResponse 函数
 */
fn.doClickResponse = function(_event) {
	var target = $(_event.target);

	var id = target.attr('data-id');

	if (target.hasClass('response') && target.attr('data-disabled') != 'true') {
		// 点击回复
		var oDiv = document.createElement('div');
		oDiv.className = 'cmt-form';

		var replyHtml = '';
		// 判断是否是手机
		if ($.isMobile()) {
			replyHtml += '<textarea class="cmt-text comment-input mobile-reply" placeholder="请输入回复评论..."></textarea>';
			replyHtml += '<div class="reply-div-mobile">';
		} else {
			replyHtml += '<textarea class="cmt-text comment-input" placeholder="请输入回复评论..."></textarea>';
			replyHtml += '<div class="reply-div-pc">';
		}
		oDiv.innerHTML = replyHtml
				+ '<button class="u-button resBtn" data-id="'
				+ id
				+ '">提交评论</button>'
				+ '<a href="javascript:void(0);" class="cancel cancel-reply">[取消回复]</a>'
				+ '</div>';
		target.parent().parent().append(oDiv);
		oDiv = null;
		target.attr('data-disabled', 'true');
	} else if (target.hasClass('cancel')) {
		// 点击取消回复
		var ppNode = target.parent().parent().parent(), oRes = ppNode.find(
				'.response').eq(0);
		target.parent().parent().remove();
		oRes.attr('data-disabled', '');
	} else if (target.hasClass('resBtn')) {
		// 点击评论
		var oText = target.parent().parent().find('.cmt-text').eq(0);
		var parent = target.attr('data-id');
		this.addCmt(target, oText, parent);
	} else {
		// 其他情况
		return !1;
	}
};