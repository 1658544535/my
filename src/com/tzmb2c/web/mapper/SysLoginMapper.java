package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HotPlayerPojo;
import com.tzmb2c.web.pojo.SysLoginExcelPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public interface SysLoginMapper {


  public List<SysLoginPojo> getSysLoginAll() throws SQLException;

  public List<SysLoginPojo> getSysLoginMF() throws SQLException;

  public void insertSysLogin(SysLoginPojo sysLoginPojo) throws SQLException;

  public int updateSysLogin(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getfindByIdSysLogin(Long id) throws SQLException;

  public int getfindCountByIdSysLogin(Long id) throws SQLException;

  public SysLoginPojo sysLoginFindId(Map<String, Object> map) throws SQLException;

  public void deleteSysLogin(Long id) throws SQLException;

  public int sysLoginAllCount(Map<String, Object> map);

  public int sysLoginAllCountByYoumi(Map<String, Object> map);

  public List<SysLoginPojo> sysLoginAllList(Map<String, Object> map);

  void delSysLogin(Long id) throws SQLException;

  public void updatePassword(SysLoginPojo sysLoginPojo) throws SQLException;

  public void updateStatus(SysLoginPojo sysLoginPojo) throws SQLException;

  public void updateType(SysLoginPojo sysLoginPojo) throws SQLException;

  SysLoginPojo getLoginPojoByLoginIdAndLoginPd(SysLoginPojo loginPojo) throws SQLException;

  public SysLoginPojo findSysLoginByLoginname(SysLoginPojo sysLoginPojo) throws SQLException;

  public int findSysLoginCountByLoginname(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getSysLoginByLoginName(String loginname) throws SQLException;

  public List<SysLoginPojo> getSysLoginByName(String name) throws SQLException;

  public int getSysLoginCountByName(String name) throws SQLException;

  public List<SysLoginPojo> getSysLoginByTaobao_user_id(String id) throws SQLException;

  public List<SysLoginPojo> getSysLoginByOpenId(String id) throws SQLException;

  public List<Long> getAllUserId() throws SQLException;

  public List<SysLoginPojo> sysLoginAllLists(Map<String, Object> map) throws SQLException;

  public void updateUserInfoById(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getUserInfoById(Long id) throws SQLException;

  public SysLoginPojo qrySysLoginByThirdId(Map<String, Object> map) throws SQLException;

  public List<SysLoginPojo> getExternalCodeNullList() throws SQLException;

  public void updateSysloginForExternalCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public void insertSysLoginReturnID(SysLoginPojo sysLoginPojo) throws SQLException;

  public int getCountByLoginName(String loginname) throws SQLException;

  public List<SysLoginPojo> getInvitationCodeNullList() throws SQLException;

  public void updateSysloginForInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getUserIdByInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public List<HotPlayerPojo> findHotPlayerList(Map<String, Object> map) throws SQLException;

  /**
   * 当日注册用户数
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return int
   */
  public int userCnt(Map<String, Object> params) throws SQLException;

  List<SysLoginPojo> listPage(Map<String, Object> params) throws SQLException;

  List<SysLoginExcelPojo> getSysLoginAll2(Map<String, Object> map);

  List<SysLoginPojo> getSUserList(Map<String, Object> params) throws SQLException;
}
