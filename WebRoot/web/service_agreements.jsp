<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<style type="text/css">
.yhxybox {
width: 1060px;
padding: 30px 70px;
margin: 5px auto;
overflow: hidden;
zoom: 1;
border: 1px solid #dadada;
font-size: 14px;
line-height: 25px;
color:#494949;
}
.yhxybox h2 {
text-align: center;
padding: 20px 0 40px;
font-size: 28px;
font-family: "Microsoft YaHei",微软雅黑,"MicrosoftJhengHei";
font-weight: normal;
}
.yhxybox p {
padding-bottom: 15px;
}
.yhxybox h3 {
font-size: 14px;
font-weight:normal;
}
strong, b {
font-weight: bold;
}
</style>
<title>淘竹马分销平台</title>
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>	
<div class="register-title">
		<div class="register-txt">
			淘竹马分销<span class="register-txt02">服务协议</span>
		</div>
	</div>
		<div class="yhxybox">
<h2><s:property value="tittle"/></h2>
<s:property value="content" escape="false"/>
 </div>
		
<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
