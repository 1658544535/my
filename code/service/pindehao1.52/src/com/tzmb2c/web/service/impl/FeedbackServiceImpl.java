package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.FeedbackDao;
import com.tzmb2c.web.pojo.FeedBackPojo;
import com.tzmb2c.web.service.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {
  @Autowired
  private FeedbackDao feedbackDao;

  @Override
  public void feedBackAdd(FeedBackPojo feedBackPojo) throws SQLException {

    feedbackDao.feedBackAdd(feedBackPojo);
  }

  @Override
  public List<FeedBackPojo> getFeedBackAll(Map<String, Object> map) {

    return feedbackDao.getFeedBackAll(map);
  }

  @Override
  public int FeedBackListCount(Map<String, Object> map) {

    return feedbackDao.FeedBackListCount(map);
  }

  @Override
  public FeedBackPojo findFeedBackById(Long id) throws SQLException {
    return feedbackDao.findFeedBackById(id);
  }

  @Override
  public void updateFeedBackOk(FeedBackPojo feedBackPojo) throws SQLException {
    feedbackDao.updateFeedBackOk(feedBackPojo);

  }


}
