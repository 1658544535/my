package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MenuDao;
import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.service.MenuService;

public class MenuServiceImpl implements MenuService {
  @Autowired
  private MenuDao menuDao;

  public void setMenuDao(MenuDao menuDao) {
    this.menuDao = menuDao;
  }


  @Override
  public List<MenuPojo> menuAllService() throws SQLException {
    return menuDao.getMenuAll();
  }


  @Override
  public List<MenuPojo> menuLevelService(Long level) throws SQLException {
    return menuDao.getMenuPojoByMenuLevel(level);
  }


  @Override
  public void addMenuService(MenuPojo menuPojo) throws SQLException {

    /*
     * if("0".equals(menuPojo.getMenuLevel())){ MenuPojo rsMenuPojo =
     * menuDao.getMenuCountByMenuLevel(menuPojo.getLevel()); int menuId =0;
     * if(rsMenuPojo.getMenuCount() > -1 && rsMenuPojo.getMenuCount() <= 9){ menuId =
     * rsMenuPojo.getMenuCount()+1; menuPojo.setMenuId("A0" + menuId); }else
     * if(rsMenuPojo.getMenuCount() > 9 && rsMenuPojo.getMenuCount() <= 99){ menuId =
     * rsMenuPojo.getMenuCount()+1; menuPojo.setMenuId("A" +menuId ); } //menuPojo.setMenuPath("#");
     * }else{ MenuPojo rsMenuPojo = menuDao.getMenuCountByMenuLevel(menuPojo.getLevel());
     * if(rsMenuPojo.getMenuCount() > -1 && rsMenuPojo.getMenuCount() <= 9){
     * menuPojo.setMenuId(menuPojo.getLevel()+ "B0" + (rsMenuPojo.getMenuCount() + 1)); }else
     * if(rsMenuPojo.getMenuCount() > 9 && rsMenuPojo.getMenuCount() <= 99){
     * menuPojo.setMenuId(menuPojo.getLevel()+ "B" + (rsMenuPojo.getMenuCount() + 1)); } }
     */
    menuDao.insertMenu(menuPojo);
  }


  @Override
  public void insertMenu(MenuPojo menuPojo) throws SQLException {

    menuDao.insertMenu(menuPojo);
  }

  @Override
  public void updateMenu(MenuPojo menuPojo) throws SQLException {
    menuDao.updateMenu(menuPojo);

  }

  @Override
  public MenuPojo getfindByIdMenu(Long id) throws SQLException {
    return menuDao.getfindByIdMenu(id);

  }

  @Override
  public void deleteMenu(Long id) throws SQLException {

    menuDao.deleteMenu(id);
  }
}
