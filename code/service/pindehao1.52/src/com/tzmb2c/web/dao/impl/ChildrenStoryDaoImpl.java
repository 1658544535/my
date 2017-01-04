package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ChildrenStoryDao;
import com.tzmb2c.web.mapper.ChildrenStoryMapper;
import com.tzmb2c.web.pojo.ChildrenStoryPojo;

public class ChildrenStoryDaoImpl implements ChildrenStoryDao {
  @Autowired
  private ChildrenStoryMapper childrenStoryMapper;

  @Override
  public List<ChildrenStoryPojo> findChildrenStoryList(Map<String, Object> map) throws SQLException {
    return childrenStoryMapper.findChildrenStoryList(map);
  }

  @Override
  public int findChildrenStoryCount(Map<String, Object> map) throws SQLException {
    return childrenStoryMapper.findChildrenStoryCount(map);
  }

  @Override
  public void insertChildrenStory(ChildrenStoryPojo childrenStoryPojo) throws SQLException {
    childrenStoryMapper.insertChildrenStory(childrenStoryPojo);
  }

  @Override
  public void delChildrenStory(Long id) throws SQLException {
    childrenStoryMapper.delChildrenStory(id);
  }

  @Override
  public ChildrenStoryPojo findChildrenStoryById(Long id) throws SQLException {
    return childrenStoryMapper.findChildrenStoryById(id);
  }

  @Override
  public void updateChildrenStory(ChildrenStoryPojo childrenStoryPojo) throws SQLException {
    childrenStoryMapper.updateChildrenStory(childrenStoryPojo);
  }

  @Override
  public void checkChildrenStory(Long id) throws SQLException {
    childrenStoryMapper.checkChildrenStory(id);
  }

  @Override
  public void uncheckChildrenStory(Long id) throws SQLException {
    childrenStoryMapper.uncheckChildrenStory(id);
  }
}
