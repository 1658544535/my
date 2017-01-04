package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysDictDao;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.SysDictService;

public class SysDictServiceImpl implements SysDictService {
  @Autowired
  private SysDictDao sysDictDao;

  public void setSysDictDao(SysDictDao sysDictDao) {
    this.sysDictDao = sysDictDao;
  }

  @Override
  public List<SysDictPojo> getSysDictListByType(String type) throws Exception {

    return sysDictDao.getSysDictListByType(type);
  }

  @Override
  public List<SysDictPojo> sysDictAllService() throws SQLException {
    return sysDictDao.getSysDictAll();
  }



  @Override
  public void addSysDictService(SysDictPojo sysDictPojo) throws SQLException {

    sysDictDao.insertSysDict(sysDictPojo);
  }


  @Override
  public void insertSysDict(SysDictPojo sysDictPojo) throws SQLException {

    sysDictDao.insertSysDict(sysDictPojo);
  }

  @Override
  public void updateSysDict(SysDictPojo sysDictPojo) throws SQLException {
    sysDictDao.updateSysDict(sysDictPojo);

  }

  @Override
  public SysDictPojo getfindByIdSysDict(Long id) throws SQLException {
    return sysDictDao.getfindByIdSysDict(id);

  }

  @Override
  public void deleteSysDict(Long id) throws SQLException {

    sysDictDao.deleteSysDict(id);
  }

  @Override
  public int sysDictAllCount(SysDictPojo sysDictDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysDictDaoPojo != null) {
      map.put("name", sysDictDaoPojo.getName());
      map.put("nameEn", sysDictDaoPojo.getNameEn());
      map.put("type", sysDictDaoPojo.getType());
    }
    return sysDictDao.sysDictAllCount(map);
  }

  @Override
  public List<SysDictPojo> sysDictAllList(SysDictPojo sysDictDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (sysDictDaoPojo != null) {
      map.put("name", sysDictDaoPojo.getName());
      map.put("nameEn", sysDictDaoPojo.getNameEn());
      map.put("type", sysDictDaoPojo.getType());
      map.put("shipin", sysDictDaoPojo.getShipin());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SysDictPojo> list = sysDictDao.sysDictAllList(map);

    return list;
  }

  @Override
  public void sysDictDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        sysDictDao.delSysDict(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delSysDict(Long id) throws SQLException {
    sysDictDao.delSysDict(id);
  }

  @Override
  public SysDictPojo findSysDictById(Long id) throws SQLException {

    return sysDictDao.findSysDictById(id);

  }

  @Override
  public SysDictPojo getSysDictByTypeAndValue(SysDictPojo sysDictPojo) throws Exception {
    // TODO Auto-generated method stub
    return sysDictDao.getSysDictByTypeAndValue(sysDictPojo);
  }
}
