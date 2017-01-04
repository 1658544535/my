package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyApplyDao;
import com.tzmb2c.web.pojo.AgencyApplyPojo;
import com.tzmb2c.web.service.AgencyApplyService;

public class AgencyApplyServiceImpl implements AgencyApplyService {
  @Autowired
  private AgencyApplyDao agencyApplyDao;

  @Override
  public int getAgencyApplyCount(Map<String, Object> map) throws SQLException {
    return agencyApplyDao.getAgencyApplyCount(map);
  }

  @Override
  public List<AgencyApplyPojo> getAgencyApplyList(Map<String, Object> map) throws SQLException {
    return agencyApplyDao.getAgencyApplyList(map);
  }

  @Override
  public AgencyApplyPojo getAgencyApplyById(Map<String, Object> map) throws SQLException {
    return agencyApplyDao.getAgencyApplyById(map);
  }

  @Override
  public void updateAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException {
    agencyApplyDao.updateAgencyApply(agencyApplyPojo);
  }

  @Override
  public void checkAgencyApply(Long id) throws SQLException {
    agencyApplyDao.checkAgencyApply(id);
  }

  @Override
  public void uncheckAgencyApply(Long id) throws SQLException {
    agencyApplyDao.uncheckAgencyApply(id);
  }

  @Override
  public void insertAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException {
    agencyApplyDao.insertAgencyApply(agencyApplyPojo);
  }

  @Override
  public void delAgencyApply(Long id) throws SQLException {
    agencyApplyDao.delAgencyApply(id);
  }


}
