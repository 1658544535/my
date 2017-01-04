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
                            结算
                            <%-- <span class="settle-info-head-text orange">
                                您可在2015年12月18日及以后的每15个自然日进行结算
                                <a href="../help.html" target="_blank">
                                    常见问题
                                </a>
                            </span> --%>
                        </h1>
                        <div class="settle-info-box">
                            <div class="settle-info-item">
                                <h3>
                                    总额
                                    <span class="settle-info-note">
                                        已结算的所有金额
                                    </span>
                                </h3>
                                <div class="settle-info-num">
                                    <b>
                                        ${amount}元
                                    </b>
                                </div>
                            </div>
                         <div class="settle-info-item last">
                                <h3>
                                    可提现余额
                                    <span class="settle-info-note">
                                        已结算但未申请提现的金额
                                    </span>
                                </h3>
                                <div class="settle-info-num">
                                    <b>
                                        ${balance}元
                                    </b>
                                <a href="goWithdrawWeb.do" class="">
                                    立即提现
                                </a>
                            </div> 
                        </div>
                     </div>
                        <h2>
                            结算记录
                        </h2>
                        <%-- <div class="need-invoice-form pull-right p10 ">
                            <span class="orange">
                                是否开发票:
                            </span>
                            &nbsp;&nbsp;
                            <input class="view-NeedInvoice" id="need_invoice_0" name="need_invoice"
                            type="radio" value="0">
                            <label for="need_invoice_0">
                                &nbsp;否&nbsp;
                            </label>
                            &nbsp;&nbsp;&nbsp;
                            <input class="view-NeedInvoice" id="need_invoice_1" name="need_invoice"
                            type="radio" value="1" checked="checked">
                            <label for="need_invoice_1">
                                &nbsp;是&nbsp;
                            </label>
                        </div> --%>
                        <div class="ui-table-container p20 with-title">
                            <table class="ui-table">
                                <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                <thead>
                                    <tr>
                                        <th style="width: 65px">
                                            结算日
                                        </th>
                                        <th>
                                            结算
                                        </th>
                                        <th style="width: 140px">
                                            结算/更新时间
                                        </th>
                                        <th style="width: 50px">
                                            状态
                                        </th>
                                        <th>
                                            操作
                                        </th>
                                    </tr>
                                </thead>
                                <tbody id="body">
                                    
                                </tbody>
                            </table>
                            <!-- <p class="grey" style="text-align: right;margin-top: 5px;color: #aaa">
                                注：专场对账单确定完成的次日上午6点以后，您可对此笔金额进行提现操作。
                            </p> -->
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
			queryData("goSettleWebCount.do", "goSettleWebList.do?randquery="+rand,pageSize);
		}
	}
	
	function installPage() {
		var str = "";
		if(this.status == 1){
			str = "<a class='ui-button ui-button-sorange ui-dbutton-self mr-5' style='width: 62px' href='#' onclick='check("+this.id+")'>确认</a>";
		}
		
		$("#body").append(
				"<tr><td><p>"+this.settleDateStr+"</p></td>"+
                "<td><p><em>"+this.settleAmount+"</em></p></td>"+
                "<td><p>结算："+this.settleDateStr+"</p><p>更新："+this.updateDateStr+"</p></td>"+
                "<td><p><span></span>"+this.statusName+"</p></td>"+
                "<td>"+
                "<a class='ui-button ui-button-sorange ui-dbutton-self mr-5' style='width: 62px' href='getOrderSetterExcel.do?manufacturerSettlePojo.id="+this.id+"'>对账单</a>"+str+
                "</td></tr>"
                );
	}
	//分页展现页面函数 
	$(function() {
		//首次要初始化分页
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"goSettleWebList.do?randIni="+rand,pageSize);
		$("#query_btn").click(query);
	});
</script>