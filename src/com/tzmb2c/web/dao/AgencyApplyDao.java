package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyApplyPojo;

public interface AgencyApplyDao {
  int getAgencyApplyCount(Map<String, Object> map) throws SQLException;

  List<AgencyApplyPojo> getAgencyApplyList(Map<String, Object> map) throws SQLException;

  AgencyApplyPojo getAgencyApplyById(Map<String, Object> map) throws SQLException;

  void updateAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException;

  void checkAgencyApply(Long id) throws SQLException;

  void uncheckAgencyApply(Long id) throws SQLException;

  void insertAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException;

  void delAgencyApply(Long id) throws SQLException;
}
