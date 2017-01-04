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
  <a>商品审核管理</a> &gt; 
  <a href="sellerCheckManage.do">商家审核信息列表</a> &gt; 
  <a href="#">商家取消审核</a>
  </div>
  <div id="returnText">
   <hr />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%">请填写取消审核的原因:</td>	
        <td><textarea rows="10" cols="100" name="manufacturerPojo.returnContent" id="returnContent" >${manufacturerPojo.returnContent}</textarea></td>
      </tr>
    </table>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  		<input type="button"  class="ok_btn" value="取消审核" id="sbutton" onclick="unchecking(${manufacturerPojo.userId})"/>
  </div>
</div>

</body>
</html>


<script type="text/javascript">
function unchecking(uid){
	var rc = $("#returnContent").val().trim();
	if(rc == null || rc == ''){
		alert("取消审核的原因不能为空！");
		}else{
			if(confirm("确定要取消审核吗？")){
				var url = "sellerUncheckSubmit.do?manufacturerPojo.returnContent="+rc+"&manufacturerPojo.userId="+uid;	
				window.location.href = url;
			}else{
				return ;
			}
		}
}

<!-- var t = 0;
function returning(){
	if(t == 0){
		$("#returnText").css('display','block');
		t = 1;
	}else{
		$("#returnText").css('display','none');
		t = 0;
	}
} 
-->

/*function returnsubmit(uid){
	if(confirm("确定要退回修改吗？")){
		if($("#returnContent").val().trim() == ""){
			alert("请填写退回原因！");
		}else{
			document.getElementById("form2").submit();
		}
	}else{
		<!-- return ; -->
	}
}*/
</script>