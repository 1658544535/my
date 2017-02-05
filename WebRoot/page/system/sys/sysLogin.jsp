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

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val)
	{
		//alert(val);
		if(confirm("确认审核吗？"))
		{
			//alert(val);
			
			var url = "deleSysLogin.do?sysLogin.id="+val;	
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
			alert("审核成功");
			queryData("getSysLoginCount.do?os=${os}&sysLogin.form=<s:property value='sysLogin.form'/>", "sysLoginAllList.do?os=${os}&sysLogin.form=<s:property value='sysLogin.form'/>&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
	function pawd(val)
	{
		//alert(val);
		if(confirm("确认重置密码么？"))
		{
			//alert(val);
			
			var url = "updatePasswordlogin.do?sysLogin.id="+val;	
			doOpreator1(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator1(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage1);
	}
	 
	function goRefreshPage1(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("重置成功!新密码：123456");
			queryData("getSysLoginCount.do?os=${os}&sysLogin.form=<s:property value='sysLogin.form'/>", "sysLoginAllList.do?os=${os}&sysLogin.form=<s:property value='sysLogin.form'/>&randdelete="+rand);
		}else{
			alert("重置失败");
		}
	}
	function update(id)
	{
		var idt = 'code'+id;
		var d=document.getElementById(idt);
		if(d != null){
		d =d.value;  
		 $.ajax({
             type: "get",
             url: "updateRemarks.do?sysLogin.id="+id+"&sysLogin.remarks="+d,
             success: function(data){
             	alert("成功");
             }
             });	
		}
            
	}
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">系统管理</a> &gt; <c:if test="${os==0}"><a href="#">管理员管理</a></c:if> <c:if test="${os==1}"><a href="#">普通用户管理</a></c:if>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="sysLogin.do?os=${os}" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">昵称：</td>
						<td><label><input type="text" name="sysLogin.name"
								id="ticketRulePojo.ticketName"
								value="<s:property value="sysLogin.name"/>"></label></td>
						<td align="right">账号：</td>
						<td><label><input type="text" name="sysLogin.loginname"
								id="ticketRulePojo.ticketName"
								value="<s:property value="sysLogin.loginname"/>"></label></td>
						<td align="right" >注册时间区间：</td><td>从<input style="min-width:60px;" name="sysLogin.createDateStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true }); "/>到<input style="min-width:60px;" name="sysLogin.createDateEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm', isShowToday: false, isShowClear: true }); "/></td>
						<%-- <td align="right">注册渠道：</td>
						<td><label><input type="text" name="sysLogin.regChannel"
								id="ticketRulePojo.regChannel"
								value="<s:property value="sysLogin.regChannel"/>"></label></td> --%>
					</tr>
					
                    <tr>
                        <td align="right">用户来源：</td>
						<td>
							<select name="sysLogin.judgeSource" id="judgeSource"
								class="floatLeft">
								    <option value="">---请选择---</option>
									<option value="1">APP</option>
									<option value="2">微信</option>
							</select>
						</td>
						<td align="right">用户id：</td>
						<td><label><input type="text" name="sysLogin.id"
								id="ticketRulePojo.ticketName"
								value="<s:property value="sysLogin.id"/>"></label></td>
					</tr>
					<%-- 
						<td align="right" >注册时间区间：</td><td width="80px"  style="padding: 0px 0px;">从<input style="min-width:60px;" name="sysLogin.createDateStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true }); "/></td>
					    <td>到<input style="min-width:60px;" name="sysLogin.createDateEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear: true }); "/></td>
					    <td align="right"></td>
					
					 --%>
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
			<s:if test="#session.role.roleId!=7"><a class="delAll_btn" onclick="deleteAll()">批量审核</a>
			<a href="addSysLogin.do?os=${os}" class="Add_btn">新增用户</a>
			<input type="button" value="导出EXCEL"  id="excel" class="submit_btn" style="float: right;"  /><label style="color:red;float: right;">最多导出三万条！</label>
			<%-- <a href="genExternalCode.do?os=${os}" class="Add_btn">生成推荐标识码</a>
			<a href="genInvitationCode.do?os=${os}" class="Add_btn">生成分享邀请码</a> --%></s:if>
			<form action="SysLoginDeleteId.do?os=${os}" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>
						<th>用户id</th>	
						<th>帐号</th>
						<th>昵称</th>
						<th>类型</th>
						<!-- <th>推荐标识码</th>
						<th>分享邀请码</th> -->
						<th>状态</th>
						<th>用户来源</th>
						<!-- <th>来源渠道</th> -->
						<th>注册时间</th>
						<th>最后登录时间</th>
						<th>留言</th>
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
		queryData("getSysLoginCount.do?os=<s:property value='os'/>&sysLogin.form=<s:property value='sysLogin.form'/>", "sysLoginAllList.do?os=<s:property value='os'/>&sysLogin.form=<s:property value='sysLogin.form'/>&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
	    var sourceName = "";
	    if(this.source !=''){
			if(this.source == 3){
				sourceName = "微信";
			}else{
				sourceName = "APP";
			}
		 }
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>" 
				+ this.id + 
		        "</td><td>" + this.loginname + 
		        "</td><td>" + this.name+ 
		        "</td><td>" + this.typeName+
		        //"</td><td>" + this.externalSignCode+
		        //"</td><td>" + this.invitationCode+		        
		        "</td><td>" + this.statusName+
		        "</td><td>" + sourceName
		        //+ "</td><td>" + this.channel
				+ "</td><td>" + this.createDateStr2
				+ "</td><td>" + this.login_date2
				+ "</td><td><textarea rows=5 id='code"+this.id+"'>"+this.remarks+"</textarea>"
				+ "</td><s:if test="#session.role.roleId!=7"><td><a class='edit_btn' onclick='update("+this.id+")'>留言</a>"
				+"<a  class='edit_btn' onclick=del('"+this.id+"')>审核</a>"
				+ "<a class='edit_btn' href='goFindSysLogin.do?sysLogin.id="+this.id +"&os=<s:property value='os'/>"+"'>编辑</a>"
				//+ "<a class='edit_btn' href='goFindUserInfo.do?userInfo.userId="+this.id +"&os=<s:property value='os'/>"+"'>用户信息</a>"
				+ "<a class='del_btn' href='updateStatus.do?sysLogin.id="+this.id +"&sysLogin.status="+this.status+"&os=<s:property value='os'/>"+"'>冻结/解冻</a>"
				+ "<a class='edit_btn' onclick=pawd('"+this.id+"')>密码重置</a>"
				<!-- + "<a class='del_btn' href='delSysLogin.do?sysLogin.id="+this.id+"&os=<s:property value='os'/>"+"' onclick='javascript:return window.confirm(\"确定删除？(该用户账户信息将被删除)\");'>删除用户</a>"-->
				+"</td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"sysLoginAllList.do?os=<s:property value='os'/>&sysLogin.form=<s:property value='sysLogin.form'/>&randIni="+rand);
		
		$("#query_btn").click(query);
		
		/**
		  导出excel
		 **/
			$("#excel").click(function() {
			if(tt.validate()){
				var formParam = $("#sysform").serialize();
				$(location).attr('href', 'getSysLoginExcel.do?os=<s:property value='os'/>&'+formParam);
			}
		});
	});
	
</script>