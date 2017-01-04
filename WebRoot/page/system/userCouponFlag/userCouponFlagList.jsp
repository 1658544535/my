<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagecommon.js"></script>
<script type="text/javascript">
	
</script>	
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">模块名称</a> &gt; <a href="navigationList.do">模块名称</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="navigationList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<!-- <tr> 
					    <td align="right">导航分类名：</td>
						<td><label><input type="text" name="navigationPojo.name"
								value=""></label></td>
						<td align="right">审核状态：</td>
						<td><select name="navigationPojo.status" id="navigationPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="1">未审核</option>
							<option value="2">已审核</option>
				    		</select></td>
					</tr>-->
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
<a class="delAll_btn" onclick="deleteAll()">批量删除</a>
<a class="Add_btn" onclick="checkAll()">批量审核</a>
<a class="Add_btn" href="goAddNavigation.do">新增导航</a></s:if>
<form action="deletecouponAllById.do" id="idform"  method="post" >
<table width="100%" border="0" class="Info_list_table">
<tr>    
    <th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>
		<th>ID</th>
		<th>用户id</th>
		<th>获取时间</th>
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
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("userCouponFlagListCount.do", "userCouponFlagListAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		$("#body").append(
				"<tr>"+
				"<td><input  name='tids' type='checkbox' value="+this.id +"/></td>"+
				"<td>"+this.id+"</td>"+
				"<td>"+this.userId+"</td>"+
				"<td>"+this.gainDate+"</td>"+
				"<td>"+
				"<a class='edit_btn' href='goUpdateUserCouponFlagById.do?userCouponFlagPojo.id="+this.id +"'>编辑</a>"+
				"<a class='del_btn'  onclick=del('delUserCouponFlagById.id?userCouponFlag.id"+this.id+"')>删除</a>"+
				"<a class='edit_btn' onclick=check('checkUserCouponFlagById.id?userCouponFlag.id"+this.id+"')>通过审核</a>"+
				"<a class='edit_btn' onclick=uncheck('unCheckUserCouponFlagById.id?userCouponFlag.id"+this.id+"')>取消审核</a>"+
				"</td>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userCouponFlagListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});	
</script>