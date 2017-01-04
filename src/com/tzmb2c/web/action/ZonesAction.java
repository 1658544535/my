/*
 * 文 件 名: ZonesAction.java 创 建 人: admin 创建时间: 2016-10-18
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
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.ZonesPojo;
import com.tzmb2c.web.service.ZonesService;

public class ZonesAction extends SuperAction {
  @Autowired
  private ZonesService zonesService;
  private ZonesPojo zonesPojo;
  private Long id;
  private String[] tids;
  private String result;
  private File upfile, upfile2;
  private String upfileFileName, upfile2FileName;
  private Integer type;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public String getUpfile2FileName() {
    return upfile2FileName;
  }

  public void setUpfile2FileName(String upfile2FileName) {
    this.upfile2FileName = upfile2FileName;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
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

  public ZonesPojo getZonesPojo() {
    return zonesPojo;
  }

  public void setZonesPojo(ZonesPojo zonesPojo) {
    this.zonesPojo = zonesPojo;
  }



  /**
   * 查询全部条数
   */
  public String goZones() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isDelete", 0);
    map.put("type", type);
    if (zonesPojo != null) {
      map.put("status", zonesPojo.getStatus());
    }
    try {
      int i = zonesService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String zonesList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "sorting desc,id desc");
    map.put("isDelete", 0);
    map.put("type", type);
    if (zonesPojo != null) {
      map.put("status", zonesPojo.getStatus());
    }
    List<ZonesPojo> zonesList = null;
    try {
      zonesList = zonesService.listPage(map);
      JSONArray json = JSONArray.fromObject(zonesList);
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
  public String goAddZones() throws Exception {
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
//    Map<String, Object> map = new HashMap<String, Object>();
//    map.put("title", zonesPojo.getTitle());
//    map.put("type", type);
//    map.put("isDelete", 0);
//    int i = zonesService.countBy(map);
//    if (i > 0) {
//      FileUtil.alertMessageBySkip("该专区名称已存在！", "goAddZones.do?type=" + type);
//      return null;
//    }
    if (user != null && zonesPojo != null) {
      // 图片上传
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, 0, file_name, "upfiles/zones", false, 300, 300, true, true,
                "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddZones.do?type=" + type);
          return null;
        } else {
          zonesPojo.setImage(image);
        }
      }
      // 分享图标
      if (upfile2 != null) {
        String file_name = getUpfile2FileName();
        String image =
            FileUtil.uploadFile(upfile2, 0, file_name, "upfiles/zones", false, 300, 300, true,
                true, "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddZones.do?type=" + type);
          return null;
        } else {
          zonesPojo.setIcon(image);
        }
      }
      zonesPojo.setCreateBy(user.getId());
      // zonesPojo.setCreateDate(new Date());
      zonesPojo.setUpdateBy(user.getId());
      // zonesPojo.setUpdateDate(new Date());
      zonesPojo.setType(type);
      try {
        zonesService.add(zonesPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goZones.do?type=" + type);
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddZones.do?type=" + type);
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goZones.do?type=" + type);
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditZones() throws Exception {
    if (id != null && id > 0) {
      zonesPojo = zonesService.getById(id);
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
//    Map<String, Object> map = new HashMap<String, Object>();
//    map.put("title", zonesPojo.getTitle());
//    map.put("type", type);
//    map.put("isDelete", 0);
//    List<ZonesPojo> zonesPojos = zonesService.listPage(map);
//    if (zonesPojos.size() > 0 && zonesPojos.get(0).getId() != zonesPojo.getId()) {
//      FileUtil.alertMessageBySkip("该专区名称已存在！", "goEditZones.do?id=" + zonesPojo.getId());
//      return null;
//    }
    if (user != null && zonesPojo != null) {
      // 图片上传
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, 0, file_name, "upfiles/zones", false, 300, 300, true, true,
                "");
        zonesPojo.setImage(file_name);
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goEditSpecial.do?id=" + zonesPojo.getId());
          return null;
        } else {
          zonesPojo.setImage(image);
        }
      }
      // 分享图标
      if (upfile2 != null) {
        String file_name = getUpfile2FileName();
        String image =
            FileUtil.uploadFile(upfile2, 0, file_name, "upfiles/zones", false, 300, 300, true,
                true, "");
        if (image == null) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddZones.do?type=" + type);
          return null;
        } else {
          zonesPojo.setIcon(image);
        }
      }
      zonesPojo.setUpdateBy(user.getId());
      zonesPojo.setUpdateDate(new Date());
      zonesPojo.setType(type);
      try {
        zonesService.update(zonesPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goZones.do?type=" + type);
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditZones.do?id=" + zonesPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goZones.do?type=" + type);
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
      zonesPojo = new ZonesPojo();
      zonesPojo.setId(id);
      zonesPojo.setStatus(1);
      zonesPojo.setUpdateBy(user.getId());
      zonesPojo.setUpdateDate(new Date());
      try {
        zonesService.update(zonesPojo);
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
      zonesPojo = new ZonesPojo();
      zonesPojo.setId(id);
      zonesPojo.setStatus(0);
      zonesPojo.setUpdateBy(user.getId());
      zonesPojo.setUpdateDate(new Date());
      try {
        zonesService.update(zonesPojo);
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
        ZonesPojo zones = new ZonesPojo();
        zones.setId(id);
        zones.setIsDelete(1);
        zonesService.update(zones);
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
      zonesPojo = new ZonesPojo();
      zonesPojo.setStatus(1);
      for (String tid : tids) {
        zonesPojo.setId(Long.valueOf(tid));
        zonesPojo.setUpdateBy(user.getId());
        zonesPojo.setUpdateDate(new Date());
        try {
          zonesService.update(zonesPojo);
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
      zonesPojo = new ZonesPojo();
      zonesPojo.setStatus(0);
      for (String tid : tids) {
        zonesPojo.setId(Long.valueOf(tid));
        zonesPojo.setUpdateBy(user.getId());
        zonesPojo.setUpdateDate(new Date());
        try {
          zonesService.update(zonesPojo);
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
          ZonesPojo zones = new ZonesPojo();
          zones.setId(Long.valueOf(tid));
          zones.setIsDelete(1);
          zonesService.update(zones);
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

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }
}
