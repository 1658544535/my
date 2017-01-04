package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ChildrenStoryDao;
import com.tzmb2c.web.pojo.ChildrenStoryPojo;
import com.tzmb2c.web.service.ChildrenStoryService;

public class ChildrenStoryServiceImpl implements ChildrenStoryService {
  @Autowired
  private ChildrenStoryDao childrenStoryDao;

  @Override
  public List<ChildrenStoryPojo> findChildrenStoryList(Map<String, Object> map) throws SQLException {
    return childrenStoryDao.findChildrenStoryList(map);
  }

  @Override
  public int findChildrenStoryCount(Map<String, Object> map) throws SQLException {
    return childrenStoryDao.findChildrenStoryCount(map);
  }

  @Override
  public void insertChildrenStory(ChildrenStoryPojo childrenStoryPojo) throws SQLException {
    childrenStoryDao.insertChildrenStory(childrenStoryPojo);
  }

  @Override
  public void delChildrenStory(Long id) throws SQLException {
    childrenStoryDao.delChildrenStory(id);
  }

  @Override
  public ChildrenStoryPojo findChildrenStoryById(Long id) throws SQLException {
    return childrenStoryDao.findChildrenStoryById(id);
  }

  @Override
  public void updateChildrenStory(ChildrenStoryPojo childrenStoryPojo) throws SQLException {
    childrenStoryDao.updateChildrenStory(childrenStoryPojo);
  }

  @Override
  public void checkChildrenStory(Long id) throws SQLException {
    childrenStoryDao.checkChildrenStory(id);
  }

  @Override
  public void uncheckChildrenStory(Long id) throws SQLException {
    childrenStoryDao.uncheckChildrenStory(id);
  }
}
