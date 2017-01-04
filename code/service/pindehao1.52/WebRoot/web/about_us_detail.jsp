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
<link href="/css/faq.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link href="css/down.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>
<script>
function search(){
		document.getElementById("idform").submit();
	}
</script>
<s:if test="helpPojo.id==232"><title>淘竹马欢迎合作</title></s:if>
<s:elseif test="helpPojo.id==233"><title>淘竹马知识产权 </title></s:elseif>
<s:elseif test="helpPojo.id==234"><title>淘竹马著作权与商标声明</title> </s:elseif>
<s:elseif test="helpPojo.id==235"><title>淘竹马法律声明 </title></s:elseif>
<s:elseif test="helpPojo.id==236"><title>淘竹马服务条款 </title></s:elseif>
<s:elseif test="helpPojo.id==237"><title>淘竹马隐私声明 </title></s:elseif>
<s:if test="helpPojo.id==37"><title>淘竹马公司简介</title>
<meta name="keywords" content="群宇 宇博 M2C分销平台 商业模式 大数据 互联网思维 玩具 母婴 B2C玩具平台  电商学院" />
<meta name="description" content="广东群宇创办于2007年，前身是汕头市爱迪尔文化传媒有限公司。2014年引进战略投资，重组为广东群宇互动科技有限公司，全面接管、进驻宇博电商园，公司与时俱进进行平台战略和新商业模式布局。
在移动互联网、大数据、云计算等科技不断发展的背景下，响应汕头市政府高瞻远瞩创建“国家电子商务示范城市”、“退二进三”的战略下“群宇互动科技”对市场、用户、产品、企业价值链乃至整个商业生态不断探索新的商业模式。在玩具产业迫切需要转型升级、完善产业链的大环境下，我们用互联网思维模式，创新提出围绕玩具渠道平台化转型，营造“生态链”，帮助玩具企业实现更全面的电子商务化，推进玩具渠道向平台化运营转型升级，构建玩具产业链的信息流、渠道流、物流、资金流以及人才流等多方共赢的格局。 
“群宇互动科技”拥有锐意求新的技术研发团队和运营团队、多模多维的孵化平台、配套齐全的电子商务产业园，将推进玩具产业链平台化运营，筑建互联网、物流网、云网，三网生态系统，打造玩具产业链、电商产业链生态圈。
“群宇互动科技”以自建、自营式PC平台及APP平台运营，旗下有三大玩具母婴用品类线上垂直平台：玩具总汇网B2B平台、淘竹马网M2C平台、翻动城网B2C平台；线下有宇博电商产业园、《玩具总汇》期刊、梁山电商学院、梁山创业联盟。" /></s:if>
<s:if test="helpPojo.id==38"><title>淘竹马服务项目</title>
<meta name="keywords" content="M2C玩具分销平台 淘竹马 电商创业  玩具分销 玩具厂家直销 玩具 品牌联盟 群宇 玩具48小时发货 玩具3C认证" />
<meta name="description" content="供应商服务项目：提供摄影、美工、仓储、分拣、物流、上架、控价等电商服务；采购商服务项目：提供优势的价格支持，一只发货，完整数据包，专业培训，专人客服，定制，售后等服务。" /></s:if>
<s:if test="helpPojo.id==39"><title>淘竹马玩具分销平台</title>
<meta name="keywords" content="玩具平台PC 玩具手机APP 母婴 群宇 莱美工业区 电商 玩具分销渠道 互联网思维  儿童玩具" />
<meta name="description" content="“群宇互动科技”以自建、自营式PC平台及APP平台运营，旗下有三大玩具母婴用品类线上垂直平台：玩具总汇网B2B平台、淘竹马网M2C平台、翻动城网B2C平台；线下有宇博电商产业园、《玩具总汇》期刊、梁山电商学院、梁山创业联盟。
以“打造产业链互联生态圈”为愿景，我们秉承客户第一、团队合作、拥抱变化的企业宗旨和创新、开放、分享、责任的经营理念，整合B2B、B2C、M2C、O2O等多类平台模式，让玩具产业电子商务梦想触手可及，渠道扩展赢得先机。" /></s:if>
<s:if test="helpPojo.id==40"><title>淘竹马玩具分销平台</title>
<meta name="keywords" content="母婴 玩具3C 淘竹马 母婴品牌 玩具企业 玩具分销 品牌玩具 群兴 骅威 电商 音乐玩具 遥控玩具 早教玩具 益智玩具 电动玩具" />
<meta name="description" content="广东群宇互动科技”以自建、自营式PC平台及APP平台运营，旗下有三大玩具母婴用品类线上垂直平台：玩具总汇网B2B平台、淘竹马网M2C平台、翻动城网B2C平台。线下有宇博电商产业园、《玩具总汇》期刊、梁山电商学院、梁山创业联盟。
    群宇：群英汇策，臻宇至上。市场逐鹿，群雄竞起，我们有算定全局的智谋，我们有一往无前的勇气，决战必胜的信念，傲视天下的魄力。山高路远，无止无息；我们群宇人折冲阵前，直面所惧，登凌绝顶，虽万千人吾往矣！我们期待您的加入！" /></s:if>
<s:if test="helpPojo.id==230"><title>淘竹马供应商入驻</title>
<meta name="keywords" content="玩具手机APP 玩具采购 M2C玩具分销 玩具分销软件 过家家玩具 童车玩具 益智玩具 遥控 早教  群兴 奥飞" />
<meta name="description" content="淘竹马玩具分销平台具有大数据统计，精准定位市场产品需求，让厂商不再盲目生产；平台渠道整合，对产品价格体系进行控管，辅助厂商重构价格体系；提供摄影、美工、仓储、分拣、物流一站式方案，解决目前电商渠道的囤货、发货的资金、人员压力等；流通环节减少至一对一，点对点，省去了诸多的流通成本，保障售后服务质量；引导玩家生产厂商入驻，为厂商进行多种销售渠道下沉，渠道优化整合，让厂商专注生产，抓好产品质量，网络销售渠道交给我们" /></s:if>
<s:if test="helpPojo.id==231"><title>淘竹马分销商招募</title>
<meta name="keywords" content="品牌玩具分销 玩具分销平台  专业 电商方案 下载玩具数据包 玩具新品快订 玩具采购囤货 玩具销售渠道  玩具售后质量 玩具电商渠道" />
<meta name="description" content="怀着创业的梦想，越来越多的传统企业走上了电商之路。在电子商务领域，B2B和B2C无疑是最常见的模式，但随着时代的发展，很多中小型企业都难获得快速发展，投资回报率仍然偏低，大量的投入资金和人力，却看不到效果，挣扎在电子商务的海洋之中，进退两难。
以己之短攻人之长，不如另辟蹊径，创新模式，一种新的商业模式M2C进入我们的视野，在电子商务发展如此瞬速的今天，这种厂家直接面对分销商的新商务模式，此模式摒除了牵涉批发等多个中间环节，并且实行的是“无店铺”的在线销售模式，因此与传统销售环节和渠道相比具有成本竞争优势，此外M2C通过集中众多小批量订单，集中向厂家下单，解决制造商批量生产与零散订单处理之间的冲突，有效解决目前B2B、B2C存在的不足。这种针对于B2M、B2C、C2C等电子商务模式而出现的延伸的商业模式，是否会成为后起之秀，成为电子商务掘金者的下一座金矿呢？ 
2015年元月，广东群宇互动科技有限公司旗下M2C玩具分销平台“淘竹马”璀璨上线。
“淘竹马”，全球专业M2C玩具分销平台，开启玩具分销互联时代，打造玩具产业链生态圈
服务万千玩具企业，开拓分销渠道。线上十三万淘宝玩具店家，线下百万母婴用品店，万位电商创业高校毕业生共同选择。
多端平台，一站式电商服务。
“淘竹马”期待您的加盟！" />
</s:if>
</head>

<body class="body">
<jsp:include page="header.jsp"></jsp:include>


<div class="top02-width">
	<div class="logo02"></div>
	<form action="goSearch.do" id="idform" method="post">
	<div class="search"><input id="searchkey" name="searchkey" type="text" value="请简单描述你的问题" onfocus="this.value=''" onblur="if(this.value==''){this.value='请简单描述你的问题'}" class="search-input"/><button type="submit" class="search-button" onclick="search()">搜 索</button></div>
	</form> 
</div> 

<div class="clear"></div>

<div class="help-nav-width">
	<div class="help-nav-fu">
    	<ul>
        	<li><a href="goaboutus.do?helpPojo.id=37"><s:if test="helpPojo.id==37"><span>公司简介</span></s:if><s:else>公司简介</s:else></a></li>
            <li><a href="goaboutus.do?helpPojo.id=38"><s:if test="helpPojo.id==38"><span>服务项目</span></s:if><s:else>服务项目</s:else></a></li>
            <li><a href="goaboutus.do?helpPojo.id=39"><s:if test="helpPojo.id==39"><span>联系我们</span></s:if><s:else>联系我们</s:else></a></li>
            <li><a href="goaboutus.do?helpPojo.id=40"><s:if test="helpPojo.id==40"><span>加入我们</span></s:if><s:else>加入我们</s:else></a></li>
            <li><a href="goaboutus.do?helpPojo.id=230"><s:if test="helpPojo.id==230"><span>供应商入驻</span></s:if><s:else>供应商入驻</s:else></a></li>
            <li><a href="goaboutus.do?helpPojo.id=231"><s:if test="helpPojo.id==231"><span>分销商招募</span></s:if><s:else>分销商招募</s:else></a></li>
        </ul>
    </div>
</div>

<div class="faq-width">
	<div class="faq-L">
    	<div class="faq-L-title"><img src="images/faq_03.jpg" alt="" width="16" height="16" />&nbsp;&nbsp;&nbsp;&nbsp;淘竹马</div>
        
       <div class="faq-L-list">
            <div id="firstpane" class="menu_list">
               <p class="menu_head">淘竹马</p>
                    <div style="display:none" class="menu_body" >
                      <a href="gotzmfxpt.do">淘竹马分销平台</a>
                      <a href="gotzmapp.do">淘竹马APP</a>
                      <a href="gowxgzh.do">淘竹马公众号</a>
                    </div>
                     <p class="menu_head">见客</p>
                    <div style="display:none" class="menu_body" >
                      <a href="gojkapp.do">见客APP</a>
                      <a href="goqkd.do">企客端</a>
                      <a href="http://www.5315.cn/" target="blank">玩具总汇网</a>
                    </div>
                    <p class="menu_head"><a href="goshike.do">舌客</a></p>
                    <p class="menu_head"><a href="godsxy.do">梁山电商学院</a></p>
                    <p class="menu_head"><a href="gowjzh.do">玩具总汇期刊</a></p>
                    <p class="menu_head"><a href="gofdcWeb.do">翻动城</a></p>
                    
                    
            </div>
        </div>
    </div>
    
<div class="faq-R-fu">
    
    	<div  style="padding:10px 30px;">
            <span style=" display:block; padding-top:20px; line-height:30px;font-size:18px; font-weight:bold; margin:0 auto; text-align:center;"><s:property value="helpPojo.title"/></span><br/>
            <s:property value="helpPojo.content" escape="false"/>
             <br/> <br/> <br/>   
             
        </div>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(10)").show();
	$("#firstpane p.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});
</script>
</body>
</html>
