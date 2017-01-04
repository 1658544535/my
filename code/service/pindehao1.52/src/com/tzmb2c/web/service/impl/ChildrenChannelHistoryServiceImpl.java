package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ChildrenChannelHistoryDao;
import com.tzmb2c.web.pojo.ChildrenChannelHistoryPojo;
import com.tzmb2c.web.service.ChildrenChannelHistoryService;

public class ChildrenChannelHistoryServiceImpl implements ChildrenChannelHistoryService {
  @Autowired
  private ChildrenChannelHistoryDao childrenChannelHistoryDao;

  @Override
  public List<ChildrenChannelHistoryPojo> findChildrenChannelHistoryList(Map<String, Object> map)
      throws SQLException {
    return childrenChannelHistoryDao.findChildrenChannelHistoryList(map);
  }

  @Override
  public int findChildrenChannelHistoryCount(Map<String, Object> map) throws SQLException {
    return childrenChannelHistoryDao.findChildrenChannelHistoryCount(map);
  }


  @Override
  public Long insertChildrenChannelHistory(ChildrenChannelHistoryPojo childrenChannelHistory)
      throws SQLException {
    return childrenChannelHistoryDao.insertChildrenChannelHistory(childrenChannelHistory);

  }

  @Override
  public void delChildrenChannelHistory(Long id) throws SQLException {
    childrenChannelHistoryDao.delChildrenChannelHistory(id);
  }

  @Override
  public void updateChildrenChannelHistory(ChildrenChannelHistoryPojo childrenChannelHistory)
      throws SQLException {
    childrenChannelHistoryDao.updateChildrenChannelHistory(childrenChannelHistory);
  }


}
