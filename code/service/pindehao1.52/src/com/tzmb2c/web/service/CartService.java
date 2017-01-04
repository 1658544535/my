package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.CartPojo;

public interface CartService {

  public List<CartPojo> cartAllService() throws SQLException;

  List<CartPojo> getCartAllByShopId(Map<String, Object> map) throws SQLException;

  public void addCartService(CartPojo cartPojo) throws SQLException;

  public void insertCart(CartPojo cartPojo) throws SQLException;

  public void updateCart(CartPojo cartPojo) throws SQLException;

  public void updateNumCartWeb(CartPojo cartPojo) throws SQLException;

  public int updateStatusCartWeb(String[] ids) throws SQLException;

  public CartPojo getfindByIdCart(Long id) throws SQLException;

  public int cartAllCount(CartPojo cartDaoPojo);

  public int findCartByUserIdCount(Long id);

  List<CartPojo> cartAllList(CartPojo ticketRulePojo, Pager page);

  void deleteCarts(String[] tids);

  void deleteCart(Long id) throws SQLException;

  CartPojo findCartById(Long merId) throws SQLException;

  CartPojo findCartByProductId(CartPojo cartPojo) throws SQLException;

  CartPojo findCartByUserId(CartPojo cartPojo) throws SQLException;

  List<CartPojo> findCartByUserId(Long id) throws SQLException;

  List<CartPojo> findCartByShopUserId(Long id) throws SQLException;

  List<CartPojo> findCartShopByUserId(Long id) throws SQLException;

  List<CartPojo> getCartsByIds(String[] ids) throws SQLException;

  List<CartPojo> getCartsByIdsSummit(String[] ids) throws SQLException;

  public List<CartPojo> submitCartsByIds(String[] ids) throws SQLException;// 按专场分组商品

  List<CartPojo> findCartByUserIdAndShopId(CartPojo cartDaoPojo) throws SQLException;

  public List<CartPojo> groupCartBrandByUserId(Long id) throws SQLException;

  public List<CartPojo> findCartByUserIdAndBrand(CartPojo cartDaoPojo) throws SQLException;
}
