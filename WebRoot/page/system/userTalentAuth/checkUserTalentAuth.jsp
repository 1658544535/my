<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript">
function check(val){
		if(confirm("确认要让此达人审核成功吗？")){
			var url = "checkUserTalentAuth.do?userTalentAuthPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要让此达人审核失败吗？")){
			var url = "uncheckUserTalentAuth.do?userTalentAuthPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
</script>
</head>
<body>
<div class="sub_wrap">
	<div class="s_nav">
		<a>达人管理</a> &gt; <a href="userTalentAuth.do">达人管理</a> &gt; <a href="goCheckUserTalentAuth.do?userTalentAuthPojo.id="${userTalentAuthPojo.id}>标签编辑</a>
	</div>
	<div class="h15"></div>
	<div>
			<input type="hidden" name="userTalentAuthPojo.id" id="userTalentAuthPojo.id" value="${userTalentAuthPojo.id}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
				<tr>
	    			<td align="right" class="grey" width="15%">用户账号：</td>
					<td>
					${userTalentAuthPojo.loginname}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">用户昵称：</td>
					<td>
					${userTalentAuthPojo.userName}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">已认证平台：</td>
					<td>
					${userTalentAuthPojo.platform}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">平台用户名：</td>
					<td>
					${userTalentAuthPojo.platformUserName}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">职业身份/身份信息：</td>
					<td>
					${userTalentAuthPojo.identity}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">粉丝数：</td>
					<td>
					${userTalentAuthPojo.fansNumName}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">跨平台能力：</td>
					<td>
					${userTalentAuthPojo.crossPlatform}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">日内容产量：</td>
					<td>
					${userTalentAuthPojo.contentOutPerName}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">日原创内容产量：</td>
					<td>
					${userTalentAuthPojo.origContentOutPerName}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">合作同类平台名称：</td>
					<td>
					${userTalentAuthPojo.coopSimilarPlat}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">合作商品品牌名称：</td>
					<td>
					${userTalentAuthPojo.coopBrand}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">其他平台商业化合作模式：</td>
					<td>
					${userTalentAuthPojo.commercialType}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">样稿标题：</td>
					<td>
					${userTalentAuthPojo.sampleTitle1}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">样稿链接：</td>
					<td>
					<a target="_blank" href="${userTalentAuthPojo.sampleUrl1}">${userTalentAuthPojo.sampleUrl1}</a>
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">申请时间：</td>
					<td>
					${userTalentAuthPojo.createDateStr}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">真实姓名：</td>
					<td>
					${userTalentAuthPojo.realName}
				    </td>
				</tr>
				<tr>
	    			<td align="right" class="grey" width="15%">手持身份证照片：</td>
					<td>
					<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userTalentAuth/${userTalentAuthPojo.idCardImage}" height="100px" />
				    </td>
				</tr>
			</table> 		
	</div>
	<div class="Btn_div">
		
		<s:if test= "status == 1">
			<input type="button"  class="ok_btn" value="审核成功" onclick="check(${userTalentAuthPojo.id})"/>
		</s:if>
		<s:elseif test= "status == 2">
			<input type="button"  class="ok_btn" value="审核失败" onclick="uncheck(${userTalentAuthPojo.id})"/>
		</s:elseif>	
		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>	
	</div>
</div>
</body>
<script>
	$(document).ready(function() {
		$("#sbutton").click(function(){	
				document.getElementById("from1").submit();					
		});
	});
</script>
</html>
