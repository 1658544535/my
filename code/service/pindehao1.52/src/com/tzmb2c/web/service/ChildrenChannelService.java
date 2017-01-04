package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ChildrenChannelPojo;

public interface ChildrenChannelService {
  List<ChildrenChannelPojo> findChildrenChannelWeb(Map<String, Object> map);

  int findChildrenChannelCount(Map<String, Object> map);

  List<ChildrenChannelPojo> findChildrenChannelList(Map<String, Object> map);

  void delChildrenChannelById(Long id);

  void checkChildrenChannelById(Long id);

  void uncheckChildrenChannelById(Long id);

  ChildrenChannelPojo findChildrenChannelById(Long id);

  void insertChildrenChannel(ChildrenChannelPojo childrenChannelPojo);

  void updateChildrenChannelById(ChildrenChannelPojo childrenChannelPojo);
}
