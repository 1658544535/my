<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<title>淘竹马分销平台</title>
<script>
$(document).ready(function(){
	$('.favorite_04-list li').hover(function(){
		$(this).find('.iconx').show();
	},function(){
		$(this).find('.iconx').hide();
	});
	
	$("#showMsgImg").hover(function(){
		$("#showMsg").show();
	},function(){
		$("#showMsg").hide();
	});
});

function onexportZip(){
	var tids=document.getElementsByName("tids"); 
	var num=0;
	for(var i=0;i<tids.length;i++){
		if(tids[i].checked){
			num=num+1;
		}
	}
	if(num>10){
		alert("您选择的数据超过十条，为了您的数据安全。请选择小于十条的数据！");
		return false;
	}
	$("#from1").attr("action","exportZip.do");
	$("#from1").submit();
	return;
}
function onexportExcel(){
	$("#from1").attr("action","getConsumerProductExcel.do");
	$("#from1").submit();
	return;
}
function onexportCSV(){
	$("#from1").attr("action","getConsumerProductCSV.do");
	$("#from1").submit();
	return;
}
function onTongbuTaobao(){
	var tids=document.getElementsByName("tids"); 
	var num=0;
	for(var i=0;i<tids.length;i++){
		if(tids[i].checked){
			num=num+1;
		}
	}
	if(num>10){
		if(window.confirm("您选择的数据超过十条，为了您的数据安全。请选择小于十条的数据！点击取消则继续操作，这可能造成您的数据不完整或者此操作失败！")){
			return false;
		}else{
			$("#from1").attr("action","insertProductToTaobao.do");
			$("#from1").submit();
			return;
		}
	}
	$("#from1").attr("action","insertProductToTaobao.do");
	$("#from1").submit();
	return;
}
function del(val)
{
	if(window.confirm("确定删除？")){ 
		//alert("确定"); 
		window.location.href="deluserConsumerCollectWeb.do?userConsumerCollectPojo.id="+val;
		return true; 
		}else{ 
		//alert("取消"); 
		return false; 
		} 
	
}
</script>
<!--下架商品图片变灰-->
<style type="text/css">
	.grayBlack { 
		-webkit-filter: grayscale(100%); 
		cursor:pointer;
		-moz-filter: grayscale(100%); 
		-ms-filter: grayscale(100%); 
		-o-filter: grayscale(100%); 
		filter: grayscale(100%); 
		filter: gray; 
	} 
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>

<div class="help-nav-width">
	<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">淘竹马<%-- <span class="register-txt02">收藏夹</span> --%></div>
        <div class="favorite_04-nav">
            <ul>
                <li><a href="goConsumerProductWeb.do"><span>我的分销商品库</span></a></li>
                <!-- <li><a href="favorite_02.html">已导出产品列表</a></li> -->
            </ul>
        </div>
    </div>
</div>

<form action="" method="post" id="from1">
<div class="favorite_01-Bg">
    <div class="favorite_04-list">
    	<ul>
    	<s:iterator value="consumerProductList">
    		<s:if test="proStatus==1">
        	<li>
            	<div class="favorite_04-list-Pic">
                    <a onclick="del(<s:property value='id'/>)" class="iconx"><div class="favorite_01-list-Pic-Ico02"><img src="images/favorite_01_05.png" alt="" width="24" height="24" /></div></a>
                    <a href="goProductDetail.do?productPojo.id=<s:property value='productId'/>"><img src="/upfiles/product/small/<s:property value='image'/>" alt="" width="220" height="221" />
                </div>
                <div class="favorite_04-list-txt01">
                	<div class="favorite_04-list-txt01-L">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></div>
                    <div class="favorite_04-list-txt01-R"><s:property value='sellNumber'/>销量</div>
                </div>
                <div class="favorite_04-list-txt02"><s:if test="productName.length()>16"><s:property value="productName.substring(0,16)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></div>
                <div class="favorite_04-list-txt03">
                	<div class="favorite_04-list-txt03-L"><img src="images/favorite_03_07.jpg" alt="" width="11" height="11" /> <s:property value='suserName'/></div>
                    <div class="favorite_04-list-txt03-R"><s:property value='location' default="广东澄海" /></div>
                </div></a>
                <br/><br/><br/>
                <input name="tids" type="checkbox" value="<s:property value='productId'/>" class="favorite_04-list-input" /> 
            </li>
            </s:if>
             <s:else>
            <li >
            <div style="background:#e3e3e3;opacity:0.34;">
            	<div class="favorite_04-list-Pic">
            	    <a class='iconx' onclick="del(<s:property value='id'/>)"><div class="favorite_01-list-Pic-Ico02"><img src="images/favorite_01_05.png" alt="" width="24" height="24" /></div></a>
            	    <a onclick="del(<s:property value='id'/>)"><img src="/upfiles/product/small/<s:property value='image'/>"  alt="" id="showMsgImg"  width="220" height="221" /></a>
                    <a class='iconx' onclick="del(<s:property value='id'/>)" id="showMsg" style="position: absolute; top: 100px; left: 60px;display:none;"><font color="f00000">该商品已经下架</font></a>
	            </div>
                <div class="favorite_04-list-txt01">
                	<div class="favorite_04-list-txt01-L">¥ <fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></div>
                    <div class="favorite_04-list-txt01-R"><s:property value='sellNumber'/>销量</div>
                </div>
                <div class="favorite_04-list-txt02"><s:if test="productName.length()>16"><s:property value="productName.substring(0,16)+'...'" /></s:if><s:else><s:property value="productName" /></s:else></div>
                <div class="favorite_04-list-txt03">
                	<div class="favorite_04-list-txt03-L"><img src="images/favorite_03_07.jpg" alt="" width="11" height="11" /> <s:property value='suserName'/></div>
                    <div class="favorite_04-list-txt03-R"><s:property value='location' default="广东澄海" /></div>
                </div></a>
              <!--  <br/><br/><br/>
                <div ><input disabled="disabled" type="checkbox" display="none" class="favorite_04-list-input" /> 该产品已下架</div>-->
                </div>
                
            </li>
            </s:else>
         </s:iterator>
        </ul>
    </div>
    <div class="clear"></div>
    <div class="favorite_04-page">
    	<label><input type="checkbox" name="all"  id="allcheck0" onclick='chkall("from1",this)' value=""/>&nbsp;&nbsp;&nbsp;全选</label>
    	<div class="digg" id="Pagination"></div>
    </div>
    <div class="favorite_04-input">
    	
    	<a href="javascript:onexportZip();" class="favorite_04-button">生成数据包</a> <!--<a href="javascript:onTongbuTaobao()" class="favorite_04-button">同步到淘宝店铺</a> -->
    </div>
    
</div>
</form>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<!--全选按钮-->
<script>
 function chkall(input1,input2)
	    {
	        var objForm = document.forms[input1];
	        var objLen = objForm.length;
	        var objSelectAll = 0;
	        for (var iCount = 0; iCount < objLen; iCount++)
	        {
	            if (input2.checked == true)
	            {
	                if (objForm.elements[iCount].type == "checkbox")
	                {
	                    objForm.elements[iCount].checked = true;
	                    //alert(objForm.elements[iCount].value);
	                    objSelectAll++;
	                }
	            }
	            else
	            {
	                if (objForm.elements[iCount].type == "checkbox")
	                {
	                    objForm.elements[iCount].checked = false;
	                    objSelectAll=0;
	                }
	            }
	        }
	        if(objSelectAll>0){
	        	objSelectAll -= 2;
	        }
           
            
	    }
</script>
<!--分页-->
<script type="text/javascript">
$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:10,	//每页显示数量
		next_text : '>',
		prev_text : '<',
		first_text: "|<",
		last_text: ">|",
		num_edge_entries: 2,
		num_display_entries: 4,
		prev_show_always : true,
		next_show_always : true,
		callback: pageselectCallback,
		load_first_page : function() {
			return false;
		}
	});
});


function pageselectCallback(pageindex, jq) {
	var pageNo = pageindex+1;
	window.location.href="goConsumerProductWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&userConsumerCollectPojo.userId=${userConsumerCollectPojo.userId}";
	return false;
}

</script>
</body>
</html>
