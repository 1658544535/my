package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.RedpacketCodeDao;
import com.tzmb2c.web.pojo.RedPacketCodePojo;
import com.tzmb2c.web.service.RedpacketCodeService;

public class RedpacketCodeServiceImpl implements RedpacketCodeService {

  @Autowired
  private RedpacketCodeDao redpacketCodeDao;

  @Override
  public List<RedPacketCodePojo> redpacketCodeList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return redpacketCodeDao.redpacketCodeList(map);
  }

  @Override
  public void AddRedpacketCode(RedPacketCodePojo redPacketCodePojo) throws SQLException {
    // TODO Auto-generated method stub
    redpacketCodeDao.AddRedpacketCode(redPacketCodePojo);
  }

}
