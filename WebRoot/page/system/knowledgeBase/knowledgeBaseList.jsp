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
<script type="text/javascript">
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = true; 
		} 
		} 
	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = false
		
		} 
		} 
	}
}
function deleteAll(){
		$("#idform").attr("action","delKnowledgeBaseAll.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkKnowledgeBaseAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkKnowledgeBase.do?timeId=${timeId}&knowledgeBasePojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	}
	
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("findKnowledgeBaseCount.do?timeId=${timeId}", "findKnowledgeBaseList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckKnowledgeBase.do?timeId=${timeId}&knowledgeBasePojo.id="+val;	
			undoOpreator(url,null);
		}else{
			return ;
		}
	}
	
	function undoOpreator(url,params){
		MAOWU.ajax.get(url, params, ungoRefreshPage);
	}
	 
	function ungoRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("取消审核成功");
			queryData("findKnowledgeBaseCount.do?timeId=${timeId}", "findKnowledgeBaseList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "delKnowledgeBase.do?knowledgeBasePojo.id="+val;	
			doOpreator(url,null);
		}else{
			return ;
		}
		
	}
	
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}
	 
	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("删除成功");
			queryData("findKnowledgeBaseCount.do", "findKnowledgeBaseList.do?randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">知识库</a> &gt; <a href="#">知识库管理</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">知识库标题：</td>
						<td><label><input type="text" name="knowledgeBasePojo.title" id="knowledgeBasePojo.title" value=""></label></td>
					    <td align="right">审核状态：</td>
						<td><select name="knowledgeBasePojo.status" id="knowledgeBasePojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核通过</option>
							<option value="2">审核不通过</option>
				    		</select><div id="status_mgId"></div></td>
	                </tr>
	     <tr>
	    <td align="right" width="15%">标签：</td>
		<td><select id="age" name="knowledgeBasePojo.ageType" class="floatLeft"  onchange="setSecond(this)">
		<option value="">---- 选择年龄段 ----</option>
		<option value="1" >0~6月</option>  
        <option value="2" >6~12月</option> 
        <option value="3" >1~3岁</option>
        <option value="4" >3~6岁</option>
        <option value="5" >6~12岁</option>
        <option value="6" >12~16岁</option> 
		</select>
		<!--<td align="right" width="15%">能力：</td>-->
		<td><select id="second" name="knowledgeBasePojo.skillType" class="floatLeft" onchange="setThree(this)">
		</select></td>
		<td><select id="three" name="knowledgeBasePojo.secSkillType" class="floatLeft" onchange="setFour(this)">
		</select></td>
 <!-- <td align="right" >商品：</td>-->
	<td><select id="four" name="knowledgeBasePojo.productType" class="floatLeft" >
		</select></td>
  
   <!--<td align="right" >品类：</td>-->
   <td>
   <select name="knowledgeBasePojo.optionType" id="" class="floatLeft">
   <option value="">----请选择----</option>
   <c:forEach items="${labelPojoList}" var="labelPojo">
   <option value="${labelPojo.id}">${labelPojo.name}</option>
   </c:forEach>
   </select>
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
<a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<!--<a class="Add_btn" onclick="checkAll()">批量审核</a>-->
<a class="Add_btn" href="addKnowledgeBase.do">新增知识库</a>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>知识库标题</th>
		<th>标签</th>
		<th>添加时间</th>
		<th>评分</th>
		<th>审核状态</th>
	   <!-- <th>排序</th>-->
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
  
  
  
  <!---->


</div>
</body>
</html>


	


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("findKnowledgeBaseCount.do", "findKnowledgeBaseList.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.ageTypeName+"; "+this.skillTypeName+"; "+this.secSkillTypeName+"; "+this.productTypeName+"; "+this.optionTypeName + "</td>"+
				"<td>"+ this.createDateStr+ "</td>"+
				"<td>"+ this.score+ "</td>"+
				"<td>"+ this.statusName + "</td>"+		
				"<td><a  class='edit_btn' onclick=check("+this.id+")>审核通过</a>"+
				"<a  class='edit_btn' onclick=uncheck("+this.id+")>审核不通过</a>"+
				"<a  class='edit_btn' href='updateKnowledgeBaseList.do?knowledgeBasePojo.id="+this.id +"'>编辑</a>"+
				"<a  class='del_btn'  onclick=del("+this.id+")>删除</a></td>");
			//	"<a  target='_blank' >预览</a></td>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"findKnowledgeBaseList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
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