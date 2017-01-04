 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<s:if test="positionList!=null">
   <s:select id="depart"  theme="simple" list="positionList" name="user.position" headerKey="" headerValue="--请选择职位--" listKey="businessDictId" listValue="businessDictName"  value="user.position"/>  
</s:if>
  

	
						
						 
<%--     <select name="user.position" id="position"  >
		           			<option value="">--请选择职位-- </option>
							<s:iterator value="#request.positionList" id="us">
								<option value="<s:property value="#us.businessDictId"/>" <s:if test="#us.businessDictId==%{us.position}">selected="selected "</s:if>> <s:property value="#request.user.position"/></option>
						   	</s:iterator>				
						</select>  --%>
						


	