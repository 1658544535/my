/*
 * 文 件 名:  UserBabyMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-30
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserBabyPojo;

public interface UserBabyMapper {
    
    UserBabyPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<UserBabyPojo> listPage(Map<String, Object> params);
    
    int insert(UserBabyPojo userBaby);
    
    int update(UserBabyPojo userBaby);
    
    int deleteById(Long id);
    
    public UserBabyPojo getByParams(Map<String, Object> params);
}