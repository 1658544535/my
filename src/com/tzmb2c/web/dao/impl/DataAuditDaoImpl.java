/*
 * 文 件 名:  DataAuditDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-29
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.DataAuditDao;
import com.tzmb2c.web.pojo.DataAuditPojo;
import com.tzmb2c.web.mapper.DataAuditMapper;

/**
 * DataAudit Dao层
 */
public class DataAuditDaoImpl implements DataAuditDao {
	
    @Autowired
    private DataAuditMapper dataAuditMapper;
    
	public int add(DataAuditPojo dataAudit) {
		if(null == dataAudit){
			return 0;
		}
        int rows = dataAuditMapper.insert(dataAudit);
        return rows;
	}

    public int update(DataAuditPojo dataAudit) {
		if(null == dataAudit){
			return 0;
		}
        int rows = dataAuditMapper.update(dataAudit);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = dataAuditMapper.deleteById(id);
        return rows;
    }
    
    public DataAuditPojo getById(Long id) {
		if(null == id){
			return null;
		}
		DataAuditPojo dataAudit = dataAuditMapper.getById(id);
        return dataAudit;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = dataAuditMapper.countBy(params);
		return rows;
	}
	
	public List<DataAuditPojo> listPage(Map<String, Object> params){
		List<DataAuditPojo> lists = dataAuditMapper.listPage(params);		
		return lists;
	}

    @Override
    public Integer dataAuditCount(Map<String, Object> params) {
      Integer rows = dataAuditMapper.dataAuditCount(params);
      return rows;
    }
  
    @Override
    public List<DataAuditPojo> dataAuditList(Map<String, Object> params) {
      List<DataAuditPojo> lists = dataAuditMapper.dataAuditList(params);       
      return lists;
    }

    @Override
    public Integer dataAuditTotalCount(Map<String, Object> params) {
      Integer rows = dataAuditMapper.dataAuditTotalCount(params);
      return rows;
    }

    @Override
    public List<DataAuditPojo> dataAuditTotalList(Map<String, Object> params) {
      List<DataAuditPojo> lists = dataAuditMapper.dataAuditTotalList(params);       
      return lists;
    }

    @Override
    public Double dataAuditAverageCount(Map<String, Object> params) {
      Double rows = dataAuditMapper.dataAuditAverageCount(params);
      return rows;
    }

    @Override
    public Integer dataAuditPeopleCount(Map<String, Object> params) {
      Integer rows = dataAuditMapper.dataAuditPeopleCount(params);
      return rows;
    }

    @Override
    public List<DataAuditPojo> dataAuditPeopleList(Map<String, Object> params) {
      List<DataAuditPojo> lists = dataAuditMapper.dataAuditPeopleList(params);       
      return lists;
    }

    @Override
    public List<DataAuditPojo> findUserAsHangzhouList() {
      List<DataAuditPojo> lists = dataAuditMapper.findUserAsHangzhouList();       
      return lists;
    }

    @Override
    public Integer IsMoreThanFiveDay(Map<String, Object> params) {
      Integer rows = dataAuditMapper.IsMoreThanFiveDay(params);
      return rows;
    }
}
