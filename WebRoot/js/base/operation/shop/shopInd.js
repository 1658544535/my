



var selectValue="";
var selectValueText="";



$(document).ready(function() {
	$("#deleShopBusi" ).click(function(){
		if($("#selectedValue" ).val()==undefined){
			alert("请选择项！");
		}else{
		if (confirm("您真的确定要删除吗？") == true) {
			
		window.location.href="deleShopInd.do?shopInd.shopIndId="+$("#selectedValue" ).val()+"&shopInd.shopId="+$("#shopId" ).val();
		}
		}
	});
});


//门店业务保存
$(document).ready(function() {
	$("#shopIndFormSubmit" ).click(function(){
		var obj=document.getElementById('selectedValue');
		var selectdMenuRole = document.getElementById("shopInd.shopIndId");
		
		selectdMenuRole.value="";
		for ( var i = 0; i < obj.options.length; i++) {
			selectdMenuRole.value +=obj.options[i].value+",";
		}
		document.getElementById("shopIndForm").submit();
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
		alert("请选择一个行业，再进行左移!");
		
	}else{
		var obj=document.getElementById('selectedValue');
		
		if(obj.options.length==0){
			obj.options.add(new Option(selectValueText,selectValue)); //这个兼容IE与firefox  
		}else{
			
			var states ="0";
			for ( var i = 0; i < obj.options.length; i++) {
				
				if(obj.options[i].value==selectValue){
					states="1";
					alert(selectValueText+"行业已经添加，不能重复添加。");
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
			alert("请选择一个行业，再进行左移!");
			
		}else{
			var obj=document.getElementById('selectedValue');
			
			if(obj.options.length==0){
				obj.options.add(new Option(selectValueText,selectValue)); //这个兼容IE与firefox  
			}else{
				
				var states ="0";
				for ( var i = 0; i < obj.options.length; i++) {
					
					if(obj.options[i].value==selectValue){
						states="1";
						alert(selectValueText+"行业已经添加，不能重复添加。");
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
		*/
	});
});


function dblclickRemoveOne(){

	
	var obj=document.getElementById('selectedValue');  
	var index=obj.selectedIndex;  
	if(index==-1){
		
		alert("请选择一个已选行业，再进行右移！");
		
	}else{
		obj.options.remove(index);  
	}
	
}

//右移
$(document).ready(function() {
	$("#removeOne" ).click(function(){
		dblclickRemoveOne();
		
		/*var obj=document.getElementById('selectedValue');  
		var index=obj.selectedIndex;  
		if(index==-1){
			
			alert("请选择一个已选行业，再进行右移！");
			
		}else{
			obj.options.remove(index);  
		}*/
	});
});












function goShopByEquiPment(equiId){
	
	window.showModalDialog("findShopByEquiPment.do?shopEqui.equiId="+equiId, window,'dialogWidth=800px;dialogHeight=600px;');
	
}

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
	$("#MyFormSubmit" ).click(function(){
		if(tt.validate()){
		document.getElementById("MyForm").submit();
		}
	});
});


$(document).ready(function() {
	$("#MyFormQuerySubmit" ).click(function(){
		document.getElementById("MyForm").submit();
	});
});






