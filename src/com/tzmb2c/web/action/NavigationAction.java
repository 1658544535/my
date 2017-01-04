package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.NavigationPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.service.NavigationService;
import com.tzmb2c.web.service.ProductTypeService;

public class NavigationAction extends SuperAction {
  @Autowired
  private NavigationService navigationService;
  @Autowired
  private ProductTypeService productTypeService;
  private NavigationPojo navigationPojo;
  private List<NavigationPojo> navigationPojoList;
  private String[] tids;
  private String s;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  /**
   * 查询全部条数
   */
  public String navigationListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (navigationPojo != null) {
        map.put("name", navigationPojo.getName());
        map.put("status", navigationPojo.getStatus());
      }
      int i = navigationService.findNavigationCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String navigationListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (navigationPojo != null) {
        map.put("name", navigationPojo.getName());
        map.put("status", navigationPojo.getStatus());
      }
      navigationPojoList = navigationService.findNavigationList(map);
      JSONArray json = JSONArray.fromObject(navigationPojoList);
      page.setRowCount(navigationPojoList.size());
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
  public String delNavigation() throws Exception {
    try {
      navigationService.delNavigation(navigationPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "navigationList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "navigationList.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  public String delNavigationAll() {
    try {
      for (String id : tids) {
        navigationService.delNavigation(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "navigationList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "navigationList.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkNavigation() throws Exception {
    try {
      navigationService.checkNavigation(navigationPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "navigationList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "navigationList.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckNavigation() throws Exception {
    try {
      navigationService.uncheckNavigation(navigationPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "navigationList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "navigationList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核
   * 
   * @return
   */
  public String checkNavigationAll() {
    try {
      for (String id : tids) {
        navigationService.checkNavigation(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "navigationList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "navigationList.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateNavigation() throws Exception {
    try {
      ProductTypePojo productTypePojo = new ProductTypePojo();
      navigationPojo = navigationService.findNavigationById(navigationPojo.getId());
      if (navigationPojo.getCategoryId() == null || navigationPojo.getLevel() == null) {
        navigationPojo.setLevelAll("无");
      } else if (navigationPojo.getCategoryId() != null && navigationPojo.getLevel() != null) {
        if (navigationPojo.getLevel() == 1) {
          productTypePojo.setId(Long.valueOf(navigationPojo.getCategoryId()));
          if (productTypeService.findProductType(productTypePojo).getName() != null) {
            s = productTypeService.findProductType(productTypePojo).getName();
            navigationPojo.setLevelAll("一级类目" + "  " + s);
          } else {
            navigationPojo.setLevelAll("无");
          }
        }
        if (navigationPojo.getLevel() == 2) {
          productTypePojo.setId(Long.valueOf(navigationPojo.getCategoryId()));
          if (productTypeService.findProductType(productTypePojo).getName() != null) {
            s = productTypeService.findProductType(productTypePojo).getName();
            navigationPojo.setLevelAll("二级类目" + "  " + s);
          } else {
            navigationPojo.setLevelAll("无");
          }
        }
      } else {
        navigationPojo.setLevelAll("无");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Exception
   */
  public String updateNavigation() throws Exception {
    try {
      if (navigationPojo.getLevels() != -1
          && (navigationPojo.getCategoryId1() != null || navigationPojo.getCategoryId2() != null)) {
        if (navigationPojo.getLevels() == 1) {
          navigationPojo.setLevel(navigationPojo.getLevels());
          navigationPojo.setCategoryId(navigationPojo.getCategoryId1());
        } else if (navigationPojo.getLevels() == 2) {
          navigationPojo.setLevel(navigationPojo.getLevels());
          navigationPojo.setCategoryId(navigationPojo.getCategoryId2());
        }
      }
      navigationService.updateNavigation(navigationPojo);
      FileUtil.alertMessageBySkip("提交成功！", "navigationList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "navigationList.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String addNavigation() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Exception
   */
  public String addNavigationOK() throws Exception {
    try {
      if (navigationPojo.getCategoryId1() != null || navigationPojo.getCategoryId2() != null) {
        if (navigationPojo.getLevel() == 1) {
          navigationPojo.setCategoryId(navigationPojo.getCategoryId1());
        } else if (navigationPojo.getLevel() == 2) {
          navigationPojo.setCategoryId(navigationPojo.getCategoryId2());
        }
      }
      navigationService.insertNavigation(navigationPojo);
      FileUtil.alertMessageBySkip("新增成功！", "navigationList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "navigationList.do");
    }
    return null;
  }

  public NavigationPojo getNavigationPojo() {
    return navigationPojo;
  }

  public void setNavigationPojo(NavigationPojo navigationPojo) {
    this.navigationPojo = navigationPojo;
  }

  public List<NavigationPojo> getNavigationPojoList() {
    return navigationPojoList;
  }

  public void setNavigationPojoList(List<NavigationPojo> navigationPojoList) {
    this.navigationPojoList = navigationPojoList;
  }
}
