package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ChildrenChannelHistoryPojo;

public interface ChildrenChannelHistoryService {
  List<ChildrenChannelHistoryPojo> findChildrenChannelHistoryList(Map<String, Object> map)
      throws SQLException;

  int findChildrenChannelHistoryCount(Map<String, Object> map) throws SQLException;

  Long insertChildrenChannelHistory(ChildrenChannelHistoryPojo childrenChannelHistory)
      throws SQLException;

  void delChildrenChannelHistory(Long id) throws SQLException;

  void updateChildrenChannelHistory(ChildrenChannelHistoryPojo childrenChannelHistory)
      throws SQLException;
}
