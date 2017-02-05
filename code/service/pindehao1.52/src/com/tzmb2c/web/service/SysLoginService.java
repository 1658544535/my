package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.HotPlayerPojo;
import com.tzmb2c.web.pojo.SysLoginExcelPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public interface SysLoginService {

  public List<SysLoginPojo> sysLoginAllService() throws SQLException;

  public List<SysLoginPojo> sysLoginMFService() throws SQLException;

  public void addSysLoginService(SysLoginPojo sysLoginPojo) throws SQLException;

  public void insertSysLogin(SysLoginPojo sysLoginPojo) throws SQLException;

  public int updateSysLogin(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getfindByIdSysLogin(Long id) throws SQLException;

  public void deleteSysLogin(Long sysLoginId) throws SQLException;

  public int sysLoginAllCount(SysLoginPojo sysLoginDaoPojo, String os);

  public int sysLoginAllCountByYoumi(Map<String, Object> map);

  List<SysLoginPojo> sysLoginAllList(SysLoginPojo ticketRulePojo, Pager page, String os)
      throws SQLException;

  void sysLoginDeleteId(String[] tids);

  void delSysLogin(Long id) throws SQLException;

  SysLoginPojo findSysLoginById(Long merId) throws SQLException;

  public int getfindCountByIdSysLogin(Long id) throws SQLException;

  void updatePassword(SysLoginPojo sysLoginPojo) throws SQLException;

  boolean loginCheckService(SysLoginPojo sysLoginPojo) throws Exception;

  public SysLoginPojo findSysLoginByLoginname(SysLoginPojo sysLoginPojo) throws SQLException;

  public int findSysLoginCountByLoginname(SysLoginPojo sysLoginPojo) throws SQLException;

  void updateStatus(SysLoginPojo sysLoginPojo) throws SQLException;

  SysLoginPojo sysLoginFindId(SysLoginPojo sysLoginDaoPojo) throws SQLException;

  void updateType(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getSysLoginByLoginName(String loginname) throws SQLException;

  public List<SysLoginPojo> getSysLoginByName(String name) throws SQLException;

  public int getSysLoginCountByName(String name) throws SQLException;

  public List<SysLoginPojo> getSysLoginByTaobao_user_id(String id) throws SQLException;

  public List<SysLoginPojo> getSysLoginByOpenId(String id) throws SQLException;

  public void updateUserInfoById(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getUserInfoById(Long id) throws SQLException;

  public String getRongyunToken(String userid, String name, String logo);

  public SysLoginPojo qrySysLoginByThirdId(Map<String, Object> map) throws SQLException;

  public String genExternalSignCode(String loginname);

  public List<SysLoginPojo> getExternalCodeNullList() throws SQLException;

  public void updateSysLoginForExternalCode(SysLoginPojo sysLoginPojo) throws SQLException;


  public List<SysLoginPojo> getInvitationCodeNullList() throws SQLException;

  public void updateSysloginForInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public String genInvitationCode(String loginname);

  public SysLoginPojo getUserIdByInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public List<HotPlayerPojo> findHotPlayerList(Map<String, Object> map) throws SQLException;

  public int userCnt(Map<String, Object> params) throws SQLException;

  List<SysLoginPojo> listPage(Map<String, Object> params) throws SQLException;

  /**
   * 商家列表
   * 
   * @param params
   * @return
   * @throws SQLException
   */
  List<SysLoginPojo> getSUserList(Map<String, Object> params) throws SQLException;

  public List<SysLoginExcelPojo> getSysLoginAll2(SysLoginExcelPojo SysLogin, Pager page, String os)
      throws SQLException;


}
