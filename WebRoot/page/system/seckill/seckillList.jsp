<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="#">平台运营</a> &gt; <a href="#">掌上秒杀管理</a></div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					 <tr>
					<td align="right">活动日期：</td>
					<td><input name="seckillPojo.beginTime" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true })"/>-
					<input name="seckillPojo.endTime" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', isShowToday: false, isShowClear: true,minDate:'#F{$dp.$D(\'beganday\')}' })"/></td> 
	                <td>审核状态：
	                <select name="seckillPojo.status" id="seckillPojo.status">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
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
			<a class="Add_btn" href="goAddSeckill.do" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkSeckillAll.do')" >批量审核</a>
			<a class="delAll_btn" onclick="deleteAll('delSeckillAll.do')" >批量删除</a>
			<a class="submit_btn" href="#" onclick="query_btn2(0)">显示今天</a>
			<a class="submit_btn" href="#" onclick="query_btn2(1)">显示明天</a>
			<a class="submit_btn" href="#" onclick="query_btn2(2)">显示后天</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>活动ID</th>
						<th>活动时间</th>
						<th>添加时间</th>
						<th>状态</th>
						<th>排序</th>
						<th>参团人数</th>
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
			queryData("seckillCnt.do", "seckillList.do?randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
	    var statusStr = "";
		if(this.status == 0){
			statusStr = "<br><a onclick='check(\"checkSeckill.do?id="+this.id +"\")'>【设置已审核】</a>";
		}else{
			statusStr = "<br><a onclick='uncheck(\"uncheckSeckill.do?id="+this.id +"\")'>【设置未审核】</a>";
		}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.id + "</td>"+
				//"<td>"+ this.beginTimeStr + "至"+this.endTimeStr+"</td>"+
				"<td>" + 
				(this.beginTimeStr).substring(0,10) + 
				"<br/>" + 
				(this.beginTimeStr).substring(10,16) + 
				" 至 " + 
				(this.endTimeStr).substring(10,16) + 
				"</td>" + 
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.statusName + statusStr+"</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td>"+ this.numNow + "</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='edit_btn' href='goSeckillGoods.do?seckillGoodsPojo.seckillId="+this.id +"'>新增商品</a>"+
				"<a class='edit_btn' href='goEditSeckill.do?id="+this.id +"'>编辑</a>"+ 
				"<a class='del_btn' onclick='del(\"delSeckill.do?id="+this.id+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "seckillList.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	
	function query_btn2(t){
		var nowDate = new Date();
		//var nYear = nowDate.getFullYear();
		//var nMonth = nowDate.getMonth() + 1;
		//var nDay = nowDate.getDate();
		//var time = nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
		
		var beginDateTime = nowDate.getTime() + (86400000 * t);
		var beginDate = new Date(beginDateTime);
		var bYear = beginDate.getFullYear();
		var bMonth = beginDate.getMonth() + 1;
		var bDay = beginDate.getDate();
		
		var startTime = bYear + '-' + bMonth + '-' + bDay + " 00:00:00";
		var endTime = bYear + '-' + bMonth + '-' + bDay + " 23:59:59";
        
		$("input[name='seckillPojo.beginTime']").val(startTime);
		$("input[name='seckillPojo.endTime']").val(endTime);
		$("#query_btn").trigger('click');
		$("input[name='seckillPojo.beginTime']").val("");
		$("input[name='seckillPojo.endTime']").val("");
	}
</script>
