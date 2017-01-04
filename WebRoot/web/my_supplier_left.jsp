<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/_head.js"></script>
<script type="text/javascript">
$(function() {
	for(var i=1;i<=4;i++){
        document.getElementById("menu"+i).onclick=function(){
        	//alert("uid"+this.id.charAt(4));
            var uid = document.getElementById("uid"+this.id.charAt(4));
            //alert(uid);
            if(uid.style.display=="none"){
            	uid.style.display="block";
            }else{
            	uid.style.display="none";
            }
        }
    }
});
	
</script>

<div class="buy-L">
    	<div class="buy-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;全部功能</div>
        <div class="my_supplier-L-list">
        	<ul>
            	<!--  <li><a href="#">快捷菜单</a></li>  -->
                <li id="menu1"><a>订单管理</a></li>
                	<div class="my_supplier-L-list-second" >
                    	<dl id="uid1" style="display: none">
                        	<dt><a href="supplyOrderListWeb.do">查看订单</a></dt>
                        	<dt><a href="supplyOrderListWeb.do?orderStatus=1">待付款订单</a></dt>
                        	<dt><a href="supplyOrderListWeb.do?orderStatus=2">已付款订单</a></dt>
                        	<dt><a href="supplyOrderListWeb.do?orderStatus=3">已发货订单</a></dt>
                        	<dt><a href="supplyOrderListWeb.do?orderStatus=4">已确认订单</a></dt>
                        </dl>
                    </div>
                <li id="menu2"><a>产品管理</a></li>
                	<div class="my_supplier-L-list-second" >
                    	<dl id="uid2" style="display: none">
                        	<!-- <dt><a href="goProductAddWeb.do">产品上传</a></dt> -->
                            <dt><a href="productManageWeb.do">产品信息管理</a></dt>
                        </dl>
                    </div>
                <li id="menu3"><a>评价管理</a></li>
                	<div class="my_supplier-L-list-second" >
                    	<dl id="uid3" style="display: none">
                            <dt><a href="productCommentManageWeb.do">评价信息管理</a></dt>
                        </dl>
                    </div>
                <li id="menu4"><a>店铺管理</a></li>
                	<div class="my_supplier-L-list-second" >
                    	<dl id="uid4" style="display: none">
                        	<dt><a href="goFindShopWeb.do">店铺设置</a></dt>
                            <!--  <dt><a href="#">店铺消息</a></dt>	-->
                        </dl>
                    </div>
                <!--  <li><a href="shipManageWeb.do">发货管理</a></li> -->
            </ul>
        </div>
    </div>