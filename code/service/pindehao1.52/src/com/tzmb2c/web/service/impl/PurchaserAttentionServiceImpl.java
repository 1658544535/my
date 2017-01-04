package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.PurchaserAttentionDao;
import com.tzmb2c.web.pojo.PurchaserAttentionPojo;
import com.tzmb2c.web.service.PurchaserAttentionService;

public class PurchaserAttentionServiceImpl implements PurchaserAttentionService {

  @Autowired
  private PurchaserAttentionDao purchaserAttentionDao;

  @Override
  public void addAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionDao.addAttention(purchaserAttentionPojo);
  }

  @Override
  public PurchaserAttentionPojo findAttentionById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return purchaserAttentionDao.findAttentionById(id);
  }

  @Override
  public PurchaserAttentionPojo attentionByUserId(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return purchaserAttentionDao.attentionByUserId(id);
  }

  @Override
  public void updateAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionDao.updateAttention(purchaserAttentionPojo);
  }

  @Override
  public void delAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionDao.delAttention(purchaserAttentionPojo);
  }

  @Override
  public List<PurchaserAttentionPojo> attentionAllList(
      PurchaserAttentionPojo purchaserAttentionPojo, Pager page, String type) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (purchaserAttentionPojo != null) {
      map.put("userId", purchaserAttentionPojo.getUserId());// 用户编号
      map.put("userName", purchaserAttentionPojo.getUserName());// 用户昵称
    }
    // map.put("type", type);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    List<PurchaserAttentionPojo> list = purchaserAttentionDao.attentionAllList(map);
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
  public int attentionAllCount(PurchaserAttentionPojo purchaserAttentionPojo) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (purchaserAttentionPojo != null) {
      map.put("userId", purchaserAttentionPojo.getUserId());// 用户编号
      map.put("userName", purchaserAttentionPojo.getUserName());// 用户昵称
    }
    return purchaserAttentionDao.attentionAllCount(map);
  }

  @Override
  public void delAllAttentionById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        purchaserAttentionDao.delAllAttentionById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkPurchaserAttention(PurchaserAttentionPojo purchaserAttentionPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionDao.checkPurchaserAttention(purchaserAttentionPojo);
  }

  @Override
  public void checkAllPurchaserAttentionById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        purchaserAttentionDao.checkAllPurchaserAttention(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

}
