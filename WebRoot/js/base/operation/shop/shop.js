

$(document).ready(function() {
	
	$("#calendarImgs" ).click(function(){
		WdatePicker({el:'shopAddDate'});
	});
});



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


function deleteAll(){
	if (confirm("您真的确定要删除选中项吗？") == true) {
		
		document.getElementById("idform").submit();
	}
	
	
}

var selectValue="";
var selectValueText="";



$(document).ready(function() {
	$("#deleShopBusi" ).click(function(){
		if($("#selectedValue" ).val()==undefined){
			alert("请选择项！");
		}else{
		if (confirm("您真的确定要删除吗？") == true) {
			
		window.location.href="deleByShopBusiId.do?shopBusi.shopBusiId="+$("#selectedValue" ).val()+"&shopBusi.shopId="+$("#shopId" ).val();
		}
		}
	});
});


//门店业务保存
$(document).ready(function() {
	$("#shopBusiFormSubmit" ).click(function(){
		
		var obj=document.getElementById('selectedValue');
		var selectdMenuRole = document.getElementById("shopBusi.shopBusiId");
		selectdMenuRole.value="";
		for ( var i = 0; i < obj.options.length; i++) {
			selectdMenuRole.value +=obj.options[i].value+",";
		}
		document.getElementById("shopBusiForm").submit();
		
	});
});


$(document).ready(function() {
	$("#menuLevel" ).click(function(){
		
		selectValue = $("#menuLevel" ).val();
		selectValueText = $("#menuLevel" ).find("option:selected").text();
	});
});


function dblclickLeftShift(){
	

	var temporary ="";
	
	if(selectValueText == undefined){
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
//菜单角色左移
$(document).ready(function() {
	$("#leftShift" ).click(function(){
		
		dblclickLeftShift();
		
		/*var temporary ="";
		
		if(selectValueText == undefined){
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
			
		}*/
	
	
	});
});




function dblclickRemoveOne(){

	
	var obj=document.getElementById('selectedValue');  
	var index=obj.selectedIndex;  
	if(index==-1){
		
		alert("请选择一个已选菜单，再进行右移！");
		
	}else{
		obj.options.remove(index);  
	}

	
}



//菜单角色右移
$(document).ready(function() {
	$("#removeOne" ).click(function(){
		dblclickRemoveOne();
		/*var obj=document.getElementById('selectedValue');  
		var index=obj.selectedIndex;  
		if(index==-1){
			
			alert("请选择一个已选菜单，再进行右移！");
			
		}else{
			obj.options.remove(index);  
		}*/
	});
});


$(document).ready(function() {
	
	$("#submitShop" ).click(function(){
		if(tt.validate()){
		var shopId = $("#shopid" ).val();
		var shopAdminId =$("#shopAdminId").val();
		
		var url = "findShopById.do";
		var datas = {"shop.shopid":shopId,"shop.shopAdminId":shopAdminId};
		$.ajax({
		    type: "post",
		    url: url,
		    data: datas,
		    datatype: "json",
		    success: function (result) {
		    	if(result==1){
		    		alert("门店ID已存在，请重新输入！");
		    		
		    	}
		    	if(result==2){
		    		alert("门店店长已存在，请重新选择！");
		    	}
		    	if(result==0){
		    		document.getElementById("addShopForm").submit();
		    	}
		    	
		     }
		}); 
	  }
	});
});




$(document).ready(function() {
	$("#updateSubmitShop" ).click(function(){
		    		document.getElementById("updateShopForm").submit();
		    		

	});
});





$(document).ready(function() {
	
	$("#shopType" ).change(function(){
		
		
		var divName = document.getElementById("divName");
		if($("#shopType").val()=="20130812151516516"){
			divName.innerHTML ="<input disabled='disabled' name='shop.shopLevel' id='shopLevel' value='0'/><input  type='hidden' name='shop.shopLevel'  value='0'/>";
			

			var url2 = "findFlagshipShopAdminId.do";
			var datas2 = {"shop.shopType":""};
			$.ajax({
			    type: "post",
			    url: url2,
			    data: datas2,
			    datatype: "json",
			    success: function (result2) {
			    	var myobj=eval(result2);
			    	var obj=document.getElementById('shopAdminId');  
			    	obj.options.length=0;  //删除所有选项option  
			    	
			    	for(var i=0;i<myobj.length;i++){
			    		
			    		obj.options.add(new Option(myobj[i].userName,myobj[i].userId)); //这个兼容IE与firefox  
			    	 }
			     }
			}); 
			
		}else{
			
			divName.innerHTML ="<select name='shop.shopLevel' id='shopLevel'></select> ";
			
			var url = "findShopByShopType.do";
			var datas = {"shop.shopType":"20130812151516516"};
			$.ajax({
			    type: "post",
			    url: url,
			    data: datas,
			    datatype: "json",
			    success: function (result) {
			    	var myobj=eval(result);
			    	
			    	var obj=document.getElementById('shopLevel');  
			    	obj.options.length=0;  //删除所有选项option  
			    	
			    	for(var i=0;i<myobj.length;i++){
			    		
			    		obj.options.add(new Option(myobj[i].shopName,myobj[i].shopId)); //这个兼容IE与firefox  
			    	 }
			     }
			}); 
			
			
			var url2 = "findFactoryShopAdminId.do";
			var datas2 = {"shop.shopType":""};
			$.ajax({
			    type: "post",
			    url: url2,
			    data: datas2,
			    datatype: "json",
			    success: function (result2) {
			    	var myobj=eval(result2);
			    	var obj=document.getElementById('shopAdminId');  
			    	obj.options.length=0;  //删除所有选项option  
			    	
			    	for(var i=0;i<myobj.length;i++){
			    		
			    		obj.options.add(new Option(myobj[i].userName,myobj[i].userId)); //这个兼容IE与firefox  
			    	 }
			     }
			}); 
			
			
			
			
			
		}
		
	});
});

function goShopBusi(shopId){
	window.open("getShopByShopBusi.do?shopBusi.shopId="+shopId, "",'width=800px,height=600px;');
	//window.showModalDialog("getShopByShopBusi.do?shopBusi.shopId="+shopId, window,'dialogWidth=800px;dialogHeight=600px;');
	
}

function goShopInd(shopId){
	window.open("findShopByInd.do?shopInd.shopId="+shopId, "",'width=800px,height=600px;');
	//window.showModalDialog("findShopByInd.do?shopInd.shopId="+shopId, window,'dialogWidth=800px;dialogHeight=600px;');
	
}


$(document).ready(function() {
	$("#auditPass" ).click(function(){
		document.getElementById("auditShopForm").submit();
	
//	window.location="auditPassShop.do";
	});
});




function deleAtta(){
	if (confirm("您真的确定要删除吗？")) {
		return true;
	}
	return false;

}

$(document).ready(function() {
	$("#returnPage" ).click(function(){
		history.go(-1);

	});
});


$(document).ready(function() {
	$("#updateShopSubmit" ).click(function(){
		if(tt.validate()){
			var shopAdminId =$("#shopAdminId").val();
			
			var temporaryShopAdminId =$("#temporaryShopAdminId").val();
			
			if(temporaryShopAdminId ==shopAdminId){
				document.getElementById("updateShopForm").submit();
			}else{
				
				var url = "findShopTestShopAdmin.do";
				var datas = {"shop.shopAdminId":shopAdminId};
				$.ajax({
				    type: "post",
				    url: url,
				    data: datas,
				    datatype: "json",
				    success: function (result) {
				    	if(result==2){
				    		alert("门店店长已存在，请重新选择！");
				    		
				    	}
				    	if(result==0){
				    		document.getElementById("updateShopForm").submit();
				    	}
				      }
					}); 
				
			}
			
		
		}
	});
});



$(document).ready(function() {
	$("#MyFormSubmit" ).click(function(){
		document.getElementById("MyForm").submit();

	});
});

$(document).ready(function() {
	
	$("#MyShopEquiForm" ).click(function(){
		if(tt.validate()){
		document.getElementById("MyForm").submit();
		}
	});
});

$(document).ready(function() {
	$("#queryShopFormSubmit" ).click(function(){
		document.getElementById("queryShopForm").submit();

	});
});






