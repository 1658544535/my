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
		})
	};
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
            })
        };
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
            })
        };
</script>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>系统管理</a> &gt; 
  <c:if test="${os==0}"><a href="sysLogin.do?os=${os}">管理员管理</a></c:if> 
  <c:if test="${os==1}"><a href="sysLogin.do?os=${os}">普通用户管理</a></c:if> 
  <c:if test="${os==2}"><a href="sysLogin.do?os=${os}">供应商管理</a></c:if> 
  <c:if test="${os==3}"><a href="sysLogin.do?os=${os}">采购商管理</a></c:if>
  <c:if test="${os==6}"><a href="sysLogin.do?os=${os}">批发商管理</a></c:if>&gt; <a href="#">新增用户</a></div>
  <div class="h15"></div>
  <div>
  <form action="insertSysLogin.do?os=${os}" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      <tr>
        <td align="right" class="grey" width="15%">昵称:</td>	
        <td colspan="3">
        <input type="text" name="sysLogin.name"  value="" class="floatLeft" id="ticketName"><span id="namead"></span></td>
        
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">帐号:</td>	
        <td colspan="3">
        <input type="text" name="sysLogin.loginname"  value="" class="floatLeft" id="ticketName"><span id="loginname"></span></td>
        
      </tr>
      <tr>
      <td align="right" class="grey" width="15%">密码:</td>	
        <td colspan="3">
        <input type="password" name="sysLogin.password"  value="" class="floatLeft" id="ticketName"><span id="password"></span></td>
      </tr>
      
    </table>
    <c:if test="${os==2}">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    	<tr>
        <td align="right" class="grey" width="15%">公司名称:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.company"  value="${manufacturerPojo.company}" class="floatLeft" id="ticketName">
		<span id="company_mgId"></span>
		</td>
		
        <td align="right" class="grey" width="15%">公司规模:</td>	
        <td width="35%">
			<select name="manufacturer.scale" id="ticketType"  class="floatLeft">
					<c:forEach items="${scale}" var="scale">
								<option value="${scale.value}"<c:if test="${manufacturerPojo.scale==scale.value}">selected="selected" </c:if>>${scale.name}</option>
					</c:forEach>
		    </select>
		<div id="scale_mgId"></div>
		</td>
		
      </tr>
      <tr>
      	<td align="right" class="grey" width="15%">公司网址:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.webSite"  value="${manufacturerPojo.webSite}" class="floatLeft" id="ticketName">
		<span id="webSite_mgId"></span>
		</td>
		
		<td align="right" class="grey"  width="15%">来源渠道:</td>
        <td width="35%">
			<select name="manufacturer.channel" id="ticketType"  class="floatLeft">
					<c:forEach items="${channel}" var="channel">
								<option value="${channel.value}"<c:if test="${manufacturerPojo.channel==channel.value}"> selected="selected"</c:if>>${channel.name}</option>
					</c:forEach>
		    </select>
		<div id="channel_mgId"></div>
		</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">主营品类:</td>	
        <td width="35%">
			<select name="manufacturer.mainCategory" id="ticketType"  class="floatLeft">
					<c:forEach items="${mainCategory}" var="mainCategory">
								<option value="${mainCategory.id}"<c:if test="${manufacturerPojo.mainCategory==mainCategory.id}"> selected="selected"</c:if>>${mainCategory.name}</option>
					</c:forEach>
		    </select>
		<div id="mainCtg_mgId"></div>
		</td>
		
		<td align="right" class="grey" width="15%">销售市场:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.salesArea"  value="${manufacturerPojo.salesArea}" class="floatLeft" id="ticketName">
		<span id="salesArea_mgId"></span>
		</td>
		
      </tr>
      <tr>
      	<td align="right" class="grey" width="15%">自营品牌:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.brand" value="${manufacturerPojo.brand}" class="floatLeft" id="ticketName">
		<span id="brand_mgId"></span>
		</td>
		<td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%">
        <select name="manufacturer.status" id="ticketType"  class="floatLeft">
				<c:forEach items="${status}" var="status">
							<option value="${status.value}"<c:if test="${manufacturerPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
				</c:forEach>
	    </select>
		<div id="status_mgId"></div>
		</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">新品推荐:</td>	
        <td width="35%">
			<select name="shop.isNew" id="ticketType"  class="floatLeft">
					<c:forEach items="${isNew}" var="isNew">
								<option value="${isNew.value}">${isNew.name}</option>
					</c:forEach>
		    </select>
		<div id="new_mgId"></div>
		</td>
        <td align="right" class="grey"  width="15%">是否查看:</td>
        <td width="35%">
			<select name="manufacturer.isread" id="ticketType"  class="floatLeft">
					<c:forEach items="${isread}" var="isread">
								<option value="${isread.value}"<c:if test="${manufacturerPojo.isread==isread.value}">selected="selected" </c:if>>${isread.name}</option>
					</c:forEach>
		    </select>
		<div id="read_mgId"></div>
		</td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">商务联系人:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.contact"  value="${manufacturerPojo.contact}" class="floatLeft" id="ticketName">
		<span id="contact_mgId"></span>
		</td>
        <td align="right" class="grey" width="15%">职务:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.duty" value="${manufacturerPojo.duty}" class="floatLeft" id="ticketName">
		<span id="duty_mgId"></span>
		</td>
      </tr>
     <tr>
        <td align="right" class="grey" width="15%">联系号码:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.phone"  value="${manufacturerPojo.phone}" class="floatLeft" id="ticketName">
		<span id="c_phone_mgId"></span>
		</td>
        <td align="right" class="grey" width="15%">传真号码:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.fax" value="${manufacturerPojo.fax}" class="floatLeft" id="ticketName">
		<span id="fax_mgId"></span>
		</td>
      </tr>
       <tr>
        <td align="right" class="grey" width="15%">QQ:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.QQ"  value="${manufacturerPojo.QQ}" class="floatLeft" id="ticketName">
		<span id="QQ_mgId"></span>
		</td>
        <td align="right" class="grey" width="15%">邮箱地址:</td>	
        <td width="35%">
        <input type="text" name="manufacturer.email" value="${manufacturerPojo.email}" class="floatLeft" id="ticketName">
		<span id="email_mgId"></span>
		</td>
      </tr>
      <tr>
      	<td align="right" class="grey" width="15%">地址:</td>	
        <td colspan="3">
        <select id="province" name="shop.province" class="floatLeft" ></select>
        <select id="city" name="shop.city" class="floatLeft"></select>
        <select id="area" name="shop.area" class="floatLeft"></select>
        <input type="text" name="shop.address"  value="" style="height:30px;padding: 3px;line-height: 22px;min-width: 350px;border: 1px #cdcdcd solid;margin-top: 2px;float: left;" id="ticketName" />
		<span id="s_address_mgId"></span>
		</p>
		</td>
      </tr>
	  <tr>
	   <td align="right" class="grey" width="15%">公司简介:</td>	
	    <td width="35%">
	    <textarea class="floatLeft" rows="6" cols="50" name="manufacturer.content" id="content"   >${manufacturerPojo.content}</textarea>
		<span id="c_content_mgId"></span>
		</td>
	    <td align="right" class="grey" width="15%">主营商品:</td>	
	    <td width="35%">
	    <textarea class="floatLeft" rows="6" cols="50" name="manufacturer.mainProduct" id="mainProduct"   >${manufacturerPojo.mainProduct}</textarea>
		<span id="mainProduct_mgId"></span>
		</td>
	  </tr>
       	
      <tr>
		<td align="right" class="grey" width="15%">品牌LOGO:</td>	
        <td width="35%">
        <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（200 * 200）</font>
		</p>
        <input type="file" name="upfile" class="floatLeft" id="ticketName"> </td>
         
        <td align="right" class="grey" width="15%">顶部主推商品图片:</td>	
        <td width="35%">
        <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（414*195）</font>
		</p>
        <input type="file" name="upfile2" class="floatLeft" id="ticketName"> </td>
      </tr>
  	</table>
  	</c:if>
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  </div>
</div>

</body>
</html>


<script>
var namead =new tt.Field(" 昵称 ","sysLogin.name").setMsgId("namead");
var loginname =new tt.Field(" 帐号 ","sysLogin.loginname").setMsgId("loginname");
var sorting =new tt.Field(" 排序 ","sysLogin.sorting").setMsgId("sorting");
var password =new tt.Field(" 密码 ","sysLogin.password").setMsgId("password");

var company =new tt.Field(" 公司名称 ","manufacturer.company").setMsgId("company_mgId");
var webSite =new tt.Field(" 公司网址 ","manufacturer.webSite").setMsgId("webSite_mgId");
var salesArea =new tt.Field(" 销售市场 ","manufacturer.salesArea").setMsgId("salesArea_mgId");
var brand =new tt.Field(" 自营品牌 ","manufacturer.brand").setMsgId("brand_mgId");
var contact =new tt.Field(" 商务联系人 ","manufacturer.contact").setMsgId("contact_mgId");
var duty =new tt.Field(" 职务 ","manufacturer.duty").setMsgId("duty_mgId");
var QQ =new tt.Field(" QQ ","manufacturer.QQ").setMsgId("QQ_mgId");
var email =new tt.Field(" 邮箱地址 ","manufacturer.email").setMsgId("email_mgId");
var cContent =new tt.Field(" 公司简介 ","manufacturer.content").setMsgId("c_content_mgId");
var mainProduct =new tt.Field(" 主营商品 ","manufacturer.mainProduct").setMsgId("mainProduct_mgId");
var cPhone =new tt.Field(" 联系号码 ","manufacturer.phone").setMsgId("c_phone_mgId");
var sArea =new tt.Field(" 地址 ","shop.area").setMsgId("s_address_mgId");
var sAddress =new tt.Field(" 地址 ","shop.address").setMsgId("s_address_mgId");

tt.vf.req.add(namead,loginname,sorting,password,company,salesArea,brand,contact,duty,QQ,email,cContent,mainProduct,cPhone,sArea,sAddress);
new tt.LV().set(0, 30).add(namead);
new tt.LV().set(0, 50).add(company);
new tt.LV().set(0, 180).add(cContent);
new tt.LV().set(0, 125).add(mainProduct);



MyValidator1 = tt.BV.ext({
	/**
	 * 验证的主方法
	 */
	v : function(trimedValue, indexOfElements, elements, field){
		var vparam = field.name;
		var url = "validLoginRegiste.do?"+ vparam + "=" + trimedValue;
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
new MyValidator1().add(namead);
new MyValidator1().add(loginname);



$(document).ready(function() {
	$("#sbutton").click(function(){		
		if(tt.validate()){
			document.getElementById("from1").submit();
		}
	});
});

</script>