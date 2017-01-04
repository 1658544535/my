/*
 * 文 件 名:  SocialCircleServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tzmb2c.web.dao.SocialCircleDao;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.service.SocialCircleService;

/**
 * <一句话功能简述>
 */
@Service
public class SocialCircleServiceImpl implements SocialCircleService {

	@Autowired
	private SocialCircleDao socialCircleDao;

	@Override
	public List<SocialCirclePojo> findSocialCircleList(Map<String, Object> map)
			throws SQLException {
		return socialCircleDao.findSocialCircleList(map);
	}

	@Override
	public int findSocialCircleCount(Map<String, Object> map)
			throws SQLException {
		return socialCircleDao.findSocialCircleCount(map);
	}

	@Override
	public void insertSocialCircle(SocialCirclePojo socialCirclePojo)
			throws SQLException {
		socialCircleDao.insertSocialCircle(socialCirclePojo);
	}

	@Override
	public void delSocialCircle(Long id) throws SQLException {
		socialCircleDao.delSocialCircle(id);
	}

	@Override
	public SocialCirclePojo findSocialCircleById(Long id) throws SQLException {
		return socialCircleDao.findSocialCircleById(id);
	}

	@Override
	public void updateSocialCircle(SocialCirclePojo socialCirclePojo)
			throws SQLException {
		socialCircleDao.updateSocialCircle(socialCirclePojo);
	}

	@Override
	public void checkSocialCircle(Long id) throws SQLException {
		socialCircleDao.checkSocialCircle(id);
	}

	@Override
	public void uncheckSocialCircle(Long id) throws SQLException {
		socialCircleDao.uncheckSocialCircle(id);
	}

  @Override
  public void addFollowNumById(SocialCirclePojo socialCirclePojo) throws SQLException {
    socialCircleDao.addFollowNumById(socialCirclePojo);
  }

  @Override
  public void delFollowNumById(SocialCirclePojo socialCirclePojo) throws SQLException {
    socialCircleDao.delFollowNumById(socialCirclePojo);
  }

  @Override
  public List<SocialCirclePojo> findSocialCircleByTarentoId(Map<String, Object> map)
      throws SQLException { 
    return socialCircleDao.findSocialCircleByTarentoId(map);
  }
}
