package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SpecialShowPojo;

public interface SpecialShowMapper {
  List<SpecialShowPojo> findSpecialShowList(Map<String, Object> map) throws SQLException;

  int findSpecialShowCount(Map<String, Object> map) throws SQLException;

  SpecialShowPojo findSpecialShowById(Long id) throws SQLException;

  SpecialShowPojo findSpecialShowByActivityId(Long activityId) throws SQLException;

  void insertSpecialShow(SpecialShowPojo specialShow) throws SQLException;

  void delSpecialShow(Long id) throws SQLException;

  void checkSpecialShow(Long id) throws SQLException;

  void uncheckSpecialShow(Long id) throws SQLException;

  void modifySpecialShow(Long id) throws SQLException;

  void schedulingSpecialShow(Long id) throws SQLException;

  void updateSpecialShow(SpecialShowPojo specialShow) throws SQLException;

  void updateSpecialShowEnd() throws SQLException;

  List<SpecialShowPojo> findSpecialShowByUid(Long id) throws SQLException;

  List<SpecialShowPojo> findSpecialShowByStatus(Long id);
}
