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
<script>
/**
$(function(){
	$("#end").click(function() {
		query();

	});
});
**/

	

</script>
</head>
<body>

<div class="sub_wrap">
<div class="s_nav"><a href="#">视频管理</a> &gt; <a href="dataAuditList.do">视频审核结果</a></div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="dataAuditList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
	                	<td align="right">标题：</td>
						<td>
							<input type="text" name="dataAuditPojo.videoTitle" value="" class="floatLeft" >
				    	</td>
	                 	<td align="right">审核人：</td>
						<td>
							<input type="text" name="dataAuditPojo.operationUserName" value="" class="floatLeft" >
				    	</td>
				    	<td align="right">视频链接：</td>
				    	<td>
							<input type="text" name="dataAuditPojo.url" value="" class="floatLeft" >
				    	</td>
				    </tr>
				    <tr>
						<td align="right">审核结果：</td>
						
				    	<td><select name="dataAuditPojo.operationType" id="dataAuditPojo.operationType">
							<option value="">----请选择----</option>
							<option value="1">通过</option>
							<option value="2">不通过</option>
							<option value="3">待编辑</option>
							<option value="del">删除</option>
				    		</select>
				    	</td>
				    	<td align="right">日期：</td>
				    	<td width="80px"  style="padding: 0px 0px;">从<input style="min-width:60px;" name="dataAuditPojo.operationDataStartStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td>到<input style="min-width:60px;" name="dataAuditPojo.operationDataEndStr" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true }); "/></td>
					    <td align="right"></td>
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
		<th>标题 </th>
		<th>链接 </th>
		<th>审核结果</th>
		<th>审核人</th>
		<th>操作时间</th>
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
	var id = $("input[name='platformSpecialPojo.id']").val();
	var r = /^[1-9][0-9]*$/;
		var rand=Math.random() * ( 100000 + 1);
		queryData("dataAuditListCount.do?" , "dataAuditListAll.do?randquery="+rand);

		
	
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var op = "";
		if(this.isDelete != null && this.isDelete != "" && this.operationType == ''){
			type = this.isDelete;
		}else{
			type = this.operationType;
		}
		$("#body").append(
				"<tr>"+
				"<td>"+ this.videoTitle + "</td>"+
				"<td>"+ this.url + "</td>"+
				"<td>"+ type + "</td>"+
				"<td>"+ this.operationUserName + "</td>"+
				"<td>"+ this.operationDataStr + "</td>"+
				+"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"dataAuditListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
	
	
	
</script>