<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/spage.css"/>
<style>
		body{font-size:14px;}
		.messageBox{overflow:hidden;}
		.m-left{float:left;width:35%;}
		.m-left-top{padding:15px;border:1px solid #ddd;}
		.m-left-top .title1{font-size:20px;color:#333;}
		.m-left-top .good1{overflow:hidden;border-bottom:1px solid #ddd;padding:20px 0;}
		.m-left-top .good1 .img1{float:left;width:80px;height:80px;border:1px solid #ddd;}
		.m-left-top .good1 .img1 img{display:block;width:100%;height:100%;}
		.m-left-top .good1 .txt1{margin-left: 100px;margin-top:15px;font-size:16px;color:#666;}
		.m-left-top .info1{font-size:14px;color:#666;padding:20px 0;border-bottom:1px solid #ddd;}
		.m-left-top .link1{padding:20px 0;color:#999;font-size:14px;}
		.m-left-top .info1 li{padding:5px;}
		.m-left-bottom{text-align:center;padding:20px;}
		.m-left-bottom button{margin:0 5px;}
		.m-right{margin-left:40%;width:55%;background:#f3f3f3;padding:40px 20px;}
		.m-right li{overflow:hidden;padding:5px 0 15px;}
		.m-right li .label1{float:left;width:80px;text-align:right;font-size:14px;color:#666;line-height:30px;}
		.m-right li .main1{margin-left:120px;font-size:14px;color:#666;line-height:30px;}
		.form-control{display:inline;display: inline-block;zoom:1;width:100%;padding:5px 10px;border:1px solid #ddd;border-radius:3px;box-sizing:border-box;font-size:14px;color:#666;}
</style>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
	<script>
		$(function(){
			var $Intro = $("#introduction"),
				$Remarks = $("#remarks"),
				$LinkName = $("#linkName"),
				$LinkType = $("#linkType"),
				$Link = $("#link");
			var $ViewIntro = $("#viewIntroduction"),
				$ViewRemarks = $("#viewRemarks"),
				$ViewLinkName = $("#viewLinkName"),
				$ViewLink = $("#viewLink");

			$Intro.on("keyup", function(){
				console.log(1);
				var txt = $(this).val();
				$ViewIntro.html(txt);
			});

			$Remarks.on("keyup", function(){
				var txt = $(this).val();
				$ViewRemarks.html(txt);
			});

			$LinkName.on("keyup", function(){
				var txt = $(this).val();
				$ViewLinkName.html(txt);
			});

		});
	</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">消息模板表管理</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		<div style="width:100%">
			<form action="updateNoticeTemplate.do" method="post" id="from1">
			<input type="hidden" name="id" id="id" value="${noticeTemplatePojo.id}"  class="inputText"> 
			<input type="hidden" name="type" id="type" value="${noticeTemplatePojo.type}"  class="inputText"> 
				<div class="messageBox">
			<div class="m-left">
				<div class="m-left-top">
					<h2 class="title1">${title}</h2>
					<div class="good1">
						<div class="img1">
							<img src="${img}" />
						</div>
						<div class="txt1">${name}</div>
					</div>
					<div class="info1">
						<ul>
							<li id="viewIntroduction">${info}</li>
							<c:if test="${express != null && noticeTemplatePojo.type==6}"><li>快递公司: ${express}</li></c:if>
							<c:if test="${expressNo != null && noticeTemplatePojo.type==6}"><li>快递单号: ${expressNo}</li></c:if>
							<c:if test="${price != null && noticeTemplatePojo.type ==2 || noticeTemplatePojo.type ==3 || noticeTemplatePojo.type ==9 || noticeTemplatePojo.type ==10}"><li>订单金额: ${price}元</li></c:if>
							
							<c:if test="${pname != null }">
								<c:if test="${noticeTemplatePojo.type == 2 || noticeTemplatePojo.type == 9 }">
									<li>商品详情: ${pname}</li>
								</c:if>
								<c:if test="${noticeTemplatePojo.type == 1 || noticeTemplatePojo.type == 6 }">
									<li>商品信息: ${pname}</li>
								</c:if>
								<c:if test="${noticeTemplatePojo.type == 4 || noticeTemplatePojo.type == 5 }">
									<li>商品名称: ${pname}</li>
								</c:if>
							</c:if>
							<c:if test="${num != null && noticeTemplatePojo.type==6}"><li>商品数量: ${num}</li></c:if>
							<c:if test="${price != null && noticeTemplatePojo.type ==1 || noticeTemplatePojo.type ==4 || noticeTemplatePojo.type ==5}"><li>支付金额: ${price}元</li></c:if>
							<c:if test="${drawDate != null && noticeTemplatePojo.type ==4 || noticeTemplatePojo.type ==5}"><li>抽奖时间: ${drawDate}</li></c:if>
							<c:if test="${drawStatus != null && noticeTemplatePojo.type ==4 || noticeTemplatePojo.type ==5}"><li>抽奖结果: ${drawStatus}</li></c:if>
							
							<c:if test="${price != null && noticeTemplatePojo.type==7}"><li>退款金额: ${price}元</li></c:if>
							<c:if test="${refundItem != null}"><li>退款项目: ${refundItem}</li></c:if>
							<c:if test="${refundReason != null}"><li>退款原因: ${refundReason}</li></c:if>
							<c:if test="${refundDate != null}"><li>退款时间: ${refundDate}</li></c:if>
							
							<%-- <c:if test="${refundReason != null && noticeTemplatePojo.type==8}"><li>失败原因: ${refundReason}</li></c:if>
							<c:if test="${refundDate != null && noticeTemplatePojo.type==8}"><li>失败时间: ${refundDate}</li></c:if> --%>
							<c:if test="${orderNo != null && noticeTemplatePojo.type == 8 }">
								<li>订单号: ${orderNo}</li>
							</c:if>
							<c:if test="${refundReason != null && noticeTemplatePojo.type==8}"><li>拼团商品: ${pname}</li></c:if>
							<c:if test="${refundReason != null && noticeTemplatePojo.type==8}"><li>商品金额: ${price}元</li></c:if>
							<c:if test="${refundReason != null && noticeTemplatePojo.type==8}"><li>退款金额: ${refundPrice}元</li></c:if>
							
							<c:if test="${address != null && noticeTemplatePojo.type ==2 || noticeTemplatePojo.type ==9 }"><li>收货信息: ${address}</li></c:if>
							<c:if test="${orderNo != null && noticeTemplatePojo.type ==2 || noticeTemplatePojo.type ==3 || noticeTemplatePojo.type ==7 || noticeTemplatePojo.type ==9 || noticeTemplatePojo.type ==10}">
								<c:if test="${noticeTemplatePojo.type == 2 || noticeTemplatePojo.type == 9 }">
								<li>订单编号: ${orderNo}</li>
								</c:if>
								<c:if test="${noticeTemplatePojo.type != 2 && noticeTemplatePojo.type != 9 }">
								<li>订单号: ${orderNo}</li>
								</c:if>
							</c:if>
							<li id="viewRemarks">${remark}</li>
						</ul>
					</div>
					<div class="link1">
						<span id="viewLinkName">${lname}</span> &gt;&gt;
					</div>
				</div>
				<div class="m-left-bottom">
					<!--<button type="button" class="ok_btn" onclick="$('#messageForm').submit()">修改</button>-->
					<input type="button"  class="ok_btn" value="提交" id="sbutton"/>
					<button type="button" class="back_btn" onclick="window.history.back()">返回</button>
				</div>
			</div>
			
			<div class="m-right">
					<ul>
						<li>
							<span class="label1">标题</span>
							<div class="main1">
								${title}
							</div>
						</li>
						<li>
							<span class="label1">简介</span>
							<div class="main1">
								<input name="info" id="introduction" class="form-control" type="text" name="" value="${info}" />
							</div>
						</li>
						<c:if test="${express != null && noticeTemplatePojo.type==6}">
						<li>
							<span class="label1">快递公司</span>
							<div class="main1">
								${express}
							</div>
						</li>
						</c:if>
						<c:if test="${expressNo != null && noticeTemplatePojo.type==6}">
						<li>
							<span class="label1">快递单号</span>
							<div class="main1">
								${expressNo}
							</div>
						</li>
						</c:if>
						<c:if test="${price != null && noticeTemplatePojo.type ==2 || noticeTemplatePojo.type ==3 || noticeTemplatePojo.type ==9 || noticeTemplatePojo.type ==10}">
						<li>
							<span class="label1">订单金额</span>
							<div class="main1">
								${price}元
							</div>
						</li>
						</c:if>
						
						<c:if test="${pname != null && noticeTemplatePojo.type !=3 && noticeTemplatePojo.type !=7 && noticeTemplatePojo.type !=8 }">
						<li>
							<c:if test="${noticeTemplatePojo.type == 2 || noticeTemplatePojo.type == 9 }">
								<span class="label1">商品详情</span>
							</c:if>
							<c:if test="${noticeTemplatePojo.type == 1 || noticeTemplatePojo.type == 6 }">
								<span class="label1">商品信息</span>
							</c:if>
							<c:if test="${noticeTemplatePojo.type == 4 || noticeTemplatePojo.type == 5 }">
								<span class="label1">商品名称</span>
							</c:if>
							<div class="main1">
								${pname}
							</div>
						</li>
						</c:if>
						<c:if test="${price != null && noticeTemplatePojo.type ==1 || noticeTemplatePojo.type ==4 || noticeTemplatePojo.type ==5}">
						<li>
							<span class="label1">支付金额</span>
							<div class="main1">
								${price}元
							</div>
						</li>
						</c:if>
						<c:if test="${num != null && noticeTemplatePojo.type==6}">
						<li>
							<span class="label1">商品数量</span>
							<div class="main1">
								${num}
							</div>
						</li>
						</c:if>
						<c:if test="${drawDate != null && noticeTemplatePojo.type ==4 || noticeTemplatePojo.type ==5}">
						<li>
							<span class="label1">抽奖时间</span>
							<div class="main1">
								${drawDate}
							</div>
						</li>
						</c:if>
						<c:if test="${drawStatus != null && noticeTemplatePojo.type ==4 || noticeTemplatePojo.type ==5}">
						<li>
							<span class="label1">抽奖结果</span>
							<div class="main1">
								${drawStatus}
							</div>
						</li>
						</c:if>
						
						
						<c:if test="${price != null && noticeTemplatePojo.type==7}">
						<li>
							<span class="label1">退款金额</span>
							<div class="main1">
								${price}元
							</div>
						</li>
						</c:if>
						<c:if test="${refundItem != null}">
						<li>
							<span class="label1">退款项目</span>
							<div class="main1">
								${refundItem}
							</div>
						</li>
						</c:if>
						<c:if test="${refundReason != null}">
						<li>
							<span class="label1">退款原因</span>
							<div class="main1">
								${refundReason}
							</div>
						</li>
						</c:if>
						<c:if test="${refundDate != null}">
						<li>
							<span class="label1">退款时间</span>
							<div class="main1">
								${refundDate}
							</div>
						</li>
						</c:if>
						
						<%-- <c:if test="${refundReason != null && noticeTemplatePojo.type==8}">
						<li>
							<span class="label1">失败原因</span>
							<div class="main1">
								${refundReason}
							</div>
						</li>
						</c:if>
						<c:if test="${refundDate != null && noticeTemplatePojo.type==8}">
						<li>
							<span class="label1">失败时间</span>
							<div class="main1">
								${refundDate}
							</div>
						</li>
						</c:if> --%>
						
						<c:if test="${orderNo != null && noticeTemplatePojo.type == 8 }">
						<li>
							<span class="label1">订单号</span>
							<div class="main1">
								${orderNo}
							</div>
						</li>
						</c:if>
						
						<c:if test="${refundDate != null && noticeTemplatePojo.type==8}">
						<li>
							<span class="label1">拼团商品</span>
							<div class="main1">
								${pname}
							</div>
						</li>
						</c:if>
						
						<c:if test="${refundDate != null && noticeTemplatePojo.type==8}">
						<li>
							<span class="label1">商品金额</span>
							<div class="main1">
								${price}元
							</div>
						</li>
						</c:if>
						
						<c:if test="${refundDate != null && noticeTemplatePojo.type==8}">
						<li>
							<span class="label1">退款金额</span>
							<div class="main1">
								${refundPrice}元
							</div>
						</li>
						</c:if>
						
						<c:if test="${address != null && noticeTemplatePojo.type ==2 || noticeTemplatePojo.type ==9}">
						<li>
							<span class="label1">收货信息</span>
							<div class="main1">
								${address}
							</div>
						</li>
						</c:if>
						<c:if test="${orderNo != null && noticeTemplatePojo.type ==2 || noticeTemplatePojo.type ==3 || noticeTemplatePojo.type ==7 || noticeTemplatePojo.type ==9 || noticeTemplatePojo.type ==10}">
						<li>
							<c:if test="${noticeTemplatePojo.type == 2 || noticeTemplatePojo.type == 9 }">
							<span class="label1">订单编号</span>
							</c:if>
							<c:if test="${noticeTemplatePojo.type != 2 && noticeTemplatePojo.type != 9 }">
							<span class="label1">订单号</span>
							</c:if>
							<div class="main1">
								${orderNo}
							</div>
						</li>
						</c:if>
						<li>
							<span class="label1">备注</span>
							<div class="main1">
								<input name="remark" id="remarks" class="form-control" type="text" name="" value="${remark}" />
							</div>
						</li>
						<li>
							<span class="label1">链接名</span>
							<div class="main1">
								<input name="lname" id="linkName" class="form-control" type="text" name="" value="${lname}" />
							</div>
						</li>
						<li>
							<span class="label1">链接</span>
							<div class="main1" style="font-size:0;">
								<select name="linkType" id="linkType" class="form-control" style="width:24%;margin-right:2%;">
									<option <c:if test="${linkType == 4}">selected="selected"</c:if> value="4">首页</option>
									<option <c:if test="${linkType == 5}">selected="selected"</c:if> value="5">普通拼团</option>
									<option <c:if test="${linkType == 6}">selected="selected"</c:if> value="6">猜价格</option>
									<option <c:if test="${linkType == 7}">selected="selected"</c:if> value="7">免费抽奖</option>
									<option <c:if test="${linkType == 8}">selected="selected"</c:if> value="8">专题</option>
									<option <c:if test="${linkType == 9}">selected="selected"</c:if> value="9">专题分类</option>
									<option <c:if test="${linkType == 10}">selected="selected"</c:if> value="10">77专区</option>
									<option <c:if test="${linkType == 11}">selected="selected"</c:if> value="11">0.1夺宝</option>
									<option <c:if test="${linkType == 1}">selected="selected"</c:if> value="1">商品详情页</option>
									<option <c:if test="${linkType == 2}">selected="selected"</c:if> value="2">订单详情页</option>
									<c:if test="${noticeTemplatePojo.type !=1 && noticeTemplatePojo.type !=4 && noticeTemplatePojo.type !=6 && noticeTemplatePojo.type !=7 && noticeTemplatePojo.type !=5 }">
									<option <c:if test="${linkType == 3}">selected="selected"</c:if> value="3">团详情页</option>
									</c:if>
								</select>
								<span style="display: none;" name="paramId">
								<input name="param" id="link" class="form-control" style="width:74%;" type="text" value="${paramValue}" />
								</span>
							</div>
						</li>
					</ul>
			</div>

		</div>
				
			</form>
		</div>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#sbutton").click(function(){	
			if(tt.validate()){
				if($("select[name='linkType']").val() == 5 || $("select[name='linkType']").val() == 8 || $("select[name='linkType']").val() == 9){
				
				}else{
					$("input[name='param']").val("");
				}
				document.getElementById("from1").submit();					
			}
		});
		
		
		$("select[name='linkType']").bind("change",function(){
			if($("select[name='linkType']").val() == 5 || $("select[name='linkType']").val() == 8 || $("select[name='linkType']").val() == 9){
				$("span[name='paramId']").css("display","");
			}else{
				$("span[name='paramId']").css("display","none");
			}
		});
		
		var lt = "${linkType}";
		if(lt == 5 || lt == 8 || lt == 9){
			$("span[name='paramId']").css("display","");
		}else{
			$("span[name='paramId']").css("display","none");
		}
	});	
</script>