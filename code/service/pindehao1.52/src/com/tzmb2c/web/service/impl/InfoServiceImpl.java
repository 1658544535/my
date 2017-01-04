package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.InfoDao;
import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.service.InfoService;

public class InfoServiceImpl implements InfoService {

  @Autowired
  private InfoDao infoDao;

  @Override
  public void addInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    this.infoDao.addInfo(infoPojo);
  }

  @Override
  public InfoPojo findInfoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return infoDao.findInfoById(id);
  }

  @Override
  public void updateInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoDao.updateInfo(infoPojo);
  }

  @Override
  public void delInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoDao.delInfo(infoPojo);
  }

  @Override
  public void verifyInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoDao.verifyInfo(infoPojo);
  }

  @Override
  public List<InfoPojo> infoAllList(InfoPojo infoPojo, Pager page, Integer status) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (infoPojo != null) {
      map.put("infoTitle", infoPojo.getTitle());// 查询的标题
      map.put("infoType", infoPojo.getType());// 查询的类型
      map.put("status", infoPojo.getStatus());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (status != null) {
      map.put("status", status);
    }

    List<InfoPojo> list = infoDao.infoAllList(map);
    return list;
  }

  @Override
  public int infoAllCount(InfoPojo infoPojo) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (infoPojo != null) {
      map.put("infoTitle", infoPojo.getTitle());// 查询的标题
      map.put("infoType", infoPojo.getType());// 查询的类型
      map.put("status", infoPojo.getStatus());
    }
    return infoDao.infoAllCount(map);
  }

  @Override
  public void delAllInfoById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        infoDao.delAllInfo(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkAllInfoById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        infoDao.checkAllInfo(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<InfoPojo> infoPageList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    // Map<String, Object> map = new HashMap<String, Object>();
    // if (page != null) {
    // map.put("pageSize", page.getPageSize());
    // map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    // }
    List<InfoPojo> list = infoDao.infoPageList(map);
    if (list != null) {
      // 判断信息是否被审核
    }
    return list;
  }

  @Override
  public void uncheckAllInfoById(String[] tids) throws SQLException {
    for (String tid : tids) {
      try {
        infoDao.uncheckAllInfoById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }

  }

  @Override
  public void uncheckinfo(InfoPojo infoPojo) throws SQLException {
    infoDao.uncheckinfo(infoPojo);
  }

  @Override
  public List<InfoPojo> getRandomList() throws SQLException {
    // TODO Auto-generated method stub
    return infoDao.getRandomList();
  }

  @Override
  public int infoTypeCount(Map<String, Object> map) throws SQLException {
    return infoDao.infoTypeCount(map);
  }

}
