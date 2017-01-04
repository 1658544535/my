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
<link href="/css/my_supplier.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="/js/testJSP/js/validate/css/validate.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="/js/testJSP/js/validate/talent-validate-all.js"
	language="javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
<title>淘竹马首页</title>
<script>
		$(function() {
			$( "#tabs" ).tabs();
			
			var time = null;
			$('#zhaqsz').hover(function(){
				$('.drop').show();
			},function(){
				time = setTimeout(function(){
					$('.drop').hide();
				},50);
			});
			$(".drop").hover(function(){
				clearTimeout(time);
				$(this).show();
			},function(){
				time = setTimeout(function(){
					$('.drop').hide();
				},50);
			});
		});
	
		function fsubmit(obj){
			obj.submit();
			//if (tt.validate()){
				//执行提交表单脚本,验证所有文本框
			//}
		}
		
		function freset(obj){
			  obj.reset();
		}
		
</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>
<jsp:include page="my_supplier_head.jsp"></jsp:include>
<div class="faq-width">
	<jsp:include page="my_supplier_left.jsp"></jsp:include>
    
    <div class="my_supplier-L">
        
        <div class="my_buyer-Part03">
			<form action="supplyOrderListWeb.do" method="post" id="form1" name="form1">
	        	<div class="my_buyer-Part03-title">
	        		
		        		订单号 <input name="orderNo" onkeyup="if(this.value==0){this.value=this.value.replace(this.value.replace(/[^\d*]/,''),'');}else{this.value=this.value.replace(/[^\d*]/,'');}" type="text" class="my_supplier-input" value="" />
		        	<span class="my_supplier-input-txt">
		        		收货人 <input name="consignee" type="text" class="my_supplier-input" value="" />
		        	</span>
		        	<span class="my_supplier-input-txt">订单状态 
			        	<select name="orderStatus" class="my_supplier-input">
			        		<option value="0">--请选择--</option>
			        		<option value="1">待付款</option>
			        		<option value="2">已付款</option>
			        		<option value="3">已发货</option>
			        		<option value="4">已确认</option>
			        		<option value="5">已评论</option>
			        	</select>
		        	</span>	
		        	<img src="images/my_supplier_05.jpg" alt="" width="26" height="26" style="cursor:pointer;" onClick="javascript:fsubmit(document.form1);"/>
		        	<br/>
		        	<!-- <span id="orderNo_mgId"></span> -->
	        	</div>
        	</form>
        	</div>
        	
        	 <div class="my_supplier-Part02">
	        	<ul>
	                <li><a href="supplyOrderListWeb.do?orderStatus=1">待付款</a></li>
	                <li><a href="supplyOrderListWeb.do?orderStatus=2">已付款</a></li>
	                <li><a href="supplyOrderListWeb.do?orderStatus=3">已发货</a></li>
	                <li><a href="supplyOrderListWeb.do?orderStatus=4">已确认</a></li>
	            </ul>
        	</div>
        	
        	<div>
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier-Part03-table">
            	<tr>
                	<td>订单号</td>
                    <td>收货人</td>
                    <td>总金额</td>
                    <td>应付金额</td>
                    <td>订单状态</td>
                    <td>下单时间</td>
                    <td>操作</td>
                </tr>
                <ul id="body">
                <s:iterator value="webSupplyOrderList">
	                <tr>
	                	<td class="my_supplier-Part03-table-See"><a href="goSupplyOrderDetailWeb.do?orderIdWeb=<s:property value='id'/>"><s:property value="orderNo"/></a></td>
	                    <td><s:property value="consignee"/></td>
	                    <td><s:property value="allPrice"/></td>
	                    <td><font color="#FF0000"><s:property value="factPrice"/></font></td>
	                    <td><s:property value="orderStatusName"/></td>
	                    <td><s:property value="createDateString"/></td>
	                    <td class="my_supplier-Part03-table-See"><a href="goSupplyOrderDetailWeb.do?orderIdWeb=<s:property value='id'/>">查看</a></td>
	                </tr>
               </s:iterator>
              	 </ul>
            </table>
        </div>
        </br>
        <div class="shop-page">
        	<div class="digg" id="Pagination"></div>
		</div>
		
        <!-- 产品销售统计 -->
        <div class="my_buyer-Part04">
        	<div class="my_buyer-Part03-title"><img src="images/my_supplier_04.jpg" alt="" width="26" height="26" /> 产品销售统计</div>
            <div class="my_supplier-Part04-list">
	            <ul>
	            	<s:iterator value="productList">
		                <li><a href="goProductDetail.do?productPojo.id=<s:property value="id"/>"><div class="my_supplier-Part04-list-Pic"><img src="upfiles/product/<s:property value="image"/>" alt="" width="231" height="198" /></div></a>
		                       <div class="my_supplier-Part04-list-txt01"><s:property value="productName"/></div>
		                       <div class="my_supplier-Part04-list-txt02">销量：<font color="#FF0000"><s:property value="sellNumber"/></font></div>
		                 </li>
	                </s:iterator> 
	           </ul>
        	</div>
       </div>
    </div>
    
    <div class="my_supplier-R">
    	<!--<div class="my_supplier-R-Part01-title"><img src="images/my_supplier_06.jpg" alt="" width="26" height="26" /> 系统消息</div>
        <div class="my_supplier-R-Part01-list">
        	<ul>
            	<li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
            </ul>
        </div>
        
		<div class="my_supplier-R-Part02">
        	<div class="my_supplier-R-Part02-title" id="tabs">
            	<div class="my_supplier-R-Part02-title-Bj">
                    <ul>
                        <li><a href="#tabs-1">买家入门</a></li>
                        <li><a href="#tabs-2">卖家入门</a></li>
                    </ul>
                    <div class="my_supplier-R-Part02-title-more"><a href="#">更多</a></div>
                </div>
                <div class="clear"></div>
                <div class="my_supplier-R-Part01-list" id="tabs-1">
                    <ul>
                        <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                        <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                        <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                    </ul>
                    <div class="clear"></div>
                </div>
                
                <div class="my_supplier-R-Part01-list" id="tabs-2">
                    <ul>
                        <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                        <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                        <li><a href="#">【公告】关于知识产权规则修订则修订则修订则修订</a></li>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>-->
        
        <div class="my_supplier-R-Part01-title">热卖单品</div>
        <div class="my_supplier-R-Part01-list02">
        	<ul>
        		<s:iterator value="hotProductListSupplyWeb">
	               <li>
	                	<div class="my_supplier-R-Part01-list02-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value="id"/>"><img src="upfiles/product/<s:property value="image"/>" alt="" width="176" height="164" /></a></div>
	                	<div class="favorite_02-list-Txt01">分销商查看价格</div>
	                	<!--<div class="my_supplier-R-Part01-list02-txt01"><fmt:formatNumber value="${distributionPrice}" pattern="#0.00" /></div>-->
	                    <div class="my_supplier-R-Part01-list02-txt02"><s:property value="productName"/></div>
                	</li>
               </s:iterator>
            </ul>
        </div>
        
    </div>
</div>

<script type="text/javascript">
/*
	//暂时先这块:产品销售统计
	//分页展现页面函数 
	function installPage() {
		$("#body").append(
			"<ul>"+
                "<li> <div class=''><img src='upfiles/product/"+this.image+"' alt='' width='200' height='150' /></div>"+
                "<div class='my_supplier-Part04-list-txt01'>"+this.productName+"</div>"+
                "<div class='my_supplier-Part04-list-txt02'>销量：<font color='#FF0000'>"+this.sellNumber+"</font></div>"+
			    "</li></ul>");
	}
	$(function() {
		 //首次要初始化分页
		 var pageSize = 4;
		 var rand=Math.random() * ( 100000 + 1);
		 MAOWU.page.init(<s:property value='page.rowCount'/>,
				 "getProductAllListWeb.do?randIni="+rand,pageSize);
	});
*/	
	var orderNo = new tt.Field("订单号 ", "orderNo")
	.setMsgId("orderNo_mgId");
	tt.vf.num.add(orderNo);
	
	
	$(function() {
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
			current_page : <s:property value='page.pageNo'/>-1,
			items_per_page:20,	//每页显示数量
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
		window.location.href="supplyOrderListWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>&orderStatus=<s:property value='orderStatus'/>";
		return false;
	}
	
</script>
<form action="getProductAllListWeb.do" method="post" id="form1">
	<!-- <input type="hidden" name="page.pageNo" value=0 id="pageNo"> -->
	<input type="hidden" name="orderStatus" value=<s:property value="orderStatus"/> id="orderStatus">
</form>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
