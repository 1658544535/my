<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerMartshowItems-9b61bc2a35m.css" type="text/css" media="all" />
<script type="text/javascript">

</script>	
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">平台运营</a> &gt; <a href="goFreeGroupActivityList.do">团免活动</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr>
						<td align="right">活动时间</td>
						<td><input name="grouponActivityPojo.beginTime" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>		
						<td><input name="grouponActivityPojo.endTime" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/></td>		
					</tr>
					<tr>
						<td align="right">筛选状态</td>
						<td align="right">
							<select name="grouponActivityPojo.activityStatus" id="grouponActivityPojo.activityStatus"  class="floatLeft">
								<option value="">----请选择----</option>
								<option value="0">未开始</option>
								<option value="1">活动中</option>
								<option value="2">活动结束</option>
								<!-- <option value="3">已开奖</option> -->
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
		<!-- 查询结束 -->
<div class="h15"></div>
<div>
<a class="Add_btn" href="goAddFreeGroupActivity.do">新增活动</a>
<a class="Add_btn" onclick="checkAll('checkAllFreeGroupActivity.do')">批量审核</a>
<a class="Add_btn" onclick="uncheckAll('uncheckAllFreeGroupActivity.do')">批量取消审核</a>
<a class="delAll_btn" onclick="deleteAll('delFreeGroupActivityByIds.do')">批量删除</a>
<form action="#" id="idform"  method="post" >
<table width="100%" border="0" class="Info_list_table">
<tr>    
    <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
	<th>活动编号</th>
	<th>产品图</th>
	<th>产品名称</th>
	<th>活动时间</th>
	<th>拼团数/价格</th>
	<!-- <th>允许每人参与数</th> -->
	<th>目前参与数</th>
	<th>活动状态</th>
	<th>审核状态</th>
	<th>操作</th>
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
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("freeGroupActivityCount.do", "freeGroupActivityList.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var statusStr="";
		var activityStatusStr="";
		var publish="";
		if(this.status == 1){
			statusStr = "已审核";
		} else if(this.status == 0){
			statusStr = "未审核";
		}
		
		if(this.activityStatus == 0){
			activityStatusStr = "未开始";
		} else if (this.activityStatus == 1) {
			activityStatusStr = "活动中";
		} else if(this.activityStatus == 2){
			activityStatusStr = "活动结束";
		/* } else if(this.activityStatus == 3){
			activityStatusStr = "已开奖"; */
		}
		
		/* if(this.activityStatus == 2){
			publish = "<a class='edit_btn' onclick=check("+this.id+")>开奖</a>";
		} */
		
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				"<td>"+ this.id + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+this.productImage+"'  height='100px'></td>"+				
				"<td>"+ this.productName + "</td>"+
				"<td>"+ this.beginTimeStr + "至"+this.endTimeStr+"</td>"+
				"<td>"+ this.num + "/"+this.price+"</td>"+
				"<td>"+ this.numNow+"<br><a href='goFreeGroupGroRec.do?grouponUserRecordPojo.id="+this.id+"'>查看</a></td>"+
				"<td>"+ activityStatusStr +"</td>"+
				"<td>"+ statusStr +"</td>"+
				"<td><a class='edit_btn' href='goUpdateFreeGroupActivity.do?grouponActivityPojo.id="+this.id +"'>编辑</a>"+
				"<a class='del_btn'  onclick=del(\"delFreeGroupActivityById.do?id="+this.id+"\")>删除</a>"+
				""+publish+"</td>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"freeGroupActivityList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>
</body>
</html>