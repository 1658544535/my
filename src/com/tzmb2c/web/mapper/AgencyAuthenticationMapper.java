package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyAuthenticationPojo;

public interface AgencyAuthenticationMapper {

  public void insertAgencyAuthentication(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  public List<AgencyAuthenticationPojo> authenticationAgencyInfoAllList(Map<String, Object> map)
      throws SQLException;

  public int authenticationAgencyInfoAllCount(Map<String, Object> map) throws SQLException;

  public void checkAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  public AgencyAuthenticationPojo findAuthenticationInfoById(Long agency_id);

  public void updateAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo);


  public void delAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  public void checkAllAuthenticationInfoById(Long agency_id);


}
