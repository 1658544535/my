package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysUserRolePojo;

public interface SysUserRoleService {

  public List<SysUserRolePojo> sysUserRoleAllService() throws SQLException;

  public void addSysUserRoleService(SysUserRolePojo sysUserRolePojo) throws SQLException;

  public void insertSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException;

  public void updateSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException;

  public SysUserRolePojo getfindByIdSysUserRole(Long id) throws SQLException;

  public SysUserRolePojo getSysUserRoleByUid(Long uid) throws SQLException;

  public void deleteSysUserRole(Long sysUserRoleId) throws SQLException;

  public int sysUserRoleAllCount(SysUserRolePojo sysUserRoleDaoPojo);

  List<SysUserRolePojo> sysUserRoleAllList(SysUserRolePojo ticketRulePojo, Pager page);

  void sysUserRoleDeleteId(String[] tids);

  void delSysUserRole(Long id) throws SQLException;

  SysUserRolePojo findSysUserRoleById(Long merId) throws SQLException;
}
