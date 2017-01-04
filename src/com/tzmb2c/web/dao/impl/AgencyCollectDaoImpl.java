package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyCollectDao;
import com.tzmb2c.web.mapper.AgencyCollectMapper;
import com.tzmb2c.web.pojo.AgencyCollectPojo;

public class AgencyCollectDaoImpl implements AgencyCollectDao {
  @Autowired
  private AgencyCollectMapper agencyCollectMapper;

  @Override
  public void insertAgencyCollect(AgencyCollectPojo agencyCollectPojo) throws SQLException {
    agencyCollectMapper.insertAgencyCollect(agencyCollectPojo);
  }

  @Override
  public AgencyCollectPojo findagencyCollectById(AgencyCollectPojo agencyCollectPojo)
      throws SQLException {
    return agencyCollectMapper.findagencyCollectById(agencyCollectPojo);
  }

  @Override
  public List<AgencyCollectPojo> findagencyCollectByUid(Map<String, Object> map) {
    return agencyCollectMapper.findagencyCollectByUid(map);
  }

  @Override
  public void updatAagencyNumWeb(AgencyCollectPojo agencyCollectPojo) throws SQLException {

    agencyCollectMapper.updatAagencyNumWeb(agencyCollectPojo);
  }

  @Override
  public void delNotCheckProduct(Map<String, Object> map) throws SQLException {
    agencyCollectMapper.delNotCheckProduct(map);
  }

  /**
   * 删除代理产品
   */
  @Override
  public void delCheckProduct(AgencyCollectPojo agencyCollectPojo) throws SQLException {
    agencyCollectMapper.delCheckProduct(agencyCollectPojo);

  }

  @Override
  public int agencyCollectCount(Map<String, Object> map) throws SQLException {
    return agencyCollectMapper.agencyCollectCount(map);
  }

  @Override
  public List<AgencyCollectPojo> agencyCollectList(Map<String, Object> map) throws SQLException {
    return agencyCollectMapper.agencyCollectList(map);
  }

  @Override
  public void updatAagencyCollect(AgencyCollectPojo agencyCollectPojo) throws SQLException {
    agencyCollectMapper.updatAagencyCollect(agencyCollectPojo);
  }

  @Override
  public void delAgencyCollectById(Long id) throws SQLException {
    agencyCollectMapper.delAgencyCollectById(id);
  }

  @Override
  public Long findagencyCollectByProductId(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return agencyCollectMapper.findagencyCollectByProductId(map);
  }

}
