package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.PurchaserAttentionPojo;
import com.tzmb2c.web.service.PurchaserAttentionService;

/**
 * 采购商：我的关注
 * 
 * @author creazylee
 * 
 */

public class PurchaserAttentionAction extends SuperAction {

  private String[] tids;// 接收待删除id的字符串数组(即checkBox的状态)
  private String type;
  private String result;
  @Autowired
  private PurchaserAttentionService purchaserAttentionService;
  private PurchaserAttentionPojo purchaserAttentionPojo;
  private List<PurchaserAttentionPojo> attentionList = null;

  public PurchaserAttentionService getPurchaserAttentionService() {
    return purchaserAttentionService;
  }

  public void setPurchaserAttentionService(PurchaserAttentionService purchaserAttentionService) {
    this.purchaserAttentionService = purchaserAttentionService;
  }

  public PurchaserAttentionPojo getPurchaserAttentionPojo() {
    return purchaserAttentionPojo;
  }

  public void setPurchaserAttentionPojo(PurchaserAttentionPojo purchaserAttentionPojo) {
    this.purchaserAttentionPojo = purchaserAttentionPojo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  // 添加
  public String goAddAttention() throws Exception {
    // ActionContext actionContext = ActionContext.getContext();
    // List<BusinessDictPojo> list
    // =businessDictService.sysDictByNameAllList("公告类型");
    // actionContext.put("bustype", list);
    return SUCCESS;
  }

  // 添加
  public String addAttention() throws IOException {
    try {
      // ActionContext actionContext = ActionContext.getContext();
      // UserPojo loginPojoResult = (UserPojo)
      // actionContext.getSession().get("user");
      // notice.setRecUserIderId(loginPojoResult.getUserId());
      // notice.setNoticeAddDate(new Date());
      // notice.setNoticeate(new Date());
      // shopmoney.setShopId("2");
      // notice.setRecUserIderId("admin");
      purchaserAttentionPojo.setCreateBy(8L);;
      purchaserAttentionService.addAttention(purchaserAttentionPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "purchaserAttentionManage.do");
    return null;
  }

  // public static void main(String[] args) {
  // System.out.println(PropertiesHelper.getValue("notice_image_root_path"));
  // }

  // 更新
  public String goFindAttention() throws Exception {
    purchaserAttentionPojo =
        purchaserAttentionService.findAttentionById(purchaserAttentionPojo.getId());
    return SUCCESS;
  }

  // 更新
  public String updateAttention() throws Exception {
    // ActionContext actionContext = ActionContext.getContext();
    // UserPojo loginPojoResult = (UserPojo)
    // actionContext.getSession().get("user");
    // notice.setRecUserIderId(loginPojoResult.getUserId());
    purchaserAttentionPojo.setCreateBy(8L);
    purchaserAttentionService.updateAttention(purchaserAttentionPojo);
    FileUtil.alertMessageBySkip("修改成功！", "purchaserAttentionManage.do");
    return null;
  }

  public String purchaserAttentionAllList() {
    attentionList = purchaserAttentionService.attentionAllList(purchaserAttentionPojo, page, type);
    JSONArray json = JSONArray.fromObject(attentionList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  // 入口
  public String getPurchaserAttentionCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(purchaserAttentionService.attentionAllCount(purchaserAttentionPojo));
    return SUCCESS;
  }

  // 删除
  public String delAttention() throws SQLException {
    try {
      purchaserAttentionService.delAttention(purchaserAttentionPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  // 删除全部
  public String delAllAttentionById() {
    purchaserAttentionService.delAllAttentionById(tids);
    FileUtil.alertMessageBySkip("删除成功！", "purchaserAttentionManage.do");
    return null;
  }

  // 审核
  // public String verifyPushNotice() throws SQLException {
  // try {
  // purchaserAttentionService.verifyNotice(purchaserAttentionPojo);
  // this.result = "1";
  // } catch (Exception e) {
  // this.result = "0";
  // }
  // return SUCCESS;
  // }

  // 审核全部
  // public String verifyAllPushNotice() {
  // purchaserAttentionService.checkAllNoticeById(tids);
  // FileUtil.alertMessageBySkip("审核成功！", "pushNoticeManage.do");
  // return null;
  // }

}
