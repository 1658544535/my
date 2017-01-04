package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ChildrenChannelHistoryDao;
import com.tzmb2c.web.mapper.ChildrenChannelHistoryMapper;
import com.tzmb2c.web.pojo.ChildrenChannelHistoryPojo;

public class ChildrenChannelHistoryDaoImpl implements ChildrenChannelHistoryDao {
  @Autowired
  private ChildrenChannelHistoryMapper childrenChannelHistoryMapper;

  @Override
  public List<ChildrenChannelHistoryPojo> findChildrenChannelHistoryList(Map<String, Object> map)
      throws SQLException {
    return childrenChannelHistoryMapper.findChildrenChannelHistoryList(map);
  }

  @Override
  public int findChildrenChannelHistoryCount(Map<String, Object> map) throws SQLException {
    return childrenChannelHistoryMapper.findChildrenChannelHistoryCount(map);
  }

  @Override
  public Long insertChildrenChannelHistory(ChildrenChannelHistoryPojo childrenChannelHistory)
      throws SQLException {
    return childrenChannelHistoryMapper.insertChildrenChannelHistory(childrenChannelHistory);
  }

  @Override
  public void delChildrenChannelHistory(Long id) throws SQLException {
    childrenChannelHistoryMapper.delChildrenChannelHistory(id);
  }

  @Override
  public void updateChildrenChannelHistory(ChildrenChannelHistoryPojo childrenChannelHistory)
      throws SQLException {
    childrenChannelHistoryMapper.updateChildrenChannelHistory(childrenChannelHistory);
  }


}
