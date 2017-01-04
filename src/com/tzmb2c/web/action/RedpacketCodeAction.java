package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.RedPacketCodePojo;
import com.tzmb2c.web.service.RedpacketCodeService;

public class RedpacketCodeAction extends SuperAction {

  @Autowired
  private RedpacketCodeService redpacketCodeService;
  private RedPacketCodePojo redPacketCodePojo;
  private List<RedPacketCodePojo> redPacketCodePojos;
  private Integer type;
  private String redpacketCodeNum;

  /**
   * 红包邀请码记录
   * 
   * @return
   * @throws Exception
   */
  public String redpacketCodeListCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (redPacketCodePojo != null) {
      map.put("userName", redPacketCodePojo.getUserName().trim());
      map.put("code", redPacketCodePojo.getCode().trim());
      if (redPacketCodePojo.getCreateTimeStr().length() >= 10) {
        Date c = sdf.parse(redPacketCodePojo.getCreateTimeStr() + " 00:00:00");
        redPacketCodePojo.setCreateTime(c.getTime() / 1000);
        map.put("createTime", redPacketCodePojo.getCreateTime());
      }
      map.put("amout", redPacketCodePojo.getAmout().trim());
      map.put("outTradeId", redPacketCodePojo.getOutTradeId().trim());
      map.put("status", redPacketCodePojo.getStatus());
    }
    page.setRowCount(redpacketCodeService.redpacketCodeList(map).size());
    return SUCCESS;
  }

  /**
   * 红包邀请码记录列表
   * 
   * @return
   * @throws ParseException
   * @throws SQLException
   */
  public String redpacketCodeListAll() throws ParseException, SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (redPacketCodePojo != null) {
      map.put("userName", redPacketCodePojo.getUserName().trim());
      map.put("code", redPacketCodePojo.getCode().trim());
      if (redPacketCodePojo.getCreateTimeStr().length() >= 10) {
        Date c = sdf.parse(redPacketCodePojo.getCreateTimeStr() + " 00:00:00");
        redPacketCodePojo.setCreateTime(c.getTime() / 1000);
        map.put("createTime", redPacketCodePojo.getCreateTime());
      }
      map.put("amout", redPacketCodePojo.getAmout().trim());
      map.put("outTradeId", redPacketCodePojo.getOutTradeId().trim());
      map.put("status", redPacketCodePojo.getStatus());
    }
    redPacketCodePojos = redpacketCodeService.redpacketCodeList(map);
    if (redPacketCodePojos != null) {
      for (RedPacketCodePojo r : redPacketCodePojos) {
        long time = r.getCreateTime();
        Date c = new Date(time * 1000);
        r.setCreateTimeStr(sdf.format(c));
        // if (r.getCode() != null) {
        // r.setCode(String.valueOf((Integer.parseInt(r.getCode())/100)));
        // }
      }
    }
    JSONArray json = JSONArray.fromObject(redPacketCodePojos);
    page.setRowCount(redPacketCodePojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  public RedPacketCodePojo getRedPacketCodePojo() {
    return redPacketCodePojo;
  }

  public void setRedPacketCodePojo(RedPacketCodePojo redPacketCodePojo) {
    this.redPacketCodePojo = redPacketCodePojo;
  }

  public List<RedPacketCodePojo> getRedPacketCodePojos() {
    return redPacketCodePojos;
  }

  public void setRedPacketCodePojos(List<RedPacketCodePojo> redPacketCodePojos) {
    this.redPacketCodePojos = redPacketCodePojos;
  }

  /**
   * 新增红包邀请码
   * 
   * @return
   */
  public String addRedpacketCode() {
    /*
     * redPacketCodePojo = new RedPacketCodePojo(); String code = ""; String str1 =
     * "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; String str2 = "0123456789"; Random r = new Random(); for (int i
     * = 0; i < 6; i++) { int i1 = r.nextInt(25); code += str1.substring(i1, i1 + 1); } for (int i =
     * 0; i < 4; i++) { int i2 = r.nextInt(9); code += str2.substring(i2, i2 + 1); }
     * redPacketCodePojo.setCode(code);
     */
    return SUCCESS;
  }

  /**
   * 新增红包邀请码提交
   * 
   * @return
   */
  public String addRedpacketCodeOk() {
    try {
      if (redpacketCodeNum != null && Integer.valueOf(redpacketCodeNum) > 0) {
        // redPacketCodePojo.setCreateTime((new Date().getTime()/1000));
        for (int i = 0; i < Integer.valueOf(redpacketCodeNum); i++) {
          String code = "";
          String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
          String str2 = "0123456789";
          Random r = new Random();
          for (int j = 0; j < 6; j++) {
            int i1 = r.nextInt(25);
            code += str1.substring(i1, i1 + 1);
          }
          for (int k = 0; k < 4; k++) {
            int i2 = r.nextInt(9);
            code += str2.substring(i2, i2 + 1);
          }
          redPacketCodePojo.setCode(code);

          redpacketCodeService.AddRedpacketCode(redPacketCodePojo);
        }
        FileUtil.alertMessageBySkip("新增成功！", "redpacketCodeList.do");
      } else {
        FileUtil.alertMessageBySkip("红包个数必须大于0！", "addRedpacketCode.do?type=0");
      }
    } catch (SQLException e) {

      FileUtil.alertMessageBySkip("新增失败！", "redpacketCodeList.do");
    }
    return null;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getRedpacketCodeNum() {
    return redpacketCodeNum;
  }

  public void setRedpacketCodeNum(String redpacketCodeNum) {
    this.redpacketCodeNum = redpacketCodeNum;
  }
}
