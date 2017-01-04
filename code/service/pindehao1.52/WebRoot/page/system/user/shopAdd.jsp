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
<style type="text/css">
.info_table td select {
width: 180px;
padding: 5px;
font-size: 14px;
margin-right: 10px;
}
</style>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a>品牌管理</a> &gt; <a href="shop.do" >店铺信息</a> &gt; <a href="#">新增</a></div>
  <div class="h15"></div>
  <div>
  <form action="insertShop.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
     <input name="shop.userId" id="shopId" value="${shopPojo.userId}" class="inputText" type="hidden" >
     <tr>
     <td align="right" class="grey" width="15%">昵称:</td>	
     <td width="35%">
     <input type="text" name="sysLogin.name"  value="" class="floatLeft" id="ticketName"><span id="namead"></span></td>
     
   </tr>
   <tr>
     <td align="right" class="grey" width="15%">帐号:</td>	
     <td width="35%">
     <input type="text" name="sysLogin.loginname"  value="" class="floatLeft" id="ticketName"><span id="loginname"></span></td>
     
   </tr>
   <tr>
   <td align="right" class="grey" width="15%">密码:</td>	
     <td width="35%">
     <input type="password" name="sysLogin.password"  value="" class="floatLeft" id="ticketName"><span id="password"></span></td>
   </tr>
     <tr>
        <td align="right" class="grey" width="15%">店铺名称:</td>	
        <td width="35%">
        <input type="text" name="shop.name"  value="" class="floatLeft" id="ticketName"><span id="namead"></span></td>
        
   </tr>
      <tr>
      <td align="right" class="grey" width="15%">联系号码:</td>	
      <td width="35%">
      <input type="text" name="shop.phone"  value="" class="floatLeft" id="ticketName"><span id="phone"></span></td>
      
      </tr>
       <tr>
      	<td align="right" class="grey" width="15%">地址:</td>	
        <td colspan="3">
        <select id="province" name="shop.province" class="floatLeft" ></select>
        <select id="city" name="shop.city" class="floatLeft"></select>
        <select id="area" name="shop.area" class="floatLeft"></select>
        <input type="text" name="shop.address"  value="" style="height:30px;padding: 3px;line-height: 22px;min-width: 180px;border: 1px #cdcdcd solid;margin-top: 2px;float: left;" id="ticketName" /><span id="address"></span></p></td>
      </tr>
      
      <tr>
        <td align="right" class="grey" width="15%">主营品类:</td>	
        <td width="35%"><select name="shop.mainCategory" id="ticketType"  class="floatLeft">
							<c:forEach items="${mainCategory}" var="mainCategory">
										<option value="${mainCategory.value}">${mainCategory.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
      	<td align="right" class="grey" width="15%">销售市场:</td>	
        <td width="35%">
        <input type="text" name="shop.salesArea"  value="" class="floatLeft" id="ticketName"><span id="salesArea"></span></td>
      </tr>
    <!--  <tr>
        <td align="right" class="grey" width="15%">纬度:</td>	
        <td width="35%">
        <input type="text" name="shop.lat" value="" class="floatLeft" id="ticketName"><span id="lat"></span></td>
      	<td align="right" class="grey" width="15%">经度:</td>	
        <td width="35%">
        <input type="text" name="shop.lng"  value="" class="floatLeft" id="ticketName"><span id="lng"></span></td>
      </tr>-->
      <tr>
        <td align="right" class="grey" width="15%">新品推荐:</td>	
        <td width="35%"><select name="shop.isNew" id="ticketType"  class="floatLeft">
							<c:forEach items="${isNew}" var="isNew">
										<option value="${isNew.value}">${isNew.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
		<td align="right" class="grey"  width="15%">状态:</td>
	    <td width="35%"><select name="shop.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}">${status.name}</option>
								</c:forEach>
					</select><div id="status_mgId"></div></td>			    
		
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">顶部主推商品图片:</td>	
        <td width="35%">
        <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（828*390）</font></p>
        <input type="file" name="upfile2" class="floatLeft" id="ticketName"> </td>
        <td align="right" class="grey" width="15%">品牌LOGO:</td>	
        <td width="35%">
        <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（320 * 320）</font></p>
        <input type="file" name="upfile" class="floatLeft" id="ticketName"> </td>
      </tr>
       <tr>
       <td align="right" class="grey" width="15%">店铺简介:</td>	
        <td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="shop.content" id="content" ></textarea><span id="content"></span></td>
        <td align="right" class="grey" width="15%">主营商品:</td>	
        <td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="shop.mainProduct" id="mainProduct" ></textarea><span id="mainProduct"></span></td>
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
var namead =new tt.Field(" 店铺名称 ","shop.name").setMsgId("namead");
var address =new tt.Field(" 地址 ","shop.address").setMsgId("address");
var salesArea =new tt.Field(" 销售市场 ","shop.salesArea").setMsgId("salesArea");
var phone =new tt.Field(" 联系号码 ","shop.phone").setMsgId("phone");
var content =new tt.Field(" 店铺简介 ","shop.content").setMsgId("content");
var mainProduct =new tt.Field(" 主营商品 ","shop.mainProduct").setMsgId("mainProduct");
var namead =new tt.Field(" 昵称 ","sysLogin.name").setMsgId("namead");
var loginname =new tt.Field(" 帐号 ","sysLogin.loginname").setMsgId("loginname");
var sorting =new tt.Field(" 排序 ","sysLogin.sorting").setMsgId("sorting");
var password =new tt.Field(" 密码 ","sysLogin.password").setMsgId("password");




tt.vf.req.add(namead,address,salesArea,phone,content,mainProduct,namead,loginname,sorting,password);
new tt.LV().set(0, 15).add(name);
new tt.LV().set(0, 30).add(namead);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>