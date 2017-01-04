package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyPojo;


public interface AgencyDao {

  void deleteAgency(Long id) throws SQLException;

  void updateAgency(AgencyPojo agencyPojo) throws SQLException;

  int getSysLoginAG(Map<String, Object> map) throws SQLException;

  List<AgencyPojo> sysLoginAGAllList(Map<String, Object> map);

  AgencyPojo findAgencyById(Long id) throws SQLException;

  List<AgencyPojo> agencyAllList(Map<String, Object> map) throws SQLException;

  int agencyCount(Map<String, Object> map) throws SQLException;

  public AgencyPojo getfindByUserIdAgency(Long agency_id) throws SQLException;

  public AgencyPojo getfindByAgencyId(Long agency_id) throws SQLException;

  void insertAgency(AgencyPojo agencyPojo) throws SQLException;

  AgencyPojo findagencyByUserId(Long id) throws SQLException;

  public AgencyPojo findagencyStatusByUserId(Long id) throws SQLException;

  List<AgencyPojo> agencyAllListForPush(Map<String, Object> map) throws SQLException;

  int agencyCountForPush(Map<String, Object> map) throws SQLException;

  void checkAgency(Long id) throws SQLException;

  AgencyPojo getfindByIdAgency(Long id) throws SQLException;

  void delAgency(Long agencyId) throws SQLException;

  void updatePushName(Long orderId) throws SQLException;

}
