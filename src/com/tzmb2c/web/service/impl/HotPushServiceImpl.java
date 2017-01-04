/*
 * 文 件 名:  HotPushServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-27
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.HotPushService;
import com.tzmb2c.web.pojo.HotPushPojo;
import com.tzmb2c.web.dao.HotPushDao;

/**
 * HotPush Service层
 */
public class HotPushServiceImpl implements HotPushService {
	
    @Autowired
    private HotPushDao hotPushdao;
    
	public int add(HotPushPojo hotPush) {
		if(null == hotPush){
			return 0;
		}
        int rows = hotPushdao.add(hotPush);
        return rows;
	}

    public int update(HotPushPojo hotPush) {
		if(null == hotPush){
			return 0;
		}
        int rows = hotPushdao.update(hotPush);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = hotPushdao.delete(id);
        return rows;
    }
    
    public HotPushPojo getById(Long id) {
		if(null == id){
			return null;
		}
		HotPushPojo hotPush = hotPushdao.getById(id);
        return hotPush;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = hotPushdao.countBy(params);
		return rows;
	}
	
	public List<HotPushPojo> listPage(Map<String, Object> params){
		List<HotPushPojo> lists = hotPushdao.listPage(params);
		return lists;
	}

   public HotPushPojo findByParams(Map<String, Object> params) {
    return hotPushdao.findByParams(params);
   }

  @Override
  public List<HotPushPojo> topicListPage(Map<String, Object> params) {
    return hotPushdao.topicListPage(params);
  }
}
