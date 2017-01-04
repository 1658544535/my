package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.FeedbackDao;
import com.tzmb2c.web.mapper.FeedbackMapper;
import com.tzmb2c.web.pojo.FeedBackPojo;

public class FeedbackDaoImpl implements FeedbackDao {

  @Autowired
  private FeedbackMapper feedbackMapper;

  @Override
  public void feedBackAdd(FeedBackPojo feedBackPojo) throws SQLException {

    feedbackMapper.feedBackAdd(feedBackPojo);
  }

  @Override
  public List<FeedBackPojo> getFeedBackAll(Map<String, Object> map) {

    return feedbackMapper.getFeedBackAll(map);
  }

  @Override
  public int FeedBackListCount(Map<String, Object> map) {

    return feedbackMapper.FeedBackListCount(map);
  }

  @Override
  public FeedBackPojo findFeedBackById(Long id) throws SQLException {
    return feedbackMapper.findFeedBackById(id);
  }

  @Override
  public void updateFeedBackOk(FeedBackPojo feedBackPojo) throws SQLException {
    feedbackMapper.updateFeedBackOk(feedBackPojo);

  }



}
