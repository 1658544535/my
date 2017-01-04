$(document).ready(function() {
	
	$("#accDateImgs" ).click(function(){
		WdatePicker({el:'accDate'});
	});	
	$("#settDateImgs" ).click(function(){
		WdatePicker({el:'settDate'});
	});
	

	$("#settDateBeginImgs" ).click(function(){
		WdatePicker({el:'settDateBegin'});
	});
	

	$("#settDateEndImgs" ).click(function(){
		WdatePicker({el:'settDateEnd'});
	});
	
	$("#submitId").click(function(){
		$("#accReduceMoney_mgId").html("");
		if($("#accReduceMoney").val() != ""){
			if($("#accReduceMoney").val()-$("#accMoney").val()>0){
				$("#fullbg,#sending").hide();  
				$("#accReduceMoney_mgId").html("请小于对账金额");
				return;
			}
		}
		if(tt.validate()){
			document.getElementById("from1").submit();
		}else{
			$("#fullbg,#sending").hide();  
		}
	});
});
