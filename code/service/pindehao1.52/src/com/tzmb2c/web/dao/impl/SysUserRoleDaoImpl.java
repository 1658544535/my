package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysUserRoleDao;
import com.tzmb2c.web.mapper.SysUserRoleMapper;
import com.tzmb2c.web.pojo.SysUserRolePojo;

public class SysUserRoleDaoImpl implements SysUserRoleDao {

  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;

  @Override
  public List<SysUserRolePojo> getSysUserRoleAll() throws SQLException {
    return sysUserRoleMapper.getSysUserRoleAll();
  }

  @Override
  public void insertSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException {
    try {
      sysUserRoleMapper.insertSysUserRole(sysUserRolePojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException {

    sysUserRoleMapper.updateSysUserRole(sysUserRolePojo);
  }

  @Override
  public SysUserRolePojo getfindByIdSysUserRole(Long id) throws SQLException {
    return sysUserRoleMapper.getfindByIdSysUserRole(id);

  }

  @Override
  public void deleteSysUserRole(Long id) throws SQLException {
    sysUserRoleMapper.deleteSysUserRole(id);
  }

  @Override
  public int sysUserRoleAllCount(Map<String, Object> map) {

    return sysUserRoleMapper.sysUserRoleAllCount(map);
  }

  @Override
  public List<SysUserRolePojo> sysUserRoleAllList(Map<String, Object> map) {

    return sysUserRoleMapper.sysUserRoleAllList(map);
  }

  @Override
  public void delSysUserRole(Long id) throws SQLException {

    sysUserRoleMapper.delSysUserRole(id);
  }

  @Override
  public SysUserRolePojo findSysUserRoleById(Long id) throws SQLException {
    return sysUserRoleMapper.getfindByIdSysUserRole(id);
  }

  @Override
  public SysUserRolePojo getSysUserRoleByUid(Long uid) throws SQLException {

    return sysUserRoleMapper.getSysUserRoleByUid(uid);
  }
}
