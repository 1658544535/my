package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.util.UtilDate;
import com.opensymphony.xwork2.ActionContext;
import com.tencent.common.MD5;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.SensitivewordFilter;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.AgeSkillLinkPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.pojo.HonorRulePojo;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SocialCircleTypePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TaskSettingPojo;
import com.tzmb2c.web.pojo.UserBabyPojo;
import com.tzmb2c.web.pojo.UserCalendarPojo;
import com.tzmb2c.web.pojo.UserCircleFollowPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;
import com.tzmb2c.web.pojo.UserMakerShopPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.pojo.UserPostCollectPojo;
import com.tzmb2c.web.pojo.UserPostCommentPojo;
import com.tzmb2c.web.pojo.UserPostHistoryPojo;
import com.tzmb2c.web.pojo.UserPostLikeHistoryPojo;
import com.tzmb2c.web.pojo.UserTalentAuthPojo;
import com.tzmb2c.web.pojo.UserTaskPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.AgeSkillLinkService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.HonorRuleService;
import com.tzmb2c.web.service.KnowledgeBaseService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.SocialCircleTypeService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.TaskSettingService;
import com.tzmb2c.web.service.UserBabyService;
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
import com.tzmb2c.web.service.UserPostHistoryService;
import com.tzmb2c.web.service.UserPostLikeHistoryService;
import com.tzmb2c.web.service.UserTalentAuthService;
import com.tzmb2c.web.service.UserTaskService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserWalletService;

public class AppApiMakerAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  // ---- spring 注入 ---- //
  @Autowired
  private SellerService sellerService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private UserMakerBrandService userMakerBrandService;
  @Autowired
  private UserMakerThemeService userMakerThemeService;
  @Autowired
  private SocialCircleService socialCircleService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserCircleFollowService userCircleFollowService;
  @Autowired
  private PlatformSpecialService platformSpecialService;
  @Autowired
  private SocialCircleTypeService socialCircleTypeService;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private UserTalentAuthService userTalentAuthService;
  @Autowired
  private KnowledgeBaseService knowledgeBaseService;
  @Autowired
  private UserPostCollectService userPostCollectService;
  @Autowired
  private UserMakerShopService userMakerShopService;
  @Autowired
  private UserCalendarService userCalendarService;
  @Autowired
  private UserTaskService userTaskService;
  @Autowired
  private UserPostHistoryService userPostHistoryService;
  @Autowired
  private UserPostCommentService userPostCommentService;
  @Autowired
  private AgeSkillLinkService ageSkillLinkService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserPostLikeHistoryService userPostLikeHistoryService;
  @Autowired
  private TaskSettingService taskSettingService;
  @Autowired
  private UserGrowthLineService userGrowthLineService;
  @Autowired
  private HonorRuleService honorRuleService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private BaiduLoginService baiduLoginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private UserBabyService userBabyService;
  // ---- 变量定义 ---- //
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
   * 年龄标签ID
   */
  private Long ageType;
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
   * pageNo:页码
   */
  private Integer pageNo;
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
   * pageSize显示条数
   */
  private Integer pageSize;
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
  /**
   * 平台专题pojo
   */
  private PlatformSpecialPojo platformSpecialPojo;
  /**
   * 宝宝
   */
  private UserBabyPojo userBabyPojo;

  private String thisMounth;
  /**
   * 任务时间
   */
  private String dateTime;

  // ---- getter and setter ---- //



  public String getImgurl() {
    return imgurl;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public void setImgurl(String imgurl) {
    this.imgurl = imgurl;
  }

  public UserBabyPojo getUserBabyPojo() {
    return userBabyPojo;
  }

  public void setUserBabyPojo(UserBabyPojo userBabyPojo) {
    this.userBabyPojo = userBabyPojo;
  }

  public Integer getMax() {
    return max;
  }

  public void setMax(Integer max) {
    this.max = max;
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

  public String[] getGrowthHonor() {
    return growthHonor;
  }

  public String getSkillNames() {
    return skillNames;
  }

  public void setSkillNames(String skillNames) {
    this.skillNames = skillNames;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public KnowledgeBasePojo getKnowledgeBasePojo() {
    return knowledgeBasePojo;
  }

  public void setKnowledgeBasePojo(KnowledgeBasePojo knowledgeBasePojo) {
    this.knowledgeBasePojo = knowledgeBasePojo;
  }

  public UserCirclePostPojo getUserCirclePost() {
    return userCirclePost;
  }

  public void setUserCirclePost(UserCirclePostPojo userCirclePost) {
    this.userCirclePost = userCirclePost;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getRegChannel() {
    return regChannel;
  }

  public void setRegChannel(String regChannel) {
    this.regChannel = regChannel;
  }

  public String getBaidu_uid() {
    return baidu_uid;
  }

  public void setBaidu_uid(String baidu_uid) {
    this.baidu_uid = baidu_uid;
  }

  public void setGrowthHonor(String[] growthHonor) {
    this.growthHonor = growthHonor;
  }

  public String getThisMounth() {
    return thisMounth;
  }

  public Long getCollectedPostId() {
    return collectedPostId;
  }

  public void setCollectedPostId(Long collectedPostId) {
    this.collectedPostId = collectedPostId;
  }

  public Long getPostCommentId() {
    return postCommentId;
  }

  public void setPostCommentId(Long postCommentId) {
    this.postCommentId = postCommentId;
  }

  public void setThisMounth(String thisMounth) {
    this.thisMounth = thisMounth;
  }

  public String getLastMounth() {
    return lastMounth;
  }

  public void setLastMounth(String lastMounth) {
    this.lastMounth = lastMounth;
  }

  public Long getAgeType() {
    return ageType;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public void setAgeType(Long ageType) {
    this.ageType = ageType;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getBrandType() {
    return brandType;
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

  public Long getTalentId() {
    return talentId;
  }

  public void setTalentId(Long talentId) {
    this.talentId = talentId;
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

  public Long getAbilityId() {
    return abilityId;
  }

  public void setAbilityId(Long abilityId) {
    this.abilityId = abilityId;
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



  public Integer getPageSize() {
    return pageSize;
  }

  public Long getFtId() {
    return ftId;
  }

  public void setFtId(Long ftId) {
    this.ftId = ftId;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPageNo() {
    return pageNo;
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

  public Long getSocialCircleId() {
    return socialCircleId;
  }

  public void setSocialCircleId(Long socialCircleId) {
    this.socialCircleId = socialCircleId;
  }

  public Integer getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Integer specialId) {
    this.specialId = specialId;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
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

  // ---- 操作方法 ---- //

  // ------ 测试样例 ------ //
  /**
   * 测试例子
   * 
   * @return
   * @throws SQLException
   */
  public String getAdd() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("add", sellerService.add(100, 55));
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

  // ------ 首页 ------ //
  /**
   * 知识库-年龄能力标签列表
   * 
   * @return
   * @throws Exception
   */
  public String getAgeSkillLabelListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> result2 = new ArrayList<Map<String, Object>>();
    HashMap<String, Object> item = new HashMap<String, Object>();
    HashMap<String, Object> item2 = new HashMap<String, Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    List<SysDictPojo> list = sysDictService.getSysDictListByType("user_age");
    if (list.size() != 0) {
      for (SysDictPojo sysDictPojo : list) {
        item = new HashMap<String, Object>();
        Long ageValue = new Long(sysDictPojo.getValue());
        item.put("ageValue", ageValue);
        item.put("ageName", sysDictPojo.getName());
        item.put("ageId", sysDictPojo.getId());
        List<AgeSkillLinkPojo> list2 =
            ageSkillLinkService.findAgeSkillLinkByAge(sysDictPojo.getId());
        if (list2.size() != 0) {
          result2 = new ArrayList<Map<String, Object>>();
          for (AgeSkillLinkPojo ageSkillLinkPojo : list2) {
            item2 = new HashMap<String, Object>();
            item2.put("skillValue", ageSkillLinkPojo.getSkillValue());
            item2.put("skillName", ageSkillLinkPojo.getSkillName());
            // item.put("skillId", ageSkillLinkPojo.getSkillId());
            result2.add(item2);
          }
          item.put("skillList", result2);
        } else {
          // msg = "查不到对应的能力标签！~";
        }
        result.add(item);
      }
    } else {
      msg = "查不到对应的年龄标签！~";
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

  // /**
  // * 知识库-标签列表（4个标签）
  // *
  // * @return
  // * @throws Exception
  // */
  // public String getKnowledgeBaseLabelListApi() throws Exception {
  // String msg = "";
  // boolean b = false;
  // Map<String, Object> map = new HashMap<String, Object>();
  // List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
  // HashMap<String, Object> item = new HashMap<String, Object>();
  //
  // // if (uid == null || uid <= 0) {
  // // msg = "用户ID不能为空哦！~";
  // // } else {
  // Map<String, Object> option = new HashMap<String, Object>();
  // option.put("status", 1);
  // option.put("pageNo", 0);
  // option.put("pageSize", 1);
  // option.put("orderBy", "update_date desc,create_date desc");
  // if (ageType == null || ageType <= 0) {
  // ageType = 1l;
  // }
  // if (skillType == null || skillType <= 0) {
  // skillType = 1l;
  // }
  // option.put("ageType", ageType);
  // option.put("skillType", skillType);
  // KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.listPage(option).get(0);
  // if (knowledgeBasePojo != null) {
  // SysDictPojo sysDictPojo = new SysDictPojo();
  // sysDictPojo.setType("user_age");
  // sysDictPojo.setValue(knowledgeBasePojo.getAgeType().toString());
  // sysDictPojo = sysDictService.getSysDictByTypeAndValue(sysDictPojo);
  // if (sysDictPojo != null) {
  // item.put("ageName", sysDictPojo.getName());
  // item.put("ageValue", sysDictPojo.getValue());
  // }
  //
  // sysDictPojo.setType("ability");
  // sysDictPojo.setValue(knowledgeBasePojo.getSkillType().toString());
  // sysDictPojo = sysDictService.getSysDictByTypeAndValue(sysDictPojo);
  // if (sysDictPojo != null) {
  // item.put("skillName", sysDictPojo.getName());
  // item.put("skillValue", sysDictPojo.getValue());
  // }
  //
  //
  // result.add(item);
  // }
  // b = true;
  // // }
  //
  // map.put("result", result);
  // map.put("error_msg", msg);
  // map.put("success", b);
  // JSONObject json = JSONObject.fromObject(map);
  // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
  // try {
  // ServletActionContext.getResponse().getWriter().write(json.toString());
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // return null;
  // }

  /**
   * 知识库-年龄标签列表
   * 
   * @return
   * @throws Exception
   */
  public String getAgeLabelListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    HashMap<String, Object> item = new HashMap<String, Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    List<SysDictPojo> list = sysDictService.getSysDictListByType("user_age");
    if (list.size() != 0) {
      for (SysDictPojo sysDictPojo : list) {
        item = new HashMap<String, Object>();
        Long ageValue = new Long(sysDictPojo.getValue());
        item.put("ageValue", ageValue);
        item.put("ageName", sysDictPojo.getName());
        item.put("ageId", sysDictPojo.getId());
        result.add(item);
      }
    } else {
      msg = "查不到对应的年龄标签！~";
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
   * 知识库-能力标签列表
   * 
   * @return
   * @throws Exception
   */
  public String getSkillLabelListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    HashMap<String, Object> item = new HashMap<String, Object>();

    if (age == null || age == "") {
      msg = "年龄不能为空哦！~";
    } else {
      List<AgeSkillLinkPojo> list = ageSkillLinkService.findSkillByAgeType(Integer.parseInt(age));
      if (list.size() != 0) {
        for (AgeSkillLinkPojo ageSkillLinkPojo : list) {
          item = new HashMap<String, Object>();
          item.put("skillValue", ageSkillLinkPojo.getSkillValue());
          item.put("skillName", ageSkillLinkPojo.getSkillName());
          // item.put("skillId", ageSkillLinkPojo.getSkillId());
          result.add(item);
        }
      } else {
        msg = "查不到对应的能力标签！~";
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
    } else if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
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
        for (UserCirclePostPojo cpost : posts) {
          post = new HashMap<String, Object>();
          post.put("postId", cpost.getId());
          post.put("banner", ConstParam.URL + "/upfiles/userCirclePost/" + cpost.getBanner());
          post.put("postTitle", cpost.getTitle() == null ? "" : cpost.getTitle());
          post.put("authorName", cpost.getUserName() == null ? "" : cpost.getUserName());
          post.put("collectNum", cpost.getCollectNum() == null ? 0 : cpost.getCollectNum());

          post.put("authorLogo", cpost.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + cpost.getUserImage());
          post.put("authorSex", cpost.getUserSex() == null ? "" : cpost.getUserSex());
          post.put("authorFansNum", getUserFollowInfoCount(null, cpost.getUserId(), 1));
          post.put("authorCollectNum", getUserFollowInfoCount(cpost.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, cpost.getUserId(), 1) == 0) {
            post.put("authorFollow", 0);
          } else {
            post.put("authorFollow", 1);
          }
          if (getUserCollectInfoCount(cpost.getId(), cpost.getUserId(), 2, uid) == 0) {
            post.put("postCollect", 0);
          } else {
            post.put("postCollect", 1);
          }
          post.put("authorId", cpost.getUserId());
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
   * 能力标签对应的帖子列表
   * 
   * @param id 能力标签ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String getSkillLabelPostListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserCirclePostPojo> posts = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (id == null || id == 0) {
      msg = "能力标签ID不能为空!";
    } else if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("skillType", id);
      params.put("status", 1);
      params.put("isDelete", 0);
      posts = userCirclePostService.userCirclePostList(params);
      if (posts != null && posts.size() > 0) {
        for (UserCirclePostPojo cpost : posts) {
          post = new HashMap<String, Object>();
          post.put("postId", cpost.getId());
          post.put("banner", ConstParam.URL + "/upfiles/userCirclePost/" + cpost.getBanner());
          post.put("postTitle", cpost.getTitle() == null ? "" : cpost.getTitle());
          post.put("authorName", cpost.getUserName() == null ? "" : cpost.getUserName());
          post.put("collectNum", cpost.getCollectNum() == null ? 0 : cpost.getCollectNum());

          post.put("authorLogo", cpost.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + cpost.getUserImage());
          post.put("authorSex", cpost.getUserSex() == null ? "" : cpost.getUserSex());
          post.put("authorFansNum", getUserFollowInfoCount(null, cpost.getUserId(), 1));
          post.put("authorCollectNum", getUserFollowInfoCount(cpost.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, cpost.getUserId(), 1) == 0) {
            post.put("authorFollow", 0);
          } else {
            post.put("authorFollow", 1);
          }
          if (getUserCollectInfoCount(cpost.getId(), cpost.getUserId(), 2, uid) == 0) {
            post.put("postCollect", 0);
          } else {
            post.put("postCollect", 1);
          }
          post.put("authorId", cpost.getUserId());

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
      if (pageNo == null || pageNo == 0) {
        pageNo = 1;
      }
      option.put("pageNo", (pageNo - 1) * 20);
      option.put("pageSize", 20);
      if (type == 1) {
        List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
        if (list.size() != 0) {
          for (UserCirclePostPojo userCirclePostPojo : list) {
            item = new HashMap<String, Object>();
            item.put("postId", userCirclePostPojo.getId());
            item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
            item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? 0
                : userCirclePostPojo.getCollectNum());
            item.put("postTitle",
                userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
            item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
            item.put("authorId", userCirclePostPojo.getUserId());
            item.put("authorName", userCirclePostPojo.getUserName() == null ? ""
                : userCirclePostPojo.getUserName());
            item.put("authorSex",
                userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
            item.put("authorFansNum",
                getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
            item.put("authorCollectNum",
                getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
            if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
            if (getUserCollectInfoCount(userCirclePostPojo.getId(), userCirclePostPojo.getUserId(),
                2, uid) == 0) {
              item.put("postCollect", 0);
            } else {
              item.put("postCollect", 1);
            }

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
            item.put("id", userMakerThemePojo.getId());
            item.put("userId", userMakerThemePojo.getUserId());
            item.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
                + userMakerThemePojo.getBanner());
            item.put(
                "title",
                userMakerThemePojo.getSpecialName() == null ? "" : userMakerThemePojo
                    .getSpecialName());
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

  /**
   * 创阁（店铺）- 店铺信息
   * 
   * @return
   * @throws SQLException
   */
  public String MakerShopInfoApi() throws SQLException {
    String msg = "";
    boolean b = false;
    int themeCount = 0;
    int postCount = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    // if (uid == null || uid == 0) {
    // msg = "用户ID不能为空哦！~";
    // }else
    if (shopId == null || shopId == 0) {
      msg = "店铺ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();

      option.put("status", 1);
      // option.put("shopType", 1);
      // option.put("userId", uid);
      option.put("shopId", shopId);
      List<UserMakerShopPojo> list = userMakerShopService.findUserMakerShopList(option);
      if (list.size() > 0) {
        UserMakerShopPojo userMakerShopPojo = list.get(0);
        item.put(
            "image",
            ConstParam.URL + "/upfiles/shop" + File.separator
                + userMakerShopPojo.getShopMainImage());
        item.put("name",
            userMakerShopPojo.getShopName() == null ? "" : userMakerShopPojo.getShopName());
        // 查找笔记数量
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", 1);
        params.put("userId", userMakerShopPojo.getUserId());
        postCount = userCirclePostService.userCirclePostCount(params);
        // 查找创客专题数量
        // params.put("type", 3);
        themeCount = userMakerThemeService.userMakerThemeCount(params);
        item.put("themeCount", themeCount);
        item.put("postCount", postCount);
        b = true;
      } else {
        msg = "找不到该店铺信息!";
      }
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

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      // if (stageType == null || stageType == 0) {
      // stageType = 1l;
      // }
      // option.put("ageType", stageType);
      option.put("ageType", stageType);
      option.put("status", 1);
      option.put("userId", uid);
      if (pageNo == null || pageNo == 0) {
        pageNo = 1;
      }
      option.put("pageNo", (pageNo - 1) * 10);
      option.put("pageSize", 10);
      List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
      if (list.size() != 0) {
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", userCirclePostPojo.getId());
          item.put("banner", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
              + userCirclePostPojo.getBanner());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? 0
              : userCirclePostPojo.getCollectNum());

          item.put("authorSex",
              userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          if (getUserCollectInfoCount(userCirclePostPojo.getId(), userCirclePostPojo.getUserId(),
              2, uid) == 0) {
            item.put("postCollect", 0);
          } else {
            item.put("postCollect", 1);
          }
          item.put("authorId",
              userCirclePostPojo.getUserId() == null ? 0 : userCirclePostPojo.getUserId());

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
   * 创客专题详情-帖子点赞（取消点赞）
   * 
   * @return
   * @throws SQLException
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String getUserMakerPostLikeApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (authorId == null || authorId == 0) {
      msg = "作者ID不能为空哦！~";
    } else if (postId == null || postId == 0) {
      msg = "帖子ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("userId", uid);
      option.put("authorId", authorId);
      option.put("postId", postId);
      List<UserPostLikeHistoryPojo> list =
          userPostLikeHistoryService.userPostLikeHistoryList(option);
      UserPostLikeHistoryPojo userPostLikeHistoryPojo = new UserPostLikeHistoryPojo();
      UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
      if (list.size() == 0) {
        userPostLikeHistoryPojo.setUserId(uid);
        userPostLikeHistoryPojo.setPostId(postId);
        userPostLikeHistoryPojo.setAuthorId(authorId);
        userPostLikeHistoryPojo.setIsLike(1);
        userPostLikeHistoryService.addUserPostLikeHistory(userPostLikeHistoryPojo);

        userCirclePostPojo.setId(postId);
        userCirclePostPojo.setLikeNum(1l);
        userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
        result = 1;
      } else {
        userPostLikeHistoryPojo.setId(list.get(0).getId());;
        if (list.get(0).getIsLike() == 0) {
          userPostLikeHistoryPojo.setIsLike(1);
          userPostLikeHistoryService.updateUserPostLikeHistory(userPostLikeHistoryPojo);

          userCirclePostPojo.setId(postId);
          userCirclePostPojo.setLikeNum(1l);
          userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
          result = 1;
        } else {
          userPostLikeHistoryPojo.setIsLike(0);
          userPostLikeHistoryService.updateUserPostLikeHistory(userPostLikeHistoryPojo);

          userCirclePostPojo.setId(postId);
          userCirclePostPojo.setLikeNum(-1l);
          userCirclePostService.decreaseUserCirclePostNumById(userCirclePostPojo);
          result = 1;
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
    } else if (authorId == null || authorId == 0) {
      msg = "作者ID不能为空哦！~";
    } else if (type == null || type == 0) {
      msg = "类型不能为空哦！~";
    } else if (type != 1 && type != 2) {
      msg = "类型错误！~";
    } else if (typeId == null || typeId == 0) {
      msg = "主题/帖子ID不能为空哦！~";
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
      if (list.size() == 0) {
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
          } else {
            userCirclePostPojo.setId(typeId);
            userCirclePostPojo.setCollectNum(1l);
            userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
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
            } else {
              userCirclePostPojo.setId(typeId);
              userCirclePostPojo.setCollectNum(1l);
              userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
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
            } else {
              userCirclePostPojo.setId(typeId);
              userCirclePostPojo.setCollectNum(-1l);
              userCirclePostService.decreaseUserCirclePostNumById(userCirclePostPojo);
            }
          }
          msg = "取消收藏成功!";
        }
      }
      result = 1;
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
    } else if (authorId == null || authorId == 0) {
      msg = "作者ID不能为空哦！~";
    } else if (type == null || type == 0) {
      msg = "类型不能为空哦！~";
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
      if (result > 0) {
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
   * 创客专题详情-主题/帖子评论列表
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerPostCommetListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result_item = new HashMap<String, Object>();
    List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (type == null || type == 0) {
      msg = "类型不能为空哦！~";
    } else if (typeId == null || typeId == 0) {
      msg = "主题/帖子ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", 1);
      option.put("type", type);
      option.put("typeId", typeId);
      option.put("authorId", uid);
      // option.put("option", "userName");
      if (pageNo == null || pageNo == 0) {
        pageNo = 1;
      }
      option.put("pageNo", (pageNo - 1) * 10);
      option.put("pageSize", 10);
      option.put("option", "userName2");
      int count = userPostCommentService.countBy(option);
      result_item.put("count", count);
      List<UserPostCommentPojo> list = userPostCommentService.listPage(option);
      if (list.size() != 0) {
        for (UserPostCommentPojo userPostCommentPojo : list) {
          item = new HashMap<String, Object>();
          item.put("logo", userPostCommentPojo.getUserImage2() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userPostCommentPojo.getUserImage2());
          item.put("userName", userPostCommentPojo.getUserName2() == null ? ""
              : userPostCommentPojo.getUserName2());
          item.put("content",
              userPostCommentPojo.getContent() == null ? "" : userPostCommentPojo.getContent());
          item.put("userId",
              userPostCommentPojo.getUserId() == null ? "" : userPostCommentPojo.getUserId());
          items.add(item);
        }
        result_item.put("commentList", items);
      }
      // result.add(result_item);
      b = true;
    }

    map.put("result", result_item);
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
   * 创客首页-创阁列表
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerSquashListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    // if (uid == null || uid == 0) {
    // msg = "用户ID不能为空哦！~";
    // } else {
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("isGenCabinet", 1);
    // option.put("shopType", 1);
    // option.put("userId", uid);
    if (pageNo == null || pageNo == 0) {
      pageNo = 1;
    }
    option.put("pageNo", (pageNo - 1) * 10);
    option.put("pageSize", 10);
    List<UserMakerShopPojo> list = userMakerShopService.findMakerShopList(option);
    if (list.size() != 0) {
      for (UserMakerShopPojo userMakerShopPojo : list) {
        item = new HashMap<String, Object>();
        item.put("shopId",
            userMakerShopPojo.getShopId() == null ? 0 : userMakerShopPojo.getShopId());
        item.put("userId",
            userMakerShopPojo.getUserId() == null ? 0 : userMakerShopPojo.getUserId());
        item.put(
            "image",
            ConstParam.URL + "/upfiles/shop" + File.separator
                + userMakerShopPojo.getShopMainImage());
        item.put("name",
            userMakerShopPojo.getShopName() == null ? "" : userMakerShopPojo.getShopName());
        item.put("logo", userMakerShopPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userMakerShopPojo.getUserImage());
        item.put("userName",
            userMakerShopPojo.getUserName() == null ? "" : userMakerShopPojo.getUserName());
        result.add(item);
      }
      b = true;
    }
    // }

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
    Map<String, Object> item = new HashMap<String, Object>();

    if (uid == null || uid <= 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", 1);
      option.put("pageNo", 0);
      option.put("pageSize", 3);
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
      if (list.size() != 0) {
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", userCirclePostPojo.getId());
          item.put("banner", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
              + userCirclePostPojo.getBanner());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("authorId",
              userCirclePostPojo.getUserId() == null ? 0 : userCirclePostPojo.getUserId());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? 0
              : userCirclePostPojo.getCollectNum());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());

          item.put("authorSex",
              userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          option.clear();
          option.put("typeId", userCirclePostPojo.getId());
          option.put("authorId", userCirclePostPojo.getUserId());
          option.put("type", 2);
          option.put("userId", uid);
          option.put("isCollect", 1);
          int is_collect = userPostCollectService.userPostCollectCount(option);
          if (is_collect == 0) {
            is_collect = 0;
          } else {
            is_collect = 1;
          }
          item.put("postCollect", is_collect);
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
   * 获取笔记：是否收藏（0-未收藏；1-已收藏）
   * 
   * @param tid 帖子id
   * @param aid 作者id
   * @param type 1-主题；2-帖子
   * @param uid 用户id
   * @return
   * @throw
   * @return int
   */
  public int getUserCollectInfoCount(Long tid, Long aid, int type, Long uid) {
    int num = 0;
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("typeId", tid);
    option.put("authorId", aid);
    option.put("type", type);
    option.put("userId", uid);
    option.put("isCollect", 1);
    num = userPostCollectService.userPostCollectCount(option);
    return num;
  }

  /**
   * 首页-每日知识
   * 
   * @return
   * @throws SQLException
   */
  public String getDailyKnowledgeApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    // List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("pageNo", 0);
    option.put("pageSize", 1);
    if (stageType == null || stageType == 0) {
      stageType = 1l;
    }
    option.put("ageType", stageType);
    option.put("orderBy", "update_date desc,create_date desc");
    List<KnowledgeBasePojo> list = knowledgeBaseService.listPage(option);
    if (list.size() != 0) {
      for (KnowledgeBasePojo knowledgeBasePojo : list) {
        item = new HashMap<String, Object>();
        item.put("icon", ConstParam.URL + "/upfiles/knowledgeBase" + File.separator
            + knowledgeBasePojo.getSmallIcon());
        item.put("title", knowledgeBasePojo.getTitle());
        item.put("id", knowledgeBasePojo.getId());
        // result.add(item);
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
   * 首页-Banner
   * 
   * @return
   * @throws SQLException
   */
  public String getHomePageBannerApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("type", 9);
    option.put("status", 1);
    List<PagePushPojo> brandbanner = pagePushService.findAdByTypeApi(option);
    if (brandbanner.size() != 0) {
      result.put("banner",
          brandbanner.get(0).getImages() == null || "".equals(brandbanner.get(0).getImages()) ? ""
              : ConstParam.URL + "/upfiles/notice" + File.separator
                  + brandbanner.get(0).getImages());
    } else {
      result.put("banner", "");
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
   * 首页-平台专题(查询最新一条数据)
   * 
   * @return
   */
  public String homeSpecial() throws Exception {
    boolean success = false;
    String error_msg = "";
    PlatformSpecialPojo platformSpecial = null;
    // result的map
    Map<String, Object> map = new HashMap<String, Object>();
    // platformSpecial的map
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();

    if (uid == null || uid == 0) {
      error_msg = "用户ID为空!";
      // 查询最新的一条专题信息
      params.clear();
      params.put("status", 1);
      params.put("type", 2);
      platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
      if (platformSpecial != null) {
        map2.put("id", platformSpecial.getId());
        map2.put("specialCategories", platformSpecial.getSpecialCategories());
        map2.put("banner", platformSpecial.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/platformSpecial" + File.separator + platformSpecial.getBanner());
        success = true;
      } else {
        error_msg = "查询不到专题!";
      }
    } else {
      // 查询成长值
      params.clear();
      params.put("userId", uid);
      params.put("isDefault", 1);
      params.put("isDelete", 0);
      userBabyPojo = userBabyService.getByParams(params);
      if (userBabyPojo == null) {
        error_msg = "查询不到宝宝信息!";
      } else if (userBabyPojo.getBabyBirthday() == null) {
        error_msg = "没有宝宝年龄!";
      } else {
        double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
        params.clear();
        params.put("status", 1);
        params.put("type", 2);
        if (age >= 0.0 && age <= 0.06) {
          params.put("ageType", 1);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else if (age > 0.06 && age <= 1.0) {
          params.put("ageType", 2);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else if (age > 1.0 && age <= 3.0) {
          params.put("ageType", 3);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else if (age > 3.0 && age <= 6.0) {
          params.put("ageType", 4);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else if (age > 6.0 && age <= 12.0) {
          params.put("ageType", 5);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else if (age > 12.0 && age <= 16.0) {
          params.put("ageType", 6);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else if (age > 16.0) {
          params.put("ageType", 6);
          platformSpecial = platformSpecialService.getNewPlatformSpecial(params);
        } else {
          error_msg = "没有该年龄对应专题!";
        }
      }
      if (platformSpecial != null) {
        map2.put("id", platformSpecial.getId());
        map2.put("specialCategories", platformSpecial.getSpecialCategories());
        map2.put("banner", platformSpecial.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/platformSpecial" + File.separator + platformSpecial.getBanner());
        success = true;
      } else {
        error_msg = "查询不到专题!";
      }
    }
    map.put("success", success);
    map.put("result", map2);
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
    params.put("orderBy", "u.sorting desc,u.collect_num desc");
    if (age == null || age == "") {
      error_msg = "年龄value不能为空!";
    } else if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
    } else {
      params.put("ageType", age);
      params.put("status", 1);
      List<UserCirclePostPojo> UserCirclePostList =
          userCirclePostService.userCirclePostList(params);
      if (UserCirclePostList.size() != 0) {
        for (UserCirclePostPojo userCirclePostPojo : UserCirclePostList) {
          item = new HashMap<String, Object>();
          item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
          item.put("postId", userCirclePostPojo.getId() == null ? 0 : userCirclePostPojo.getId());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? 0
              : userCirclePostPojo.getCollectNum());
          item.put("authorSex",
              userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorId",
              userCirclePostPojo.getUserId() == null ? 0 : userCirclePostPojo.getUserId());
          // 查询是否收藏
          params2.put("userId", uid);
          params2.put("typeId", userCirclePostPojo.getId());
          params2.put("authorId", userCirclePostPojo.getUserId());
          params2.put("isCollect", 1);
          int i = userPostCollectService.userPostCollectCount(params2);
          if (i > 0) {
            item.put("postCollect", 1);
          } else {
            item.put("postCollect", 0);
          }
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
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
    } else if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (authorId == null || authorId == 0) {
      msg = "帖子作者ID不能为空哦！~";
    } else {
      UserCirclePostPojo userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
      if (userCirclePostPojo != null) {
        // 判断是否点赞
        params.clear();
        params.put("userId", uid);
        params.put("postId", id);
        params.put("isLike", 1);
        int i = userPostLikeHistoryService.userPostLikeHistoryCount(params);
        if (i > 0) {
          item.put("isLike", 1);
        } else {
          item.put("isLike", 0);
        }
        item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
        item.put("postId", userCirclePostPojo.getId());// 帖子id
        item.put("postTitle",
            userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
        item.put("authorName",
            userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
        item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
        // 查询是否收藏
        params2.put("userId", uid);
        params2.put("typeId", userCirclePostPojo.getId());
        params2.put("authorId", userCirclePostPojo.getUserId());
        params2.put("isCollect", 1);
        int j = userPostCollectService.userPostCollectCount(params2);
        if (j > 0) {
          item.put("postCollect", 1);
        } else {
          item.put("postCollect", 0);
        }
        item.put("collectNum",
            userCirclePostPojo.getCollectNum() == null ? 0 : userCirclePostPojo.getCollectNum());
        item.put("commentNum",
            userCirclePostPojo.getCommentNum() == null ? 0 : userCirclePostPojo.getCommentNum());// 评论数
        item.put("likeNum",
            userCirclePostPojo.getLikeNum() == null ? 0 : userCirclePostPojo.getLikeNum());// 评论数
        item.put("authorId", userCirclePostPojo.getUserId());
        item.put("authorSex",
            userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
        item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
        item.put("authorCollectNum",
            getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
        if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
          item.put("authorFollow", 0);
        } else {
          item.put("authorFollow", 1);
        }
        String dTime =
            UtilDate.timeDifference(userCirclePostPojo.getCreateDateStr(),
                UtilDate.getDateFormatter());
        item.put("dateTime", dTime);
        // 查询作者帖子数
        params2.clear();
        params2.put("userId", userCirclePostPojo.getUserId());
        params2.put("status", 1);
        params2.put("isDelete", 0);
        int postCount = userCirclePostService.userCirclePostCount(params2);
        item.put("authorPostNum", postCount);
        item.put("isPost", 1);
      } else {
        SysLoginPojo sysLoginPojo = sysLoginService.findSysLoginById(authorId);
        if (sysLoginPojo != null) {
          item.put("isPost", 0);
          params.clear();
          params.put("userId", uid);
          params.put("postId", id);
          params.put("isLike", 1);
          int i = userPostLikeHistoryService.userPostLikeHistoryCount(params);
          if (i > 0) {
            item.put("isLike", 1);
          } else {
            item.put("isLike", 0);
          }
          item.put("banner", "");
          item.put("postId", "");// 帖子id
          item.put("postTitle", "");
          item.put("authorName", sysLoginPojo.getName() == null ? "" : sysLoginPojo.getName());
          item.put("authorLogo", sysLoginPojo.getImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + sysLoginPojo.getImage());
          item.put("postCollect", "");
          item.put("collectNum", "");
          item.put("commentNum", "");// 评论数
          item.put("likeNum", "");// 评论数
          item.put("authorId", sysLoginPojo.getId());
          item.put("authorSex", sysLoginPojo.getUserSex() == null ? "" : sysLoginPojo.getUserSex());
          item.put("authorFansNum", getUserFollowInfoCount(null, sysLoginPojo.getId(), 1));
          item.put("authorCollectNum", getUserFollowInfoCount(sysLoginPojo.getId(), null, 1));
          if (getUserFollowInfoCount(uid, sysLoginPojo.getId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
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
          item.put("authorPostNum", postCount);
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
   * 查询字典年龄列表(时间轴)
   * 
   * @return
   * @throws Exception
   */
  public String userAgeList() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> userAgelist = new ArrayList<Map<String, Object>>();

    List<SysDictPojo> sysDictPojoList = sysDictService.getSysDictListByType("user_age");
    if (sysDictPojoList != null) {
      for (SysDictPojo SDP : sysDictPojoList) {
        item = new HashMap<String, Object>();
        item.put("growthStage", SDP.getName());
        item.put("stageType", SDP.getValue());
        userAgelist.add(item);
      }
      success = true;
    } else {
      error_msg = "查询不到年龄列表";
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
   * 首页-专题活动-专题列表
   * 
   * @return
   * @throws SQLException
   */
  public String getPlatformSpecialApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> pfsMap = new HashMap<String, Object>();
    List<Map<String, Object>> hotlist = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> classicList = new ArrayList<Map<String, Object>>();
    Map<String, Object> lists = new HashMap<String, Object>();
    // 查询热门专题
    params.put("pageSize", 2);
    params.put("pageNo", 0);
    params.put("type", 3);
    params.put("status", 1);
    List<PlatformSpecialPojo> hotSpecialList = platformSpecialService.listPage(params);
    if (hotSpecialList.size() != 0) {
      for (PlatformSpecialPojo hs : hotSpecialList) {
        pfsMap = new HashMap<String, Object>();
        pfsMap.put("id", hs.getId());
        pfsMap.put("banner", hs.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/platformSpecial" + File.separator + hs.getBanner());
        pfsMap.put("specialCategories", hs.getSpecialCategories());
        hotlist.add(pfsMap);
      }
      b = true;
    }

    // 查询经典专题
    params.put("pageSize", 4);
    params.put("pageNo", 0);
    params.put("type", 4);
    params.put("status", 1);
    List<PlatformSpecialPojo> classicSpecialList = platformSpecialService.listPage(params);
    if (classicSpecialList.size() != 0) {
      for (PlatformSpecialPojo cs : classicSpecialList) {
        pfsMap = new HashMap<String, Object>();
        pfsMap.put("id", cs.getId());
        pfsMap.put("banner", cs.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/platformSpecial" + File.separator + cs.getBanner());
        pfsMap.put("specialCategories", cs.getSpecialCategories());
        classicList.add(pfsMap);
      }
      b = true;
    }
    if (hotlist.size() == 0 && classicList.size() == 0) {
      msg = "专题列表查询不到!";
    }
    lists.put("hotlist", hotlist);
    lists.put("classicList", classicList);
    map.put("result", lists);
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
   * 平台专题详情-图文介绍及标签
   * 
   * @return
   */
  public String SpecialDetailsApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> psMap = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (specialId == null) {
      msg = "specialId不能为空!";
    } else {
      PlatformSpecialPojo ps = platformSpecialService.getById(specialId);
      if (ps != null) {
        psMap.put("title", ps.getTitle());
        psMap.put("banner", ps.getBanner());
        psMap.put("content", ps.getContent());
        psMap.put("ageType", ps.getAgeType());
        psMap.put("ageTypeStr", ps.getAgeTypeStr());
        psMap.put("skillType", ps.getSkillType());
        psMap.put("skillTypeStr", ps.getSkillTypeStr());
        psMap.put("optionType", ps.getOptionType());
        psMap.put("optionTypeStr", ps.getOptionTypeStr());
        psMap.put("productType", ps.getProductType());
        psMap.put("productTypeStr", ps.getProductTypeStr());
        b = true;
        result.put("psMap", psMap);
      } else {
        msg = "查询不到专题详情!";
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


  // /**
  // * 首页-创客品牌/主题（首页）
  // *
  // * @return
  // * @throws SQLException
  // */
  // public String getUserMakerBrandApi() throws SQLException {
  // String msg = "";
  // boolean b = false;
  // Map<String, Object> map = new HashMap<String, Object>();
  // // List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
  // Map<String, Object> result_items = new HashMap<String, Object>();
  // List<Map<String, Object>> result1 = new ArrayList<Map<String, Object>>();
  // List<Map<String, Object>> result2 = new ArrayList<Map<String, Object>>();
  // List<Map<String, Object>> result3 = new ArrayList<Map<String, Object>>();
  // List<Map<String, Object>> result4 = new ArrayList<Map<String, Object>>();
  // new ArrayList<Object>();
  //
  // Map<String, Object> option = new HashMap<String, Object>();
  // option.put("status", 1);
  // option.put("pageNo", 0);
  //
  // option.put("brandType", 0);
  // option.put("pageSize", 1);
  // List<UserMakerBrandPojo> list1 = userMakerBrandService.userMakerBrandList(option);
  // if (list1.size() != 0) {
  // Map<String, Object> items;
  // for (UserMakerBrandPojo userMakerBrandPojo : list1) {
  // items = new HashMap<String, Object>();
  // items.put("brandId", userMakerBrandPojo.getId());
  // items.put("logo", ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
  // + userMakerBrandPojo.getLogo());
  // result1.add(items);
  // }
  // }
  //
  // option.put("brandType", 1);
  // option.put("pageSize", 4);
  // List<UserMakerBrandPojo> list2 = userMakerBrandService.userMakerBrandList(option);
  // if (list2.size() != 0) {
  // Map<String, Object> items;
  // for (UserMakerBrandPojo userMakerBrandPojo : list2) {
  // items = new HashMap<String, Object>();
  // items.put("brandId", userMakerBrandPojo.getId());
  // items.put("logo", ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
  // + userMakerBrandPojo.getLogo());
  // result2.add(items);
  // }
  // }
  // b = true;
  //
  // option.put("type", 1);
  // option.put("pageSize", 1);
  // List<UserMakerThemePojo> list3 = userMakerThemeService.userMakerThemeList(option);
  // if (list3.size() != 0) {
  // Map<String, Object> items;
  // for (UserMakerThemePojo userMakerThemePojo : list3) {
  // items = new HashMap<String, Object>();
  // items.put("themeId", userMakerThemePojo.getId());
  // items.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
  // + userMakerThemePojo.getBanner());
  // result3.add(items);
  // }
  // }
  //
  // option.put("type", 2);
  // option.put("pageSize", 1);
  // List<UserMakerThemePojo> list4 = userMakerThemeService.userMakerThemeList(option);
  // if (list4.size() != 0) {
  // Map<String, Object> items;
  // for (UserMakerThemePojo userMakerThemePojo : list4) {
  // items = new HashMap<String, Object>();
  // items.put("themeId", userMakerThemePojo.getId());
  // items.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
  // + userMakerThemePojo.getBanner());
  // result4.add(items);
  // }
  // }
  // result_items.put("brandList1", result1);
  // result_items.put("brandList2", result2);
  // result_items.put("themeList1", result3);
  // result_items.put("themeList2", result4);
  // // result.add(result_items);
  //
  // map.put("result", result_items);
  // map.put("error_msg", msg);
  // map.put("success", b);
  // JSONObject json = JSONObject.fromObject(map);
  // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
  // try {
  // ServletActionContext.getResponse().getWriter().write(json.toString());
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // return null;
  // }

  /**
   * 首页-创客品牌/主题（首页）
   * 
   * @return
   * @throws SQLException
   */
  public String getUserMakerBrandApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result_item1 = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> result_item2 = new ArrayList<Map<String, Object>>();
    Map<String, Object> result_items = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> result1 = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> result2 = new ArrayList<Map<String, Object>>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("pageNo", 0);

    if (brandType == null || "".equals(brandType)) {
      msg = "查询的品牌类型不能为空哦！~";
    } else if (themeType == null || "".equals(themeType)) {
      msg = "查询的专题类型不能为空哦！~";
    } else {
      String brandTypes[] = brandType.split(",");
      for (int i = 0; i < brandTypes.length; i++) {
        String bts[] = brandTypes[i].split(":");
        option.put("brandType", Integer.valueOf(bts[0]));
        option.put("pageSize", Integer.valueOf(bts[1]));
        List<UserMakerBrandPojo> list1 = userMakerBrandService.userMakerBrandList(option);
        if (list1.size() != 0) {
          Map<String, Object> items;
          result1 = new ArrayList<Map<String, Object>>();
          for (UserMakerBrandPojo userMakerBrandPojo : list1) {
            items = new HashMap<String, Object>();
            items.put("brandId", userMakerBrandPojo.getId());
            items.put("userId", userMakerBrandPojo.getUserId());
            items.put("logo", ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
                + userMakerBrandPojo.getLogo());
            result1.add(items);
          }
        } else {
          result1 = new ArrayList<Map<String, Object>>();
        }
        result_items = new HashMap<String, Object>();
        result_items.put("brandType", Integer.valueOf(bts[0]));
        result_items.put("brandList", result1);
        result_item1.add(result_items);
      }

      String themeTypes[] = themeType.split(",");
      for (int i = 0; i < themeTypes.length; i++) {
        String tts[] = themeTypes[i].split(":");
        option.put("type", Integer.valueOf(tts[0]));
        option.put("pageSize", Integer.valueOf(tts[1]));
        List<UserMakerThemePojo> list2 = userMakerThemeService.userMakerThemeList(option);
        if (list2.size() != 0) {
          Map<String, Object> items;
          result2 = new ArrayList<Map<String, Object>>();
          for (UserMakerThemePojo userMakerThemePojo : list2) {
            items = new HashMap<String, Object>();
            items.put("themeId", userMakerThemePojo.getId());
            items.put("userId", userMakerThemePojo.getUserId());
            items.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
                + userMakerThemePojo.getBanner());
            result2.add(items);
          }
        } else {
          result2 = new ArrayList<Map<String, Object>>();
        }
        result_items = new HashMap<String, Object>();
        result_items.put("themeType", Integer.valueOf(tts[0]));
        result_items.put("themeList", result2);
        result_item2.add(result_items);
      }

      result.put("brand", result_item1);
      result.put("theme", result_item2);
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
    } else if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      UserMakerBrandPojo userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(id);
      if (userMakerBrandPojo != null) {
        Map<String, Object> option = new HashMap<String, Object>();
        option.put("userId", userMakerBrandPojo.getUserId());
        option.put("type", 1);
        option.put("isFollow", 1);
        int follow_num = userCircleFollowService.userCircleFollowCount(option);
        item.put("followNum", follow_num);

        option.clear();
        option.put("typeId", userMakerBrandPojo.getUserId());
        option.put("type", 1);
        option.put("isFollow", 1);
        int fan_num = userCircleFollowService.userCircleFollowCount(option);
        item.put("fanNum", fan_num);

        option.clear();
        option.put("typeId", userMakerBrandPojo.getUserId());
        option.put("type", 1);
        option.put("userId", uid);
        option.put("isFollow", 1);
        int is_follow = userCircleFollowService.userCircleFollowCount(option);
        if (is_follow == 0) {
          is_follow = 0;
        } else {
          is_follow = 1;
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
   * 获取用户：关注人数；粉丝人数；是否关注（0-未关注；1-已关注）
   * 
   * @param uid 查询用户关注人数
   * @param tid 查询用户粉丝人数
   * @param uid&tid 查uid是否关注tid
   * @param type 1-用户；2-圈子
   * @return
   * @throw
   * @return int
   */
  public int getUserFollowInfoCount(Long uid, Long tid, int type) {
    int num = 0;
    Map<String, Object> option = new HashMap<String, Object>();
    if (uid != null && uid > 0) {
      option.put("userId", uid);
    }
    if (tid != null && tid > 0) {
      option.put("typeId", tid);
    }
    option.put("type", type);
    option.put("isFollow", 1);
    num = userCircleFollowService.userCircleFollowCount(option);
    return num;
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
    new HashMap<String, Object>();
    // List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    // Map<String, Object> label = new HashMap<String, Object>();

    if (id == null || id == 0) {
      msg = "创客主题活动ID不能为空哦！~";
    } else if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      UserMakerThemePojo userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
      if (userMakerThemePojo != null) {
        Map<String, Object> option = new HashMap<String, Object>();
        option.put("typeId", userMakerThemePojo.getUserId());
        option.put("type", 1);
        option.put("userId", uid);
        option.put("isFollow", 1);
        int is_follow = userCircleFollowService.userCircleFollowCount(option);
        if (is_follow == 0) {
          is_follow = 0;
        } else {
          is_follow = 1;
        }
        item.put("isFollow", is_follow);
        // 关注的人数
        option.clear();
        option.put("userId", userMakerThemePojo.getUserId());
        option.put("type", 1);
        option.put("isFollow", 1);
        int follow_num = userCircleFollowService.userCircleFollowCount(option);
        item.put("followNum", follow_num);
        // 粉丝数
        option.clear();
        option.put("typeId", userMakerThemePojo.getUserId());
        option.put("type", 1);
        option.put("isFollow", 1);
        int fan_num = userCircleFollowService.userCircleFollowCount(option);
        item.put("fanNum", fan_num);

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

        item.put("userId", userMakerThemePojo.getUserId());
        item.put("userSex",
            userMakerThemePojo.getUserSex() == null ? "" : userMakerThemePojo.getUserSex());
        item.put("userName",
            userMakerThemePojo.getUserName() == null ? "" : userMakerThemePojo.getUserName());
        item.put("logo", userMakerThemePojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userMakerThemePojo.getUserImage());
        // item.put("content",
        // userMakerThemePojo.getContent() == null ? "" : userMakerThemePojo.getContent());
        // result.add(item);
        // 查询标签
        // label.put("ageType", userMakerThemePojo.getAgeType());
        // label.put("ageTypeName", userMakerThemePojo.getAgeTypeName());
        // label.put("skillType", userMakerThemePojo.getSkillType());
        // label.put("skillTypeName", userMakerThemePojo.getSkillTypeName());
        // label.put("productType", userMakerThemePojo.getProductType());
        // label.put("productTypeName", userMakerThemePojo.getProductTypeName());
        // label.put("optionType", userMakerThemePojo.getOptionType());
        // label.put("optionTypeName", userMakerThemePojo.getOptionTypeName());
        // item.put("label", label);
      } else {
        msg = "该创客主题活动不存在哦！~";
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
   * 创客首页-创阁-品牌专题列表
   * 
   * @return
   * @throws SQLException
   */
  public String makerBannerSpecialsApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = null;

    if (makerBannerId == null || makerBannerId == 0) {
      msg = "创客品牌ID不能为空哦！~";
    } else if (makerId == null || makerId == 0) {
      msg = "创客ID不能为空哦！~";
    } else {
      params.put("makerBrandId", makerBannerId);
      params.put("userId", makerId);
      params.put("status", 1);
      List<UserMakerThemePojo> umtList = userMakerThemeService.userMakerThemeList(params);
      if (umtList.size() != 0) {
        for (UserMakerThemePojo umt : umtList) {
          item = new HashMap<String, Object>();
          item.put("banner", umt.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + umt.getBanner());
          item.put("specialName", umt.getSpecialName());
          item.put("specialId", umt.getId());
          result.add(item);
        }
        b = true;
      } else {
        msg = "查询不到创客专题哦！~";
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

  // ------ 日迹 ------ //
  /**
   * 当月任务完成日历
   * 
   * @param uid 用户ID
   * @param cal yyyy-mm
   * @return
   * @throws Exception
   */
  public String curMonTasksCal() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {

      if (cal != null && cal != ""
          && (cal.length() != 7 || StringUtil.stringToDate(cal.concat("-01")) == null)) {
        msg = "日期不正确!";
      } else {
        String date = cal;
        if (cal == null || cal == "") {
          date = StringUtil.dateToString(new Date()).substring(0, 7);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", uid);
        params.put("cal", date);
        List<UserCalendarPojo> cals = userCalendarService.listPage(params);
        if (cals != null && cals.size() > 0) {
          for (UserCalendarPojo ucal : cals) {
            item = new HashMap<String, Object>();
            item.put("dateTime", StringUtil.dateToString(ucal.getCalendar()) == null ? ""
                : StringUtil.dateToString(ucal.getCalendar()));
            result.add(item);
          }
          b = true;
        } else {
          msg = "您没有任务完成记录!";
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

  /**
   * 当月任务完成日历
   * 
   * @param uid 用户ID
   * @param cal yyyy-mm
   * @return
   * @throws Exception
   */
  public String curMonthTasksCal() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {

      if (cal != null && cal != ""
          && (cal.length() != 7 || StringUtil.stringToDate(cal.concat("-01")) == null)) {
        msg = "日期不正确!";
      } else {
        String date = cal;
        if (cal == null || cal == "") {
          date = StringUtil.dateToString(new Date()).substring(0, 7);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", uid);
        params.put("cal", date);
        List<UserCalendarPojo> cals = userCalendarService.listPage(params);
        Calendar cale = Calendar.getInstance();
        cale.setTime(StringUtil.stringToDate(date.concat("-01")));
        int size = cale.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = 0;
        String[] tasks = new String[size];
        for (int i = 0; i < tasks.length; i++) {
          tasks[i] = "0";
        }
        if (cals != null && cals.size() > 0) {
          for (UserCalendarPojo ucal : cals) {
            if (StringUtil.dateToString(ucal.getCalendar()) != null) {
              cale.setTime(ucal.getCalendar());
              day = cale.get(Calendar.DAY_OF_MONTH);
              tasks[day - 1] = "1";
            }
          }
        } else {
          msg = "您没有任务完成记录!";
        }
        result.put("today", StringUtil.dateToString(new Date()));
        result.put("cal", tasks);
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
   * 历史任务列表
   * 
   * @param uid 用户ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   * @throw
   * @return 任务列表
   */
  public String taskHistory() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> tasklist = null;
    Map<String, Object> params = null;
    Map<String, Object> result2 = null;
    List<UserTaskPojo> tasks = null;
    Map<String, Object> task = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      params.put("taskDate", StringUtil.dateToString(new Date()));
      params.put("histFlag", 1);
      tasks = userTaskService.listPage(params);

      if (tasks != null && tasks.size() > 0) {
        Date comp = new Date();
        for (UserTaskPojo userTask : tasks) {
          if (comp.compareTo(userTask.getTaskDate()) != 0) {
            tasklist = new ArrayList<Map<String, Object>>();
            result2 = new HashMap<String, Object>();
            result2.put("date", StringUtil.dateToString(userTask.getTaskDate()));
            result2.put("list", tasklist);
            result.add(result2);
            comp = userTask.getTaskDate();
          }
          if (comp.compareTo(userTask.getTaskDate()) == 0) {
            task = new HashMap<String, Object>();
            task.put("id", userTask.getId());
            task.put("title", userTask.getTaskTitle() == null ? "" : userTask.getTaskTitle());
            task.put("content", userTask.getTaskContent() == null ? "" : userTask.getTaskContent());
            task.put("status", userTask.getStatus() == null ? 0 : userTask.getStatus());
            tasklist.add(task);
          }
        }
        b = true;
      } else {
        msg = "您没有任务数据!";
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
   * 当日任务列表
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   * @throw
   * @return 任务列表
   */
  public String todoTasks() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<TaskSettingPojo> tsList = null;
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> task = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      params.put("userId", uid);
      params.put("isDefault", 1);
      params.put("isDelete", 0);
      userBabyPojo = userBabyService.getByParams(params);
      if (userBabyPojo != null) {
        if (userBabyPojo.getBabyBirthday() != null) {
          // 查询年龄
          double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
          params.clear();
          // 查询该年龄任务
          params.put("userId", uid);
          params.put("isDelete", 0);

          if (dateTime == null || dateTime.equals("")) {
            params.put("taskDateStr", StringUtil.dateToString(new Date()));
          } else {
            params.put("taskDateStr", dateTime);
          }
          if (age >= 0.0 && age <= 0.06) {
            params.put("age", 1);
          } else if (age > 0.06 && age <= 1.0) {
            params.put("age", 2);
          } else if (age > 1.0 && age <= 3.0) {
            params.put("age", 3);
          } else if (age > 3.0 && age <= 6.0) {
            params.put("age", 4);
          } else if (age > 6.0 && age <= 12.0) {
            params.put("age", 5);
          } else if (age > 12.0 && age <= 16.0) {
            params.put("age", 6);
          } else {
            params.put("age", 6);
          }
          tsList = taskSettingService.findTaskSettingToday(params);
          if (tsList.size() != 0) {
            for (TaskSettingPojo tsPojo : tsList) {
              task = new HashMap<String, Object>();
              task.put("taskId", tsPojo.getId());
              task.put("userTaskId", StringUtil.checkLong(tsPojo.getUserTaskId()));
              task.put("taskType", StringUtil.checkLong(tsPojo.getTaskType()));
              task.put("title", StringUtil.checkString(tsPojo.getTaskTitle()));
              task.put("content", StringUtil.checkString(tsPojo.getTaskContent()));
              task.put("createDate", StringUtil.dateToString(tsPojo.getTaskDate()));
              // 0未完成1已完成
              task.put("status", StringUtil.checkInt(tsPojo.getTaskStatus()));
              // 任务进行中状态 0未开始1已开始
              if (tsPojo.getStartTime() != null || tsPojo.getTaskStatus() != null
                  && tsPojo.getTaskStatus() == 1) {
                task.put("doing", 1);
              } else {
                task.put("doing", 0);
              }
              result.add(task);
            }
          } else {
            msg = "您没有任务数据!";
          }
          b = true;
        }
      } else {
        UserBabyPojo userBaby = new UserBabyPojo();
        userBaby.setIsDefault(1);
        userBaby.setUserId(uid);
        userBaby.setBabyName("宝宝");
        userBaby.setBabySex(1);
        userBaby.setBabyBirthday(new Date());
        userBabyService.add(userBaby);
        // 查询该年龄当日的任务
        params.clear();
        params.put("userId", uid);
        params.put("isDelete", 0);
        params.put("taskDateStr", StringUtil.dateToString(new Date()));
        params.put("age", 1);
        tsList = taskSettingService.findTaskSettingToday(params);
        if (tsList.size() != 0) {
          for (TaskSettingPojo tsPojo : tsList) {
            task = new HashMap<String, Object>();
            task.put("taskId", tsPojo.getId());
            task.put("userTaskId", StringUtil.checkLong(tsPojo.getUserTaskId()));
            task.put("taskType", StringUtil.checkLong(tsPojo.getTaskType()));
            task.put("title", StringUtil.checkString(tsPojo.getTaskTitle()));
            task.put("content", StringUtil.checkString(tsPojo.getTaskContent()));
            // 0未完成1已完成
            task.put("status", StringUtil.checkInt(tsPojo.getTaskStatus()));
            // 任务进行中状态 0未开始1已开始
            if (tsPojo.getStartTime() != null || tsPojo.getTaskStatus() != null
                && tsPojo.getTaskStatus() == 1) {
              task.put("doing", 1);
            } else {
              task.put("doing", 0);
            }
            result.add(task);
          }
        } else {
          msg = "您没有任务数据!";
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
   * 开启任务
   * 
   * @param uid 用户ID
   * @param taskId 用户任务ID
   * @return
   * @throws Exception
   * @throw
   * @return 任务列表
   */
  public String doTask() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    /*
     * if (uid == null || uid == 0) { msg = "用户ID不能为空!"; } else if (taskId == null || taskId == 0) {
     * msg = "用户任务ID不能为空!"; } else { int flag = 0; UserTaskPojo task =
     * userTaskService.getById(taskId); if (task != null && task.getStartTime() == null) { task =
     * new UserTaskPojo(); task.setStartTime(new Date()); task.setId(taskId); flag =
     * userTaskService.update(task); } if (flag == 1) { b = true; msg = "开启任务成功!"; } else if (task
     * != null && task.getStartTime() != null) { msg = "任务已开启过!"; } else { msg = "开启任务失败!"; } }
     */

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (taskId == null || taskId == 0) {
      msg = "任务ID不能为空!";
    } else {
      Long babyId = findBabyId(uid);
      if (babyId != null) {
        params.clear();
        params.put("userId", uid);
        params.put("babyId", babyId);
        UserGrowthLinePojo userGrowthLinePojo2 =
            userGrowthLineService.getUserGrowthLineByUid(params);
        if (userGrowthLinePojo2 == null) {
          // 用户成长线表插入记录
          UserGrowthLinePojo userGrowthLine = new UserGrowthLinePojo();
          userGrowthLine.setUserId(uid);
          userGrowthLine.setBabyId(babyId);
          userGrowthLine.setListenSkill(0);
          userGrowthLine.setLanguageSkill(0);
          userGrowthLine.setVisionSkill(0);
          userGrowthLine.setMoveSkill(0);
          userGrowthLine.setTouchSkill(0);
          userGrowthLine.setActionSkill(0);
          userGrowthLine.setEmotionSkill(0);
          userGrowthLine.setLanguageGrow(0);
          userGrowthLine.setCoordinationSkill(0);
          userGrowthLine.setBrainGrow(0);
          userGrowthLine.setExploringSkill(0);
          userGrowthLine.setSpecialSkill(0);
          userGrowthLine.setSportSkill(0);
          userGrowthLine.setThinkSkill(0);
          userGrowthLine.setLearnSkill(0);

          userGrowthLine.setListenSkillLast(0);
          userGrowthLine.setLanguageSkillLast(0);
          userGrowthLine.setVisionSkillLast(0);
          userGrowthLine.setMoveSkillLast(0);
          userGrowthLine.setTouchSkillLast(0);
          userGrowthLine.setActionSkillLast(0);
          userGrowthLine.setEmotionSkillLast(0);
          userGrowthLine.setLanguageGrowLast(0);
          userGrowthLine.setCoordinationSkillLast(0);
          userGrowthLine.setBrainGrowLast(0);
          userGrowthLine.setExploringSkillLast(0);
          userGrowthLine.setSpecialSkillLast(0);
          userGrowthLine.setSportSkillLast(0);
          userGrowthLine.setThinkSkillLast(0);
          userGrowthLine.setLearnSkillLast(0);

          userGrowthLine.setScore(0l);
          userGrowthLine.setCognitiveSkill(0);
          userGrowthLine.setCognitiveSkillLast(0);
          userGrowthLineService.addUserGrowthLine(userGrowthLine);
        }

        params = new HashMap<String, Object>();
        params.put("userId", uid);
        params.put("taskSettingId", taskId);
        int i = userTaskService.countBy(params);
        if (i != 0) {
          msg = "该任务已开启过!";
        } else {
          TaskSettingPojo taskSetting = taskSettingService.getTaskSettingById(taskId);
          if (taskSetting == null) {
            msg = "未找到对应任务信息!";
          } else {
            UserTaskPojo task = new UserTaskPojo();
            task.setUserId(uid);
            task.setTaskSettingId(taskId);
            task.setTaskDate(taskSetting.getTaskDate());
            task.setStartTime(new Date());
            task.setStatus(0);
            int j = userTaskService.add(task);
            result.put("userTaskId", 0);
            if (j != 0) {
              b = true;
              result.put("userTaskId", task.getId());
              msg = "该任务开启成功!";
            } else {
              msg = "该任务开启失败!";
            }
          }
        }
      } else {
        msg = "查询不到宝宝信息!";
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
   * 完成任务
   * 
   * @param uid 用户ID
   * @param taskId 用户任务ID
   * @return
   * @throws Exception
   * @throw
   * @return 任务列表
   */
  public String doneTask() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (taskId == null || taskId == 0) {
      msg = "用户任务ID不能为空!";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", taskId);
      params.put("userId", uid);
      List<UserTaskPojo> tasks = userTaskService.listPage(params);
      if (tasks != null && tasks.size() == 1) {
        UserTaskPojo task = tasks.get(0);
        if (task.getTaskType() != null && task.getTaskType() == 2) {
          UserTaskPojo utask = new UserTaskPojo();
          utask.setStatus(1);
          utask.setEndTime(new Date());
          utask.setId(taskId);
          int i = userTaskService.update(utask);

          if (i > 0) {
            // 添加任务历史
            Date dt = new Date();
            int ageType = 0;

            // 查询成长值
            params.clear();
            params.put("userId", uid);
            params.put("isDefault", 1);
            params.put("isDelete", 0);
            userBabyPojo = userBabyService.getByParams(params);
            if (userBabyPojo != null && userBabyPojo.getBabyBirthday() != null) {
              // 查询年龄
              double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
              // 查询年龄对应能力
              if (age >= 0.0 && age <= 0.06) {
                ageType = 1;
              } else if (age > 0.06 && age <= 1.0) {
                ageType = 2;
              } else if (age > 1.0 && age <= 3.0) {
                ageType = 3;
              } else if (age > 3.0 && age <= 6.0) {
                ageType = 4;
              } else if (age > 6.0 && age <= 12.0) {
                ageType = 5;
              } else if (age > 12.0 && age <= 16.0) {
                ageType = 6;
              } else {
                ageType = 6;
              }

              // 查找成长线
              params.clear();
              params.put("userId", uid);
              params.put("babyId", userBabyPojo.getId());
              UserGrowthLinePojo userGrow = userGrowthLineService.getUserGrowthLineByUid(params);
              // 更新成长线
              int score = task.getTaskScore();
              int skillType = Integer.valueOf(task.getAbility());
              if (userGrow != null) {
                Map<String, Object> growLine = new HashMap<String, Object>();
                SellerService.calcGrowLine(skillType, score, growLine);
                growLine.put("score", score);
                growLine.put("userId", userGrow.getUserId());
                growLine.put("babyId", userGrow.getBabyId());
                userGrowthLineService.addUserScore(growLine);
              }

              if (ageType > 0) {
                params.clear();
                params.put("userId", uid);
                params.put("ageType", ageType);
                params.put("taskDate", StringUtil.dateToString(dt));
                int count = userTaskService.isALLTaskDone(params);
                // 没有未完成的任务
                if (count == 0) {
                  UserCalendarPojo ucal = new UserCalendarPojo();
                  ucal.setUserId(uid);
                  ucal.setCalendar(dt);
                  userCalendarService.add(ucal);
                }
              }
              b = true;
              msg = "任务完成!";
            }
          } else {
            msg = "任务更新失败!";
          }
        } else {
          msg = "不是线下任务!";
        }
      } else {
        msg = "找不到用户对应任务!";
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

  // ------ 社圈 ------ //
  /**
   * 获取分享内容
   * 
   * @return
   * @throws Exception
   */
  public String getShareContentApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();

    if (type == null || type <= 0) {
      msg = "类型不能为空哦！~";
    } else if (type != 4 && (id == null || id <= 0)) {
      msg = "ID不能为空哦！~";
    } else {
      if (type == 1) {
        UserMakerBrandPojo userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(id);
        if (userMakerBrandPojo != null) {
          String content = userMakerBrandPojo.getContent();
          if (content != null && !"".equals(content)) {
            content = getHtmlText(content);
            if (content.length() > 50) {
              result.put("content", content.substring(0, 50));
            } else {
              result.put("content", content);
            }
          } else {
            result.put("content", "");
          }
          result.put("title",
              userMakerBrandPojo.getBrandName() == null ? "" : userMakerBrandPojo.getBrandName());
          result.put("url", ConstParam.URL + "/v3.0/getShareContentWebApi.do?type=" + type + "&id="
              + id);
          result.put("image", ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
              + userMakerBrandPojo.getLogo());
          b = true;
        }
      } else if (type == 2) {
        UserMakerThemePojo userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
        if (userMakerThemePojo != null) {
          String content = userMakerThemePojo.getContent();
          if (content != null && !"".equals(content)) {
            content = getHtmlText(content);
            if (content.length() > 50) {
              result.put("content", content.substring(0, 50));
            } else {
              result.put("content", content);
            }
          } else {
            result.put("content", "");
          }
          result.put(
              "title",
              userMakerThemePojo.getSpecialName() == null ? "" : userMakerThemePojo
                  .getSpecialName());
          result.put("url", ConstParam.URL + "/v3.0/getShareContentWebApi.do?type=" + type + "&id="
              + id);
          result.put("image", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
              + userMakerThemePojo.getBanner());
          b = true;
        }
      } else if (type == 3) {
        UserCirclePostPojo userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
        if (userCirclePostPojo != null) {
          String content = userCirclePostPojo.getContent();
          if (content != null && !"".equals(content)) {
            content = getHtmlText(content);
            if (content.length() > 50) {
              result.put("content", content.substring(0, 50));
            } else {
              result.put("content", content);
            }
          } else {
            result.put("content", "");
          }
          result.put("title",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          result.put("url", ConstParam.URL + "/v3.0/getShareContentWebApi.do?type=" + type + "&id="
              + id);
          result.put("image", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
              + userCirclePostPojo.getBanner());
          b = true;
        }
      } else if (type == 4) {
        if (ageType == null || ageType <= 0) {
          msg = "年龄类型Id不能为空哦！~";
        } else if (skillType == null || skillType <= 0) {
          msg = "能力类型Id不能为空哦！~";
        } else {
          params.clear();
          params.put("status", 1);
          params.put("orderBy", "update_date desc,create_date desc");
          params.put("ageType", ageType);
          params.put("skillType", skillType);
          KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);
          if (knowledgeBasePojo != null) {
            String content = knowledgeBasePojo.getContent();
            if (content != null && !"".equals(content)) {
              content = getHtmlText(content);
              if (content.length() > 50) {
                result.put("content", content.substring(0, 50));
              } else {
                result.put("content", content);
              }
            } else {
              result.put("content", "");
            }
            result.put("title",
                knowledgeBasePojo.getTitle() == null ? "" : knowledgeBasePojo.getTitle());
            result.put("url", ConstParam.URL + "/v3.0/getShareContentWebApi.do?type=" + type
                + "&ageType=" + ageType + "&skillType=" + skillType);
            result.put("image", ConstParam.URL + "/upfiles/knowledgeBase" + File.separator
                + knowledgeBasePojo.getSmallIcon());
            b = true;
          }
        }
      } else if (type == 5) {
        int idint = id.intValue();
        PlatformSpecialPojo platformSpecialPojo = platformSpecialService.getById(idint);
        if (platformSpecialPojo != null) {
          String content = platformSpecialPojo.getContent();
          if (content != null && !"".equals(content)) {
            content = getHtmlText(content);
            if (content.length() > 50) {
              result.put("content", content.substring(0, 50));
            } else {
              result.put("content", content);
            }
          } else {
            result.put("content", "");
          }
          result.put("title",
              platformSpecialPojo.getTitle() == null ? "" : platformSpecialPojo.getTitle());
          result.put("url", ConstParam.URL + "/v3.0/getShareContentWebApi.do?type=" + type + "&id="
              + id);
          result.put("image", ConstParam.URL + "/upfiles/platformSpecial" + File.separator
              + platformSpecialPojo.getBanner());
          b = true;
        }
      } else {
        Long babyId = findBabyId(id);
        if (babyId != null) {
          params.clear();
          params.put("userId", id);
          params.put("babyId", babyId);
          UserGrowthLinePojo userGrowthLinePojo =
              userGrowthLineService.getUserGrowthLineByUid(params);
          if (userGrowthLinePojo != null) {
            result.put("content", "挖掘出每个孩子的闪亮点");
            result.put("title", "记录你一点一滴的成长");
            result.put("url", ConstParam.URL + "/v3.0/getShareContentWebApi.do?type=" + type
                + "&id=" + id);
            result.put("image", ConstParam.URL + "/upfiles/growthLine/growthLineLogo.jpg");
            b = true;
          }
        } else {
          msg = "查询不到宝宝信息!";
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

  /**
   * 获取分享内容Web
   * 
   * @throws Exception
   */
  public String getShareContentWebApi() throws Exception {
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> params = new HashMap<String, Object>();
    if (type == null || type <= 0) {
      FileUtil.alertMessage("类型不能为空哦！~");
    } else if (type != 4 && (id == null || id <= 0)) {
      FileUtil.alertMessage("ID不能为空哦！~");
    } else {
      if (type == 1) {
        UserMakerBrandPojo userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(id);
        if (userMakerBrandPojo != null) {
          content = userMakerBrandPojo.getContent();
          title = userMakerBrandPojo.getBrandName();
          imgurl =
              ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
                  + userMakerBrandPojo.getLogo();
        }
      } else if (type == 2) {
        UserMakerThemePojo userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
        if (userMakerThemePojo != null) {
          content = userMakerThemePojo.getContent();
          title = userMakerThemePojo.getSpecialName();
          imgurl =
              ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
                  + userMakerThemePojo.getBanner();
        }
      } else if (type == 3) {
        UserCirclePostPojo userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
        if (userCirclePostPojo != null) {
          content = userCirclePostPojo.getContent();
          title = userCirclePostPojo.getTitle();

          imgurl1 = userCirclePostPojo.getBanner();
          imgurl2 =
              ConstParam.URL + "/upfiles/userCirclePost" + File.separator
                  + userCirclePostPojo.getBanner();
          imgurl3 = userCirclePostPojo.getImage() == null ? "" : userCirclePostPojo.getImage();
          imgurl4 =
              ConstParam.URL + "/upfiles/userCirclePost" + File.separator
                  + userCirclePostPojo.getImage();
          url = userCirclePostPojo.getVideoUrl();

          imgurl =
              ConstParam.URL + "/upfiles/userCirclePost" + File.separator
                  + userCirclePostPojo.getBanner();
        }
      } else if (type == 4) {
        if (ageType == null || ageType <= 0) {
          FileUtil.alertMessage("年龄类型Id不能为空哦！~");
        } else if (skillType == null || skillType <= 0) {
          FileUtil.alertMessage("能力类型Id不能为空哦！~");
        } else {
          params.clear();
          params.put("status", 1);
          params.put("orderBy", "update_date desc,create_date desc");
          params.put("ageType", ageType);
          params.put("skillType", skillType);
          KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);
          if (knowledgeBasePojo != null) {
            content = knowledgeBasePojo.getContent();
            title = knowledgeBasePojo.getTitle();

            imgurl =
                ConstParam.URL + "/upfiles/knowledgeBase" + File.separator
                    + knowledgeBasePojo.getSmallIcon();
          }
        }
      } else if (type == 5) {
        int idint = id.intValue();
        PlatformSpecialPojo platformSpecialPojo = platformSpecialService.getById(idint);
        if (platformSpecialPojo != null) {
          content = platformSpecialPojo.getContent();
          title = platformSpecialPojo.getTitle();
          Long sc =
              new Long(platformSpecialPojo.getSpecialCategories() == null ? 0
                  : platformSpecialPojo.getSpecialCategories());
          Long id =
              new Long(platformSpecialPojo.getProductType() == null ? 0
                  : platformSpecialPojo.getId());
          if (sc == 2 && id > 0) {
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put("type", 6);
            params2.put("titleId", id);
            List<ActivityProductPojo> activityProductList =
                activityProductService.findActivityGoods(params2);
            for (ActivityProductPojo activityProductPojo : activityProductList) {
              activityProductPojo.setActivePrice(Double.valueOf(df.format(activityProductPojo
                  .getActivePrice())));
            }
            ActionContext actionContext = ActionContext.getContext();
            actionContext.put("list", activityProductList);

            imgurl =
                ConstParam.URL + "/upfiles/platformSpecial" + File.separator
                    + platformSpecialPojo.getBanner();
          }
        }
      } else {
        Long babyId = findBabyId(id);
        if (babyId != null) {
          params.clear();
          params.put("userId", id);
          params.put("babyId", babyId);
          UserGrowthLinePojo userGrowthLinePojo =
              userGrowthLineService.getUserGrowthLineByUid(params);
          if (userGrowthLinePojo != null) {
            title = "记录你一点一滴的成长";
            getShareGrowthLineContentWebApi(userGrowthLinePojo.getUserId());

            imgurl = ConstParam.URL + "/upfiles/growthLine/growthLineLogo.jpg";
          }
        } else {
          FileUtil.alertMessage("查询不到宝宝信息!");
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 获取分享内容Web（成长线）
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public void getShareGrowthLineContentWebApi(Long uid) throws Exception {
    // if (uid == null || uid <= 0) {
    // FileUtil.alertMessage("用户ID不能为空哦！~");
    // // msg = "用户ID不能为空哦！~";
    // } else {
    // if (uid == null || uid <= 0) {
    // FileUtil.alertMessage("用户ID不能为空哦！");
    // } else {
    Map<String, Object> param = new HashMap<String, Object>();
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    param.put("userId", uid);
    param.put("status", 1);
    param.put("histFlag", 2);
    param.put("taskDate", StringUtil.dateToString(cal.getTime()));
    userTaskService.countBy(param);
    // if (userTaskCount <= 0) {
    // FileUtil.skipAction("getGrowthLineWebApi.do?{\"error_msg\":\"请先完成今日任务!\"}");
    // return null;
    // }
    Long babyId = findBabyId(uid);
    if (babyId != null) {
      // 查询成长值
      param.clear();
      param.put("userId", uid);
      param.put("isDefault", 1);
      param.put("isDelete", 0);
      userBabyPojo = userBabyService.getByParams(param);
      int ageType = 1;
      if (userBabyPojo != null) {
        double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
        // 查询年龄对应专题
        if (age >= 0.0 && age <= 0.06) {
          ageType = 1;
        } else if (age > 0.06 && age <= 1.0) {
          ageType = 2;
        } else if (age > 1.0 && age <= 3.0) {
          ageType = 3;
        } else if (age > 3.0 && age <= 6.0) {
          ageType = 4;
        } else if (age > 6.0 && age <= 12.0) {
          ageType = 5;
        } else if (age > 12.0 && age <= 16.0) {
          ageType = 6;
        } else if (age > 16.0) {
          ageType = 6;
        }
      }
      param.clear();
      param.put("userId", uid);
      param.put("babyId", babyId);
      UserGrowthLinePojo userGrowp = userGrowthLineService.getUserGrowthLineByUid(param);
      if (userGrowp != null) {
        // 雷达图
        HonorRulePojo honorRule = null;
        Integer[] skills = new Integer[6];
        String[] skillnames = new String[6];
        if (ageType == 1) {
          skills[0] = userGrowp.getVisionSkill();
          skills[1] = userGrowp.getListenSkill();
          skills[2] = userGrowp.getTouchSkill();
          skills[3] = userGrowp.getActionSkill();
          skills[4] = userGrowp.getEmotionSkill();
          skills[5] = userGrowp.getLanguageGrow();

          skillnames[0] = "视觉能力";
          skillnames[1] = "听觉能力";
          skillnames[2] = "触觉能力";
          skillnames[3] = "动作发育";
          skillnames[4] = "情感社交";
          skillnames[5] = "语言发育";

          // thisMounth =
          // String.valueOf(userGrowp.getVisionSkill()).concat(",")
          // .concat(String.valueOf(userGrowp.getListenSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getTouchSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getActionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getLanguageGrow()));
          lastMounth =
              String.valueOf(userGrowp.getVisionSkillLast()).concat(",")
                  .concat(String.valueOf(userGrowp.getListenSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getTouchSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getActionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getLanguageGrowLast()));

          // skillNames = "视觉能力,听发能力,触觉能力,动作发育,情感社交,语言发育";
        } else if (ageType == 2) {
          skills[0] = userGrowp.getCoordinationSkill();
          skills[1] = userGrowp.getLanguageGrow();
          skills[2] = userGrowp.getBrainGrow();
          skills[3] = userGrowp.getActionSkill();
          skills[4] = userGrowp.getEmotionSkill();
          skills[5] = userGrowp.getExploringSkill();

          skillnames[0] = "手眼协调";
          skillnames[1] = "语言发育";
          skillnames[2] = "大脑发育";
          skillnames[3] = "动作发育";
          skillnames[4] = "情感社交";
          skillnames[5] = "探索认知";

          // thisMounth =
          // String.valueOf(userGrowp.getCoordinationSkill()).concat(",")
          // .concat(String.valueOf(userGrowp.getLanguageGrow())).concat(",")
          // .concat(String.valueOf(userGrowp.getBrainGrow())).concat(",")
          // .concat(String.valueOf(userGrowp.getActionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getExploringSkill()));
          lastMounth =
              String.valueOf(userGrowp.getCoordinationSkillLast()).concat(",")
                  .concat(String.valueOf(userGrowp.getLanguageGrowLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getBrainGrowLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getActionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getExploringSkillLast()));
          // skillNames = "手眼协调,语言发育,大脑发育,动作发育,情感社交,探索认知";
        } else if (ageType == 3) {
          skills[0] = userGrowp.getMoveSkill();
          skills[1] = userGrowp.getLanguageSkill();
          skills[2] = userGrowp.getSpecialSkill();
          skills[3] = userGrowp.getEmotionSkill();
          skills[4] = userGrowp.getCognitiveSkill();
          skills[5] = userGrowp.getSportSkill();

          skillnames[0] = "动手能力";
          skillnames[1] = "语言能力";
          skillnames[2] = "专项能力";
          skillnames[3] = "情感社交";
          skillnames[4] = "认知能力";
          skillnames[5] = "运动能力";

          // thisMounth =
          // String.valueOf(userGrowp.getMoveSkill()).concat(",")
          // .concat(String.valueOf(userGrowp.getLanguageSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getSpecialSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getCognitiveSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getSportSkill()));
          lastMounth =
              String.valueOf(userGrowp.getMoveSkillLast()).concat(",")
                  .concat(String.valueOf(userGrowp.getLanguageSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getSpecialSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getCognitiveSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getSportSkillLast()));
          // skillNames = "动手能力,语言能力,专项能力,情感社交,认知能力,运动能力";
        } else if (ageType == 4) {
          skills[0] = userGrowp.getThinkSkill();
          skills[1] = userGrowp.getMoveSkill();
          skills[2] = userGrowp.getSpecialSkill();
          skills[3] = userGrowp.getEmotionSkill();
          skills[4] = userGrowp.getCognitiveSkill();
          skills[5] = userGrowp.getSportSkill();

          skillnames[0] = "思维能力";
          skillnames[1] = "动手能力";
          skillnames[2] = "专项能力";
          skillnames[3] = "情感社交";
          skillnames[4] = "认知能力";
          skillnames[5] = "运动能力";

          // thisMounth =
          // String.valueOf(userGrowp.getThinkSkill()).concat(",")
          // .concat(String.valueOf(userGrowp.getMoveSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getSpecialSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getCognitiveSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getSportSkill()));
          lastMounth =
              String.valueOf(userGrowp.getThinkSkillLast()).concat(",")
                  .concat(String.valueOf(userGrowp.getMoveSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getSpecialSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getCognitiveSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getSportSkillLast()));
          // skillNames = "思维能力,动手能力,专项能力,情感社交,认知能力,运动能力";
        } else if (ageType == 5 || ageType == 6) {
          skills[0] = userGrowp.getThinkSkill();
          skills[1] = userGrowp.getMoveSkill();
          skills[2] = userGrowp.getSpecialSkill();
          skills[3] = userGrowp.getEmotionSkill();
          skills[4] = userGrowp.getLearnSkill();
          skills[5] = userGrowp.getSportSkill();

          skillnames[0] = "思维能力";
          skillnames[1] = "动手能力";
          skillnames[2] = "专项能力";
          skillnames[3] = "情感社交";
          skillnames[4] = "学习认知";
          skillnames[5] = "运动能力";

          // thisMounth =
          // String.valueOf(userGrowp.getThinkSkill()).concat(",")
          // .concat(String.valueOf(userGrowp.getMoveSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getSpecialSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getLearnSkill())).concat(",")
          // .concat(String.valueOf(userGrowp.getSportSkill()));
          lastMounth =
              String.valueOf(userGrowp.getThinkSkillLast()).concat(",")
                  .concat(String.valueOf(userGrowp.getMoveSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getSpecialSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getLearnSkillLast())).concat(",")
                  .concat(String.valueOf(userGrowp.getSportSkillLast()));
          // skillNames = "思维能力,动手能力,专项能力,情感社交,学习认知,运动能力";
        }
        thisMounth = StringUtils.join(skills, ",");
        skillNames = StringUtils.join(skillnames, ",");

        // 称号
        growthHonor = new String[17];
        for (int i = 0; i < growthHonor.length; i++) {
          growthHonor[i] = "";
        }
        Map<String, Object> params = new HashMap<String, Object>();
        long score = userGrowp.getScore() == null ? 0l : userGrowp.getScore();
        int skillType = 0;
        if (score > 5 && score <= 30) {
          skillType = -1;
        } else if (score > 30 && score <= 60) {
          skillType = -2;
        } else if (score > 60 && score <= 100) {
          skillType = -3;
        } else if (score > 100) {
          skillType = -4;
        }
        if (skillType < 0) {
          params.clear();
          params.put("ageType", ageType);
          params.put("skillType", skillType);
          honorRule = honorRuleService.selectHonorBy(params);
          if (honorRule != null && StringUtils.isNotEmpty(honorRule.getHonor())) {
            growthHonor[0] = honorRule.getHonor();
          }
        }
        // 能力分数排序
        this.sortSkill(skills, skillnames);
        if (skills[0] > 0) {
          max = skills[0] * 2;
        } else {
          max = 10;
        }
        // 荣誉
        if (skills[0] != null && skills[0] > 60 && skillnames[0] != null) {
          SysDictPojo dict = new SysDictPojo();
          dict.setType("ability");
          dict.setName(skillnames[0]);
          List<SysDictPojo> dicts = sysDictService.sysDictAllList(dict, null);

          if (dicts != null && dicts.size() > 0) {
            skillType = Integer.valueOf(dicts.get(0).getValue());
          }
          if (skillType > 0) {
            params.clear();
            params.put("ageType", ageType);
            params.put("skillType", skillType);
            honorRule = honorRuleService.selectHonorBy(params);
            if (honorRule != null && StringUtils.isNotEmpty(honorRule.getHonor())) {
              growthHonor[1] = honorRule.getHonor();
            }
          }
          growthHonor[2] = skillnames[0];
        }

        // 可增强能力
        if (skills[5] != null) {
          growthHonor[3] = skillnames[5];
        }
        if (skills[4] != null) {
          growthHonor[4] = skillnames[4];
        }

        if (skillnames[5] != null) {
          SysDictPojo dict = new SysDictPojo();
          dict.setType("ability");
          dict.setName(skillnames[5]);
          List<SysDictPojo> dicts = sysDictService.sysDictAllList(dict, null);

          if (dicts != null && dicts.size() > 0) {
            skillType = Integer.valueOf(dicts.get(0).getValue());
          }
        }
        if (skillType > 0) {
          growthHonor[5] = String.valueOf(ageType);
          growthHonor[6] = String.valueOf(skillType);
        }
      }
    } else {
      FileUtil.alertMessage("宝宝信息未找到！");
    }
  }

  /**
   * 获取html文本
   * 
   * @return
   * @throw
   * @return String
   */
  public String getHtmlText(String content) {
    Pattern p_script;
    Matcher m_script;
    Pattern p_style;
    Matcher m_style;
    Pattern p_html;
    Matcher m_html;

    String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>}
    String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>}
    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    m_script = p_script.matcher(content);
    content = m_script.replaceAll(""); // 过滤script标签

    p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    m_style = p_style.matcher(content);
    content = m_style.replaceAll(""); // 过滤style标签

    p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    m_html = p_html.matcher(content);
    content = m_html.replaceAll(""); // 过滤html标签

    content = content.replace("&nbsp;", " ");// 这是过滤空格的标签，把原来的空格换成空格键
    return content;
  }

  /**
   * 编辑帖子
   * 
   * @return
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String getUpdateUserCirclePostApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;

    if (title == null || "".equals(title)) {
      msg = "标题不能为空哦！~";
    } else if (content == null || "".equals(content)) {
      msg = "文字内容不能为空哦！~";
    } else if (postId == null || postId <= 0) {
      msg = "帖子ID不能为空哦！~";
    } else {
      UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
      userCirclePostPojo.setId(postId);
      userCirclePostPojo.setTitle(title);
      userCirclePostPojo.setContent(content);

      String file_name = "";
      String uploadPath = "";
      if (img1 != null) {
        file_name = StringUtil.getCurrentDateStr() + ".jpg";
        uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", img1);
        userCirclePostPojo.setBanner(file_name);
      }
      if (img2 != null) {
        file_name = StringUtil.getCurrentDateStr() + ".jpg";
        uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", img2);
        userCirclePostPojo.setImage(file_name);
      }
      if (url != null && !"".equals(url)) {
        userCirclePostPojo.setVideoUrl(url);
      } else {
        userCirclePostPojo.setVideoUrl("");
      }

      userCirclePostPojo.setStatus(0);
      result = userCirclePostService.updateUserCirclePost(userCirclePostPojo);
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
   * 编辑帖子-获取帖子信息
   * 
   * @return
   * @throws Exception
   */
  public String getUserCirclePostInfoApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();

    if (id == null || id == 0) {
      msg = "笔记ID不能为空哦！~";
    } else {
      UserCirclePostPojo userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
      if (userCirclePostPojo != null) {
        // result.put("uid",
        // userCirclePostPojo.getUserId() == null ? "" : userCirclePostPojo.getUserId());
        // result.put("circleId",
        // userCirclePostPojo.getCircleId() == null ? "" : userCirclePostPojo.getCircleId());
        result.put("title",
            userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
        result.put("content",
            userCirclePostPojo.getContent() == null ? "" : userCirclePostPojo.getContent());
        result.put("img1", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
        result.put("img2", userCirclePostPojo.getImage() == null ? "" : ConstParam.URL
            + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getImage());
        result.put("url",
            userCirclePostPojo.getVideoUrl() == null ? "" : userCirclePostPojo.getVideoUrl());
      } else {
        msg = "该笔记不存在或已删除哦！~";
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
   * 发帖
   * 
   * @return
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String getAddUserCirclePostApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int result = 0;

    if (uid == null || uid <= 0) {
      msg = "用户ID不能为空哦！~";
    } else if (id == null || id <= 0) {
      msg = "圈子ID不能为空哦！~";
    } else if (title == null || "".equals(title)) {
      msg = "标题不能为空哦！~";
    } else if (content == null || "".equals(content)) {
      msg = "文字内容不能为空哦！~";
    } else if (img1 == null) {
      msg = "图片不能为空哦！~";
    } else {
      // 过滤敏感字符
      SensitivewordFilter filter = new SensitivewordFilter();
      System.out.println("敏感词的数量：" + SensitivewordFilter.sensitiveWordMap.size());

      String string = content;
      System.out.println("待检测语句字数：" + string.length());
      long beginTime = System.currentTimeMillis();
      Set<String> set = filter.getSensitiveWord(string, 2);
      string = filter.replaceSensitiveWord(string, 2, "*");
      long endTime = System.currentTimeMillis();
      System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
      System.out.println("结果为：" + string);
      System.out.println("总共消耗时间为：" + (endTime - beginTime));

      String titleString = title;
      System.out.println("待检测语句字数：" + titleString.length());
      long beginTime2 = System.currentTimeMillis();
      Set<String> set2 = filter.getSensitiveWord(titleString, 2);
      titleString = filter.replaceSensitiveWord(titleString, 2, "*");
      long endTime2 = System.currentTimeMillis();
      System.out.println("语句中包含敏感词的个数为：" + set2.size() + "。包含：" + set2);
      System.out.println("结果为：" + titleString);
      System.out.println("总共消耗时间为：" + (endTime2 - beginTime2));

      UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
      userCirclePostPojo.setUserId(uid);
      userCirclePostPojo.setCircleId(id);
      userCirclePostPojo.setTitle(title);
      userCirclePostPojo.setContent(string);

      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", img1);
      userCirclePostPojo.setBanner(file_name);

      if (img2 != null) {
        file_name = StringUtil.getCurrentDateStr() + ".jpg";
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", img2);
        userCirclePostPojo.setImage(file_name);
      } else {
        userCirclePostPojo.setImage("");
      }
      if (url != null && !"".equals(url)) {
        boolean isMatch = SellerService.IsUrl(url);
        if (isMatch) {
          msg = "连接可用!";
          userCirclePostPojo.setVideoUrl(url);
        } else {
          userCirclePostPojo.setVideoUrl("");
          msg = "连接不可用!";
        }
      } else {
        userCirclePostPojo.setVideoUrl("");
      }

      userCirclePostPojo.setLikeNum(0l);
      userCirclePostPojo.setCommentNum(0l);
      userCirclePostPojo.setCollectNum(0l);
      userCirclePostPojo.setLookNum(0l);
      userCirclePostPojo.setScore(0);

      userCirclePostPojo.setAgeType(0l);
      userCirclePostPojo.setSkillType(0l);
      userCirclePostPojo.setOptionType(0l);
      userCirclePostPojo.setProductType(0l);

      userCirclePostPojo.setStatus(0);
      userCirclePostPojo.setIsDelete(0);
      userCirclePostPojo.setSecSkillType(0l);
      result = userCirclePostService.addUserCirclePost(userCirclePostPojo);
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
   * 社圈列表
   * 
   * @return
   * @throws SQLException
   */
  public String socialCircleList() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    // Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Object> socialCircleArrayList = new ArrayList<Object>();
    // 分页
    int ps = 10;
    if (pageSize != null && pageSize != 0) {
      ps = pageSize;
    }
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
    } else {
      option.put("pageNo", (pageNo - 1) * ps);
    }
    option.put("pageSize", ps);
    option.put("orderBy", "followNum desc");
    option.put("status", 1);

    // 判断用户时候登录
    if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
    } else if (circleTypeId == null || circleTypeId == 0) {
      error_msg = "社圈分类id不能为空!";
    } else {
      option.put("uid", uid);
      option.put("circleTypeId", circleTypeId);
      List<SocialCirclePojo> socialCircleList = socialCircleService.findSocialCircleList(option);
      if (socialCircleList.size() != 0) {
        for (SocialCirclePojo sc : socialCircleList) {
          item = new HashMap<String, Object>();
          item.put("socialId", sc.getId());
          item.put("followNum", sc.getFollowNum() == null ? "" : sc.getFollowNum());
          item.put("banner", sc.getBanner() == null ? "" : ConstParam.URL + "/upfiles/socialCircle"
              + File.separator + sc.getBanner());
          item.put("title", sc.getTitle());
          // 查询是否关注
          params.put("userId", uid);
          params.put("type", 2);
          params.put("typeId", sc.getId());
          params.put("isFollow", 1);
          Integer i = userCircleFollowService.userCircleFollowCount(params);
          if (i > 0) {
            item.put("isConcern", 1);
          } else {
            item.put("isConcern", 0);
          }
          socialCircleArrayList.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到社圈列表";
      }
    }

    map.put("success", success);
    map.put("result", socialCircleArrayList);
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
   * 社圈详情
   * 
   * @return
   * @throws SQLException
   */
  public String socialCircleDetailsApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("success", success);

    map.put("result", map);
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
   * 社圈分类
   * 
   * @return
   * @throws SQLException
   */
  public String socialCircleCategory() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> sctMap = null;
    List<Object> sctArray = new ArrayList<Object>();
    params.put("status", 1);
    List<SocialCircleTypePojo> sctList = socialCircleTypeService.findSocialCircleTypeList(params);
    if (sctList.size() != 0) {
      for (SocialCircleTypePojo sct : sctList) {
        sctMap = new HashMap<String, Object>();
        sctMap.put("id", sct.getId());
        sctMap.put("name", sct.getName());
        sctMap.put("image", sct.getImages() == null ? "" : ConstParam.URL
            + "/upfiles/socialCircleType" + File.separator + sct.getImages());
        sctArray.add(sctMap);
      }
      success = true;
    } else {
      error_msg = "查询不到社圈分类列表";
    }
    map.put("success", success);
    map.put("result", sctArray);
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
    } else if (followType != 1 && followType != 2) {
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
   * 热门玩家
   * 
   * @return
   * @throws SQLException
   */
  public String hotPlayerApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    List<Map<String, Object>> userTalentAuthList = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    // 分页
    int ps = 20;
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
    params.put("userType", 11);
    params.put("orderBy", "followNum desc");
    List<UserTalentAuthPojo> utaList = userTalentAuthService.findUserTalentAuthList(params);
    if (utaList.size() != 0) {
      for (UserTalentAuthPojo uta : utaList) {
        item = new HashMap<String, Object>();
        item.put("id", uta.getUserId());
        item.put("userName", StringUtil.checkString(uta.getUserName()));
        item.put("userImage", uta.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + uta.getUserImage());
        item.put("followNum", uta.getFollowNum());
        userTalentAuthList.add(item);
      }
      success = true;
    } else {
      error_msg = "查询不到热门玩家列表";
    }

    map.put("success", success);
    map.put("result", userTalentAuthList);
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
   * 社圈-详情
   * 
   * @return
   * @throws SQLException
   */
  public String circleDetailApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> map3 = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> params2 = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> userCirclePostList = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      error_msg = "用户id不能为空";
    } else if (socialCircleId == null || socialCircleId == 0) {
      error_msg = "社圈id不能为空!";
    } else {
      // 查找用户信息
      SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
      map3.put("userType", sysLogin.getType());
      // map3.put("type", 2);
      // 查询是否关注
      params2.put("userId", uid);
      params2.put("typeId", socialCircleId);
      params2.put("isFollow", 1);
      Integer i = userCircleFollowService.userCircleFollowCount(params2);
      if (i > 0) {
        map3.put("isConcern", 1);
      } else {
        map3.put("isConcern", 0);
      }
      // 查找圈子笔记总数
      params.put("circleId", socialCircleId);
      int postCount = userCirclePostService.userCirclePostCount(params);
      map3.put("noteCount", postCount);
      // 查找圈子头像和名称
      SocialCirclePojo socialCirclePojo = socialCircleService.findSocialCircleById(socialCircleId);
      if (socialCirclePojo != null) {
        map3.put("circleName",
            socialCirclePojo.getTitle() == null ? "" : socialCirclePojo.getTitle());
        map3.put("circleBanner", socialCirclePojo.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/socialCircle" + File.separator + socialCirclePojo.getBanner());
        success = true;
      } else {
        map3.put("circleName", "");
        map3.put("circleBanner", "");
      }
      // 分页
      int ps = 3;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      // 查找圈子笔记
      params.put("status", 1);
      params.put("orderBy", "u.collect_num desc");
      List<UserCirclePostPojo> ucpList = userCirclePostService.userCirclePostList(params);
      if (ucpList.size() != 0) {
        for (UserCirclePostPojo ucp : ucpList) {
          item = new HashMap<String, Object>();
          item.put("banner", ucp.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + ucp.getBanner());
          item.put("postId", ucp.getId() == null ? 0 : ucp.getId());
          item.put("postTitle", ucp.getTitle() == null ? "" : ucp.getTitle());
          item.put("collectNum", ucp.getCollectNum() == null ? 0 : ucp.getCollectNum());
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex());
          item.put("authorName", ucp.getUserName() == null ? "" : ucp.getUserName());
          item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
          item.put("authorId", ucp.getUserId() == null ? 0 : ucp.getUserId());
          // 查询是否收藏
          params2.clear();
          params2.put("userId", uid);
          params2.put("typeId", ucp.getId());
          params2.put("authorId", ucp.getUserId());
          params2.put("isCollect", 1);
          int j = userPostCollectService.userPostCollectCount(params2);
          if (j > 0) {
            item.put("postCollect", 1);
          } else {
            item.put("postCollect", 0);
          }
          item.put("authorFansNum", getUserFollowInfoCount(null, ucp.getUserId(), 1));
          item.put("authorCollectNum", getUserFollowInfoCount(ucp.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, ucp.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          userCirclePostList.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到热门玩家列表!";
      }
    }

    map2.put("userCirclePostList", userCirclePostList);
    map2.put("circleInfo", map3);
    map.put("success", success);
    map.put("result", map2);
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
   * 社圈详情-笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String circleNotesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> userCirclePostList = new ArrayList<Map<String, Object>>();
    if (socialCircleId == null || socialCircleId == 0) {
      error_msg = "社圈id不能为空!";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
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
      params.put("circleId", socialCircleId);
      params.put("orderBy", "u.look_num desc");
      // 查找圈子笔记
      List<UserCirclePostPojo> ucpList = userCirclePostService.userCirclePostList(params);
      if (ucpList.size() != 0) {
        for (UserCirclePostPojo ucp : ucpList) {
          item = new HashMap<String, Object>();
          item.put("postId", ucp.getId() == null ? 0 : ucp.getId());
          item.put("postTitle", ucp.getTitle() == null ? "" : ucp.getTitle());
          item.put("banner", ucp.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + ucp.getBanner());
          item.put("content", ucp.getContent() == null ? "" : ucp.getContent());
          item.put("authorName", ucp.getUserName() == null ? "" : ucp.getUserName());
          item.put("authorId", ucp.getUserId() == null ? 0 : ucp.getUserId());
          item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
          item.put("collectNum", ucp.getCollectNum() == null ? 0 : ucp.getCollectNum());
          item.put("commentNum", ucp.getCommentNum() == null ? 0 : ucp.getCommentNum());
          item.put("likeNum", ucp.getLikeNum() == null ? 0 : ucp.getLikeNum());
          String dTime =
              UtilDate.timeDifference(ucp.getCreateDateStr(), UtilDate.getDateFormatter());
          item.put("dTime", dTime);
          // 查询是否收藏
          params.clear();
          params.put("userId", uid);
          params.put("typeId", ucp.getId());
          params.put("authorId", ucp.getUserId());
          params.put("isCollect", 1);
          int i = userPostCollectService.userPostCollectCount(params);
          if (i > 0) {
            item.put("postCollect", 1);
          } else {
            item.put("postCollect", 0);
          }
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex());
          item.put("authorFansNum", getUserFollowInfoCount(null, ucp.getUserId(), 1));
          item.put("authorCollectNum", getUserFollowInfoCount(ucp.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, ucp.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          userCirclePostList.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到圈子笔记!";
      }
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
   * 达人精选
   * 
   * @return
   * @throws Exception
   */
  public String talentSiftApi() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<AgeSkillLinkPojo> aslList = null;
    List<Map<String, Object>> ageSkillLinkList = new ArrayList<Map<String, Object>>();
    if (uid != null && uid != 0) {
      UserInfoPojo userInfoPojo = userInfoService.findUserInfoByUserId(uid);
      if (userInfoPojo != null) {
        if (userInfoPojo.getBabyBirthday() != null) {
          // 查询年龄
          double age =
              SellerService.getAge(StringUtil.stringToDate(userInfoPojo.getBabyBirthday()));
          // 查询年龄对应能力
          if (age >= 0.0 && age <= 0.06) {
            aslList = ageSkillLinkService.findSkillByAgeType(1);
          } else if (age > 0.06 && age <= 1.0) {
            aslList = ageSkillLinkService.findSkillByAgeType(2);
          } else if (age > 1.0 && age <= 3.0) {
            aslList = ageSkillLinkService.findSkillByAgeType(3);
          } else if (age > 3.0 && age <= 6.0) {
            aslList = ageSkillLinkService.findSkillByAgeType(4);
          } else if (age > 6.0 && age <= 12.0) {
            aslList = ageSkillLinkService.findSkillByAgeType(5);
          } else if (age > 12.0 && age <= 16.0) {
            aslList = ageSkillLinkService.findSkillByAgeType(6);
          } else {
            aslList = ageSkillLinkService.findSkillByAgeType(6);
          }
          if (aslList.size() != 0) {
            for (AgeSkillLinkPojo asl : aslList) {
              item = new HashMap<String, Object>();
              item.put("skillId", asl.getSkillId());
              item.put("skillName", asl.getSkillName());
              item.put("skillValue", asl.getSkillValue());
              ageSkillLinkList.add(item);
            }
            success = true;
          } else {
            error_msg = "查询不到能力!";
          }
        } else {
          error_msg = "查询不到年龄!";
          aslList = ageSkillLinkService.findSkillByAgeType(1);
          if (aslList.size() != 0) {
            for (AgeSkillLinkPojo asl : aslList) {
              item = new HashMap<String, Object>();
              item.put("skillId", asl.getSkillId());
              item.put("skillName", asl.getSkillName());
              item.put("skillValue", asl.getSkillValue());
              ageSkillLinkList.add(item);
            }
            success = true;
          } else {
            error_msg = "查询不到能力!";
          }
        }
      } else {
        error_msg = "查询不到该用户!";
      }
    } else {
      error_msg = "用户id不能为空!";
    }
    map.put("success", success);
    map.put("result", ageSkillLinkList);
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
    if (uid == null || uid == 0) {
      error_msg = "用户ID不能为空!";
    } else if (ftId == null || ftId == 0) {
      error_msg = "达人ID不能为空!";
    } else {
      UserInfoPojo userInfoPojo = userInfoService.findUserInfoByUserId(ftId);
      if (userInfoPojo != null) {
        params = new HashMap<String, Object>();
        params.put("userId", uid);
        params.put("type", 1);
        params.put("typeId", ftId);
        params.put("isFollow", 1);
        int i = userCircleFollowService.userCircleFollowCount(params);
        userInfo.put("isConcern", i > 0 ? 1 : 0);
        userInfo.put("userName", userInfoPojo.getUserName());
        userInfo.put("userId", userInfoPojo.getUserId());
        userInfo.put("userImage", userInfoPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userInfoPojo.getUserImage());
        String babyBirth = userInfoPojo.getBabyBirthday();
        if (StringUtils.isNotBlank(babyBirth)) {
          // 查询年龄
          double age = SellerService.getAge(StringUtil.stringToDate(babyBirth));
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
        } else {
          userInfo.put("age", "刚出生");
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
   * 社圈-昵称-发布的笔记-笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String userCircleNotesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> params2 = new HashMap<String, Object>();
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
        for (UserCirclePostPojo ucp : ucpList) {
          item = new HashMap<String, Object>();
          item.put("banner", ucp.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + ucp.getBanner());
          item.put("postId", ucp.getId());// 帖子id
          item.put("postTitle", ucp.getTitle() == null ? "" : ucp.getTitle());
          item.put("auditStatus", ucp.getStatus() == null ? "" : ucp.getStatus());
          item.put("authorName", ucp.getUserName() == null ? "" : ucp.getUserName());
          item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
          // 查询是否收藏
          params2.put("userId", uid);
          params2.put("typeId", ucp.getId());
          params2.put("authorId", ucp.getUserId());
          params2.put("isCollect", 1);
          int i = userPostCollectService.userPostCollectCount(params2);
          if (i > 0) {
            item.put("postCollect", 1);
          } else {
            item.put("postCollect", 0);
          }
          item.put("collectNum", ucp.getCollectNum() == null ? 0 : ucp.getCollectNum());
          item.put("authorId", ucp.getUserId());
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex());
          item.put("authorFansNum", getUserFollowInfoCount(null, ucp.getUserId(), 1));
          item.put("authorCollectNum", getUserFollowInfoCount(ucp.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, ucp.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          userCirclePostList.add(item);
        }
        success = true;
      } else {
        error_msg = "还没有笔记~";
      }
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
   * 我的圈-关注的人列表
   * 
   * @return
   * @throws SQLException
   */
  public String myToFocusApi() throws SQLException {
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
      params.put("type", 1);
      params.put("isFollow", 1);
      params.put("userId", uid);// 用户id
      List<UserCircleFollowPojo> ucfList = userCircleFollowService.findFocusUserList(params);// 查询关注的人
      if (ucfList.size() != 0) {
        for (UserCircleFollowPojo ucf : ucfList) {
          item = new HashMap<String, Object>();
          // item.put("typeId", ucf.getTypeId());// 被关注人id
          /*
           * item.put("userImage", ucf.getUserImage() == null ? "" : ConstParam.URL +
           * "/upfiles/userlogo" + File.separator + ucf.getUserImage()); item.put("userName",
           * ucf.getUserName() == null ? "" : ucf.getUserName());
           */
          /*
           * params2.clear(); params2.put("userId", ucf.getTypeId()); params2.put("type", 1);
           * params2.put("isFollow", 1); int followNum =
           * userCircleFollowService.userCircleFollowCount(params2); item.put("followNum",
           * followNum);// 粉丝
           */
          SysLoginPojo sysLoginPojo = sysLoginService.getUserInfoById(ucf.getTypeId());
          item.put("authorLogo", sysLoginPojo.getImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + sysLoginPojo.getImage());
          item.put("authorName", sysLoginPojo.getName() == null ? "" : sysLoginPojo.getName());
          item.put("authorId", sysLoginPojo.getId() == null ? "" : sysLoginPojo.getId());
          item.put("authorSex", sysLoginPojo.getUserSex() == null ? "" : sysLoginPojo.getUserSex());

          UserCirclePostPojo userCirclePost =
              userCirclePostService.getUserCirclePostByIdUserId(ucf.getTypeId());
          // post = new HashMap<String, Object>();
          if (userCirclePost != null) {
            item.put("isPost", 1);
            item.put("postId", userCirclePost.getId() == null ? "" : userCirclePost.getId());// 帖子id
            item.put("authorId",
                userCirclePost.getUserId() == null ? 0 : userCirclePost.getUserId());
            item.put("authorName",
                userCirclePost.getUserName() == null ? "" : userCirclePost.getUserName());
            item.put("authorLogo", userCirclePost.getUserImage() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + userCirclePost.getUserImage());
            item.put("collectNum",
                userCirclePost.getCollectNum() == null ? 0 : userCirclePost.getCollectNum());// 收藏数
            item.put("commentNum",
                userCirclePost.getCommentNum() == null ? 0 : userCirclePost.getCommentNum());// 评论数
            item.put("postTitle",
                userCirclePost.getTitle() == null ? "" : userCirclePost.getTitle());// 标题
            item.put("banner", userCirclePost.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userCirclePost" + File.separator + userCirclePost.getBanner());// 图片
            // 查询是否收藏
            params2.put("userId", uid);
            params2.put("typeId", userCirclePost.getId());
            params2.put("authorId", userCirclePost.getUserId());
            params2.put("isCollect", 1);
            int i = userPostCollectService.userPostCollectCount(params2);
            if (i > 0) {
              item.put("postCollect", 1);
            } else {
              item.put("postCollect", 0);
            }
            item.put("authorSex",
                userCirclePost.getUserSex() == null ? "" : userCirclePost.getUserSex());
            item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePost.getUserId(), 1));
            item.put("authorCollectNum",
                getUserFollowInfoCount(userCirclePost.getUserId(), null, 1));
            if (getUserFollowInfoCount(uid, userCirclePost.getUserId(), 1) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
          } else {
            item.put("postId", "");// 帖子id
            item.put("collectNum", "");// 收藏数
            item.put("commentNum", "");// 评论数
            item.put("postTitle", "");// 标题
            item.put("banner", "");// 图片
            item.put("postCollect", "");
            // item.put("authorId","");
            // item.put("authorName","");
            // item.put("authorLogo", "");
            // item.put("authorSex","");
            item.put("authorFansNum", getUserFollowInfoCount(null, ucf.getTypeId(), 1));
            item.put("authorCollectNum", getUserFollowInfoCount(ucf.getTypeId(), null, 1));
            if (getUserFollowInfoCount(uid, ucf.getTypeId(), 1) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
            error_msg = "查询不到帖子!";
            item.put("isPost", 0);
          }
          params2.clear();
          params2.put("userId", ucf.getTypeId());
          params2.put("status", 1);
          int postNum = userCirclePostService.userCirclePostCount(params2);
          item.put("postNum", postNum);
          // item.put("postInfo", post);
          userCircleNoteList.add(item);
        }
        map2.put("userCircleNoteList", userCircleNoteList);
        success = true;
      } else {
        error_msg = "用户没有关注的人!";
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
      params.put("type", 1);
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
          params2.clear();
          params2.put("typeId", ucf.getUserId());
          params2.put("type", 1);
          params2.put("isFollow", 1);
          int followNum = userCircleFollowService.userCircleFollowCount(params2);// 查询关注他的人count
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
            item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePost.getUserId(), 1));
            item.put("authorCollectNum",
                getUserFollowInfoCount(userCirclePost.getUserId(), null, 1));
            if (getUserFollowInfoCount(uid, userCirclePost.getUserId(), 1) == 0) {
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
            item.put("authorFansNum", getUserFollowInfoCount(null, ucf.getUserId(), 1));
            item.put("authorCollectNum", getUserFollowInfoCount(ucf.getUserId(), null, 1));
            if (getUserFollowInfoCount(uid, ucf.getUserId(), 1) == 0) {
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
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        option.put("pageNo", 0);
      } else {
        option.put("pageNo", (pageNo - 1) * ps);
      }
      option.put("pageSize", ps);

      params.put("userId", uid);
      params.put("isFollow", 1);
      params.put("type", 2);
      params.put("status", 1);
      // 查询用户关注的圈子
      List<UserCircleFollowPojo> ucfList = userCircleFollowService.findSocialCircleList(params);
      if (ucfList.size() != 0) {
        for (UserCircleFollowPojo ucf : ucfList) {
          scs.add(ucf.getSocialCircleId());
        }
        if (scs.size() != 0) {
          option.put("scs", scs);
          option.put("status", 1);
          // option.put("orderBy", "look_num desc");
          List<UserCirclePostPojo> ucpList = userCirclePostService.findBycircleIds(option);
          for (UserCirclePostPojo ucp : ucpList) {
            item = new HashMap<String, Object>();
            item.put("postId", ucp.getId());
            item.put("authorName", ucp.getUserName() == null ? "" : ucp.getUserName());
            item.put("authorId", ucp.getUserId() == null ? 0 : ucp.getUserId());
            item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
            item.put("postTitle", ucp.getTitle() == null ? "" : ucp.getTitle());
            item.put("content", ucp.getContent() == null ? "" : ucp.getContent());
            item.put("commentNum", ucp.getCommentNum() == null ? 0 : ucp.getCommentNum());
            item.put("collectNum", ucp.getCollectNum() == null ? 0 : ucp.getCollectNum());
            item.put("lookNum", ucp.getLookNum() == null ? 0 : ucp.getLookNum());
            item.put("banner", ucp.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userCirclePost" + File.separator + ucp.getBanner());
            // 查询是否收藏
            params.clear();
            params.put("userId", uid);
            params.put("typeId", ucp.getId());
            params.put("authorId", ucp.getUserId());
            params.put("isCollect", 1);
            int i = userPostCollectService.userPostCollectCount(params);
            if (i > 0) {
              item.put("postCollect", 1);
            } else {
              item.put("postCollect", 0);
            }
            item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex());
            item.put("authorFansNum", getUserFollowInfoCount(null, ucp.getUserId(), 1));
            item.put("authorCollectNum", getUserFollowInfoCount(ucp.getUserId(), null, 1));
            if (getUserFollowInfoCount(uid, ucp.getUserId(), 1) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
            hotPostList.add(item);
          }
          if (hotPostList.size() != 0) {
            success = true;
          } else {
            error_msg = "查询不到用户关注的圈子的帖子!";
          }
        } else {
          error_msg = "查询不到用户关注的圈子!";
        }
      } else {
        error_msg = "该用户未关注圈子!";
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

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (abilityId == null || abilityId == 0) {
      msg = "能力value不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
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
      option.put("orderBy", "u.sorting desc,u.update_date desc,u.create_date desc");
      List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
      if (list.size() != 0) {
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", userCirclePostPojo.getId());
          item.put("banner", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
              + userCirclePostPojo.getBanner());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? 0
              : userCirclePostPojo.getCollectNum());
          item.put("authorId",
              userCirclePostPojo.getUserId() == null ? 0 : userCirclePostPojo.getUserId());
          // 查询是否收藏
          params.clear();
          params.put("userId", uid);
          params.put("typeId", userCirclePostPojo.getId());
          params.put("authorId", userCirclePostPojo.getUserId());
          params.put("isCollect", 1);
          int i = userPostCollectService.userPostCollectCount(params);
          if (i > 0) {
            item.put("postCollect", 1);
          } else {
            item.put("postCollect", 0);
          }
          item.put("authorSex",
              userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
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
   * 社圈-昵称—参与的圈组－圈子列表
   * 
   * @return
   * @throws SQLException
   */
  public String talentSocialCirclesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Object> scArrayList = new ArrayList<Object>();
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
    option.put("status", 1);
    if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
    } else if (talentId == null || talentId == 0) {
      error_msg = "达人id不能为空";
    } else {
      // 根据用户id查找社圈
      option.put("userId", talentId);
      option.put("type", 2);
      option.put("isFollow", 1);
      option.put("orderBy", "follow_num desc");
      List<UserCircleFollowPojo> talentFocusList =
          userCircleFollowService.findSocialCircleList(option);
      if (talentFocusList.size() != 0) {
        for (UserCircleFollowPojo talentFocus : talentFocusList) {
          item = new HashMap<String, Object>();
          // 判断达人关注圈子用户是否关注
          option.clear();
          option.put("userId", uid);
          option.put("typeId", talentFocus.getTypeId());
          option.put("isFollow", 1);
          option.put("type", 2);
          option.put("isDelete", 0);
          int i = userCircleFollowService.userCircleFollowCount(option);
          if (i > 0) {
            item.put("isConcern", 1);
          } else {
            item.put("isConcern", 0);
          }
          item.put("banner", talentFocus.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/socialCircle" + File.separator + talentFocus.getBanner());
          item.put("title", talentFocus.getScTitle() == null ? "" : talentFocus.getScTitle());
          item.put("followNum", talentFocus.getFollowNum() == null ? 0 : talentFocus.getFollowNum());
          item.put("socialCircleId",
              talentFocus.getSocialCircleId() == null ? 0 : talentFocus.getSocialCircleId());
          scArrayList.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到用户关注的圈子!";
      }
    }

    map.put("success", success);
    map.put("result", scArrayList);
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
   * 社圈-热门玩家-昵称—图片集
   * 
   * @return
   * @throws SQLException
   */
  public String talentUserPhotos() throws SQLException {
    boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> photos = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = null;
    Map<String, Object> item = null;

    if (uid == null || uid == 0) {
      msg = "用户id不能为空";
    } else if (talentId == null || talentId == 0) {
      msg = "达人id不能为空";
    } else {
      // 分页
      int ps = 20;
      if (pageNo == null || pageNo == 0) {
        pageNo = 1;
      }
      option = new HashMap<String, Object>();
      option.put("pageNo", (pageNo - 1) * ps);
      option.put("pageSize", ps);
      option.put("userId", talentId);
      option.put("status", 1);
      option.put("isDelete", 0);

      List<UserCirclePostPojo> circlePosts = userCirclePostService.selectPhotos(option);
      if (circlePosts != null && circlePosts.size() > 0) {
        for (UserCirclePostPojo post : circlePosts) {
          if (StringUtils.isNotBlank(post.getBanner())) {
            item = new HashMap<String, Object>();
            item.put("postId", post.getId());
            item.put("image",
                ConstParam.URL + "/upfiles/userCirclePost" + File.separator + post.getBanner());
            photos.add(item);
          }
          if (StringUtils.isNotBlank(post.getImage())) {
            item = new HashMap<String, Object>();
            item.put("postId", post.getId());
            item.put("image",
                ConstParam.URL + "/upfiles/userCirclePost" + File.separator + post.getImage());
            photos.add(item);
          }
        }
      } else {
        msg = "没有图片信息!";
      }
      success = true;
    }
    map.put("success", success);
    map.put("result", photos);
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

  // 从List中随机出count个对象
  private List<SocialCirclePojo> randomList(List<SocialCirclePojo> socialCircleList, int count) {
    // 创建一个长度为count(count<=list)的数组,用于存随机数
    int[] a = new int[count];
    // 利于此数组产生随机数
    int[] b = new int[socialCircleList.size()];
    int size = socialCircleList.size();

    // 取样填充至数组a满
    for (int i = 0; i < count; i++) {
      int num = (int) (Math.random() * size); // [0,size)
      int where = -1;
      for (int j = 0; j < b.length; j++) {
        if (b[j] != -1) {
          where++;
          if (where == num) {
            b[j] = -1;
            a[i] = j;
          }
        }
      }
      size = size - 1;
    }
    // a填满后 将数据加载到rslist
    List<SocialCirclePojo> rslist = new ArrayList<SocialCirclePojo>();
    for (int i = 0; i < count; i++) {
      SocialCirclePojo df = socialCircleList.get(a[i]);
      rslist.add(df);
    }
    return rslist;
  }

  /**
   * 社圈-猜你收藏（随机返回4条用户没有关注的数据）
   * 
   * @return
   * @throws SQLException
   */
  public String guessSocialCirclesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Object> socialCircleArrayList = new ArrayList<Object>();
    List<SocialCirclePojo> rslist = new ArrayList<SocialCirclePojo>();
    // 分页
    int ps = 50;
    if (pageSize != null && pageSize != 0) {
      ps = pageSize;
    }
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
    } else {
      option.put("pageNo", (pageNo - 1) * ps);
    }
    option.put("pageSize", ps);
    option.put("rank", 1);
    option.put("status", 1);

    // 判断用户时候登录
    if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
    } else {
      option.put("uid", uid);
      option.put("isFollow", 0);
      List<SocialCirclePojo> socialCircleList = socialCircleService.findSocialCircleList(option);
      if (socialCircleList != null && socialCircleList.size() != 0) {
        if (socialCircleList.size() < 10) {
          rslist = randomList(socialCircleList, socialCircleList.size());
          if (rslist != null) {
            for (SocialCirclePojo sc : rslist) {
              item = new HashMap<String, Object>();
              item.put("id", sc.getId());
              item.put("followNum", sc.getFollowNum() == null ? 0 : sc.getFollowNum());
              item.put("nickname", sc.getNickname() == null ? "" : sc.getNickname());
              item.put("banner", sc.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/socialCircle" + File.separator + sc.getBanner());
              item.put("title", sc.getTitle() == null ? "" : sc.getTitle());
              // 查找用户是否关注圈子
              params.put("userId", uid);
              params.put("type", 2);
              params.put("typeId", sc.getId());
              params.put("isFollow", 1);
              int i = userCircleFollowService.userCircleFollowCount(params);
              if (i > 0) {
                item.put("isFollow", 1);
              } else {
                item.put("isFollow", 0);
              }
              socialCircleArrayList.add(item);
            }
            success = true;
          }
        } else {
          rslist = randomList(socialCircleList, 10);
          if (rslist != null) {
            for (SocialCirclePojo sc : rslist) {
              item = new HashMap<String, Object>();
              item.put("id", sc.getId());
              item.put("followNum", sc.getFollowNum() == null ? 0 : sc.getFollowNum());
              item.put("nickname", sc.getNickname() == null ? "" : sc.getNickname());
              item.put("banner", sc.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/socialCircle" + File.separator + sc.getBanner());
              item.put("title", sc.getTitle() == null ? "" : sc.getTitle());
              // 查找用户是否关注圈子
              params.put("userId", uid);
              params.put("type", 2);
              params.put("typeId", sc.getId());
              params.put("isFollow", 1);
              int i = userCircleFollowService.userCircleFollowCount(params);
              if (i > 0) {
                item.put("isFollow", 1);
              } else {
                item.put("isFollow", 0);
              }
              socialCircleArrayList.add(item);
            }
            success = true;
          } else {
            error_msg = "查询不到社圈列表";
          }
        }
      } else {
        error_msg = "已关注全部圈子!";
      }
    }

    map.put("success", success);
    map.put("result", socialCircleArrayList);
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
   * 社圈分类和社圈列表
   * 
   * @return
   * @throws SQLException
   */
  public String socialCircleAndTypeApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    Map<String, Object> sctMap = null;
    List<Object> scArrayList = new ArrayList<Object>();
    List<Object> sctArrayList = new ArrayList<Object>();
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
    option.put("status", 1);
    // 查找社圈分类
    params.put("status", 1);
    List<SocialCircleTypePojo> sctList = socialCircleTypeService.findSocialCircleTypeList(params);
    if (sctList.size() != 0) {
      for (SocialCircleTypePojo sct : sctList) {
        sctMap = new HashMap<String, Object>();
        scArrayList = new ArrayList<Object>();
        sctMap.put("id", sct.getId());
        sctMap.put("name", sct.getName());
        sctMap.put("image", sct.getImages() == null ? "" : ConstParam.URL
            + "/upfiles/socialCircleType" + File.separator + sct.getImages());
        // 判断社圈分类id
        if (sct.getId() == null && sct.getId() == 0) {
          error_msg = "获取不到社圈分类id";
        } else {
          // 根据社圈分类id查找社圈列表
          option.put("circleTypeId", sct.getId());
          List<SocialCirclePojo> socialCircleList =
              socialCircleService.findSocialCircleList(option);
          if (socialCircleList.size() != 0) {
            for (SocialCirclePojo sc : socialCircleList) {
              item = new HashMap<String, Object>();
              item.put("socialId", sc.getId());
              item.put("followNum", sc.getFollowNum());
              item.put("banner", sc.getBanner() == null ? "" : ConstParam.URL
                  + "/upfiles/userSocialCircle" + File.separator + sc.getBanner());
              item.put("title", sc.getTitle());
              // 查找用户是否关注圈子
              /*
               * params.put("userId", uid); params.put("type", 2); params.put("typeId", sc.getId());
               * int i = userCircleFollowService.userCircleFollowCount(params); if (i > 0) {
               * item.put("isFollow", 1); } else { item.put("isFollow", 0); }
               */
              scArrayList.add(item);
            }
            success = true;
          } else {
            error_msg += "分类id" + sct.getId() + "查询不到社圈列表\n";
          }
        }
        sctMap.put("type", scArrayList);
        sctArrayList.add(sctMap);
      }
    } else {
      error_msg = "查询不到社圈分类列表";
    }
    map.put("success", success);
    map.put("result", sctArrayList);
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
   * 用户关注的圈子
   * 
   * @return
   * @throws SQLException
   */
  public String userFocusSocialCircleApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Object> scArrayList = new ArrayList<Object>();
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
    option.put("status", 1);
    if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
    } else {
      // 根据用户id查找社圈
      option.put("userId", uid);
      option.put("type", 2);
      option.put("isFollow", 1);
      option.put("orderBy", "follow_num desc");
      List<UserCircleFollowPojo> ucfList = userCircleFollowService.findSocialCircleList(option);
      params.put("userId", uid);
      params.put("isFollow", 1);
      if (ucfList.size() != 0) {
        for (UserCircleFollowPojo ucf : ucfList) {
          item = new HashMap<String, Object>();
          params.put("typeId", ucf.getSocialCircleId());
          // 查找用户是否关注圈子
          int i = userCircleFollowService.userCircleFollowCount(params);
          if (i > 0) {
            item.put("isConcern", 1);
          } else {
            item.put("isConcern", 0);
          }
          item.put("banner", ucf.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/socialCircle" + File.separator + ucf.getBanner());
          item.put("title", ucf.getScTitle() == null ? "" : ucf.getScTitle());
          item.put("followNum", ucf.getFollowNum() == null ? 0 : ucf.getFollowNum());
          item.put("socialCircleId", ucf.getSocialCircleId() == null ? 0 : ucf.getSocialCircleId());
          scArrayList.add(item);
        }
        success = true;
      } else {
        error_msg = "还没关注过圈子!";
      }
    }
    map.put("success", success);
    map.put("result", scArrayList);
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
        num = userCircleFollowService.userCircleFollowCount(params);
        result.put("followNum", num);
        // 关注的圈子数
        params.put("type", 2);
        num = userCircleFollowService.userCircleFollowCount(params);
        result.put("followCircleNum", num);
        // 粉丝数
        params.clear();
        params.put("type", 1);
        params.put("typeId", uid);
        params.put("isFollow", 1);
        num = userCircleFollowService.userCircleFollowCount(params);
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
            msg = "没有该年龄!";
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
   * 个人资料信息
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   */
  public String getProfile() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      SysLoginPojo user = sysLoginService.getUserInfoById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        result.put("name", user.getName() == null ? "" : user.getName());
        result.put("sex", user.getSex() == null ? 1 : user.getSex());
        result.put("logo", ConstParam.URL + "/upfiles/userlogo/" + user.getImage());
        params.put("userId", uid);
        params.put("isDelete", 0);
        params.put("isDefault", 1);
        List<UserBabyPojo> userBabyList = userBabyService.listPage(params);
        if (userBabyList != null && userBabyList.size() != 0) {
          result.put("babySex", userBabyList.get(0).getBabySex() == null ? "" : userBabyList.get(0)
              .getBabySex());
          result.put(
              "babyBirth",
              userBabyList.get(0).getBabyBirthday() == null ? "" : StringUtil
                  .dateToString(userBabyList.get(0).getBabyBirthday()));
        } else {
          result.put("babySex", "");
          result.put("babyBirth", "");
          msg = "未找到宝宝信息!";
        }
        b = true;
      } else {
        msg = "未找到用户信息!";
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
   * 个人资料编辑
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   */
  public String editProfile() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      SysLoginPojo user = sysLoginService.getUserInfoById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        // 上传头像
        if (logo != null) {
          String image = sellerService.uploadFile(logo, "upfiles" + File.separator + "userlogo");
          user.setImage(image);
        }
        if (name != null && !"".equals(name)) {
          user.setName(name);
        }
        if (sex != null && sex != 0) {
          user.setSex(sex);
        }

        if (sex != null && sex != 0) {
          user.setSex(sex);
        }

        if (babySex != null && babySex != 0) {
          user.setBabySex(babySex);
        }

        if (babyBirth != null && !"".equals(babyBirth)) {
          user.setBabyBirthday(babyBirth);
        }

        sysLoginService.updateUserInfoById(user);
        msg = "提交成功!";

        // 查询成长值
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
          // 修改宝宝表信息
          UserBabyPojo userBaby = new UserBabyPojo();
          userBaby.setId(userBabyPojo.getId());
          userBaby.setBabySex(babySex);
          userBaby.setBabyBirthday(StringUtil.stringToDate(babyBirth));
          userBabyService.update(userBaby);

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
            msg = "查询不到年龄!";
          }
        }
        b = true;
      } else {
        msg = "未找到用户信息!";
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
   * 笔记浏览历史
   * 
   * @param uid 用户ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String postViewHistory() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserPostHistoryPojo> postHists = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      params.put("isDelete", 0);
      postHists = userPostHistoryService.listPage(params);
      if (postHists != null && postHists.size() > 0) {
        for (UserPostHistoryPojo postHist : postHists) {
          params.clear();
          params.put("id", postHist.getPostId());
          params.put("status", 1);
          params.put("isDelete", 0);
          UserCirclePostPojo userCirclePostPojo = userCirclePostService.findBycircleParams(params);
          if (userCirclePostPojo != null) {
            post = new HashMap<String, Object>();
            post.put("histId", postHist.getId());
            post.put("postId", postHist.getPostId());
            post.put("banner", postHist.getBanner() == null ? "" : ConstParam.URL
                + "/upfiles/userCirclePost/" + postHist.getBanner());
            post.put("postTitle", postHist.getTitle() == null ? "" : postHist.getTitle());
            post.put("author", postHist.getAuthor() == null ? "" : postHist.getAuthor());
            post.put("collectNum", postHist.getCollectNum() == null ? 0 : postHist.getCollectNum());
            post.put("userId", postHist.getUserId() == null ? 0 : postHist.getUserId());
            // 查询是否收藏
            params.clear();
            params.put("userId", uid);
            params.put("typeId", postHist.getPostId());
            params.put("authorId", postHist.getAuthorId());
            params.put("isCollect", 1);
            int i = userPostCollectService.userPostCollectCount(params);
            if (i > 0) {
              post.put("postCollect", 1);
            } else {
              post.put("postCollect", 0);
            }
            post.put("authorName", postHist.getAuthor() == null ? "" : postHist.getAuthor());
            post.put("authorId", postHist.getAuthorId() == null ? 0 : postHist.getAuthorId());
            post.put("authorlogo", postHist.getAuthorLogo() == null ? "" : ConstParam.URL
                + "/upfiles/userlogo/" + postHist.getAuthorLogo());
            post.put("authorSex", postHist.getAuthorSex() == null ? "" : postHist.getAuthorSex());
            post.put("authorFansNum", getUserFollowInfoCount(null, postHist.getAuthorId(), 1));
            post.put("authorCollectNum", getUserFollowInfoCount(postHist.getAuthorId(), null, 1));
            if (getUserFollowInfoCount(uid, postHist.getAuthorId(), 1) == 0) {
              post.put("authorFollow", 0);
            } else {
              post.put("authorFollow", 1);
            }
            result.add(post);
          }
        }
        b = true;
      } else {
        msg = "您还没浏览任何笔记!";
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
   * 笔记浏览历史移除
   * 
   * @param uid 用户ID
   * @param histId 笔记浏览历史ID
   * @return
   * @throws Exception
   */
  public String rmPostViewHist() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (histId == null || histId == 0) {
      msg = "笔记浏览历史ID不能为空!";
    } else {
      int flag = userPostHistoryService.delete(histId);
      if (flag == 1) {
        b = true;
        msg = "删除成功";
      } else {
        msg = "删除失败";
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
   * 发布的笔记列表
   * 
   * @param uid 用户ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String publishedPosts() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserCirclePostPojo> posts = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      posts = userCirclePostService.userCirclePostList(params);
      if (posts != null && posts.size() > 0) {
        for (UserCirclePostPojo cpost : posts) {
          post = new HashMap<String, Object>();
          post.put("auditStatus", cpost.getStatus());
          post.put("postId", cpost.getId());
          post.put("banner", cpost.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost/" + cpost.getBanner());
          post.put("postTitle", cpost.getTitle() == null ? "" : cpost.getTitle());
          post.put("authorName", cpost.getUserName() == null ? "" : cpost.getUserName());
          post.put("authorLogo", cpost.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo/" + cpost.getUserImage());
          post.put("authorId", cpost.getUserId() == null ? 0 : cpost.getUserId());
          post.put("collectNum", cpost.getCollectNum() == null ? 0 : cpost.getCollectNum());
          post.put("status", cpost.getStatus() == null ? 0 : cpost.getStatus());
          // post.put("userId", cpost.getUserId() == null ? 0 : cpost.getUserId());
          // post.put("authorSex", cpost.getUserSex());
          // 查询是否收藏
          params.clear();
          params.put("userId", uid);
          params.put("typeId", cpost.getId());
          params.put("authorId", cpost.getUserId());
          params.put("isCollect", 1);
          int i = userPostCollectService.userPostCollectCount(params);
          if (i > 0) {
            post.put("postCollect", 1);
          } else {
            post.put("postCollect", 0);
          }
          post.put("authorSex", cpost.getUserSex() == null ? "" : cpost.getUserSex());
          post.put("authorFansNum", getUserFollowInfoCount(null, cpost.getUserId(), 1));
          post.put("authorCollectNum", getUserFollowInfoCount(cpost.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, cpost.getUserId(), 1) == 0) {
            post.put("authorFollow", 0);
          } else {
            post.put("authorFollow", 1);
          }
          result.add(post);
        }
        b = true;
      } else {
        msg = "您还没写笔记,加油!";
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
   * 发布的笔记移除
   * 
   * @param uid 用户ID
   * @param postId 笔记ID
   * @return
   * @throws Exception
   */
  public String rmPost() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (postId == null || postId == 0) {
      msg = "笔记ID不能为空!";
    } else {
      int flag = userCirclePostService.deleteUserCirclePostById(postId);
      if (flag == 1) {
        b = true;
        msg = "移除成功";
      } else {
        msg = "移除失败";
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
   * 回复的笔记列表
   * 
   * @param uid 用户ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String commentedPosts() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserPostCommentPojo> postCmts = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      // 类型1-创客主题 2-笔记
      params.put("type", 2);
      postCmts = userPostCommentService.listPage(params);
      if (postCmts != null && postCmts.size() > 0) {
        for (UserPostCommentPojo cpost : postCmts) {
          post = new HashMap<String, Object>();
          // 帖子评论ID
          post.put("cmtId", cpost.getId());
          // 帖子ID
          post.put("postId", cpost.getTypeId());
          post.put("banner", cpost.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost/" + cpost.getBanner());
          post.put("postTitle", cpost.getTitle() == null ? "" : cpost.getTitle());
          post.put("authorName", cpost.getAuthor() == null ? "" : cpost.getAuthor());
          post.put("collectNum", cpost.getCollectNum() == null ? 0 : cpost.getCollectNum());
          // 状态 1-审核通过
          post.put("status", cpost.getStatus() == null ? 0 : cpost.getStatus());
          post.put("userId", cpost.getUserId() == null ? 0 : cpost.getUserId());
          // 查询是否收藏
          params.clear();
          params.put("userId", uid);
          params.put("typeId", cpost.getTypeId());
          params.put("authorId", cpost.getAuthorId());
          params.put("isCollect", 1);
          int i = userPostCollectService.userPostCollectCount(params);
          if (i > 0) {
            post.put("postCollect", 1);
          } else {
            post.put("postCollect", 0);
          }
          post.put("authorName", cpost.getAuthor() == null ? "" : cpost.getAuthor());
          post.put("authorLogo", cpost.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo/" + cpost.getUserImage());
          post.put("authorId", cpost.getAuthorId() == null ? 0 : cpost.getAuthorId());
          post.put("authorSex", cpost.getAuthorSex() == null ? "" : cpost.getAuthorSex());
          post.put("authorFansNum", getUserFollowInfoCount(null, cpost.getAuthorId(), 1));
          post.put("authorCollectNum", getUserFollowInfoCount(cpost.getAuthorId(), null, 1));
          if (getUserFollowInfoCount(uid, cpost.getAuthorId(), 1) == 0) {
            post.put("authorFollow", 0);
          } else {
            post.put("authorFollow", 1);
          }
          result.add(post);
        }
        b = true;
      } else {
        msg = "您没有回复的笔记!";
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
   * 收藏的笔记列表
   * 
   * @param uid 用户ID
   * @param pageNo 页码
   * @return
   * @throws Exception
   */
  public String collectedPosts() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<UserPostCollectPojo> postCols = null;
    Map<String, Object> params = null;
    Map<String, Object> post = null;
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params = new HashMap<String, Object>();
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("userId", uid);
      // 类型1-创客主题 2-笔记
      params.put("type", 2);
      // 1-已收藏 0-取消收藏
      params.put("isCollect", 1);
      postCols = userPostCollectService.userPostCollectList(params);
      if (postCols != null && postCols.size() > 0) {
        for (UserPostCollectPojo cpost : postCols) {
          post = new HashMap<String, Object>();
          // // 帖子收藏ID
          // post.put("cid", cpost.getId());
          // // 帖子ID
          // post.put("id", cpost.getTypeId());
          // post.put("pic", ConstParam.URL + "/upfiles/userCirclePost/" + cpost.getBanner());
          // post.put("title", cpost.getTitle() == null ? "" : cpost.getTitle());
          // post.put("author", cpost.getAuthor() == null ? "" : cpost.getAuthor());
          // post.put("collectNum", cpost.getCollectNum() == null ? 0 : cpost.getCollectNum());
          // post.put("userId", cpost.getUserId() == null ? 0 : cpost.getUserId());
          //
          //
          // result.add(post);
          post.put("cid", cpost.getId());
          post.put("postId", cpost.getTypeId());
          post.put("banner", ConstParam.URL + "/upfiles/userCirclePost/" + cpost.getBanner());
          post.put("postTitle", cpost.getTitle() == null ? "" : cpost.getTitle());
          post.put("authorName", cpost.getUserName() == null ? "" : cpost.getUserName());
          post.put("collectNum", cpost.getCollectNum() == null ? 0 : cpost.getCollectNum());

          post.put("authorLogo", cpost.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + cpost.getUserImage());
          post.put("authorSex", cpost.getUserSex() == null ? "" : cpost.getUserSex());
          post.put("authorFansNum", getUserFollowInfoCount(null, cpost.getUserId(), 1));
          post.put("authorCollectNum", getUserFollowInfoCount(cpost.getUserId(), null, 1));
          if (getUserFollowInfoCount(uid, cpost.getUserId(), 1) == 0) {
            post.put("authorFollow", 0);
          } else {
            post.put("authorFollow", 1);
          }
          if (getUserCollectInfoCount(cpost.getTypeId(), cpost.getAuthorId(), 2, uid) == 0) {
            post.put("postCollect", 0);
          } else {
            post.put("postCollect", 1);
          }
          post.put("authorId", cpost.getAuthorId());
          result.add(post);
        }
        b = true;
      } else {
        msg = "您没有收藏的笔记!";
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
   * 删除回复笔记
   * 
   * @return
   */
  public String delCommentedPostApi() {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (postCommentId == null || postCommentId == 0) {
      msg = "回复笔记id不能为空!";
    } else {
      // params.put("userId", uid);
      // params.put("id", postCommentId);
      UserPostCommentPojo usePostComment = userPostCommentService.getById(postCommentId);
      if (usePostComment != null) {
        int i = userPostCommentService.delete(postCommentId);
        if (i > 0) {
          UserCirclePostPojo userCirclePost = new UserCirclePostPojo();
          userCirclePost.setCommentNum(-1l);
          userCirclePost.setId(usePostComment.getTypeId());
          userCirclePostService.decreaseUserCirclePostNumById(userCirclePost);
          b = true;
          msg = "删除成功!";
        } else {
          msg = "删除失败!";
        }
      } else {
        msg = "查询不到该用户笔记!";
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
   * 删除收藏笔记
   * 
   * @return
   */
  public String delCollectedPostApi() {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (collectedPostId == null || collectedPostId == 0) {
      msg = "收藏笔记id不能为空!";
    } else {
      // params.put("userId", uid);
      // params.put("id", collectedPostId);
      UserPostCollectPojo userPostCollect =
          userPostCollectService.getUserPostCollectById(collectedPostId);
      if (userPostCollect != null) {
        int i = userPostCollectService.deleteUserPostCollectById(collectedPostId);
        if (i > 0) {
          UserCirclePostPojo userCirclePost = new UserCirclePostPojo();
          userCirclePost.setCollectNum(-1l);
          userCirclePost.setId(userPostCollect.getTypeId());
          userCirclePostService.decreaseUserCirclePostNumById(userCirclePost);
          b = true;
          msg = "取消成功!";
        } else {
          msg = "取消失败!";
        }
      } else {
        msg = "查询不到该用户收藏笔记!";
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
   * 达人认证申请
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   */
  public String masterAuthApply() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> authInfo = new HashMap<String, Object>();

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      // 申请状态：0-未申请1审核中2审核通过3审核失败
      int status = 0;
      SysLoginPojo user = sysLoginService.findSysLoginById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        UserTalentAuthPojo master = userTalentAuthService.findUserTalentAuthByUid(uid);
        if (master != null) {
          status = master.getStatus() == null ? 0 : master.getStatus();
          if (status == 1) {
            // 审核通过
            status = 2;
          } else if (status == 2) {
            // 审核失败
            status = 3;
          } else {
            // 已申请待审核
            status = 1;
          }
          if (status == 3) {
            authInfo.put("platform", StringUtil.checkString(master.getPlatform()));
            authInfo.put("platUserName", StringUtil.checkString(master.getPlatformUserName()));
            authInfo.put("identity", StringUtil.checkString(master.getIdentity()));
            authInfo.put("fansNum", StringUtil.checkInt(master.getFansNum()));
            authInfo.put("crossPlatform", StringUtil.checkString(master.getCrossPlatform()));
            authInfo.put("contentOutPer", StringUtil.checkInt(master.getContentOutPer()));
            authInfo.put("orContentOutPer", StringUtil.checkInt(master.getOrigContentOutPer()));
            authInfo.put("coopSimilarPlat", StringUtil.checkString(master.getCoopSimilarPlat()));
            authInfo.put("coopBrand", StringUtil.checkString(master.getCoopBrand()));
            authInfo.put("commercialType", StringUtil.checkString(master.getCommercialType()));
            authInfo.put("commercialPrice", StringUtil.checkString(master.getCommercialPrice()));
            authInfo.put("sampleTitle1", StringUtil.checkString(master.getSampleTitle1()));
            authInfo.put("sampleUrl1", StringUtil.checkString(master.getSampleUrl1()));
            // authInfo.put("sampleTitle2", StringUtil.checkString(master.getSampleTitle2()));
            // authInfo.put("sampleUrl2", StringUtil.checkString(master.getSampleUrl2()));
            // authInfo.put("sampleTitle3", StringUtil.checkString(master.getSampleTitle3()));
            // authInfo.put("sampleUrl3", StringUtil.checkString(master.getSampleUrl3()));
            authInfo.put("realName", StringUtil.checkString(master.getRealName()));
            authInfo.put(
                "idCardImg",
                ConstParam.URL + "/upfiles/userTalentAuth/"
                    + StringUtil.checkString(master.getIdCardImage()));
            authInfo.put("fansTitle", StringUtil.fansDic(master.getFansNum()));
            authInfo.put("contentTitle", StringUtil.contentDic(master.getContentOutPer()));
            authInfo.put("orContentTitle", StringUtil.contentDic(master.getOrigContentOutPer()));
          }
        } else {
          // 未申请
          status = 0;
        }
        result.put("status", status);
        result.put("authInfo", authInfo);
        b = true;
      } else {
        msg = "找不到该用户!";
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
   * 达人认证申请提交
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   */
  public String masterAuthApplySubmit() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();

    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (StringUtils.isEmpty(platform)) {
      msg = "平台选择不能为空!";
    } else if (StringUtils.isEmpty(platUserName)) {
      msg = "平台用户名不能为空!";
    } else if (StringUtils.isEmpty(identity)) {
      msg = "身份信息不能为空!";
    } else if (fansNum == null || fansNum == 0) {
      msg = "粉丝数不能为空!";
    } else if (StringUtils.isEmpty(coopBrand)) {
      msg = "合作商品品牌名称!";
    } else if (contentOutPer == null || contentOutPer == 0) {
      msg = "日内容产量不能为空!";
    } else if (orContentOutPer == null || orContentOutPer == 0) {
      msg = "日原创内容产量不能为空!";
    } else if (StringUtils.isEmpty(commercialType)) {
      msg = "合作模式不能为空!";
    } else if (StringUtils.isEmpty(sampleTitle1)) {
      msg = "样稿标题1不能为空!";
    } else if (StringUtils.isEmpty(sampleUrl1)) {
      msg = "样稿链接地址1不能为空!";
    }
    // else if (StringUtils.isEmpty(sampleTitle2)) {
    // msg = "样稿标题2不能为空!";
    // } else if (StringUtils.isEmpty(sampleUrl2)) {
    // msg = "样稿链接地址2不能为空!";
    // } else if (StringUtils.isEmpty(sampleTitle3)) {
    // msg = "样稿标题3不能为空!";
    // } else if (StringUtils.isEmpty(sampleUrl3)) {
    // msg = "样稿链接地址3不能为空!";
    // }
    else if (StringUtils.isEmpty(realName)) {
      msg = "姓名不能为空!";
    } else if (idCardImg == null) {
      msg = "身份证不能为空!";
    } else {

      SysLoginPojo user = sysLoginService.findSysLoginById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        UserTalentAuthPojo master = userTalentAuthService.findUserTalentAuthByUid(uid);
        // 状态：0-未审核1审核通过2审核失败
        if (master != null && master.getStatus() != null && master.getStatus() == 2) {
          // 审核失败修改
          Long authId = master.getId();
          master = new UserTalentAuthPojo();
          master.setId(authId);
          master.setPlatform(platform);
          master.setPlatformUserName(platUserName);
          master.setIdentity(identity);
          master.setFansNum(fansNum);
          master.setCrossPlatform(crossPlatform);
          master.setContentOutPer(contentOutPer);
          master.setOrigContentOutPer(orContentOutPer);
          master.setCoopSimilarPlat(coopSimilarPlat);
          master.setCoopBrand(coopBrand);
          master.setCommercialType(commercialType);
          master.setCommercialPrice(commercialPrice);
          master.setSampleTitle1(sampleTitle1);
          master.setSampleUrl1(sampleUrl1);
          // master.setSampleTitle2(sampleTitle2);
          // master.setSampleUrl2(sampleUrl2);
          // master.setSampleTitle3(sampleTitle3);
          // master.setSampleUrl3(sampleUrl3);
          master.setRealName(realName);

          if (idCardImg != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userTalentAuth")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userTalentAuth/", idCardImg);
            master.setIdCardImage(file_name);
          }
          master.setStatus(0);
          master.setUpdateBy(uid);
          userTalentAuthService.updateUserTalentAuth(master);

          // result.put("fansTitle", StringUtil.fansDic(master.getFansNum()));
          // result.put("contentTitle", StringUtil.contentDic(master.getContentOutPer()));
          // result.put("orContentTitle", StringUtil.contentDic(master.getOrigContentOutPer()));
          b = true;
          msg = "已提交申请等待审核!";
        } else if (master == null) {
          // 未申请
          master = new UserTalentAuthPojo();
          master.setUserId(uid);
          master.setPlatform(platform);
          master.setPlatformUserName(platUserName);
          master.setIdentity(identity);
          master.setFansNum(fansNum);
          master.setCrossPlatform(crossPlatform);
          master.setContentOutPer(contentOutPer);
          master.setOrigContentOutPer(orContentOutPer);
          master.setCoopSimilarPlat(coopSimilarPlat);
          master.setCoopBrand(coopBrand);
          master.setCommercialType(commercialType);
          master.setCommercialPrice(commercialPrice);
          master.setSampleTitle1(sampleTitle1);
          master.setSampleUrl1(sampleUrl1);
          // master.setSampleTitle2(sampleTitle2);
          // master.setSampleUrl2(sampleUrl2);
          // master.setSampleTitle3(sampleTitle3);
          // master.setSampleUrl3(sampleUrl3);
          master.setRealName(realName);

          if (idCardImg != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userTalentAuth")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userTalentAuth/", idCardImg);
            master.setIdCardImage(file_name);
          }
          master.setStatus(0);
          master.setCreateBy(uid);
          master.setUpdateBy(uid);
          userTalentAuthService.insertUserTalentAuth(master);

          // result.put("fansTitle", StringUtil.fansDic(master.getFansNum()));
          // result.put("contentTitle", StringUtil.contentDic(master.getContentOutPer()));
          // result.put("orContentTitle", StringUtil.contentDic(master.getOrigContentOutPer()));
          b = true;
          msg = "已提交申请等待审核!";
        } else {
          msg = "达人申请提交失败!";
        }
      } else {
        msg = "找不到该用户!";
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

  // ------ Web页面 ------ //
  // /**
  // *
  // * 成长线
  // *
  // * @return
  // * @throw
  // * @return String
  // * @throws Exception
  // */
  // public String getGrowthLineWebApi() throws Exception {
  // // String msg = "";
  // // boolean b = false;
  // // Map<String, Object> map = new HashMap<String, Object>();
  // // Map<String, Object> result = new HashMap<String, Object>();
  //
  // if (uid == null || uid <= 0) {
  // FileUtil.alertMessage("用户ID不能为空哦！~");
  // // msg = "用户ID不能为空哦！~";
  // } else {
  // String name = "", honor = "", skillTypeName = "", skillTypeName1 = "", skillTypeName2 = "",
  // img1 =
  // "", titile1 = "", id1 = "", ageTypeId = "", skillTypeId = "", img2 = "", titile2 = "", id2 =
  // "", img3 = "", id3 = "", authorId = "";
  //
  // Map<String, Object> params = new HashMap<String, Object>();
  // params.put("userId", uid);
  // List<UserGrowthLinePojo> list = userGrowthLineService.userGrowthLineList(params);
  // if (list.size() > 0) {
  // UserGrowthLinePojo userGrowthLinePojo = list.get(0);
  // // 视觉能力，语言能力，情感社交，动作发育，听发能力，触觉能力
  // // int this_mounth[] =
  // // {userGrowthLinePojo.getVisionSkill(), userGrowthLinePojo.getLanguageSkill(),
  // // userGrowthLinePojo.getEmotionSkill(), userGrowthLinePojo.getActionSkill(),
  // // userGrowthLinePojo.getListenSkill(), userGrowthLinePojo.getTouchSkill()};
  // // int last_mounth[] =
  // // {userGrowthLinePojo.getVisionSkillLast(), userGrowthLinePojo.getLanguageSkillLast(),
  // // userGrowthLinePojo.getEmotionSkillLast(), userGrowthLinePojo.getActionSkillLast(),
  // // userGrowthLinePojo.getListenSkillLast(), userGrowthLinePojo.getTouchSkillLast()};
  // thisMounth =
  // userGrowthLinePojo.getVisionSkill() + "," + userGrowthLinePojo.getLanguageSkill() + ","
  // + userGrowthLinePojo.getEmotionSkill() + "," + userGrowthLinePojo.getActionSkill()
  // + "," + userGrowthLinePojo.getListenSkill() + ","
  // + userGrowthLinePojo.getTouchSkill();
  // lastMounth =
  // userGrowthLinePojo.getVisionSkillLast() + ","
  // + userGrowthLinePojo.getLanguageSkillLast() + ","
  // + userGrowthLinePojo.getEmotionSkillLast() + ","
  // + userGrowthLinePojo.getActionSkillLast() + ","
  // + userGrowthLinePojo.getListenSkillLast() + ","
  // + userGrowthLinePojo.getTouchSkillLast();
  //
  // // sort
  // Map<Integer, Object> skillTypeMap = new HashMap<Integer, Object>();
  // skillTypeMap.put(userGrowthLinePojo.getVisionSkill(), "视觉能力");
  // skillTypeMap.put(userGrowthLinePojo.getLanguageSkill(), "语言能力");
  // skillTypeMap.put(userGrowthLinePojo.getEmotionSkill(), "情感社交");
  // skillTypeMap.put(userGrowthLinePojo.getActionSkill(), "动作发育");
  // skillTypeMap.put(userGrowthLinePojo.getListenSkill(), "听觉能力");
  // skillTypeMap.put(userGrowthLinePojo.getTouchSkill(), "触觉能力");
  // List<Integer> skillTypes = new ArrayList<Integer>();
  // skillTypes.add(userGrowthLinePojo.getVisionSkill());
  // skillTypes.add(userGrowthLinePojo.getLanguageSkill());
  // skillTypes.add(userGrowthLinePojo.getEmotionSkill());
  // skillTypes.add(userGrowthLinePojo.getActionSkill());
  // skillTypes.add(userGrowthLinePojo.getListenSkill());
  // skillTypes.add(userGrowthLinePojo.getTouchSkill());
  // Collections.sort(skillTypes);
  // if (skillTypes.get(skillTypes.size() - 1) > 60) {
  // skillTypeName = skillTypeMap.get(skillTypes.get(skillTypes.size() - 1)).toString();
  // }
  // skillTypeName1 = skillTypeMap.get(skillTypes.get(0)).toString();
  // skillTypeName2 = skillTypeMap.get(skillTypes.get(1)).toString();
  // // Map<Integer, String> skilltreeMap = new TreeMap<Integer, String>();
  // // skilltreeMap.put(userGrowthLinePojo.getVisionSkill(), "视觉能力");
  // // skilltreeMap.put(userGrowthLinePojo.getLanguageSkill(), "语言能力");
  // // skilltreeMap.put(userGrowthLinePojo.getEmotionSkill(), "情感社交");
  // // skilltreeMap.put(userGrowthLinePojo.getActionSkill(), "动作发育");
  // // skilltreeMap.put(userGrowthLinePojo.getListenSkill(), "听发能力");
  // // skilltreeMap.put(userGrowthLinePojo.getTouchSkill(), "触觉能力");
  // // skillTypeName = skilltreeMap.get(skilltreeMap.size() - 1).toString();
  // // skillTypeName1 = skilltreeMap.get(0).toString();
  // // skillTypeName2 = skilltreeMap.get(1).toString();
  //
  // // skillType
  // int ageType = 1, skillType = -1;
  // if (userGrowthLinePojo.getScore() > 100) {
  // skillType = -4;
  // } else if (userGrowthLinePojo.getScore() > 60) {
  // skillType = -3;
  // } else if (userGrowthLinePojo.getScore() > 30) {
  // skillType = -2;
  // } else {
  // skillType = -1;
  // }
  //
  // // ageType
  // UserInfoPojo userInfoPojo = userInfoService.findUserInfoByUserId(uid);
  // if (userInfoPojo != null) {
  // // 查询年龄
  // double age =
  // SellerService.getAge(StringUtil.stringToDate(userInfoPojo.getBabyBirthday()));
  // // 查询年龄对应专题
  // if (age >= 0.0 && age <= 0.06) {
  // ageType = 1;
  // } else if (age > 0.06 && age <= 1.0) {
  // ageType = 2;
  // } else if (age > 1.0 && age <= 3.0) {
  // ageType = 3;
  // } else if (age > 3.0 && age <= 6.0) {
  // ageType = 4;
  // } else if (age > 6.0 && age <= 12.0) {
  // ageType = 5;
  // } else if (age > 12.0 && age <= 16.0) {
  // ageType = 6;
  // } else if (age > 16.0) {
  // ageType = 6;
  // } else {
  // // 出生日期大于当前时间
  // }
  // } else {
  // // 该用户不存在
  // }
  //
  // // 称号
  // params.clear();
  // params.put("ageType", ageType);
  // params.put("skillType", skillType);
  // HonorRulePojo honorRulePojo = honorRuleService.selectHonorBy(params);
  // if (honorRulePojo != null) {
  // name = honorRulePojo.getHonor();
  // }
  //
  // // honor
  // if (!"".equals(skillTypeName)) {
  // // params.clear();
  // // params.put("type", "ability");
  // // params.put("name", skillType1);
  // SysDictPojo ticketRulePojo = new SysDictPojo();
  // ticketRulePojo.setType("ability");
  // ticketRulePojo.setName(skillTypeName);
  // List<SysDictPojo> sysDictPojos = sysDictService.sysDictAllList(ticketRulePojo, null);
  // if (sysDictPojos.size() > 0) {
  // ticketRulePojo = sysDictPojos.get(0);
  // params.clear();
  // params.put("ageType", ageType);
  // params.put("skillType", ticketRulePojo.getValue());
  // honorRulePojo = honorRuleService.selectHonorBy(params);
  // if (honorRulePojo != null) {
  // honor = honorRulePojo.getHonor();
  // }
  // }
  // }
  //
  // growthHonor = new String[15];
  // growthHonor[0] = name;
  // growthHonor[1] = skillTypeName;
  // growthHonor[2] = honor;
  // growthHonor[3] = skillTypeName1;
  // growthHonor[4] = skillTypeName2;
  // // growthHonor =
  // // name + "," + skillTypeName + "," + honor + "," + skillTypeName1 + "," + skillTypeName2;
  //
  // SysDictPojo ticketRulePojo = new SysDictPojo();
  // ticketRulePojo.setType("ability");
  // ticketRulePojo.setName(skillTypeName1);
  // List<SysDictPojo> sysDictPojos = sysDictService.sysDictAllList(ticketRulePojo, null);
  // if (sysDictPojos.size() > 0) {
  // // 相关知识
  // ticketRulePojo = sysDictPojos.get(0);
  // params.clear();
  // params.put("status", 1);
  // params.put("orderBy", "update_date desc,create_date desc");
  // params.put("ageType", ageType);
  // params.put("skillType", ticketRulePojo.getValue());
  // KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);
  // if (knowledgeBasePojo != null) {
  // img1 = knowledgeBasePojo.getSmallIcon() == null ? "" : knowledgeBasePojo.getSmallIcon();
  // titile1 = knowledgeBasePojo.getTitle() == null ? "" : knowledgeBasePojo.getTitle();
  // id1 = knowledgeBasePojo.getId().toString();
  // ageTypeId = knowledgeBasePojo.getAgeType().toString();
  // skillTypeId = knowledgeBasePojo.getSkillType().toString();
  // }
  //
  // // 相关宝典
  // params.put("isDelete", 0);
  // UserCirclePostPojo userCirclePostPojo = userCirclePostService.findBycircleParams(params);
  // if (userCirclePostPojo != null) {
  // img2 = userCirclePostPojo.getBanner() == null ? "" : userCirclePostPojo.getBanner();
  // titile2 = userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle();
  // id2 = userCirclePostPojo.getId().toString();
  // authorId = userCirclePostPojo.getUserId().toString();
  // }
  //
  // // 相关好玩
  // PlatformSpecialPojo platformSpecialPojo =
  // platformSpecialService.findSpecialByParams(params);
  // if (platformSpecialPojo != null) {
  // img3 = platformSpecialPojo.getBanner() == null ? "" : platformSpecialPojo.getBanner();
  // id3 = platformSpecialPojo.getId().toString();
  // }
  // }
  // // 相关知识
  // growthHonor[5] = img1;
  // growthHonor[6] = titile1;
  // growthHonor[7] = id1;
  // growthHonor[13] = ageTypeId;
  // growthHonor[14] = skillTypeId;
  //
  // // 相关宝典
  // growthHonor[8] = img2;
  // growthHonor[9] = titile2;
  // growthHonor[10] = id2;
  // growthHonor[15] = authorId;
  //
  // // 相关好玩
  // growthHonor[11] = img3;
  // growthHonor[12] = id3;
  // }
  // return SUCCESS;
  // }
  //
  // // map.put("result", result);
  // // map.put("error_msg", msg);
  // // map.put("success", b);
  // // JSONObject json = JSONObject.fromObject(map);
  // // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
  // // try {
  // // ServletActionContext.getResponse().getWriter().write(json.toString());
  // // } catch (IOException e) {
  // // e.printStackTrace();
  // // }
  // return null;
  // }

  /**
   * 
   * 成长线
   * 
   * @return String
   * @throws Exception
   */
  public String getGrowthLine() throws Exception {
    if (uid == null || uid <= 0) {
      FileUtil.alertMessage("用户ID不能为空哦！");
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DAY_OF_MONTH, 1);
      param.put("userId", uid);
      param.put("status", 1);
      param.put("histFlag", 2);
      param.put("taskDate", StringUtil.dateToString(cal.getTime()));
      int userTaskCount = userTaskService.countBy(param);
      if (userTaskCount <= 0) {
        FileUtil.skipAction("getGrowthLineWebApi.do?{\"error_msg\":\"请先完成今日任务!\"}");
        return null;
      }
      // ageType
      UserInfoPojo user = userInfoService.findUserInfoByUserId(uid);
      if (user == null) {
        FileUtil.alertMessage("用户信息未找到！");
      } else {
        // 查找宝宝信息
        param.clear();
        param.put("userId", uid);
        param.put("isDefault", 1);
        param.put("isDelete", 0);
        userBabyPojo = userBabyService.getByParams(param);
        if (userBabyPojo == null || userBabyPojo.getBabyBirthday() == null) {
          FileUtil.alertMessage("宝宝信息未找到！");
        } else {
          // 查询年龄
          double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
          int ageType = 1;
          // 查询年龄对应专题
          if (age >= 0.0 && age <= 0.06) {
            ageType = 1;
          } else if (age > 0.06 && age <= 1.0) {
            ageType = 2;
          } else if (age > 1.0 && age <= 3.0) {
            ageType = 3;
          } else if (age > 3.0 && age <= 6.0) {
            ageType = 4;
          } else if (age > 6.0 && age <= 12.0) {
            ageType = 5;
          } else if (age > 12.0 && age <= 16.0) {
            ageType = 6;
          } else if (age > 16.0) {
            ageType = 6;
          }
          // 查找成长线信息
          param.clear();
          param.put("userId", uid);
          param.put("babyId", userBabyPojo.getId());
          UserGrowthLinePojo userGrowp = userGrowthLineService.getUserGrowthLineByUid(param);
          if (userGrowp == null) {
            FileUtil.alertMessage("用户成长线信息未找到！");
          } else {
            // 雷达图
            HonorRulePojo honorRule = null;
            Integer[] skills = new Integer[6];
            String[] skillnames = new String[6];
            if (ageType == 1) {
              skills[0] = userGrowp.getVisionSkill();
              skills[1] = userGrowp.getListenSkill();
              skills[2] = userGrowp.getTouchSkill();
              skills[3] = userGrowp.getActionSkill();
              skills[4] = userGrowp.getEmotionSkill();
              skills[5] = userGrowp.getLanguageGrow();

              skillnames[0] = "视觉能力";
              skillnames[1] = "听觉能力";
              skillnames[2] = "触觉能力";
              skillnames[3] = "动作发育";
              skillnames[4] = "情感社交";
              skillnames[5] = "语言发育";

              // thisMounth =
              // String.valueOf(userGrowp.getVisionSkill()).concat(",")
              // .concat(String.valueOf(userGrowp.getListenSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getTouchSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getActionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getLanguageGrow()));
              lastMounth =
                  String.valueOf(userGrowp.getVisionSkillLast()).concat(",")
                      .concat(String.valueOf(userGrowp.getListenSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getTouchSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getActionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getLanguageGrowLast()));

              // skillNames = "视觉能力,听发能力,触觉能力,动作发育,情感社交,语言发育";
            } else if (ageType == 2) {
              skills[0] = userGrowp.getCoordinationSkill();
              skills[1] = userGrowp.getLanguageGrow();
              skills[2] = userGrowp.getBrainGrow();
              skills[3] = userGrowp.getActionSkill();
              skills[4] = userGrowp.getEmotionSkill();
              skills[5] = userGrowp.getExploringSkill();

              skillnames[0] = "手眼协调";
              skillnames[1] = "语言发育";
              skillnames[2] = "大脑发育";
              skillnames[3] = "动作发育";
              skillnames[4] = "情感社交";
              skillnames[5] = "探索认知";

              // thisMounth =
              // String.valueOf(userGrowp.getCoordinationSkill()).concat(",")
              // .concat(String.valueOf(userGrowp.getLanguageGrow())).concat(",")
              // .concat(String.valueOf(userGrowp.getBrainGrow())).concat(",")
              // .concat(String.valueOf(userGrowp.getActionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getExploringSkill()));
              lastMounth =
                  String.valueOf(userGrowp.getCoordinationSkillLast()).concat(",")
                      .concat(String.valueOf(userGrowp.getLanguageGrowLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getBrainGrowLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getActionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getExploringSkillLast()));
              // skillNames = "手眼协调,语言发育,大脑发育,动作发育,情感社交,探索认知";
            } else if (ageType == 3) {
              skills[0] = userGrowp.getMoveSkill();
              skills[1] = userGrowp.getLanguageSkill();
              skills[2] = userGrowp.getSpecialSkill();
              skills[3] = userGrowp.getEmotionSkill();
              skills[4] = userGrowp.getCognitiveSkill();
              skills[5] = userGrowp.getSportSkill();

              skillnames[0] = "动手能力";
              skillnames[1] = "语言能力";
              skillnames[2] = "专项能力";
              skillnames[3] = "情感社交";
              skillnames[4] = "认知能力";
              skillnames[5] = "运动能力";

              // thisMounth =
              // String.valueOf(userGrowp.getMoveSkill()).concat(",")
              // .concat(String.valueOf(userGrowp.getLanguageSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getSpecialSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getCognitiveSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getSportSkill()));
              lastMounth =
                  String.valueOf(userGrowp.getMoveSkillLast()).concat(",")
                      .concat(String.valueOf(userGrowp.getLanguageSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getSpecialSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getCognitiveSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getSportSkillLast()));
              // skillNames = "动手能力,语言能力,专项能力,情感社交,认知能力,运动能力";
            } else if (ageType == 4) {
              skills[0] = userGrowp.getThinkSkill();
              skills[1] = userGrowp.getMoveSkill();
              skills[2] = userGrowp.getSpecialSkill();
              skills[3] = userGrowp.getEmotionSkill();
              skills[4] = userGrowp.getCognitiveSkill();
              skills[5] = userGrowp.getSportSkill();

              skillnames[0] = "思维能力";
              skillnames[1] = "动手能力";
              skillnames[2] = "专项能力";
              skillnames[3] = "情感社交";
              skillnames[4] = "认知能力";
              skillnames[5] = "运动能力";

              // thisMounth =
              // String.valueOf(userGrowp.getThinkSkill()).concat(",")
              // .concat(String.valueOf(userGrowp.getMoveSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getSpecialSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getCognitiveSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getSportSkill()));
              lastMounth =
                  String.valueOf(userGrowp.getThinkSkillLast()).concat(",")
                      .concat(String.valueOf(userGrowp.getMoveSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getSpecialSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getCognitiveSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getSportSkillLast()));
              // skillNames = "思维能力,动手能力,专项能力,情感社交,认知能力,运动能力";
            } else if (ageType == 5 || ageType == 6) {
              skills[0] = userGrowp.getThinkSkill();
              skills[1] = userGrowp.getMoveSkill();
              skills[2] = userGrowp.getSpecialSkill();
              skills[3] = userGrowp.getEmotionSkill();
              skills[4] = userGrowp.getLearnSkill();
              skills[5] = userGrowp.getSportSkill();

              skillnames[0] = "思维能力";
              skillnames[1] = "动手能力";
              skillnames[2] = "专项能力";
              skillnames[3] = "情感社交";
              skillnames[4] = "学习认知";
              skillnames[5] = "运动能力";

              // thisMounth =
              // String.valueOf(userGrowp.getThinkSkill()).concat(",")
              // .concat(String.valueOf(userGrowp.getMoveSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getSpecialSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getEmotionSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getLearnSkill())).concat(",")
              // .concat(String.valueOf(userGrowp.getSportSkill()));
              lastMounth =
                  String.valueOf(userGrowp.getThinkSkillLast()).concat(",")
                      .concat(String.valueOf(userGrowp.getMoveSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getSpecialSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getEmotionSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getLearnSkillLast())).concat(",")
                      .concat(String.valueOf(userGrowp.getSportSkillLast()));
              // skillNames = "思维能力,动手能力,专项能力,情感社交,学习认知,运动能力";
            }
            thisMounth = StringUtils.join(skills, ",");
            skillNames = StringUtils.join(skillnames, ",");

            // 称号
            growthHonor = new String[17];
            for (int i = 0; i < growthHonor.length; i++) {
              growthHonor[i] = "";
            }
            Map<String, Object> params = new HashMap<String, Object>();
            long score = userGrowp.getScore() == null ? 0l : userGrowp.getScore();
            int skillType = 0;
            if (score > 5 && score <= 30) {
              skillType = -1;
            } else if (score > 30 && score <= 60) {
              skillType = -2;
            } else if (score > 60 && score <= 100) {
              skillType = -3;
            } else if (score > 100) {
              skillType = -4;
            }
            if (skillType < 0) {
              params.clear();
              params.put("ageType", ageType);
              params.put("skillType", skillType);
              honorRule = honorRuleService.selectHonorBy(params);
              if (honorRule != null && StringUtils.isNotEmpty(honorRule.getHonor())) {
                growthHonor[0] = honorRule.getHonor();
              }
            }
            // 能力分数排序
            this.sortSkill(skills, skillnames);
            if (skills[0] > 0) {
              max = skills[0] * 2;
            } else {
              max = 10;
            }
            // 荣誉
            if (skills[0] != null && skills[0] > 60 && skillnames[0] != null) {
              SysDictPojo dict = new SysDictPojo();
              dict.setType("ability");
              dict.setName(skillnames[0]);
              List<SysDictPojo> dicts = sysDictService.sysDictAllList(dict, null);

              if (dicts != null && dicts.size() > 0) {
                skillType = Integer.valueOf(dicts.get(0).getValue());
              }
              if (skillType > 0) {
                params.clear();
                params.put("ageType", ageType);
                params.put("skillType", skillType);
                honorRule = honorRuleService.selectHonorBy(params);
                if (honorRule != null && StringUtils.isNotEmpty(honorRule.getHonor())) {
                  growthHonor[1] = honorRule.getHonor();
                }
              }
              growthHonor[2] = skillnames[0];
            }

            // 可增强能力
            if (skills[5] != null) {
              growthHonor[3] = skillnames[5];
            }
            if (skills[4] != null) {
              growthHonor[4] = skillnames[4];
            }

            if (skillnames[5] != null) {
              SysDictPojo dict = new SysDictPojo();
              dict.setType("ability");
              dict.setName(skillnames[5]);
              List<SysDictPojo> dicts = sysDictService.sysDictAllList(dict, null);

              if (dicts != null && dicts.size() > 0) {
                skillType = Integer.valueOf(dicts.get(0).getValue());
              }
            }
            if (skillType > 0) {
              growthHonor[5] = String.valueOf(ageType);
              growthHonor[6] = String.valueOf(skillType);

              // 相关知识
              params.clear();
              params.put("status", 1);
              params.put("orderBy", "update_date desc,create_date desc");
              params.put("ageType", ageType);
              params.put("skillType", skillType);
              KnowledgeBasePojo knowledge = knowledgeBaseService.findKnowledgeByParams(params);
              if (knowledge != null) {
                growthHonor[7] = String.valueOf(knowledge.getId());
                growthHonor[8] = StringUtil.checkString(knowledge.getSmallIcon());
                growthHonor[9] = StringUtil.checkString(knowledge.getTitle());
              }

              // 相关宝典
              params.put("isDelete", 0);
              UserCirclePostPojo post = userCirclePostService.findBycircleParams(params);
              if (post != null) {
                growthHonor[10] = String.valueOf(post.getId());
                growthHonor[11] = StringUtil.checkString(post.getBanner());
                growthHonor[12] = StringUtil.checkString(post.getTitle());
                growthHonor[16] = String.valueOf(post.getUserId());
              }

              // 相关好玩
              PlatformSpecialPojo platform = platformSpecialService.findSpecialByParams(params);
              if (platform != null) {
                growthHonor[13] = String.valueOf(platform.getId());
                growthHonor[14] = StringUtil.checkString(platform.getBanner());
                growthHonor[15] = StringUtil.checkString(platform.getTitle());
              }
            }
          }
        }
      }
      return SUCCESS;
    }

    // map.put("result", result);
    // map.put("error_msg", msg);
    // map.put("success", b);
    // JSONObject json = JSONObject.fromObject(map);
    // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    // try {
    // ServletActionContext.getResponse().getWriter().write(json.toString());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    return null;
  }

  /**
   * 能力排序.
   * 
   * @param skill
   * @param skillName
   * @throw
   * @return void
   */
  public void sortSkill(Integer[] skill, String[] skillName) {
    Integer temp = null;
    String tempStr = null;
    for (int i = 0; i < skill.length; i++) {
      for (int j = i + 1; j < skill.length; j++) {
        if (skill[i] < skill[j]) {
          temp = skill[i];
          skill[i] = skill[j];
          skill[j] = temp;
          tempStr = skillName[i];
          skillName[i] = skillName[j];
          skillName[j] = tempStr;
        }
      }
    }
  }

  /*
   * public static void main(String[] args) { Integer[] skills = new Integer[6]; skills[0] = 3;
   * skills[1] = 4; skills[2] = 2; skills[3] = 5; skills[4] = 6; skills[5] = 1; String[] skillname =
   * new String[6]; skillname[0] = "视觉能力"; skillname[1] = "听发能力"; skillname[2] = "触觉能力";
   * skillname[3] = "动作发育"; skillname[4] = "情感社交"; skillname[5] = "语言发育";
   * 
   * AppApiMakerAction ac = new AppApiMakerAction(); ac.sortSkill(skills, skillname);
   * System.out.println(StringUtils.join(skills, ","));
   * System.out.println(StringUtils.join(skillname, ",")); }
   */

  /**
   * 注册验证
   * 
   * @return
   * @throws SQLException
   */
  public String registerCheckApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    SysLoginPojo sysLoginPojobyloginname = null;
    if (phone == null || phone.equals("")) {
      error_msg = "手机号不能为空!";
    } else if (captcha == null || captcha.equals("")) {
      error_msg = "验证码不能为空!";
    } else if (pass == null || pass.equals("")) {
      error_msg = "密码不能为空!";
    } else {
      if (pass.length() < 6 || pass.length() > 12) {
        error_msg = "请输入6到12位密码!";
      } else {
        sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(phone);
        if (sysLoginPojobyloginname != null) {
          error_msg = "手机号已注册过！";
        } /*
           * else { error_msg = "验证通过！"; success = true; }
           */
        else {
          UserVerifyPojo verifyPojo = null;
          verifyPojo = new UserVerifyPojo();
          verifyPojo.setLoginname(phone);
          // 得到系统生成的验证码
          verifyPojo = userVerifyService.findNewestByPhone(verifyPojo);
          if (verifyPojo == null) {
            error_msg = "验证码已经失效，请重新发送验证！";
          } else if (!captcha.equals(verifyPojo.getCaptcha())) {
            error_msg = "请填写正确验证码！";
          } else {
            success = true;
            error_msg = "验证通过！";
          }
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
   * 
   * 用户注册api
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String register() throws Exception {
    boolean flag = false;
    Map<String, Object> params = new HashMap<String, Object>();
    if (baidu_uid != null && StringUtils.isNotBlank(baidu_uid)) {
      // 相同机器码最多注册5个用户
      int count = baiduLoginService.checkMachineCodeRepeatByCode(baidu_uid);
      if (count >= 5) {
        flag = true;
        System.out.println(">>>>> baiduid2:" + baidu_uid);
      }
      if (!flag) {
        List<String> blackWords = WalletService.getBlackWords();
        int len = blackWords.size();

        for (int i = 0; i < len; i++) {
          if (baidu_uid.startsWith(blackWords.get(i))) {
            flag = true;
            break;
          }
        }
      }

    }
    /*
     * else { flag = true; }
     */
    if (flag) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("result", "");
      map.put("error_msg", "系统繁忙，请稍后再试！");
      map.put("success", false);
      JSONObject json = JSONObject.fromObject(map);
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      try {
        ServletActionContext.getResponse().getWriter().write(json.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
    Boolean success = false;
    Boolean regFlag = false;
    String error_msg = "";
    // 用户注册呢称为tzm+手机号
    // name = "tzm" + phone;
    // 根据手机号码判断是否注册过
    SysLoginPojo sysLoginPojobyloginname = null;
    if (phone == null || phone.equals("")) {
      regFlag = true;
      error_msg = "请输入手机号码！";
    } else if (captcha == null || captcha.equals("")) {
      regFlag = true;
      error_msg = "请输入验证码！";
    } else if (captcha.toString().length() != 6) {
      regFlag = true;
      error_msg = "请填写正确6位验证码！";
    } else if (pass == null || pass.equals("")) {
      regFlag = true;
      error_msg = "请输入密码！";
    } else if (pass.length() < 6) {
      regFlag = true;
      error_msg = "密码强度弱（至少6位），请换一个！";
    } else if (category == null || "".equals(category) || !"1".equals(category)
        && !"2".equals(category) && !"6".equals(category)) {
      regFlag = true;
      error_msg = "用户注册类型错误！";
    } else if (babySex == null || babySex == 0) {
      regFlag = true;
      error_msg = "请输入宝宝性别！";
    } else if (babyBirth == null || babyBirth.equals("")) {
      regFlag = true;
      error_msg = "请输入宝宝生日！";
    } else if (name == null || name.equals("")) {
      regFlag = true;
      error_msg = "请输入昵称！";
    } else {
      sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(phone);
      if (sysLoginPojobyloginname != null) {
        regFlag = true;
        error_msg = "手机号已注册过！";
      }
    }

    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    UserVerifyPojo verifyPojo = null;
    if (!regFlag) {
      verifyPojo = new UserVerifyPojo();
      verifyPojo.setLoginname(phone);
      // 得到系统生成的验证码
      verifyPojo = userVerifyService.findNewestByPhone(verifyPojo);
    }
    if (regFlag) {
      // error_msg = "手机号码已经存在！";
    } else if (verifyPojo == null) {
      error_msg = "验证码已经失效，请重新发送验证！";
    } else if (!captcha.equals(verifyPojo.getCaptcha())) {
      error_msg = "请填写正确验证码！";
    } else {
      SysLoginPojo sysPojo = new SysLoginPojo();
      sysPojo.setType(category);
      sysPojo.setStatus(1);
      sysPojo.setSorting(0);
      // sysPojo.setPassword(MD5Util.MD5(pass));
      sysPojo.setPassword(pass);
      sysPojo.setLoginname(phone);
      sysPojo.setName(name);
      if (baidu_uid != null && !"".equals(baidu_uid)) {
        sysPojo.setBaidu_uid(baidu_uid);
      }
      if (openid != null && !"".equals(openid)) {
        // sysPojo.setOpenid(openid);
        sysPojo.setUnionid(openid);
      }
      if (sinaid != null && !"".equals(sinaid)) {
        sysPojo.setSinaid(sinaid);
      }
      if (regChannel != null && !"".equals(regChannel)) {
        sysPojo.setRegChannel(regChannel);
      }
      if (imei != null && !"".equals(imei)) {
        sysPojo.setImei(imei);
      }
      sysPojo.setCreateDate(new Date());
      String externalSignCode = phone + new Date().getTime() / 1000 + StringUtil.genRandomStr(4);
      externalSignCode = MD5.MD5Encode(externalSignCode);
      sysPojo.setExternalSignCode(externalSignCode);
      sysPojo.setInvitationCode(walletService.genInviteCode());
      sysPojo.setCreateBy(0l);
      sysLoginService.addSysLoginService(sysPojo);

      // 得到添加成功的账户id
      if (sysPojo != null && sysPojo.getId() != null && sysPojo.getId() != 0) {
        // 根据用户注册时的手机查询用户
        // sysPojo = sysLoginService.findSysLoginByLoginname(sysPojo);

        // 向表baidu_login插入数据
        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
          BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
          baiduLoginPojo.setUserId(sysPojo.getId());
          baiduLoginPojo.setLoginName(phone);
          baiduLoginPojo.setBaiduId(baidu_uid);
          baiduLoginPojo.setType(1);
          walletService.isInsertBaibuLogin(baiduLoginPojo);
        }
        // 有米积分墙回调
        if (StringUtils.isNotEmpty(imei)) {
          // 1-ios 2-android
          Integer channel = 0;
          if (StringUtils.isEmpty(regChannel) || "App Store".equals(regChannel)) {
            channel = 1;
          } else if ("Umeng".equals(regChannel) || "YouMi".equals(regChannel)) {
            channel = 2;
          }
          if (channel > 0) {
            walletService.checkAdFromYoumi(imei, channel, sysPojo.getId());
          }
        }

        Date today = new Date();
        if (StringUtil.stringDate("2016-07-06 00:00:00").compareTo(today) <= 0) {
          // 首次注册送优惠券
          long uid = sysPojo.getId();
          Map<String, Object> param = new HashMap<String, Object>();
          // 满5减5
          param.put("om", "5");
          param.put("m", "5");
          Date current = new Date();
          JSONObject json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          // 满60减10
          param.put("om", "60");
          param.put("m", "10");
          json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          // 满120减20
          param.put("om", "120");
          param.put("m", "20");
          json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        }

        // 融云token
        // 用户id
        /*
         * String userid = String.valueOf(sysPojo.getId()); // 用户名 String username =
         * sysPojo.getName(); // 头像 String logo = ""; // token String token =
         * sysLoginService.getRongyunToken(userid, username, logo); SysLoginPojo updsyspojo = new
         * SysLoginPojo(); updsyspojo.setToken(token); updsyspojo.setRemarks("");
         * updsyspojo.setLoginname(sysPojo.getLoginname());
         * loginService.updateLoginPojo(updsyspojo);
         */
        HttpServletRequest request = ServletActionContext.getRequest();

        LoginRecPojo loginRecPojo = new LoginRecPojo();
        loginRecPojo.setType(sysPojo.getType());
        loginRecPojo.setLoginDate(new Date());
        loginRecPojo.setLoginIp(walletService.getIpAddr(request));
        loginRecPojo.setUserId(sysPojo.getId());
        loginRecService.addLoginRec(loginRecPojo);
        // 注册同时userinfo表需插入一条数据
        // sysPojo = loginService
        // .getLoginPojoByLoginnameAndPassword(sysPojo);
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserId(sysPojo.getId());
        userInfoPojo.setCreateDate(new Date());
        userInfoPojo.setStatus(1);
        userInfoPojo.setBabyBirthday(babyBirth);
        userInfoPojo.setBabySex(babySex);
        userInfoService.insertUserInfo(userInfoPojo);
        // 插入宝宝信息
        UserBabyPojo userBaby = new UserBabyPojo();
        userBaby.setBabyName("宝宝");
        userBaby.setBabyBirthday(StringUtil.stringToDate(babyBirth));
        userBaby.setBabySex(babySex);
        userBaby.setIsDefault(1);
        userBaby.setUserId(sysPojo.getId());
        // 添加成功会返回id
        userBabyService.add(userBaby);
        if (userBaby.getId() != null) {
          // 用户成长线表插入记录
          UserGrowthLinePojo userGrowthLine = new UserGrowthLinePojo();
          userGrowthLine.setUserId(sysPojo.getId());
          userGrowthLine.setBabyId(userBaby.getId());
          userGrowthLine.setListenSkill(0);
          userGrowthLine.setLanguageSkill(0);
          userGrowthLine.setVisionSkill(0);
          userGrowthLine.setMoveSkill(0);
          userGrowthLine.setTouchSkill(0);
          userGrowthLine.setActionSkill(0);
          userGrowthLine.setEmotionSkill(0);
          userGrowthLine.setLanguageGrow(0);
          userGrowthLine.setCoordinationSkill(0);
          userGrowthLine.setBrainGrow(0);
          userGrowthLine.setExploringSkill(0);
          userGrowthLine.setSpecialSkill(0);
          userGrowthLine.setSportSkill(0);
          userGrowthLine.setThinkSkill(0);
          userGrowthLine.setLearnSkill(0);

          userGrowthLine.setListenSkillLast(0);
          userGrowthLine.setLanguageSkillLast(0);
          userGrowthLine.setVisionSkillLast(0);
          userGrowthLine.setMoveSkillLast(0);
          userGrowthLine.setTouchSkillLast(0);
          userGrowthLine.setActionSkillLast(0);
          userGrowthLine.setEmotionSkillLast(0);
          userGrowthLine.setLanguageGrowLast(0);
          userGrowthLine.setCoordinationSkillLast(0);
          userGrowthLine.setBrainGrowLast(0);
          userGrowthLine.setExploringSkillLast(0);
          userGrowthLine.setSpecialSkillLast(0);
          userGrowthLine.setSportSkillLast(0);
          userGrowthLine.setThinkSkillLast(0);
          userGrowthLine.setLearnSkillLast(0);

          userGrowthLine.setScore(0l);
          userGrowthLine.setCognitiveSkill(0);
          userGrowthLine.setCognitiveSkillLast(0);
          userGrowthLineService.addUserGrowthLine(userGrowthLine);
        }
        // 默认关注三个圈子
        /*
         * Long[] circles = {2l, 3l, 4l}; for (int i = 0; i < circles.length; i++) { // 添加关注
         * UserCircleFollowPojo userCircleFollow = new UserCircleFollowPojo();
         * userCircleFollow.setUserId(sysPojo.getId()); userCircleFollow.setType(2);
         * userCircleFollow.setTypeId(circles[i]); userCircleFollow.setCreateBy(sysPojo.getId());
         * userCircleFollow.setCreateDate(new Date());
         * userCircleFollow.setUpdateBy(sysPojo.getId()); userCircleFollow.setUpdateDate(new
         * Date()); userCircleFollowService.addUserCircleFollow(userCircleFollow); }
         */

        // 用户积分表插入记录
        /*
         * UserScorePojo userScorePojo = new UserScorePojo();
         * userScorePojo.setUserId(sysPojo.getId()); userScorePojo.setName(sysPojo.getName());
         * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         * userScorePojo.setShakeDateStr(sdf.format(new Date())); userScorePojo.setScore(0L);
         * userScorePojo.setShakeDate(StringUtil.stringToDate(sdf.format(new Date())));
         * userScorePojo.setShakeNum(3); userScorePojo.setBunding(0); userScorePojo.setUpload(0);
         * userScorePojo.setImprove(0); userScoreService.insertUserScore(userScorePojo);
         */

        // 用户钱包表插入记录
        UserWalletPojo userWalletPojo = new UserWalletPojo();
        userWalletPojo.setUserId(sysPojo.getId());
        userWalletPojo.setBalance(0.00);
        userWalletPojo.setTotalBalance(0.00);
        userWalletPojo.setCreateBy(sysPojo.getId());
        userWalletPojo.setUpdateBy(sysPojo.getId());
        userWalletService.insertUserWallet(userWalletPojo);
      }

      map1.put("phone", sysPojo.getLoginname());
      map1.put("name", sysPojo.getName());
      map1.put("type", sysPojo.getType());
      map1.put("uid", sysPojo.getId());
      map1.put("token", sysPojo.getToken() == null ? "" : sysPojo.getToken());
      map1.put("openid", sysPojo.getUnionid() == null ? "" : sysPojo.getUnionid());
      map1.put("sinaid", sysPojo.getSinaid() == null ? "" : sysPojo.getSinaid());
      if (sysPojo.getType() != null && "2".equals(sysPojo.getType())) {
        map1.put("judgeType", 1);
      } else {
        map1.put("judgeType", 0);
      }
      // 查询年龄阶段
      double age = SellerService.getAge(StringUtil.stringToDate(babyBirth));
      if (age >= 0.0 && age <= 0.06) {
        map1.put("ageType", 1);
      } else if (age > 0.06 && age <= 1.0) {
        map1.put("ageType", 2);
      } else if (age > 1.0 && age <= 3.0) {
        map1.put("ageType", 3);
      } else if (age > 3.0 && age <= 6.0) {
        map1.put("ageType", 4);
      } else if (age > 6.0 && age <= 12.0) {
        map1.put("ageType", 5);
      } else if (age > 12.0 && age <= 16.0) {
        map1.put("ageType", 6);
      } else if (age > 16.0) {
        map1.put("ageType", 6);
      } else {
        map1.put("ageType", "");
        error_msg = "没有该年龄!";
      }
      params.clear();
      // 查找圈子数
      params.put("userId", sysPojo.getId());
      params.put("status", 1);
      params.put("isFollow", 1);
      params.put("type", 2);
      int followNum = userCircleFollowService.userCircleFollowCount(params);
      map1.put("followNum", followNum);

      success = true;
      error_msg = "提交成功！";
    }
    map.put("success", success);
    map.put("result", map1);
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
   * 成长线webview-笔记详情-知识库-专题详情
   * 
   * @return
   */
  public String goPostOrSpecialOrKnowledge() throws Exception {
    if (type == null || type == 0) {
      System.out.println(">>>>>> type为空!");
    } else {
      // 笔记详情
      if (type == 1) {
        if (postId == null || postId == 0) {
          System.out.println(">>>>>> postId为空!");
        }
        if (userId == null || userId == 0) {
          System.out.println(">>>>>> userId为空!");
        } else {
          userCirclePost = new UserCirclePostPojo();
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("id", postId);
          params.put("status", 1);
          userCirclePost = userCirclePostService.findBycircleParams(params);
          if (userCirclePost == null) {
            System.out.println(">>>>>> postId=" + postId + " 查不到!");
          } else {
            // 判断连接是否可用
            URL url;
            try {
              url =
                  new URL(userCirclePost.getVideoUrl() == null ? "" : userCirclePost.getVideoUrl());
              url.openStream();
              System.out.println("连接可用");
            } catch (Exception e1) {
              userCirclePost.setVideoUrl(null);
              System.out.println("连接打不开!");
              url = null;
            }
            // 判断是否领取任务
            params.clear();
            params.put("userId", userId);
            params.put("taskAge", userCirclePost.getAgeType());
            params.put("ability", userCirclePost.getSkillType());
            params.put("taskTypeLink", 1);
            List<UserTaskPojo> userTaskList = userTaskService.checkBrowsePostTask(params);
            if (userTaskList != null && userTaskList.size() > 0) {
              for (UserTaskPojo userTask : userTaskList) {
                if (userTask.getStatus() != 1) {
                  // 修改任务为完成
                  UserTaskPojo userTaskPojo = new UserTaskPojo();
                  userTaskPojo.setId(userTask.getId());
                  userTaskPojo.setEndTime(new Date());
                  userTaskPojo.setStatus(1);
                  userTaskService.update(userTaskPojo);
                  // 添加积分
                  params.clear();
                  params.put("userId", userId);
                  params.put("score", userTask.getTaskScore());
                  userGrowthLineService.addUserScore(params);
                }
              }
            }
            // 查询是否存在浏览记录
            params.clear();
            params.put("postId", postId);
            params.put("userId", userId);
            params.put("isDelete", 0);
            int i = userPostHistoryService.countBy(params);
            if (i > 0) {
              params.put("lookNum", 1l);
              params.put("viewDate", new Date());
              params.put("updateDate", new Date());
              userPostHistoryService.addLookNum(params);
            } else {
              // 添加笔记浏览记录
              UserPostHistoryPojo userPostHistory = new UserPostHistoryPojo();
              userPostHistory.setUserId(userId);
              userPostHistory.setPostId(postId);
              userPostHistory.setIsDelete(0);
              userPostHistory.setLookNum(1l);
              userPostHistory.setViewDate(new Date());
              userPostHistory.setUpdateDate(new Date());
              userPostHistoryService.add(userPostHistory);
            }
          }
        }
        return "Post";
      } else if (type == 2) {// 知识库
        knowledgeBasePojo = new KnowledgeBasePojo();
        if (ageType == null || ageType == 0) {
          System.out.println(">>>>>> ageType为空!");
        } else if (skillType == null || skillType == 0) {
          System.out.println(">>>>>> skillType为空!");
        } else {
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("ageType", ageType);
          params.put("skillType", skillType);
          params.put("status", 1);
          knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);
          if (knowledgeBasePojo == null) {
            System.out.println(">>>>>>" + ageType + "和" + skillType + "查不到知识库!");
          }
        }
        return "KnowledgeBase";
      } else if (type == 3) {// 专题详情
        if (specialId == null || specialId == 0) {
          System.out.println(">>>>>> specialId为空!");
        } else {
          platformSpecialPojo = new PlatformSpecialPojo();
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("id", specialId);
          params.put("status", 1);
          platformSpecialPojo = platformSpecialService.findSpecialByParams(params);
          if (platformSpecialPojo == null) {
            System.out.println(">>>>>> specialId=" + specialId + " 查不到!");
          }
        }
        return "PlatformSpecial";
      }
    }
    return "errorpage";
  }


  /**
   * 
   * 用户登录API
   * 
   * @throws Exception
   */
  public String agentlogin() throws Exception {
    boolean success = false;
    boolean ckFlag = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> map3 = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    SysLoginPojo loginck = null;
    if (openid != null && !"".equals(openid)) {
      // map3.put("openid", openid);
      map3.put("unionid", openid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "微信号未注册！";
        ckFlag = true;
      }
    } else if (sinaid != null && !"".equals(sinaid)) {
      map3.put("sinaid", sinaid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "新浪id未注册！";
        ckFlag = true;
      }
    } else if (phone == null || phone.equals("")) {
      error_msg = "用户登录名不能为空！";
      ckFlag = true;
    } else if (pass == null || pass.equals("")) {
      error_msg = "用户密码不能为空！";
      ckFlag = true;
    }
    if (ckFlag) {

    } else {
      // 验证用户名和密码是否正确
      SysLoginPojo loginPojo = new SysLoginPojo();
      if (loginck != null) {
        loginPojo.setLoginname(loginck.getLoginname());
        loginPojo.setPassword(loginck.getPassword());
        phone = loginck.getLoginname();
      } else {
        loginPojo.setLoginname(phone);
        // loginPojo.setPassword(MD5Util.MD5(pass));
        loginPojo.setPassword(pass);
      }

      if (loginService.loginCheckWeb(loginPojo)) {
        // 成功
        SysLoginPojo logiPojo = sysLoginService.getSysLoginByLoginName(phone);
        if (logiPojo != null
            && logiPojo.getStatus() == 1
            && (Long.valueOf(logiPojo.getType()) == 6 || Long.valueOf(logiPojo.getType()) == 1
                || Long.valueOf(logiPojo.getType()) == 11 || Long.valueOf(logiPojo.getType()) == 12)) {
          /*
           * HttpServletRequest request = ServletActionContext.getRequest(); // 向登录日志表中插入信息
           * LoginRecPojo loginRecPojo = new LoginRecPojo();
           * loginRecPojo.setType(logiPojo.getType()); loginRecPojo.setLoginDate(new Date());
           * loginRecPojo.setLoginIp(walletService.getIpAddr(request));
           * loginRecPojo.setUserId(logiPojo.getId()); loginRecService.addLoginRec(loginRecPojo);
           */

          // 接收baidu_id并保存进数据库表
          if (logiPojo.getBaidu_uid() != baidu_uid) {
            SysLoginPojo login = new SysLoginPojo();
            login.setBaidu_uid(baidu_uid);
            login.setLoginname(phone);
            loginService.updateBaiduUid(login);
          }

          // 向表baidu_login插入数据
          /*
           * if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
           * BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
           * baiduLoginPojo.setUserId(logiPojo.getId()); baiduLoginPojo.setLoginName(phone);
           * baiduLoginPojo.setBaiduId(baidu_uid); baiduLoginPojo.setType(2);
           * walletService.isInsertBaibuLogin(baiduLoginPojo); }
           */

          // 设置用户id会员名称
          Long uid = logiPojo.getId();
          String name = logiPojo.getName();
          // 查询全部订单数量
          // OrderPojo orderPojo = orderService.orderStatusNum(uid);
          success = true;
          error_msg = "登录成功！";
          map.put("success", success);
          map.put("error_msg", error_msg);
          map2.put("uid", uid);
          map2.put("name", name);
          map2.put("phone", phone);
          map2.put("type", logiPojo.getType());
          map2.put("token", logiPojo.getToken() == null ? "" : logiPojo.getToken());
          // map2.put("openid", logiPojo.getOpenid() == null ? "" : logiPojo.getOpenid());
          map2.put("openid", logiPojo.getUnionid() == null ? "" : logiPojo.getUnionid());
          map2.put("sinaid", logiPojo.getSinaid() == null ? "" : logiPojo.getSinaid());
          if (logiPojo.getImage() == null || "".equals(logiPojo.getImage())) {
            map2.put("image", "");
          } else {
            map2.put("image", ConstParam.URL + "/upfiles/userlogo/" + logiPojo.getImage());
          }
          AgencyPojo agencyPojo = agencyService.findagencyByUserId(uid);
          int status = 0;
          if (agencyPojo != null) {
            map2.put("judgeType", 3);
            status = agencyPojo.getStatus();
          } /*
             * else if (consumerService.findConsumerByUserId(uid) != null) { map2.put("judgeType",
             * 2); }
             */else if (manufacturerService.findManufacturerByUserId(uid) != null) {
            map2.put("judgeType", 1);
          } else {
            map2.put("judgeType", 0);
          }
          if (agencyPojo != null && agencyPojo.getAgencyId() != null) {
            map2.put("agencyId", agencyPojo.getAgencyId());
          } else {
            map2.put("agencyId", 0);
          }

          map2.put("status", status);

          // 加newUser
          int newUser =
              walletService.isNewWalletUser(uid, StringUtil.stringDate("2015-12-12 00:00:00"));
          map2.put("newUser", newUser);
          // 加balance
          UserWalletPojo userWallet0 = userWalletService.findUserWalletByUserId(uid);
          if (userWallet0 != null) {
            map2.put("balance", userWallet0.getBalance() == null ? 0 : userWallet0.getBalance());
          } else {
            map2.put("balance", 0);
          }

          // 查询成长值
          params.clear();
          params.put("userId", uid);
          params.put("isDefault", 1);
          params.put("isDelete", 0);
          userBabyPojo = userBabyService.getByParams(params);
          if (userBabyPojo != null && userBabyPojo.getBabyBirthday() != null) {
            // 查询年龄
            double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
            // 查询年龄对应能力
            if (age >= 0.0 && age <= 0.06) {
              map2.put("ageType", 1);
            } else if (age > 0.06 && age <= 1.0) {
              map2.put("ageType", 2);
            } else if (age > 1.0 && age <= 3.0) {
              map2.put("ageType", 3);
            } else if (age > 3.0 && age <= 6.0) {
              map2.put("ageType", 4);
            } else if (age > 6.0 && age <= 12.0) {
              map2.put("ageType", 5);
            } else if (age > 12.0 && age <= 16.0) {
              map2.put("ageType", 6);
            } else if (age > 16.0) {
              map2.put("ageType", 6);
            } else {
              map2.put("ageType", "");
            }
          } else {
            map2.put("ageType", "");
            error_msg = "查询不到宝宝信息!";
          }

          // 查找圈子数
          params.clear();
          params.put("userId", logiPojo.getId());
          params.put("status", 1);
          params.put("isFollow", 1);
          params.put("type", 2);
          int followNum = userCircleFollowService.userCircleFollowCount(params);
          map2.put("followNum", followNum);

          map.put("result", map2);
          // JSONObject json = JSONObject.fromObject(map);
          // actionContext.put("result", json.toString());
          // return SUCCESS;
        } else {
          error_msg = "请用批发商账号登陆";
        }
      } else {
        error_msg = "用户名或密码错误";
      }
    }
    map.put("success", success);
    map.put("result", map2);
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
   * 平台专专题列表-商品
   * 
   * @return
   * @throws SQLException
   */
  public String platformSpecialGoods() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    DecimalFormat df = new DecimalFormat("#.##");
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
    if (id == null || id == 0) {
      error_msg = "专题详情不能为空!";
    } else {
      params.put("type", 6);
      params.put("titleId", id);
      List<ActivityProductPojo> activityProductList =
          activityProductService.findActivityGoods(params);
      if (activityProductList != null && activityProductList.size() != 0) {
        for (ActivityProductPojo activityProductPojo : activityProductList) {
          item = new HashMap<String, Object>();
          item.put("id", activityProductPojo.getProductId());
          item.put("image", ConstParam.URL + "/upfiles/product/" + activityProductPojo.getImage());
          item.put("name", activityProductPojo.getProductName());
          item.put(
              "sellingPrice",
              activityProductPojo.getSellingPrice() == null ? "" : df.format(activityProductPojo
                  .getSellingPrice()));
          item.put(
              "price",
              activityProductPojo.getActivePrice() == null ? "" : df.format(activityProductPojo
                  .getActivePrice()));
          item.put("activityId", activityProductPojo.getActivityId());
          result.add(item);
        }
        success = true;
      } else {
        error_msg = "查询不到商品!";
      }
    }

    map.put("success", success);
    map.put("result", result);
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
   * 查找默认宝宝Id
   * 
   * @return
   */
  public Long findBabyId(Long userId) {
    Map<String, Object> param = new HashMap<String, Object>();
    // 查找宝宝信息
    param.clear();
    param.put("userId", userId);
    param.put("isDefault", 1);
    param.put("isDelete", 0);
    userBabyPojo = userBabyService.getByParams(param);
    return userBabyPojo.getId();
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
}
