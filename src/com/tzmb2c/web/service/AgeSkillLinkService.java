package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgeSkillLinkPojo;

public interface AgeSkillLinkService {

  /**
   * 通过年龄查询年龄相对应的能力
   * 
   * @param ageSkillLinkPojo 年龄与能力连接Pojo
   * @return 相对应的能力
   * @throws SQLException
   */
  List<AgeSkillLinkPojo> findAgeSkillLinkByAge(Long ageId) throws SQLException;

  /**
   * 通过能力查询相对应的次能力
   * 
   * @param ageSkillLinkPojo 年龄与能力连接Pojo
   * @return 相对应的次能力
   * @throws SQLException
   */
  List<AgeSkillLinkPojo> findSecondSkillBySkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException;

  /**
   * 通过次能力查询相对应的商品类型
   * 
   * @param ageSkillLinkPojo 年龄与能力连接Pojo
   * @return 相对应的次能力
   * @throws SQLException
   */
  List<AgeSkillLinkPojo> findProductBySecondSkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException;

  /**
   * 通过年龄字典value查询相对应的能力
   * 
   * @param ageType 年龄字典value
   * @return
   * @throws SQLException
   */
  List<AgeSkillLinkPojo> findSkillByAgeType(int ageType) throws SQLException;
  
  /** 
   * 新增记录
   * @param ageSkillLink 四级联动记录实体
   * @return int
   */
  public int add(AgeSkillLinkPojo ageSkillLink);

  /** 
   * 更新记录
   * @param ageSkillLink 四级联动记录实体
   * @return int
   */
  public int update(AgeSkillLinkPojo ageSkillLink);
  
  /** 
   * 删除记录
   * @param id 记录ID
   * @return int
   */
  public int delete(Integer id);

  /** 
   * 根据id查询记录
   * @param id 记录ID
   * @return AgeSkillLinkPojo 四级联动实体
   */
  public AgeSkillLinkPojo getById(Integer id);

  /** 
   * 查询记录条数
   * @param params Map类型的参数
   * @return Integer
   */
  public Integer countBy(Map<String, Object> params);

  /** 
   * 查询记录信息
   * @param params Map类型的参数
   * @return List<AgeSkillLinkPojo>
   */
  public List<AgeSkillLinkPojo> listPage(Map<String, Object> params);

}
