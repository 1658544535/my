package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysRoleMenuDao;
import com.tzmb2c.web.mapper.SysRoleMenuMapper;
import com.tzmb2c.web.pojo.SysRoleMenuPojo;

public class SysRoleMenuDaoImpl implements SysRoleMenuDao {

  @Autowired
  private SysRoleMenuMapper menuRoleMapper;

  @Override
  public List<SysRoleMenuPojo> getMenuRoleAll() {
    return menuRoleMapper.getMenuRoleAll();
  }

  @Override
  public void insertMenuRole(SysRoleMenuPojo menuRolePojo) {
    menuRoleMapper.insertMenuRole(menuRolePojo);

  }

  @Override
  public List<SysRoleMenuPojo> getMenuRoleByRoleid(Long roleid) throws SQLException {

    return menuRoleMapper.getMenuRoleByRoleid(roleid);
  }

  @Override
  public void deleMenuRoleByMenuidAndRoleId(SysRoleMenuPojo menuRolePojo) throws SQLException {
    // TODO Auto-generated method stub
    menuRoleMapper.deleMenuRoleByMenuidAndRoleId(menuRolePojo);
  }

  @Override
  public void deleMenuRoleByRoleId(Long roleId) throws SQLException {
    menuRoleMapper.deleMenuRoleByRoleId(roleId);
  }


}
