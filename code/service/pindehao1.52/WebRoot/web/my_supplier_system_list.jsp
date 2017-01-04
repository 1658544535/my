<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马首页</title>

</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>

<div class="faq-width">
<jsp:include page="my_supplier_left.jsp"></jsp:include>

	<div class="my_supplier_Product-R">
        	<div class="infor-L-List-Title">
            	<div class="infor-L-List-Title-L">消息</div>
            </div>
            <div class="infor-L-List-Txt">
            	<ul id="body">
	            	<s:iterator value="systemInfolist">
                			<li><div class="infor-L-List-Txt01-fu"><a href="infoDetailMuf.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></div><div class="infor-L-List-Txt02"><s:property value="createDateStr" /></div></li>
                	</s:iterator>
                </ul>
            </div>
         <div class="clear"></div>
        <div class="my_supplier_Product-R-page">
	    	<div class="digg" id="Pagination"></div>
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
	window.location.href="systemInfoWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>";
	return false;
}

</script>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
