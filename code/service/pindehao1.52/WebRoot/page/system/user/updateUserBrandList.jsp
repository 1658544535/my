<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" language="javascript"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base/operation/shop/shop.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
<script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	$(function(){
			select1();
			select2();
	});
	function select1() {
		$("#mainCategory").append("<option value=''>- 请选择 -</option>");
		$.ajax({
			type: "post",
			url: "getProductTypeByPid.do?pid=-1",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${userBrandPojo.mainCategory}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#mainCategory").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
			}
		});
	}
	function select2() {
		$("#brandId").append("<option value=''>- 请选择 -</option>");
		$.ajax({
			type: "post",
			url: "brandDicListAll.do",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${userBrandPojo.brandId}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#brandId").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].brand + "</option>");
				}
			}
		});
	}
</script>
</head>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>商家中心管理</a> &gt;
  <a href="userBrandList.do">用户品牌管理</a> &gt;
  <a href="#">编辑用户品牌</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="updateUserBrand.do" method="post" id="from1" enctype="multipart/form-data">
  	<input type="hidden" name="userBrandPojo.id" value="${userBrandPojo.id}" class="floatLeft" id="ticketName" />
  	<input type="hidden" name="brandDicPojo.id" value="${userBrandPojo.brandId}" class="floatLeft" id="ticketName" />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
  <!-- <tr>
	    <td align="right" class="grey" width="15%">品牌名称：</td>
		<td><select id="brandId" name="userBrandPojo.brandId" class="floatLeft" ></select>					
		<span id="brandId_mgId"></span></td>
	</tr> -->
	 <tr>
	    <td align="right" class="grey" width="15%">品牌名称：</td>
		<td>
		<input type="text" name="userBrandPojo.brandName" value="${userBrandPojo.brandName}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="brandName_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">品牌LOGO：</td>
        <td width="35%">
        <s:if test="userBrandPojo.logo != null"><img src="/upfiles/businessCenter/brandDic/${userBrandPojo.logo}" height='100px'/></s:if>
	    <input type="file" name="upfile" class="floatLeft" id="logo" />		
      	</td>
		<td><span id="logo_mgId"></span></td>
   </tr>
	<tr>
	    <td align="right" class="grey" width="15%">品牌描述：</td>
		<td width="35%">
        <textarea class="floatLeft" rows="6" cols="50" name="userBrandPojo.brandDisc" id="content">${userBrandPojo.brandDisc}</textarea>
		<span id="brandDisc_mgId"></span></td>
	</tr>
	<tr>
		<td align="right" class="grey" width="15%">授权类型：</td>
        <td><select name="userBrandPojo.grantType" id="userBrandPojo.grantType"  class="floatLeft">
							<option value="1" <s:if test="userBrandPojo.grantType==1">selected="selected"</s:if>>自主品牌</option>
							<option value="2" <s:if test="userBrandPojo.grantType==2">selected="selected"</s:if>>独家代理</option>
							<option value="3" <s:if test="userBrandPojo.grantType==3">selected="selected"</s:if>>一级代理</option>
							<option value="4" <s:if test="userBrandPojo.grantType==4">selected="selected"</s:if>>二级代理</option>
							<option value="5" <s:if test="userBrandPojo.grantType==5">selected="selected"</s:if>>三级代理</option>
				    		</select></td>					
		<td><span id="grantType_mgId"></span></td>
    </tr>
	<tr>
    <td align="right" class="grey" width="15%">品牌有效期：</td>
	<td>
	从
	<input id="s" name="userBrandPojo.startDateStr" value="${userBrandPojo.startDateStr}" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',minDate:'2010-01-01'})"/>       
	至
	<input id="e" name="userBrandPojo.endDateStr" value="${userBrandPojo.endDateStr}" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'s\')}'})"/>      
	</td>
	<td><span id="startDate_mgId"></span><span id="endDate_mgId"></span></td>
	</tr>
	<!--<tr>
	    <td align="right" class="grey" width="15%">主营品类：</td>
		<td>玩具文体</td>
	</tr>-->
	<tr>
		<td align="right" class="grey" width="15%">经营等级：</td>
        <td><select name="userBrandPojo.manageLevel" id="userBrandPojo.manageLevel"  class="floatLeft">
							<option value="1" <s:if test="userBrandPojo.manageLevel==1">selected="selected"</s:if>>品牌</option>
							<option value="2" <s:if test="userBrandPojo.manageLevel==2">selected="selected"</s:if>>单品</option>
				    		</select></td>					
		<td><span id="manageLevel_mgId"></span></td>
    </tr>
	<tr>
	    <td align="right" class="grey" width="15%">排序：</td>
		<td>
		<input type="text" name="userBrandPojo.sorting" value="${userBrandPojo.sorting}" class="floatLeft" id="ticketName" />		
		</td>
		<td><span id="sorting_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">审核状态：</td>
        <td><select name="userBrandPojo.status" id="userBrandPojo.status"  class="floatLeft">
							<option value="0" <s:if test="userBrandPojo.status==0">selected="selected"</s:if>>未审核</option>
							<option value="1" <s:if test="userBrandPojo.status==1">selected="selected"</s:if>>已审核</option>
				    		</select></td>					
		<td><span id="status_mgId"></span></td>
    </tr>
    <tr>
	    <td align="right" class="grey" width="15%">证书1：</td>
		<td>
						<input type="file" class="floatLeft" name="upfile1" class="floatLeft" id="specialPic1">
						<font color="#FF0000">图片建议尺寸：800*500</font>
		</td>
		<td>
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userBrand/${userBrandPojo.image1}" height="100px" />
			<span id="image1_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">证书2：</td>
		<td>
						<input type="file" class="floatLeft" name="upfile2" class="floatLeft" id="specialPic2">
						<font color="#FF0000">图片建议尺寸：800*500</font>
		</td>
		<td>
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userBrand/${userBrandPojo.image2}" height="100px" />
            <span id="image2_mgId"></span></td>
	</tr>
	<tr>
	    <td align="right" class="grey" width="15%">证书3：</td>
		<td>
						<input type="file" class="floatLeft" name="upfile3" class="floatLeft" id="specialPic3">
						<font color="#FF0000">图片建议尺寸：800*500</font>
		</td>
		<td>
			<img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userBrand/${userBrandPojo.image3}" height="100px" />
            <span id="image3_mgId"></span></td>
	</tr>
    </table> 
    </form>
 </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
</div>
</div>
</body>
</html>
<script>
	$(document).ready(function(){
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});
	//var userId       =new tt.Field("用户ID ","userBrandPojo.userId").setMsgId("userId_mgId");	
	var brandName      =new tt.Field("品牌名称 ","userBrandPojo.brandName").setMsgId("brandName_mgId");
	var brandDisc    =new tt.Field("品牌描述 ","userBrandPojo.brandDisc").setMsgId("brandDisc_mgId");
	var grantType    =new tt.Field("授权类型","userBrandPojo.grantType").setMsgId("grantType_mgId");
	var startDate    =new tt.Field("开始时间 ","userBrandPojo.startDateStr").setMsgId("startDate_mgId");
	var endDate      =new tt.Field("结束时间 ","userBrandPojo.endDateStr").setMsgId("endDate_mgId");	
	//var mainCategory =new tt.Field("主营品类","userBrandPojo.mainCategory").setMsgId("mainCategory_mgId");
	var manageLevel  =new tt.Field("经营等级","userBrandPojo.manageLevel").setMsgId("manageLevel_mgId");
	var sorting      =new tt.Field("排序","userBrandPojo.sorting").setMsgId("sorting_mgId");	
	var v_status     =new tt.Field("审核状态","userBrandPojo.status").setMsgId("status_mgId");				
	var image1       =new tt.Field("证书1","userBrandPojo.image1").setMsgId("image1_mgId");			
	var image2       =new tt.Field("证书2","userBrandPojo.image2").setMsgId("image2_mgId");		
	var image3       =new tt.Field("证书3","userBrandPojo.image3").setMsgId("image3_mgId");
	tt.vf.req.add(brandName,brandDisc,grantType,startDate,endDate,manageLevel,sorting,v_status);
	
	/**
	  点击图片显示原图
	 **/
	$(document).delegate(".info_table img","click",function(){
		var imgSrc = $(this).attr("src");
		$("body").append("<div id='popup' onClick='$(\"#popup\").remove();' style='position:fixed;top:0;left:0;bottom:0;right:0;z-index:999;overflow:auto;background:#333;background:rgba(0,0,0,0.75);'>"+
						"<img src='"+ imgSrc +"' style='display:block;max-width:50%;margin:10% auto;' /></div>");
	});	
</script>