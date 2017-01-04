package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgeSkillLinkPojo;

/**
 * 年龄与能力连接Mapper
 * 
 * @author LinJianhong
 * 
 */
public interface AgeSkillLinkMapper {

  /**
   * 通过年龄查询年龄相对应的能力
   * 
   * @param ageId 年龄id
   * @return
   * @throws SQLException
   */
  List<AgeSkillLinkPojo> findAgeSkillLinkByAge(Long ageId) throws SQLException;

  /**
   * 通过能力查询相对应的次能力
   * 
   * @param ageSkillLinkPojo
   * @return
   * @throws SQLException
   */
  List<AgeSkillLinkPojo> findSecondSkillBySkill(AgeSkillLinkPojo ageSkillLinkPojo)
      throws SQLException;

  /**
   * 通过次能力查询相对应的商品类型
   * 
   * @param ageSkillLinkPojo
   * @return
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
   * 根据id查询记录
   * @param id 记录ID
   * @return AgeSkillLinkPojo 四级联动实体
   */
  AgeSkillLinkPojo getById(Integer id);
  
  /** 
   * 查询记录条数
   * @param params Map类型的参数
   * @return int 记录条数
   */
  int countBy(Map<String, Object> params);

  /** 
   * 查询记录信息
   * @param params Map类型的参数
   * @return List<AgeSkillLinkPojo> 四级联动列表
   */
  List<AgeSkillLinkPojo> listPage(Map<String, Object> params);
  
  /** 
   * 插入数据
   * @param ageSkillLink 四级联动实体
   * @return int
   */
  int insert(AgeSkillLinkPojo ageSkillLink);
  
  /** 
   * 更新数据
   * @param ageSkillLink 四级联动实体
   * @return int
   */
  int update(AgeSkillLinkPojo ageSkillLink);
  
  /** 
   * 根据ID删除记录
   * @param id 记录ID
   * @return int
   */
  int deleteById(Integer id);
}
