package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.AgencyPojo;


public interface AgencyService {


  public int getSysLoginAG(AgencyPojo agencyDaoPojo, String os) throws SQLException;

  List<AgencyPojo> sysLoginAGAllList(AgencyPojo ticketRulePojo, Pager page, String os);

  void deleteAgency(Long id) throws SQLException;

  public void updateAgency(AgencyPojo agencyPojo) throws SQLException;

  AgencyPojo findAgencyById(Long merId) throws SQLException;

  public AgencyPojo getfindByUserIdAgency(Long agency_id) throws SQLException;

  public AgencyPojo getfindByAgencyId(Long agency_id) throws SQLException;

  void insertAgency(AgencyPojo agencyPojo) throws SQLException;

  List<AgencyPojo> agencyAllList(Map<String, Object> map) throws SQLException;

  int agencyCount(Map<String, Object> map) throws SQLException;

  AgencyPojo findagencyByUserId(Long id) throws SQLException;

  public AgencyPojo findagencyStatusByUserId(Long id) throws SQLException;

  public List<AgencyPojo> agencyAllListForPush(Map<String, Object> map) throws SQLException;

  public int agencyCountForPush(Map<String, Object> map) throws SQLException;

  public void checkAgency(Long agencyId) throws SQLException;

  public AgencyPojo getfindByIdAgency(Long agencyId) throws SQLException;

  public void delAgency(Long agencyId) throws SQLException;

  void agencyChecks(String[] tids) throws SQLException;

  public void updatePushName(Long orderId) throws SQLException;

}
