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
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.service.UserWalletService;

public class UserWalletAction extends SuperAction {
  @Autowired
  private UserWalletService userWalletService;
  private UserWalletPojo userWalletPojo;
  private List<UserWalletPojo> userWalletPojoList;
  private String[] tids;
  private Long paixu;

  public List<UserWalletPojo> getUserWalletPojoList() {
    return userWalletPojoList;
  }

  public void setUserWalletPojoList(List<UserWalletPojo> userWalletPojoList) {
    this.userWalletPojoList = userWalletPojoList;
  }

  public UserWalletPojo getUserWalletPojo() {
    return userWalletPojo;
  }

  public void setUserWalletPojo(UserWalletPojo userWalletPojo) {
    this.userWalletPojo = userWalletPojo;
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
  public String userWalletCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userWalletPojo != null) {
        map.put("loginName", userWalletPojo.getLoginName());
        map.put("userName", userWalletPojo.getUserName());
        map.put("blackFlag", userWalletPojo.getBlackFlag());
        map.put("paixu", userWalletPojo.getPaixu());
      }
      int i = userWalletService.findUserWalletCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String userWalletAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userWalletPojo != null) {
        map.put("loginName", userWalletPojo.getLoginName());
        map.put("userName", userWalletPojo.getUserName());
        map.put("blackFlag", userWalletPojo.getBlackFlag());
        map.put("paixu", userWalletPojo.getPaixu());
      }
      userWalletPojoList = userWalletService.findUserWalletList(map);
      JSONArray json = JSONArray.fromObject(userWalletPojoList);
      page.setRowCount(userWalletPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delUserWallet() throws Exception {
    try {
      userWalletService.delUserWallet(userWalletPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "userWallet.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "userWallet.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  public String delUserWalletAll() {
    try {
      for (String id : tids) {
        userWalletService.delUserWallet(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "userWallet.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "userWallet.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUserWallet() throws Exception {
    userWalletPojo = userWalletService.findUserWalletById(userWalletPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑
   * 
   * @return
   * @throws Exception
   */
  public String updateUserWallet() throws Exception {
    try {
      userWalletService.updateUserWallet(userWalletPojo);
      FileUtil.alertMessageBySkip("提交成功！", "userWallet.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "userWallet.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUserWallet() throws Exception {
    return SUCCESS;
  }

  /**
   * 提交新增
   * 
   * @return
   */
  public String addUserWallet() {
    try {
      userWalletService.insertUserWallet(userWalletPojo);
      FileUtil.alertMessageBySkip("新增成功！", "userWallet.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "userWallet.do");
    }
    return null;
  }

  /**
   * 后台订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getWalletExcel() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (tids != null && !tids.equals("")) {
      map.put("tids", tids);
    }
    if (userWalletPojo != null) {
      map.put("loginName", userWalletPojo.getLoginName());
      map.put("userName", userWalletPojo.getUserName());
      map.put("blackFlag", userWalletPojo.getBlackFlag());
      map.put("paixu", userWalletPojo.getPaixu());
    } else {
      if (tids != null && !tids.equals("")) {
        map.put("tids", tids);
      }
    }
    userWalletPojoList = userWalletService.findUserWalletList(map);
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    fileName = "用户钱包帐户余额.xls";
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
      sheet.addCell(new Label(1, 0, "用户昵称"));
      sheet.addCell(new Label(2, 0, "钱包余额"));
      sheet.addCell(new Label(3, 0, "累计总额"));
      sheet.addCell(new Label(4, 0, "嫌疑对象"));
      for (int j = 1, i = 1; j <= userWalletPojoList.size(); j++) {
        UserWalletPojo userWalletPojo = userWalletPojoList.get(j - 1);
        DecimalFormat df = new DecimalFormat("#.##");
        sheet.addCell(new Label(0, i, userWalletPojo.getLoginName()));
        sheet.addCell(new Label(1, i, userWalletPojo.getUserName()));
        sheet.addCell(new Label(2, i, df.format(userWalletPojo.getBalance())));
        sheet.addCell(new Label(3, i, df.format(userWalletPojo.getTotalBalance())));
        sheet.addCell(new Label(4, i, userWalletPojo.getBlackFlagName()));
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

  public Long getPaixu() {
    return paixu;
  }

  public void setPaixu(Long paixu) {
    this.paixu = paixu;
  }
}
