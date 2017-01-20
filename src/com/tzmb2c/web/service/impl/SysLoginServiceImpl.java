package com.tzmb2c.web.service.impl;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tencent.common.MD5;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.dao.SysLoginDao;
import com.tzmb2c.web.pojo.HotPlayerPojo;
import com.tzmb2c.web.pojo.SysLoginExcelPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SysLoginService;

public class SysLoginServiceImpl implements SysLoginService {
  @Autowired
  private SysLoginDao sysLoginDao;

  public void setSysLoginDao(SysLoginDao sysLoginDao) {
    this.sysLoginDao = sysLoginDao;
  }


  @Override
  public List<SysLoginPojo> sysLoginAllService() throws SQLException {
    return sysLoginDao.getSysLoginAll();
  }

  @Override
  public List<SysLoginPojo> sysLoginMFService() throws SQLException {
    return sysLoginDao.getSysLoginMF();
  }


  @Override
  public void addSysLoginService(SysLoginPojo sysLoginPojo) throws SQLException {

    /*
     * if("0".equals(sysLoginPojo.getSysLoginLevel())){ SysLoginPojo rsSysLoginPojo =
     * sysLoginDao.getSysLoginCountBySysLoginLevel(sysLoginPojo.getLevel()); int sysLoginId =0;
     * if(rsSysLoginPojo.getSysLoginCount() > -1 && rsSysLoginPojo.getSysLoginCount() <= 9){
     * sysLoginId = rsSysLoginPojo.getSysLoginCount()+1; sysLoginPojo.setSysLoginId("A0" +
     * sysLoginId); }else if(rsSysLoginPojo.getSysLoginCount() > 9 &&
     * rsSysLoginPojo.getSysLoginCount() <= 99){ sysLoginId = rsSysLoginPojo.getSysLoginCount()+1;
     * sysLoginPojo.setSysLoginId("A" +sysLoginId ); } //sysLoginPojo.setSysLoginPath("#"); }else{
     * SysLoginPojo rsSysLoginPojo =
     * sysLoginDao.getSysLoginCountBySysLoginLevel(sysLoginPojo.getLevel());
     * if(rsSysLoginPojo.getSysLoginCount() > -1 && rsSysLoginPojo.getSysLoginCount() <= 9){
     * sysLoginPojo.setSysLoginId(sysLoginPojo.getLevel()+ "B0" + (rsSysLoginPojo.getSysLoginCount()
     * + 1)); }else if(rsSysLoginPojo.getSysLoginCount() > 9 && rsSysLoginPojo.getSysLoginCount() <=
     * 99){ sysLoginPojo.setSysLoginId(sysLoginPojo.getLevel()+ "B" +
     * (rsSysLoginPojo.getSysLoginCount() + 1)); } }
     */
    sysLoginDao.insertSysLogin(sysLoginPojo);
  }


  @Override
  public void insertSysLogin(SysLoginPojo sysLoginPojo) throws SQLException {

    sysLoginDao.insertSysLogin(sysLoginPojo);
  }

  @Override
  public synchronized int updateSysLogin(SysLoginPojo sysLoginPojo) throws SQLException {
    return sysLoginDao.updateSysLogin(sysLoginPojo);

  }

  @Override
  public void updatePassword(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginDao.updatePassword(sysLoginPojo);

  }

  @Override
  public void updateStatus(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginDao.updateStatus(sysLoginPojo);

  }

  @Override
  public void updateType(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginDao.updateType(sysLoginPojo);

  }

  @Override
  public SysLoginPojo getfindByIdSysLogin(Long id) throws SQLException {
    return sysLoginDao.getfindByIdSysLogin(id);

  }

  @Override
  public void deleteSysLogin(Long id) throws SQLException {

    sysLoginDao.deleteSysLogin(id);
  }

  @Override
  public SysLoginPojo sysLoginFindId(SysLoginPojo sysLoginDaoPojo) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysLoginDaoPojo != null) {
      map.put("name", sysLoginDaoPojo.getName());
      map.put("loginname", sysLoginDaoPojo.getLoginname());
      map.put("password", sysLoginDaoPojo.getPassword());
    }
    return sysLoginDao.sysLoginFindId(map);

  }

  @Override
  public int sysLoginAllCount(SysLoginPojo sysLoginDaoPojo, String os) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysLoginDaoPojo != null) {
      map.put("id", sysLoginDaoPojo.getId());
      map.put("name", sysLoginDaoPojo.getName());
      map.put("loginname", sysLoginDaoPojo.getLoginname());
      map.put("QQ", sysLoginDaoPojo.getQQ());
      map.put("channel", sysLoginDaoPojo.getChannel());
      map.put("regChannel", sysLoginDaoPojo.getRegChannel());
      map.put("createDateStartStr", sysLoginDaoPojo.getCreateDateStartStr());
      map.put("createDateEndStr", sysLoginDaoPojo.getCreateDateEndStr());
      map.put("appapi", sysLoginDaoPojo.getAppapi());
      map.put("form", sysLoginDaoPojo.getForm());
      map.put("judgeSource", sysLoginDaoPojo.getJudgeSource());
    }
    if (os != null) {
      map.put("os", os);
    }
    return sysLoginDao.sysLoginAllCount(map);
  }

  @Override
  public List<SysLoginPojo> sysLoginAllList(SysLoginPojo ticketRulePojo, Pager page, String os)
      throws SQLException {

    Map<String, Object> map = new HashMap<String, Object>();
    List<SysLoginPojo> list = null;
    if (ticketRulePojo != null) {
      map.put("id", ticketRulePojo.getId());
      map.put("name", ticketRulePojo.getName());
      map.put("loginname", ticketRulePojo.getLoginname());
      map.put("QQ", ticketRulePojo.getQQ());
      map.put("QQs", ticketRulePojo.getQQs());
      map.put("id", ticketRulePojo.getId());
      map.put("channel", ticketRulePojo.getChannel());
      map.put("regChannel", ticketRulePojo.getRegChannel());
      map.put("createDateStartStr", ticketRulePojo.getCreateDateStartStr());
      map.put("createDateEndStr", ticketRulePojo.getCreateDateEndStr());
      map.put("userManufacturerIsNotNull", ticketRulePojo.getUserManufacturerIsNotNull());
      map.put("form", ticketRulePojo.getForm());
      map.put("judgeSource", ticketRulePojo.getJudgeSource());
    }
    if (os != null) {
      map.put("os", os);
    }
      if (page != null) {
        map.put("pageSize", page.getPageSize());
        map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      } else {
        map.put("pageSize", 10);
        map.put("pageNo", 0);
      }
      
    list = sysLoginDao.sysLoginAllList(map);


    return list;

  }

  @Override
  public void sysLoginDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        sysLoginDao.delSysLogin(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delSysLogin(Long id) throws SQLException {
    sysLoginDao.delSysLogin(id);
  }

  @Override
  public SysLoginPojo findSysLoginById(Long id) throws SQLException {

    return sysLoginDao.findSysLoginById(id);

  }

  @Override
  public boolean loginCheckService(SysLoginPojo loginPojo) throws Exception {
    boolean loop = false;
    SysLoginPojo loginPojoResult = sysLoginDao.getLoginPojoByLoginIdAndLoginPd(loginPojo);
    if (loginPojoResult != null) {
      if (StringUtils.isNotEmpty(loginPojoResult.getLoginname())
          && StringUtils.isNotEmpty(loginPojoResult.getLoginname())) {
        // ActionContext actionContext = ActionContext.getContext();
        // actionContext.getSession().put("user", loginPojoResult);
        loop = true;
      }
    }
    return loop;
  }

  @Override
  public SysLoginPojo findSysLoginByLoginname(SysLoginPojo sysLoginPojo) throws SQLException {
    return sysLoginDao.findSysLoginByLoginname(sysLoginPojo);
  }


  @Override
  public SysLoginPojo getSysLoginByLoginName(String loginname) throws SQLException {
    return sysLoginDao.getSysLoginByLoginName(loginname);
  }


  @Override
  public List<SysLoginPojo> getSysLoginByName(String name) throws SQLException {

    return sysLoginDao.getSysLoginByName(name);
  }

  @Override
  public List<SysLoginPojo> getSysLoginByTaobao_user_id(String id) throws SQLException {

    return sysLoginDao.getSysLoginByTaobao_user_id(id);
  }


  @Override
  public List<SysLoginPojo> getSysLoginByOpenId(String id) throws SQLException {

    return sysLoginDao.getSysLoginByOpenId(id);
  }


  @Override
  public void updateUserInfoById(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginDao.updateUserInfoById(sysLoginPojo);

  }


  @Override
  public SysLoginPojo getUserInfoById(Long id) throws SQLException {
    return sysLoginDao.getUserInfoById(id);
  }

  /**
   * @param userid 帐号
   * @param name 昵称
   * @param logo 头像
   * @return
   */
  @Override
  public String getRongyunToken(String userid, String name, String logo) {
    // 融云token
    // 参数appkey
    String key = "e5t4ouvptxjga";
    // 密码
    String secret = "zj0A4beYLnJars";
    // token
    String token = "";

    SdkHttpResult result = null;
    Map<String, Object> productMap = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      result = ApiHttpClient.getToken(key, secret, userid, name, name, FormatType.json);
      System.out.println(">>>>>>" + result);
      productMap = mapper.readValue(result.getResult(), HashMap.class);
      token = productMap.get("token") == null ? "" : productMap.get("token").toString();
      System.out.println(productMap.get("code"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return token;
  }


  @Override
  public SysLoginPojo qrySysLoginByThirdId(Map<String, Object> map) throws SQLException {
    return sysLoginDao.qrySysLoginByThirdId(map);
  }


  @Override
  public String genExternalSignCode(String loginname) {
    String externalSignCode = loginname + new Date().getTime() / 1000 + StringUtil.genRandomStr(4);
    externalSignCode = MD5.MD5Encode(externalSignCode);
    return externalSignCode;
  }


  @Override
  public List<SysLoginPojo> getExternalCodeNullList() throws SQLException {

    return sysLoginDao.getExternalCodeNullList();
  }


  @Override
  public void updateSysLoginForExternalCode(SysLoginPojo sysLoginPojo) throws SQLException {
    // 生成邀请码
    sysLoginPojo.setExternalSignCode(genExternalSignCode(sysLoginPojo.getLoginname()));
    sysLoginDao.updateSysLoginForExternalCode(sysLoginPojo);

  }

  @Override
  public List<SysLoginPojo> getInvitationCodeNullList() throws SQLException {

    return sysLoginDao.getInvitationCodeNullList();
  }

  @Override
  public void updateSysloginForInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException {
    // 生成邀请码(新)
    sysLoginPojo.setInvitationCode(genInvitationCode(sysLoginPojo.getLoginname()));
    sysLoginDao.updateSysloginForInvitationCode(sysLoginPojo);

  }

  @Override
  public String genInvitationCode(String loginname) {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 6; ++i) {
      int number = random.nextInt(36);
      sb.append(str.charAt(number));
    }
    String invitationCode = sb.toString();
    return invitationCode;
  }


  @Override
  public SysLoginPojo getUserIdByInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException {
    return sysLoginDao.getUserIdByInvitationCode(sysLoginPojo);
  }


  @Override
  public int getfindCountByIdSysLogin(Long id) throws SQLException {

    return sysLoginDao.getfindCountByIdSysLogin(id);
  }


  @Override
  public int getSysLoginCountByName(String name) throws SQLException {

    return sysLoginDao.getSysLoginCountByName(name);
  }


  @Override
  public int findSysLoginCountByLoginname(SysLoginPojo sysLoginPojo) throws SQLException {

    return sysLoginDao.findSysLoginCountByLoginname(sysLoginPojo);
  }


  @Override
  public int sysLoginAllCountByYoumi(Map<String, Object> map) {
    return sysLoginDao.sysLoginAllCountByYoumi(map);
  }


  @Override
  public List<HotPlayerPojo> findHotPlayerList(Map<String, Object> map) throws SQLException {
    return sysLoginDao.findHotPlayerList(map);
  }


  @Override
  public int userCnt(Map<String, Object> params) throws SQLException {
    return sysLoginDao.userCnt(params);
  }


  @Override
  public List<SysLoginPojo> listPage(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return sysLoginDao.listPage(params);
  }

  @Override
  public List<SysLoginExcelPojo> getSysLoginAll2(SysLoginExcelPojo sysLoginExcel, Pager page,
      String os) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysLoginExcel != null) {
      map.put("name", sysLoginExcel.getName());
      map.put("loginname", sysLoginExcel.getLoginname());
      map.put("createDateStartStr", sysLoginExcel.getCreateDateStartStr());
      map.put("createDateEndStr", sysLoginExcel.getCreateDateEndStr());
      map.put("judgeSource", sysLoginExcel.getJudgeSource());
    }
    if (os != null) {
      map.put("os", os);
    }
    return sysLoginDao.getSysLoginAll2(map);
  }

}
