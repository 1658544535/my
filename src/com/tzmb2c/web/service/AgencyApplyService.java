package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyApplyPojo;

public interface AgencyApplyService {
  public int getAgencyApplyCount(Map<String, Object> map) throws SQLException;

  public List<AgencyApplyPojo> getAgencyApplyList(Map<String, Object> map) throws SQLException;

  public AgencyApplyPojo getAgencyApplyById(Map<String, Object> map) throws SQLException;

  public void updateAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException;

  public void checkAgencyApply(Long id) throws SQLException;

  public void uncheckAgencyApply(Long id) throws SQLException;

  public void insertAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException;

  public void delAgencyApply(Long id) throws SQLException;

}
