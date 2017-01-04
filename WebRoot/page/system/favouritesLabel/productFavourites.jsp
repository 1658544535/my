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
		$("#idform").attr("action","delFavouritesDetailLabel.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkUserCirclePostAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkUserCirclePost.do?timeId=${timeId}&userCirclePostPojo.id="+val;	
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
			queryData("findUserCirclePostCount.do?timeId=${timeId}", "findUserCirclePostList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckUserCirclePost.do?timeId=${timeId}&userCirclePostPojo.id="+val;	
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
			queryData("findUserCirclePostCount.do?timeId=${timeId}", "findUserCirclePostList.do?timeId=${timeId}&randdelete="+rand);
		}else{
			alert("取消审核失败");
		}
	}
function del(val){
		if(confirm("你真的想删除该记录么？")){
			var url = "delFavouritesDetailLabelOne.do?yourFavouritesDetailPojo.id="+val;	
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
			queryData("userCirclePostFavouritesProCount.do", "userCirclePostFavouritesList.do?randquery="+rand);
		}else{
			alert("删除失败");
		}
	}
	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">标签管理</a> &gt; <a href="favouritesLabelList.do">有你喜欢</a> &gt; <a href="#">查看玩具</a></div>
    <!-- <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div> -->
		<!-- 查询开始 -->

		<form action="" method="post" id="sysform">
			
			<!-- <div id="search_show" style=""> -->
				<table width="100%" border="0" class="Search_table">
					<input type="hidden" name="yourFavouritesPojo.id" value="${yourFavouritesPojo.id }">
					<input type="hidden" name="yourFavouritesPojo.contentType" value="${yourFavouritesPojo.contentType }">
					<%-- <tr>
						<td align="right">笔记标题：</td>
						<td><label><input type="text" name="userCirclePostPojo.title" id="userCirclePostPojo.title" value=""></label></td>
					    <td align="right">审核状态：</td>
						<td><select name="userCirclePostPojo.status" id="userCirclePostPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">审核通过</option>
							<option value="2">审核失败</option>
							<!-- <option value="3">删除</option> -->
				    		</select><div id="status_mgId"></div></td>
	                </tr>

	              <tr>
						<td align="right">发帖人：</td>
						<td><label><input type="text" name="userCirclePostPojo.userName" id="userCirclePostPojo.userName" value=""></label></td>
						
						<td align="right" >发帖时间：</td><td width="80px"  style="padding: 0px 0px;">从<input style="min-width:60px;" name="userCirclePostPojo.createDateStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td>到<input style="min-width:60px;" name="userCirclePostPojo.createDateEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td align="right"></td>
					
				</tr>  				
	      								                	     <tr>
	    <td align="right" width="15%">品类：</td>
		<td><select id="age" name="userCirclePostPojo.ageType" class="floatLeft"  onchange="setSecond(this)">
		<option value="">---- 选择年龄段 ----</option>
		<option value="1" >0~6月</option>  
        <option value="2" >6~12月</option> 
        <option value="3" >1~3岁</option>
        <option value="4" >3~6岁</option>
        <option value="5" >6~12岁</option>
        <option value="6" >12~16岁</option> 
		</select>
		<td><select id="second" name="userCirclePostPojo.skillType" class="floatLeft"  onchange="setThree(this)">
		</select>	
		</td>
		<td><select id="three" name="userCirclePostPojo.secSkillType" class="floatLeft" onchange="setFour(this)">
		</select>	
		</td>
		<td><select id="four" name="userCirclePostPojo.productType" class="floatLeft">
		</select>	
		</td>
		
		
   	

	</tr> --%>
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<!-- <div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>

				<div class="Clear"></div> -->
			<!-- </div> -->
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
  <div class="h15"></div>
  <div>
<a class="Add_btn" href="productFavouritesAdd.do?yourFavouritesPojo.id=${yourFavouritesPojo.id }&yourFavouritesPojo.contentType=${yourFavouritesPojo.contentType }" >添加</a>
<!--<a class="Add_btn" onclick="checkAll()">批量审核</a>-->
<!-- <a class="submit_btn" href="#" onclick="query_btn2(0)">审核中</a>
<a class="submit_btn" href="#" onclick="query_btn2(1)">审核成功</a>
<a class="submit_btn" href="#" onclick="query_btn2(2)">审核失败</a>
<a class="submit_btn" href="#" onclick="query_btn2(3)">删除</a> -->

  <form action="deletecouponAllById.do" id="idform"  method="post" >
    <table width="100%" border="0" class="Info_list_table">
  	<input type="hidden" name="yourFavouritesPojo.id" value="${yourFavouritesPojo.id }">
  	<input type="hidden" name="yourFavouritesPojo.contentType" value="${yourFavouritesPojo.contentType }">
    <tr>
		<th>商品名称</th>
		<th>商品ID</th>
		<th>公司名</th>
		<th>商品货号</th>
		<th>商品图片</th>
		<th>商品类别</th>
		<th>推荐序号</th>
		<th>添加日期</th>
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
		queryData("userCirclePostFavouritesProCount.do", "userCirclePostFavouritesList.do?randquery="+rand);
	}
	
	disPlayDel();
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ this.name + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td><img src='/upfiles/product/small/"+ this.image +"' width='100px' height='100px'></td>"+
				"<td>"+ this.productTypeName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.creatDateString + "</td>"+
				"<s:if test="#session.role.roleId != 7"><td><a class='del_btn' onclick=del('"+this.favId+"')>删除</a></td></s:if>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userCirclePostFavouritesList.do?randIni="+rand);
		
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

    $("#second").empty();
    select2(val);
     
}  

function select2(val) {
var ability = "${userCirclePostPojo.skillType}";
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
        	$("#second").append("<option value=''>----请选择----</option>");
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            if(o_msg[i].skillValue == ability){
            		selStr = " selected='selected' ";
            	}
            	$("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
            }
        }
    })
};

//第三个下拉框事件
function setThree(obj){  
    var val = obj.value;
    skillValuejs = val;
    $("#three").empty();
    selectThree(val);
     
}  

function selectThree(val) {
var secondAbility = "${userCirclePostPojo.secSkillType}";
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
            	if(o_msg[i].secondSkillValue == secondAbility){
            		selStr = " selected='selected' ";
            	}
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
var productType = "${userCirclePostPojo.productType}";
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
            	if(o_msg[i].productId == productType){
            		selStr = " selected='selected' ";
            	}
                $("#four").append("<option value=" + o_msg[i].productId + selStr +">" + o_msg[i].productName + "</option>");
            }
        }
    })
};
//----------四级联动结束-------------------

function query_btn2(o){
	$("select[name='userCirclePostPojo.status']").val(o);
	$("#query_btn").trigger('click');
	
	
}

function disPlayDel(){
	var o = $("select[name='userCirclePostPojo.status']").val();
	if(o == 3){
		$("body span[name='isDelete']").attr('style','display: none;');
		//$("body span[name='isDelete']").css('display','none;');
	}else{
		$("body span[name='isDelete']").attr('style','');
		//$("body span[name='isDelete']").css('display','');
	}
}

$("body").on("click", "input[name='tids']", function () {
	if($(this).next().text() == "选取"){
		$(this).next().addClass("blue");
		$(this).next().text("取消");
	}else{
		$(this).next().removeClass("blue");
		$(this).next().text("选取");
	}
});
</script>