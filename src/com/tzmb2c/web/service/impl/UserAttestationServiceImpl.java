package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserAttestationDao;
import com.tzmb2c.web.pojo.UserAttestationPojo;
import com.tzmb2c.web.service.UserAttestationService;

public class UserAttestationServiceImpl implements UserAttestationService {

  @Autowired
  private UserAttestationDao userAttestationDao;

  @Override
  public void addAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationDao.addAttestationInfo(userAttestationPojo);
  }

  @Override
  public UserAttestationPojo findAttestationInfoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userAttestationDao.findAttestationInfoById(id);
  }

  @Override
  public void updateAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationDao.updateAttestationInfo(userAttestationPojo);
  }

  @Override
  public void delAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationDao.delAttestationInfo(userAttestationPojo);
  }

  @Override
  public void checkAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationDao.checkAttestationInfo(userAttestationPojo);
  }

  @Override
  public List<UserAttestationPojo> attestationInfoAllList(UserAttestationPojo userAttestationPojo,
      Pager page, String type) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userAttestationPojo != null) {
      map.put("userId", userAttestationPojo.getUserId());// 用户ID
      map.put("userName", userAttestationPojo.getUserName());// 用户昵称
      map.put("status", userAttestationPojo.getStatus());// 显示状态(取业务字典：0未审核1已审核)
    }
    map.put("type", type);// 用户类型
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<UserAttestationPojo> list = userAttestationDao.attestationInfoAllList(map);
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
  public int attestationInfoAllCount(UserAttestationPojo userAttestationPojo, String type) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userAttestationPojo != null) {
      map.put("userId", userAttestationPojo.getUserId());// 用户ID
      map.put("userName", userAttestationPojo.getUserName());// 用户昵称
      map.put("status", userAttestationPojo.getStatus());// 显示状态(取业务字典：0未审核1已审核)
    }
    map.put("type", type);// 用户类型
    return userAttestationDao.attestationInfoAllCount(map);
  }

  @Override
  public void delAllAttestationInfoById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        userAttestationDao.delAllAttestationInfoById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkAllAttestationInfoById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        userAttestationDao.checkAllAttestationInfoById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

}
