<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- <!doctype html>
<html>
<head>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=0" name="viewport">
<title>成长线</title> --%>
<meta charset="utf-8">
<style>
html,body{width:100%;margin:0;padding:0;font-family:Helvetica;}
html{font-size:12px;-webkit-text-size-adjust:none;}
body{background:#eeeeee;}
body,div,ul,li,h1,h2,h3,h4,p,dl,dt,dd{margin:0px;padding:0px;}
h1,h2,h3,h4,h5{font-size:1rem;font-weight:normal;}
a{-webkit-tap-highlight-color:rgba(0,0,0,0);outline:none;text-decoration:none;}
input[type="text"],input[type="password"]{-webkit-appearance:none;appearance:none;outline:none;-webkit-tap-highlight-color:rgba(0,0,0,0);border-radius:0;}
ul,li{list-style:none;}
table{border-collapse:collapse;}
img{max-width:100%;border:none;-webkit-touch-callout:none;}

.c-pink{color:#c82276;}

.growthLine-margin{padding:10px 0;background:#fff;}
.growthLine-box{position:relative;width:100%;padding-bottom:79.866666667%;}
.growthLine{position:absolute;width:100%;height:100%;top:0;left:0;}

.listItem{clear:both;margin-top:10px;background:#fff;}
.item-title{height:40px;line-height:40px;padding:0 10px;font-size:14px;color:#2f2f2f;overflow:hidden;}
.item-title img.icon{float:left;width:27px;height:40px;margin-right:8px;}

.glory>li{min-height:54px;padding:0 10px;overflow:hidden;}
.glory>li .icon{float:left;width:54px;height:54px;margin:0 10px;}
.glory>li div.info{margin-left:74px;border-bottom:1px solid #eee;}
.glory>li div.info:last-child{border-bottom:none;}
.glory>li .title{padding:10px 10px 3px 0;font-size:14px;color:#343434;font-weight:bold;}
.glory>li .tips{padding:3px 10px 10px 0;font-size:12px;color:#6d6d6d;}

.linkItem,.linkBlockItem{padding:0 10px 10px;}
.linkItem>li{min-height:64px;margin-top:10px;overflow:hidden;border:1px solid #eee;}
.linkItem>li a{display:block;background:url(<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/icon_go.png) no-repeat right center;background-size:17px 14px;}
.linkItem>li img.img{float:left;width:80px;height:64px;}
.linkItem>li p.txt{margin-left:80px;padding:10px;color:#636363;font-size:12px;line-height:22px;}

.linkBlockItem>li{margin-top:10px;}
.linkBlockItem>li a{display:block;}
.linkBlockItem>li img.img{display:block;width:100%;}

</style>
<!-- </head> -->

<!-- <body> -->
<%-- <div style="display:none;"><img src="${imgurl }" /></div> --%>

    <div class="wraper">
        <!-- 成长线 -->
        <div class="growthLine-margin"><div class="growthLine-box"><div class="growthLine">
            <div id="growthLine" style="width: 100%;height:100%;"></div>
        </div></div></div>

        <!-- 成长荣耀 -->
        <div class="listItem">
            <div class="item-title"><img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_1.png" width="27" height="40" />成长荣耀</div>
            <ul class="glory">
                <li>
                    <img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_5.png" width="54" height="54" />
                    <div class="info">
                        <h4 class="title"><c:if test="${growthHonor != null && growthHonor[0] != ''}">您的<span class="c-pink">${userBabyPojo.babyName}</span>已经获得了<span class="c-pink">${growthHonor[0]}</span>的称号</c:if>
                        <c:if test="${growthHonor == null || growthHonor[0] == ''}">现在起，陪伴宝宝一起完成任务努力成长吧！</c:if>
                        </h4>
                    </div>
                </li>
                <li>
                    <img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_6.png" width="54" height="54" />
                    <div class="info">
                    	<h4 class="title"><c:if test="${growthHonor[1] != ''}">您的<span class="c-pink">${userBabyPojo.babyName}</span>已经获得了<span class="c-pink">${growthHonor[1]}</span>的荣誉</c:if>
                    	<c:if test="${growthHonor == null || growthHonor[1] == ''}">加油哦！今天你能更棒哦！</c:if></h4>
                    	<p class="tips"><c:if test="${growthHonor[2] != ''}">本月${growthHonor[2]}有较大的提升哦</c:if></p>
                    </div>
                </li>
                <li>
                    <img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_7.png" width="54" height="54" />
                    <div class="info">
                        <h4 class="title">您的 <span class="c-pink">${userBabyPojo.babyName}</span> 下阶段可增强方面</h4>
                        <p class="tips">
                            <span class="c-pink"><c:if test="${growthHonor[3] != ''}">1.${growthHonor[3]}</c:if></span>&nbsp;&nbsp;
                            <span class="c-pink"><c:if test="${growthHonor[4] != ''}">2.${growthHonor[4]}</c:if></span>&nbsp;&nbsp;
                        </p>
                    </div>
                </li>
            </ul>
        </div>

        <%-- <!-- 相关知识 -->
        <div class="listItem">
            <div class="item-title"><img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_2.png" width="27" height="40" />相关知识</div>
            <ul class="linkItem">
            	<c:if test="${growthHonor[7] != ''}">
                <li><a href='{"type":"1","id":"${growthHonor[7]}","ageType":"${growthHonor[5]}","skillType":"${growthHonor[6]}"}'>
                    <img class="img" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/knowledgeBase/${growthHonor[8]}" width="80" height="64" />
                    <p class="txt">${growthHonor[9]}</p>
                </a></li>
                </c:if>
            </ul>
        </div>

        <!-- 相关宝典 -->
        <div class="listItem">
            <div class="item-title"><img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_3.png" width="27" height="40" />相关宝典</div>
            <ul class="linkItem">
            	<c:if test="${growthHonor[10] != ''}">
                <li><a href='{"type":"2","id":"${growthHonor[10]}","ageType":"${growthHonor[5]}","skillType":"${growthHonor[6]}","authorId":"${growthHonor[16]}"}'>
                    <img class="img" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/userCirclePost/${growthHonor[11]}" width="80" height="64" />
                    <p class="txt">${growthHonor[12]}</p>
                </a></li>
                </c:if>
            </ul>
        </div>

        <!-- 相关好玩 -->
        <div class="listItem">
            <div class="item-title"><img class="icon" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/images/growthLine_4.png" width="27" height="40" />相关好玩</div>
            <ul class="linkBlockItem">
            	<c:if test="${growthHonor[13] != ''}">
                <li><a href='{"type":"3","id":"${growthHonor[13]}","ageType":"${growthHonor[5]}","skillType":"${growthHonor[6]}"}'>
                    <img class="img" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/upfiles/platformSpecial/${growthHonor[14]}" />
                </a></li>
                </c:if>
            </ul>
        </div> --%>
    	
    </div>

    <script src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/webView/growthLine/js/echarts.min.js"></script>
    <script type="text/javascript">
    $(function(){
    var thisMounth = "${thisMounth}";
    var lastMounth = "${lastMounth}";
    var skillName = "${skillNames}";
    var thisMounths = thisMounth.split(",");
    var lastMounths = lastMounth.split(",");
    var skillNames = skillName.split(",");
    var max = parseInt("${max}")+2;
    
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('growthLine'));

    // 指定图表的配置项和数据
    var option = {
        legend: {
            orient: "vertical",     //纵向
            right: 0,
            bottom: 0,
            itemWidth: 14,
            itemHeight: 14,
            data: ["上个月","本月"]
        },
        tooltip: {},
        radar: {
            indicator: [
               { name: skillNames[0], max: max},
               { name: skillNames[1], max: max},
               { name: skillNames[2], max: max},
               { name: skillNames[3], max: max},
               { name: skillNames[4], max: max},
               { name: skillNames[5], max: max}
            ],
            name: {
                textStyle: {
                    color: '#585858',
                    fontWeight: 'bold',
                    fontSize: 13
                }
            },
            splitNumber: 5,     //分割几块
            splitLine: {
                lineStyle: {
                    color: '#fff',
                    width: 2
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['#e1cfab', '#efe4d2', '#f6f7f2', '#c4d9ea', '#9fcaed']
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#fff',
                    width: 2
                }
            }
        },
        series: [{
            name: '上个月 vs 本月',
            type: 'radar',
            symbol: 'circle',
            symbolSize: 8,
            lineStyle: {
                normal: {
                    width: 2
                }
            },
            data : [
                {
                    value : lastMounths,
                    name : '上个月',
                    itemStyle: {
                        normal:{
                            color: '#015da6'
                        }
                    }
                },
                 {
                    value : thisMounths,
                    name : '本月',
                    itemStyle: {
                        normal:{
                            color: '#e62119'
                        }
                    }
                }
            ]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    });
    </script>
<!-- </body>
</html> -->
