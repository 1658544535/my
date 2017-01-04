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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马玩具分销平台</title>
<script>
		function fsubmit(obj){
			  obj.submit();
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
			<form action="getShipOrderAllListWeb.do" method="post" id="form1" name="form1">
	        	<div class="my_buyer-Part03-title">
		        	订单号 <input name="orderId" type="text" class="my_supplier-input" value="" />
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
		        	
		        	<img src="images/my_supplier_05.jpg"  alt="搜索" title="搜索" width="38" height="38" style="cursor:pointer;" onClick="javascript:fsubmit(document.form1);"/>
	        	</div>
        	</form>
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="my_supplier-Part03-table">
            	<tr>
                	<td>订单号</td>
                    <td>收货人</td>
                    <td>收货地址</td>
                    <td>总金额</td>
                    <td>应付金额</td>
                    <td>订单状态</td>
                    <td>下单时间</td>
                    <td>操作</td>
                </tr>
                <s:iterator value="shipOrderListWeb">
		    			<tr>
		    				<td>
				                <span class=""><s:property value='id'/></span>
			    			</td>
		    				<td>
				                <span class=""><s:property value='consignee'/></span>
			    			</td>
		    				<td>
			                   <span class=""><s:property value='consigneeAddress'/></span>
		    				</td>
		    				<td>
			                   <span class=""><s:property value='allPrice'/></span>
		    				</td>
		    				<td>
				                <span class=""><s:property value='factPrice'/></span>
		    				</td>
		    				<td>
				                <span class=""><s:property value='orderStatusName'/></span>
		    				</td>
		    				<td>
				                <span class=""><s:property value='createDateString'/></span>
		    				</td>
		    				<td>
		    					<s:if test=" orderStatus >= 3 ">
				                	<span class="userShipManage-table"><a href="" >查看发货详情</a></span>
				                </s:if>
				                <s:else>
				                	<s:if test="orderStatus <= 3">
				                		<span class="userShipManage-table"><a href="" >未发货</a></span>
				                	</s:if>
    							</s:else>
		    				</td>
			            </tr>
			   </s:iterator>
            </table>
        </div>
        <div class="clear"></div>
        <div class="userShipManage-page">
        	<div class="digg" id="Pagination"></div>
		</div>
    </div>
    
</div>

<script type="text/javascript">
$(function() {
	//alert(123);
	$("#Pagination").pagination(<s:property value='page.rowCount'/>, {
		current_page : <s:property value='page.pageNo'/>-1,
		items_per_page:12,	//每页显示数量
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
	alert(123);
	var pageNo = pageindex+1;
	window.location.href="shipManageWeb.do?page.pageNo="+pageNo+"&page.rowCount=<s:property value='page.rowCount'/>";
	return false;
}

</script>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
