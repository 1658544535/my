package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.SysRoleMenuPojo;

public interface SysRoleMenuMapper {

  List<SysRoleMenuPojo> getMenuRoleAll();

  void insertMenuRole(SysRoleMenuPojo menuRolePojo);

  List<SysRoleMenuPojo> getMenuRoleByRoleid(Long roleid);

  void deleMenuRoleByMenuidAndRoleId(SysRoleMenuPojo menuRolePojo) throws SQLException;

  void deleMenuRoleByRoleId(Long roleId) throws SQLException;

}
