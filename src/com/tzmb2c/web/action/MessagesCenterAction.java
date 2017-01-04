package com.tzmb2c.web.action;

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
import com.tzmb2c.web.pojo.MessagesCenterPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.MessagesCenterService;
import com.tzmb2c.web.service.SysLoginService;

public class MessagesCenterAction extends SuperAction {
  @Autowired
  private MessagesCenterService messagesCenterService;
  @Autowired
  private SysLoginService sysLoginService;
  private SysLoginPojo sysLoginPojo;
  private MessagesCenterPojo messagesCenterPojo;
  private List<MessagesCenterPojo> messagesCenterPojoList;
  private String[] tids;

  private int messageCount;// 消息数目



  public int getMessageCount() {
    return messageCount;
  }

  public void setMessageCount(int messageCount) {
    this.messageCount = messageCount;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public MessagesCenterPojo getMessagesCenterPojo() {
    return messagesCenterPojo;
  }

  public void setMessagesCenterPojo(MessagesCenterPojo messagesCenterPojo) {
    this.messagesCenterPojo = messagesCenterPojo;
  }

  public List<MessagesCenterPojo> getMessagesCenterPojoList() {
    return messagesCenterPojoList;
  }

  public void setMessagesCenterPojoList(List<MessagesCenterPojo> messagesCenterPojoList) {
    this.messagesCenterPojoList = messagesCenterPojoList;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  /**
   * 查询全部条数
   */
  public String messagesCenterListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (messagesCenterPojo != null) {
        map.put("isRead", messagesCenterPojo.getIsRead());
        map.put("isDelete", messagesCenterPojo.getIsDelete());
        map.put("userId", messagesCenterPojo.getUserId());
        map.put("loginName", messagesCenterPojo.getLoginName());
        map.put("status", messagesCenterPojo.getStatus());
        map.put("beginTimeStr", messagesCenterPojo.getBeginTimeStr());
        map.put("endTimeStr", messagesCenterPojo.getEndTimeStr());
        map.put("paixu", 1);
      }
      int i = messagesCenterService.findMessagesCenterCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String messagesCenterListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (messagesCenterPojo != null) {
        map.put("isRead", messagesCenterPojo.getIsRead());
        map.put("isDelete", messagesCenterPojo.getIsDelete());
        map.put("userId", messagesCenterPojo.getUserId());
        map.put("loginName", messagesCenterPojo.getLoginName());
        map.put("status", messagesCenterPojo.getStatus());
        map.put("beginTimeStr", messagesCenterPojo.getBeginTimeStr());
        map.put("endTimeStr", messagesCenterPojo.getEndTimeStr());
        map.put("paixu", 1);
      }
      messagesCenterPojoList = messagesCenterService.findMessagesCenterList(map);
      JSONArray json = JSONArray.fromObject(messagesCenterPojoList);
      page.setRowCount(messagesCenterPojoList.size());
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
  public String delMessagesCenter() throws Exception {
    try {
      messagesCenterService.delMessagesCenter(messagesCenterPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "messagesCenter.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  public String delMessagesCenterAll() {
    try {
      for (String id : tids) {
        messagesCenterService.delMessagesCenter(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("批量删除成功！", "messagesCenter.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量删除失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkMessagesCenter() throws Exception {
    try {
      messagesCenterService.checkMessagesCenter(messagesCenterPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "messagesCenter.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckMessagesCenter() throws Exception {
    try {
      messagesCenterService.uncheckMessagesCenter(messagesCenterPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "messagesCenter.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核
   * 
   * @return
   */
  public String checkMessagesCenterAll() {
    try {
      for (String id : tids) {
        messagesCenterService.checkMessagesCenter(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("批量审核成功！", "messagesCenter.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量审核失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateMessagesCenter() throws Exception {
    try {
      messagesCenterPojo = messagesCenterService.findMessagesCenterById(messagesCenterPojo.getId());
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
  public String updateMessagesCenter() throws Exception {
    try {
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      if (messagesCenterPojo != null) {
        sysLoginPojo = sysLoginService.getSysLoginByLoginName(messagesCenterPojo.getLoginName());
        if (sysLoginPojo != null) {
          messagesCenterPojo.setUserId(sysLoginPojo.getId());
          messagesCenterPojo.setIsDelete(messagesCenterPojo.getIsDelete());
          messagesCenterPojo.setIsRead(messagesCenterPojo.getIsRead());
          messagesCenterService.updateMessagesCenter(messagesCenterPojo);
        } else {
          FileUtil.alertMessageBySkip("查无此用户，请正确填写！",
              "goUpdateMessagesCenter.do?messagesCenterPojo.id=" + messagesCenterPojo.getId());
          return null;
        }
      }
      FileUtil.alertMessageBySkip("提交成功！", "messagesCenter.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddMessagesCenter() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Exception
   */
  public String addMessagesCenter() throws Exception {
    try {
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      if (messagesCenterPojo != null) {
        if ("0".equals(messagesCenterPojo.getLoginName())) {
          SysLoginPojo ticketRulePojo = new SysLoginPojo();
          ticketRulePojo.setStatus(1);
          ticketRulePojo.setUserManufacturerIsNotNull(1);
          List<SysLoginPojo> userIdList =
              sysLoginService.sysLoginAllList(ticketRulePojo, null, "2");
          if (userIdList.size() != 0) {
            for (SysLoginPojo s : userIdList) {
              messagesCenterPojo.setUserId(s.getId());
              messagesCenterPojo.setIsDelete(0L);
              messagesCenterPojo.setIsRead(0L);
              messagesCenterService.insertMessagesCenter(messagesCenterPojo);
            }
          } else {
            FileUtil.alertMessageBySkip("新增失败！", "goAddMessagesCenter.do");
            return null;
          }
        } else {
          sysLoginPojo = sysLoginService.getSysLoginByLoginName(messagesCenterPojo.getLoginName());
          if (sysLoginPojo != null) {
            messagesCenterPojo.setUserId(sysLoginPojo.getId());
            messagesCenterPojo.setIsDelete(0L);
            messagesCenterPojo.setIsRead(0L);
            messagesCenterService.insertMessagesCenter(messagesCenterPojo);
          } else {
            FileUtil.alertMessageBySkip("查无此用户，请正确填写！", "goAddMessagesCenter.do");
            return null;
          }
        }
      }
      FileUtil.alertMessageBySkip("新增成功！", "messagesCenter.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "messagesCenter.do");
    }
    return null;
  }

  /**
   * 商家中心--消息中心
   * 
   * @return
   * @throws Exception
   */
  public String goMessagesCenterWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "goSellerIndex.do");
        return null;
      }
      Long uID = sysLogin.getId();
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      messagesCenterPojo = new MessagesCenterPojo();
      messagesCenterPojo.setIsDelete(0L);
      messagesCenterPojo.setPaixu(111L);
      messagesCenterPojo.setStatus(1L);
      messagesCenterPojo.setUserId(uID);
      map.put("isDelete", messagesCenterPojo.getIsDelete());
      map.put("userId", messagesCenterPojo.getUserId());
      map.put("status", messagesCenterPojo.getStatus());
      map.put("paixu", 1);
      int i = messagesCenterService.findMessagesCenterCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 商家中心--消息中心--查询全部内容
   */
  public String messagesCenterListWeb() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      Long uID = messagesCenterPojo.getUserId();
      messagesCenterPojo = new MessagesCenterPojo();
      messagesCenterPojo.setIsDelete(0L);
      messagesCenterPojo.setPaixu(111L);
      messagesCenterPojo.setStatus(1L);
      messagesCenterPojo.setUserId(uID);
      map.put("isDelete", messagesCenterPojo.getIsDelete());
      map.put("userId", messagesCenterPojo.getUserId());
      map.put("status", messagesCenterPojo.getStatus());
      map.put("paixu", 1);
      messagesCenterPojoList = messagesCenterService.findMessagesCenterList(map);
      JSONArray json = JSONArray.fromObject(messagesCenterPojoList);
      page.setRowCount(messagesCenterPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 商家中心--消息中心--已读
   * 
   * @return
   * @throws Exception
   */
  public String readMessagesCenterWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "goSellerIndex.do");
        return null;
      }
      if (messagesCenterPojo.getId() != 0) {
        messagesCenterService.readMessagesCenterWeb(messagesCenterPojo.getId());
      }
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isRead", 0);
      map.put("isDelete", 0);
      map.put("userId", sysLogin.getId());
      map.put("status", 1);
      ActionContext actionContext = ActionContext.getContext();
      actionContext.getSession().put("sellerMessageCount",
          messagesCenterService.findMessagesCenterCount(map));
      this.messageCount = messagesCenterService.findMessagesCenterCount(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 商家中心--消息中心--批量删除
   * 
   * @return
   * @throws Exception
   */
  public String delMessagesCenterWebAll() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "goSellerIndex.do");
        return null;
      }
      for (String id : tids) {
        messagesCenterService.delMessagesCenterWeb(Long.valueOf(id));
      }

      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isRead", 0);
      map.put("isDelete", 0);
      map.put("userId", sysLogin.getId());
      map.put("status", 1);
      ActionContext actionContext = ActionContext.getContext();
      actionContext.getSession().put("sellerMessageCount",
          messagesCenterService.findMessagesCenterCount(map));
      FileUtil.alertMessageBySkip("批量删除成功！", "goMessagesCenterWeb.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量删除失败！", "goMessagesCenterWeb.do");
    }
    return null;
  }

  /**
   * 商家中心--消息中心--批量标记已读
   * 
   * @return
   * @throws Exception
   */
  public String readMessagesCenterWebAll() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "goSellerIndex.do");
        return null;
      }
      for (String id : tids) {
        messagesCenterService.readMessagesCenterWeb(Long.valueOf(id));
      }

      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isRead", 0);
      map.put("isDelete", 0);
      map.put("userId", sysLogin.getId());
      map.put("status", 1);
      ActionContext actionContext = ActionContext.getContext();
      actionContext.getSession().put("sellerMessageCount",
          messagesCenterService.findMessagesCenterCount(map));
      FileUtil.alertMessageBySkip("批量标记已读成功！", "goMessagesCenterWeb.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量标记已读失败！", "goMessagesCenterWeb.do");
    }
    return null;
  }
}
