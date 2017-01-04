package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PurchaserAttentionPojo;

public interface PurchaserAttentionMapper {

  public List<PurchaserAttentionPojo> attentionAllList(Map<String, Object> map);

  public int attentionAllCount(Map<String, Object> map);

  public PurchaserAttentionPojo findAttentionById(Long id);

  public void addAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public void delAllAttentionById(String id) throws SQLException;

  public void delAttention(PurchaserAttentionPojo purchaserAttentionPojo) throws SQLException;

  public void updateAttention(PurchaserAttentionPojo purchaserAttentionPojo);

  public void checkAllPurchaserAttentionById(String id) throws SQLException;

  public void checkPurchaserAttention(PurchaserAttentionPojo purchaserAttentionPojo)
      throws SQLException;

  public PurchaserAttentionPojo attentionByUserId(Long id);

}
