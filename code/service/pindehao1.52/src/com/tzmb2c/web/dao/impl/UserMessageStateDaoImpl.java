package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserMessageStateDao;
import com.tzmb2c.web.mapper.UserMessageStateMapper;
import com.tzmb2c.web.pojo.UserMessageStatePojo;

public class UserMessageStateDaoImpl implements UserMessageStateDao {

  @Autowired
  private UserMessageStateMapper umsm;

  @Override
  public void addUserMsg(UserMessageStatePojo umsp) throws SQLException {
    umsm.addUserMsg(umsp);
  }

  @Override
  public String getUserMsgReadState(UserMessageStatePojo umsp) {
    return umsm.getUserMsgReadState(umsp);
  }

  @Override
  public void updateUserMsgState(UserMessageStatePojo umsp) {
    umsm.updateUserMsgState(umsp);
  }

}
