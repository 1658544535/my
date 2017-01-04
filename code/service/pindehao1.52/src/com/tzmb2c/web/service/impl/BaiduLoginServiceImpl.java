package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.BaiduLoginDao;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.service.BaiduLoginService;

public class BaiduLoginServiceImpl implements BaiduLoginService {
  @Autowired
  private BaiduLoginDao baiduLoginDao;

  @Override
  public List<BaiduLoginPojo> findBaiduLoginList(Map<String, Object> map) throws SQLException {
    return baiduLoginDao.findBaiduLoginList(map);
  }

  @Override
  public int findBaiduLoginCount(Map<String, Object> map) throws SQLException {
    return baiduLoginDao.findBaiduLoginCount(map);
  }

  @Override
  public void insertBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    baiduLoginDao.insertBaiduLogin(baiduLoginPojo);
  }

  @Override
  public void delBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    baiduLoginDao.delBaiduLogin(baiduLoginPojo);
  }

  @Override
  public BaiduLoginPojo findBaiduLoginById(Long id) throws SQLException {
    return baiduLoginDao.findBaiduLoginById(id);
  }

  @Override
  public void updateBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    baiduLoginDao.updateBaiduLogin(baiduLoginPojo);
  }

  @Override
  public int checkMachineCodeRepeat(Long uid) throws SQLException {
    return baiduLoginDao.checkMachineCodeRepeat(uid);
  }

  @Override
  public int checkMachineCodeRepeatByCode(String code) throws SQLException {
    return baiduLoginDao.checkMachineCodeRepeatByCode(code);
  }
}
