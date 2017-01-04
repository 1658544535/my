package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PurchaserAttentionDao;
import com.tzmb2c.web.mapper.PurchaserAttentionMapper;
import com.tzmb2c.web.pojo.PurchaserAttentionPojo;

public class PurchaserAttentionDaoImpl implements PurchaserAttentionDao {

  @Autowired
  private PurchaserAttentionMapper purchaserAttentionMapper;

  @Override
  public void addAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionMapper.addAttention(purchaserAttentionPojo);
  }

  @Override
  public PurchaserAttentionPojo findAttentionById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return purchaserAttentionMapper.findAttentionById(id);
  }

  @Override
  public PurchaserAttentionPojo attentionByUserId(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return purchaserAttentionMapper.attentionByUserId(id);
  }

  @Override
  public void updateAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionMapper.updateAttention(purchaserAttentionPojo);
  }

  @Override
  public void delAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionMapper.delAttention(purchaserAttentionPojo);
  }

  @Override
  public void delAllAttentionById(String id) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionMapper.delAllAttentionById(id);
  }

  @Override
  public List<PurchaserAttentionPojo> attentionAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return purchaserAttentionMapper.attentionAllList(map);
  }

  @Override
  public int attentionAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return purchaserAttentionMapper.attentionAllCount(map);
  }

  @Override
  public void checkPurchaserAttention(PurchaserAttentionPojo purchaserAttentionPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionMapper.checkPurchaserAttention(purchaserAttentionPojo);
  }

  @Override
  public void checkAllPurchaserAttention(String id) throws SQLException {
    // TODO Auto-generated method stub
    purchaserAttentionMapper.checkAllPurchaserAttentionById(id);
  }

}
