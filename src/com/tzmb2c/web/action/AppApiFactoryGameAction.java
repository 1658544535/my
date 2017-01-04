package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.GameFactoryToyPojo;
import com.tzmb2c.web.pojo.GameToyPartsPojo;
import com.tzmb2c.web.pojo.UserPartsLogPojo;
import com.tzmb2c.web.pojo.UserToyLogPojo;
import com.tzmb2c.web.service.GameFactoryToyService;
import com.tzmb2c.web.service.GameToyPartsService;
import com.tzmb2c.web.service.UserPartsLogService;
import com.tzmb2c.web.service.UserToyLogService;

public class AppApiFactoryGameAction extends SuperAction {

  @Autowired
  private GameFactoryToyService gameFactoryToyService;
  @Autowired
  private GameToyPartsService gameToyPartsService;
  @Autowired
  private UserToyLogService userToyLogService;
  @Autowired
  private UserPartsLogService userPartsLogService;
  private UserToyLogPojo userToyLogPojo;
  private GameToyPartsPojo gameToyPartsPojo, gameToyPartsPojo1;
  private UserPartsLogPojo userPartsLogPojo;
  private List<UserToyLogPojo> userToyLogPojos;
  private static final long serialVersionUID = 1L;
  private Long userId;// 用户ID
  private Long logId;// 记录ID
  private Long toyId;// 玩具ID
  private String address;// 送货地址
  private String telphone;// 用户联系方式
  private String username;// 收件人
  private Integer status;// 状态

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getLogId() {
    return logId;
  }

  public void setLogId(Long logId) {
    this.logId = logId;
  }

  public String getTelphone() {
    return telphone;
  }

  public void setTelphone(String telphone) {
    this.telphone = telphone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getToyId() {
    return toyId;
  }

  public void setToyId(Long toyId) {
    this.toyId = toyId;
  }

  public List<UserToyLogPojo> getUserToyLogPojos() {
    return userToyLogPojos;
  }

  public void setUserToyLogPojos(List<UserToyLogPojo> userToyLogPojos) {
    this.userToyLogPojos = userToyLogPojos;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public GameToyPartsPojo getGameToyPartsPojo() {
    return gameToyPartsPojo;
  }

  public void setGameToyPartsPojo(GameToyPartsPojo gameToyPartsPojo) {
    this.gameToyPartsPojo = gameToyPartsPojo;
  }


  public UserPartsLogPojo getUserPartsLogPojo() {
    return userPartsLogPojo;
  }

  public void setUserPartsLogPojo(UserPartsLogPojo userPartsLogPojo) {
    this.userPartsLogPojo = userPartsLogPojo;
  }

  public UserToyLogPojo getUserToyLogPojo() {
    return userToyLogPojo;
  }

  public void setUserToyLogPojo(UserToyLogPojo userToyLogPojo) {
    this.userToyLogPojo = userToyLogPojo;
  }

  /**
   * 配件生成玩具
   * 
   */
  public String toyPartsCreateToy() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    if (userId == null || userId == 0) {
      msg = "请先登录！~";// 用户ID不能为空哦！~
    } else {
      item.put("paixu", 1);
      item.put("status", 2);
      item.put("userId", userId);
      // 查出已生成状态的不同的部件（若部件相同，则取的是先前生成的部件）
      List<UserPartsLogPojo> userPartsLogPojos = userPartsLogService.findUserPartsLogList(item);
      List<Long> a = new ArrayList<Long>();
      if (userPartsLogPojos.size() >= 8) {
        long temp = 0;
        int count = 0;
        // 找出相同toyId的八个部件并生成对应的玩具
        for (UserPartsLogPojo upl : userPartsLogPojos) {
          gameToyPartsPojo = gameToyPartsService.findGameToyPartsById(upl.getPartsId());
          temp = gameToyPartsPojo.getToyId();
          for (UserPartsLogPojo upl2 : userPartsLogPojos) {
            gameToyPartsPojo1 = gameToyPartsService.findGameToyPartsById(upl2.getPartsId());
            if (gameToyPartsPojo1 != null && temp == gameToyPartsPojo1.getToyId()) {
              count++;
              a.add(upl2.getId());
            }
          }
          if (count == 8) {
            for (Long id : a) {
              UserPartsLogPojo userPartsLogPojo1 = new UserPartsLogPojo();
              userPartsLogPojo1.setId(id);
              userPartsLogPojo1.setStatus(3l);
              userPartsLogService.updateUserPartsLog(userPartsLogPojo1);
            }
            UserToyLogPojo userToyLogPojo = new UserToyLogPojo();
            userToyLogPojo.setUserId(userId);
            userToyLogPojo.setToyId(gameToyPartsPojo.getToyId());
            userToyLogService.addUserToyLog(userToyLogPojo);
            msg = "合成成功";
            break;
          }
          a.clear();
          count = 0;
        }
      } else {
        msg = "未满足条件~";
      }
      success = true;
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;

  }


  /**
   * 
   * 获取用户玩具信息
   * 
   * @return
   * @throw
   * @return String
   * @throws SQLException
   */
  public String getToyDetails() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (userId == null || userId == 0) {
      msg = "请先登录！~";// 用户ID不能为空哦！~
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("userId", userId);
      List<UserToyLogPojo> userToyLogPojos = userToyLogService.findAll(option);
      if (userToyLogPojos.size() >= 0) {
        for (UserToyLogPojo utl : userToyLogPojos) {
          item = new HashMap<String, Object>();
          item.put("toyId", utl.getToyId());
          item.put("toyName", utl.getToyName());
          item.put("toyImage",
              ConstParam.URL + "/upfiles/gameFactoryToy" + File.separator + utl.getToyImages());
          item.put("toyCreateDate", utl.getCreateDateStr());// 玩具合成日期
          item.put("receiver", utl.getUsername());// 收件人姓名
          item.put("address", utl.getAddress());// 送货地址
          item.put("telphone", utl.getTelphone());// 用户联系方式
          item.put("expressNo", utl.getExpressNo());// 快递单号
          item.put("expressName", utl.getExpressName());// 快递名称
          result.add(item);
        }
      } else {
        // 查询不到用户玩具记录
      }
      success = true;
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 获取用户玩具配件详情信息
   * 
   * @return
   * @throw
   * @return String
   * @throws SQLException
   */
  public String getToyPartsDetails() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (userId == null || userId == 0) {
      msg = "请先登录！~";// 用户ID不能为空哦！~
    } else if (status == null) {
      msg = "状态不能为空哦！~";// 状态（0-未生成、1-生成中、2-已生成、3-已合成）
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", status);
      option.put("userId", userId);
      List<UserPartsLogPojo> userPartsLogPojos = userPartsLogService.findUserPartsLogList(option);
      if (userPartsLogPojos.size() >= 0) {
        for (UserPartsLogPojo upl : userPartsLogPojos) {
          item = new HashMap<String, Object>();
          item.put("partsId", upl.getPartsId() == null ? "" : upl.getPartsId());
          item.put("partsName", upl.getPartsName() == null ? "" : upl.getPartsName());
          item.put("partsImage",
              ConstParam.URL + "/upfiles/gameToyParts" + File.separator + upl.getPartsImages());
          item.put("createBeginDate",
              upl.getCreateBeginDateStr() == null ? "" : upl.getCreateBeginDateStr());
          item.put("createEndDate",
              upl.getCreateEndDateStr() == null ? "" : upl.getCreateEndDateStr());
          item.put("partsCreateDate", upl.getCreateDateStr());
          // 生成所需时间（以分钟为单位）
          item.put("partsCreateTime", upl.getCreateTime());
          item.put("toyId", upl.getToyId() == null ? "" : upl.getToyId());
          item.put("toyName", upl.getToyName() == null ? "" : upl.getToyName());
          item.put("toyImage",
              ConstParam.URL + "/upfiles/gameFactoryToy" + File.separator + upl.getToyImages());
          result.add(item);
        }
      } else {
        // 查询不到用户玩具配件记录
      }
      success = true;
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 用户抽箱
   * 
   * @return
   * @throw
   * @return String
   * @throws SQLException
   */
  public synchronized String userSmokeBox() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result2 = new ArrayList<Map<String, Object>>();
    Map<String, Object> item2 = new HashMap<String, Object>();

    if (userId == null || userId == 0) {
      msg = "请先登录！~";// 用户ID不能为空哦！~
    } else {
      // 查询玩具记录
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("status", 1);
      List<GameFactoryToyPojo> gameFactoryToyPojos =
          gameFactoryToyService.findGameFactoryToyList(option);
      if (gameFactoryToyPojos.size() != 0) {
        int probabilitys = 0;
        int probability2 = 0;
        for (GameFactoryToyPojo gft : gameFactoryToyPojos) {
          // 查询玩具配件记录
          option.clear();
          option.put("paixu", 1);
          option.put("status", 1);
          option.put("toyId", gft.getId());
          List<GameToyPartsPojo> gameToyPartsPojos =
              gameToyPartsService.findGameToyPartsList(option);
          if (gameToyPartsPojos.size() != 0) {
            for (GameToyPartsPojo gtf : gameToyPartsPojos) {
              item = new HashMap<String, Object>();
              item.put("partsName", gtf.getName() == null ? "" : gtf.getName());
              item.put("partsImage", ConstParam.URL + "/upfiles/gameToyParts" + File.separator
                  + gtf.getImages());
              // item.put("partsCreateDate", gtf.getCreateDateStr());
              // 生成所需时间（以分钟为单位）
              item.put("partsCreateTime", gtf.getCreateTime());
              item.put("partsId", gtf.getId() == null ? "" : gtf.getId());
              item.put("toyId", gtf.getToyId() == null ? "" : gtf.getToyId());
              item.put("toyName", gtf.getName() == null ? "" : gtf.getToyName());
              item.put("toyImage", ConstParam.URL + "/upfiles/gameFactoryToy" + File.separator
                  + gtf.getToyImages());
              item.put("probability", gtf.getProbability());
              result.add(item);
              probabilitys = probabilitys + gtf.getProbability();
            }
          } else {
            // 查询不到玩具配件记录
          }
        }

        Random rd = new Random();
        int num = rd.nextInt(3) + 1;
        for (int i = 0; i < num; i++) {
          int probability = rd.nextInt(probabilitys) + 1;
          for (Map<String, Object> m : result) {
            probability2 = probability2 + (int) m.get("probability");
            if (probability2 >= probability) {
              item2 = new HashMap<String, Object>();
              item2.put("partsId", m.get("partsId"));
              item2.put("partsName", m.get("partsName"));
              item2.put("partsImage", m.get("partsImage"));
              item2.put("partsCreateDate", StringUtil.dateString(new Date()));
              // 生成所需时间（以分钟为单位）
              item2.put("partsCreateTime", m.get("partsCreateTime"));
              item2.put("toyId", m.get("toyId"));
              item2.put("toyName", m.get("toyName"));
              item2.put("toyImage", m.get("toyImage"));
              result2.add(item2);

              // System.out.println(probability + "," + probabilitys2 + "," + m.get("partsId"));
              // 添加用户玩具配件记录
              UserPartsLogPojo userPartsLogPojo = new UserPartsLogPojo();
              userPartsLogPojo.setUserId(userId);
              userPartsLogPojo.setToyId((Long) m.get("toyId"));
              userPartsLogPojo.setPartsId((Long) m.get("partsId"));
              userPartsLogPojo.setStatus(0l);
              userPartsLogService.insertUserPartsLog(userPartsLogPojo);
              probability2 = 0;
              break;
            }
          }
        }
      } else {
        // 查询不到玩具记录
      }
      success = true;
    }

    map.put("result", result2);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 用户填写送货地址以及联系方式
   * 
   * @return
   * @throw
   * @return String
   * @throws SQLException
   */
  public String toyGameShippingAddress() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();
    if (userId == null || userId == 0) {
      msg = "请先登录！~";// 用户ID不能为空哦！~
    } else if (logId == null) {
      msg = "玩具记录ID不能为空哦~";
    } else if (address == null || "".equals(address)) {
      msg = "送货地址不能为空哦~";
    } else if (telphone == null || "".equals(telphone)) {
      msg = "用户联系方式不能为空哦~";
    } else if (username == null || "".equals(username)) {
      msg = "收件人不能为空哦~";
    } else {
      UserToyLogPojo userToyLogPojo = userToyLogService.findUserToyLogById(logId);
      if (!userId.equals(userToyLogPojo.getUserId())) {
        msg = "用户ID不符！";
      } else if (userToyLogPojo.getAddress() != null && !"".equals(userToyLogPojo.getAddress())
          && userToyLogPojo.getTelphone() != null && !"".equals(userToyLogPojo.getTelphone())) {
        msg = "此奖品已经填写好送货地址和联系方式，不能修改哦亲~";
      } else {
        UserToyLogPojo userToyLogPojos = new UserToyLogPojo();
        userToyLogPojos.setId(logId);
        userToyLogPojos.setAddress(address);
        userToyLogPojos.setTelphone(telphone);
        userToyLogPojos.setUsername(username);
        userToyLogService.updateUserToyLog(userToyLogPojos);
        success = true;
      }
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
