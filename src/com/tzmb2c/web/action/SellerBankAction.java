/*
 * 文 件 名:  SellerBankAction.java
 * 创 建 人:  admin
 * 创建时间:  2017-01-17
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
import com.tzmb2c.web.pojo.SellerBankPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SellerBankService;

public class SellerBankAction extends SuperAction {
  @Autowired
  private SellerBankService sellerBankService;
  private SellerBankPojo sellerBankPojo;
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

  public SellerBankPojo getSellerBankPojo() {
    return sellerBankPojo;
  }

  public void setSellerBankPojo(SellerBankPojo sellerBankPojo) {
    this.sellerBankPojo = sellerBankPojo;
  }

  /**
   * 查询全部条数
   */
  public String goSellerBank() throws Exception {
    if (page == null) {
      page = new Pager();
    }
	try {
      int i = sellerBankService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 查询全部条数
   */
  public String sellerBankRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
	try {
      int i = sellerBankService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String sellerBankList() throws Exception {
	if (page == null) {
	  page = new Pager();
	  page.setPageNo(1);
	}
	page.setPageSize(10);
	Map<String, Object> map = new HashMap<String, Object>();	
	map.put("pageSize", page.getPageSize());
	map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
	List<SellerBankPojo> sellerBankList = null;
	try {
      sellerBankList = sellerBankService.listPage(map);
      JSONArray json = JSONArray.fromObject(sellerBankList);
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
  public String goAddSellerBank() throws Exception {
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
	if(user != null && sellerBankPojo != null){
	  sellerBankPojo.setCreateBy(user.getId());
	  sellerBankPojo.setCreateDate(new Date());
	  sellerBankPojo.setUpdateBy(user.getId());
	  sellerBankPojo.setUpdateDate(new Date());
	  try {
		sellerBankService.add(sellerBankPojo);
		FileUtil.alertMessageBySkip("新增成功！", "goSellerBank.do");
	  } catch (Exception e) {
		e.printStackTrace();
		FileUtil.alertMessageBySkip("新增失败！", "goAddSellerBank.do");
	  }
	} else {
	  FileUtil.alertMessageBySkip("操作失败！", "goSellerBank.do");
	}
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSellerBank() throws Exception {
    if (id != null && id > 0) {
      sellerBankPojo = sellerBankService.getById(id);
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
	if(user != null && sellerBankPojo != null){
	  sellerBankPojo.setUpdateBy(user.getId());
	  sellerBankPojo.setUpdateDate(new Date());
	  try {
		sellerBankService.update(sellerBankPojo);
		FileUtil.alertMessageBySkip("提交成功！", "goSellerBank.do");
	  } catch (Exception e) {
		e.printStackTrace();
		FileUtil.alertMessageBySkip("提交失败！", "goEditSellerBank.do?id=" + sellerBankPojo.getId());
	  }
	} else {
	  FileUtil.alertMessageBySkip("操作失败！", "goSellerBank.do");
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
	  sellerBankPojo = new SellerBankPojo();
	  sellerBankPojo.setId(id);
	  sellerBankPojo.setStatus(1);
	  sellerBankPojo.setUpdateBy(user.getId());
	  sellerBankPojo.setUpdateDate(new Date());
	  try {
		sellerBankService.update(sellerBankPojo);
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
	  sellerBankPojo = new SellerBankPojo();
	  sellerBankPojo.setId(id);
	  sellerBankPojo.setStatus(0);
	  sellerBankPojo.setUpdateBy(user.getId());
	  sellerBankPojo.setUpdateDate(new Date());
	  try {
	    sellerBankService.update(sellerBankPojo);
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
	    sellerBankService.delete(id);
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
	  sellerBankPojo = new SellerBankPojo();
	  sellerBankPojo.setStatus(1);
	  for (String tid : tids) {
	  	sellerBankPojo.setId(Long.valueOf(tid));
	  	sellerBankPojo.setUpdateBy(user.getId());
	  	sellerBankPojo.setUpdateDate(new Date());
	  	try {
	  	  sellerBankService.update(sellerBankPojo);
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
	  sellerBankPojo = new SellerBankPojo();
	  sellerBankPojo.setStatus(0);
	  for (String tid : tids) {
	  	sellerBankPojo.setId(Long.valueOf(tid));
	  	sellerBankPojo.setUpdateBy(user.getId());
	  	sellerBankPojo.setUpdateDate(new Date());
	  	try {
	  	  sellerBankService.update(sellerBankPojo);
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
	  	  sellerBankService.delete(Long.valueOf(tid));
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
