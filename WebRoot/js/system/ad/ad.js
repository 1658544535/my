$(document).ready(function() {
	
	$("#adStartDateImgs" ).click(function(){
		WdatePicker({el:'adStartDate'});
	});	
	$("#adEndDateImgs" ).click(function(){
		WdatePicker({el:'adEndDate'});
	});
	$("#inquiryDateImgs" ).click(function(){
		WdatePicker({el:'inquiryDate'});
	});
	
	
	$("#submitId").click(function(){
		if(tt.validate()){
			document.getElementById("from1").submit();
		}else{
			$("#fullbg,#sending").hide();  
		}
	});
});
