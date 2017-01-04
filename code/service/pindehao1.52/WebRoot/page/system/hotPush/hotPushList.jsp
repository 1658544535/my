<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../../common/top.jsp"></jsp:include>
<jsp:include page="../../common/common_head.jsp"></jsp:include>
<style>
/*弹窗*/
.popup{display:none;position:fixed;top:0;left:0;right:0;bottom:0;z-index:999;background:#666;background:rgba(0,0,0,0.2);}
.popup-box{position:relative;width:820px;margin:160px auto 0;background:#fff;border-radius:5px;box-shadow:0 0 8px 0 rgba(0,0,0,0.2);}
.popup-header{height:50px;line-height:50px;padding:0 10px;font-size:16px;color:#333;border-bottom:1px solid #ddd;}
.popup-box>.popup-close{position:absolute;width:50px;height:50px;top:0;right:0;background:url(../images/popup-close.png) no-repeat 50% 50%;}
.popup-content{margin:20px;}
.popup-footer{padding:20px 0;margin:0 20px;border-top:1px solid #f1f1f1;text-align:center;}
.popup-footer .popup-sure,.popup-footer .popup-close{display:inline-block;width:60px;height:30px;margin:0 5px;line-height:30px;border-radius:5px;color:#fff;font-size:14px;background:#61b3ff;}
.popup-footer .popup-close{background:#999;}
.popup-footer .popup-sure:hover,.popup-footer .popup-close:hover{opacity:.8;*filter:alpha(opacity=80);}

.popup-dl{font-size:14px;line-height:23px;color:#666;}
.popup-dl dt{padding:5px 0;color:#333;font-weight:bold;}
.popup-dl dd textarea{width:100%;border:1px solid #ddd;border-radius:5px;padding:5px;resize:none;box-sizing:border-box;font-size:14px;color:#666;line-height:26px;}
</style>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/system/help/helpCommon.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/jquery.nanoscroller.min.js"></script>
<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/js/base.js"></script>
</head>
<body>
<div class="sub_wrap">
<div class="s_nav"><a href="#">热门推荐管理</a></div>
     <div class="Search_control">
	 <p>按条件查找</p>
	 <a class="collapse_btn" id="searchBar" onclick="showandhide()"></a>
	 </div>
		<!-- 查询开始 -->
		<form action="hotPushList.do" method="post" id="sysform">
			<div id="search_show" style="">
				<table width="100%" border="0" class="Search_table">  
					<tr> 
					    <td align="right">专题名称：</td>
						<td><label><input type="text" name="hotPushPojo.specialName" value=""></label></td>
						<td align="right">专题来源：</td>
						<td><select name="hotPushPojo.type" id="hotPushPojo.type"  class="floatLeft" onchange="checkType(this.options[this.options.selectedIndex].value);">
							<option value="">----请先选择----</option>
							<option value="1">平台专题</option>
							<option value="2">创客品牌</option>
				    		</select></td>
				    	<td align="right">专题类型：</td>
				    	<td><select name="specialType" id="hotPushPojo.specialType"  class="floatLeft specialType">
				    		</select></td>
					</tr>					    
				</table>
				<input type="hidden" name="page.pageNo" value=0 id="pageNo">
				<div class="floatRight">
					   <input id="query_btn" type="button" class="submit_btn" value="查询" />
				</div>
				       <div class="Clear"></div>
			    </div>
		</form>
		<!-- 查询结束 -->
<div class="h15"></div>
<div>
<table width="100%" border="0" class="Info_list_table">
<tr>    
		<th>Banner</th>
		<th>专题</th>
		<th>专题来源</th>
		<th>专题类型</th>
		<th>标签</th>
		<th>序号</th>
		<th>操作</th>
</tr>
<tbody id="body"></tbody>      
</table>
<div class="page">
		<div class="floatleft">
			总共 <i id="rowcount">${page.rowCount}</i> 条/<i id="pagecount"></i>页
		</div>
		<div style="float: right" id="Pagination" class="pagination"></div>
		<div class="Clear"></div>
	    </div>
        </div>
        <!-- 设置预警弹窗 -->
		<div class="popup" id="sorting">
			<div class="popup-box" style="width:300px">
				<div class="popup-header">设置排序</div>
				<a class="popup-close" href="javascript:;" title="关闭"></a>
				<div class="popup-content" style="margin-left: 50px;">
					<dl class="popup-dl">
						<td class="grey">序号: </td><input onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" 
						onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" 
						maxlength="4" type="text" name="sorting" value="" />
					</dl>
				</div>
				<div class="popup-footer">
					<a class="popup-sure" href="javascript:;">确定</a>
					<a class="popup-close" href="javascript:;">取消</a>
				</div>
			</div>
		</div><!-- popup -->
</body>
</html>
<script type="text/javascript">
var ctx  ="<s:property value="ctx" />";
var pagecount = "${page.rowCount}"; 
function query() {
if(tt.validate()){
		var rand=Math.random() * ( 100000 + 1);
		queryData("hotPushListCount.do", "hotPushListAll.do?randquery="+rand);
	}
}
	/**
	 *分页展现页面函数 
	 **/
	function installPage() {
		var type;
		var specialType;
		if(this.type == 1){
			type="平台专题";
			if(this.specialType==1){
				specialType="软文专题";
			} else if (this.specialType==2){
				specialType="商品专题";
			}
		} else if(this.type == 2 ){
			type="创客专题";
			if(this.specialType==1){
				specialType="创好玩";
			} else if (this.specialType==2){
				specialType="创选活动";
			} else if (this.specialType==3){
				specialType="普通创客专题";
			}
		}
		
		$("#body").append(
				"<tr>"+
				"<td><img src='<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/upfiles/hotpush/"+this.banner+"' height='100px' /></td>"+
				"<td>"+ this.specialName + "</td>"+
				"<td>"+ type + "</td>"+				
				"<td>"+ specialType + "</td>"+
				"<td>"+ this.ageTypeName +" ; "+this.skillTypeName+" ; "+this.productTypeName+"</td>"+
				"<td>"+ this.sorting + "</td>"+
				"<td><a class='edit_btn' onclick='setSorting("+this.id+")'>改序号</a>"+
				"<a class='edit_btn' href='goHotPush.do?id="+this.id +"'>编辑</a>"+
				"<a class='del_btn' onclick=del("+this.id+","+this.type+","+this.specialId+")>取消</a>"+
				"</td>>");
				
	}	
	$(function() {
		/**
		  首次要初始化分页
		 **/
		 var rand=Math.random() * ( 100000 + 1);
		MAOWU.page.init(<s:property value="page.rowCount"/>,
				"hotPushListAll.do?randIni="+rand);	
		$("#query_btn").click(query);		
	});
	//设置排序
	function setSorting(id){
		var option = {
			"obj": "#sorting",
			"sureFun": function(){
				var sorting = $("#sorting input[name='sorting']").val();
				$.ajax({
					url:"setSorting.do?hotPushPojo.sorting="+sorting+"&hotPushPojo.id="+id,
					success:function(data){
						if (data == 1) {
							alert("设置成功!");
							query();
						} else {
							alert("设置失败!");
						}
					},
					error: function(){
						alert("设置失败!");
					}
				}); 
			},
			"closeFun": function(){
			}
		};
		showPopup(option);
	}
	//取消
	function del(id,type,specialId){
		if(confirm("确认要取消吗？")){
			var url = "delhotPush.do?hotPushPojo.id="+id+"&hotPushPojo.type="+type+"&hotPushPojo.specialId="+specialId;	
			window.location.href=url;
		}else{
			return ;
		}
	}
	//选择下拉
	function checkType(val){
		var str = "";
		if(val==1){
			str = "<option value=''>----请选择----</option>"+
			 	  "<option value='1,1'>软文专题</option>"+
				  "<option value='1,2'>商品专题</option>";
			$(".specialType").empty();
		    $(".specialType").append(str);
		} else if(val == 2){
			str = "<option value=''>----请选择----</option>"+
				  "<option value='2,1'>创好玩</option>"+
				  "<option value='2,2'>创选活动</option>"+
			      "<option value='2,1'>普通创客专题</option>";
			$(".specialType").empty();
			$(".specialType").append(str);
		}
	}
</script>