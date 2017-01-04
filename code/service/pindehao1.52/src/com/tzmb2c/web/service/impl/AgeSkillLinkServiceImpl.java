package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgeSkillLinkDao;
import com.tzmb2c.web.pojo.AgeSkillLinkPojo;
import com.tzmb2c.web.service.AgeSkillLinkService;

public class AgeSkillLinkServiceImpl implements AgeSkillLinkService {
  @Autowired
  private AgeSkillLinkDao ageSkillLinkDao;


  @Override
  public List<AgeSkillLinkPojo> findAgeSkillLinkByAge(Long ageId) throws SQLException {
    return ageSkillLinkDao.findAgeSkillLinkByAge(ageId);
  }


  @Override
  public List<AgeSkillLinkPojo> findSecondSkillBySkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException {
    return ageSkillLinkDao.findSecondSkillBySkill(ageSkillLinkPojo);
  }


  @Override
  public List<AgeSkillLinkPojo> findProductBySecondSkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException {

    return ageSkillLinkDao.findProductBySecondSkill(ageSkillLinkPojo);
  }


  @Override
  public List<AgeSkillLinkPojo> findSkillByAgeType(int ageType) throws SQLException {
    return ageSkillLinkDao.findSkillByAgeType(ageType);
  }

  @Override
  public int add(AgeSkillLinkPojo ageSkillLink) {
    if (null == ageSkillLink) {
      return 0;
    }
    int rows = ageSkillLinkDao.add(ageSkillLink);
    return rows;
  }

  @Override
  public int update(AgeSkillLinkPojo ageSkillLink) {
    if (null == ageSkillLink) {
      return 0;
    }
    int rows = ageSkillLinkDao.update(ageSkillLink);
    return rows;
  }

  @Override
  public int delete(Integer id) {
    if (null == id) {
      return 0;
    }
    int rows = ageSkillLinkDao.delete(id);
    return rows;
  }

  @Override
  public AgeSkillLinkPojo getById(Integer id) {
    if (null == id) {
      return null;
    }
    AgeSkillLinkPojo ageSkillLink = ageSkillLinkDao.getById(id);
    return ageSkillLink;
  }

  public Integer countBy(Map<String, Object> params) {
    Integer rows = ageSkillLinkDao.countBy(params);
    return rows;
  }

  public List<AgeSkillLinkPojo> listPage(Map<String, Object> params) {
    List<AgeSkillLinkPojo> lists = ageSkillLinkDao.listPage(params);
    return lists;
  }

}
