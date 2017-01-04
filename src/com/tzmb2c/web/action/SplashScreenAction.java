package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.SplashScreenPojo;
import com.tzmb2c.web.service.SplashScreenService;

public class SplashScreenAction extends SuperAction {
  @Autowired
  private SplashScreenService splashScreenService;
  private SplashScreenPojo splashScreenPojo;
  private List<SplashScreenPojo> splashScreenPojoList;
  private String[] tids;
  private File upfile1;
  private File upfile2;
  private File upfile3;

  public List<SplashScreenPojo> getSplashScreenPojoList() {
    return splashScreenPojoList;
  }

  public void setSplashScreenPojoList(List<SplashScreenPojo> splashScreenPojoList) {
    this.splashScreenPojoList = splashScreenPojoList;
  }

  public SplashScreenPojo getSplashScreenPojo() {
    return splashScreenPojo;
  }

  public void setSplashScreenPojo(SplashScreenPojo splashScreenPojo) {
    this.splashScreenPojo = splashScreenPojo;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public File getUpfile1() {
    return upfile1;
  }

  public void setUpfile1(File upfile1) {
    this.upfile1 = upfile1;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  /**
   * 查询全部条数
   */
  public String splashScreenCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (splashScreenPojo != null) {
        map.put("title", splashScreenPojo.getTitle());
        map.put("status", splashScreenPojo.getStatus());
      }
      int i = splashScreenService.findSplashScreenCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String splashScreenAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (splashScreenPojo != null) {
        map.put("title", splashScreenPojo.getTitle());
        map.put("status", splashScreenPojo.getStatus());
      }
      splashScreenPojoList = splashScreenService.findSplashScreenList(map);
      JSONArray json = JSONArray.fromObject(splashScreenPojoList);
      page.setRowCount(splashScreenPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 删除
   * 
   * @return
   */
  public String delSplashScreen() throws Exception {
    try {
      splashScreenService.delSplashScreen(splashScreenPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "splashScreen.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "splashScreen.do");
    }
    return null;
  }

  /**
   * 批量删除
   * 
   * @return
   */
  public String delSplashScreenAll() {
    try {
      for (String id : tids) {
        splashScreenService.delSplashScreen(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "splashScreen.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "splashScreen.do");
    }
    return null;
  }

  /**
   * 通过审核
   * 
   * @return
   */
  public String checkSplashScreen() throws SQLException {
    try {
      splashScreenService.checkSplashScreen(splashScreenPojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "splashScreen.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("通过审核失败！", "splashScreen.do");
    }
    return null;
  }

  /**
   * 取消审核
   * 
   * @return
   */
  public String uncheckSplashScreen() throws SQLException {
    try {
      splashScreenService.uncheckSplashScreen(splashScreenPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "splashScreen.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "splashScreen.do");
    }
    return null;
  }

  /**
   * 批量通过审核
   * 
   * @return
   */
  public String checkSplashScreenAll() {
    try {
      for (String id : tids) {
        splashScreenService.checkSplashScreen(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "splashScreen.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "splashScreen.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSplashScreen() throws Exception {
    splashScreenPojo = splashScreenService.findSplashScreenById(splashScreenPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String updateSplashScreen() throws Throwable {
    try {
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/splashScreen")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/splashScreen/", upfile1);
        splashScreenPojo.setImage1(file_name);
      } else {
        splashScreenPojo.setImage1("");
      }

      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/splashScreen")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/splashScreen/", upfile2);
        splashScreenPojo.setImage2(file_name);
      } else {
        splashScreenPojo.setImage2("");
      }

      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/splashScreen")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/splashScreen/", upfile3);
        splashScreenPojo.setImage3(file_name);
      } else {
        splashScreenPojo.setImage3("");
      }
      splashScreenService.updateSplashScreen(splashScreenPojo);
      FileUtil.alertMessageBySkip("提交成功！", "splashScreen.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "splashScreen.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddSplashScreen() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String addSplashScreen() throws Throwable {
    try {
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/splashScreen")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/splashScreen/", upfile1);
        splashScreenPojo.setImage1(file_name);
      } else {
        splashScreenPojo.setImage1("");
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/splashScreen")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/splashScreen/", upfile2);
        splashScreenPojo.setImage2(file_name);
      } else {
        splashScreenPojo.setImage2("");
      }

      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/splashScreen")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/splashScreen/", upfile3);
        splashScreenPojo.setImage3(file_name);
      } else {
        splashScreenPojo.setImage3("");
      }
      splashScreenService.insertSplashScreen(splashScreenPojo);
      FileUtil.alertMessageBySkip("新增成功！", "splashScreen.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "splashScreen.do");
    }
    return null;
  }
}
