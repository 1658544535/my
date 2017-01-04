package com.tzmb2c.web.action;

import java.io.File;
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
import com.tzmb2c.web.pojo.HomePageIconPojo;
import com.tzmb2c.web.service.HomePageIconService;

public class HomePageIconAction extends SuperAction {
  @Autowired
  private HomePageIconService homePageIconService;
  private HomePageIconPojo homePageIconPojo;
  private List<HomePageIconPojo> homePageIconPojoList;
  private File upfile;
  private String[] tids;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public List<HomePageIconPojo> getHomePageIconPojoList() {
    return homePageIconPojoList;
  }

  public void setHomePageIconPojoList(List<HomePageIconPojo> homePageIconPojoList) {
    this.homePageIconPojoList = homePageIconPojoList;
  }

  public HomePageIconPojo getHomePageIconPojo() {
    return homePageIconPojo;
  }

  public void setHomePageIconPojo(HomePageIconPojo homePageIconPojo) {
    this.homePageIconPojo = homePageIconPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 查询全部条数
   */
  public String homePageIconListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (homePageIconPojo != null) {
        map.put("title", homePageIconPojo.getTitle());
        map.put("sorting", homePageIconPojo.getSorting());
        map.put("status", homePageIconPojo.getStatus());
      }
      int i = homePageIconService.findHomePageIconCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String homePageIconListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (homePageIconPojo != null) {
        map.put("title", homePageIconPojo.getTitle());
        map.put("sorting", homePageIconPojo.getSorting());
        map.put("status", homePageIconPojo.getStatus());
      }
      homePageIconPojoList = homePageIconService.findHomePageIconList(map);
      JSONArray json = JSONArray.fromObject(homePageIconPojoList);
      page.setRowCount(homePageIconPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delHomePageIcon() throws Exception {
    try {
      homePageIconService.delHomePageIcon(homePageIconPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "homePageIconList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "homePageIconList.do");
    }
    return null;
  }

  /**
   * 批量审核
   * 
   * @return
   */
  public String delHomePageIconAll() {
    try {
      for (String id : tids) {
        homePageIconService.delHomePageIcon(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部刪除成功！", "homePageIconList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部刪除失败！", "homePageIconList.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkHomePageIcon() throws Exception {
    try {
      homePageIconService.checkHomePageIcon(homePageIconPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "homePageIconList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "homePageIconList.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckHomePageIcon() throws Exception {
    try {
      homePageIconService.uncheckHomePageIcon(homePageIconPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "homePageIconList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "homePageIconList.do");
    }
    return null;
  }

  /**
   * 批量审核
   * 
   * @return
   */
  public String checkHomePageIconAll() {
    try {
      for (String id : tids) {
        homePageIconService.checkHomePageIcon(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部审核成功！", "homePageIconList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部审核失败！", "homePageIconList.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateHomePageIcon() throws Exception {
    homePageIconPojo = homePageIconService.findHomePageIconById(homePageIconPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String updateHomePageIcon() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/icon") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/icon/", upfile);
        homePageIconPojo.setImage(file_name);
      }
      homePageIconService.updateHomePageIcon(homePageIconPojo);
      FileUtil.alertMessageBySkip("提交成功！", "homePageIconList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "homePageIconList.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddHomePageIcon() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String addHomePageIcon() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/icon") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/icon/", upfile);
        homePageIconPojo.setImage(file_name);
      }
      homePageIconService.insertHomePageIcon(homePageIconPojo);
      FileUtil.alertMessageBySkip("新增成功！", "homePageIconList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "homePageIconList.do");
    }
    return null;
  }
}
