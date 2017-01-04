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
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">少儿频道</a> &gt; <a>少儿浏览记录</a> &gt;
<c:if test="${type==1}"><a href="#">频道浏览记录</a></c:if>
<c:if test="${type==2}"><a href="#">专场浏览记录</a></c:if>
<c:if test="${type==3}"><a href="#">商品浏览记录</a></c:if>
<c:if test="${type==4}"><a href="#">TV视频浏览记录</a></c:if>
<c:if test="${type==5}"><a href="#">视觉视频浏览记录</a></c:if></div>
    <div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->

		<form action="childrenChannelHistory.do?type=${type}" method="post" id="sysform">

			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">用户名：</td>
						<td><label><input type="text" name="childrenChannelHistoryPojo.userName" id="childrenChannelHistoryPojo.userName" value=""></label></td>
	                </tr>
	                <tr>
	                	<s:if test="type==1">
	                		<td align="right">频道名称：</td>
	                	</s:if>
	                	<s:elseif test="type==2">
	                		<td align="right">专场名称：</td>
	                	</s:elseif>
	                	<s:elseif test="type==3 or type==4 or type==5">
	                		<td align="right">商品名称：</td>
	                	</s:elseif>
							<td><input type="text" name="childrenChannelHistoryPojo.businessName" id="childrenChannelHistoryPojo.businessName" value=""></td>	
						<s:if test="type==3 or type==4 or type==5">
	                		<td align="right">专场名称：</td>
	                		<td><input type="text" name="childrenChannelHistoryPojo.businessNames" id="childrenChannelHistoryPojo.businessNames" value=""></td>
	                	</s:if>				
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
<div style="float:right">
<a class="submit_btn"  href="childrenChannelHistory.do?type=<s:property value='type'/>&childrenChannelHistoryPojo.paixu=1">浏览次数↑</a>
<a class="submit_btn"  href="childrenChannelHistory.do?type=<s:property value='type'/>&childrenChannelHistoryPojo.paixu=2">浏览次数↓</a>
</div>
  <form action="deletecouponAllById.do" id="idform"  method="post" >
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<!--<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()" ></th>-->
		<th>用户名</th>
		<th>浏览类型</th>
		<s:if test = "type == 1">
		<th>频道名称</th>
		</s:if>
		<s:elseif test = "type == 2">
		<th>专场标题</th>
		</s:elseif>
		<s:else>
		<th>商品名称</th>
		</s:else>
		<s:if test = "type==3 or type==4 or type==5">
		<th>专场标题</th>
		</s:if>
		<th>浏览次数</th>
		<th>创建时间</th>
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

</div>
</body>
</html>


	


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("findChildrenChannelHistoryCount.do?type=<s:property value='type'/>&childrenChannelHistoryPojo.paixu=${childrenChannelHistoryPojo.paixu}", "findChildrenChannelHistoryList.do?type=<s:property value='type'/>&childrenChannelHistoryPojo.paixu=${childrenChannelHistoryPojo.paixu}&randquery="+rand);
	}
}



	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var op ="";
		var type = ${type};
		if(type == 3 || type == 4 || type == 5){
			op = "<td>"+ this.businessNames + "</td>";
			}
		$("#body").append(
				"<tr><td>"+ this.userName + "</td>"+
				"<td>"+ this.typeName + "</td>"+
				"<td>"+ this.businessName + "</td>"+
				op+
				"<td>"+ this.hid + "</td>"+
				"<td>"+ this.createDateStr + "</td>"+
				"<td>"+ this.updateDateStr + "</td>"+
				"</tr>");
	}
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"findChildrenChannelHistoryList.do?type=<s:property value='type'/>&childrenChannelHistoryPojo.paixu=${childrenChannelHistoryPojo.paixu}&randIni="+rand);
		
		$("#query_btn").click(query);
		
		
	});
	
</script>