package maowu.framework.utils.web.struts2;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tzmb2c.web.pojo.SysLoginPojo;

public class SessionInterceptor extends AbstractInterceptor {

  private Logger logger = Logger.getLogger(SessionInterceptor.class);

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    logger.info("进入 SessionInterceptor");
    ActionContext actionContext = ActionContext.getContext();
    // @SuppressWarnings("unused")
    SysLoginPojo userPojo = (SysLoginPojo) actionContext.getSession().get("user");
    actionContext.getSession().get("menuListAll");

    System.out.println(invocation.getProxy().getNamespace());

    if (userPojo == null || userPojo.getLoginname().isEmpty()) {
      logger.info("进入 SessionInterceptor,user not null.");
      // result = invocation.invoke();
      return "loginpage";
      // } else {
      // logger.info("进入 SessionInterceptor,user is null.");
      // if (actionList.contains(invocation.getProxy().getActionName()
      // + ".do")) {
      // return invocation.invoke();
      // } else {
      // return "notAuthority";
      // }

    }
    return invocation.invoke();

  }

}
