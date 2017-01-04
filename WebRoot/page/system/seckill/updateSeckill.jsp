<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="goSeckill.do">掌上秒杀管理</a> &gt; <a href="goEditSeckill.do?id=${id}">掌上秒杀编辑</a> &gt; <a href="goSeckillGoods.do?seckillGoodsPojo.seckillId=${id}">秒杀商品表管理</a></div>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateSeckill.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="seckillPojo.id" id="seckillPojo.id" value="${seckillPojo.id}" class="inputText" />
				<tr>
					<td align="right" class="grey" width="15%">活动时间：</td>
					<td><input name="seckillPojo.beginTime" value="${seckillPojo.beginTimeStr}" id="beganday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', isShowToday: false, isShowClear: true })"/>-
					<input name="seckillPojo.endTime" value="${seckillPojo.endTimeStr}" id="endday" readonly="readonly" class="Wdate" type="text" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', isShowToday: false, isShowClear: true,minDate:'#F{$dp.$D(\'beganday\')}' })"/></td>
					<td><span id="beginTime_mgId"></span><span id="endTime_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">排序：</td>
					<td><input type="text" name="seckillPojo.sorting" id="seckillPojo.sorting" value="${seckillPojo.sorting}" /></td>
					<td><span id="sorting_mgId"></span></td>
				</tr>
				</table> 
			</form>
		</div>
		<div class="Btn_div">
			<button type="input" class="back_btn" onclick="window.history.back()">返回</button><input type="button"  class="ok_btn" value="提交" id="sbutton"/>
		</div>
	</div>
</body>
</html>
<script>
	var beginTime =new tt.Field(" 开始时间 ","seckillPojo.beginTime").setMsgId("beginTime_mgId");
	var endTime =new tt.Field(" 结束时间 ","seckillPojo.endTime").setMsgId("endTime_mgId");	
	var sorting =new tt.Field(" 排序","seckillPojo.sorting").setMsgId("sorting_mgId");
	tt.vf.req.add(sorting,beginTime,endTime);
	tt.vf.num.add(sorting);
	tt.vf.int.add(sorting);
	new tt.NRV().set(0, '++').add(sorting);
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				document.getElementById("from1").submit();					
			}
		});
	});	
</script>