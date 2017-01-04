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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<title>淘竹马行业资讯</title>
<style type="text/css">
<!--
.STYLE2 {color: #FF0000; font-weight: bold; }
.STYLE3 {color: #FF0000; font-weight: bold; font-size: 18px; }
-->
.thread-extra {
border-top: 1px solid #eee;
margin-top: 20px;
padding-top: 19px;
overflow:hidden;
}
.thread-recommend {
float: left;
width: 882px;

overflow:hidden;
}
.thread-recommend h3 {
font-size: 14px;
font-weight: 700;
color: #333;
padding-bottom: 10px;
}
ol{ display:block; list-style:none;}
.thread-recommend-item {
/*width: 352px;*/
line-height: 28px;
overflow:hidden;
}
.thread-recommend-item a {
float: left;
font-size: 14px;
color: #666;
text-decoration:none;
}
.thread-recommend-item a:hover{ color:#df434e;}
detail-content .thread-extra .thread-recommend-item {
line-height: 28px;
}
.thread-recommend-item span {
float: right;
color: #999;
line-height:28px;
font-size:12px;
}
.number_style{ float:right;}
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="top02-width">
	<!--<div class="logo"></div>-->
	<a href="goIndexWeb.do" class="logo"></a>
	<form action="infoList.do" id="idform" method="post">
	<div class="search"><input id="tittle" name="tittle" type="text" value="搜索 资讯" onfocus="this.value=''" onblur="if(this.value==''){this.value='搜索 资讯'}" class="search-input"/><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form>
</div> 

<div class="clear"></div>

<div  class="shopping_Cart clearfix">
	<div style="width:100%; height:30px; background:#fff; font-size:14px; text-indent:10px; border:1px solid #dfdfdf;line-height:40px; ">您当前位置：&nbsp;<a href="infoPageList.do">行业资讯</a>&nbsp;>>&nbsp;<s:if test="infoPojo.type == 2">商城新闻</s:if> <s:if test="infoPojo.type == 1">行业新闻</s:if>&nbsp;>>&nbsp;<s:property value="infoPojo.title"/></div>
	<div class="information_left">
		<div class="my_supplier-R-Part01-title" >&nbsp;市场动态</div>
        <div class="my_supplier-R-Part01-list" style="width:222px; height:180px;">
        	<ul  class="shop_information">
        	<s:iterator value="infoList">
                			<li><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></li>
                	</s:iterator>
            </ul>
            <div class="infor-L-List-Title-R"><a href="infoList.do?type=2">更多</a></div>   
        </div>
		<div class="my_supplier-R-Part01-title" style="margin-top:15px;" >&nbsp;行业新闻</div>
        <div class="my_supplier-R-Part01-list" style="width:222px; height:180px;">
        	<ul  class="shop_information">
        	<s:iterator value="infoPojos">
        	<li><a href="infoDetail.do?infoPojo.id=<s:property value='id'/>"><s:property value="title"/></a></li>
        	</s:iterator>
            </ul> 
            <div class="infor-L-List-Title-R"><a href="infoList.do?type=1">更多</a></div>
        </div> 
	</div>
	
	<div class="information_right clearfix">
    	<div  style="padding:10px 30px;">
            <span style=" display:block; padding-top:20px; line-height:30px;font-size:18px; font-weight:bold; margin:0 auto; text-align:center;"><s:property value="infoPojo.title"/></span>
            </br><span style="text-align:center; display:block;">作者：<s:property value="infoPojo.author"/></span>
            <s:property value="infoPojo.content" escape="false"/>
          </br></br></br></br>
      <div style="float:left;width:100%">
				    	<span>赶快分享：</span>

				    	<a href="javascript:;" hidefocus="true" onclick="jdPshowRecommend('http://v.t.sina.com.cn/share/share.php?appkey=11111111','sina');"
	                        id="site-sina" title="推荐到新浪微博">
	                        <img src="images/xqy_22.gif" />
	                    </a><a href="javascript:;" hidefocus="true" onclick="jdPshowRecommend('http://v.t.qq.com/share/share.php?source=1000002&site=','qzone');"
	                        id="site-qzone" title="推荐到腾讯微博">
	                        <img src="images/xqy_24.gif" />
	                    </a>
	                    <script type="text/javascript">
	                        function jdPshowRecommend(url, stype) {
	                            var title = "<s:property value="title"/>";	                           
	                            var content333 ="<s:property value="contents" />...";
	                          



var  content=content333.replace("ldquo","");
 content=content.replace("rdquo","");
 content=content.replace("&","");
  content=content.replace(";","");
  content=content.replace("&;","");

	                            var img = "";
	                            var productUrl = window.location.href;
	                            //var pin = readCookie("pin") || "";
	                            var pin = "";
	                            if (stype == "qzone") {
	                                url = url + "&title=" + content + "&pic=" + img + "&url=" + productUrl + pin;
	                            }
	                            if (stype == "sina") {
	                                url = url + "&title=" + encodeURIComponent(content) + "&pic=&url=" + encodeURIComponent(productUrl) + pin;
	                                window.open(url, "", "height=500, width=600");
	                            }
	                            if (stype == "renren") {
	                                url = url + "title=" + title + "&content=" + content + "&pic=" + img + "&url=" + productUrl + pin;
	                            }
	                            if (stype == "kaixing") {
	                                url = url + "rtitle=" + title + "&rcontent=" + content + "&rurl=" + productUrl + pin;
	                            }
	                            if (stype == "douban") {
	                                url = url + "title=" + title + "&comment=" + content + "&url=" + productUrl + pin;
	                            }
	                            if (stype == "msn") {
	                                url = url + "url=" + productUrl + pin + "&title=" + title + "&description=" + content + "&screenshot=" + img;
	                            }

	                            if (stype != "sina") {
	                                window.open(encodeURI(url), "", "height=500, width=600");
	                            }
	                        }
	                    </script>

				    </div>
      
			            <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/button.js#style=-1&amp;ssc=false&amp;mdiv=-1&amp;type=15"></script>
			            <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC1.js"></script>
            </div>
            </br>
                  <s:if test="infoPojo.preTittle != null"><div class="top_con" style="margin-top:15px;" ><a href="infoDetail.do?infoPojo.id=<s:property value="infoPojo.preId"/>" >上一篇：<s:property value="infoPojo.preTittle"/></a></div></s:if>
                  <s:if test="infoPojo.nextTittle != null"><div class="top_con"><a href="infoDetail.do?infoPojo.id=<s:property value="infoPojo.nextId"/>" >下一篇：<s:property value="infoPojo.nextTittle"/></a></div></s:if>
                   <div class="thread-extra fd-clr">
                            <div class="thread-recommend">
                                <h3>相关推荐：</h3>
                                <ol>
                                <s:iterator value="randInfoList">
                                	                                						                            <li class="thread-recommend-item fd-clr">
                                    			<a href="infoDetail.do?infoPojo.id=<s:property value='id'/>" ><s:property value="title"/></a> 
                                    			<span class="number_style"><s:property value="createDateStr"/></span>
                                   			</li>
			                    </s:iterator>        				                            
			                                                            	                                </ol>
                            </div>
                      
                        </div>
        </div>
       
    </div>
    
    
    </div>


	
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
