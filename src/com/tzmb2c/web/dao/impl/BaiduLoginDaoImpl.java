package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.BaiduLoginDao;
import com.tzmb2c.web.mapper.BaiduLoginMapper;
import com.tzmb2c.web.pojo.BaiduLoginPojo;

public class BaiduLoginDaoImpl implements BaiduLoginDao {
  @Autowired
  private BaiduLoginMapper baiduLoginMapper;

  @Override
  public List<BaiduLoginPojo> findBaiduLoginList(Map<String, Object> map) throws SQLException {
    return baiduLoginMapper.findBaiduLoginList(map);
  }

  @Override
  public int findBaiduLoginCount(Map<String, Object> map) throws SQLException {
    return baiduLoginMapper.findBaiduLoginCount(map);
  }

  @Override
  public void insertBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    baiduLoginMapper.insertBaiduLogin(baiduLoginPojo);
  }

  @Override
  public void delBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    baiduLoginMapper.delBaiduLogin(baiduLoginPojo);
  }

  @Override
  public BaiduLoginPojo findBaiduLoginById(Long id) throws SQLException {
    return baiduLoginMapper.findBaiduLoginById(id);
  }

  @Override
  public void updateBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    baiduLoginMapper.updateBaiduLogin(baiduLoginPojo);
  }

  @Override
  public int checkMachineCodeRepeat(Long uid) throws SQLException {
    return baiduLoginMapper.checkMachineCodeRepeat(uid);
  }

  @Override
  public int checkMachineCodeRepeatByCode(String code) throws SQLException {
    return baiduLoginMapper.checkMachineCodeRepeatByCode(code);
  }
}
