package com.tzmb2c.web.action;


import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.OssUtil;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.SyntheticalDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class ActivityTimeAction extends SuperAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;

  private ActivityTimePojo activityTimePojo;
  private SysLoginPojo sysLogin, sysLogin1;
  private String result;
  private String[] tids;
  private Integer t;
  private List<SyntheticalDictPojo> syntheticalDictPojos;
  private Integer op;
  private Integer ws;

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin1(SysLoginPojo sysLogin1) {
    this.sysLogin1 = sysLogin1;
  }

  public SysLoginPojo getSysLogin1() {
    return sysLogin1;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public String findActivityTimeList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("isdelete", "0");
    if (activityTimePojo != null) {
      map.put("beginTime", activityTimePojo.getBeginTime());
      map.put("endTime", activityTimePojo.getEndTime());
      map.put("channel", activityTimePojo.getChannel());
    }
    map.put("op", op);
    List<ActivityTimePojo> list = activityTimeService.findActivityTimeList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(activityTimeService.findActivityTimeCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findActivityTimeCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isdelete", "0");
    if (activityTimePojo != null) {
      map.put("beginTime", activityTimePojo.getBeginTime());
      map.put("endTime", activityTimePojo.getEndTime());
      map.put("channel", activityTimePojo.getChannel());
    }
    map.put("op", op);
    int i = activityTimeService.findActivityTimeCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String delActivityTime() {
    try {
      if (activityTimePojo != null) {
        // activityGoodsService.delActivityGoodsByTimeId(activityTimePojo.getId());
        activityTimeService.delActivityTime(activityTimePojo.getId());
      }
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delActivityTimeAll() throws Exception {
    if (tids != null) {
      for (String tid : tids) {
        try {
          // activityGoodsService.delActivityGoodsByTimeId(Long.valueOf(tid));
          activityTimeService.delActivityTime(Long.valueOf(tid));
        } catch (Exception e) {
          e.printStackTrace();
          if (op == 2) {
            FileUtil.alertMessageBySkip("删除失败！", "thematicActivityManage.do?op=2");
          } else {
            FileUtil.alertMessageBySkip("删除失败！", "activityTimeManage.do?op=1");
          }

        }
      }
      if (op == 2) {
        FileUtil.alertMessageBySkip("删除成功！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("删除成功！", "activityTimeManage.do?op=1");
      }
    } else {
      if (op == 2) {
        FileUtil.alertMessageBySkip("请先勾选！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("请先勾选！", "activityTimeManage.do?op=1");
      }
    }

    return null;
  }

  public String findActivityTimeById() throws Exception {
    // ActionContext ac = ActionContext.getContext();
    // ac.put("activityTimePojo",
    // activityTimeService.findActivityTimeById(activityTimePojo.getId()));
    // if (t == 2) {
    // syntheticalDictPojos =
    // syntheticalDictService.getSyntheticalDictListByTypeStatus("activity_type");
    // }
    if (activityTimePojo != null) {
      activityTimePojo = activityTimeService.findActivityTimeById(activityTimePojo.getId());
    }
    return SUCCESS;
  }

  public String getActivityTimeById() throws Exception {
    this.ws = 0;
    if (activityTimePojo != null) {
      activityTimePojo = activityTimeService.findActivityTimeById(activityTimePojo.getId());
    }
    Date time = StringUtil.stringDate(StringUtil.dateString(new Date()));
    Date dt1 = StringUtil.stringDate(activityTimePojo.getBeginTime());
    Date dt2 = StringUtil.stringDate(activityTimePojo.getEndTime());

    if (time.getTime() >= dt1.getTime() && time.getTime() < dt2.getTime()
        && activityTimePojo.getSpecialStatus() == 4) {
      this.ws = 1;
    }
    return SUCCESS;
  }

  public void updateActivityTime() throws Throwable {
    String ossSign = PropertiesHelper.getValue("oss_sign");
    if ("0".equals(ossSign) && !"".equals(ossSign)) {
      if (upfile != null) {
        FileInputStream fis = new FileInputStream(upfile);
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/activity/")
                + File.separator;
        String filename = StringUtil.getCurrentDateStr() + ".jpg";
        File dirfile = new File(uploadPath);
        if (!dirfile.exists() || dirfile != null) {
          dirfile.mkdirs();
        }
        FileUtil.copyFile(fis, uploadPath + filename);
        activityTimePojo.setBanner(filename);
      } else if ("1".equals(ossSign) && !"".equals(ossSign)) {
        if (upfile != null) {
          FileInputStream fin = new FileInputStream(upfile);
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          OssUtil.ordinaryUpload("taozhumab2c", "upfiles/activity/" + file_name, fin);
          activityTimePojo.setBanner(file_name);
        }
      }
      activityTimeService.updateActivityTime(activityTimePojo);
      if (op == 2) {
        FileUtil.alertMessageBySkip("修改成功！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("修改成功！", "activityTimeManage.do?op=1");
      }
    } else {
      activityTimeService.updateActivityTime(activityTimePojo);
      if (op == 2) {
        FileUtil.alertMessageBySkip("修改成功！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("修改成功！", "activityTimeManage.do?op=1");
      }
    }
  }

  public String activityTimeAdd() throws Exception {
    // if (t == 2) {
    // syntheticalDictPojos =
    // syntheticalDictService.getSyntheticalDictListByTypeStatus("activity_type");
    // }
    return SUCCESS;
  }

  private File upfile;

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String insertActivityTime() throws Throwable {
    try {
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        loginPojo.prePersist(loginPojo);
      }
      sysLogin1 = sysLoginService.sysLoginFindId(loginPojo);
      // activityTimePojo = new ActivityTimePojo();
      activityTimePojo.setCreateBy(sysLogin1.getId());
      String ossSign = PropertiesHelper.getValue("oss_sign");
      if ("0".equals(ossSign) && !"".equals(ossSign)) {
        if (upfile != null) {
          FileInputStream fis = new FileInputStream(upfile);
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/activity/")
                  + File.separator;
          String filename = StringUtil.getCurrentDateStr() + ".jpg";
          File dirfile = new File(uploadPath);
          if (!dirfile.exists() || dirfile != null) {
            dirfile.mkdirs();
          }
          FileUtil.copyFile(fis, uploadPath + filename);
          activityTimePojo.setBanner(filename);
        }
      } else if ("1".equals(ossSign) && !"".equals(ossSign)) {
        if (upfile != null) {
          FileInputStream fin = new FileInputStream(upfile);
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          OssUtil.ordinaryUpload("taozhumab2c", "upfiles/activity/" + file_name, fin);
          activityTimePojo.setBanner(file_name);
        }
      }
      activityTimeService.insertActivityTime(activityTimePojo);
      if (op == 2) {
        FileUtil.alertMessageBySkip("添加成功！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("添加成功！", "activityTimeManage.do?op=1");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 专题活动管理
   * 
   * @return
   * @throws SQLException
   */
  public String thematicActivityManage() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isdelete", "0");
    if (activityTimePojo != null) {
      map.put("beginTime", activityTimePojo.getBeginTime());
      map.put("endTime", activityTimePojo.getEndTime());
      map.put("channel", activityTimePojo.getChannel());
    }
    map.put("op", op);
    int i = activityTimeService.findActivityTimeCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public List<SyntheticalDictPojo> getSyntheticalDictPojos() {
    return syntheticalDictPojos;
  }

  public void setSyntheticalDictPojos(List<SyntheticalDictPojo> syntheticalDictPojos) {
    this.syntheticalDictPojos = syntheticalDictPojos;
  }

  public Integer getOp() {
    return op;
  }

  public void setOp(Integer op) {
    this.op = op;
  }

  public Integer getWs() {
    return ws;
  }

  public void setWs(Integer ws) {
    this.ws = ws;
  }
}
