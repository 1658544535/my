package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PurchaserAttentionPojo;

public interface PurchaserAttentionDao {

  public List<PurchaserAttentionPojo> attentionAllList(Map<String, Object> map);

  public int attentionAllCount(Map<String, Object> map);

  public void addAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public PurchaserAttentionPojo findAttentionById(Long id) throws SQLException;

  public void delAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public void delAllAttentionById(String id) throws SQLException;

  public void updateAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public void checkPurchaserAttention(PurchaserAttentionPojo purchaserAttentionPojo)
      throws SQLException;

  public void checkAllPurchaserAttention(String id) throws SQLException;

  PurchaserAttentionPojo attentionByUserId(Long id) throws SQLException;

}
