package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.UserVisitPojo;

public interface UserVisitDao {

  public List<UserVisitPojo> getTodayVisitPojos() throws SQLException;

  public int getTodayVisitNum() throws SQLException;
}
