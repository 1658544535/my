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
/**
$(function(){
	$("#end").click(function() {
		query();

	});
});
**/
function p_del() { 
	var msg = "您真的确定要取消审核吗？"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	} 

function p_update() { 
	var msg = "您真的确定要通过审核吗？"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	}
	
function p_del2() { 
	var msg = "您真的确定要删除吗？"; 
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

    $("#three").html("<option value=''>----请选择----</option>");
    $("#four").html("<option value=''>----请选择----</option>");
    select2(val);
     
}  

function select2(val) {
	$("#second").html("<option value=''>----请选择----</option>");
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val ,
        dataType: 'json',
        success: function (msg) {
        	var o_msg = eval(msg);
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
    $("#four").html("<option value=''>----请选择----</option>");
    selectThree(val);
     
}  

function selectThree(val) {
	$("#three").html("<option value=''>----请选择----</option>");
    $.ajax(
    {
        type: "get",
        url: "getSecondSkillTypes.do?skillValue="+val+"&ageValue="+ageValuejs,
        dataType: 'json',
        success: function (msg) {
        	var o_msg = eval(msg);
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
    selectFour(val);
     
}  

function selectFour(val) {
	$("#four").html("<option value=''>----请选择----</option>");
    $.ajax(
    {
        type: "get",
        url: "getProductTypes.do?secondSkillValue="+val+"&ageValue="+ageValuejs+"&skillValue="+skillValuejs,
        dataType: 'json',
        success: function (msg) {
        	var o_msg = eval(msg);
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
<div class="s_nav"><a href="#">发布管理</a> &gt; <a href="platformSpecialList.do">专题管理</a></div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="specialShowList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
	                	<td align="right">专题类别：</td>
						<td ><select name="platformSpecialPojo.specialCategories" id="platformSpecialPojo.specialCategories">
							<option value="">----请选择----</option>
							<option value="1">软文专题</option>
							<option value="2">商品专题</option>
				    		</select>
				    	</td>
	                 	<td align="right">专题类型：</td>
						<td ><select name="platformSpecialPojo.type" id="platformSpecialPojo.type" >
							<option value="">----请选择----</option>
							<option value="1">普通专题</option>
							<option value="2">首页专题</option>
							<option value="3">热门专题</option>
							<option value="4">经典专题</option>
				    		</select>
				    	</td>
				    </tr>
				    <tr>
						<td align="right">专题标题：</td>
						<td><input type="text" name="platformSpecialPojo.title" id="platformSpecialPojo.title" value=""></td>
				    	<td align="right">审核状态：</td>
				    	<td><select name="platformSpecialPojo.status" id="platformSpecialPojo.status">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核成功</option>
							<option value="2">审核失败</option>
							<option value="3">删除</option>
				    		</select>
				    	</td>
	                </tr>
					<tr>
						<td align="right" width="15%">专题标签：</td>
						<td colspan="3">
						<table>
							<tr>
								<td>
									<select id="ageType" name="platformSpecialPojo.ageType" class="floatLeft" onchange="setSecond(this)">
										<option value="">---- 选择 ----</option>
										<option value="1">0~6月</option>  
								        <option value="2">6~12月</option> 
								        <option value="3">1~3岁</option>
								        <option value="4">3~6岁</option>
								        <option value="5">6~12岁</option>
								        <option value="6">12~16岁</option> 
									</select>
								</td>
								<td>
									<select id="second" name="platformSpecialPojo.skillType" class="floatLeft"  onchange="setThree(this)">
										<option value="">----请选择----</option>
									</select>
								</td>
								<td>
									<select id="three" name="platformSpecialPojo.secSkillType" class="floatLeft"  onchange="setFour(this)">
										<option value="">----请选择----</option>
									</select>
								</td>
								<td>
									<select id="four" name="platformSpecialPojo.productType" class="floatLeft">
										<option value="">----请选择----</option>
									</select>
								</td>
								<td>
									<select id="optionType" name="platformSpecialPojo.optionType" class="floatLeft">
										<option value="">----请选择----</option>
										<c:forEach items="${labelList}" var="labelList">
												<option value="${labelList.id}">${labelList.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
						</td>					
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

<a class="Add_btn" href="goAddPlatformSpecialList.do" id="btn4">新增</a><br>
<a class="submit_btn" href="#" onclick="query_btn2(0)">审核中</a>
<a class="submit_btn" href="#" onclick="query_btn2(1)">审核成功</a>
<a class="submit_btn" href="#" onclick="query_btn2(2)">审核失败</a>
<a class="submit_btn" href="#" onclick="query_btn2(3)">删除</a>

  <form action="deletecouponAllById.do" id="idform"  method="post" >
  
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>专题标题 </th>
		<th>标签 </th>
		<th>专题类别</th>
		<th>专题类型</th>
		<th>评分</th>
		<th>是否上热门</th>
		<th>审核状态</th>
		<th>更新时间</th>
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
	var id = $("input[name='platformSpecialPojo.id']").val();
	var r = /^[1-9][0-9]*$/;
	//if(id != "" && !r.test(id)){
	//	alert("专题ID必须为正整数！");
	//}else{
		var rand=Math.random() * ( 100000 + 1);
		queryData("platformSpecialListCount.do?" , "platformSpecialListAll.do?randquery="+rand);
	//}
		
	
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var op = "";
		var isHomePageBtn = "";
		var isHomePage = "";
		if(this.specialCategories == 2 && this.version == 0){
			op = "<a class='edit_btn' href='goSettingPlatformSpecialPorduct.do?types=6&titleId="+this.id+"'>设置商品</a>";
		}
		if(this.isHomePage == 1){
			isHomePage = "上热门";
		} else {
			isHomePage = "未上热门";
		}
		if((this.type == 3 || this.type == 4) && this.status == 1){
			isHomePageBtn = "<a class='edit_btn' href='setPSIsHomePage.do?platformSpecialPojo.id="+this.id + "&platformSpecialPojo.isHomePage=1' onclick='javascript:return isHomePage(1)'>上热门</a>"+
			"<a class='edit_btn' href='setPSIsHomePage.do?platformSpecialPojo.id="+this.id + "&platformSpecialPojo.isHomePage=0' onclick='javascript:return isHomePage(0)'>取消上热门</a>";
		}
		var operation = "";
		if(this.status == 3){
			op = "";
			isHomePageBtn = "";
		}else{
			operation = "<a class='edit_btn' href='goUpdatePlatformSpecial.do?platformSpecialPojo.id="+this.id+"'>编辑</a>"+
			"<a class='edit_btn' href='updateOnePlatformSpecial.do?platformSpecialPojo.id="+this.id + "' onclick='javascript:return p_update()'>审核</a>"+
			"<a class='edit_btn' href='delPlatformSpecial.do?platformSpecialPojo.id="+this.id + "' onclick='javascript:return p_del()'>取消审核</a>"+
			"<a class='edit_btn' href='delPlatformSpecial2.do?platformSpecialPojo.id="+this.id + "' onclick='javascript:return p_del2()'>删除</a>";
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.ageTypeStr+"; "+this.skillTypeStr+"; "+this.secSkillTypeStr+"; "+this.productTypeStr + "; "+this.optionTypeStr + "</td>"+
				"<td>"+ this.specialCategoriesStr + "</td>"+
				"<td>"+ this.typeStr + "</td>"+
				"<td>"+ this.score + "</td>"+
				"<td>"+ isHomePage + "</td>"+
				"<td>"+ this.statusStr + "</td>"+
				"<td>"+ this.updateDateStr + "</td>"+
				"<td>"+
				op+
				operation+
				isHomePageBtn+
				"</td>"+
				"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"platformSpecialListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
	
	function query_btn2(o){
		$("select[name='platformSpecialPojo.status']").val(o);
		$("#query_btn").trigger('click');
	}
	
</script>