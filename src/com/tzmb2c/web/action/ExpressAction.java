package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.SysDictService;

/**
 * @author EricChen
 */

public class ExpressAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private SysDictService sysDictService;
  private String type;
  private String postid;
  private String result;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPostid() {
    return postid;
  }

  public void setPostid(String postid) {
    this.postid = postid;
  }

  public String goExpressSearch() throws Exception {
    ActionContext ac = ActionContext.getContext();
    List<SysDictPojo> sysDicPojoList = sysDictService.getSysDictListByType("logistics_type");
    ac.put("sysDicPojoList", sysDicPojoList);
    return SUCCESS;
  }

  public String express() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List list = new ArrayList();
    String url = "http://www.kuaidi100.com/query?type=" + type + "&postid=" + postid;
    String str = loadJson(url);
    JSONObject jsonobject = JSONObject.fromObject(str);
    if (!jsonobject.get("status").equals("400")) {
      map.put("expressType", jsonobject.getString("com"));
      map.put("expressNumber", jsonobject.getString("nu"));
      JSONArray jsonarray = jsonobject.getJSONArray("data");
      for (int i = 0; i < jsonarray.size(); i++) {
        map1 = new HashMap<String, Object>();
        map1.put("content", jsonarray.getJSONObject(i).get("context"));
        list.add(map1);
      }
      map.put("data", list);
    } else {
      map.put("result", 1);
      map.put("message", "没有物流信息");
    }
    JSONObject json = JSONObject.fromObject(map);
    ActionContext actionContext = ActionContext.getContext();
    actionContext.put("result", json.toString());
    return SUCCESS;
  }

  public String expressSearch() throws Exception {
    String url =
        "http://api.kuaidi.com/openapi.html?id=481ea0389df9f6b101f7fd8af272fbef&com=" + type
            + "&nu=" + postid;
    String str = loadJson(url);
    JSONObject jsonobject = JSONObject.fromObject(str);
    this.result = jsonobject.toString();
    return SUCCESS;
  }

  // 将地址接收到的json转成str
  public static String loadJson(String url) {
    StringBuilder json = new StringBuilder();
    try {
      URL urlObject = new URL(url);
      URLConnection uc = urlObject.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
      String inputLine = null;
      while ((inputLine = in.readLine()) != null) {
        json.append(inputLine);
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json.toString();
  }
}
