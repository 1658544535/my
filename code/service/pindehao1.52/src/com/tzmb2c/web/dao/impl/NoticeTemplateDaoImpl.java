/*
 * 文 件 名:  NoticeTemplateDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-01
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.NoticeTemplateDao;
import com.tzmb2c.web.pojo.NoticeTemplatePojo;
import com.tzmb2c.web.mapper.NoticeTemplateMapper;

/**
 * NoticeTemplate Dao层
 */
public class NoticeTemplateDaoImpl implements NoticeTemplateDao {
	
    @Autowired
    private NoticeTemplateMapper noticeTemplateMapper;
    
	public int add(NoticeTemplatePojo noticeTemplate) throws SQLException{
		if(null == noticeTemplate){
			return 0;
		}
        int rows = noticeTemplateMapper.insert(noticeTemplate);
        return rows;
	}

    public int update(NoticeTemplatePojo noticeTemplate) throws SQLException{
		if(null == noticeTemplate){
			return 0;
		}
        int rows = noticeTemplateMapper.update(noticeTemplate);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = noticeTemplateMapper.deleteById(id);
        return rows;
    }
    
    public NoticeTemplatePojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		NoticeTemplatePojo noticeTemplate = noticeTemplateMapper.getById(id);
        return noticeTemplate;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = noticeTemplateMapper.countBy(params);
		return rows;
	}
	
	public List<NoticeTemplatePojo> listPage(Map<String, Object> params) throws SQLException{
		List<NoticeTemplatePojo> lists = noticeTemplateMapper.listPage(params);		
		return lists;
	}
}
