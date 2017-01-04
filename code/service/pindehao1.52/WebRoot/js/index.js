$(document).ready(function() {

	$("#accordion").accordion({
		collapsible : true,
		active : 0,
		heightStyle: "fill"
	});
	
	$(".menu-url" ).click(function(){
		$("#mainiframe").attr("src",$(this).attr("href")); 
		return false;
	});
});