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
  <a>商品优惠管理</a> &gt; 
  <a href="redpacketCodeList.do">红包邀请码记录列表</a> &gt; 
  <c:if test="${type == 0 }"><a href="#">新增邀请码</a></c:if>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addRedpacketCodeOk.do" method="post" id="form1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      <%-- <tr>
        <td align="right" class="grey" width="15%">红包邀请码:</td>	
        <td colspan="3">
        <input type="text" name="redPacketCodePojo.code"  value="${redPacketCodePojo.code }" class="floatLeft" id="ticketName" readonly="readonly">
        </td>
      </tr> --%>
      <tr>
        <td align="right" class="grey" width="15%">红包金额:</td>	
        <td colspan="3">
        <input type="text" name="redPacketCodePojo.amout"  value="${redPacketCodePojo.amout }" class="floatLeft" id="ticketName">
        <span id="amout_mgId"></span>
        </td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">红包个数:</td>	
        <td colspan="3">
        <input type="text" name="redpacketCodeNum"  value="${redpacketCodeNum }" class="floatLeft" id="ticketName">
        <span id="num_mgId"></span>
        </td>
      </tr>
      <tr>
		<td align="right" class="grey"  width="15%">状态:</td>
        <td colspan="3">
        <select name="redPacketCodePojo.status" id="ticketName"  class="floatLeft">
        	<option value="0">禁用</option>
        	<option value="1" selected="selected">启用</option>
	    </select>
        </td>
      </tr>
  	</table>
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  		<c:if test="${type == 0 }"><input type="button"  class="ok_btn" value="提交" id="sbutton"/></c:if>
  </div>
</div>

</body>
</html>


<script>
var amout =new tt.Field("红包金额","redPacketCodePojo.amout").setMsgId("amout_mgId");
var num =new tt.Field("红包个数","redpacketCodeNum").setMsgId("num_mgId");

tt.vf.req.add(amout,num);
tt.vf.num.add(amout,num);

$(document).ready(function() {
	$("#sbutton").click(function(){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
});
</script>