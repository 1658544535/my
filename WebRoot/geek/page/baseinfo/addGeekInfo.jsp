<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘竹马创客中心</title>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/default.css" media="all" />
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/css/seller_common.css" type="text/css" media="all" />
    
    
    <script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery/jquery-1.10.1.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/sys_util_web.js"></script>
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/css/validate.css" /> 
	<script language="javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/testJSP/js/validate/talent-validate-all.js" ></script>
	<%-- <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
	<link type="text/css" rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/css/spage.css"/> --%>
	<script type="text/javascript" src="<s:i18n name="sysconfig"><s:text name="seller_dns" /></s:i18n>/js/My97DatePicker/WdatePicker.js"></script>
	
	<script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/geek/js/base.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>	
    <script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>	
    <script type="text/javascript" src="/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" href="<s:i18n name="sysconfig"><s:text name="houtai_dns" /></s:i18n>/seller/css/pageSellerItemPlist-73ca4d5fc7m.css" type="text/css" media="all" />
    
   
</head>

<body>
   <jsp:include page="../geekHeader.jsp"/>
        <div id="content" class="wrapper">
            <div class="pure-g admin-wrapper">
                <div class="pure-u-1 main">
                    <style>
                        .ui-radio{margin:0 5px 0 20px}.ui-nav-item-current a,.ui-nav-item-current a:hover{filter:none;background:none;color:#fff}.ui-nav-item-current .ui-nav-submain{display:none;background:#fff}.ui-nav-subitem-current a,.ui-nav-subitem-current a:hover{border:none;-webkit-box-shadow:none;box-shadow:none}.uploadify{position:relative;height:120px;width:120px;text-align:center;border:1px solid #ddd}.uploadPreview_note{width:120px;height:120px;line-height:120px}.uploadPreview_imgfile{position:absolute;width:100%;height:100%;left:0;top:0;opacity:0;z-index:3;filter:alpha(opacity=0);cursor:pointer}.uploadPreview_img{position:absolute;width:100%;height:100%;left:0;top:0;background:#fff}
                    </style>
                    <h1 class="seller-title">信息完善</h1>
                    <div class="address">
                        <div class="box">
                            <form action="insertGeekInfo.do" method="post" accept-charset="utf-8" class="ui-form p50" enctype="multipart/form-data" id="from1">
                             <input class="ui-input" name="userMakerShopPojo.shopType" type="hidden" value="" />
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 头像</label>
                                    <div class="fl picInfo">
                                        <div class="uploadify">
                                            <p class="uploadPreview_note" ><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                            <div class="uploadPreview_img" style="display:none;">
                                                <img src="" style="width:120px;height:120px;">
                                            </div>
                                            <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile1" id="upfile1" />
                                             <p class="talentErrMsg" id="message1" name="message1" style="display:none;">
                                             头像不允许为空
                                             </p>
                                            <div id="upfile1_mgId"></div>
                                        </div>
                                    </div>
                                </div>      
                                
                               
                                                      
                                
                               <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 昵称</label>
                                    <input class="ui-input" name="sysLoginPojo.name" type="text" value="${sysLoginPojo.name}" /><div id="name_mgId">
                                </div>
                             </div>
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 宝宝性别</label>
                                    <label><input class="ui-radio" name="userInfoPojo.babySex" type="radio" value="1" checked="true"/>王子</label>
                                    <label><input class="ui-radio" name="userInfoPojo.babySex" type="radio" value="2" />公主</label><div id="sex_mgId"></div>
                                </div>
                                 <div class="ui-form-item">
                                 <label class="ui-label"><span class="ui-form-required">*</span>宝宝生日</label>				             
								<input id="e" name="userInfoPojo.babyBirthday" value="" class="Wdate" type="text" onFocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
							<div id="image1Date_mgId"></div>

                             </div>  
                              <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 手机</label>
                                    <input class="ui-input" name="userInfoPojo.phone" type="text" value="${userInfoPojo.phone}" /><div id="phone_mgId"></div>
                                </div>                               
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> QQ</label>
                                    <input class="ui-input" name="userInfoPojo.QQ" type="text" value="${userInfoPojo.QQ}" /><div id="QQ_mgId"></div>
                                </div>
                                
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 公司</label>
                                    <input class="ui-input" name="manufacturerPojo.company" type="text" value="" /><div id="company_mgId"></div>
                                </div>
                             
                                <div class="ui-form-item">
                                    <label class="ui-label"><span class="ui-form-required">*</span> 地区</label>
                                    <select id="province" name="shopPojo.province" class="ui-input ui-input-checkcode" style="width: 130px;">
                                    
                                    </select>
                                    <select id="city" name="shopPojo.city" class="ui-input ui-input-checkcode" style="width: 150px;">
                           
                                    </select>
                                    <select id="area" name="shopPojo.area" class="ui-input ui-input-checkcode" style="width: 145px;">
                                   
                                    </select><div id="province_mgId"></div><div id="city_mgId"></div><div id="area_mgId"></div>
                                </div>

                                <div class="ui-form-item">
                                    <label for="address" class="ui-label">
                                        <span class="ui-form-required">*</span> 详细地址
                                    </label>
                                    <textarea name="shopPojo.address" class="ui-textarea" placeholder="不需要重复填写省市区，必须大于5个字符，小于120个字符"></textarea></select><div id="address_mgId"></div>
                                </div>
                                

                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 店铺名称
                                </label>
                                <input class="ui-input" name="shopPojo.name" type="text" value="${shopPojo.name}" /><div id="name1_mgId"></div>
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 店铺LOGO
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img src="" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile2" id="upfile2"/>
                                         <p class="talentErrMsg" id="message2" name="message2" style="display:none;">
                                             店铺Logo不允许为空
                                             </p>
                                        <div id="upfile2_mgId"></div>
                                       
                                    </div>
                                </div>
                            </div>
     
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 店铺主图
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img src="" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile3" id="upfile3"/><div id="upfile3_mgId"></div>
                                         <p class="talentErrMsg" id="message3" name="message3" style="display:none;">
                                             店铺Logo不允许为空
                                             </p>
                                    </div>
                                </div>
                            </div>
               
     

     
          
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 适用年龄
                                </label>
                                <select name="userMakerShopPojo.ageType" id="age" onchange="setSecond(this)">
                                    <option value="1">0-6月</option>
                                    <option value="2">6-12月</option>
                                    <option value="3">1-3岁</option>
                                    <option value="4">3-6岁</option>
                                    <option value="5">6-12岁</option>
                                    <option value="6">12-16岁</option>
                                   
                                </select><div id="ageType_mgId"></div>
                            </div>
                     

            
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 开发能力
                                </label>
                                <select name="userMakerShopPojo.ability" id="second">                                  
                                </select><div id="ability_mgId"></div>
                            </div>
                             <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 联系人
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.contact" type="text" value="" /><div id="contact_mgId"></div>
                            </div>
                    
               

               <!--
                            
                            
                           

                 
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 地址
                                </label>
                                <select id="province" name="" class="ui-input ui-input-checkcode" style="width: 130px;">
                                    <option value="0">
                                        请选择省市/其他...
                                    </option>
                                </select>
                                <select id="city" name="" class="ui-input ui-input-checkcode" style="width: 150px;">
                                    <option value="0">
                                        请选择城市...
                                    </option>
                                </select>
                                <select id="area" name="" class="ui-input ui-input-checkcode" style="width: 145px;">
                                    <option value="0">
                                        请选择区/县...
                                    </option>
                                </select>
                                <br/><br/>
                                <textarea name="" class="ui-textarea" placeholder="不需要重复填写省市区，必须大于5个字符，小于120个字符"></textarea>
                            </div>
                      -->

                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 联系电话
                                </label>
                                <input class="ui-input" name="shopPojo.phone" type="text" value="" /> <div id="phone_mgId"></div>
                            </div>
                     

                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 客服电话
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.servicePhone" type="text" value="" /><div id="servicePhone_mgId"></div>
                            </div>
                  
                       
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 支付宝帐号
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.alipayAccount" type="text" value="" /><div id="alipayAccount_mgId"></div>
                            </div>
                     
                   
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 支付宝实名
                                </label>
                                <input class="ui-input" name="userMakerShopPojo.alipayName" type="text" value="" /><div id="alipayName_mgId"></div>
                            </div>
                      

                      
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 单平台内容产出量
                                </label>
                                <select name="userMakerShopPojo.contentOutput">
                                    <option value="0">1/7天</option>
                                    <option value="1">1/5天</option>
                                    <option value="2">1/3天</option>
                                    <option value="3">1/1天</option>
                                    <option value="4">2/1天</option>
                                    <option value="5">3/1天以上</option>
                                </select><div id="contentOutput_mgId"></div>
                            </div>
                       

                      
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 单平台原创内容产出量
                                </label>
                                <select name="userMakerShopPojo.contentOutputOriginal">
                                    <option value="0">1/7天</option>
                                    <option value="1">1/5天</option>
                                    <option value="2">1/3天</option>
                                    <option value="3">1/1天</option>
                                    <option value="4">2/1天</option>
                                    <option value="5">3/1天以上</option>
                                </select><div id="contentOutputOriginal_mgId"></div>
                            </div>
                      

                        <div class="store-tab">
                            <div class="store-tab-title">
                                <a href="#store_company" class="ui-button ui-button-mwhite" onclick="showtype(0)">企业认证</a>
                                <a href="#store_pc" class="ui-button ui-button-mwhite" onclick="showtype(1)">个人认证</a>
                            </div>
                            <!-- 企业认证 -->
                            <div id="store_company" class="store-tab-item">
                               
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 企业全称
                                        </label>
                                        <input class="ui-input" name="userMakerShopPojo.shopTypeName1" type="text" value="" /><div id="shopTypeName1_mgId"></div>
                                   
                                </div>

                               
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 营业执照正/副本
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <div class="fl picInfo">
                                            <div class="uploadify">
                                                <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                                <div class="uploadPreview_img" style="display:none;">
                                                    <img src="" style="width:120px;height:120px;">
                                                </div>
                                                <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile4" id="upfile4"/><div id="upfile4_mgId"></div>
                                                 <p class="talentErrMsg" id="message4" name="message4" style="display:none;">
                                             营业执照正/副本不允许为空
                                             </p>
                                            </div>
                                        </div>

                                    </div>
                               

                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span>  法人身份证正反面图
                                            <p class="ui-form-explain">（加盖企业公章）</p>
                                        </label>
                                        <div class="fl picInfo">
                                            <div class="uploadify">
                                                <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                                <div class="uploadPreview_img" style="display:none;">
                                                    <img src="" style="width:120px;height:120px;">
                                                </div>
                                                <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile5" id="upfile5"/><div id="upfile5_mgId"></div>
                                                    <p class="talentErrMsg" id="message5" name="message5" style="display:none;">
                                             法人身份证正反面图不允许为空
                                             </p>
                                            </div>
                                        </div>
                                    </div>                              
                            </div>
                            <!-- 企业认证 end -->
                            <!-- 个人认证 -->
                            <div id="store_pc" class="store-tab-item">
                              
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 店铺平台
                                        </label>
                                        <input class="ui-input" name="userMakerShopPojo.shopTypeName2" type="text" value="" /><div id="shopTypeName2_mgId"></div>
                                    </div>
                             

                         
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 店铺地址
                                        </label>
                                        <input class="ui-input" name="userMakerShopPojo.shopTypeUrl" type="text" value="" /><div id="shopTypeUrl_mgId"></div>
                                    </div>
                               

                               
                                    <div class="ui-form-item">
                                        <label for="" class="ui-label">
                                            <span class="ui-form-required">*</span> 店主手持身份证照片
                                        </label>
                                        <div class="fl picInfo">
                                            <div class="uploadify">
                                                <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                                <div class="uploadPreview_img" style="display:none;">
                                                    <img src="" style="width:120px;height:120px;">
                                                </div>
                                                <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile6" id="upfile6"/><div id="upfile6_mgId"></div>
                                                      <p class="talentErrMsg" id="message6" name="message6" style="display:none;">
                                             店主手持身份证照片不允许为空
                                             </p>
                                            </div>
                                        </div>

                                    </div>
                            </div>
                            <!-- 个人认证 end -->
                        </div>
                       
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 品牌名称
                                </label>
                                <input class="ui-input" name="userMakerBrandPojo.brandName" type="text" value="" /><div id="brandName_mgId"></div>
                            </div>
                              <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 主营类目
                                </label>	
                               <select name="userMakerBrandPojo.mainCategory" id="productType"  class="floatLeft">
				               </select><div id="mainCategory_msgId"></div>
				               </div>

                     
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 状态
                                </label>
                                <p class="ui-form-text">待审核</p>
                            </div>
                     

                       
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 品牌LOGO
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img src="" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile7" id="upfile7"/><div id="upfile7_mgId"></div>
                                                 <p class="talentErrMsg" id="message7" name="message7" style="display:none;">
                                             品牌LOGO不允许为空
                                             </p>
                                    </div>
                                </div>
                            </div>
                      

                      
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 商标注册证明
                                </label>
                                <div class="fl picInfo">
                                    <div class="uploadify">
                                        <p class="uploadPreview_note"><i class="iconfont">&#xf00f7;</i>添加图片</p>
                                        <div class="uploadPreview_img" style="display:none;">
                                            <img src="" style="width:120px;height:120px;">
                                        </div>
                                        <input type="file" accept="image/png,image/gif,image/jpg,image/jpeg" class="uploadPreview_imgfile" name="upfile8" id="upfile8"/><div id="upfile8_mgId"></div>
                                           <p class="talentErrMsg" id="message8" name="message8" style="display:none;">
                                             商标注册证明不允许为空
                                             </p>
                                    </div>
                                </div>
                            </div>
                       

                      
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                    <span class="ui-form-required">*</span> 内容
                                </label>
                                <!--编辑器-->
                                <script id="editor" name="userMakerBrandPojo.content" type="text/plain" style="width:708px;height:400px;"></script> <div id="content_mgId"></div>
                             
                            </div>
                        

                        



                                <div class="ui-form-item">
                                    <button type="button" class="ui-button ui-button-lred" id="sbutton"> 
                                        确定
                                    </button>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   <jsp:include page="../geekFooter.jsp"/>
    <script>
    var v_name             = new tt.Field("昵称 ","sysLoginPojo.name").setMsgId("name_mgId");  
   // var v_sex              = new tt.Field("性别 ","userInfoPojo.sex").setMsgId("sex_mgId");
   // var v_phone              = new tt.Field("手机 ","userInfoPojo.phone").setMsgId("phone_mgId");
  //  var v_QQ              = new tt.Field("QQ ","userInfoPojo.QQ").setMsgId("QQ_mgId");
  //  var v_company              = new tt.Field("公司 ","manufacturerPojo.company").setMsgId("company_mgId");
    var v_province              = new tt.Field("省份 ","shopPojo.province").setMsgId("province_mgId");
    var v_city              = new tt.Field("城市 ","shopPojo.city").setMsgId("city_mgId");
    var v_area              = new tt.Field("区域 ","shopPojo.area").setMsgId("area_mgId");
    var v_address              = new tt.Field("地址 ","shopPojo.address").setMsgId("address_mgId");
    var v_name1              = new tt.Field("店铺名称 ","shopPojo.name").setMsgId("name1_mgId");
    var v_ageType              = new tt.Field("适用年龄 ","userMakerShopPojo.ageType").setMsgId("ageType_mgId");
    var v_ability              = new tt.Field("开发能力","userMakerShopPojo.ability").setMsgId("ability_mgId");
   // var v_tel              = new tt.Field("联系电话 ","userInfoPojo.tel").setMsgId("tel_mgId");
    var v_servicePhone              = new tt.Field("客服电话 ","userMakerShopPojo.servicePhone").setMsgId("servicePhone_mgId");
    var v_alipayAccount              = new tt.Field("支付宝账号 ","userMakerShopPojo.alipayAccount").setMsgId("alipayAccount_mgId");
    var v_alipayName              = new tt.Field("支付宝实名 ","userMakerShopPojo.alipayName").setMsgId("alipayName_mgId");
    // var contentOutput             = new tt.Field("手机 ","userInfoPojo.contentOutput").setMsgId("contentOutput_mgId");
    //var contentOutputOriginal              = new tt.Field("手机 ","userInfoPojo.phone").setMsgId("contentOutputOriginal_mgId");
    var v_shopTypeName1              = new tt.Field("企业全称 ","userMakerShopPojo.shopTypeName1").setMsgId("shopTypeName1_mgId");
    var v_shopTypeName2             = new tt.Field("店铺平台 ","userMakerShopPojo.shopTypeName2").setMsgId("shopTypeName2_mgId");
    var v_shopTypeUrl              = new tt.Field("店铺地址 ","userMakerShopPojo.shopTypeUrl").setMsgId("shopTypeUrl_mgId");
    var v_brandName              = new tt.Field("品牌名称 ","userMakerBrandPojo.brandName").setMsgId("brandName_mgId");
  //  var v_content              = new tt.Field("内容 ","userMakerBrandPojo.content").setMsgId("content_mgId");
  var v_contact             = new tt.Field("联系人 ","userMakerShopPojo.contact").setMsgId("contact_mgId");
   var v_phone             = new tt.Field("联系电话 ","shopPojo.phone").setMsgId("phone_mgId");
   var mainCategory = new tt.Field(" 主营类目 ", "userMakerBrandPojo.mainCategory").setMsgId("mainCategory_msgId");
  
    tt.vf.req.add(v_name,v_province,v_city,v_area,v_address,v_name1,v_ageType,v_ability,v_servicePhone,v_alipayAccount,v_alipayName,v_brandName,v_shopTypeUrl,v_contact,v_phone,mainCategory);
	//new tt.RV().set(new RegExp("^1(3[0-9]|4[57]|5[01256789]|7[68]|8[0-9])([0-9]{8})$"), "请输入正确手机号").add(v_phone);
    
    
    
    
    
    
        //地区联动
    //    $(function(){_init_area();});
   $(function(){
       //主营品类
       $("#productType").append("<option value=''>- 请选择 -</option>");
			                $.ajax(
					{
						type: "post",
						url: "getMainCategory.do",
						dataType: 'json',
						success: function (msg) {
							var s = "";
							var o_msg = eval(msg);
							for (var i = 0; i < o_msg.length; i++) {
								$("#productType").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
								
							}
						}
					})
            
            
        $(".store-tab-title>a").bind("click", function(){
                if(!$(this).hasClass("ui-button-mgreen")){
                    $(this).siblings().removeClass("ui-button-mgreen").addClass("ui-button-mwhite");
                    $(this).removeClass("ui-button-mwhite").addClass("ui-button-mgreen");
                    var oId = $(this).attr("href");
                    $(".store-tab-item").hide();
                    $(oId).show();
                }
                return false;
                 });
            $(".store-tab-title>a").eq(0).trigger("click");
       
      
       		//图片更换
		$(document).delegate(".uploadPreview_imgfile","change",function(){
			var _this = $(this);
			var url = _this.val();
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(_this.get(0).files[0]);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(_this.get(0).files[0]);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(_this.get(0).files[0]);
			}
			_this.siblings(".uploadPreview_img").find("img").attr("src", url);
			_this.siblings(".uploadPreview_img").show();
		 _this.siblings(".uploadPreview_note").hide()
		});
		
       
       
		select1();
		var val=$('#age').val();
		select4(val);
		$('#province').bind("change", select2);
        $('#city').bind("change", select3);
         var ue = UE.getEditor('editor');
           
   
         
         
         
         
         
         
         
         
        
	});
	
	        function showtype(v){
        	$("input[name='userMakerShopPojo.shopType']").val(v);
        	var s2 = $("input[name='userMakerShopPojo.shopTypeName2']").val();
        	var s1 = $("input[name='userMakerShopPojo.shopTypeName1']").val();
        	var sl = $("input[name='userMakerShopPojo.shopTypeUrl']").val();
	        if(v == 0){
	        	if(s2 != "" && s2 != null){
	       			document.getElementById('userMakerShopPojo.shopTypeName2').value='';
	       		}
	       		if(sl != "" && sl != null){
	       			document.getElementById('userMakerShopPojo.shopTypeUrl').value='';
	       		}
	       		tt.vf.req.add(v_shopTypeName1);
	       		tt.vf.req.rm(v_shopTypeName2,v_shopTypeUrl);
	        }else if(v == 1){
	            if(s1 != "" && s1 != null){
	        		document.getElementById('userMakerShopPojo.shopTypeName1').value='';
	        	}
	       		tt.vf.req.add(v_shopTypeName2,v_shopTypeUrl);
	        	tt.vf.req.rm(v_shopTypeName1);
	        }
	        
	        
	     $("#sbutton").click(function(){
        //获取主图信息
    	 //var oFile1 = document.getElementById('upfile1');
    	// var oFile2 = document.getElementById('upfile2');
    	// var oFile3 = document.getElementById('upfile3');   	
    	// var oFile5 = document.getElementById('upfile5');
    	// var oFile7 = document.getElementById('upfile7');
	    // var oFile4 = document.getElementById('upfile4');
     	// var oFile8 = document.getElementById('upfile8');
     	//  var oFile6 = document.getElementById('upfile6');
        	if(tt.validate()){		
        	         
    	    		
    	    		
					document.getElementById("from1").submit();
				
			}
		  });
	        
	        
	        
	        
	        
	        
	        
	        
        }
	
	
	function select1() {
		$("#province").append("<option value=''>- 请选择 -</option>");
		$.ajax(
		{
			type: "post",
			url: "getSysAreaByPid.do?sysAreaPojo.pid=0",
			dataType: 'json',
			success: function (msg) {
				var s = "";
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${shopPojo.province}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#province").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
					if("${shop.province}" == o_msg[i].id){
						select2();
					}
				}
			}
		})
	};
	function select2() {
		$("#city").html("");
        $("#city").append("<option value=''>- 请选择 -</option>");
        $.ajax(
        {
            type: "post",
            url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#province').val(),
            dataType: 'json',
            success: function (msg) {
            	var s = "";
            	var o_msg = eval(msg);
                for (var i = 0; i < o_msg.length; i++) {
                	if("${shopPojo.city}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
                    $("#city").append("<option value=" + o_msg[i].id + " "+s+">" + o_msg[i].name + "</option>");
                    if("${shop.city}" == o_msg[i].id){
						select3();
					}
                }
            }
        })
    };
	function select3() {
		$("#area").html("");
        $("#area").append("<option value=''>- 请选择 -</option>");
       $.ajax(
       {
           type: "post",
           url: "getSysAreaByPid.do?sysAreaPojo.pid="+$('#city').val(),
           dataType: 'json',
           success: function (msg) {
        	   var s = "";
				var o_msg = eval(msg);
				for (var i = 0; i < o_msg.length; i++) {
					if("${shopPojo.area}" == o_msg[i].id){
						s = "selected = selected";
					}else{
						s = "";
					}
					$("#area").append("<option value=" + o_msg[i].id + " "+s+"> " + o_msg[i].name + "</option>");
				}
           }
       })
   };
        
             
    function setSecond(obj){  
    var val = obj.value;
    $("#second").empty();
    select4(val);
     
}  

function select4(val) {
	//var ability = "${videoPojo.skill}";
    $.ajax(
    {
        type: "get",
        url: "getSkillTypes.do?ageId="+val ,
        dataType: 'json',
        success: function (msg) {
        	console.log(msg);
        	var o_msg = eval(msg);
            for (var i = 0; i < o_msg.length; i++) {
            	var selStr = "";
            	//alert(o_msg[i].skillValue);
            	//alert(ability);
           
                $("#second").append("<option value=" + o_msg[i].skillValue + selStr +">" + o_msg[i].skillName + "</option>");
            }
        }
    })
};   
        
 
        
    </script>
</body>

</html>
