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
$(function(){
	channeSelect();//频道下拉
	$("#return").hide(); 
})
	var end='';
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
$(function(){
		$("#end").click(function() {
			end=1;
			query();
			$("#tip").hide(); 
			$("#sysform").hide();  	
			$("#end").hide();
			$("#btn1").hide();
			$("#btn2").hide();
			$("#btn3").hide();
			$("#btn4").hide();
			$("#return").show();
		});
});
$(function(){
		$("#return").click(function() {	
				$(location).attr('href', 'specialShowList.do');
		});
});
function deleteAll(){
		$("#idform").attr("action","delSpecialShowAll.do").submit();
	}
function checkAll(){
		$("#idform").attr("action","checkSpecialShowAll.do").submit();
	}
function checkProductAll(){
		$("#idform").attr("action","checkSpecialShowProductAll.do").submit();
	}
function check(val){
		if(confirm("确认要通过审核吗？")){
			var url = "checkSpecialShow.do?specialShowPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function uncheck(val){
		if(confirm("确认要取消审核吗？")){
			var url = "uncheckSpecialShow.do?specialShowPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function del(val){
		if(confirm("确认要删除此条吗？")){
			var url = "delSpecialShow.do?specialShowPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}	
function modify(val){
		if(confirm("确认要退回修改此条吗？")){
			var url = "modifySpecialShow.do?specialShowPojo.id="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
function channeSelect() {
$("#channelId").append("<option value=''>- 请选择 -</option>");
    $.ajax(
    {
        type: "post",
        url: "findChildrenChannelList.do?childrenChannelPojo.status=1",
        dataType: 'json',
        success: function (msg) {
        	var o_msg = eval(msg);
            for (var i = 0; i < o_msg.length; i++) {
                var selectedStr = "";
            	if("${specialShowPojo.channelId}" == o_msg[i].id){
            		selectedStr = "selected='selected'";
            	}
                $("#channelId").append("<option value=" + o_msg[i].id + " "+selectedStr+">" + o_msg[i].name + "</option>");
            }
        }
    })
};

	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">专场特卖管理</a> &gt; <a href="specialShowList.do">专场详情列表</a></div>
    <div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="specialShowList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">专场标题：</td>
						<td><label><input type="text" name="specialShowPojo.title"
								id="specialShowPojo.title"
								value=""></label></td>
		                <td align="right">状态：</td>
						<td><select name="specialShowPojo.status" id="specialShowPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">待审核</option>
							<option value="2">待排期</option>
							<option value="3">退回修改</option>
							<option value="4">排期完成</option>
				    		</select><div id="status_mgId"></div></td>
	                </tr>
					<tr>
						<td align="right">开始日期：</td>
						<td><input name="specialShowPojo.beginTimeStr" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
						<td align="right">结束日期：</td>
						<td><input name="specialShowPojo.endTimeStr" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>
					</tr>
					<tr>
						<td align="right">专场ID：</td>
						<td><input type="text" name="specialShowPojo.id" id="specialShowPojo.id" value=""></td>
						<td align="right">所属频道：</td>
						<td><select id="channelId" name="specialShowPojo.channelId" class="floatLeft" ></select></td>
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
<s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()" id="btn1">批量删除</a>
<a class="Add_btn" onclick="checkAll()" id="btn2">批量审核</a>
<a class="Add_btn" onclick="checkProductAll()" id="btn3">批量审核活动商品</a>
<a class="Add_btn" href="goAddSpecialShowList.do" id="btn4">新增专场</a>
<input type="button" value="显示所有已结束专场"  id="end" class="submit_btn" style="float: right;"  />
<input type="button" value="返回当前进行的专场"  id="return" class="submit_btn" style="float: right;" />
</s:if>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
    	<th>专场ID</th>
		<th>专场标题 </th>
		<th>专场名称 </th>
		<th>首页海报</th>
		<!--<th>所属频道</th>-->
		<th>开始时间</th>
		<th>结束时间</th>
		<th>更新时间</th>
		<th>折扣卖点</th>
		<th>具体优惠</th>
		<th>适用年龄段</th>	
		<th>排序</th>
		<th>审核状态</th>
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
</div>
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	var id = $("input[name='specialShowPojo.id']").val();
	var r = /^[1-9][0-9]*$/;
	if(id != "" && !r.test(id)){
		alert("专场ID必须为正整数！");
	}else{
		var rand=Math.random() * ( 100000 + 1);
		queryData("specialShowListCount.do?end="+end, "specialShowListAll.do?end="+end+"&randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var op1 ="";
		if(this.status == 2){
			op1 = "<a class='edit_btn' href='goUpdateSpecialShow.do?specialShowPojo.t=2&specialShowPojo.Id="+this.id +"'>进行排期</a>";
		}
		if(this.status == 4){
			op1 = "<a class='edit_btn' href='goUpdateSpecialShow.do?specialShowPojo.t=3&specialShowPojo.Id="+this.id +"'>修改排期</a>";
		}
		var op2 ="";
		if(this.status != 3){
			op2 = "<br/><a class='edit_btn' onclick=check("+this.id+")>通过审核</a>"+"<a class='edit_btn' onclick=uncheck("+this.id+")>取消审核</a><br/>";
			    if(this.status !== 4){
				op2 =op2+"<a class='edit_btn' onclick=modify("+this.id+")>退回修改</a>";
				}
	    }
	
		var op3 = "<a class='edit_btn' href='goUpdateSpecialShow.do?specialShowPojo.t=1&specialShowPojo.id="+this.id +"'>编辑</a>";
		if(this.status == 5){
			op2 = "";
			op3 = "";
		}
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ this.title + "</td>"+
				"<td>"+ this.name + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/specialShow/"+ this.banner + "' height='100px' /></td>"+
			  //"<td>"+ this.channelName + "</td>"+
				"<td>"+ this.beginTimeStr + "</td>"+
				"<td>"+ this.endTimeStr + "</td>"+
				"<td>"+ this.updateDateStr + "</td>"+
				"<td>"+ this.discountStr + "</td>"+
				"<td>"+ this.specificDiscount + "</td>"+			
				"<td>"+ this.ageRangeName + "</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='specialProductList.do?activityId="+this.activityId+"&specialProductPojo.userBrandId="+this.userBrandId+"&specialProductPojo.specialId="+this.id +"'>专场商品设置</a>"+
				op1 + 
				op2 +
				op3 +
				//"<a class='del_btn'  onclick=del("+this.id+")>删除</a>"+
				"</td></s:if>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"specialShowListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
</script>