/*
 * 文 件 名:  CmbUserAgreeServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-07-18
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.CmbUserAgreeService;
import com.tzmb2c.web.pojo.CmbUserAgreePojo;
import com.tzmb2c.web.dao.CmbUserAgreeDao;

/**
 * CmbUserAgree Service层
 */
public class CmbUserAgreeServiceImpl implements CmbUserAgreeService {
	
    @Autowired
    private CmbUserAgreeDao cmbUserAgreedao;
    
	public int add(CmbUserAgreePojo cmbUserAgree) {
		if(null == cmbUserAgree){
			return 0;
		}
        int rows = cmbUserAgreedao.add(cmbUserAgree);
        return rows;
	}

    public int update(CmbUserAgreePojo cmbUserAgree) {
		if(null == cmbUserAgree){
			return 0;
		}
        int rows = cmbUserAgreedao.update(cmbUserAgree);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = cmbUserAgreedao.delete(id);
        return rows;
    }
    
    public CmbUserAgreePojo getById(Long id) {
		if(null == id){
			return null;
		}
		CmbUserAgreePojo cmbUserAgree = cmbUserAgreedao.getById(id);
        return cmbUserAgree;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = cmbUserAgreedao.countBy(params);
		return rows;
	}
	
	public List<CmbUserAgreePojo> listPage(Map<String, Object> params){
		List<CmbUserAgreePojo> lists = cmbUserAgreedao.listPage(params);
		return lists;
	}

  @Override
  public CmbUserAgreePojo getByUserId(Long userId) {
    return cmbUserAgreedao.getByUserId(userId);
  }
}
