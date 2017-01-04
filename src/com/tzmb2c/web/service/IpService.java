package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.Map;


public interface IpService {
  void insertIP(Map<String, Object> map) throws SQLException;
}
