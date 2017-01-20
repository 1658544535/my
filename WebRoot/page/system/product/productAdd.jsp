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
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/commonLoad.css" media="screen" rel="stylesheet">
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/productSku.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="/seller/css/default.css" type="text/css" media="all" />
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/bootstrap.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/productSku.css" type="text/css" media="all" />
<style type="text/css">
	.area{
		float: left;
		margin-left: 15px;
		margin-top: 10px;
		line-height: 26px;
        height: 26px;
	}
	.area a{
		line-height: 26px;
        height: 26px;
	    padding: 0 10px;
	    border: 1px solid #e6e6e6;
	    color: #666;
	    display: inline-block;
	    text-align: center;
	    text-decoration: none;
	    vertical-align: middle;
	    cursor: pointer;
	    font-family: verdana,Hiragino Sans GB;
	    border-radius: 2px;
	}
	.on{
		border:1px solid #FF5454;
	}
	.areaCtrl div{
		float: left;
		margin-left: 15px;
		margin-top: 10px;
		line-height: 26px;
        height: 26px;
	}
	.areaCtrl div a{
		font-weight : bold;
		line-height: 26px;
        height: 26px;
	    padding: 0 10px;
	    border: 1px solid #e6e6e6;
	    color: #666;
	    display: inline-block;
	    text-align: center;
	    text-decoration: none;
	    vertical-align: middle;
	    cursor: pointer;
	    font-family: verdana,Hiragino Sans GB;
	    border-radius: 2px;
	}
	.areaCount{
		color:red;
	}
	.areaInfo{
		margin-left: 15px;
		font-size: 14px;
	}
</style>
<script type="text/javascript">
document.onkeydown = function () {
    if (window.event && window.event.keyCode == 13) {
        window.event.returnValue = false;
    }
}
</script>
<title>新增商品</title>
</head>
<body>
	<div id="fullbg"></div>
	<div id="sending" style="width: 100%; height: 40px">
		<p class="close"></p>
		<p align="center">
			<img alt="加载中..." src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/image/loading.gif" height="30px" width="30px">
		</p>
	</div>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">商品管理</a> &gt; <a href="productManage.do">商品信息管理</a> &gt; <a href="#">新增商品</a>
		</div>
	<form action="productAdd.do?productPojo.userId=${productPojo.userId}" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="productPojo.version" id="" value="1">
		<input type="hidden" name="productPojo.ladderPrice" id="productPojo.ladderPrice"
						value="[]">
		<input type="hidden" name="productPojo.updateBy" id="productPojo.updateBy"
						value="${sessionScope.loginPojoId}">
		<!-- 默认包邮 -->
		<input type="hidden" name="productPojo.postageType" id="productPojo.postageType"
						value="1"> 
		<input type="hidden" name="productPojo.ageType" id="productPojo.ageType" value="0"> 
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="info_table">
				<tr>
					<td class="grey" colspan="4">商品基本信息</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品分类:</td>
					<td>
                        <select name="productPojo.productType1" id="productType1" class="floatLeft" onChange="changeProType()">
                            <option value="">----- 一级类目 -----</option>
							<s:iterator value="productType1List">
							<option value="<s:property value="id"/>" <s:if test="id == productPojo.productType1">selected="selected"</s:if>><s:property value="name"/></option>
							</s:iterator>
                        </select>
                        <select name="productPojo.productTypeIds" id="productTypeIds" class="floatLeft" style=""  onChange="changePidType();">
                            <option value="">----- 二级类目 -----</option>${typeIdsStr}
                        </select>
                        <select name="productPojo.productTypeId" id="productTypeId" class="floatLeft" style="" >
                            <option value="">----- 三级类目 -----</option>${typeIdStr}
                        </select>					     
					    <span id="productType1_mgId"></span> <span id="productTypeIds_mgId"></span>	<span id="productTypeId_mgId"></span>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品库存:</td>
					<td>
						<%-- <input class="floatLeft" type="text" name="productPojo.stock" id="stock" readonly="readonly" value="0">
						<span id="_mgId"></span> --%>
						<label class="stock" id="stock">0</label>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">每人最大购买数量:</td>
					<td>
					   <input class="floatLeft" type="text" name="productPojo.maxNum" id="maxNum" value="0">
						<span id="maxNum_mgId"></span> 
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">销售基数:</td>
					<td>
					   <input class="floatLeft" type="text" name="productPojo.baseNumber" id="baseNumber" value="">
						<span id="baseNumber_mgId"></span> 
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品最大开团次数:</td>
					<td>
					   <input class="floatLeft" type="text" name="productPojo.limitNum" id="baseNumber" value="">
						<span id="limitNum_mgId"></span> 
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品剩余开团次数:</td>
					<td>
					   <input class="floatLeft" type="text" name="productPojo.surplusNum" id="baseNumber" value="">
						<span id="surplusNum_mgId"></span> 
					</td>
				</tr>
				<tr>
					<td class="grey" colspan="4">商品拼团信息</td>
				</tr>
				<tr align="center">
					<td colspan="4">
						<table width="" border="0" cellpadding="0" cellspacing="0" class="info_table">
							<tr>
								<td align="" width="" class="grey">拼团人数</td>
								<td align="" width="" class="grey">拼团价格</td>
								<td align="" width="" class="grey">状态</td>
								<td align="" width="" class="grey"><i class="iconfont" onclick="addGroup()" title="添加">󰅵</i></td>
							</tr>
							<tr>
								<td>
									<input class="floatLeft" type="text" name="groupNums" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="10">
								</td>
								<td>
									<input class="floatLeft" type="text" name="groupPrices" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" maxlength="10">
								</td>
								<td>
									<select class="floatLeft" name="groupStatuss">
										<option value="1">上架</option>
										<option value="0">下架</option>
									</select>
								</td>
								<td>
									<!-- <a title="删除" name="removeGroupBox"><b style="font-style: italic;">X</b></a> -->
								</td>
							</tr>
							<tbody id="groupBox"></tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="grey" colspan="4">商品规格</td>
				</tr>
				<tr>
					<td colspan="4">
						<!------------------sku start ----------------------->
						<div class="container-fluid">
							<div class="row">
								<div class="col-sm-6 col-md-8 main">
									<div id="parameter">
										<div style="">
											<div style="float: left;margin-top: 6px;">请选择商品规格:</div>
											<div class="ggBtn gg1"><a>一种规格</a></div>
											<div class="ggBtn gg2"><a>二种规格</a></div>
											<%--<input type="button" class=""  style="width:100px;" id="sumbnt" value="提交" onclick="checkSkuJoin();">--%>
											<br><br>
											<input type="hidden" name="norm" id="norm"/>
											<!-- <div style="clear: both;margin-top: 10px;color:#aaa">如果没有找到您需要的规格名称请联系对接运营</div> -->
										</div>
										<div style="margin: 10px 0 20px 0" class="" id="sxId">
											<select class="sxSelect" name="sxType">
												<c:forEach items="${skuTypeList}" var="skuType" >
													<option value="${skuType.value}" >${skuType.name}</option>
												</c:forEach>
											</select>
										</div>
										<div "form-group" id="sx">
											<div class="form-group">
												<div id="color">
													<!-- <div class="parameter_color sxBtn" attValue="1" attName="brandcolor" rcdid="10" dicid="1" datatext="白色"><a class="delDiv"></a>白色</div> -->
												</div>
												<div class="addSX">
													<a class="add">+添加</a>
													<div class="addSX-main">
														<input type="text" />
														<span class="addSX-sure">确认</span>
														<span class="addSX-close">取消</span>
													</div>
												</div>
											</div>
										</div>
										<div style="margin: 10px 0 20px 0" class="display-none" id="gsId">
											<select class="gsSelect" name="gsType">
												<c:forEach items="${skuTypeList}" var="skuType" >
													<option value="${skuType.value}">${skuType.name}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group" id="gs">
											<div class="form-group">
												<div id="memory">
													<!-- <div class="parameter_memory sxBtn" attValue="2" attName="brandmemory" rcdid="20" dicid="2" datatext="x"><a class="delDiv"></a>x</div> -->
												</div>
												<div class="addGS" style="">
													<a class="add">+添加</a>
													<div class="addGS-main">
														<input type="text" />
														<span class="addGS-sure">确认</span>
														<span class="addGS-close">取消</span>
													</div>
												</div>
											</div> 
										</div>
										<div class="form-group" id="parameter_info" style="margin-top:30px;">
											<table class="table table-bordered">
												<thead>
												<th>规格一</th>
												<th class="gsdisplay">规格二</th>
												<th>商品编号</th> 
												<%-- <th>价格</th> --%>
												<th>库存</th>                                                                    
												<th>SKU预览图</th>                                                                    
												<th>状态</th>                                                                    
												</thead>
												<tbody id="parameter_info_tb"></tbody>
											</table>  
										</div>
									  </div>
								  </div>
							 </div>
						</div>
						<input type="hidden" id="skuListStr" name="skuListStr"/>
						<input type="hidden" id="attrListStr" name="attrListStr"/>
						<input type="hidden" id="delskuStr" name="delskuStr"/>
						<!------------------sku end ----------------------->
					</td>
				</tr>
				<tr>
					<td class="grey" colspan="4">商品详情信息</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品视频:</td>
					<td><input style="width: 500px;" class="floatLeft" type="text"
						name="productPojo.videoUrl" value="${productPojo.videoUrl}" id="videoUrl"> <span
						id="videoUrl_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品标题:</td>
					<td><input style="width: 500px;" class="floatLeft" type="text"
						name="productPojo.productName" id="productName"> <span
						id="productName_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品货号:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.productNum"  id="productNum"> <span
						id="productNum_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品市场价:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.sellingPrice" id="sellingPrice" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" maxlength="10"> <span
						id="sellingPrice_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">配送地址(包邮):</td>
					<td>
						<c:forEach items="${sysAreaPojos}" var="areaList">
							<c:choose>
							   <c:when test="${areaList.isOften=='1'}">  
								<div class="area on" dataId="${areaList.id}" isOften="${areaList.isOften}"><a>${areaList.name}</a></div>
							   </c:when>
							   <c:otherwise> 
							    <div class="area up" dataId="${areaList.id}" isOften="${areaList.isOften}"><a>${areaList.name}</a></div>
							   </c:otherwise>
							</c:choose>
						</c:forEach>
						<div class="clear"></div><br>
						<div class="areaInfo">已选择<span class="areaCount">25</span>个配送地址</div>
						<div class="areaCtrl">
							<div onclick="areaSelAll()"><a>全选</a></div>
							<div onclick="areaSelBack()"><a>反选</a></div>
							<div onclick="areaIsOften()"><a>常用地址</a></div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">独立购价格:</td>
					<td><input class="floatLeft" type="text"
						name="productPojo.distributionPrice" id="distributionPrice" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" maxlength="10"> <span
						id="distributionPrice_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品简介:</td>
					<td><textarea class="floatLeft" name="productPojo.productSketch" id="productSketch" style="width: 400px;height: 200px;"></textarea>
					<span id="productSketch_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">高清主图:</td>
					<td>
						<div class="uploadify main_img"style="position:relative;height: 200px; width: 400px;">
	                        <button class="uploadPreview_note"style="width:400px;height:200px;line-height:120px;"><i class="iconfont">&#xf00f7;</i>添加图片</button>
	                        <div class="uploadPreview_img"style="display:none;"> <img style="width:400px;height:200px;"/></div>
	                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/></br>
	                        <span id="image_mgId"></span>
                        </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（750 * 350）</font> 首页或列表页大图 a. 尺寸750 x 350px b. 大小100k内 c. 图片背景应以纯白为主 d.图片背景以纯白为主，商品团居中显示</p>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">高清缩略图:</td>
					<td>
						<div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                            <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;"><i class="iconfont">&#xf00f7;</i>添加图片</button>
                            <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile1" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>
                            </br><span id="image_mgId"></span>
                        </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（400 * 400）</font>订单列表图 a. 尺寸400 x 400px b. 大小100k c. 图片背景为纯白底</p>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品缩略图:</td>
					<td>
						<div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                            <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;"><i class="iconfont">&#xf00f7;</i>添加图片</button>
                            <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile2" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/></br>
                            <span id="image_mgId"></span>
                        </div>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（200 * 200）</font> 商品分类列表、搜索展示图 a. 尺寸200 x 200px b. 大小32k c. 图片背景为纯白底（服装除外）</p>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品轮播图:</td>
					<td>
						<div class="uploadify main_img"style="position:relative;height: 120px; width: 120px;">
                            <button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;"><i class="iconfont">&#xf00f7;</i>添加图片</button>
                            <div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div>
                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile2" name="upfiles" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/></br>
                           <span id="image_mgId"></span>
                        </div>
						<div id="imageBox"></div>
						<br /><i class="iconfont" onclick="addImage()">󰅵</i>
						<p>&nbsp;&nbsp; <font color="#df434e">上传图片规格（640 * 400）</font> 商品详情页顶端的轮播图 a. 尺寸640 x 400px b. 大小100k c. 数量在1-10之间 </p>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%" class="grey">商品详情图:</td>
					<td colspan="3">
						<textarea name="productPojo.content" id="content"></textarea>
						<script type="text/javascript">
							var ue = UE.getEditor("content");
						</script>
						<span id="content_mgId"></span>
					</td>
				</tr>
			</table>
		</div>
		<input id="faraway" name="productPojo.faraway" type="hidden"/>
		<div class="Btn_div float_btn" >
			<input name="" type="button" value="返回" class="back_btn"
				onclick="window.history.back()" /><input id="submitId"
				name="submitId" type="button" class="ok_btn" value="提 交" />
		</div>
	</form>
	</div>
</body>
<script>
var normType = "${productPojo.normType}";//判断选择的规格
var productType1 =new tt.Field(" 一级分类","productPojo.productType1").setMsgId("productType1_mgId");
var productTypeIds =new tt.Field(" 二级分类","productPojo.productTypeIds").setMsgId("productTypeIds_mgId");
var productTypeId =new tt.Field(" 三级分类","productPojo.productTypeId").setMsgId("productTypeId_mgId");
var productName =new tt.Field(" 商品名称","productPojo.productName").setMsgId("productName_mgId");
var productNum =new tt.Field(" 商品货号","productPojo.productNum").setMsgId("productNum_mgId");
//var baseNumber =new tt.Field("销售基数","productPojo.baseNumber").setMsgId("baseNumber_mgId");
//var location_mgId =new tt.Field(" 产地","productPojo.location").setMsgId("location_mgId");
var productSketch =new tt.Field(" 商品简述","productPojo.productSketch").setMsgId("productSketch_mgId");
var distributionPrice =new tt.Field(" 商品价格","productPojo.distributionPrice").setMsgId("distributionPrice_mgId");
var sellingPrice =new tt.Field(" 商品原价","productPojo.sellingPrice").setMsgId("sellingPrice_mgId");
var videoUrl =new tt.Field(" 视频URL","productPojo.videoUrl").setMsgId("videoUrl_mgId");
var maxNum =new tt.Field(" 限购","productPojo.maxNum").setMsgId("maxNum_mgId");
	tt.vf.req.add(productType1,productTypeIds,productTypeId,productName,productNum,productSketch,distributionPrice,sellingPrice);
	new tt.LV().set(0, 500).add(productSketch);
	tt.vf.num.add(maxNum);
	new tt.NRV().set(0, '++').add(maxNum);
	$(document).ready(function() {
		selectTypeChange();
		//计算偏远地区选择个数
		areaCount();
		$("#submitId").click(function(){
			//偏远地区
			var faraway = "";
			$(".up").each(function(){
				faraway += $(this).attr('dataId')+",";
			});
			faraway = faraway.substring(0,faraway.length-1)
		    $("#faraway").val(faraway);
			if($("#norm").val() == 2 && $(".sxSelect").val() == $(".gsSelect").val()){
				alert("sku两个规格不能一样!");
				return;
			}
			//sku input 输入验证
			var flag = true; 
			$("#parameter_info_tb .stock_price_blur").each(function(){
				if(($(this).val() == "" || $(this).val() == "undefined") && $(this).attr("data_attr") != "skuImage"){
					flag = false;
				}
			});
			//sku 图片验证
			$("#parameter_info_tb img").each(function(){
				if($(this).attr("src").length == 0){
					flag = false;
				}
			});
			if(flag == false){
				alert("请填写完整sku信息");
				return;
			}
			
			//skujson传到后台
			$("#skuListStr").val(JSON.stringify(stock_price_data));
			$("#attrListStr").val(JSON.stringify(sku_attr));
			$("#delskuStr").val(JSON.stringify(del_obj));
			
			var groupNums = $("input[name='groupNums']");
			var groupPrices = $("input[name='groupPrices']");
			var skuTypes = $("input[name='skuTypes']");
			var skuColors = $("input[name='skuColors']");
			var skuStocks = $("input[name='skuStocks']");
			var businessCodes = $("input[name='businessCodes']");
			var skuFiles = $("input[name='skuFiles']");
			
			for(var i = 0; i < groupNums.length; i++){
				if(groupNums[i].value == "" || groupPrices[i].value == ""){
					alert("请填写商品拼团信息！~");
					return;
				}
			}
			
			for(var i = 0; i < skuTypes.length; i++){
				if(skuTypes[i].value == "" || skuColors[i].value == "" || skuStocks[i].value == "" || businessCodes[i].value == "" || skuFiles[i].value == ""){
					alert("请填写商品规格！~");
					return;
				}
			}
			
			if(tt.validate()){
			    $("#fullbg").css({  
			    zIndex: 1000,
			    height:$(document.body).height(),  
			    width:"100%",  
			    display:"block"  	
			    });  
			    $("#sending").css({  
			    zIndex: 1002,
			    background:"none",
			    border:	"none"
			    });  
			    $("#sending").show();
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
		var aVal = $("select[name='productPojo.productType1']").val();
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
	
	//图片更换
	$(document).delegate(".uploadPreview_imgfile","change",function(){
		var _this = $(this);
		var url = _this.val();
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(_this.get(0).files[0]);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(_this.get(0).files[0]);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
		}
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
	});
	
	//图片更换
	$(document).delegate(".uploadPreview_imgfile2","change",function(){
		var _this = $(this);
		var url = _this.val();
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(_this.get(0).files[0]);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(_this.get(0).files[0]);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
		}
		_this.siblings(".uploadPreview_img").find("img").attr("src", url);
		_this.siblings(".uploadPreview_img").show();
		_this.siblings(".uploadPreview_note").hide();
		addImage();
	});
	
	var ii = 1;
	function addImage(){
		var lunbotuNum = $(".lunbotu").length;
		if(lunbotuNum < 9){
			ii++;
			$("#imageBox").append('<div class="uploadify main_img lunbotu"style="position:relative;height: 120px; width: 120px;margin-top: 10px;"><a style="z-index : 1000;position : absolute;right : 10px;top : 10px;font-style : italic;" class="" name="removeBox"><b>X</b></a><button class="uploadPreview_note"style="width:120px;height:120px;line-height:120px;"><i class="iconfont">&#xf00f7;</i>添加图片'+ ii +'</button><div class="uploadPreview_img"style="display:none;"> <img style="width:120px;height:120px;"/></div><input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile2" name="upfiles" data-id="\'\+dataId\+\'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/><br /><span id="image'+ ii +'_mgId"></span></div> ');
		}else{
			alert("轮播图最多只能添加10张图片！~");
		}
	}

	$("body").on("click", "a[name='removeBox']", function () {
		ii--;
		$(this).parent().remove();
	});
	
	function addGroup(){
		$("#groupBox").append('<tr><td><input class="floatLeft" type="text" name="groupNums" onkeyup="this.value=this.value.replace(/\\D/g,\'\')" onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" maxlength="10"></td>'+
		'<td><input class="floatLeft" type="text" name="groupPrices" onkeyup="this.value=this.value.replace(/[^\\d.]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^\\d.]/g,\'\')" maxlength="10"></td>'+
		'<td><select class="floatLeft" name="groupStatuss"><option value="1">上架</option><option value="0">下架</option></select></td><td><a title="删除" name="removeGroupBox"><b style="font-style: italic;">X</b></a></td></tr>');
	}
	
	$("body").on("click", "a[name='removeGroupBox']", function () {
		var p = $(this).parent();
		p.parent().remove();
	});
	
	
	$("body").on("click", "a[name='removeSkuBox']", function () {
		var p = $(this).parent();
		p.parent().remove();
		addStock();
	});
	
	function addStock(){
		var stocks = 0;
		var skuStocks = $("input[name='skuStocks']");
		for(var i = 0;i < skuStocks.length;i++){
			if(skuStocks[i].value != ''){
				stocks += parseInt(skuStocks[i].value);
			}
		}
		$("#stock").html(stocks);
	}
	
	//偏远地区监听
	 $(document).delegate(".area", "click", function(){
       	if($(this).hasClass('on')){
       		$(this).removeClass('on');
       		$(this).addClass('up');
       		areaCount();
       	} else {
       		$(this).removeClass('up');
       		$(this).addClass('on');
       		areaCount();
       	}
     });
	//计算选择地址个数
	function areaCount(){
		$(".areaCount").text($(".on").size());
	}
	//全选
	function areaSelAll(){
		$('.area').removeClass('up');
		$('.area').addClass('on');
		areaCount();
	}
	
	//反选
	function areaSelBack(){
		$(".area").each(function () {  
			if($(this).hasClass('on')){
	       		$(this).removeClass('on');
	       		$(this).addClass('up');
	       		areaCount();
	       	} else {
	       		$(this).removeClass('up');
	       		$(this).addClass('on');
	       		areaCount();
	       	}
        });
	}
	
	//常用地址
	function areaIsOften(){
		$(".area").each(function () {  
			if($(this).attr('isOften') == 0){
	       		$(this).removeClass('on');
	       		$(this).addClass('up');
	       		areaCount();
	       	} else {
	       		$(this).removeClass('up');
	       		$(this).addClass('on');
	       		areaCount();
	       	}
        });
	}
</script>

</html>
