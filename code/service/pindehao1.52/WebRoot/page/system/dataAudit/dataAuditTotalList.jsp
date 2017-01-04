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
function doAuditAverage(obj){  
	if(obj.name == "auditAverage"){
		dataAuditAverageCount("");
	}else if(obj.name == "auditAverageTrue"){
		dataAuditAverageCount(1);
	}
	else if(obj.name == "auditAverageFalse"){
		dataAuditAverageCount(2);
	}
     
} 

function dataAuditAverageCount(val) {
    $.ajax(
    {
        type: "get",
        url: "dataAuditAverageCount.do?dataAuditPojo.operationType="+val,
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

</script>
</head>
<body>

<div class="sub_wrap">
<div class="s_nav"><a href="#">视频管理</a> &gt; <a href="dataAuditTotalList.do">审核数据总计</a></div>
<div class="Search_control" id="tip">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
<!-- 查询开始 -->
		<form action="dataAuditPeopleListAll.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
				    <tr>
						<td align="right">日期：</td>
						<td><input name="dataAuditPojo.operationDataStartStr" id="startdate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear:true })"/>
						 -- <input name="dataAuditPojo.operationDataEndStr" id="enddate" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', isShowToday: false, isShowClear:true })"/></td>
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
	 <label for="male" name="dataTotal">审核总量：${auditDateMap['auditTotal']}</label>
	 <label for="male" name="dataTotalTrue" style="margin-left:25px;">审核通过总量：${auditDateMap['auditTotalTrue']}</label>
	 <label for="male" name="dataTotalFalse" style="margin-left:25px;">审核未通过总量：${auditDateMap['auditTotalFalse']}</label>
  </div>
  <div style="width:100%;height:20px;margin-bottom:15px;">
  	<input type="button" name="auditAverage" id="auditAverage" value=" 计算日均量 "  onclick="doAuditAverage(this)"/>
  	<input type="text" name="" id="auditAverageInput" value=""  style="margin-right:25px;">
  	<input type="button" name="auditAverageTrue" id="auditAverageTrue" value=" 计算日均通过 "  onclick="doAuditAverage(this)"/>
  	<input type="text" name="" id="auditAverageTrueInput" value=""  style="margin-right:25px;">
  	<input type="button" name="auditAverageFalse" id="auditAverageFalse" value=" 计算日均不通过 "  onclick="doAuditAverage(this)"/>
  	<input type="text" name="" id="auditAverageFalseInput" value="">
  </div>
  
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
		<th>日审人数 </th>
		<th>日总量</th>
		<th>日总通过</th>
		<th>日总不通过</th>
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
		queryData("dataAuditTotalListCount.do?" , "dataAuditTotalListAll.do?randquery="+rand);	
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td>"+ this.operationPeopleNo + "</td>"+
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
				"dataAuditTotalListAll.do?randIni="+rand);
		$("#query_btn").click(query);				
	});	
	
	
	
</script>