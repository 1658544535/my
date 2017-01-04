package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tzmb2c.web.pojo.UserGrowthLinePojo;

public interface UserGrowthLineMapper {

  UserGrowthLinePojo getUserGrowthLineById(Long id);

  UserGrowthLinePojo getUserGrowthLineByUid(Map<String, Object> params);

  int userGrowthLineCount(Map<String, Object> params);

  List<UserGrowthLinePojo> userGrowthLineList(Map<String, Object> params);

  int addUserGrowthLine(UserGrowthLinePojo userGrowthLine);

  int updateUserGrowthLine(UserGrowthLinePojo userGrowthLine);

  int batchAgeUpdGrowLine(@Param(value="ageType") Integer ageType);

  int batchMonthUpdGrowLine();

  int deleteUserGrowthLineById(Long id);

  public int addUserScore(Map<String, Object> params);
}
