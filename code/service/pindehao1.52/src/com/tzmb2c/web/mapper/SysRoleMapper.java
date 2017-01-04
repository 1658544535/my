package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysRolePojo;

public interface SysRoleMapper {


  public List<SysRolePojo> getSysRoleAll() throws SQLException;

  public void insertSysRole(SysRolePojo sysRolePojo) throws SQLException;

  public void updateSysRole(SysRolePojo sysRolePojo) throws SQLException;

  public SysRolePojo getfindByIdSysRole(Long id) throws SQLException;

  public void deleteSysRole(Long id) throws SQLException;

  public int sysRoleAllCount(Map<String, Object> map);

  public List<SysRolePojo> sysRoleAllList(Map<String, Object> map);

  void delSysRole(Long id) throws SQLException;

}
