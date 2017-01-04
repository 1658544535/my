<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">商家审核管理</a> &gt; <a href="#">商家审核信息列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="sellerCheckManage.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">商家ID：</td>
						<td>
							<input type="text" name="manufacturerPojo.userId" id="" value="">
						</td>
				    	<td align="right">用户名：</td>
						<td>
							<input type="text" name="manufacturerPojo.loginname" id="" value="">
						</td>
					</tr>
					<tr>
				    	<td align="right">状态：</td>
						<td><select name="manufacturerPojo.status" id=".status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
							</select>
						</td>
						<td align="right">提交日期：</td>
						<td>
							<input id="c" name="manufacturerPojo.createDateStr" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'})"/>
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
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>商家ID</th>
		<th>用户名</th>
		<th>是否审核</th>
		<th>提交日期</th>
		<th>提交时间</th>
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
		queryData("sellerCheckCount.do", "sellerCheckList.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var op = "<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='sellerUnchecking.do?manufacturerPojo.userId="+this.userId+"'>取消审核</a>"+				 
				 "</td></s:if>";
		if(this.status == 0){
			op = "<s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='sellerChecking.do?manufacturerPojo.userId="+this.userId+"'>审核</a>"+
				 "</td></s:if>";
		}
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.couponId +"   /></td>"+
				"<td>"+ this.userId + "</td>"+
				"<td>"+ this.loginname + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.createByDate + "</td>"+
				"<td>"+ this.createByTime + "</td>"+
				op);
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"sellerCheckList.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>