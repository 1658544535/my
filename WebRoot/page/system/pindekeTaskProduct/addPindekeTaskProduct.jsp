<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerMartshowItems-9b61bc2a35m.css" type="text/css" media="all" />
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<style>
.mar-tb{
	margin: 15px 0px 15px 0px;
}
.base-info{
	width: 100%;
	height: 40px;
	line-height: 40px;
	padding-left:10px;
	border-width: 1px;
	border-style: solid;
	border-color: rgba(204, 204, 204, 1);
}
</style>
<style>
.mask{width:100%;height:100%;background:#fff;top:0;left:0;display:none;position:fixed;z-index:10;}
.wrap{display:none;width:100%;height:100%;position:fixed;top:0;left:0;z-index:11;background:#fff;}
.top01-popup .ok{float:right;cursor:pointer;font-style:normal;padding-right:10px;color:#000;}
</style>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">拼得客任务</a> &gt; <a href="#">新增</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="addPindekeTaskProduct.do" method="post" id="from1" enctype="multipart/form-data">
			<div class="grey base-info" style="">基本信息</div><br>
			<div style="padding:0 3% 0 3%">
				<div class="mar-tb">
					<div style="float: left"><lable>活动时间 : </lable></div>
					<div style="float: left"><input type="text" name="grouponActivityPojo.beginTime" id="grouponActivityPojo.beginTime" value="" 
						readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/>
						<input type="text" name="grouponActivityPojo.endTime" id="grouponActivityPojo.endTime" value="" 
						readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></div>
				</div>
				<span id="beginTime_mgId"></span><span id="endTime_mgId"></span>
				<div class="Clear"></div>	
				<div class="mar-tb">
					<div style="float: left"><lable>成团人数 : </lable></div>
					<div style="float: left">
						<input type="text" name="grouponActivityPojo.num" id="num" value="" onchange="setCommission()"/>（每个团的成团人数）
					</div>
				<span id="num_mgId"></span>
				</div>
				<div class="Clear"></div>			
				<div class="mar-tb">
					<div style="float: left"><lable>拼团价格 : </lable></div>
					<div style="float: left">
						<input type="text" name="grouponActivityPojo.price" id="price" value="" onchange="setCommission()"/>
					</div>
					<span id="price_mgId"></span>
				</div>
				<div class="Clear"></div>		
				<div class="mar-tb">
					<div style="float: left"><lable>返佣比例 : </lable></div>
					<div style="float: left">
						<input type="text" name="grouponActivityPojo.rebateRatio" id="rebateRatio" value="" onchange="setCommission()"/>（单位：%，只需填写数字即可）
					</div>
					<span id="rebateRatio_mgId"></span>
				</div>
				<div class="Clear"></div>	
				<div class="mar-tb" id="commission">
					<div style="float: left"><lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;佣金 : </lable></div>
					<div style="float: left">
					    <input type="text" id="commission" value="" style="border:none" readonly="readonly"/>
					</div>
				</div>
				<div class="Clear"></div>	
				<div class="mar-tb">
					<div style="float: left"><lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序 : </lable></div>
					<div style="float: left">
						<input type="text" name="grouponActivityPojo.sorting" id="grouponActivityPojo.sorting" value=""/>
					</div>
					<span id="sorting_mgId"></span>
				</div>
				<div class="Clear"></div>		
				<%-- <div class="mar-tb">
					<div style="float: left"><lable>限购数量 : </lable></div>
					<div style="float: left">
						<input type="text" name="grouponActivityPojo.restrictionNum" id="grouponActivityPojo.restrictionNum" value=""/>（如果不限购则设置为：0）
					</div>
					<span id="restrictionNum_mgId"></span>
				</div> --%>
				<div class="Clear"></div>
				<div class="mar-tb">
					<div style="float: left;"><lable>拼团商品信息 : </lable></div><span><div id="productId_mgId" style="color: red;"></div></span>
					<div class="action" style="float: right;margin-right: 55%"><a class="Add_btn">点击添加</a></div><br><br>
					<div id="product" style="background-color: rgba(242, 242, 242, 1);width: 725px;height: 98px;">
					<p style="text-align:center;line-height:98px; color:#ccc "><span>拼团信息为空</span></p>
					</div>
			</div>
			<input type="hidden" name="grouponActivityPojo.productId" id="productId"/>
		</form>
		<div class="Clear"></div>
		</div>
		<div class="Btn_div" style="text-align: left;padding:0 3% 0 3%">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
		</div>
	</div>
	<!-- 弹出层开始 -->
	<div class="mask"></div>
	<div class="wrap">
		<div class="popup">
			<div class="top01-popup" style="display: none" >
				<em class="ok">确定</em>
			</div>
			<iframe src="goSelectPindekeTaskProduct.do" id="iframepage" name="iframepage" frameBorder=0 width="100%" onLoad="reinitIframeEND();"></iframe>
		</div>
	</div>
	<!-- 弹出层结束 -->
</body>
</html>
<script>
	var beginTime =new tt.Field("开始时间","grouponActivityPojo.beginTime").setMsgId("beginTime_mgId");
	var endTime =new tt.Field("结束时间","grouponActivityPojo.endTime").setMsgId("endTime_mgId");
	var num =new tt.Field("成团人数","grouponActivityPojo.num").setMsgId("num_mgId");
	var price =new tt.Field("拼团价格","grouponActivityPojo.price").setMsgId("price_mgId");
	var rebateRatio =new tt.Field("返佣比例","grouponActivityPojo.rebateRatio").setMsgId("rebateRatio_mgId");
	//var restrictionNum =new tt.Field("限购数量","grouponActivityPojo.restrictionNum").setMsgId("restrictionNum_mgId");
	var sorting =new tt.Field("排序","grouponActivityPojo.sorting").setMsgId("sorting_mgId");
	tt.vf.req.add(beginTime,endTime,num,price,rebateRatio,sorting);
	tt.vf.num.add(sorting);
	tt.vf.int.add(sorting);
	new tt.NRV().set(0, '++').add(sorting);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				if($("#productId").val()==null || $("#productId").val() == "" || $("#productId").val() == undefined){
					$("#productId_mgId").text("活动商品不能为空!");
				} else {
					document.getElementById("from1").submit();					
				}
			}
		});
	});	
</script>
<script type="text/javascript">
	//弹出层
	$(function(){
		$('.action').click(function(){
			$('.wrap').show();
			$('.mask').show();
		});
		$('.ok').click(function(){
			$('.wrap').hide();
			$('.mask').hide();
		})
	})
</script>
<script type="text/javascript">
	//替换商品
	function replacePro(id){
		console.log("这是子页面传来的商品id:"+id);
        $.ajax({
            type: "GET",
            url: "findPindekeTaskProductById.do?productPojo.id="+id,
            dataType: "json",
            success: function(data){
            		//替换拼团信息内容
            		var productObj=data.result;
            		var tableStr="";
            		var statusStr="";
            		if(productObj != null){
            			console.log(productObj);
            			$("#productId").val(productObj.id);
            			if(productObj.status==1){
            				statusStr="已审核";
            			} else if(productObj.status==0){
            				statusStr="未审核";
            			}
            			tableStr = '<table width="100%" border="0" class="Info_list_table" style="background:#fff">'+
			    						'<tr>'+
			    						'<th>商品编号</th>'+
			    						'<th>商品货号</th>'+
			    						'<th>商品名称</th>'+
			    						'<th>商品图</th>'+
			    						'<th>价格</th>'+
			    						'<th>审核状态</th>'+
			    						'</tr>'+
			    						'<tbody id="body">'+
			    						'<tr>'+
			    							'<td>'+productObj.productNo+'</td>'+
			    							'<td>'+productObj.productNum+'</td>'+
			    							'<td>'+productObj.productName+'</td>'+
			    							'<td><img src="<s:i18n name='sysconfig'><s:text name='houtai_dns' /></s:i18n>/upfiles/product/'+productObj.image+'" width="50px" height="50px"/></td>'+
			    							'<td>'+productObj.price+'</td>'+
			    							'<td>'+statusStr+'</td>'+
			    						'</tr>'+
			    						'</tbody>'+    
		    						'</table>';
		    			$("#product").empty();
            			$("#product").append(tableStr);
            		} else {
            			alert("替换失败!");
            		}
	        }
        });
		$(".ok").click();
	}
	
	  function setCommission(){
       var a=$("#num").val();
       var b=$("#price").val();
       var c=$("#rebateRatio").val();
       $(commission).val(((a-1)*b*c*1/100).toFixed(2));
       }
</script>
