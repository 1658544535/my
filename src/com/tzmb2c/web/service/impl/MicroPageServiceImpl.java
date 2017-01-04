/*
 * 文 件 名:  MicroPageServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.MicroPageService;
import com.tzmb2c.web.pojo.MicroPagePojo;
import com.tzmb2c.web.dao.MicroPageDao;

/**
 * MicroPage Service层
 */
public class MicroPageServiceImpl implements MicroPageService {
	
    @Autowired
    private MicroPageDao microPagedao;
    
	public int add(MicroPagePojo microPage) {
		if(null == microPage){
			return 0;
		}
        int rows = microPagedao.add(microPage);
        return rows;
	}

    public int update(MicroPagePojo microPage) {
		if(null == microPage){
			return 0;
		}
        int rows = microPagedao.update(microPage);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = microPagedao.delete(id);
        return rows;
    }
    
    public MicroPagePojo getById(Long id) {
		if(null == id){
			return null;
		}
		MicroPagePojo microPage = microPagedao.getById(id);
        return microPage;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = microPagedao.countBy(params);
		return rows;
	}
	
	public List<MicroPagePojo> listPage(Map<String, Object> params){
		List<MicroPagePojo> lists = microPagedao.listPage(params);
		return lists;
	}
}
