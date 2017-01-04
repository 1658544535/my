/*
 * 文 件 名:  ActivityProductCommentAction.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-28
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ActivityProductCommentPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ActivityProductCommentService;

public class ActivityProductCommentAction extends SuperAction {
  @Autowired
  private ActivityProductCommentService activityProductCommentService;
  private ActivityProductCommentPojo activityProductCommentPojo;
  private Long id;
  private String[] tids;
  private String result;
  private Integer activityId;
  
  public Long getId(){
	return this.id;
  }
  
  public void setId(Long id){
	this.id = id;
  }
  
  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  public String[] getTids(){
	  return this.tids;
  }
  
  public void setTids(String[] tids){
	  this.tids = tids;
  }
  
  public String getResult(){
	  return this.result;
  }
  
  public void setResult(String result){
	  this.result = result;
  }

  public ActivityProductCommentPojo getActivityProductCommentPojo() {
    return activityProductCommentPojo;
  }

  public void setActivityProductCommentPojo(ActivityProductCommentPojo activityProductCommentPojo) {
    this.activityProductCommentPojo = activityProductCommentPojo;
  }

  /**
   * 查询全部条数
   */
  public String goActivityProductComment() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("activityId", activityProductCommentPojo.getActivityId());
    if(activityProductCommentPojo.getKeywords() != null){
      map.put("keywords", activityProductCommentPojo.getKeywords());
      }
	try {
      int i = activityProductCommentService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 查询全部条数
   */
  public String activityProductCommentRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("activityId", activityProductCommentPojo.getActivityId());
    if(activityProductCommentPojo.getKeywords() != null){
      map.put("keywords", activityProductCommentPojo.getKeywords());
      }
	try {
      int i = activityProductCommentService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String activityProductCommentList() throws Exception {
	if (page == null) {
	  page = new Pager();
	  page.setPageNo(1);
	}
	page.setPageSize(10);
	Map<String, Object> map = new HashMap<String, Object>();	
	map.put("activityId", activityProductCommentPojo.getActivityId());
	if(activityProductCommentPojo.getKeywords() != null){
	map.put("keywords", activityProductCommentPojo.getKeywords());
	}
	map.put("pageSize", page.getPageSize());
	map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
	List<ActivityProductCommentPojo> activityProductCommentList = null;
	try {
      activityProductCommentList = activityProductCommentService.listPage(map);
      JSONArray json = JSONArray.fromObject(activityProductCommentList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }
  
  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddActivityProductComment() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
	SysLoginPojo user = UserUtil.getUser();
	if(user != null && activityProductCommentPojo != null){
	  activityProductCommentPojo.setCreateBy(user.getId());
	  activityProductCommentPojo.setCreateDate(new Date());
	  activityProductCommentPojo.setUpdateBy(user.getId());
	  activityProductCommentPojo.setUpdateDate(new Date());
	  try {
		activityProductCommentService.add(activityProductCommentPojo);
		FileUtil.alertMessageBySkip("新增成功！", "goActivityProductComment.do");
	  } catch (Exception e) {
		e.printStackTrace();
		FileUtil.alertMessageBySkip("新增失败！", "goAddActivityProductComment.do");
	  }
	} else {
	  FileUtil.alertMessageBySkip("操作失败！", "goActivityProductComment.do");
	}
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditActivityProductComment() throws Exception {
    if (id != null && id > 0) {
      activityProductCommentPojo = activityProductCommentService.getById(id);
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
	SysLoginPojo user = UserUtil.getUser();
	if(user != null && activityProductCommentPojo != null){
	  activityProductCommentPojo.setUpdateBy(user.getId());
	  activityProductCommentPojo.setUpdateDate(new Date());
	  try {
		activityProductCommentService.update(activityProductCommentPojo);
		FileUtil.alertMessageBySkip("提交成功！", "goActivityProductComment.do");
	  } catch (Exception e) {
		e.printStackTrace();
		FileUtil.alertMessageBySkip("提交失败！", "goEditActivityProductComment.do?id=" + activityProductCommentPojo.getId());
	  }
	} else {
	  FileUtil.alertMessageBySkip("操作失败！", "goActivityProductComment.do");
	}
    
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
	SysLoginPojo user = UserUtil.getUser();
	this.result = "0";
	if(user != null && id != null && id > 0){
	  activityProductCommentPojo = new ActivityProductCommentPojo();
	  activityProductCommentPojo.setId(id);
	  activityProductCommentPojo.setStatus(1);
	  activityProductCommentPojo.setUpdateBy(user.getId());
	  activityProductCommentPojo.setUpdateDate(new Date());
	  try {
		activityProductCommentService.update(activityProductCommentPojo);
		this.result = "1";
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
	SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
	if(user != null && id != null && id > 0){
	  activityProductCommentPojo = new ActivityProductCommentPojo();
	  activityProductCommentPojo.setId(id);
	  activityProductCommentPojo.setStatus(0);
	  activityProductCommentPojo.setUpdateBy(user.getId());
	  activityProductCommentPojo.setUpdateDate(new Date());
	  try {
	    activityProductCommentService.update(activityProductCommentPojo);
	    this.result = "1";
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	}
    return SUCCESS;
  }
  
  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
	SysLoginPojo user = UserUtil.getUser();
	this.result = "0";
	if(user != null && id != null && id > 0){
	  try {
	    activityProductCommentService.delete(id);
	    this.result = "1";
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	}
    return SUCCESS;
  }
  
  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
	SysLoginPojo user = UserUtil.getUser();
	this.result = "0";
	if(user != null && tids != null && tids.length > 0){
	  boolean part  = false;
	  activityProductCommentPojo = new ActivityProductCommentPojo();
	  activityProductCommentPojo.setStatus(1);
	  for (String tid : tids) {
	  	activityProductCommentPojo.setId(Long.valueOf(tid));
	  	activityProductCommentPojo.setUpdateBy(user.getId());
	  	activityProductCommentPojo.setUpdateDate(new Date());
	  	try {
	  	  activityProductCommentService.update(activityProductCommentPojo);
	  	} catch (Exception e) {
	  	  part = true;
	  	  e.printStackTrace();
	  	}
	  }
	  if(part){
	  	this.result = "2";
	  } else {
	  	this.result = "1";
	  }
	}
    return SUCCESS;
  }
  
  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAll() throws Exception {
	SysLoginPojo user = UserUtil.getUser();
	this.result = "0";
	if(user != null && tids != null && tids.length > 0){
	  boolean part  = false;
	  activityProductCommentPojo = new ActivityProductCommentPojo();
	  activityProductCommentPojo.setStatus(0);
	  for (String tid : tids) {
	  	activityProductCommentPojo.setId(Long.valueOf(tid));
	  	activityProductCommentPojo.setUpdateBy(user.getId());
	  	activityProductCommentPojo.setUpdateDate(new Date());
	  	try {
	  	  activityProductCommentService.update(activityProductCommentPojo);
	  	} catch (Exception e) {
	  	  part = true;
	  	  e.printStackTrace();
	  	}
	  }
	  if(part){
	  	this.result = "2";
	  } else {
	  	this.result = "1";
	  }
	}
    return SUCCESS;
  }
  
  /**
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
	SysLoginPojo user = UserUtil.getUser();
	this.result = "0";
	if(user != null && tids != null && tids.length > 0){
	  boolean part  = false;
	  for (String tid : tids) {
	  	try {
	  	  activityProductCommentService.delete(Long.valueOf(tid));
	  	} catch (Exception e) {
	  	  part = true;
	  	  e.printStackTrace();
	  	}
	  }
	  if(part){
	  	this.result = "2";
	  } else {
	  	this.result = "1";
	  }
	}
    return SUCCESS;
  }
}
