<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					var selectedStr = "";
                    if("${shopPojo.province}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#province").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
				if("${shopPojo.province}"!=null && "${shopPojo.province}"!=""){
					select2();
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
                    	var selectedStr = "";
                    	if("${shopPojo.city}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#city").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                    }
                    if("${shopPojo.city}"!=null && "${shopPojo.city}"!=""){
						select3();
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
                    	var selectedStr = "";
                    	if("${shopPojo.area}" == o_msg[i].id){
                    		selectedStr = "selected='selected'";
                    	}
                        $("#area").append("<option value=" + o_msg[i].id + " "+selectedStr+"> " + o_msg[i].name + "</option>");
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
  <div class="s_nav"><a>品牌管理</a> &gt; <a href="shop.do" >店铺信息</a> &gt; <a href="#">编辑</a></div>
  <div class="h15"></div>
  <div>
  <form action="updateShop.do" method="post" id="from1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
    <input name="shop.id" id="shopId" value="${shopPojo.id}" class="inputText" type="hidden" >
     <input name="shop.userId" id="shopId" value="${shopPojo.userId}" class="inputText" type="hidden" >
     <input name="shop.images" id="shopId" value="${shopPojo.images}" class="inputText" type="hidden" >
     <input name="shop.topImage" id="shopId" value="${shopPojo.topImage}" class="inputText" type="hidden" >
      <tr>
        <td align="right" class="grey" width="15%">店铺名称:</td>	
        <td width="35%">
        <input type="text" name="shop.name"  value="${shopPojo.name}" class="floatLeft" id="ticketName"><span id="namead"></span></td>
        <td align="right" class="grey" width="15%">主营品类:</td>	
        <td width="35%">
       	<%-- <select name="shop.mainCategory" id="ticketType"  class="floatLeft">
						<c:forEach items="${mainCategory}" var="mainCategory">
							<option value="${mainCategory.value}"<c:if test="${shopPojo.mainCategory==mainCategory.value}">selected="selected" </c:if>>${mainCategory.name}</option>
								</c:forEach>  
			</select> --%>
			<c:forEach items="${mainCategory}" var="mainCategory">
			<label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="${mainCategory.id}" <c:if test="${fn:contains(shopPojo.mainCategory, ':mainCategory.id:')}">checked="true" </c:if>/>${mainCategory.name} </label>
								    <%-- <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="1" <c:if test="${fn:contains(shopPojo.mainCategory, ':1:')}">checked="true" </c:if>/>电动遥控玩具 </label> 
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="2" <c:if test="${fn:contains(shopPojo.mainCategory, ':2:')}">checked="true" </c:if>/>早教玩具 </label>
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="3" <c:if test="${fn:contains(shopPojo.mainCategory, ':3:')}">checked="true" </c:if>/>过家家玩具 </label> 
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="4" <c:if test="${fn:contains(shopPojo.mainCategory, ':4:')}">checked="true" </c:if>/>儿童童车 </label> 
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="5" <c:if test="${fn:contains(shopPojo.mainCategory, ':5:')}">checked="true" </c:if>/>益智玩具 </label>
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="6" <c:if test="${fn:contains(shopPojo.mainCategory, ':6:')}">checked="true" </c:if>/>其他玩具 </label> 	
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="7" <c:if test="${fn:contains(shopPojo.mainCategory, ':7:')}">checked="true" </c:if>/>0-3岁玩具 </label> 
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="8" <c:if test="${fn:contains(shopPojo.mainCategory, ':8:')}">checked="true" </c:if>/>3-6岁玩具 </label> 
								    <label><input name="shop.mainCategory" id="ticketType"  type="checkbox" value="9" <c:if test="${fn:contains(shopPojo.mainCategory, ':9:')}">checked="true" </c:if>/>6岁以上玩具 </label>  --%>
			</c:forEach>
			<div id="mainCategory_mgId"></div></td>
        
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">联系号码:</td>	
        <td width="35%">
        <input type="text" name="shop.phone"  value="${shopPojo.phone}" class="floatLeft" id="ticketName"><span id="phone"></span></td>
        <td align="right" class="grey"  width="15%">状态:</td>
        <td width="35%"><select name="shop.status" id="ticketType"  class="floatLeft">
							<c:forEach items="${status}" var="status">
										<option value="${status.value}"<c:if test="${shopPojo.status==status.value}">selected="selected" </c:if>>${status.name}</option>
								</c:forEach>
				    </select><div id="status_mgId"></div></td>
      </tr>
      <tr>
      	<td align="right" class="grey" width="15%">地址:</td>	
        <td colspan="3">
        <select id="province" name="shop.province" class="floatLeft" ></select>
        <select id="city" name="shop.city" class="floatLeft"></select>
        <select id="area" name="shop.area" class="floatLeft"></select>
        <input type="text" name="shop.address"  value="${shopPojo.address}" style="height:30px;padding: 3px;line-height: 22px;min-width: 180px;border: 1px #cdcdcd solid;margin-top: 2px;float: left;" id="ticketName" /><span id="address"></span></p></td>
      </tr>
  <!--    <tr>
        <td align="right" class="grey" width="15%">纬度:</td>	
        <td width="35%">
        <input type="text" name="shop.lat" value="${shopPojo.lat}" class="floatLeft" id="ticketName"><span id="lat"></span></td>
      	<td align="right" class="grey" width="15%">经度:</td>	
        <td width="35%">
        <input type="text" name="shop.lng"  value="${shopPojo.lng}" class="floatLeft" id="ticketName"><span id="lng"></span></td>
      </tr>-->
      <tr>
        <td align="right" class="grey" width="15%">新品推荐:</td>	
        <td width="35%"><select name="shop.isNew" id="ticketType"  class="floatLeft">
							<c:forEach items="${isNew}" var="isNew">
										<option value="${isNew.value}"<c:if test="${shopPojo.isNew==isNew.value}">selected="selected" </c:if>>${isNew.name}</option>
								</c:forEach>
				    </select><div id="scale_mgId"></div></td>
		<td align="right" class="grey" width="15%">销售市场:</td>	
        <td width="35%">
        <input type="text" name="shop.salesArea"  value="${shopPojo.salesArea}" class="floatLeft" id="ticketName"><span id="salesArea"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey"  width="15%">首页推荐类型:</td>
        <td width="35%"><select name="shop.recommendType" id="ticketType"  class="floatLeft">
        					<option value="0"<c:if test="${shopPojo.recommendType==0}">selected="selected" </c:if>>不推荐</option>
							<c:forEach items="${recommendType}" var="recommendType">
										<option value="${recommendType.id}"<c:if test="${shopPojo.recommendType==recommendType.id}">selected="selected" </c:if>>${recommendType.name}</option>
								</c:forEach>
				    </select><div id="status_mgId"></div></td>
		<td align="right" class="grey" width="15%">排序:</td>	
        <td width="35%">
        <input type="text" name="shop.sorting"  value="${shopPojo.sorting}" class="floatLeft" id="ticketName"><span id="sorting"></span></td>
      		    
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">品牌LOGO:</td>	
        <td width="35%">
        <p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${shopPojo.images}' width='350px' /></p>
       
        <input type="file" name="upfile" class="floatLeft" id="ticketName">
        </br>
         <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（320 * 320）</font></p> </td>
        <td align="right" class="grey" width="15%">顶部主推商品图片:</td>	
        <td width="35%">
        <p><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/shop/${shopPojo.topImage}' width='350px' /></p>
        <input type="file" name="upfile2" class="floatLeft" id="ticketName">
        </br>
        <p> &nbsp;&nbsp; <font color="#df434e">上传图片规格（828*390）</font></p>
         </td>
      </tr>
        
       <tr>
        <td align="right" class="grey" width="15%">店铺简介:</td>	
        <td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="shop.content" id="content"   >${shopPojo.content}</textarea><span id="content"></span></td>
        <td align="right" class="grey" width="15%">主营商品:</td>	
        <td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="shop.mainProduct" id="mainProduct"   >${shopPojo.mainProduct}</textarea><span id="mainProduct"></span></td>
        </tr>
        <tr>
        <td align="right" class="grey" width="15%">商品评分:</td>	
        <td width="35%">
       
        <input type="text" name="shop.productCommt" style="font-color:grey;"  onfocus="this.value=''" onblur="if(this.value==''){this.value='5'}" value="${shopPojo.productCommt}" class="floatLeft" id="ticketName"><span id="productCommt"></span></td>
        <td align="right" class="grey" width="15%">发货评分:</td>	
        <td width="35%">
       
        <input type="text" name="shop.deliverCommt" style="font-color:grey;" onfocus="this.value=''" onblur="if(this.value==''){this.value='5'}" value="${shopPojo.deliverCommt}" class="floatLeft" id="ticketName"><span id="deliverCommt"></span></td>
        </tr>
        <tr>
        <td align="right" class="grey" width="15%">物流评分:</td>	
        <td width="35%">
       
        <input type="text" name="shop.logisticsCommt" style="font-color:grey;"  onfocus="this.value=''" onblur="if(this.value==''){this.value='5'}" value="${shopPojo.logisticsCommt}" class="floatLeft" id="ticketName"><span id="logisticsCommt"></span></td>
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

var sorting =new tt.Field(" 排序 ","shop.sorting").setMsgId("sorting");
var namead =new tt.Field(" 店铺名称 ","shop.name").setMsgId("namead");
var address =new tt.Field(" 地址 ","shop.address").setMsgId("address");
var salesArea =new tt.Field(" 销售市场 ","shop.salesArea").setMsgId("salesArea");
var phone =new tt.Field(" 联系号码 ","shop.phone").setMsgId("phone");
var content =new tt.Field(" 店铺简介 ","shop.content").setMsgId("content");
var mainProduct =new tt.Field(" 主营商品 ","shop.mainProduct").setMsgId("mainProduct");
var productCommt =new tt.Field(" 商品评分(1~5分) ","shop.productCommt").setMsgId("productCommt");
var deliverCommt =new tt.Field(" 发货评分(1~5分) ","shop.deliverCommt").setMsgId("deliverCommt");
var logisticsCommt =new tt.Field(" 物流评分(1~5分)","shop.logisticsCommt").setMsgId("logisticsCommt");
var mainCategory =new tt.Field(" 主营商品","shop.mainCategory").setMsgId("mainCategory_mgId");

tt.vf.req.add(sorting,namead,address,salesArea,phone,content,mainProduct,productCommt,deliverCommt,logisticsCommt);
new tt.SCV().set(1, "++").add(mainCategory); 
new tt.LV().set(0, 15).add(name);
	$(document).ready(function() {
		$("#sbutton").click(function(){		
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	

</script>