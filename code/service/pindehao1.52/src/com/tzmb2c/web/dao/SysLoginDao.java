package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HotPlayerPojo;
import com.tzmb2c.web.pojo.SysLoginExcelPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public interface SysLoginDao {

  List<SysLoginPojo> getSysLoginAll() throws SQLException;

  List<SysLoginPojo> getSysLoginMF() throws SQLException;

  void insertSysLogin(SysLoginPojo sysLoginPojo) throws SQLException;

  int updateSysLogin(SysLoginPojo sysLoginPojo) throws SQLException;

  SysLoginPojo getfindByIdSysLogin(Long id) throws SQLException;

  void deleteSysLogin(Long id) throws SQLException;

  int sysLoginAllCount(Map<String, Object> map);

  public int sysLoginAllCountByYoumi(Map<String, Object> map);

  List<SysLoginPojo> sysLoginAllList(Map<String, Object> map);

  void delSysLogin(Long id) throws SQLException;

  SysLoginPojo findSysLoginById(Long id) throws SQLException;

  public int getfindCountByIdSysLogin(Long id) throws SQLException;

  void updatePassword(SysLoginPojo sysLoginPojo) throws SQLException;

  SysLoginPojo getLoginPojoByLoginIdAndLoginPd(SysLoginPojo loginPojo) throws SQLException;

  SysLoginPojo findSysLoginByLoginname(SysLoginPojo sysLoginPojo) throws SQLException;

  public int findSysLoginCountByLoginname(SysLoginPojo sysLoginPojo) throws SQLException;

  void updateStatus(SysLoginPojo sysLoginPojo) throws SQLException;

  SysLoginPojo sysLoginFindId(Map<String, Object> map) throws SQLException;

  void updateType(SysLoginPojo sysLoginPojo) throws SQLException;

  SysLoginPojo getSysLoginByLoginName(String loginname) throws SQLException;

  public List<SysLoginPojo> getSysLoginByName(String name) throws SQLException;

  public int getSysLoginCountByName(String name) throws SQLException;

  public List<SysLoginPojo> getSysLoginByTaobao_user_id(String id) throws SQLException;

  public List<SysLoginPojo> getSysLoginByOpenId(String id) throws SQLException;

  public List<Long> getAllUserId() throws SQLException;

  List<SysLoginPojo> sysLoginAllLists(Map<String, Object> map) throws SQLException;

  public void updateUserInfoById(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getUserInfoById(Long id) throws SQLException;

  public SysLoginPojo qrySysLoginByThirdId(Map<String, Object> map) throws SQLException;

  public List<SysLoginPojo> getExternalCodeNullList() throws SQLException;

  public void updateSysLoginForExternalCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public List<SysLoginPojo> getInvitationCodeNullList() throws SQLException;

  public void updateSysloginForInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public SysLoginPojo getUserIdByInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException;

  public List<HotPlayerPojo> findHotPlayerList(Map<String, Object> map) throws SQLException;

  public int userCnt(Map<String, Object> params) throws SQLException;

  List<SysLoginPojo> listPage(Map<String, Object> params) throws SQLException;

  List<SysLoginExcelPojo> getSysLoginAll2(Map<String, Object> map);

  List<SysLoginPojo> getSUserList(Map<String, Object> params) throws SQLException;
}
