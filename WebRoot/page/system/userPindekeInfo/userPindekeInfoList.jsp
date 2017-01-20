<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script>


function check2(url){
	if(confirm("确认通过？确认后信息不可更改")){
		MAOWU.ajax.get(url, null, checkRefreshPage2);
	}else{
		return ;
	}
}
function checkRefreshPage2(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("审核成功");
		query();
	} else{
		alert("审核失败");
	}
}

function check3(url){
		if(confirm("确认要审核不通过吗？")){
			var msg = prompt("请输入审核不通过原因！~", "");
	        if(msg != null && msg.trim() != ""){
	        	url += "&returnMsg="+msg;
				MAOWU.ajax.get(url, null, checkRefreshPage3);
	        }else{
	        	alert("请输入审核不通过原因！~");
	        }
		}else{
			return ;
		}
	}
function checkRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核不通过成功");
			query();
		} else{
			alert("审核不通过失败");
		}
}


function frozen(url){
	if(confirm("确认冻结？确认后信息不可更改")){
		MAOWU.ajax.get(url, null, frozenRefreshPage);
	}else{
		return ;
	}
}
function frozenRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("冻结成功");
		query();
	} else{
		alert("冻结失败");
	}
}


</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">用户信息表管理</a> &gt; <a href="#">拼得客用户信息列表</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">审核状态：</td>
						<td>
							<select name="userPindekeInfoPojo.status" id=""  class="floatLeft">
								<option value="">全部</option>
								<option value="0">待审核</option>
								<option value="1">审核通过</option>
								<option value="2">审核不通过</option>
								<option value="3">冻结</option>
				    		</select>
						</td>
						<td align="right">用户账号或手机号码：</td>
						<td>
							<input name="userPindekeInfoPojo.name" id="" type="text" placeholder="" class="floatLeft" />
						</td>
						<td align="right">申请时间：</td>
						<td>
							<input id="s" name="userPindekeInfoPojo.pindekeDateStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'e\')}'})"/>
							<input id="e" name="userPindekeInfoPojo.pindekeDateEndStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'s\')}'})"/>
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
		<form action="#" id="idform" method="post" >
		<a class="Add_btn" href="goAddUserPindekeInfo.do" >新增</a>
			<%--<a class="Add_btn" onclick="checkAll('checkUserPindekeInfoAll.do')" >审核选中</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckUserPindekeInfoAll.do')" >选中取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delUserPindekeInfoAll.do?isDelete=1')" >选中删除</a>--%>
			
			
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>真实姓名</th>
						<th>拼得客编号</th>
						<th>用户账号</th>
						<th>手机号码</th>
						<th>申请人身份证号码</th>
						<th>邀请者</th>
						<th>申请时间</th>
						<th>审核状态</th>
						<th>审核原因</th>
						<th>开团数</th>
						<th>成团数</th>
						<th>冻结金额</th>
						<th>返佣金额</th>
						<th>提现金额</th>
						<th>剩余金额</th>
						<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
	</div>
</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}"; 
	/**
	 ** 条件查询函数 
	 **/
	function query() {
		if(tt.validate()){
			var rand=Math.random() * (100001);
			queryData("userPindekeInfoCnt.do", "userPindekeInfoList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var statusStr="";
		var statusStr2="";
				if(this.status==0){
				statusStr  = "待审核";
				statusStr2 = "<a class='edit_btn' onclick='check2(\"checkUserPindekeInfo.do?id="+this.id +"&userId="+this.userId+"\")'>审核通过</a><a class='edit_btn' onclick='check3(\"goUserPindekeInfoReturnMsg.do?id="+this.id +"&status=2&userId="+this.userId+"\")'>审核不通过</a>";
				}else if(this.status==1){
				statusStr  = "审核通过";
				statusStr2 = "<a class='del_btn' onclick='frozen(\"frozenUserPindekeInfo.do?id="+this.id+"\")'>冻结</a>";
				}else if(this.status==2){
				statusStr  = "审核不通过";
				}else if(this.status==3){
				statusStr  = "冻结";
				}
			var returnMsgStr="";
				if(this.returnMsg == ""){
				this.returnMsg  = "--";
				}
		
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.name + "</td>"+
				"<td><input type='text' name='pindekeNumber' value='"+ this.pindekeNumber + "' onblur='update(this.value, "+ this.id +");''></td>"+
				"<td>"+ this.loginname + "</td>"+
				"<td>"+ this.phone + "</td>"+
				"<td>"+ this.cardNo + "</td>"+
				"<td>"+ this.inviterName + "</td>"+
				"<td>"+ this.creatDateString + "</td>"+
				"<td>"+ statusStr + "</td>"+
				"<td>"+ this.returnMsg + "</td>"+
				"<td>"+ this.grouponNum + "</td>"+
				"<td>"+ this.grouponSuccessNum + "</td>"+
				"<td>"+ this.freezingPrice + "</td>"+
				"<td>"+ this.rebatePrice + "</td>"+
				"<td>"+ this.withdrawPrice + "</td>"+
				"<td>"+ this.surpluPrice + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td>"+
				"<a class='edit_btn' href='goCheckUserPindekeInfo.do?id="+this.id +"'>查看</a>"+
				//"<a class='edit_btn' href='goUpdateUserPindekeInfo.do?id="+this.id +"'>编辑</a>"+
				statusStr2+
				"<!--<a class='del_btn' onclick='del(\"delUserPindekeInfo.do?id="+this.id+"&isDelete=1\")'>删除</a>-->"+
				"</td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "userPindekeInfoList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	
    /**
	 **  修改拼得客编号
	 **/
	function update(pindekeNumber,id)
	{
//		if(pindekeNumber != null && pindekeNumber != "")
//		{
			var url = "updatePindekeNumber.do?userPindekeInfoPojo.id="+id+"&userPindekeInfoPojo.pindekeNumber="+pindekeNumber;
			doOpreator(url,null);
//		}else{
//			alert("请输入编号");
//		}

	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			queryData("userPindekeInfoCnt.do", "userPindekeInfoList.do?randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
</script>