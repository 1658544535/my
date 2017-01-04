/*
 * 文 件 名:  SocialCircleTypeServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tzmb2c.web.dao.SocialCircleTypeDao;
import com.tzmb2c.web.pojo.SocialCircleTypePojo;
import com.tzmb2c.web.service.SocialCircleTypeService;

/**
 * <一句话功能简述>
 */
@Service
public class SocialCircleTypeServiceImpl implements SocialCircleTypeService {

	@Autowired
	private SocialCircleTypeDao socialCircleTypeDao;

	@Override
	public List<SocialCircleTypePojo> findSocialCircleTypeList(
			Map<String, Object> map) throws SQLException {
		return socialCircleTypeDao.findSocialCircleTypeList(map);
	}

	@Override
	public int findSocialCircleTypeCount(Map<String, Object> map)
			throws SQLException {
		return socialCircleTypeDao.findSocialCircleTypeCount(map);
	}

	@Override
	public void insertSocialCircleType(SocialCircleTypePojo socialCircleTypePojo)
			throws SQLException {
		socialCircleTypeDao.insertSocialCircleType(socialCircleTypePojo);
	}

	@Override
	public SocialCircleTypePojo findSocialCircleTypeById(Long id)
			throws SQLException {
		return socialCircleTypeDao.findSocialCircleTypeById(id);
	}

	@Override
	public void updateSocialCircleType(SocialCircleTypePojo socialCircleTypePojo)
			throws SQLException {
		socialCircleTypeDao.updateSocialCircleType(socialCircleTypePojo);
	}

	@Override
	public void delSocialCircleType(Long id) throws SQLException {
		socialCircleTypeDao.delSocialCircleType(id);
	}

}
