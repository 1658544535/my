package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class SysRoleMenuPojo extends SuperPojo {

  private Long menuId;
  private String menuName;
  private Long roleId;
  private String roleName;
  private String menuIds;
  private Long level; // 菜单父级


  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
  }

  public String getMenuIds() {
    return menuIds;
  }

  public void setMenuIds(String menuIds) {
    this.menuIds = menuIds;
  }

  public Long getMenuId() {
    return menuId;
  }

  public void setMenuId(Long menuId) {
    this.menuId = menuId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}
