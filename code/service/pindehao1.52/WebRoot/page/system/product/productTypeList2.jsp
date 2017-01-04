<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
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



	function del(val)
	{
		//alert(val);
		if(confirm("你真的想删除该记录么?？"))
		{
			//alert(val);
			
			var url = "deleProductType.do?productTypePojo.id="+val;	
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
			alert("删除成功");
			
		
			queryData("getCountProductType.do?pid=${pid}&level=${level}&typeName=${typeName}", "getProductTypeByPid.do?pid=${pid}&level=${level}&typeName=${typeName}&randdelete="+rand);
		}else{
			alert("删除失败");
		}
	}
	
	function check(val)
	{
		//alert(val);
		if(confirm("确认要通过审核吗？"))
		{
			//alert(val);
			
			var url = "checkProductType.do?productTypePojo.id="+val;	
			doOpreator2(url,null);
		}else{
			return ;
		}
		
	}
	function doOpreator2(url,params){
		MAOWU.ajax.get(url, params, goRefreshPage2);
	}
	 
	function goRefreshPage2(result){
		
		var rand=Math.random() * ( 100000 + 1);
		if(result=="1"){
			alert("审核成功");
			queryData("getCountProductType.do?pid=${pid}&level=${level}&typeName=${typeName}", "getProductTypeByPid.do?pid=${pid}&level=${level}&typeName=${typeName}&randdelete="+rand);
		}else{
			alert("审核失败");
		}
	}
	
function deleteAll(){
        $.ajax({
            type: "POST",
            url:"delAllProductTypeById.do",
            data:$('#idform').serialize(),
            success: function(result) {
            if(confirm("确认要删除吗？")){
                if(result==1){
                	alert("删除成功");
                	window.location.reload(); 
                } else{
                	alert("删除失败");
                	window.location.reload(); 
                }
                }else{
					return ;
				}
            },
            error: function(request) {
            	alert("删除过程出错");
            }
        });
}
	</script>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav">${typeName }</div>
    
  <div class="h15"></div>
   <div>
 <a href="goproductTypeAdd2.do?productTypePojo.pid=${pid}&productTypePojo.userId=0&productTypePojo.level=0&typeName=${typeName }" class="Add_btn">新增分类</a>
 <a class="Add_btn" onclick="checkAll()">批量审核</a>
   <a class="delAll_btn" onclick="deleteAll()">批量删除</a>
   <form action="" id="idform" method="post">
   
  	<table width="100%" border="0" class="Info_list_table">
    <tr>
    	<th><input type="checkbox" id="selectcb" name="selectcb" onclick="allcb()"></th>
		<!--<th>所属用户</th>-->
		<th>名称</th>
		<th>分类图片</th>
		<!--<th>是否红色</th>-->
		<th>状态</th>
		<th>排序</th>
		<s:if test="#session.role.roleId!=7"><th>操作</th></s:if>
    </tr>
    <tbody id="body"></tbody>
      
    </table>
    </form>
  </div>
  
</div>
</body>
</html>


	


<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
/* function query() {
	if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("getCountProductType.do?pid=${pid}&level=${level}&typeName=${typeName}", "getProductTypeByPid.do?pid=${pid}&level=${level}&typeName=${typeName}&randdelete="+rand);
	}
} */
+ "</td><td>"
+ "<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/attestation/"+ this.user3cImage + "' height='50px' />"
+ "</td><td>"


	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
	
		$("#body").append(
				"<tr><td><input  name='tids' type='checkbox' value="+this.id +"   /></td>"+
				//"<td>"+ this.userName + "</td>"+
				"<td><a href='productManage.do?productPojo.productTypeId="+this.id+"&isProType=1'>"+ this.name + "</a></td>"+
				"<td>"+"<img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/productType/" + this.image + "' height='50px' />"+"</td>"+
				//"<td>"+ this.isRedName + "</td>"+
				"<td>"+ this.statusName + "</td>"+
				"<td>"+ this.sorting+"</td>"+
				"</td><s:if test="#session.role.roleId!=7"><td><!--<a class='edit_btn' href='goproductTypeAdd.do?productTypePojo.pid="+this.id+"&productTypePojo.userId="+this.userId+"'>添加子类型</a>-->" +
				"<a  class='edit_btn' onclick=check('"+this.id+"')>审核</a><a  class='del_btn' onclick=del('"+this.id+"')>删除</a>"+
				"<a class='edit_btn' href='goFindProductType.do?productTypePojo.id="+this.id +"&pid=${pid}&level=${level}&typeName=${typeName }'>编辑</a></td></s:if></tr>");
	}
	

	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"getProductTypeByPid.do?pid=${pid}&level=${level}&randIni="+rand);
		
		/* $("#query_btn").click(query);
		
		 */
	});
	
	
function checkAll(){
        $.ajax({
            type: "POST",
            url:"checkAllProductTypeById.do",
            data:$('#idform').serialize(),
            success: function(data) {
            if(confirm("确认要通过审核吗？")){
                if(data==1){
                	alert("审核成功");
                	window.location.reload(); 
                } else{
                	alert("审核失败");
                	window.location.reload(); 
                }
                }else{
					return ;
				}
            },
            error: function(request) {
            	alert("审核过程出错");
            }
        });
	}
</script>