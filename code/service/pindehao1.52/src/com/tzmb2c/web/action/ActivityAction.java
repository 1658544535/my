package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ActivityService;
import com.tzmb2c.web.service.SysDictService;


public class ActivityAction extends SuperAction {
  @Autowired
  private ActivityService activityService;
  @Autowired
  private SysDictService sysDictService;

  private ActivityPojo activityPojo;
  private File upfile2, upfile;
  private String fileName;
  private List<SysDictPojo> mainProductList = null;
  private String result;
  private List<SysDictPojo> typeSysDictList = null;


  public List<SysDictPojo> getTypeSysDictList() {
    return typeSysDictList;
  }

  public void setTypeSysDictList(List<SysDictPojo> typeSysDictList) {
    this.typeSysDictList = typeSysDictList;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ActivityPojo getActivityPojo() {
    return activityPojo;
  }

  public void setActivityPojo(ActivityPojo activityPojo) {
    this.activityPojo = activityPojo;
  }

  public List<SysDictPojo> getMainProductList() {
    return mainProductList;
  }

  public void setMainProductList(List<SysDictPojo> mainProductList) {
    this.mainProductList = mainProductList;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String activityAdd() {
    getList();
    return SUCCESS;
  }

  public String pushAppList() throws SQLException, DocumentException {
    getList();
    pushAppListCount();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (activityPojo != null) {
      map.put("mainCategory", activityPojo.getMainCategory());
      map.put("title", activityPojo.getTitle());
    }
    List<ActivityPojo> list = activityService.findAll(map);
    for (ActivityPojo p : list) {
      p.setImage(p.getImage());
    }
    JSONArray json = JSONArray.fromObject(list);
    int i = activityService.findAllCount(map);
    page.setRowCount(i);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String pushAppListCount() throws SQLException {
    getList();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (activityPojo != null) {
      map.put("mainCategory", activityPojo.getMainCategory());
      map.put("title", activityPojo.getTitle());
    }
    int i = activityService.findAllCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String uploadFile(File file, String s, String d) {
    try {
      FileInputStream fis = new FileInputStream(file);
      String path = ServletActionContext.getRequest().getRealPath(s);
      System.out.println(path);
      // Random random=new Random();
      // int i=random.nextInt(1000);
      File f = new File(path);
      if (!f.exists()) {
        f.mkdirs();
      }
      fileName = StringUtil.getCurrentDateStrByfu() + ".jpg";
      File fs = new File(path, fileName);
      FileOutputStream fos = new FileOutputStream(fs);
      int len = 0;
      byte[] buffer = new byte[1024];

      while ((len = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, len);
      }
      fos.flush();
      fos.close();
      fis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return fileName;
  }

  public String addActivity() throws SQLException {
    SysLoginPojo sysLoginPojo = findSessionUser();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("title", activityPojo.getTitle());
    map.put("type", activityPojo.getType());
    // map.put("type_id",activityPojo.getType_id());
    if (upfile2 != null) {
      String image = uploadFile(upfile2, "upfiles" + File.separator + "activity", "activity");
      map.put("image", image);
    }
    map.put("mainCategory", activityPojo.getMainCategory());
    map.put("typeId", activityPojo.getTypeId());
    map.put("create_by", sysLoginPojo.getId());
    activityService.insertActivity(map);
    FileUtil.alertMessageBySkip("提交成功！", "pushAppManage.do");
    return null;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      mainProductList = sysDictService.getSysDictListByType("product_category");
      typeSysDictList = sysDictService.getSysDictListByType("pushID_type");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delActivity() throws SQLException {
    try {
      activityService.delActivity(activityPojo);
      this.result = "1";
    } catch (Exception ex) {
      ex.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 查找单条
   * 
   * @return
   * @throws Exception
   */
  public String goFindActivity() throws Exception {
    getList();
    activityPojo = activityService.findActivityById(activityPojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条
   * 
   * @return
   * @throws Throwable
   */
  public String updateActivity() throws Throwable {
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/activity")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/activity/", upfile2);
      activityPojo.setImage(file_name);
    }
    activityService.updateActivity(activityPojo);
    FileUtil.alertMessageBySkip("修改成功！", "pushAppManage.do");
    return null;
  }



}
