package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.RedPacketCodePojo;

public interface RedpacketCodeService {

  public List<RedPacketCodePojo> redpacketCodeList(Map<String, Object> map) throws SQLException;

  public void AddRedpacketCode(RedPacketCodePojo redPacketCodePojo) throws SQLException;


}
