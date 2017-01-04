<%@ page language="java" contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" import="java.util.*" import="java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" />
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/common.js"></script>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/sysDict/sysDictCommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<title>修改商品</title>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">商品管理</a> &gt; <a href="productManage.do">商品信息管理</a> &gt; <a
				href="#">修改商品</a>
		</div>
	
	<div class="h15"></div>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<form action="productUpdate.do?productPojo.userId=${productPojo.userId}" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productPojo.id" id="productPojo.id"
						value="${productPojo.id}">
		<input type="hidden" name="productPojo.minimum" id="productPojo.minimum"
						value="${productPojo.minimum}"> 
		<input type="hidden" name="productPojo.image" id="productPojo.image"
						value="${productPojo.image}">
		<!--<input type="hidden" name="productPojo.isIntroduce" id="productPojo.isIntroduce"
						value="${productPojo.isIntroduce}">-->
		<input type="hidden" name="productPojo.status" id="productPojo.status"
						value="${productPojo.status}"> 
		<input type="hidden" name="productPojo.isNew" id="productPojo.isNew"
						value="${productPojo.isNew}"> 
		<input type="hidden" name="productPojo.recommend" id="productPojo.recommend"
						value="${productPojo.recommend}"> 
		<!-- 默认包邮 -->
		<input type="hidden" name="productPojo.postageType" id="productPojo.postageType"
						value="1"> 
		<input type="hidden" name="productPojo.ageType" id="productPojo.ageType" value="0"> 
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td align="right" width="20%" class="grey">商品类型:</td>
					<td>
<%-- 					<select name="productPojo.productTypeId" id="productPojo.productTypeId" onchange="selectTypeChange();">
						<s:iterator value="productTypes">
							<option value="<s:property value="value"/>" age="<s:property value="ageType" />"
							<s:if test="value == productPojo.productTypeId">selected="selected"</s:if>><s:property value="name" />
							</option>
						</s:iterator>
					</select> --%>
					<p>
					        <%-- <select name="productPojo.productType1" id="productType1" class="floatLeft" style="width:140px;"  onChange="changeProType()">
                            <option value="">----- 一级类目 -----</option>
							<s:iterator value="productType1List">
							<option value="<s:property value="id"/>" <s:if test="id == productPojo.productType1">selected="selected"</s:if>><s:property value="name"/></option>
							</s:iterator>
                            </select> --%>
                             <select class="floatLeft" name="productPojo.productTypeIds" id="productTypeIds" style="width:140px;" onChange="changePidType()"><option value="">----- 二级类目 -----</option>${typeIdsStr}</select>
                             <select class="floatLeft" name="productPojo.productTypeId" id="productTypeId" style="width:140px;" ><option value="">----- 二级类目 -----</option>${typeIdStr}</select>
					</p><br>        
					<p><span id="productType1_mgId"></span><span id="productTypeIds_mgId"></span>	<span id="productTypeId_mgId"></span></p></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品名称:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.productName" value="${productPojo.productName}" id="productName"> <span
						id="productName_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品货号:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.productNum" value="${productPojo.productNum}" id="productNum"> <span
						id="productNum_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">产地:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.location" value="${productPojo.location}" id="location"> <span
						id="location_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品简述:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.productSketch" value="${productPojo.productSketch}" id="productSketch"> <span
						id="productSketch_mgId"></span></td>
				</tr>
				<!--<tr>
					<td align="right" width="20%" class="grey">商品详情:</td>
					<td colspan="3">
					<textarea name="productPojo.content">${productPojo.content}</textarea>
					<span id="content_mgId"></span></td>
				</tr>-->
				<tr>
					<td align="right" width="20%" class="grey">年龄描述:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.ageDesc" value="${productPojo.ageDesc}" id="ageDesc"> <span
						id="ageDesc_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">标签描述:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.tag" value="${productPojo.tag}" id="tag">
					<span id="tag_mgId"></span><font color="#FF0000">请输入时使用顿号（、）分隔输入的条数</font></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品原价:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.sellingPrice" value="${productPojo.sellingPrice}" id="sellingPrice"> <span
						id="sellingPrice_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品价格:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.distributionPrice" value="${productPojo.distributionPrice}" id="distributionPrice"> <span
						id="distributionPrice_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">折扣:</td>
					<td>${productPojo.discount}折</td>
				</tr>
				<!--<tr>
					<td align="right" width="20%" class="grey">最低零售价:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.lowestPrice" value="${productPojo.lowestPrice}" id="lowestPrice"> <span
						id="lowestPrice_mgId"></span></td>
				</tr>-->
				<!--
				<tr>
			        <td align="right" class="grey"  width="20%">是否包邮:</td>
			        <td width="35%"><select name="productPojo.postageType" id="ticketType"  class="floatLeft">
										<c:forEach items="${isPower}" var="postageType">
													<option value="${postageType.value}" <c:if test="${productPojo.postageType==postageType.value}">selected="selected" </c:if>>${postageType.name}</option>
											</c:forEach>
									</select></td>
			    </tr>
			    -->
				<tr>
					<td align="right" width="20%" class="grey">重量(kg):</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.weight" value="${productPojo.weight}" id="weight"> <span
						id="weight_mgId"></span></td>
				</tr>
				<tr>
			        <td align="right" class="grey"  width="20%">单位:</td>
			        <td width="35%"><select name="productPojo.unit" id="ticketType"  class="floatLeft">
										<c:forEach items="${unit}" var="unit">
													<option value="${unit.value}" <c:if test="${productPojo.unit==unit.value}">selected="selected" </c:if>>${unit.name}</option>
											</c:forEach>
									</select></td>
			      </tr>
				<tr>
			        <td align="right" class="grey"  width="20%">品牌:</td>
			        <td width="35%">
			       <%--  <select name="productPojo.brand" id="options"  class="floatLeft">
										<c:forEach items="${brand}" var="brand">
													<option value="${brand.value}" <c:if test="${productPojo.brand==brand.value}">selected="selected" </c:if>>${brand.name}</option>
											</c:forEach>
									</select>
									<label>查询:</label>
									<input class="" type="text"
						name="op" id="op" onchange="option()"> --%>
						<select name="productPojo.userBrandId" id="" class="floatLeft">
                       <option value="">----全部----</option>
					   <s:iterator value="brandList">
					   <option value="<s:property value="id"/>" <s:if test="id == productPojo.userBrandId">selected="selected"</s:if>><s:property value="brandName"/></option>
					   </s:iterator>
                       </select>
						<span id="brand_mgId"></span></td>
			      </tr>
			      <tr>
			        <td align="right" class="grey"  width="20%">材质:</td>
			        <td width="35%"><select name="productPojo.texture" id="ticketType"  class="floatLeft">
			        					<option value="">--请选择--</option>
										<c:forEach items="${texture}" var="texture">
													<option value="${texture.value}" <c:if test="${productPojo.texture==texture.value}">selected="selected" </c:if>>${texture.name}</option>
											</c:forEach>
									</select></td>
			      </tr>
			      <!--
			      <tr>
			        <td align="right" class="grey"  width="20%">适用年龄:</td>
			        <td width="35%"><select name="productPojo.age" id="ticketType"  class="floatLeft">
										<c:forEach items="${age}" var="age">
													<option value="${age.value}" <c:if test="${productPojo.age==age.value}">selected="selected" </c:if>>${age.name}</option>
											</c:forEach>
									</select></td>
			      </tr>
			      -->
			      <tr>
			        <td align="right" class="grey"  width="20%">是否电动:</td>
			        <td width="35%"><select name="productPojo.isPower" id="ticketType"  class="floatLeft">
										<c:forEach items="${isPower}" var="isPower">
													<option value="${isPower.value}" <c:if test="${productPojo.isPower==isPower.value}">selected="selected" </c:if>>${isPower.name}</option>
											</c:forEach>
									</select></td>
			      </tr>
			      <tr>
			        <td align="right" class="grey"  width="20%">包装方式:</td>
			        <td width="35%"><select name="productPojo.pack" id="ticketType"  class="floatLeft">
			        					<option value="">--请选择--</option>
										<c:forEach items="${pack}" var="pack">
													<option value="${pack.value}" <c:if test="${productPojo.pack==pack.value}">selected="selected" </c:if>>${pack.name}</option>
											</c:forEach>
									</select></td>
			      </tr>
			      <tr>
			        <td align="right" class="grey"  width="20%">掌柜热卖:</td>
			        <td width="35%"><select name="productPojo.isHotsale" id="ticketType"  class="floatLeft">
										<c:forEach items="${isHotsale}" var="isHotsale">
													<option value="${isHotsale.value}" <c:if test="${productPojo.isHotsale==isHotsale.value}">selected="selected" </c:if>>${isHotsale.name}</option>
											</c:forEach>
									</select></td>
			      </tr>
			      <tr>
			        <td align="right" class="grey"  width="20%">是否推荐:</td>
			        <td width="35%"><select name="productPojo.isIntroduce" id="ticketType"  class="floatLeft">
										<c:forEach items="${isIntroduce}" var="isIntroduce">
													<option value="${isIntroduce.value}" <c:if test="${productPojo.isIntroduce==isIntroduce.value}">selected="selected" </c:if>>${isIntroduce.name}</option>
											</c:forEach>
									</select>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <font color="#df434e">（掌柜推荐，主推商品）</font></p>
									</td>
			      </tr>
			      <tr>
					<td align="right" class="grey">日期:</td><td><input name="productPojo.createDate"" value="${productPojo.creatDateString}" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/></td>
				<!--<tr>
					<td align="right" width="20%" class="grey">起订量:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.minimum" value="${productPojo.minimum}" id="minimum"> <span
						id="minimum_mgId"></span></td>
				</tr>-->
				<tr>
					<td align="right" width="20%" class="grey">商品图片:</td>
					<td>
						<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/${productPojo.image}" height="100px" />
						<input type="file" class="floatLeft" name="upfile" class="floatLeft" id="userPic">
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（320 * 320）</font></p>
					</td>
				</tr>
				
				<tr>
					<td align="right" width="20%" class="grey">销量基数:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.baseNumber" value="${productPojo.baseNumber}" id="baseNumber"> <span
						id="baseNumber_mgId"></span></td>
				</tr>	
				
				<tr>
					<td align="right" width="20%" class="grey">视频URL:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.videoUrl" value="${productPojo.videoUrl}" id="videoUrl"> <span
						id="videoUrl_mgId"></span></td>
				</tr>
				<%-- <tr>
					<td align="right" width="20%" class="grey">TV视频图片:</td>
					<td>
						<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/${productPojo.tvideoUrlImage}" height="100px" />
						<input type="file" class="floatLeft" name="upfile1" class="floatLeft" id="userPic">
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（828 * 412）</font></p>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">TV视频URL:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.tvideoUrl" value="${productPojo.tvideoUrl}" id="tvideoUrl"> <span
						id="tvideoUrl_mgId"></span></td>
				</tr> --%>
			</table>
		</div>

		<div class="Btn_div">
			<input name="" type="button" value="返回" class="back_btn"
				onclick="window.history.back()" /><input id="submitId"
				name="submitId" type="button" class="ok_btn" value="提 交" />
		</div>

	</form>
	</div>
</body>
<script>
var productType1 =new tt.Field(" 一级分类","productPojo.productType1").setMsgId("productType1_mgId");
var productTypeIds =new tt.Field(" 二级分类","productPojo.productTypeIds").setMsgId("productTypeIds_mgId");
var productTypeId =new tt.Field(" 三级分类","productPojo.productTypeId").setMsgId("productTypeId_mgId");
var productName =new tt.Field(" 商品名称","productPojo.productName").setMsgId("productName_mgId");
var productNum =new tt.Field(" 商品货号","productPojo.productNum").setMsgId("productNum_mgId");
var location_mgId =new tt.Field(" 产地","productPojo.location").setMsgId("location_mgId");
var productSketch =new tt.Field(" 商品简述","productPojo.productSketch").setMsgId("productSketch_mgId");
/* var ageDesc =new tt.Field(" 年龄描述","productPojo.ageDesc").setMsgId("ageDesc_mgId");
var tag =new tt.Field(" 标签描述","productPojo.tag").setMsgId("tag_mgId"); */
var distributionPrice =new tt.Field(" 商品价格","productPojo.distributionPrice").setMsgId("distributionPrice_mgId");
var sellingPrice =new tt.Field(" 商品原价","productPojo.sellingPrice").setMsgId("sellingPrice_mgId");
var baseNumber =new tt.Field(" 销量基数","productPojo.baseNumber").setMsgId("baseNumber_mgId");
var weight =new tt.Field("重量","productPojo.weight").setMsgId("weight_mgId");
var brand =new tt.Field(" 品牌","productPojo.userBrandId").setMsgId("brand_mgId");
var videoUrl =new tt.Field(" 视频URL","productPojo.videoUrl").setMsgId("videoUrl_mgId");
var tvideoUrl =new tt.Field(" TV视频URL","productPojo.tvideoUrl").setMsgId("tvideoUrl_mgId");
new tt.RV().set(new RegExp("^http\://[0-9A-z\.]+\.com/[0-9A-z/]*[0-9A-z]+\.mp4([\?].+){0,1}$"), "格式不正确").add(tvideoUrl,videoUrl);
	tt.vf.req.add(productType1,productTypeIds,productTypeId,productName,productNum,location_mgId,productSketch,sellingPrice,distributionPrice,weight,brand);
	//new tt.NRV().set(0, '++').add(stock);
	//tt.vf.int.add(stock);
	new tt.LV().set(0, 10).add(productSketch);
	tt.vf.num.add(weight);
	new tt.NRV().set(0, '++').add(weight);
	$(document).ready(function() {
		//selectTypeChange();
		$("#submitId").click(function(){	     
			if(tt.validate()){
				document.getElementById("from1").submit();
			}
		});
	});
	
	function selectTypeChange() {
		var sel = document.getElementById("productPojo.productTypeId");
		if(sel != null){
	    	var index=sel.selectedIndex;
	    	var age=sel.options[index].getAttribute("age");  
	    	document.getElementById("productPojo.ageType").value=age;
			if(age == 0){
				document.getElementById("productTypeId_mgId").innerHTML="无";
			}else if(age == 1){
				document.getElementById("productTypeId_mgId").innerHTML="0-3岁婴幼儿玩具";
			}else if(age == 2){
				document.getElementById("productTypeId_mgId").innerHTML="3-6岁儿童玩具";
			}else if(age == 3){
				document.getElementById("productTypeId_mgId").innerHTML="6岁以上玩具";
			}
		}
	}
	
	function option(){
		$("#options").empty();
		var text = $("#op").val();
		$.ajax(
				{
					type: "post",
					url: "addBrand.do",
					dataType: 'json',
					data: {"text":text},
					success: function (msg) {
						var o_msg = eval(msg);
						for (var i = 0; i < o_msg.length; i++) {
							$("#options").append("<option value=" + o_msg[i].value + ">" + o_msg[i].name + "</option>");
						}
					}
				}
			);
	}
	
	function changeProType(){
		var aVal =$("#productType1").val();
		$.ajax({
			url:"getProductType2.do?productTypePojo.level=1&productTypePojo.topLevel=" + aVal,
			success:function(data){
				var c = eval("(" + data + ")");
				$("#productTypeIds").html('<option value="">----- 二级类目 -----</option>');
				$("#productTypeId").html('<option value="">----- 三级类目 -----</option>');
				$("#productTypeIds").append(c[0].data);
			},
			error:function(){
				alert("error");
			}
		});
		
	}
	
	function changePidType(){
		var bVal = $("select[name='productPojo.productTypeIds']").val();
		$.ajax({
			url:"getProductType3.do?productTypePojo.pid=" + bVal,
			success:function(data){
				var c = eval("(" + data + ")");
				$("#productTypeId").html('<option value="">----- 三级类目 -----</option>');
				$("#productTypeId").append(c[0].data);
			},
			error:function(){
				alert("error");
			}
		});
		
	}
	
</script>
</html>

