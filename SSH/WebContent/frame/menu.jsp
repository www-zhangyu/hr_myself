<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<!--把下面代码加到<head>与</head>之间-->
 
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

html,body {

	text-align: center;
}

a:link,a:visited {
	text-decoration: none;
}
/*菜单个性设置*/

 ul {
	list-style: none;
	text-align: left;
}


/*一级菜单*/
.menu li.level1 a {
	display: block;
	padding:0px;
	margin:0px;
	line-height: 30px;
	text-align: left;
	height:45px;
	font-size: 16px;
	color: #F0F1F2;
	/* background-color: #222222; */
	padding-top: 10px;
	padding-left:15px;
	border-bottom:0.5px solid #41586E; 
}
/*一级菜单两态样式，供JS调用*/
.menu li.level1 a.hove {
	background-position: left -29px;
	color: #fff;
}

.menu li.level1 a.cur {
	background-position: left -58px;
	color: #fff;
}
/*二级菜单*/
.menu li ul {
	overflow: hidden;
}

.menu li ul.level2 {
	width: 100%;
	display: none;
}

.menu li ul.level2 li {
float:right;
	height: 45px;
	line-height: 30px;
	width:100%;
}
#level2_img{
	height: 45px;
	width: 54px;
}
.menu li ul.level2 li a {
	float:right;
	display: block;
	height: 45px;
	/* width:265.6px; */
	width:100%;
	line-height: 26px;
	/* background: url(${pageContext.request.contextPath}/public/img/level2.png);  */
	background:#344759;
	text-align:left;
	color: #344759;
	font-size:12px;
	color:#F0F1F2;
	border-buttom: 0.5px #41586E solid;
	overflow: hidden;
	z-index: 1;
}

/*二级菜单两态样式，供JS调用*/
.menu li ul.level2 li a.hove1 {
	  font-size:14px;
	  background:#3D5368;
}
ul, ol {
    margin: 0;
    padding: 0;
}
</style>
<script type="text/javascript">
$(function(){
	$(".menu li ul.level li a").click(function(){
		$(".menu li ul.level li a").removeClass("hover1");
		$(this).addClass("hover1");
	})
})
</script>
<script language="javascript" type="text/javascript">
	
	function getElementsByClassName(searchClass, node, tag) {
		if (document.getElementsByClassName) {
			return document.getElementsByClassName(searchClass);
		} else {
			node = node || document;
			tag = tag || "*";
			var classes = searchClass.split(" "), elements = (tag === "*" && node.all) ? node.all
					: node.getElementsByTagName(tag), patterns = [], returnElements = [], current, match;
			var i = classes.length;
			while (--i >= 0) {
				patterns.push(new RegExp("(^|\\s)" + classes[i] + "(\\s|$)"));
			}
			var j = elements.length;
			while (--j >= 0) {
				current = elements[j];
				match = false;
				for ( var k = 0, kl = patterns.length; k < kl; k++) {
					match = patterns[k].test(current.className);
					if (!match)
						break;
				}
				if (match)
					returnElements.push(current);
			}
			return returnElements;
		}
	}
	/*
	 通用加载函数，页面中如果要用到onload函数在窗体一加载时就执行的代码，可以直接添加到这个函数，否则会引起多个onload函数的执行冲突
	 带参数的调用方法：addLoadEvent(new Function("refurFrame('单词管理');"));
	 */
	function addLoadEvent(func) {
		var oldonload = window.onload;
		if (typeof window.onload != 'function') {
			window.onload = func;
		} else {
			window.onload = function() {
				oldonload();
				func();
			};
		}
	}
	/*判断是否有className的函数，调用例子为：o.className=o.addClass(o,"normal");*/
	function hasClass(element, className) {
		var reg = new RegExp('(\\s|^)' + className + '(\\s|$)');
		return element.className.match(reg);
	}
	/*动态添加className的函数，调用例子为：addClass(document.getElementById("test"), "test");*/
	function addClass(element, className) {
		if (!this.hasClass(element, className)) {
			element.className += " " + className;/*如果有多个样式叠加，则用这种方式，如class="style1 style2"*/
			/*element.className = className; */
		}
	}
	/*动态删除className的函数，调用例子为：removeClass(document.getElementById("test"), "test")*/
	function removeClass(element, className) {
		if (hasClass(element, className)) {
			var reg = new RegExp('(\\s|^)' + className + '(\\s|$)');
			element.className = element.className.replace(reg, ' ');
		}
	}
	/*获取第一个子节点的函数，兼容FF*/
	function getFirstChild(obj) {
		var firstDIV;
		for (var i = 0; i < obj.childNodes.length; i++) {
			if (obj.childNodes[i].nodeType == 1) {
				firstDIV = obj.childNodes[i];
				return firstDIV;
			} else
				continue;
		}
	}
	addLoadEvent(new Function("hovermenu('level1','level2','hove','cur');"));
	//menu代表菜单总的ID名称
	//level1代表一级菜单项的父容器，level2代表二级菜单项的父容器
	//后面的三个参数style1、style2、style3分别代表鼠标移入、移出、点击的三态样式名
	addLoadEvent(new Function("submenu('level2','hove1','hove1');"));
	/*滑动显隐菜单列表*/
	var temp;
	var temp1;
	function hovermenu(cssName1, cssName2, style2, style3) {
		var ArrLinks = getElementsByClassName(cssName1);//一级菜单父容器的数组
		var ArrLevel = new Array();//第一级菜单的数组
		for ( var i = 0; i < ArrLinks.length; i++) {
			var curobj = getFirstChild(ArrLinks[i]);//获得第一个子对象
			ArrLevel.push(curobj);
		}
		var ArrDivs = getElementsByClassName(cssName2);//二级菜单的父容器数组，要显示的二级菜单容器
		for ( var i = 0; i < ArrLinks.length; i++) {
			var obj = getFirstChild(ArrLinks[i]);//获得第一个子对象
			obj.index = i;
			obj.onmouseover = function() {
				overme(this, ArrLevel, style2, temp);
			};
			obj.onmouseout = function() {
				outme(this, ArrLevel, style2, temp);
			};
			obj.onclick = function() {
				clickme(this, ArrLinks, ArrDivs, style2, style3, temp);
			};
			obj.onfocus = function() {
				this.blur();
			};//去掉虚线框
		}
	}
	//二级菜单绑定事件
	function submenu(cssName2, style2, style3) {
		var ArrLinks = getElementsByClassName(cssName2);//一级菜单父容器的数组
		var ArrLevel = new Array();//第一级菜单的数组
		for ( var i = 0; i < ArrLinks.length; i++) {
			var sublinks = ArrLinks[i].getElementsByTagName('A');//获得第一个子对象
			for ( var m = 0; m < sublinks.length; m++) {
				ArrLevel.push(sublinks[m]);
			}
		}
		for ( var i = 0; i < ArrLevel.length; i++) {
			var obj = ArrLevel[i];
			obj.index = i;
			obj.onmouseover = function() {
				overme(this, ArrLevel, style2, temp1);
			};
			obj.onmouseout = function() {
				outme(this, ArrLevel, style2, temp1);
			};
			obj.onclick = function() {
				subclick(this, ArrLevel, style2, style3, temp1);
			};
			obj.onfocus = function() {
				this.blur();
			};//去掉虚线框
		}
	}
	function overme(o, links, style2, state) {
		if (state != o.index) {
			addClass(o, style2);
		}
	}
	function outme(o, links, style2, state) {
		if (state != o.index) {
			removeClass(o, style2);
		}
		;
	}
	//一级菜单点击事件
	function clickme(o, links, divs, style2, style3, state) {
		//要判断是否有子菜单项
		var objUl = links[o.index].getElementsByTagName('UL');
		$(objUl).slideToggle("slow");
		if (state != o.index) {
			temp = o.index;
		}
		for ( var i = 0; i < links.length; i++) {
			var curobj = getFirstChild(links[i]);//获得第一个子对象
			removeClass(curobj, style3);
			removeClass(curobj, style2);
		}
		addClass(o, style3);
	}
	//二级菜单点击事件
	function subclick(o, links, style2, style3, state) {
		if (state != o.index) {
			for ( var i = 0; i < links.length; i++) {
				removeClass(links[i], style2);
			}
			temp1 = o.index;
			addClass(o, style2);
		}
	}
</script>
</head>
<body>
	<!--把下面代码加到<body>与</body>之间-->
		<ul class="menu" id="menu" background-color: #222222;">
		</ul>

</body>
</html>