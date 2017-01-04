package maowu.framework.utils.web.struts2;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.alipay.util.AlipayCore;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tencent.common.MD5;

public class IfcaeValidInterceptor extends AbstractInterceptor {
  private static String includecls;
  private static String key = "taozhumapintuan@corp.com";

  public String getIncludecls() {
    return includecls;
  }

  public void setIncludecls(String includecls) {
    IfcaeValidInterceptor.includecls = includecls;
  }

  /**
     * 
     */
  private static final long serialVersionUID = 1L;
  private Logger logger = Logger.getLogger(IfcaeValidInterceptor.class);

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    logger.info("进入 IfcaeFilterInterceptor");
    // APP版本访问限制
    String namespace = ServletActionContext.getActionMapping().getNamespace();
    if (StringUtils.isNotBlank(namespace) && namespace.compareToIgnoreCase("/v1.9") < 0) {
      appApiError("亲~，版本过低，请升级到最新版本！");
      return null;
    }

    ActionContext actionContext = ActionContext.getContext();
    // System.out.println(invocation.getAction().getClass().getName());
    // System.out.println(includecls);
    HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
    String agent = request.getHeader("User-Agent");

    String method = ServletActionContext.getActionMapping().getName();

    // 接口访问限制
    if (agent == null
        || agent.contains("Windows NT")
        || !(agent.contains("GroupPurchase") || agent.contains("Mozilla")
            || agent.contains("MicroMessenger") || agent.contains("PinDeHaoAndroid"))) {
      throw new ConfigurationException("");
    }

    // 浏览器可以访问的接口
    if (agent.contains("Mozilla")) {
      if (!("helpCenterDetail".equals(method) || "getShareContentWebApi".equals(method) || "getProductInfoView"
          .equals(method))) {
        throw new ConfigurationException("");
      }
    }
    if (!(agent.contains("GroupPurchase") || agent.contains("PinDeHaoAndroid") || agent
        .contains("MicroMessenger"))) {
      if (method != null && ("userlogin".equals(method) || "captcha".equals(method))) {
        throw new ConfigurationException("");
      }

    }

    if (agent != null
        && (agent.contains("GroupPurchase") || agent.contains("PinDeHaoAndroid") || agent
            .contains("MicroMessenger"))) {
      if (agent.contains("PinDeHaoAndroid")) {
        if (method != null && "captcha".equals(method)) {
          validSign(request);
        }
      } else if (method != null
          && ("userlogin".equals(method) || "captcha".equals(method) || "password".equals(method) || "reSetPassWord"
              .equals(method))) {
        validSign(request);
      }

    }
    // iOS控制接口访问的开放时间
    /*if (agent != null && agent.contains("GroupPurchase")) {
      if (method != null && "captcha".equals(method)) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 9) {
          throw new ConfigurationException("");
        }
      }
    }*/


    return invocation.invoke();

  }

  public String createParamStr(HttpServletRequest request) {
    // 获取请求参数
    Map<String, String> params = new HashMap<String, String>();

    Map requestParams = request.getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      if ("sign".equals(name)) {
        continue;
      }
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
      // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
      params.put(name, valueStr);
    }
    String prestr = AlipayCore.createLinkString(params); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

    return prestr;
  }

  /**
   * 验证sign.
   * 
   * @param request
   * @throws ConfigurationException
   */
  protected void validSign(HttpServletRequest request) throws ConfigurationException {
    String sign = request.getParameter("sign");
    if (sign == null || "".equals(sign) || sign.length() != 32) {
      throw new ConfigurationException("");
    }
    String lcsign = createParamStr(request);
    lcsign += key;
    lcsign = MD5.MD5Encode(lcsign).toUpperCase();
    lcsign = MD5.MD5Encode(lcsign).toUpperCase();
    logger.info(">>>localsign:" + lcsign);

    if (!sign.equals(lcsign)) {
      throw new ConfigurationException("");
    }
  }

  /**
   * app接口异常处理
   * 
   * @return
   */
  protected void appApiError(String errormsg) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", "");
    map.put("error_msg", errormsg);
    map.put("success", false);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
