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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/pagination/jquery.pagination.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/sys_util_web.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销商</title>
<script>
$(function() {
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
</script>

<script type="text/javascript">
	$(function(){
		$.ajax({
		url:'orderlist.do?order.orderStatus=${orderPojo.orderStatus}',
		type:'post',
		dataType: 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
    	},
    	success: function(result){
    		valueShop(result);
    	}
		});
	});
	function openclick(s){
		if(confirm("确认吗？")){
		var url="updateOrderStatus.do?orderPojo.id="+s+"&orderPojo.orderStatus=3";
			   $.ajax({
				url:url,
				type:'post',
				error: function(XMLHttpRequest, textStatus, errorThrown){
    		},
    		success: function(result2){
    			window.location.href = "orderWeb.do";
    		}
			});
		}
		else{
			return;
		}
	}
	function valueShop(result){
		var data = eval(result);
		$("#shop_list").html("");
		for(var i=0;i<data.length;i++){
		
		
			$("#shop_list").append(
			"<li>"+
	        "<table border='0' cellpadding='0' cellspacing='0' width='100%' class='buy-R-Part03-table' id='shop"+data[i].id+"'>"+
	        "<tr>"+
	        //"<td class='buy-R-Part03-table-title01'><input type='hidden' id='ostatus"+data[i].id+"' value='"+data[i].orderStatus+"'/><input type='checkbox' name='' value=''/>  "+data[i].createDateString+"</td>"+
	        "<td class='buy-R-Part03-table-title01'><input type='hidden' id='ostatus"+data[i].id+"' value='"+data[i].orderStatus+"'/>"+data[i].createDateString+"</td>"+
	        "<td class='buy-R-Part03-table-title02'><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].id+"' class=''>订单号： "+data[i].orderNo+"</a></td>"+
	        "<td class='buy-R-Part03-table-title03' colspan='3'>"+data[i].shopName+"</td>"+
	        "<td class='buy-R-Part03-table-title03' colspan='3'>送货方式："+data[i].consigneeTypeName+"</td>"+
	        "<td style='background:#f4f8fb; text-indent:90px;'><a class='del_btn' onclick='del("+data[i].id+","+data[i].orderStatus+")'><!--|<img src='images/favorite_02_08.jpg' alt='' width='18' height='18' />--> </a></td>"+
	        //"<td style='background:#f4f8fb; text-indent:90px;'></td>"+
	        "</tr>");
	        
	        //alert('orderDetaillist.do?order.orderStatus=${orderPojo.orderStatus}&orderDetail.orderId='+data[i].id);
	        $.ajax({
				url:'orderDetaillist.do?order.orderStatus=${orderPojo.orderStatus}&orderDetail.orderId='+data[i].id,
				type:'post',
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown){
    		},
    		success: function(result2){
    		
    		
    			valueProduct(result2);
    		}
			});
	        
	        $("#shop_list").append("</table>"+
	        "</li>");
		}
	}
	function valueProduct(result){
		var data = eval(result);
			for(var i=0;i<data.length;i++){
				var appendStr = "";
				var returns=data[i].reStatus;
				var isCancelOrder=data[i].isCancelOrder;
				var name="";
				var name2="";
				if(returns==0){
						if($("#ostatus"+data[i].orderId).val()==1){
                			name="";
                		}else if($("#ostatus"+data[i].orderId).val()==2){
                			name="<a href='goReturnGoodsWeb.do?orderDetail.id="+data[i].id+"&os=1'>退款</a>";
                		}else if($("#ostatus"+data[i].orderId).val()==3){
                			name="<a href='goReturnGoodsWeb.do?orderDetail.id="+data[i].id+"&os=1'>退款</a>&nbsp&nbsp/&nbsp&nbsp<a href='goReturnGoodsWeb.do?orderDetail.id="+data[i].id+"&os=2' >退货</a>";
                		}else if($("#ostatus"+data[i].orderId).val()==4){
                        	name="<a href='goReturnGoodsWeb.do?orderDetail.id="+data[i].id+"&os=3' >售后退货</a>";
                        }else if($("#ostatus"+data[i].orderId).val()==5){
                        	name="<a href='goReturnGoodsWeb.do?orderDetail.id="+data[i].id+"&os=3' >售后退货</a>";
                        }
                  }else{
         				if(returns==1){
         					name2="审核中";
         				}
         				else if(returns==2){
         					name2="<a href='goReturnsSellerWeb.do?orderDetail.productId="+data[i].productId+"&orderDetail.orderId="+data[i].orderId+"&orderDetail.id="+data[i].id+"' >退货</a>";
         				}
         				else if(returns==3){
         					name2="退货中";
         				}
         				else if(returns==4){
         					name2="退货成功";
         				}
         				else if(returns==5){
         					name2="退货/退款失败";
         				}
         				else if(returns==6){
         					name2="审核失败";
         				}
         				else if(returns==7){
         					name2="退款成功";
         				}
                   }
					appendStr +=
	         			"<td width='120' class='buy-R-Part03-table-Pic'><a href='goProductDetail.do?productPojo.id="+data[i].productId+"'><img src='/upfiles/product/"+data[i].productImages+"' alt='' width='79' height='80' /></td>"+
	            		"<td width='220' class='buy-R-Part03-table-txt01'><a href='goProductDetail.do?productPojo.id="+data[i].productId+"'>"+data[i].productName+"<p class='buy-R-Part03-table-txt01-color'><!--颜色分类：-->"+data[i].color+"<br />"+data[i].size+"</p></td>"+
	            		"<td width='140' class='buy-R-Part03-table-txt02'>￥"+data[i].stockPrice+"</td>"+"<td></td>"+
                		"<td width='30' class='buy-R-Part03-table-txt02'>"+data[i].num+"</td>"+
                		"<td width='100' class='buy-R-Part03-table-txt02'>"+name+"</td>"+
                		"<td width='100' class='buy-R-Part03-table-txt03'>￥"+data[i].sumtotal+"</td>"+
                		"<td width='100' class='buy-R-Part03-table-txt02'>"+name2+"</td>"+
                		"<td width='100' class='buy-R-Part03-table-txt03'><!--<a href='#'>快件已签收</a><br /><a href='#'>订单详情</a><br />--><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].orderId+" ' ><!--<span>查看物流</span>--></a></td>";
                if(i==0){
                	if(isCancelOrder==1){
                		appendStr += "<td width='100' class='buy-R-Part03-table-txt04' rowspan='"+data.length+"'><a href='#' class='buy-R-Part03-table-txt04-button02'>交易关闭</a></td>";
                	}else{
                	if($("#ostatus"+data[i].orderId).val()==1){
                			appendStr += "<td width='100' class='buy-R-Part03-table-txt04' rowspan='"+data.length+"'><a  href='goOrderDetailPay.do?orderPojo.id="+data[i].orderId+" ' class='buy-R-Part03-table-txt04-button'>立即付款</a>&nbsp<a  href='isCancel.do?order.id="+data[i].orderId+"&os=1' class='buy-R-Part03-table-txt04-button'>取消订单</a><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].orderId+" ' ><br/><span style='text-align:center;color:#df434e;font-size:12px;display:block; height:30px;'>订单详情</span></a></td>";
                		}else if($("#ostatus"+data[i].orderId).val()==2){
                			appendStr += "<td width='100' class='buy-R-Part03-table-txt04' rowspan='"+data.length+"'><a href='#' class='buy-R-Part03-table-txt04-button02'>等待发货</a><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].orderId+" ' ><br/><span style='text-align:center;color:#df434e;font-size:12px;display:block;'>订单详情</span></a></td>";
                		}else if($("#ostatus"+data[i].orderId).val()==3){
                			appendStr += "<td width='100' class='buy-R-Part03-table-txt04' rowspan='"+data.length+"'><span style='text-align:center;color:#df434e;font-size:12px;display:block;'>卖家已发货</span><br/><a  class='buy-R-Part03-table-txt04-button' onclick=openclick("+data[i].orderId+")>确认收货</a><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].orderId+" ' ><br/><span style='text-align:center;color:#df434e;font-size:12px;display:block;'>订单详情</span></a></td>";
                		}else if($("#ostatus"+data[i].orderId).val()==4){
                        	appendStr += "<td width='100' class='buy-R-Part03-table-txt04' rowspan='"+data.length+"'><a href='goUserCommentWeb.do?orderPojo.id="+data[i].orderId+" ' class='buy-R-Part03-table-txt04-button'>订单评论</a><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].orderId+" ' ><br/><span style='text-align:center;color:#df434e;font-size:12px;display:block;'>订单详情</span></a></td>";
                        }else if($("#ostatus"+data[i].orderId).val()==5){
                        	appendStr += "<td width='100' class='buy-R-Part03-table-txt04' rowspan='"+data.length+"'><a href='#' class='buy-R-Part03-table-txt04-button02'>已评论</a><a href='goMyOrderDetailWeb.do?orderPojo.id="+data[i].orderId+" ' ><br/><span style='text-align:center;color:#df434e;font-size:12px;display:block;'>订单详情</span></a></td>";
                        }
                        }
				}
				$("#shop"+data[i].orderId).append("<tr>"+appendStr+"</tr>");
			}
	}
	
	function del(val,status)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "deleOrder.do?order.id="+val;	
			doOpreator(url,null,status);
		}else{
			return ;
		}
		
	}
	function doOpreator(url,params,status){
		MAOWU.ajax.get(url, params, goRefreshPage(status));
	}
	 
	function goRefreshPage(status){
		var rand=Math.random() * ( 100000 + 1);	
			window.location.href = "orderWeb.do?order.orderStatus="+status;
	}
	</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="clear"></div>

<div class="help-nav-width">
<div style="width:1190px; margin:0 auto;">
        <div class="favorite_01-logo">淘竹马分销</div>
        <div class="buy-nav">
            <ul>
                <li><a href="myConsumerWeb.do"><span>采购商主页</span></a></li>
                <li><a href="systemInfoConWeb.do"  id="myMessage" >消息</object></a></li>	
                <li><a href="#" id="zhaqsz">帐户设置 <img src="images/buy_03.png" alt="" width="10" height="10" /></a></li>
                <!--	<li><a href="#">个人主页</a></li>	-->
            </ul>
        </div>
        
        <div class="drop">
        	<div class="drop-arrow"></div>
            <div class="drop-Bj">
            	<div class="drop-title">安全设置</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="goRetrieve.do">修改登录密码</a></dt>
                        <!--<dt><a href="goSetAccountSecurityQuestion.do">密保问题设置</a></dt> -->
                        <!--  <dt><a href="#">其他</a></dt>  -->
                    </dl>
                </div>
            </div>
            
            <div class="drop-Bj02">
            	<div class="drop-title02">个人资料</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="deliveryAddressListWeb.do">收货地址</a></dt>
                         <!--  <dt><a href="goUpdateConsumerWeb.do">修改个人信息</a></dt>-->
                        <dt><a href="goConsumerWeb.do">查看个人信息</a></dt>
                        <!--  <dt><a href="#">商铺认证</a></dt>	-->
                    </dl>
                </div>
            </div>
            
            <!-- 
            <div class="drop-Bj03">
            	<div class="drop-title03">账号绑定</div>
                <div class="drop-list">
                	<dl>
                    	<dt><a href="#">支付宝绑定</a></dt>
                        <dt><a href="#">微博绑定</a></dt>
                    </dl>
                </div>
            </div>
            -->
        </div>
    </div>
</div>

<div class="faq-width">
	<div class="buy-L">
    	<div class="buy-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;全部功能</div>
        <div class="buy-L-list">
        	<ul>
            	<li><a href="cartWeb.do">我的购物车</a></li>
                <li><a href="orderWeb.do">已买到的宝贝</a></li>
                <li><a href="deliveryAddressListWeb.do">我的收货地址</a></li>
                <li><a href="userCollectWeb.do">我的收藏</a></li>
                <!--  <li><a href="#">API申请</a></li>  -->
                <li><a href="userCommentAllListWeb.do">我的评价</a></li>
                <!--  <li><a href="#">退款维权</a></li>   -->
            </ul>
        </div>
    </div>
    
    <div class="buy-R">
    	<div class="buy-R-Part01">
        	<ul>
            	<li><a href="orderWeb.do">所有订单</a></li>
                <li><a href="orderWeb.do?order.orderStatus=1">待付款 <font color="#df434e">${orderPojo.dfkNum}</font></a></li>
                <li><a href="orderWeb.do?order.orderStatus=2">待发货 <font color="#df434e">${orderPojo.dfhNum}</font></a></li>
                <li><a href="orderWeb.do?order.orderStatus=3">待收货 <font color="#df434e">${orderPojo.dshNum}</font></a></li>
                <li><a href="orderWeb.do?order.orderStatus=4">待评价 <font color="#df434e">${orderPojo.dplNum}</font></a></li>
            </ul>
        </div>
        
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="buy-R-Part02">
        	<tr>
            	<td width="280">宝贝</td>
                <td width="100">单价（元）</td>
                <td width="30">数量</td>
                <td width="100"><!--商品操作--></td>
                <td width="100">实付款（元）</td>
                <td width="100"><!--交易状态--></td>
                <td width="110">操作</td>
            </tr>
        </table>
        <div class="buy-R-Part03">
        	<ul>
        		<li id="shop_list"></li>
        	</ul>
        </div>
    </div>
</div>


<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
