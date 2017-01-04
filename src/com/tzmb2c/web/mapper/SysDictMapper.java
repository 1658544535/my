package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysDictPojo;

public interface SysDictMapper {

  public List<SysDictPojo> getSysDictListByType(String type) throws Exception;

  public SysDictPojo getSysDictByTypeAndValue(SysDictPojo sysDictPojo) throws Exception;

  public List<SysDictPojo> getSysDictAll() throws SQLException;

  public void insertSysDict(SysDictPojo sysDictPojo) throws SQLException;

  public void updateSysDict(SysDictPojo sysDictPojo) throws SQLException;

  public SysDictPojo getfindByIdSysDict(Long id) throws SQLException;

  public void deleteSysDict(Long id) throws SQLException;

  public int sysDictAllCount(Map<String, Object> map);

  public List<SysDictPojo> sysDictAllList(Map<String, Object> map);

  void delSysDict(Long id) throws SQLException;

}
