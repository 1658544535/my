package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ChildrenChannelDao;
import com.tzmb2c.web.pojo.ChildrenChannelPojo;
import com.tzmb2c.web.service.ChildrenChannelService;

public class ChildrenChannelServiceImpl implements ChildrenChannelService {
  @Autowired
  private ChildrenChannelDao childrenChannelDao;

  @Override
  public List<ChildrenChannelPojo> findChildrenChannelWeb(Map<String, Object> map) {
    return childrenChannelDao.findChildrenChannelWeb(map);
  }

  @Override
  public int findChildrenChannelCount(Map<String, Object> map) {
    return childrenChannelDao.findChildrenChannelCount(map);
  }

  @Override
  public List<ChildrenChannelPojo> findChildrenChannelList(Map<String, Object> map) {
    return childrenChannelDao.findChildrenChannelList(map);
  }

  @Override
  public void delChildrenChannelById(Long id) {
    childrenChannelDao.delChildrenChannelById(id);

  }

  @Override
  public void checkChildrenChannelById(Long id) {
    childrenChannelDao.checkChildrenChannelById(id);

  }

  @Override
  public void uncheckChildrenChannelById(Long id) {
    childrenChannelDao.uncheckChildrenChannelById(id);

  }

  @Override
  public ChildrenChannelPojo findChildrenChannelById(Long id) {
    return childrenChannelDao.findChildrenChannelById(id);
  }

  @Override
  public void insertChildrenChannel(ChildrenChannelPojo childrenChannelPojo) {
    childrenChannelDao.insertChildrenChannel(childrenChannelPojo);

  }

  @Override
  public void updateChildrenChannelById(ChildrenChannelPojo childrenChannelPojo) {
    childrenChannelDao.updateChildrenChannelById(childrenChannelPojo);

  }
}
