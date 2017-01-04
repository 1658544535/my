<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
</head>
<body>
	<div class="sub_wrap">
		<div class="s_nav">
			<a href="#">版本控制表管理</a> &gt; <a href="#">编辑</a>
		</div>
		<div class="h15"></div>
		                                               <!--  ---------IOS版本控制------------   -->
		<div>
			<form action="updateVersionControl.do" method="post" id="from1" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" style="margin-bottom:20px;"> 
					<input type="hidden" name="versionControlPojo.id" id="versionControlPojo.id" value="${versionControlPojo.id}" class="inputText" />
					<tr>
						<td align="center" class="grey" width="15%"  colspan="3">IOS版本更新管理</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">最新用户版本：</td>
						<td><input type="text" name="versionControlPojo.lastVersion" id="versionControlPojo.lastVersion" value="${versionControlPojo.lastVersion}" align="left"/></td>
						<td><span id="lastVersion_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">强制更新版本：</td>
						<td><input type="text" name="versionControlPojo.forceVersion" id="versionControlPojo.forceVersion" value="${versionControlPojo.forceVersion}" /></td>
						<td><span id="forceVersion_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">是否开启更新：</td>
						<td><select id="age" name="versionControlPojo.state" class="floatLeft" onchange="setSecond(this)">
					        <option value="0" <s:if test="versionControlPojo.state==0">selected="selected"</s:if>>否</option>
					        <option value="1" <s:if test="versionControlPojo.state==1">selected="selected"</s:if>>是</option> 
							</select></td>
						<td><span id="state_mgId"></span></td>	
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">更新语：</td>
						<td><textarea class="floatLeft" name="versionControlPojo.sketch" id="sketch" style="width: 400px;height: 100px;">${versionControlPojo.sketch}</textarea>	</td>
					    <td><span id="sketch_mgId"></span></td>
					</tr>
				</table> 
			</form>
		</div>
		<div class="Btn_div" style="margin-bottom:100px;">
			<input type="button"  class="ok_btn" value="提交" id="sbutton"/>
		</div>
		
		                                            <!--  ---------Android版本控制------------   -->
		<div>
			<form action="updateVersionControl.do" method="post" id="from2" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" style="margin-bottom:20px;"> 
					<input type="hidden" name="versionControlPojo2.id" id="versionControlPojo2.id" value="${versionControlPojo2.id}" class="inputText" />
					<tr>
						<td align="center" class="grey" width="15%"  colspan="3">Android版本更新管理</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">最新用户版本：</td>
						<td><input type="text" name="versionControlPojo2.lastVersion" id="versionControlPojo2.lastVersion" value="${versionControlPojo2.lastVersion}" /></td>
					    <td><span id="lastVersion2_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">强制更新版本：</td>
						<td><input type="text" name="versionControlPojo2.forceVersion" id="versionControlPojo2.forceVersion" value="${versionControlPojo2.forceVersion}" /></td>
					    <td><span id="forceVersion2_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">是否开启更新：</td>
						<td><select id="age" name="versionControlPojo2.state" class="floatLeft" onchange="setSecond(this)">
					        <option value="0" <s:if test="versionControlPojo2.state==0">selected="selected"</s:if>>否</option>
					        <option value="1" <s:if test="versionControlPojo2.state==1">selected="selected"</s:if>>是</option> 
							</select></td>
					    <td><span id="state2_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">更新语：</td>
						<td><textarea class="floatLeft" name="versionControlPojo2.sketch" id="sketch" style="width: 400px;height: 100px;">${versionControlPojo2.sketch}</textarea>	</td>
					    <td><span id="sketch2_mgId"></span></td>
					</tr>
				</table> 
			</form>
		 </div>
		<div class="Btn_div">
			<input type="button"  class="ok_btn" value="提交" id="sbutton2"/>
		</div>
		
		
			                                            <!--  ---------开关控制------------   -->
		<div>
			<form action="updateVersionControl.do" method="post" id="from2" enctype="multipart/form-data">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table" style="margin-bottom:20px;"> 
					<input type="hidden" name="versionControlPojo2.id" id="versionControlPojo2.id" value="${versionControlPojo2.id}" class="inputText" />
					<tr>
						<td align="center" class="grey" width="15%"  colspan="3">开关控制</td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">极验开关：</td>
						<td>
						<s:if test="#openGeekValid==0">
						<label>已关闭</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button"  value="开启" onclick="geekValid(1)"/>
						</s:if>
						<s:if test="#openGeekValid==1">
						<label>已启动</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button"  value="关闭" onclick="geekValid(0)"/></td>
						</s:if>
					    <td><span id="lastVersion2_mgId"></span></td>
					</tr>
					<tr>
						<td align="right" class="grey" width="15%">0.1抽奖开关：</td>
						<td><s:if test="#openDrawValid==0">
						<label>已关闭</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button"  value="开启" onclick="drawValid(1)"/>
						</s:if>
						<s:if test="#openDrawValid==1">
						<label>已启动</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button"  value="关闭" onclick="drawValid(0)"/>
						</s:if>
						</td>
					    <td><span id="forceVersion2_mgId"></span></td>
					</tr>
				</table> 
			</form>
		 </div>
	</div>
</body>
</html>
<script>

	$(document).ready(function() {
		$("#sbutton").click(function(){	
		    var lastVersion = new tt.Field(" 最新用户版本 ", "versionControlPojo.lastVersion").setMsgId("lastVersion_mgId");
			var forceVersion = new tt.Field(" 强制更新版本 ", "versionControlPojo.forceVersion").setMsgId("forceVersion_mgId");		
			var sketch = new tt.Field(" 更新语 ", "versionControlPojo.sketch").setMsgId("sketch_mgId");
			tt.vf.req.add(lastVersion,forceVersion);
			new tt.LV().set(0, 1000).add(sketch);
			tt.vf.num.add(lastVersion,forceVersion);
			if(confirm("确定更新么？")){
				if(tt.validate()){
				document.getElementById("from1").submit();					
			    }
			}else{
				return ;
			}
		});
		$("#sbutton2").click(function(){	
			var lastVersion2 = new tt.Field(" 最新用户版本 ", "versionControlPojo2.lastVersion").setMsgId("lastVersion2_mgId");
			var forceVersion2 = new tt.Field(" 强制更新版本 ", "versionControlPojo2.forceVersion").setMsgId("forceVersion2_mgId");		
			var sketch2 = new tt.Field(" 更新语 ", "versionControlPojo.sketch2").setMsgId("sketch2_mgId");
			tt.vf.req.add(lastVersion2,forceVersion2);
			new tt.LV().set(0, 1000).add(sketch2);
			tt.vf.num.add(lastVersion2,forceVersion2);
			if(confirm("确定更新么？")){
				if(tt.validate()){
				document.getElementById("from2").submit();					
			    }
			}else{
				return ;
			}
		});
	});	
	//极验开关
	function geekValid(v){
	if(v==1){
	var a="确定开启吗？";
	}else{
	var a="确定关闭吗？";
	};
	if(confirm(a))
			{
				$.ajax({
					url:"../v3.5/openGeekValid.do?status=" + v,
					success:function(){
						alert("修改成功！");
						window.location.href = "goEditVersionControl.do";
					},
					error:function(){
						alert("修改失败！");
					}
				});
			}else{
				return ;
			}
	};
	//0.1抽奖开关
	function drawValid(v){
	if(v==1){
	var a="确定开启吗？";
	}else{
	var a="确定关闭吗？";
	};
	if(confirm(a))
			{
		$.ajax({
			url:"../v3.5/openDrawValid.do?status=" + v,
			success:function(){
				alert("修改成功！");
				window.location.href = "goEditVersionControl.do";
			},
			error:function(){
				alert("修改失败！");
			}
		});
		}else{
				return ;
			}
	};
</script>