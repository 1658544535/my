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
<title>购物车提示</title>


<script language="JavaScript">
 $(document).ready(function(){
    $("#juxubuy").click(function() {
			$("#windownbg").remove();
			$("#windown-box").fadeOut("slow",function(){$(this).remove();});
		});
  });
</script>
</head>

<body class="body">
<table>
<tr style="height:140px;padding:5px;">
<td style="width:60px;">
<div style="height:120px;margin-top:15px;">
<img src="/images/001.png" width="50px">
<div>
</td>
<td>
<div style="height:120px;margin-top:10px;">
<p style="font-size: 16px;">购物车此时有${getcartCount}种商品，共<label style="color:red;">￥${allCartPrice}</label>元</p>
<p style="font-size: 16px;margin-top:40px;margin-left:60pxpx;" > 
<a href="/cartWeb.do" style="float:left;"><img src="/images/jiesuan.gif"></a>
<a id="juxubuy" style="float:left;cursor:pointer;" ><img src="/images/jixu.gif"></a></p>
</div></td>
</tr>

</table>
</body>
</html>
