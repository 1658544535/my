package com.tzmb2c.web.mapper;

import java.sql.SQLException;

import com.tzmb2c.web.pojo.TempDataPojo;

public interface TempDataMapper {
  public TempDataPojo getSingleRecord() throws SQLException;

  public void deleteSingleRecord(int id) throws SQLException;
}
