package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysUserRolePojo;

public interface SysUserRoleDao {

  List<SysUserRolePojo> getSysUserRoleAll() throws SQLException;

  void insertSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException;

  void updateSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException;

  SysUserRolePojo getfindByIdSysUserRole(Long id) throws SQLException;

  public SysUserRolePojo getSysUserRoleByUid(Long uid) throws SQLException;

  void deleteSysUserRole(Long id) throws SQLException;

  int sysUserRoleAllCount(Map<String, Object> map);

  List<SysUserRolePojo> sysUserRoleAllList(Map<String, Object> map);

  void delSysUserRole(Long id) throws SQLException;

  SysUserRolePojo findSysUserRoleById(Long id) throws SQLException;

}
