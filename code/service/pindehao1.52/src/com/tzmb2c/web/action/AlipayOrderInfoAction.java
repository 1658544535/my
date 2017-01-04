package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.util.AlipayNotify;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 支付宝
 * 
 * @author creazylee
 * 
 */
public class AlipayOrderInfoAction extends SuperAction {

  private File upfile;
  private String upfileFileName;
  private String upfileContentType;
  private String[] tids;
  private String type;
  private String result;
  private int item;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSellService productSellService;
  private String tittle;

  private Map requestParams;

  public Map getRequestParams() {
    return requestParams;
  }

  public void setRequestParams(Map requestParams) {
    this.requestParams = requestParams;
  }

  public String getTittle() {
    return tittle;
  }

  public void setTittle(String tittle) {
    this.tittle = tittle;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getUpfileContentType() {
    return upfileContentType;
  }

  public void setUpfileContentType(String upfileContentType) {
    this.upfileContentType = upfileContentType;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getItem() {
    return item;
  }

  public void setItem(int item) {
    this.item = item;
  }

  public String goalipay() throws Exception {
    // AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
    // alipayOrderInfoPojo.setBuyerEmail("fsdfsdfs");
    // alipayOrderInfoPojo.setCreateDate(new Date());
    // alipayOrderInfoPojo.setVersion(0);
    // alipayOrderInfoService.insertOne(alipayOrderInfoPojo);
    return SUCCESS;
  }

  // public String requestPay() throws Exception {
  // // 支付类型
  // String payment_type = "1";
  // // 必填，不能修改
  // // 服务器异步通知页面路径
  // String notify_url = "";
  // // String notify_url =
  // // "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";
  // // 需http://格式的完整路径，不能加?id=123这类自定义参数 //页面跳转同步通知页面路径
  // String return_url = "http://m2c.taozhuma.com/getreturnUrl.do";
  // // String return_url =
  // // "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";
  // // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/ //卖家支付宝帐户
  // String seller_email = "taozhuma@5315.cn";
  // // 必填 //商户订单号
  // String out_trade_no = "6843192280647188";
  // // 商户网站订单系统中唯一订单号，必填 //订单名称
  // String subject = "淘竹马商品购买";
  // // 必填 //付款金额
  // String total_fee = "0.01";
  // // 必填 //订单描述
  // String body = "美国专业护腕鼠标垫，舒缓式凝胶软垫模拟手腕的自然曲线和运动，创造和缓的GelFlex舒适地带！";
  // // 商品展示地址
  // String show_url = "";
  // // 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html //防钓鱼时间戳
  // String anti_phishing_key = "";
  // // 若要使用请调用类文件submit中的query_timestamp函数 //客户端的IP地址
  // String exter_invoke_ip = "";
  // // 非局域网的外网IP地址，如：221.0.0.1
  //
  // // 把请求参数打包成数组
  // Map<String, String> sParaTemp = new HashMap<String, String>();
  // sParaTemp.put("service", "create_direct_pay_by_user");
  // sParaTemp.put("partner", AlipayConfig.partner);
  // sParaTemp.put("_input_charset", AlipayConfig.input_charset);
  // sParaTemp.put("payment_type", payment_type);
  // sParaTemp.put("notify_url", notify_url);
  // sParaTemp.put("return_url", return_url);
  // sParaTemp.put("seller_email", seller_email);
  // sParaTemp.put("out_trade_no", out_trade_no);
  // sParaTemp.put("subject", subject);
  // sParaTemp.put("total_fee", total_fee);
  // sParaTemp.put("body", body);
  // sParaTemp.put("show_url", show_url);
  // sParaTemp.put("anti_phishing_key", anti_phishing_key);
  // sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
  //
  // // 建立请求
  // String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
  // System.out.println(sHtmlText);
  // ServletActionContext.getResponse().setContentType(
  // "text/html; charset=utf-8");
  // ServletActionContext.getResponse().getWriter().write(sHtmlText);
  // return null;
  //
  // }
  /*
   * 支付宝同步跳转
   */
  public String getreturnUrl() throws Exception {
    // 获取支付宝GET过来反馈信息
    Map<String, String> params = new HashMap<String, String>();

    Map requestParams = ServletActionContext.getRequest().getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
      // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
      params.put(name, valueStr);
    }
    System.out.println(">>>>>支付宝同步支付返回:" + params);
    // params.get("out_trade_no");
    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    // 商户订单号

    // String out_trade_no = new String(ServletActionContext.getRequest()
    // .getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

    // 支付宝交易号

    // String trade_no = new String(ServletActionContext.getRequest()
    // .getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

    // 交易状态
    String trade_status =
        new String(ServletActionContext.getRequest().getParameter("trade_status")
            .getBytes("ISO-8859-1"), "UTF-8");

    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

    // 计算得出通知验证结果
    boolean verify_result = AlipayNotify.verify(params);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

    if (verify_result) {// 验证成功
      // ////////////////////////////////////////////////////////////////////////////////////////
      // 请在这里加上商户的业务逻辑程序代码

      // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
      if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
        // 判断该笔订单是否在商户网站中已经做过处理
        // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        // 如果有做过处理，不执行商户的业务程序
        // ServletActionContext.getResponse().getWriter()
        // .write("交易成功<br />");
        // ServletActionContext.getResponse().getWriter()
        // .write(params.toString());
        String outTradeNo = params.get("out_trade_no");
        System.out.println(">>>>>>支付宝支付流水号:" + outTradeNo);
        AlipayOrderInfoPojo alipayOrderInfoPojo =
            alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
        if (alipayOrderInfoPojo != null
            && "WAIT_BUYER_PAY".equals(alipayOrderInfoPojo.getTradeStatus())) {
          // 验证价格
          Double totalFee = Double.valueOf(params.get("total_fee"));
          Double aliTotalFee = Double.valueOf(alipayOrderInfoPojo.getTotalFee());
          if (totalFee.doubleValue() == aliTotalFee.doubleValue()) {
            // 更新支付宝订单状态
            alipayOrderInfoPojo.setTradeNo(params.get("trade_no"));
            alipayOrderInfoPojo.setBuyerEmail(params.get("buyer_email"));
            alipayOrderInfoPojo.setBuyerId(params.get("buyer_id"));
            alipayOrderInfoPojo.setTradeStatus(params.get("trade_status"));
            alipayOrderInfoPojo.setOutTradeNo(params.get("out_trade_no"));
            alipayOrderInfoPojo.setUpdateDate(new Date());
            // 同步标志
            alipayOrderInfoPojo.setUpdateBy(1000L);
            alipayOrderInfoService.updateOrder(alipayOrderInfoPojo);
            // 单订单标志
            String extra_common_param = params.get("extra_common_param");
            // 改变支付状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("updateDate", new Date());
            map.put("outTradeNo", params.get("out_trade_no"));
            if (extra_common_param != null && !extra_common_param.equals("")
                && extra_common_param.length() != 0) {
              map.put("orderId", Long.parseLong(extra_common_param));
            }
            orderService.orderPaySuccess(map);
            // 添加销售量
            List<OrderDetailPojo> orderDetaillist =
                orderDetailService.getOrderDetailByOutTradeNo(params.get("out_trade_no"));
            if (orderDetaillist != null && orderDetaillist.size() > 0) {
              for (OrderDetailPojo p : orderDetaillist) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                ProductPojo productPojo = new ProductPojo();
                productPojo.setId(p.getProductId());
                productPojo = productService.findProduct(productPojo);
                if (productPojo != null) {
                  map1.put("id", productPojo.getId());
                  map1.put("sellNumber", productPojo.getSellNumber() + p.getNum());
                  productService.updateProductsellNumber(map1);
                  // 更新商品当日销售量表
                  ProductSellPojo productSellPojo = new ProductSellPojo();
                  productSellPojo.setProductId(productPojo.getId());
                  productSellPojo.setSellNumber(productPojo.getSellNumber() + p.getNum());
                  // 根据商品id查找对应商品当日销售量
                  ProductSellPojo productSell = productSellService.getById(productPojo.getId());
                  productSellPojo.setDaySell(productSell.getDaySell() + p.getNum());
                  productSellPojo.setUpdateDate(new Date());
                  productSellService.update(productSellPojo);
                }
              }
            }
            System.out.println("支付成功！");
          } else {
            System.out.println("未找到支付记录或已支付过！");
          }
        } else {
          System.out.println("支付价格与订单价格不一致!");
        }
      } else {
        String outTradeNo = params.get("out_trade_no");
        System.out.println(">>>>>>支付宝支付流水号:" + outTradeNo);
        AlipayOrderInfoPojo alipayOrderInfoPojo =
            alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
        if (alipayOrderInfoPojo != null
            && "WAIT_BUYER_PAY".equals(alipayOrderInfoPojo.getTradeStatus())) {
          alipayOrderInfoPojo.setBuyerEmail(params.get("buyer_email"));
          alipayOrderInfoPojo.setBuyerId(params.get("buyer_id"));
          // alipayOrderInfoPojo.setTradeStatus("TRADE_FAIL");
          alipayOrderInfoPojo.setTradeStatus(trade_status);
          alipayOrderInfoPojo.setOutTradeNo(params.get("out_trade_no"));
          alipayOrderInfoPojo.setUpdateDate(new Date());
          // 异步标志
          alipayOrderInfoPojo.setUpdateBy(998L);
          alipayOrderInfoService.updateOrder(alipayOrderInfoPojo);
        } else {
          System.out.println("未找到支付记录或已支付过！");
        }

      }

      // 该页面可做页面美工编辑
      System.out.println("验证成功<br />");
      // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
      ServletActionContext.getResponse().getWriter().write("验证成功<br />");
      // ServletActionContext.getResponse().getWriter()
      // .write(params.toString());
      // ////////////////////////////////////////////////////////////////////////////////////////
    } else {
      // 该页面可做页面美工编辑
      System.out.println("验证失败");
      ServletActionContext.getResponse().getWriter().write("验证失败<br />");
      // ServletActionContext.getResponse().getWriter()
      // .write(params.toString());
    }

    return SUCCESS;
  }

}
