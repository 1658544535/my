package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ChildrenChannelDao;
import com.tzmb2c.web.mapper.ChildrenChannelMapper;
import com.tzmb2c.web.pojo.ChildrenChannelPojo;

public class ChildrenChannelDaoImpl implements ChildrenChannelDao {
  @Autowired
  private ChildrenChannelMapper childrenChannelMapper;

  @Override
  public List<ChildrenChannelPojo> findChildrenChannelWeb(Map<String, Object> map) {
    return childrenChannelMapper.findChildrenChannelWeb(map);
  }

  @Override
  public int findChildrenChannelCount(Map<String, Object> map) {
    return childrenChannelMapper.findChildrenChannelCount(map);
  }

  @Override
  public List<ChildrenChannelPojo> findChildrenChannelList(Map<String, Object> map) {
    return childrenChannelMapper.findChildrenChannelList(map);
  }

  @Override
  public void delChildrenChannelById(Long id) {
    childrenChannelMapper.delChildrenChannelById(id);

  }

  @Override
  public void checkChildrenChannelById(Long id) {
    childrenChannelMapper.checkChildrenChannelById(id);

  }

  @Override
  public void uncheckChildrenChannelById(Long id) {
    childrenChannelMapper.uncheckChildrenChannelById(id);

  }

  @Override
  public ChildrenChannelPojo findChildrenChannelById(Long id) {
    return childrenChannelMapper.findChildrenChannelById(id);
  }

  @Override
  public void insertChildrenChannel(ChildrenChannelPojo childrenChannelPojo) {
    childrenChannelMapper.insertChildrenChannel(childrenChannelPojo);
  }

  @Override
  public void updateChildrenChannelById(ChildrenChannelPojo childrenChannelPojo) {
    childrenChannelMapper.updateChildrenChannelById(childrenChannelPojo);

  }



}
