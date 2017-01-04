<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	/*改变checkBox的状态*/
	function allcb() {
		var checkbox = document.getElementById("selectcb");
		if (checkbox.checked == true) {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = true;
				}
			}

		} else {
			var code_Values = document.getElementsByName("tids");
			for (i = 0; i < code_Values.length; i++) {
				if (code_Values[i].type == "checkbox") {
					code_Values[i].checked = false
				}
			}
		}
	}

	/*删除全部*/
	function deleteAll() {
		document.getElementById("idform").submit();
	}

	/*删除*/
	function del(val) {
		if (confirm("你真的想删除么？")) {
			//var url = "delnotice.do?notice.noticeId=" + val;
			var url = "delProductTypeRecommend.do?productTypeRecommendPojo.id=" + val;
			doOpreator(url, null);
		} else {
			return;
		}

	}
	
	function doOpreator(url, params) {
		MAOWU.ajax.get(url, params, goRefreshPage);
	}

	/*刷新*/
	function goRefreshPage(result) {
		var rand = Math.random() * (100000 + 1);
		if (result =="1") {
			alert("删除成功");//浏览器阻止创建更多的的多画框对话框
			//MAOWU.page.init(<s:property value="page.rowCount"/>,"noticeAllList.do?randIni=" + rand);
			queryData("getProductTypeRecommendCount.do", "productTypeRecommendAllList.do?randquery=" + rand);
		} else {
			alert("删除失败");
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">商家中心管理</a> &gt; <a href="#">热门品牌</a>&gt; <a href="#">添加热门品牌</a>
		</div>
		 <div class="Search_control">
		<p>按条件查找</p>
		<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="goProductTypeRecommend.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					    <td align="right">品牌名：</td>
						<td><input type="text" name="userBrandPojo.brandName" value=""></td>	
					    <td align="right">审核状态：</td>
					    <td><select name="userBrandPojo.status" id="userBrandPojo.status"  class="floatLeft">
							<option value="">----请选择----</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
				    		</select></td>
					</tr>
					
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
				<input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				<div class="Clear"></div>
			</div>
		</form>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<input type="hidden" name="pagePushPojo.type"
				value="${pagePushPojo.type}" class="floatLeft" id="merNamffe">
			<form action="productTypeRecommendDeleteId.do" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
					<tr>
						<!--<th><input type="checkbox" id="selectcb" name="selectcb"
							onclick="allcb()"></th>-->
						<th>品牌名</th>
						<th>品牌logo</th>
						<th>排序</th>
						<th>审核状态</th>
						<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
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
	var ctx = "<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	function query() {
		if (tt.validate()) {
			var rand = Math.random() * (100000 + 1);
			queryData("userBrandListCount.do", "userBrandListAll.do?randquery=" + rand);
		}
	}

	//分页展现页面函数 
	function installPage() {
		$("#body")
				.append(
						<!--"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td><td>"-->
								"<tr><td>"+ this.brandName
								+ "</td><td>"
								+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/businessCenter/brandDic/"+ this.logo + "' height='50px' />"
								+ "</td><td>"
								+ this.sorting
								+ "</td><td>"
								+ this.statusName
								+ "</td><s:if test="#session.role.roleId!=7"><td><a class='edit_btn' href='selectHotBrandRecommend.do?hotBrandRecommendPojo.brandId="+this.id +"'>选取</a></td></s:if></tr>");   
	}

	$(function() {
		//首次要初始化分页
		var rand = Math.random() * (100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"userBrandListAll.do?randIni=" + rand);
		$("#query_btn").click(query);
	});

function setSecond(obj){  
    var val = obj.value;
    $("#second").empty();
    select2(val);
     
}  

function select2(val) {
	var id = "${productTypePojo.id}";
    $.ajax(
    {
        type: "get",
        url: "getProductTypeBySecond.do?productTypePojo.id="+val ,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
        	if(o_msg.length>0){
        	$("#second").append("<option value=''>--请选择--</option>");
        	}
            for (var i = 0; i < o_msg.length; i++) {
            
            	var selStr = "";
            	if(o_msg[i].id == id){
            		selStr = " selected='selected' ";
            	}
                $("#second").append("<option value=" + o_msg[i].id + selStr +">" + o_msg[i].name + "</option>");
            }
        }
    })
};


</script>