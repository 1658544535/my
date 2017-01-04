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
		select3();
		$('#ageType').bind("change", select2);
		$('#province').bind("change", select4);
		$('#city').bind("change", select5);
	});
	function select1() {
	$("#ageType").append("<option value=''>--请选择--</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysDictListByType.do?sysDict.type=user_age",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
				if("${userMakerShopPojo.ageType}" == o_msg[i].value){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#ageType").append("<option value=" + o_msg[i].value + " "+s+">" + o_msg[i].name + "</option>");
				}
				if("${userMakerShopPojo.ageType}"!=null && "${userMakerShopPojo.ageType}"!=""){
					select2();
				}
			}
		})
	};
	function select2() {
            $("#ability").html("");
            $.ajax(
            {
                type: "post",
                url: "getSkillTypes.do?ageId="+$('#ageType').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                	$("#ability").append("<option value=''>--请选择--</option>");
                    for (var i = 0; i < o_msg.length; i++) {
	                    if("${userMakerShopPojo.ability}" == o_msg[i].skillValue){
							s = "selected = selected";
						}else{
							s = "";
						}
                        $("#ability").append("<option value=" + o_msg[i].skillValue + " "+s+">" + o_msg[i].skillName + "</option>");
                    }
                }
            })
        };
    function select3() {
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
				if("${userMakerShopPojo.province}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#province").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
				}
				if("${userMakerShopPojo.province}"!=null && "${userMakerShopPojo.province}"!=""){
					select4();
				}
			}
		})
	};
	function select4() {
	 $("#city").html("");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${userMakerShopPojo.city}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#city").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
				}
				if("${userMakerShopPojo.city}"!=null && "${userMakerShopPojo.city}"!=""){
					select5();
				}
			}
		})
	};
	function select5() {
            $("#area").html("");
            $.ajax(
            {
                type: "post",
                url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
                dataType: 'json',
                success: function (msg) {
                	var o_msg = eval(msg);
                    for (var i = 0; i < o_msg.length; i++) {
	                    if("${userMakerShopPojo.area}" == o_msg[i].id){
							s = "selected = selected";
						}else{
							s = "";
						}
					    $("#area").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
                    }
                }
            })
        };
function check(val){
		if(confirm("确认要审核成功吗？")){
			var url = "checkUserMakerShop.do?userMakerShopPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要审核失败吗？")){
			var url = "uncheckUserMakerShop.do?userMakerShopPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
</script>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMakerShop.do">创客店铺管理</a> &gt;
  <a href="goDetailedUserMakerShop.do?userMakerShopPojo.id=${userMakerShopPojo.id}">查看/编辑详情</a>
  </div>
  <div class="h15"></div>
    <form action="updateDetailedUserMakerShop.do" method="post" id="from1" enctype="multipart/form-data">
  <div>
  <input type="hidden" name="userMakerShopPojo.id" value="${userMakerShopPojo.id}" class="floatLeft" id="ticketName" />
   <input type="hidden" name="userMakerShopPojo.userId" value="${userMakerShopPojo.userId}" class="floatLeft" id="ticketName" />
    <input type="hidden" name="userMakerShopPojo.shopId" value="${userMakerShopPojo.shopId}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <tr>
	    <td align="right" class="grey" width="15%">创客昵称：</td>
	    <td><input type="text" name="userMakerShopPojo.userName" value="${userMakerShopPojo.userName}"><div id="userName_mgId"></div></td>    
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">店铺名称：</td>
	    <td><input type="text" name="userMakerShopPojo.shopName" value="${userMakerShopPojo.shopName}"><div id="shopName_mgId"></div></td>    
	</tr>
	<tr>
        <td align="right" class="grey" width="10%">年龄:</td>
        <td><select id="ageType" name="userMakerShopPojo.ageType" class="floatLeft" ></select>					
		<div id="ageType_mgId"></div></td>
	</tr>
	<tr>								
	    <td align="right" class="grey" width="15%">开发能力：</td>
	    <td><select id="ability" name="userMakerShopPojo.ability" class="floatLeft" ></select>					
		<div id="ability_mgId"></div></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">联系人：</td>
		<td><input type="text" name="userMakerShopPojo.contact" value="${userMakerShopPojo.contact}"><div id="contact_mgId"></div></td>   
	</tr>
	<tr>
        <td align="right" class="grey" width="15%">地址：</td>
        <td>  <select id="province" name="userMakerShopPojo.province" class="ui-input ui-input-checkcode" style="width: 130px;">
            </select>&nbsp;&nbsp;
            <select id="city" name="userMakerShopPojo.city" class="ui-input ui-input-checkcode" style="width: 150px;">
            </select>&nbsp;&nbsp;
            <select id="area" name="userMakerShopPojo.area" class="ui-input ui-input-checkcode" style="width: 145px;">
            </select>
            <input type="text" name="userMakerShopPojo.detailedAddress" value="${userMakerShopPojo.detailedAddress}">
            <div id="detailedAddress_mgId"></div>
        </td>
    </tr>
	<tr>
		<td align="right" class="grey" width="15%">联系电话：</td>
		<td><input type="text" name="userMakerShopPojo.phone" value="${userMakerShopPojo.phone}"><div id="phone_mgId"></div></td>   
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">客服电话：</td>
		<td><input type="text" name="userMakerShopPojo.servicePhone" value="${userMakerShopPojo.servicePhone}"><div id="servicePhone_mgId"></div></td> 
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">支付宝账号：</td>
		<td><input type="text" name="userMakerShopPojo.alipayAccount" value="${userMakerShopPojo.alipayAccount}"><div id="alipayAccount_mgId"></div></td>          
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">支付宝实名：</td>
		<td><input type="text" name="userMakerShopPojo.alipayName" value="${userMakerShopPojo.alipayName}"><div id="alipayName_mgId"></div></td>     
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">单平台内容产出量：</td>
		<td>  <select name="userMakerShopPojo.contentOutput">
                                    <option value="0" <s:if test="userMakerShopPojo.contentOutput==0">selected="selected"</s:if>>1/7天</option>
                                    <option value="1" <s:if test="userMakerShopPojo.contentOutput==1">selected="selected"</s:if>>1/5天</option>
                                    <option value="2" <s:if test="userMakerShopPojo.contentOutput==2">selected="selected"</s:if>>1/3天</option>
                                    <option value="3" <s:if test="userMakerShopPojo.contentOutput==3">selected="selected"</s:if>>1/1天</option>
                                    <option value="4" <s:if test="userMakerShopPojo.contentOutput==4">selected="selected"</s:if>>2/1天</option>
                                    <option value="5" <s:if test="userMakerShopPojo.contentOutput==5">selected="selected"</s:if>>3/1天以上</option>
                                </select><div id="contentOutput_mgId"></div></td>                                                                                                       
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">单平台原创内容产出量：</td>
		<td> <select name="userMakerShopPojo.contentOutputOriginal">
                                    <option value="0" <s:if test="userMakerShopPojo.contentOutputOriginal==0">selected="selected"</s:if>>1/7天</option>
                                    <option value="1" <s:if test="userMakerShopPojo.contentOutputOriginal==1">selected="selected"</s:if>>1/5天</option>
                                    <option value="2" <s:if test="userMakerShopPojo.contentOutputOriginal==2">selected="selected"</s:if>>1/3天</option>
                                    <option value="3" <s:if test="userMakerShopPojo.contentOutputOriginal==3">selected="selected"</s:if>>1/1天</option>
                                    <option value="4" <s:if test="userMakerShopPojo.contentOutputOriginal==4">selected="selected"</s:if>>2/1天</option>
                                    <option value="5" <s:if test="userMakerShopPojo.contentOutputOriginal==5">selected="selected"</s:if>>3/1天以上</option>
                                </select><div id="contentOutputOriginal_mgId"></div></td>                                                                                                      
	</tr>
	<tr><td align="right" class="grey" width="15%">店铺LOGO：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${userMakerShopPojo.shopLOGO}" height="100px" /></td></tr>
	<tr>
	<td> <input type="file" name="upfile" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </table>
    </td>
	</tr>
		</tr>
	<tr><td align="right" class="grey" width="15%">店铺主图：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${userMakerShopPojo.shopMainImage}" height="100px" /></td></tr>
	<tr>
	<td> <input type="file" name="upfile1" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </table>
    </td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">${userMakerShopPojo.shopTypeNames}</td>
	</tr>
	<s:if test = "userMakerShopPojo.shopType == 0">
	<tr>
		<td align="right" class="grey" width="15%">企业全称：</td>
		<td><input type="text" name="userMakerShopPojo.shopTypeName1" value="${userMakerShopPojo.shopTypeName}"><div id="shopTypeName1_mgId"></div></td>           
	</tr>
	
    <tr><td align="right" class="grey" width="15%">营业执照正/副本（加盖企业公章）：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageOriginal}" height="100px" /></td></tr>
	<tr>
	<td> <input type="file" name="upfile2" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </table>
    </td>
	</tr>
	<tr><td align="right" class="grey" width="15%">法人身份证正反面图（加盖企业公章）：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageCopy}" height="100px" /></td></tr>
	<tr>
	<td> <input type="file" name="upfile3" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </table>
    </td>
	</tr>
	</s:if>
	<s:elseif test = "userMakerShopPojo.shopType == 1">
	<tr>
		<td align="right" class="grey" width="15%">店铺平台：</td>
		<td><input type="text" name="userMakerShopPojo.shopTypeName2" value="${userMakerShopPojo.shopTypeName}"><div id="shopTypeName2_mgId"></div></td>     
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">店铺地址：</td>
		<td><input type="text" name="userMakerShopPojo.shopTypeUrl" value="${userMakerShopPojo.shopTypeUrl}"><div id="shopTypeUrl_mgId"></div></td>     
	</tr>
	<tr><td align="right" class="grey" width="15%">店主手持身份证照片：</td>
	<td>
	<table border="0" cellpadding="0" cellspacing="0">
	<tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userMakerShop/${userMakerShopPojo.imageOriginal}" height="100px" /></td></tr>
	<tr>
	<td> <input type="file" name="upfile4" class="floatLeft" id="ticketName" /> <span id="picture_msgId"></span></td>
	</tr>
	<!--<tr><td><p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（739*500）</font></p></td></tr>-->
    </table>
    </td>
	</tr>
	</s:elseif>
    </table> 
  </div>
  <div class="Btn_div">
 <!-- <s:if test = "userMakerShopPojo.status == 2">
  		<input type="button"  class="ok_btn" value="审核成功" onclick="check(${userMakerShopPojo.id})"/>
  </s:if>	
  <s:elseif test = "userMakerShopPojo.status == 1">
  		<input type="button"  class="ok_btn" value="审核失败" onclick="uncheck(${userMakerShopPojo.id})"/>
  </s:elseif> -->
  <input type="button"  class="ok_btn" value="提交" id="sbutton"/>
  <button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  </div>
  </form>
</div>
  <script>

    var userName              = new tt.Field("创客昵称 ","userMakerShopPojo.userName").setMsgId("userName_mgId");
    var shopName              = new tt.Field("店铺名称 ","userMakerShopPojo.shopName").setMsgId("shopName_mgId");
    var ageType               = new tt.Field("年龄 ","userMakerShopPojo.ageType").setMsgId("ageType_mgId");
    var ability               = new tt.Field("开发能力 ","userMakerShopPojo.ability").setMsgId("ability_mgId");
    var contact               = new tt.Field("联系人 ","userMakerShopPojo.contact").setMsgId("contact_mgId");
   // var province              = new tt.Field("省 ","userMakerShopPojo.province").setMsgId("province_mgId");
   // var city                  = new tt.Field("市 ","userMakerShopPojo.city").setMsgId("city_mgId");
   // var area                  = new tt.Field("区域 ","userMakerShopPojo.area").setMsgId("area_mgId");
    var detailedAddress       = new tt.Field("详细地址 ","userMakerShopPojo.detailedAddress").setMsgId("detailedAddress_mgId");
    var phone                 = new tt.Field("联系电话 ","userMakerShopPojo.phone").setMsgId("phone_mgId");
    var servicePhone          = new tt.Field("客服电话 ","userMakerShopPojo.servicePhone").setMsgId("servicePhone_mgId");
    var alipayAccount         = new tt.Field("支付宝帐号 ","userMakerShopPojo.alipayAccount").setMsgId("alipayAccount_mgId");
    var alipayName            = new tt.Field("支付宝实名 ","userMakerShopPojo.alipayName").setMsgId("alipayName_mgId");
    var contentOutput         = new tt.Field("单平台内容产出量","userMakerShopPojo.contentOutput").setMsgId("contentOutput_mgId");
    var contentOutputOriginal = new tt.Field("单平台原创内容产出量 ","userMakerShopPojo.contentOutputOriginal").setMsgId("contentOutputOriginal_mgId");
    var shopTypeName1         = new tt.Field("企业全称 ","userMakerShopPojo.shopTypeName1").setMsgId("shopTypeName1_mgId");
    var shopTypeName2         = new tt.Field("店铺平台","userMakerShopPojo.shopTypeName2").setMsgId("shopTypeName2_mgId");
   	var shopTypeUrl           = new tt.Field("店铺地址","userMakerShopPojo.shopTypeUrl").setMsgId("shopTypeUrl_mgId");
    tt.vf.req.add(userName,shopName,ageType,ability,contact,detailedAddress,phone,servicePhone,alipayAccount,alipayName,
    contentOutput,contentOutputOriginal,shopTypeName1,shopTypeName2,shopTypeUrl);
    
            $("#sbutton").click(function(){
        	if(tt.validate()){
					document.getElementById("from1").submit();
			}
		  });	
      </script>
</body>
</html>