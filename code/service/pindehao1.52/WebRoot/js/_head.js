document.write('<link rel="icon" href="images/taozhuma.ico" type="image/x-icon" />');

$('#myMessage').each(function() {
	$.ajax({
		url : 'InfoCount1.do',
		type : 'post',
		dataType : 'json',
		async : 'false',
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载失败!!!!!");
		},
		success : function(result) {
			var count = result;
			$('#myMessage').html("消息 " + count);
		}
	});

});
