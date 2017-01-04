package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.pojo.HotBrandRecommendPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.UserMakerShopPojo;
import com.tzmb2c.web.service.FocusSettingService;
import com.tzmb2c.web.service.HotBrandRecommendService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 推送综合管理
 * 
 * @author creazylee
 * 
 */
public class HotBrandRecommendAction extends SuperAction {

  private File upfile;
  private String upfileFileName;
  private String upfileContentType;
  private String[] tids;
  private String type;
  private String result;
  private int item;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private FocusSettingService focusSettingService;
  @Autowired
  private HotBrandRecommendService hotBrandRecommendService;
  private PagePushPojo pagePushPojo;
  private List<SysDictPojo> noticeSysDictList = null;
  private List<SysDictPojo> statusSysDictList = null;
  private FocusSettingPojo focusSettingPojo;
  private HotBrandRecommendPojo hotBrandRecommendPojo;
  private UserMakerShopPojo userMakerShopPojo;

  public UserMakerShopPojo getUserMakerShopPojo() {
    return userMakerShopPojo;
  }

  public void setUserMakerShopPojo(UserMakerShopPojo userMakerShopPojo) {
    this.userMakerShopPojo = userMakerShopPojo;
  }

  public FocusSettingPojo getFocusSettingPojo() {
    return focusSettingPojo;
  }

  public void setFocusSettingPojo(FocusSettingPojo focusSettingPojo) {
    this.focusSettingPojo = focusSettingPojo;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public List<SysDictPojo> getNoticeSysDictList() {
    return noticeSysDictList;
  }

  public void setNoticeSysDictList(List<SysDictPojo> noticeSysDictList) {
    this.noticeSysDictList = noticeSysDictList;
  }

  public PagePushPojo getPagePushPojo() {
    return pagePushPojo;
  }

  public void setPagePushPojo(PagePushPojo pagePushPojo) {
    this.pagePushPojo = pagePushPojo;
  }

  public PagePushService getPagePushService() {
    return pagePushService;
  }

  public void setPagePushService(PagePushService pagePushService) {
    this.pagePushService = pagePushService;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getUpfileContentType() {
    return upfileContentType;
  }

  public void setUpfileContentType(String upfileContentType) {
    this.upfileContentType = upfileContentType;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getItem() {
    return item;
  }

  public void setItem(int item) {
    this.item = item;
  }



  /***
   * 查找所有记录
   * 
   * @return
   */
  public String hotBrandRecommendAllList() {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "hbr.sorting desc, hbr.create_date desc");
    List<HotBrandRecommendPojo> hotBrandRecommendPojos = hotBrandRecommendService.listPage(map);
    JSONArray json = JSONArray.fromObject(hotBrandRecommendPojos);
    page.setResult(json.toString());
    return SUCCESS;

  }

  /***
   * 获取所有记录的数目
   * 
   * @return
   * @throws Exception
   */
  public String goHotBrandRecommend() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    page.setRowCount(hotBrandRecommendService.countBy(map));
    return SUCCESS;
  }


  /***
   * 添加单条
   * 
   * @return
   * @throws Throwable
   */
  public String selectHotBrandRecommend() throws Throwable {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("brandId", hotBrandRecommendPojo.getBrandId());
      int count = hotBrandRecommendService.countBy(map);
      if (count > 0) {
        FileUtil.alertMessageBySkip("该品牌已推荐！", "goSelectHotBrandRecommend.do");
        return null;
      }
      hotBrandRecommendPojo.setBrandId(hotBrandRecommendPojo.getBrandId());
      hotBrandRecommendPojo.setSorting(0);
      hotBrandRecommendPojo.setCreateDate(new Date());
      hotBrandRecommendPojo.setUpdateDate(new Date());
      hotBrandRecommendService.add(hotBrandRecommendPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "goHotBrandRecommend.do");
    return null;
  }

  /***
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delHotBrandRecommend() throws SQLException {
    try {
      hotBrandRecommendService.delete(hotBrandRecommendPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }


  /***
   * 查找单条
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateHotBrandRecommend() throws Exception {
    hotBrandRecommendPojo = hotBrandRecommendService.getById(hotBrandRecommendPojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条
   * 
   * @return
   * @throws Throwable
   */
  public String updateHotBrandRecommend() throws Throwable {
    hotBrandRecommendPojo.setUpdateDate(new Date());
    hotBrandRecommendService.update(hotBrandRecommendPojo);
    FileUtil.alertMessageBySkip("修改成功！", "goHotBrandRecommend.do");
    return null;
  }

  public HotBrandRecommendPojo getHotBrandRecommendPojo() {
    return hotBrandRecommendPojo;
  }

  public void setHotBrandRecommendPojo(HotBrandRecommendPojo hotBrandRecommendPojo) {
    this.hotBrandRecommendPojo = hotBrandRecommendPojo;
  }

}
