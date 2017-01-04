package com.taobaoLogin.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.ItemAddRequest;
import com.taobao.api.response.ItemAddResponse;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SysLoginService;

public class taobaoLoginAction extends SuperAction {

  private List<SysLoginPojo> sysLoginPojos;
  private String[] tids;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public List<SysLoginPojo> getSysLoginPojos() {
    return sysLoginPojos;
  }

  public void setSysLoginPojos(List<SysLoginPojo> sysLoginPojos) {
    this.sysLoginPojos = sysLoginPojos;
  }

  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private ProductTypeService productTypeService;

  private static final Logger logger = Logger.getLogger(taobaoLoginAction.class);
  public static String KEY = "23104625";
  public static String SECRET = "fca005615278b60dbbc526b3cbeb29f7";
  public static String REDIRECT = "http://www.taozhuma.com/doLoginBytaobao.do";
  public static String AUTHORIZEURL = "https://oauth.taobao.com/authorize";
  public static String TOKENURL = " https://oauth.taobao.com/token";
  private static String sessionkey1;

  public static String getSessionkey1() {
    return sessionkey1;
  }

  public static void setSessionkey1(String sessionkey1) {
    taobaoLoginAction.sessionkey1 = sessionkey1;
  }

  public void toLoginBytaobao() throws IOException {
    StringBuilder url = new StringBuilder(AUTHORIZEURL);
    url.append("?response_type=code").append("&client_id=").append(KEY).append("&redirect_uri=")
        .append(REDIRECT).append("&state=").append(MD5Util.MD5(SECRET + KEY));
    ServletActionContext.getResponse().sendRedirect(url.toString()); // 重定向到淘宝授权
  }

  public String doLoginBytaobao() throws Exception {
    HttpServletRequest request = ServletActionContext.getRequest();
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    logger.info("taobao doLogin state:" + state);
    if (Util.isEmptyString(code)) {
      return LOGIN;
    }
    Map<String, String> param = new HashMap<String, String>();
    param.put("response_type", "code");
    param.put("client_id", KEY);
    param.put("redirect_uri", REDIRECT);
    param.put("state", state);
    param.put("scope", "item"); // item,promotion,usergrade
    param.put("view", "web");
    param.put("code", code);
    param.put("client_secret", SECRET);
    param.put("grant_type", "authorization_code");
    String responseJson = "";
    try {

      responseJson = WebUtils.doPost(TOKENURL, param, 3000, 3000);
    } catch (Exception e) {
      e.getMessage();
    }
    System.out.println(responseJson);
    JSONObject jo = JSONObject.fromObject(responseJson);
    if (jo.get("taobao_user_id") == null) {
      return LOGIN;
    }
    // 得到淘宝用户名
    String nikename = java.net.URLDecoder.decode(jo.get("taobao_user_nick").toString(), "UTF-8");
    // 得到淘宝用户id
    String taobao_user_id = jo.get("taobao_user_id").toString();
    sessionkey1 = jo.get("access_token").toString();
    SysLoginPojo sysloginPojo = new SysLoginPojo();
    sysloginPojo.setName(nikename);
    sysloginPojo.setTaobao_user_id(taobao_user_id);
    // 根据淘宝ID判断是否有过注册
    sysLoginPojos = sysLoginService.getSysLoginByTaobao_user_id(taobao_user_id);
    // 如果没有暂时保存到session中，跳转到激活页面
    if (sysLoginPojos.size() == 0) {
      ActionContext.getContext().getSession().put("wuser", sysloginPojo);
      return "ACTIVATE";
    }
    // 将查询到的用户信息传递到session中
    ActionContext.getContext().getSession().put("wuser", sysLoginPojos.get(0));
    return "SUCCESS";
  }

  /**
   * 添加分销商品到淘宝
   * 
   * @throws ApiException
   * 
   * */
  public String insertProductToTaobao() {
    TaobaoClient client =
        new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", KEY, SECRET);
    ItemAddRequest req = new ItemAddRequest();
    // 判断用户是否登录
    SysLoginPojo user = UserUtil.getWebUser();
    if (user.getTaobao_user_id() == null || sessionkey1 == null) {
      FileUtil.alertMessageBySkip("您的淘宝授权已经失效，请重新登录！", "doLoginOutWeb.do");
      return null;
    }
    if (tids == null || tids.length < 1) {
      FileUtil.alertMessageBySkip("请先勾选需要添加到淘宝的产品", "goConsumerProductWeb.do");
      return null;
    }
    // 循环得到用户选择的产品，并添加到淘宝用户分销信息中
    List<ProductPojo> productList = new ArrayList<ProductPojo>();
    ProductPojo product;
    try {
      for (int i = 0; i < tids.length; i++) {
        product = new ProductPojo();
        product.setId(Long.valueOf(tids[i]));
        productList.add(productService.findProduct(product));
        req.setNum(30L);
        req.setPrice(productList.get(i).getDistributionPrice().toString());// 设置分销价
        req.setType("fixed");// 设置发布类型为一口价
        req.setStuffStatus("new");// 设置产品新旧程度为全新
        req.setTitle(productList.get(i).getProductName());// 设置产品标题即产品名称
        req.setDesc("请卖家自行更改产品描述：" + productList.get(i).getProductSketch());// 设置产品描述
        req.setLocationState("广东");// 设置所在省
        req.setLocationCity("汕头");// 设置所在城市
        req.setApproveStatus("instock");// 设置商品上传的状态，默认为在仓库中
        productList.get(i).getProductTypeIds();
        // 判断该条商品信息的类型
        // if(productList.get(i).getProductTypeIds().equals("1")){
        // req.setCid(50012843L);//设置淘宝中的商品信息类型为电动遥控
        // }
        // if(productList.get(i).getProductTypeIds().equals("3")){
        // req.setCid(50074234L);//设置淘宝中的商品信息类型为拼图
        // }
        // if(productList.get(i).getProductTypeIds().equals("5")){
        // req.setCid(50074232L);//设置淘宝中的商品信息类型为积木
        // }
        // if(productList.get(i).getProductTypeIds().equals("2")||productList.get(i).getProductTypeIds().equals("0")){
        // req.setCid(50008876L);//设置淘宝中的商品信息类型为早教智能
        // }
        // if(productList.get(i).getProductTypeIds().equals("4")){
        // req.setCid(211111L);//设置淘宝中的商品信息类型为童车
        // }
        // if(productList.get(i).getProductTypeIds().equals("6")){
        // req.setCid(50077659L);//设置淘宝中的商品信息类型为6岁以上
        // }
        req.setCid(121392021l);
        // req.setFreightPayer("buyer");//设置运费承担方式，默认买家承担 如果要设置承担方式则必须设置邮费信息
        req.setHasInvoice(true);// 设置是否保修，默认保修
        Long productId = Long.parseLong(tids[i].trim());
        List<ProductFocusImagesPojo> list =
            productFocusImagesService.getProductFocusImagesByPid(productId);
        // 得到该商品所在文件夹路径
        String pathString =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        // 设置输出图路径
        String outputDir =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/taobaoImage")
                + File.separator;
        // 使用图片工具类，将图片压缩成小于500Kb的文件
        CompressPicture cp = new CompressPicture();
        cp.compressPic(pathString, outputDir, list.get(0).getImages(), list.get(0).getImages(),
            3072, 3072, true);
        FileItem fItem =
            new FileItem(new File(outputDir + File.separator + list.get(0).getImages()));
        req.setImage(fItem);
        req.setIsTaobao(true);// 设置是否淘宝显示，默认是
        req.setSellPromise(true);// 设置是否承诺退换货服务，默认承诺
        // req.setWeight(Long.parseLong(productList.get(i).getWeight()));//设置商品的重量
        // 设置厂名
        // req.setFoodSecurityFactory("广东汕头信宇玩具股份有限公司");
        // 设置厂址
        // req.setFoodSecurityFactorySite("广东省汕头市澄海区莱美工业区");
        // 设置厂家联系方式
        // req.setFoodSecurityContact("0754-85121854");
        // 设置材质
        req.setFoodSecurityMix(productList.get(i).getTexture());
        // 设置储藏方法
        req.setFoodSecurityPlanStorage("常温避免阳光直射");
        ItemAddResponse response = client.execute(req, sessionkey1);
        if (!response.isSuccess()) {
          JSONObject jo = JSONObject.fromObject(response.getBody());
          String errormsg = jo.get("error_response").toString();
          JSONObject emsg = JSONObject.fromObject(errormsg);
          FileUtil.alertMessageBySkip("添加失败，" + emsg.get("sub_msg"), "goConsumerProductWeb.do");
          return null;
        } else {
          // 向数据库中添加相应的信息
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.getMessage();
    }
    FileUtil.alertMessageBySkip("尊敬的用户，您选择的商品信息已经成功添加到了您的淘宝店铺仓库中。请您前往您的淘宝店铺中进一步确认你的商品信息！",
        "goConsumerProductWeb.do");
    return null;
  }
}
