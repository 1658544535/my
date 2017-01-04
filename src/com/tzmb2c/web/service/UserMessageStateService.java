package com.tzmb2c.web.service;


import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.pojo.UserMessageStatePojo;

public interface UserMessageStateService {

  public void addUserMsg(UserMessageStatePojo umsp) throws SQLException;

  public void addMsgToAllUser(InfoPojo infoPojo) throws SQLException;

  public void updateInfoReadState(Long id, List<InfoPojo> infolist);

  public String getUserMsgReadState(UserMessageStatePojo umsp);

  public void updateUserMsgState(UserMessageStatePojo umsp);

}
