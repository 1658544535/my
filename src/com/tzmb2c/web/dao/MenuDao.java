package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.MenuPojo;

public interface MenuDao {

  List<MenuPojo> getMenuPojoByMenuLevel(Long level) throws SQLException;

  List<MenuPojo> getMenuPojoByUserId(Long userId) throws SQLException;

  List<MenuPojo> getMenuAll() throws SQLException;

  MenuPojo getMenuCountByMenuLevel(Long level) throws SQLException;

  void insertMenu(MenuPojo menuPojo) throws SQLException;

  void updateMenu(MenuPojo menuPojo) throws SQLException;

  MenuPojo getfindByIdMenu(Long id) throws SQLException;

  void deleteMenu(Long id) throws SQLException;

}
