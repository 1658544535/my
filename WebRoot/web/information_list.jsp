<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马分销平台</title>
<style type="text/css">
<!--
.STYLE2 {color: #FF0000; font-weight: bold; }
.STYLE3 {color: #FF0000; font-weight: bold; font-size: 18px; }
-->
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<a href="goIndexWeb.do"><div class="logo"></div></a>
	<form action="infoList.do" id="idform" method="post">
	<div class="search"><input id="tittle" name="tittle" type="text" value="搜索 资讯" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 资讯'}" class="search-input"/><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form>    
</div> 

<div class="clear"></div>

<div class="shopping_Cart clearfix">
	<div style="width:100%; height:40px; background:#fff; font-size:14px; text-indent:10px; border:1px solid #dfdfdf;line-height:40px; ">您当前位置：&nbsp;<a href="infoPageList.do">行业资讯</a>&nbsp;>>&nbsp;列表</div>
	<div class="information_left">
		<div class="my_supplier-R-Part01-title" >&nbsp;市场动态</div>
        <div class="my_supplier-R-Part01-list" style="width:222px; height:180px;">
        	<ul  class="shop_information">
        	<s:iterator value="infoList">
                			<li><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></li>
                	</s:iterator>
            </ul>
            <div class="infor-L-List-Title-R"><a  href="infoList.do?type=2">更多</a></div>   
        </div>
		<div class="my_supplier-R-Part01-title" style="margin-top:15px;" >&nbsp;行业新闻</div>
        <div class="my_supplier-R-Part01-list" style="width:222px; height:180px;">
        	<ul  class="shop_information">
        	<s:iterator value="infoPojos">
        	<li><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></li>
        	</s:iterator>
            </ul>
            <div class="infor-L-List-Title-R"><a href="infoList.do?type=1">更多</a></div>
        </div>
	</div>
	
	 <div class="infor-R-fu">
        <div class="infor-L-List-fu">
        	<div class="infor-L-List-Title">
            	<div class="infor-L-List-Title-L">标题</div>
            </div>
            <div class="infor-L-List-Txt">
            	<ul id="body">
	            	<s:iterator value="pageInfoList">
                			<li><div class="infor-L-List-Txt01-fu"><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></div><div class="infor-L-List-Txt02"><s:property value="createDateStr" /></div></li>
                	</s:iterator>
                </ul>
            </div>
        </div>
         <div class="clear"></div>
        <div class="shop-page">
        	<div class="digg" id="Pagination"></div>
		</div>
        </div>
        
    </div>
    
    
    </div>
	
</div>

<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:15,	//每页显示数量
		next_text : '>',
		prev_text : '<',
		first_text: "|<",
		last_text: ">|",
		num_edge_entries: 2,
		num_display_entries: 4,
		prev_show_always : true,
		next_show_always : true,
		callback: pageselectCallback,
		load_first_page : function() {
			return false;
		}
	});
});

function pageselectCallback(pageindex, jq) {
	var pageNo = pageindex+1;
	window.location.href="infoList.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&type=${type}";
	return false;
}

</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
