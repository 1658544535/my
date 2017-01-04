package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.util.UtilDate;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.CategoryDetailSettingPojo;
import com.tzmb2c.web.pojo.CategorySettingPojo;
import com.tzmb2c.web.pojo.ExternalLinksPojo;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.pojo.HotBrandRecommendPojo;
import com.tzmb2c.web.pojo.HotPlayerPojo;
import com.tzmb2c.web.pojo.HotPushPojo;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductRecommendPojo;
import com.tzmb2c.web.pojo.ProductTypeRecommendPojo;
import com.tzmb2c.web.pojo.ShopRecommendPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBabyPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserCircleFollowPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;
import com.tzmb2c.web.pojo.UserMakerShopPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.pojo.UserPostCollectPojo;
import com.tzmb2c.web.pojo.UserPostCommentPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.AgeSkillLinkService;
import com.tzmb2c.web.service.CategoryDetailSettingService;
import com.tzmb2c.web.service.CategorySettingService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.ExternalLinksService;
import com.tzmb2c.web.service.FocusSettingService;
import com.tzmb2c.web.service.HotBrandRecommendService;
import com.tzmb2c.web.service.HotPushService;
import com.tzmb2c.web.service.KnowledgeBaseService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.ProductImagesService;
import com.tzmb2c.web.service.ProductRecommendService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeRecommendService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopRecommendService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.TaskSettingService;
import com.tzmb2c.web.service.UserBabyService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserCalendarService;
import com.tzmb2c.web.service.UserCircleFollowService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.UserGrowthLineService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserMakerBrandService;
import com.tzmb2c.web.service.UserMakerShopService;
import com.tzmb2c.web.service.UserMakerThemeService;
import com.tzmb2c.web.service.UserPostCollectService;
import com.tzmb2c.web.service.UserPostCommentService;
import com.tzmb2c.web.service.UserPostLikeHistoryService;
import com.tzmb2c.web.service.UserTalentAuthService;
import com.tzmb2c.web.service.YourFavouritesDetailService;
import com.tzmb2c.web.service.YourFavouritesService;

public class AppApiTzmeditorVerAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  // ---- spring 注入 ---- //
  @Autowired
  private SellerService sellerService;
  @Autowired
  private KnowledgeBaseService knowledgeBaseService;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private UserPostCollectService userPostCollectService;
  @Autowired
  private UserCircleFollowService userCircleFollowService;
  @Autowired
  private UserGrowthLineService userGrowthLineService;
  @Autowired
  private UserBabyService userBabyService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private PlatformSpecialService platformSpecialService;
  @Autowired
  private UserTalentAuthService userTalentAuthService;
  @Autowired
  private UserMakerBrandService userMakerBrandService;
  @Autowired
  private UserMakerThemeService userMakerThemeService;
  @Autowired
  private SocialCircleService socialCircleService;
  @Autowired
  private AgeSkillLinkService ageSkillLinkService;
  @Autowired
  private TaskSettingService taskSettingService;
  @Autowired
  private UserCalendarService userCalendarService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserPostCommentService userPostCommentService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private UserPostLikeHistoryService userPostLikeHistoryService;
  @Autowired
  private FocusSettingService focusSettingService;
  @Autowired
  private YourFavouritesService yourFavouritesService;
  @Autowired
  private YourFavouritesDetailService favouritesDetailService;
  @Autowired
  private HotPushService hotPushService;
  @Autowired
  private HotBrandRecommendService hotBrandRecommendService;
  @Autowired
  private ProductTypeRecommendService productTypeRecommendService;
  @Autowired
  private ProductRecommendService productRecommendService;
  @Autowired
  private ShopRecommendService shopRecommendService;
  @Autowired
  private UserMakerShopService userMakerShopService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private CategorySettingService categorySettingService;
  @Autowired
  private CategoryDetailSettingService categoryDetailSettingService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductImagesService productImagesService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private YourFavouritesDetailService yourFavouritesDetailService;
  @Autowired
  private ExternalLinksService externalLinksService;
  @Autowired
  private ProductTypeService productTypeService;

  // ---- 变量定义 ---- //
  /**
   * 年龄阶段值
   */
  private Long ageType;
  /**
   * pageSize显示条数
   */
  private Integer pageSize;
  /**
   * pageNo:页码
   */
  private Integer pageNo;
  /**
   * 最大值
   */
  private Integer max;
  /**
   * 成长荣誉
   */
  private String[] growthHonor;
  /**
   * 本月分数
   */
  /**
   * 上月分数
   */
  private String lastMounth;
  /**
   * 能力对应名称
   */
  private String skillNames;

  /**
   * 能力标签ID
   */
  private Long skillType;
  /**
   * 标题
   */
  private String title;
  /**
   * 文件
   */
  private File img1, img2;
  /**
   * 图片URL
   */
  private String imgurl1, imgurl2, imgurl3, imgurl4, imgurl;
  /**
   * URL
   */
  private String url;
  /**
   * 查询的品牌类型
   */
  private String brandType;
  /**
   * 查询的专题类型
   */
  private String themeType;
  /**
   * 成长阶段值
   */
  private Long stageType;
  /**
   * 作者ID
   */
  private Long authorId;
  /**
   * 内容
   */
  private String content;
  /**
   * 类型
   */
  private Integer type;
  /**
   * ID
   */
  private Long typeId;
  /**
   * uid:用户id
   */
  private Long uid;
  /**
   * num:数量
   */
  private Integer num;
  /**
   * id:序号
   */
  private Long id;// 级别ID
  /**
   * sorting:排序
   */
  private String sorting;
  /**
   * phone:手机号码
   */
  private String phone;
  /**
   * openid:微信ID
   */
  private String openid;
  /**
   * sinaid:新浪ID
   */
  private String sinaid;
  /**
   * captcha:验证码
   */
  private String captcha;
  /**
   * pass:密码
   */
  private String pass;
  /**
   * specialId专题id
   */
  private Integer specialId;
  /**
   * socialCircleId社圈id
   */
  private Long socialCircleId;
  /**
   * followType关注类型
   */
  private Integer followType;
  /**
   * circleTypeId圈子类型ID
   */
  private Long circleTypeId;
  /**
   * ftId被关注用户/圈子ID
   */
  private Long ftId;
  /**
   * 用户任务ID
   */
  private Long taskId;
  /**
   * 用户头像
   */
  private File logo;
  /**
   * 昵称
   */
  private String name;
  /**
   * 性别 1男2女
   */
  private Integer sex;
  /**
   * 宝宝性别 1男2女
   */
  private Integer babySex;
  /**
   * 宝宝生日
   */
  private String babyBirth;
  /**
   * 笔记浏览历史ID
   */
  private Long histId;
  /**
   * 笔记ID
   */
  private Long postId;

  /**
   * 已认证平台
   */
  private String platform;
  /**
   * 认证平台用户名
   */
  private String platUserName;
  /**
   * 职业身份
   */
  private String identity;
  /**
   * 粉丝数
   */
  private Integer fansNum;
  /**
   * 跨平台能力
   */
  private String crossPlatform;
  /**
   * 日内容产量
   */
  private Integer contentOutPer;
  /**
   * 日原创内容产量
   */
  private Integer orContentOutPer;
  /**
   * 合作同类平台名称
   */
  private String coopSimilarPlat;
  /**
   * 合作商品品牌名称
   */
  private String coopBrand;
  /**
   * 其他平台商业化合作模式
   */
  private String commercialType;
  /**
   * 其他平台合作报价
   */
  private String commercialPrice;
  /**
   * 样稿标题1
   */
  private String sampleTitle1;
  /**
   * 样稿链接地址1
   */
  private String sampleUrl1;
  /**
   * 样稿标题2
   */
  private String sampleTitle2;
  /**
   * 样稿链接地址2
   */
  private String sampleUrl2;
  /**
   * 样稿标题3
   */
  private String sampleTitle3;
  /**
   * 样稿链接地址3
   */
  private String sampleUrl3;
  /**
   * isFollowFlag社圈列表显示时候已关注
   */
  private Integer isFollowFlag;
  /**
   * 任务日历
   */
  private String cal;
  /**
   * 能力id
   */
  private Long abilityId;
  /**
   * 达人id
   */
  private Long talentId;
  /**
   * 创客品牌id
   */
  private Long makerBannerId;
  /**
   * 创客id
   */
  private Long makerId;
  /**
   * 达人姓名
   */
  private String realName;
  /**
   * 达人手持身份证
   */
  private File idCardImg;
  /**
   * 年龄value
   */
  private String age;
  /**
   * 店铺idshopId
   */
  private Long shopId;

  /**
   * 回复笔记id
   */
  private Long postCommentId;
  /**
   * 回复收藏笔记id
   */
  private Long collectedPostId;
  /**
   * 百度id
   */
  private String baidu_uid;

  private String regChannel;
  /**
   * 设备标识
   */
  private String imei;
  /**
   * 类目(1/一级类目;2/二级类目)
   */
  private String category;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 贴子pojo
   */
  private UserCirclePostPojo userCirclePost;
  /**
   * 知识库pojo
   */
  private KnowledgeBasePojo knowledgeBasePojo;

  private String thisMounth;

  /**
   * 宝宝昵称
   */
  private String babyNick;

  private String isDefault;
  /**
   * 宝宝Id
   */
  private String babyId;

  private UserBabyPojo userBabyPojo;
  /**
   * 搜索
   */
  private String keyword;
  /**
   * 任务时间
   */
  private String dateTime;
  /**
   * 创客专题ID
   */
  private Long pid;
  /**
   * 收藏ID
   */
  private Long cid;
  /**
   * 平台专题
   */
  private PlatformSpecialPojo platformSpecialPojo;
  /**
   * 返回json数据用
   */
  private Map<String, Object> dataMap;
  /**
   * 创客专题
   */
  private UserMakerThemePojo userMakerThemePojo;

  // ---- getter and setter ---- //

  public Long getAgeType() {
    return ageType;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public UserMakerThemePojo getUserMakerThemePojo() {
    return userMakerThemePojo;
  }

  public void setUserMakerThemePojo(UserMakerThemePojo userMakerThemePojo) {
    this.userMakerThemePojo = userMakerThemePojo;
  }

  public PlatformSpecialPojo getPlatformSpecialPojo() {
    return platformSpecialPojo;
  }

  public void setPlatformSpecialPojo(PlatformSpecialPojo platformSpecialPojo) {
    this.platformSpecialPojo = platformSpecialPojo;
  }

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public Integer getMax() {
    return max;
  }

  public void setMax(Integer max) {
    this.max = max;
  }

  public String[] getGrowthHonor() {
    return growthHonor;
  }

  public void setGrowthHonor(String[] growthHonor) {
    this.growthHonor = growthHonor;
  }

  public String getLastMounth() {
    return lastMounth;
  }

  public void setLastMounth(String lastMounth) {
    this.lastMounth = lastMounth;
  }

  public String getSkillNames() {
    return skillNames;
  }

  public void setSkillNames(String skillNames) {
    this.skillNames = skillNames;
  }

  public Long getSkillType() {
    return skillType;
  }

  public void setSkillType(Long skillType) {
    this.skillType = skillType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public File getImg1() {
    return img1;
  }

  public void setImg1(File img1) {
    this.img1 = img1;
  }

  public File getImg2() {
    return img2;
  }

  public void setImg2(File img2) {
    this.img2 = img2;
  }

  public String getImgurl1() {
    return imgurl1;
  }

  public void setImgurl1(String imgurl1) {
    this.imgurl1 = imgurl1;
  }

  public String getImgurl2() {
    return imgurl2;
  }

  public void setImgurl2(String imgurl2) {
    this.imgurl2 = imgurl2;
  }

  public String getImgurl3() {
    return imgurl3;
  }

  public void setImgurl3(String imgurl3) {
    this.imgurl3 = imgurl3;
  }

  public String getImgurl4() {
    return imgurl4;
  }

  public void setImgurl4(String imgurl4) {
    this.imgurl4 = imgurl4;
  }

  public String getImgurl() {
    return imgurl;
  }

  public void setImgurl(String imgurl) {
    this.imgurl = imgurl;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getBrandType() {
    return brandType;
  }

  public void setBrandType(String brandType) {
    this.brandType = brandType;
  }

  public String getThemeType() {
    return themeType;
  }

  public void setThemeType(String themeType) {
    this.themeType = themeType;
  }

  public Long getStageType() {
    return stageType;
  }

  public void setStageType(Long stageType) {
    this.stageType = stageType;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSorting() {
    return sorting;
  }

  public void setSorting(String sorting) {
    this.sorting = sorting;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getSinaid() {
    return sinaid;
  }

  public void setSinaid(String sinaid) {
    this.sinaid = sinaid;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public Integer getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Integer specialId) {
    this.specialId = specialId;
  }

  public Long getSocialCircleId() {
    return socialCircleId;
  }

  public void setSocialCircleId(Long socialCircleId) {
    this.socialCircleId = socialCircleId;
  }

  public Integer getFollowType() {
    return followType;
  }

  public void setFollowType(Integer followType) {
    this.followType = followType;
  }

  public Long getCircleTypeId() {
    return circleTypeId;
  }

  public void setCircleTypeId(Long circleTypeId) {
    this.circleTypeId = circleTypeId;
  }

  public Long getFtId() {
    return ftId;
  }

  public void setFtId(Long ftId) {
    this.ftId = ftId;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public File getLogo() {
    return logo;
  }

  public void setLogo(File logo) {
    this.logo = logo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public Integer getBabySex() {
    return babySex;
  }

  public void setBabySex(Integer babySex) {
    this.babySex = babySex;
  }

  public String getBabyBirth() {
    return babyBirth;
  }

  public void setBabyBirth(String babyBirth) {
    this.babyBirth = babyBirth;
  }

  public Long getHistId() {
    return histId;
  }

  public void setHistId(Long histId) {
    this.histId = histId;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getPlatUserName() {
    return platUserName;
  }

  public void setPlatUserName(String platUserName) {
    this.platUserName = platUserName;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public Integer getFansNum() {
    return fansNum;
  }

  public void setFansNum(Integer fansNum) {
    this.fansNum = fansNum;
  }

  public String getCrossPlatform() {
    return crossPlatform;
  }

  public void setCrossPlatform(String crossPlatform) {
    this.crossPlatform = crossPlatform;
  }

  public Integer getContentOutPer() {
    return contentOutPer;
  }

  public void setContentOutPer(Integer contentOutPer) {
    this.contentOutPer = contentOutPer;
  }

  public Integer getOrContentOutPer() {
    return orContentOutPer;
  }

  public void setOrContentOutPer(Integer orContentOutPer) {
    this.orContentOutPer = orContentOutPer;
  }

  public String getCoopSimilarPlat() {
    return coopSimilarPlat;
  }

  public void setCoopSimilarPlat(String coopSimilarPlat) {
    this.coopSimilarPlat = coopSimilarPlat;
  }

  public String getCoopBrand() {
    return coopBrand;
  }

  public void setCoopBrand(String coopBrand) {
    this.coopBrand = coopBrand;
  }

  public String getCommercialType() {
    return commercialType;
  }

  public void setCommercialType(String commercialType) {
    this.commercialType = commercialType;
  }

  public String getCommercialPrice() {
    return commercialPrice;
  }

  public void setCommercialPrice(String commercialPrice) {
    this.commercialPrice = commercialPrice;
  }

  public String getSampleTitle1() {
    return sampleTitle1;
  }

  public void setSampleTitle1(String sampleTitle1) {
    this.sampleTitle1 = sampleTitle1;
  }

  public String getSampleUrl1() {
    return sampleUrl1;
  }

  public void setSampleUrl1(String sampleUrl1) {
    this.sampleUrl1 = sampleUrl1;
  }

  public String getSampleTitle2() {
    return sampleTitle2;
  }

  public void setSampleTitle2(String sampleTitle2) {
    this.sampleTitle2 = sampleTitle2;
  }

  public String getSampleUrl2() {
    return sampleUrl2;
  }

  public void setSampleUrl2(String sampleUrl2) {
    this.sampleUrl2 = sampleUrl2;
  }

  public String getSampleTitle3() {
    return sampleTitle3;
  }

  public void setSampleTitle3(String sampleTitle3) {
    this.sampleTitle3 = sampleTitle3;
  }

  public String getSampleUrl3() {
    return sampleUrl3;
  }

  public void setSampleUrl3(String sampleUrl3) {
    this.sampleUrl3 = sampleUrl3;
  }

  public Integer getIsFollowFlag() {
    return isFollowFlag;
  }

  public void setIsFollowFlag(Integer isFollowFlag) {
    this.isFollowFlag = isFollowFlag;
  }

  public String getCal() {
    return cal;
  }

  public void setCal(String cal) {
    this.cal = cal;
  }

  public Long getAbilityId() {
    return abilityId;
  }

  public void setAbilityId(Long abilityId) {
    this.abilityId = abilityId;
  }

  public Long getTalentId() {
    return talentId;
  }

  public void setTalentId(Long talentId) {
    this.talentId = talentId;
  }

  public Long getMakerBannerId() {
    return makerBannerId;
  }

  public void setMakerBannerId(Long makerBannerId) {
    this.makerBannerId = makerBannerId;
  }

  public Long getMakerId() {
    return makerId;
  }

  public void setMakerId(Long makerId) {
    this.makerId = makerId;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public File getIdCardImg() {
    return idCardImg;
  }

  public void setIdCardImg(File idCardImg) {
    this.idCardImg = idCardImg;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public Long getPostCommentId() {
    return postCommentId;
  }

  public void setPostCommentId(Long postCommentId) {
    this.postCommentId = postCommentId;
  }

  public Long getCollectedPostId() {
    return collectedPostId;
  }

  public void setCollectedPostId(Long collectedPostId) {
    this.collectedPostId = collectedPostId;
  }

  public String getBaidu_uid() {
    return baidu_uid;
  }

  public void setBaidu_uid(String baidu_uid) {
    this.baidu_uid = baidu_uid;
  }

  public String getRegChannel() {
    return regChannel;
  }

  public void setRegChannel(String regChannel) {
    this.regChannel = regChannel;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public UserCirclePostPojo getUserCirclePost() {
    return userCirclePost;
  }

  public void setUserCirclePost(UserCirclePostPojo userCirclePost) {
    this.userCirclePost = userCirclePost;
  }

  public KnowledgeBasePojo getKnowledgeBasePojo() {
    return knowledgeBasePojo;
  }

  public void setKnowledgeBasePojo(KnowledgeBasePojo knowledgeBasePojo) {
    this.knowledgeBasePojo = knowledgeBasePojo;
  }

  public String getThisMounth() {
    return thisMounth;
  }

  public void setThisMounth(String thisMounth) {
    this.thisMounth = thisMounth;
  }

  public String getBabyNick() {
    return babyNick;
  }

  public void setBabyNick(String babyNick) {
    this.babyNick = babyNick;
  }

  public String getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(String isDefault) {
    this.isDefault = isDefault;
  }

  public String getBabyId() {
    return babyId;
  }

  public void setBabyId(String babyId) {
    this.babyId = babyId;
  }

  public UserBabyPojo getUserBabyPojo() {
    return userBabyPojo;
  }

  public void setUserBabyPojo(UserBabyPojo userBabyPojo) {
    this.userBabyPojo = userBabyPojo;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public void setAgeType(Long ageType) {
    this.ageType = ageType;
  }

  // ---------------------------------- 3.3 ----------------------------------------- //
  /**
   * 
   * banner接口
   * 
   * @return String
   * @throws SQLException
   */
  public String homeBanners() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> bannerList = new ArrayList<Map<String, Object>>();
    if (type == null || !(type == 1 || type == 2)) {
      msg = "类型参数错误";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("type", type);
      option.put("status", 1);
      option.put("orderBy", "sorting desc, create_date desc");
      List<FocusSettingPojo> focusSettings = focusSettingService.listPage(option);
      if (focusSettings != null && focusSettings.size() > 0) {
        Map<String, Object> item = null;
        for (FocusSettingPojo focus : focusSettings) {
          item = new HashMap<String, Object>();
          item.put(
              "banner",
              ConstParam.URL + "/upfiles/focusbanner" + File.separator
                  + StringUtil.checkVal(focus.getBanner()));
          item.put("title", StringUtil.checkVal(focus.getTitle()));
          // 1-商品，2-专场活动，3-创客专题，4-平台商品专题，5-平台内容专题，6-笔记详情，7-微页面，8-外链，9-创客
          item.put("type", StringUtil.checkVal(focus.getParamType()));
          // 1-商品ID，2-专场活动ID，3-创客专题ID，4/5-平台专题ID，6-笔记ID，7-微页面ID，8-外链ID，9-创客ID
          item.put("firstId", StringUtil.checkVal(focus.getParamId()));
          // 1-活动ID，3-创客用户ID，6-笔记用户ID
          item.put("secondId", "");
          int paramType = focus.getParamType() == null ? 0 : focus.getParamType();
          if (paramType == 1) {
            // 活动ID
            item.put("secondId", StringUtil.checkVal(focus.getActivityId()));
          } else if (paramType == 3) {
            // 3-创客用户ID
            UserMakerThemePojo makerTheme =
                userMakerThemeService.getUserMakerThemeById(focus.getParamId());
            if (makerTheme != null) {
              item.put("secondId", StringUtil.checkVal(makerTheme.getUserId()));
            }
          } else if (paramType == 6) {
            // 6-笔记用户ID
            UserCirclePostPojo post = userCirclePostService.getPostBaseInfoById(focus.getParamId());
            if (post != null) {
              item.put("secondId", StringUtil.checkVal(post.getUserId()));
            }
          } else if (paramType == 8) {
            // 外链
            ExternalLinksPojo link = externalLinksService.getById(focus.getParamId());
            item.put("firstId", StringUtil.checkVal(link.getLink()));
          }
          bannerList.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }
    map.put("result", bannerList);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 有你喜欢列表接口
   * 
   * @return String
   * @throws SQLException
   */
  public String favouriteList() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> favList = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("isDelete", 0);
    option.put("orderBy", "sorting desc, create_date desc");
    option.put("pageNo", 0);
    option.put("pageSize", 7);
    List<YourFavouritesPojo> favs = yourFavouritesService.listPage(option);
    if (favs != null && favs.size() > 0) {
      Map<String, Object> item = null;
      for (YourFavouritesPojo fav : favs) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(fav.getId()));
        item.put("image", ConstParam.URL + "/upfiles/favouritesLabel/" + File.separator
            + StringUtil.checkVal(fav.getImage()));
        item.put("name", StringUtil.checkVal(fav.getName()));
        // 1-商品列表，2-笔记列表 ，3-三级分类
        item.put("type", StringUtil.checkVal(fav.getContentType()));
        item.put("userId", "");
        item.put("postId", "");
        item.put("pid", "");
        item.put("activityId", "");
        item.put("productTypeId", "");
        int type = fav.getContentType() == null ? 0 : fav.getContentType();
        if (type == 1) {
          // 单个商品 序号最大
          option.clear();
          option.put("favId", fav.getId());
          option.put("type", 1);
          // 活动商品审核通过
          option.put("activity", 1);
          option.put("agStatus", 1);
          // 商品审核通过
          option.put("proStatus", 1);
          option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
          option.put("pageNo", 0);
          option.put("pageSize", 1);
          List<ProductPojo> products = favouritesDetailService.getProductFavouritesList(option);
          if (products != null && products.size() > 0) {
            ProductPojo product = products.get(0);
            item.put("pid", StringUtil.checkVal(product.getId()));
            item.put("activityId", StringUtil.checkVal(product.getActivityId()));
          }
        } else if (type == 2) {
          // 单个笔记 序号最大
          option.clear();
          option.put("favId", fav.getId());
          option.put("type", 2);
          option.put("status", 1);
          option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
          option.put("pageNo", 0);
          option.put("pageSize", 1);
          List<UserCirclePostPojo> posts =
              favouritesDetailService.getUserCirclePostFavouritesList(option);
          if (posts != null && posts.size() > 0) {
            UserCirclePostPojo post = posts.get(0);
            item.put("postId", StringUtil.checkVal(post.getId()));
            item.put("userId", StringUtil.checkVal(post.getUserId()));
          }
        } else if (type == 3) {
          // 三级分类
          /*ProductTypePojo ptype = new ProductTypePojo();
          ptype.setId(abilityId);
          ptype = productTypeService.findProductType(ptype);
          if (ptype != null) {
            
          }*/
          option.clear();
          option.put("favId", fav.getId());
          option.put("type", 3);
          List<YourFavouritesDetailPojo> favDetails = favouritesDetailService.listPage(option);
          if (favDetails != null && favDetails.size() > 0) {
            item.put("productTypeId", StringUtil.checkVal(favDetails.get(0).getProductTypeId()));
          }
        }
        favList.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", favList);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 有你喜欢笔记列表接口
   * 
   * @return String
   * @throws SQLException
   */
  public String favouritePosts() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> favPosts = new ArrayList<Map<String, Object>>();
    if (id == null || id == 0) {
      msg = "ID不能为空";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("favId", id);
      option.put("type", 2);
      option.put("status", 1);
      option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
      // 去掉序号最大的第一篇
      option.put("pageNo", (pageNo - 1) * pageSize + 1);
      option.put("pageSize", pageSize);
      List<UserCirclePostPojo> posts =
          favouritesDetailService.getUserCirclePostFavouritesList(option);
      if (posts != null && posts.size() > 0) {
        Map<String, Object> item = null;
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo post : posts) {
          item = new HashMap<String, Object>();
          item.put("postId", StringUtil.checkVal(post.getId()));
          url =
              post.getBanner() == null ? "" : ConstParam.URL + "/upfiles/userCirclePost/"
                  + File.separator + post.getBanner();
          item.put("banner", url);

          item.put("postTitle", StringUtil.checkVal(post.getTitle()));
          item.put("authorName", StringUtil.checkVal(post.getUserName()));
          item.put("authorId", StringUtil.checkVal(post.getUserId()));
          item.put("collectNum", StringUtil.checkVal(post.getCollectNum()));
          item.put(
              "authorLogo",
              ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(post.getUserImage()));

          item.put("authorSex", StringUtil.checkVal(post.getUserSex()));
          item.put("authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, post.getUserId(), 0)));
          item.put("authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(post.getUserId(), 0l, 0)));
          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(post.getSketch()));
          if (uid == null || uid <= 0) {
            item.put("postCollect", 0);
            item.put("authorFollow", 0);
          } else {
            // 是否关注作者
            if (userCircleFollowService.getFollowNum(uid, post.getUserId(), 0) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
            // 查询是否收藏
            if (userPostCollectService.isCollect(post.getId(), post.getUserId(), 2, uid) == 0) {
              item.put("postCollect", 0);
            } else {
              item.put("postCollect", 1);
            }
          }

          favPosts.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }
    map.put("result", favPosts);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 有你喜欢商品列表接口
   * 
   * @return String
   * @throws SQLException
   */
  public String favouriteGoods() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> favGoods = new ArrayList<Map<String, Object>>();
    if (id == null || id == 0) {
      msg = "ID不能为空";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("favId", id);
      option.put("type", 1);
      // 活动商品审核通过
      option.put("activity", 1);
      option.put("agStatus", 1);
      // 商品审核通过
      option.put("proStatus", 1);
      option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      List<ProductPojo> products = favouritesDetailService.getProductFavouritesList(option);
      if (products != null && products.size() > 0) {
        Map<String, Object> item = null;
        for (ProductPojo product : products) {
          item = new HashMap<String, Object>();
          item.put("pid", StringUtil.checkVal(product.getId()));
          item.put("activityId", StringUtil.checkVal(product.getActivityId()));
          item.put("name", StringUtil.checkVal(product.getProductName()));
          item.put(
              "image",
              ConstParam.URL + "/upfiles/product/" + File.separator
                  + StringUtil.checkVal(product.getImage()));
          item.put("sellingPrice", StringUtil.checkVal(product.getSellingPrice()));
          item.put("price", StringUtil.checkVal(product.getActivePrice()));
          favGoods.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }
    map.put("result", favGoods);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 热门话题接口
   * 
   * @return String
   * @throws SQLException
   */
  public String hotTopics() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> topics = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("orderBy", "sorting desc, createDate desc");
    option.put("pageNo", 0);
    option.put("pageSize", 4);
    List<HotPushPojo> hotPushs = hotPushService.listPage(option);
    if (hotPushs != null && hotPushs.size() > 0) {
      Map<String, Object> item = null;
      Integer type = 0;
      for (HotPushPojo hotPush : hotPushs) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(hotPush.getSpecialId()));
        item.put("title", StringUtil.checkVal(hotPush.getSpecialName()));
        item.put("banner", "");
        type = hotPush.getType();
        item.put("type", StringUtil.checkVal(type));
        item.put("uid", "");
        // 1-平台专题，2-创客专题
        if (type == 1) {
          item.put(
              "banner",
              ConstParam.URL + "/upfiles/hotpush/" + File.separator
                  + StringUtil.checkVal(hotPush.getBanner()));
          // 1-软文，2-商品，3-创好玩
          item.put("subType", StringUtil.checkVal(hotPush.getSpecialType()));
        } else if (type == 2) {
          item.put(
              "banner",
              ConstParam.URL + "/upfiles/hotpush/" + File.separator
                  + StringUtil.checkVal(hotPush.getBanner()));
          item.put("subType", "3");
          item.put("uid", StringUtil.checkVal(hotPush.getUserId()));
        }
        topics.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", topics);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 话题列表接口
   * 
   * @return String
   * @throws SQLException
   */
  public String topicList() throws SQLException {
    String msg = "";
    boolean b = false;
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 10;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> topics = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("orderBy", "sorting desc, createDate desc");
    option.put("pageNo", (pageNo - 1) * pageSize);
    option.put("pageSize", pageSize);
    List<HotPushPojo> hotPushs = hotPushService.topicListPage(option);
    if (hotPushs != null && hotPushs.size() > 0) {
      Map<String, Object> item = null;
      Integer type = 0;
      for (HotPushPojo hotPush : hotPushs) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(hotPush.getSpecialId()));
        item.put("title", StringUtil.checkVal(hotPush.getSpecialName()));
        item.put("content", StringUtil.checkVal(hotPush.getSketch()));
        item.put("banner", "");
        type = hotPush.getType();
        item.put("type", StringUtil.checkVal(type));
        item.put("uid", "");
        // 1-平台专题，2-创客专题
        if (type == 1) {
          item.put("banner", ConstParam.URL + "/upfiles/platformSpecial/" + File.separator
              + StringUtil.checkVal(hotPush.getBanner()));
          // 1-软文，2-商品，3-创好玩
          item.put("subType", StringUtil.checkVal(hotPush.getSpecialType()));
        } else if (type == 2) {
          item.put("banner", ConstParam.URL + "/upfiles/userMakerTheme/" + File.separator
              + StringUtil.checkVal(hotPush.getBanner()));
          item.put("subType", "3");
          item.put("uid", StringUtil.checkVal(hotPush.getUserId()));
        }
        topics.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", topics);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 首页-玩家宝典
   * 
   * @return
   */
  public String homePostApi() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> params2 = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> userAgelist = new ArrayList<Map<String, Object>>();

    checkUserBaby(uid);

    if (age == null || age == "") {
      error_msg = "年龄value不能为空!";
    }
    /*
     * else if (uid == null) { error_msg = "用户id不能为空!"; }
     */
    else {
      // 分页
      int ps = 10;
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      params.put("pageNo", (pageNo - 1) * ps);
      params.put("pageSize", ps);
      params.put("ageType", age);
      params.put("status", 1);

      if (typeId == null) {
        params
            .put("orderBy",
                "u.sorting desc,u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      } else {
        if (typeId == 0) {
          params.put("pageNo", Integer.valueOf((Integer) params.get("pageNo")) + 4);
          params
              .put("orderBy",
                  "u.sorting desc,u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
        } else if (typeId == 1) {
          params.put("orderBy", "u.look_num desc");
        } else if (typeId == 2) {
          params.put("orderBy", "u.update_date desc");
        } else {
          params
              .put("orderBy",
                  "u.sorting desc,u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
        }
      }

      List<UserCirclePostPojo> UserCirclePostList =
          userCirclePostService.userCirclePostList(params);
      if (UserCirclePostList.size() != 0) {
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo userCirclePostPojo : UserCirclePostList) {
          item = new HashMap<String, Object>();
          url =
              userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner();
          item.put("banner", url);
          item.put("postId", userCirclePostPojo.getId() == null ? "0" : userCirclePostPojo.getId()
              .toString());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? "0"
              : userCirclePostPojo.getCollectNum().toString());
          item.put("authorSex", userCirclePostPojo.getUserSex() == null ? "1" : userCirclePostPojo
              .getUserSex().toString());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorId", userCirclePostPojo.getUserId() == null ? "0" : userCirclePostPojo
              .getUserId().toString());
          // 查询是否收藏
          if (uid == null || uid == 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            params2.put("userId", uid);
            params2.put("typeId", userCirclePostPojo.getId());
            params2.put("authorId", userCirclePostPojo.getUserId());
            params2.put("isCollect", 1);
            int i = userPostCollectService.userPostCollectCount(params2);
            if (i > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            // 查询是否关注
            if (sellerService.getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }
          item.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1) + "");
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));
          userAgelist.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到笔记";
      }

    }
    map.put("success", success);
    map.put("result", userAgelist);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 品牌推荐接口
   * 
   * @return String
   * @throws SQLException
   */
  public String brandsRecommend() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> brands = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("orderBy", "hbr.sorting desc, hbr.create_date desc");
    option.put("brandStatus", 1);
    option.put("pageNo", 0);
    option.put("pageSize", 6);
    List<HotBrandRecommendPojo> brandRecs = hotBrandRecommendService.listPage(option);
    if (brandRecs != null && brandRecs.size() > 0) {
      Map<String, Object> item = null;
      for (HotBrandRecommendPojo brand : brandRecs) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(brand.getBrandId()));
        item.put("name", StringUtil.checkVal(brand.getName()));
        item.put("image", ConstParam.URL + "/upfiles/businessCenter/brandDic/" + File.separator
            + StringUtil.checkVal(brand.getImage()));
        brands.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", brands);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 品类推荐接口
   * 
   * @return String
   * @throws SQLException
   */
  public String productTypesRecommend() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> types = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("orderBy", "p.sorting desc, p.create_date desc");
    option.put("typeStatus", 1);
    option.put("typeVisable", 1);
    option.put("pageNo", 0);
    option.put("pageSize", 7);
    List<ProductTypeRecommendPojo> typeRecs = productTypeRecommendService.listPage(option);
    if (typeRecs != null && typeRecs.size() > 0) {
      Map<String, Object> item = null;
      for (ProductTypeRecommendPojo type : typeRecs) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(type.getProductTypeId()));
        item.put(
            "image",
            ConstParam.URL + "/upfiles/productType/" + File.separator
                + StringUtil.checkVal(type.getImage()));
        item.put("name", StringUtil.checkVal(type.getName()));
        types.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", types);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 每日上新接口
   * 
   * @return String
   * @throws SQLException
   */
  public String newEveryDay() throws SQLException {
    String msg = "";
    boolean b = false;
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 10;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("orderBy", "pr.sorting desc, pr.create_date desc");
    option.put("type", 1);
    // 商品活动状态
    option.put("activity", 1);
    option.put("agStatus", 1);
    // 商品审核状态
    option.put("proStatus", 1);
    option.put("pageNo", (pageNo - 1) * pageSize);
    option.put("pageSize", pageSize);
    List<ProductRecommendPojo> productRecs = productRecommendService.listPage(option);
    if (productRecs != null && productRecs.size() > 0) {
      Map<String, Object> item = null;
      for (ProductRecommendPojo product : productRecs) {
        item = new HashMap<String, Object>();
        item.put("pid", StringUtil.checkVal(product.getProductId()));
        item.put("activityId", StringUtil.checkVal(product.getActivityId()));
        item.put("name", StringUtil.checkVal(product.getName()));
        item.put(
            "image",
            ConstParam.URL + "/upfiles/product" + File.separator
                + StringUtil.checkVal(product.getImage()));
        item.put("sellingPrice", StringUtil.checkVal(product.getSellingPrice()));
        item.put("price", StringUtil.checkVal(product.getActivePrice()));
        item.put("recommendation", StringUtil.checkVal(product.getRecommendation()));
        products.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", products);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 销量排行接口
   * 
   * @return String
   * @throws SQLException
   */
  public String hotSellRank() throws SQLException {
    String msg = "";
    boolean b = false;
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 10;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("orderBy", "pr.sorting desc, pr.create_date desc");
    option.put("type", 2);
    // 商品活动状态
    option.put("activity", 1);
    option.put("agStatus", 1);
    // 商品审核状态
    option.put("proStatus", 1);
    option.put("pageNo", (pageNo - 1) * pageSize);
    option.put("pageSize", pageSize);
    List<ProductRecommendPojo> productRecs = productRecommendService.listPage(option);
    if (productRecs != null && productRecs.size() > 0) {
      Map<String, Object> item = null;
      for (ProductRecommendPojo product : productRecs) {
        item = new HashMap<String, Object>();
        item.put("pid", StringUtil.checkVal(product.getProductId()));
        item.put("activityId", StringUtil.checkVal(product.getActivityId()));
        item.put("name", StringUtil.checkVal(product.getName()));
        item.put(
            "image",
            ConstParam.URL + "/upfiles/product" + File.separator
                + StringUtil.checkVal(product.getImage()));
        item.put("sellingPrice", StringUtil.checkVal(product.getSellingPrice()));
        item.put("price", StringUtil.checkVal(product.getActivePrice()));
        item.put("recommendation", StringUtil.checkVal(product.getRecommendation()));
        products.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", products);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 热门达人接口
   * 
   * @return String
   * @throws SQLException
   */
  public String hotPlayerApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    List<Map<String, Object>> players = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    // 分页
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 20;
    params.put("pageNo", (pageNo - 1) * pageSize);
    params.put("pageSize", pageSize);
    params.put("status", 1);
    params.put("orderBy", "id desc");
    List<HotPlayerPojo> hotplayers = sysLoginService.findHotPlayerList(params);
    if (hotplayers != null && hotplayers.size() > 0) {
      for (HotPlayerPojo player : hotplayers) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(player.getId()));
        item.put("userName", StringUtil.checkVal(player.getUserName()));
        item.put(
            "userImage",
            ConstParam.URL + "/upfiles/userlogo" + File.separator
                + StringUtil.checkVal(player.getUserImage()));
        item.put("followNum", StringUtil.checkVal(player.getFollowNum()));
        // 1-达人，2-创客
        item.put("type", StringUtil.checkVal(player.getType()));
        players.add(item);
      }
    } else {
      error_msg = "暂无数据！";
    }
    success = true;

    map.put("success", success);
    map.put("result", players);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 达人品牌接口
   * 
   * @return String
   * @throws SQLException
   */
  public String geekBrands() throws SQLException {
    boolean success = false;
    String error_msg = "";
    List<Map<String, Object>> brands = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("pageNo", 0);
    params.put("pageSize", 4);
    // 1-精选品牌
    params.put("brandType", 1);
    params.put("status", 1);
    List<UserMakerBrandPojo> mbrands = userMakerBrandService.userMakerBrandList(params);
    if (mbrands != null && mbrands.size() > 0) {
      Map<String, Object> item = null;
      for (UserMakerBrandPojo brand : mbrands) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(brand.getId()));
        item.put("uid", StringUtil.checkVal(brand.getUserId()));
        item.put(
            "image",
            ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
                + StringUtil.checkVal(brand.getLogo()));
        item.put("name", StringUtil.checkVal(brand.getBrandName()));
        brands.add(item);
      }
    } else {
      error_msg = "暂无数据！";
    }
    success = true;

    map.put("success", success);
    map.put("result", brands);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 达人店铺接口
   * 
   * @return String
   * @throws SQLException
   */
  public String geekShops() throws SQLException {
    boolean success = false;
    String error_msg = "";
    List<Map<String, Object>> shops = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("pageNo", 0);
    params.put("pageSize", 5);
    params.put("shopStatus", 1);
    params.put("orderBy", "r.sorting desc,r.create_date desc");
    List<ShopRecommendPojo> shopRecs = shopRecommendService.listPage(params);
    if (shopRecs != null && shopRecs.size() > 0) {
      Map<String, Object> item = null;
      for (ShopRecommendPojo shop : shopRecs) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(shop.getShopId()));
        item.put("uid", StringUtil.checkVal(shop.getUserId()));
        item.put(
            "image",
            ConstParam.URL + "/upfiles/shop" + File.separator
                + StringUtil.checkVal(shop.getImages()));
        item.put("name", StringUtil.checkVal(shop.getName()));
        shops.add(item);
      }
    } else {
      error_msg = "暂无数据！";
    }
    success = true;

    map.put("success", success);
    map.put("result", shops);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 达人店铺-更多 接口
   * 
   * @return String
   * @throws SQLException
   */
  public String geekShopsMore() throws SQLException {
    boolean success = false;
    String error_msg = "";
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 10;
    List<Map<String, Object>> shops = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("pageNo", (pageNo - 1) * pageSize);
    params.put("pageSize", pageSize);
    params.put("status", 1);
    params.put("orderBy", "sorting desc,create_date desc");
    List<UserMakerShopPojo> mshops = userMakerShopService.findMakerShopList(params);
    if (mshops != null && mshops.size() > 0) {
      Map<String, Object> item = null;
      for (UserMakerShopPojo shop : mshops) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(shop.getShopId()));
        item.put("uid", StringUtil.checkVal(shop.getUserId()));
        item.put(
            "image",
            ConstParam.URL + "/upfiles/shop" + File.separator
                + StringUtil.checkVal(shop.getShopMainImage()));
        item.put("name", StringUtil.checkVal(shop.getShopName()));
        shops.add(item);
      }
    } else {
      error_msg = "暂无数据！";
    }
    success = true;

    map.put("success", success);
    map.put("result", shops);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 检查老用户的宝宝信息问题.
   * 
   * @param uid 用户ID
   * @throws SQLException
   * @throw
   * @return void
   */
  public void checkUserBaby(Long uid) throws SQLException {
    if (uid == null || uid == 0) {
      return;
    }
    Map<String, Object> param = new HashMap<String, Object>();
    // 查找宝宝信息
    param.clear();
    param.put("userId", uid);
    param.put("isDelete", 0);
    int count = userBabyService.countBy(param);
    // 判断是否有宝宝表信息
    if (count == 0) {
      UserInfoPojo uinfo = userInfoService.findUserInfoByUserId(uid);
      UserBabyPojo baby = new UserBabyPojo();
      Date now = new Date();
      int status = 0;
      if (uinfo == null) {
        baby.setUserId(uid);
        baby.setBabyName("宝宝");
        baby.setBabySex(1);
        baby.setBabyBirthday(now);
        baby.setIsDefault(1);
        baby.setIsDelete(0);
        baby.setCreateBy(uid);
        baby.setUpdateBy(uid);
        baby.setCreateDate(now);
        baby.setUpdateDate(now);
      } else {
        baby.setUserId(uid);
        baby.setBabyName("宝宝");
        baby.setBabySex(uinfo.getBabySex());
        baby.setBabyBirthday(StringUtils.isBlank(uinfo.getBabyBirthday()) ? now : StringUtil
            .stringToDate(uinfo.getBabyBirthday()));
        baby.setIsDefault(1);
        baby.setIsDelete(0);
        baby.setCreateBy(uid);
        baby.setUpdateBy(uid);
        baby.setCreateDate(now);
        baby.setUpdateDate(now);
      }
      status = userBabyService.add(baby);
      // 插入宝宝表成功，更新或插入成长线
      if (status > 0) {
        param.clear();
        param.put("userId", uid);
        List<UserGrowthLinePojo> ugrows = userGrowthLineService.userGrowthLineList(param);
        UserGrowthLinePojo ugrow = null;
        if (ugrows != null && ugrows.size() == 1) {
          ugrow = ugrows.get(0);
          if (ugrow.getBabyId() == null || ugrow.getBabyId() == 0) {
            UserGrowthLinePojo udpgrow = new UserGrowthLinePojo();
            udpgrow.setBabyId(baby.getId());
            udpgrow.setId(ugrow.getId());
            userGrowthLineService.updateUserGrowthLine(udpgrow);
          }
        } else if (ugrows == null || ugrows.size() == 0) {
          ugrow = new UserGrowthLinePojo();
          userGrowthLineService.userGrowthLineInit(ugrow);
          ugrow.setUserId(uid);
          ugrow.setBabyId(baby.getId());
          userGrowthLineService.addUserGrowthLine(ugrow);
        }
      }
    }
  }

  /**
   * 社圈-达人精选（能力标签）－笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String talentSiftAbilityNotesApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

    /*
     * if (uid == null || uid == 0) { msg = "用户ID不能为空哦！~"; } else
     */
    if (abilityId == null || abilityId == 0) {
      msg = "能力value不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      // 排序
      if (typeId == null) {
        option
            .put("orderBy",
                "u.sorting desc,u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      } else {
        if (typeId == 0) {
          option
              .put("orderBy",
                  "u.sorting desc,u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
        } else if (typeId == 1) {
          option.put("orderBy", "u.look_num desc");
        } else if (typeId == 2) {
          option.put("orderBy", "u.update_date desc");
        } else {
          option
              .put("orderBy",
                  "u.sorting desc,u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
        }
      }
      option.put("status", 1);
      option.put("skillType", abilityId);
      // option.put("userId", uid);
      // 分页
      int ps = 20;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        option.put("pageNo", 0);
      } else {
        option.put("pageNo", (pageNo - 1) * ps);
      }
      option.put("pageSize", ps);
      List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
      if (list.size() != 0) {
        String url = "";
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", StringUtil.checkVal(userCirclePostPojo.getId()));
          url =
              userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner();
          item.put("banner", url);
          item.put("postTitle", StringUtil.checkVal(userCirclePostPojo.getTitle()));
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorName", StringUtil.checkVal(userCirclePostPojo.getUserName()));
          item.put("collectNum", StringUtil.checkVal(userCirclePostPojo.getCollectNum()));
          item.put("authorId", StringUtil.checkVal(userCirclePostPojo.getUserId()));
          // 查询是否收藏和是否关注
          if (uid == null || uid == 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            params.clear();
            params.put("userId", uid);
            params.put("typeId", userCirclePostPojo.getId());
            params.put("authorId", userCirclePostPojo.getUserId());
            params.put("isCollect", 1);
            int i = userPostCollectService.userPostCollectCount(params);
            if (i > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            if (sellerService.getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }
          item.put("authorSex", StringUtil.checkVal(userCirclePostPojo.getUserSex()));
          item.put(
              "authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                  userCirclePostPojo.getUserId(), 0)));
          item.put(
              "authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(
                  userCirclePostPojo.getUserId(), 0l, 0)));
          Map<String, String> imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));
          result.add(item);
        }
        b = true;
      } else {
        msg = "查询不到笔记!";
      }
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 知识库列表接口
   * 
   * @return String
   * @throws SQLException
   */
  public String knowledgeBase() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> knows = new ArrayList<Map<String, Object>>();
    if (ageType == null || "".equals(ageType) || 0 == ageType) {
      msg = "年龄参数不能为空！";
    } else if (skillType == null || "".equals(skillType) || 0 == skillType) {
      msg = "能力参数不能为空！";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("ageType", ageType);
      option.put("skillType", skillType);
      option.put("status", 1);
      option.put("orderBy", "update_date desc,create_date desc");
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      List<KnowledgeBasePojo> knowledges = knowledgeBaseService.listPage(option);
      if (knowledges != null && knowledges.size() > 0) {
        Map<String, Object> item = null;
        for (KnowledgeBasePojo know : knowledges) {
          item = new HashMap<String, Object>();
          item.put("id", StringUtil.checkVal(know.getId()));
          item.put("title", StringUtil.checkVal(know.getTitle()));
          item.put("content", StringUtil.checkVal(know.getSketch()));
          item.put("banner", ConstParam.URL + "/upfiles/knowledgeBase/" + File.separator
              + StringUtil.checkVal(know.getSmallIcon()));
          knows.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }
    map.put("result", knows);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 创客专题详情-主题/帖子收藏（取消收藏）
   * 
   * @return
   * @throws SQLException
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String getUserMakerPostCollectApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (type == null || !(type == 1 || type == 2 || type == 3)) {
      msg = "类型不能为空哦！~";
    } else if (authorId == null || type == 3 && authorId != 0 || type != 3 && authorId == 0) {
      msg = "作者ID不能为空哦！~";
    } else if (typeId == null || typeId == 0) {
      msg = "话题/帖子ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("userId", uid);
      option.put("type", type);
      option.put("typeId", typeId);
      option.put("authorId", authorId);
      List<UserPostCollectPojo> list = userPostCollectService.userPostCollectList(option);
      UserPostCollectPojo userPostCollectPojo = new UserPostCollectPojo();
      UserMakerThemePojo userMakerThemePojo = new UserMakerThemePojo();
      UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
      if (list == null || list.size() == 0) {
        userPostCollectPojo.setUserId(uid);
        userPostCollectPojo.setType(type);
        userPostCollectPojo.setTypeId(typeId);
        userPostCollectPojo.setAuthorId(authorId);
        userPostCollectPojo.setIsCollect(1);
        int i = userPostCollectService.addUserPostCollect(userPostCollectPojo);
        if (i > 0) {
          if (type == 1) {
            userMakerThemePojo.setId(typeId);
            userMakerThemePojo.setCollectNum(1l);
            userMakerThemeService.increaseUserMakerThemeNumById(userMakerThemePojo);
          } else if (type == 2) {
            userCirclePostPojo.setId(typeId);
            userCirclePostPojo.setCollectNum(1l);
            userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
          } else if (type == 3) {
            platformSpecialService.increaseCollectsById(typeId, 1l);
          }
        }
        msg = "收藏成功!";
      } else {
        userPostCollectPojo.setId(list.get(0).getId());
        if (list.get(0).getIsCollect() == 0) {
          userPostCollectPojo.setIsCollect(1);
          int i = userPostCollectService.updateUserPostCollect(userPostCollectPojo);
          if (i > 0) {
            if (type == 1) {
              userMakerThemePojo.setId(typeId);
              userMakerThemePojo.setCollectNum(1l);
              userMakerThemeService.increaseUserMakerThemeNumById(userMakerThemePojo);
            } else if (type == 2) {
              userCirclePostPojo.setId(typeId);
              userCirclePostPojo.setCollectNum(1l);
              userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
            } else if (type == 3) {
              platformSpecialService.increaseCollectsById(typeId, 1l);
            }
          }
          msg = "收藏成功!";
        } else if (list.get(0).getIsCollect() == 1) {
          userPostCollectPojo.setIsCollect(0);
          int i = userPostCollectService.updateUserPostCollect(userPostCollectPojo);
          if (i > 0) {
            if (type == 1) {
              userMakerThemePojo.setId(typeId);
              userMakerThemePojo.setCollectNum(-1l);
              userMakerThemeService.decreaseUserMakerThemeNumById(userMakerThemePojo);
            } else if (type == 2) {
              userCirclePostPojo.setId(typeId);
              userCirclePostPojo.setCollectNum(-1l);
              userCirclePostService.decreaseUserCirclePostNumById(userCirclePostPojo);
            } else if (type == 3) {
              platformSpecialService.decreaseCollectsById(typeId, -1l);
            }
          }
          msg = "取消收藏成功!";
        }
      }
      result = 1;
      b = true;
    }
    map.put("result", StringUtil.checkVal(result));
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 收藏的话题列表
   * 
   * @param uid 用户ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String collectedTopics() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserPostCollectPojo> collects = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 20;
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      // 1-已收藏 0-取消收藏
      params.put("isCollect", 1);
      collects = userPostCollectService.userTopicCollectList(params);
      if (collects != null && collects.size() > 0) {
        int type = 0;
        for (UserPostCollectPojo cpost : collects) {
          post = new HashMap<String, Object>();
          post.put("cid", StringUtil.checkVal(cpost.getId()));
          post.put("id", StringUtil.checkVal(cpost.getTypeId()));
          post.put("title", StringUtil.checkVal(cpost.getTitle()));
          post.put("collectNum", StringUtil.checkVal(cpost.getCollectNum()));
          // 1-软文专题2-商品专题3-创客专题
          post.put("type", StringUtil.checkVal(cpost.getSubType()));
          // 1-创客主题 3-平台专题
          type = cpost.getType();
          if (type == 1) {
            post.put(
                "banner",
                ConstParam.URL + "/upfiles/userMakerTheme/"
                    + StringUtil.checkVal(cpost.getBanner()));
            post.put("authorName", StringUtil.checkVal(cpost.getUserName()));
            post.put("authorLogo", ConstParam.URL + "/upfiles/userlogo" + File.separator
                + StringUtil.checkVal(cpost.getUserImage()));
            post.put("authorSex", StringUtil.checkVal(cpost.getUserSex()));
            post.put("authorId", StringUtil.checkVal(cpost.getAuthorId()));
            post.put("authorFansNum", StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                cpost.getAuthorId(), 0)));
            post.put("authorCollectNum", StringUtil.checkVal(userCircleFollowService.getFollowNum(
                cpost.getAuthorId(), 0l, 0)));
            if (userCircleFollowService.getFollowNum(uid, cpost.getAuthorId(), 0) == 0) {
              post.put("authorFollow", "0");
            } else {
              post.put("authorFollow", "1");
            }
            if (userPostCollectService.isCollect(cpost.getTypeId(), cpost.getAuthorId(), 1, uid) == 0) {
              post.put("isCollect", "0");
            } else {
              post.put("isCollect", "1");
            }
          } else if (type == 3) {
            post.put(
                "banner",
                ConstParam.URL + "/upfiles/platformSpecial/"
                    + StringUtil.checkVal(cpost.getBanner()));
            post.put("authorName", "");

            post.put("authorLogo", "");
            post.put("authorSex", "");
            post.put("authorId", "");
            post.put("authorFansNum", "");
            post.put("authorCollectNum", "");
            post.put("authorFollow", "");
            if (userPostCollectService.isCollect(cpost.getTypeId(), cpost.getAuthorId(), 3, uid) == 0) {
              post.put("isCollect", "0");
            } else {
              post.put("isCollect", "1");
            }
          }
          result.add(post);
        }
      } else {
        msg = "您没有收藏的话题!";
      }
      b = true;

    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 删除收藏话题
   * 
   * @return
   */
  public String delCollectedTopic() {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (cid == null || cid == 0) {
      msg = "收藏id不能为空!";
    } else if (type == null || !(type == 1 || type == 2 || type == 3)) {
      // 1-软文专题，2-商品专题，3-创客专题;
      msg = "话题类型不能为空!";
    } else {
      UserPostCollectPojo collect = userPostCollectService.getUserPostCollectById(cid);
      if (type == 1 || type == 2) {
        // 平台专题
        type = 3;
      } else if (type == 3) {
        // 创客专题
        type = 1;
      }
      if (collect != null && collect.getType() == type
          && collect.getUserId().longValue() == uid.longValue()) {
        int i = userPostCollectService.deleteUserPostCollectById(cid);
        if (i > 0) {
          if (type == 1) {
            // 1-主题
            UserMakerThemePojo theme = new UserMakerThemePojo();
            theme.setId(collect.getTypeId());
            theme.setCollectNum(-1l);
            userMakerThemeService.decreaseUserMakerThemeNumById(theme);
          } else if (type == 3) {
            // 3-平台
            try {
              platformSpecialService.decreaseCollectsById(collect.getTypeId(), -1l);
            } catch (SQLException e) {
              e.printStackTrace();
              System.out.println(">>>>>>减少话题收藏数失败，id=" + collect.getTypeId());
            }
          }
          b = true;
          msg = "取消成功!";
        } else {
          msg = "取消失败!";
        }
      } else {
        msg = "查询不到该用户收藏话题!";
      }
    }
    map.put("result", "");
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 用户关注达人列表
   * 
   * @return
   */
  public String followPlayers() {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserCircleFollowPojo> follows = null;
    Map<String, Object> params = null;
    Map<String, Object> item = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      params.put("userType", 1);
      // 1-已关注 0-取消关注
      params.put("isFollow", 1);
      follows = userCircleFollowService.findUserConcernList(params);
      if (follows != null && follows.size() > 0) {
        for (UserCircleFollowPojo follow : follows) {
          item = new HashMap<String, Object>();
          item.put("fid", StringUtil.checkVal(follow.getId()));
          item.put("userId", StringUtil.checkVal(follow.getUserId()));
          item.put("userImage",
              ConstParam.URL + "/upfiles/userlogo/" + StringUtil.checkVal(follow.getUserImage()));
          item.put("userName", StringUtil.checkVal(follow.getUserName()));
          // 1-达人 3-创客
          item.put("type", StringUtil.checkVal(follow.getType()));
          // 达人或创客的粉丝数
          item.put("followNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, follow.getUserId(), 0)));
          result.add(item);
        }
      } else {
        msg = "您没有关注的达人!";
      }
      b = true;
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 关注用户/圈子
   * 
   * @return
   */
  public String addConcernApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    UserCircleFollowPojo userCircleFollow = new UserCircleFollowPojo();
    if (uid == null || uid.equals("")) {
      error_msg = "会员Id不能为空!";
    } else if (followType == null || followType == 0) {
      error_msg = "关注类型不能为空!";
    } else if (followType != 1 && followType != 2 && followType != 3) {
      error_msg = "关注类型错误!";
    } else if (ftId == null || ftId == 0) {
      error_msg = "关注圈子/用户id不能为空!";
    } else {
      params.put("userId", uid);
      params.put("type", followType);
      params.put("typeId", ftId);
      UserCircleFollowPojo userCircleFollowPojo =
          userCircleFollowService.findUserCircleFollowByParams(params);
      if (userCircleFollowPojo != null) {
        // 判断是否存在该数据
        if (userCircleFollowPojo.getIsFollow() == 1) {
          error_msg = "已关注，请不要重复关注!";
        } else if (userCircleFollowPojo.getIsFollow() == 0) {
          userCircleFollowPojo = new UserCircleFollowPojo();
          userCircleFollowPojo.setUserId(uid);
          userCircleFollowPojo.setType(followType);
          userCircleFollowPojo.setTypeId(ftId);
          userCircleFollowPojo.setIsFollow(1);
          int i = userCircleFollowService.updateUserCircleFollow(userCircleFollowPojo);
          if (i > 0) {
            if (followType == 2) {
              // 增加社圈关注数量
              SocialCirclePojo socialCirclePojo = new SocialCirclePojo();
              socialCirclePojo.setFollowNum(1l);
              socialCirclePojo.setId(ftId);
              socialCircleService.addFollowNumById(socialCirclePojo);
            }
            error_msg = "关注成功!";
            success = true;
          } else {
            error_msg = "关注失败!";
          }
        } else {
          error_msg = "关注标记有误!";
        }
      } else {
        // 添加关注
        userCircleFollow = new UserCircleFollowPojo();
        userCircleFollow.setUserId(uid);
        userCircleFollow.setType(followType);
        userCircleFollow.setTypeId(ftId);
        userCircleFollow.setCreateBy(uid);
        userCircleFollow.setCreateDate(new Date());
        userCircleFollow.setUpdateBy(uid);
        userCircleFollow.setUpdateDate(new Date());
        int i = userCircleFollowService.addUserCircleFollow(userCircleFollow);
        if (i > 0) {
          if (followType == 2) {
            // 增加社圈关注数量
            SocialCirclePojo socialCirclePojo = new SocialCirclePojo();
            socialCirclePojo.setFollowNum(1l);
            socialCirclePojo.setId(ftId);
            socialCircleService.addFollowNumById(socialCirclePojo);
          }
          success = true;
          error_msg = "关注成功!";
        } else {
          error_msg = "关注失败!";
        }
      }
    }
    map.put("success", success);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 取消关注用户/圈子
   * 
   * @return
   * @throws SQLException
   */
  public String delConcernApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      error_msg = "会员Id不能为空!";
    } else if (ftId == null || ftId.equals("")) {
      error_msg = "被关注用户/圈子Id不能为空!";
    } else if (followType == null || followType.equals("")) {
      error_msg = "被关注用户/圈子类型不能为空!";
    } else if (followType != 1 && followType != 2 && followType != 3) {
      error_msg = "关注类型错误!";
    } else {
      params.put("userId", uid);
      params.put("type", followType);
      params.put("typeId", ftId);
      // 查询用户
      UserCircleFollowPojo userCircleFollowPojo =
          userCircleFollowService.findUserCircleFollowByParams(params);
      if (userCircleFollowPojo != null) {
        if (userCircleFollowPojo.getIsFollow() == 0) {
          error_msg = "已经取消关注，请不要重复取消！";
        } else if (userCircleFollowPojo.getIsFollow() == 1) {
          UserCircleFollowPojo userCircleFollow = new UserCircleFollowPojo();
          userCircleFollow.setUserId(uid);
          userCircleFollow.setTypeId(ftId);
          userCircleFollow.setType(followType);
          userCircleFollow.setIsFollow(0);
          int i = userCircleFollowService.updateUserCircleFollow(userCircleFollow);
          if (i > 0) {
            if (followType == 2) {
              // 减少社圈关注数量
              SocialCirclePojo socialCirclePojo = new SocialCirclePojo();
              socialCirclePojo.setFollowNum(2l);
              socialCirclePojo.setId(ftId);
              socialCircleService.delFollowNumById(socialCirclePojo);
            }
            success = true;
            error_msg = "取消关注成功！";
          } else {
            error_msg = "取消关注失败！";
          }
        }
      } else {
        error_msg = "已经取消关注，请不要重复取消！";
      }
    }

    map.put("success", success);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 社圈-达人-发布的笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String userCircleNotesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> userCirclePostList = new ArrayList<Map<String, Object>>();
    if (talentId != null && talentId != 0) {
      // 分页
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("userId", talentId);
      params.put("status", 1);
      params.put("isDelete", 0);
      List<UserCirclePostPojo> ucpList = userCirclePostService.userCirclePostList(params);
      if (ucpList.size() != 0) {
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo ucp : ucpList) {
          item = new HashMap<String, Object>();
          url =
              ucp.getBanner() == null ? "" : ConstParam.URL + "/upfiles/userCirclePost"
                  + File.separator + ucp.getBanner();
          item.put("banner", url);
          item.put("postId", StringUtil.checkVal(ucp.getId()));// 帖子id
          item.put("postTitle", StringUtil.checkVal(ucp.getTitle()));
          item.put("auditStatus", StringUtil.checkVal(ucp.getStatus()));
          item.put("authorName", StringUtil.checkVal(ucp.getUserName()));
          item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
          // 查询是否收藏和是否关注
          if (uid == null || uid == 0) {
            item.put("authorFollow", "0");
            item.put("postCollect", "0");
          } else {
            if (userPostCollectService.isCollect(ucp.getId(), ucp.getUserId(), 2, uid) == 0) {
              item.put("postCollect", "0");
            } else {
              item.put("postCollect", "1");
            }
            if (userCircleFollowService.getFollowNum(uid, ucp.getUserId(), 0) == 0) {
              item.put("authorFollow", "0");
            } else {
              item.put("authorFollow", "1");
            }
          }
          item.put("collectNum", StringUtil.checkVal(ucp.getCollectNum()));
          item.put("authorId", StringUtil.checkVal(ucp.getUserId()));
          item.put("authorSex", StringUtil.checkVal(ucp.getUserSex()));
          item.put("authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, ucp.getUserId(), 0)));
          item.put("authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(ucp.getUserId(), 0l, 0)));

          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(ucp.getSketch()));
          userCirclePostList.add(item);
        }
      } else {
        error_msg = "还没有笔记~";
      }
      success = true;
    } else {
      error_msg = "用户id不能为空!";
    }
    map.put("success", success);
    map.put("result", userCirclePostList);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 社圈-昵称-用户信息
   * 
   * @return
   * @throws Exception
   */
  public String circleUserInfoApi() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = null;
    Map<String, Object> userInfo = new HashMap<String, Object>();
    /*
     * if (uid == null || uid == 0) { error_msg = "用户ID不能为空!"; } else
     */
    if (ftId == null || ftId == 0) {
      error_msg = "达人ID不能为空!";
    } else {
      UserInfoPojo userInfoPojo = userInfoService.findUserInfoByUserId(ftId);
      if (userInfoPojo != null) {
        // 查询是否关注
        if (uid == null || uid == 0) {
          userInfo.put("isConcern", 0);
        } else {
          int i = userCircleFollowService.getFollowNum(uid, ftId, 0);
          userInfo.put("isConcern", i > 0 ? 1 : 0);
        }
        userInfo.put("userName", userInfoPojo.getUserName());
        userInfo.put("userId", userInfoPojo.getUserId());
        userInfo.put("userSex", StringUtil.checkVal(userInfoPojo.getSex()));
        userInfo.put("userImage", userInfoPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userInfoPojo.getUserImage());
        // 查询宝宝信息
        params = new HashMap<String, Object>();
        params.put("userId", userInfoPojo.getUserId());
        params.put("isDefault", 1);
        params.put("isDelete", 0);
        userBabyPojo = userBabyService.getByParams(params);
        if (userBabyPojo == null) {
          userInfo.put("age", "刚出生");
          error_msg = "查询不到宝宝信息!";
        } else if (userBabyPojo.getBabyBirthday() == null) {
          userInfo.put("age", "刚出生");
          error_msg = "查询不到宝宝生日!";
        } else {
          // 查询年龄
          double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
          String ageStr = "";
          if (age <= 0.0) {
            ageStr = "刚出生";
          } else if (age < 1.0) {
            ageStr = (int) Math.floor(age * 100) + "个月";
          } else {
            int year = (int) Math.floor(age);
            int month = (int) Math.floor((age - year) * 100);
            ageStr = year + "岁";
            if (month > 0.0) {
              ageStr += month + "个月";
            }
          }
          userInfo.put("age", ageStr);
        }
        // 参与的圈组
        params.clear();
        params.put("userId", ftId);
        params.put("type", 2);
        params.put("isFollow", 1);
        int j = userCircleFollowService.userCircleFollowCount(params);
        userInfo.put("circleNum", j);
        // 发布的笔记
        params.clear();
        params.put("userId", ftId);
        params.put("is_delete", 0);
        params.put("status", 1);
        int k = userCirclePostService.userCirclePostCount(params);
        userInfo.put("postNum", k);
        // 粉丝数
        userInfo.put("fansNum",
            StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, ftId, 0)));

        // 关注的人数
        userInfo.put("followNum",
            StringUtil.checkVal(userCircleFollowService.getFollowNum(ftId, 0l, 0)));
        success = true;
      } else {
        error_msg = "找不到该用户!";
      }
    }
    map.put("success", success);
    map.put("result", userInfo);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 创客专题详情-主题/专题/帖子评论列表
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerPostCommetListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = null;

    if (type == null || !(type == 1 || type == 2 || type == 3)) {
      // 评论类型（1主题，2帖子，3平台）
      msg = "类型不能为空哦！~";
    } else if (uid == null || type == 3 && uid != 0 || type != 3 && uid == 0) {
      msg = "作者用户ID不能为空哦！~";
    } else if (typeId == null || typeId == 0) {
      msg = "主题/帖子ID不能为空哦！~";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", 1);
      option.put("type", type);
      option.put("typeId", typeId);
      option.put("authorId", uid);
      option.put("option", "userName2");
      int count = userPostCommentService.countBy(option);
      // 评论条数
      result.put("count", StringUtil.checkVal(count));
      option.put("pageNo", (pageNo - 1) * 10);
      option.put("pageSize", 10);
      List<UserPostCommentPojo> list = userPostCommentService.listPage(option);
      if (list.size() != 0) {
        for (UserPostCommentPojo userPostCommentPojo : list) {
          item = new HashMap<String, Object>();
          item.put(
              "logo",
              ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(userPostCommentPojo.getUserImage2()));
          item.put("userName", StringUtil.checkVal(userPostCommentPojo.getUserName2()));
          item.put("content", StringUtil.checkVal(userPostCommentPojo.getContent()));
          item.put("userId", StringUtil.checkVal(userPostCommentPojo.getUserId()));
          items.add(item);
        }
        result.put("commentList", items);
      }
      b = true;
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 创客专题详情-主题/帖子评论回复
   * 
   * @return
   * @throws SQLException
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String getUserMakerPostReplyApi() throws SQLException {
    String msg = "评论失败！~";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (type == null || !(type == 1 || type == 2 || type == 3)) {
      // 评论类型（1主题，2帖子，3平台）
      msg = "类型不能为空哦！~";
    } else if (authorId == null || type == 3 && authorId != 0 || type != 3 && authorId == 0) {
      msg = "作者ID不能为空哦！~";
    } else if (typeId == null || typeId == 0) {
      msg = "主题/帖子ID不能为空哦！~";
    } else if (content == null || "".equals(content)) {
      msg = "评论内容不能为空哦！~";
    } else {
      UserPostCommentPojo userPostCommentPojo = new UserPostCommentPojo();
      userPostCommentPojo.setUserId(uid);
      userPostCommentPojo.setType(type);
      userPostCommentPojo.setTypeId(typeId);
      userPostCommentPojo.setAuthorId(authorId);
      userPostCommentPojo.setContent(content);
      userPostCommentPojo.setStatus(1);
      result = userPostCommentService.add(userPostCommentPojo);
      if (result > 0 && type == 2) {
        UserCirclePostPojo userCirclePost = new UserCirclePostPojo();
        userCirclePost.setCommentNum(1l);
        userCirclePost.setId(typeId);
        userCirclePostService.increaseUserCirclePostNumById(userCirclePost);
      }
      b = true;
      msg = "评论成功！~";
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 知识库-玩家宝典列表
   * 
   * @return
   * @throws SQLException
   */
  public String getKnowledgeBaseCanonListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = null;
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("pageNo", 0);
    option.put("pageSize", 20);
    option.put("orderBy", "u.collect_num desc");
    if (ageType == null || ageType <= 0) {
      ageType = 1l;
    }
    if (skillType == null || skillType <= 0) {
      skillType = 1l;
    }
    option.put("ageType", ageType);
    option.put("skillType", skillType);
    List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
    if (list != null && list.size() > 0) {
      Map<String, String> imgInfo = null;
      String url = "";
      for (UserCirclePostPojo userCirclePostPojo : list) {
        item = new HashMap<String, Object>();
        item.put("postId", StringUtil.checkVal(userCirclePostPojo.getId()));
        url =
            userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner();
        item.put("banner", url);
        item.put("postTitle", StringUtil.checkVal(userCirclePostPojo.getTitle()));
        item.put("authorName", StringUtil.checkVal(userCirclePostPojo.getUserName()));
        item.put("authorId", StringUtil.checkVal(userCirclePostPojo.getUserId()));
        item.put("collectNum", StringUtil.checkVal(userCirclePostPojo.getCollectNum()));
        item.put(
            "authorLogo",
            ConstParam.URL + "/upfiles/userlogo" + File.separator
                + StringUtil.checkVal(userCirclePostPojo.getUserImage()));

        item.put("authorSex", StringUtil.checkVal(userCirclePostPojo.getUserSex()));
        item.put(
            "authorFansNum",
            StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                userCirclePostPojo.getUserId(), 0)));
        item.put("authorCollectNum", StringUtil.checkVal(userCircleFollowService.getFollowNum(
            userCirclePostPojo.getUserId(), 0l, 0)));
        imgInfo = SellerService.getImgWH(url);
        item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
        item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
        item.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));
        if (uid == null || uid <= 0) {
          item.put("postCollect", 0);
          item.put("authorFollow", 0);
        } else {
          // 是否关注作者
          if (userCircleFollowService.getFollowNum(uid, userCirclePostPojo.getUserId(), 0) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          // 查询是否收藏
          if (userPostCollectService.isCollect(userCirclePostPojo.getId(),
              userCirclePostPojo.getUserId(), 2, uid) == 0) {
            item.put("postCollect", 0);
          } else {
            item.put("postCollect", 1);
          }
        }
        result.add(item);
      }
    } else {
      msg = "暂无数据!";
    }
    b = true;
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 社圈详情-最热笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String hotCircleNotesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> hotPosts = new ArrayList<Map<String, Object>>();
    if (socialCircleId == null || socialCircleId == 0) {
      error_msg = "社圈ID不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      Map<String, Object> item = null;

      // 热门帖子
      params.put("pageNo", 0);
      params.put("pageSize", 20);
      params.put("status", 1);
      params.put("circleId", socialCircleId);
      params.put("orderBy",
          "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      List<UserCirclePostPojo> posts = userCirclePostService.userCirclePostList(params);
      if (posts != null && posts.size() > 0) {
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo post : posts) {
          item = new HashMap<String, Object>();
          item.put("postId", StringUtil.checkVal(post.getId()));
          item.put("postTitle", StringUtil.checkVal(post.getTitle()));
          url =
              post.getBanner() == null ? "" : ConstParam.URL + "/upfiles/userCirclePost"
                  + File.separator + post.getBanner();
          item.put("banner", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
              + StringUtil.checkVal(post.getBanner()));
          item.put("authorName", StringUtil.checkVal(post.getUserName()));
          item.put("authorId", StringUtil.checkVal(post.getUserId()));
          item.put(
              "authorLogo",
              ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(post.getUserImage()));
          item.put("collectNum", StringUtil.checkVal(post.getCollectNum()));
          item.put("commentNum", StringUtil.checkVal(post.getCommentNum()));
          item.put("likeNum", StringUtil.checkVal(post.getLikeNum()));
          String dTime =
              UtilDate.timeDifference(post.getCreateDateStr(), UtilDate.getDateFormatter());
          item.put("dTime", dTime + "");
          // 查询是否关注和是否收藏
          if (uid == null || uid == 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            if (userPostCollectService.isCollect(post.getId(), post.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            if (userCircleFollowService.getFollowNum(uid, post.getUserId(), 0) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }
          item.put("authorSex", StringUtil.checkVal(post.getUserSex()));
          item.put("authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, post.getUserId(), 0)));
          item.put("authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(post.getUserId(), 0l, 0)));
          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(post.getSketch()));
          hotPosts.add(item);
        }
      } else {
        error_msg = "暂无数据!";
      }
      success = true;
    }
    map.put("success", success);
    map.put("result", hotPosts);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 社圈详情-精品笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String qualityCircleNotesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> postList = new ArrayList<Map<String, Object>>();
    if (socialCircleId == null || socialCircleId == 0) {
      error_msg = "社圈ID不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      Map<String, Object> item = null;
      // 普通贴子
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      params.put("status", 1);
      params.put("circleId", socialCircleId);
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("orderBy", "u.create_date desc");
      List<UserCirclePostPojo> posts = userCirclePostService.userCirclePostList(params);
      if (posts != null && posts.size() > 0) {
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo post : posts) {
          item = new HashMap<String, Object>();
          item.put("postId", StringUtil.checkVal(post.getId()));
          item.put("postTitle", StringUtil.checkVal(post.getTitle()));
          url =
              post.getBanner() == null ? "" : ConstParam.URL + "/upfiles/userCirclePost"
                  + File.separator + post.getBanner();
          item.put("banner", url);
          item.put("authorName", StringUtil.checkVal(post.getUserName()));
          item.put("authorId", StringUtil.checkVal(post.getUserId()));
          item.put(
              "authorLogo",
              ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(post.getUserImage()));
          item.put("collectNum", StringUtil.checkVal(post.getCollectNum()));
          item.put("commentNum", StringUtil.checkVal(post.getCommentNum()));
          item.put("likeNum", StringUtil.checkVal(post.getLikeNum()));
          String dTime =
              UtilDate.timeDifference(post.getCreateDateStr(), UtilDate.getDateFormatter());
          item.put("dTime", dTime + "");
          // 查询是否关注和是否收藏
          if (uid == null || uid == 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            if (userPostCollectService.isCollect(post.getId(), post.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            if (userCircleFollowService.getFollowNum(uid, post.getUserId(), 0) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }
          item.put("authorSex", StringUtil.checkVal(post.getUserSex()));
          item.put("authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, post.getUserId(), 0)));
          item.put("authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(post.getUserId(), 0l, 1)));
          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(post.getSketch()));
          postList.add(item);
        }
      } else {
        error_msg = "暂无数据!";
      }
      success = true;
    }
    map.put("success", success);
    map.put("result", postList);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 创客店铺商品列表
   * 
   * @return String
   * @throws SQLException
   */
  public String geekGoods() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;

      Map<String, Object> option = new HashMap<String, Object>();
      Date curr = new Date();
      option.put("orderBy", "pr.sorting desc, pr.create_date desc");
      option.put("type", 3);
      option.put("beginTime", StringUtil.dateString(curr));
      option.put("endTime", StringUtil.dateString(curr));
      // 商品活动状态
      option.put("status", 1);
      // 商品审核状态
      option.put("proStatus", 1);
      // 专场排期完成
      option.put("special", 1);
      option.put("specStatus", 4);
      // 专场未删除
      option.put("specDelete", "0");
      option.put("userId", uid);
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      List<ActivityGoodsPojo> goods = activityGoodsService.findActivityGoodsList(option);
      if (goods != null && goods.size() > 0) {
        Map<String, Object> item = null;
        for (ActivityGoodsPojo product : goods) {
          item = new HashMap<String, Object>();
          item.put("pid", StringUtil.checkVal(product.getProductId()));
          item.put("activityId", StringUtil.checkVal(product.getTimeId()));
          item.put("name", StringUtil.checkVal(product.getProductName()));
          item.put(
              "image",
              ConstParam.URL + "/upfiles/product" + File.separator
                  + StringUtil.checkVal(product.getProductImage()));
          item.put("price", StringUtil.checkVal(product.getActivePrice()));
          products.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }

    map.put("result", products);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 产品品类/年龄列表
   * 
   * @return String
   * @throws SQLException
   */
  public String categoryList() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> categorys = new ArrayList<Map<String, Object>>();
    if (type == null || !(type == 1 || type == 2)) {
      msg = "类型不能为空!";
    } else {

      Map<String, Object> option = new HashMap<String, Object>();
      option.put("orderBy", "sorting desc, create_date desc");
      option.put("type", type);
      option.put("status", 1);
      List<CategorySettingPojo> cates = categorySettingService.listPage(option);
      if (cates != null && cates.size() > 0) {
        Map<String, Object> item = null;
        Map<String, Object> item2 = null;
        List<CategoryDetailSettingPojo> cateDetails = null;
        List<Map<String, Object>> cateDts = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", 1);
        params.put("visable", 1);
        for (CategorySettingPojo category : cates) {
          item = new HashMap<String, Object>();
          item.put("pid", StringUtil.checkVal(category.getId()));
          item.put("name", StringUtil.checkVal(category.getTitle()));
          params.put("categoryId", category.getId());
          cateDetails = categoryDetailSettingService.listPage(params);
          cateDts = new ArrayList<Map<String, Object>>();
          if (cateDetails != null && cateDetails.size() > 0) {
            for (CategoryDetailSettingPojo detail : cateDetails) {
              item2 = new HashMap<String, Object>();
              item2.put("id", StringUtil.checkVal(detail.getTypeId()));
              item2.put("name", StringUtil.checkVal(detail.getName()));
              item2.put("image", detail.getImage() == null ? "" : ConstParam.URL
                  + "/upfiles/productType" + File.separator + detail.getImage());
              cateDts.add(item2);
            }
          }
          item.put("type", cateDts);
          categorys.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }

    map.put("result", categorys);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 判断是否收藏
   * 
   * @return
   * @throws SQLException
   */
  public String isCollected() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;

    if (type == null || !(type == 1 || type == 2 || type == 3)) {
      // 评论类型（1主题，2帖子，3平台）
      msg = "类型不能为空哦！~";
    } else if (typeId == null || typeId == 0) {
      msg = "话题/帖子ID不能为空哦！~";
    } else {
      if (uid == null || uid == 0) {
        result = 0;
      } else {
        result = userPostCollectService.isCollect(typeId, null, type, uid);
        if (result > 0) {
          result = 1;
        }
      }
      b = true;
    }
    map.put("result", StringUtil.checkVal(result));
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 我的圈-圈组热帖列表
   * 
   * @return
   * @throws SQLException
   */
  public String hotPostApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Long> scs = new ArrayList<Long>();
    List<Map<String, Object>> hotPostList = new ArrayList<Map<String, Object>>();
    if (uid != null && uid != 0) {
      // 分页
      int pageSize = 10;
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);

      params.put("userId", uid);
      params.put("isFollow", 1);
      params.put("type", 2);
      params.put("status", 1);
      // 查询用户关注的圈子
      List<UserCircleFollowPojo> ucfList = userCircleFollowService.findSocialCircleList(params);
      if (ucfList != null && ucfList.size() > 0) {
        for (UserCircleFollowPojo ucf : ucfList) {
          scs.add(ucf.getSocialCircleId());
        }
        if (scs.size() > 0) {
          option.put("scs", scs);
          option.put("status", 1);
          List<UserCirclePostPojo> ucpList = userCirclePostService.findBycircleIds(option);
          Map<String, String> imgInfo = null;
          String url = "";
          for (UserCirclePostPojo ucp : ucpList) {
            item = new HashMap<String, Object>();
            item.put("postId", StringUtil.checkVal(ucp.getId()));
            item.put("authorName", StringUtil.checkVal(ucp.getUserName()));
            item.put("authorId", StringUtil.checkVal(ucp.getUserId()));
            item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
            item.put("postTitle", StringUtil.checkVal(ucp.getTitle()));
            item.put("commentNum", StringUtil.checkVal(ucp.getCommentNum()));
            item.put("collectNum", StringUtil.checkVal(ucp.getCollectNum()));
            item.put("lookNum", StringUtil.checkVal(ucp.getLookNum()));
            url =
                ucp.getBanner() == null ? "" : ConstParam.URL + "/upfiles/userCirclePost"
                    + File.separator + ucp.getBanner();
            item.put("banner", url);
            // 查询是否收藏
            params.clear();
            params.put("userId", uid);
            params.put("typeId", ucp.getId());
            params.put("authorId", ucp.getUserId());
            params.put("isCollect", 1);
            int i = userPostCollectService.userPostCollectCount(params);
            if (i > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            item.put("authorSex", StringUtil.checkVal(ucp.getUserSex()));
            item.put("authorFansNum",
                StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, ucp.getUserId(), 0)));
            item.put("authorCollectNum",
                StringUtil.checkVal(userCircleFollowService.getFollowNum(ucp.getUserId(), 0l, 0)));
            if (userCircleFollowService.getFollowNum(uid, ucp.getUserId(), 0) == 0) {
              item.put("authorFollow", "0");
            } else {
              item.put("authorFollow", "1");
            }
            imgInfo = SellerService.getImgWH(url);
            item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
            item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
            item.put("content", StringUtil.checkVal(ucp.getSketch()));
            hotPostList.add(item);
          }
          if (hotPostList.size() == 0) {
            error_msg = "暂无数据!";
          }
          success = true;
        } else {
          error_msg = "您还未未关注圈子!";
        }
      } else {
        error_msg = "您还未未关注圈子!";
      }

    } else {
      error_msg = "用户id不能为空!";
    }
    map.put("success", success);
    map.put("result", hotPostList);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 商品图文详情
   * 
   * @throw
   * @return String
   */
  public String productDetailApp() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Object> list2 = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    if (id == null) {
      msg = "产品ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      Map<String, Object> map1 = null;
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null && productPojo.getStatus() == 1) {
        Integer ver = productPojo.getVersion() == null || productPojo.getVersion() == 0 ? 0 : 1;
        String proUrl = "";
        if (1 == ver) {
          proUrl = ConstParam.URL + "/getProductInfoView.do?id=" + id;
        } else {
          List<ProductImagesPojo> list = productImagesService.productForId(productPojo.getId());
          if (list != null && list.size() > 0) {
            String image = "";
            Image img = null;
            for (ProductImagesPojo productImagesPojo : list) {
              map1 = new HashMap<String, Object>();

              image = ConstParam.URL + "/upfiles/product/" + productImagesPojo.getImages();
              map1.put("image", image);

              try {
                img = Image.getInstance(new URL(image));
                map1.put("width", img.getWidth());
                map1.put("height", img.getHeight());
              } catch (BadElementException e) {
                e.printStackTrace();
                map1.put("width", 0);
                map1.put("height", 0);
              } catch (MalformedURLException e) {
                e.printStackTrace();
                map1.put("width", 0);
                map1.put("height", 0);
              } catch (IOException e) {
                e.printStackTrace();
                map1.put("width", 0);
                map1.put("height", 0);
              }
              list2.add(map1);
            }
            if (list2.size() > 0) {
              image = ConstParam.URL + "/images/guarantee.jpg";
              map1 = new HashMap<String, Object>();
              map1.put("image", image);
              try {
                img = Image.getInstance(new URL(image));
                map1.put("width", img.getWidth());
                map1.put("height", img.getHeight());
              } catch (BadElementException e) {
                e.printStackTrace();
                map1.put("width", 0);
                map1.put("height", 0);
              } catch (MalformedURLException e) {
                e.printStackTrace();
                map1.put("width", 0);
                map1.put("height", 0);
              } catch (IOException e) {
                e.printStackTrace();
                map1.put("width", 0);
                map1.put("height", 0);
              }
              list2.add(map1);
            }
          } else {
            msg = "该产品暂无图片数据";
          }
        }
        result.put("version", ver);
        result.put("url", proUrl);
        result.put("images", list2);
      } else {
        msg = "该产品暂无图片数据";
      }
      b = true;
    }
    map.put("result", result);
    map.put("success", b);
    map.put("error_msg", msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 可视化专题标签
   * 
   * @throws SQLException
   * @return String
   */
  public String getVisTag() throws SQLException {
    if (id == null || id == 0 || type == null || type == 0) {
      System.out.println(">>>>>> id和type都不能为空!");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      Map<String, Object> option = new HashMap<String, Object>();
      params.put("id", id);
      params.put("status", 1);
      if (type == 1) {
        platformSpecialPojo = platformSpecialService.findSpecialByParams(params);
        if (platformSpecialPojo == null && platformSpecialPojo.getStatus() == 1) {
          System.out.println(">>>>>> id=" + id + "平台专题查不到!");
        } else {
          dataMap = new HashMap<String, Object>();
          dataMap.put("ageType", platformSpecialPojo.getAgeType() == null ? ""
              : platformSpecialPojo.getAgeType());
          dataMap.put("ageTypeStr", platformSpecialPojo.getAgeTypeStr() == null ? ""
              : platformSpecialPojo.getAgeTypeStr());
          dataMap.put("skillType", platformSpecialPojo.getSkillType() == null ? ""
              : platformSpecialPojo.getSkillType());
          dataMap.put("skillTypeStr", platformSpecialPojo.getSkillTypeStr() == null ? ""
              : platformSpecialPojo.getSkillTypeStr());
          dataMap.put("productType", platformSpecialPojo.getProductType() == null ? ""
              : platformSpecialPojo.getProductType());
          dataMap.put("productTypeStr", platformSpecialPojo.getProductTypeStr() == null ? ""
              : platformSpecialPojo.getProductTypeStr());
          // 查询有你喜欢
          YourFavouritesPojo yourFavourites =
              yourFavouritesService.getById(platformSpecialPojo.getYourFavouritesId());
          if (yourFavourites != null && yourFavourites.getContentType() != null) {
            dataMap.put("yourId", yourFavourites.getId());
            dataMap.put("sort", yourFavourites.getContentType());
            dataMap.put("yourName",
                "".equals(yourFavourites.getName()) ? "" : yourFavourites.getName());
            if (yourFavourites.getContentType() == 1) {
              // 单个商品 序号最大
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 1);
              // 活动商品审核通过
              option.put("activity", 1);
              option.put("agStatus", 1);
              // 商品审核通过
              option.put("proStatus", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              List<ProductPojo> products = favouritesDetailService.getProductFavouritesList(option);
              if (products != null && products.size() > 0) {
                ProductPojo product = products.get(0);
                dataMap.put("pid", StringUtil.checkVal(product.getId()));
                dataMap.put("activityId", StringUtil.checkVal(product.getActivityId()));
              }
            } else if (yourFavourites.getContentType() == 2) {
              // 单个笔记 序号最大
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 2);
              option.put("status", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              List<UserCirclePostPojo> posts =
                  favouritesDetailService.getUserCirclePostFavouritesList(option);
              if (posts != null && posts.size() > 0) {
                UserCirclePostPojo post = posts.get(0);
                dataMap.put("postId", StringUtil.checkVal(post.getId()));
                dataMap.put("userId", StringUtil.checkVal(post.getUserId()));
              }
            } else if (yourFavourites.getContentType() == 3) {
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 3);
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              List<YourFavouritesDetailPojo> yfds = favouritesDetailService.listPage(option);
              if (yfds != null && yfds.size() > 0) {
                YourFavouritesDetailPojo yfd = yfds.get(0);
                dataMap.put("productTypeId", StringUtil.checkVal(yfd.getProductTypeId()));
              }
            }
          }

        }
      } else if (type == 2) {
        userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
        if (userMakerThemePojo != null && userMakerThemePojo.getStatus() == 1
            && userMakerThemePojo.getIsDelete() == 0) {
          dataMap = new HashMap<String, Object>();
          dataMap.put("ageType",
              userMakerThemePojo.getAgeType() == null ? "" : userMakerThemePojo.getAgeType());
          dataMap.put("ageTypeStr", userMakerThemePojo.getAgeTypeStr() == null ? ""
              : userMakerThemePojo.getAgeTypeStr());
          dataMap.put("skillType", userMakerThemePojo.getSkillType() == null ? ""
              : userMakerThemePojo.getSkillType());
          dataMap.put("skillTypeStr", userMakerThemePojo.getSkillTypeStr() == null ? ""
              : userMakerThemePojo.getSkillTypeStr());
          dataMap.put("productType", userMakerThemePojo.getProductType() == null ? ""
              : userMakerThemePojo.getProductType());
          dataMap.put("productTypeStr", userMakerThemePojo.getProductTypeStr() == null ? ""
              : userMakerThemePojo.getProductTypeStr());
          // 查询有你喜欢
          YourFavouritesPojo yourFavourites =
              yourFavouritesService.getById(userMakerThemePojo.getYourFavouritesId());
          if (yourFavourites != null && yourFavourites.getContentType() != null) {
            dataMap.put("yourId", yourFavourites.getId());
            dataMap.put("sort", yourFavourites.getContentType());
            dataMap.put("yourName",
                "".equals(yourFavourites.getName()) ? "" : yourFavourites.getName());
            if (yourFavourites.getContentType() == 1) {
              // 单个商品 序号最大
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 1);
              // 活动商品审核通过
              option.put("activity", 1);
              option.put("agStatus", 1);
              // 商品审核通过
              option.put("proStatus", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              List<ProductPojo> products = favouritesDetailService.getProductFavouritesList(option);
              if (products != null && products.size() > 0) {
                ProductPojo product = products.get(0);
                dataMap.put("pid", StringUtil.checkVal(product.getId()));
                dataMap.put("activityId", StringUtil.checkVal(product.getActivityId()));
              }
            } else if (yourFavourites.getContentType() == 2) {
              // 单个笔记 序号最大
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 2);
              option.put("status", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              List<UserCirclePostPojo> posts =
                  favouritesDetailService.getUserCirclePostFavouritesList(option);
              if (posts != null && posts.size() > 0) {
                UserCirclePostPojo post = posts.get(0);
                dataMap.put("postId", StringUtil.checkVal(post.getId()));
                dataMap.put("userId", StringUtil.checkVal(post.getUserId()));
              }
            } else if (yourFavourites.getContentType() == 3) {
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 3);
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              List<YourFavouritesDetailPojo> yfds = favouritesDetailService.listPage(option);
              if (yfds != null && yfds.size() > 0) {
                YourFavouritesDetailPojo yfd = yfds.get(0);
                dataMap.put("productTypeId", StringUtil.checkVal(yfd.getProductTypeId()));
              }
            }
          }
        } else {
          System.out.println(">>>>>> id=" + id + " 创客专题查不到!");
        }
      } else if (type == 3) {
        userCirclePost = userCirclePostService.getUserCirclePostById(id);
        if (userCirclePost != null && userCirclePost.getStatus() == 1
            && userCirclePost.getIsDelete() == 0) {
          dataMap = new HashMap<String, Object>();
          dataMap.put("ageType",
              userCirclePost.getAgeType() == null ? "" : userCirclePost.getAgeType());
          dataMap.put("ageTypeStr",
              userCirclePost.getAgeTypeName() == null ? "" : userCirclePost.getAgeTypeName());
          dataMap.put("skillType",
              userCirclePost.getSkillType() == null ? "" : userCirclePost.getSkillType());
          dataMap.put("skillTypeStr", userCirclePost.getSkillTypeName() == null ? ""
              : userCirclePost.getSkillTypeName());
          dataMap.put("productType",
              userCirclePost.getProductType() == null ? "" : userCirclePost.getProductType());
          dataMap.put("productTypeStr", userCirclePost.getProductTypeName() == null ? ""
              : userCirclePost.getProductTypeName());
          // 查询有你喜欢
          YourFavouritesPojo yourFavourites =
              yourFavouritesService.getById(userCirclePost.getYourFavouritesId());
          if (yourFavourites != null && yourFavourites.getContentType() != null) {
            dataMap.put("yourId", yourFavourites.getId());
            dataMap.put("sort", yourFavourites.getContentType());
            dataMap.put("yourName",
                "".equals(yourFavourites.getName()) ? "" : yourFavourites.getName());
            if (yourFavourites.getContentType() == 1) {
              // 单个商品 序号最大
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 1);
              // 活动商品审核通过
              option.put("activity", 1);
              option.put("agStatus", 1);
              // 商品审核通过
              option.put("proStatus", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              List<ProductPojo> products = favouritesDetailService.getProductFavouritesList(option);
              if (products != null && products.size() > 0) {
                ProductPojo product = products.get(0);
                dataMap.put("pid", StringUtil.checkVal(product.getId()));
                dataMap.put("activityId", StringUtil.checkVal(product.getActivityId()));
              }
            } else if (yourFavourites.getContentType() == 2) {
              // 单个笔记 序号最大
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 2);
              option.put("status", 1);
              option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              List<UserCirclePostPojo> posts =
                  favouritesDetailService.getUserCirclePostFavouritesList(option);
              if (posts != null && posts.size() > 0) {
                UserCirclePostPojo post = posts.get(0);
                dataMap.put("postId", StringUtil.checkVal(post.getId()));
                dataMap.put("userId", StringUtil.checkVal(post.getUserId()));
              }
            } else if (yourFavourites.getContentType() == 3) {
              option.clear();
              option.put("favId", yourFavourites.getId());
              option.put("type", 3);
              option.put("pageNo", 0);
              option.put("pageSize", 1);
              option.put("orderBy", "sorting desc, create_date desc");
              List<YourFavouritesDetailPojo> yfds = favouritesDetailService.listPage(option);
              if (yfds != null && yfds.size() > 0) {
                YourFavouritesDetailPojo yfd = yfds.get(0);
                dataMap.put("productTypeId", StringUtil.checkVal(yfd.getProductTypeId()));
              }
            }
          }
        } else {
          System.out.println(">>>>>> id=" + id + " 笔记查不到!");
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 更多分类-品牌接口
   * 
   * @return String
   * @throws SQLException
   */
  public String brandList() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> brands = new ArrayList<Map<String, Object>>();
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 18;
    Map<String, Object> option = new HashMap<String, Object>();
    Date curr = new Date();
    option.put("currentTimeStr", StringUtil.dateString(curr));
    option.put("pageNo", (pageNo - 1) * pageSize);
    option.put("pageSize", pageSize);
    List<UserBrandPojo> ubrands = userBrandService.findActiveBrandList(option);
    if (ubrands != null && ubrands.size() > 0) {
      Map<String, Object> item = null;
      for (UserBrandPojo brand : ubrands) {
        item = new HashMap<String, Object>();
        item.put("id", StringUtil.checkVal(brand.getId()));
        item.put("name", StringUtil.checkVal(brand.getBrandName()));
        item.put("image", ConstParam.URL + "/upfiles/businessCenter/brandDic/" + File.separator
            + StringUtil.checkVal(brand.getLogo()));
        brands.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", brands);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 年龄标签对应的帖子列表
   * 
   * @param id 年龄标签ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String getAgeLabelPostListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserCirclePostPojo> posts = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (id == null || id == 0) {
      msg = "年龄标签ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("ageType", id);
      params.put("status", 1);
      params.put("isDelete", 0);
      posts = userCirclePostService.userCirclePostList(params);
      if (posts != null && posts.size() > 0) {
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo cpost : posts) {
          post = new HashMap<String, Object>();
          post.put("postId", StringUtil.checkVal(cpost.getId()));
          url =
              cpost.getBanner() == null ? "" : ConstParam.URL + "/upfiles/userCirclePost/"
                  + cpost.getBanner();
          post.put("banner", url);
          post.put("postTitle", StringUtil.checkVal(cpost.getTitle()));
          post.put("authorName", StringUtil.checkVal(cpost.getUserName()));
          post.put("collectNum", StringUtil.checkVal(cpost.getCollectNum()));
          post.put("authorLogo", cpost.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + cpost.getUserImage());
          post.put("authorSex", StringUtil.checkVal(cpost.getUserSex()));
          post.put("authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l, cpost.getUserId(), 0)));
          post.put("authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(cpost.getUserId(), 0l, 0)));

          if (uid == null || uid == 0) {
            post.put("authorFollow", "0");
            post.put("postCollect", "0");
          } else {
            // 查询是否关注
            if (userCircleFollowService.getFollowNum(uid, cpost.getUserId(), 0) == 0) {
              post.put("authorFollow", "0");
            } else {
              post.put("authorFollow", "1");
            }
            // 查询是否收藏
            if (userPostCollectService.isCollect(cpost.getId(), cpost.getUserId(), 2, uid) == 0) {
              post.put("postCollect", "0");
            } else {
              post.put("postCollect", 1);
            }
          }
          imgInfo = SellerService.getImgWH(url);
          post.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          post.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          post.put("content", StringUtil.checkVal(cpost.getSketch()));
          post.put("authorId", StringUtil.checkVal(cpost.getUserId()));
          result.add(post);
        }
        b = true;
      } else {
        msg = "暂时还没有该标签对应的笔记哦！~";
      }
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 平台专题详情-相关宝典
   * 
   * @return
   */
  public String getPlatformSpecialCanonListApi() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    if (specialId == null || specialId <= 0) {
      error_msg = "平台专题ID不能为空哦！~";
    }
    /*
     * else if (uid == null || uid <= 0) { error_msg = "当前用户ID不能为空哦！~"; }
     */
    else {
      Map<String, Object> params = new HashMap<String, Object>();

      PlatformSpecialPojo platformSpecialPojo = platformSpecialService.getById(specialId);
      if (platformSpecialPojo != null) {
        params.put("ageType",
            platformSpecialPojo.getAgeType() == null ? 1 : platformSpecialPojo.getAgeType());
      } else {
        params.put("ageType", 1);
      }

      // 分页
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);

      params.put("status", 1);
      params.put("orderBy",
          "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");

      List<UserCirclePostPojo> UserCirclePostList =
          userCirclePostService.userCirclePostList(params);
      if (UserCirclePostList.size() > 0) {
        Map<String, Object> item = null;
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo userCirclePostPojo : UserCirclePostList) {
          item = new HashMap<String, Object>();
          url =
              userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner();
          item.put("banner", url);
          item.put("postId", StringUtil.checkVal(userCirclePostPojo.getId()));
          item.put("postTitle", StringUtil.checkVal(userCirclePostPojo.getTitle()));
          item.put("collectNum", StringUtil.checkVal(userCirclePostPojo.getCollectNum()));
          item.put("authorSex", StringUtil.checkVal(userCirclePostPojo.getUserSex()));
          item.put("authorName", StringUtil.checkVal(userCirclePostPojo.getUserName()));
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorId", StringUtil.checkVal(userCirclePostPojo.getUserId()));

          // 查询是否关注和是否收藏
          if (uid == null || uid <= 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            if (userPostCollectService.isCollect(userCirclePostPojo.getId(),
                userCirclePostPojo.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            if (userCircleFollowService.getFollowNum(uid, userCirclePostPojo.getUserId(), 0) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }

          item.put(
              "authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                  userCirclePostPojo.getUserId(), 0)));

          item.put(
              "authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(
                  userCirclePostPojo.getUserId(), 0l, 0)));

          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));
          list.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到相关笔记哦！~";
      }
    }
    map.put("success", success);
    map.put("result", list);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 首页-创客品牌-创客圈
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerCircleApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    /*
     * if (uid == null || uid <= 0) { msg = "当前用户ID不能为空哦！~"; } else
     */
    if ((id == null || id <= 0) && (pid == null || pid <= 0)) {
      msg = "品牌用户ID或者创客专题ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", 1);
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      if (id != null && id > 0) {
        option.put("userId", id);
      } else if (pid != null && pid > 0) {
        UserMakerThemePojo makerThemePojo = userMakerThemeService.getUserMakerThemeById(pid);
        if (makerThemePojo != null) {
          option.put("ageType",
              makerThemePojo.getAgeType() == null ? 1 : makerThemePojo.getAgeType());
        } else {
          option.put("ageType", 1);
        }
      }
      option.put("pageNo", (pageNo - 1) * 10);
      option.put("pageSize", pageSize);
      option.put("orderBy",
          "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
      if (list.size() != 0) {
        Map<String, String> imgInfo = null;
        String url = "";
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", StringUtil.checkVal(userCirclePostPojo.getId()));
          url =
              userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner();
          item.put("banner", url);
          item.put("postTitle", StringUtil.checkVal(userCirclePostPojo.getTitle()));
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorName", StringUtil.checkVal(userCirclePostPojo.getUserName()));
          item.put("collectNum", StringUtil.checkVal(userCirclePostPojo.getCollectNum()));

          item.put("authorSex", StringUtil.checkVal(userCirclePostPojo.getUserSex()));
          item.put(
              "authorFansNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                  userCirclePostPojo.getUserId(), 0)));
          item.put(
              "authorCollectNum",
              StringUtil.checkVal(userCircleFollowService.getFollowNum(
                  userCirclePostPojo.getUserId(), 0l, 0)));
          // 查询是否关注和是否收藏
          if (uid == null || uid <= 0) {
            item.put("authorFollow", "0");
            item.put("postCollect", "0");
          } else {
            if (userCircleFollowService.getFollowNum(uid, userCirclePostPojo.getUserId(), 0) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
            if (userPostCollectService.isCollect(userCirclePostPojo.getId(),
                userCirclePostPojo.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
          }
          item.put("authorId", StringUtil.checkVal(userCirclePostPojo.getUserId()));
          imgInfo = SellerService.getImgWH(url);
          item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
          item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
          item.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));

          result.add(item);
        }
      }
      b = true;
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 领取优惠券
   * 
   * @return
   * @throws SQLException
   */
  public String getCoupon() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (cid == null || cid == 0) {
      // 优惠券模板ID
      msg = "ID不能为空哦！~";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("userId", uid);
      param.put("couponId", cid);
      int count = couponService.getuserCouponCount(param);
      if (count == 0) {
        param.clear();
        long curr = System.currentTimeMillis() / 1000;
        param.put("couponId", cid);
        param.put("status", 1);
        param.put("used", 0);
        param.put("userId", 0);
        param.put("couponNum", 1);
        param.put("validStime", curr);
        param.put("validEtime", curr);
        param.put("pageNo", 0);
        param.put("pageSize", 1);
        try {
          List<UserCouponPojo> coupons = couponService.getuserCouponList(param);
          if (coupons != null && coupons.size() > 0) {
            UserCouponPojo cp = coupons.get(0);
            UserCouponPojo coupon = new UserCouponPojo();
            coupon.setUserId(uid);
            coupon.setCouponNo(cp.getCouponNo());
            coupon.setVersion(cp.getVersion());
            coupon.setStatus(cp.getStatus());
            result = couponService.updateUserCoupon(coupon);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        result = 2;
      }
      b = true;
      if (result == 1) {
        msg = "领取成功！";
      } else if (result == 2) {
        msg = "您已领取过优惠券!";
      } else {
        msg = "抢光了！";
      }
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 首页-玩家宝典-详情
   * 
   * @return
   * @throws Exception
   */
  public String homePostDetail() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> params2 = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    new HashMap<String, Object>();

    if (id == null || id == 0) {
      msg = "玩家宝典id不能为空！~";
    }
    /*
     * else if (uid == null || uid == 0) { msg = "用户ID不能为空哦！~"; }
     */
    else if (authorId == null || authorId == 0) {
      msg = "帖子作者ID不能为空哦！~";
    } else {
      UserCirclePostPojo userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
      if (userCirclePostPojo != null) {
        if (uid == null || uid == 0) {
          item.put("isLike", "0");
          item.put("authorFollow", "0");
          item.put("postCollect", "0");
        } else {
          // 判断是否点赞
          params.clear();
          params.put("userId", uid);
          params.put("postId", id);
          params.put("isLike", 1);
          int i = userPostLikeHistoryService.userPostLikeHistoryCount(params);
          if (i > 0) {
            item.put("isLike", "1");
          } else {
            item.put("isLike", "0");
          }
          // 查询是否收藏
          if (userPostCollectService.isCollect(userCirclePostPojo.getId(),
              userCirclePostPojo.getUserId(), 2, uid) == 0) {
            item.put("postCollect", "0");
          } else {
            item.put("postCollect", "1");
          }
          // 查询是否关注
          if (userCircleFollowService.getFollowNum(uid, userCirclePostPojo.getUserId(), 0) == 0) {
            item.put("authorFollow", "0");
          } else {
            item.put("authorFollow", "1");
          }
        }
        item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
        item.put("postId", StringUtil.checkVal(userCirclePostPojo.getId()));// 帖子id
        item.put("postTitle", StringUtil.checkVal(userCirclePostPojo.getTitle()));
        item.put("authorName", StringUtil.checkVal(userCirclePostPojo.getUserName()));
        item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
        item.put("collectNum", StringUtil.checkVal(userCirclePostPojo.getCollectNum()));
        item.put("commentNum", StringUtil.checkVal(userCirclePostPojo.getCommentNum()));// 评论数
        item.put("likeNum", StringUtil.checkVal(userCirclePostPojo.getLikeNum()));// 评论数
        item.put("authorId", StringUtil.checkVal(userCirclePostPojo.getUserId()));
        item.put("authorSex", StringUtil.checkVal(userCirclePostPojo.getUserSex()));
        item.put(
            "authorFansNum",
            StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                userCirclePostPojo.getUserId(), 0)));
        item.put("authorCollectNum", StringUtil.checkVal(userCircleFollowService.getFollowNum(
            userCirclePostPojo.getUserId(), 0l, 0)));
        String dTime =
            UtilDate.timeDifference(userCirclePostPojo.getCreateDateStr(),
                UtilDate.getDateFormatter());
        item.put("dateTime", StringUtil.checkVal(dTime));
        // 查询作者帖子数
        params2.clear();
        params2.put("userId", userCirclePostPojo.getUserId());
        params2.put("status", 1);
        params2.put("isDelete", 0);
        int postCount = userCirclePostService.userCirclePostCount(params2);
        item.put("authorPostNum", StringUtil.checkVal(postCount));
        item.put("isPost", "1");
        String authorType = userCirclePostPojo.getUserType();
        if ("11".equals(authorType)) {
          authorType = "1";
        } else if ("12".equals(authorType)) {
          authorType = "2";
        }
        item.put("authorType", StringUtil.checkVal(authorType));
      } else {
        SysLoginPojo sysLoginPojo = sysLoginService.findSysLoginById(authorId);
        if (sysLoginPojo != null) {
          item.put("isPost", "0");
          params.clear();
          params.put("userId", uid);
          params.put("postId", id);
          params.put("isLike", 1);
          int i = userPostLikeHistoryService.userPostLikeHistoryCount(params);
          if (i > 0) {
            item.put("isLike", "1");
          } else {
            item.put("isLike", "0");
          }
          item.put("banner", "");
          item.put("postId", "");// 帖子id
          item.put("postTitle", "");
          item.put("authorName", StringUtil.checkVal(sysLoginPojo.getName()));
          item.put("authorLogo", sysLoginPojo.getImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + sysLoginPojo.getImage());
          item.put("postCollect", "");
          item.put("collectNum", "");
          item.put("commentNum", "");// 评论数
          item.put("likeNum", "");// 评论数
          item.put("authorId", StringUtil.checkVal(sysLoginPojo.getId()));
          item.put("authorSex", StringUtil.checkVal(sysLoginPojo.getUserSex()));
          item.put("authorFansNum", StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
              sysLoginPojo.getId(), 0)));
          item.put("authorCollectNum", StringUtil.checkVal(userCircleFollowService.getFollowNum(
              sysLoginPojo.getId(), 0l, 0)));
          if (userCircleFollowService.getFollowNum(uid, sysLoginPojo.getId(), 0) == 0) {
            item.put("authorFollow", "0");
          } else {
            item.put("authorFollow", "1");
          }
          // String dTime = UtilDate.timeDifference(userCirclePostPojo.getCreateDateStr(),
          // UtilDate.getDateFormatter());
          item.put("dateTime", "");
          // 查询作者帖子数
          params2.clear();
          params2.put("userId", sysLoginPojo.getId());
          params2.put("status", 1);
          params2.put("isDelete", 0);
          int postCount = userCirclePostService.userCirclePostCount(params2);
          item.put("authorPostNum", StringUtil.checkVal(postCount));
          String authorType = sysLoginPojo.getType();
          if ("11".equals(authorType)) {
            authorType = "1";
          } else if ("12".equals(authorType)) {
            authorType = "2";
          }
          item.put("authorType", StringUtil.checkVal(authorType));
          msg = "玩家宝典不存在！~";
        } else {
          msg = "不存在这个帖子作者!";
        }
      }
      // item.put("label", label);
      b = true;
    }

    map.put("result", item);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 个人信息中心
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   */
  public String infoCenter() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = null;

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      SysLoginPojo user = sysLoginService.findSysLoginById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        result.put("type", user.getType() == null ? "1" : user.getType());
        result.put("logo", ConstParam.URL + "/upfiles/userlogo/" + user.getImage());
        result.put("name", user.getName() == null ? "" : user.getName());
        result.put("userSex", user.getUserSex() == null ? "" : user.getUserSex());

        int num = 0;
        // 关注的人数
        params = new HashMap<String, Object>();
        params.put("userId", uid);
        params.put("type", 1);
        params.put("isFollow", 1);
        // num = userCircleFollowService.userCircleFollowCount(params);
        num = userCircleFollowService.getFollowNum(uid, 0l, 0);
        result.put("followNum", num);
        // 关注的圈子数
        params.put("type", 2);
        num = userCircleFollowService.userCircleFollowCount(params);
        result.put("followCircleNum", num);
        // 粉丝数
        // params.clear();
        // params.put("type", 1);
        // params.put("typeId", uid);
        // params.put("isFollow", 1);
        // num = userCircleFollowService.userCircleFollowCount(params);
        num = userCircleFollowService.getFollowNum(0l, uid, 0);
        result.put("followerNum", num);
        // 查询成长值
        params.clear();
        params.put("userId", uid);
        params.put("isDefault", 1);
        params.put("isDelete", 0);
        userBabyPojo = userBabyService.getByParams(params);
        if (userBabyPojo == null) {
          result.put("ageType", 1);
          msg = "查询不到宝宝信息!";
        } else if (userBabyPojo.getBabyBirthday() == null) {
          result.put("ageType", 1);
          msg = "没有宝宝年龄!";
        } else {
          double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
          if (age >= 0.0 && age <= 0.06) {
            result.put("ageType", 1);
          } else if (age > 0.06 && age <= 1.0) {
            result.put("ageType", 2);
          } else if (age > 1.0 && age <= 3.0) {
            result.put("ageType", 3);
          } else if (age > 3.0 && age <= 6.0) {
            result.put("ageType", 4);
          } else if (age > 6.0 && age <= 12.0) {
            result.put("ageType", 5);
          } else if (age > 12.0 && age <= 16.0) {
            result.put("ageType", 6);
          } else if (age > 16.0) {
            result.put("ageType", 6);
          } else {
            result.put("ageType", 1);
            msg = "没有该年龄对应专题!";
          }
        }
        b = true;
      }
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 我的粉丝
   * 
   * @return
   * @throws SQLException
   */
  public String focusToMyApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> params2 = new HashMap<String, Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> userCircleNoteList = new ArrayList<Map<String, Object>>();
    if (uid != null && uid != 0) {
      // 分页
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("userType", 1);
      params.put("isFollow", 1);
      params.put("typeId", uid);// 被关注用户id
      List<UserCircleFollowPojo> ucfList = userCircleFollowService.findFocusUserList(params);// 查询关注我的人
      if (ucfList.size() != 0) {
        for (UserCircleFollowPojo ucf : ucfList) {
          item = new HashMap<String, Object>();
          /*
           * // 查询是否关注 params.put("userId", uid); params.put("type", 1); params.put("isFollow", 1);
           * params.put("typeId", ucf.getUserId()); Integer i =
           * userCircleFollowService.userCircleFollowCount(params); if (i > 0) {
           * item.put("authorFollow", 1); } else { item.put("authorFollow", 0); }
           */
          // item.put("userId", ucf.getUserId());// 关注我的人id
          item.put("authorLogo", ucf.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucf.getUserImage());
          item.put("authorName", ucf.getUserName() == null ? "" : ucf.getUserName());
          item.put("authorId", ucf.getUserId() == null ? "" : ucf.getUserId());
          item.put("authorSex", ucf.getUserSex() == null ? "" : ucf.getUserSex());
          // params2.clear();
          // params2.put("typeId", ucf.getUserId());
          // params2.put("type", 1);
          // params2.put("isFollow", 1);
          // int followNum = userCircleFollowService.userCircleFollowCount(params2);// 查询关注他的人count
          int followNum = userCircleFollowService.getFollowNum(0l, ucf.getUserId(), 0);// 查询关注他的人count
          item.put("followNum", followNum);// 粉丝数
          UserCirclePostPojo userCirclePost =
              userCirclePostService.getUserCirclePostByIdUserId(ucf.getUserId());
          // post = new HashMap<String, Object>();
          if (userCirclePost != null) {
            item.put("isPost", 1);
            item.put("postId", userCirclePost.getId() == null ? 0 : userCirclePost.getId());// 帖子id
            item.put("collectNum",
                userCirclePost.getCollectNum() == null ? 0 : userCirclePost.getCollectNum());// 收藏数
            item.put("commentNum",
                userCirclePost.getCommentNum() == null ? 0 : userCirclePost.getCommentNum());// 评论数
            item.put("postTitle",
                userCirclePost.getTitle() == null ? "" : userCirclePost.getTitle());// 标题
            item.put("banner", userCirclePost.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userCirclePost" + File.separator + userCirclePost.getBanner());// 图片
            // 查询是否收藏
            params2.clear();
            params2.put("userId", uid);
            params2.put("typeId", userCirclePost.getId());
            params2.put("authorId", userCirclePost.getUserId());
            params2.put("isCollect", 1);
            int j = userPostCollectService.userPostCollectCount(params2);
            if (j > 0) {
              item.put("postCollect", 1);
            } else {
              item.put("postCollect", 0);
            }
            item.put("authorId",
                userCirclePost.getUserId() == null ? 0 : userCirclePost.getUserId());
            item.put("authorLogo", userCirclePost.getUserImage() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + userCirclePost.getUserImage());
            item.put("authorName",
                userCirclePost.getUserName() == null ? 0 : userCirclePost.getUserName());
            item.put("authorSex",
                userCirclePost.getUserSex() == null ? "" : userCirclePost.getUserSex());
            item.put("authorFansNum",
                userCircleFollowService.getFollowNum(0l, userCirclePost.getUserId(), 0));
            item.put("authorCollectNum",
                userCircleFollowService.getFollowNum(userCirclePost.getUserId(), 0l, 0));
            if (userCircleFollowService.getFollowNum(uid, userCirclePost.getUserId(), 0) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
          } else {
            error_msg = "查询不到帖子!";
            item.put("isPost", 0);
            item.put("postId", "");// 帖子id
            item.put("collectNum", "");// 收藏数
            item.put("commentNum", "");// 评论数
            item.put("postTitle", "");// 标题
            item.put("banner", "");// 图片
            item.put("postCollect", "");
            // item.put("authorId","");
            // item.put("authorLogo", "");
            // item.put("authorName","");
            // item.put("authorSex","");
            item.put("authorFansNum", userCircleFollowService.getFollowNum(0l, ucf.getUserId(), 0));
            item.put("authorCollectNum",
                userCircleFollowService.getFollowNum(ucf.getUserId(), 0l, 0));
            if (userCircleFollowService.getFollowNum(uid, ucf.getUserId(), 0) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
          }
          // 查询关注他的人贴子count
          params2.clear();
          params2.put("userId", ucf.getUserId());
          params2.put("status", 1);
          int postNum = userCirclePostService.userCirclePostCount(params2);
          item.put("postNum", postNum);
          // item.put("postInfo", post);
          userCircleNoteList.add(item);
        }
        map2.put("userCircleNoteList", userCircleNoteList);
        success = true;
      } else {
        error_msg = "用户没有粉丝!";
      }
    } else {
      error_msg = "用户id不能为空!";
    }
    map.put("success", success);
    map.put("result", userCircleNoteList);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 首页-创客品牌-品牌详情
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerBrandDetailApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    // List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (id == null || id == 0) {
      msg = "创客品牌ID不能为空哦！~";
    }
    /*
     * else if (uid == null || uid == 0) { msg = "用户ID不能为空哦！~"; }
     */
    else {
      UserMakerBrandPojo userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(id);
      if (userMakerBrandPojo != null) {
        Map<String, Object> option = new HashMap<String, Object>();
        // option.put("userId", userMakerBrandPojo.getUserId());
        // option.put("type", 1);
        // option.put("isFollow", 1);
        // int follow_num = userCircleFollowService.userCircleFollowCount(option);
        int follow_num =
            userCircleFollowService.getFollowNum(userMakerBrandPojo.getUserId(), 0l, 0);
        item.put("followNum", follow_num);

        // option.clear();
        // option.put("typeId", userMakerBrandPojo.getUserId());
        // option.put("type", 1);
        // option.put("isFollow", 1);
        // int fan_num = userCircleFollowService.userCircleFollowCount(option);
        int fan_num = userCircleFollowService.getFollowNum(0l, userMakerBrandPojo.getUserId(), 0);
        item.put("fanNum", fan_num);
        int is_follow = 0;
        if (uid == null || uid == 0) {
          is_follow = 0;
        } else {
          // 查询是否关注
          // option.clear();
          // option.put("typeId", userMakerBrandPojo.getUserId());
          // option.put("type", 1);
          // option.put("userId", uid);
          // option.put("isFollow", 1);
          is_follow = userCircleFollowService.getFollowNum(uid, userMakerBrandPojo.getUserId(), 0);
          if (is_follow == 0) {
            is_follow = 0;
          } else {
            is_follow = 1;
          }
        }
        item.put("isFollow", is_follow);

        item.put("userId", userMakerBrandPojo.getUserId());
        item.put("userSex",
            userMakerBrandPojo.getUserSex() == null ? "" : userMakerBrandPojo.getUserSex());
        item.put("userName",
            userMakerBrandPojo.getUserName() == null ? "" : userMakerBrandPojo.getUserName());
        item.put("logo", userMakerBrandPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userMakerBrandPojo.getUserImage());
        // item.put("content",
        // userMakerBrandPojo.getContent() == null ? "" : userMakerBrandPojo.getContent());

        option.clear();
        option.put("status", 1);
        option.put("pageNo", 0);
        option.put("pageSize", 1);
        List<UserMakerThemePojo> list = userMakerThemeService.userMakerThemeList(option);
        if (list.size() != 0) {
          item.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
              + list.get(0).getBanner());
          item.put("themeId", list.get(0).getId());
        }
        // result.add(item);
      } else {
        msg = "该创客品牌不存在哦！~";
      }
      b = true;
    }

    map.put("result", item);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 首页-创客主题-主题活动
   * 
   * @return
   * @throws Exception
   */
  public String getUserMakerThemeActivityApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    if (id == null || id == 0) {
      msg = "创客主题活动ID不能为空哦！~";
    }
    /*
     * else if (uid == null || uid == 0) { msg = "用户ID不能为空哦！~"; }
     */
    else {
      UserMakerThemePojo userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
      if (userMakerThemePojo != null) {
        Map<String, Object> option = new HashMap<String, Object>();
        if (uid == null || uid == 0) {
          item.put("isFollow", 0);
        } else {
          // option.put("typeId", userMakerThemePojo.getUserId());
          // option.put("type", 1);
          // option.put("userId", uid);
          // option.put("isFollow", 1);
          // int is_follow = userCircleFollowService.userCircleFollowCount(option);
          int is_follow =
              userCircleFollowService.getFollowNum(uid, userMakerThemePojo.getUserId(), 0);
          if (is_follow == 0) {
            is_follow = 0;
          } else {
            is_follow = 1;
          }
          item.put("isFollow", is_follow);
        }
        // 关注的人数
        // option.clear();
        // option.put("userId", userMakerThemePojo.getUserId());
        // option.put("type", 1);
        // option.put("isFollow", 1);
        // int follow_num = userCircleFollowService.userCircleFollowCount(option);
        int follow_num = userCircleFollowService.getFollowNum(uid, 0l, 0);
        item.put("followNum", follow_num);
        // 粉丝数
        // option.clear();
        // option.put("typeId", userMakerThemePojo.getUserId());
        // option.put("type", 1);
        // option.put("isFollow", 1);
        // int fan_num = userCircleFollowService.userCircleFollowCount(option);
        int fan_num = userCircleFollowService.getFollowNum(0l, userMakerThemePojo.getUserId(), 0);
        item.put("fanNum", fan_num);
        if (uid == null || uid == 0) {
          item.put("isCollect", 0);
        } else {
          option.clear();
          option.put("typeId", userMakerThemePojo.getId());
          option.put("authorId", userMakerThemePojo.getUserId());
          option.put("type", 1);
          option.put("userId", uid);
          option.put("isCollect", 1);
          int is_collect = userPostCollectService.userPostCollectCount(option);
          if (is_collect == 0) {
            is_collect = 0;
          } else {
            is_collect = 1;
          }
          item.put("isCollect", is_collect);
        }
        item.put("userId", userMakerThemePojo.getUserId());
        item.put("userSex",
            userMakerThemePojo.getUserSex() == null ? "" : userMakerThemePojo.getUserSex());
        item.put("userName",
            userMakerThemePojo.getUserName() == null ? "" : userMakerThemePojo.getUserName());
        item.put("logo", userMakerThemePojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userMakerThemePojo.getUserImage());
      } else {
        msg = "该创客主题活动不存在哦！~";
      }
      b = true;
    }
    map.put("result", item);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 创阁（店铺）-笔记列表/专题列表
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerSquashDetailListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (uid == null || uid == 0) {
      msg = "创客用户ID不能为空哦！~";
    } else if (type == null || type == 0) {
      msg = "类型不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", 1);
      option.put("userId", uid);
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      option.put("pageNo", (pageNo - 1) * 20);
      option.put("pageSize", 20);
      if (type == 1) {
        List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
        if (list.size() != 0) {
          Map<String, String> imgInfo = null;
          String url = "";
          for (UserCirclePostPojo userCirclePostPojo : list) {
            item = new HashMap<String, Object>();
            item.put("postId", StringUtil.checkVal(userCirclePostPojo.getId()));
            url =
                userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                    + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner();
            item.put("banner", url);
            item.put("collectNum", StringUtil.checkVal(userCirclePostPojo.getCollectNum()));
            item.put("postTitle", StringUtil.checkVal(userCirclePostPojo.getTitle()));
            item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
            item.put("authorId", StringUtil.checkVal(userCirclePostPojo.getUserId()));
            item.put("authorName", StringUtil.checkVal(userCirclePostPojo.getUserName()));
            item.put("authorSex", StringUtil.checkVal(userCirclePostPojo.getUserSex()));
            item.put(
                "authorFansNum",
                StringUtil.checkVal(userCircleFollowService.getFollowNum(0l,
                    userCirclePostPojo.getUserId(), 0)));
            item.put(
                "authorCollectNum",
                StringUtil.checkVal(userCircleFollowService.getFollowNum(
                    userCirclePostPojo.getUserId(), 0l, 0)));
            if (userCircleFollowService.getFollowNum(uid, userCirclePostPojo.getUserId(), 0) == 0) {
              item.put("authorFollow", "0");
            } else {
              item.put("authorFollow", "1");
            }
            if (userPostCollectService.isCollect(userCirclePostPojo.getId(),
                userCirclePostPojo.getUserId(), 2, uid) == 0) {
              item.put("postCollect", "0");
            } else {
              item.put("postCollect", "1");
            }
            imgInfo = SellerService.getImgWH(url);
            item.put("bannerWidth", StringUtil.checkVal(imgInfo.get("width")));
            item.put("bannerHeight", StringUtil.checkVal(imgInfo.get("height")));
            item.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));
            result.add(item);
          }
          b = true;
        } else {
          msg = "该用户查询不到笔记!";
        }
      } else {
        // option.put("type", 3);
        List<UserMakerThemePojo> list = userMakerThemeService.userMakerThemeList(option);
        if (list.size() != 0) {
          for (UserMakerThemePojo userMakerThemePojo : list) {
            item = new HashMap<String, Object>();
            item.put("id", StringUtil.checkVal(userMakerThemePojo.getId()));
            item.put("userId", StringUtil.checkVal(userMakerThemePojo.getUserId()));
            item.put("banner", userMakerThemePojo.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userMakerTheme" + File.separator + userMakerThemePojo.getBanner());
            item.put("title", StringUtil.checkVal(userMakerThemePojo.getSpecialName()));
            item.put("content", StringUtil.checkVal(userMakerThemePojo.getSketch()));
            result.add(item);
          }
          b = true;
        } else {
          msg = "该用户查询不到专题!";
        }
      }
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
