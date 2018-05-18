/**
 * @author 何明胜
 * 
 * 2017年9月25日
 */

(function(window, undefined) {
	/**
	 * 创建元素节点并返回
	 */
	function create(tagName, className, parent) {
		var element = document.createElement(tagName);
		element.className = className;
		parent.appendChild(element);
		return element;
	}

	/**
	 * 数组消除重复
	 */
	function clearRepeat(arr) {
		var obj = {}, result = [];
		for (var i = 0, len = arr.length; i < len; i++) {
			obj[arr[i]] = 1;
		}
		for ( var i in obj) {
			result.push(i);
		}
		return result;
	}

	/**
	 * 添加类名
	 */
	function addClassName(element, className) {
		var aClass = element.className.split(' ');
		aClass.push(className);
		aClass = clearRepeat(aClass);
		element.className = aClass.join(' ');
	}
	/**
	 * 删除类名
	 */
	function delClassName(element, className) {
		var aClass = element.className.split(' '), index = aClass
				.indexOf(className);
		if (index > 0)
			aClass.splice(index, 1);
		element.className = aClass.join(' ');
	}

	/**
	 * 检查是否含有类名
	 * 
	 * @param element
	 * @param className
	 * @returns {boolean}
	 */
	function hasClassName(element, className) {
		var aClass = element.className.split(' '), index = aClass
				.indexOf(className);
		if (index > 0)
			return true;
		return false;
	}

	var Pager = function(obj) {

		this.__total = obj.total || 1;
		this.__index = obj.index || 1;
		this.__parent = obj.parent;
		this.__onchange = obj.onchange;
		// 初始化分页器
		this.__init(obj);

	};

	var pro = Pager.prototype;
	/**
	 * 初始化分页器
	 */
	pro.__init = function(obj) {
		if (this.__total < this.__index)
			return;
		// 存储数字
		this.__numbers = [];
		// 储存省略号
		this.__dots = [];
		this.__wrapper = create('div', 'pager-box', this.__parent);
		this.__body = create('div', 'pager', this.__wrapper);
		// 存储上一页
		this.__preBtn = create('a', 'prev', this.__body);
		this.__preBtn.href = 'javascript:void(0);';
		this.__preBtn.innerText = (obj.label && obj.label.prev) || '上一页';
		// 存储数字
		if (this.__total < 8) {
			for (var i = 0; i < this.__total; i++) {
				var t = create('a', 'number', this.__body);
				t.href = 'javascript:void(0);';
				t.innerText = i + 1;
				this.__numbers.push(t);
			}
		} else {
			for (var i = 0; i < 2; i++) {
				var t = create('span', 'dots', this.__body);
				t.innerText = '...';
				this.__dots.push(t);
			}
			;
			for (var i = 0; i < 7; i++) {
				var t = create('a', 'number', this.__body);
				t.href = 'javascript:void(0);';
				this.__numbers.push(t);
			}

		}
		// 存储下一页
		this.__nextBtn = create('a', 'next', this.__body);
		this.__nextBtn.href = 'javascript:void(0);';
		this.__nextBtn.innerText = (obj.label && obj.label.next) || '下一页';
		//
		this._$setIndex(this.__index);
		//
		this.__body.onclick = this.__doClick.bind(this);
	};

	pro.__doClick = function(e) {
		var e = e || window.event, target = e.target || e.srcElement;
		// 点击省略号
		if (target.tagName.toLowerCase() == 'span')
			return;
		// 点击了不能点击的上一页或者下一页
		if (hasClassName(target, 'js-disabled'))
			return;
		// 点击了当前页
		if (hasClassName(target, 'js-selected'))
			return;

		if (target == this.__preBtn) {
			// 点击了上一页
			this._$setIndex(this.__index - 1);
		} else if (target == this.__nextBtn) {
			// 点击了下一页
			this._$setIndex(this.__index + 1);
		} else {
			// 点击了数字
			var index = target.innerText;
			this._$setIndex(index);
		}

	};

	/**
	 * 跳转页数
	 */
	pro._$setIndex = function(index) {
		index = parseInt(index);
		// 更新信息
		if (index != this.__index) {
			this.__last = this.__index;
			this.__index = index;
		}
		// 处理
		delClassName(this.__preBtn, 'js-disabled');
		delClassName(this.__nextBtn, 'js-disabled');
		if (this.__total < 8) {
			// 总页数小于8的情况
			if (this.__last)
				delClassName(this.__numbers[this.__last - 1], 'js-selected');
			addClassName(this.__numbers[this.__index - 1], 'js-selected');
			if (this.__index == 1)
				addClassName(this.__preBtn, 'js-disabled');
			if (this.__index == this.__total)
				addClassName(this.__nextBtn, 'js-disabled');

		} else {
			this.__dots[0].style.display = 'none';
			this.__dots[1].style.display = 'none';
			for (var i = 0; i < 7; i++) {
				delClassName(this.__numbers[i], 'js-selected');
			}
			;
			if (this.__index < 5) {
				for (var i = 0; i < 6; i++) {
					this.__numbers[i].innerText = i + 1;
				}
				this.__numbers[6].innerText = this.__total;
				this.__dots[1].style.display = 'block';
				this.__body.insertBefore(this.__dots[1], this.__numbers[6]);
				addClassName(this.__numbers[this.__index - 1], 'js-selected');
				if (this.__index == 1)
					addClassName(this.__preBtn, 'js-disabled');
			} else if (this.__index > this.__total - 4) {
				for (var i = 1; i < 7; i++) {
					this.__numbers[i].innerText = this.__total + i - 6;
				}
				this.__numbers[0].innerText = '1';
				this.__dots[0].style.display = 'block';
				this.__body.insertBefore(this.__dots[0], this.__numbers[1]);
				addClassName(this.__numbers[this.__index + 6 - this.__total],
						'js-selected');
				if (this.__index == this.__total)
					addClassName(this.__nextBtn, 'js-disabled');
			} else {
				this.__numbers[0].innerText = '1';
				for (var i = 1; i < 6; i++) {
					this.__numbers[i].innerText = this.__index - 3 + i;
					if (i == 3)
						addClassName(this.__numbers[i], 'js-selected');
				}
				this.__numbers[6].innerText = this.__total;
				this.__dots[0].style.display = 'block';
				this.__body.insertBefore(this.__dots[0], this.__numbers[1]);
				this.__dots[1].style.display = 'block';
				this.__body.insertBefore(this.__dots[1], this.__numbers[6]);
			}

		}
		if (typeof this.__onchange == 'function') {
			this.__onchange({
				index : this.__index,
				last : this.__last,
				total : this.__total
			})
		}

	};
	/**
	 * 得到总页数
	 */
	pro._$getIndex = function() {
		return this.__index;
	};
	/**
	 * 得到上一个页数
	 */
	pro._$getLast = function() {
		return this.__last;
	};
	// 变成全局
	window.Pager = Pager;

})(window);

/*(function() {
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
});*/