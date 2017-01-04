<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
    </head>
    <script type="text/javascript">
    var pageSize = 10;
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
			var url = "deleProductFocusImagesGeekWeb.do?productFocusImages.productId=${productFocusImagesPojo.productId}&productFocusImages.id="+val;	
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
			queryData("getProductFocusImagesCountGeekWeb.do?productFocusImages.productId=${productFocusImagesPojo.productId }", "productFocusImagesAllListGeekWeb.do?productFocusImages.productId=${productFocusImagesPojo.productId}&randdelete="+rand,pageSize);
		}else{
			alert("删除失败");
		}
	}
	</script>
	<body>
			<jsp:include page="../geekHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .ui-form-item select{height:25px;line-height:25px;border:1px solid #D8D8D8;width:100px;}.ui-form-item input[type=text]{height:25px;border:1px solid #D8D8D8;line-height:25px;padding:0 5px;}.ui-btn-theme{background:#f5f5f5;padding:0 10px;border:1px solid #e6e6e6;color:#666;font-size:12px;}.ui-btn-theme i{font-size:14px;margin-right:-2px;vertical-align:top;}a.ui-btn-theme:hover{color:#ff4965;border:1px solid #ff4965;background:#FFF;}.ui-input:hover,.ui-select-theme:hover{border:1px solid #888!important;}.qc_statuc_innercont{position:relative;*z-index:12;}.status_cont .iconfont{color:#999;display:inline-block;font-size-adjust:none;font-size:10px;-webkit-transform:scale(.8);cursor:pointer;}.qc_statuc_innercont{position:relative;*z-index:12;}.status_cont .qc_status_note{display:none;border-radius:3px;position:absolute;background:#eee;border:1px solid #ececec;top:25px;left:-50px;width:100px;padding:5px;z-index:20;*zoom:1;}.triangle{position:absolute;top:-16px;left:49px;width:0;height:0;border-width:8px;border-style:solid;border-color:transparent transparent #eee;}
                        </style>
                        <h1 class="geek-title">
                            商品焦点图集
                            <div class="geek-title-head-more">
                            	<s:if test="productFocusImages.productId != null">
                                <a class="ui-button ui-button-lgreen" href="goAddProductFocusImagesGeekWeb.do?productFocusImages.productId=${productFocusImages.productId }" >
                                   <i class="iconfont">&#xf0175;</i>
                                   添加商品图片
                                </a>
                                </s:if>
                                <a class="ui-button ui-button-morange" onclick="deleteAll()" >
                                    <i class="iconfont">&#xf0176;</i>
                                    批量删除
                                </a>
                            </div>
                        </h1>
                        <!-- search start -->
                        <div class="geek-search-form" style="margin: 8px 0 -14px -8px;">
                            <form action="#" method="post" accept-charset="utf-8" id="sysform">
                                <div class="ui-box-content">
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label" style="width: 36px;">
                                            搜索
                                        </label>
                                    </div>
                                    <div class="ui-form-item">
                                        <input class="ui-input w-mart-input" type="text" name="productFocusImages.images" value="" placeholder="请输入图片名称" style="width:150px; " id="images">
                                    </div>
                                    <div class="ui-form-item search-btn">
                                    	<input type="hidden" name="page.pageNo" value=0 id="pageNo">
                                        <a id="query_btn" href="#" class="ui-button ui-btn-theme w-mart-button ui-button-mwhite">
                                        	<i class="iconfont">&#xf012c;</i>
                                            查询
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- search end -->
                        <div class="ui-table-container p20 martshow-index">
                         <form action="productFocusImagesDeleteIdGeekWeb.do?productFocusImages.productId=${productFocusImages.productId}" id="idform" method="post">
                            <table class="ui-table">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" class="J_checkedAll" id="selectcb" name="selectcb" onclick="allcb()" ></th>
					                    <th>产品</th>
						                <th>货号</th>
						                <th>图片</th>
						                <th>图片名称</th>
						                <th>排序</th>
						               	<th>状态</th>
						               	<th>操作</th>
                                    </tr>
                                </thead>
                                <tbody id="body"></tbody>
                            </table>
                          </form>
                            <!-- 页码开始 -->
                            <div class="pagination" id="Pagination">
                            </div>
                            <!-- 页码结束 -->
                        </div>
                        
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../geekFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
	//查询
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	function query() {
			var rand=Math.random() * ( 100000 + 1);
			queryData("getProductFocusImagesCountGeekWeb.do?productFocusImages.productId=${productFocusImagesPojo.productId }", "productFocusImagesAllListGeekWeb.do?productFocusImages.productId=${productFocusImagesPojo.productId }&randquery="+rand,pageSize);
	}
	
	/**
	 *分页展现页面函数 
	 **/ 
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td width='140px'><a href='goProductDetail.do?productPojo.id="+ this.id + "'><font color='#4c4c4c'>"+this.productName+"<br/></a>"+"</font></td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/productFocusImages/"+ this.images + "' height='100px' /></td>"+
				"<td>"+ this.images + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.statusName + "</td>"+
			    "</td><td><a class='edit_btn' href='goFindProductFocusImagesGeekWeb.do?productFocusImages.id="+this.id +"&productFocusImages.userId=${productFocusImages.userId}'>编辑</a></br>"+
				"<a href='javascript:;' class='del_btn' onclick=del('"+this.id+"')>删除</a></td></tr>");
	}
	
	$(function() {
		 /** 首次要初始化分页**/
		 var rand=Math.random() * ( 100000 + 1);
		 MAOWU.page.init(<s:property value='page.rowCount'/>,
				 "productFocusImagesAllListGeekWeb.do?productFocusImages.productId=${productFocusImagesPojo.productId }&randIni="+rand,pageSize);
		 $("#query_btn").click(query);
	});
	
</script>