$(document).ready(function() {
	//验证的js文件：
	$("#submitId").click(function(){
		//提交表单时，验证所有文本框的方法
		if(tt.validate()){
			//执行提交表单脚本如下:
			document.getElementById("from1").submit();//返回.jsp页面执行提交操作
		}else{
			$("#fullbg,#sending").hide();  
		}
	});
});