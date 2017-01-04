package com.tzmb2c.web.mapper;


import java.sql.SQLException;

import com.tzmb2c.web.pojo.UserMessageStatePojo;


public interface UserMessageStateMapper {

  public void addUserMsg(UserMessageStatePojo userMessageStatePojo) throws SQLException;

  public String getUserMsgReadState(UserMessageStatePojo umsp);

  public void updateUserMsgState(UserMessageStatePojo umsp);

}
