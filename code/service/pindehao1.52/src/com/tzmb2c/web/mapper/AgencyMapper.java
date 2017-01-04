package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyPojo;


public interface AgencyMapper {

  void deleteAgency(Long id) throws SQLException;

  public void updateAgency(AgencyPojo agencyPojo) throws SQLException;

  public AgencyPojo getfindByUserIdAgency(Long agency_id) throws SQLException;

  public void insertAgency(AgencyPojo agencyPojo) throws SQLException;

  List<AgencyPojo> agencyAllList(Map<String, Object> map) throws SQLException;

  int agencyCount(Map<String, Object> map) throws SQLException;

  public int getSysLoginAG(Map<String, Object> map) throws SQLException;

  public List<AgencyPojo> sysLoginAGAllList(Map<String, Object> map);

  public AgencyPojo getfindAgencyById(Long id) throws SQLException;

  public AgencyPojo findagencyByUserId(Long id) throws SQLException;

  public AgencyPojo findagencyStatusByUserId(Long id) throws SQLException;

  public AgencyPojo getfindByAgencyId(Long agency_id) throws SQLException;

  public List<AgencyPojo> agencyAllListForPush(Map<String, Object> map) throws SQLException;

  public int agencyCountForPush(Map<String, Object> map) throws SQLException;

  AgencyPojo getfindByIdAgency(Long id) throws SQLException;

  void checkAgency(Long id) throws SQLException;

  void delAgency(Long agencyId) throws SQLException;

  void updatePushName(Long orderId) throws SQLException;
}
