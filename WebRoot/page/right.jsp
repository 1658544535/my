<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/msg.css" media="screen" rel="stylesheet">

<title>正文</title>

<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/spage.css"/>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/date.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">


	function mouseoverOper(obj) { 
		$(obj).css(
				{border:"1px solid red"}
				)
		}
	function mouseoutOper(obj) { $(obj).css({border:"1px solid #ccc"})}

	
	function showMsg(){
		
		var ad='${requestScope.adMsg}';
		var mer='${requestScope.merMsg}';
		if(ad>0){
			$('#ad').show();
			$('#INDEXT_RB_AD').show();
		}
		if(mer>0){
			$('#mer').show();
			$('#INDEXT_RB_AD').show();
		}
		
	}
	
	function init(){
		document.getElementById("divOuters").innerHTML="<div id=\"divWeather\">&nbsp;</div><iframe allowtransparency=\"true\" frameborder=\"0\" width=\"290\" height=\"96\" scrolling=\"no\" src=\"http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=0&d=2&k=&f=1&q=0&e=0&a=1&c=54511&w=290&h=96\"></iframe>";
		var hideInfo='${sessionScope.partitionTM}';
		if(hideInfo>0){
			//$('#dayDifference').show();
			$('#dayDifference').html('您已经<font color="#FF6600"> '+hideInfo+' </font>天未登录系统！');
		}
		var date=new Date();
		//月份
		var i=date.getMonth()+1;
		//日期
		var j=date.getDate();
		//星期
		var k=date.getDay();
		$('#dateInfo').text(j);
		switch (i) {
		  case 1: 
			$('#month').text('January');
		    break;
		  case 2: 
			  $('#month').text("February");
		    break;
		  case 3: 
			  $('#month').text("March");
		    break;
		  case 4: 
			  $('#month').text("April");
		    break;
		  case 5: 
			  $('#month').text("May");
		    break;
		  case 6: 
			  $('#month').text("June");
		    break;
		  case 7: 
			  $('#month').text("July");
		    break;
		  case 8: 
			  $('#month').text("August");
		    break;
		  case 9: 
			  $('#month').text("Spetember");
		    break;
		  case 10: 
			  $('#month').text("October");
		    break;
		  case 11: 
			  $('#month').text("November");
		    break;
		  case 12: 
			  $('#month').text("December");
		    break;
		  default:  $('#month').text("other");
		}
		
		switch (k) {
		  case 1: 
			$('#dayInfo').text('星期一');
		    break;
		  case 2: 
			  $('#dayInfo').text("星期二");
		    break;
		  case 3: 
			  $('#dayInfo').text("星期三");
			  break;
		  case 4: 
			  $('#dayInfo').text("星期四");
			  break;
		  case 5: 
			  $('#dayInfo').text("星期五");
			  break;
		  case 6: 
			  $('#dayInfo').text("星期六");
			  break;
		  case 7: 
			  $('#dayInfo').text("星期日");
			  break;
		}
	}

	
</script>

  <style>
	#solidStart{
		background:url('<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/star.png') no-repeat;
		width:16px;
		height:16px; 
		background-position:0px 2px;
		
	}
	#hollowStart{
		background:url('<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/star.png') no-repeat;
		width:16px;
		height:16px; 
		padding:0 0;
		margin:0 0 ;
		background-position:0px -14px;
	}
	
	#divWeather {
	position: absolute;
	top: 0;
	left: 0;
	width: 292px;
	height: 98px;
	background: #fff;
	filter: alpha(opacity = 10);
	z-index: 1px;
	filter: Alpha(opacity = 10);
	-moz-opacity: .1;
	opacity: 0.1;
}

#divOuters {
	position: absolute;
	width: 290px;
	height: 96px;
	background: #fff;
	border: 0px solid red;
	filter: alpha(opacity = 100);
	z-index: 1px;
}
  </style>
  
</head>

<body>
<div class="sub_wrap">
	<div>
	    <div class="wel_left_img"><img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/images/user_pic.jpg" onerror="this.src='/images/user_pic.jpg'" width="150px" height="150px" /></div>
	    <div class="wel_right">
	        <table width="260" border="0" cellpadding="0" cellspacing="0">
	          <tr>
	            <td width="100" rowspan="4"></td>
            	<td height="10">&nbsp;</td>
	          </tr>
	          <tr>
	            <td height="35" align="center"><b id="month"></b></td>
	          </tr>
	          <tr>
	            <td><p id="dateInfo"></p></td>
	          </tr>
	          <tr>
	            <td align="center"><span id="dayInfo"></span><span id="nlDate"></span></td>
	          </tr>
	        </table>
	    </div>
	    <div class="wel_mid_info">
	        <div class="use_name"> ${sessionScope.username}，您好！</div>
	        <div class="useTime" id="dayDifference">&nbsp; </div>
	        <div class="use_time">上次登录时间为：${sessionScope.preTM}</div>
	        	星级指数：
	           	    	
	           	    	<span id="solidStart"  >&nbsp;  &nbsp; </span>
	           	    	<span id="solidStart"  >&nbsp;  &nbsp; </span>
	           	    	<div id="hollowStart" style="display: inline;height: 5px;">&nbsp;  &nbsp; </div>
	           	    <div id="divOuters">
					</div>
	        <table cellpadding="0" cellspacing="0" class="weather">
	          <tr>
	            <td>
	            	
	           </td>
	          </tr>
	        </table>
	    </div>
	    <div class="clear"></div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<table>
    	<tr>
			<td align="right">时间范围：</td>
			<td>
				<input value="" id="beginTime" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })" id="create-time-start-selector"/>
			</td>
			<td>
				<input value="" id="endTime" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })" id="create-time-end-selector"/>
			</td>
			<td>
				<div class="floatRight">
					<input onclick="checkInfo()" type="button" class="submit_btn" value="查询" />
				</div>
			</td>
		</tr>
    </table>
    <br>
	<b id="a"></b>
	<!--
	</br>
	<b id="b"></b>
	-->
	</br>
	<b id="c"></b>
	</br>
	<b id="d"></b>
	</br>
	<b id="e"></b>
	</br>
	<b id="f"></b>
	</br>
	<b id="g"></b>
	</br>
	<b id="h"></b>
	</br>
	<b id="i"></b>
	<script type="text/javascript">
		$(function(){
			info(GetDateStr(0),GetDateStr(1));
		});
		function checkInfo(){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			info(beginTime,endTime);
		}
	    function info(beginTime,endTime){
 			showMsg();
 			init();
 			$.ajax({
            type: "get",
            url: "refundcont.do?beginTime="+beginTime+"&endTime="+endTime,
            success: function(data){
            	var a = data.split(':')[0];
 				//var b= data.split(':')[1];
 				document.getElementById("a").innerHTML="共有<font color='red'>"+a+"</font>条退货退款申请";
 				// document.getElementById("b").innerHTML="共有<font color='red'>"+b+"</font>条退货信息";
            }
            });
             //今日注册人数	
 			$.ajax({
            type: "get",
            url: "userCnt.do?beginTime="+beginTime+"&endTime="+endTime,
            success: function(data){
 				document.getElementById("c").innerHTML="共有注册人数<font color='red'>"+data+"</font>人";
            }
            });
            //已成团已付款今日订单数	
 			$.ajax({
            type: "get",
            url: "orderCnt.do?beginTime="+beginTime+"&endTime="+endTime,
            success: function(data){
 				document.getElementById("d").innerHTML="共有<font color='red'>"+data+"</font>个成团订单";
            }
            });	
            //已成团已付款今日订单销售额	
 			$.ajax({
            type: "get",
            url: "totalSale.do?beginTime="+beginTime+"&endTime="+endTime,
            success: function(data){
 				document.getElementById("e").innerHTML="订单销售金额￥<font color='red'>"+data+"</font>元";
            }
            });
 			//0.1已成团已付款今日订单数	
 			$.ajax({
            type: "get",
            url: "oneOrderCnt.do?beginTime="+beginTime+"&endTime="+endTime,
            success: function(data){
 				document.getElementById("f").innerHTML="0.1今日共有<font color='red'>"+data+"</font>个成团订单";
            }
            });
 			//已成团已付款今日订单销售额	
 			$.ajax({
            type: "get",
            url: "oneTotalSale.do?beginTime="+beginTime+"&endTime="+endTime,
            success: function(data){
 				document.getElementById("g").innerHTML="0.1订单销售金额￥<font color='red'>"+data+"</font>元";
            }
            });
 			 //总开团数	
 			$.ajax({
            type: "get",
            url: "openGroupCnt.do",
            success: function(data){
 				document.getElementById("h").innerHTML="总开团数：<font color='red'>"+data+"</font>";
            }
            });
 			 //总开团数	
 			$.ajax({
            type: "get",
            url: "openSuccessGroupCnt.do",
            success: function(data){
 				document.getElementById("i").innerHTML="总成团数：<font color='red'>"+data+"</font>";
            }
            });
	    }
	    //获取时间
	    function GetDateStr(AddDayCount) {
	        var dd = new Date();
	        dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	        var y = dd.getFullYear();
	        var m = dd.getMonth()+1;//获取当前月份的日期
	        var d = dd.getDate();
	        return y+"-"+m+"-"+d;
	    }
    </script>
</div>
</body>
</html>
