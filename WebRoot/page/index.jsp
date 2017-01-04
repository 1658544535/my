<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
       if(session.getAttribute("loginPojoId")==null || session.getAttribute("user")=="")

       {
             String path = request.getContextPath();
             String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
             response.sendRedirect(basePath+"loginin.do");
       }
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>拼得好运营平台</title>
</head>
<frameset rows="85,*,0" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=request.getContextPath()%>/page/top.jsp" name="topFrame" scrolling="NO" noresize title="topFrame" >
  <frameset cols="190,*" frameborder="no" border="0" framespacing="0" >
    <frame src="<%=request.getContextPath()%>/page/left.jsp" name="leftFrame"  title="leftFrame"  noresize scrolling="yes">

    <frame src="<%=request.getContextPath()%>/page/right.jsp" name="workFrame" title="workFrame" scrolling="yes" noresize >
  </frameset>
  <frame src="<%=request.getContextPath()%>/page/bottom.jsp" name="bottomFrame" scrolling="no" noresize title="bottomFrame" >
</frameset>


</html>
