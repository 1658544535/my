package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.AgeSkillLinkPojo;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.AgeSkillLinkService;
import com.tzmb2c.web.service.LabelService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 年龄与能力连接Action
 * 
 * @author LinJianhong
 * 
 */
public class AgeSkillLinkAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  private Long ageId; // 年龄id
  private Long skillValue; // 能力value
  private Long secondSkillValue; // 次能力value
  private Long ageValue; // 年龄value
  private String abilityTypes; // 能力类型
  private Long productTypeId; // 商品类型id
  private AgeSkillLinkPojo aPojo, ageSkillLinkPojo;
  private List<AgeSkillLinkPojo> ageSkillLinkList;
  private List<SysDictPojo> ageTypeList;
  private List<SysDictPojo> skillList;
  private List<SysDictPojo> secondSkillList;
  private List<ProductTypePojo> productTypeList;

  @Autowired
  private AgeSkillLinkService ageSkillLinkService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private LabelService labelService;
  @Autowired
  private ProductTypeService productTypeService;



  public AgeSkillLinkPojo getAgeSkillLinkPojo() {
    return ageSkillLinkPojo;
  }

  public void setAgeSkillLinkPojo(AgeSkillLinkPojo ageSkillLinkPojo) {
    this.ageSkillLinkPojo = ageSkillLinkPojo;
  }

  public Long getAgeId() {
    return ageId;
  }

  public void setAgeId(Long ageId) {
    this.ageId = ageId;
  }

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



  public AgeSkillLinkPojo getaPojo() {
    return aPojo;
  }

  public void setaPojo(AgeSkillLinkPojo aPojo) {
    this.aPojo = aPojo;
  }

  public List<AgeSkillLinkPojo> getAgeSkillLinkList() {
    return ageSkillLinkList;
  }

  public void setAgeSkillLinkList(List<AgeSkillLinkPojo> ageSkillLinkList) {
    this.ageSkillLinkList = ageSkillLinkList;
  }


  public List<SysDictPojo> getAgeTypeList() {
    return ageTypeList;
  }

  public void setAgeTypeList(List<SysDictPojo> ageTypeList) {
    this.ageTypeList = ageTypeList;
  }

  public List<SysDictPojo> getSkillList() {
    return skillList;
  }

  public void setSkillList(List<SysDictPojo> skillList) {
    this.skillList = skillList;
  }

  public List<SysDictPojo> getSecondSkillList() {
    return secondSkillList;
  }

  public void setSecondSkillList(List<SysDictPojo> secondSkillList) {
    this.secondSkillList = secondSkillList;
  }

  public List<ProductTypePojo> getProductTypeList() {
    return productTypeList;
  }

  public void setProductTypeList(List<ProductTypePojo> productTypeList) {
    this.productTypeList = productTypeList;
  }

  /**
   * 获取能力类型
   * 
   * @return
   * @throws SQLException
   */
  public String getSkillTypes() throws Exception {
    SysDictPojo sysDictPojo = new SysDictPojo();
    sysDictPojo.setType("user_age");
    sysDictPojo.setValue(ageId.toString());
    JSONArray json =
        JSONArray.fromObject(ageSkillLinkService.findAgeSkillLinkByAge(sysDictService
            .getSysDictByTypeAndValue(sysDictPojo).getId()));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());

    return SUCCESS;
  }

  /**
   * 获取次能力类型
   * 
   * @return
   * @throws SQLException
   */
  public String getSecondSkillTypes() throws Exception {
    // 创建一个字典的Pojo，用以查询相对应的id值
    SysDictPojo sysDictPojo = new SysDictPojo();
    // 创建一个连接年龄与能力的Pojo，用以存放数据库查询参数
    AgeSkillLinkPojo ageSkillLinkPojo = new AgeSkillLinkPojo();
    // 构造类型为ability的Pojo
    sysDictPojo.setType("ability");
    sysDictPojo.setValue(skillValue.toString());
    // 查询sysDictPojo对应的id并存放到ageSkillLinkPojo的skillId中
    ageSkillLinkPojo.setSkillId(sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
    // 构造类型为ability的Pojo
    if (ageValue != null) {
      sysDictPojo.setType("user_age");
      sysDictPojo.setValue(ageValue.toString());
      // 查询sysDictPojo对应的id并存放到ageSkillLinkPojo的ageId中
      ageSkillLinkPojo.setAgeId(sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
    }
    // json返回数据
    JSONArray json =
        JSONArray.fromObject(ageSkillLinkService.findSecondSkillBySkill(ageSkillLinkPojo));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());

    return SUCCESS;
  }

  /**
   * 获取商品类型
   * 
   * @return
   * @throws SQLException
   */
  public String getProductTypes() throws Exception {
    // 创建一个字典的Pojo，用以查询相对应的id值
    SysDictPojo sysDictPojo = new SysDictPojo();
    // 创建一个连接年龄与能力的Pojo，用以存放数据库查询参数
    AgeSkillLinkPojo ageSkillLinkPojo = new AgeSkillLinkPojo();
    // 构造类型为ability的Pojo
    sysDictPojo.setType("ability");
    sysDictPojo.setValue(skillValue.toString());
    // 查询sysDictPojo对应的id并存放到ageSkillLinkPojo的skillId中
    ageSkillLinkPojo.setSkillId(sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());

    // 构造类型为user_age的Pojo
    sysDictPojo.setType("user_age");
    sysDictPojo.setValue(ageValue.toString());
    // 查询sysDictPojo对应的id并存放到ageSkillLinkPojo的ageId中
    ageSkillLinkPojo.setAgeId(sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());

    // 构造类型为second_ability的Pojo
    sysDictPojo.setType("second_ability");
    sysDictPojo.setValue(secondSkillValue.toString());
    // 查询sysDictPojo对应的id并存放到ageSkillLinkPojo的ageId中
    ageSkillLinkPojo.setSecondSkillId(sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());


    // json返回数据
    JSONArray json =
        JSONArray.fromObject(ageSkillLinkService.findProductBySecondSkill(ageSkillLinkPojo));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());

    return SUCCESS;
  }

  /**
   * 四级联动条数
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String ageSkillLinkListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      // 上下文容器
      ActionContext ac = ActionContext.getContext();
      Map<String, Object> map = new HashMap<String, Object>();
      // 如果pojo不为空时
      if (ageSkillLinkPojo != null) {
        // 构造一个sysDictPojo用来保存查询的基础条件
        SysDictPojo sysDictPojo = new SysDictPojo();
        // 当ageId不为空时
        if (ageSkillLinkPojo.getAgeId() != null) {
          // 设置type与value
          sysDictPojo.setType("user_age");
          sysDictPojo.setValue(ageSkillLinkPojo.getAgeId().toString());
          // 将查询到的id保存到ageId中
          map.put("ageId", sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
        }
        // 如果skillId为空时
        if (ageSkillLinkPojo.getSkillId() != null) {
          // 设置type与value
          sysDictPojo.setType("ability");
          sysDictPojo.setValue(ageSkillLinkPojo.getSkillId().toString());
          // 将查询到的id保存到skillId中
          map.put("skillId", sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
        }
        // 如果secondSkill不为空时
        if (ageSkillLinkPojo.getSecondSkillId() != null) {
          // 设置type与value
          sysDictPojo.setType("second_ability");
          sysDictPojo.setValue(ageSkillLinkPojo.getSecondSkillId().toString());
          // 将查询到的id保存到secondSkillId中
          map.put("secondSkillId", sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
        }
        // 将查询到的商品id保存到productId中
        map.put("productId", ageSkillLinkPojo.getProductId());

      }
      // 查询数据信息
      int i = ageSkillLinkService.countBy(map);
      map.clear();// map使用完毕后清空
      List<LabelPojo> labelList = labelService.findLabelList(map);// 将清空后的map作为参数进行标签查询
      ac.put("labelList", labelList);// 将列表放入上下文容器中
      page.setRowCount(i);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询四级联动列表
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String ageSkillLinkListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      new SysDictPojo();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      // 如果pojo不为空时
      if (ageSkillLinkPojo != null) {
        // 构造一个sysDictPojo用来保存查询的基础条件
        SysDictPojo sysDictPojo = new SysDictPojo();
        // 当ageId不为空时
        if (ageSkillLinkPojo.getAgeId() != null) {
          // 设置type与value
          sysDictPojo.setType("user_age");
          sysDictPojo.setValue(ageSkillLinkPojo.getAgeId().toString());
          // 将查询到的id保存到ageId中
          map.put("ageId", sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
        }
        // 如果skillId为空时
        if (ageSkillLinkPojo.getSkillId() != null) {
          // 设置type与value
          sysDictPojo.setType("ability");
          sysDictPojo.setValue(ageSkillLinkPojo.getSkillId().toString());
          // 将查询到的id保存到skillId中
          map.put("skillId", sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
        }
        // 如果secondSkill不为空时
        if (ageSkillLinkPojo.getSecondSkillId() != null) {
          // 设置type与value
          sysDictPojo.setType("second_ability");
          sysDictPojo.setValue(ageSkillLinkPojo.getSecondSkillId().toString());
          // 将查询到的id保存到secondSkillId中
          map.put("secondSkillId", sysDictService.getSysDictByTypeAndValue(sysDictPojo).getId());
        }
        // 将查询到的商品id保存到productId中
        map.put("productId", ageSkillLinkPojo.getProductId());

      }
      // 查询列表
      ageSkillLinkList = ageSkillLinkService.listPage(map);
      // 将列表用json的形式返回给前端
      JSONArray json = JSONArray.fromObject(ageSkillLinkList);
      page.setRowCount(ageSkillLinkList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 删除联动信息（直接删除）
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String delAgeSkillLink() throws Exception {
    try {
      // 获取前端传递过来的联动信息的id值
      // 如果id的值不为空或者不为0时
      if (ageSkillLinkPojo.getId() != null && ageSkillLinkPojo.getId() != 0) {
        // 调用方法直接删除数据库记录（数据库中没有判读是否删除的字段，所以直接删除）
        ageSkillLinkService.delete(ageSkillLinkPojo.getId().intValue());
        // 重定向action
        FileUtil.alertMessageBySkip("操作成功！", "ageSkillLinkList.do");
      } else {
        // 重定向action
        FileUtil.alertMessageBySkip("操作失败！", "ageSkillLinkList.do");
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "ageSkillLinkList.do");
    }
    return null;
  }

  /**
   * 跳转到新增四级联动页面
   * 
   * @return
   * @throw
   * @return String
   */
  public String goAddAgeSkillLink() {
    try {
      // 获取字典中type为“user_age”的列表
      ageTypeList = sysDictService.getSysDictListByType("user_age");
      // 获取字典中type为“ability”的列表
      skillList = sysDictService.getSysDictListByType("ability");
      // 获取字典中type为“second_ability”的列表
      secondSkillList = sysDictService.getSysDictListByType("second_ability");
      // 获取商品类型（第三级分类）
      productTypeList = productTypeService.findThridProductType();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 新增数据
   * 
   * @return
   * @throw
   * @return String
   */
  public String addAgeSkillLink() {
    if (ageSkillLinkPojo != null && ageSkillLinkPojo.getAgeId() != null
        && ageSkillLinkPojo.getSkillId() != null && ageSkillLinkPojo.getSecondSkillId() != null
        && ageSkillLinkPojo.getProductId() != null) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("ageId", ageSkillLinkPojo.getAgeId());
      params.put("skillId", ageSkillLinkPojo.getSkillId());
      params.put("secondSkillId", ageSkillLinkPojo.getSecondSkillId());
      params.put("productId", ageSkillLinkPojo.getProductId());
      int count = ageSkillLinkService.countBy(params);
      if (count == 0) {
        try {
          // 将数据插入数据库
          ageSkillLinkService.add(ageSkillLinkPojo);
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("新增失败！", "ageSkillLinkList.do");
        }
        // 重定向action
        FileUtil.alertMessageBySkip("新增成功！", "ageSkillLinkList.do");
      } else {
        FileUtil.alertMessageBySkip("该4级关联信息已存在，新增失败！", "ageSkillLinkList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("4级信息全必输，新增失败！", "ageSkillLinkList.do");
    }
    return null;
  }

  /**
   * 跳转到更新数据页面
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String goUpdateAgeSkillLink() throws Exception {
    try {
      if (ageSkillLinkPojo != null) {
        // 获取字典中type为“user_age”的列表
        ageTypeList = sysDictService.getSysDictListByType("user_age");
        // 获取字典中type为“ability”的列表
        skillList = sysDictService.getSysDictListByType("ability");
        // 获取字典中type为“second_ability”的列表
        secondSkillList = sysDictService.getSysDictListByType("second_ability");
        // 获取商品类型（第三级分类）
        productTypeList = productTypeService.findThridProductType();
        // 通过前端 传递的id获取相对应的pojo信息
        ageSkillLinkPojo = ageSkillLinkService.getById(ageSkillLinkPojo.getId().intValue());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 更新数据
   * 
   * @return
   * @throws Throwable
   * @throw
   * @return String
   */
  public String updateAgeSkillLink() throws Throwable {
    if (ageSkillLinkPojo != null && ageSkillLinkPojo.getAgeId() != null
        && ageSkillLinkPojo.getSkillId() != null && ageSkillLinkPojo.getSecondSkillId() != null
        && ageSkillLinkPojo.getProductId() != null) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("ageId", ageSkillLinkPojo.getAgeId());
      params.put("skillId", ageSkillLinkPojo.getSkillId());
      params.put("secondSkillId", ageSkillLinkPojo.getSecondSkillId());
      params.put("productId", ageSkillLinkPojo.getProductId());
      int count = ageSkillLinkService.countBy(params);
      if (count == 0) {
        try {
          // 更新数据
          ageSkillLinkService.update(ageSkillLinkPojo);
          // 重定向action
          FileUtil.alertMessageBySkip("编辑成功！", "ageSkillLinkList.do");
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("编辑失败！", "ageSkillLinkList.do");
        }
      } else {
        FileUtil.alertMessageBySkip("该4级关联信息已存在，更新失败！", "ageSkillLinkList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("4级信息全必输，编辑失败！", "ageSkillLinkList.do");
    }
    return null;
  }

}
