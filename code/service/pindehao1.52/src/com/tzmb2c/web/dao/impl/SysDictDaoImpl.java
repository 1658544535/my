package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysDictDao;
import com.tzmb2c.web.mapper.SysDictMapper;
import com.tzmb2c.web.pojo.SysDictPojo;

public class SysDictDaoImpl implements SysDictDao {

  @Autowired
  private SysDictMapper sysDictMapper;

  @Override
  public List<SysDictPojo> getSysDictAll() throws SQLException {
    return sysDictMapper.getSysDictAll();
  }

  @Override
  public void insertSysDict(SysDictPojo sysDictPojo) throws SQLException {
    sysDictMapper.insertSysDict(sysDictPojo);

  }

  @Override
  public void updateSysDict(SysDictPojo sysDictPojo) throws SQLException {

    sysDictMapper.updateSysDict(sysDictPojo);
  }

  @Override
  public SysDictPojo getfindByIdSysDict(Long id) throws SQLException {
    return sysDictMapper.getfindByIdSysDict(id);

  }

  @Override
  public void deleteSysDict(Long id) throws SQLException {
    sysDictMapper.deleteSysDict(id);
  }

  @Override
  public int sysDictAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysDictMapper.sysDictAllCount(map);
  }

  @Override
  public List<SysDictPojo> sysDictAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysDictMapper.sysDictAllList(map);
  }

  @Override
  public void delSysDict(Long id) throws SQLException {

    sysDictMapper.delSysDict(id);
  }

  @Override
  public SysDictPojo findSysDictById(Long id) throws SQLException {
    return sysDictMapper.getfindByIdSysDict(id);
  }

  @Override
  public List<SysDictPojo> getSysDictListByType(String type) throws Exception {
    // TODO Auto-generated method stub
    return sysDictMapper.getSysDictListByType(type);
  }

  @Override
  public SysDictPojo getSysDictByTypeAndValue(SysDictPojo sysDictPojo) throws Exception {
    // TODO Auto-generated method stub
    return sysDictMapper.getSysDictByTypeAndValue(sysDictPojo);
  }
}
