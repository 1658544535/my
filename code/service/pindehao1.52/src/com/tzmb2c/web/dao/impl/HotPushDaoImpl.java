/*
 * 文 件 名:  HotPushDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-27
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HotPushDao;
import com.tzmb2c.web.pojo.HotPushPojo;
import com.tzmb2c.web.mapper.HotPushMapper;

/**
 * HotPush Dao层
 */
public class HotPushDaoImpl implements HotPushDao {
	
    @Autowired
    private HotPushMapper hotPushMapper;
    
	public int add(HotPushPojo hotPush) {
		if(null == hotPush){
			return 0;
		}
        int rows = hotPushMapper.insert(hotPush);
        return rows;
	}

    public int update(HotPushPojo hotPush) {
		if(null == hotPush){
			return 0;
		}
        int rows = hotPushMapper.update(hotPush);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = hotPushMapper.deleteById(id);
        return rows;
    }
    
    public HotPushPojo getById(Long id) {
		if(null == id){
			return null;
		}
		HotPushPojo hotPush = hotPushMapper.getById(id);
        return hotPush;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = hotPushMapper.countBy(params);
		return rows;
	}
	
	public List<HotPushPojo> listPage(Map<String, Object> params){
		List<HotPushPojo> lists = hotPushMapper.listPage(params);		
		return lists;
	}

    public HotPushPojo findByParams(Map<String, Object> params) {
       return hotPushMapper.findByParams(params);
    }

    @Override
    public List<HotPushPojo> topicListPage(Map<String, Object> params) {
      return hotPushMapper.topicListPage(params);
    }
}
