package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SyntheticalDictPojo;

public interface SyntheticalDictMapper {

  public List<SyntheticalDictPojo> getSyntheticalDictListByType(Map<String, Object> map)
      throws Exception;

  public SyntheticalDictPojo getSyntheticalDictByTypeAndValue(
      SyntheticalDictPojo syntheticalDictPojo) throws Exception;

  public List<SyntheticalDictPojo> getSyntheticalDictAll() throws SQLException;

  public void insertSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException;

  public void updateSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException;

  public SyntheticalDictPojo getfindByIdSyntheticalDict(Long id) throws SQLException;

  public void deleteSyntheticalDict(Long id) throws SQLException;

  public int syntheticalDictAllCount(Map<String, Object> map);

  public List<SyntheticalDictPojo> syntheticalDictAllList(Map<String, Object> map);

  void delSyntheticalDict(Long id) throws SQLException;

  public void checkAllById(String id) throws SQLException;

  public void verifyInfo(SyntheticalDictPojo syntheticalDictPojo) throws SQLException;

  public List<SyntheticalDictPojo> getSyntheticalDictListByTypeStatus(String type) throws Exception;

}
