/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
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
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.service.SocialCircleService;

/**
 * 社圈分类
 * @version 1.0
 * @author
 */
public class SocialCircleAction extends SuperAction {

  @Autowired
  private SocialCircleService socialCircleService;

  private SocialCirclePojo socialCirclePojo;
  private List<SocialCirclePojo> socialCirclePojoList;
  private File upfile;

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public List<SocialCirclePojo> getSocialCirclePojoList() {
    return socialCirclePojoList;
  }

  public void setSocialCirclePojoList(
      List<SocialCirclePojo> socialCirclePojoList) {
    this.socialCirclePojoList = socialCirclePojoList;
  }

  public SocialCirclePojo getSocialCirclePojo() {
    return socialCirclePojo;
  }

  public void setSocialCirclePojo(SocialCirclePojo socialCirclePojo) {
    this.socialCirclePojo = socialCirclePojo;
  }

  public String socialCircleListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (socialCirclePojo != null) {
        map.put("title", socialCirclePojo.getTitle());
        map.put("status", socialCirclePojo.getStatus());
        map.put("circleTypeId", socialCirclePojo.getCircleTypeId());
      }
      int i = socialCircleService.findSocialCircleCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String socialCircleListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (socialCirclePojo != null) {
        map.put("title", socialCirclePojo.getTitle());
        map.put("status", socialCirclePojo.getStatus());
        map.put("circleTypeId", socialCirclePojo.getCircleTypeId());
      }
      socialCirclePojoList = socialCircleService
          .findSocialCircleList(map);
      JSONArray json = JSONArray.fromObject(socialCirclePojoList);
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
  public String delSocialCircle() throws Exception {
    try {
      socialCircleService.delSocialCircle(socialCirclePojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "socialCircle.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "socialCircle.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  /*
   * public String delNavigationAll() { try { for (String id : tids) {
   * navigationService.delNavigation(Long.valueOf(id)); }
   * FileUtil.alertMessageBySkip("全部删除成功！", "navigationList.do"); } catch
   * (Exception e) { e.printStackTrace();
   * FileUtil.alertMessageBySkip("全部删除失败！", "navigationList.do"); } return
   * null; }
   */

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkSocialCircle() throws Exception {
    try {
      socialCircleService.checkSocialCircle(socialCirclePojo.getId());
      FileUtil.alertMessageBySkip("此条审核成功！", "socialCircle.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条审核未能成功！", "socialCircle.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckSocialCircle() throws Exception {
    try {
      socialCircleService.uncheckSocialCircle(socialCirclePojo.getId());
      FileUtil.alertMessageBySkip("此条审核失败！", "socialCircle.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条审核未能失败！", "socialCircle.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核
   * 
   * @return
   */
  /*
   * public String checkNavigationAll() { try { for (String id : tids) {
   * navigationService.checkNavigation(Long.valueOf(id)); }
   * FileUtil.alertMessageBySkip("全部通过审核成功！", "navigationList.do"); } catch
   * (Exception e) { e.printStackTrace();
   * FileUtil.alertMessageBySkip("全部通过审核失败！", "navigationList.do"); } return
   * null; }
   */

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSocialCircle() throws Exception {
    try {
      socialCirclePojo = socialCircleService
          .findSocialCircleById(socialCirclePojo.getId());
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
  public String updateSocialCircle() throws Exception {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath = ServletActionContext.getServletContext()
            .getRealPath("/upfiles/socialCircle") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath,
            "upfiles/socialCircle/", upfile);
        socialCirclePojo.setBanner(file_name);
      } else {
        socialCirclePojo.setBanner("");
      }
      socialCircleService.updateSocialCircle(socialCirclePojo);
      FileUtil.alertMessageBySkip("提交成功！", "socialCircle.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "socialCircle.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddSocialCircle() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Exception
   */
  public String addSocialCircle() throws Exception {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath = ServletActionContext.getServletContext()
            .getRealPath("/upfiles/socialCircle") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath,
            "upfiles/socialCircle/", upfile);
        socialCirclePojo.setBanner(file_name);
      } else {
        socialCirclePojo.setBanner("");
      }
      socialCircleService.insertSocialCircle(socialCirclePojo);
      FileUtil.alertMessageBySkip("新增成功！", "socialCircle.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "socialCircle.do");
    }
    return null;
  }
}
