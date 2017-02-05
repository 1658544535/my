package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysLoginDao;
import com.tzmb2c.web.mapper.SysLoginMapper;
import com.tzmb2c.web.pojo.HotPlayerPojo;
import com.tzmb2c.web.pojo.SysLoginExcelPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public class SysLoginDaoImpl implements SysLoginDao {

  @Autowired
  private SysLoginMapper sysLoginMapper;

  @Override
  public List<SysLoginPojo> getSysLoginAll() throws SQLException {
    return sysLoginMapper.getSysLoginAll();
  }

  @Override
  public List<SysLoginPojo> getSysLoginMF() throws SQLException {
    return sysLoginMapper.getSysLoginMF();
  }

  @Override
  public void insertSysLogin(SysLoginPojo sysLoginPojo) throws SQLException {
    try {
      sysLoginMapper.insertSysLogin(sysLoginPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public int updateSysLogin(SysLoginPojo sysLoginPojo) throws SQLException {

    return sysLoginMapper.updateSysLogin(sysLoginPojo);
  }

  @Override
  public void updatePassword(SysLoginPojo sysLoginPojo) throws SQLException {

    sysLoginMapper.updatePassword(sysLoginPojo);
  }

  @Override
  public void updateStatus(SysLoginPojo sysLoginPojo) throws SQLException {

    sysLoginMapper.updateStatus(sysLoginPojo);
  }

  @Override
  public void updateType(SysLoginPojo sysLoginPojo) throws SQLException {

    sysLoginMapper.updateType(sysLoginPojo);
  }

  @Override
  public SysLoginPojo getfindByIdSysLogin(Long id) throws SQLException {
    return sysLoginMapper.getfindByIdSysLogin(id);

  }

  @Override
  public void deleteSysLogin(Long id) throws SQLException {
    sysLoginMapper.deleteSysLogin(id);
  }

  @Override
  public int sysLoginAllCount(Map<String, Object> map) {

    return sysLoginMapper.sysLoginAllCount(map);
  }

  @Override
  public List<SysLoginPojo> sysLoginAllList(Map<String, Object> map) {

    return sysLoginMapper.sysLoginAllList(map);
  }

  @Override
  public List<SysLoginPojo> sysLoginAllLists(Map<String, Object> map) {

    return sysLoginMapper.sysLoginAllList(map);
  }

  @Override
  public void delSysLogin(Long id) throws SQLException {

    sysLoginMapper.delSysLogin(id);
  }

  @Override
  public SysLoginPojo findSysLoginById(Long id) throws SQLException {
    return sysLoginMapper.getfindByIdSysLogin(id);
  }

  @Override
  public SysLoginPojo getLoginPojoByLoginIdAndLoginPd(SysLoginPojo loginPojo) throws SQLException {
    return sysLoginMapper.getLoginPojoByLoginIdAndLoginPd(loginPojo);
  }

  @Override
  public SysLoginPojo findSysLoginByLoginname(SysLoginPojo sysLoginPojo) throws SQLException {
    return sysLoginMapper.findSysLoginByLoginname(sysLoginPojo);
  }

  @Override
  public SysLoginPojo sysLoginFindId(Map<String, Object> map) throws SQLException {

    return sysLoginMapper.sysLoginFindId(map);
  }

  @Override
  public SysLoginPojo getSysLoginByLoginName(String loginname) throws SQLException {

    return sysLoginMapper.getSysLoginByLoginName(loginname);
  }

  @Override
  public List<SysLoginPojo> getSysLoginByName(String name) throws SQLException {

    return sysLoginMapper.getSysLoginByName(name);
  }

  @Override
  public List<SysLoginPojo> getSysLoginByTaobao_user_id(String id) throws SQLException {

    return sysLoginMapper.getSysLoginByTaobao_user_id(id);
  }

  @Override
  public List<SysLoginPojo> getSysLoginByOpenId(String id) throws SQLException {

    return sysLoginMapper.getSysLoginByOpenId(id);
  }

  @Override
  public List<Long> getAllUserId() throws SQLException {
    return sysLoginMapper.getAllUserId();
  }

  @Override
  public void updateUserInfoById(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginMapper.updateUserInfoById(sysLoginPojo);

  }

  @Override
  public SysLoginPojo getUserInfoById(Long id) throws SQLException {
    return sysLoginMapper.getUserInfoById(id);
  }

  @Override
  public SysLoginPojo qrySysLoginByThirdId(Map<String, Object> map) throws SQLException {
    return sysLoginMapper.qrySysLoginByThirdId(map);
  }

  @Override
  public List<SysLoginPojo> getExternalCodeNullList() throws SQLException {

    return sysLoginMapper.getExternalCodeNullList();
  }

  @Override
  public void updateSysLoginForExternalCode(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginMapper.updateSysloginForExternalCode(sysLoginPojo);

  }

  @Override
  public List<SysLoginPojo> getInvitationCodeNullList() throws SQLException {

    return sysLoginMapper.getInvitationCodeNullList();
  }

  @Override
  public void updateSysloginForInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException {
    sysLoginMapper.updateSysloginForInvitationCode(sysLoginPojo);

  }

  @Override
  public SysLoginPojo getUserIdByInvitationCode(SysLoginPojo sysLoginPojo) throws SQLException {
    // TODO Auto-generated method stub
    return sysLoginMapper.getUserIdByInvitationCode(sysLoginPojo);
  }

  @Override
  public int getfindCountByIdSysLogin(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return sysLoginMapper.getfindCountByIdSysLogin(id);
  }

  @Override
  public int getSysLoginCountByName(String name) throws SQLException {
    // TODO Auto-generated method stub
    return sysLoginMapper.getSysLoginCountByName(name);
  }

  @Override
  public int findSysLoginCountByLoginname(SysLoginPojo sysLoginPojo) throws SQLException {
    // TODO Auto-generated method stub
    return sysLoginMapper.findSysLoginCountByLoginname(sysLoginPojo);
  }

  @Override
  public int sysLoginAllCountByYoumi(Map<String, Object> map) {
    return sysLoginMapper.sysLoginAllCountByYoumi(map);
  }

  @Override
  public List<HotPlayerPojo> findHotPlayerList(Map<String, Object> map) throws SQLException {
    return sysLoginMapper.findHotPlayerList(map);
  }

  @Override
  public int userCnt(Map<String, Object> params) throws SQLException {
    return sysLoginMapper.userCnt(params);
  }

  @Override
  public List<SysLoginPojo> listPage(Map<String, Object> params) throws SQLException {
    return sysLoginMapper.listPage(params);
  }

  @Override
  public List<SysLoginExcelPojo> getSysLoginAll2(Map<String, Object> map) {
    return sysLoginMapper.getSysLoginAll2(map);
  }

  @Override
  public List<SysLoginPojo> getSUserList(Map<String, Object> params) throws SQLException {
    return sysLoginMapper.getSUserList(params);
  }
}
