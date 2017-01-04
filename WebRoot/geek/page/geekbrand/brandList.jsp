<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
     <link rel="stylesheet" href="../../geek/css/default.css" media="all" />
    <link rel="stylesheet" href="../../geek/css/seller_common.css" type="text/css" media="all" />
	<jsp:include page="../common_head.jsp"></jsp:include>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
		<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
		<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
		<link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    <script src="../../geek/js/base.js" type="text/javascript" charset="utf-8"></script>
    
    <style>
    	.ui-tipbox-content{margin:14px 0 20px 87px!important;}
    	.ui-tipbox-content h3.ui-tipbox-title{margin-top:22px;}
    </style>
</head>
<body>
<jsp:include page="../geekHeader.jsp"/>
 <div class="seller-search-form" style="margin: 8px 0 -14px -8px;display:none;">
                <form action="#" method="post" accept-charset="utf-8" id="sysform">
                	<input type="hidden" name="page.pageNo" value=0 id="pageNo" />
                </form>
            </div>
        <div id="content" class="wrapper">
                <div class="pure-g admin-wrapper">
                    <div class="pure-u-1 main">
                        <style>
                            .update-brand{font-size:11px;height:20px !important;line-height:20px !important;padding:0 5px 0 5px;}.qc-link{font-size:12px;margin-right:5px;}.status_cont .iconfont{color:#999;display:inline-block;font-size-adjust:none;font-size:10px;-webkit-transform:scale(.8);cursor:pointer;}.qc_statuc_innercont{position:relative;*z-index:12;}.status_cont .qc_status_note{display:none;border-radius:3px;position:absolute;background:#eee;border:1px solid #ececec;top:25px;left:-50px;width:100px;padding:5px;z-index:20;*zoom:1;}.triangle{position:absolute;top:-16px;left:49px;width:0;height:0;border-width:8px;border-style:solid;border-color:transparent transparent #eee;}.seller-title-head-more{top:16px;}.seller-frozen{background-color:#999 !important;border:1px solid #999 !important;top:0 !important;cursor:default !important;}
                        </style>
                        <h1 class="seller-title">
                            品牌列表
                            <div class="seller-title-head-more">
                                <a class="ui-button ui-button-morange view-ShowBrandBoxBtn" href="goAddGeekBrand.do">
                                    <i class="iconfont">&#xf0175;</i>
                                    添加品牌
                                </a>
                            </div>
                        </h1>
                        <div class="p20 seller-brand">
                            <div class="ui-table-container">
                                <table class="ui-table">
                                    <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                    <thead>
                                        <tr>
                                            <th style="width:150">
                                                  品牌名
                                            </th>
                                            <th style="width:150">
                                                  品牌LOGO
                                            </th>
                                            <th style="width:450">
                                                  品牌描述
                                            </th>
                                            <th style="width:150">
                                                  主营类目
                                            </th>
                                            <th style="width:150">
                                                状态
                                            </th>
                                            <th style="width:100">
                                                操作
                                            </th>
                                        </tr>
                                    </thead>
                                    <!-- 表头可选 -->
                                    <tbody id="body">
                                    </tbody>
                                </table>
                                <!-- 页码开始 -->
	                            <div class="pagination" id="Pagination">
	                            </div>
                                <!-- 页码结束 -->
                            </div>
                        </div>
                       
                    </div>
                </div>
            </div>
		</div>
		
     
<jsp:include page="../geekFooter.jsp"/>

    <script src="../../seller/js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../geek/js/base.js" type="text/javascript" charset="utf-8"></script>


    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">UE.getEditor("editor",{toolbars: [[]],readonly:true,wordCount:false,});</script>
</body>

</html>
<script type="text/javascript">
	var ctx  ="<s:property value="ctx" />";
	var pagecount = "${page.rowCount}";
	var pageSize = 10;
	
	function query() {
		if(tt.validate()){
			var rand=Math.random() * ( 100000 + 1);
			queryData("geekBrandManageCount.do", "geekBrandManageList.do?randquery="+rand,pageSize);
		}
	}
	
	function installPage() {
		$("#body").append(
				"<tr id='"+this.id+"'>"+
                "<td>"+this.brandName+"</td>"+     
                "<td><img src='<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userMakerBrand/"+this.logo+"' style='width:50px;height:50px;' /></td>"+
                "<td>"+this.content+"</td>"+
                "<td>"+this.mainCategoryName+"</td>"+
                "<td>"+this.statusName+"</td>"+
                "<td class='ui-table-action'><a target='' href='goEditGeekBrand.do?userMakerBrandPojo.id="+this.id+"'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a target='' href='delGeekBrand.do?userMakerBrandPojo.id="+this.id+"&userMakerBrandPojo.brandId="+this.brandId+"' onclick='javascript:return p_del()'>删除</a></td>"+
                "</tr>"
                );
	}
	//分页展现页面函数 
	$(function() {
		//首次要初始化分页
		var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,"geekBrandManageList.do?randIni="+rand,pageSize);
		$("#query_btn").click(query);
	});
	function p_del() { 
			var msg = "您真的确定要删除吗？"; 
			if (confirm(msg)==true){ 
			return true; 
			}else{ 
			return false; 
			} 
			} 
</script>
