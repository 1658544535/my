 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="allDept!=null">
   <s:select id="depart" list="allDept" name="dept" headerKey="" headerValue="--请选择部门--" listKey="deptId" listValue="deptName" value="departmentId" />  
</s:if>