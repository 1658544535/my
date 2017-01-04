
$(document).ready(function() {
	
	$("#calendarImgs" ).click(function(){
		WdatePicker({el:'seStartDate'});
	});
});

$(document).ready(function() {
	
	$("#calendarImgss" ).click(function(){
		WdatePicker({el:'seEndDate'});
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
	$("#MyFormSubmit").click(function(){
		if(tt.validate()){
			var seName = $("#seName" ).val();			
			var thisseName =$("#thisseName" ).val();
			if(thisseName!=seName){					
			var url = "findSeparateBySeName.do";
			var datas = {"separatePojo.seName":seName};
			$.ajax({
				    type: "post",
				    url: url,
				    data: datas,
				    datatype: "json",				    
				    success: function (result) {
				    	if(result==1){
				    		alert("分成名称已存在，请重新输入！");

				    	}else{
				    			testDate();
				    		
				    		
				    		//window.showModalDialog("goAddattachment.do?busiId="+shopId+"&attaBusiType="+type, window,'dialogWidth=800px;dialogHeight=600px;');
				    		
				    	}
				     }
				}); 

			}else{
				testDate();
			}
		}
	});
	
});

function testDate(){
	var seId = $("#seId" ).val();
	var seType = $("#seType" ).val();
	var seStartInt = $("#seStartInt" ).val();
	var seEndInt = $("#seEndInt" ).val();
	var seStartDate = $("#seStartDate" ).val();
	var seEndDate = $("#seEndDate" ).val();
	var urls = "findSeparateBySeTpye.do";
	var datass = {"separatePojo.seId":seId,"separatePojo.seType":seType,"separatePojo.seStartInt":seStartInt,"separatePojo.seEndInt":seEndInt,"separatePojo.seStartDate":seStartDate,"separatePojo.seEndDate":seEndDate};
		$.ajax({
		    type: "post",
		    url: urls,
		    data: datass,
		    datatype: "json",
		    success: function (result) {
		    	if(result==1){
		    		alert("此段时间内区间已重复，请重新输入区间或时间！");
		    	}else{
		    		document.getElementById("MyForm").submit();
		    		//window.showModalDialog("goAddattachment.do?busiId="+shopId+"&attaBusiType="+type, window,'dialogWidth=800px;dialogHeight=600px;');
		    		
		    	}
		     }
		}); 
	
}







