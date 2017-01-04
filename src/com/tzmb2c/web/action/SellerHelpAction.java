package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.HelpTypeService;

public class SellerHelpAction extends SuperAction {

  @Autowired
  private HelpTypeService helpTypeService;
  @Autowired
  private HelpService helpService;

  private HelpTypePojo helpTypePojo;
  private List<HelpTypePojo> helpTypePojos, helpTypePojos2;
  private HelpPojo helpPojo;
  private List<HelpPojo> helpPojos;
  private String[] result;

  public List<HelpTypePojo> getHelpTypePojos2() {
    return helpTypePojos2;
  }

  public void setHelpTypePojos2(List<HelpTypePojo> helpTypePojos2) {
    this.helpTypePojos2 = helpTypePojos2;
  }

  /**
   * 
   * @return
   * @throws SQLException
   */
  public String goHelpWeb() throws SQLException {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("请先登录！", "sellerLogin.do");
      return null;
    }
    if (helpPojo != null) {
      doHelpWeb();
    }
    helpTypePojo = new HelpTypePojo();
    helpTypePojo.setPid(16l);
    helpTypePojo.setStatus(1);
    helpTypePojos = helpTypeService.getHelpTypeByPidAndStatus(helpTypePojo);
    if (helpTypePojos.size() != 0) {
      for (HelpTypePojo h : helpTypePojos) {
        HelpTypePojo ht = new HelpTypePojo();
        ht.setPid(h.getId());
        ht.setStatus(1);
        helpTypePojos2 = helpTypeService.getHelpTypeByPidAndStatus(ht);
        if (helpTypePojos2.size() != 0) {
          h.setHelpTypeChildPojoList(helpTypePojos2);
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * @return
   * @throws SQLException
   */
  public String doHelpWeb() throws SQLException {
    if (helpPojo != null) {
      helpPojos = helpService.getListByTypeid(helpPojo.getId());
      if (helpPojos.size() != 0) {
        result =
            new String[] {helpPojos.get(0).getTitle() == null ? "" : helpPojos.get(0).getTitle(),
                helpPojos.get(0).getContent() == null ? "" : helpPojos.get(0).getContent()};
      }
    }
    return SUCCESS;
  }

  public HelpTypePojo getHelpTypePojo() {
    return helpTypePojo;
  }

  public void setHelpTypePojo(HelpTypePojo helpTypePojo) {
    this.helpTypePojo = helpTypePojo;
  }

  public List<HelpTypePojo> getHelpTypePojos() {
    return helpTypePojos;
  }

  public void setHelpTypePojos(List<HelpTypePojo> helpTypePojos) {
    this.helpTypePojos = helpTypePojos;
  }

  public HelpPojo getHelpPojo() {
    return helpPojo;
  }

  public void setHelpPojo(HelpPojo helpPojo) {
    this.helpPojo = helpPojo;
  }

  public List<HelpPojo> getHelpPojos() {
    return helpPojos;
  }

  public void setHelpPojos(List<HelpPojo> helpPojos) {
    this.helpPojos = helpPojos;
  }

  public String[] getResult() {
    return result;
  }

  public void setResult(String[] result) {
    this.result = result;
  }
}
