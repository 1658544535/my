package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysUserRolePojo;

public interface SysUserRoleMapper {


  public List<SysUserRolePojo> getSysUserRoleAll() throws SQLException;

  public void insertSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException;

  public void updateSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException;

  public SysUserRolePojo getfindByIdSysUserRole(Long id) throws SQLException;

  public SysUserRolePojo getSysUserRoleByUid(Long uid) throws SQLException;

  public void deleteSysUserRole(Long id) throws SQLException;

  public int sysUserRoleAllCount(Map<String, Object> map);

  public List<SysUserRolePojo> sysUserRoleAllList(Map<String, Object> map);

  void delSysUserRole(Long id) throws SQLException;

}
