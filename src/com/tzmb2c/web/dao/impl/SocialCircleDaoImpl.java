/*
 * 文 件 名:  SocialCircleServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tzmb2c.web.dao.SocialCircleDao;
import com.tzmb2c.web.mapper.SocialCircleMapper;
import com.tzmb2c.web.pojo.SocialCirclePojo;

/**
 * <一句话功能简述>
 */
@Service
public class SocialCircleDaoImpl implements SocialCircleDao {

	@Autowired
	private SocialCircleMapper socialCircleMapper;


	@Override
	public List<SocialCirclePojo> findSocialCircleList(Map<String, Object> map)
			throws SQLException {
		return socialCircleMapper.findSocialCircleList(map);
	}

	@Override
	public int findSocialCircleCount(Map<String, Object> map)
			throws SQLException {
		return socialCircleMapper.findSocialCircleCount(map);
	}

	@Override
	public void insertSocialCircle(SocialCirclePojo socialCirclePojo)
			throws SQLException {
		socialCircleMapper.insertSocialCircle(socialCirclePojo);
	}

	@Override
	public void delSocialCircle(Long id) throws SQLException {
		socialCircleMapper.delSocialCircle(id);
	}

	@Override
	public SocialCirclePojo findSocialCircleById(Long id) throws SQLException {
		return socialCircleMapper.findSocialCircleById(id);
	}

	@Override
	public void updateSocialCircle(SocialCirclePojo socialCirclePojo)
			throws SQLException {
		socialCircleMapper.updateSocialCircle(socialCirclePojo);
	}

	@Override
	public void checkSocialCircle(Long id) throws SQLException {
		socialCircleMapper.checkSocialCircle(id);
	}

	@Override
	public void uncheckSocialCircle(Long id) throws SQLException {
		socialCircleMapper.uncheckSocialCircle(id);
	}

  @Override
  public void addFollowNumById(SocialCirclePojo socialCirclePojo) throws SQLException {
    socialCircleMapper.addFollowNumById(socialCirclePojo);
  }

  @Override
  public void delFollowNumById(SocialCirclePojo socialCirclePojo) throws SQLException {
    socialCircleMapper.delFollowNumById(socialCirclePojo);
  }

  @Override
  public List<SocialCirclePojo> findSocialCircleByTarentoId(Map<String, Object> map)
      throws SQLException {
    return socialCircleMapper.findSocialCircleByTarentoId(map);
    
  }

}
