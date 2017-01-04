package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserGrowthLinePojo;

public interface UserGrowthLineService {

  public int addUserGrowthLine(UserGrowthLinePojo userGrowthLine);

  public int updateUserGrowthLine(UserGrowthLinePojo userGrowthLine);

  public int deleteUserGrowthLineById(Long id);

  public UserGrowthLinePojo getUserGrowthLineById(Long id);

  public UserGrowthLinePojo getUserGrowthLineByUid(Map<String, Object> params);

  public Integer userGrowthLineCount(Map<String, Object> params);

  public List<UserGrowthLinePojo> userGrowthLineList(Map<String, Object> params);

  public void userGrowthLineInit(UserGrowthLinePojo userGrowthLine);

  /**
   * 添加用户积分
   * 
   * @param params
   * @return
   */
  public int addUserScore(Map<String, Object> params);

  public int batchAgeUpdGrowLine(int ageType);

  public int batchMonthUpdGrowLine();

}
