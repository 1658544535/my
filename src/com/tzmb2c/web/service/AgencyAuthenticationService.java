package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.AgencyAuthenticationPojo;


public interface AgencyAuthenticationService {

  void insertAgencyAuthentication(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;


  List<AgencyAuthenticationPojo> authenticationAgencyInfoAllList(
      AgencyAuthenticationPojo agencyAuthenticationPojo, Pager page, String type)
      throws SQLException;


  int authenticationAgencyInfoAllCount(AgencyAuthenticationPojo agencyAuthenticationPojo,
      String type) throws SQLException;


  void checkAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;


  AgencyAuthenticationPojo findAuthenticationInfoById(Long agency_id) throws SQLException;


  void updateAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException;

  void checkAllAuthenticationInfoById(String[] tids);


  void delAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo) throws SQLException;



}
