/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SocialCircleTypePojo;

public interface SocialCircleTypeService {

	List<SocialCircleTypePojo> findSocialCircleTypeList(Map<String, Object> map)
			throws SQLException;

	int findSocialCircleTypeCount(Map<String, Object> map) throws SQLException;

	void insertSocialCircleType(SocialCircleTypePojo socialCircleTypePojo)
			throws SQLException;

	SocialCircleTypePojo findSocialCircleTypeById(Long id) throws SQLException;

	void updateSocialCircleType(SocialCircleTypePojo socialCircleTypePojo)
			throws SQLException;

	void delSocialCircleType(Long id) throws SQLException;
}
