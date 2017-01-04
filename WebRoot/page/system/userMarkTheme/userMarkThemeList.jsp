<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script>
$(function(){
	$("#end").click(function() {
		query();

	});
});
function p_del() { 
	var msg = "您真的确定要审核失败吗？"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	} 

function p_update() { 
	var msg = "您真的确定要审核成功吗？"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	}
function isHomePage(flag) { 
	if (flag == 1){
		var msg = "您真的确定要上热门吗？"; 
	} else {
		var msg = "您真的确定要取消上热门吗？";
	}
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	}
//-------------四级联动开始----------------	

//全局变量
var ageValuejs;  
var skillValuejs;

//第二个下拉框事件
function setSecond(obj){  
  var val = obj.value;
  ageValuejs = obj.value;
    if(val==""){
      $("#three").empty();
      $("#four").empty();
      }
  $("#second").empty();
  select2(val);
   
}  

function select2(val) {
  $.ajax(
  {
      type: "get",
      url: "getSkillTypes.do?ageId="+val ,
      dataType: 'json',
      success: function (msg) {
      	console.log(msg);
      	var o_msg = eval(msg);
      	$("#second").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
          }
      }
  })
};

//第三个下拉框事件
function setThree(obj){  
  var val = obj.value;
  skillValuejs = val;
   if(val==""){
      $("#four").empty();
      }
  $("#three").empty();
  selectThree(val);
   
}  

function selectThree(val) {
  //alert("quan ju :"+ageValuejs);
  $.ajax(
  {
      type: "get",
      url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
      dataType: 'json',
      success: function (msg) {
      	console.log(msg);
      	var o_msg = eval(msg);
      	$("#three").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#three").append("<option value=" + o_msg[i].secondSkillValue + selStr +">" + o_msg[i].secondSkillName + "</option>");
          }
      }
  })
};

//第四个下拉框事件
function setFour(obj){  
  var val = obj.value;
  $("#four").empty();
  selectFour(val);
   
}  

function selectFour(val) {
  //alert("quan ju :"+skillValuejs);
  $.ajax(
  {
      type: "get",
      url: "getProductTypes.do?secondSkillValue="+val+"&ageValue="+ageValuejs+"&skillValue="+skillValuejs,
      dataType: 'json',
      success: function (msg) {
      	console.log(msg);
      	var o_msg = eval(msg);
      	$("#four").append("<option value=''>----请选择----</option>");
          for (var i = 0; i < o_msg.length; i++) {
          	var selStr = "";
              $("#four").append("<option value=" + o_msg[i].productId + selStr +">" + o_msg[i].productName + "</option>");
          }
      }
  })
};
//----------四级联动结束-------------------
</script>
</head>
<body>

<div class="sub_wrap">
<div class="s_nav"><a href="#">创客管理</a> &gt; <a href="userMakerThemeList.do">创客专题管理</a></div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="userMakerThemeList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">创客昵称：</td>
						<td><label><input type="text" name="userMakerThemePojo.makerName"
								id="userMakerThemePojo.makerName"
								value=""></label></td>
						<td align="right">审核状态：</td>
						<td ><select name="userMakerThemePojo.status" id="userMakerThemePojo.status"  class="floatLeft" >
							<option value="">----请选择----</option>
							<option value="0">待审核</option>
							<option value="1">审核成功</option>
							<option value="2">审核失败</option>
				    		</select>
				    	</td>
				    	<td align="right">类型：</td>
						<td><select name="userMakerThemePojo.type" id="userMakerThemePojo.type"  class="floatLeft" >
							<option value="">----请选择----</option>
							<option value="1">创好玩</option>
							<option value="2">创选活动</option>
							<option value="3">普通创客专题</option>
				    		</select>
				    		<span id="message_type"></span>
				    	</td>

	                </tr>
	                <tr>
						<td align="right">创客品牌：</td>
						<td><label><input type="text" name="userMakerThemePojo.makerBrandName"
								id="userMakerThemePojo.makerBrandName"
								value=""></label>
						</td>
						<td align="right">专题名称：</td>
						<td><label><input type="text" name="userMakerThemePojo.specialName"
								id="userMakerThemePojo.specialName"
								value=""></label>
						</td>
						<td><input type="text" name="" id="" value="" hidden/></td>
						<td><input type="text" name="" id="" value="" hidden/></td>
					</tr>
	                
					<tr>
						<td align="right" width="15%">专题标签：</td>
						<td><select id="ageType" name="userMakerThemePojo.ageType" class="floatLeft" onchange="setSecond(this)">
								<option value="">---- 选择 ----</option>
								<option value="1">0~6月</option>  
						        <option value="2">6~12月</option> 
						        <option value="3">1~3岁</option>
						        <option value="4">3~6岁</option>
						        <option value="5">6~12岁</option>
						        <option value="6">12~16岁</option> 
							</select>					
						</td>
						<td><select id="second" name="userMakerThemePojo.skillType" class="floatLeft"  onchange="setThree(this)"></select></td>
						<td><select id="three" name="" class="floatLeft"  onchange="setFour(this)"></select></td>
						<td><select id="four" name="userMakerThemePojo.productType" class="floatLeft"></select></td>
						<td><select id="optionType" name="userMakerThemePojo.optionType" class="floatLeft">
							<option value="">----请选择----</option>
							<c:forEach items="${labelList}" var="labelList">
										<option value="${labelList.id}">${labelList.name}</option>
								</c:forEach>
							</select>
						</td>
						
						<!--<select id="productType" name="platformSpecialPojo.productType" class="floatLeft"></select>-->
						</td>
						
						<td><input type="text" name="" id="" value="" hidden/></td>
						
					</tr>
								
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
  <div class="h15"></div>
  <div>

  <form action="deletecouponAllById.do" id="idform"  method="post" >
  
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>创客昵称 </th>
		<th>专题名称 </th>
		<th>创客品牌</th>
		<th>标签</th>
		<th>类型</th>
		<th>创建时间</th>
		<th>评分</th>
		<th>审核状态</th>
		<th>是否上热门</th>
		<th>操作</th>
    </tr>
    <tbody id="body"></tbody>     
    </table>
    </form>
    <div class="page">
		<div class="floatleft">
			总共 <i id="rowcount">${page.rowCount }</i> 条/<i id="pagecount"></i>页
		</div>
		<div style="float: right" id="Pagination" class="pagination"></div>
		<div class="Clear"></div>
	</div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	var id = $("input[name='userMakerThemePojo.id']").val();
	var r = /^[1-9][0-9]*$/;
	//if(id != "" && !r.test(id)){
	//	alert("专题ID必须为正整数！");
	//}else{
		var rand=Math.random() * ( 100000 + 1);
		queryData("userMakerThemeListCount.do?" , "userMakerThemeListAll.do?randquery="+rand);
	//}
		
	
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var isHomePageBtn = "";
		var isHomePage = "";
		if(this.specialCategories == 2){
			op = "<a class='edit_btn' href='goSettingPlatformSpecialPorduct.do?types=6&titleId="+this.id+"'>设置商品</a>";
		}
		if(this.isHomePage == 1){
			isHomePage = "上热门";
		} else {
			isHomePage = "未上热门";
		}
		if(this.type == 2 || this.type == 1){
			isHomePageBtn = "<a class='edit_btn' href='setUMTIsHomePage.do?userMakerThemePojo.id="+this.id + "&userMakerThemePojo.isHomePage=1' onclick='javascript:return isHomePage(1)'>上热门</a>"+
			"<a class='edit_btn' href='setUMTIsHomePage.do?userMakerThemePojo.id="+this.id + "&userMakerThemePojo.isHomePage=0' onclick='javascript:return isHomePage(0)'>取消上热门</a>";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.makerName + "</td>"+
				"<td>"+ this.specialName + "</td>"+
				"<td>"+ this.makerBrandName + "</td>"+
				"<td>"+ this.ageTypeStr+"; "+this.skillTypeStr+"; "+this.secSkillTypeStr+"; "+this.productTypeStr + "; "+this.optionTypeStr + "</td>"+
				"<td>"+ this.typeStr + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.score + "</td>"+
				"<td>"+ this.statusStr + "</td>"+
				"<td>"+ isHomePage + "</td>"+
				"<td>"+
				"<a class='edit_btn' href='goUpdateUserMakerTheme.do?userMakerThemePojo.id="+this.id+"'>设置审核</a>"+isHomePageBtn+
				"<a class='edit_btn' href='goUpdateUserMakerThemeContent.do?userMakerThemePojo.id="+this.id+"'>编辑专题内容</a>"+
				"</td></tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userMakerThemeListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
	
	
	
</script>