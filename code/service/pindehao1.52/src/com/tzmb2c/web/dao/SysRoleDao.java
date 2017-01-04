package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysRolePojo;

public interface SysRoleDao {

  List<SysRolePojo> getSysRoleAll() throws SQLException;

  void insertSysRole(SysRolePojo sysRolePojo) throws SQLException;

  void updateSysRole(SysRolePojo sysRolePojo) throws SQLException;

  SysRolePojo getfindByIdSysRole(Long id) throws SQLException;

  void deleteSysRole(Long id) throws SQLException;

  int sysRoleAllCount(Map<String, Object> map);

  List<SysRolePojo> sysRoleAllList(Map<String, Object> map);

  void delSysRole(Long id) throws SQLException;

  SysRolePojo findSysRoleById(Long id) throws SQLException;

}
