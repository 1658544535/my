/*
 * 文 件 名:  SocialCircleTypeMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SocialCircleTypePojo;

public interface SocialCircleTypeMapper {
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