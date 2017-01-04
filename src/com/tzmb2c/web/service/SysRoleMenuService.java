package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.SysRoleMenuPojo;

public interface SysRoleMenuService {

  List<SysRoleMenuPojo> menuRoleAllService();

  void addMenuRoleService(SysRoleMenuPojo menuRolePojo);

  List<SysRoleMenuPojo> getMenuRoleByRoleid(Long roleid) throws SQLException;

  void deleMenuRoleByMenuidAndRoleId(SysRoleMenuPojo menuRolePojo) throws SQLException;

  void deleMenuRoleByRoleId(Long roleId) throws SQLException;


}
