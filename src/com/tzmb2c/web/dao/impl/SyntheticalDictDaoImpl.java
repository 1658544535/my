package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SyntheticalDictDao;
import com.tzmb2c.web.mapper.SyntheticalDictMapper;
import com.tzmb2c.web.pojo.SyntheticalDictPojo;

public class SyntheticalDictDaoImpl implements SyntheticalDictDao {

  @Autowired
  private SyntheticalDictMapper syntheticalDictMapper;

  @Override
  public List<SyntheticalDictPojo> getSyntheticalDictAll() throws SQLException {
    return syntheticalDictMapper.getSyntheticalDictAll();
  }

  @Override
  public void insertSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException {
    syntheticalDictMapper.insertSyntheticalDict(syntheticalDictPojo);

  }

  @Override
  public void updateSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException {

    syntheticalDictMapper.updateSyntheticalDict(syntheticalDictPojo);
  }

  @Override
  public SyntheticalDictPojo getfindByIdSyntheticalDict(Long id) throws SQLException {
    return syntheticalDictMapper.getfindByIdSyntheticalDict(id);

  }

  @Override
  public void deleteSyntheticalDict(Long id) throws SQLException {
    syntheticalDictMapper.deleteSyntheticalDict(id);
  }

  @Override
  public int syntheticalDictAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return syntheticalDictMapper.syntheticalDictAllCount(map);
  }

  @Override
  public List<SyntheticalDictPojo> syntheticalDictAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return syntheticalDictMapper.syntheticalDictAllList(map);
  }

  @Override
  public void delSyntheticalDict(Long id) throws SQLException {

    syntheticalDictMapper.delSyntheticalDict(id);
  }

  @Override
  public SyntheticalDictPojo findSyntheticalDictById(Long id) throws SQLException {
    return syntheticalDictMapper.getfindByIdSyntheticalDict(id);
  }

  @Override
  public List<SyntheticalDictPojo> getSyntheticalDictListByType(Map<String, Object> map)
      throws Exception {
    // TODO Auto-generated method stub
    return syntheticalDictMapper.getSyntheticalDictListByType(map);
  }

  @Override
  public SyntheticalDictPojo getSyntheticalDictByTypeAndValue(
      SyntheticalDictPojo syntheticalDictPojo) throws Exception {
    // TODO Auto-generated method stub
    return syntheticalDictMapper.getSyntheticalDictByTypeAndValue(syntheticalDictPojo);
  }

  @Override
  public void checkAll(String id) throws SQLException {
    syntheticalDictMapper.checkAllById(id);

  }

  @Override
  public void verifyInfo(SyntheticalDictPojo syntheticalDictPojo) throws SQLException {
    syntheticalDictMapper.verifyInfo(syntheticalDictPojo);

  }

  @Override
  public List<SyntheticalDictPojo> getSyntheticalDictListByTypeStatus(String type) throws Exception {
    return syntheticalDictMapper.getSyntheticalDictListByTypeStatus(type);
  }
}
