package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.FeedBackPojo;

public interface FeedbackService {

  public void feedBackAdd(FeedBackPojo feedBackPojo) throws SQLException;

  /**
   * 获取所有意见反馈信息
   * 
   * @param map
   * @return
   */
  public List<FeedBackPojo> getFeedBackAll(Map<String, Object> map);

  /**
   * 意见反馈分页
   * 
   * @param map
   * @return
   */
  public int FeedBackListCount(Map<String, Object> map);

  public FeedBackPojo findFeedBackById(Long id) throws SQLException;

  public void updateFeedBackOk(FeedBackPojo feedBackPojo) throws SQLException;;
}
