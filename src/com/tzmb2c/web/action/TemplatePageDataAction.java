package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.TemplatePageDataService;

import maowu.framework.utils.web.SuperAction;

/**
 * 
 * @author 
 */
public class TemplatePageDataAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private TemplatePageDataService templatePageDataService;
  @Autowired
  private PlatformSpecialService platformSpecialService;
  
  private TemplatePageDataPojo templatePageDataPojo;
  private String result;
  private String saveData;
  private Long pageId;
  private Long id;//专题或笔记id
  private Integer type;//模板数据类型
  
  

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPageId() {
    return pageId;
  }

  public void setPageId(Long pageId) {
    this.pageId = pageId;
  }

  public String getSaveData() {
    return saveData;
  }

  public void setSaveData(String saveData) {
    this.saveData = saveData;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }
  
  /**
   * 
   * 查询页面json
   * @return
   * @throw
   * @return String
   */
  public String getPageData() throws Exception{
    if(id == null || id == 0){
      System.out.println("id不能为空!");
      this.result = "1";
    } else if(type == null|| type == 0){
      System.out.println("type不能为空!");
      this.result = "2";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("pageId", id);
      params.put("type", type);
      templatePageDataPojo = templatePageDataService.findByParams(params);
      if(templatePageDataPojo == null){
        System.out.println("查询不到数据!");
        this.result = "3";
      } else {
        this.result = templatePageDataPojo.getData();
      }
    }
    return SUCCESS;
  }


}
