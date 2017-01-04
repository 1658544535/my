package com.tzmb2c.web.pojo;

import java.util.List;

import maowu.framework.utils.pojo.SuperPojo;

public class MenuPojo extends SuperPojo {

  private Long id;// 用户编号
  private String name;// 菜单名称
  private String nameEn;// 菜单名称英文
  private Long level;// 级别：顶级菜单默认为：0
  private String levelName;// 父级菜单
  private String path;// 路径
  private List<MenuPojo> menuPojoList;
  private String icon;// 图标
  private Integer sorting;// 排序
  private Integer status;// 显示状态(取业务字典：0隐藏1显示)
  private Integer menuCount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameEn() {
    return nameEn;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
  }

  public String getLevelName() {
    return levelName;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<MenuPojo> getMenuPojoList() {
    return menuPojoList;
  }

  public void setMenuPojoList(List<MenuPojo> menuPojoList) {
    this.menuPojoList = menuPojoList;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getMenuCount() {
    return menuCount;
  }

  public void setMenuCount(Integer menuCount) {
    this.menuCount = menuCount;
  }

}
