package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.AgencyDao;
import com.tzmb2c.web.dao.ProductDao;
import com.tzmb2c.web.dao.SysLoginDao;
import com.tzmb2c.web.dao.UserInfoDao;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.service.AgencyService;

public class AgencyServiceImpl implements AgencyService {

  @Autowired
  private AgencyDao agencyDao;
  @Autowired
  private ProductDao productDao;
  @Autowired
  private SysLoginDao sysLoginDao;
  @Autowired
  private UserInfoDao userInfoDao;

  @Override
  public int getSysLoginAG(AgencyPojo agencyDaoPojo, String os) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (agencyDaoPojo != null) {
      map.put("name", agencyDaoPojo.getName());
      map.put("loginname", agencyDaoPojo.getLoginname());
      map.put("QQ", agencyDaoPojo.getQQ());
    }
    if (os != null) {
      map.put("os", os);
    }
    return agencyDao.getSysLoginAG(map);
  }


  @Override
  public List<AgencyPojo> sysLoginAGAllList(AgencyPojo ticketRulePojo, Pager page, String os) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("name", ticketRulePojo.getName());
      map.put("loginname", ticketRulePojo.getLoginname());
      map.put("QQ", ticketRulePojo.getQQ());
      // map.put("agencyId",ticketRulePojo.getAgency_id());
      map.put("agenctId", ticketRulePojo.getAgencyId());
    }
    if (os != null) {
      map.put("os", os);
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<AgencyPojo> list = agencyDao.sysLoginAGAllList(map);

    return list;
  }

  @Override
  public void insertAgency(AgencyPojo agencyPojo) throws SQLException {
    agencyDao.insertAgency(agencyPojo);
  }

  @Override
  public AgencyPojo getfindByUserIdAgency(Long agencyId) throws SQLException {
    return agencyDao.getfindByUserIdAgency(agencyId);
  }


  @Override
  public AgencyPojo getfindByAgencyId(Long agencyId) throws SQLException {
    return agencyDao.getfindByAgencyId(agencyId);
  }

  @Override
  public void deleteAgency(Long agencyId) throws SQLException {
    agencyDao.deleteAgency(agencyId);
  }


  @Override
  public void updateAgency(AgencyPojo agency) throws SQLException {
    agencyDao.updateAgency(agency);

  }



  @Override
  public AgencyPojo findAgencyById(Long merId) throws SQLException {

    return agencyDao.findAgencyById(merId);
  }


  @Override
  public List<AgencyPojo> agencyAllList(Map<String, Object> map) throws SQLException {

    return agencyDao.agencyAllList(map);
  }

  @Override
  public int agencyCount(Map<String, Object> map) throws SQLException {

    return agencyDao.agencyCount(map);
  }

  @Override
  public AgencyPojo findagencyByUserId(Long agencyId) throws SQLException {

    return agencyDao.findagencyByUserId(agencyId);

  }


  @Override
  public List<AgencyPojo> agencyAllListForPush(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return agencyDao.agencyAllListForPush(map);
  }

  @Override
  public void updatePushName(Long orderId) throws SQLException {

    agencyDao.updatePushName(orderId);
  }

  @Override
  public int agencyCountForPush(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return agencyDao.agencyCountForPush(map);
  }

  @Override
  public void checkAgency(Long agencyId) throws SQLException {
    agencyDao.checkAgency(agencyId);
  }

  @Override
  public AgencyPojo getfindByIdAgency(Long agencyId) throws SQLException {
    return agencyDao.getfindByIdAgency(agencyId);

  }

  @Override
  public void delAgency(Long agencyId) throws SQLException {
    AgencyPojo pojo = agencyDao.getfindByIdAgency(agencyId);
    if (pojo != null) {
      productDao.updateProductStatus(pojo.getUserId());
      sysLoginDao.deleteSysLogin(pojo.getUserId());
      userInfoDao.delUserInfobyUserId(pojo.getUserId());
    }

    agencyDao.delAgency(agencyId);
  }

  @Override
  public void agencyChecks(String[] tids) {
    for (String tid : tids) {
      try {
        agencyDao.checkAgency(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }


  @Override
  public AgencyPojo findagencyStatusByUserId(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return agencyDao.findagencyStatusByUserId(id);
  }
}
