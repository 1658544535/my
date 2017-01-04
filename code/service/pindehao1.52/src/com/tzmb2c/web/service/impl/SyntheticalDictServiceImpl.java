package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SyntheticalDictDao;
import com.tzmb2c.web.pojo.SyntheticalDictPojo;
import com.tzmb2c.web.service.SyntheticalDictService;

public class SyntheticalDictServiceImpl implements SyntheticalDictService {
  @Autowired
  private SyntheticalDictDao syntheticalDictDao;

  public void setSyntheticalDictDao(SyntheticalDictDao syntheticalDictDao) {
    this.syntheticalDictDao = syntheticalDictDao;
  }

  @Override
  public List<SyntheticalDictPojo> getSyntheticalDictListByTypeStatus(String type) throws Exception {

    return syntheticalDictDao.getSyntheticalDictListByTypeStatus(type);
  }

  @Override
  public List<SyntheticalDictPojo> getSyntheticalDictListByType(Map<String, Object> map)
      throws Exception {

    return syntheticalDictDao.getSyntheticalDictListByType(map);
  }

  @Override
  public List<SyntheticalDictPojo> SyntheticalDictAllService() throws SQLException {
    return syntheticalDictDao.getSyntheticalDictAll();
  }



  @Override
  public void addSyntheticalDictService(SyntheticalDictPojo syntheticalDictPojo)
      throws SQLException {

    syntheticalDictDao.insertSyntheticalDict(syntheticalDictPojo);
  }


  @Override
  public void insertSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException {

    syntheticalDictDao.insertSyntheticalDict(syntheticalDictPojo);
  }

  @Override
  public void updateSyntheticalDict(SyntheticalDictPojo syntheticalDictPojo) throws SQLException {
    syntheticalDictDao.updateSyntheticalDict(syntheticalDictPojo);

  }

  @Override
  public SyntheticalDictPojo getfindByIdSyntheticalDict(Long id) throws SQLException {
    return syntheticalDictDao.getfindByIdSyntheticalDict(id);

  }

  @Override
  public void deleteSyntheticalDict(Long id) throws SQLException {

    syntheticalDictDao.deleteSyntheticalDict(id);
  }

  @Override
  public int syntheticalDictAllCount(SyntheticalDictPojo syntheticalDictDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (syntheticalDictDaoPojo != null) {
      map.put("name", syntheticalDictDaoPojo.getName());
      map.put("nameEn", syntheticalDictDaoPojo.getNameEn());
      map.put("type", syntheticalDictDaoPojo.getType());
    }
    return syntheticalDictDao.syntheticalDictAllCount(map);
  }

  @Override
  public List<SyntheticalDictPojo> syntheticalDictAllList(
      SyntheticalDictPojo syntheticalDictDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (syntheticalDictDaoPojo != null) {
      map.put("name", syntheticalDictDaoPojo.getName());
      map.put("nameEn", syntheticalDictDaoPojo.getNameEn());
      map.put("type", syntheticalDictDaoPojo.getType());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SyntheticalDictPojo> list = syntheticalDictDao.syntheticalDictAllList(map);

    return list;
  }

  @Override
  public void syntheticalDictDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        syntheticalDictDao.delSyntheticalDict(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delSyntheticalDict(Long id) throws SQLException {
    syntheticalDictDao.delSyntheticalDict(id);
  }

  @Override
  public SyntheticalDictPojo findSyntheticalDictById(Long id) throws SQLException {

    return syntheticalDictDao.findSyntheticalDictById(id);

  }

  @Override
  public SyntheticalDictPojo getSyntheticalDictByTypeAndValue(
      SyntheticalDictPojo syntheticalDictPojo) throws Exception {
    // TODO Auto-generated method stub
    return syntheticalDictDao.getSyntheticalDictByTypeAndValue(syntheticalDictPojo);
  }

  @Override
  public void checkAllById(String[] tids) {
    for (String tid : tids) {
      try {
        syntheticalDictDao.checkAll(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }

  }

  @Override
  public void verifyInfo(SyntheticalDictPojo syntheticalDictDaoPojo) throws SQLException {
    syntheticalDictDao.verifyInfo(syntheticalDictDaoPojo);

  }
}
