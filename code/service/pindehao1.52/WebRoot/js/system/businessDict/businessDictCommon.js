$(document).ready(function() {
	
	$("#submitId").click(function(){
		
		if(tt.validate()){
			document.getElementById("from1").submit();
		}else{
			$("#fullbg,#sending").hide();  
		}
	});
});