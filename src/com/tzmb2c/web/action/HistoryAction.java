package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.service.HistoryService;

/**
 * 用户浏览记录 2014-12-24
 * 
 * @author creazylee
 */

public class HistoryAction extends SuperAction {

  @Autowired
  private HistoryService historyService;
  private HistoryPojo historyPojo;
  private List<HistoryPojo> historyList = null;

  private String[] tids;
  private String result;
  private int uid;


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public HistoryService getHistoryService() {
    return historyService;
  }

  public void setHistoryService(HistoryService historyService) {
    this.historyService = historyService;
  }

  public HistoryPojo getHistoryPojo() {
    return historyPojo;
  }

  public void setHistoryPojo(HistoryPojo historyPojo) {
    this.historyPojo = historyPojo;
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

  /***
   * 查找所有的浏览记录
   * 
   * @return
   */
  public String historyAllList() {
    historyList = historyService.historyAllList(historyPojo, page);
    JSONArray json = JSONArray.fromObject(historyList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 查找浏览记录数目
   * 
   * @return
   * @throws Exception
   */
  public String getHistoryCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(historyService.historyAllCount(historyPojo));
    return SUCCESS;
  }

  /**
   * 查找单条浏览记录
   * 
   * @return
   * @throws Exception
   */
  public String findOneUserOrderDetailById() throws Exception {
    historyPojo = historyService.findHistoryPojoById(historyPojo.getId());
    return SUCCESS;
  }

  /***
   * 审核单条浏览记录
   * 
   * @return
   * @throws SQLException
   */
  public String checkUserOrderDetail() throws SQLException {
    historyService.checkHistoryPojo(historyPojo);
    FileUtil.alertMessageBySkip("审核成功！", "historyManage.do");
    return null;
  }

  /***
   * 批量审核
   * 
   * @return
   */
  public String checkAllUserOrderDetailById() {
    historyService.checkAllHistoryPojoById(tids);
    FileUtil.alertMessageBySkip("审核成功！", "historyManage.do");
    return null;
  }

}
