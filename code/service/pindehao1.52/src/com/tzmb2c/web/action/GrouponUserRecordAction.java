/*
 * 文 件 名:  GrouponUserRecordAction.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-22
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
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GrouponUserRecordService;

public class GrouponUserRecordAction extends SuperAction {
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  private GrouponUserRecordPojo grouponUserRecordPojo;
  private Long id;
  private String[] tids;
  private String result;
  
  public Long getId(){
	return this.id;
  }
  
  public void setId(Long id){
	this.id = id;
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

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  /**
   * 查询全部条数
   */
  public String goGrouponUserRecord() throws Exception {
    if (page == null) {
      page = new Pager();
    }
	try {
      int i = grouponUserRecordService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 查询全部条数
   */
  public String grouponUserRecordRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
	try {
      int i = grouponUserRecordService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String grouponUserRecordList() throws Exception {
	if (page == null) {
	  page = new Pager();
	  page.setPageNo(1);
	}
	page.setPageSize(10);
	Map<String, Object> map = new HashMap<String, Object>();	
	map.put("pageSize", page.getPageSize());
	map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
	List<GrouponUserRecordPojo> grouponUserRecordList = null;
	try {
      grouponUserRecordList = grouponUserRecordService.listPage(map);
      JSONArray json = JSONArray.fromObject(grouponUserRecordList);
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
  public String goAddGrouponUserRecord() throws Exception {
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
	if(user != null && grouponUserRecordPojo != null){
	  grouponUserRecordPojo.setCreateBy(user.getId());
	  grouponUserRecordPojo.setCreateDate(new Date());
	  grouponUserRecordPojo.setUpdateBy(user.getId());
	  grouponUserRecordPojo.setUpdateDate(new Date());
	  try {
		grouponUserRecordService.add(grouponUserRecordPojo);
		FileUtil.alertMessageBySkip("新增成功！", "goGrouponUserRecord.do");
	  } catch (Exception e) {
		e.printStackTrace();
		FileUtil.alertMessageBySkip("新增失败！", "goAddGrouponUserRecord.do");
	  }
	} else {
	  FileUtil.alertMessageBySkip("操作失败！", "goGrouponUserRecord.do");
	}
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditGrouponUserRecord() throws Exception {
    if (id != null && id > 0) {
      grouponUserRecordPojo = grouponUserRecordService.getById(id);
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
	if(user != null && grouponUserRecordPojo != null){
	  grouponUserRecordPojo.setUpdateBy(user.getId());
	  grouponUserRecordPojo.setUpdateDate(new Date());
	  try {
		grouponUserRecordService.update(grouponUserRecordPojo);
		FileUtil.alertMessageBySkip("提交成功！", "goGrouponUserRecord.do");
	  } catch (Exception e) {
		e.printStackTrace();
		FileUtil.alertMessageBySkip("提交失败！", "goEditGrouponUserRecord.do?id=" + grouponUserRecordPojo.getId());
	  }
	} else {
	  FileUtil.alertMessageBySkip("操作失败！", "goGrouponUserRecord.do");
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
	  grouponUserRecordPojo = new GrouponUserRecordPojo();
	  grouponUserRecordPojo.setId(id);
	  grouponUserRecordPojo.setStatus(1);
	  grouponUserRecordPojo.setUpdateBy(user.getId());
	  grouponUserRecordPojo.setUpdateDate(new Date());
	  try {
		grouponUserRecordService.update(grouponUserRecordPojo);
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
	  grouponUserRecordPojo = new GrouponUserRecordPojo();
	  grouponUserRecordPojo.setId(id);
	  grouponUserRecordPojo.setStatus(0);
	  grouponUserRecordPojo.setUpdateBy(user.getId());
	  grouponUserRecordPojo.setUpdateDate(new Date());
	  try {
	    grouponUserRecordService.update(grouponUserRecordPojo);
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
	    grouponUserRecordService.delete(id);
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
	  grouponUserRecordPojo = new GrouponUserRecordPojo();
	  grouponUserRecordPojo.setStatus(1);
	  for (String tid : tids) {
	  	grouponUserRecordPojo.setId(Long.valueOf(tid));
	  	grouponUserRecordPojo.setUpdateBy(user.getId());
	  	grouponUserRecordPojo.setUpdateDate(new Date());
	  	try {
	  	  grouponUserRecordService.update(grouponUserRecordPojo);
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
	  grouponUserRecordPojo = new GrouponUserRecordPojo();
	  grouponUserRecordPojo.setStatus(0);
	  for (String tid : tids) {
	  	grouponUserRecordPojo.setId(Long.valueOf(tid));
	  	grouponUserRecordPojo.setUpdateBy(user.getId());
	  	grouponUserRecordPojo.setUpdateDate(new Date());
	  	try {
	  	  grouponUserRecordService.update(grouponUserRecordPojo);
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
	  	  grouponUserRecordService.delete(Long.valueOf(tid));
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
