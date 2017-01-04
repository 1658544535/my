package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserGrowthLineDao;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;
import com.tzmb2c.web.service.UserGrowthLineService;

public class UserGrowthLineServiceImpl implements UserGrowthLineService {

  @Autowired
  private UserGrowthLineDao userGrowthLineDao;

  @Override
  @Transactional
  public int addUserGrowthLine(UserGrowthLinePojo userGrowthLine) {
    if (null == userGrowthLine) {
      return 0;
    }
    int rows = userGrowthLineDao.addUserGrowthLine(userGrowthLine);
    return rows;
  }

  @Override
  public int updateUserGrowthLine(UserGrowthLinePojo userGrowthLine) {
    if (null == userGrowthLine) {
      return 0;
    }
    int rows = userGrowthLineDao.updateUserGrowthLine(userGrowthLine);
    return rows;
  }

  @Override
  public int deleteUserGrowthLineById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userGrowthLineDao.deleteUserGrowthLineById(id);
    return rows;
  }


  @Override
  public UserGrowthLinePojo getUserGrowthLineById(Long id) {
    if (null == id) {
      return null;
    }
    UserGrowthLinePojo userGrowthLine = userGrowthLineDao.getUserGrowthLineById(id);
    //
    return userGrowthLine;
  }

  @Override
  public Integer userGrowthLineCount(Map<String, Object> params) {
    Integer rows = userGrowthLineDao.userGrowthLineCount(params);
    return rows;
  }

  @Override
  public List<UserGrowthLinePojo> userGrowthLineList(Map<String, Object> params) {
    List<UserGrowthLinePojo> lists = userGrowthLineDao.userGrowthLineList(params);

    return lists;
  }

  @Override
  public int addUserScore(Map<String, Object> params) {
    return userGrowthLineDao.addUserScore(params);
  }

  @Override
  public UserGrowthLinePojo getUserGrowthLineByUid(Map<String, Object> params) {
    return userGrowthLineDao.getUserGrowthLineByUid(params);
  }

  @Override
  public int batchAgeUpdGrowLine(int ageType) {
    return userGrowthLineDao.batchAgeUpdGrowLine(ageType);
  }

  @Override
  public int batchMonthUpdGrowLine() {
    return userGrowthLineDao.batchMonthUpdGrowLine();
  }

  @Override
  public void userGrowthLineInit(UserGrowthLinePojo userGrowthLine) {
    userGrowthLine.setUserId(0l);
    userGrowthLine.setBabyId(0l);
    userGrowthLine.setListenSkill(0);
    userGrowthLine.setLanguageSkill(0);
    userGrowthLine.setVisionSkill(0);
    userGrowthLine.setMoveSkill(0);
    userGrowthLine.setTouchSkill(0);
    userGrowthLine.setActionSkill(0);
    userGrowthLine.setEmotionSkill(0);
    userGrowthLine.setLanguageGrow(0);
    userGrowthLine.setCoordinationSkill(0);
    userGrowthLine.setBrainGrow(0);
    userGrowthLine.setExploringSkill(0);
    userGrowthLine.setSpecialSkill(0);
    userGrowthLine.setSportSkill(0);
    userGrowthLine.setThinkSkill(0);
    userGrowthLine.setLearnSkill(0);
    userGrowthLine.setListenSkillLast(0);
    userGrowthLine.setLanguageSkillLast(0);
    userGrowthLine.setVisionSkillLast(0);
    userGrowthLine.setMoveSkillLast(0);
    userGrowthLine.setTouchSkillLast(0);
    userGrowthLine.setActionSkillLast(0);
    userGrowthLine.setEmotionSkillLast(0);
    userGrowthLine.setLanguageGrowLast(0);
    userGrowthLine.setCoordinationSkillLast(0);
    userGrowthLine.setBrainGrowLast(0);
    userGrowthLine.setExploringSkillLast(0);
    userGrowthLine.setSpecialSkillLast(0);
    userGrowthLine.setSportSkillLast(0);
    userGrowthLine.setThinkSkillLast(0);
    userGrowthLine.setLearnSkillLast(0);
    userGrowthLine.setScore(0l);
    userGrowthLine.setCognitiveSkill(0);
    userGrowthLine.setCognitiveSkillLast(0);
  }
}
