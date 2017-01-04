/*
 * 文 件 名:  CmbUserAgreeDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-07-18
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.CmbUserAgreeDao;
import com.tzmb2c.web.pojo.CmbUserAgreePojo;
import com.tzmb2c.web.mapper.CmbUserAgreeMapper;

/**
 * CmbUserAgree Dao层
 */
public class CmbUserAgreeDaoImpl implements CmbUserAgreeDao {
	
    @Autowired
    private CmbUserAgreeMapper cmbUserAgreeMapper;
    
	public int add(CmbUserAgreePojo cmbUserAgree) {
		if(null == cmbUserAgree){
			return 0;
		}
        int rows = cmbUserAgreeMapper.insert(cmbUserAgree);
        return rows;
	}

    public int update(CmbUserAgreePojo cmbUserAgree) {
		if(null == cmbUserAgree){
			return 0;
		}
        int rows = cmbUserAgreeMapper.update(cmbUserAgree);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = cmbUserAgreeMapper.deleteById(id);
        return rows;
    }
    
    public CmbUserAgreePojo getById(Long id) {
		if(null == id){
			return null;
		}
		CmbUserAgreePojo cmbUserAgree = cmbUserAgreeMapper.getById(id);
        return cmbUserAgree;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = cmbUserAgreeMapper.countBy(params);
		return rows;
	}
	
	public List<CmbUserAgreePojo> listPage(Map<String, Object> params){
		List<CmbUserAgreePojo> lists = cmbUserAgreeMapper.listPage(params);		
		return lists;
	}

  @Override
  public CmbUserAgreePojo getByUserId(Long userId) {
    return cmbUserAgreeMapper.getByUserId(userId);
  }
}
