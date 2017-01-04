package com.tzmb2c.web.action;

import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.MessageReportPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.MessageReportService;
import com.tzmb2c.web.service.MessageService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class MessageReportAction extends SuperAction {

  @Autowired
  private MessageReportService messageReportService;
  @Autowired
  private MessageService messageService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  private MessageReportPojo messageReport;
  private String result;
  private String[] tids;

  public MessageReportPojo getMessageReport() {
    return messageReport;
  }

  public void setMessageReport(MessageReportPojo messageReport) {
    this.messageReport = messageReport;
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

  public String getMessageReportCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(messageReportService.messageReportAllCount(messageReport));
    ActionContext ac = ActionContext.getContext();
    ac.put("type", sysDictService.getSysDictListByType("type"));
    return SUCCESS;
  }

  public String messageReportAllList() {
    JSONArray json =
        JSONArray.fromObject(messageReportService.messageReportAllList(messageReport, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String messageReportDeleteId() {
    if (tids != null) {
      messageReportService.messageReportDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "messageReport.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "messageReport.do");
    }

    return null;
  }

  public String deleMessageReport() throws SQLException {
    try {
      messageReportService.delMessageReport(messageReport.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindMessageReport() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("messageReportPojo", messageReportService.findMessageReportById(messageReport.getId()));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(messageReport.getUserId()));
    ac.put("messagePojo", messageService.findMessageById(messageReport.getMessageId()));
    ac.put("type", sysDictService.getSysDictListByType("type"));
    return SUCCESS;
  }

  public String updateMessageReport() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      messageReport.preUpdate(loginPojo);
    }

    messageReportService.updateMessageReport(messageReport);
    FileUtil.alertMessageBySkip("修改成功！", "messageReport.do");

    return null;
  }

  public String addMessageReport() throws Exception {
    if (messageReport.getStatus() == 1) {
      FileUtil.alertMessageBySkip("重复回复！", "message.do");
    } else {
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        messageReport.prePersist(loginPojo);
      }
      messageService.checkMessage(messageReport.getMessageId());
      messageReportService.insertMessageReport(messageReport);
      FileUtil.alertMessageBySkip("回复成功！", "message.do");
    }


    return null;
  }
}
