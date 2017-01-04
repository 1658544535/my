<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>

<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript">
	$(function(){
		$("#ticketType").change(function(){
			if($("#ticketType option:selected").val()=="index"){
				$("#index").show();
				$("#floor1").hide();
				$("#floor2").hide();
				$("#floor3").hide();
				$("#floor4").hide();
				$("#floor5").hide();
				$("#floor6").hide();
			}else if($("#ticketType option:selected").val()=="1F"){
				$("#index").hide();
				$("#floor1").show();
				$("#floor2").hide();
				$("#floor3").hide();
				$("#floor4").hide();
				$("#floor5").hide();
				$("#floor6").hide();
			}else if($("#ticketType option:selected").val()=="2F"){
				$("#index").hide();
				$("#floor2").show();
				$("#floor1").hide();
				$("#floor3").hide();
				$("#floor4").hide();
				$("#floor5").hide();
				$("#floor6").hide();
			}else if($("#ticketType option:selected").val()=="3F"){
				$("#index").hide();
				$("#floor3").show();
				$("#floor2").hide();
				$("#floor1").hide();
				$("#floor4").hide();
				$("#floor5").hide();
				$("#floor6").hide();
			}else if($("#ticketType option:selected").val()=="4F"){
				$("#index").hide();
				$("#floor4").show();
				$("#floor2").hide();
				$("#floor3").hide();
				$("#floor1").hide();
				$("#floor5").hide();
				$("#floor6").hide();
			}else if($("#ticketType option:selected").val()=="5F"){
				$("#index").hide();
				$("#floor5").show();
				$("#floor2").hide();
				$("#floor3").hide();
				$("#floor4").hide();
				$("#floor1").hide();
				$("#floor6").hide();
			}else if($("#ticketType option:selected").val()=="6F"){
				$("#index").hide();
				$("#floor6").show();
				$("#floor2").hide();
				$("#floor3").hide();
				$("#floor4").hide();
				$("#floor5").hide();
				$("#floor1").hide();
			}
		});
	});
</script>
<style type="text/css">
	.wrapper02-content{width:1190px;}
	.wrapper02-content ul{list-style:none;}
	.wrapper02-content ul li{float:left; margin:0 2px; *margin:0 1px;}
	.wrapper02-content ul li a{text-decoration:none;}
	.wrapper02-content ul li a:hover{text-decoration:none;}
	.wrapper02-content1{width:1190px;}
	.wrapper02-content1 ul{list-style:none;}
	.wrapper02-content1 ul li{float:left; margin:0 2px; *margin:0 1px;}
	.wrapper02-content1 ul li a{text-decoration:none;}
	.wrapper02-content1 ul li a:hover{text-decoration:none;}
</style>
</head>
<body>
<div class="sub_wrap">
    <div class="s_nav"><a href="productManage.do">商品管理</a> &gt; <a href="#">商品优先设置</a></div>
    <div class="Search_control">
			<p>优先显示模块</p>
			<a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
		</div>
		<!-- 查询开始 -->
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">
					<tr>
					<td align="right">请选择修改优先显示模块：</td>
						<td><select name="productPojo.productTypeId" id="ticketType"  class="floatLeft">
							<option value="">----请选择----</option>
								<option value="index">首页新品推荐</option>
								<option value="1F">1F商品推荐</option>
								<option value="2F">2F商品推荐</option>
								<option value="3F">3F商品推荐</option>
								<option value="4F">4F商品推荐</option>
								<option value="5F">5F商品推荐</option>
								<option value="6F">6F商品推荐</option>
				    		</select><div id="scale_mgId"></div></td>
					</tr>
				</table>
				<div class="Clear"></div>
			</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="info_table">
				<tr id="index" style="display:none">
					<td align="right" width="20%" class="grey">首页新品推荐:</td>
					<td>
					<div class="wrapper02-content" id="showIndex">
				    	<ul>
				    	<s:iterator value="productNewList">
				        	<li>
				        		<s:if test="%{status == 0}">
					        	  <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=1" style="color:red" >
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="234" height="242" />
				        			<div>
				        				<s:if test="productName.length()>16">
				        					<s:property value="productName.substring(0,16)+'...'" />
				        				</s:if>
				        				<s:else>
				        				
				        					<s:property value="productName" /> 
				        				</s:else>
				        			</div>
					        	 </a>
					        	</s:if>
					        	
					      
					        	<s:else>
					        	<a  href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=1" >
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="234" height="242" />
				        			<div>
				        				<s:if test="productName.length()>16">
				        					<s:property value="productName.substring(0,16)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" /> 
				        				</s:else>
				        			</div>
				        		</a>
					        	 </s:else>
				        
				        		<div align="center">
				        		 <a  href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=1" ><input  type="button" value="替换"/> </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				<tr id="floor1" style="display:none">
					<td align="right" width="20%" class="grey">1F:</td>
					<td>
					<div class="wrapper02-content1" id="showIndex">
				    	<ul>
				    	<s:iterator value="productTypeList1">
				        	<li>
				        		<s:if test="%{status == 0}">
				        		 <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=11" style="color:red"> 
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		</a>
				        		</s:if>
				        		
				        		<s:else>
				        		   <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=11" >
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		   </a>
				        		</s:else>
				        		<div align="center">
				        		<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=11" > <input type="button" value="替换"/>   </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				<tr id="floor2" style="display:none">
					<td align="right" width="20%" class="grey">2F:</td>
					<td>
					<div class="wrapper02-content1" id="showIndex">
				    	<ul>
				    	<s:iterator value="productTypeList2">
				        	<li>
					        		<s:if test="%{status == 0}">
									   <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=22" style="color:red">
					        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
					        			<div>
					        				<s:if test="productName.length()>13">
					        					<s:property value="productName.substring(0,13)+'...'" />
					        				</s:if>
					        				<s:else>
					        					<s:property value="productName" />
					        				</s:else>
					        			</div>
					        		</a>
		                          </s:if>
		
		                         <s:else>
		                           <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=22" >
					        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
					        			<div>
					        				<s:if test="productName.length()>13">
					        					<s:property value="productName.substring(0,13)+'...'" />
					        				</s:if>
					        				<s:else>
					        					<s:property value="productName" />
					        				</s:else>
					        			</div>
					        		</a>
		                         </s:else>
				        		<div align="center">
				        		<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=22" >		<input type="button" value="替换"/>  </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				<tr id="floor3" style="display:none">
					<td align="right" width="20%" class="grey">3F:</td>
					<td>
					<div class="wrapper02-content1" id="showIndex">
				    	<ul>
				    	<s:iterator value="productTypeList3">
				        	<li>
				        		<s:if test="%{status == 0}">
									<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=33" style="color:red">
									  <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
										<div>
									          <s:if test="productName.length()>13">
										    <s:property value="productName.substring(0,13)+'...'" />
										  </s:if>
										  <s:else>
										    <s:property value="productName" />
									          </s:else>
										</div>
									</a>
								</s:if>
								
								<s:else>
									<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=33" >
									   <img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
										<div>
										   <s:if test="productName.length()>13">
											<s:property value="productName.substring(0,13)+'...'" />
										   </s:if>
										   <s:else>
										        <s:property value="productName" />
										   </s:else>
										</div>
									</a>
								</s:else>
				        		<div align="center">
				        		<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=33" >		<input type="button" value="替换"/>  </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				<tr id="floor4" style="display:none">
					<td align="right" width="20%" class="grey">4F:</td>
					<td>
					<div class="wrapper02-content1" id="showIndex">
				    	<ul>
				    	<s:iterator value="productTypeList4">
				        	<li>
				        		<s:if test="%{status == 0}">
								  <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=44" style="color:red">
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		 </a>
								</s:if>
								
								<s:else>
								  <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=44" >
							          <img src="/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			 <div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			 </div>
				        		   </a>
								</s:else>
				        		<div align="center">
				        		<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=44" >		<input type="button" value="替换"/>   </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				<tr id="floor5" style="display:none">
					<td align="right" width="20%" class="grey">5F:</td>
					<td>
					<div class="wrapper02-content1" id="showIndex">
				    	<ul>
				    	<s:iterator value="productTypeList5">
				        	<li>
				        		<s:if test="%{status == 0}">
								 <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=55" style="color:red">
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		</a>
								</s:if>
								
								<s:else>
								   <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=55" >
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		  </a>
								</s:else>
				        		<div align="center">
				        		 <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=55" >		<input type="button" value="替换"/>   </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				<tr id="floor6" style="display:none">
					<td align="right" width="20%" class="grey">6F:</td>
					<td>
					<div class="wrapper02-content1" id="showIndex">
				    	<ul>
				    	<s:iterator value="productTypeList6">
				        	<li>
				        		<s:if test="%{status == 0}">
								  <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=66" style="color:red" >
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		</a>
								</s:if>
								
								<s:else>
								  <a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=66" >
				        			<img src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/product/<s:property value='image'/>" alt="" width="180" height="230" />
				        			<div>
				        				<s:if test="productName.length()>13">
				        					<s:property value="productName.substring(0,13)+'...'" />
				        				</s:if>
				        				<s:else>
				        					<s:property value="productName" />
				        				</s:else>
				        			</div>
				        		   </a>
 								</s:else>
				        		<div align="center">
				        		<a href="goSetIntroduce.do?oldId=<s:property value='id'/>&typeStr=66" >		<input type="button" value="替换"/>  </a>
				        		</div>
				        	</li>
				        </s:iterator>
				        </ul>
				    </div>
					</td>
				</tr>
				</table>
  <div class="h15"></div>
   <div>
</div>
</body>
</html>