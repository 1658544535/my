package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.AgencyAuthenticationDao;
import com.tzmb2c.web.pojo.AgencyAuthenticationPojo;
import com.tzmb2c.web.service.AgencyAuthenticationService;


public class AgencyAuthenticationServiceImpl implements AgencyAuthenticationService {
  @Autowired
  private AgencyAuthenticationDao agencyAuthenticationDao;

  @Override
  public void insertAgencyAuthentication(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {
    agencyAuthenticationDao.insertAgencyAuthentication(agencyAuthenticationPojo);
  }

  @Override
  public List<AgencyAuthenticationPojo> authenticationAgencyInfoAllList(
      AgencyAuthenticationPojo agencyAuthenticationPojo, Pager page, String type)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (agencyAuthenticationPojo != null) {
      map.put("user_id", agencyAuthenticationPojo.getUser_id());// 用户ID
      map.put("user_name", agencyAuthenticationPojo.getUser_name());// 用户昵称
      map.put("realName", agencyAuthenticationPojo.getRealName());// 用户实名
      map.put("auth_status", agencyAuthenticationPojo.getAuth_status());// 显示状态(取业务字典：0待认证1认证通过2认证未通过)
    }
    map.put("type", type);// 用户类型
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<AgencyAuthenticationPojo> list =
        agencyAuthenticationDao.authenticationAgencyInfoAllList(map);
    if (list != null) {
      // 判断信息是否被审核
      // for(PagePushPojo no:list){
      // BusinessDictPojo sysDictPojo = new BusinessDictPojo();
      // sysDictPojo.setBusinessDictId(m.getNoticeType());
      // BusinessDictPojo
      // syddsffDictPojo=businessDictDao.sysDictById(sysDictPojo);
      // m.setNoticeType(syddsffDictPojo.getBusinessDictName());
      // if(m.getNoticeState().equals("0")){
      // no.setNoticeState("未审核");
      // }else{
      // no.setNoticeState("已审核");
      // }
      // }
    }
    return list;
  }

  @Override
  public int authenticationAgencyInfoAllCount(AgencyAuthenticationPojo agencyAuthenticationPojo,
      String type) throws SQLException {

    Map<String, Object> map = new HashMap<String, Object>();
    if (agencyAuthenticationPojo != null) {
      map.put("user_id", agencyAuthenticationPojo.getUser_id());// 用户ID
      map.put("user_name", agencyAuthenticationPojo.getUser_name());// 用户昵称
      map.put("realName", agencyAuthenticationPojo.getRealName());// 用户实名
      map.put("auth_status", agencyAuthenticationPojo.getAuth_status());// 显示状态(取业务字典：0待认证1认证通过2认证未通过)
    }
    map.put("type", type);// 用户类型
    return agencyAuthenticationDao.authenticationAgencyInfoAllCount(map);
  }

  @Override
  public void checkAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {

    agencyAuthenticationDao.checkAuthenticationInfo(agencyAuthenticationPojo);
  }

  @Override
  public AgencyAuthenticationPojo findAuthenticationInfoById(Long agency_id) throws SQLException {

    return agencyAuthenticationDao.findAuthenticationInfoById(agency_id);
  }

  @Override
  public void updateAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {

    agencyAuthenticationDao.updateAuthenticationInfo(agencyAuthenticationPojo);
  }

  @Override
  public void checkAllAuthenticationInfoById(String[] tids) {

    for (String tid : tids) {
      try {
        Long t = Long.parseLong(tid);
        agencyAuthenticationDao.checkAllAuthenticationInfoById(t);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delAuthenticationInfo(AgencyAuthenticationPojo agencyAuthenticationPojo)
      throws SQLException {

    agencyAuthenticationDao.delAuthenticationInfo(agencyAuthenticationPojo);
  }


}
