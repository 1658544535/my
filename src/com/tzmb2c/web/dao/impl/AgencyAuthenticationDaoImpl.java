package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyAuthenticationDao;
import com.tzmb2c.web.mapper.AgencyAuthenticationMapper;
import com.tzmb2c.web.pojo.AgencyAuthenticationPojo;



public class AgencyAuthenticationDaoImpl implements AgencyAuthenticationDao {
  @Autowired
  private AgencyAuthenticationMapper agencyAuthenticationMapper;

  @Override
  public void insertAgencyAuthentication(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {
    agencyAuthenticationMapper.insertAgencyAuthentication(agencyAuthenticationPojo);
  }

  @Override
  public List<AgencyAuthenticationPojo> authenticationAgencyInfoAllList(Map<String, Object> map)
      throws SQLException {
    return agencyAuthenticationMapper.authenticationAgencyInfoAllList(map);
  }

  @Override
  public int authenticationAgencyInfoAllCount(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return agencyAuthenticationMapper.authenticationAgencyInfoAllCount(map);
  }

  @Override
  public void checkAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    agencyAuthenticationMapper.checkAuthenticationInfo(agencyAuthenticationPojo);
  }

  @Override
  public AgencyAuthenticationPojo findAuthenticationInfoById(Long agency_id) throws SQLException {
    // TODO Auto-generated method stub
    return agencyAuthenticationMapper.findAuthenticationInfoById(agency_id);
  }

  @Override
  public void updateAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    agencyAuthenticationMapper.updateAuthenticationInfo(agencyAuthenticationPojo);
  }

  @Override
  public void checkAllAuthenticationInfoById(Long agency_id) throws SQLException {
    // TODO Auto-generated method stub
    agencyAuthenticationMapper.checkAllAuthenticationInfoById(agency_id);
  }

  @Override
  public void delAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    agencyAuthenticationMapper.delAuthenticationInfo(agencyAuthenticationPojo);
  }
}
