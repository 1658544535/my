<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMaker.do">创客管理</a> &gt;<a href="goEditMaker.do">编辑头像</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="editMaker.do" method="post" id="from1" enctype="multipart/form-data">
  	<input type="hidden" name="sysLoginPojo.id" id="sysLoginPojo.id" value="${sysLoginPojo.id}" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">账号：</td>
		<td><s:property value="sysLoginPojo.loginname"/></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">昵称：</td>
		<td><s:property value="sysLoginPojo.name"/></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">头像:</td>
		<td>
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userlogo/${sysLoginPojo.image}" height="100px" />
			<input type="file" class="floatLeft" name="logo" />
		</td>
	</tr>
    </table> 
    </form>
  </div>
  <div class="Btn_div">
  		<input type="button"  class="ok_btn" value="提交" id="sbutton"/>  <button type="input" class="back_btn" onclick="window.history.back()">返回</button>
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