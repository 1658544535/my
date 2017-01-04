package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.HongbaoLogPojo;
import com.tzmb2c.web.pojo.LotteryForwardPojo;
import com.tzmb2c.web.pojo.LotteryLogPojo;
import com.tzmb2c.web.pojo.LotteryPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;

public interface CouponMapper {

  public List<CouponPojo> getcouponList(Map<String, Object> map) throws Exception;

  public int getCouponCount(Map<String, Object> map) throws Exception;

  public int getuserCouponCount(Map<String, Object> map) throws SQLException;

  public CouponPojo getcouponById(Long id) throws Exception;

  public void deletecouponById(Long id) throws Exception;

  public void checkcouponById(Long id) throws Exception;

  public void uncheckcouponById(Long id) throws Exception;

  public void addcoupon(CouponPojo couponPojo) throws Exception;

  public int addUserCoupon(UserCouponPojo userCouponPojo) throws Exception;

  public void addUserCouponBatch(List<UserCouponPojo> coupons) throws SQLException;

  public void addCouponOrder(CouponOrderPojo couponOrderPojo) throws Exception;

  public void updatecouponById(CouponPojo couponPojo) throws Exception;

  public int updateUserCoupon(UserCouponPojo userCouponPojo) throws Exception;

  public void checkUserCoupon(UserCouponPojo userCouponPojo) throws SQLException;

  public void delUserCouponByNo(String couponNo) throws SQLException;

  public List<UserCouponPojo> getuserCouponList(Map<String, Object> map) throws Exception;

  public UserCouponPojo getUserCouponByNo(Map<String, Object> map) throws Exception;

  public List<LotteryLogPojo> getLotteryLogList(Map<String, Object> map) throws Exception;

  public List<LotteryForwardPojo> getLotteryForwardList(Map<String, Object> map) throws Exception;

  public List<CouponOrderPojo> getcouponOrderList(Map<String, Object> map) throws Exception;

  public List<LotteryPojo> getLotteryList(Map<String, Object> map) throws Exception;

  public List<HongbaoLogPojo> getHongbaoLogList(Map<String, Object> map) throws Exception;

  public void updateCouponOrderStatus(CouponOrderPojo couponOrderPojo) throws SQLException;


}
