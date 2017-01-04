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
import com.tzmb2c.web.pojo.SocialCircleTypePojo;
import com.tzmb2c.web.service.SocialCircleTypeService;

/**
 * @version 1.0
 * @author
 */
public class SocialCircleTypeAction extends SuperAction {

  @Autowired
  private SocialCircleTypeService socialCircleTypeService;

  private SocialCircleTypePojo socialCircleTypePojo;
  private List<SocialCircleTypePojo> socialCircleTypePojoList;
  private Long op;
  private String result;
  private File upfile;
  public Long getOp() {
    return op;
  }

  public void setOp(Long op) {
    this.op = op;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public SocialCircleTypePojo getSocialCircleTypePojo() {
    return socialCircleTypePojo;
  }

  public void setSocialCircleTypePojo(
      SocialCircleTypePojo socialCircleTypePojo) {
    this.socialCircleTypePojo = socialCircleTypePojo;
  }

  public List<SocialCircleTypePojo> getSocialCircleTypePojoList() {
    return socialCircleTypePojoList;
  }

  public void setSocialCircleTypePojoList(
      List<SocialCircleTypePojo> socialCircleTypePojoList) {
    this.socialCircleTypePojoList = socialCircleTypePojoList;
  }

  /**
   * 查询全部条数
   */
  public String socialCircleTypeListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (socialCircleTypePojo != null) {
        map.put("name", socialCircleTypePojo.getName());
      }
      int i = socialCircleTypeService.findSocialCircleTypeCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String socialCircleTypeListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      if (op == null) {
        map.put("pageSize", 10);
        map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      }
      if (socialCircleTypePojo != null) {
        map.put("name", socialCircleTypePojo.getName());
      }
      socialCircleTypePojoList = socialCircleTypeService
          .findSocialCircleTypeList(map);
      JSONArray json = JSONArray.fromObject(socialCircleTypePojoList);
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
  public String delSocialCircleType() throws Exception {
    try {
      socialCircleTypeService.delSocialCircleType(socialCircleTypePojo
          .getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 跳转编辑标签页面
   * 
   * @return
   * @throws Throwable
   */
  public String goUpdateSocialCircleType() throws Exception {
    if (socialCircleTypePojo != null) {
      socialCircleTypePojo = socialCircleTypeService
          .findSocialCircleTypeById(socialCircleTypePojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 编辑标签
   * 
   * @return
   * @throws Throwable
   */
  public String updateSocialCircleType() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/socialCircleType")
            + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/socialCircleType/", upfile);
        socialCircleTypePojo.setImages(file_name);
      }
      if (!"".equals(socialCircleTypePojo.getName())) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ids", socialCircleTypePojo.getId());
        map.put("name", socialCircleTypePojo.getName());
        int n = socialCircleTypeService.findSocialCircleTypeCount(map);
        if (n != 0) {
          FileUtil.alertMessageBySkip("分类名重复，请重新赋值！",
              "goUpdateSocialCircleType.do?socialCircleTypePojo.id="
                  + socialCircleTypePojo.getId());
          return null;
        }
      }
      socialCircleTypeService.updateSocialCircleType(socialCircleTypePojo);
      FileUtil.alertMessageBySkip("编辑成功！", "socialCircleType.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "socialCircleType.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */

  public String goAddSocialCircleType() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增标签
   * 
   * @return
   * @throws Throwable
   */
  public String addSocialCircleType() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/socialCircleType")
            + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/socialCircleType/", upfile);
        socialCircleTypePojo.setImages(file_name);
      }
      if (!"".equals(socialCircleTypePojo.getName())) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", socialCircleTypePojo.getName());
        int n = socialCircleTypeService.findSocialCircleTypeCount(map);
        if (n != 0) {
          FileUtil.alertMessageBySkip("分类名重复，请重新赋值！",
              "goAddSocialCircleType.do");
          return null;
        }
      }
      socialCircleTypeService
      .insertSocialCircleType(socialCircleTypePojo);
      FileUtil.alertMessageBySkip("新增成功！", "socialCircleType.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "socialCircleType.do");
    }
    return null;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

}
