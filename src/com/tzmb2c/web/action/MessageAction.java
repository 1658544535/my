package com.tzmb2c.web.action;

import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.MessagePojo;
import com.tzmb2c.web.pojo.MessageReportPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.MessageReportService;
import com.tzmb2c.web.service.MessageService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class MessageAction extends SuperAction {

  @Autowired
  private MessageService messageService;
  @Autowired
  private MessageReportService messageReportService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  private MessagePojo message;
  private String result;
  private String[] tids;
  private MessageReportPojo messageReport;

  public MessageReportPojo getMessageReport() {
    return messageReport;
  }

  public void setMessageReport(MessageReportPojo messageReport) {
    this.messageReport = messageReport;
  }

  public MessagePojo getMessage() {
    return message;
  }

  public void setMessage(MessagePojo message) {
    this.message = message;
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

  public String getMessageCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(messageService.messageAllCount(message));
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("message_status"));
    return SUCCESS;
  }

  public String messageAllList() {
    JSONArray json = JSONArray.fromObject(messageService.messageAllList(message, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String messageDeleteId() {
    if (tids != null) {
      messageService.messageDeleteId(tids);
      // 同时删除回复消息
      for (String tid : tids) {
        try {
          messageReport = messageReportService.getfindByMessageId(Long.parseLong(tid));
          if (messageReport != null) {
            messageReportService.delMessageReport(messageReport.getId());
          }
        } catch (SQLException e) {

          e.printStackTrace();
        }
      }
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "message.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "message.do");
    }

    return null;
  }

  public String deleMessage() throws SQLException {
    try {
      messageService.delMessage(message.getId());
      // 同时删除回复消息
      messageReport = messageReportService.getfindByMessageId(message.getId());
      if (messageReport != null) {
        messageReportService.delMessageReport(messageReport.getId());
      }
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindMessage() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("messagePojo", messageService.findMessageById(message.getId()));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(message.getUserId()));
    ac.put("status", sysDictService.getSysDictListByType("message_status"));
    ac.put("type", sysDictService.getSysDictListByType("type"));
    return SUCCESS;
  }

  public String updateMessage() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      message.preUpdate(loginPojo);
    }
    messageService.updateMessage(message);
    FileUtil.alertMessageBySkip("修改成功！", "message.do");

    return null;
  }
}
