package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysSecurityQuestionDao;
import com.tzmb2c.web.pojo.SysSecurityQuestionPojo;
import com.tzmb2c.web.service.SysSecurityQuestionService;

public class SysSecurityQuestionServiceImpl implements SysSecurityQuestionService {

  @Autowired
  private SysSecurityQuestionDao sysSecurityQuestionDao;

  @Override
  public List<SysSecurityQuestionPojo> sysSecurityQuestionAllList(
      SysSecurityQuestionPojo sysSecurityQuestionPojo, Pager page, String type) throws SQLException {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysSecurityQuestionPojo != null) {
      // map.put("userId", accountSecurityPojo.getUserId());// 用户ID
      // map.put("userName", accountSecurityPojo.getUserName());// 用户昵称
    }
    map.put("type", type);// 用户类型
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SysSecurityQuestionPojo> list = sysSecurityQuestionDao.sysSecurityQuestionAllList(map);
    if (list != null) {
      // 判断信息是否被审核
    }
    return list;
  }

  @Override
  public int sysSecurityQuestionAllCount(SysSecurityQuestionPojo sysSecurityQuestionPojo,
      String type) throws SQLException {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysSecurityQuestionPojo != null) {
      // map.put("userId", accountSecurityPojo.getUserId());// 用户ID
      // map.put("userName", accountSecurityPojo.getUserName());// 用户昵称
    }
    map.put("type", type);// 用户类型
    return sysSecurityQuestionDao.sysSecurityQuestionAllCount(map);
  }

  @Override
  public void delSysSecurityQuestion(SysSecurityQuestionPojo sysSecurityQuestionPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    sysSecurityQuestionDao.delSysSecurityQuestion(sysSecurityQuestionPojo);
  }

  @Override
  public void delAllSysSecurityQuestionById(String[] tids) throws SQLException {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        sysSecurityQuestionDao.delAllSysSecurityQuestionById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<SysSecurityQuestionPojo> sysSecurityQuestionListWeb(
      SysSecurityQuestionPojo sysSecurityQuestionPojo) throws SQLException {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    List<SysSecurityQuestionPojo> list = sysSecurityQuestionDao.sysSecurityQuestionListWeb(map);
    return list;
  }

}
