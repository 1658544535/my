/*
 * 文 件 名:  SocialCircleMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SocialCirclePojo;

public interface SocialCircleMapper {
	List<SocialCirclePojo> findSocialCircleList(Map<String, Object> map)
			throws SQLException;

	int findSocialCircleCount(Map<String, Object> map) throws SQLException;

	void insertSocialCircle(SocialCirclePojo socialCirclePojo)
			throws SQLException;

	void delSocialCircle(Long id) throws SQLException;

	SocialCirclePojo findSocialCircleById(Long id) throws SQLException;

	void updateSocialCircle(SocialCirclePojo socialCirclePojo)
			throws SQLException;

	void checkSocialCircle(Long id) throws SQLException;

	void uncheckSocialCircle(Long id) throws SQLException;
	
	/**
     * 添加关注
     * @param id
     * @throws SQLException
     */
    void addFollowNumById(SocialCirclePojo socialCirclePojo) throws SQLException;
    /**
     * 取消关注
     * @param id
     * @throws SQLException
     */
    void delFollowNumById(SocialCirclePojo socialCirclePojo) throws SQLException;
    
    /**
     * 通过达人id查找达人关注的圈子
     * @param map
     * @throws SQLException
     */
    List<SocialCirclePojo> findSocialCircleByTarentoId(Map<String, Object> map)
        throws SQLException;

}