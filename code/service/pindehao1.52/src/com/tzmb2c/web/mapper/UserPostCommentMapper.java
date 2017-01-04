/*
 * 文 件 名:  UserPostCommentMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-04
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPostCommentPojo;

public interface UserPostCommentMapper {
    
    UserPostCommentPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<UserPostCommentPojo> listPage(Map<String, Object> params);
    
    int insert(UserPostCommentPojo userPostComment);
    
    int update(UserPostCommentPojo userPostComment);
    
    int deleteById(Long id);
}