package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SyntheticalDictPojo;

public interface SyntheticalDictService {

  public List<SyntheticalDictPojo> getSyntheticalDictListByType(Map<String, Object> map)
      throws Exception;

  public SyntheticalDictPojo getSyntheticalDictByTypeAndValue(
      SyntheticalDictPojo SyntheticalDictPojo) throws Exception;

  public List<SyntheticalDictPojo> SyntheticalDictAllService() throws SQLException;

  public void addSyntheticalDictService(SyntheticalDictPojo SyntheticalDictPojo)
      throws SQLException;

  public void insertSyntheticalDict(SyntheticalDictPojo SyntheticalDictPojo) throws SQLException;

  public void updateSyntheticalDict(SyntheticalDictPojo SyntheticalDictPojo) throws SQLException;

  public SyntheticalDictPojo getfindByIdSyntheticalDict(Long id) throws SQLException;

  public void deleteSyntheticalDict(Long SyntheticalDictId) throws SQLException;

  public int syntheticalDictAllCount(SyntheticalDictPojo SyntheticalDictDaoPojo);

  List<SyntheticalDictPojo> syntheticalDictAllList(SyntheticalDictPojo ticketRulePojo, Pager page);

  void syntheticalDictDeleteId(String[] tids);

  void delSyntheticalDict(Long id) throws SQLException;

  SyntheticalDictPojo findSyntheticalDictById(Long merId) throws SQLException;

  public void checkAllById(String[] tids);

  public void verifyInfo(SyntheticalDictPojo SyntheticalDictDaoPojo) throws SQLException;

  public List<SyntheticalDictPojo> getSyntheticalDictListByTypeStatus(String type) throws Exception;
}
