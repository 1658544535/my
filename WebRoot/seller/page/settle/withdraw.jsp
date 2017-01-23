<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">
//申请提现
function withdraw(){
	if(tt.validate()){
		if(confirm("确认申请提现吗？")){
			var val=Number($("input[name='manufacturerWithdrawPojo.withdrawAmount']").val());
			var url = "doWithdrawWebCheck.do?manufacturerWithdrawPojo.withdrawAmount="+val;	
			doOpreator(url,null);
		}else{
			return ;
		}
	}
}
//提现取消申请
function unWithdraw(val){
	if(confirm("确认取消该申请吗？")){
		var url = "doWithdrawWebUncheck.do?manufacturerWithdrawPojo.id="+val;	
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
		alert("操作成功！");
		queryData("goWithdrawWebCount.do", "goWithdrawWebList.do?randquery="+rand);
	}else if(result=="2"){
		alert("申请后1小时后才能再次申请！");
	}else if(result=="3"){
	    alert("您还未绑定银行卡！");
	}else if(result=="4"){
	    alert("你的账户还未通过审核哦！");
	}else{
		alert("操作失败！");
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
                            提现
                        </h1>
                        <div class="ui-box withdraw-apply-box">
                            <div class="ui-box-head">
                                <h3 class="ui-box-head-title">
                                    申请提现
                                </h3>
                                <span class="ui-box-head-text orange" style="color:#E63">
                                    提现处理时间为提现后10个工作日
                                </span>
                            </div>
                            <form class="ui-form">
                                <fieldset>
                                    <legend>
                                        申请提现
                                    </legend>
                                    <div class="withdraw-apply-content">
                                        <span>
                                            可提现余额:
                                        </span>
                                        <span class="withdraw-apply-balance" id="balance">
                                             <font>${balance}</font>元
                                        </span>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                      <!--  <input class="ui-input" type="text" name="manufacturerWithdrawPojo.withdrawAmount" id="withdrawAmount" placeholder="提现金额">-->
                                        <a href="goWithdrawals.do" class="ui-button ui-button-sgreen view-BtnApply withdraw-apply-btn">提现</a>
                                       <!-- <input type="button" value="提现" class="ui-button ui-button-sgreen view-BtnApply withdraw-apply-btn"  onclick="withdraw()">-->
                                        <span class="orange" style="font-size:12px;margin-left:10px;width:150px">
                                            注：提现金额最少为100元；申请后1小时后才能再次申请
                                        </span>
                                    </div>
                                    <div id="withdrawAmount_mgId">
                                	</div>
                                </fieldset>
                            </form>
                        </div>
                        <h2>
                            提现记录
                        </h2>
        <!--   <form action="manufacturerWithdrawExcel.do"  method="post" id="sysform1" onkeydown="if(event.keyCode==13)return false;">
           <div  class="ui-table-container p20 with-title">
				              
						<label>  编号：</label><input type="text" name="manufacturerWithdrawPojo.number"><input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
					<a class="btn-write" onclick="$('#sysform1').submit()">导出Excel</a>
		   </form>-->
		   
		   <form action="order.do?os=${os}&a=${a}" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
						<td align="right">编号：</td>
						<td><label><input type="text"
								name="manufacturerWithdrawPojo.number" id="ticketRulePojo.ticketName"
								value="${manufacturerWithdrawPojo.number }"></label>
						</td>
					</tr>
				</table>
			</div>
							<div class="floatRight">
					<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
			
		</form>
                        <div class="ui-table-container p20 with-title">
                            <table class="ui-table">
                                <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                <thead>
                                    <tr>
                                        <th>
                                            编号
                                        </th>
                                        <th>
                                            状态
                                        </th>

                                        <th>
                                            提现金额
                                        </th>
                                        <th>
                                           手续费
                                        </th>
                                       <th>
                                            银行名
                                        </th>
                                       <th>
                                            账户ID
                                        </th>
                                        <th>
                                            账户名
                                        </th>
                                        <th>
                                            提现日
                                        </th>
                                        <th>
                                             申请/审核时间
                                        </th>
                                        <th>
                                             操作
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
	var withdrawAmount = new tt.Field("提现金额","manufacturerWithdrawPojo.withdrawAmount").setMsgId("withdrawAmount_mgId");
	//tt.Conf.reqStarCls = "";
	tt.vf.req.add(withdrawAmount);
	tt.vf.num.add(withdrawAmount);
	new tt.NRV().set(100, ${balance}).add(withdrawAmount);
	
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	var pageSize = 10;
	
	function query() {
			var rand=Math.random() * ( 100000 + 1);
			queryData("goWithdrawWebCount.do", "goWithdrawWebList.do?randquery="+rand,pageSize);
	}
	
	function installPage() {
		var str = "";
		if(this.status == 0){
			str = "<a class='ui-button ui-button-sorange ui-dbutton-self mr-5' style='width: 62px' onclick='unWithdraw("+this.id+")' >取消申请</a>";
		}
		$("#body").append(
				"<tr><td><p>"+this.number+"</p></td>"+
                "<td><p><span></span>"+this.statusName+"</p></td>"+
                "<td><p><em>"+this.withdrawAmount+"</em></p></td>"+
                "<td><p><em>"+this.withdrawalFee+"</em></p></td>"+
                "<td><p><em>"+this.bankName+"</em></p></td>"+
                "<td><p><em>"+this.bankCardNo+"</em></p></td>"+
                "<td><p><em>"+this.userName+"</em></p></td>"+
                "<td><p><em>"+this.withdrawDateStr+"</em></p></td>"+
                "<td><p>申请："+this.createDateStr+"</p><p>审核："+this.updateDateStr+"</p></td>"+
                "<td>"+str+"</td>"+
                "</tr>"
                );
	}
	//分页展现页面函数 
	$(function() {
		//首次要初始化分页
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"goWithdrawWebList.do?randIni="+rand,pageSize);
		$("#query_btn").click(query);
	});


</script>