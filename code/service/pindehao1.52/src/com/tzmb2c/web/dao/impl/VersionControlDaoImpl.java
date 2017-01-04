/*
 * 文 件 名:  VersionControlDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-01
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VersionControlDao;
import com.tzmb2c.web.pojo.VersionControlPojo;
import com.tzmb2c.web.mapper.VersionControlMapper;

/**
 * VersionControl Dao层
 */
public class VersionControlDaoImpl implements VersionControlDao {
	
    @Autowired
    private VersionControlMapper versionControlMapper;
    
	public int add(VersionControlPojo versionControl) throws SQLException{
		if(null == versionControl){
			return 0;
		}
        int rows = versionControlMapper.insert(versionControl);
        return rows;
	}

    public int update(VersionControlPojo versionControl) throws SQLException{
		if(null == versionControl){
			return 0;
		}
        int rows = versionControlMapper.update(versionControl);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = versionControlMapper.deleteById(id);
        return rows;
    }
    
    public VersionControlPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		VersionControlPojo versionControl = versionControlMapper.getById(id);
        return versionControl;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = versionControlMapper.countBy(params);
		return rows;
	}
	
	public List<VersionControlPojo> listPage(Map<String, Object> params) throws SQLException{
		List<VersionControlPojo> lists = versionControlMapper.listPage(params);		
		return lists;
	}
}
