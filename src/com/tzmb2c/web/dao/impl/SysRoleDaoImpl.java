package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysRoleDao;
import com.tzmb2c.web.mapper.SysRoleMapper;
import com.tzmb2c.web.pojo.SysRolePojo;

public class SysRoleDaoImpl implements SysRoleDao {

  @Autowired
  private SysRoleMapper sysRoleMapper;

  @Override
  public List<SysRolePojo> getSysRoleAll() throws SQLException {
    return sysRoleMapper.getSysRoleAll();
  }

  @Override
  public void insertSysRole(SysRolePojo sysRolePojo) throws SQLException {
    try {
      sysRoleMapper.insertSysRole(sysRolePojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateSysRole(SysRolePojo sysRolePojo) throws SQLException {

    sysRoleMapper.updateSysRole(sysRolePojo);
  }

  @Override
  public SysRolePojo getfindByIdSysRole(Long id) throws SQLException {
    return sysRoleMapper.getfindByIdSysRole(id);

  }

  @Override
  public void deleteSysRole(Long id) throws SQLException {
    sysRoleMapper.deleteSysRole(id);
  }

  @Override
  public int sysRoleAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysRoleMapper.sysRoleAllCount(map);
  }

  @Override
  public List<SysRolePojo> sysRoleAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysRoleMapper.sysRoleAllList(map);
  }

  @Override
  public void delSysRole(Long id) throws SQLException {

    sysRoleMapper.delSysRole(id);
  }

  @Override
  public SysRolePojo findSysRoleById(Long id) throws SQLException {
    return sysRoleMapper.getfindByIdSysRole(id);
  }
}
