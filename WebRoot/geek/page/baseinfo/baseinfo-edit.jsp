<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/default.css" media="all" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
    <script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
	<%-- <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/default.css" media="all" />
	<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/spage.css"/> --%>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script>
		//图片更换
		$(document).delegate(".uploadPreview_imgfile","change",function(){
			var _this = $(this);
			var url = _this.val();
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(_this.get(0).files[0]);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(_this.get(0).files[0]);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
			}
			_this.siblings(".uploadPreview_img").find("img").attr("src", url);
			_this.siblings(".uploadPreview_img").show();
		 _this.siblings(".uploadPreview_note").hide()
		});
</script>
</head>

<body>
<jsp:include page="../geekHeader.jsp"/>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                        .ui-radio{margin:0 5px 0 20px}.ui-nav-item-current a,.ui-nav-item-current a:hover{filter:none;background:none;color:#fff}.ui-nav-item-current .ui-nav-submain{display:none;background:#fff}.ui-nav-subitem-current a,.ui-nav-subitem-current a:hover{border:none;-webkit-box-shadow:none;box-shadow:none}.uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
                    </style>
                    <h1 class="seller-title">修改基本信息</h1>
                    

                    <div class="address">
                        <div class="box">
                            <form action="updateBaseInfo.do" method="post" accept-charset="utf-8" class="ui-form p50" enctype="multipart/form-data" id="from1">
                              <input type="hidden" name="userInfoPojo.id" value="${userInfoPojo.id}">
                               <input type="hidden" name="manufacturerPojo.id" value="${manufacturerPojo.id}">
                               <input type="hidden" name="shopPojo.id" value="${shopPojo.id}">
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 头像</label>
                                    <div class="fl picInfo">
                                        <div class="uploadify">
                                            <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                            <div class="uploadPreview_img" >
                                                <img  src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userlogo/${sysLoginPojo.image}" style="width:120px;height:120px;">
                                            </div>    
                                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile1" data-id="'+dataId+'"style="position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer;"/>                                        </div>
                                    </div>
                                </div>
                                
                                
                                
                              <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 昵称</label>
                                    <input class="ui-input" name="sysLoginPojo.name" type="text" value="${sysLoginPojo.name}" /><div id="name_mgId"></div>
                                </div> 
                                
                      
                       <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 宝宝性别</label>
                                    <label><input class="ui-radio" name="userInfoPojo.babySex" type="radio" value="1" <s:if test="userInfoPojo.babySex==1">checked="true"</s:if>/>王子</label>
                                    <label><input class="ui-radio" name="userInfoPojo.babySex" type="radio" value="2" <s:if test="userInfoPojo.babySex==2">checked="true"</s:if>/>公主</label>
                                </div>
                                
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 宝宝生日</label>
                                   <input id="e" name="userInfoPojo.babyBirthday" value="${userInfoPojo.babyBirthday}" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                                </div>
                                
                          <!--      <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> QQ</label>
                                    <input class="ui-input" name="userInfoPojo.QQ" type="text" value="${userInfoPojo.QQ}" /><div id="QQ_mgId"></div>
                                </div>
                                
                             <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 公司</label>
                                    <input class="ui-input" name="manufacturerPojo.company" type="text" value="${manufacturerPojo.company}" /><div id="company_mgId"></div>
                                </div>  

                                  <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 地区</label>
                                    <select id="province" name="shopPojo.province" class="ui-input ui-input-checkcode" style="width: 130px;">
                                     
                                    </select>
                                    <select id="city" name="shopPojo.city" class="ui-input ui-input-checkcode" style="width: 150px;">
                           
                                    </select>
                                    <select id="area" name="shopPojo.area" class="ui-input ui-input-checkcode" style="width: 145px;">
                                   
                                    </select><div id="province_mgId"></div><div id="city_mgId"></div><div id="area_mgId"></div>
                                </div>

                                <div class="ui-form-item">
                                    <label for="address" class="ui-label">
                                        <span class="ui-form-required">*</span> 详细地址
                                    </label>
                                    <textarea name="shopPojo.address" class="ui-textarea" placeholder="不需要重复填写省市区，必须大于5个字符，小于120个字符">${shopPojo.address}</textarea>
                                </div>
                     -->
                                <div class="ui-form-item">
                                    <button type="button" class="ui-button ui-button-lred" id="sbutton">
                                        确定
                                    </button>
                                    <button type="input" class="ui-button ui-button-lred" onclick="window.history.back()">返回</button>
                                </div>
                                
                                
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../geekFooter.jsp"/>
    
    <script src="../js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="../js/base.js" type="text/javascript"></script>
    <script src="../js/area.js" type="text/javascript"></script>
    <script>
    var v_name             = new tt.Field("昵称 ","sysLoginPojo.name").setMsgId("name_mgId");  
    var v_phone              = new tt.Field("手机 ","userInfoPojo.phone").setMsgId("phone_mgId");
    var v_QQ              = new tt.Field("QQ ","userInfoPojo.QQ").setMsgId("QQ_mgId");
    var v_company              = new tt.Field("公司 ","manufacturerPojo.company").setMsgId("company_mgId");
    var v_province              = new tt.Field("省份 ","shopPojo.province").setMsgId("province_mgId");
    var v_city              = new tt.Field("城市 ","shopPojo.city").setMsgId("city_mgId");
    var v_area              = new tt.Field("区域 ","shopPojo.area").setMsgId("area_mgId");
    var v_address              = new tt.Field("地址 ","shopPojo.address").setMsgId("address_mgId");
    tt.vf.req.add(v_name,v_phone,v_QQ,v_company,v_province,v_city,v_area,v_address);
    
    
        
    		$("#sbutton").click(function() {
    	
    			if (tt.validate()) {
    			
        				document.getElementById("from1").submit();
        			
    			}
    			
    		});
    	
    
    
    
    
    
        //地区联动
        //$(function(){_init_area();});
    $(function(){
          
		  select1();
		  select2();
		  select3();
		  $('#province').bind("change", select2);
          $('#city').bind("change", select3);
         var ue = UE.getEditor('editor');
     });
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var s = "";
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${shopPojo.province}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#province").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
					if("${shop.province}" == o_msg[i].id){
						select2();
					}
				}
			}
		})
	};
	function select2() {
		$("#city").html("");
        $("#city").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
            dataType: 'json',
            success: function (msg) {
            	var s = "";
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	if("${shopPojo.city}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
                    $("#city").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
                    if("${shop.city}" == o_msg[i].id){
						select3();
					}
                }
            }
        })
    };
	function select3() {
		$("#area").html("");
        $("#area").append("<option value=''>- 请选择 -</option>");
       $.ajax(
       {
           type: "post",
           url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
           dataType: 'json',
           success: function (msg) {
        	   var s = "";
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${shopPojo.area}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#area").append("<option value=" + o_msg[i].id + " "+s+"> " + o_msg[i].name + "</option>");
				}
           }
       })
   };
    </script>
</body>

</html>
