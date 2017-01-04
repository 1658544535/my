 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="deptList!=null">
   <s:select id="depart" theme="simple" list="deptList" class="floatLeft" name="user.userDept" headerKey="" headerValue="--请选择部门--" listKey="businessDictId" listValue="businessDictName"  value="user.userDept"/>  
</s:if>
	
	