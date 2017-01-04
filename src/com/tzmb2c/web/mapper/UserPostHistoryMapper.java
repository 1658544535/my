/*
 * 文 件 名:  UserPostHistoryMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-03
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPostHistoryPojo;

public interface UserPostHistoryMapper {
    
    UserPostHistoryPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<UserPostHistoryPojo> listPage(Map<String, Object> params);
    
    int insert(UserPostHistoryPojo userPostHistory);
    
    int update(UserPostHistoryPojo userPostHistory);
    
    int deleteById(Long id);
    
    public Integer addLookNum(Map<String, Object> params);
}