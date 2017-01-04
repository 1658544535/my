$(document).ready(function() {
	
	$("#calendarImg" ).click(function(){
		WdatePicker({el:'userBirthday'});
	});
	
	$("#query" ).click(function(){
		$("#dateform" ).submit();
	});
	
	$("#queryCalendarImg" ).click(function(){
		WdatePicker({el:'reqTime'});
	});
	
	$("#submitId").click(function(){
		 var bh = 1119;  
		    var bw = 1119;  
		    $("#fullbg").css({  
		    height:bh,  
		    width:bw,  
		    display:"block"  
		    });  
		    $("#sending").show();  
	});
	
	$("input[type='button']").click(function(){
		 var bh = 1119;  
		    var bw = 1119;  
		    $("#fullbg").css({  
		    height:bh,  
		    width:bw,  
		    display:"block"  
		    });  
		    $("#sending").show();  
	});
});




