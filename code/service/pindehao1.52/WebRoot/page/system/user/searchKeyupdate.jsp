<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>商品搜索管理</a> &gt; <a href="searchKey.do" >用户搜索关键字记录</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateSearchKey.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="searchKey.id" id="searchKeyId" value="${searchKeyPojo.id}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">关键词:</td>	
        <td width="35%">
        <input type="text" name="searchKey.keyword"  value="${searchKeyPojo.keyword}" class="floatLeft" id="ticketName"><span id="keyword"></span></td>
        
        <td align="right" class="grey"  width="15%">类型:</td>
        <td width="35%"><select name="searchKey.type" id="ticketType"  class="floatLeft">
        						<c:forEach items="${type}" var="type">
										<option value="${type.value}"<c:if test="${searchKeyPojo.type==type.value}">selected="selected" </c:if>>${type.name}</option>
								</c:forEach>
				    </select></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">次数:</td>	
        <td width="35%">
        <input type="text" name="searchKey.hits"  value="${searchKeyPojo.hits}" class="floatLeft" id="ticketName"><span id="hits"></span></td>
           <td align="right" class="grey" width="15%">排序:</td>	
        <td width="35%">
        <input type="text" name="searchKey.displayOrder"  value="${searchKeyPojo.displayOrder}" class="floatLeft" id="ticketName"><span id="displayOrder"></span></td>
      </tr>
      
           
    </table>
  
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  </div>
</div>

</body>
</html>


<script>
var displayOrder =new tt.Field(" 排序 ","searchKey.displayOrder").setMsgId("displayOrder");
var hits =new tt.Field(" 商品规格 ","searchKey.hits").setMsgId("hits");
var keyword =new tt.Field(" 库存ID ","searchKey.keyword").setMsgId("keyword");

tt.vf.req.add(displayOrder,hits,keyword);
new tt.LV().set(0, 30).add(keyword);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>