package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserWalletLogService;

public class UserWalletLogAction extends SuperAction {
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserWalletLogService userWalletLogService;
  private UserWalletLogPojo userWalletLogPojo;
  private List<UserWalletLogPojo> userWalletLogPojoList;
  private String[] tids;

  public List<UserWalletLogPojo> getUserWalletLogPojoList() {
    return userWalletLogPojoList;
  }

  public void setUserWalletLogPojoList(List<UserWalletLogPojo> userWalletLogPojoList) {
    this.userWalletLogPojoList = userWalletLogPojoList;
  }

  public UserWalletLogPojo getUserWalletLogPojo() {
    return userWalletLogPojo;
  }

  public void setUserWalletLogPojo(UserWalletLogPojo userWalletLogPojo) {
    this.userWalletLogPojo = userWalletLogPojo;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  /**
   * 查询全部条数
   */
  public String userWalletLogCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userWalletLogPojo != null) {
        map.put("loginName", userWalletLogPojo.getLoginName());
        map.put("curBal", userWalletLogPojo.getCurBal());
        map.put("sourceName", userWalletLogPojo.getSourceName());
        map.put("orderNo", userWalletLogPojo.getOrderNo());
        map.put("type", userWalletLogPojo.getType());
        map.put("createDateStartStr", userWalletLogPojo.getCreateDateStartStr());
        map.put("createDateEndStr", userWalletLogPojo.getCreateDateEndStr());
        map.put("userId", userWalletLogPojo.getUserId());
        map.put("machineCode", userWalletLogPojo.getMachineCode());
      }
      int i = userWalletLogService.findUserWalletLogCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String userWalletLogAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userWalletLogPojo != null) {
        map.put("loginName", userWalletLogPojo.getLoginName());
        map.put("curBal", userWalletLogPojo.getCurBal());
        map.put("sourceName", userWalletLogPojo.getSourceName());
        map.put("orderNo", userWalletLogPojo.getOrderNo());
        map.put("type", userWalletLogPojo.getType());
        map.put("createDateStartStr", userWalletLogPojo.getCreateDateStartStr());
        map.put("createDateEndStr", userWalletLogPojo.getCreateDateEndStr());
        map.put("userId", userWalletLogPojo.getUserId());
        map.put("machineCode", userWalletLogPojo.getMachineCode());
      }
      userWalletLogPojoList = userWalletLogService.findUserWalletLogList(map);
      for (UserWalletLogPojo userWalletLogPojo : userWalletLogPojoList) {
        if (userWalletLogPojo.getSourceName() != null) {
          SysLoginPojo sysLoginPojo = new SysLoginPojo();
          sysLoginPojo.setLoginname(userWalletLogPojo.getSourceName());
          userWalletLogPojo.setRegisteredTime(sysLoginService.findSysLoginByLoginname(sysLoginPojo)
              .getCreateDateStr1());
        }
      }
      JSONArray json = JSONArray.fromObject(userWalletLogPojoList);
      page.setRowCount(userWalletLogPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 后台订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getWalletLogExcel() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (tids != null && !tids.equals("")) {
      map.put("tids", tids);
    }
    if (userWalletLogPojo != null) {
      map.put("loginName", userWalletLogPojo.getLoginName());
      map.put("curBal", userWalletLogPojo.getCurBal());
      map.put("sourceName", userWalletLogPojo.getSourceName());
      map.put("orderNo", userWalletLogPojo.getOrderNo());
      map.put("type", userWalletLogPojo.getType());
      map.put("createDateStartStr", userWalletLogPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userWalletLogPojo.getCreateDateEndStr());
      map.put("userId", userWalletLogPojo.getUserId());
      map.put("machineCode", userWalletLogPojo.getMachineCode());
    } else {
      if (tids != null && !tids.equals("")) {
        map.put("tids", tids);
      }
    }
    userWalletLogPojoList = userWalletLogService.findUserWalletLogList(map);
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    this.downloadFileName = "用户钱包明细记录.xls";
    fileName = this.downloadFileName;
    // filePath = "/home"+fileName;
    // 这里直接找到项目的路径，liunx和window路径不同，不能混淆在一起!!!
    filePath =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName;
    File file = new File(filePath);
    if (!file.isFile()) {
      file.createNewFile();
    }
    try {
      ots = new FileOutputStream(file);
      wwb = Workbook.createWorkbook(ots);
      WritableSheet sheet = wwb.createSheet("sheet1", 0);
      sheet.addCell(new Label(0, 0, "用户账号"));
      sheet.addCell(new Label(1, 0, "当前余额"));
      sheet.addCell(new Label(2, 0, "交易金额"));
      sheet.addCell(new Label(3, 0, "记录来源账号"));
      sheet.addCell(new Label(4, 0, "机器码"));
      sheet.addCell(new Label(5, 0, "订单号"));
      sheet.addCell(new Label(6, 0, "交易时间"));
      for (int j = 1, i = 1; j <= userWalletLogPojoList.size(); j++) {
        UserWalletLogPojo userWalletLogPojo = userWalletLogPojoList.get(j - 1);
        DecimalFormat df = new DecimalFormat("#.##");
        sheet.addCell(new Label(0, i, userWalletLogPojo.getLoginName()));
        sheet.addCell(new Label(1, i, df.format(userWalletLogPojo.getCurBal())));
        if (userWalletLogPojo.getType() == 0) {
          sheet.addCell(new Label(2, i, df.format(userWalletLogPojo.getTradeAmt())));
        } else if (userWalletLogPojo.getType() == 1) {
          sheet.addCell(new Label(2, i, "-" + df.format(userWalletLogPojo.getTradeAmt())));
        } else {
          sheet.addCell(new Label(2, i, "无变动"));
        }
        sheet.addCell(new Label(3, i, userWalletLogPojo.getSourceName()));
        sheet.addCell(new Label(4, i, userWalletLogPojo.getMachineCode()));
        sheet.addCell(new Label(5, i, userWalletLogPojo.getOrderNo()));
        sheet.addCell(new Label(6, i, userWalletLogPojo.getCreateDateStr()));
        i++;
      }
      wwb.write();
      ots.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (wwb != null) {
          wwb.close();
        }
        if (ots != null) {
          ots.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    // FileUtil.copyFile(filePath,
    // ServletActionContext.getServletContext().getRealPath("/temp/"+fileName));
    // File delfile = new File(filePath);
    // if (delfile.isFile() && delfile.exists()) {
    // delfile.delete();
    // }
    HttpServletResponse response = ServletActionContext.getResponse();
    // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
    response.setContentType("application/vnd.ms-excel");
    String fileNames = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
    response.setHeader("Content-Disposition", "attachment;fileName=" + fileNames);
    ServletOutputStream out;
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      out = response.getOutputStream();
      int i = 0;
      while ((i = fileInputStream.read()) != -1) {
        out.write(i);
      }
      fileInputStream.close();
      out.close();
      File delfile = new File(filePath);
      // 路径为文件且不为空则进行删除
      if (delfile.isFile() && delfile.exists()) {
        delfile.delete();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /**
   * 根据id删除
   * 
   * @return
   */
  /*
   * public String delUserWalletLog() throws Exception{ try {
   * userWalletLogService.delUserWalletLog(userWalletLogPojo.getId());
   * FileUtil.alertMessageBySkip("删除成功！", "userWalletLog.do"); } catch (Exception e) {
   * FileUtil.alertMessageBySkip("删除失败！", "userWalletLog.do"); } return null; }
   */
  /**
   * 根据id批量删除
   * 
   * @return
   */
  /*
   * public String delUserWalletLogAll(){ try { for (String id : tids) {
   * userWalletLogService.delUserWalletLog(Long.valueOf(id)); }
   * FileUtil.alertMessageBySkip("全部删除成功！", "userWalletLog.do"); } catch (Exception e) {
   * e.printStackTrace(); FileUtil.alertMessageBySkip("全部删除失败！", "userWalletLog.do"); } return null;
   * }
   */
  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String goUpdateUserWalletLog() throws Exception{ userWalletLogPojo =
   * userWalletLogService.findUserWalletLogById(userWalletLogPojo.getId()); return SUCCESS; }
   */
  /**
   * 编辑
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String updateUserWalletLog() throws Exception{ try {
   * userWalletLogService.updateUserWalletLog(userWalletLogPojo);
   * FileUtil.alertMessageBySkip("提交成功！", "userWalletLog.do"); } catch (Exception e) {
   * e.printStackTrace(); FileUtil.alertMessageBySkip("提交失败！", "userWalletLog.do"); } return null; }
   */
  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String goAddUserWalletLog() throws Exception{ return SUCCESS; }
   *//**
   * 提交新增
   * 
   * @return
   */
  /*
   * public String addUserWalletLog(){ try {
   * userWalletLogService.insertUserWalletLog(userWalletLogPojo);
   * FileUtil.alertMessageBySkip("新增成功！", "userWalletLog.do"); } catch (Exception e) {
   * e.printStackTrace(); FileUtil.alertMessageBySkip("新增失败！", "userWalletLog.do"); } return null; }
   * }
   */
}
