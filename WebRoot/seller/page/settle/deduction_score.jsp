<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">
function check(val){
	if(confirm("确认结算吗？")){
		var url = "doSettleWebCheck.do?manufacturerSettlePojo.id="+val;	
		doOpreator(url,null);
	}else{
		return ;
	}
}

function doOpreator(url,params){
	MAOWU.ajax.get(url, params, goRefreshPage);
}
 
function goRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("确认成功！");
		queryData("goSettleWebCount.do", "goSettleWebList.do?randquery="+rand);
	}else{
		alert("确认失败！");
	}
}
</script>
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<div class="seller-search-form" style="margin: 8px 0 -14px -8px;display:none;">
                <form action="#" method="post" accept-charset="utf-8" id="sysform">
                	<input type="hidden" name="page.pageNo" value=0 id="pageNo" />
                </form>
        </div>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .ui-table-container{position:relative;}.view-ConfirmNoticeTip{position:absolute;width:400px;display:none;}.need-invoice-form{text-aligh:right;width:190px;margin:4px;margin-left:780px;}
                        </style>
                        <h1 class="seller-title">
                            扣分详情
                        </h1>
                        
                        <h2>
                        </h2>
                        <div class="ui-table-container p20 with-title">
                            <table class="ui-table">
                                <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                <thead>
                                    <tr>
                                        <th style="width: 140px">
                                            扣减时间
                                        </th>
                                        <th style="width: 65px">
                                            扣减分数
                                        </th>
                                        <th>
                                            扣减原因
                                        </th>
                                    </tr>
                                </thead>
                                <tbody id="body">
                                    
                                </tbody>
                            </table>
                            <!-- 页码开始 -->
	                            <div class="pagination" id="Pagination">
	                            </div>
                            <!-- 页码结束 -->
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<jsp:include page="../sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	var pageSize = 10;
	
	function query() {
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("getDeductionScoreCountWeb.do", "getDeductionScoreListWeb.do?randquery="+rand,pageSize);
		}
	}
	
	function installPage() {
		$("#body").append(
				"<tr><td><p>"+this.createDateStr+"</p></td>"+
                "<td><p><font color='red'>"+this.deductScore+"</font></p></td>"+
                "<td><p>"+this.remark+"</p></td></tr>"
                );
	}
	//分页展现页面函数 
	$(function() {
		//首次要初始化分页
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"getDeductionScoreListWeb.do?randIni="+rand,pageSize);
		$("#query_btn").click(query);
	});
</script>