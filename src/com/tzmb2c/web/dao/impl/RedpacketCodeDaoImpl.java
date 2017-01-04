package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.RedpacketCodeDao;
import com.tzmb2c.web.mapper.RedpacketCodeMapper;
import com.tzmb2c.web.pojo.RedPacketCodePojo;

public class RedpacketCodeDaoImpl implements RedpacketCodeDao {

  @Autowired
  private RedpacketCodeMapper redpacketCodeMapper;

  @Override
  public List<RedPacketCodePojo> redpacketCodeList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return redpacketCodeMapper.redpacketCodeList(map);
  }

  @Override
  public void AddRedpacketCode(RedPacketCodePojo redPacketCodePojo) throws SQLException {
    // TODO Auto-generated method stub
    redpacketCodeMapper.AddRedpacketCode(redPacketCodePojo);
  }
}
