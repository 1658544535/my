package maowu.framework.utils.web.struts2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCertificatesPhotoPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.MessagesCenterService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCertificatesPhotoService;

public class WebSessionInterceptor extends AbstractInterceptor {

  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private UserCertificatesPhotoService userCertificatesPhotoService;
  @Autowired
  private MessagesCenterService messagesCenterService;

  private Logger logger = Logger.getLogger(WebSessionInterceptor.class);

  private ProductTypePojo productTypePojo;
  private List<ProductTypePojo> productTypePojos;
  private SysLoginPojo sysLogin;
  private ShopPojo shop;
  private ManufacturerPojo manufacturerPojo;
  private UserCertificatesPhotoPojo userCertificatesPhotoPojo;

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    logger.info("进入 SessionInterceptor");
    ActionContext actionContext = ActionContext.getContext();
    // @SuppressWarnings("unused")
    SysLoginPojo userPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    // List<MenuPojo> menuListAll = (List<MenuPojo>) actionContext
    // .getSession().get("menuListAll");
    // List<String> actionList = getmenuAll(menuListAll);
    //
    // System.out.println(invocation.getProxy().getNamespace());

    if (userPojo == null || userPojo.getLoginname().isEmpty()) {
      logger.info("进入 SessionInterceptor,user is null.");
      // result = invocation.invoke();
      return "loginpage";
      // } else {
      // logger.info("进入 SessionInterceptor,user not null.");
      // if (actionList.contains(invocation.getProxy().getActionName()
      // + ".do")) {
      // return invocation.invoke();
      // } else {
      // return "notAuthority";
      // }

    } else {
      logger.info("进入 SessionInterceptor,user not null.");
      // SysLoginPojo s = sysLoginService.findSysLoginById(userPojo.getId());
      // if (s != null && (s.getName() == null || "".equals(s.getName()))) {
      ManufacturerPojo m = manufacturerService.findManufacturerByUserId(userPojo.getId());
      if (m == null) {
        actionContext.getSession().put("informationIsCheck", 0);

        ActionContext ac = ActionContext.getContext();
        // ac.put("type", sysDictService.getSysDictListByType("sys_login_type"));
        // ac.put("status", sysDictService.getSysDictListByType("status"));
        ac.put("scale", sysDictService.getSysDictListByType("scale"));
        // ac.put("channel", sysDictService.getSysDictListByType("channel"));
        ac.put("isread", sysDictService.getSysDictListByType("isread"));
        // ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
        ProductTypePojo productTypePojo = new ProductTypePojo();
        productTypePojo.setId(0l);
        productTypePojo.setStatus(1);
        productTypePojos = productTypeService.getProductTypeByPid(productTypePojo);
        for (int i = 0; i < productTypePojos.size(); i++) {
          if (productTypePojos.get(i).getName().equals("0-3岁婴幼儿玩具")
              || productTypePojos.get(i).getName().equals("3-6岁儿童玩具")
              || productTypePojos.get(i).getName().equals("6岁以上玩具")) {
            productTypePojos.remove(i);
          }
        }
        ac.put("mainCategory", productTypePojos);
        // ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
        return "information";
      } else if (m != null && m.getStatus() == 0) {
        actionContext.getSession().put("informationIsCheck", 0);

        ActionContext ac = ActionContext.getContext();
        // ac.put("type", sysDictService.getSysDictListByType("sys_login_type"));
        // ac.put("status", sysDictService.getSysDictListByType("status"));
        ac.put("scale", sysDictService.getSysDictListByType("scale"));
        ac.put("isread", sysDictService.getSysDictListByType("isread"));
        // ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
        ProductTypePojo productTypePojo = new ProductTypePojo();
        productTypePojo.setPid(-1l);
        productTypePojo.setStatus(1);
        productTypePojos = productTypeService.getProductTypeByPid(productTypePojo);
        for (int i = 0; i < productTypePojos.size(); i++) {
          if (productTypePojos.get(i).getName().equals("0-3岁婴幼儿玩具")
              || productTypePojos.get(i).getName().equals("3-6岁儿童玩具")
              || productTypePojos.get(i).getName().equals("6岁以上玩具")) {
            productTypePojos.remove(i);
          }
        }
        ac.put("mainCategory", productTypePojos);
        // ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));

        // sysLogin = new SysLoginPojo();
        // sysLogin.setName(userPojo.getName());
        // setManufacturerPojo(manufacturerService.findManufacturerByUserId(userPojo.getId()));
        setShop(shopService.getfindByIdShopWeb(userPojo.getId()));
        setUserCertificatesPhotoPojo(userCertificatesPhotoService
            .findUserCertificatesPhotoByUid(userPojo.getId()));
        ac.put("sysLogin", sysLogin);
        ac.put("manufacturerPojo", m);
        ac.put("shop", shop);
        ac.put("userCertificatesPhotoPojo", userCertificatesPhotoPojo);

        return "informationPerfect";
      } else {
        actionContext.getSession().put("informationIsCheck", 1);
        actionContext.put("manufacturerPojo", m);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isRead", 0);
        map.put("isDelete", 0);
        map.put("userId", userPojo.getId());
        map.put("status", 1);
        actionContext.getSession().put("sellerMessageCount",
            messagesCenterService.findMessagesCenterCount(map));
      }
    }
    return invocation.invoke();

  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public UserCertificatesPhotoPojo getUserCertificatesPhotoPojo() {
    return userCertificatesPhotoPojo;
  }

  public void setUserCertificatesPhotoPojo(UserCertificatesPhotoPojo userCertificatesPhotoPojo) {
    this.userCertificatesPhotoPojo = userCertificatesPhotoPojo;
  }

}
