package maowu.framework.utils.web.struts2;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.UserMakerThemeService;

public class GeekSessionInterceptor extends AbstractInterceptor {
  private Logger logger = Logger.getLogger(GeekSessionInterceptor.class);
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private UserMakerThemeService userMakerThemeService;
  @Autowired
  private ProductService productService;


  private SysLoginPojo sysLogin;
  private ShopPojo shop;
  private ManufacturerPojo manufacturerPojo;
  private Integer userPostCount;
  private Integer geekThemeCount;
  private Integer productCount;
  private SysLoginPojo sysLoginPojo;

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }


  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    logger.info("进入 SessionInterceptor");
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo userPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");

    if (userPojo == null || userPojo.getLoginname().isEmpty()) {
      logger.info("进入 SessionInterceptor,user is null.");
      return "loginpage";

    } else {
      logger.info("进入 SessionInterceptor,user not null.");
      manufacturerPojo = manufacturerService.findManufacturerByUserId(userPojo.getId());
      if (manufacturerPojo == null) {
        actionContext.getSession().put("geekInfoCheck", 0);
        sysLoginPojo = sysLoginService.findSysLoginById(userPojo.getId());
        actionContext.put("sysLoginPojo", sysLoginPojo);
        return "inforPerfect";
      } else if (manufacturerPojo.getStatus() != null && manufacturerPojo.getStatus() == 1) {
        actionContext.getSession().put("geekInfoCheck", 1);
        actionContext.put("manufacturerPojo", manufacturerPojo);
        shop = shopService.getfindByIdShopWeb(userPojo.getId());
        actionContext.put("shop", shop);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userPojo.getId());
        map.put("status", 1);
        userPostCount = userCirclePostService.userCirclePostCount(map);
        geekThemeCount = userMakerThemeService.userMakerThemeCount(map);
        ProductPojo productPojo = new ProductPojo();
        productPojo.setUserId(userPojo.getId());
        productPojo.setStatus(1);
        productCount = productService.getAllCount(productPojo);
        actionContext.put("userPostCount", userPostCount);
        actionContext.put("geekThemeCount", geekThemeCount);
        actionContext.put("productCount", productCount);
        return "geekWeb";
      } else {
        actionContext.getSession().put("geekInfoCheck", 2);
        actionContext.put("manufacturerPojo", manufacturerPojo);
        shop = shopService.getfindByIdShopWeb(userPojo.getId());
        actionContext.put("shop", shop);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userPojo.getId());
        map.put("status", 1);
        userPostCount = userCirclePostService.userCirclePostCount(map);
        geekThemeCount = userMakerThemeService.userMakerThemeCount(map);
        ProductPojo productPojo = new ProductPojo();
        productPojo.setUserId(userPojo.getId());
        productPojo.setStatus(1);
        productCount = productService.getAllCount(productPojo);
        actionContext.put("userPostCount", userPostCount);
        actionContext.put("geekThemeCount", geekThemeCount);
        actionContext.put("productCount", productCount);
        return "geekWeb";
      }
    }
    // return invocation.invoke();

  }

  public Integer getUserPostCount() {
    return userPostCount;
  }

  public void setUserPostCount(Integer userPostCount) {
    this.userPostCount = userPostCount;
  }

  public Integer getGeekThemeCount() {
    return geekThemeCount;
  }

  public void setGeekThemeCount(Integer geekThemeCount) {
    this.geekThemeCount = geekThemeCount;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public Integer getProductCount() {
    return productCount;
  }

  public void setProductCount(Integer productCount) {
    this.productCount = productCount;
  }
}
