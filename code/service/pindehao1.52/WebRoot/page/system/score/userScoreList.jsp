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
		document.getElementById("idform").submit();
	}


	function del(val)
	{
		if(confirm("你真的想删除该记录么？"))
		{
			var url = "deletecouponById.do?couponPojo.couponId="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
	

	</script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">积分管理</a> &gt; <a href="#">用户积分列表</a></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="userScoreList.do" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">昵称：</td>
						<td><label><input type="text" name="userScorePojo.name"
								id="userScorePojo.name"
								value=""></label></td>
			
						<td align="right">摇一摇剩余次数：</td>
						<td><select name="userScorePojo.shakeNum" id="userScorePojo.shakeNum"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">0次</option>
							<option value="1">1次</option>
							<option value="2">2次</option>
							<option value="3">3次</option>
				    		</select></td>
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
		<th>用户名称</th>
		<th>总积分</th>
		<!-- <th>摇一摇时间</th> -->
		<th>摇一摇剩余次数</th>
		<th>绑定微信</th>
		<th>上传头像</th>
		<th>完善个人资料</th>
		<th>更新时间</th>
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
		queryData("userScoreListCount.do", "userScoreListAll.do?randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.name + "</td>"+
				"<td>"+ this.score + "</td>"+
			<!--	"<td>"+ this.shakeDateStr + "</td>"+  -->
				"<td>"+ this.shakeNum + "</td>"+
				"<td>"+ this.bundingName + "</td>"+
				"<td>"+ this.uploadName + "</td>"+
				"<td>"+ this.improveName + "</td>"+
				"<td>"+ this.updateDateStr + "</td>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userScoreListAll.do?randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>