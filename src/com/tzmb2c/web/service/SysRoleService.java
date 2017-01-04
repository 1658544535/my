package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysRolePojo;

public interface SysRoleService {

  public List<SysRolePojo> sysRoleAllService() throws SQLException;

  public void addSysRoleService(SysRolePojo sysRolePojo) throws SQLException;

  public void insertSysRole(SysRolePojo sysRolePojo) throws SQLException;

  public void updateSysRole(SysRolePojo sysRolePojo) throws SQLException;

  public SysRolePojo getfindByIdSysRole(Long id) throws SQLException;

  public void deleteSysRole(Long sysRoleId) throws SQLException;

  public int sysRoleAllCount(SysRolePojo sysRoleDaoPojo);

  List<SysRolePojo> sysRoleAllList(SysRolePojo ticketRulePojo, Pager page);

  void sysRoleDeleteId(String[] tids);

  void delSysRole(Long id) throws SQLException;

  SysRolePojo findSysRoleById(Long merId) throws SQLException;
}
