/*
 * 文 件 名:  ExternalLinksDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ExternalLinksDao;
import com.tzmb2c.web.pojo.ExternalLinksPojo;
import com.tzmb2c.web.mapper.ExternalLinksMapper;

/**
 * ExternalLinks Dao层
 */
public class ExternalLinksDaoImpl implements ExternalLinksDao {
	
    @Autowired
    private ExternalLinksMapper externalLinksMapper;
    
	public int add(ExternalLinksPojo externalLinks) {
		if(null == externalLinks){
			return 0;
		}
        int rows = externalLinksMapper.insert(externalLinks);
        return rows;
	}

    public int update(ExternalLinksPojo externalLinks) {
		if(null == externalLinks){
			return 0;
		}
        int rows = externalLinksMapper.update(externalLinks);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = externalLinksMapper.deleteById(id);
        return rows;
    }
    
    public ExternalLinksPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ExternalLinksPojo externalLinks = externalLinksMapper.getById(id);
        return externalLinks;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = externalLinksMapper.countBy(params);
		return rows;
	}
	
	public List<ExternalLinksPojo> listPage(Map<String, Object> params){
		List<ExternalLinksPojo> lists = externalLinksMapper.listPage(params);		
		return lists;
	}
}
