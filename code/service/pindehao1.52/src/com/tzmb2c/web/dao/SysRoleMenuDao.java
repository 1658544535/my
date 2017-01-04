package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.SysRoleMenuPojo;

public interface SysRoleMenuDao {

  List<SysRoleMenuPojo> getMenuRoleAll();

  void insertMenuRole(SysRoleMenuPojo menuRolePojo);

  List<SysRoleMenuPojo> getMenuRoleByRoleid(Long roleid) throws SQLException;

  public void deleMenuRoleByMenuidAndRoleId(SysRoleMenuPojo menuRolePojo) throws SQLException;

  public void deleMenuRoleByRoleId(Long roleId) throws SQLException;
}
