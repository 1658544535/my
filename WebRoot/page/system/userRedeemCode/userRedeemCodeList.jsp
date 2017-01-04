<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">用户兑换码管理</a> &gt; <a href="#">用户兑换码管理</a></div>
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
						<td><select name="status" id="status"  class="floatLeft">
							<option value="">全部</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
							</select></td>
						<td align="right">使用状态：</td>
						<td><select name="used" id="used"  class="floatLeft">
							<option value="">全部</option>
							<option value="0">未使用</option>
							<option value="1">已使用</option>
							</select></td>
					</tr>
					<tr>
					    <td align="right">优惠券码：</td>
						<td><label><input type="text" name="keywords" id="keywords" value=""></label></td>
						<td align="right">生成时间：</td>
						<td>
								<input id="g" name="genTime" class="Wdate" type="text" 
								onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
								</td>
						<td align="right">使用时间：</td>
						<td>
								<input id="u" name="useTime" class="Wdate" type="text" 
								onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
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
			<a class="Add_btn" href="goAddUserRedeemCode.do" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkUserRedeemCodeAll.do')" >批量审核</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckUserRedeemCodeAll.do')" >批量取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delUserRedeemCodeAll.do')" >批量删除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>用户名称</th>
						<th>兑换码</th>
						<th>金额</th>
						<th>状态</th>
						<th>使用状态</th>
						<th>有效开始时间</th>
						<th>有效结束时间</th>
						<th>生成时间</th>
						<th>使用时间</th>
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
			queryData("userRedeemCodeCnt.do", "userRedeemCodeList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
		var statusStr = "",usedStr = "";
		if (this.status == 0) {
			statusStr = "未审核";
		} else if (this.status == 1) {
			statusStr = "已审核";
		}
		
		if (this.used == 0) {
			usedStr = "未使用";
		} else if (this.used == 1) {
			usedStr = "已使用";
		}
		if(this.used == 0){
			var inner = "<td><a class='edit_btn' onclick='check(\"checkUserRedeemCode.do?code="+this.code +"\")'>审核通过</a>"+
				"<a class='edit_btn' onclick='uncheck(\"uncheckUserRedeemCode.do?code="+this.code +"\")'>取消审核</a>"+
				"<a class='edit_btn' href='goEditUserRedeemCode.do?code="+this.code +"'>编辑</a>"+
				"<a class='del_btn' onclick='del(\"delUserRedeemCode.do?code="+this.code+"\")'>删除</a></td>";
		} else {
			var inner = "<td></td>";
		}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.code +" /></td>"+
				"<td>"+ this.loginname + "</td>"+
				"<td>"+ this.code + "</td>"+
				"<td>"+ this.price + "</td>"+
				"<td>"+ statusStr + "</td>"+
				"<td>"+ usedStr + "</td>"+
				"<td>"+ this.validStimeStr + "</td>"+
				"<td>"+ this.validEtimeStr + "</td>"+
				"<td>"+ this.genTimeStr + "</td>"+
				"<td>"+ this.useTimeStr + "</td><s:if test="#session.role.roleId!=7">"+ inner +"</s:if>");
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "userRedeemCodeList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
