<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>
	<body>
		<jsp:include page="sellerHeader.jsp"></jsp:include>
			<div id="content" class="pure-g support-wrapper wrapper">
                <div class="bo-side pure-u-1-5 view-LeftPanel">
                    <div class="main-title">
                        <a href="goHelpWeb.do">
                            <i class="iconfont">&#xf00b6;</i>
                            <span>
                                商家帮助中心
                            </span>
                        </a>
                    </div>
					<c:forEach items="${helpTypePojos }" var="h">
                    <h5 class="no-border-top">
                        <i class="iconfont">&#xf016d;</i>
                        ${h.name }
                    </h5>
                    <ul>
                    	<c:forEach items="${h.helpTypeChildPojoList }" var="h2">
                        <li>
                            <a href="#" onclick="findText(${h2.id})">
                                ${h2.name }
                            </a>
                        </li>
                    	</c:forEach>
                    </ul>
                	</c:forEach>
                </div>
                <div class="bo-main pure-u-4-5">
                	<h3 id="textTitle">
                		商家帮助中心
                    </h3>
                    <div class="main-content main-contents" id="textBox" >
                     <c:forEach items="${helpTypePojos }" var="h">
                     <ul class="min-list clearfix">
                        <li>
                            <h5> ${h.name }</h5>
                            <ul>
                                <c:forEach items="${h.helpTypeChildPojoList }" var="h2">
                                     <li><a href="#" onclick="findText(${h2.id})"><span class="point">•</span> ${h2.name }</a></li>
                                </c:forEach>
                            </ul>                          
                        </li>
                    </ul>
                  </c:forEach>
		            </div>
				</div>
			</div>
		<jsp:include page="sellerFooter.jsp"></jsp:include>
        <script>
			$(function(){
				$(".no-border-top").on("click",function(){
					$(".no-border-top,ul").removeClass("current");
					$(this).addClass("current");
					$(this).next("ul").addClass("current");
				});
			});
		</script>
	</body>
</html>
<script>
	$(function() {
		if("${result}" != null && "${result}" != ""){
			$("#textTitle").html("${result[0]}");
			$("#textBox").html("${result[1]}");
		}
	});
	
	function findText(id){
		$("#textBox").html();
		$.ajax({
			type: "post",
			url: "doHelpWeb.do?helpPojo.id="+id,
			dataType: 'json',
			success: function (msg) {
				$("#textTitle").html(msg[0]);
				$("#textBox").html(msg[1]);
			}
		});
	}
</script>