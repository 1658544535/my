package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysDictPojo;

public interface SysDictDao {

  List<SysDictPojo> getSysDictListByType(String type) throws Exception;

  SysDictPojo getSysDictByTypeAndValue(SysDictPojo sysDictPojo) throws Exception;

  List<SysDictPojo> getSysDictAll() throws SQLException;

  void insertSysDict(SysDictPojo sysDictPojo) throws SQLException;

  void updateSysDict(SysDictPojo sysDictPojo) throws SQLException;

  SysDictPojo getfindByIdSysDict(Long id) throws SQLException;

  void deleteSysDict(Long id) throws SQLException;

  int sysDictAllCount(Map<String, Object> map);

  List<SysDictPojo> sysDictAllList(Map<String, Object> map);

  void delSysDict(Long id) throws SQLException;

  SysDictPojo findSysDictById(Long id) throws SQLException;

}
