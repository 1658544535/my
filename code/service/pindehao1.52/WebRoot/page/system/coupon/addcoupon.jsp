<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">
	
</script>
<body>
<div class="sub_wrap">
  <div class="s_nav">
	  <a>优惠券管理</a> &gt; <a href="couponList.do">优惠券列表</a> &gt; 
	  <c:if test="${type == 0 }"><a href="#">新增优惠券</a></c:if>
	  <c:if test="${type == 1 }"><a href="#">编辑优惠券</a></c:if>
  </div>
  <div class="h15"></div>
  <div>
  <form action="addcouponOk.do" method="post" id="form1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
      <tr>
		<td align="right" class="grey"  width="15%">类型:</td>
        <td>
	        <select name="couponPojo.type" id="type" class="floatLeft">
				<option value="1" <s:if test="couponPojo.type == 1"> selected="selected"</s:if>>满m减n金额</option>
				<option value="2" <s:if test="couponPojo.type == 2"> selected="selected"</s:if>>直减金额</option>
		    </select>
      	</td>
		<td><span id="type_mgId"></span></td>
      </tr>
      <tr class="one">
		<td align="right" class="grey"  width="15%">订单金额:</td>
		<td>
			<input type="text" name="couponPojo.om"  value="${couponPojo.om}" class="floatLeft" id="om" >
		</td>
		<td><span id="om_mgId"></span></td>
	  </tr>
	  <tr class="one">
		<td align="right" class="grey"  width="15%">扣减金额:</td>
		<td>
			<input type="text" name="couponPojo.m"  value="${couponPojo.m}" class="floatLeft" id="m">
		</td>
		<td><span id="m_mgId"></span></td>
      </tr>
      <tr style="display: none;" class="two">
		<td align="right" class="grey" width="15%">扣减金额:</td>
		<td>
			<input type="text" name="couponPojo.m2"  value="${couponPojo.m2 }" class="floatLeft" id="m2">
		</td>
		<td><span id="m2_mgId"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">优惠券名称:</td>	
        <td>
	        <input type="text" name="couponPojo.name"  value="${couponPojo.name}" class="floatLeft" id="name">
        </td>
	    <td><span id="name_mgId"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">优惠券说明:</td>	
        <td>
	        <textarea id="caption" name="couponPojo.caption" class="floatLeft" style="margin: 0px; width: 300px; height: 70px;">${couponPojo.caption}</textarea>
        </td>
	    <td><span id="caption_mgId"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">优惠券数量:</td>	
        <td>
	        <input type="text" name="couponPojo.sheetNum"  value="${couponPojo.sheetNum}" class="floatLeft" id="sheetNum">
        </td>
	    <td><span id="sheetNum_mgId"></span></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%">可领取数:</td>	
        <td>
	        <input type="text" name="couponPojo.surplusNum"  value="${couponPojo.surplusNum}" class="floatLeft" id="surplusNum">
        </td>
	    <td><span id="surplusNum_mgId"></span></td>
      </tr>
      <tr>
		<td align="right" class="grey"  width="15%">状态:</td>
        <td>
	        <select name="couponPojo.status" id="status"  class="floatLeft">
	        	<option value="1" <s:if test="couponPojo.status == 1"> selected="selected"</s:if>>启用</option>
	        	<option value="0" <s:if test="couponPojo.status == 0"> selected="selected"</s:if>>禁用</option>
		    </select>
      	</td>
		<td><span id="status_mgId"></span></td>
      </tr>
      <tr>
		<td align="right" class="grey"  width="15%">有效期开始时间:</td>
        <td>
        	<input id="s" class="floatLeft" name="couponPojo.validStimeDStr" value="${couponPojo.validStimeDStr}" class="Wdate" type="text" onFocus="var e=$dp.$('e');WdatePicker({onpicked:function(){e.focus();},isShowToday:false,dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'e\')}'})"/>
      	</td>
      	<td><span id="validStime_mgId"></span></td>
      </tr>
      <tr>
		<td align="right" class="grey"  width="15%">有效期结束时间:</td>
        <td>
        	<input id="e" class="floatLeft" name="couponPojo.validEtimeDStr" value="${couponPojo.validEtimeDStr }" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'s\')}'})"/>
      	</td>
      	<td><span id="validEtime_mgId"></span></td>
      </tr>
      <tr>
		<td align="right" class="grey"  width="15%">是否指定商品:</td>
        <td>
	        <select name="couponPojo.isProduct" id="isProduct"  class="floatLeft">
				<option value="0" <s:if test="couponPojo.isProduct == 0"> selected="selected"</s:if>>否</option>
				<option value="1" <s:if test="couponPojo.isProduct == 1"> selected="selected"</s:if>>是</option>
		    </select>
      	</td>
		<td><span id="isProduct_mgId"></span></td>
      </tr>
      <tr style="display: none;" class="product">
		<td align="right" class="grey"  width="15%">商品ID:</td>
		<td>
			<input type="text" name="couponPojo.productId"  value="${couponPojo.productId }" class="floatLeft" id="productId">
		</td>
		<td><span id="productId_mgId"></span></td>
      </tr>
  	</table>
    </form>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  		<c:if test="${type == 0 }"><input type="button"  class="ok_btn" value="提交" id="sbutton"/></c:if>
  		<c:if test="${type == 1 }"><input type="button"  class="ok_btn" value="提交" id="sbutton2"/></c:if>
  </div>
</div>

</body>
</html>


<script>
var vname =new tt.Field(" 优惠券名称 ","couponPojo.name").setMsgId("name_mgId");
var validStime =new tt.Field(" 有效期开始时间 ","couponPojo.validStimeDStr").setMsgId("validStime_mgId");
var validEtime =new tt.Field(" 有效期结束时间 ","couponPojo.validEtimeDStr").setMsgId("validEtime_mgId");
var om =new tt.Field(" 订单金额 ","couponPojo.om").setMsgId("om_mgId");
var m =new tt.Field(" 扣减金额 ","couponPojo.m").setMsgId("m_mgId");
var m2 =new tt.Field(" 扣减金额 ","couponPojo.m2").setMsgId("m2_mgId");
var caption =new tt.Field(" 优惠券说明 ","couponPojo.caption").setMsgId("caption_mgId");
var sheetNum =new tt.Field(" 优惠券数量 ","couponPojo.sheetNum").setMsgId("sheetNum_mgId");
var surplusNum =new tt.Field(" 可领取数 ","couponPojo.surplusNum").setMsgId("surplusNum_mgId");
var productId =new tt.Field(" 指定商品ID","couponPojo.productId").setMsgId("productId_mgId");

tt.vf.req.add(vname,validStime,validEtime,om,m,m2,sheetNum,surplusNum);
tt.vf.num.add(om,m,m2,sheetNum,surplusNum,productId);
new tt.LV().set(0, 150).add(caption);
new tt.NRV().set(0, '++').add(sheetNum);
new tt.NRV().set(0, '++').add(surplusNum);
new tt.CV().add(surplusNum).set('n', '<=', sheetNum); 
new tt.CV().add(m).set('n', '<=', om);


MyValidator1 = tt.BV.ext({
	/**
	 * 验证的主方法
	 */
	v : function(trimedValue, indexOfElements, elements, field){
		var vparam = field.name;
		var url = "validCouponName.do?"+ vparam + "=" + trimedValue;
		var flag = false;
		$.ajax(
			{
				type: "post",
				url: url,
				dataType: 'json',
				async: false,
				success: function (data) {
					if(data==1){
						flag = true;
					}else{
					    flag = false;
					};
				}
			})
		return flag;
	},
	getI18 : function(label){
		return "已存过该名称，请换一个!";
	},
	/**
	 * 验证通过时，提示信息
	 */
	getTip : function(e,f,v,val) {
		return "恭喜！可以添加!";
	}
});

<c:if test="${type == 0 }">
new MyValidator1().add(vname);
</c:if>

$(function(){
	$("select[name='couponPojo.type']").bind("change", select);
	$("select[name='couponPojo.isProduct']").bind("change", selproduct);
	select();
	selproduct();
});

function select(){
	var op=$("select[name='couponPojo.type']").val();
	if(op == 1){
		$(".one").show();
		$(".two").hide();
		tt.vf.req.add(om,m);
		tt.vf.req.rm(m2);
	}else if(op == 2){
		$(".one").hide();
		$(".two").show();
		tt.vf.req.add(m2);
		tt.vf.req.rm(om,m);
	}else{
		$("select[name='couponPojo.type']").val("1");
		$(".one").show();
		$(".two").hide();
		tt.vf.req.add(om,m);
		tt.vf.req.rm(m2);
	}
}

function selproduct(){
	var op=$("select[name='couponPojo.isProduct']").val();
	if(op == 0){
		$(".product").hide();
		tt.vf.req.rm(productId);
	}else if(op == 1){
		$(".product").show();
		tt.vf.req.add(productId);
	}else{
		$("select[name='couponPojo.isProduct']").val("0");
		$(".product").hide();
		tt.vf.req.rm(productId);
	}
}

$(document).ready(function() {
	<c:if test="${type == 0}">
	$("#sbutton").click(function(){
		if(tt.validate()){
			document.getElementById("form1").submit();
		}
	});
	</c:if>
	<c:if test="${type == 1}">
	$("#sbutton2").click(function(){
		if(tt.validate()){
			$("#form1").attr("action","updatecouponOk.do?couponPojo.couponId=${couponPojo.couponId }").submit();
		}
	});
	</c:if>
});

</script>
