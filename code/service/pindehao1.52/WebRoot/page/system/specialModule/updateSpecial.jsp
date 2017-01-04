<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerMartshowItems-9b61bc2a35m.css" type="text/css" media="all" />
<style>
.mar-tb{
	margin: 15px 0px 15px 0px;
}
.base-info{
	width: 100%;
	height: 40px;
	line-height: 40px;
	padding-left:10px;
	border-width: 1px;
	border-style: solid;
	border-color: rgba(204, 204, 204, 1);
}
</style>
<style>
.mask{width:100%;height:100%;background:#fff;top:0;left:0;display:none;position:fixed;z-index:10;}
.wrap{display:none;width:100%;height:100%;position:fixed;top:0;left:0;z-index:11;background:#fff;}
.top01-popup .ok{float:right;cursor:pointer;font-style:normal;padding-right:10px;color:#000;}
</style>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="goSpecial.do">专题管理</a> &gt; <a href="goEditSpecial.do?id=${id}">修改专题</a> &gt; <a href="goSpecialGoods.do?specialGoodsPojo.specialId=${id}">专题商品列表</a>
		</div>
		<div class="h15"></div>
		<div>
		<form action="updateSpecial.do" method="post" id="from1" enctype="multipart/form-data">
		<input type="hidden" name="specialPojo.id" value="${id}">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr>
					<td align="right" class="grey" width="15%">标题：</td>
					<td><input type="text" name="specialPojo.title" id="specialPojo.title" value="${specialPojo.title}"/></td>
					<td><span id="title_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">专题分类：</td>
					<td><select name="specialPojo.specialTypeId" id="ticketType">
                    <option value="0"  <c:if test="${specialPojo.specialTypeId==0}">selected="selected"</c:if>>无分类</option>
				    <c:forEach items="${specialTypePojos}" var="specialType">
					<option value="${specialType.id}" <c:if test="${specialType.id==specialPojo.specialTypeId}">selected="selected"</c:if>>${specialType.name}</option>
				    </c:forEach>
	                </select></td>
					<td><span id="specialTypeId_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">上传图片：</td>
					<td><table border="0" cellpadding="0" cellspacing="0">
	                    <tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/special/${specialPojo.image}" height="100px"  /></td></tr>
	                    <tr>
	                    <td><input type="file" name="upfile" class="floatLeft" id="ticketName"></td>
	                    </tr>
	                    </table>
	                    <span style="color:red">(图片格式640*300)</span></td>
					<td><span id="image_mgId"></span></td>
				</tr>
				<tr>
				    <td align="right" class="grey" width="15%">排序:</td>
					<td width="35%"><input type="text" name="specialPojo.sorting" id="specialPojo.sorting" value="${specialPojo.sorting}" /></td>
					<td><span id="sorting_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">分享图标：</td>
					<td><table border="0" cellpadding="0" cellspacing="0">
	                    <tr><td><img  class="floatLeft" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/special/${specialPojo.icon}" height="100px"  /></td></tr>
	                    <tr>
	                    <td><input type="file" name="upfile2" class="floatLeft" id="ticketName"></td>
	                    </tr>
	                    </table>
					<td><span id="image2_mgId"></span></td>
				</tr>
				<tr>
					<td align="right" class="grey" width="15%">分享文案：</td>
					<td><input type="text" name="specialPojo.shareDesc" id="specialPojo.shareDesc" value="${specialPojo.shareDesc}"/></td>
					<td><span id="shareDesc_mgId"></span></td>
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
    var specialTypeId = new tt.Field(" 专题分类", "specialPojo.specialTypeId").setMsgId("specialTypeId_mgId");
	var title = new tt.Field(" 标题", "specialPojo.title").setMsgId("title_mgId");
	var sorting = new tt.Field(" 排序", "specialPojo.sorting").setMsgId("sorting_mgId");
	//var specialTypeId = new tt.Field(" 专题分类", "specialPojo.specialTypeId").setMsgId("specialTypeId_mgId");
    var shareDesc = new tt.Field(" 分享文案", "specialPojo.shareDesc").setMsgId("shareDesc_mgId");
	
	tt.vf.req.add(title,sorting,shareDesc);
	tt.vf.num.add(sorting);
	tt.vf.int.add(sorting);
	new tt.NRV().set(0, '++').add(sorting);
    //new tt.LV().set(0, 50).add(name);
$(document).ready(function() {
	$("#sbutton").click(function(){	
		if(tt.validate()){
				document.getElementById("from1").submit();								
		}
	});
});	
</script>
<script type="text/javascript">
	//弹出层
	$(function(){
		$('.action').click(function(){
			$('.wrap').show();
			$('.mask').show();
		});
		$('.ok').click(function(){
			$('.wrap').hide();
			$('.mask').hide();
		})
	})
</script>
<script type="text/javascript">
	//加载团免活动商品
	$(function(){
	  replacePro("${grouponActivityPojo.productId}");
	})
	//替换商品
	function replacePro(id){
        $.ajax({
            type: "GET",
            url: "findFreeGroupProductById.do?productPojo.id="+id,
            dataType: "json",
            async:false,
            success: function(data){
            		//替换拼团信息内容
            		var productObj=data.result;
            		var tableStr="";
            		if(productObj != null){
            			console.log(productObj);
            			$("#productId").val(productObj.id);
            			tableStr = '<table width="100%" border="0" class="Info_list_table" style="background:#fff">'+
			    						'<tr>'+
			    						'<th>商品编号</th>'+
			    						'<th>商品货号</th>'+
			    						'<th>商品名称</th>'+
			    						'<th>商品图</th>'+
			    						'</tr>'+
			    						'<tbody id="body">'+
			    						'<tr>'+
			    							'<td>'+productObj.productNo+'</td>'+
			    							'<td>'+productObj.productNum+'</td>'+
			    							'<td>'+productObj.productName+'</td>'+
			    							'<td><img src="/upfiles/product/'+productObj.image+'" width="50px" height="50px"/></td>'+
			    						'</tr>'+
			    						'</tbody>'+    
		    						'</table>';
		    			$("#product").empty();
            			$("#product").append(tableStr);
            		} else {
            			alert("替换失败!");
            		}
	        }
        });
		$(".ok").click();
	}
</script>