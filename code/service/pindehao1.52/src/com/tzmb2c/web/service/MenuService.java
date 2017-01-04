package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.MenuPojo;

public interface MenuService {

  public List<MenuPojo> menuAllService() throws SQLException;

  public List<MenuPojo> menuLevelService(Long menuLevel) throws SQLException;

  public void addMenuService(MenuPojo menuPojo) throws SQLException;

  public void insertMenu(MenuPojo menuPojo) throws SQLException;

  public void updateMenu(MenuPojo menuPojo) throws SQLException;

  public MenuPojo getfindByIdMenu(Long id) throws SQLException;

  public void deleteMenu(Long menuId) throws SQLException;
}
