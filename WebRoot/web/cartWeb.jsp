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
<script language="JavaScript">
    function chkall(input1,input2)
	    {
	        var objForm = document.forms[input1];
	        var objLen = objForm.length;
	        var priceFirst = eval(document.getElementById('cartallprice')).value;
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
            $(".shopping_Cart-table-txt08-red01").text(objSelectAll);
            if(objSelectAll==0){
            	$(".shopping_Cart-table-txt08-red02").text(0);
            }else{
                sumCountAndPrice();
            	//$(".shopping_Cart-table-txt08-red02").text(priceFirst);
            	//document.getElementById("cartallprice").value =priceFirst;
            }
            
	    }
</script>
<script type="text/javascript">

	/**改变单选框自动计算总数量和总价格*/
	function sumCountAndPrice(){
		var str=document.getElementsByName("cids");
		var objarray = str.length;
		var objCount = 0;
		var charStr="";
		for (i=0;i<objarray;i++)
			{
			  if(str[i].checked == true)
				  {
					  if(i<objarray-1){
					  	charStr+=str[i].value+",";
					  }else{
						charStr+=str[i].value;
					  }
			  }
		}
		
		for (i = 0; i < str.length; i++) {
			if (str[i].type == "checkbox") {
				if(str[i].checked == true){
					objCount++;
				}
			}
		}
       //alert(objCount);
		//alert(chestr);
		if(objCount<str.length){
		document.getElementById('allcheck').checked = false;
		document.getElementById('allcheck0').checked = false;
		}
		
		if(charStr == ""){
			$(".shopping_Cart-table-txt08-red02").text("0");//没选中任何的商品
		}else{
			//alert(charStr);
			
			$.ajax({
		        type:"GET",
		        url:"countSumPrice.do",      
		        data: "cidStr="+charStr,
		        success:function(data){
		        	var sumPrice = data;
		        	
		        	$(".shopping_Cart-table-txt08-red02").text(sumPrice);
		        	 document.getElementById("cartallprice").value=sumPrice;
		        	//alert(document.getElementById("cartallprice").value);
		        },
		        error: function(){
				alert("失败");
		    	} 
		    }); 
			
		}
		
		//alert("objCount="+objCount);
		$(".shopping_Cart-table-txt08-red01").text(objCount);
		
	}
	
	function del(val)
	{
		
		if(confirm("你真的想删除该记录么？"))
		{
		$.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"delcartWeb.do",// 把数据提交到ok.php       
        data: "cart.id="+val,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
        //alert(data)
            if(data==1){
             window.location.replace("/cartWeb.do");
            }else{
              alert("购物车产品删除失败");
            }   
        },
        error: function(){
		alert("购物车产品删除失败");
    	} 
    }); 
		}else{
			return ;
		}
		
	}
function delcheck()
{
var str=document.getElementsByName("cids");
var objarray=str.length;
var chestr="";
for (i=0;i<objarray;i++)
{
  if(str[i].checked == true)
  {
  if(i<objarray-1){
   chestr+=str[i].value+",";
  }else{
    chestr+=str[i].value;
  }
  
  }
}
if(chestr == "")
{
  alert("请先选择您要删除的购物车商品！");
}
else
{
   $.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"delCheckCartWeb.do",// 把数据提交到ok.php       
        data: "strId="+chestr,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
       // alert(data)
            if(data==1){
             window.location.replace("/cartWeb.do");
            }else{
              alert("购物车产品删除失败");
            }   
        },
        error: function(){
		alert("购物车产品删除失败");
    	} 
    }); 
}
}

 $(document).ready(function(){// DOM的onload事件处理函数  
                               <s:iterator value="cartlist">
                            	$("#jian<s:property value='id'/>").click(function(){// 当按钮submit被点击时的处理函数 
                                	z_number1_<s:property value='id'/>();// submit别点击时执行postdata函数 	
                                	var z_num=$("#z_num<s:property value='id'/>").val();
                                	myform_action(<s:property value='id'/>,z_num);
                                });  
                                 $("#jia<s:property value='id'/>").click(function(){// 当按钮submit被点击时的处理函数 
                                	z_number_<s:property value='id'/>();// submit别点击时执行postdata函数 	
                                	var z_num=$("#z_num<s:property value='id'/>").val();
                                	myform_action(<s:property value='id'/>,z_num);
                                }); 
                                
                                </s:iterator>
}); 
                                
                                <s:iterator value="cartlist">
                                function z_number1_<s:property value='id'/>(){
		                        	var z_num=$("#z_num<s:property value='id'/>").val();
		                            z_num=parseInt(z_num)
		                        	if(z_num>1){
		                        		z_num=z_num-1;
		                        	}
		                        	document.getElementById("z_num<s:property value='id'/>").value = z_num;
		                        }
		                        function z_number_<s:property value='id'/>(){
		                        	var z_num=$("#z_num<s:property value='id'/>").val();
		                        	z_num=parseInt(z_num)
		                        	z_num=z_num+1;
		                        	document.getElementById("z_num<s:property value='id'/>").value = z_num;
		                        	
		                        }

		                        
		                         </s:iterator>
		                        
		                        function myform_action(cid,num){
		                           $.ajax({// 调用jquery的ajax方法  
								        type:"POST",// 设置ajax方法提交数据的形式  
								        url:"/updateNumCartWeb.do",// 把数据提交到ok.php       
								        data: "cart.id="+cid+"&cart.num="+num, 
								        dataType:'json', 
								        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
		                                var message = eval("("+data+")");
								            if(message.success==1){
								             //window.location.replace("/cartWeb.do");
								             $("#price_"+cid).html(message.price);
								             $("#allprice_"+cid).html(message.allprice);
								             sumCountAndPrice();
								            }else{
								              alert("购物车产品数量修改失败");
								            }   
								        },
								        error: function(){
										alert("购物车产品数量修改失败");
								    	} 
								    }); 			
								}

function collect(pid){
 $.ajax({// 调用jquery的ajax方法  
        type:"GET",// 设置ajax方法提交数据的形式  
        url:"/addProductCollect.do",// 把数据提交到ok.php       
        data: "productPojo.id="+pid,  
        success:function(data){// 提交成功后的回调，msg变量是ok.php输出的内容 
            if(data == 1){
    			alert("该产品收藏成功");
    		}else if(data == 3){
    			 window.location.replace("/doLoginWeb.do?url="+window.location.href);
    		}else if(data == 4){
    			alert("该产品已在产品收藏库中");
    		}else{
    			alert("该产品收藏失败");
    		}
        },
        error: function(){
		alert("收藏商品失败");
    	} 
    });  
	
}

function searchBtn(){
	$("#sysform").attr("action","searchWeb.do");
	$("#sysform").submit();
}
	</script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<div class="logo"></div>
	<form action="searchWeb.do" method="post" id="sysform">
	<div class="search">
	<input name="productPojo.productName" type="text" value="搜索 商品/店铺" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 商品/店铺'}" class="search-input"/>
	<button type="submit" class="search-button" onclick="searchBtn()">搜 索</button></div>
	</form>  
</div> 

<div class="clear"></div>

<div class="shopping_Cart">
	<div  class="shopping_Cart-title">我的购物车</div>
	<form action="goSelectAddress.do" method="post" name="myForm" id="myForm" >
	<input type="hidden" name="cartallprice" id="cartallprice" value="${allCartPrice}"  />
    <table border="0" cellpadding="0" cellspacing="0" class="shopping_Cart-table">
    	<tr >
        	<td colspan="2" class="shopping_Cart-table-title"><label><input type="checkbox" name="all" id="allcheck" onclick='chkall("myForm",this)' value=""/>&nbsp;&nbsp;&nbsp; 全选</label></td>
          	<td colspan="2" class="shopping_Cart-table-title">商品信息</td>
          	<td class="shopping_Cart-table-title">单价（元）</td>
          	<td class="shopping_Cart-table-title">数量</td>
          	<td class="shopping_Cart-table-title">金额(元)</td>
          	<td class="shopping_Cart-table-title">操作</td>
        </tr>
        <s:if test="cartlist.isEmpty()">
        <tr height=100px;><td align="center">购物车暂未商品</td></tr>
		</s:if>
		<s:else>
		<s:iterator value="cartlist">
	        <tr>
	        	<td colspan="2" class="shopping_Cart-table-txt01">
	        	<s:if test="proStatus==1">
	        		<input type="checkbox" name="cids" value="<s:property value='id'/>" onclick="sumCountAndPrice();" />
	        		<span class="shopping_Cart-table-txt01-Pic"><a href="goProductDetail.do?productPojo.id=<s:property value="productId"/>"><img src="/upfiles/product/<s:property value='productImage'/>" alt="" width="79" height="80" /></span></td>
	            <td class="shopping_Cart-table-txt02"><a href="goProductDetail.do?productPojo.id=<s:property value="productId"/>"><s:property value='productName'/></td>
	            <td class="shopping_Cart-table-txt03"><s:property value='color'/><p><s:property value='size'/></p></td>
	            <td class="shopping_Cart-table-txt04">¥<span id="price_<s:property value='id'/>"><s:property value='stockPrice'/></span></td>
	            <td class="shopping_Cart-table-txt05">
	            <a  href="javascript:void(0);" id="jian<s:property value='id'/>"  class="shopping_Cart-table-txt05-Symbol">-</a>
	            <input type="text" name="number" id="z_num<s:property value='id'/>" onkeyup="if(this.value==0){this.value=this.value.replace(this.value.replace(/[^\d*]/,''),'1');}else{this.value=this.value.replace(/[^\d*]/,'');}" onblur="myform_action(<s:property value='id'/>,this.value);" style="width:30px;height:24px;text-align:center;padding-top:0px;border: #aaa 1px solid;" value="<s:property value='num'/>">
	            <a  href="javascript:void(0);" id="jia<s:property value='id'/>"  class="shopping_Cart-table-txt05-Symbol">+</a></td>
	            <td class="shopping_Cart-table-txt06"><span id="allprice_<s:property value='id'/>"><s:property value='allStockPrice'/></span></td>
	            <td class="shopping_Cart-table-txt07"><a href="javascript://" onclick="collect(<s:property value='productId'/>)">移入收藏夹</a><br /><a  class='del_btn' onclick="del(<s:property value='id'/>)">删除</a></td>
	        	</s:if>
	        	<s:else>
	        	<input disabled="disabled" type="checkbox" value="<s:property value='id'/>" onclick="sumCountAndPrice();" />
					<span class="shopping_Cart-table-txt01-Pic"><img src="/upfiles/product/<s:property value='productImage'/>" alt="" width="79" height="80" /></span></td>
	            <td style="overflow:visible" class="shopping_Cart-table-txt02"><font color="#A9A9A9"><s:property value='productName'/></font></td>
	            <td class="shopping_Cart-table-txt03"><s:property value='color'/><p><s:property value='size'/></p></td>
	            <td class="shopping_Cart-table-txt04"><font color="#A9A9A9">¥<span id="price_<s:property value='id'/>"><s:property value='stockPrice'/></span></font></td>
	            <td class="shopping_Cart-table-txt05">
	            <a  href="javascript:void(0);"  class="shopping_Cart-table-txt05-Symbol">-</a>
	            <input type="text" name="number" readonly="readonly" id="z_num<s:property value='id'/>" onkeyup="if(this.value==0){this.value=this.value.replace(this.value.replace(/[^\d*]/,''),'1');}else{this.value=this.value.replace(/[^\d*]/,'');}" onblur="myform_action(<s:property value='id'/>,this.value);" style="width:30px;height:24px;text-align:center;padding-top:0px;border: #aaa 1px solid;" value="<s:property value='num'/>">
	            <a href="javascript:void(0);" class="shopping_Cart-table-txt05-Symbol">+</a></td>
	            <td class="shopping_Cart-table-txt06"><font color="#A9A9A9"><span id="allprice_<s:property value='id'/>"><s:property value='allStockPrice'/></span></font></td>
	            <td class="shopping_Cart-table-txt07"><font color="F00000">该商品已下架</font><br/></a><a  class='del_btn' onclick="del(<s:property value='id'/>)">删除</a></td>
				</s:else> 
	        </tr>
		</s:iterator>
		</s:else>
        <tr>
        	<td colspan="2" class="shopping_Cart-table-txt08"><label><input type="checkbox" name="all"  id="allcheck0"  onclick='chkall("myForm",this)' value=""/>&nbsp;&nbsp;&nbsp;全选</label></td>
            <td class="shopping_Cart-table-txt08"><a href="javascript://" onclick="delcheck();" >删除</a></td>
            <td class="shopping_Cart-table-txt08"><!--<a  href="javascript://" onclick="return addcollect();">加入收藏夹</a>--></td>
            <td class="shopping_Cart-table-txt08">已选商品 <span class="shopping_Cart-table-txt08-red01">0</span> 件</td>
            <td colspan="2" class="shopping_Cart-table-txt08">合计：<span class="shopping_Cart-table-txt08-red02">¥0.00</span></td>
            <td class="shopping_Cart-table-txt09"><a href="#" id="submitId" class="shopping_Cart-table-txt09-Balance">去结算</a></td>
        </tr>
    </table>
    </form>
</div>
<div id="leftsead">
	<!--<ul>
		
		<li><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001501677&aty=0&a=0&curl=&ty=1" target="_blank"><img src="images/ll04.png" width="131" height="49" class="hides"/><img src="images/l04.png" width="47" height="49" class="shows" /></a></li>
		
	</ul>-->
</div>
<style type="text/css">
/* leftsead */
#leftsead{width:131px;height:143px;position:fixed;top:258px;right:0px;}
*html #leftsead{margin-top:258px;position:absolute;top:expression(eval(document.documentElement.scrollTop));}
#leftsead li{width:131px;height:60px;}
#leftsead li img{float:right;}
#leftsead li a{height:49px;float:right;display:block;min-width:47px;max-width:131px;}
#leftsead li a .shows{display:block;}
#leftsead li a .hides{margin-right:-143px;cursor:pointer;cursor:hand;}
#leftsead li a.youhui .hides{display:none;position:absolute;right:190px;top:2px;}
</style>
<script type="text/javascript">
$(document).ready(function(){

	$("#leftsead a").hover(function(){
		if($(this).prop("className")=="youhui"){
			$(this).children("img.hides").show();
		}else{
			$(this).children("img.hides").show();
			$(this).children("img.shows").hide();
			$(this).children("img.hides").animate({marginRight:'0px'},'slow'); 
		}
	},function(){ 
		if($(this).prop("className")=="youhui"){
			$(this).children("img.hides").hide('slow');
		}else{
			$(this).children("img.hides").animate({marginRight:'-143px'},'slow',function(){$(this).hide();$(this).next("img.shows").show();});
		}
	});

	$("#top_btn").click(function(){if(scroll=="off") return;$("html,body").animate({scrollTop: 0}, 600);});

});
</script>
<script type="text/javascript">
$(document).ready(function() {
	//结算
	$("#submitId").click(function(){
		var str=document.getElementsByName("cids");
		var objarray=str.length;
		var chestr="";
		for (i=0;i<objarray;i++)
		{
		  if(str[i].checked == true)
		  {
		  if(i<objarray-1){
		   chestr+=str[i].value+",";
		  }else{
		    chestr+=str[i].value;
		  }
		  
		  }
		}
		if(chestr == "")
		{
		  alert("请先选择您要结算的购物车商品！");
		}
		else
		{
			document.getElementById("myForm").submit();
		}
	});
});
</script>
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
