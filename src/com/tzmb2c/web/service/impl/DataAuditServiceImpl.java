/*
 * 文 件 名:  DataAuditServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-29
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.DataAuditService;
import com.tzmb2c.web.pojo.DataAuditPojo;
import com.tzmb2c.web.dao.DataAuditDao;

/**
 * DataAudit Service层
 */
public class DataAuditServiceImpl implements DataAuditService {
	
    @Autowired
    private DataAuditDao dataAuditdao;
    
	public int add(DataAuditPojo dataAudit) {
		if(null == dataAudit){
			return 0;
		}
        int rows = dataAuditdao.add(dataAudit);
        return rows;
	}

    public int update(DataAuditPojo dataAudit) {
		if(null == dataAudit){
			return 0;
		}
        int rows = dataAuditdao.update(dataAudit);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = dataAuditdao.delete(id);
        return rows;
    }
    
    public DataAuditPojo getById(Long id) {
		if(null == id){
			return null;
		}
		DataAuditPojo dataAudit = dataAuditdao.getById(id);
        return dataAudit;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = dataAuditdao.countBy(params);
		return rows;
	}
	
	public List<DataAuditPojo> listPage(Map<String, Object> params){
		List<DataAuditPojo> lists = dataAuditdao.listPage(params);
		return lists;
	}

    @Override
    public Integer dataAuditCount(Map<String, Object> params) {
      Integer rows = dataAuditdao.dataAuditCount(params);
      return rows;
    }
  
    @Override
    public List<DataAuditPojo> dataAuditList(Map<String, Object> params) {
      List<DataAuditPojo> lists = dataAuditdao.dataAuditList(params);
      return lists;
    }

    @Override
    public Integer dataAuditTotalCount(Map<String, Object> params) {
      Integer rows = dataAuditdao.dataAuditTotalCount(params);
      return rows;
    }

    @Override
    public List<DataAuditPojo> dataAuditTotalList(Map<String, Object> params) {
      List<DataAuditPojo> lists = dataAuditdao.dataAuditTotalList(params);
      return lists;
    }

    @Override
    public Double dataAuditAverageCount(Map<String, Object> params) {
      Double rows = dataAuditdao.dataAuditAverageCount(params);
      return rows;
    }

    @Override
    public Integer dataAuditPeopleCount(Map<String, Object> params) {
      Integer rows = dataAuditdao.dataAuditPeopleCount(params);
      return rows;
    }

    @Override
    public List<DataAuditPojo> dataAuditPeopleList(Map<String, Object> params) {
      List<DataAuditPojo> lists = dataAuditdao.dataAuditPeopleList(params);
      return lists;
    }

    @Override
    public List<DataAuditPojo> findUserAsHangzhouList() {
      List<DataAuditPojo> lists = dataAuditdao.findUserAsHangzhouList();
      return lists;
    }

    @Override
    public Integer IsMoreThanFiveDay(Map<String, Object> params) {
      Integer rows = dataAuditdao.IsMoreThanFiveDay(params);
      return rows;
    }
}
