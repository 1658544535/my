package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SyntheticalDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.SysDictService;

public class SyntheticalDictAction extends SuperAction {

  @Autowired
  private SyntheticalDictService syntheticalDictService;
  private SyntheticalDictPojo syntheticalDict;
  private List<SyntheticalDictPojo> syntheticalDictPojoList;
  private String result;
  private String[] tids;

  @Autowired
  private SysDictService sysDictService;

  public SyntheticalDictPojo getSyntheticalDict() {
    return syntheticalDict;
  }

  public void setSyntheticalDict(SyntheticalDictPojo syntheticalDict) {
    this.syntheticalDict = syntheticalDict;
  }

  public List<SyntheticalDictPojo> getSyntheticalDictPojoList() {
    return syntheticalDictPojoList;
  }

  public void setSyntheticalDictPojoList(List<SyntheticalDictPojo> syntheticalDictPojoList) {
    this.syntheticalDictPojoList = syntheticalDictPojoList;
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

  public String getSyntheticalDictCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(syntheticalDictService.syntheticalDictAllCount(syntheticalDict));
    return SUCCESS;
  }

  public String syntheticalDictAllList() {
    JSONArray json =
        JSONArray.fromObject(syntheticalDictService.syntheticalDictAllList(syntheticalDict, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String syntheticalDictDeleteId() {
    if (tids != null) {
      syntheticalDictService.syntheticalDictDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "syntheticalDict.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "syntheticalDict.do");
    }

    return null;
  }

  public String deleSyntheticalDict() throws SQLException {
    try {
      syntheticalDictService.delSyntheticalDict(syntheticalDict.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindSyntheticalDict() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("syntheticalDictPojo",
        syntheticalDictService.findSyntheticalDictById(syntheticalDict.getId()));
    ac.put("status", sysDictService.getSysDictListByType("synthetical_dict_status"));
    return SUCCESS;
  }

  public String updateSyntheticalDict() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      syntheticalDict.preUpdate(loginPojo);
    }

    syntheticalDictService.updateSyntheticalDict(syntheticalDict);
    FileUtil.alertMessageBySkip("修改成功！", "syntheticalDict.do");

    return null;
  }

  public String addSyntheticalDict() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("synthetical_dict_status"));
    return SUCCESS;
  }

  public String insertSyntheticalDict() throws Exception {


    if (syntheticalDictService.getSyntheticalDictByTypeAndValue(syntheticalDict) != null) {
      FileUtil.alertMessageBySkip("新增失败！", "syntheticalDict.do");
    } else {
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        syntheticalDict.prePersist(loginPojo);
      }
      syntheticalDictService.insertSyntheticalDict(syntheticalDict);
      FileUtil.alertMessageBySkip("新增成功！", "syntheticalDict.do");
    }

    return null;
  }


  /***
   * 批量审核
   * 
   * @return
   */
  public String verifyAllPushNotice() {

    try {
      if (tids != null) {
        syntheticalDictService.checkAllById(tids);
      }
      FileUtil.alertMessageBySkip("审核成功！", "syntheticalDict.do");
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 单条审核 fu
   * 
   * @return
   */
  public String update() {
    try {
      syntheticalDictService.verifyInfo(syntheticalDict);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String getSyntheticalDictListByType() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (syntheticalDict != null) {
      map.put("type", syntheticalDict.getType());
    }
    syntheticalDictPojoList = syntheticalDictService.getSyntheticalDictListByType(map);
    JSONArray json = JSONArray.fromObject(syntheticalDictPojoList);
    setResult(json.toString());
    return SUCCESS;
  }
}
