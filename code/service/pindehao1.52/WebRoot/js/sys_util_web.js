// comm.js for all page , need jquery 1.6+
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};
String.prototype.left = function(n) {
	return this.length <= n ? this : this.substring(0, n);
};// 取x字串的左 n 位
String.prototype.right = function(n) {
	return this.length <= n ? this : this.substr(this.length - n);
};// 取字串的右 n 位
String.prototype.startWith = function(c) {
	return c && this.left(c.length) == c;
};// 是否以c开头
String.prototype.endWith = function(c) {
	return c && this.right(c.length) == c;
};// 是否以c结束

function Deprecated() {/* alert('@Deprecated') */
}

/*
 * 基于jQuery 公用工具
 */
var MAOWU = {};
// 低级方法
MAOWU.lang = {
	isNumber : function(x) {
		return !isNaN(x);
	},// 检测是否是数字
	isString : function(x) {
		return typeof (x) == "string";
	},// 是否是字符串
	isObject : function(x) {
		return typeof (x) == "object";
	},
	isBoolean : function(x) {
		return typeof (x) == "boolean";
	},
	isArray : function(x) {
		return Object.prototype.toString.call(x) === '[object Array]';
	},
	// 检查目标值是否是空的,null,undefined,'',{} 都返回true
	isEmpty : function(x) {
		if (!x || (x + '').trim() == '' || (x + '').trim() == 'undefined'
				|| (x + '').trim() == 'null')
			return true;
		if (this.isObject(x)) {
			for ( var n in x)
				return false;
			return true;
		} else if (this.isArray(x))
			return x.length == 0;
		return false;
	},

	left : function(x, n) {
		return this.xstring(x, '').left(n);
	},// 取x字串的左 n 位
	right : function(x, n) {
		return this.xstring(x, '').right(n);
	},// 取字串的右 n 位
	cutLeft : function(x, n) {
		return this.isEmpty(x = x + '') ? '' : (x.length <= n ? '' : (x
				.substr(n)));
	},// 删除x左边n个字符
	startWith : function(x, y) {
		return x && x.startWith(y);
	},// x是否以y开头
	endWith : function(x, y) {
		return x && x.endWith(y);
	},
	// 如果给定值o是空值,则返回给定默认值v
	xobj : function(o, v) {
		return o ? o : v;
	},
	xstring : function(o, v) {
		return o ? (o + '') : v;
	},
	xint : function(o, v) {
		return o ? parseInt(o) : v;
	},
	xfloat : function(o, v) {
		this.isNumber(o) ? Number(o) : o;
	},
	// 字节长度, 所有全角2个字节计算
	byteLength : function(x) {
		var i = l = 0, y = x + '';
		for (; i < y.length; i++)
			l += y.charCodeAt(i) > 255 ? 2 : 1;
		return l;
	},
	toString : function(o) {
		var rs = "";
		var level = arguments.length > 1 ? arguments[1] : 1;
		if (level++ > 10)
			return rs;
		if (o == undefined) {
			rs += "undefined";
		} else if (o.nodeType || o.jquery) {
			rs += "''";// 如果htmlDom 或是 jQuery 对象, 则放弃
		} else if (Object.prototype.toString.call(o) === '[object Array]') {
			rs += "[";
			for ( var i = 0; i < o.length; i++) {
				if (i > 0)
					rs += ",";
				rs += this.toString(o[i], level);
			}
			rs += "]";
			return rs;
		} else if (typeof (o) == 'object') {
			rs += "{";
			var j = 0;
			for ( var p in o) {
				if (j++ > 0)
					rs += ",";
				var s = typeof (o[p]) == 'string' ? "'" : "";
				rs += "'" + p + "' : " + s + this.toString(o[p], level) + s;
			}
			rs += "}";
			return rs;
		} else {
			return (o + '').replace("'", "\'");
		}
		return rs;
	},
	toString2 : function(o) {
		var rs = "";
		var level = arguments.length > 1 ? arguments[1] : 1;
		if (level++ > 10)
			return rs;
		if (o == undefined) {
			rs += "undefined";
		} else if (o.nodeType || o.jquery) {
			rs += this.getTab(level) + "''";// 如果htmlDom 或是 jQuery 对象, 则放弃
		} else if (Object.prototype.toString.call(o) === '[object Array]') {
			rs += "[\n";
			for ( var i = 0; i < o.length; i++) {
				if (i > 0)
					rs += ",\n";
				rs += this.getTab(level + 1) + this.toString2(o[i], level) + '';
			}
			rs += '\n' + this.getTab(level) + "]";
			return rs;
		} else if (typeof (o) == 'object') {
			rs += "{\n";
			var j = 0;
			for ( var p in o) {
				if (j++ > 0)
					rs += ",\n";
				var s = typeof (o[p]) == 'string' ? "'" : "";
				rs += this.getTab(level + 1) + "'" + p + "' : " + s
						+ this.toString2(o[p], level) + s + '';
			}
			rs += '\n' + this.getTab(level) + "}";
			return rs;
		} else {
			return (o + '').replace("'", "\'");
		}
		return rs;
	},
	getTab : function(n) {
		return this.left('\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t', n);
	}
};
// 常用正则
MAOWU.reg = {
	phone : /^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$/,
	email : /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,
	isPhone : function(v) {
		return MAOWU.reg.phone.test($.trim(v + ''));
	},// 是不是手机号
	isEmail : function(v) {
		return MAOWU.reg.email.test($.trim(v + ''));
	},
	hrefSpliter : /^(?:([^:\/?#]+):)?(?:\/\/(?:([^\/?#]*)@)?([^\/?#:@]*)(?::([0-9]+))?)?([^?#]+)?(?:\?([^#]*))?(?:#(.*))?$/,
	nothing : 'at all'
};
// 高级工具包
MAOWU.util = {
	press : function(e, keyName, callback) {
		if (!keyName || !callback)
			return false;
		var key = e.keyCode || e.which;
		var map = {
			'backspace' : 8,
			'tab' : 9,
			'enter' : 13,
			'shift' : 16,
			'ctrl' : 17,
			'alt' : 18,
			'capslock' : 20,
			'esc' : 32
		};
		keyName = keyName.toLowerCase();// 目前只支持小写检测
		if ((keyName.length == 1 && key == keyName.charCodeAt())
				|| (map[keyName] == key)) {
			setTimeout(function() {
				callback();
			}, 10);
		}
		return true;
	},
	enter : function(e, callback) {
		return this.press(e, 'Enter', callback);
	},
	/* 取得document element的绝对位置,相对与文档左上角 */
	getOffSet : function(o) {
		var tag = o, l = 0, t = 0;
		while (tag) {
			l += tag.offsetLeft + tag.clientLeft;
			t += tag.offsetTop + tag.clientTop;
			tag = tag.offsetParent;
		}
		return {
			left : l,
			top : t
		};
	}
};

// 导航管理
MAOWU.nav = {
	// 解析链接 return
	// ['SCHEME','USER_INFO','DOMAIN','PORT','PATH','QUERY_DATA','FRAGMENT']
	splitRe_ : MAOWU.reg.hrefSpliter,
	split : function(uri) {
		return uri.match(this.splitRe_);
	},
	path : undefined,
	HREF : {
		SCHEME : '',
		DOMAIN : '',
		PORT : '',
		PATH : '',
		QUERY_DATA : '',
		FRAGMENT : '',
		HOST : ''
	},
	addHeadSlash : function(path) {
		path = '\/' + MAOWU.lang.xstring(path, '').trim();
		return path.startWith('\/\/') ? path.substr(1) : path;
	},
	addEndSlash : function(path) {
		return (MAOWU.lang.xstring(path, '').trim() + '\/').replace(/\/+?$/,
				'\/');
	},
	// 常用方法, 如传入 http://www.xxx.com:8080/abc/def.html?g=hij 返回: /abc/def.html
	getPath : function(href) {
		if (!href)
			return '';
		var m = href.match(this.splitRe_);
		return m && m[5] ? m[5] : '';
	},
	getSubPath : function(path, level) {
		level = level ? level : 1;// 几级目录
		if (!path)
			return '';
		path = this.addHeadSlash(path.trim());
		var arr = path.split('\/');
		return arr[level] ? arr[level] : '';
	},
	parse : function(href) {
		if (!href)
			return {};
		var obj = {}, m = href.match(this.splitRe_);
		$.each([ 'SCHEME', 'USER_INFO', 'DOMAIN', 'PORT', 'PATH', 'QUERY_DATA',
				'FRAGMENT' ], function(i, n) {
			obj[n] = m[i + 1];
		});
		obj['HOST'] = obj['SCHEME'] + '://' + obj['DOMAIN']
				+ (obj['PORT'] ? (':' + obj['PORT']) : '') + '/';
		return obj;
	},
	init : function() {
		this.HREF = this.parse(window.location + '');
	},

	// 一级栏目
	level_1 : function() {
		// var _this = this;
		// head部分菜单menu的JS效果
		var $div_menu_ul_li = $("div.menu ul li");
		$div_menu_ul_li.click(function() {
			$(this).addClass("selected").siblings().removeClass("selected");
		});
	}
};
MAOWU.nav.init();// prepare sth
$(MAOWU.nav.level_1);

/* cookie 操作 */
MAOWU.cookie = {
	set2 : function(cookieName, cookieValue, config) {
		config = config || {};
		this.set(cookieName, cookieValue, config.path, config.domain,
				config.secure, config.expire);
	},
	set : function(cookieName, cookieValue, path, domain, secure, expire) {
		var expires = new Date();
		expires.setTime(expires.getTime() + (expire ? expire : 9000000000));
		document.cookie = escape(cookieName)
				+ '='
				+ escape(cookieValue)
				+ (expires ? '; expires=' + expires.toGMTString() : '')
				+ (path ? ('; path=' + path) : ('; path=' + (g_basicUrl
						.endWith('/') ? g_basicUrl : (g_basicUrl + "/"))))
				+ (domain ? '; domain=' + domain : '')
				+ (secure ? '; secure' : '');
	},
	get : function(name) {
		var cookie_start = document.cookie.indexOf(name);
		var cookie_end = document.cookie.indexOf(";", cookie_start);
		return cookie_start == -1 ? '' : unescape(document.cookie.substring(
				cookie_start + name.length + 1,
				(cookie_end > cookie_start ? cookie_end
						: document.cookie.length)));
	},
	del : function(cookieName, cookieValue, path, domain, secure) {
		cookieValue = "hello";
		var expires = new Date();
		expires.setTime(expires.getTime() - 10);
		document.cookie = escape(cookieName)
				+ '='
				+ escape(cookieValue)
				+ (expires ? '; expires=' + expires.toGMTString() : '')
				+ (path ? '; path=' + path : ('; path=' + (g_basicUrl
						.endWith('/') ? g_basicUrl : (g_basicUrl + "/"))))
				+ (domain ? '; domain=' + domain : '')
				+ (secure ? '; secure' : '');
	}
};
/*
 * MAWO ajax操作
 */
/*******************************************************************************
 * $.ajax带json数据的异步请求
 ******************************************************************************/
MAOWU.ajax = {
	/***************************************************************************
	 * data : { key1 : value1, key2 : value2}, 若data = {} 则 url 做拼接参数请求
	 **************************************************************************/
	post : function(url, data, callBack) {
		$.ajax({
			url : url,// 跳转到 action
			data : data,
			type : 'post',
			cache : false,
			async : true, // 默认为true 异步
			dataType : 'json',
			success : function(result) {
				callBack(result);
			},
			error : function() {
				alert("error");
			}
		});

	},
	/***************************************************************************
	 * data : { key1 : value1, key2 : value2}||'name1=' +
	 * value1+'&name2='+value2, 若data = {} 则 url 做拼接参数请求
	 **************************************************************************/
	get : function(url, data, callBack) {
		$.ajax({
			url : url,// 跳转到 action
			data : data,
			type : 'get',
			cache : false,
			async : false, // 默认为true 异步
			dataType : 'json',
			success : function(result) {
				callBack(result);
			},
			error : function() {
				alert("error");
			}
		});

	},
	/***************************************************************************
	 * $.ajax序列化表格内容为字符串的异步请求
	 **************************************************************************/
	postForm : function(url, formId, callBack) {
		var formParam = $("#" + formId + "").serialize();
		$.ajax({
			type : 'post',
			url : url,
			data : formParam,
			cache : false,
			async : false, // 默认为true 异步
			dataType : 'json',
			beforeSend:function(){
				$("#body").html("");
			    $("#body").append("<img align='center' src='"+ctx+"/image/loading.gif' alt='正在加载...' />");
			},
			success : function(result) {
				callBack(result);
			},
			error : function() {
				alert("error");
			}

		});
	},
	/**
	 * 内置UI表单ajax提交方式
	 */
	postUIform : function(formId, callBack) {
		$("#" + formId + "").form({
			
			success : function(result) {
				callBack(result);
			},
			error : function() {
				alert("error");
			}
		});

	}

};
var pageSize = 10;
var pageNo = 0;
var flag = 0;
var request_url = "#";
MAOWU.page = {
	init : function(rowCount, url,page_size) {
		if(flag==0){
		resetPageCount(pagecount);
		}
		flag =1;
		request_url = url;
		initData(pageNo);
		$("#Pagination").pagination(rowCount, {
			current_page : pageNo,
			items_per_page : page_size,
			link_to : '#',
			num_display_entries : 4,
			next_text : '>',
			last_text: ">|",
			next_show_always : true,
			prev_text : '<',
			first_text: "|<",
			prev_show_always : true,
			num_edge_entries : 2,
			ellipse_text : '...',
			callback : pageselectCallback,
			load_first_page : function() {
				return false;
			}
		});
		function pageselectCallback(pageindex, jq) {
			initData(pageindex);
			return false;
		}
		function initData(pageNo) {
			$("#pageNo").attr("value",pageNo+1);
			var parameter = $("#sysform").serializeArray();
			$.getJSON(request_url, parameter, function(data) {
				var c = eval("(" + data + ")");
				$("#body").html("");
				$.each(c, installPage);
			});
		}
	}
};

var rowCount;
function queryData(getCountUrl, getDataUrl) {
	$("#pageNo").attr("value", 0);
	MAOWU.ajax.postForm(getCountUrl, 'sysform', resetRowCount);
	MAOWU.page.init(rowCount, getDataUrl);
}
function resetRowCount(rowcount) {
	$("#rowcount").html(rowcount);
	rowCount = rowcount;
	resetPageCount(rowcount);
}
function resetPageCount(rowcount){
	var left = parseInt(rowcount%pageSize==0?0:1);
	var muty = parseInt(rowcount/pageSize);
	var pageCount =left+muty;
	$("#pagecount").html(pageCount);
}





// 分页回调
// 分页
function handlePaginationClick(page_index, jq) {
	var name = $("#name").val();
	var mobile = $("#mobile").val();
	var company = $("#company").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var allpass = $("input[type='checkbox'][name='allpass']:checked");
	var str = "";
	for ( var i = 0; i < allpass.length; i++) {
		str += allpass[i].value + ",";
	}
	if (str != "") {
		str = str.substring(0, str.length - 1);
		$("#isPass").val(str);
	}
	url = "${ctx}/user/searchByPage?name=" + encodeURI(encodeURI(name))
			+ "&moblie=" + mobile + "&company=" + encodeURI(encodeURI(company))
			+ "&startTime=" + startTime + "&endTime=" + endTime + "&start="
			+ (page_index + 1) + "&limit=" + 20 + "&isPass="
			+ $("#isPass").val() + "";
	url = "platformStatis.do";
	$(location).attr('href', url);
	return false;
}

// 编码字节
// @Deprecated
function esca(tag) {
	var a = encodeURIComponent(tag);
	window.location.href = "/maowu/xx.do?par=" + a;
}


//调整js窗口
var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
function reinitIframe(){  
	var iframe = document.getElementById("iframepage");  
	try{  
	  var bHeight = iframe.contentWindow.document.body.scrollHeight;  
	  var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
	  var height = Math.max(bHeight, dHeight);  
	  iframe.height = height;  
	}catch (ex){}  
}  
function reinitIframeEND(){  
	var iframe = document.getElementById("iframepage");  
	try{  
	  var bHeight = iframe.contentWindow.document.body.scrollHeight;  
	  var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
	  var height = Math.max(bHeight, dHeight);  
	  iframe.height = height;  
	}catch (ex){}  
	//停止定时  
	window.clearInterval(timer1);  
}  
