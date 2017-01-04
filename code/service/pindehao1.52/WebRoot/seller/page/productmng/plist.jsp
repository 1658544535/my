<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
		<script type="text/javascript">
			var pageSize = 10;
			$(function(){
				$("#excel").click(function() {
					if(tt.validate()){
						var formParam = $("#sysform").serialize();
						$(location).attr('href', 'getProductExcel.do?'+formParam);
					}
				});
			});

			function allcb(){
				var checkbox=document.getElementById("selectcb");
				if(checkbox.checked==true){
					var code_Values = document.getElementsByName("tids") ;
					for(i = 0;i < code_Values.length;i++){
						if(code_Values[i].type == "checkbox")
						{
							code_Values[i].checked = true;
						}
					}

				}else{
					var code_Values = document.getElementsByName("tids") ;
					for(i = 0;i < code_Values.length;i++){
						if(code_Values[i].type == "checkbox")
						{
							code_Values[i].checked = false

						}
					}
				}

			}

			function deleteAll(){
				document.getElementById("idform").submit();
			}


			function del(val)
			{
				if(confirm("你真的想删除该记录么？"))
				{
					var url = "deleProductSeller.do?productPojo.id="+val;
					doOpreator(url,null);
				}else{
					return ;
				}

			}
			function doOpreator(url,params){
				MAOWU.ajax.get(url, params, goRefreshPage);
			}

			function goRefreshPage(result){

				var rand=Math.random() * ( 100000 + 1);
				if(result=="1"){
					alert("删除成功");
					queryData("productAllCountSellerWeb.do?productPojo.userId=${productPojo.userId}", "getProductAllListSellerWeb.do?productPojo.userId=${productPojo.userId}&randdelete="+rand,pageSize);
				}else{
					alert("删除失败");
				}
			}

			function check(val)
			{
				if(confirm("确认要通过审核吗？"))
				{
					var url = "checkProductSeller.do?productPojo.id="+val;
					doOpreator2(url,null);
				}else{
					return ;
				}

			}
			function doOpreator2(url,params){
				MAOWU.ajax.get(url, params, goRefreshPage2);
			}

			function goRefreshPage2(result){

				var rand=Math.random() * ( 100000 + 1);
				if(result=="1"){
					alert("恢复售卖成功");
					queryData("productAllCountSellerWeb.do?productPojo.userId=${productPojo.userId}", "getProductAllListSellerWeb.do?productPojo.userId=${productPojo.userId}&randdelete="+rand,pageSize);
				}else{
					alert("恢复售卖失败");
				}
			}

			function uncheck(val)
			{
				if(confirm("确认要取消审核吗？"))
				{
					var url = "uncheckProductSeller.do?productPojo.id="+val;
					undoOpreator(url,null);
				}else{
					return ;
				}

			}
			function undoOpreator(url,params){
				MAOWU.ajax.get(url, params, ungoRefreshPage);
			}

			function ungoRefreshPage(result){

				var rand=Math.random() * ( 100000 + 1);
				if(result=="1"){
					alert("暂停售卖成功");
					queryData("productAllCountSellerWeb.do?productPojo.userId=${productPojo.userId}", "getProductAllListSellerWeb.do?productPojo.userId=${productPojo.userId}&randdelete="+rand,pageSize);
				}else{
					alert("暂停售卖失败");
				}
			}

			/*批量审核*/
			function checkAll() {
				$("#idform").attr("action","productCheckSelllerWeb.do");
				$("#idform").submit();
			}
			/*批量取消审核*/
			function uncheckAll(){
				$("#idform").attr("action","productUncheckSelllerWeb.do");
				$("#idform").submit();
			}
		</script>
	</head>
	<body>
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
		<div id="content" class="wrapper">
			<div class="pure-g admin-wrapper">
				<div class="pure-u-1 main">
					<div class="sp-body">
						<h1 class="seller-title">
							商品库
						</h1>
						<div class="ui-table-container p20">
							<div class="ui-tab m-s-info" style="position: relative">
								<form action="getProductAllListSellerWeb.do" method="post" id="sysform">
									<input type="hidden" name="page.pageNo" value=0 id="pageNo">
									<table class="m-s-table" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<!-- <td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>商品ID：</label>
													<input class="ui-input w-mart-input J_enterForSearch" type="text" name="productPojo.id" id="productId" value=''/>
												</td> -->
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>商品序号：</label>
													<input class="ui-input w-mart-input J_enterForSearch" type="text"  name="productPojo.productNo" id="productNo" value=''/>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>商品货号：</label>
													<input class="ui-input w-mart-input J_enterForSearch" type="text"  name="productPojo.productNum" id="productNum" value=''/>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>商品名称：</label>
													<input class="ui-input w-mart-input J_enterForSearch" type="text" name="productPojo.productName" id="productName" value=''/>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>品牌：</label>
													<select name="productPojo.userBrandId" id="" class="w-mart-select ui-select-theme">
														<option value="">----全部----</option>
														<s:iterator value="brandList">
															<option value="<s:property value="id"/>" <s:if test="id == productPojo.userbrandId">selected="selected"</s:if>>
																<s:property value="brandName"/>
															</option>
														</s:iterator>
													</select>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label> 一级类目：</label>
                                                     <select name="productPojo.productType1" id="productType1" class="w-mart-select ui-select-theme" onChange="changeProType()">
                                                      <option value="">----全部----</option>
									                  <s:iterator value="productType1List">
										              <option value="<s:property value="id"/>" <s:if test="id == productPojo.productType1">selected="selected"</s:if>><s:property value="name"/></option>
									                  </s:iterator>
                                                    </select>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>二级类目：</label>
													<select name="productPojo.productTypeIds" id="productTypeIds" class="w-mart-select ui-select-theme" onChange="changePidType()">
                                                   <option value="">----全部----</option>${typeIdsStr}</select>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<label>三级类目：</label>
													<select name="productPojo.productTypeId" id="productTypeId" class="w-mart-select ui-select-theme" >
                                                    <option value="">----全部----</option>${typeIdStr}</select>
												</td>
												<td class="m-s-td1 m-s-td" style="padding-right:10px">
													<div class="search-btn" style="width:100px">
														<br/>
														<a href="#" id="query_btn" class="ui-button ui-btn-theme w-mart-button ui-button-mwhite" style="width: 80px;">
                                                             <i class="iconfont">&#xf012c;</i>
                                                           		  搜索
                                                        </a>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
							<div class="ui-fn clearfix">
								<a class="ui-button ui-button-lgreen fl" href="goProductAddSellerWeb.do"><i class="iconfont">&#xf0175;</i>添加商品</a>
								<!--<a class="ui-button ui-button-morange fl" onclick="deleteAll()"><i class="iconfont">&#xf0176;</i>批量删除</a>&nbsp;&nbsp;
								<a class="J_pauseSaleAll ui-button ui-btn-theme ui-btn-h26" onclick="checkAll()">批量恢复售卖</a>&nbsp;&nbsp;
								<a class="J_pauseSaleAll ui-button ui-btn-theme ui-btn-h26" onclick="uncheckAll()">批量暂停售卖</a>&nbsp;&nbsp;  -->
							</div>
							<div>
							<form action="productDeleteIdSelllerWeb.do" id="idform" method="post">
								<table class="ui-table product-list mt-20">
									<thead>
										<tr>
											<th>
											<input type="checkbox" class="J_checkedAll" id="selectcb" name="selectcb" onclick="allcb()" ></th>
											<!-- <th>商品ID</th> -->
											<th>商品序号</th>
											<th>图片</th>
											<th>名称</th>
											<th>品牌</th>
											<th>货号</th>
											<th>三级类目</th>
											<th>原价</th>
											<th>特卖价格</th>
											<th>录入时间</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="body"></tbody>
								</table>
							</form>
							<!-- 页码开始 -->
							<div class="shop-page">
								<div class="digg" id="Pagination"></div>
								<i id="rowcount" style="display:none;"></i>
								<i id="pagecount" style="display:none;"></i>
							</div>
							<!-- 页码结束 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>

<script type="text/javascript">

	//查询
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
		var rand=Math.random() * ( 100000 + 1);
		queryData("productAllCountSellerWeb.do", "getProductAllListSellerWeb.do?randquery=" + rand,pageSize);
	}

	/**
	*分页展现页面函数
	**/
	function installPage() {
		$("#body").append(
		"<tr>"+
		"<td><input name='tids' type='checkbox' value="+this.id+"></td>"+
		<!-- "<td>"+this.id+"</td>"+ -->
		"<td width='30px'>"+this.productNo+"</td>"+
		"<td><img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/product/"+this.image+"' alt='' width='98' height='98' /></td>"+
		"<td width='80px'><a target='_blank' href='goPreviewWeb.do?productPojo.id="+ this.id + "'><font color='#4c4c4c'>"+this.productName+"<br/></a>"+
		"</font></td>"+
		"<td>"+this.brandNames+"</td>"+
		"<td>"+this.productNum+"</td>"+
		"<td>"+this.productTypeName+"</td>"+
		"<td>"+this.sellingPrice+"</td>"+
		"<td>"+this.distributionPrice+"</td>"+
		"<td>"+this.creatDateString+"</td>"+
		"<td>"+this.statusName+"</td>"+
		"<td width='80px'><a target='_blank' href='goFindProductSellerWeb.do?productPojo.id="+ this.id + "'>编辑</a><br>"+
		//"<a href='javascript:;' onclick=del('"+ this.id + "')>删除</a><br>"+
		//"<a target='_blank' href='productFocusImagesSellerWeb.do?productFocusImages.productId="+this.id+"' class='edit_btn' )>焦点图片</a><br>"+
		"<a target='_blank' href='productImagesManageSellerWeb.do?productImagesPojo.productId="+this.id+"' class='edit_btn' )>商品图片</a><br>"+
		"<a target='_blank' href='goPreviewWeb.do?productPojo.id="+this.id+"' class='edit_btn' )>商品预览</a>"+
		//	        "<a href='javascript:;' class='edit_btn' onclick=check('"+this.id+"')>恢复售卖</a><br>"+
		//	        "<a href='javascript:;' class='edit_btn' onclick=uncheck('"+this.id+"')>暂停售卖</a>"+
		"</td></tr>");

	}

	$(function() {
		/** 首次要初始化分页**/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value='page.rowCount'/>, "getProductAllListSellerWeb.do?randIni="+rand,pageSize);
		$("#query_btn").click(query);
	});
	
function changeProType(){
	var aVal = $("select[name='productPojo.productType1']").val();
	$.ajax({
		url:"getProductType2.do?productTypePojo.level=1&productTypePojo.topLevel=" + aVal,
		success:function(data){
			var c = eval("(" + data + ")");
			$("#productTypeIds").html('<option value="">-----全部-----</option>');
			$("#productTypeId").html('<option value="">-----全部-----</option>');
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
			$("#productTypeId").html('<option value="">-----全部-----</option>');
			$("#productTypeId").append(c[0].data);
		},
		error:function(){
			alert("error");
		}
	});
	
}
</script>
