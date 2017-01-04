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
				if(code_Values[i].type == "checkbox") { 
					code_Values[i].checked = true; 
				} 
			} 
		 
		}else{	
			var code_Values = document.getElementsByName("tids") ; 
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox"){ 
					code_Values[i].checked = false
				} 
			} 
		}
	}
	
	function doOpreator(url, params, callBackFn) {
		MAOWU.ajax.get(url, params, callBackFn);
	}

	function deleteAll(){
		document.getElementById("idform").submit();
	}


	function del(val){
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "delUserCoupon.do?userCouponPojo.couponNo="+val;	
			doOpreator(url,null,goRefreshPage1);
		}else{
			return ;
		}
	}
	/*刷新*/
	function goRefreshPage1(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("删除成功");//浏览器阻止创建更多的的多画框对话框
			queryData("userCouponListCount.do", "userCouponListAll.do?randquery="+rand);
		} else {
			alert("删除成功");
		}
	}


	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">积分管理</a> &gt; <a href="#">积分明细列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="userScoreLogList.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						 <td align="right">昵称：</td>
						<td><input type="text" name="userScoreLogPojo.name"
								id="userScoreLogPojo.name"
								value=""></td> 
				    	<td align="right">积分来源：</td>
						<td><select name="userScoreLogPojo.tradeSource" id="userScoreLogPojo.tradeSource"  class="floatLeft">
							<option value="">全部</option>
							<option value="1">摇一摇</option>
							<option value="2">绑定微信</option>
							<option value="3">上传头像</option>
							<option value="4">完善资料</option>
							<option value="5">积分兑换</option>
							</select>
							</td>
						</tr>	
					    <tr>
						<!-- 这里做了限制只能选择今天以前的日期(包括今天) -->
						<td align="right">开始日期：</td>
						<td><input name="userScoreLogPojo.begandayStr" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/></td>
						<td align="right">结束日期：</td>
						<td><input name="userScoreLogPojo.enddayStr" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/></td>
					    <tr>
					
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
  <form action=".do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>用户名称</th>
		<th>当前积分</th>
		<th>积分变动</th>
		<th>积分来源</th>
		<th>生成时间</th>
		<th>备注</th>
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
		queryData("userScoreLogListCount.do", "userScoreLogListAll.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"    /></td>"+
				"<td>"+ this.name + "</td>"+
				"<td>"+ this.curScore + "</td>"+
				"<td>"+ this.tradeScore + "</td>"+
				"<td>"+ this.tradeSourceName + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.remark + "</td>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userScoreLogListAll.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>