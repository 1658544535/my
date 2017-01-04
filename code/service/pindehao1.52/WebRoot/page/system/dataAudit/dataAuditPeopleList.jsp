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

//获取平均值
function doAuditAverages(obj){  
	var uid = document.getElementById("operationUserId").value;
	if(uid == null || uid == '' || uid == 0){
		alert("请选择一个审核人员");
		return false;
	}
	console.log(uid);
	if(obj.name == "auditAverage"){
		dataAuditAverageCount("",uid);
	}else if(obj.name == "auditAverageTrue"){
		dataAuditAverageCount(1,uid);
	}
	else if(obj.name == "auditAverageFalse"){
		dataAuditAverageCount(2,uid);
	}
     
} 
//平均值的调用方法
function dataAuditAverageCount(val,uid) {
    $.ajax(
    {
        type: "get",
        url: "dataAuditAverageCount.do?dataAuditPojo.operationType="+val+"&dataAuditPojo.operationUserId="+uid,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
        	for (var i = 0; i < o_msg.length; i++) {
        		if(val == 1){
        			document.getElementById("auditAverageTrueInput").value=o_msg[i].dataAuditCount;
        		}else if(val == 2){
        			document.getElementById("auditAverageFalseInput").value=o_msg[i].dataAuditCount;
        		}else{
        			document.getElementById("auditAverageInput").value=o_msg[i].dataAuditCount;
        		}
        	
			}
        	
        }
    })
};
//获取审核总量
function getAudit(obj){
	console.log(obj.value);
	doAuditAverage(obj.value);

}
//审核总量调用方法
function doAuditAverage(val) {
	if(val == 0){
		document.getElementById("dataTotal").innerText = "审核总量：***";
        document.getElementById("dataTotalTrue").innerText = "审核通过总量：***";
        document.getElementById("dataTotalFalse").innerText = "审核未通过总量：***";
        document.getElementById("auditAverageTrueInput").value='';
	    document.getElementById("auditAverageFalseInput").value='';
	    document.getElementById("auditAverageInput").value='';
        return false;
	}
    $.ajax(
    {
        type: "get",
        url: "getAuditAverageCount.do?dataAuditPojo.operationUserId="+val,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	console.log(msg.auditTotal);
        	var o_msg = eval(msg);
        	for (var i = 0; i < o_msg.length; i++) {
        		console.log(o_msg[i].auditTotal);
        		document.getElementById("dataTotal").innerText = "审核总量："+o_msg[i].auditTotal;
        		document.getElementById("dataTotalTrue").innerText = "审核通过总量："+o_msg[i].auditTotalFalse;
        		document.getElementById("dataTotalFalse").innerText = "审核未通过总量："+o_msg[i].auditTotalTrue;
	        	document.getElementById("auditAverageTrueInput").value='';
	        	document.getElementById("auditAverageFalseInput").value='';
	        	document.getElementById("auditAverageInput").value='';
			}
        	
        }
    })
};

</script>
</head>
<body>

<div class="sub_wrap">
<div class="s_nav"><a href="#">视频管理</a> &gt; <a href="dataAuditPeopleList.do">个人审核数据统计</a></div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="dataAuditTotalList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				    <tr>
						<td align="right">日期：</td>
						<td><input name="dataAuditPojo.operationDataStartStr" id="startdate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear:true })"/>
						 -- <input name="dataAuditPojo.operationDataEndStr" id="enddate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear:true })"/></td>
						<td align="right">审核人：</td>
						<td>
							<select id="optionType" name="dataAuditPojo.operationUserId" class="floatLeft">
								<option value="">----请选择----</option>
									<c:forEach items="${dataAuditPojoList}" var="dataAuditPojoList">
										<option value="${dataAuditPojoList.id}">${dataAuditPojoList.operationUserName}</option>
									</c:forEach>
							</select>
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
  <div style="width:100%;height:20px;margin-bottom:15px;">
  <label for="male" name="check">审核人：</label>
  	<select id="operationUserId" name="operationUserId" onchange="getAudit(this)"  style="margin-right:15px;">
		<option value="0">----请选择----</option>
			<c:forEach items="${dataAuditPojoList}" var="dataAuditPojoList">
				<option value="${dataAuditPojoList.id}">${dataAuditPojoList.operationUserName}</option>
			</c:forEach>
	</select>
	 <label for="male" id="dataTotal" name="dataTotal">审核总量：***</label>
	 <label for="male" id="dataTotalTrue" name="dataTotalTrue" style="margin-left:25px;">审核通过总量：***</label>
	 <label for="male" id="dataTotalFalse" name="dataTotalFalse" style="margin-left:25px;">审核未通过总量：***</label>
  </div>
  <div style="width:100%;height:20px;margin-bottom:15px;">
  	<input type="button" name="auditAverage" id="auditAverage" value=" 计算日均量 "  onclick="doAuditAverages(this)"/>
  	<input type="text" name="" id="auditAverageInput" value=""  style="margin-right:25px;">
  	<input type="button" name="auditAverageTrue" id="auditAverageTrue" value=" 计算日均通过 "  onclick="doAuditAverages(this)"/>
  	<input type="text" name="" id="auditAverageTrueInput" value=""  style="margin-right:25px;">
  	<input type="button" name="auditAverageFalse" id="auditAverageFalse" value=" 计算日均不通过 "  onclick="doAuditAverages(this)"/>
  	<input type="text" name="" id="auditAverageFalseInput" value="">
  </div>
  
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>审核人 </th>
		<th>日审核量</th>
		<th>日审核通过</th>
		<th>日审核不通过</th>
		<th>日期</th>
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
		queryData("dataAuditPeopleListCount.do?" , "dataAuditPeopleListAll.do?randquery="+rand);	
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.operationUserName + "</td>"+
				"<td>"+ this.operationTotalByDay + "</td>"+
				"<td>"+ this.operationTypeTrueByDay + "</td>"+
				"<td>"+ this.operationTypeFalseByDay + "</td>"+
				"<td>"+ this.simpleData + "</td>"+
				+"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"dataAuditPeopleListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
	
	
	
</script>