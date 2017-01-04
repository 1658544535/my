<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/css/spage.css"/>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">

// //修改排序
// function copyDate(id){
// 	alert("${activityProductPojo.categoryId}");
// 	$.ajax({
// 		url:"copyActivityProduct.do?activityProductPojo.categoryId="+id,
// 		success:function(data){
// 			if (data == 1) {
// 				alert("设置成功!");
// 				location.reload();
// 			} else if (data == 2) {
// 				alert("设置失败!");
// 			} else if (data == 3) {
// 				alert("请先登录");
// 			} 
// 		},
// 	});
// }
</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="productManage.do">活动商品管理</a> &gt;<a href="#"> 单品活动商品复制</a></div>
    <form action="copyActivityProduct.do" method="post" id="from1">
    	<input type="hidden" id="activityProductPojo.id" name="activityProductPojo.id" value="${activityProductPojo.id}"/>
		<div id="" style="">
			<table width="100%" border="0" class="info_table">
				<tr>
		    		<td align="right" class="picdis" width="15%" id="ca1">请选择要复制的导航类型:</td>
		            <td class="picdis" id="ca2">
						<select id="activityProductPojo.categoryId" name="activityProductPojo.categoryId" class="floatLeft">
							<option value="0">上新</option>
							<c:forEach items="${navigationList}" var="navigation">
								<option value="${navigation.categoryId}">${navigation.name}</option>
							</c:forEach>
						</select>
		    	    </td>
		       </tr>
		    </table>  
		</div>
	</form>
	<div class="Btn_div">
		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
		<input type="submit" class="ok_btn" value="复制" id="sbutton" />
	</div>
</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
		$("#sbutton").click(function() {	
			if (tt.validate()) {		
				document.getElementById("from1").submit();
			}
		});
	});
</script>