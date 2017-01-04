/*
 * 文 件 名:  NoticeTemplateServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-01
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.NoticeTemplateService;
import com.tzmb2c.web.pojo.NoticeTemplatePojo;
import com.tzmb2c.web.dao.NoticeTemplateDao;

/**
 * NoticeTemplate Service层
 */
public class NoticeTemplateServiceImpl implements NoticeTemplateService {
	
    @Autowired
    private NoticeTemplateDao noticeTemplatedao;
    
	public int add(NoticeTemplatePojo noticeTemplate) throws SQLException{
		if(null == noticeTemplate){
			return 0;
		}
        int rows = noticeTemplatedao.add(noticeTemplate);
        return rows;
	}

    public int update(NoticeTemplatePojo noticeTemplate) throws SQLException{
		if(null == noticeTemplate){
			return 0;
		}
        int rows = noticeTemplatedao.update(noticeTemplate);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = noticeTemplatedao.delete(id);
        return rows;
    }
    
    public NoticeTemplatePojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		NoticeTemplatePojo noticeTemplate = noticeTemplatedao.getById(id);
        return noticeTemplate;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = noticeTemplatedao.countBy(params);
		return rows;
	}
	
	public List<NoticeTemplatePojo> listPage(Map<String, Object> params) throws SQLException{
		List<NoticeTemplatePojo> lists = noticeTemplatedao.listPage(params);
		return lists;
	}
}
