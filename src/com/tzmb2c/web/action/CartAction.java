package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductStockPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductStockService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class CartAction extends SuperAction {

  @Autowired
  private CartService cartService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private ProductStockService productStockService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;

  private CartPojo cart, productCart;
  private ProductPojo productPojo;
  private SysLoginPojo sysLoginPojo, sysLogin;
  private ProductStockPojo productStockPojo;
  private List<CartPojo> cartList, goodsInCartList;
  private ShopPojo shopPojo;
  private String result;
  private String[] tids;
  private String strId;
  private String cidStr;
  private String[] cidStrs;
  private String strProductPrices;
  private Double buyPrice;
  private Double allbuyPrice;
  private String allbuyPrice0;
  private int minNum;
  private int maxNum;
  private Integer oldNum;
  private Integer newNum;

  public String getCidStr() {
    return cidStr;
  }

  public void setCidStr(String cidStr) {
    this.cidStr = cidStr;
  }

  public CartPojo getCart() {
    return cart;
  }

  public void setCart(CartPojo cart) {
    this.cart = cart;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getCartCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(cartService.cartAllCount(cart));
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));

    return SUCCESS;
  }

  public String cartAllList() {
    // 查询商品sku
    List<CartPojo> carts = cartService.cartAllList(cart, page);
    if (carts != null && carts.size() > 0) {
      ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
      ProductSkuLinkPojo productSkuLinkPojo = null;
      for (CartPojo cart : carts) {
        if (cart.getSkuLinkId() != null && cart.getSkuLinkId() > 0) {
          productSkuLink.setId(Long.valueOf(cart.getSkuLinkId()));
          productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
          if (productSkuLinkPojo != null
              && (StringUtils.isNotBlank(productSkuLinkPojo.getColorValue()) || StringUtils
                  .isNotBlank(productSkuLinkPojo.getFormatValue()))) {
            cart.setProductName(cart.getProductName().concat("（颜色：")
                .concat(productSkuLinkPojo.getColorValue()).concat("，尺寸：")
                .concat(productSkuLinkPojo.getFormatValue()).concat("）"));
          }
        }
      }
    }
    JSONArray json = JSONArray.fromObject(carts);
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String cartDeleteId() {
    if (tids != null) {
      cartService.deleteCarts(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "cart.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "cart.do");
    }

    return null;
  }

  public String deleCart() throws SQLException {
    try {
      cartService.deleteCart(cart.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindCart() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("cartPojo", cartService.findCartById(cart.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(cart.getUserId()));
    return SUCCESS;
  }

  public String updateCart() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      cart.preUpdate(loginPojo);
    }

    cartService.updateCart(cart);
    FileUtil.alertMessageBySkip("修改成功！", "cart.do");

    return null;
  }

  public String updateNumCartWeb() throws Exception {

    try {
      productCart = cartService.findCartById(cart.getId());

      productPojo = new ProductPojo();
      productPojo.setId(productCart.getProductId());
      productPojo = productService.findProduct(productPojo);

      String prices = productPojo.getLadderPrice();
      JSONArray json = JSONArray.fromObject(prices);
      // strProductPrices = json.toString();

      for (int i = 0; i < json.size(); i++) {
        JSONObject object0 = JSONObject.fromObject(json.get(i));
        String strMinNum = (String) object0.get("min");
        minNum = Integer.valueOf(strMinNum).intValue();
        String strMaxNum = (String) object0.get("max");
        maxNum = Integer.valueOf(strMaxNum).intValue();
        if (minNum <= cart.getNum() && maxNum >= cart.getNum() || minNum <= cart.getNum()
            && maxNum == 0) {
          String bPrice = (String) object0.get("price");
          buyPrice = Double.valueOf(bPrice);
        } else if (cart.getNum() > maxNum && maxNum > 0) {
          String bPrice = (String) object0.get("price");
          buyPrice = Double.valueOf(bPrice);
        }
      }

      cart.setStockPrice(buyPrice);
      cartService.updateNumCartWeb(cart);
      allbuyPrice = buyPrice * Double.valueOf(cart.getNum());
      DecimalFormat df = new DecimalFormat(".#");
      allbuyPrice0 = df.format(allbuyPrice);
      this.result =
          "{\"success\":\"1\",\"price\":\"" + buyPrice + "\",\"num\":\"" + cart.getNum()
              + "\",\"allprice\":\"" + allbuyPrice0 + "\"}";
    } catch (Exception e) {
      this.result =
          "{\"success\":\"0\",\"price\":\"" + buyPrice + "\",\"num\":\"" + cart.getNum()
              + "\",\"allprice\":\"" + allbuyPrice0 + "\"}";
    }
    return SUCCESS;
  }

  public String addCartWeb() throws Exception {
    // ActionContext ac = ActionContext.getContext();
    // ac.put("cartlist", cartService.findCartByUserId(cart.getUserId()));
    ActionContext actionContext = ActionContext.getContext();
    sysLoginPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    // SysLoginPojo sysLoginPojo = UserUtil.getWebUser();


    productPojo = productService.findProduct(productPojo);
    shopPojo = new ShopPojo();
    shopPojo.setUserId(productPojo.getUserId());
    shopPojo = shopService.findShop(shopPojo);
    // productStockPojo = new ProductStockPojo();
    // productStockPojo.setProductId(productPojo.getId());
    // productStockPojo = productStockService.findByProductStock(productStockPojo);


    if (sysLoginPojo != null) {
      productCart = new CartPojo();
      cart.setUserId(sysLoginPojo.getId());
      cart.setProductId(productPojo.getId());
      productCart = cartService.findCartByProductId(cart);
      if (productCart == null) {
        try {
          cart.prePersist(sysLoginPojo);

          String prices = productPojo.getLadderPrice();
          JSONArray json = JSONArray.fromObject(prices);
          // strProductPrices = json.toString();

          for (int i = 0; i < json.size(); i++) {
            JSONObject object0 = JSONObject.fromObject(json.get(i));
            String strMinNum = (String) object0.get("min");
            minNum = Integer.valueOf(strMinNum).intValue();
            String strMaxNum = (String) object0.get("max");
            maxNum = Integer.valueOf(strMaxNum).intValue();
            if (minNum <= cart.getNum() && maxNum >= cart.getNum()) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            } else if (minNum <= cart.getNum() && maxNum == 0) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            } else if (cart.getNum() > maxNum && maxNum > 0) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            }
            // if(buyPrice==0){
            // String bPrice=(String) object0.get("price");
            // buyPrice = Double.valueOf(bPrice);
            // }
          }

          long StockId = 0;
          cart.setUserId(sysLoginPojo.getId());
          cart.setShopId(shopPojo.getId());
          cart.setProductId(productPojo.getId());
          cart.setProductName(productPojo.getProductName());
          cart.setProductImage(productPojo.getImage());
          cart.setWeight(productPojo.getWeight());
          cart.setStockId(StockId);
          cart.setStockPriceOld(productPojo.getDistributionPrice());
          cart.setStockPrice(buyPrice);
          cart.setNum(cart.getNum());
          cart.setType(0);
          cart.setChannel(0);
          cart.setStatus(0);
          cart.setUserName(sysLoginPojo.getName());
          cart.setShopName(shopPojo.getName());
          cart.setPostageType(productPojo.getPostageType());
          cartService.insertCart(cart);
          this.result = "{\"text\":\"1\"}";
        } catch (Exception e) {
          this.result = "{\"text\":\"2\"}";
        }
      } else {
        try {


          oldNum = productCart.getNum();
          newNum = oldNum + cart.getNum();

          String prices = productPojo.getLadderPrice();
          JSONArray json = JSONArray.fromObject(prices);
          // strProductPrices = json.toString();

          for (int i = 0; i < json.size(); i++) {
            JSONObject object0 = JSONObject.fromObject(json.get(i));
            String strMinNum = (String) object0.get("min");
            minNum = Integer.valueOf(strMinNum).intValue();
            String strMaxNum = (String) object0.get("max");
            maxNum = Integer.valueOf(strMaxNum).intValue();
            // if((minNum<=newNum && maxNum>=newNum) || (minNum<=newNum && maxNum==0)){
            // String bPrice=(String) object0.get("price");
            // buyPrice = Double.valueOf(bPrice);
            // }
            if (minNum <= newNum && maxNum >= newNum) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            } else if (minNum <= newNum && maxNum == 0) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            } else if (cart.getNum() > maxNum && maxNum > 0) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            }
          }
          productCart.preUpdate(sysLoginPojo);
          productCart.setNum(newNum);
          if (buyPrice != null) {
            productCart.setStockPrice(buyPrice);
          } else {
            productCart.setStockPrice(productCart.getStockPrice());
          }
          cartService.updateCart(productCart);
          this.result = "{\"text\":\"1\"}";
        } catch (Exception e) {
          this.result = "{\"text\":\"2\"}";
        }
      }
    } else {
      this.result = "{\"text\":\"0\"}";
    }
    return SUCCESS;
  }

  public String cartWeb() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    sysLoginPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    if (sysLoginPojo == null) {
      FileUtil.alertMessageBySkip("请先登录！", "doLoginWeb.do");
      return null;
    } else {
      ActionContext ac = ActionContext.getContext();
      // cart=new CartPojo();
      // cart.setUserId(sysLogin.getId());
      DecimalFormat df = new DecimalFormat(".#");
      List<CartPojo> temp = new ArrayList<CartPojo>();
      cartList = cartService.findCartByUserId(sysLoginPojo.getId());
      for (CartPojo cart : cartList) {
        cart.setAllStockPrice(df.format(cart.getStockPrice() * cart.getNum()));
        temp.add(cart);
      }
      int getcartCount = cartService.findCartByUserIdCount(sysLoginPojo.getId());
      Double allCartPrice = 0.0;
      cartList = cartService.findCartByUserId(sysLoginPojo.getId());
      for (CartPojo cart : cartList) {
        allCartPrice = allCartPrice + cart.getStockPrice() * cart.getNum();
      }

      String allCartPrice0 = df.format(allCartPrice);
      ac.put("cartlist", temp);
      ac.put("getcartCount", getcartCount);
      ac.put("allCartPrice", allCartPrice0);

      return SUCCESS;
    }

  }

  /***
   * 购物车：计算所选商品的总价格
   * 
   * @return
   * @throws Exception
   */
  public String countSumPrice() throws Exception {
    cidStrs = cidStr.split(",");
    Double allCartPrice = 0.0;
    // 遍历选中的购物车商品
    goodsInCartList = cartService.getCartsByIds(cidStrs);
    for (CartPojo cartDetail : goodsInCartList) {
      allCartPrice = allCartPrice + cartDetail.getStockPrice() * cartDetail.getNum();
    }
    DecimalFormat df = new DecimalFormat(".##");
    String allCartPrice0 = df.format(allCartPrice);
    this.result = allCartPrice0;
    return SUCCESS;
  }

  public String delcartWeb() throws Exception {
    try {
      cartService.deleteCart(cart.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delCheckCartWeb() throws Exception {
    String[] ids = StringUtils.isNotBlank(strId) ? strId.split(",") : null;
    try {
      cartService.deleteCarts(ids);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String cartShow() throws Exception {

    ActionContext actionContext = ActionContext.getContext();
    sysLoginPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    if (sysLoginPojo == null) {
      FileUtil.alertMessageBySkip("请先登录！", "doLoginWeb.do");
      return null;
    } else {
      int getcartCount = cartService.findCartByUserIdCount(sysLoginPojo.getId());
      Double allCartPrice = 0.00;
      cartList = cartService.findCartByUserId(sysLoginPojo.getId());
      for (CartPojo cart : cartList) {
        double cnum = cart.getNum();
        allCartPrice = allCartPrice + cart.getStockPrice() * cnum;
      }
      DecimalFormat df = new DecimalFormat(".##");
      String allCartPrice0 = df.format(allCartPrice);

      ActionContext ac = ActionContext.getContext();
      ac.put("getcartCount", getcartCount);
      ac.put("allCartPrice", allCartPrice0);
      return SUCCESS;
    }
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public ProductStockPojo getProductStockPojo() {
    return productStockPojo;
  }

  public void setProductStockPojo(ProductStockPojo productStockPojo) {
    this.productStockPojo = productStockPojo;
  }

  public String getStrProductPrices() {
    return strProductPrices;
  }

  public void setStrProductPrices(String strProductPrices) {
    this.strProductPrices = strProductPrices;
  }

  public Double getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(Double buyPrice) {
    this.buyPrice = buyPrice;
  }

  public int getMinNum() {
    return minNum;
  }

  public void setMinNum(int minNum) {
    this.minNum = minNum;
  }

  public int getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(int maxNum) {
    this.maxNum = maxNum;
  }

  public CartPojo getProductCart() {
    return productCart;
  }

  public void setProductCart(CartPojo productCart) {
    this.productCart = productCart;
  }

  public Integer getOldNum() {
    return oldNum;
  }

  public void setOldNum(Integer oldNum) {
    this.oldNum = oldNum;
  }

  public Integer getNewNum() {
    return newNum;
  }

  public void setNewNum(Integer newNum) {
    this.newNum = newNum;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public List<CartPojo> getCartList() {
    return cartList;
  }

  public void setCartList(List<CartPojo> cartList) {
    this.cartList = cartList;
  }

  public String getStrId() {
    return strId;
  }

  public void setStrId(String strId) {
    this.strId = strId;
  }
}
