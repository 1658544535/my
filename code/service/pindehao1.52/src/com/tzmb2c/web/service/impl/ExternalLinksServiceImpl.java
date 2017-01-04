/*
 * 文 件 名:  ExternalLinksServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ExternalLinksService;
import com.tzmb2c.web.pojo.ExternalLinksPojo;
import com.tzmb2c.web.dao.ExternalLinksDao;

/**
 * ExternalLinks Service层
 */
public class ExternalLinksServiceImpl implements ExternalLinksService {
	
    @Autowired
    private ExternalLinksDao externalLinksdao;
    
	public int add(ExternalLinksPojo externalLinks) {
		if(null == externalLinks){
			return 0;
		}
        int rows = externalLinksdao.add(externalLinks);
        return rows;
	}

    public int update(ExternalLinksPojo externalLinks) {
		if(null == externalLinks){
			return 0;
		}
        int rows = externalLinksdao.update(externalLinks);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = externalLinksdao.delete(id);
        return rows;
    }
    
    public ExternalLinksPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ExternalLinksPojo externalLinks = externalLinksdao.getById(id);
        return externalLinks;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = externalLinksdao.countBy(params);
		return rows;
	}
	
	public List<ExternalLinksPojo> listPage(Map<String, Object> params){
		List<ExternalLinksPojo> lists = externalLinksdao.listPage(params);
		return lists;
	}
}
