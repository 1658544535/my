package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysRoleMenuDao;
import com.tzmb2c.web.pojo.SysRoleMenuPojo;
import com.tzmb2c.web.service.SysRoleMenuService;

public class SysRoleMenuServiceImpl implements SysRoleMenuService {

  @Autowired
  private SysRoleMenuDao menuRoleDao;


  public void setMenuRoleDao(SysRoleMenuDao menuRoleDao) {
    this.menuRoleDao = menuRoleDao;
  }

  @Override
  public List<SysRoleMenuPojo> menuRoleAllService() {
    return menuRoleDao.getMenuRoleAll();
  }

  @Override
  public void addMenuRoleService(SysRoleMenuPojo menuRolePojo) {
    menuRoleDao.insertMenuRole(menuRolePojo);

  }

  @Override
  public List<SysRoleMenuPojo> getMenuRoleByRoleid(Long roleid) throws SQLException {
    return menuRoleDao.getMenuRoleByRoleid(roleid);
  }

  @Override
  public void deleMenuRoleByMenuidAndRoleId(SysRoleMenuPojo menuRolePojo) throws SQLException {
    menuRoleDao.deleMenuRoleByMenuidAndRoleId(menuRolePojo);
  }

  @Override
  public void deleMenuRoleByRoleId(Long roleId) throws SQLException {
    menuRoleDao.deleMenuRoleByRoleId(roleId);
  }

}
