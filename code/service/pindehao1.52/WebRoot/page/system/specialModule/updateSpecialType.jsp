<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">专题模块</a> &gt; <a href="#">专题分类</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		<div>
			<form action="updateSpecialType.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table"> 
					<input type="hidden" name="specialTypePojo.id" id="specialTypePojo.id" value="${specialTypePojo.id}" class="inputText" />
					<tr>
						<td align="right" class="grey" width="15%">名称：</td>
						<td><input type="text" name="specialTypePojo.name" id="specialTypePojo.name" value="${specialTypePojo.name}" /></td>
						<td><span id="name_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">排序：</td>
						<td><input type="text" name="specialTypePojo.sorting" id="specialTypePojo.sorting" value="${specialTypePojo.sorting}" /></td>
						<td><span id="sorting_mgId"></span></td>
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
	var title = new tt.Field(" 名称", "specialTypePojo.name").setMsgId("name_mgId");
	var sorting = new tt.Field(" 排序", "specialTypePojo.sorting").setMsgId("sorting_mgId");
    tt.vf.req.add(title,sorting);
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