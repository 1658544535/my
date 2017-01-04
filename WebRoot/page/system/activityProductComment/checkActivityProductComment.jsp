<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script>
/**
 * 审核通过某条
**/
function check1(url){
	if(confirm("确认要审核通过吗？")){
		MAOWU.ajax.get(url, null, checkRefreshPage1);
	}else{
		return ;
	}
}
/**
 * 审核通过后刷新
**/
function checkRefreshPage1(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("审核成功");
		location.replace(location);
	} else{
		alert("审核失败");
	}
}
/**
 * 取消审核某条
**/
function uncheck1(url){
	if(confirm("确认要取消审核吗？")){
		MAOWU.ajax.get(url, null, uncheckRefreshPage1);
	}else{
		return ;
	}
}
/**
 * 取消审核后刷新
**/
function uncheckRefreshPage1(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("取消审核成功");
		location.replace(location);
	} else{
		alert("取消审核失败");
	}
}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">活动商品评论表管理</a> &gt; <a href="#">查看</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="activityProductCommentPojo.id" id="activityProductCommentPojo.id" value="${activityProductCommentPojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">用户名：</td>
						<td>${activityProductCommentPojo.userName}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">评论时间：</td>
						<td>${activityProductCommentPojo.creatDateString}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">评论内容：</td>
						<td>${activityProductCommentPojo.content}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">晒图：</td>
						<td>
						<c:if test="${activityProductCommentPojo.img1 != null && activityProductCommentPojo.img1 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img1}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img1}"/></a></c:if>
						<c:if test="${activityProductCommentPojo.img2 != null && activityProductCommentPojo.img2 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img2}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img2}"/></a></c:if>
						<c:if test="${activityProductCommentPojo.img3 != null && activityProductCommentPojo.img3 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img3}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img3}"/></a><br /></c:if>
						<c:if test="${activityProductCommentPojo.img4 != null && activityProductCommentPojo.img4 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img4}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img4}"/></a></c:if>
						<c:if test="${activityProductCommentPojo.img5 != null && activityProductCommentPojo.img5 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img5}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img5}"/></a></c:if>
						<c:if test="${activityProductCommentPojo.img6 != null && activityProductCommentPojo.img6 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img6}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img6}"/></a><br /></c:if>
						<c:if test="${activityProductCommentPojo.img7 != null && activityProductCommentPojo.img7 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img7}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img7}"/></a></c:if>
						<c:if test="${activityProductCommentPojo.img8 != null && activityProductCommentPojo.img8 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img8}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img8}"/></a></c:if>
						<c:if test="${activityProductCommentPojo.img9 != null && activityProductCommentPojo.img9 != ''}"><a href="/upfiles/productComment/${activityProductCommentPojo.img9}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/productComment/${activityProductCommentPojo.img9}"/></a></c:if>
						</td>
					</tr>
					
				</table> 
			</form>
		</div>
		<div class="Btn_div">
			<c:if test="${activityProductCommentPojo.status == 0}"><a class="comment_btn" onclick="check1('checkActivityProductComment.do?id=${activityProductCommentPojo.id}')">审核通过</a></c:if>
			<a class="check_btn" onclick="uncheck1('uncheckActivityProductComment.do?id=${activityProductCommentPojo.id}')">审核不通过</a>
		</div>
	</div>
</body>
</html>