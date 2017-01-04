package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.NavigationDao;
import com.tzmb2c.web.mapper.NavigationMapper;
import com.tzmb2c.web.pojo.NavigationPojo;

public class NavigationDaoImpl implements NavigationDao {
	@Autowired
	private NavigationMapper navigationMapper;

	@Override
	public List<NavigationPojo> findNavigationList(Map<String, Object> map) throws SQLException {

		return navigationMapper.findNavigationList(map);
	}

	@Override
	public int findNavigationCount(Map<String, Object> map) throws SQLException {

		return navigationMapper.findNavigationCount(map);
	}

	@Override
	public void insertNavigation(NavigationPojo navigationPojo) throws SQLException {

		navigationMapper.insertNavigation(navigationPojo);

	}

	@Override
	public NavigationPojo findNavigationById(Long id) throws SQLException {

		return navigationMapper.findNavigationById(id);
	}

	@Override
	public void updateNavigation(NavigationPojo navigationPojo) throws SQLException {

		navigationMapper.updateNavigation(navigationPojo);
	}

	@Override
	public void checkNavigation(Long id) throws SQLException {

		navigationMapper.checkNavigation(id);
	}

	@Override
	public void uncheckNavigation(Long id) throws SQLException {

		navigationMapper.uncheckNavigation(id);
	}

	@Override
	public void delNavigation(Long id) throws SQLException {

		navigationMapper.delNavigation(id);
	}
}
