/**
 * 自动显示分页的工具类 将当前分页的容器节点传入
 * 
 * 2017年9月29日
 */

var currentPageNum = 1; // 当前页码
var paginationDisplayLength = 5;// 分页栏的显示条数
var totalPages = 1; // 总页数
var onlyOnePageIsShow = true;// 只有一页的时候是否显示

/**
 * 调用入口
 * 
 * @param paginationContainer
 * @returns
 */
function PaginationHelper(paginationContainer, pageSize) {
	initPagination(paginationContainer, pageSize);
}

/**
 * 初始化分页
 * 
 * @param element
 * @returns
 */
function initPagination(paginationContainer, pageSize) {
	paginationContainer.html('');
	// 当前选中页码
	currentPageNum = Number(paginationContainer.attr('currpagenum'));
	// 分页显示几个页面
	paginationDisplayLength = Number(paginationContainer
			.attr('paginationmaxlength'));
	// 一共有多少页
	totalPages = Number(paginationContainer.attr('totalpages'));
	// 只有一页是否显示
	onlyOnePageIsShow = paginationContainer.attr('onlyonepageshow');

	// 如果需要分页
	if (isNeedPagination(totalPages, onlyOnePageIsShow)) {
		// 左边的箭头
		var content = '<ul class="pagination pagination-sm"><li value="0"><a href="javascript:void(0);">«</a></li>';
		// 中间的数字
		for (var i = 1; i <= totalPages; i++) {
			content += '<li value="' + i + '"><a href="javascript:void(0);">'
					+ i + '</a></li>'
		}
		// 右边的箭头
		content += '<li value="-1"><a href="javascript:void(0);">»</a></li></ul>';
		paginationContainer.append(content);
		// 添加每页大小选择显示
		addPageSizeChoose(paginationContainer, pageSize);
		// 当前页面添加激活类
		paginationContainer.children('ul').children(
				'li[value=' + currentPageNum + ']').attr('class', 'active');
		// 设置显示最大长度
		setDisplayMaxLength(paginationContainer, paginationDisplayLength);
		// 注册监听器
		addClickListener(paginationContainer);
	}
}

/**
 * 是否需要分页
 * 
 * @param totalpage
 * @param settingfromHTML
 * @returns
 */
function isNeedPagination(totalPages, settingFromHTML) {
	var condition;
	// 先从页面获取是否需要显示分页，再从js变量
	if (settingFromHTML == 'true') {
		condition = true;
	} else if (settingFromHTML == 'false') {
		condition = false;
	} else {
		condition = onlyOnePageIsShow;
	}

	if (condition && totalPages < 1) {
		return false;
	} else if (!condition && totalPages <= 1) {
		return false;
	}
	return true;
}

/**
 * 设置显示最大长度
 * 
 * @param element
 * @param len
 * @returns
 */
function setDisplayMaxLength(element, len) {
	if (checkParamIsPositiveInteger(len)) {
		len = Number(len);
	} else {
		// 如果不是整数就使用默认
		len = paginationDisplayLength;
	}

	if (len < totalPages) {
		var temp1 = parseInt((len - 1) / 2);
		var temp2 = parseInt(len / 2);
		if (temp1 < temp2) { // len为偶数
			var leftstart = currentPageNum - temp1;
			var rightend = currentPageNum + temp1 + 1;
		} else { // len为奇数
			var leftstart = currentPageNum - temp1;
			var rightend = currentPageNum + temp1;
		}
		// 如果左边小于1，右边来补
		if (leftstart < 1) {
			rightend += (1 - leftstart);
			leftstart = 1;
		}
		// 右边大于总数，左边来补
		if (rightend > totalPages) {
			if (leftstart > 1) {
				leftstart -= (rightend - totalPages);
			}
			rightend = totalPages;
		}
		if (leftstart < 1) {
			leftstart = 1
		}
		// 隐藏左边
		while (leftstart > 1) {
			element.children('ul')
					.children('li[value = ' + (--leftstart) + ']').css(
							'display', 'none');
		}
		// 隐藏右边
		while (rightend < totalPages) {
			element.children('ul').children('li[value = ' + (++rightend) + ']')
					.css('display', 'none');
		}
	}
}

/**
 * 点击事件注册
 * 
 * @param element
 * @returns
 */
function addClickListener(element) {
	element.children('ul').children('li')
			.bind(
					'click',
					function() {
						var pageNumValue = Number($(this).attr('value'));
						if (pageNumValue == 0) { // 左边减一页
							pageNumValue = currentPageNum - 1;
						} else if (pageNumValue == -1) { // 右边加一页
							pageNumValue = currentPageNum + 1;
						}
						// 跳转
						if (pageNumValue != currentPageNum && pageNumValue != 0
								&& pageNumValue <= totalPages) {
							$(this).parent().parent().attr('currpagenum',
									pageNumValue);
							// 回调相应文章的相应函数，跨js
							paginationClick(pageNumValue);
							initPagination(element);
						}
						return false;
					});
}

/**
 * 检查是否为整数
 * 
 * @param param
 * @returns
 */
function checkParamIsPositiveInteger(param) {
	var reg = /^[1-9]+[0-9]*]*$/;
	return reg.test(param);
}

/**
 * 在分页后面显示选择每页数量
 * 
 * @param paginationContainer
 * @param currPageSize
 * @returns
 */
function addPageSizeChoose(paginationContainer, currPageSize) {
	var isDownload = new RegExp('download').test(window.location.href);
	
	//目前不支持文件
	if (!isDownload) {
		paginationContainer
				.append(' <!-- 选择每页显示的条数 -->'
						+ '<div id="choose_page_size" class="btn-group pagination pagination-sm dropup choose-page-size">'
						+ '<button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
						+ '每页显示<span>' + currPageSize
						+ '</span>条 <span class="caret"></span>' + '</button>'
						+ '<ul class="dropdown-menu">'
						+ '<li value="5"><a href="#">每页显示5条</a></li>'
						+ '<li value="10"><a href="#">每页显示10条</a></li>'
						+ '<li value="20"><a href="#">每页显示20条</a></li>'
						+ '</ul>' + '</div>');
	}
}