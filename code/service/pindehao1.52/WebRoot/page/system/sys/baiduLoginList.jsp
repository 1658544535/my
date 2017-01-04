<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
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
</script>	
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">用户管理</a> &gt; <a href="#">百度用户浏览记录</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="baiduLoginList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">登陆时间区间：</td><td width="80px"  style="padding: 0px 0px;">从<input style="min-width:60px;" name="baiduLoginPojo.loginTimeStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td>到<input style="min-width:60px;" name="baiduLoginPojo.loginTimeEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
						<td align="right">来源：</td>
						<td><label><select class="" name="baiduLoginPojo.type" id="baiduLoginPojotype">
													<option value="">----全部----</option>
													<option value=1>注册</option>
													<option value=2>登录</option>
											</select></label></td>
					</tr>   
					<tr> 
					    <td align="right">登陆昵称：</td>
						<td><label><input type="text" name="baiduLoginPojo.loginName"
								value=""></label></td><td></td>
						<td align="right">百度ID：</td>
						<td><label><input type="text" name="baiduLoginPojo.baiduId"
								value=""></label></td>
					</tr>					    
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					   <input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				       <div class="Clear"></div>
			    </div>
		</form>
		<!-- 查询结束 -->
<div class="h15"></div>
<div>
<form action="deletecouponAllById.do" id="idform"  method="post" >
<table width="100%" border="0" class="Info_list_table">
<tr>    <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>用户ID</th>
		<th>登陆昵称</th>
		<th>登陆时间</th>
		<th>百度ID</th>
		<!-- <th>更新时间</th> -->
		<th>来源</th>
</tr>
<tbody id="body"></tbody>      
</table>
</form>
<div class="page">
		<div class="floatleft">
		总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
		</div>
		<div style="float: right" id="Pagination" class="pagination"></div>
		<div class="Clear"></div>
	    </div>
        </div>
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("baiduLoginListCount.do", "baiduLoginListAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.id + "</td>"+
				"<td>"+ this.loginName + "</td>"+
				"<td>"+ this.loginTimeStr + "</td>"+
				"<td>"+ this.baiduId + "</td>"+
/* 				"<td>"+ this.updateDateStr +"</td>"+  */
		        "<td>"+ this.typeName + "</td></tr>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"baiduLoginListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>