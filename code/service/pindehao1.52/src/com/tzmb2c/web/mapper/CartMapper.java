package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.CartPojo;

public interface CartMapper {


  public List<CartPojo> getCartAll() throws SQLException;

  public List<CartPojo> getCartAllByShopId(Map<String, Object> map) throws SQLException;

  public void insertCart(CartPojo cartPojo) throws SQLException;

  public void updateCart(CartPojo cartPojo) throws SQLException;

  public int updateStatusCartWeb(String[] ids) throws SQLException;// 修改选中的购物车商品的状态

  public void updateNumCartWeb(CartPojo cartPojo) throws SQLException;

  public CartPojo getfindByIdCart(Long id) throws SQLException;

  public CartPojo findCartByProductId(CartPojo cartPojo) throws SQLException;

  public CartPojo findCartByUserId(CartPojo cartPojo) throws SQLException;

  public List<CartPojo> getfindByUserIdCart(Long id) throws SQLException;

  public List<CartPojo> findCartByShopUserId(Long id) throws SQLException;

  public List<CartPojo> findCartShopByUserId(Long id) throws SQLException;

  public void deleteCart(Long id) throws SQLException;

  public int cartAllCount(Map<String, Object> map);

  public int findCartByUserIdCount(Long id);

  public List<CartPojo> cartAllList(Map<String, Object> map);

  void deleteCarts(String[] cartIds) throws SQLException;

  public List<CartPojo> getCartsByIds(String[] ids) throws SQLException;

  public List<CartPojo> getCartsByIdsSummit(String[] ids) throws SQLException;// 获取经过分组的商品

  public List<CartPojo> submitCartsByIds(String[] ids) throws SQLException;// 按专场分组商品


  public List<CartPojo> findCartByUserIdAndShopId(CartPojo cartDaoPojo) throws SQLException;

  public List<CartPojo> groupCartBrandByUserId(Long id) throws SQLException;

  public List<CartPojo> findCartByUserIdAndBrand(CartPojo cartDaoPojo) throws SQLException;
}
