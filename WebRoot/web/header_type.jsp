<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/g.base.js"></script>
<script type="text/javascript" src="js/_head.js"></script>
<script>
	$(function() {
		$( "#tabs" ).tabs();
		var time = null;
		$('.nav_box-L-category li').hover(
			function () {
				$('div[r-cate]').hide();
				$(this).addClass('nav_box-L-category_a_hover');
				$('div[r-cate="'+$(this).attr('a-cate')+'"]').show();
				$('.topBanner').css('background','url('+$(this).attr('banner')+') no-repeat top center;');
			},
			function () {
				$(this).removeClass('nav_box-L-category_a_hover');
				var cate = $(this).attr('a-cate');
				time = setTimeout(function(){
					$('div[r-cate="'+cate+'"]').hide();
				},50);
			}
		);
		$(".nav_box-R-cate01").hover(function(){
			clearTimeout(time);
			$('li[a-cate="'+$(this).attr('r-cate')+'"]').addClass('nav_box-L-category_a_hover');
			$(this).show();
		},function(){
			var cate = $(this).attr('r-cate');
			time = setTimeout(function(){
				$('li[a-cate="'+cate+'"]').removeClass('nav_box-L-category_a_hover');
				$('div[r-cate]').hide();
			},50);
    	});
    	
    	getTypes(1);
    	getTypes(2);
    	getTypes(3);
    	getTypes(4);
    	getTypes(5);
    	getTypes(6);
	});
	
	function getTypes(pid){
		$.ajax({
		url:'getAllProductTypes.do?productTypePojo.status=1&productTypePojo.pid='+pid,
		type:'post',
		dataType: 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown){
    	},
    	success: function(result){
    		valueType(pid,result);
    	}
		});
	}
	
	function valueType(pid,result){
		var data = eval(result);
		$("#nav0"+pid).html("");
		for(var i=0;i<data.length;i++){
			if(data[i].isRed == "1"){
			$("#nav0"+pid).append("<li><a href='goProductListWeb.do?productPojo.status=1&productPojo.productTypeId="+data[i].id+"#infor'><font color='#df434e'>"+data[i].name+"</font></a></li>");
			}else{
				$("#nav0"+pid).append("<li><a href='goProductListWeb.do?productPojo.status=1&productPojo.productTypeId="+data[i].id+"#infor'>"+data[i].name+"</a></li>");
			}
		}
	}
</script>

<div class="nav_box-Posit">
    <div class="nav_box">
        <!--左侧栏目开始-->
        <div class="nav_box-L">
            <div class="nav_box-L-all"><img src="images/index_11.jpg" alt="" width="26" height="26" /><a href="goProductListWeb.do?productPojo.status=1">全部分类</a></div>
            <div class="nav_box-L-category">
                <ul>
                    <li a-cate="01" banner="images/index_18.jpg"><a href="goProductListWeb.do?productPojo.productTypeIds=1#infor"><img src="images/index_12.png" alt="" width="26" height="26" /> 遥控/电动玩具</a></li>
                    <li a-cate="02" banner="images/new_02.jpg"><a href="goProductListWeb.do?productPojo.productTypeIds=2#infor"><img src="images/index_13.png" alt="" width="26" height="26" /> 早教/音乐玩具</a></li>
                    <li a-cate="03" banner="images/index_18.jpg"><a href="goProductListWeb.do?productPojo.productTypeIds=3#infor"><img src="images/index_14.png" alt="" width="26" height="26" /> 过家家玩具</a></li>
                    <li a-cate="04" banner="images/new_02.jpg"><a href="goProductListWeb.do?productPojo.productTypeIds=4#infor"><img src="images/index_15.png" alt="" width="26" height="26" /> 童车玩具</a></li>
                    <li a-cate="05" banner="images/index_18.jpg"><a href="goProductListWeb.do?productPojo.productTypeIds=5#infor"><img src="images/index_16.png" alt="" width="26" height="26" /> 益智玩具</a></li>
                    <li a-cate="06" banner="images/new_02.jpg"><a href="goProductListWeb.do?productPojo.productTypeIds=6#infor"><img src="images/index_17.png" alt="" width="26" height="26" /> 其他玩具</a></li>
                </ul>
            </div>
        </div>
        <!--左侧栏目结束-->
        
        <!--右侧内容开始-->
        <div id="nav_box" class="nav_box-R">
            <div class="nav_box-R-cate01" r-cate="01"><ul id="nav01"></ul></div>
            <div class="nav_box-R-cate01" r-cate="02"><ul id="nav02"></ul></div>
            <div class="nav_box-R-cate01" r-cate="03"><ul id="nav03"></ul></div>
            <div class="nav_box-R-cate01" r-cate="04"><ul id="nav04"></ul></div>
            <div class="nav_box-R-cate01" r-cate="05"><ul id="nav05"></ul></div>
            <div class="nav_box-R-cate01" r-cate="06"><ul id="nav06"></ul></div>
        </div>
        <!--右侧内容结束-->
    </div>
</div>