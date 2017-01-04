/*
 * 文 件 名:  SocialCircleTypeServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tzmb2c.web.dao.SocialCircleTypeDao;
import com.tzmb2c.web.mapper.SocialCircleTypeMapper;
import com.tzmb2c.web.pojo.SocialCircleTypePojo;

/**
 * <一句话功能简述>
 */
@Service
public class SocialCircleTypeDaoImpl implements SocialCircleTypeDao {
	@Autowired
	private SocialCircleTypeMapper socialCircleTypeMapper;

	@Override
	public List<SocialCircleTypePojo> findSocialCircleTypeList(
			Map<String, Object> map) throws SQLException {
		return socialCircleTypeMapper.findSocialCircleTypeList(map);
	}

	@Override
	public int findSocialCircleTypeCount(Map<String, Object> map)
			throws SQLException {
		return socialCircleTypeMapper.findSocialCircleTypeCount(map);
	}

	@Override
	public void insertSocialCircleType(SocialCircleTypePojo socialCircleTypePojo)
			throws SQLException {
		socialCircleTypeMapper.insertSocialCircleType(socialCircleTypePojo);
	}

	@Override
	public SocialCircleTypePojo findSocialCircleTypeById(Long id)
			throws SQLException {
		return socialCircleTypeMapper.findSocialCircleTypeById(id);
	}

	@Override
	public void updateSocialCircleType(SocialCircleTypePojo socialCircleTypePojo)
			throws SQLException {
		socialCircleTypeMapper.updateSocialCircleType(socialCircleTypePojo);
	}

	@Override
	public void delSocialCircleType(Long id) throws SQLException {
		socialCircleTypeMapper.delSocialCircleType(id);
	}
}
