package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.CouponDao;
import com.tzmb2c.web.mapper.CouponMapper;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.HongbaoLogPojo;
import com.tzmb2c.web.pojo.LotteryForwardPojo;
import com.tzmb2c.web.pojo.LotteryLogPojo;
import com.tzmb2c.web.pojo.LotteryPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;

public class CouponDaoImpl implements CouponDao {

  @Autowired
  private CouponMapper couponMapper;

  @Override
  public List<CouponPojo> getcouponList(Map<String, Object> map) throws Exception {

    return couponMapper.getcouponList(map);
  }

  @Override
  public void deletecouponById(Long id) throws Exception {

    couponMapper.deletecouponById(id);
  }

  @Override
  public void checkcouponById(Long id) throws Exception {

    couponMapper.checkcouponById(id);
  }

  @Override
  public void uncheckcouponById(Long id) throws Exception {

    couponMapper.uncheckcouponById(id);
  }

  @Override
  public void addcoupon(CouponPojo couponPojo) throws Exception {

    couponMapper.addcoupon(couponPojo);
  }

  @Override
  public void updatecouponById(CouponPojo couponPojo) throws Exception {

    couponMapper.updatecouponById(couponPojo);
  }

  @Override
  public List<UserCouponPojo> getuserCouponList(Map<String, Object> map) throws Exception {

    return couponMapper.getuserCouponList(map);
  }

  @Override
  public List<LotteryLogPojo> getLotteryLogList(Map<String, Object> map) throws Exception {

    return couponMapper.getLotteryLogList(map);
  }

  @Override
  public List<LotteryForwardPojo> getLotteryForwardList(Map<String, Object> map) throws Exception {

    return couponMapper.getLotteryForwardList(map);
  }

  @Override
  public List<CouponOrderPojo> getcouponOrderList(Map<String, Object> map) throws Exception {

    return couponMapper.getcouponOrderList(map);
  }

  @Override
  public List<LotteryPojo> getLotteryList(Map<String, Object> map) throws Exception {

    return couponMapper.getLotteryList(map);
  }

  @Override
  public List<HongbaoLogPojo> getHongbaoLogList(Map<String, Object> map) throws Exception {

    return couponMapper.getHongbaoLogList(map);
  }

  @Override
  public int addUserCoupon(UserCouponPojo userCouponPojo) throws Exception {
    return couponMapper.addUserCoupon(userCouponPojo);
  }

  @Override
  public void addCouponOrder(CouponOrderPojo couponOrderPojo) throws Exception {
    couponMapper.addCouponOrder(couponOrderPojo);
  }

  @Override
  public UserCouponPojo getUserCouponByNo(Map<String, Object> map) throws Exception {
    return couponMapper.getUserCouponByNo(map);
  }

  @Override
  public int updateUserCoupon(UserCouponPojo userCouponPojo) throws Exception {
    return couponMapper.updateUserCoupon(userCouponPojo);
  }

  @Override
  public CouponPojo getcouponById(Long id) throws Exception {
    return couponMapper.getcouponById(id);
  }

  @Override
  public void checkUserCoupon(UserCouponPojo userCouponPojo) throws SQLException {
    couponMapper.checkUserCoupon(userCouponPojo);
  }

  @Override
  public void delUserCouponByNo(String couponNo) throws SQLException {
    couponMapper.delUserCouponByNo(couponNo);
  }

  @Override
  public void updateCouponOrderStatus(CouponOrderPojo couponOrderPojo) throws SQLException {
    couponMapper.updateCouponOrderStatus(couponOrderPojo);
  }

  @Override
  public int getuserCouponCount(Map<String, Object> map) throws SQLException {
    return couponMapper.getuserCouponCount(map);
  }

  @Override
  public int getCouponCount(Map<String, Object> map) throws Exception {
    return couponMapper.getCouponCount(map);
  }

  @Override
  public void addUserCouponBatch(List<UserCouponPojo> coupons) throws SQLException {
    couponMapper.addUserCouponBatch(coupons);
  }
}
