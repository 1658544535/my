package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.web.dao.LoginDao;
import com.tzmb2c.web.dao.SysLoginDao;
import com.tzmb2c.web.dao.SysUserRoleDao;
import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.SysUserRolePojo;
import com.tzmb2c.web.service.LoginService;

public class LoginServiceImpl implements LoginService {
  @Autowired
  private LoginDao loginDao;
  @Autowired
  private SysLoginDao userDao;
  @Autowired
  private SysUserRoleDao sysUserRoleDao;


  @Override
  public boolean loginCheckService(SysLoginPojo loginPojo) throws Exception {
    boolean loop = false;
    SysLoginPojo loginPojoResult = loginDao.getLoginPojoByLoginnameAndPasswordAndType(loginPojo);
    if (loginPojoResult != null) {
      SysUserRolePojo sysUserRolePojo = sysUserRoleDao.getSysUserRoleByUid(loginPojoResult.getId());
      if (StringUtils.isNotEmpty(loginPojoResult.getLoginname())) {
        ActionContext actionContext = ActionContext.getContext();
        actionContext.getSession().put("user", loginPojoResult);
        actionContext.getSession().put("role", sysUserRolePojo);
        loop = true;
      }
    }
    return loop;
  }

  @Override
  public boolean loginCheckWeb(SysLoginPojo loginPojo) throws Exception {
    boolean loop = false;
    SysLoginPojo loginPojoResult = loginDao.getLoginPojoByLoginnameAndPassword(loginPojo);
    if (loginPojoResult != null) {
      if (StringUtils.isNotEmpty(loginPojoResult.getLoginname())
          && loginPojoResult.getType().equals("2")) {
        ActionContext actionContext = ActionContext.getContext();
        actionContext.getSession().put("wuser", loginPojoResult);
        loop = true;
      } else if (StringUtils.isNotEmpty(loginPojoResult.getLoginname())
          && loginPojoResult.getType().equals("12")) {
        ActionContext actionContext = ActionContext.getContext();
        actionContext.getSession().put("geekuser", loginPojoResult);
        actionContext.getSession().put("wuser", loginPojoResult);
        loop = true;
      } else if (StringUtils.isNotEmpty(loginPojoResult.getLoginname())
          && loginPojoResult.getType().equals("11")) {
        ActionContext actionContext = ActionContext.getContext();
        actionContext.getSession().put("tarentouser", loginPojoResult);
        loop = true;
      }else if (StringUtils.isNotEmpty(loginPojoResult.getLoginname())
          && !loginPojoResult.getType().equals("0")) {
        ActionContext actionContext = ActionContext.getContext();
        actionContext.getSession().put("wuser", loginPojoResult);
        loop = true;
      }
    }
    return loop;
  }

  @Override
  public SysLoginPojo getLoginPojoByLoginnameAndPassword(SysLoginPojo loginPojo) throws Exception {
    return loginDao.getLoginPojoByLoginnameAndPassword(loginPojo);
  }


  @Override
  public int insertLoginPojo(SysLoginPojo loginPojo) throws SQLException {
    return loginDao.insertLoginPojo(loginPojo);

  }

  @Override
  public SysLoginPojo getUserPojoService(Long userId) throws Exception {
    SysLoginPojo u = userDao.findSysLoginById(userId);
    return u;
  }



  @Override
  public List<MenuPojo> getMenuService(Long userId) throws Exception {
    // List<MenuPojo> list=loginDao.getMenuAllBySonLevel("A02");
    List<MenuPojo> parentMenuList = loginDao.getMenuAllByUserId(userId);

    for (MenuPojo mp : parentMenuList) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("loginId", userId + "");
      map.put("menuId", mp.getId() + "");

      mp.setMenuPojoList(loginDao.getMenuAllBySonLevel(map));
    }

    return parentMenuList;
  }



  @Override
  public void updatePassword(SysLoginPojo loginPojo) {
    loginDao.updatePassword(loginPojo);
  }

  @Override
  public SysLoginPojo getLoginPojoByLoginname(String loginname) throws Exception {
    return loginDao.getLoginPojoByLoginname(loginname);
  }

  @Override
  public void updateLoginPojo(SysLoginPojo loginPojo) throws Exception {
    loginDao.updateLoginPojo(loginPojo);
  }

  @Override
  public void updateOauthBrand(SysLoginPojo loginPojo) throws Exception {
    loginDao.updateOauthBrand(loginPojo);
  }

  /**
   * 更新百度id
   */
  @Override
  public void updateBaiduUid(SysLoginPojo loginPojo) throws SQLException {
    loginDao.updateBaiduUid(loginPojo);

  }

}
