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

<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<script type="text/javascript">
	
</script>
<body>
<div class="sub_wrap">
  <div class="s_nav">
  <a>商品审核管理</a> &gt; 
  <a href="sellerCheckManage.do">商家审核信息列表</a> &gt; 
  <a href="#">商家审核</a>
  </div>
  <div class="h15"></div>
  <div>
  <form action="" method="post" id="form1" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>公司名称:</td>	
        <td colspan="3">${manufacturerPojo.company }</td>
      </tr>
   <!--   <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>经营范围:</td>	
        <td colspan="3">${manufacturerPojo.scopeBusiness }</td>
      </tr>-->
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>公司法人:</td>	
        <td colspan="3">${manufacturerPojo.legalPerson }</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>法人资质:</td>	
        <td colspan="3">
	        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image4 }' height='100px'/>
	        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image5 }' height='100px'/>
        </td>
      </tr>
   <!--   <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>旗下品牌:</td>	
        <td colspan="3">${manufacturerPojo.brand }</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>公司地址:</td>	
        <td colspan="3">${manufacturerPojo.address }</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>公司电话:</td>	
        <td colspan="3">${manufacturerPojo.companyPhone }</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>公司传真:</td>	
        <td colspan="3">${manufacturerPojo.fax }</td>
      </tr>-->
  	</table>
  	<hr />
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>业务联系人姓名:</td>	
        <td colspan="3">${manufacturerPojo.contact }</td>
      </tr>
     <!-- <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>业务联系人QQ&nbsp;:</td>	
        <td colspan="3">${manufacturerPojo.QQ }</td>
      </tr>-->
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>业务联系人电话:</td>	
        <td colspan="3">${manufacturerPojo.phone }</td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>业务联系人邮箱:</td>	
        <td colspan="3">${manufacturerPojo.email }</td>
      </tr>
  	</table>
  	<hr />
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>身份证有效期:</td>	
        <td colspan="3">从&nbsp;
	        <font color="red">${userCertificatesPhotoPojo.image4BeginDate }</font>
	        &nbsp;至&nbsp;
	        <font color="red">${userCertificatesPhotoPojo.image4EndDate }</font>
        </td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>营业执照有效期:</td>	
        <td colspan="3">从&nbsp;
	        <font color="red">${userCertificatesPhotoPojo.image1BeginDate }</font>
	        &nbsp;至&nbsp;
	        <font color="red">${userCertificatesPhotoPojo.image1EndDate }</font>
        </td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>组织机构有效期:</td>	
        <td colspan="3">从&nbsp;
	        <font color="red">${userCertificatesPhotoPojo.image2BeginDate }</font>
	        &nbsp;至&nbsp;
	        <font color="red">${userCertificatesPhotoPojo.image2EndDate }</font>
        </td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>营业执照:</td>	
        <td colspan="3"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image1 }' height='100px'/></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>组织机构代码证:</td>	
        <td colspan="3"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image2 }' height='100px'/></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>税务登记证:</td>	
        <td colspan="3"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image3 }' height='100px'/></td>
      </tr>
    <!--  <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>合同证:</td>	
        <td colspan="3"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image6 }' height='100px'/></td>
      </tr>
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>其他资质照（可不填）:</td>	
        <td colspan="3"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image7 }' height='100px'/></td>
      </tr>-->
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>开户许可证:</td>	
        <td colspan="3"><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.image8 }' height='100px'/></td>
      </tr>
  	</table>
  	<hr />
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>质检图片:</td>	
        <td colspan="3">
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.qcImage1 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.qcImage2 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.qcImage3 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.qcImage4 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.qcImage5 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.qcImage6 }' height='100px'/>
        </td>
      </tr>
  	</table>
  <hr />
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
     <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>品牌授权证明图片:</td>	
        <td colspan="3">
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage1 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage2 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage3 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage4 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage5 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage6 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage7 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage8 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage9 }' height='100px'/>
        <img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/userCertificatesPhoto/${userCertificatesPhotoPojo.blImage10 }' height='100px'/>
        </td>
      </tr>
  	</table>
    </form>
   </div>
   <div id="returnText" style="display: none;">
   <hr />
    <form action="sellerReturning.do?manufacturerPojo.userId=${manufacturerPojo.userId }" method="post" id="form2" enctype="multipart/form-data">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" name="Info_list_table">
      <tr>
        <td align="right" class="grey" width="15%" style='word-wrap: break-word;'>请填写退回原因:</td>	
        <td><textarea rows="10" cols="100" name="manufacturerPojo.returnContent" id="returnContent">${manufacturerPojo.returnContent }</textarea></td>
      </tr>
    </table>
    </form>
    <div class="Btn_div">
  		<input type="button"  class="ok_btn" value="确定退回修改" id="sbutton3" onclick="returnsubmit(${manufacturerPojo.userId })"/>
  </div>
  </div>
  <div class="Btn_div">
  		<button type="input" class="back_btn" onclick="window.history.back()">返回</button>
  		<c:if test="${manufacturerPojo.status == 0 }">
  		<input type="button"  class="ok_btn" value="审核通过" id="sbutton" onclick="checking(${manufacturerPojo.userId })"/>
  		<input type="button"  class="ok_btn" value="退回修改" id="sbutton2" onclick="returning()"/>
  		<font color="red">单击填写退回原因</font>
  		</c:if>
  </div>
</div>

</body>
</html>


<script>
function checking(uid){
	if(confirm("确定要通过审核吗？")){
		var url = "sellerCheckSubmit.do?manufacturerPojo.userId="+uid;	
		window.location.href = url;
	}else{
		return ;
	}
}

var t = 0;
function returning(){
	if(t == 0){
		$("#returnText").css('display','block');
		t = 1;
	}else{
		$("#returnText").css('display','none');
		t = 0;
	}
}

function returnsubmit(uid){
	if(confirm("确定要退回修改吗？")){
		if($("#returnContent").val().trim() == ""){
			alert("请填写退回原因！");
		}else{
			document.getElementById("form2").submit();
		}
	}else{
		return ;
	}
}

//点击图片显示原图
$(document).delegate("table[name='Info_list_table'] img","click",function(){
	var imgSrc = $(this).attr("src");
	$("body").append("<div id='popup' onClick='$(\"#popup\").remove();' style='position:fixed;top:0;left:0;bottom:0;right:0;z-index:999;overflow:auto;background:#333;background:rgba(0,0,0,0.75);'>"+
	"<img src='"+ imgSrc +"' style='display:block;max-width:50%;margin:10% auto;' /></div>");
});
</script>