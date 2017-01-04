package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.CartDao;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.service.CartService;

public class CartServiceImpl implements CartService {
  @Autowired
  private CartDao cartDao;

  public void setCartDao(CartDao cartDao) {
    this.cartDao = cartDao;
  }


  @Override
  public List<CartPojo> cartAllService() throws SQLException {
    return cartDao.getCartAll();
  }



  @Override
  public void addCartService(CartPojo cartPojo) throws SQLException {

    cartDao.insertCart(cartPojo);
  }


  @Override
  public void insertCart(CartPojo cartPojo) throws SQLException {

    cartDao.insertCart(cartPojo);
  }

  @Override
  public void updateCart(CartPojo cartPojo) throws SQLException {
    cartDao.updateCart(cartPojo);

  }

  @Override
  public void updateNumCartWeb(CartPojo cartPojo) throws SQLException {
    cartDao.updateNumCartWeb(cartPojo);

  }

  @Override
  public int updateStatusCartWeb(String[] ids) throws SQLException {
    return cartDao.updateStatusCartWeb(ids);

  }

  @Override
  public CartPojo getfindByIdCart(Long id) throws SQLException {
    return cartDao.getfindByIdCart(id);

  }

  @Override
  public void deleteCart(Long id) throws SQLException {
    cartDao.deleteCart(id);
  }

  @Override
  public int cartAllCount(CartPojo cartDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (cartDaoPojo != null) {
      map.put("productName", cartDaoPojo.getProductName());
      map.put("channel", cartDaoPojo.getChannel());
      map.put("productModel", cartDaoPojo.getProductModel());
      map.put("productNum", cartDaoPojo.getProductNum());
    }
    return cartDao.cartAllCount(map);
  }


  @Override
  public int findCartByUserIdCount(Long id) {
    return cartDao.findCartByUserIdCount(id);
  }

  @Override
  public List<CartPojo> cartAllList(CartPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("productName", ticketRulePojo.getProductName());
      map.put("channel", ticketRulePojo.getChannel());
      map.put("productModel", ticketRulePojo.getProductModel());
      map.put("productNum", ticketRulePojo.getProductNum());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<CartPojo> list = cartDao.cartAllList(map);

    return list;
  }

  @Override
  public void deleteCarts(String[] tids) {
    try {
      cartDao.deleteCarts(tids);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public CartPojo findCartById(Long id) throws SQLException {

    return cartDao.findCartById(id);

  }

  @Override
  public CartPojo findCartByProductId(CartPojo cartPojo) throws SQLException {

    return cartDao.findCartByProductId(cartPojo);

  }

  @Override
  public CartPojo findCartByUserId(CartPojo cartPojo) throws SQLException {

    return cartDao.findCartByUserId(cartPojo);

  }

  @Override
  public List<CartPojo> findCartByUserId(Long id) throws SQLException {

    return cartDao.findCartByUserId(id);

  }

  @Override
  public List<CartPojo> findCartByShopUserId(Long id) throws SQLException {

    return cartDao.findCartByShopUserId(id);

  }

  @Override
  public List<CartPojo> getCartsByIds(String[] ids) throws SQLException {
    // StringBuffer sb = new StringBuffer("");
    // for(int i=0;i<ids.length;i++){
    // sb.append(ids[i]).append(",");
    // }
    // String cStr = sb.toString();
    // if(cStr.length() > 0){
    // //cStr.substring(0,cStr.length()-1);
    // cStr = cStr.substring(0,cStr.length()-1);
    //
    // }
    return cartDao.getCartsByIds(ids);
  }


  @Override
  public List<CartPojo> getCartsByIdsSummit(String[] ids) throws SQLException {
    return cartDao.getCartsByIdsSummit(ids);
  }


  @Override
  public List<CartPojo> getCartAllByShopId(Map<String, Object> map) throws SQLException {
    return cartDao.getCartAllByShopId(map);
  }

  @Override
  public List<CartPojo> findCartByUserIdAndShopId(CartPojo cartDaoPojo) throws SQLException {
    return cartDao.findCartByUserIdAndShopId(cartDaoPojo);
  }


  @Override
  public List<CartPojo> findCartShopByUserId(Long id) throws SQLException {
    return cartDao.findCartShopByUserId(id);
  }


  @Override
  public List<CartPojo> groupCartBrandByUserId(Long id) throws SQLException {
    return cartDao.groupCartBrandByUserId(id);
  }


  @Override
  public List<CartPojo> findCartByUserIdAndBrand(CartPojo cartPojo) throws SQLException {
    return cartDao.findCartByUserIdAndBrand(cartPojo);
  }


  @Override
  public List<CartPojo> submitCartsByIds(String[] ids) throws SQLException {
    return cartDao.submitCartsByIds(ids);
  }
}
