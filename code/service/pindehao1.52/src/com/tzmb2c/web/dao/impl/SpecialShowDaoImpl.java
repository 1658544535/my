package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialShowDao;
import com.tzmb2c.web.mapper.SpecialShowMapper;
import com.tzmb2c.web.pojo.SpecialShowPojo;

public class SpecialShowDaoImpl implements SpecialShowDao {
  @Autowired
  private SpecialShowMapper specialShowMapper;

  @Override
  public List<SpecialShowPojo> findSpecialShowList(Map<String, Object> map) throws SQLException {
    return specialShowMapper.findSpecialShowList(map);
  }

  @Override
  public int findSpecialShowCount(Map<String, Object> map) throws SQLException {
    return specialShowMapper.findSpecialShowCount(map);
  }

  @Override
  public SpecialShowPojo findSpecialShowById(Long id) throws SQLException {
    return specialShowMapper.findSpecialShowById(id);
  }

  @Override
  public List<SpecialShowPojo> findSpecialShowByUid(Long id) throws SQLException {
    return specialShowMapper.findSpecialShowByUid(id);
  }

  @Override
  public void insertSpecialShow(SpecialShowPojo specialShow) throws SQLException {
    specialShowMapper.insertSpecialShow(specialShow);
  }

  @Override
  public void delSpecialShow(Long id) throws SQLException {
    specialShowMapper.delSpecialShow(id);
  }

  @Override
  public void checkSpecialShow(Long id) throws SQLException {
    specialShowMapper.checkSpecialShow(id);
  }

  @Override
  public void uncheckSpecialShow(Long id) throws SQLException {
    specialShowMapper.uncheckSpecialShow(id);
  }

  @Override
  public void modifySpecialShow(Long id) throws SQLException {
    specialShowMapper.modifySpecialShow(id);
  }

  @Override
  public void schedulingSpecialShow(Long id) throws SQLException {
    specialShowMapper.schedulingSpecialShow(id);
  }

  @Override
  public void updateSpecialShow(SpecialShowPojo specialShow) throws SQLException {
    specialShowMapper.updateSpecialShow(specialShow);
  }

  @Override
  public SpecialShowPojo findSpecialShowByActivityId(Long activityId) throws SQLException {
    return specialShowMapper.findSpecialShowByActivityId(activityId);
  }

  @Override
  public void updateSpecialShowEnd() throws SQLException {
    specialShowMapper.updateSpecialShowEnd();
  }

  @Override
  public List<SpecialShowPojo> findSpecialShowByStatus(Long id) {
    return specialShowMapper.findSpecialShowByStatus(id);
  }
}
