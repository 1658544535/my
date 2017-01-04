package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyDao;
import com.tzmb2c.web.mapper.AgencyMapper;
import com.tzmb2c.web.pojo.AgencyPojo;

public class AgencyDaoImpl implements AgencyDao {
  @Autowired
  private AgencyMapper agencyMapper;

  @Override
  public AgencyPojo getfindByUserIdAgency(Long agencyId) throws SQLException {
    return agencyMapper.getfindByUserIdAgency(agencyId);
  }

  @Override
  public AgencyPojo getfindByAgencyId(Long agencyId) throws SQLException {
    return agencyMapper.getfindByAgencyId(agencyId);
  }


  @Override
  public List<AgencyPojo> agencyAllList(Map<String, Object> map) throws SQLException {
    return agencyMapper.agencyAllList(map);
  }

  @Override
  public int agencyCount(Map<String, Object> map) throws SQLException {
    return agencyMapper.agencyCount(map);
  }



  @Override
  public int getSysLoginAG(Map<String, Object> map) throws SQLException {
    return agencyMapper.getSysLoginAG(map);
  }


  @Override
  public List<AgencyPojo> sysLoginAGAllList(Map<String, Object> map) {
    return agencyMapper.sysLoginAGAllList(map);
  }


  @Override
  public void insertAgency(AgencyPojo agencyPojo) throws SQLException {
    try {
      agencyMapper.insertAgency(agencyPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void deleteAgency(Long agencyId) throws SQLException {

    agencyMapper.deleteAgency(agencyId);
  }


  @Override
  public void updateAgency(AgencyPojo agency) throws SQLException {
    try {
      agencyMapper.updateAgency(agency);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public AgencyPojo findAgencyById(Long agencyId) throws SQLException {
    return agencyMapper.getfindAgencyById(agencyId);
  }

  @Override
  public AgencyPojo findagencyByUserId(Long agencyId) throws SQLException {
    return agencyMapper.findagencyByUserId(agencyId);
  }

  @Override
  public List<AgencyPojo> agencyAllListForPush(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return agencyMapper.agencyAllListForPush(map);
  }

  @Override
  public void updatePushName(Long orderId) throws SQLException {

    agencyMapper.updatePushName(orderId);
  }

  @Override
  public int agencyCountForPush(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return agencyMapper.agencyCountForPush(map);
  }

  @Override
  public AgencyPojo getfindByIdAgency(Long agencyId) throws SQLException {
    return agencyMapper.getfindByIdAgency(agencyId);

  }

  @Override
  public void checkAgency(Long agencyId) throws SQLException {

    agencyMapper.checkAgency(agencyId);
  }

  @Override
  public void delAgency(Long agencyId) throws SQLException {

    agencyMapper.delAgency(agencyId);
  }

  @Override
  public AgencyPojo findagencyStatusByUserId(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return agencyMapper.findagencyStatusByUserId(id);
  }
}
