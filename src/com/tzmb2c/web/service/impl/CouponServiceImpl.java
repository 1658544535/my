package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.CouponDao;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.HongbaoLogPojo;
import com.tzmb2c.web.pojo.LotteryForwardPojo;
import com.tzmb2c.web.pojo.LotteryLogPojo;
import com.tzmb2c.web.pojo.LotteryPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.service.CouponService;

public class CouponServiceImpl implements CouponService {

  @Autowired
  private CouponDao couponDao;

  @Override
  public List<CouponPojo> getcouponList(Map<String, Object> map) throws Exception {

    return couponDao.getcouponList(map);
  }

  @Override
  public void deletecouponById(Long id) throws Exception {

    couponDao.deletecouponById(id);
  }

  @Override
  public void checkcouponById(Long id) throws Exception {

    couponDao.checkcouponById(id);
  }

  @Override
  public void uncheckcouponById(Long id) throws Exception {

    couponDao.uncheckcouponById(id);
  }

  @Override
  public void addcoupon(CouponPojo couponPojo) throws Exception {

    couponDao.addcoupon(couponPojo);
  }

  @Override
  public void updatecouponById(CouponPojo couponPojo) throws Exception {

    couponDao.updatecouponById(couponPojo);
  }

  @Override
  public List<UserCouponPojo> getuserCouponList(Map<String, Object> map) throws Exception {

    return couponDao.getuserCouponList(map);
  }

  @Override
  public List<LotteryLogPojo> getLotteryLogList(Map<String, Object> map) throws Exception {

    return couponDao.getLotteryLogList(map);
  }

  @Override
  public List<LotteryForwardPojo> getLotteryForwardList(Map<String, Object> map) throws Exception {

    return couponDao.getLotteryForwardList(map);
  }

  @Override
  public List<CouponOrderPojo> getcouponOrderList(Map<String, Object> map) throws Exception {

    return couponDao.getcouponOrderList(map);
  }

  @Override
  public List<LotteryPojo> getLotteryList(Map<String, Object> map) throws Exception {

    return couponDao.getLotteryList(map);
  }

  @Override
  public List<HongbaoLogPojo> getHongbaoLogList(Map<String, Object> map) throws Exception {

    return couponDao.getHongbaoLogList(map);
  }

  @Override
  public int addUserCoupon(UserCouponPojo userCouponPojo) throws Exception {
    return couponDao.addUserCoupon(userCouponPojo);
  }

  @Override
  public void addCouponOrder(CouponOrderPojo couponOrderPojo) throws Exception {
    couponDao.addCouponOrder(couponOrderPojo);
  }

  @Override
  public UserCouponPojo getUserCouponByNo(Map<String, Object> map) throws Exception {
    return couponDao.getUserCouponByNo(map);
  }

  @Override
  public int updateUserCoupon(UserCouponPojo userCouponPojo) throws Exception {
    return couponDao.updateUserCoupon(userCouponPojo);
  }

  @Override
  public CouponPojo getcouponById(Long id) throws Exception {
    return couponDao.getcouponById(id);
  }

  @Override
  public void checkUserCoupon(UserCouponPojo userCouponPojo) throws SQLException {
    couponDao.checkUserCoupon(userCouponPojo);
  }

  @Override
  public void delUserCouponByNo(String couponNo) throws SQLException {
    couponDao.delUserCouponByNo(couponNo);
  }

  @Override
  public void updateCouponOrderStatus(CouponOrderPojo couponOrderPojo) throws SQLException {
    couponDao.updateCouponOrderStatus(couponOrderPojo);
  }

  @Override
  public int getuserCouponCount(Map<String, Object> map) throws SQLException {
    return couponDao.getuserCouponCount(map);
  }

  @Override
  public int getCouponCount(Map<String, Object> map) throws Exception {
    return couponDao.getCouponCount(map);
  }

  @Override
  public void addUserCouponBatch(List<UserCouponPojo> coupons) throws SQLException {
    couponDao.addUserCouponBatch(coupons);
  }


}
