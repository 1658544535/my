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
	$(function(){
		select1();
		$('#province').bind("change", select2);
        $('#city').bind("change", select3);
	});
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					$("#province").append("<option value=" + o_msg[i].id + " "+">" + o_msg[i].name + "</option>");
				}
			}
		});
	}
	function select2() {
            $("#city").html("");
            $("#city").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        $("#city").append("<option value=" + o_msg[i].id + " "+">" + o_msg[i].name + "</option>");
                    }
                }
            });
        }
	function select3() {
            $("#area").html("");
             $("#area").append("<option value=''>- 请选择 -</option>");
            $.ajax(
            {
                type: "post",
                url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
                        $("#area").append("<option value=" + o_msg[i].id + " "+"> " + o_msg[i].name + "</option>");
                    }
                }
            });
        }
</script>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>系统管理</a> &gt; 
  <a href="agencyApplication.do">批发商申请管理</a>&gt; <c:if test="${type == 0 }"><a href="#">新增批发商</a></c:if><c:if test="${type == 1 }"><a href="#">编辑批发商</a></c:if></div>
  <div class="h15"></div>
  <div>
  <form action="AgencyApplyAddOk.do" method="post" id="form1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      <tr>
        <td align="right" class="grey" width="15%">联系人:</td>	
        <td colspan="3">
        <input type="text" name="agencyApplyPojo.contact"  value="${agencyApplyPojo.contact }" class="floatLeft" id="ticketName">
        <span id="contact_mgId"></span>
        </td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">手机号码:</td>	
        <td colspan="3">
        <input type="text" name="agencyApplyPojo.phone"  value="${agencyApplyPojo.phone }" class="floatLeft" id="ticketName">
        <span id="phone_mgId"></span>
        </td>
      </tr>
      <tr>
      <td align="right" class="grey" width="15%">QQ:</td>	
        <td colspan="3">
        <input type="text" name="agencyApplyPojo.QQ"  value="${agencyApplyPojo.QQ }" class="floatLeft" id="ticketName">
        <span id="QQ_mgId"></span>
        </td>
      </tr>
      <tr>
		<td align="right" class="grey"  width="15%">状态:</td>
        <td colspan="3">
        <select name="agencyApplyPojo.status" id="ticketName"  class="floatLeft">
				<%-- <c:forEach items="${status}" var="status">
							<option value="${status.value}"<c:if test="${manufacturerPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
				</c:forEach> --%>
				<option value="0" selected="selected">未审核</option>
				<option value="1">已审核</option>
	    </select>
		<span id="status_mgId"></span>
		</td>
      </tr>
      <tr>
      	<td align="right" class="grey" width="15%">地址:</td>	
        <td colspan="3">
        <select id="province" name="agencyApplyPojo.provinceId" class="floatLeft" ></select>
        <select id="city" name="agencyApplyPojo.cityId" class="floatLeft"></select>
        <select id="area" name="agencyApplyPojo.areaId" class="floatLeft"></select>
        <input type="text" name="agencyApplyPojo.address"  value="${agencyApplyPojo.address }" style="height:30px;padding: 3px;line-height: 22px;min-width: 350px;border: 1px #cdcdcd solid;margin-top: 2px;float: left;" id="ticketName" />
		<span id="s_address_mgId"></span>
		</td>
      </tr>
      <tr>
	    <td align="right" class="grey" width="15%">主营商品:</td>	
	    <td colspan="3">
	    <textarea class="floatLeft" rows="6" cols="50" name="agencyApplyPojo.mainProduct" id="mainProduct"   >${agencyApplyPojo.mainProduct}</textarea>
		<span id="mainProduct_mgId"></span>
		</td>
	  </tr>
	  <tr>
      <%-- <td align="right" class="grey" width="15%">创建者:</td>	
        <td colspan="3">
        <input type="text" name="agencyApplyPojo.createBy"  value="" class="floatLeft" id="ticketName">
        <span id="createBy_mgId"></span>
        </td>
      </tr> --%>
  	</table>
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  		<c:if test="${type == 0 }"><input type="button"  class="ok_btn" value="提交" id="sbutton"/></c:if>
  		<c:if test="${type == 1 }"><input type="button"  class="ok_btn" value="提交" id="sbutton2"/></c:if>
  </div>
</div>

</body>
</html>


<script>
var contact =new tt.Field(" 联系人 ","agencyApplyPojo.contact").setMsgId("contact_mgId");
var phone =new tt.Field(" 手机号码 ","agencyApplyPojo.phone").setMsgId("phone_mgId");
var QQ =new tt.Field(" QQ ","agencyApplyPojo.QQ").setMsgId("QQ_mgId");
var mainProduct =new tt.Field(" 主营商品 ","agencyApplyPojo.mainProduct").setMsgId("mainProduct_mgId");
var sAddress2 =new tt.Field(" 地址 ","agencyApplyPojo.address").setMsgId("s_address_mgId");
var sAddress =new tt.Field(" 地址 ","agencyApplyPojo.areaId").setMsgId("s_address_mgId");

tt.vf.req.add(contact,phone,QQ,mainProduct,sAddress,sAddress2);
tt.vf.num.add(phone,QQ);
new tt.LV().set(0, 50).add(contact);
new tt.LV().set(11, 11).add(phone);
new tt.LV().set(5, 50).add(QQ);
new tt.LV().set(0, 255).add(mainProduct);
new tt.LV().set(0, 255).add(sAddress2);



MyValidator1 = tt.BV.ext({
	/**
	 * 验证的主方法
	 */
	v : function(trimedValue, indexOfElements, elements, field){
		var vparam = field.name;
		var url = "validAgencyApplyAdd.do?"+ vparam + "=" + trimedValue;
		var flag = false;
		$.ajax(
			{
				type: "post",
				url: url,
				dataType: 'json',
				async: false,
				success: function (data) {
					if(data==1){
						flag = true;
					}else{
					    flag = false;
					};
				}
			})
		return flag;
	},
	getI18 : function(label){
		return "已被注册，请换一个!";
	},
	/**
	 * 验证通过时，提示信息
	 */
	getTip : function(e,f,v,val) {
		return "恭喜！可以使用!";
	}
});



$(document).ready(function() {
	$("#sbutton").click(function(){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
	
	$("#sbutton2").click(function(){
		if(tt.validate()){
			$("#form1").attr("action","updateAgencyApplyOk.do?agencyApplyPojo.id=${agencyApplyPojo.id }").submit();
		}
	});
});

</script>
<c:if test="${type == 0 }">
<script>new MyValidator1().add(contact);</script>
</c:if>