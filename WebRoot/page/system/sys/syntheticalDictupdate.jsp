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
  <div class="s_nav"><a>系统管理</a> &gt; <a href="syntheticalDict.do" >综合字典管理</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateSyntheticalDict.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="syntheticalDict.id" id="syntheticalDictId" value="${syntheticalDictPojo.id}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">字典名称:</td>	
        <td width="35%">
        <input type="text" name="syntheticalDict.name"  value="${syntheticalDictPojo.name}" class="floatLeft" id="ticketName"><span id="namead"></span></td>
        
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="syntheticalDict.status" id="ticketType"
							class="floatLeft">
								<c:forEach items="${status}" var="status">
										<option value="${status.value}" <c:if test="${syntheticalDictPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
						</select></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">字典名称英文:</td>	
        <td width="35%">
        <input type="text" name="syntheticalDict.nameEn"  value="${syntheticalDictPojo.nameEn}" class="floatLeft" id="ticketName"><span id="nameEn"></span></td>
        
        <td align="right" class="grey" width="15%">字典分类:</td>	
        <td width="35%">
        <input type="text" name="syntheticalDict.type"  value="${syntheticalDictPojo.type}" class="floatLeft" id="ticketName"><span id="type"></span></td>
        
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">字典取值:</td>	
        <td width="35%">
        <input type="text" name="syntheticalDict.value"  value="${syntheticalDictPojo.value}" class="floatLeft" id="ticketName"><span id="value"></span></td>
           <td align="right" class="grey" width="15%">排序:</td>	
        <td width="35%">
        <input type="text" name="syntheticalDict.sorting"  value="${syntheticalDictPojo.sorting}" class="floatLeft" id="ticketName"><span id="sorting"></span></td>
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
var type =new tt.Field(" 字典分类 ","syntheticalDict.type").setMsgId("type");
var namead =new tt.Field(" 字典名称 ","syntheticalDict.name").setMsgId("namead");
var nameEn =new tt.Field(" 字典名称英文 ","syntheticalDict.nameEn").setMsgId("nameEn");
var value =new tt.Field(" 字典取值 ","syntheticalDict.value").setMsgId("value");
var sorting =new tt.Field(" 排序 ","syntheticalDict.sorting").setMsgId("sorting");

tt.vf.req.add(type,namead,nameEn,value,sorting);
new tt.LV().set(0, 15).add(type);
new tt.LV().set(0, 30).add(namead);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>