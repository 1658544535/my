package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.HelpTypeService;
import com.tzmb2c.web.service.SysDictService;

public class HelpTypeAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private HelpTypeService helpTypeService;
  private String msg;
  private Long pid;
  private List<HelpTypePojo> helpTypeList;
  private HelpTypePojo helpTypePojo;
  private String result;
  private String[] tids;
  private List<SysDictPojo> statusSysDictList = null;
  @Autowired
  private SysDictService sysDictService;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
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

  public List<HelpTypePojo> getHelpTypeList() {
    return helpTypeList;
  }

  public void setHelpTypeList(List<HelpTypePojo> helpTypeList) {
    this.helpTypeList = helpTypeList;
  }

  public HelpTypePojo getHelpTypePojo() {
    return helpTypePojo;
  }

  public void setHelpTypePojo(HelpTypePojo helpTypePojo) {
    this.helpTypePojo = helpTypePojo;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  /***
   * 获取所有帮助类型的数目
   * 
   * @return
   * @throws Exception
   */
  public String getCountHelpType() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (pid == null) {
      pid = 0l;
    }
    page.setRowCount(helpTypeService.getHelpTypeCount(pid));
    return SUCCESS;
  }

  /***
   * 查找所有帮助类型记录
   * 
   * @return
   * @throws Exception
   */
  public String getHelpTypeByPid() throws Exception {
    if (pid == null) {
      pid = 0l;
    }
    helpTypeList = helpTypeService.getHelpTypeByPid(pid);
    JSONArray json = JSONArray.fromObject(helpTypeService.getHelpTypeByPid(pid));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());
    return SUCCESS;

  }

  /***
   * 跳转到添加页面
   * 
   * @return
   */
  public String gohelpTypeAdd() {
    getList();
    return SUCCESS;
  }

  /***
   * 添加帮助类型
   * 
   * @return
   */
  public String helpTypeAdd() {
    // if (helpTypePojo.getId() == null)
    // helpTypePojo.setId(0l);
    // helpTypePojo.setStatus(0);
    try {
      helpTypeService.addHelpType(helpTypePojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "helpTypeManage.do");
    return null;
  }

  /***
   * 查找单条+跳转到修改页面
   * 
   * @return
   */
  public String goFindHelpType() {
    getList();
    try {
      helpTypePojo = helpTypeService.findHelpType(helpTypePojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 更新单条帮助类型信息
   * 
   * @return
   */
  public String helpTypeUpdate() {
    try {
      helpTypeService.helpTypeUpdate(helpTypePojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("修改成功！", "helpTypeManage.do");
    return null;
  }

  /***
   * 删除单条帮助类型信息
   * 
   * @return
   * @throws SQLException
   */
  public String deleHelpType() throws SQLException {
    try {
      helpTypeService.deleHelpType(helpTypePojo.getId());
      // 删除子类
      helpTypeList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
      for (int i = 0; i < helpTypeList.size(); i++) {
        helpTypeService.deleHelpType(helpTypeList.get(i).getId());
      }
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 审核单条帮助类型信息
   * 
   * @return
   * @throws SQLException
   */
  public String checkHelpType() throws SQLException {
    try {
      helpTypeService.checkHelpType(helpTypePojo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 批量审核 fu
   * 
   * @return
   */
  public String allCheckHelpType() {

    try {
      if (tids != null) {
        helpTypeService.checkAllById(tids);
      }
      FileUtil.alertMessageBySkip("审核成功！", "helpTypeManage.do");
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}
