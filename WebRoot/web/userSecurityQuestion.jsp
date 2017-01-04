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
<script type="text/javascript" src="js/_head.js"></script>
<title>淘竹马分销商</title>
<script>
$(document).ready(function() 
 {
    $('#button').click(function ()
    {
  
       if($("#q1").val()=='')
        {
     	alert("答案一不允许未空");
     	return false;
        }
        if($("#q2").val()=='')
        {
     	alert("答案二不允许未空");
     	return false;
        }
        if($("#q3").val()=='')
        {
     	alert("答案三不允许未空");
     	return false;
        }
 });
   });
   </script>
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
                        <dt><a href="goSetAccountSecurityQuestion.do">密保问题设置</a></dt>
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
                <!--  <li><a href="#">退款维权</a></li>  -->
            </ul>
        </div>
    </div>
    
    <div class="my_supplier_Product-R">
    	<div class="my_supplier_Product-R-title">密保问题设置</div>
    	<div class="accountSecurityQuestion-fm">
	    	<form action="setAccountSecurityQuestion.do" id="idform" method="post">
		        <table border="0" cellpadding="0" cellspacing="0" class="accountSecurityQuestion-fm-table">
		            <tr>
		                <td class="register-form-tableTxt">问题一：</td>
		                <td>
			                <select name="userSecurityQuestion1.quesion" class="register-form-tableInput">
						 		
								<s:iterator value="sysSecurityQuestionList">
									<option value="<s:property value="quesion"/>">
										<s:property value="quesion" />
									</option>
								</s:iterator>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">答案：</td>
		                <td><input name="userSecurityQuestion1.answer" type="text" class="register-form-tableInput" id="q1"/><span id="answer"></span></td>
		            </tr>
		             <tr>
		                <td class="register-form-tableTxt">问题二：</td>
		                 <td>
			                <select name="userSecurityQuestion2.quesion" class="register-form-tableInput">
						 		
								<s:iterator value="sysSecurityQuestionList">
									<option value="<s:property value="quesion"/>">
										<s:property value="quesion" />
									</option>
								</s:iterator>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">答案：</td>
		                <td><input name="userSecurityQuestion2.answer" type="text" class="register-form-tableInput" id="q2"/></td>
		            </tr>
		             <tr>
		                <td class="register-form-tableTxt">问题三：</td>
		                 <td>
			                <select name="userSecurityQuestion3.quesion" class="register-form-tableInput">
						 		
								<s:iterator value="sysSecurityQuestionList">
									<option value="<s:property value="quesion"/>">
										<s:property value="quesion" />
									</option>
								</s:iterator>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="register-form-tableTxt">答案：</td>
		                <td><input name="userSecurityQuestion3.answer" type="text" class="register-form-tableInput" id="q3"/></td>
		            </tr>
		    	</table>
		    	<!--	<div align="left">   -->
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="accountSecurityQuestion-button" id="button">提交</button>
				<!--  </div>	 -->
	    	</form>
    	</div>
    </div>
</div>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
