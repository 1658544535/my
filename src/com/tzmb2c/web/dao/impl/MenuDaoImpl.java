package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MenuDao;
import com.tzmb2c.web.mapper.MenuMapper;
import com.tzmb2c.web.pojo.MenuPojo;

public class MenuDaoImpl implements MenuDao {

  @Autowired
  private MenuMapper menuMapper;

  @Override
  public List<MenuPojo> getMenuPojoByMenuLevel(Long level) throws SQLException {
    return menuMapper.getMenuPojoByMenuLevel(level);
  }

  @Override
  public List<MenuPojo> getMenuPojoByUserId(Long userId) throws SQLException {
    return menuMapper.getMenuPojoByUserId(userId);
  }

  @Override
  public List<MenuPojo> getMenuAll() throws SQLException {
    return menuMapper.getMenuAll();
  }

  @Override
  public MenuPojo getMenuCountByMenuLevel(Long level) throws SQLException {
    return menuMapper.getMenuCountByMenuLevel(level);
  }

  @Override
  public void insertMenu(MenuPojo menuPojo) throws SQLException {
    try {
      menuMapper.insertMenu(menuPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateMenu(MenuPojo menuPojo) throws SQLException {

    menuMapper.updateMenu(menuPojo);
  }

  @Override
  public MenuPojo getfindByIdMenu(Long id) throws SQLException {
    return menuMapper.getfindByIdMenu(id);

  }

  @Override
  public void deleteMenu(Long id) throws SQLException {
    List<MenuPojo> menuList = menuMapper.getMenuPojoByMenuLevel(id);
    if (menuList.size() != 0) {

      for (MenuPojo menu : menuList) {
        menuMapper.deleteMenu(menu.getId());
      }
    }

    menuMapper.deleteMenu(id);
  }
}
