<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/sys_util_web.js"></script>
</script>
<script type="text/javascript">
function allcb(){
	var checkbox=document.getElementById("selectcb"); 
	if(checkbox.checked==true){
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = true; 
		} 
		} 	 
	}else{	
		var code_Values = document.getElementsByName("tids") ; 
		for(i = 0;i < code_Values.length;i++){ 
		if(code_Values[i].type == "checkbox") 
		{ 
		code_Values[i].checked = false		
		} 
		} 
	}
}
function delAll(){
		$("#idform").attr("action","delQiNiuAll.do").submit();
	}
function del(val){
	if(confirm("确认要删除此条吗？")){
			var url = "delQiNiu.do?key="+val;	
			window.location.href=url;
		}else{
			return ;
		}
	}
</script>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">七牛文件管理</a>
		</div>
		<div class="Search_control">
			<p>按条件查找</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
		<form action="navigationList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">文件名：</td>
						<td><label><input type="text" name="filename"
								value=""></label></td>
					</tr>					    
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					   <input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				       <div class="Clear"></div>
			    </div>
		</form>
		<!-- 查询结束 -->
		<div class="h15"></div>
		<div>
			<a class="Add_btn" href="#" onclick="openupload()">文件上传</a>
			<a class="delAll_btn" onclick="delAll()">批量删除</a>
			<form action="" id="idform" method="post">
				<table width="100%" border="0" class="Info_list_table">
							<tr>
								<th><input type="checkbox" id="selectcb" name="selectcb"
									onclick="allcb()"></th>
								<th>文件名</th>
								<th>文件类型</th>
								<th>文件大小</th>
								<th>文件URL</th>
								<th>最后更新时间</th>
								<th>操作</th>
							</tr>
					<tbody id="body"></tbody>
				</table>
			</form>
			<div class="page">
				<div class="floatleft">
					总共 <i id="rowcount">${page.rowCount}</i>条/<i id="pagecount"></i>页
				</div>
				<div style="float: right" id="Pagination" class="pagination"></div>
				<div class="Clear"></div>
			</div>
		</div>
	</div>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/qiniu_css/global.min.css" />
	<div class="modal-background js-modal-20960136922076344 upload resource-index" style="display: none; height: 969px; opacity: 1;" id="uploadbox">
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
                <button class="btn right js-modal-close" onclick="closeupload()">关闭</button>
            </p>
    </div>
	        <i class="icon-close js-modal-close" style="background-image: url(<%=request.getContextPath()%>/image/qiniu_img/sprite.png);" onclick="closeupload()"></i>
	    </div>
		<div class="modal-bg"></div>
    </div>
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
</body>
</html>

<script type="text/javascript">
var ctx = "<s:property value="ctx" />";
var pagecount = "${page.rowCount}";
var pageSize = 10;

function query() {
	if (tt.validate()) {
		var rand = Math.random() * (100000 + 1);
		queryData("qiniuFileCount.do", "qiniuFileList.do?randquery=" + rand);
	}
}

/**
 *分页展现页面函数 
 **/
function installPage() {
	$("#body")
			.append(
					"<tr>"+
                        "<td><input  name='tids' type='checkbox' value='"+this.key +"' /></td>"+
                        "<td>"+this.key+"</td>"+
                        "<td>"+this.mimeType+"</td>"+
                        "<td>"+this.fsizeStr+"M</td>"+
                        "<td><a target='_blank' href="+this.fileURL+">"+this.fileURL+"</a></td>"+
                        "<td>"+this.putTimeStr+"</td>"+
                        "<td><a class='del_btn' onclick=del('"+this.key+"')>删除</a></td>"+
                    "</tr>"
                    );
}

$(function() {
	/**
	  *首次要初始化分页
	 **/
	var rand = Math.random() * (100000 + 1);
	MAOWU.page.init(<s:property value="page.rowCount"/>,
			"qiniuFileList.do?randIni=" + rand,pageSize);
	$("#query_btn").click(query);
	
});
</script>