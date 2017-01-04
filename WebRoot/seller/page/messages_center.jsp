<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商家中心</title>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
<script type="text/javascript">
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = true; 
		} 
		} 	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = false		
		} 
		} 
	}
}
function delAll(){
		$("#idform").attr("action","delMessagesCenterWebAll.do").submit();
	}
function readAll(){
		$("#idform").attr("action","readMessagesCenterWebAll.do").submit();
	}
</script>
    </head>
	<body>
		<jsp:include page="sellerHeader.jsp"></jsp:include>
			<div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <h1 class="seller-title">
                           	消息
                        </h1>
                        <div class="box">
                        <form action="" id="idform" method="post">
	                        <div class="ui-table-container p20 martshow-index">
		                         <div id="message-op" style="overflow:hidden;padding-bottom:10px;">                            
		                            <a class="ui-button ui-button-lred fl" id="batch-delete" onclick="delAll()" style="margin-right:10px;">
		                           	               批量删除
		                            </a>
		                            <a class="ui-button ui-button-lblue fl" id="batch-read" onclick="readAll()">
		                              	   批量已读
		                            </a>
		                        </div>
		                        <table class="ui-table mt-8">
			                    <thead>	                    
				            	<tr>		            	    
				            	    <th><input type="checkbox" class="J_checkedAll" id="selectcb" name="selectcb" onclick="allcb()" ></th>
				                	<th>时间</th>
				                	<th width="500px">内容</th>
				                </tr>
				                </thead>		                
			                    <tbody id="body"></tbody> 
			                    </table>
		                    </div>
                        </div>
                            </form>
                            <div style="float: right" id="Pagination" class="pagination"></div>
                        </div>
                     </div>
                </div>
                <div class="miniDialog_mask" style="display: none;"></div>
                <div class="miniDialog_wrapper showMessagesDeta" style="width: 600px; height: auto;padding-bottom:20px; margin-left: -300px; margin-top: -225px;">
		            <div class="miniDialog_title">
		        				        消息
		            </div>
		            <div class="miniDialog_content_outer">
		                <div class="miniDialog_content">
		                    <div style="padding:15px 60px;">
			                    <div class="ui-form-item view-InputItem" style="padding:0 5px 20px 50px">
				                    <label for="" class="ui-label">时间:</label>
				                    <div class="ui-txt" id="messagesTime">2016-01-12</div>
				                </div>
			                    <div class="ui-form-item view-InputItem" style="padding:0 5px 20px 50px">
				                    <label for="" class="ui-label">内容:</label>
				                    <div class="ui-txt" id="messagesTxt" style="max-height:200px;overflow:auto;">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</div>
				                </div>
		                    </div>
		                    <div style="text-align:center;"><a class="ui-button ui-button-lgreen" href="javascript:messagesPopup_close();">关闭</a></div>
		                </div>
		            </div>
		            <div class="miniDialog_buttons_area">
		            </div>
		            <div class="miniDialog_close_x" onClick="messagesPopup_close();">
		                <i class="iconfont">󰂳</i>
		            </div>
		        </div>
		<jsp:include page="sellerFooter.jsp"></jsp:include>
	</body>
</html>
<script type="text/javascript">	
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}";
	//异步查询消息数目
	function read(val){
		$.ajax({
			url:'messageCount.do?messagesCenterPojo.id='+val,
			type:'post',
			dataType: 'json',
			success: function(result){
				if(result!=0){
					$("#messageCount").empty();
					$('#messageCss').css("color","#ff647c");
					$('#messageCount').text("("+result+")");
				}else{
					$("#messageCount").empty();
					$('#messageCss').css("color","gray");
				}
			}
	
		});
	}
	//分页展现页面函数 
	$(function() {
		/** 首次要初始化分页**/
		var rand=Math.random() * ( 100000 + 1);	
		MAOWU.page.init(<s:property value='page.rowCount'/>, "messagesCenterListWeb.do?messagesCenterPojo.userId=${messagesCenterPojo.userId}&randIni="+rand);
		read(0);
	});
	function installPage() {
		if(this.isRead == 1){
			var trStr = "<tr style=\"color:#CCCCCC;\">";
			var tdStr = "style='color:#CCCCCC'";
		} else {
			var trStr = "<tr>";
			var tdStr = "";
		}
		$("#body").append(
			trStr+
			    "<td><input name='tids' type='checkbox' value="+this.id+"></td>"+
				"<td>"+this.createDateStr+"</td>"+
				"<td><p style='width:500px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'><a "+tdStr+" href='javascript:;' class='messagesPopup_open' onClick='messagesPopup_open("+this.id+")'>"+this.message+"</a></p></td>"+
		        "</tr>"
	               );
	}
	function query() {
		var rand=Math.random() * ( 100000 + 1);
		queryData("messagesCenterCountWeb.do?messagesCenterPojo.userId="+'${messagesCenterPojo.userId}', "messagesCenterListWeb.do?randquery="+rand+"&messagesCenterPojo.userId="+'${messagesCenterPojo.userId}');
	}
	//弹出内容
	var val;
	function messagesPopup_open(v){
	val = v;
	$(document).delegate(".messagesPopup_open","click",function(){
		var _this = $(".messagesPopup_open");
		var aTime = _this.parents("tr").find("td").eq(1).text();
		var aTxt = _this.parents("tr").find("td").eq(2).find("a").text();
		$("#messagesTime").text(aTime);
		$("#messagesTxt").text(aTxt);
		$(".showMessagesDeta,.miniDialog_mask").show();
	})}
	function messagesPopup_close(){
		$(".showMessagesDeta,.miniDialog_mask").hide();
		read(val);
		query();
	}
</script>