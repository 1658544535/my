<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改帮助信息</title>
<script type="text/javascript"
	src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
</head>
<body>
	<div align="center">
		<h1>帮助信息详情</h1>
	</div>
	<div align="center">
		
			<table>
				<tr>
					<td>信息名称:</td>
					<td><input type="text" name="helpPojo.helpname"
						id="helpname"
						value="<s:property value="helpPojo.helpname"/>"  disabled="disabled"> 
					</td>
				</tr>
				<tr>
					<td>添加人:</td>
					<td><input type="text" name="helpPojo.userid"
						id="userid"
						value="<s:property value="helpPojo.userid"/>" disabled="disabled"> 
					</td>
				</tr>
				<tr>
					<td>添加时间:</td>
					<td><input type="text" value="<s:date name="helpPojo.helpadddate" format="yyyy-MM-dd"/>"  disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>信息内容:</td>
					<td><textarea rows="10" cols="18" name="helpPojo.helpcontent"
							id="helpcontent"  disabled="disabled"><s:property
								value="helpPojo.helpcontent" /></textarea>
								
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="button" onclick="window.history.back()">返回</button></td>
				</tr>
			</table>


		
	</div>

</body>
</html>