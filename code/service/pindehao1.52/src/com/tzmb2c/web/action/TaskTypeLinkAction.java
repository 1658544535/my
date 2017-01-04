package com.tzmb2c.web.action;

import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.TaskTypeLinkService;

/**
 * 年龄与能力连接Action
 * 
 * @author LinJianhong
 * 
 */
public class TaskTypeLinkAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  private Long taskType; // 年龄id
  private Long skillValue; // 能力value
  private Long secondSkillValue; // 次能力value
  private Long ageValue; // 年龄value
  private String abilityTypes; // 能力类型
  private Long productTypeId; // 商品类型id



  public String getAbilityTypes() {
    return abilityTypes;
  }

  public void setAbilityTypes(String abilityTypes) {
    this.abilityTypes = abilityTypes;
  }

  public Long getSkillValue() {
    return skillValue;
  }

  public void setSkillValue(Long skillValue) {
    this.skillValue = skillValue;
  }

  public Long getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(Long productTypeId) {
    this.productTypeId = productTypeId;
  }

  public Long getAgeValue() {
    return ageValue;
  }

  public void setAgeValue(Long ageValue) {
    this.ageValue = ageValue;
  }



  public Long getSecondSkillValue() {
    return secondSkillValue;
  }

  public void setSecondSkillValue(Long secondSkillValue) {
    this.secondSkillValue = secondSkillValue;
  }



  @Autowired
  private TaskTypeLinkService taskTypeLinkService;
  @Autowired
  private SysDictService sysDictService;

  /**
   * 获取次任务类型
   * 
   * @return
   * @throws SQLException
   */
  public String getTaskTypeLink() throws Exception {
    SysDictPojo sysDictPojo = new SysDictPojo();
    sysDictPojo.setType("task_type");
    sysDictPojo.setValue(taskType.toString());
    JSONArray json =
        JSONArray.fromObject(taskTypeLinkService.findTaskTypeLinkByTaskType(sysDictService
            .getSysDictByTypeAndValue(sysDictPojo).getId()));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());

    return SUCCESS;
  }

  public Long getTaskType() {
    return taskType;
  }

  public void setTaskType(Long taskType) {
    this.taskType = taskType;
  }



}
