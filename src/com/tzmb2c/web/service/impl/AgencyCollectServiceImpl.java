package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.AgencyCollectDao;
import com.tzmb2c.web.pojo.AgencyCollectPojo;
import com.tzmb2c.web.service.AgencyCollectService;


public class AgencyCollectServiceImpl implements AgencyCollectService {
  @Autowired
  private AgencyCollectDao agencyCollectDao;

  @Override
  public void insertAgencyCollect(AgencyCollectPojo agencyCollectPojo) throws SQLException {

    agencyCollectDao.insertAgencyCollect(agencyCollectPojo);
  }

  @Override
  public AgencyCollectPojo findagencyCollectById(AgencyCollectPojo agencyCollectPojo)
      throws SQLException {

    return agencyCollectDao.findagencyCollectById(agencyCollectPojo);

  }

  @Override
  public List<AgencyCollectPojo> findagencyCollectByUid(AgencyCollectPojo agencyCollectPojo,
      Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();

    if (agencyCollectPojo != null) {

      map.put("uid", agencyCollectPojo.getUid());
      map.put("agency_id", agencyCollectPojo.getAgency_id());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return agencyCollectDao.findagencyCollectByUid(map);
  }

  @Override
  public void updatAagencyNumWeb(AgencyCollectPojo agencyCollectPojo) throws SQLException {
    agencyCollectDao.updatAagencyNumWeb(agencyCollectPojo);

  }

  @Override
  public void delNotCheckProduct(long agency_id, String strPids) throws SQLException {
    String[] pids = strPids.split(",");
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("agency_id", agency_id);
    map.put("pids", pids);
    agencyCollectDao.delNotCheckProduct(map);
  }

  /**
   * 删除代理产品
   */
  @Override
  public void delCheckProduct(AgencyCollectPojo agencyCollectPojo) throws SQLException {
    agencyCollectDao.delCheckProduct(agencyCollectPojo);

  }

  @Override
  public int agencyCollectCount(Map<String, Object> map) throws SQLException {
    return agencyCollectDao.agencyCollectCount(map);
  }

  @Override
  public List<AgencyCollectPojo> agencyCollectList(Map<String, Object> map) throws SQLException {
    return agencyCollectDao.agencyCollectList(map);
  }

  @Override
  public void updatAagencyCollect(AgencyCollectPojo agencyCollectPojo) throws SQLException {
    agencyCollectDao.updatAagencyCollect(agencyCollectPojo);
  }

  @Override
  public void delAgencyCollectById(Long id) throws SQLException {
    agencyCollectDao.delAgencyCollectById(id);
  }

  @Override
  public Long findagencyCollectByProductId(Map<String, Object> map) throws SQLException {
    return agencyCollectDao.findagencyCollectByProductId(map);
  }

}
