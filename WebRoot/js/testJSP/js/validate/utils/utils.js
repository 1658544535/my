/**
 * 
 * @param {}
 *            target
 * @param {}
 *            type such as "click"
 * @param {}
 *            handler
 */
tt.Util.addEventHandler = function(o, type, handler) {
	f = arguments.length == 4;
	if (o.addEventListener) {
		if (f) {
			o.removeEventListener(type, handler, false);
		} else {
			o.addEventListener(type, handler, false);
		}
	} else if (o.attachEvent) {

		if (f) {
			o.detachEvent("on" + type, handler);
		} else {
			o.attachEvent("on" + type, handler);
		}
	} else {
		if (f) {
		} else {
			o["on" + type] = handler;

		}
	}
};

/**
 * 将htmlElement插入到srcElement元素后面
 * 
 * @param srcElement
 * @param htmlElement
 */
tt.insertAfter = function(src, e) {
	tt.insertHtml('afterend', src, e);
};

tt.insertHtml = function(where, el, html) {
	where = where.toLowerCase();
	if (el.insertAdjacentHTML) {
		switch (where) {
			case "afterend" :
				el.insertAdjacentHTML('AfterEnd', html);
				return el.nextSibling;
		}
	} else {
		var range = el.ownerDocument.createRange();
		var frag;
		switch (where) {
			case "afterend" :
				range.setStartAfter(el);
				frag = range.createContextualFragment(html);
				el.parentNode.insertBefore(frag, el.nextSibling);
				return el.nextSibling;
		}
	}
};
/**
 * 为element添加className样式
 * 
 * @param element
 *            被操作的元素
 * @param className
 *            样式名
 * @return
 */
tt.addCls = function(target, _className) {
	tClassName = target.className;
	tClassName = " " + tClassName + " ";
	if (tClassName.indexOf(" " + _className + " ") == -1) {
		target.className = tClassName + _className;
	}
};
/**
 * 为element删除className样式
 * 
 * @param element
 *            被操作的元素
 * @param className
 *            样式名
 * @return
 */
tt.rmCls = function(target, name) {
	if (!target || !target.className) {
		return;
	}
	var oClass = target.className;
	var reg = "/\\b" + name + "\\b/g";
	target.className = oClass ? oClass.replace(eval(reg), '') : '';
};

/**
 * 删除某一个元素
 * 
 * @param element
 * @return
 */
tt.rmEle = function(e) {
	if (e && e.parentNode) {
		e.parentNode.removeChild(e);
	}
};

/**
 * 相当于string的trim
 * 
 * @param str
 * @return
 */
tt.trim = function(s, m) {
	if (!s) {
		return "";
	}
	r = /(^\s*)|(\s*$)/g;
	if (m) {
		if (m == "l") {
			r = /(^\s*)/g;
		} else if (m == "r") {
			r = /(\s*$)/g;
		}
	}
	return s.replace(r, "");
};

/**
 * 根据类名实例化js对象
 * 
 * @param {}
 *            clazz
 * @return {}
 */
tt.instanceByClass = function(c) {
	eval("var r = new " + c + "();");
	return r;
};

/**
 * 
 * @param {}
 *            v comparedValue
 * @param {}
 *            exp expression
 * @return {}
 */
tt.parRngExp = function(v, exp) {
	var map = {
		'(' : '>',
		'[' : '>='
	};
	var expArr = [];
	var m1 = {
		"{" : "(",
		"}" : ")",
		"|" : "||",
		"&" : "&&"
	};
	for (i = 0; i < exp.length; i++) {
		c = exp.charAt(i);

		if (c == '(' || c == '[') {
			compareOper1 = map[c];

			index1 = exp.indexOf(')');
			index2 = exp.indexOf(']');
			_index = index1;
			compareOper2 = '<';
			if (index1 == -1 && index2 == -1) {
				alert('expression is invalid, not found ] or )!');
				return null;
			} else if (index1 == -1 || (index1 > index2 && index2 != -1)) {
				_index = index2;
				compareOper2 = '<=';
			}
			var singleExp = exp.substring(i + 1, _index);

			var numArr = singleExp.split(',');
			numArr[0] = tt.trim(numArr[0]);

			if (numArr.length == 1) {
				numArr[1] = tt.trim(numArr[0]);
			} else if (numArr.length == 2) {
				numArr[1] = tt.trim(numArr[1]);
			} else {
				alert(singleExp + ' is error!');
				return null;
			}

			expArr.push("(");
			if (numArr[0] != '') {
				expArr.push(v);
				expArr.push(compareOper1);
				expArr.push(numArr[0]);
			}
			if (numArr[0] != '' && numArr[1] != '') {
				expArr.push(' && ');
			}
			if (numArr[1] != '') {
				expArr.push(v);
				expArr.push(compareOper2);
				expArr.push(numArr[1]);
			}

			expArr.push(")");

			exp = exp.substring(_index + 1, exp.length);
			i = 0;
			continue;
		} else if (m1[c]) {
			expArr.push(m1[c]);
		}
	}
	return expArr.join('');
};
/**
 * tt.getI18S("my name is {0}, your name is {1}",["kebo","smis"], 0);
 * tt.getI18S("my name is {1}, your name is {2}",["kebo","smis"], 1);
 */
tt.getI18S = function() {
	ret = arguments[0];
	if (arguments.length > 1) {
		si = 0; // startIndex
		if (arguments.length == 3) {
			si = arguments[2];
		}
		for (i = 0; i < arguments[1].length; i++) {
			ret = ret.replace("{" + si + "}", arguments[1][i]);
			si++;
		}
	}
	return ret;
};

/**
 * 
 * @param {}
 *            e
 * @return {} true:包含
 */
Array.prototype.ttCons = function(e) {
	i = 0;
	for (; i < this.length && this[i] != e; i++);
	return !(i == this.length);
};

tt.getStrLen = function(s) {
	var len = 0;
	var c = -1;
	for (var i = 0; i < s.length; i++) {
		c = s.charCodeAt(i);
		if (c >= 0 && c <= 128)
			len += 1;
		else
			len += 2;
	}
	return len;
};
tt.getById = function(id) {
	return document.getElementById(id);
};

/**
 * 获取元素的位置信息
 * 
 * @param {}
 *            e
 * @return {} {"t":t,'l':l,"b":b,'r':r};
 */
tt.getPos = function(e) {
	var rect = e.getBoundingClientRect();
	var scrollTop = 0;
	var scrollLeft = 0;
	var temp = e;
	while (temp = temp.offsetParent) {
		scrollTop += temp.scrollTop;
		scrollLeft += temp.scrollLeft;
	}

	var t = rect.top + scrollTop;
	var l = rect.left + scrollLeft;
	var r = rect.right + scrollLeft;
	var b = rect.bottom + scrollTop;
	return {
		"t" : t,
		'l' : l,
		"b" : b,
		'r' : r
	};
};
/**
 * 将srcE移到targetE后面
 * 
 * @param {}
 *            srcE
 * @param {}
 *            targetE
 */
tt.moveToPos = function(srcE, targetE) {
	var targetpostion = tt.getPos(targetE);
	srcE.style.zIndex = 9;
	srcE.style.position = "absolute";
	srcE.style.top = targetpostion.t - 3;// -
											// srcE.currentStyle.borderTopWidth
											// - srcE.style.marginTop;
	srcE.style.left = targetpostion.r + 8;// - srcE.currentStyle.borderLeftWidth -
										// srcE.style.marginLeft;
};
tt.getSelectedCount = function(j, es) {
	if (!es) {
		return 0;
	}
	
	var types = tt.inputType(es[j]);
	var c = 0;

	if (types.isSelect) {
		for (var i = 0; i < es[j].options.length; i++) {
			if (es[j].options[i].selected) {
				c++;
			}
		}
		return c;
	} else {
		for (var i = 0; i < es.length; i++) {
			if (es[i].checked) {
				c++;
			}
		}
		return c;
	}
	return c;

};

/**
 * 信息提示框的关闭按钮动作处理类
 * 
 * @param {}
 *            obj
 * @param {}
 *            closeObj
 */
tt.closeHandler = function(obj, closeObj) {
	this.h = function() {
		tt.rmEle(obj);
	};
};

/**
 * 
 * @param {}
 *            e element
 * @return {}
 */
tt.inputType = function(e) {
	return {
		'isSelect' : e.tagName == "SELECT",
		'isCheckbox' : e.tagName == "INPUT" && e.type == 'checkbox',
		'isRadio' : e.tagName == "INPUT" && e.type == 'radio'
	};
};

tt.setVfP = function(clrSpace) {
	tt.vf.clrSpace = clrSpace;
};