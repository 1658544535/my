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
	var msg = "您真的要删除该记录吗？"; 
	if (confirm(msg)==true){ 
	return true; 
	}else{ 
	return false; 
	} 
	} 

function p_update() { 
	var msg = "您真的确定要进行审核吗？"; 
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
<div class="s_nav"><a>联动标签管理</a> </div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="ageSkillLinkListAll.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					
				    <tr>
						<td align="right"></td>

	                </tr>
					<tr>
						<td align="right" width="15%">联动标签：</td>
						<td colspan="3">
						<table>
							<tr>
								<td>
									<select id="ageType" name="ageSkillLinkPojo.ageId" class="floatLeft" onchange="setSecond(this)">
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
									<select id="second" name="ageSkillLinkPojo.skillId" class="floatLeft"  onchange="setThree(this)">
										<option value="">----请选择----</option>
									</select>
								</td>
								<td>
									<select id="three" name="ageSkillLinkPojo.secondSkillId" class="floatLeft"  onchange="setFour(this)">
										<option value="">----请选择----</option>
									</select>
								</td>
								<td>
									<select id="four" name="ageSkillLinkPojo.productType" class="floatLeft">
										<option value="">----请选择----</option>
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

<a class="Add_btn" href="goAddAgeSkillLink.do" id="btn4">新增</a>

  <form action="deletecouponAllById.do" id="idform"  method="post" >
  
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>适用年龄 </th>
		<th>能力 </th>
		<th>次能力</th>
		<th>商品标签</th>
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
		queryData("ageSkillLinkListCount.do?" , "ageSkillLinkListAll.do?randquery="+rand);
	//}
		
	
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		
		$("#body").append(
				"<tr>"+
				"<td>"+ this.ageName + "</td>"+
				"<td>"+ this.skillName+"</td>"+
				"<td>"+ this.secondSkillName + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td><a class='edit_btn' href='goUpdateAgeSkillLink.do?ageSkillLinkPojo.id="+this.id+"'>编辑</a>"+
				"<a class='edit_btn' href='delAgeSkillLink.do?ageSkillLinkPojo.id="+this.id + "' onclick='javascript:return p_del()'>删除</a>"+
				"</td>"+
				"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"ageSkillLinkListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
	
	
	
</script>