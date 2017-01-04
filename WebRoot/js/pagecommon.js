/**
 * 复选框全选/取消全选
**/
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids"); 
		for(i = 0;i < code_Values.length;i++) { 
			if(code_Values[i].type == "checkbox") { 
				code_Values[i].checked = true; 
			} 
		}
	} else {	
		var code_Values = document.getElementsByName("tids"); 
		for(i = 0;i < code_Values.length;i++) { 
			if(code_Values[i].type == "checkbox") { 
				code_Values[i].checked = false;
			}
		}
	}
}
/**
 * 审核选中
**/
function checkAll(url){
	if(confirm("确认要审核通过吗？")){
		var formParam = $("#idform").serialize();
		MAOWU.ajax.get(url, formParam, checkRefreshPage);
	}else{
		return ;
	}
}
/**
 * 取消审核选中
**/
function uncheckAll(url){
	if(confirm("确认要取消审核吗？")){
		var formParam = $("#idform").serialize();
		MAOWU.ajax.get(url, formParam, uncheckRefreshPage);
	}else{
		return ;
	}
}
/**
 * 删除选中
**/
function deleteAll(url){
	if(confirm("确定要删除该记录吗？")){
		var formParam = $("#idform").serialize();
		MAOWU.ajax.get(url, formParam, delRefreshPage);
	}else{
		return ;
	}
}
/**
 * 删除某条
**/
function del(url){
	if(confirm("确定要删除该记录吗？")){
		MAOWU.ajax.get(url, null, delRefreshPage);
	}else{
		return ;
	}
}
/**
 * 删除后刷新
**/
function delRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("删除成功");
		query();
	} else if(result=="2"){
		alert("部分删除失败");
		query();
	} else{
		alert("删除失败");
	}
}
/**
 * 审核通过某条
**/
function check(url){
	if(confirm("确认要审核通过吗？")){
		MAOWU.ajax.get(url, null, checkRefreshPage);
	}else{
		return ;
	}
}
/**
 * 审核通过后刷新
**/
function checkRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("审核成功");
		query();
	} else if(result=="2"){
		alert("部分审核失败");
		query();
	} else{
		alert("审核失败");
	}
}
/**
 * 取消审核某条
**/
function uncheck(url){
	if(confirm("确认要取消审核吗？")){
		MAOWU.ajax.get(url, null, uncheckRefreshPage);
	}else{
		return ;
	}
}
/**
 * 取消审核后刷新
**/
function uncheckRefreshPage(result){
	var rand=Math.random() * ( 100000 + 1);
	if(result=="1"){
		alert("取消审核成功");
		query();
	} else if(result=="2"){
		alert("部分取消审核失败");
		query();
	} else{
		alert("取消审核失败");
	}
}