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

<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<script type="text/javascript">
	
</script>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>商品扣分管理</a> &gt; 
  <a href="#">修改扣分详情</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateUserDeductionScoreOk.do" method="post" id="form1" enctype="multipart/form-data">
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>商家剩余分数:</td>
        <td><font color="red">${scoreAll }</font></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>扣减分数:</td>
        <td><input class="floatLeft" type="text" name="userDeductionScorePojo.deductScore" id="" value="${userDeductionScorePojo.deductScore }">
        <span id="deductScore_mgId"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>扣减原因:</td>
        <td><textarea class="floatLeft" rows="10" cols="100" name="userDeductionScorePojo.remark" id="">${userDeductionScorePojo.remark }</textarea>
        <span id="remark_mgId"></span></td>
      </tr>
      <input type="hidden" name="userDeductionScorePojo.id" id="" value="${userDeductionScorePojo.id }">
      <input type="hidden" name="userDeductionScorePojo.suserId" id="" value="${userDeductionScorePojo.suserId }">
  	</table>
   </form>
   </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  		<input type="button"  class="ok_btn" value="修改扣分" id="sbutton" onclick="submiting()"/>
  </div>
</div>

</body>
</html>


<script>
var deductScore =new tt.Field(" 扣减分数 ","userDeductionScorePojo.deductScore").setMsgId("deductScore_mgId");
var remark =new tt.Field(" 扣减原因 ","userDeductionScorePojo.remark").setMsgId("remark_mgId");
var scoreall = ${scoreAll };
tt.vf.req.add(deductScore,remark);
new tt.RV().set(new RegExp("^(([1-9][0-9]*([.][0-9]{1}){0,1})|([0]([.][1-9]{1}){1}))$"),"请输入正数，最多一位小数").add(deductScore); 
new tt.CV().add(deductScore).set('n', '<=', scoreall);

function submiting(uid){
	if(confirm("确定要修改扣分吗？")){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	}else{
		return ;
	}
}
</script>