<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">拼得客-用户信息表管理</a> &gt; <a href="#">查看</a>
		</div>
		<div class="h15"></div>
		<div>
			<c:if test="${userPindekeInfoPojo.status == 1}">
			<a class="Add_btn" href="goPindekeUserDealLog.do?userDealLogPojo.userId=${userPindekeInfoPojo.userId}" >查看用户交易列表</a>
			</c:if>
			<form action="updateUserPindekeInfo.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="userPindekeInfoPojo.id" id="userPindekeInfoPojo.id" value="${userPindekeInfoPojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">真实姓名：</td>
						<td>${userPindekeInfoPojo.name}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">手机号码：</td>
						<td>${userPindekeInfoPojo.phone}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">身份证号码：</td>
						<td>${userPindekeInfoPojo.cardNo}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">推广渠道：</td>
						<td>${userPindekeInfoPojo.extendChannel}</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">推广证明：</td>
						<td>
						<c:if test="${userPindekeInfoPojo.extendImg1 != null && userPindekeInfoPojo.extendImg1 != ''}"><a href="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg1}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg1}"/></a></c:if>  
						<c:if test="${userPindekeInfoPojo.extendImg2 != null && userPindekeInfoPojo.extendImg2 != ''}"><a href="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg2}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg2}"/></a></c:if>  
						<c:if test="${userPindekeInfoPojo.extendImg3 != null && userPindekeInfoPojo.extendImg3 != ''}"><a href="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg3}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg3}"/></a></c:if>
						<c:if test="${userPindekeInfoPojo.extendImg4 != null && userPindekeInfoPojo.extendImg4 != ''}"><a href="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg4}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg4}"/></a></c:if>  
						<c:if test="${userPindekeInfoPojo.extendImg5 != null && userPindekeInfoPojo.extendImg5 != ''}"><a href="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg5}" target="_Blank"><img style="width:200px; height:200px" src="/upfiles/userpindeke/${userPindekeInfoPojo.extendImg5}"/></a></c:if>
						</td>
					</tr>
				</table> 
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
		</div>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
</script>