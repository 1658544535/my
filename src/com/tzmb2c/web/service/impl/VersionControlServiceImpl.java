/*
 * 文 件 名:  VersionControlServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-01
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.VersionControlService;
import com.tzmb2c.web.pojo.VersionControlPojo;
import com.tzmb2c.web.dao.VersionControlDao;

/**
 * VersionControl Service层
 */
public class VersionControlServiceImpl implements VersionControlService {
	
    @Autowired
    private VersionControlDao versionControldao;
    
	public int add(VersionControlPojo versionControl) throws SQLException{
		if(null == versionControl){
			return 0;
		}
        int rows = versionControldao.add(versionControl);
        return rows;
	}

    public int update(VersionControlPojo versionControl) throws SQLException{
		if(null == versionControl){
			return 0;
		}
        int rows = versionControldao.update(versionControl);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = versionControldao.delete(id);
        return rows;
    }
    
    public VersionControlPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		VersionControlPojo versionControl = versionControldao.getById(id);
        return versionControl;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = versionControldao.countBy(params);
		return rows;
	}
	
	public List<VersionControlPojo> listPage(Map<String, Object> params) throws SQLException{
		List<VersionControlPojo> lists = versionControldao.listPage(params);
		return lists;
	}
}
