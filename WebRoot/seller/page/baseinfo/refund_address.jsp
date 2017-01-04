<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
    </head>
	<body>
		<jsp:include page="../sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <h1 class="seller-title">
                           	 我的退货地址
                        </h1>
                        <div class="address">
                            <h2>
                              	  已保存的有效地址
                            </h2>
                        <div class="box">
                        <table class="ui-table mt-8">
	                    <thead>
		            	<tr>
		                	<th>收货人</th>
		                	<th>所在地区</th>
		                	<th>街道地址</th>
		                    <th>邮编</th>
		                    <th>手机</th>
		                    <th></th>
		                    <th>操作</th>
		                </tr>
		                </thead>		                
	                    <tbody id="body"></tbody> 
	                    </table>
                        </div>
                        <h2 class="add-tip">
                            <span class="tips-label">
                             	  新增退货地址
                            </span>
                            <span class="tips">
                                	请如实填写，确保商品发往正确的地址
                            </span>
                        </h2>                     
                        <div class="box">
                            <form action="addAddressWeb.do" method="post" accept-charset="utf-8" id="seller-account-refund-address" class="ui-form">
                                <fieldset>
                                    <div class="ui-form-item">
                                        <label for="province" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                           	 收货地址
                                        </label>
                                        <select id="province" name="deliveryAddressPojo1.province" class="floatLeft" style="width: 150px;">                                                     
                                        </select>
                                        <select id="city" name="deliveryAddressPojo1.city" class="floatLeft" style="width: 120px;">
                                        </select>
                                        <select id="area" name="deliveryAddressPojo1.area" class="floatLeft" style="width: 120px;">
                                        </select>
                                        <br/>
                                        <span id="province_mgId"></span>
                                        <span id="city_mgId"></span>
                                        <span id="area_mgId"></span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label for="address" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                           	 详细地址
                                        </label>
                                        <textarea id="address" name="deliveryAddressPojo1.address" class="ui-textarea" placeholder="不需要重复填写省市区，必须大于5个字符，小于120个字符">${deliveryAddressPojo1.address}</textarea>
                                        <br/>
                                        <span id="address_mgId"></span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label for="postcode" class="ui-label">
                                          	  邮政编码
                                        </label>
                                        <input class="ui-input" id="postcode" name="deliveryAddressPojo1.postcode" type="text" value="${deliveryAddressPojo1.postcode}">
                                        <br/>
                                        <span id="postcode_mgId"></span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label for="consignee" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                            	收货人姓名
                                        </label>
                                        <input class="ui-input" id="consignee" name="deliveryAddressPojo1.consignee" type="text" value="${deliveryAddressPojo1.consignee}">
                                        <br/>
                                        <span id="consignee_mgId"></span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label for="consigneePhone" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                           	 手机/座机号码
                                        </label>
                                        <input class="ui-input" id="consigneePhone" name="deliveryAddressPojo1.consigneePhone" type="text" value="${deliveryAddressPojo1.consigneePhone}">
                                        <br/>
                                        <span id="consigneePhone_mgId"></span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label for="isDefault" class="ui-label">
                                            <span class="ui-form-required">
                                                *
                                            </span>
                                            	默认地址
                                        </label>
                                        <select name="deliveryAddressPojo1.isDefault" id="isDefault"  class="floatLeft">
												<option value="0">否</option>
												<option value="1">是</option>
												</select>
                                        <br/>
                                        <span id="isDefault_mgId"></span>
                                    </div>
                                    <div class="ui-form-item">
                                        <a id="refund-address-save" class="ui-button ui-button-lred" >
                                            	确定
                                        </a>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                     </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	var pageSize = 10;
	
	var province       =new tt.Field("省份","deliveryAddressPojo1.province").setMsgId("province_mgId");
	var city           =new tt.Field("城市","deliveryAddressPojo1.city").setMsgId("city_mgId");
	var area           =new tt.Field("区域","deliveryAddressPojo1.area").setMsgId("area_mgId");
	var address        =new tt.Field("详细地址","deliveryAddressPojo1.address").setMsgId("address_mgId");
	var postcode       =new tt.Field("邮政编码","deliveryAddressPojo1.postcode").setMsgId("postcode_mgId");
	var consignee      =new tt.Field("收货人姓名","deliveryAddressPojo1.consignee").setMsgId("consignee_mgId");
	var consigneePhone =new tt.Field("手机/座机号码","deliveryAddressPojo1.consigneePhone").setMsgId("consigneePhone_mgId");
	var isDefault      =new tt.Field("默认地址","deliveryAddressPojo1.isDefault").setMsgId("isDefault_mgId");
	tt.Conf.reqStarCls = ""; 
    tt.vf.req.add(province,city,area,address,consignee,consigneePhone,isDefault);
	
	function installPage() {
		$("#body").append(
			"<tr>"+
				"<td>"+this.consignee+"</td>"+
				"<td>"+this.realarea+"</td>"+
				"<td>"+this.address+"</td>"+
	            "<td>"+this.postcode+"</td>"+
	            "<td>"+this.consigneePhone+"</td>"+
	            "<td>"+this.isDefaultName+"</td>"+
		        "<td width='80px'><a href='goUpdateAddressWeb.do?deliveryAddressPojo.id="+this.id +"'>编辑</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"+
		        "<a href='delAddressWeb.do?deliveryAddressPojo.id="+this.id +"'>删除</a>"+
		        "</td></tr>"
                );
	}
	//分页展现页面函数 
	$(function() {
		/** 首次要初始化分页**/
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value='page.rowCount'/>, "getAddressListWeb.do?randIni="+rand,10);	
		
		$("#refund-address-save").click(function(){	
			if(tt.validate()){
				document.getElementById("seller-account-refund-address").submit();					
			}
		});
		
		select1();
		$('#province').bind("change", select2);
        $('#city').bind("change", select3);
	});
	
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					var selectedStr = "";
                    if("${deliveryAddressPojo1.province}" == o_msg[i].id){
                    	selectedStr = "selected='selected'";
                    }
					$("#province").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
				}
				if("${deliveryAddressPojo1.province}"!=null && "${deliveryAddressPojo1.province}"!=""){
					select2();
				}
			}
		});
	}
	
	function select2() {
        $("#city").html("");
        $("#city").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
            dataType: 'json',
            success: function (msg) {
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	var selectedStr = "";
                	if("${deliveryAddressPojo1.city}" == o_msg[i].id){
                		selectedStr = "selected='selected'";
                	}
                    $("#city").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
                }
                if("${deliveryAddressPojo1.city}"!=null && "${deliveryAddressPojo1.city}"!=""){
					select3();
				}
            }
        });
    }
	function select3() {
        $("#area").html("");
        $("#area").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
            dataType: 'json',
            success: function (msg) {
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	var selectedStr = "";
                	if("${deliveryAddressPojo1.area}" == o_msg[i].id){
                		selectedStr = "selected='selected'";
                	}
                    $("#area").append("<option value=" + o_msg[i].id + " "+selectedStr+"> " + o_msg[i].name + "</option>");
                }
            }
        });
    }
</script>