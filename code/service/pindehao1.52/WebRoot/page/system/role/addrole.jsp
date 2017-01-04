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
  <div class="s_nav"><a>系统管理</a> &gt; <a href="store_list.html" >角色管理</a> &gt; <a href="#">新增角色</a></div>
  <div class="h15"></div>
  <div>	
  <form action="addrole.do" method="post" id="from1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      <tr>
  
        <td align="right" class="grey" width="15%">角色名称:</td>	
        <td width="35%">
        <input type="text" name="roleName" class="floatLeft" id="ticketName"><span id="shopAddress_msgId"></span></td>
    
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
	var adName =new tt.Field("角色名称","roleName").setMsgId("shopAddress_msgId");
	var adMoney =new tt.Field(" 票金额 ","ticketRulePojo.ticketMoney").setMsgId("ticketMoney_mgId");
	var ticketType =new tt.Field(" 票类型 ","ticketRulePojo.ticketType").setMsgId("ticketType_mgId");
	var ticketPer =new tt.Field(" 百分比 ","ticketRulePojo.ticketPer").setMsgId("ticketPer_mgId");
	
	new tt.RV().set(new RegExp("^(?:0|[1-9][0-9]?|100)$"), "必须输入整数0至100").add(ticketPer);
	
	tt.vf.num.add(adMoney);
	tt.vf.req.add(adName,ticketType,adMoney,ticketPer);
	new tt.LV().set(0, 15).add(adMoney);
	new tt.LV().set(0, 30).add(adName);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>