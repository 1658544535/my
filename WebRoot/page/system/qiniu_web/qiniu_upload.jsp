<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/css/qiniu_css/global.min.css" />
</head>
<body>
<div class="modal-background js-modal-20960136922076344 upload resource-index" style="display: block; height: 969px; opacity: 1;" id="uploadbox">
		<div class="modal-box" style="margin-top: 100px;">
	        <div class="box-head">文件上传</div>
	        <div class="box-body">
      
      <form method="post" action="qiniuUploadSubmit.do" enctype="multipart/form-data" id="upload">
      <div class="file-upload" id="container" style="position: relative;">
        <!-- <div class="file-prefix-setting js-file-prefix-setting" id="filenames">
	        <br /><br /><br /><br /><br />
	        <h2 align="right">
	        单个上传：
	        <br /><br /><br /><br /><br />
	        批量上传：
	        <h2>
        </div> -->
        <div class="file-prefix-setting js-file-prefix-setting">
	        <textarea cols="" rows="" readonly="readonly" style="height: 273px; width: 193px;" id="filenames">
	        
	        </textarea>
        </div>
        <div class="file-upload-area">
        	<br /><br /><br />
	        <h2 align="left" style="display: none;">单个上传：<br /><br /></h2>
	        <input accept="audio/mp4,video/mp4" class="btn left bg-btn" style="position: relative; z-index: 1; left: 50px; display: none;" type="file" name="file" id="file" onchange="change(this)" /><br /><br />
	        <br /><br /><br />
	        <h2 align="left" style="display: none;">批量上传：<br /><br /></h2>
	        <input multiple="multiple" accept="audio/mp4,video/mp4" class="btn left bg-btn" style="position: relative; z-index: 1; left: 50px;" type="file" name="files" id="files" onchange="change()" /><br /><br />
        </div>
        <div class="file-upload-btn">
          <span class="select-file">
            <a class="btn left bg-btn
            
            " id="subbtn" href="#" style="position: relative; z-index: 1;" onclick="chick()">上传文件</a>
            <!-- <input class="btn left bg-btn" style="position: relative; z-index: 1;" type="button" id="subbtn" onclick="chick()" value="上传文件" /> -->
          </span>
        </div>
      <div id="html5_1aehbgbhg2gg1d5tmfpdp51q563_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 340px; left: 298px; width: 130px; height: 35px; overflow: hidden; z-index: 0;"><input id="html5_1aehbgbhg2gg1d5tmfpdp51q563" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept=""></div>
      </div>
      </form>
    </div>
	        <div class="box-foot">
      <p class="btn-line">
                <span> 注意：上传的文件必须小于60M，只能为MP4类型，名称不能有中文！<!-- (只能选择一种上传方式，默认选择单个上传！) --></span>
                <button id="btnCancel" class="btn cancel-all" type="button" disabled="disabled">全部取消</button>
                <!-- <button class="btn right js-modal-close" onclick="closeupload()">关闭</button> -->
            </p>
    </div>
	        <%-- <i class="icon-close js-modal-close" style="background-image: url(<%=request.getContextPath()%>/image/qiniu_img/sprite.png);" onclick="closeupload()"></i> --%>
	    </div>
		<div class="modal-bg"></div>
    </div>
</body>
</html>
<script type="text/javascript">
function change() {
	//$("#filenames").html("");
	$("#filenames").val("");
	var file = $("#file").val();
	var files = $("#files").val();
	var r = /[0-9A-z_-]+\.mp4$/;//^\_\-
	if (file != "") {
		if (/* file.indexOf('.mp4') > -1 &&  */r.test(file)) {
			$('#subbtn').removeAttr("disabled");
			$('#subbtn').attr('onclick',"chick()");
			
			var fileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");
			var fileExt = file.replace(/.+\./,"");
			//$("#filenames").append(fileName + "." + fileExt + "<br />");
			$("#filenames").val(" ( 1 ) " + fileName + "." + fileExt);
		} else {
			$('#subbtn').attr('disabled',"disabled");
			$('#subbtn').removeAttr("onclick");
			alert("上传的文件只能为MP4类型，名称不能有中文！(数字和字母的组合)");
		}
	} else if (files != "") {
		var n = 0;
		if (files.indexOf('.mp4') > -1) {
			$('#subbtn').removeAttr("disabled");
			$('#subbtn').attr('onclick',"chick()");
			
			var fileinfo = $('#files').prop("files");
			//var filenames = [];
			var filenamestr = "",i = 1;
			$.each(fileinfo,function(k, v){
				//$("#filenames").append(v['name'] + "<br />");
				//filenames.push(v['name'] + "\n");
				filenamestr += " ( " + i + " ) " + v['name'] + "\n";
				i++;
				
				if (!r.test(v['name'])) {
					n++;
				}
			});
			//$("#filenames").val(filenames);
			$("#filenames").val(filenamestr);
			
			/* for(var i = 0;i < files.length;i++){
				
			} */
		} else {
			$('#subbtn').attr('disabled',"disabled");
			$('#subbtn').removeAttr("onclick");
			alert("上传的文件只能为MP4类型，名称不能有中文！(数字和字母的组合)");
		}
		
		if (n != 0) {
			$('#subbtn').attr('disabled',"disabled");
			$('#subbtn').removeAttr("onclick");
			alert("上传的文件只能为MP4类型，名称不能有中文！(数字和字母的组合)");
		} 
	} else {
		
	}
}
function chick() {
	var file = $("#file").val();
	var files = $("#files").val();
	if (file != "" || files != ""){
		$('#upload').submit();
	} else {
		alert("请选择要上传的文件！");
	}
}
function closeupload(){
	$("#uploadbox").css('display','none');
}
function openupload(){
	$("#uploadbox").css('display','block');
}
</script>