package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyAuthenticationPojo;

public interface AgencyAuthenticationDao {

  void insertAgencyAuthentication(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  List<AgencyAuthenticationPojo> authenticationAgencyInfoAllList(Map<String, Object> map)
      throws SQLException;

  int authenticationAgencyInfoAllCount(Map<String, Object> map) throws SQLException;

  void checkAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  AgencyAuthenticationPojo findAuthenticationInfoById(Long agency_id) throws SQLException;

  void updateAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  void checkAllAuthenticationInfoById(Long agency_id) throws SQLException;

  void delAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo) throws SQLException;

}
