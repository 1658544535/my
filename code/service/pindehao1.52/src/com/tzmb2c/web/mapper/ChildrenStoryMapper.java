package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ChildrenStoryPojo;

public interface ChildrenStoryMapper {
  List<ChildrenStoryPojo> findChildrenStoryList(Map<String, Object> map) throws SQLException;

  int findChildrenStoryCount(Map<String, Object> map) throws SQLException;

  void insertChildrenStory(ChildrenStoryPojo childrenStoryPojo) throws SQLException;

  void delChildrenStory(Long id) throws SQLException;

  ChildrenStoryPojo findChildrenStoryById(Long id) throws SQLException;

  void updateChildrenStory(ChildrenStoryPojo childrenStoryPojo) throws SQLException;

  void checkChildrenStory(Long id) throws SQLException;

  void uncheckChildrenStory(Long id) throws SQLException;
}
