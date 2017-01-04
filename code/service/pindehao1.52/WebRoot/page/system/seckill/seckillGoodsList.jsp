<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav"><a href="goSeckill.do">掌上秒杀管理</a> &gt; <a href="goEditSeckill.do?id=${seckillGoodsPojo.seckillId}">掌上秒杀编辑</a> &gt; <a href="goSeckillGoods.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}">秒杀商品表管理</a></div>    
		<!-- 查询开始 -->
		<form action="#" method="post" id="sysform">
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
		</form>
		<!--<a href="javascript:self.print();">打印该页</a>-->
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="goGrouponActivityProduct.do?seckill=1&seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}" >新增</a>
			<a class="Add_btn" onclick="checkAll('checkSeckillGoodsAll.do')" >批量审核</a>
			<a class="Add_btn" onclick="uncheckAll('uncheckSeckillGoodsAll.do')" >批量取消审核</a>
			<a class="delAll_btn" onclick="deleteAll('delSeckillGoodsAll.do')" >批量删除</a>
			<form action="#" id="idform" method="post" >
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
						<th>商品编号</th>
						<th>商品货号</th>
						<th>商品名称</th>
						<th>商品图</th>
						<th>商品销量</th>
						<th>团购价</th>
						<th>成团人数</th>
						<th>秒杀价</th>
						<th>活动数量</th>
						<th>活动剩余数量</th>
						<th>排序</th>
						<th>参与人数</th>
						<th>状态</th>
						<th>首页显示状态</th>
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
			queryData("seckillGoodsCnt.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&", "seckillGoodsList.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&randquery="+rand);
		}
	}
	
	/**
	 ** 分页展现页面函数 
	 **/
	function installPage() {
	    var statusStr = "";
		var isHomeStr = "";
		var isHomeStatus = "";
		if(this.status == 0){
			statusStr = "<br><a onclick='goodscheck("+this.id+", "+ this.activityId +")'>【设置已审核】</a>"; 
		}else{
			statusStr = "<br><a onclick='goodsUncheck("+this.id+", "+ this.activityId +")'>【设置未审核】</a>";
		}
		if(this.isHome == 0){
			isHomeStr = "首页不显示";
		} else {
			isHomeStr = "首页显示";
		}
		if(this.isHome == 0){
			isHomeStatus = "<br><a onclick='topHome("+this.id+","+this.activityId+",1)'>【首页显示】</a>"; 
		}else{
			isHomeStatus = "<br><a onclick='topHome("+this.id+","+this.activityId+",0)'>【首页不显示】</a>";
		}
		$("#body").append(
				"<tr><td><input name='tids' type='checkbox' value="+this.id +" /></td>"+
				"<td>"+ this.productNo + "</td>"+
				"<td>"+ this.productNum + "</td>"+
				"<td>"+ this.productName + "</td>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/"+ this.image + "' height='50px' /></td>"+
				"<td>"+ this.sellNumber + "</td>"+
				"<td>"+ this.price + "</td>"+
				"<td><input type='text' name='num' value='"+ this.num +"' onkeyup=\"this.value=this.value.replace(\/[^\\d.]\/g,'')\""+ " onblur='updateNum(this.value, "+ this.activityId +");''></td>"+  
				"<td><input type='text' name='price' value='"+ this.price + "' onblur='updatePrice(this.value, "+ this.activityId +");''></td>"+ 
				"<td><input type='text' name='limitNum' value='"+ this.limitNum +"' onkeyup=\"this.value=this.value.replace(\/[^\\d.]\/g,'')\"" + " onblur='updateLimitNum(this.value, "+ this.activityId +");''></td>"+
				"<td><input type='text' name='surplusNum' value='"+ this.surplusNum +"' onkeyup=\"this.value=this.value.replace(\/[^\\d.]\/g,'')\"" + " onblur='updateSurplusNum(this.value, "+ this.activityId +");''></td>"+
				"<td><input type='text' name='sorting' value='"+ this.sorting +"' onkeyup=\"this.value=this.value.replace(\/[^\\d.]\/g,'')\"" + " onblur='updateSorting(this.value, "+ this.id +","+ this.activityId +");''></td>"+
				"<td>"+ this.numNow + "<br><a href='goSeckillGroRec.do?grouponUserRecordPojo.id="+this.activityId+"'>查看</a></td>"+
				"<td>"+ this.statusName + statusStr+"</td>"+
				"<td>"+ isHomeStr + isHomeStatus+"</td>"+
				<s:if test="#session.role.roleId!=7">
				"<td><a class='del_btn' onclick='del(\"delSeckillGoods.do?id="+this.id+"&activityId="+this.activityId+"\")'>删除</a></td>"
				</s:if>
				);
	}
	/**
	 **  首次要初始化分页
	 **/
	$(function() {
		var rand=Math.random() * (100001);
		MAOWU.page.init(${page.rowCount}, "seckillGoodsList.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&randIni="+rand);	
		$("#query_btn").click(query);		
	});	
	/**
	 **  审核
	 **/
	function goodscheck(id,activityId)
	{
	  if(confirm("确认要通过审核吗？")){
			var url = "checkSeckillGoods.do?id="+id+"&activityId="+activityId;	
			doOpreator1(url,null);
		}else{
			return ;
		}
	  }
	function doOpreator1(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage1);
	}

	function goRefreshPage1(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			queryData("seckillGoodsCnt.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}", "seckillGoodsList.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
		/**
	 **  审核
	 **/
	function goodsUncheck(id,activityId)
	{
	  if(confirm("确认要取消审核吗？")){
			var url = "uncheckSeckillGoods.do?id="+id+"&activityId="+activityId;	
			doOpreator2(url,null);
		}else{
			return ;
		}
	  }
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}

	function goRefreshPage2(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			queryData("seckillGoodsCnt.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}", "seckillGoodsList.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
	/**
	 **  修改成团人数
	 **/
	function updateNum(num,activityId)
	{
		if(num != null && num != "")
		{
			var url = "updateSeckillActivity.do?grouponActivityPojo.id="+activityId+"&grouponActivityPojo.num="+num;
			doOpreator(url,null);
		}else{
			alert("请输入成团人数");
		}

	}
	/**
	 **  修改秒杀价
	 **/
	function updatePrice(price,activityId)
	{
		if(price != null && price != "")
		{
			var url = "updateSeckillActivity.do?grouponActivityPojo.id="+activityId+"&grouponActivityPojo.price="+price;
			doOpreator(url,null);
		}else{
			alert("请输入秒杀价");
		}

	}
	/**
	 **  修改活动数量
	 **/
	function updateLimitNum(limitNum,activityId)
	{
		if(limitNum != null && limitNum != "")
		{
			var url = "updateSeckillActivity.do?grouponActivityPojo.id="+activityId+"&grouponActivityPojo.limitNum="+limitNum;
			doOpreator(url,null);
		}else{
			alert("请输入活动数量");
		}

	}
	/**
	 **  修改活动剩余数量
	 **/
	function updateSurplusNum(surplusNum,activityId)
	{
		if(surplusNum != null && surplusNum != "")
		{
			var url = "updateSeckillActivity.do?grouponActivityPojo.id="+activityId+"&grouponActivityPojo.surplusNum="+surplusNum;
			doOpreator(url,null);
		}else{
			alert("请输入活动剩余数量");
		}

	}
	/**
	 **  修改排序
	 **/
	function updateSorting(sorting,seckillGoodsId,activityId)
	{
		if(sorting != null && sorting != "")
		{
			var url = "updateSeckillGoodsSorting.do?seckillGoodsPojo.id="+seckillGoodsId+"&seckillGoodsPojo.sorting="+sorting+"&seckillGoodsPojo.activityId="+activityId;
			doOpreator(url,null);
		}else{
			alert("请输入排序");
		}

	}
	function doOpreator(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	function goRefreshPage(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			queryData("seckillGoodsCnt.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}", "seckillGoodsList.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
	
	//上首页
	function topHome(id,activityId,isHome){
		var statusStr = "";
		var url = "";
		if(isHome == 0){
		 	statusStr = "首页不显示";
		} else {
			statusStr = "首页显示";
		}
		if(confirm(statusStr)){
			url = "updateIsHome.do?id="+id+"&activityId="+activityId+"&isHome="+isHome;
			MAOWU.ajax.get(url, null, goRefreshPage3);
		}else{
			return ;
		}
	}
	//刷新页面
	function goRefreshPage3(result){
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			queryData("seckillGoodsCnt.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}", "seckillGoodsList.do?seckillGoodsPojo.seckillId=${seckillGoodsPojo.seckillId}&randdelete="+rand,pageSize);
		}else{
			alert("修改失败");
		}
	}
</script>
