<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0036)http://mapi.baobeian.net/apiTools.do -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘竹马接口使用文档</title>


<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<style type="text/css">
	html,bady,div,p,span,a,input{margin:0;padding:0;}
	.page_all{width:100%;}
	.page_main{width:80%;margin:0 auto;}
	.page_left{float:left;width:40%;}
	.page_table{width:100%;margin:0 auto;border-collapse:collapse;border:1px solid #DDD;}
	.page_table td,.page_table th{border:1px solid #DDD;padding:5px 2px;}
	.page_table .left_td{width:120px;text-align:center;}
	.page_table td input{height:20px;}
	.page_table .tr_hide{display:none;}
	.page_table td .api_submit{width:80px;height:30px;border:1px solid #BBB;font-size:18px;background-color:#CCC;text-align:center;}
	
	
	.page_right{float:right;width:55%;border:1px solid #DDD;padding:0 0 30px 30px;}
	.page_right .rs_url{width:90%;height:100px;}
	.page_right .rs_json{width:90%;height:400px;}

</style>
</head>
<body>
	<div class="page_all">
		<p><a href="">淘竹马接口文档</a>
		<div class="page_main">
			<!-- 左边栏 -->
			<div class="page_left">
				<table class="page_table">
					<tbody><tr>
						<td class="left_td">选择接口</td>
						<td>
							<select class="api_select">
								<option value="abc">请选择API接口</option>
								<option value="captcha">1.1手机发送验证码接口</option>
                                <option value="register">1.2注册提交信息接口</option>
                                <option value="applyConsumerApi">1.3注册分销商信息接口</option>
                                <option value="applyManufacturerApi">1.4注册供应商信息接口</option>
                                <option value="password">1.5重置密码接口</option>
                                <option value="login">2.1登录</option>
                                <option value="reSetPassWord">2.2修改密码</option>
                                <option value="searchProduct">3.1搜索（商品）</option>
                                <option value="searchShop">3.1.1搜索（店铺）</option>
                                <option value="searchBrand">3.1.2搜索（品牌）</option>
                                <option value="clientInfo">3.2消息</option>
                                <option value="focus">3.3首页焦点图</option>
                                <option value="clientInfoDetail">3.4消息详情</option>
                                <option value="newsList">4.1行业资讯</option>
                                <option value="newsDetail">4.2行业资讯详情</option>
                                <option value="shopList">5.1店铺街</option>
                                <option value="shopDetail">5.2店铺详情</option>
                                <option value="activity">6.活动</option>
                                <option value="product">7.1产品列表</option>
                                <option value="productDetailApi">7.2产品详情</option>
                                <option value="productDetailFocusImg">7.3产品焦点图</option>
                                <option value="productDetailImg">7.4产品描述图</option>
                                <option value="productType">7.5产品分类</option>
                                <option value="comment">7.6产品评价</option>
                                <option value="productLists">7.7供应商产品列表</option>
                                <option value="productDetailComment">7.8产品评价详情</option>
                                <option value="favorites">8.1我收藏的产品列表</option>
                                <option value="addFavorite">8.2添加收藏</option>
                                <option value="isFavorite">8.3收藏判断</option>
                                <option value="delFavorite">8.4取消收藏</option>
                                <option value="addAddress">9.1添加地址</option>
                                <option value="selectAddress">9.2设置默认地址</option>
                                <option value="addressDetail">9.3我的地址详情</option>
                                <option value="eidtAddress">9.4修改地址</option>
                                <option value="deleteAddress">9.5删除地址</option>
                                <option value="myaddress">9.6我的地址列表</option>
                                <option value="addcart">10.1添加购物车</option>
                                <option value="mycart">10.2查看购物车</option>
                                <option value="editCart">10.3修改购物车数量</option>
                                <option value="delCart">10.4删除购物车产品</option>
                                <option value="addPurchase">11.1直接购买</option>
                                <option value="orderProduct">11.2选中提交购物车</option>
                                <option value="addorder">11.3提交订单</option>
                                <option value="myorder">11.4我的订单</option>
                                <option value="orderdetail">11.5订单详情</option>
                                <option value="editOrderStatus">11.7确认订单</option>
                                <option value="shopOrder">11.8供应商订单</option>
                                <option value="addOrderByPurchase">11.8直接购买提交订单</option>
                                <option value="cancelOrder">11.9取消订单</option>
                                <option value="orderDetailInfo">11.10订单单个产品详情</option>
                                <option value="feedback">12.1问题反馈</option>
                                <option value="footprint">12.2访问记录</option>
                                <option value="express">12.3查看物流</option>
                                <option value="helpCenter">13.1帮助中心—常见问题类型</option>
                                <option value="helpCenterList">13.2帮助中心—常见问题列表</option>
                                <option value="helpCenterDetail">13.3帮助中心—常见问题详情</option>
                                <option value="searchHelp">13.4帮助中心—搜索帮助中心</option>
                                <option value="allUserNum">14.1注册用户数量统计</option>
                                <option value="todayBargainNum">14.2今日成交量</option>
                                <option value="todaysVisitList">14.3今日访问IP列表</option>
                                <option value="todayVisitNum">14.4今日访问IP总数</option>
                                <option value="transactionPriceSum">14.5总成交额</option>
                                <option value="province">14.6查询省市区名称列表</option>
                                <option value="logistics">14.7查询所有物流公司表</option>
                                <option value="provinceInfo">14.8查询省市区名称</option>
                                <option value="applyAgencyApi">1.5注册代理商信息接口</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="left_td">请求方式</td>
						<td>&nbsp;POST</td>
					</tr>
					<tr style="background-color:#BBB;">
						<td class="left_td">参数名</td>
						<td>&nbsp;参数值</td>
					</tr>					
						<!-- 发送手机验证码-->
                        <tr class="tr_params tr_hide captcha">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide captcha">
							<td class="left_td">source</td>
							<td><input type="text" class="api_params" name="source" size="18" value="1">
							<span style="color:red;">*</span>
							1=注册2=修改密码</td>
						</tr>
						
                         <!-- 注册普通用户信息-->
                        <tr class="tr_params tr_hide register">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide register">
							<td class="left_td">captcha</td>
							<td><input type="text" class="api_params" name="captcha" size="18" value="652323">
							<span style="color:red;">*</span>
							验证码</td>
						</tr>
                           <tr class="tr_params tr_hide register">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="会员名称">
							<span style="color:red;">*</span>
							会员名称</td>
						</tr>
                         <tr class="tr_params tr_hide register">
							<td class="left_td">pass</td>
							<td><input type="text" class="api_params" name="pass" size="18" value="123123">
							<span style="color:red;">*</span>
							密码</td>
						</tr>
                            <tr class="tr_params tr_hide register">
							<td class="left_td">type</td>
							<td><input type="text" class="api_params" name="type" size="18" value="">
							选择注册类型1为分销2为供应3为代理</td>
						</tr>
                        
                        <!-- 注册分销商信息-->
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="396">
							<span style="color:red;">*</span>
							会员ID</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">companyName</td>
							<td><input type="text" class="api_params" name="companyName" size="18" value="有家川菜馆">
							<span style="color:red;">*</span>
							店铺名称</td>
						</tr>
                           <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">mainCategory</td>
							<td><input type="text" class="api_params" name="mainCategory" size="18" value="1">
							<span style="color:red;">*</span>
							主营类目（1为遥控/电动玩具；2为早教/音乐玩具 ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">groups</td>
							<td><input type="text" class="api_params" name="groups" size="18" value="1">
							<span style="color:red;">*</span>
							运营总人数（1为1~5人；2为6~20人；3为21~50人；4为51~100人；5为100人以上</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">platform</td>
							<td><input type="text" class="api_params" name="platform" size="18" value="实体店">
							<span style="color:red;">*</span>
							销售平台</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">salesArea</td>
							<td><input type="text" class="api_params" name="salesArea" size="18" value="重庆">
							<span style="color:red;">*</span>
							销售地区</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">contact</td>
							<td><input type="text" class="api_params" name="contact" size="18" value="蒋生">
							<span style="color:red;">*</span>
							联系人</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">duty</td>
							<td><input type="text" class="api_params" name="duty" size="18" value="CEO">
							联系人职务</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">email</td>
							<td><input type="text" class="api_params" name="email" size="18" value="123123@163.com">
							<span style="color:red;">*</span>
							联系人邮箱</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">QQ</td>
							<td><input type="text" class="api_params" name="QQ" size="18" value="123123">
							<span style="color:red;">*</span>
							QQ</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">tel</td>
							<td><input type="text" class="api_params" name="tel" size="18" value="12312312">
							联系电话</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="12312312312">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">fax</td>
							<td><input type="text" class="api_params" name="fax" size="18" value="12312123">
							传真号码</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">type</td>
							<td><input type="text" class="api_params" name="type" size="18" value="1">
							<span style="color:red;">*</span>
							判断是网店还是实体店（新增）1网店2实体店</td>
						</tr>
                         <tr class="tr_params tr_hide applyConsumerApi">
							<td class="left_td">content</td>
							<td><input type="text" class="api_params" name="content" size="18" value="123123.taobao.com">
							<span style="color:red;">*</span>
							网店URL或者实体店地址</td>
						</tr>
                        
                         <!-- 注册供应商信息-->
                        <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="396">
							<span style="color:red;">*</span>
							会员ID</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">companyName</td>
							<td><input type="text" class="api_params" name="companyName" size="18" value="有家川菜馆">
							<span style="color:red;">*</span>
							公司名称</td>
						</tr>
                           <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">mainCategory</td>
							<td><input type="text" class="api_params" name="mainCategory" size="18" value="1">
							<span style="color:red;">*</span>
							主营类目（1为遥控/电动玩具；2为早教/音乐玩具 ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">groups</td>
							<td><input type="text" class="api_params" name="groups" size="18" value="1">
							<span style="color:red;">*</span>
							运营总人数（1为1~5人；2为6~20人；3为21~50人；4为51~100人；5为100人以上</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">brand</td>
							<td><input type="text" class="api_params" name="brand" size="18" value="自营品牌">
							<span style="color:red;">*</span>
							自营品牌</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">salesArea</td>
							<td><input type="text" class="api_params" name="salesArea" size="18" value="重庆">
							<span style="color:red;">*</span>
							销售地区</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">contact</td>
							<td><input type="text" class="api_params" name="contact" size="18" value="蒋生">
							<span style="color:red;">*</span>
							联系人</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">duty</td>
							<td><input type="text" class="api_params" name="duty" size="18" value="CEO">
                            <span style="color:red;">*</span>
							联系人职务</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">email</td>
							<td><input type="text" class="api_params" name="email" size="18" value="123123@163.com">
							<span style="color:red;">*</span>
							联系人邮箱</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">QQ</td>
							<td><input type="text" class="api_params" name="QQ" size="18" value="123123">
							<span style="color:red;">*</span>
							QQ</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="12312312312">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">fax</td>
							<td><input type="text" class="api_params" name="fax" size="18" value="12312123">
							传真号码</td>
						</tr>
                        <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">webSite</td>
							<td><input type="text" class="api_params" name="webSite" size="18" value="www.taozhuma.com">
							网店URL</td>
						</tr>
                        <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">mainProduct</td>
							<td><input type="text" class="api_params" name="mainProduct" size="18" value="遥控打飞机">
                            <span style="color:red;">*</span>
							主营产品</td>
						</tr>
                        <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">content</td>
							<td><input type="text" class="api_params" name="content" size="18" value="这是我司简介">
                            <span style="color:red;">*</span>
							公司简介</td>
						</tr><tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">user3cImage</td>
							<td><input type="text" class="api_params" name="user3cImage" size="18" value="12312123.jpg">
                            <span style="color:red;">*</span>
							3C认证图片</td>
						</tr><tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">attestationImage</td>
							<td><input type="text" class="api_params" name="attestationImage" size="18" value="12312123.jpg">
                            <span style="color:red;">*</span>
							营业执照</td>
						</tr>
                         <tr class="tr_params tr_hide applyManufacturerApi">
							<td class="left_td">address</td>
							<td><input type="text" class="api_params" name="address" size="18" value="重庆">
							<span style="color:red;">*</span>
							公司地址</td>
						</tr>
						
                         
                        
                        
                         <!-- 修改密码-->
                         <tr class="tr_params tr_hide password">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide password">
							<td class="left_td">pass</td>
							<td><input type="text" class="api_params" name="pass" size="18" value="123456">
							<span style="color:red;">*</span>
							新密码</td>
						</tr>
                         <tr class="tr_params tr_hide password">
							<td class="left_td">captcha</td>
							<td><input type="text" class="api_params" name="captcha" size="18" value="111111">
							<span style="color:red;">*</span>
							验证码</td>
						</tr>
                        
                        <!-- 登录-->
                          <tr class="tr_params tr_hide login">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide login">
							<td class="left_td">pass</td>
							<td><input type="text" class="api_params" name="pass" size="18" value="123456">
							<span style="color:red;">*</span>
							密码</td>
						</tr>
                        
                         <!-- 登录-->
                          <tr class="tr_params tr_hide reSetPassWord">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide reSetPassWord">
							<td class="left_td">pass</td>
							<td><input type="text" class="api_params" name="pass" size="18" value="123456">
							<span style="color:red;">*</span>
							旧密码</td>
						</tr>
                        <tr class="tr_params tr_hide reSetPassWord">
							<td class="left_td">passWork</td>
							<td><input type="text" class="api_params" name="passWork" size="18" value="123456">
							<span style="color:red;">*</span>
							新密码</td>
						</tr>
                        
                        <!-- 搜索商品-->
                         <tr class="tr_params tr_hide searchProduct">
							<td class="left_td">productNo</td>
							<td><input type="text" class="api_params" name="productNo" size="18" value="49600-8">
							搜索货号（为空搜索全部）</td>
						</tr>
                         <tr class="tr_params tr_hide searchProduct">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="美">
							搜索名称（为空搜索全部）</td>
						</tr>
                        <tr class="tr_params tr_hide searchProduct">
							<td class="left_td">sorting</td>
							<td><input type="text" class="api_params" name="sorting" size="18" value="11">
							排序（1为销量降序，11为销量升序，2为价格升序，22为价格降序， 4为点击数降序，44为点击数升序，为空ID降序）</td>
						</tr>
                           <tr class="tr_params tr_hide searchProduct">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!-- 搜索店铺-->
                        <tr class="tr_params tr_hide searchShop">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="大">
							搜索名称（为空搜索全部）</td>
						</tr>
                           <tr class="tr_params tr_hide searchShop">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!-- 搜索品牌-->
                        <tr class="tr_params tr_hide searchBrand">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="美致">
							搜索名称（为空搜索全部）</td>
						</tr>
                           <tr class="tr_params tr_hide searchBrand">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!-- 消息-->
                        <tr class="tr_params tr_hide clientInfo">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                        
                             <!-- 消息详情-->
                        <tr class="tr_params tr_hide clientInfoDetail">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="78">
							<span style="color:red;">*</span>
							消息ID</td>
						</tr>
                        
                          <!-- 行业资讯-->
                        <tr class="tr_params tr_hide newsList">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                             <!-- 行业资讯详情-->
                        <tr class="tr_params tr_hide newsDetail">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="37">
							<span style="color:red;">*</span>
							资讯ID</td>
						</tr>
                        
                           <!-- 店铺街-->
                        <tr class="tr_params tr_hide shopList">
							<td class="left_td">type</td>
							<td><input type="text" class="api_params" name="type" size="18" value="0">
							<span style="color:red;">*</span>
							（0为所有；1为遥控/电动玩具；2为早教/音乐玩具 ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）默认为0</td>
						</tr>
                          <tr class="tr_params tr_hide shopList">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                        
                   	 <!-- 店铺详情-->
                        <tr class="tr_params tr_hide shopDetail">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="3">
							<span style="color:red;">*</span>
							店铺id</td>
						</tr>
                        <tr class="tr_params tr_hide shopDetail">
							<td class="left_td">source</td>
							<td><input type="text" class="api_params" name="source" size="18" value="22">
							产品销量(1降序11升序)、金额(2降序22升序)、点击率(3降序33升序)</td>
						</tr>
                          <tr class="tr_params tr_hide shopDetail">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							产品页码</td>
						</tr>
                        
                        <!-- 活动-->
                        <tr class="tr_params tr_hide activity">
							<td class="left_td">type</td>
							<td><input type="text" class="api_params" name="type" size="18" value="3">
							<span style="color:red;">*</span>
							(每日新品:1,热销产品:2,竹马推荐:3,新品快定:4,家有好货:5)</td>
						</tr>
                        <tr class="tr_params tr_hide activity">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="22">
							页码</td>
						</tr>
                        
                         <!-- 产品列表-->
                        <tr class="tr_params tr_hide product">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="">
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide product">
							<td class="left_td">subTypeId</td>
							<td><input type="text" class="api_params" name="subTypeId" size="18" value="">
							（0为所有；1为遥控/电动玩具；2为早教/音乐玩具 ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）</td>
						</tr>
                         <tr class="tr_params tr_hide product">
							<td class="left_td">typeId</td>
							<td><input type="text" class="api_params" name="typeId" size="18" value="">
							小分类</td>
						</tr>
                        <tr class="tr_params tr_hide product">
							<td class="left_td">brandid</td>
							<td><input type="text" class="api_params" name="brandid" size="18" value="">
							品牌id</td>
						</tr>
                         <tr class="tr_params tr_hide product">
							<td class="left_td">sorting</td>
							<td><input type="text" class="api_params" name="sorting" size="18" value="">
							(0为热门(降序)，33为热门（升序），1为销售数量（降序），11为销售数量（升序），2为价格从高到低，22为价格从低到高,4为新品排序)</td>
						</tr>
                        <tr class="tr_params tr_hide product">
							<td class="left_td">display</td>
							<td><input type="text" class="api_params" name="display" size="18" value="">
							是否新品：0=》否；1=》是</td>
						</tr>
                         <tr class="tr_params tr_hide product">
							<td class="left_td">keys</td>
							<td><input type="text" class="api_params" name="keys" size="18" value="">
							搜索关键字（产品名称）</td>
						</tr>
                        <tr class="tr_params tr_hide product">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="">
							页码</td>
						</tr>
                        
                        <!--  产品详情-->
                          <tr class="tr_params tr_hide productDetailApi">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="98">
							<span style="color:red;">*</span>
							产品id</td>
						</tr>
                        <tr class="tr_params tr_hide productDetailApi">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="">
							客户id</td>
						</tr>
                        
                          <!--  产品焦点图-->
                          <tr class="tr_params tr_hide productDetailFocusImg">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="98">
							<span style="color:red;">*</span>
							产品id</td>
						</tr>
                        
                           <!--  产品描述图-->
                          <tr class="tr_params tr_hide productDetailImg">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="98">
							<span style="color:red;">*</span>
							产品id</td>
						</tr>
                        
                           <!--  产品评价-->
                          <tr class="tr_params tr_hide comment">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide comment">
							<td class="left_td">orderId</td>
							<td><input type="text" class="api_params" name="orderId" size="18" value="1224">
							<span style="color:red;">*</span>
							订单id</td>
						</tr>
                         <tr class="tr_params tr_hide comment">
							<td class="left_td">productId</td>
							<td><input type="text" class="api_params" name="productId" size="18" value="98">
							<span style="color:red;">*</span>
							产品id</td>
						</tr>
                         <tr class="tr_params tr_hide comment">
							<td class="left_td">comment</td>
							<td><input type="text" class="api_params" name="comment" size="18" value="嘿嘿，商品不错，老板娘也不错">
							<span style="color:red;">*</span>
							评价内容</td>
						</tr>
                        
                         <!--  供应商产品列表-->
                          <tr class="tr_params tr_hide productLists">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="204">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide productLists">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!--  产品评价详情-->
                          <tr class="tr_params tr_hide productDetailComment">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="204">
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide productDetailComment">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="98">
							<span style="color:red;">*</span>
							产品id</td>
						</tr>
                        
                         <!--  我收藏的产品列表-->
                          <tr class="tr_params tr_hide favorites">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide favorites">
							<td class="left_td">favType</td>
							<td><input type="text" class="api_params" name="favType" size="18" value="3">
							<span style="color:red;">*</span>
							收藏的类型：1=产品；2=店铺；3=分销产品库</td>
						</tr>
                          <tr class="tr_params tr_hide favorites">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!--  添加收藏-->
                          <tr class="tr_params tr_hide addFavorite">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide addFavorite">
							<td class="left_td">favType</td>
							<td><input type="text" class="api_params" name="favType" size="18" value="3">
							<span style="color:red;">*</span>
							收藏的类型：1=产品；2=店铺；3=分销产品库</td>
						</tr>
                          <tr class="tr_params tr_hide addFavorite">
							<td class="left_td">favSenId</td>
							<td><input type="text" class="api_params" name="favSenId" size="18" value="89">
							<span style="color:red;">*</span>
							产品或者店铺id</td>
						</tr>
                        
                         <!--  收藏判断-->
                          <tr class="tr_params tr_hide isFavorite">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide isFavorite">
							<td class="left_td">favType</td>
							<td><input type="text" class="api_params" name="favType" size="18" value="3">
							<span style="color:red;">*</span>
							收藏的类型：1=产品；2=店铺；3=分销产品库</td>
						</tr>
                          <tr class="tr_params tr_hide isFavorite">
							<td class="left_td">favSenId</td>
							<td><input type="text" class="api_params" name="favSenId" size="18" value="89">
							<span style="color:red;">*</span>
							产品或者店铺id</td>
						</tr>
                        
                         <!--  取消收藏-->
                          <tr class="tr_params tr_hide delFavorite">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide delFavorite">
							<td class="left_td">favType</td>
							<td><input type="text" class="api_params" name="favType" size="18" value="3">
							<span style="color:red;">*</span>
							收藏的类型：1=产品；2=店铺；3=分销产品库</td>
						</tr>
                          <tr class="tr_params tr_hide delFavorite">
							<td class="left_td">cids</td>
							<td><input type="text" class="api_params" name="cids" size="18" value="89,90">
							<span style="color:red;">*</span>
							(String类型,拼接)产品或者店铺id</td>
						</tr>
                        
                         <!--  添加地址-->
                          <tr class="tr_params tr_hide addAddress">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide addAddress">
							<td class="left_td">province</td>
							<td><input type="text" class="api_params" name="province" size="18" value="1">
							<span style="color:red;">*</span>
							省份(广东省)</td>
						</tr>
                          <tr class="tr_params tr_hide addAddress">
							<td class="left_td">city</td>
							<td><input type="text" class="api_params" name="city" size="18" value="1">
							<span style="color:red;">*</span>
							城市(汕头)</td>
						</tr>
                         <tr class="tr_params tr_hide addAddress">
							<td class="left_td">area</td>
							<td><input type="text" class="api_params" name="area" size="18" value="1">
							<span style="color:red;">*</span>
							区</td>
						</tr>
                        <tr class="tr_params tr_hide addAddress">
							<td class="left_td">tel</td>
							<td><input type="text" class="api_params" name="tel" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                          <tr class="tr_params tr_hide addAddress">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="嘿嘿">
							<span style="color:red;">*</span>
							收货人</td>
						</tr>
                         <tr class="tr_params tr_hide addAddress">
							<td class="left_td">address</td>
							<td><input type="text" class="api_params" name="address" size="18" value="隔壁是老王">
							<span style="color:red;">*</span>
							详细地址</td>
						</tr>
                        <tr class="tr_params tr_hide addAddress">
							<td class="left_td">postCode</td>
							<td><input type="text" class="api_params" name="postCode" size="18" value="124578">
							<span style="color:red;">*</span>
							邮编</td>
						</tr>
                        
                         <!--  设置默认地址-->
                          <tr class="tr_params tr_hide selectAddress">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide selectAddress">
							<td class="left_td">addId</td>
							<td><input type="text" class="api_params" name="addId" size="18" value="242">
							<span style="color:red;">*</span>
							选中的地址id</td>
						</tr>
                        
                         <!--  我的地址详情-->
                          <tr class="tr_params tr_hide addressDetail">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide addressDetail">
							<td class="left_td">addId</td>
							<td><input type="text" class="api_params" name="addId" size="18" value="242">
							<span style="color:red;">*</span>
							地址id</td>
						</tr>
                        
                         <!--  修改地址-->
                          <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">addId</td>
							<td><input type="text" class="api_params" name="addId" size="18" value="242">
							<span style="color:red;">*</span>
							地址id</td>
						</tr>
                        <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">province</td>
							<td><input type="text" class="api_params" name="province" size="18" value="1">
							<span style="color:red;">*</span>
							省份(广东省)</td>
						</tr>
                          <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">city</td>
							<td><input type="text" class="api_params" name="city" size="18" value="1">
							<span style="color:red;">*</span>
							城市(汕头)</td>
						</tr>
                         <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">area</td>
							<td><input type="text" class="api_params" name="area" size="18" value="1">
							<span style="color:red;">*</span>
							区</td>
						</tr>
                        <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">tel</td>
							<td><input type="text" class="api_params" name="tel" size="18" value="13800138000">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                          <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="嘿嘿">
							<span style="color:red;">*</span>
							收货人</td>
						</tr>
                         <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">address</td>
							<td><input type="text" class="api_params" name="address" size="18" value="隔壁是老王">
							<span style="color:red;">*</span>
							详细地址</td>
						</tr>
                        <tr class="tr_params tr_hide eidtAddress">
							<td class="left_td">postCode</td>
							<td><input type="text" class="api_params" name="postCode" size="18" value="124578">
							<span style="color:red;">*</span>
							邮编</td>
						</tr>
                        
                          <!--  删除地址-->
                          <tr class="tr_params tr_hide deleteAddress">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide deleteAddress">
							<td class="left_td">addId</td>
							<td><input type="text" class="api_params" name="addId" size="18" value="242">
							<span style="color:red;">*</span>
							地址id</td>
						</tr>
                        
                         <!--  我的地址列表-->
                          <tr class="tr_params tr_hide myaddress">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="512">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        
                        <!--  添加购物车-->
                          <tr class="tr_params tr_hide addcart">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide addcart">
							<td class="left_td">pid</td>
							<td><input type="text" class="api_params" name="pid" size="18" value="98">
							<span style="color:red;">*</span>
							产品id</td>
						</tr>
                        <tr class="tr_params tr_hide addcart">
							<td class="left_td">num</td>
							<td><input type="text" class="api_params" name="num" size="18" value="20">
							<span style="color:red;">*</span>
							数量</td>
						</tr>
                        
                         <!--  查看购物车-->
                          <tr class="tr_params tr_hide mycart">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        
                         <!--  修改购物车数量-->
                          <tr class="tr_params tr_hide editCart">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide editCart">
							<td class="left_td">cids</td>
							<td><input type="text" class="api_params" name="cids" size="18" value="1863:21,1864:20">
							<span style="color:red;">*</span>
							购物车Id和数量字符串数组</td>
						</tr>
                        
                         <!--  删除购物车-->
                          <tr class="tr_params tr_hide delCart">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide delCart">
							<td class="left_td">cids</td>
							<td><input type="text" class="api_params" name="cids" size="18" value="1863">
							<span style="color:red;">*</span>
							购物车Id和数量字符串数组</td>
						</tr>
                        <tr class="tr_params tr_hide delCart">
							<td class="left_td">cids</td>
							<td><input type="text" class="api_params" name="cids" size="18" value="1864">
							<span style="color:red;">*</span>
							购物车Id和数量字符串数组</td>
						</tr>
                        
                         <!--  11.1直接购买-->
                          <tr class="tr_params tr_hide addPurchase">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide addPurchase">
							<td class="left_td">pid</td>
							<td><input type="text" class="api_params" name="pid" size="18" value="98">
							<span style="color:red;">*</span>
							商品ID</td>
						</tr>
                        <tr class="tr_params tr_hide addPurchase">
							<td class="left_td">num</td>
							<td><input type="text" class="api_params" name="num" size="18" value="1864">
							<span style="color:red;">*</span>
							购买数量</td>
						</tr>
                        
                         <!--  选中提交购物车-->
                          <tr class="tr_params tr_hide orderProduct">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide orderProduct">
							<td class="left_td">cids</td>
							<td><input type="text" class="api_params" name="cids" size="18" value="1511 ,1510">
							<span style="color:red;">*</span>
							提交的购物车ID</td>
						</tr>
                        
                         <!--  提交订单-->
                          <tr class="tr_params tr_hide addorder">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide addorder">
							<td class="left_td">addressId</td>
							<td><input type="text" class="api_params" name="addressId" size="18" value="241">
							<span style="color:red;">*</span>
							我的地址ID</td>
						</tr>
                        <tr class="tr_params tr_hide addorder">
							<td class="left_td">cids</td>
							<td><input type="text" class="api_params" name="cids" size="18" value="1861">
							<span style="color:red;">*</span>
							确认的购买的商品的购物车ID</td>
						</tr>
                         <tr class="tr_params tr_hide addorder">
							<td class="left_td">buyer_message</td>
							<td><input type="text" class="api_params" name="buyer_message" size="18" value="嘿嘿">
							<span style="color:red;">*</span>
							买家留言</td>
						</tr>
                        <tr class="tr_params tr_hide addorder">
							<td class="left_td">consigneeType</td>
							<td><input type="text" class="api_params" name="consigneeType" size="18" value="1">
							<span style="color:red;">*</span>
							收货方式（1，快递2，自提）</td>
						</tr>
                         <tr class="tr_params tr_hide addorder">
							<td class="left_td">payMethod</td>
							<td><input type="text" class="api_params" name="payMethod" size="18" value="2">
							<span style="color:red;">*</span>
							支付方式：1:网银；2：支付宝 ；默认为1；</td>
						</tr>
                        
                         <!--  我的订单-->
                          <tr class="tr_params tr_hide myorder">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                         <tr class="tr_params tr_hide myorder">
							<td class="left_td">orderStatus</td>
							<td><input type="text" class="api_params" name="orderStatus" size="18" value="1">
							<span style="color:red;">*</span>
							订单状态：1待付款,2已付款，待发货,3已发货,4已收货，5已评价</td>
						</tr>
                        <tr class="tr_params tr_hide myorder">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!--  订单详情-->
                          <tr class="tr_params tr_hide orderdetail">
							<td class="left_td">oid</td>
							<td><input type="text" class="api_params" name="oid" size="18" value="1225">
							<span style="color:red;">*</span>
							订单id</td>
						</tr>
                        
                        <!--  订单确认-->
                          <tr class="tr_params tr_hide editOrderStatus">
							<td class="left_td">oid</td>
							<td><input type="text" class="api_params" name="oid" size="18" value="1225">
							<span style="color:red;">*</span>
							订单id</td>
						</tr>
                        <tr class="tr_params tr_hide editOrderStatus">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide editOrderStatus">
							<td class="left_td">status</td>
							<td><input type="text" class="api_params" name="status" size="18" value="2">
							<span style="color:red;">*</span>
							订单状态：1待付款,2已付款，待发货,3已发货,4已收货，5已确定</td>
						</tr>
                        
                        <!--  供应商订单-->
                          <tr class="tr_params tr_hide shopOrder">
							<td class="left_td">orderStatus</td>
							<td><input type="text" class="api_params" name="orderStatus" size="18" value="1">
							<span style="color:red;">*</span>
							订单状态：1待付款,2已付款，待发货,3已发货,4已收货，5已评价</td>
						</tr>
                        <tr class="tr_params tr_hide shopOrder">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							供应商id</td>
						</tr>
                        <tr class="tr_params tr_hide shopOrder">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                          <!--  直接购买提交订单-->
                          <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">addressId</td>
							<td><input type="text" class="api_params" name="addressId" size="18" value="241">
							<span style="color:red;">*</span>
							我的地址ID</td>
						</tr>
                        <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">pid</td>
							<td><input type="text" class="api_params" name="pid" size="18" value="98">
							<span style="color:red;">*</span>
							商品ID</td>
						</tr>
                         <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">buyer_message</td>
							<td><input type="text" class="api_params" name="buyer_message" size="18" value="嘿嘿">
							<span style="color:red;">*</span>
							买家留言</td>
						</tr>
                        <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">num</td>
							<td><input type="text" class="api_params" name="num" size="18" value="12">
							<span style="color:red;">*</span>
							商品数量</td>
						</tr>
                        <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">consigneeType</td>
							<td><input type="text" class="api_params" name="consigneeType" size="18" value="1">
							<span style="color:red;">*</span>
							收货方式（1，快递2，自提）</td>
						</tr>
                        <tr class="tr_params tr_hide addOrderByPurchase">
							<td class="left_td">payMethod</td>
							<td><input type="text" class="api_params" name="payMethod" size="18" value="1">
							<span style="color:red;">*</span>
							支付方式1:网银，2,：支付宝</td>
						</tr>
                        
                         <!--  问题反馈-->
                          <tr class="tr_params tr_hide feedback">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide feedback">
							<td class="left_td">type</td>
							<td><input type="text" class="api_params" name="type" size="18" value="1">
							<span style="color:red;">*</span>
							2为建议1为投诉</td>
						</tr>
                        <tr class="tr_params tr_hide feedback">
							<td class="left_td">email</td>
							<td><input type="text" class="api_params" name="email" size="18" value="98@163.com">
							<span style="color:red;">*</span>
							邮箱</td>
						</tr>
                         <tr class="tr_params tr_hide feedback">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="13800138000">
							<span style="color:red;">*</span>
							联系电话</td>
						</tr>
                        <tr class="tr_params tr_hide feedback">
							<td class="left_td">content</td>
							<td><input type="text" class="api_params" name="content" size="18" value="什么技术啊，都注册不了">
							<span style="color:red;">*</span>
							反馈内容</td>
						</tr>
                        
                        
                         <!--  访问记录-->
                          <tr class="tr_params tr_hide footprint">
							<td class="left_td">uid</td>
							<td><input type="text" class="api_params" name="uid" size="18" value="514">
							<span style="color:red;">*</span>
							会员id</td>
						</tr>
                        <tr class="tr_params tr_hide footprint">
							<td class="left_td">pageNo</td>
							<td><input type="text" class="api_params" name="pageNo" size="18" value="1">
							页码</td>
						</tr>
                        
                         <!--  查看物流 -->
                          <tr class="tr_params tr_hide express">
							<td class="left_td">orderId</td>
							<td><input type="text" class="api_params" name="orderId" size="18" value="250">
							<span style="color:red;">*</span>
							订单id</td>
						</tr>
                        
                         <!--  帮助中心—常见问题列表 -->
                          <tr class="tr_params tr_hide helpCenterList">
							<td class="left_td">typeId</td>
							<td><input type="text" class="api_params" name="typeId" size="18" value="1">
							<span style="color:red;">*</span>
							子类ID</td>
						</tr>
                        
                          <!--  帮助中心—常见问题详情-->
                          <tr class="tr_params tr_hide helpCenterDetail">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="42">
							<span style="color:red;">*</span>
							问题id</td>
						</tr>
                        
                         <!-- 帮助中心—搜索帮助中心-->
                          <tr class="tr_params tr_hide searchHelp">
							<td class="left_td">name</td>
							<td><input type="text" class="api_params" name="name" size="18" value="42">
							搜索名称（为空搜索全部）</td>
						</tr>
                        
                         <!--  取消订单-->
                          <tr class="tr_params tr_hide cancelOrder">
							<td class="left_td">oid</td>
							<td><input type="text" class="api_params" name="oid" size="18" value="42">
							<span style="color:red;">*</span>
							订单id</td>
						</tr>
                        
                        <!--  订单单个产品详情-->
                          <tr class="tr_params tr_hide orderDetailInfo">
							<td class="left_td">orderDetailId</td>
							<td><input type="text" class="api_params" name="orderDetailId" size="18" value="1446">
							<span style="color:red;">*</span>
							订单详情id</td>
						</tr>
                        
                          <!--  查询省市区名称-->
                          <tr class="tr_params tr_hide province">
							<td class="left_td">pid</td>
							<td><input type="text" class="api_params" name="pid" size="18" value="0">
							<span style="color:red;">*</span>
							地区类型id（0查询所有的省份）pid</td>
						</tr>
                        
                          <!--  查询省市区名称-->
                          <tr class="tr_params tr_hide provinceInfo">
							<td class="left_td">id</td>
							<td><input type="text" class="api_params" name="id" size="18" value="20">
							<span style="color:red;">*</span>
							省市区ID</td>
						</tr>
						
						<!-- 注册代理商信息-->
                        <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">user_id</td>
							<td><input type="text" class="api_params" name="user_id" size="18" value="3">
							<span style="color:red;">*</span>
							会员ID</td>
						</tr>
						  <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">contact</td>
							<td><input type="text" class="api_params" name="contact" size="18" value="蒋生">
							<span style="color:red;">*</span>
							联系人</td>
						</tr>
						 <tr class="tr_params tr_hide  applyAgencyApi">
							<td class="left_td">tel</td>
							<td><input type="text" class="api_params" name="tel" size="18" value="12312123">
							传真号码</td>
						</tr>
						 <tr class="tr_params tr_hide  applyAgencyApi">
							<td class="left_td">phone</td>
							<td><input type="text" class="api_params" name="phone" size="18" value="12312312312">
							<span style="color:red;">*</span>
							手机号码</td>
						</tr>
                         <tr class="tr_params tr_hide  applyAgencyApi">
							<td class="left_td">fax</td>
							<td><input type="text" class="api_params" name="fax" size="18" value="12312123">
							传真号码</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">address_home</td>
							<td><input type="text" class="api_params" name="address_home" size="18" value="重庆">
							家庭住址</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">address_company</td>
							<td><input type="text" class="api_params" name="address_company" size="18" value="重庆">
							<span style="color:red;">*</span>
							公司地址</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">corporation</td>
							<td><input type="text" class="api_params" name="corporation" size="18" value="张三">
							<span style="color:red;">*</span>
							法人代表</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">province</td>
							<td><input type="text" class="api_params" name="province" size="18" value="2">
							<span style="color:red;">*</span>
							省份</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">city</td>
							<td><input type="text" class="api_params" name="city" size="18" value="36">
							<span style="color:red;">*</span>
							城市</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">manufacturer_id</td>
							<td><input type="text" class="api_params" name="manufacturer_id" size="18" value="40">
							<span style="color:red;">*</span>
							代理厂家ID</td>
						</tr>
                          <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">company</td>
							<td><input type="text" class="api_params" name="company" size="18" value="有家川菜馆">
							<span style="color:red;">*</span>
							公司名称</td>
						</tr>
						<tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">email</td>
							<td><input type="text" class="api_params" name="email" size="18" value="123123@163.com">
							<span style="color:red;">*</span>
							联系人邮箱</td>
						</tr>
						<tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">QQ</td>
							<td><input type="text" class="api_params" name="QQ" size="18" value="123123@163.com">
							<span style="color:red;">*</span>
							联系人QQ</td>
						</tr>
                           <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">main_category</td>
							<td><input type="text" class="api_params" name="main_category" size="18" value="1">
							<span style="color:red;">*</span>
							主营类目（1为遥控/电动玩具；2为早教/音乐玩具 ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）</td>
						</tr>
						 <tr class="tr_params tr_hide applyAgencyApi">
							<td class="left_td">proxy_product</td>
							<td><input type="text" class="api_params" name="proxy_product" size="18" value="88,89">
                            <span style="color:red;">*</span>
							主营产品编号</td>
						</tr>
                        
	
					<tr>
					<td colspan="2" align="center"> <input class="api_submit" type="button" value="提 交"></td>
					</tr>
					
				</tbody></table>
				
				<h3>错误码对照表</h3>
				<table class="page_table" style="width:300px;margin:0;">
				<tbody><tr><td>0</td><td>失败</td></tr>
				<tr><td>1</td><td>成功</td></tr>
				</table>
				<br>
			</div>
			
			<!--右边栏 -->
			<div class="page_right">
				<h3>请求URL完整地址：</h3>
				<div>
					<textarea class="rs_url"></textarea>	
				</div>	
						
				<div>
					<h3>返回Json结果：</h3>
					<textarea class="rs_json"></textarea>	
				</div>	
			
			</div>
			<div style="clear:both;"></div>
			
		</div>
					<script type="text/javascript">
					$(document).ready(function(){
					
						//选择接口
						$(".api_select").change(function(){
							var val = $(this).val();
							if(val=='abc'){
								$(".tr_params").addClass("tr_hide");
							}else{
								$(".tr_params").addClass("tr_hide");
								$("."+val).removeClass("tr_hide");
							}
						});
						
						//提交接口
						$(".api_submit").click(function(){
							var api_task = $(".api_select").val();
							if(api_task != 'abc'){
							
								var url = "/api/"+api_task+".do?";
								var url_params = "";
								$("."+api_task+" input.api_params").each(function(){
									var name = $(this).attr("name");
									var v    = $(this).val();
									if(name=='title'||name=='content'){
										//v = encodeURIComponent(v);
									}
									if(url_params==""){
										url_params +=name+"="+v;
									}else{
										url_params +="&"+name+"="+v;
									}
								});	
								url = url + url_params;
								$(".rs_url").html(url);
								var timestamp = Date.parse(new Date())/1000; 
								$.ajax({ 
									url: url,
									type: "POST",
									dataType: "html",
									data:{},
									timeout: 30000,
									cache: false,
									success: function(data){
										$(".rs_json").html(data);
									},
									error: function(hrx,msg){
										alert(msg);
									}
								});
							}
						});
				
					});
					
				</script>
	</div>

</body></html>