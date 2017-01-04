<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
</head>

<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">订单管理</a> &gt; <a href="#">发货订单导入</a>
		</div>
		<div class="Search_control">
			<p>发货订单导入</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		
		<div id="search_show" style="">
			 <form action="importOrder.do" method="post" id="formImport" enctype="multipart/form-data" >
            	<table>
            		<tr>
            			<td style="float:left;margin-bottom:15px;" colspan="2">
            				批量导入<a href="downloadTemplates.do" style="font-size:10pt">【下载发货模板】</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="float:left;margin-bottom:15px">
            				<span style="margin-left:100px">&nbsp;</span>
            			</td>
            			<td style="float:left;margin-bottom:15px">
            				<span style="font-size:10pt">发货订单：<input type="file" name="importupfile" /></span>
            			</td>
            		</tr>
            		<tr>
            			<td style="float:left;margin-bottom:15px">
                        	<span style="font-size:10pt;margin-left:100px;margin-right:10px;"><input name="repeatImport" type="checkbox" value="1"/> 允许重复导入</span>
            			</td>
            			<td style="float:left;margin-bottom:15px">
                			<span style="color:red;font-size:5pt"> 注：该项勾选可导入相同订单并覆盖。</span>
            			</td>
            		</tr>
            		<tr>
            			<td style="margin:auto" colspan="2">
                			<a class="submit_btn" onclick="$('#formImport').submit()">开始导入</a>
            			</td>
            		</tr>
            	</table>
              </form>
              <div class="Clear"></div>
	    </div>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
		
		<div class="p20">
			<h5 class="h5">操作结果</h5>
        	<div class="ui-box seller-search-form">
                <div class="ui-box-container">
                    <div class="ui-box-content">
                    	<div class="p10 f-14">
                    	    <span style="color:red" >本次导入</span>
                    		导入成功：<span style="color:green"><s:property value="success"/></span>
                    		导入失败：<span style="color:red" ><s:property value="fail"/></span>
                    	</div>
                    	
                    	<div class="floatRight">
		           			<form action="" method="post" id="sysform" onkeydown="if(event.keyCode==13)return false;">
                        		<input type="hidden" name="page.pageNo" value=0 id="pageNo">
								搜索订单：
								
                        		<input type="text" class="ui-input" name="deliveryOrderImportPojo.orderNo"/>
                        		 <a href="javascript:;" id="query_btn" class="submit_btn">&nbsp;&nbsp;查询</a>
                            </from>
                    	</div>
                    	
                    	<table width="100%" border="0" class="Info_list_table">
                            <thead>
                                <tr>
                                    <th>订单号</th>
                                    <th>快递公司</th>
                                    <th>快递单号</th>
                                    <th>状态</th>
                                    <th>备注</th>
                                </tr>
                            </thead>
                            <tbody id="body">
                            </tbody>
                        </table>
					    <!-- 页码开始 -->
						<div class="page">
							<div class="floatleft">
								总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
							</div>
							<div style="float: right" id="Pagination" class="pagination"></div>
							<div class="Clear"></div>
						</div>
						<!-- 页码结束 -->
                    </div>
                </div>
            </div>
        </div>
    </div>
       
    <!-- 尾部 -->
</body>

<script>
//查询
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}";
var pageSize = 10;
function query() {
	var rand=Math.random() * ( 100000 + 1);
	queryData("orderDeliveryCount.do?impBatchNo=${impBatchNo}", "orderDeliveryList.do?impBatchNo=${impBatchNo}&randquery=" + rand,pageSize);
}
/**
*分页展现页面函数
**/
function installPage() {
	$("#body").append(
			"<tr>"+
            "<td>"+this.orderNo+"</td>"+
            "<td>"+this.logisticsNameStr+"</td>"+
            "<td>"+this.logisticsNo+"</td>"+
            "<td>"+this.statusName+"</td>"+
            "<td>"+this.remarks+"</td>"+
        "</tr>"
	);
}
$(function() {
	/** 首次要初始化分页**/
	var rand=Math.random() * ( 100000 + 1);
	MAOWU.page.init(<s:property value='page.rowCount'/>, "orderDeliveryList.do?impBatchNo=${impBatchNo}&randIni="+rand,pageSize);
	$("#query_btn").click(query);
});
</script>
</html>
