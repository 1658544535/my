package com.tzmb2c.web.dao;


import java.sql.SQLException;

import com.tzmb2c.web.pojo.UserMessageStatePojo;


public interface UserMessageStateDao {

  public void addUserMsg(UserMessageStatePojo umsp) throws SQLException;

  public String getUserMsgReadState(UserMessageStatePojo umsp);

  public void updateUserMsgState(UserMessageStatePojo umsp);

}
