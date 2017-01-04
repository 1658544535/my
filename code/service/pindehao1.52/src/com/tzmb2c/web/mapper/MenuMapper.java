package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.MenuPojo;

public interface MenuMapper {

  public List<MenuPojo> getMenuPojoByUserId(Long userId) throws SQLException;

  public List<MenuPojo> getMenuPojoByMenuLevel(Long level) throws SQLException;

  public List<MenuPojo> getMenuAll() throws SQLException;

  public MenuPojo getMenuCountByMenuLevel(Long level) throws SQLException;

  public void insertMenu(MenuPojo menuPojo) throws SQLException;

  public void updateMenu(MenuPojo menuPojo) throws SQLException;

  public MenuPojo getfindByIdMenu(Long id) throws SQLException;

  public void deleteMenu(Long id) throws SQLException;

}
