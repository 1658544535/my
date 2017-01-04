package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysLoginDao;
import com.tzmb2c.web.dao.UserMessageStateDao;
import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.pojo.UserMessageStatePojo;
import com.tzmb2c.web.service.UserMessageStateService;

public class UserMessageStateServiceImpl implements UserMessageStateService {

  @Autowired
  private UserMessageStateDao umsd;

  @Autowired
  private SysLoginDao sld;

  @Override
  public void addUserMsg(UserMessageStatePojo umsp) throws SQLException {
    umsd.addUserMsg(umsp);
  }

  @Override
  public void addMsgToAllUser(InfoPojo infoPojo) throws SQLException {
    List<Long> userIds = sld.getAllUserId();
    Long infoId = infoPojo.getId();
    for (int i = 0; i < userIds.size(); i++) {
      UserMessageStatePojo umsp = new UserMessageStatePojo();
      umsp.setUserId(userIds.get(i));
      umsp.setMessageId(infoId);
      umsp.setIsReader("0");
      umsd.addUserMsg(umsp);
    }
  }

  @Override
  public void updateInfoReadState(Long id, List<InfoPojo> infolist) {
    for (int i = 0; i < infolist.size(); i++) {
      InfoPojo info = infolist.get(i);
      UserMessageStatePojo umsp = new UserMessageStatePojo();
      umsp.setUserId(id);
      umsp.setMessageId(info.getId());
      String is_reader = umsd.getUserMsgReadState(umsp);
      if (null == is_reader || is_reader.equals("1")) {
        info.setIs_reader(1);
      } else {
        info.setIs_reader(0);
      }
    }
  }

  @Override
  public String getUserMsgReadState(UserMessageStatePojo umsp) {
    return umsd.getUserMsgReadState(umsp);
  }

  @Override
  public void updateUserMsgState(UserMessageStatePojo umsp) {
    umsd.updateUserMsgState(umsp);
  }


}
