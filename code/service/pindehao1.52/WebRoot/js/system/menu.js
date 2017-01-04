$(document).ready(function() {
	
	$("#calendarImg" ).click(function(){
		WdatePicker({el:'userBirthday'});
	});
});


var selectValue ='';//select全局变量MenuId
var selectValueText='';//select全局变量MenuName

var selectSonValue;//select全局变量MenuId
var selectSonValueText;//select全局变量MenuName

$(document).ready(function() {
	$("#deleMenu" ).click(function(){

			if(selectValue==undefined || selectValue==""){
				
				alert("请选择一个菜单");
			}else{
				var msg = "您真的确定要删除吗？";
				if (confirm(msg) == true) {
				var menuForm = document.getElementById("menuForm");
				menuForm.action="deleteMenu.do?id="+selectValue;
				menuForm.submit();
				  }
			}
	});
});




$(document).ready(function() {
	$("#goAddMenu" ).click(function(){
		var menuForm = document.getElementById("menuForm");
		menuForm.action="goAddPage.do?id="+selectValue;
		menuForm.submit();
	});
});

$(document).ready(function() {
	$("#goSonAddMenu" ).click(function(){
		if(selectValue==undefined || selectValue==""){
			alert("请选择主菜单！");
		}else{
		var menuForm = document.getElementById("menuForm");
		menuForm.action="goSonAddPage.do?id="+selectValue;
		menuForm.submit();
		}
	});
});

$(document).ready(function() {
	$("#goSonUpdateMenu" ).click(function(){
		if(selectSonValue==undefined || selectSonValue==""){
			alert("请选择子菜单！");
		}else{
		var menuForm = document.getElementById("menuForm");
		menuForm.action="goSonUpdateMenu.do?id="+selectSonValue;
		
		menuForm.submit();
		}
	});
});

$(document).ready(function() {
	$("#goUpdateMenu" ).click(function(){
		if(selectValue==undefined||selectValue==""){
			
			alert("请选择一个菜单");
		}else{
		var menuForm = document.getElementById("menuForm");
		menuForm.action="findByIdMenu.do?id="+selectValue;
		menuForm.submit();
		}

	});
});




$(document).ready(function() {
	$("#sonSelect" ).click(function(){
		
		selectSonValue = $("#sonSelect" ).val();
		selectSonValueText = $("#sonSelect" ).find("option:selected").text();
		selectValue = $("#sonSelect" ).val();
		selectValueText = $("#sonSelect" ).find("option:selected").text();
	});
});



$(document).ready(function() {
	$("#level" ).click(function(){
		
		selectValue = $("#level" ).val();
		selectValueText = $("#level" ).find("option:selected").text();
		
		
		var url = "findMenuBySon.do";
		var datas = {"level":selectValue};
		$.ajax({
		    type: "post",
		    url: url,
		    data: datas,
		    datatype: "json",
		    success: function (result) {
		    	var obj=document.getElementById('sonSelect');  
		    	obj.options.length=0;  //删除所有选项option   
		    	
		    	var myobj=eval(result.jsonStr);
		    	for(var i=0;i<myobj.length;i++){
		    		
		    		obj.options.add(new Option(myobj[i].menName,myobj[i].menuId)); //这个兼容IE与firefox  
		    	 }
		     }
		}); 

	});
});


$(document).ready(function() {
	$("#menuRoleId" ).change(function(){
		selectValue = $("#menuRoleId" ).val();
		
		
		var url = "getMenuRoleByRoleid.do";
		var datas = {"menuRolePojo.roleId":selectValue};
		$.ajax({
		    type: "post",
		    url: url,
		    data: datas,
		    datatype: "json",
		    success: function (result) {
		    	var obj=document.getElementById('selectedValue');  
		    	
		    	obj.options.length=0;  //删除所有选项option   
		    	
		    	var myobj=eval(result.jsonStr);
		    	
		    	for(var i=0;i<myobj.length;i++){
		    		obj.options.add(new Option(myobj[i].menuName,myobj[i].menuId)); //这个兼容IE与firefox  
		    	 }
		     }
		}); 
	});
});

//菜单角色左移
$(document).ready(function() {
	$("#leftShift" ).click(function(){
		
		if($("#menuRoleId" ).val()=="0"){
			
			alert("请选择角色名称,再进行左移");
		}else{
		var temporary ="";
		
		if(selectValueText == undefined||selectValueText =="" ){
			alert("请选择一个菜单，再进行左移!");
			
		}else{
			var obj=document.getElementById('selectedValue');
			
			if(obj.options.length==0){
				obj.options.add(new Option(selectValueText,selectValue)); //这个兼容IE与firefox  
			}else{
				
				var states ="0";
				for ( var i = 0; i < obj.options.length; i++) {
					
					if(obj.options[i].value==selectValue){
						states="1";
						alert(selectValueText+"菜单已经添加，不能重复添加。");
						break; 
					}else{
						states="0";
						temporary +=obj.options[i].value+",";
					}
				}
				if(states==0){
				obj.options.add(new Option(selectValueText,selectValue)); //这个兼容IE与firefox  
				}
			}
			
		}
		}
	});
});


/**
 * 双击右移
 */
function dblclickRemoveOne(){

	
	var obj=document.getElementById('selectedValue');  
	var index=obj.selectedIndex;  
	if(index==-1){
		
		alert("请选择一个已选菜单，再进行右移！");
		
	}else{
		obj.options.remove(index);  
	}

	
}

/**
 * 双击左移
 */
function dblclickLeftShift(){
	
	if($("#menuRoleId" ).val()=="0"){
		
		alert("请选择角色名称,再进行左移");
	}else{
	
	var temporary ="";
	
	if(selectValueText == undefined || selectValueText ==''){
		alert("请选择一个菜单，再进行左移!");
		
	}else{
		var obj=document.getElementById('selectedValue');
		
		if(obj.options.length==0){
			obj.options.add(new Option(selectValueText,selectValue)); //这个兼容IE与firefox  
		}else{
			
			var states ="0";
			for ( var i = 0; i < obj.options.length; i++) {
				
				if(obj.options[i].value==selectValue){
					states="1";
					alert(selectValueText+"菜单已经添加，不能重复添加。");
					break; 
				}else{
					states="0";
					temporary +=obj.options[i].value+",";
				}
			}
			if(states==0){
			obj.options.add(new Option(selectValueText,selectValue)); //这个兼容IE与firefox  
			}
		}
		
	}

	}
}

$(document).ready(function() {
	$("#delemenuRole" ).click(function(){
		
		
		var obj=document.getElementById('selectedValue');  
		var index=obj.selectedIndex;  
		if(index==-1){
			
			alert("请选择一个已选菜单，再进行删除！");
		}else{
			if (confirm("您真的确定要删除吗？") == true) {
			window.location.href="deleMenuRole.do?menuRolePojo.menuId="+$("#selectedValue" ).val()+"&menuRolePojo.roleId="+$("#menuRoleId" ).val();
			}
		}
	});
});




//菜单角色保存
$(document).ready(function() {
	$("#menuRoleFormSubmit" ).click(function(){
		
		if($("#menuRoleId" ).val()=="0"){
			
			alert("请选择角色名称！");
		}else{
			
		var obj=document.getElementById('selectedValue');
		var selectdMenuRole = document.getElementById("menuRolePojo.menuIds");
		selectdMenuRole.value="";
		
		for ( var i = 0; i < obj.options.length; i++) {
			selectdMenuRole.value +=obj.options[i].value+",";
		}
		
		document.getElementById("menuRoleForm").submit();
		}
	});
});

//菜单角色右移
$(document).ready(function() {
	$("#removeOne" ).click(function(){
		
		var obj=document.getElementById('selectedValue');  
		var index=obj.selectedIndex;  
		if(index==-1){
			
			alert("请选择一个已选菜单，再进行右移！");
			
		}else{
			obj.options.remove(index);  
		}
	});
});

function deleAtta(){
		if (confirm("您真的确定要删除吗？")) {
			return true;
		//window.location.href="deleAtta.do?attachmentPojo.attaId="+attaId;
		}
		return false;
	
}




$(document).ready(function() {
	$("#returnPage" ).click(function(){
		history.go(-1);

	});
});




$(document).ready(function() {
	$("#MyFormSubmit" ).click(function(){
		if(tt.validate()){
		document.getElementById("MyForm").submit();
		}
	});
});

$(document).ready(function() {
	$("#queryFormSubmit" ).click(function(){
		document.getElementById("queryForm").submit();
	});
});

$(document).ready(function() {
	$("#queryAttaSubmit" ).click(function(){
		document.getElementById("queryAttaForm").submit();
	});
});




