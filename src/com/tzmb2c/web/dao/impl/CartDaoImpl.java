package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.CartDao;
import com.tzmb2c.web.mapper.CartMapper;
import com.tzmb2c.web.pojo.CartPojo;

public class CartDaoImpl implements CartDao {

  @Autowired
  private CartMapper cartMapper;

  @Override
  public List<CartPojo> getCartAll() throws SQLException {
    return cartMapper.getCartAll();
  }

  @Override
  public void insertCart(CartPojo cartPojo) throws SQLException {
    try {
      cartMapper.insertCart(cartPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateCart(CartPojo cartPojo) throws SQLException {

    cartMapper.updateCart(cartPojo);
  }

  @Override
  public void updateNumCartWeb(CartPojo cartPojo) throws SQLException {

    cartMapper.updateNumCartWeb(cartPojo);
  }

  @Override
  public int updateStatusCartWeb(String[] ids) throws SQLException {

    return cartMapper.updateStatusCartWeb(ids);
  }

  @Override
  public CartPojo getfindByIdCart(Long id) throws SQLException {
    return cartMapper.getfindByIdCart(id);

  }

  @Override
  public int cartAllCount(Map<String, Object> map) {
    return cartMapper.cartAllCount(map);
  }

  @Override
  public int findCartByUserIdCount(Long id) {
    return cartMapper.findCartByUserIdCount(id);
  }

  @Override
  public List<CartPojo> cartAllList(Map<String, Object> map) {
    return cartMapper.cartAllList(map);
  }

  @Override
  public void deleteCart(Long id) throws SQLException {

    cartMapper.deleteCart(id);
  }

  @Override
  public void deleteCarts(String[] ids) throws SQLException {

    cartMapper.deleteCarts(ids);
  }

  @Override
  public CartPojo findCartById(Long id) throws SQLException {
    return cartMapper.getfindByIdCart(id);
  }

  @Override
  public CartPojo findCartByProductId(CartPojo cartPojo) throws SQLException {
    return cartMapper.findCartByProductId(cartPojo);
  }

  @Override
  public CartPojo findCartByUserId(CartPojo cartPojo) throws SQLException {
    return cartMapper.findCartByUserId(cartPojo);
  }

  @Override
  public List<CartPojo> findCartByUserId(Long id) throws SQLException {
    return cartMapper.getfindByUserIdCart(id);
  }

  @Override
  public List<CartPojo> findCartByShopUserId(Long id) throws SQLException {
    return cartMapper.findCartByShopUserId(id);
  }

  @Override
  public List<CartPojo> getCartsByIds(String[] ids) throws SQLException {
    return cartMapper.getCartsByIds(ids);
  }

  @Override
  public List<CartPojo> getCartsByIdsSummit(String[] ids) throws SQLException {
    return cartMapper.getCartsByIdsSummit(ids);
  }

  @Override
  public List<CartPojo> getCartAllByShopId(Map<String, Object> map) throws SQLException {
    return cartMapper.getCartAllByShopId(map);
  }

  @Override
  public List<CartPojo> findCartByUserIdAndShopId(CartPojo cartDaoPojo) throws SQLException {
    return cartMapper.findCartByUserIdAndShopId(cartDaoPojo);
  }

  @Override
  public List<CartPojo> findCartShopByUserId(Long id) throws SQLException {
    return cartMapper.findCartShopByUserId(id);
  }

  @Override
  public List<CartPojo> groupCartBrandByUserId(Long id) throws SQLException {
    return cartMapper.groupCartBrandByUserId(id);
  }

  @Override
  public List<CartPojo> findCartByUserIdAndBrand(CartPojo cartDaoPojo) throws SQLException {
    return cartMapper.findCartByUserIdAndBrand(cartDaoPojo);
  }

  @Override
  public List<CartPojo> submitCartsByIds(String[] ids) throws SQLException {
    return cartMapper.submitCartsByIds(ids);
  }
}
