/*
 * 文 件 名: SpecialAction.java 创 建 人: admin 创建时间: 2016-10-14
 */
package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SpecialGoodsPojo;
import com.tzmb2c.web.pojo.SpecialPojo;
import com.tzmb2c.web.pojo.SpecialTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SpecialService;
import com.tzmb2c.web.service.SpecialTypeService;

public class SpecialAction extends SuperAction {
  @Autowired
  private SpecialService specialService;
  @Autowired
  private SpecialTypeService specialTypeService;
  private SpecialPojo specialPojo;
  private Long id;
  private String[] tids;
  private String result;
  private List<SpecialTypePojo> specialTypePojos;
  private File upfile, upfile2;
  private String upfileFileName, upfile2FileName;
  private SpecialGoodsPojo specialGoodsPojo;

  public String getUpfile2FileName() {
    return upfile2FileName;
  }

  public void setUpfile2FileName(String upfile2FileName) {
    this.upfile2FileName = upfile2FileName;
  }

  public SpecialGoodsPojo getSpecialGoodsPojo() {
    return specialGoodsPojo;
  }

  public void setSpecialGoodsPojo(SpecialGoodsPojo specialGoodsPojo) {
    this.specialGoodsPojo = specialGoodsPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public SpecialPojo getSpecialPojo() {
    return specialPojo;
  }

  public void setSpecialPojo(SpecialPojo specialPojo) {
    this.specialPojo = specialPojo;
  }

  /**
   * 查询全部条数
   */
  public String goSpecial() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isDelete", 0);
    if (specialPojo != null) {
      map.put("specialTypeId", specialPojo.getSpecialTypeId());
      map.put("status", specialPojo.getStatus());
    }
    try {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("orderBy", "sorting desc");
      specialTypePojos = specialTypeService.listPage(map1);
      int i = specialService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String specialList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "sorting");
    map.put("isDelete", 0);
    if (specialPojo != null) {
      map.put("specialTypeId", specialPojo.getSpecialTypeId());
      map.put("status", specialPojo.getStatus());
    }
    List<SpecialPojo> specialList = null;
    try {
      specialList = specialService.listPage(map);
      JSONArray json = JSONArray.fromObject(specialList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddSpecial() throws Exception {
    try {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("orderBy", "sorting desc");
      specialTypePojos = specialTypeService.listPage(map1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("title", specialPojo.getTitle());
    // int i = specialService.countBy(map);
    // if (i > 0) {
    //   FileUtil.alertMessageBySkip("该专题名称已存在！", "goAddSpecial.do");
    //   return null;
    // }
    if (user != null && specialPojo != null) {
      // 图片上传
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, 0, file_name, "upfiles/special", false, 300, 300, true,
                true, "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddSpecial.do");
          return null;
        } else {
          specialPojo.setImage(image);
        }
      }
      // 分享图标
      if (upfile2 != null) {
        String file_name = getUpfile2FileName();
        String image =
            FileUtil.uploadFile(upfile2, 0, file_name, "upfiles/special", false, 300, 300, true,
                true, "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddSpecial.do");
          return null;
        } else {
          specialPojo.setIcon(image);
        }
      }
      specialPojo.setCreateBy(user.getId());
      // specialPojo.setCreateDate(new Date());
      specialPojo.setUpdateBy(user.getId());
      // specialPojo.setUpdateDate(new Date());
      try {
        specialService.add(specialPojo);
        if (specialGoodsPojo != null) {
          specialGoodsPojo.setSpecialId(specialPojo.getId());
        }

        FileUtil.alertMessageBySkip("新增成功！", "goSpecial.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddSpecial.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSpecial.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSpecial() throws Exception {
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("orderBy", "sorting desc");
    specialTypePojos = specialTypeService.listPage(map1);
    if (id != null && id > 0) {
      specialPojo = specialService.getById(id);
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("title", specialPojo.getTitle());
    // List<SpecialPojo> specialPojos = specialService.listPage(map);
    // if (specialPojos.size() > 0 && specialPojos.get(0).getId() != specialPojo.getId()) {
    //    FileUtil.alertMessageBySkip("该专题名称已存在！", "goEditSpecial.do?id=" + specialPojo.getId());
    //    return null;
    // }
    if (user != null && specialPojo != null) {
      // 图片上传
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, 0, file_name, "upfiles/special", false, 300, 300, true,
                true, "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goEditSpecial.do?id=" + specialPojo.getId());
          return null;
        } else {
          specialPojo.setImage(image);
        }
      }
      // 分享图标
      if (upfile2 != null) {
        String file_name = getUpfile2FileName();
        String image =
            FileUtil.uploadFile(upfile2, 0, file_name, "upfiles/special", false, 300, 300, true,
                true, "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddSpecial.do");
          return null;
        } else {
          specialPojo.setIcon(image);
        }
      }
      specialPojo.setUpdateBy(user.getId());
      specialPojo.setUpdateDate(new Date());
      try {
        specialService.update(specialPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goSpecial.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditSpecial.do?id=" + specialPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSpecial.do");
    }

    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      specialPojo = new SpecialPojo();
      specialPojo.setId(id);
      specialPojo.setStatus(1);
      specialPojo.setUpdateBy(user.getId());
      specialPojo.setUpdateDate(new Date());
      try {
        specialService.update(specialPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      specialPojo = new SpecialPojo();
      specialPojo.setId(id);
      specialPojo.setStatus(0);
      specialPojo.setUpdateBy(user.getId());
      specialPojo.setUpdateDate(new Date());
      try {
        specialService.update(specialPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        SpecialPojo special = new SpecialPojo();
        special.setId(id);
        special.setIsDelete(1);
        specialService.update(special);
        // specialService.delete(id);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      specialPojo = new SpecialPojo();
      specialPojo.setStatus(1);
      for (String tid : tids) {
        specialPojo.setId(Long.valueOf(tid));
        specialPojo.setUpdateBy(user.getId());
        specialPojo.setUpdateDate(new Date());
        try {
          specialService.update(specialPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      specialPojo = new SpecialPojo();
      specialPojo.setStatus(0);
      for (String tid : tids) {
        specialPojo.setId(Long.valueOf(tid));
        specialPojo.setUpdateBy(user.getId());
        specialPojo.setUpdateDate(new Date());
        try {
          specialService.update(specialPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          SpecialPojo special = new SpecialPojo();
          special.setId(Long.valueOf(tid));
          special.setIsDelete(1);
          specialService.update(special);
          // specialService.delete(Long.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  public List<SpecialTypePojo> getSpecialTypePojos() {
    return specialTypePojos;
  }

  public void setSpecialTypePojos(List<SpecialTypePojo> specialTypePojos) {
    this.specialTypePojos = specialTypePojos;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }
}
