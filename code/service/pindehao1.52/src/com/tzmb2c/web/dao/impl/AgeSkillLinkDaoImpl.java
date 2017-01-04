package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgeSkillLinkDao;
import com.tzmb2c.web.mapper.AgeSkillLinkMapper;
import com.tzmb2c.web.pojo.AgeSkillLinkPojo;

public class AgeSkillLinkDaoImpl implements AgeSkillLinkDao {
  @Autowired
  private AgeSkillLinkMapper ageSkillLinkMapper;


  @Override
  public List<AgeSkillLinkPojo> findAgeSkillLinkByAge(Long ageId) throws SQLException {
    return ageSkillLinkMapper.findAgeSkillLinkByAge(ageId);
  }



  @Override
  public List<AgeSkillLinkPojo> findSecondSkillBySkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException {

    return ageSkillLinkMapper.findSecondSkillBySkill(ageSkillLinkPojo);
  }



  @Override
  public List<AgeSkillLinkPojo> findProductBySecondSkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException {

    return ageSkillLinkMapper.findProductBySecondSkill(ageSkillLinkPojo);
  }



  @Override
  public List<AgeSkillLinkPojo> findSkillByAgeType(int ageType) throws SQLException {
    return ageSkillLinkMapper.findSkillByAgeType(ageType);
  }

  @Override
  public int add(AgeSkillLinkPojo ageSkillLink) {
    if (null == ageSkillLink) {
      return 0;
    }
    int rows = ageSkillLinkMapper.insert(ageSkillLink);
    return rows;
  }

  @Override
  public int update(AgeSkillLinkPojo ageSkillLink) {
    if (null == ageSkillLink) {
      return 0;
    }
    int rows = ageSkillLinkMapper.update(ageSkillLink);
    return rows;
  }

  @Override
  public int delete(Integer id) {
    if (null == id) {
      return 0;
    }
    int rows = ageSkillLinkMapper.deleteById(id);
    return rows;
  }

  @Override
  public AgeSkillLinkPojo getById(Integer id) {
    if (null == id) {
      return null;
    }
    AgeSkillLinkPojo ageSkillLink = ageSkillLinkMapper.getById(id);
    //
    return ageSkillLink;
  }
  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = ageSkillLinkMapper.countBy(params);
    return rows;
  }

  @Override
  public List<AgeSkillLinkPojo> listPage(Map<String, Object> params) {
    List<AgeSkillLinkPojo> lists = ageSkillLinkMapper.listPage(params);
    return lists;
  }


}
