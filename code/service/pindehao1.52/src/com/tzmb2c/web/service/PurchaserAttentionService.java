package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.PurchaserAttentionPojo;

public interface PurchaserAttentionService {

  public List<PurchaserAttentionPojo> attentionAllList(
      PurchaserAttentionPojo purchaserAttentionPojo, Pager page, String type);

  public int attentionAllCount(PurchaserAttentionPojo purchaserAttentionPojo);

  public void addAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public PurchaserAttentionPojo findAttentionById(Long id) throws SQLException;

  public void delAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public void delAllAttentionById(String[] tids);

  public void updateAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public void checkPurchaserAttention(PurchaserAttentionPojo purchaserAttentionPojo)
      throws SQLException;

  public void checkAllPurchaserAttentionById(String[] tids);

  PurchaserAttentionPojo attentionByUserId(Long id) throws SQLException;

}
