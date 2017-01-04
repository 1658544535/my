package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysDictPojo;

public interface SysDictService {

  public List<SysDictPojo> getSysDictListByType(String type) throws Exception;

  public SysDictPojo getSysDictByTypeAndValue(SysDictPojo sysDictPojo) throws Exception;

  public List<SysDictPojo> sysDictAllService() throws SQLException;

  public void addSysDictService(SysDictPojo sysDictPojo) throws SQLException;

  public void insertSysDict(SysDictPojo sysDictPojo) throws SQLException;

  public void updateSysDict(SysDictPojo sysDictPojo) throws SQLException;

  public SysDictPojo getfindByIdSysDict(Long id) throws SQLException;

  public void deleteSysDict(Long sysDictId) throws SQLException;

  public int sysDictAllCount(SysDictPojo sysDictDaoPojo);

  List<SysDictPojo> sysDictAllList(SysDictPojo ticketRulePojo, Pager page);

  void sysDictDeleteId(String[] tids);

  void delSysDict(Long id) throws SQLException;

  SysDictPojo findSysDictById(Long merId) throws SQLException;
}
