<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    </head>

<body>
<jsp:include page="../sellerHeader.jsp"></jsp:include>
	<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                           .view-ModifyShipmentPanel{margin-bottom:5px;}h5.h5{width:960px;}h5.h5 span{float:right;width:100px;text-align:center;}.u-icon-fenxiao{display:inline-block;*display:inline;*zoom:1;height:14px;line-height:14px;color:#fff;background:#ffae2e;border:1px solid #fa9a12;border-radius:2px;padding:0 3px;}.view-ModifyShipmentSave{background:none;border:none;color:#08c;}.view-ModifyShipmentSave:hover{text-decoration:underline;}
                        </style>
                        <h1 class="seller-title">
                            发货订单导入
                        </h1>
		<div class="p20">
			 <form action="importOneOrderWeb.do" method="post" id="formImport" enctype="multipart/form-data" >
            	<table>
            		<tr>
            			<td style="float:left;margin-bottom:15px;" colspan="2">
            				单条导入
            			</td>
            		</tr>
            		<tr>
            			<td style="float:left;margin-bottom:15px">
            				<span style="font-size:10pt">订单编号：<input type="text" class="ui-input" name="orderNum"/></span>
            				<p id="orderNum_mgId"></p>
            			</td>
            			<td style="float:left;margin-bottom:15px">
                            <span style="font-size:10pt">快递公司：<input type="text" class="ui-input" name="logisticsName"/></span>
                            <p id="logisticsName_mgId"></p>
            			</td>
            			<td style="float:left;margin-bottom:15px">
                            <span style="font-size:10pt">快递单号：<input type="text" class="ui-input" name="logisticsNo"/></span>
                            <p id="logisticsNo_mgId"></p>
            			</td>
            			<td style="float:left;margin:5px">
                			<input class="ui-button ui-button-sgreen" value="开始导入" id="allImport"/>
            			</td>
            		</tr>
            	</table>
              </form>
              <div class="Clear"></div>
	    </div>

		<div class="p20">
			 <form action="importOrderWeb.do" method="post" id="formImport1" enctype="multipart/form-data" >
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
            			<td style="margin:auto" colspan="2">
                			<input class="ui-button ui-button-sgreen" value="开始导入" id="oneImport"/>
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
    </div>
    </div>
       <jsp:include page="../sellerFooter.jsp"></jsp:include>
    <!-- 尾部 -->
</body>

<script>
var orderNum =new tt.Field(" 订单编号 ","orderNum").setMsgId("orderNum_mgId");
var logisticsName =new tt.Field("快递公司 ","logisticsName").setMsgId("logisticsName_mgId");
var logisticsNo =new tt.Field(" 快递单号 ","logisticsNo").setMsgId("logisticsNo_mgId");
tt.vf.req.add(orderNum,logisticsName,logisticsNo);

//查询
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}";
var pageSize = 10;
function query() {
	var rand=Math.random() * ( 100000 + 1);
	queryData("orderDeliveryCountWeb.do?impBatchNo=${impBatchNo}", "orderDeliveryListWeb.do?impBatchNo=${impBatchNo}&randquery=" + rand,pageSize);
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
	MAOWU.page.init(<s:property value='page.rowCount'/>, "orderDeliveryListWeb.do?impBatchNo=${impBatchNo}&randIni="+rand,pageSize);
	$("#query_btn").click(query);
	$("#allImport").click(function() {
			if(tt.validate()){
				document.getElementById("formImport").submit();
			}
		});
	$("#oneImport").click(function() {
				document.getElementById("formImport1").submit();
		});	
});
</script>
</html>
