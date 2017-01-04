package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyApplyDao;
import com.tzmb2c.web.mapper.AgencyApplyMapper;
import com.tzmb2c.web.pojo.AgencyApplyPojo;

/**
 * @author EricChen
 */
public class AgencyApplyDaoImpl implements AgencyApplyDao {

  @Autowired
  private AgencyApplyMapper agencyApplyMapper;

  @Override
  public int getAgencyApplyCount(Map<String, Object> map) throws SQLException {
    return agencyApplyMapper.getAgencyApplyCount(map);
  }

  @Override
  public List<AgencyApplyPojo> getAgencyApplyList(Map<String, Object> map) throws SQLException {
    return agencyApplyMapper.getAgencyApplyList(map);
  }

  @Override
  public AgencyApplyPojo getAgencyApplyById(Map<String, Object> map) throws SQLException {
    return agencyApplyMapper.getAgencyApplyById(map);
  }

  @Override
  public void updateAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException {
    agencyApplyMapper.updateAgencyApply(agencyApplyPojo);
  }

  @Override
  public void checkAgencyApply(Long id) throws SQLException {
    agencyApplyMapper.checkAgencyApply(id);
  }

  @Override
  public void uncheckAgencyApply(Long id) throws SQLException {
    agencyApplyMapper.uncheckAgencyApply(id);
  }

  @Override
  public void insertAgencyApply(AgencyApplyPojo agencyApplyPojo) throws SQLException {
    agencyApplyMapper.insertAgencyApply(agencyApplyPojo);
  }

  @Override
  public void delAgencyApply(Long id) throws SQLException {
    agencyApplyMapper.delAgencyApply(id);
  }


}
