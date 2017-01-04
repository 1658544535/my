package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SyntheticalDictPojo;

public interface SyntheticalDictDao {

  List<SyntheticalDictPojo> getSyntheticalDictListByType(Map<String, Object> map) throws Exception;

  SyntheticalDictPojo getSyntheticalDictByTypeAndValue(SyntheticalDictPojo syntheticalDictPojo)
      throws Exception;

  List<SyntheticalDictPojo> getSyntheticalDictAll() throws SQLException;

  void insertSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException;

  void updateSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException;

  SyntheticalDictPojo getfindByIdSyntheticalDict(Long id) throws SQLException;

  void deleteSyntheticalDict(Long id) throws SQLException;

  int syntheticalDictAllCount(Map<String, Object> map);

  List<SyntheticalDictPojo> syntheticalDictAllList(Map<String, Object> map);

  void delSyntheticalDict(Long id) throws SQLException;

  SyntheticalDictPojo findSyntheticalDictById(Long id) throws SQLException;

  public void checkAll(String id) throws SQLException;

  public void verifyInfo(SyntheticalDictPojo syntheticalDictPojo) throws SQLException;

  public List<SyntheticalDictPojo> getSyntheticalDictListByTypeStatus(String type) throws Exception;

}
