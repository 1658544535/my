package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialShowDao;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.service.SpecialShowService;

public class SpecialShowServiceImpl implements SpecialShowService {
  @Autowired
  private SpecialShowDao specialShowDao;

  @Override
  public List<SpecialShowPojo> findSpecialShowList(Map<String, Object> map) throws SQLException {
    return specialShowDao.findSpecialShowList(map);
  }

  @Override
  public int findSpecialShowCount(Map<String, Object> map) throws SQLException {
    return specialShowDao.findSpecialShowCount(map);
  }

  @Override
  public SpecialShowPojo findSpecialShowById(Long id) throws SQLException {
    return specialShowDao.findSpecialShowById(id);
  }

  @Override
  public List<SpecialShowPojo> findSpecialShowByUid(Long id) throws SQLException {
    return specialShowDao.findSpecialShowByUid(id);
  }

  @Override
  public void insertSpecialShow(SpecialShowPojo specialShow) throws SQLException {
    specialShowDao.insertSpecialShow(specialShow);
  }

  @Override
  public void delSpecialShow(Long id) throws SQLException {
    specialShowDao.delSpecialShow(id);
  }

  @Override
  public void checkSpecialShow(Long id) throws SQLException {
    specialShowDao.checkSpecialShow(id);
  }

  @Override
  public void uncheckSpecialShow(Long id) throws SQLException {
    specialShowDao.uncheckSpecialShow(id);
  }

  @Override
  public void modifySpecialShow(Long id) throws SQLException {
    specialShowDao.modifySpecialShow(id);
  }

  @Override
  public void schedulingSpecialShow(Long id) throws SQLException {
    specialShowDao.schedulingSpecialShow(id);
  }

  @Override
  public void updateSpecialShow(SpecialShowPojo specialShow) throws SQLException {
    specialShowDao.updateSpecialShow(specialShow);
  }

  @Override
  public SpecialShowPojo findSpecialShowByActivityId(Long activityId) throws SQLException {
    return specialShowDao.findSpecialShowByActivityId(activityId);
  }

  @Override
  public void updateSpecialShowEnd() throws SQLException {
    specialShowDao.updateSpecialShowEnd();
  }

  @Override
  public List<SpecialShowPojo> findSpecialShowByStatus(Long id) {
    return specialShowDao.findSpecialShowByStatus(id);
  }
}
