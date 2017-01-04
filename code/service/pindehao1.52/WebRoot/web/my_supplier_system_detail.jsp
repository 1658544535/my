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
<title>淘竹马玩具分销平台</title>

</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>

<div class="faq-width">
<jsp:include page="my_supplier_left.jsp"></jsp:include>

	<div class="my_supplier_Product-R">
        	<div class="infor-L-List-Title">
            	<div class="infor-L-List-Title-L">消息详情</div>
            </div>
            <div  style="padding:10px 40px;">
	            <span style=" display:block; padding-top:20px; line-height:30px;font-size:18px; font-weight:bold; margin:0 auto; text-align:center;"><s:property value="infoPojo.title"/></span>
	            <span style="text-align:center; display:block;">来源：<s:property value="infoPojo.author"/></span>
	            <s:property value="infoPojo.content" escape="false"/>
        		<s:if test="infoPojo.preTittle != null"><div class="top_con" style="margin-top:15px;" ><a href="infoDetailMuf.do?infoPojo.id=<s:property value="infoPojo.preId"/>" >上一篇：<s:property value="infoPojo.preTittle"/></a></div></s:if>
                <s:if test="infoPojo.nextTittle != null"><div class="top_con"><a href="infoDetailMuf.do?infoPojo.id=<s:property value="infoPojo.nextId"/>" >下一篇：<s:property value="infoPojo.nextTittle"/></a></div></s:if>
        	</div>
     </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
