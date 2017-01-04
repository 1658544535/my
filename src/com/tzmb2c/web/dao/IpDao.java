package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.Map;

public interface IpDao {
  void insertIp(Map<String, Object> map) throws SQLException;
}
