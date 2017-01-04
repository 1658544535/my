package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.RedPacketCodePojo;

public interface RedpacketCodeDao {

  public List<RedPacketCodePojo> redpacketCodeList(Map<String, Object> map) throws SQLException;

  public void AddRedpacketCode(RedPacketCodePojo redPacketCodePojo) throws SQLException;


}
