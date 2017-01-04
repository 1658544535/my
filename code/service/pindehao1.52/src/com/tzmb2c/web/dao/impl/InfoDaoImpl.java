package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.InfoDao;
import com.tzmb2c.web.mapper.InfoMapper;
import com.tzmb2c.web.pojo.InfoPojo;

public class InfoDaoImpl implements InfoDao {

  @Autowired
  private InfoMapper infoMapper;

  @Override
  public void addInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.addInfo(infoPojo);
  }

  @Override
  public InfoPojo findInfoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return infoMapper.findInfoById(id);
  }

  @Override
  public void updateInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.updateInfo(infoPojo);
  }

  @Override
  public void delInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.delInfo(infoPojo);
  }

  @Override
  public void verifyInfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.verifyInfo(infoPojo);
  }

  @Override
  public void delAllInfo(String id) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.delAllInfoById(id);
  }

  @Override
  public void checkAllInfo(String id) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.checkAllInfoById(id);
  }

  @Override
  public List<InfoPojo> infoAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return infoMapper.infoAllList(map);
  }

  @Override
  public int infoAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return infoMapper.infoAllCount(map);
  }

  @Override
  public List<InfoPojo> infoPageList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return infoMapper.infoPageList(map);
  }

  @Override
  public void uncheckAllInfoById(String id) throws SQLException {
    infoMapper.uncheckAllInfoById(id);

  }

  @Override
  public void uncheckinfo(InfoPojo infoPojo) throws SQLException {
    // TODO Auto-generated method stub
    infoMapper.uncheckinfo(infoPojo);

  }

  @Override
  public List<InfoPojo> getRandomList() throws SQLException {
    // TODO Auto-generated method stub
    return infoMapper.getRandomList();
  }

  @Override
  public int infoTypeCount(Map<String, Object> map) throws SQLException {
    return infoMapper.infoTypeCount(map);
  }
}
