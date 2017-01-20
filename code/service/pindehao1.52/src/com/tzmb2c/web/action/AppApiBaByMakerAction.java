package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ActivityTitlePojo;
import com.tzmb2c.web.pojo.AgeSkillLinkPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.HonorRulePojo;
import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.MicroPagePojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SpecialPojo;
import com.tzmb2c.web.pojo.SpecialTypePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TaskPojo;
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
import com.tzmb2c.web.pojo.UserTalentAuthPojo;
import com.tzmb2c.web.pojo.UserTaskPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.pojo.ZonesPojo;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ActivityTitleService;
import com.tzmb2c.web.service.AgeSkillLinkService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.FocusSettingService;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.HonorRuleService;
import com.tzmb2c.web.service.InfoService;
import com.tzmb2c.web.service.KnowledgeBaseService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.MicroPageService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.SpecialService;
import com.tzmb2c.web.service.SpecialTypeService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.TaskLibraryService;
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
import com.tzmb2c.web.service.UserTalentAuthService;
import com.tzmb2c.web.service.UserTaskService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.ZonesService;

public class AppApiBaByMakerAction extends SuperAction {
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
  private SysLoginService sysLoginService;
  @Autowired
  private UserCircleFollowService userCircleFollowService;
  @Autowired
  private PlatformSpecialService platformSpecialService;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private UserTalentAuthService userTalentAuthService;
  @Autowired
  private KnowledgeBaseService knowledgeBaseService;
  @Autowired
  private UserTaskService userTaskService;
  @Autowired
  private AgeSkillLinkService ageSkillLinkService;
  @Autowired
  private UserInfoService userInfoService;
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
  private UserBabyService userBabyService;
  @Autowired
  private UserCalendarService userCalendarService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private UserPostCollectService userPostCollectService;
  @Autowired
  private SocialCircleService socialCircleService;
  @Autowired
  private TaskLibraryService taskLibraryService;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private UserMakerShopService userMakerShopService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private InfoService infoService;
  @Autowired
  private ActivityTitleService activityTitleService;
  @Autowired
  private MicroPageService microPageService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private ProductService productService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private FocusSettingService focusSettingService;
  @Autowired
  private SpecialTypeService specialTypeService;
  @Autowired
  private SpecialService specialService;
  @Autowired
  private ZonesService zonesService;

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
  private UserMakerThemePojo userMakerThemePojo;
  private UserCirclePostPojo userCirclePostPojo;
  private PlatformSpecialPojo platformSpecialPojo;
  private MicroPagePojo microPagePojo;
  private Integer t;
  private Long pdkUid;

  // ---- getter and setter ---- //

  public Integer getT() {
    return t;
  }

  public Long getPdkUid() {
    return pdkUid;
  }

  public void setPdkUid(Long pdkUid) {
    this.pdkUid = pdkUid;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public MicroPagePojo getMicroPagePojo() {
    return microPagePojo;
  }

  public void setMicroPagePojo(MicroPagePojo microPagePojo) {
    this.microPagePojo = microPagePojo;
  }

  public PlatformSpecialPojo getPlatformSpecialPojo() {
    return platformSpecialPojo;
  }

  public void setPlatformSpecialPojo(PlatformSpecialPojo platformSpecialPojo) {
    this.platformSpecialPojo = platformSpecialPojo;
  }

  public UserCirclePostPojo getUserCirclePostPojo() {
    return userCirclePostPojo;
  }

  public void setUserCirclePostPojo(UserCirclePostPojo userCirclePostPojo) {
    this.userCirclePostPojo = userCirclePostPojo;
  }

  public UserMakerThemePojo getUserMakerThemePojo() {
    return userMakerThemePojo;
  }

  public void setUserMakerThemePojo(UserMakerThemePojo userMakerThemePojo) {
    this.userMakerThemePojo = userMakerThemePojo;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public String getImgurl() {
    return imgurl;
  }

  public void setImgurl(String imgurl) {
    this.imgurl = imgurl;
  }

  public String getKeyword() {
    return keyword;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Integer getMax() {
    return max;
  }

  public UserBabyPojo getUserBabyPojo() {
    return userBabyPojo;
  }

  public void setUserBabyPojo(UserBabyPojo userBabyPojo) {
    this.userBabyPojo = userBabyPojo;
  }

  public String getBabyId() {
    return babyId;
  }

  public void setBabyId(String babyId) {
    this.babyId = babyId;
  }

  public String getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(String isDefault) {
    this.isDefault = isDefault;
  }

  public String getBabyNick() {
    return babyNick;
  }

  public void setBabyNick(String babyNick) {
    this.babyNick = babyNick;
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

  // ------ 首页 ------ //
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
    } else if (uid == null || uid <= 0) {
      error_msg = "当前用户ID不能为空哦！~";
    } else {
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
        for (UserCirclePostPojo userCirclePostPojo : UserCirclePostList) {
          item = new HashMap<String, Object>();

          item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
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

          if (getUserCollectInfoCount(userCirclePostPojo.getId(), userCirclePostPojo.getUserId(),
              2, uid) > 0) {
            item.put("postCollect", "1");
          } else {
            item.put("postCollect", "0");
          }

          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1)
              + "");

          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");

          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
          }
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

    if (uid == null || uid <= 0) {
      msg = "当前用户ID不能为空哦！~";
    } else if ((id == null || id <= 0) && (pid == null || pid <= 0)) {
      msg = "品牌用户ID或者创客专题ID不能为空哦！~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      // if (stageType == null || stageType <= 0) {
      // stageType = 1l;
      // }
      option.put("status", 1);
      if (id != null && id > 0) {
        option.put("userId", id);
        if (pageNo == null || pageNo == 0) {
          pageNo = 1;
        }
        option.put("pageNo", (pageNo - 1) * 10);
        option.put("pageSize", 10);
      } else if (pid != null && pid > 0) {
        UserMakerThemePojo makerThemePojo = userMakerThemeService.getUserMakerThemeById(pid);
        if (makerThemePojo != null) {
          option.put("ageType",
              makerThemePojo.getAgeType() == null ? 1 : makerThemePojo.getAgeType());
        } else {
          option.put("ageType", 1);
        }
        option.put("pageNo", 0);
        option.put("pageSize", 2);
      } else {

      }
      option.put("orderBy",
          "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
      if (list.size() != 0) {
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", userCirclePostPojo.getId() + "");
          item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? "0"
              : userCirclePostPojo.getCollectNum().toString());

          item.put("authorSex", userCirclePostPojo.getUserSex() == null ? "1" : userCirclePostPojo
              .getUserSex().toString());
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1)
              + "");
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
          }
          if (getUserCollectInfoCount(userCirclePostPojo.getId(), userCirclePostPojo.getUserId(),
              2, uid) > 0) {
            item.put("postCollect", "1");
          } else {
            item.put("postCollect", "0");
          }
          item.put("authorId", userCirclePostPojo.getUserId() == null ? "0" : userCirclePostPojo
              .getUserId().toString());

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
        item.put("icon", knowledgeBasePojo.getSmallIcon() == null ? "" : ConstParam.URL
            + "/upfiles/knowledgeBase" + File.separator + knowledgeBasePojo.getSmallIcon());
        item.put("title", knowledgeBasePojo.getTitle());
        item.put("id", knowledgeBasePojo.getId().toString());
        item.put("ageType", knowledgeBasePojo.getAgeType() == null ? "1" : knowledgeBasePojo
            .getAgeType().toString());
        item.put("skillType", knowledgeBasePojo.getSkillType() == null ? "1" : knowledgeBasePojo
            .getSkillType().toString());
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
   * 社圈详情-笔记列表
   * 
   * @return
   * @throws SQLException
   */
  public String circleNotesApi() throws SQLException {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> all = new HashMap<String, Object>();

    if (socialCircleId == null || socialCircleId == 0) {
      error_msg = "社圈ID不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      Map<String, Object> item = null;

      // 热门帖子
      List<Map<String, Object>> userCirclePostList = new ArrayList<Map<String, Object>>();
      List<Long> pids = new ArrayList<Long>();
      params.put("pageNo", 0);
      params.put("pageSize", 3);
      params.put("status", 1);
      params.put("circleId", socialCircleId);
      params.put("orderBy",
          "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      List<UserCirclePostPojo> ucpList = userCirclePostService.userCirclePostList(params);
      if (ucpList.size() > 0) {
        for (UserCirclePostPojo ucp : ucpList) {
          item = new HashMap<String, Object>();
          item.put("postId", ucp.getId() == null ? "0" : ucp.getId().toString());
          item.put("postTitle", ucp.getTitle() == null ? "" : ucp.getTitle());
          item.put("banner", ucp.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + ucp.getBanner());
          item.put("content", ucp.getContent() == null ? "" : ucp.getContent());
          item.put("authorName", ucp.getUserName() == null ? "" : ucp.getUserName());
          item.put("authorId", ucp.getUserId() == null ? "0" : ucp.getUserId().toString());
          item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
          item.put("collectNum", ucp.getCollectNum() == null ? "0" : ucp.getCollectNum().toString());
          item.put("commentNum", ucp.getCommentNum() == null ? "0" : ucp.getCommentNum().toString());
          item.put("likeNum", ucp.getLikeNum() == null ? "0" : ucp.getLikeNum().toString());
          String dTime =
              UtilDate.timeDifference(ucp.getCreateDateStr(), UtilDate.getDateFormatter());
          item.put("dTime", dTime + "");
          if (getUserCollectInfoCount(ucp.getId(), ucp.getUserId(), 2, uid) > 0) {
            item.put("postCollect", "1");
          } else {
            item.put("postCollect", "0");
          }
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex().toString());
          item.put("authorFansNum", getUserFollowInfoCount(null, ucp.getUserId(), 1) + "");
          item.put("authorCollectNum", getUserFollowInfoCount(ucp.getUserId(), null, 1) + "");
          if (getUserFollowInfoCount(uid, ucp.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
          }
          userCirclePostList.add(item);
          pids.add(ucp.getId());
        }
        // } else {
        // error_msg = "查询不到圈子热门笔记哦！~";
      }

      // 普通贴子
      List<Map<String, Object>> postList = new ArrayList<Map<String, Object>>();
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * 10);
      }
      params.put("pageSize", 10);
      params.put("orderBy", "u.create_date desc");
      params.put("pids", pids);
      List<UserCirclePostPojo> ucpList2 = userCirclePostService.userCirclePostList(params);
      if (ucpList2.size() > 0) {
        for (UserCirclePostPojo ucp : ucpList2) {
          item = new HashMap<String, Object>();
          item.put("postId", ucp.getId() == null ? "0" : ucp.getId().toString());
          item.put("postTitle", ucp.getTitle() == null ? "" : ucp.getTitle());
          item.put("banner", ucp.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + ucp.getBanner());
          item.put("content", ucp.getContent() == null ? "" : ucp.getContent());
          item.put("authorName", ucp.getUserName() == null ? "" : ucp.getUserName());
          item.put("authorId", ucp.getUserId() == null ? "0" : ucp.getUserId().toString());
          item.put("authorLogo", ucp.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + ucp.getUserImage());
          item.put("collectNum", ucp.getCollectNum() == null ? "0" : ucp.getCollectNum().toString());
          item.put("commentNum", ucp.getCommentNum() == null ? "0" : ucp.getCommentNum().toString());
          item.put("likeNum", ucp.getLikeNum() == null ? "0" : ucp.getLikeNum().toString());
          String dTime =
              UtilDate.timeDifference(ucp.getCreateDateStr(), UtilDate.getDateFormatter());
          item.put("dTime", dTime + "");
          if (getUserCollectInfoCount(ucp.getId(), ucp.getUserId(), 2, uid) > 0) {
            item.put("postCollect", "1");
          } else {
            item.put("postCollect", "0");
          }
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex().toString());
          item.put("authorFansNum", getUserFollowInfoCount(null, ucp.getUserId(), 1) + "");
          item.put("authorCollectNum", getUserFollowInfoCount(ucp.getUserId(), null, 1) + "");
          if (getUserFollowInfoCount(uid, ucp.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
          }
          postList.add(item);
        }
        // } else {
        // error_msg = "查询不到圈子普通笔记哦！~";
      }

      success = true;
      all.put("userCirclePostList", userCirclePostList);
      all.put("postList", postList);
    }
    map.put("success", success);
    map.put("result", all);
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
    Map<String, Object> map3 = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> params2 = new HashMap<String, Object>();
    new ArrayList<Map<String, Object>>();
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
        map3.put("isConcern", "1");
      } else {
        map3.put("isConcern", "0");
      }
      // 查找圈子笔记总数
      params.put("circleId", socialCircleId);
      int postCount = userCirclePostService.userCirclePostCount(params);
      map3.put("noteCount", postCount + "");
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
    }

    map.put("success", success);
    map.put("result", map3);
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
   * 首页宝典搜索
   * 
   * @return
   * @throw
   * @return String
   * @throws Exception
   */
  public String getHomePageCanonSearch() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item;

    new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (keyword == null || "".equals(keyword)) {
      msg = "搜索的内容不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("title", keyword);
      params.put("status", 1);
      params.put("orderBy",
          "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(params);
      if (list.size() > 0) {
        for (UserCirclePostPojo u : list) {
          item = new HashMap<String, Object>();
          item.put("banner",
              ConstParam.URL + "/upfiles/userCirclePost" + File.separator
                  + (u.getBanner() == null ? "" : u.getBanner()));
          item.put("postId", u.getId().toString());
          item.put("postTitle", u.getTitle() == null ? "" : u.getTitle());
          if (getUserCollectInfoCount(u.getId(), u.getUserId(), 2, uid) > 0) {
            item.put("postCollect", "1");
          } else {
            item.put("postCollect", "0");
          }
          item.put("collectNum", u.getCollectNum() == null ? "0" : u.getCollectNum().toString());

          item.put("authorId", u.getUserId().toString());
          item.put("authorLogo",
              u.getUserImage() == null ? "" : ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + (u.getUserImage() == null ? "" : u.getUserImage()));
          item.put("authorName", u.getUserName() == null ? "" : u.getUserName());
          item.put("authorSex", u.getUserSex() == null ? "1" : u.getUserSex() + "");
          if (getUserFollowInfoCount(uid, u.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
          }
          item.put("authorFansNum", getUserFollowInfoCount(null, u.getUserId(), 1) + "");
          item.put("authorCollectNum", getUserFollowInfoCount(u.getUserId(), null, 1) + "");
          result.add(item);
        }
        b = true;
      } else {
        msg = "没有找到相关的内容哦！~";
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
   * 
   * 首页专题搜索
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String getHomePageSpecialSearch() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item;

    new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (keyword == null || "".equals(keyword)) {
      msg = "搜索的内容不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("title", keyword);
      // params.put("type", 3);
      params.put("status", 1);
      params.put("orderBy", "update_date desc,create_date desc");
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      List<PlatformSpecialPojo> list = platformSpecialService.listPage(params);
      if (list.size() > 0) {
        for (PlatformSpecialPojo p : list) {
          item = new HashMap<String, Object>();
          item.put("banner",
              ConstParam.URL + "/upfiles/platformSpecial" + File.separator
                  + (p.getBanner() == null ? "" : p.getBanner()));
          item.put("platId", p.getId().toString());
          item.put("platTitle", p.getTitle() == null ? "" : p.getTitle());
          item.put("specialCategories", p.getSpecialCategories() == null ? "1" : p
              .getSpecialCategories().toString());
          result.add(item);
        }
        b = true;
      } else {
        msg = "没有找到相关的内容哦！~";
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
   * 首页达人搜索
   * 
   * @return
   * @throw
   * @return String
   * @throws Exception
   */
  public String getHomePageMasterSearch() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item;

    new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (keyword == null || "".equals(keyword)) {
      msg = "查询的内容不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("userId", uid);
      params.put("userType", 11);
      params.put("userName", keyword);
      params.put("status", 1);
      params.put("orderBy", "fansNum desc,s.update_date desc,s.create_date desc");
      pageSize = 20;
      if (pageNo == null || pageNo <= 0) {
        pageNo = 1;
      }
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      List<UserTalentAuthPojo> list = userTalentAuthService.searchUserTalentAuth(params);
      if (list.size() > 0) {
        for (UserTalentAuthPojo u : list) {
          item = new HashMap<String, Object>();
          item.put("userName", u.getUserName() == null ? "" : u.getUserName());
          item.put("userImage",
              ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + (u.getUserImage() == null ? "" : u.getUserImage()));
          item.put("userId", u.getUserId().toString());
          item.put("fansNum", u.getFansNum() == null ? "0" : u.getFansNum().toString());

          // item.put("followNum", u.getFollowNum() == null ? "0" : u.getFollowNum().toString());
          if (u.getFollowNum() > 0) {
            item.put("isConcern", "1");
          } else {
            item.put("isConcern", "0");
          }

          UserInfoPojo userInfoPojo = userInfoService.findUserInfoByUserId(u.getUserId());
          if (userInfoPojo != null) {
            // 查询年龄
            int age = SellerService.getBabyAgeType(userInfoPojo.getBabyBirthday());
            item.put("age", age + "");
          } else {
            item.put("age", "1");
          }

          item.put("circleNum", getUserFollowInfoCount(u.getUserId(), null, 2) + "");
          item.put("postNum", u.getPostNum() == null ? "0" : u.getPostNum().toString());
          result.add(item);
        }
        b = true;
      } else {
        msg = "没有找到相关的内容哦！~";
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
            if (i == 0) {
              // 查询推送信息创客首页banner
              Map<String, Object> option1 = new HashMap<String, Object>();
              option1.put("type", 10);
              option1.put("status", 1);
              List<PagePushPojo> brandbanner = pagePushService.findAdByTypeApi(option1);
              if (brandbanner.size() != 0 && i == 0) {
                items.put(
                    "logo",
                    brandbanner.get(0).getImages() == null
                        || "".equals(brandbanner.get(0).getImages()) ? "" : ConstParam.URL
                        + "/upfiles/notice" + File.separator + brandbanner.get(0).getImages());
              } else {
                items.put("logo", "");
              }
            } else {
              items.put("logo", ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
                  + userMakerBrandPojo.getLogo());
            }
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

  // ------ 日迹 ------ //
  /**
   * 任务详情
   * 
   * @return
   * @throws SQLException
   */
  public String getTaskDetail() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();

    if (taskId == null || taskId == 0) {
      msg = "任务ID不能为空哦！~";
    } else {
      TaskSettingPojo task = taskSettingService.getTaskSettingById(taskId);
      if (task != null) {
        TaskPojo taskLib = taskLibraryService.findTaskLibraryById(task.getTaskId());
        if (taskLib != null) {
          result.put("taskType",
              taskLib.getTaskType() == null ? "" : String.valueOf(taskLib.getTaskType()));
          result.put("title", StringUtil.checkString(taskLib.getTaskTitle()));
          result.put("content", StringUtil.checkString(taskLib.getTaskContent()));
          result.put("image", ConstParam.URL + "/upfiles/task/" + taskLib.getTaskAge() + "-"
              + taskLib.getAbility() + ".png");
          b = true;
        } else {
          msg = "未找到任务信息！~";
        }

      } else {
        msg = "未找到任务信息！~";
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

  // ------ 我的 ------ //

  // ------ baby ----- //
  /**
   * 添加宝宝个人资料
   * 
   * @return
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String addUserBabyInfoApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (babyNick == null || babyNick.equals("")) {
      msg = "宝宝昵称不能为空!";
    } else if (babySex == null || babySex == 0) {
      msg = "宝宝性别不能为空!";
    } else if (babyBirth == null || babyBirth.equals("")) {
      msg = "宝宝生日不能为空!";
    } else {
      params.put("userId", uid);
      params.put("isDelete", 0);
      int j = userBabyService.countBy(params);
      if (j < 3) {
        params.put("babyName", babyNick);
        params.put("babySex", babySex);
        params.put("babyBirthday", StringUtil.stringToDate(babyBirth));
        int i = userBabyService.countBy(params);
        if (i > 0) {
          msg = "用户已有该宝宝!";
        } else {
          UserBabyPojo userBaby = new UserBabyPojo();
          userBaby.setUserId(uid);
          userBaby.setBabyName(babyNick);
          userBaby.setBabySex(babySex);
          userBaby.setBabyBirthday(StringUtil.stringToDate(babyBirth));
          userBaby.setCreateBy(uid);
          userBaby.setCreateDate(new Date());
          userBaby.setUpdateBy(uid);
          userBaby.setUpdateDate(new Date());
          if (isDefault != null) {
            if (Integer.valueOf(isDefault) == 1) {
              userBaby.setIsDefault(Integer.parseInt(isDefault));
              // 判断是否存在默认宝宝
              params.clear();
              params.put("userId", uid);
              params.put("isDelete", 0);
              params.put("isDefault", 1);
              List<UserBabyPojo> userBabyList = userBabyService.listPage(params);
              if (userBabyList != null) {
                for (UserBabyPojo userBabyPojo : userBabyList) {
                  userBabyPojo.setIsDefault(0);
                  userBabyService.update(userBabyPojo);
                }
                userBabyService.add(userBaby);
              } else {
                userBabyService.add(userBaby);
              }
            } else if (Integer.valueOf(isDefault) == 0) {
              userBaby.setIsDefault(0);
              userBabyService.add(userBaby);
            }
          } else {
            userBaby.setIsDefault(0);
            userBabyService.add(userBaby);
          }
          params.clear();
          params.put("userId", uid);
          params.put("babyName", babyNick);
          params.put("babySex", babySex);
          params.put("babyBirthday", StringUtil.stringToDate(babyBirth));
          params.put("isDelete", 0);
          userBabyPojo = userBabyService.getByParams(params);
          if (userBabyPojo != null) {
            // 用户成长线表插入记录
            UserGrowthLinePojo userGrowthLine = new UserGrowthLinePojo();
            userGrowthLine.setUserId(uid);
            userGrowthLine.setBabyId(userBabyPojo.getId());
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

          // 查询年龄
          double age = SellerService.getAge(StringUtil.stringToDate(babyBirth));
          if (age >= 0.0 && age <= 0.06) {
            ageType = 1l;
          } else if (age > 0.06 && age <= 1.0) {
            ageType = 2l;
          } else if (age > 1.0 && age <= 3.0) {
            ageType = 3l;
          } else if (age > 3.0 && age <= 6.0) {
            ageType = 4l;
          } else if (age > 6.0 && age <= 12.0) {
            ageType = 5l;
          } else if (age > 12.0 && age <= 16.0) {
            ageType = 6l;
          } else {
            ageType = 6l;
          }
          result.put("ageType", ageType == null ? "" : ageType);
          msg = "添加宝宝成功!";
          b = true;
        }
      } else {
        msg = "用户最多只能添加三个宝宝!";
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
   * 编辑宝宝个人资料
   * 
   * @return
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String updateUserBabyInfoApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (babyNick == null || babyNick.equals("")) {
      msg = "宝宝昵称不能为空!";
    } else if (babySex == null || babySex == 0) {
      msg = "宝宝性别不能为空!";
    } else if (babyBirth == null || babyBirth.equals("")) {
      msg = "宝宝生日不能为空!";
    } else if (babyId == null || babyId.equals("")) {
      msg = "宝宝ID不能为空!";
    } else {
      UserBabyPojo userBaby = new UserBabyPojo();
      params.put("userId", uid);
      params.put("id", Long.valueOf(babyId));
      params.put("isDelete", 0);
      int babyCount = userBabyService.countBy(params);
      if (babyCount > 0) {
        if (Integer.valueOf(isDefault) == 1) {
          // 修改其他数据为非默认
          params.clear();
          params.put("userId", uid);
          params.put("isDefault", 1);
          params.put("isDelete", 0);
          List<UserBabyPojo> userBabyList = userBabyService.listPage(params);
          if (userBabyList != null && userBabyList.size() != 0) {
            for (UserBabyPojo userBabyPojo : userBabyList) {
              if (userBabyPojo.getId() != Long.valueOf(babyId)) {
                userBabyPojo.setIsDefault(0);
                userBabyPojo.setUpdateDate(new Date());
                userBabyService.update(userBabyPojo);
              }
            }
          }
          // 设置为默认宝宝
          userBaby.setIsDefault(Integer.parseInt(isDefault));
          userBaby.setUserId(uid);
          userBaby.setId(Long.valueOf(babyId));
          userBaby.setBabyName(babyNick);
          userBaby.setBabySex(babySex);
          userBaby.setBabyBirthday(StringUtil.stringToDate(babyBirth));
          userBaby.setUpdateDate(new Date());
          userBabyService.update(userBaby);
        } else if (Integer.valueOf(isDefault) == 0) {
          params.clear();
          params.put("userId", uid);
          params.put("isDelete", 0);
          params.put("isDefault", 1);
          babyCount = userBabyService.countBy(params);
          // 只有一个默认宝宝的话不修改
          if (babyCount > 1) {
            userBaby.setIsDefault(0);
          }
          userBaby.setUserId(uid);
          userBaby.setId(Long.valueOf(babyId));
          userBaby.setBabyName(babyNick);
          userBaby.setBabySex(babySex);
          userBaby.setBabyBirthday(StringUtil.stringToDate(babyBirth));
          userBaby.setUpdateDate(new Date());
          userBabyService.update(userBaby);
        }
        // 查询年龄
        double age = SellerService.getAge(StringUtil.stringToDate(babyBirth));
        if (age >= 0.0 && age <= 0.06) {
          ageType = 1l;
        } else if (age > 0.06 && age <= 1.0) {
          ageType = 2l;
        } else if (age > 1.0 && age <= 3.0) {
          ageType = 3l;
        } else if (age > 3.0 && age <= 6.0) {
          ageType = 4l;
        } else if (age > 6.0 && age <= 12.0) {
          ageType = 5l;
        } else if (age > 12.0 && age <= 16.0) {
          ageType = 6l;
        } else {
          ageType = 6l;
        }
        result.put("ageType", ageType == null ? "" : ageType);
        b = true;
        msg = "修改宝宝资料成功!";
      } else {
        msg = "没有这个宝宝!";
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
   * 查看宝宝个人资料
   * 
   * @return
   * @throws Exception
   */
  public String userBabyInfoApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else if (babyId == null || babyId.equals("")) {
      msg = "宝宝ID不能为空!";
    } else {
      params.put("userId", uid);
      params.put("id", babyId);
      UserBabyPojo userBabyPojo = userBabyService.getByParams(params);
      if (userBabyPojo != null) {
        result
            .put("babyNick", userBabyPojo.getBabyName() == null ? "" : userBabyPojo.getBabyName());
        result.put("babySex", userBabyPojo.getBabySex().toString() == null ? "" : userBabyPojo
            .getBabySex().toString());
        result.put(
            "babyBirth",
            StringUtil.dateToString(userBabyPojo.getBabyBirthday()) == null ? "" : StringUtil
                .dateToString(userBabyPojo.getBabyBirthday()));
        result.put("isDefault", userBabyPojo.getIsDefault().toString() == null ? "" : userBabyPojo
            .getIsDefault().toString());
        b = true;
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
    Map<String, Object> babyInfo = new HashMap<String, Object>();
    List<Map<String, Object>> babyInfoArray = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      SysLoginPojo user = sysLoginService.getUserInfoById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        result.put("name", user.getName() == null ? "" : user.getName());
        result.put("sex", user.getSex() == null ? 1 : user.getSex());
        result.put("logo", ConstParam.URL + "/upfiles/userlogo/" + user.getImage());
      } else {
        result.put("name", "");
        result.put("sex", "");
        result.put("logo", "");
        msg = "未找到用户信息!";
      }
      params.put("userId", uid);
      params.put("isDelete", 0);
      params.put("pageSize", 3);
      params.put("pageNo", 0);
      params.put("orderBy", "is_default desc");
      List<UserBabyPojo> userBabyList = userBabyService.listPage(params);
      if (userBabyList != null && userBabyList.size() != 0) {
        for (UserBabyPojo userBaby : userBabyList) {
          babyInfo = new HashMap<String, Object>();
          babyInfo.put("babyId", userBaby.getId() == null ? "" : userBaby.getId());
          babyInfo.put(
              "babyBirth",
              StringUtil.dateToString(userBaby.getBabyBirthday()) == null ? "" : StringUtil
                  .dateToString(userBaby.getBabyBirthday()));
          babyInfo.put("babySex", userBaby.getBabySex() == null ? "" : userBaby.getBabySex());
          babyInfo.put("babyNick", userBaby.getBabyName() == null ? "" : userBaby.getBabyName());
          babyInfo.put("isDefault", userBaby.getIsDefault() == null ? "" : userBaby.getIsDefault());
          babyInfoArray.add(babyInfo);
        }
        result.put("babyInfo", babyInfoArray);
      } else {
        result.put("babyInfo", babyInfoArray);
        msg = "未找到宝宝信息!";
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
        /*
         * if (sex != null && sex != 0) { user.setSex(sex); } if (babySex != null && babySex != 0) {
         * user.setBabySex(babySex); } if (babyBirth != null && !"".equals(babyBirth)) {
         * user.setBabyBirthday(babyBirth); }
         */
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
    } else if (babyNick == null || babyNick.equals("")) {
      regFlag = true;
      error_msg = "请输入宝宝昵称！";
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
        userBaby.setBabyName(babyNick);
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
        Long[] circles = {2l, 3l, 4l};
        for (int i = 0; i < circles.length; i++) {
          // 添加关注
          UserCircleFollowPojo userCircleFollow = new UserCircleFollowPojo();
          userCircleFollow.setUserId(sysPojo.getId());
          userCircleFollow.setType(2);
          userCircleFollow.setTypeId(circles[i]);
          userCircleFollow.setCreateBy(sysPojo.getId());
          userCircleFollow.setCreateDate(new Date());
          userCircleFollow.setUpdateBy(sysPojo.getId());
          userCircleFollow.setUpdateDate(new Date());
          userCircleFollowService.addUserCircleFollow(userCircleFollow);
        }

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
            boolean flag = true;
            for (int i = 0; i < skills.length; i++) {
              if (skills[i] > 0) {
                flag = false;
                break;
              }
            }
            if (flag) {
              FileUtil.skipAction("getGrowthLineWebApi.do?{\"error_msg\":\"请先完成今日任务!\"}");
              return null;
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
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<AgeSkillLinkPojo> aslList = null;
    List<Map<String, Object>> ageSkillLinkList = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
    } else {
      params.put("userId", uid);
      params.put("isDefault", 1);
      params.put("isDelete", 0);
      userBabyPojo = userBabyService.getByParams(params);
      if (userBabyPojo == null) {
        error_msg = "查询不到宝宝信息!";
      } else if (userBabyPojo.getBabyBirthday() == null) {
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
      } else {
        // 查询年龄
        double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
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
      }
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
        // 查询宝宝信息
        params.clear();
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
              if (StringUtil.dateToString(tsPojo.getTaskDate()).equals(
                  StringUtil.dateToString(new Date()))) {
                task.put("isTody", "1");
              } else {
                task.put("isTody", "0");
              }
              if (tsPojo.getTaskAge() != null && tsPojo.getAbility() != null) {
                task.put("banner", ConstParam.URL + "/upfiles/task/" + tsPojo.getTaskAge() + "-"
                    + tsPojo.getAbility() + ".png");
              } else {
                task.put("banner", "");
              }
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
            result2.put(
                "date",
                StringUtil.dateToString(userTask.getTaskDate()).equals(
                    StringUtil.dateToString(new Date())) ? "今天" : StringUtil.dateToString(userTask
                    .getTaskDate()));
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
      // } else if (typeId == null) {
      // msg = "排序类型不能为空！~";
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
        for (UserCirclePostPojo userCirclePostPojo : list) {
          item = new HashMap<String, Object>();
          item.put("postId", userCirclePostPojo.getId().toString());
          item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
          item.put("postTitle",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
              + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());
          item.put("authorName",
              userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
          item.put("collectNum", userCirclePostPojo.getCollectNum() == null ? "0"
              : userCirclePostPojo.getCollectNum().toString());
          item.put("authorId", userCirclePostPojo.getUserId() == null ? "0" : userCirclePostPojo
              .getUserId().toString());
          // 查询是否收藏
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
          item.put("authorSex", userCirclePostPojo.getUserSex() == null ? "1" : userCirclePostPojo
              .getUserSex().toString());
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1)
              + "");
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
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
    if (age == null || age == "") {
      error_msg = "年龄value不能为空!";
    } else if (uid == null || uid == 0) {
      error_msg = "用户id不能为空!";
      // } else if (typeId == null) {
      // error_msg = "排序类型不能为空！~";
    } else {
      params.put("ageType", age);
      params.put("status", 1);

      if (typeId == null) {
        params.put("orderBy",
            "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
      } else {
        if (typeId == 0) {
          params.put("orderBy",
              "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
        } else if (typeId == 1) {
          params.put("orderBy", "u.look_num desc");
        } else if (typeId == 2) {
          params.put("orderBy", "u.update_date desc");
        } else {
          params.put("orderBy",
              "u.comment_num desc,u.collect_num desc,u.update_date desc,u.create_date desc");
        }
      }

      List<UserCirclePostPojo> UserCirclePostList =
          userCirclePostService.userCirclePostList(params);
      if (UserCirclePostList.size() != 0) {
        for (UserCirclePostPojo userCirclePostPojo : UserCirclePostList) {
          item = new HashMap<String, Object>();
          item.put("banner", userCirclePostPojo.getBanner() == null ? "" : ConstParam.URL
              + "/upfiles/userCirclePost" + File.separator + userCirclePostPojo.getBanner());
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
          item.put("authorFansNum", getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1)
              + "");
          item.put("authorCollectNum",
              getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
          if (getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
            item.put("authorFollow", "1");
          } else {
            item.put("authorFollow", "0");
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
      // } else if (type != 4 && (id == null || id <= 0)) {
    } else if (id == null || id <= 0) {
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
          result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?t=" + type + "&id="
              + id);
          // result.put("image", ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
          // + userMakerBrandPojo.getLogo());

          result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
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
            // result.put("content", "");
            result.put("content", StringUtil.checkVal(userMakerThemePojo.getSketch()));
          }

          result.put(
              "title",
              userMakerThemePojo.getSpecialName() == null ? "" : userMakerThemePojo
                  .getSpecialName());
          result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?type=2&t=" + type
              + "&id=" + id);
          // result.put("image", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
          // + userMakerThemePojo.getBanner());

          result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
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
            // result.put("content", "");
            result.put("content", StringUtil.checkVal(userCirclePostPojo.getSketch()));
          }

          result.put("title",
              userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
          result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?type=3&t=" + type
              + "&id=" + id);
          // result.put("image", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
          // + userCirclePostPojo.getBanner());

          result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
          b = true;
        }
      } else if (type == 4) {
        // if (ageType == null || ageType <= 0) {
        // msg = "年龄类型Id不能为空哦！~";
        // } else if (skillType == null || skillType <= 0) {
        // msg = "能力类型Id不能为空哦！~";
        // } else {
        // params.clear();
        // params.put("status", 1);
        // params.put("orderBy", "update_date desc,create_date desc");
        // params.put("ageType", ageType);
        // params.put("skillType", skillType);
        // KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);

        KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.getById(id);
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
          result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?t=" + type + "&id="
              + id);
          // result.put("image", ConstParam.URL + "/upfiles/knowledgeBase" + File.separator
          // + knowledgeBasePojo.getSmallIcon());

          result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
          b = true;
          // }
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
            // result.put("content", "");
            result.put("content", StringUtil.checkVal(platformSpecialPojo.getSketch()));
          }

          result.put("title",
              platformSpecialPojo.getTitle() == null ? "" : platformSpecialPojo.getTitle());
          result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?type=1&t=" + type
              + "&id=" + id);
          // result.put("image", ConstParam.URL + "/upfiles/platformSpecial" + File.separator
          // + platformSpecialPojo.getBanner());

          result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
          b = true;
        }
      } else if (type == 6) {
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
            result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?t=" + type + "&id="
                + id);
            // result.put("image", ConstParam.URL + "/upfiles/growthLine/growthLineLogo.jpg");

            result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
            b = true;
          }
        } else {
          msg = "查询不到宝宝信息!";
        }

      } else if (type == 7) {
        MicroPagePojo microPagePojo = microPageService.getById(id);
        if (microPagePojo != null) {
          result.put("content", "");
          result.put("title", microPagePojo.getTitle() == null ? "" : microPagePojo.getTitle());
          result.put("url", ConstParam.URL + "/v3.1/getShareContentWebApi.do?type=4&t=" + type
              + "&id=" + id);
          result.put("image", ConstParam.URL + "/upfiles/microPage/microPageLogo.jpg");
          b = true;
        }
      } else if (type == 8) {// 商品详情页
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null) {
          ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());
          if (productPojo != null) {
            result.put("content", StringUtil.checkVal(productPojo.getProductSketch()));
            result.put("title", StringUtil.checkVal(grouponActivityPojo.getPrice()) + "元的"
                + StringUtil.checkVal(productPojo.getProductName()));
            result.put("url", ConstParam.WX_URL2 + "/groupon.php?id=" + id + "&pdkUid=" + pdkUid);
            result.put(
                "image",
                ConstParam.URL + "/upfiles/product/"
                    + StringUtil.checkVal(productPojo.getImageSmall())
                    + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
            b = true;
          }
        }
      } else if (type == 9) {// 邀请参团团详情
        GrouponActivityRecordPojo grouponActivityRecordPojo =
            grouponActivityRecordService.getById(id);
        if (grouponActivityRecordPojo != null) {
          GrouponActivityPojo grouponActivityPojo =
              grouponActivityService.getById(grouponActivityRecordPojo.getActivityId());
          if (grouponActivityPojo != null) {
            ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());
            if (productPojo != null) {
              result.put("content", StringUtil.checkVal(productPojo.getProductSketch()));
              result.put("title", "我买了" + StringUtil.checkVal(grouponActivityPojo.getPrice())
                  + "元的" + StringUtil.checkVal(productPojo.getProductName()) + "");
              result.put("url", ConstParam.WX_URL2 + "/groupon_join.php?aid=" + id + "&pdkUid="
                  + grouponActivityRecordPojo.getPdkUid());
              result.put(
                  "image",
                  ConstParam.URL + "/upfiles/product/"
                      + StringUtil.checkVal(productPojo.getImageSmall())
                      + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
              b = true;
            }
          }
        }
      } else if (type == 10) {// 猜价格列表（id默认传11）
        result.put("content", "全新品，猜价得，猜中立得好礼");
        result.put("title", "猜价免费赢奖品");
        result.put("url", ConstParam.WX_URL2 + "/product_guess_price.php");
        // params.clear();
        // params.put("status", 1);
        // params.put("type", 2);
        // params.put("pageNo", 0);
        // params.put("pageSize", 1);
        // params.put("nowTimeStr", StringUtil.dateString(new Date()));
        // params.put("orderBy", "sorting desc, create_date desc");
        // List<FocusSettingPojo> list = focusSettingService.listPage(params);
        // if (list != null && list.size() > 0) {
        // FocusSettingPojo focusSettingPojo = list.get(0);
        // result.put(
        // "image",
        // ConstParam.URL + "/upfiles/focusbanner" + File.separator
        // + StringUtil.checkVal(focusSettingPojo.getBanner()));
        // } else {
        result.put("image", ConstParam.URL + "/upfiles/microPage/product_guess_price.jpg");
        // }
        b = true;
      } else if (type == 11) {// 猜价格详情
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null) {
          ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());// pid
          if (productPojo != null) {
            result.put("content", StringUtil.checkVal(productPojo.getProductSketch()));
            result.put("title", "邀请您一起参加猜价赢奖品" + StringUtil.checkVal(productPojo.getProductName())
                + "");
            result.put("url", ConstParam.WX_URL2 + "/product_guess_price.php?act=detail&gid=" + id
                + "&pid=" + productPojo.getId());// pid
            result.put(
                "image",
                ConstParam.URL + "/upfiles/product/"
                    + StringUtil.checkVal(productPojo.getImageSmall())
                    + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
            b = true;
          }
        }
      } else if (type == 12) {// 专题分类
        result.put("content", "【拼得好】辞旧迎新春 欢乐春节购物季  囤货购礼 有范出行！");
        SpecialTypePojo specialTypePojo = specialTypeService.getById(id);
        if (specialTypePojo != null) {
          result.put("title", StringUtil.checkVal(specialTypePojo.getName()));
        } else {
          result.put("title", "专题分类");
        }
        result.put("url", ConstParam.WX_URL2 + "/specials.php");
        result.put("image", ConstParam.URL + "/upfiles/microPage/wxLOGO.png");
        b = true;
      } else if (type == 13) {// 专题
        SpecialPojo specialPojo = specialService.getById(id);
        if (specialPojo != null) {
          // result.put("content", "缤纷专题好货，点击拼手速立抢！");
          result.put("content", StringUtil.checkString(specialPojo.getShareDesc()));
          result.put("title", StringUtil.checkVal(specialPojo.getTitle()));
          result.put("url", ConstParam.WX_URL2 + "/special.php?id=" + id);
          result.put(
              "image",
              ConstParam.URL + "/upfiles/special" + File.separator
                  + StringUtil.checkString(specialPojo.getIcon()));
          b = true;
        }
      } else if (type == 14) {// 7.7专区
        result.put("content", "只要9.9元，没有更低，只有最低");
        result.put("title", "9.9专区");
        result.put("url", ConstParam.WX_URL2 + "/special_77.php");
        result.put("image", ConstParam.URL + "/upfiles/microPage/77logo.png");

        params.clear();
        params.put("type", 1);
        params.put("status", 1);
        params.put("isDelete", 0);
        params.put("orderBy", " id desc");
        params.put("pageNo", 0);
        params.put("pageSize", 1);
        List<ZonesPojo> list = zonesService.listPage(params);
        if (list != null && list.size() > 0) {
          ZonesPojo zonesPojo = list.get(0);
          if (zonesPojo != null) {
            result.put("title", StringUtil.checkVal(zonesPojo.getTitle()));
            result.put("content", StringUtil.checkVal(zonesPojo.getShareDesc()));
            result.put("image",
                ConstParam.URL + "/upfiles/zones/" + StringUtil.checkVal(zonesPojo.getIcon()));
          }
        }
        b = true;
      } else if (type == 15) {// 掌上秒杀列表
        result.put("content", "拼出来的乐趣，秒出来的低价");
        result.put("title", "掌上秒杀");
        result.put("url", ConstParam.WX_URL2 + "/seckill.php");
        result.put("image", ConstParam.URL + "/upfiles/microPage/seckill.jpg");
        b = true;
      } else if (type == 16) {// 掌上秒杀（开枪中）
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null) {
          ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());// pid
          if (productPojo != null) {
            result.put("content", StringUtil.checkVal(grouponActivityPojo.getProductSketch()));
            result.put("title", StringUtil.checkVal(grouponActivityPojo.getPrice()) + "元的"
                + StringUtil.checkVal(productPojo.getProductName()));
            result.put("url", ConstParam.WX_URL2 + "/groupon.php?id=" + id);
            result.put(
                "image",
                ConstParam.URL + "/upfiles/product/"
                    + StringUtil.checkVal(productPojo.getImageSmall())
                    + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
          }
        }
        b = true;
      } else if (type == 17) {// 掌上秒杀（即将开始）
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null) {
          ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());// pid
          if (productPojo != null) {
            result.put("content", StringUtil.checkVal(grouponActivityPojo.getProductSketch()));
            result.put("title", StringUtil.checkVal(grouponActivityPojo.getPrice()) + "元的"
                + StringUtil.checkVal(productPojo.getProductName()));
            result.put("url", ConstParam.WX_URL2 + "/groupon.php?id=" + id);
            result.put(
                "image",
                ConstParam.URL + "/upfiles/product/"
                    + StringUtil.checkVal(productPojo.getImageSmall())
                    + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
            b = true;
          }
        }
      } else if (type == 18) {// 0.1秒杀首页
        result.put("content", "0.1商品包邮任性送，机不可失，还等什么？戳我即可领取~");
        result.put("title", "便宜无底限");
        result.put("url", ConstParam.WX_URL2 + "/lottery_new.php");
        result.put("image", ConstParam.URL + "/upfiles/microPage/0.1.jpg");
        b = true;
      } else if (type == 19) {// 0.1秒杀详情页
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null) {
          ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());// pid
          if (productPojo != null) {
            result.put("content",
                "我已抢到0.1特价秒杀团，" + StringUtil.checkVal(productPojo.getProductName()));
            result.put("title", StringUtil.checkVal(productPojo.getProductName()));
            result.put("url", ConstParam.WX_URL2 + "/groupon.php?id=" + id);
            result.put(
                "image",
                ConstParam.URL + "/upfiles/product/"
                    + StringUtil.checkVal(productPojo.getImageSmall())
                    + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
            b = true;
          }
        }
      } else if (type == 20) {// 新品专区
        result.put("content", "【拼得好】年度爆款，低价不停，好礼不断！");
        result.put("title", "新品专区");
        result.put("url", ConstParam.WX_URL2 + "/newspecial.php");
        result.put("image", ConstParam.URL + "/upfiles/microPage/newspecial.jpg");

        params.clear();
        params.put("type", 2);
        params.put("status", 1);
        params.put("isDelete", 0);
        params.put("orderBy", " id desc");
        params.put("pageNo", 0);
        params.put("pageSize", 1);
        List<ZonesPojo> list = zonesService.listPage(params);
        if (list != null && list.size() > 0) {
          ZonesPojo zonesPojo = list.get(0);
          if (zonesPojo != null) {
            result.put("content", StringUtil.checkVal(zonesPojo.getShareDesc()));
            result.put("image",
                ConstParam.URL + "/upfiles/zones/" + StringUtil.checkVal(zonesPojo.getIcon()));
          }
        }
        b = true;
      } else if (type == 21) {// 免费体验（免费抽奖 ）列表
        result.put("content", "我参与了拼得好抽奖活动！有机会获得各种礼品，快来和我一起玩吧！");
        result.put("title", "免费体验");
        result.put("url", ConstParam.WX_URL2 + "/freedraw.php");
        result.put("image", ConstParam.URL + "/upfiles/microPage/freedraw.jpg");
        b = true;
      } else if (type == 22) {// 免费体验（免费抽奖） 商品详情
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null) {
          ProductPojo productPojo = productService.getById(grouponActivityPojo.getProductId());// pid
          if (productPojo != null) {
            result.put("content", StringUtil.checkVal(productPojo.getProductSketch()));
            result.put("title", "【免费体验】0元，" + StringUtil.checkVal(productPojo.getProductName()));
            result.put("url", ConstParam.WX_URL2 + "/groupon.php?id=" + id);// ?
            result.put(
                "image",
                ConstParam.URL + "/upfiles/product/"
                    + StringUtil.checkVal(productPojo.getImageSmall())
                    + "?x-oss-process=image/watermark,image_c2hhcmVsb2dvLnBuZw==,g_north");
            b = true;
          }
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
    if (t == null || t <= 0) {
      FileUtil.alertMessage("类型不能为空哦！~");
      // } else if (type != 4 && (id == null || id <= 0)) {
    } else if (id == null || id <= 0) {
      FileUtil.alertMessage("ID不能为空哦！~");
    } else {
      if (t == 1) {
        UserMakerBrandPojo userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(id);
        if (userMakerBrandPojo != null) {
          content = userMakerBrandPojo.getContent();
          title = userMakerBrandPojo.getBrandName();
          imgurl =
              ConstParam.URL + "/upfiles/userMakerBrand" + File.separator
                  + userMakerBrandPojo.getLogo();
        }
      } else if (t == 2) {
        userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
        if (userMakerThemePojo != null) {
          if (userMakerThemePojo.getVersion() != null && userMakerThemePojo.getVersion() == 0) {
            content = userMakerThemePojo.getContent();
            title = userMakerThemePojo.getSpecialName();
            imgurl =
                ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
                    + userMakerThemePojo.getBanner();

            return SUCCESS;
          } else {
            title = userMakerThemePojo.getSpecialName();

            return "newpage";
          }
        }
      } else if (t == 3) {
        userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
        if (userCirclePostPojo != null) {
          if (userCirclePostPojo.getVersion() != null && userCirclePostPojo.getVersion() == 0) {
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

            return SUCCESS;
          } else {
            title = userCirclePostPojo.getTitle();

            return "newpage";
          }
        }
      } else if (t == 4) {
        // if (ageType == null || ageType <= 0) {
        // FileUtil.alertMessage("年龄类型Id不能为空哦！~");
        // } else if (skillType == null || skillType <= 0) {
        // FileUtil.alertMessage("能力类型Id不能为空哦！~");
        // } else {
        // params.clear();
        // params.put("status", 1);
        // params.put("orderBy", "update_date desc,create_date desc");
        // params.put("ageType", ageType);
        // params.put("skillType", skillType);
        // KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);

        KnowledgeBasePojo knowledgeBasePojo = knowledgeBaseService.getById(id);
        if (knowledgeBasePojo != null) {
          content = knowledgeBasePojo.getContent();
          title = knowledgeBasePojo.getTitle();

          imgurl =
              ConstParam.URL + "/upfiles/knowledgeBase" + File.separator
                  + knowledgeBasePojo.getSmallIcon();
        }
        // }
      } else if (t == 5) {
        int idint = id.intValue();
        platformSpecialPojo = platformSpecialService.getById(idint);
        if (platformSpecialPojo != null) {
          if (platformSpecialPojo.getVersion() != null && platformSpecialPojo.getVersion() == 0) {
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

            return SUCCESS;
          } else {
            title = platformSpecialPojo.getTitle();

            return "newpage";
          }
        }
      } else if (t == 6) {
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
      } else {
        microPagePojo = microPageService.getById(id);
        if (microPagePojo != null) {
          title = microPagePojo.getTitle();

          return "newpage";
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
    if (userBabyPojo != null) {
      return userBabyPojo.getId();
    }
    return null;
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
   * 
   * 首页banner接口
   * 
   * @return String
   * @throws SQLException
   */
  public String homeBannerApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = null;
    List<Map<String, Object>> bannerList = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    UserMakerShopPojo userMakerShopPojo;
    option.put("type", 9);
    option.put("status", 1);
    List<PagePushPojo> brandbanner = pagePushService.findAdByTypeApi(option);
    if (brandbanner.size() != 0) {
      for (PagePushPojo pagePushPojo : brandbanner) {
        result = new HashMap<String, Object>();
        result.put("banner",
            pagePushPojo.getImages() == null || "".equals(pagePushPojo.getImages()) ? ""
                : ConstParam.URL + "/upfiles/notice" + File.separator + pagePushPojo.getImages());
        result.put("type", pagePushPojo.getUrl() == null ? "" : pagePushPojo.getUrl().toString());
        if (!pagePushPojo.getUrl().equals("2")) {
          result.put("firstId", pagePushPojo.getTitle() == null ? "" : pagePushPojo.getTitle()
              .toString());
        }
        if (pagePushPojo.getUrl().equals("1")) {
          result.put("secondId", "");
          result.put("title", "创客首页");
        } else if (pagePushPojo.getUrl().equals("2")) {
          result.put("title", "创客店铺详情");
          userMakerShopPojo =
              userMakerShopService.findUserMakerShopById(Long.valueOf(pagePushPojo.getTitle()));
          if (userMakerShopPojo != null) {
            result.put("firstId", userMakerShopPojo.getShopId() == null ? "" : userMakerShopPojo
                .getShopId().toString());
            result.put("secondId", userMakerShopPojo.getUserId() == null ? "" : userMakerShopPojo
                .getUserId().toString());
          } else {
            result.put("secondId", "");
            result.put("firstId", "");
          }
        } else if (pagePushPojo.getUrl().equals("3")) {
          UserMakerThemePojo userMakerThemePojo =
              userMakerThemeService.getUserMakerThemeById(Long.valueOf(pagePushPojo.getTitle()));
          if (userMakerThemePojo != null) {
            result.put("secondId", userMakerThemePojo.getUserId());
          } else {
            result.put("secondId", "");
          }
          result.put("title", "创客专题详情");
        } else if (pagePushPojo.getUrl().equals("4")) {
          result.put("secondId", "");
          result.put("title", "平台专题详情");
        } else if (pagePushPojo.getUrl().equals("5")) {
          result.put("secondId", "");
          result.put("title", "平台专题详情");
        }
        bannerList.add(result);
      }
      b = true;
    } else {
      msg = "查询不到首页推送图!";
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
   * 首页焦点图
   * 
   * @throws SQLException
   * */
  public String focus() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    // 根据类型查询首页焦点图
    List<PagePushPojo> list2 = pagePushService.findTopThreeDate(8);
    String type = "";
    Long id = 0L;
    for (PagePushPojo pagePushPojo : list2) {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("image",
          ConstParam.URL + "/upfiles/notice" + File.separator + pagePushPojo.getImages());
      map1.put("id", pagePushPojo.getTitle());
      map1.put("subType", pagePushPojo.getUrl());
      type = pagePushPojo.getUrl();
      // 1-产品，2-店铺，3-列表，4-文字 5-web活动页
      if ("2".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        ActivityTimePojo activity = activityTimeService.findActivityTimeById(id);
        if (activity != null && StringUtils.isNotBlank(activity.getTitle())) {
          map1.put("title", activity.getTitle());
        } else {
          map1.put("title", "");
        }
      } else if ("3".equals(type) || "5".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        ActivityTitlePojo activity = activityTitleService.findActivityTitleById(id);
        if (activity != null && StringUtils.isNotBlank(activity.getTitle())) {
          map1.put("title", activity.getTitle());
        } else {
          map1.put("title", "");
        }
      } else if ("4".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        InfoPojo info = infoService.findInfoById(id);
        if (info != null && StringUtils.isNotBlank(info.getTitle())) {
          map1.put("title", info.getTitle());
        } else {
          map1.put("title", "");
        }
      } else {
        map1.put("title", "");
      }
      list.add(map1);
      b = true;
    }
    map.put("result", list);
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
