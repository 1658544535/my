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

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.util.UtilDate;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.AgeSkillLinkPojo;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TaskSettingPojo;
import com.tzmb2c.web.pojo.UserBabyPojo;
import com.tzmb2c.web.pojo.UserCalendarPojo;
import com.tzmb2c.web.pojo.UserCircleFollowPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.pojo.UserPostCommentPojo;
import com.tzmb2c.web.pojo.UserTalentAuthPojo;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.AgeSkillLinkService;
import com.tzmb2c.web.service.KnowledgeBaseService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.TaskSettingService;
import com.tzmb2c.web.service.UserBabyService;
import com.tzmb2c.web.service.UserCalendarService;
import com.tzmb2c.web.service.UserCircleFollowService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.UserGrowthLineService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserMakerBrandService;
import com.tzmb2c.web.service.UserMakerThemeService;
import com.tzmb2c.web.service.UserPostCollectService;
import com.tzmb2c.web.service.UserPostCommentService;
import com.tzmb2c.web.service.UserPostLikeHistoryService;
import com.tzmb2c.web.service.UserTalentAuthService;

public class AppApiPushGoodsAction extends SuperAction {
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

  // ---- getter and setter ---- //

  public Long getAgeType() {
    return ageType;
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


  // ------ 首页 ------ //
  /**
   * 首页-竹马头条
   * 
   * @return
   * @throws SQLException
   */
  public String headlineApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    if (ageType == null || ageType == 0) {
      ageType = 1l;
    }
    // 分页
    option.put("pageNo", 0);
    option.put("pageSize", 2);

    option.put("ageType", ageType);
    option.put("orderBy", "update_date desc,create_date desc");
    List<KnowledgeBasePojo> list = knowledgeBaseService.listPage(option);
    int i = 1;
    if (list.size() != 0) {
      for (KnowledgeBasePojo knowledgeBasePojo : list) {
        item = new HashMap<String, Object>();
        item.put("title", knowledgeBasePojo.getTitle() == null ? "" : knowledgeBasePojo.getTitle());
        item.put("id", knowledgeBasePojo.getId().toString());
        item.put("ageType", knowledgeBasePojo.getAgeType() == null ? "1" : knowledgeBasePojo
            .getAgeType().toString());
        item.put("skillType", knowledgeBasePojo.getSkillType() == null ? "1" : knowledgeBasePojo
            .getSkillType().toString());
        if (i == 1) {
          item.put("hot", "精选");
        } else {
          item.put("hot", "精选");
        }
        result.add(item);
        i++;
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
   * 首页推送商品
   * 
   * @return
   * @throws SQLException
   */
  public String pushGoodsApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    // 分页
    params.put("pageNo", 0);
    params.put("pageSize", 3);
    params.put("type", 21);
    params.put("productType", 2);
    List<ActivityProductPojo> ActivityProductList =
        activityProductService.findActivityGoods(params);
    if (ActivityProductList.size() != 0) {
      for (ActivityProductPojo activityProduct : ActivityProductList) {
        item = new HashMap<String, Object>();
        item.put(
            "productId",
            activityProduct.getProductId() == null ? "" : String.valueOf(activityProduct
                .getProductId()));
        item.put(
            "activityId",
            activityProduct.getActivityId() == null ? "" : String.valueOf(activityProduct
                .getActivityId()));
        item.put("productImg", activityProduct.getProductImg() == null ? "" : ConstParam.URL
            + "/upfiles/product" + File.separator + activityProduct.getProductImg());
        item.put("productName",
            activityProduct.getProductName() == null ? "" : activityProduct.getProductName());
        item.put(
            "productPrice",
            activityProduct.getActivePrice() == null ? "" : df.format(activityProduct
                .getActivePrice()));
        item.put("sellPrice",
            activityProduct.getSellPrice() == null ? "" : df.format(activityProduct.getSellPrice()));
        result.add(item);
      }
      b = true;
    } else {
      msg = "查询不到商品";
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
   * 今日特卖商品
   * 
   * @return
   * @throws SQLException
   */
  public String toDaySaleApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (type == null || type.equals("")) {
      msg = "分类id不能为空!";
    } else {
      // params.put("pageNo", 0);
      // params.put("pageSize", 5);
      params.put("type", 21);
      params.put("productType", 1);
      params.put("categoryId", type);
      List<ActivityProductPojo> ActivityProductList =
          activityProductService.findActivityGoods(params);
      if (ActivityProductList.size() != 0) {
        for (ActivityProductPojo activityProduct : ActivityProductList) {
          item = new HashMap<String, Object>();
          item.put("productId",
              activityProduct.getProductId() == null ? "" : activityProduct.getProductId());
          item.put("activityId",
              activityProduct.getActivityId() == null ? "" : activityProduct.getActivityId());
          item.put("productImg", activityProduct.getProductImg() == null ? "" : ConstParam.URL
              + "/upfiles/product" + File.separator + activityProduct.getProductImg());
          item.put("productName",
              activityProduct.getProductName() == null ? "" : activityProduct.getProductName());
          item.put(
              "productSellPrice",
              activityProduct.getSellPrice() == null ? "" : df.format(activityProduct
                  .getSellPrice()));
          item.put(
              "productPrice",
              activityProduct.getActivePrice() == null ? "" : df.format(activityProduct
                  .getActivePrice()));
          result.add(item);
        }
        b = true;
      } else {
        msg = "查询不到商品";
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
    }
    /*
     * else if (uid == null) { error_msg = "用户id不能为空!"; }
     */
    else {
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
      params.put("ids", 2542);
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
        item.put("collectNum",
            userCirclePostPojo.getCollectNum() == null ? 0 : userCirclePostPojo.getCollectNum());
        item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());

        item.put("authorSex",
            userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
        item.put("authorFansNum",
            sellerService.getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
        item.put("authorCollectNum",
            sellerService.getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));

        if (uid == null || uid <= 0) {
          item.put("postCollect", 0);
          item.put("authorFollow", 0);
        } else {
          // 是否关注作者
          if (sellerService.getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
          // 查询是否收藏
          if (sellerService.getUserCollectInfoCount(userCirclePostPojo.getId(),
              userCirclePostPojo.getUserId(), 2, uid) == 0) {
            item.put("postCollect", 0);
          } else {
            item.put("postCollect", 1);
          }
        }
        result.add(item);
      }
    } else {
      msg = "查询不到数据!";
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
          post.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, cpost.getUserId(), 1));
          post.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(cpost.getUserId(), null, 1));

          if (uid == null || uid == 0) {
            post.put("authorFollow", 0);
            post.put("postCollect", 0);
          } else {
            // 查询是否关注
            if (sellerService.getUserFollowInfoCount(uid, cpost.getUserId(), 1) == 0) {
              post.put("authorFollow", 0);
            } else {
              post.put("authorFollow", 1);
            }
            // 查询是否收藏
            if (sellerService.getUserCollectInfoCount(cpost.getId(), cpost.getUserId(), 2, uid) == 0) {
              post.put("postCollect", 0);
            } else {
              post.put("postCollect", 1);
            }
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
    }
    /*
     * else if (uid == null || uid == 0) { msg = "用户ID不能为空!"; }
     */
    else {
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
          post.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, cpost.getUserId(), 1));
          post.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(cpost.getUserId(), null, 1));
          // 查询是否关注和是否收藏
          if (uid == null || uid == 0) {
            post.put("authorFollow", 0);
            post.put("postCollect", 0);
          } else {
            if (sellerService.getUserFollowInfoCount(uid, cpost.getUserId(), 1) == 0) {
              post.put("authorFollow", 0);
            } else {
              post.put("authorFollow", 1);
            }
            if (sellerService.getUserCollectInfoCount(cpost.getId(), cpost.getUserId(), 2, uid) == 0) {
              post.put("postCollect", 0);
            } else {
              post.put("postCollect", 1);
            }
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
    if (keyword == null || "".equals(keyword)) {
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
          item.put("collectNum", u.getCollectNum() == null ? "0" : u.getCollectNum().toString());
          item.put("authorId", u.getUserId().toString());
          item.put("authorLogo",
              u.getUserImage() == null ? "" : ConstParam.URL + "/upfiles/userlogo" + File.separator
                  + (u.getUserImage() == null ? "" : u.getUserImage()));
          item.put("authorName", u.getUserName() == null ? "" : u.getUserName());
          item.put("authorSex", u.getUserSex() == null ? "1" : u.getUserSex() + "");
          // 查询是否关注和是否收藏
          if (uid == null || uid == 0) {
            item.put("authorFollow", "0");
            item.put("postCollect", "0");
          } else {
            if (sellerService.getUserFollowInfoCount(uid, u.getUserId(), 1) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
            if (sellerService.getUserCollectInfoCount(u.getId(), u.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
          }
          item.put("authorFansNum", sellerService.getUserFollowInfoCount(null, u.getUserId(), 1)
              + "");
          item.put("authorCollectNum", sellerService.getUserFollowInfoCount(u.getUserId(), null, 1)
              + "");
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
    if (keyword == null || "".equals(keyword)) {
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
    /*
     * if (uid == null || uid == 0) { msg = "用户ID不能为空哦！~"; } else
     */
    if (keyword == null || "".equals(keyword)) {
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
          // 查询是否关注
          if (uid == null || uid == 0) {
            item.put("isConcern", "0");
          } else {
            if (u.getFollowNum() > 0) {
              item.put("isConcern", "1");
            } else {
              item.put("isConcern", "0");
            }
          }

          UserInfoPojo userInfoPojo = userInfoService.findUserInfoByUserId(u.getUserId());
          if (userInfoPojo != null) {
            // 查询年龄
            int age = SellerService.getBabyAgeType(userInfoPojo.getBabyBirthday());
            item.put("age", age + "");
          } else {
            item.put("age", "1");
          }

          item.put("circleNum", sellerService.getUserFollowInfoCount(u.getUserId(), null, 2) + "");
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

          // 查询是否关注和是否收藏
          if (uid == null || uid <= 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            if (sellerService.getUserCollectInfoCount(userCirclePostPojo.getId(),
                userCirclePostPojo.getUserId(), 2, uid) > 0) {
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

          item.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1) + "");

          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
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

        // 查询是否关注
        option.clear();
        option.put("typeId", userMakerBrandPojo.getUserId());
        option.put("type", 1);
        option.put("userId", uid);
        option.put("isFollow", 1);
        int is_follow = userCircleFollowService.userCircleFollowCount(option);
        if (uid == null || uid == 0) {
          is_follow = 0;
        } else {
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

    // 判断用户是否登录
    if (uid != null) {
      option.put("uid", uid);
      option.put("isFollow", 0);
    }
    List<SocialCirclePojo> socialCircleList = socialCircleService.findSocialCircleList(option);
    if (socialCircleList != null && socialCircleList.size() != 0) {
      if (socialCircleList.size() < 10) {
        rslist = sellerService.randomList(socialCircleList, socialCircleList.size());
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
            if (uid == null) {
              item.put("isFollow", 0);
            } else {
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
            }
            socialCircleArrayList.add(item);
          }
          success = true;
        }
      } else {
        rslist = sellerService.randomList(socialCircleList, 10);
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
            if (uid == null) {
              item.put("isFollow", 0);
            } else {
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
          params = new HashMap<String, Object>();
          params.put("userId", uid);
          params.put("type", 1);
          params.put("typeId", ftId);
          params.put("isFollow", 1);
          int i = userCircleFollowService.userCircleFollowCount(params);
          userInfo.put("isConcern", i > 0 ? 1 : 0);
        }
        userInfo.put("userName", userInfoPojo.getUserName());
        userInfo.put("userId", userInfoPojo.getUserId());
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
    // 没登陆时默认查找ageType等于1的能力
    if (uid == null || uid == 0) {
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
    /*
     * if (uid == null || uid == 0) { error_msg = "用户id不能为空!"; } else
     */
    if (talentId == null || talentId == 0) {
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
          // 判断达人关注圈子,用户是否关注
          if (uid == null || uid == 0) {
            item.put("isConcern", 0);
          } else {
            option = new HashMap<String, Object>();
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
          item.put("authorSex", userCirclePostPojo.getUserSex() == null ? "1" : userCirclePostPojo
              .getUserSex().toString());
          item.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1) + "");
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
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
    /*
     * if (uid == null || uid == 0) { error_msg = "用户id不能为空!"; } else
     */
    if (circleTypeId == null || circleTypeId == 0) {
      error_msg = "社圈分类id不能为空!";
    } else {
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
          if (uid == null || uid == 0) {
            item.put("isConcern", 0);
          } else {
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
          item.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1) + "");
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1) + "");
          // 查询是否关注和是否收藏
          if (uid == null || uid <= 0) {
            item.put("authorFollow", "0");
            item.put("postCollect", "0");
          } else {
            if (sellerService.getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
            if (sellerService.getUserCollectInfoCount(userCirclePostPojo.getId(),
                userCirclePostPojo.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
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
        }
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
      params.put("isDelete", 0);
      params.put("taskDateStr", StringUtil.dateToString(new Date()));
      params.put("age", 1);
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
    if (uid == null) {
      uid = 0l;
    }
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
   * 精选宝典
   * 
   * @return
   * @throws SQLException
   */
  public String siftPostApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("pageNo", 0);
    option.put("pageSize", 4);
    option.put("orderBy", "u.sorting desc");
    if (ageType == null || ageType <= 0) {
      ageType = 1l;
    }
    option.put("ageType", ageType);
    List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(option);
    if (list.size() != 0) {
      for (UserCirclePostPojo userCirclePostPojo : list) {
        item = new HashMap<String, Object>();
        item.put("postId", String.valueOf(userCirclePostPojo.getId()));
        item.put("banner", ConstParam.URL + "/upfiles/userCirclePost" + File.separator
            + userCirclePostPojo.getBanner());
        item.put("postTitle",
            userCirclePostPojo.getTitle() == null ? "" : userCirclePostPojo.getTitle());
        item.put("authorName",
            userCirclePostPojo.getUserName() == null ? "" : userCirclePostPojo.getUserName());
        item.put(
            "authorId",
            userCirclePostPojo.getUserId() == null ? 0 : String.valueOf(userCirclePostPojo
                .getUserId()));
        item.put(
            "collectNum",
            userCirclePostPojo.getCollectNum() == null ? 0 : String.valueOf(userCirclePostPojo
                .getCollectNum()));
        item.put("authorLogo", userCirclePostPojo.getUserImage() == null ? "" : ConstParam.URL
            + "/upfiles/userlogo" + File.separator + userCirclePostPojo.getUserImage());

        item.put(
            "authorSex",
            userCirclePostPojo.getUserSex() == null ? "" : String.valueOf(userCirclePostPojo
                .getUserSex()));
        item.put(
            "authorFansNum",
            String.valueOf(sellerService.getUserFollowInfoCount(null,
                userCirclePostPojo.getUserId(), 1)));
        item.put("authorCollectNum", String.valueOf(sellerService.getUserFollowInfoCount(
            userCirclePostPojo.getUserId(), null, 1)));
        // 是否关注作者
        if (uid == null || uid <= 0) {
          item.put("authorFollow", "0");
          item.put("postCollect", 0);
        } else {
          if (sellerService.getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", "0");
          } else {
            item.put("authorFollow", "1");
          }
          // 查询是否收藏
          if (sellerService.getUserCollectInfoCount(userCirclePostPojo.getId(),
              userCirclePostPojo.getUserId(), 2, uid) == 0) {
            item.put("postCollect", "0");
          } else {
            item.put("postCollect", "1");
          }
        }
        result.add(item);
      }
    } else {
      msg = "查询不到数据!";
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
          // 查询是否收藏和是否关注
          if (uid == null || uid == 0) {
            item.put("authorFollow", 0);
            item.put("postCollect", 0);
          } else {
            if (sellerService.getUserCollectInfoCount(ucp.getId(), ucp.getUserId(), 2, uid) == 0) {
              item.put("postCollect", 0);
            } else {
              item.put("postCollect", 1);
            }
            if (sellerService.getUserFollowInfoCount(uid, ucp.getUserId(), 1) == 0) {
              item.put("authorFollow", 0);
            } else {
              item.put("authorFollow", 1);
            }
          }
          item.put("collectNum", ucp.getCollectNum() == null ? 0 : ucp.getCollectNum());
          item.put("authorId", ucp.getUserId());
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex());
          item.put("authorFansNum", sellerService.getUserFollowInfoCount(null, ucp.getUserId(), 1));
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(ucp.getUserId(), null, 1));
          userCirclePostList.add(item);
        }
        success = true;
      } else {
        error_msg = "还没有笔记~";
      }
    } else {
      error_msg = "达人id不能为空!";
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
    /*
     * if (uid == null || uid == 0) { error_msg = "用户id不能为空"; } else
     */
    if (socialCircleId == null || socialCircleId == 0) {
      error_msg = "社圈id不能为空!";
    } else {
      // 查找用户信息
      if (uid == null || uid == 0) {
        map3.put("userType", "");
      } else {
        SysLoginPojo sysLogin = sysLoginService.findSysLoginById(socialCircleId);
        map3.put("userType", sysLogin.getType());
      }
      // map3.put("type", 2);
      // 查询是否关注
      if (uid == null || uid == 0) {
        map3.put("isConcern", "0");
      } else {
        params2.put("userId", uid);
        params2.put("typeId", socialCircleId);
        params2.put("isFollow", 1);
        params2.put("type", 2);
        Integer i = userCircleFollowService.userCircleFollowCount(params2);
        if (i > 0) {
          map3.put("isConcern", "1");
        } else {
          map3.put("isConcern", "0");
        }
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
          // 查询是否关注和是否收藏
          if (uid == null || uid == 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            if (sellerService.getUserCollectInfoCount(ucp.getId(), ucp.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            if (sellerService.getUserFollowInfoCount(uid, ucp.getUserId(), 1) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex().toString());
          item.put("authorFansNum", sellerService.getUserFollowInfoCount(null, ucp.getUserId(), 1)
              + "");
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(ucp.getUserId(), null, 1) + "");
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
      if (pids.size() != 0) {
        params.put("pids", pids);
      } else {
        pids.add(0l);
      }
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
          // 查询是否关注和是否收藏
          if (uid == null || uid == 0) {
            item.put("postCollect", "0");
            item.put("authorFollow", "0");
          } else {
            if (sellerService.getUserCollectInfoCount(ucp.getId(), ucp.getUserId(), 2, uid) > 0) {
              item.put("postCollect", "1");
            } else {
              item.put("postCollect", "0");
            }
            if (sellerService.getUserFollowInfoCount(uid, ucp.getUserId(), 1) > 0) {
              item.put("authorFollow", "1");
            } else {
              item.put("authorFollow", "0");
            }
          }
          item.put("authorSex", ucp.getUserSex() == null ? "" : ucp.getUserSex().toString());
          item.put("authorFansNum", sellerService.getUserFollowInfoCount(null, ucp.getUserId(), 1)
              + "");
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(ucp.getUserId(), null, 1) + "");
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
      params.put("groupBy", "c.type_id");
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
          post.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, cpost.getAuthorId(), 1));
          post.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(cpost.getAuthorId(), null, 1));
          if (sellerService.getUserFollowInfoCount(uid, cpost.getAuthorId(), 1) == 0) {
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
    params.put("orderBy", "update_date desc,create_date desc");
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
    params.put("orderBy", "update_date desc,create_date desc");
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
        option.put("orderBy", "u.update_date desc,u.create_date desc");
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
          item.put("isLike", 0);
          item.put("authorFollow", 0);
          item.put("postCollect", 0);
        } else {
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
          // 查询是否收藏
          if (sellerService.getUserCollectInfoCount(userCirclePostPojo.getId(),
              userCirclePostPojo.getUserId(), 2, uid) == 0) {
            item.put("postCollect", 0);
          } else {
            item.put("postCollect", 1);
          }
          // 查询是否关注
          if (sellerService.getUserFollowInfoCount(uid, userCirclePostPojo.getUserId(), 1) == 0) {
            item.put("authorFollow", 0);
          } else {
            item.put("authorFollow", 1);
          }
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
        item.put("collectNum",
            userCirclePostPojo.getCollectNum() == null ? 0 : userCirclePostPojo.getCollectNum());
        item.put("commentNum",
            userCirclePostPojo.getCommentNum() == null ? 0 : userCirclePostPojo.getCommentNum());// 评论数
        item.put("likeNum",
            userCirclePostPojo.getLikeNum() == null ? 0 : userCirclePostPojo.getLikeNum());// 评论数
        item.put("authorId", userCirclePostPojo.getUserId());
        item.put("authorSex",
            userCirclePostPojo.getUserSex() == null ? "" : userCirclePostPojo.getUserSex());
        item.put("authorFansNum",
            sellerService.getUserFollowInfoCount(null, userCirclePostPojo.getUserId(), 1));
        item.put("authorCollectNum",
            sellerService.getUserFollowInfoCount(userCirclePostPojo.getUserId(), null, 1));
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
          item.put("authorFansNum",
              sellerService.getUserFollowInfoCount(null, sysLoginPojo.getId(), 1));
          item.put("authorCollectNum",
              sellerService.getUserFollowInfoCount(sysLoginPojo.getId(), null, 1));
          if (sellerService.getUserFollowInfoCount(uid, sysLoginPojo.getId(), 1) == 0) {
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
    params.put("orderBy", "id desc");
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
   * 首页创客主题
   * 
   * @return
   * @throws SQLException
   */
  public String getHomeUserMakerBrandApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> items = null;

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("pageNo", 0);
    option.put("pageSize", 1);
    option.put("isHomePage", 1);
    option.put("type", 1);
    List<UserMakerThemePojo> theme = userMakerThemeService.userMakerThemeList(option);
    if (theme.size() > 0) {
      UserMakerThemePojo userMakerThemePojo = theme.get(0);
      items = new HashMap<String, Object>();
      items.put("themeType", userMakerThemePojo.getType());
      items.put("themeId", userMakerThemePojo.getId());
      items.put("userId", userMakerThemePojo.getUserId());
      items.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
          + userMakerThemePojo.getBanner());
      result.add(items);
    }
    option.put("type", 2);
    theme = userMakerThemeService.userMakerThemeList(option);
    if (theme.size() > 0) {
      UserMakerThemePojo userMakerThemePojo = theme.get(0);
      items = new HashMap<String, Object>();
      items.put("themeType", userMakerThemePojo.getType());
      items.put("themeId", userMakerThemePojo.getId());
      items.put("userId", userMakerThemePojo.getUserId());
      items.put("banner", ConstParam.URL + "/upfiles/userMakerTheme" + File.separator
          + userMakerThemePojo.getBanner());
      result.add(items);
    }
    if (result.size() == 0) {
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
   * 首页-专题活动
   * 
   * @return
   * @throws SQLException
   */
  public String getHomePlatformSpecialApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> hotlist = new ArrayList<Map<String, Object>>();
    Map<String, Object> pfsMap = null;
    // 查询热门专题
    params.put("pageSize", 2);
    params.put("pageNo", 0);
    // params.put("type", 3);
    params.put("status", 1);
    params.put("isHomePage", 1);
    params.put("ids", 68);
    List<PlatformSpecialPojo> hotSpecialList = platformSpecialService.listPage(params);
    if (hotSpecialList.size() > 0) {
      for (PlatformSpecialPojo hs : hotSpecialList) {
        pfsMap = new HashMap<String, Object>();
        pfsMap.put("id", hs.getId() == null ? "" : String.valueOf(hs.getId()));
        pfsMap.put("banner", hs.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/platformSpecial" + File.separator + hs.getBanner());
        pfsMap.put("specialCategories",
            hs.getSpecialCategories() == null ? "" : String.valueOf(hs.getSpecialCategories()));
        hotlist.add(pfsMap);
      }
    } else {
      msg = "暂无数据!";
    }
    b = true;
    map.put("result", hotlist);
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
