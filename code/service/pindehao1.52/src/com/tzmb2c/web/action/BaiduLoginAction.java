package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.service.BaiduLoginService;

public class BaiduLoginAction extends SuperAction {
  @Autowired
  private BaiduLoginService baiduLoginService;
  private BaiduLoginPojo baiduLoginPojo;
  private List<BaiduLoginPojo> baiduLoginPojoList;

  public List<BaiduLoginPojo> getBaiduLoginList() {
    return baiduLoginPojoList;
  }

  public void setBaiduLoginList(List<BaiduLoginPojo> baiduLoginPojoList) {
    this.baiduLoginPojoList = baiduLoginPojoList;
  }

  public BaiduLoginPojo getBaiduLoginPojo() {
    return baiduLoginPojo;
  }

  public void setBaiduLoginPojo(BaiduLoginPojo baiduLoginPojo) {
    this.baiduLoginPojo = baiduLoginPojo;
  }

  /**
   * 查询全部条数
   */
  public String baiduLoginListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (baiduLoginPojo != null) {
        map.put("loginTime", baiduLoginPojo.getLoginTime());
        map.put("loginName", baiduLoginPojo.getLoginName());
        map.put("baiduId", baiduLoginPojo.getBaiduId());
        map.put("loginTimeStartStr", baiduLoginPojo.getLoginTimeStartStr());
        map.put("loginTimeEndStr", baiduLoginPojo.getLoginTimeEndStr());
        map.put("type", baiduLoginPojo.getType());
      }
      int i = baiduLoginService.findBaiduLoginCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String baiduLoginListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (baiduLoginPojo != null) {
        map.put("loginTime", baiduLoginPojo.getLoginTime());
        map.put("loginName", baiduLoginPojo.getLoginName());
        map.put("baiduId", baiduLoginPojo.getBaiduId());
        map.put("loginTimeStartStr", baiduLoginPojo.getLoginTimeStartStr());
        map.put("loginTimeEndStr", baiduLoginPojo.getLoginTimeEndStr());
        map.put("type", baiduLoginPojo.getType());
      }
      baiduLoginPojoList = baiduLoginService.findBaiduLoginList(map);
      JSONArray json = JSONArray.fromObject(baiduLoginPojoList);
      page.setRowCount(baiduLoginPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 根据id删除百度用户浏览记录
   * 
   * @return
   */
  public String delBaiduLogin() throws Exception {
    try {
      baiduLoginService.delBaiduLogin(baiduLoginPojo);
      FileUtil.alertMessageBySkip("删除成功！", "baiduLoginList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "baiduLoginList.do");
    }
    return null;
  }

  /**
   * 编辑百度用户浏览记录页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateBaiduLogin() throws Exception {
    baiduLoginPojo = baiduLoginService.findBaiduLoginById(baiduLoginPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑百度用户浏览记录
   * 
   * @return
   * @throws Exception
   */
  public String updateBaiduLogin() throws Exception {
    try {
      baiduLoginService.updateBaiduLogin(baiduLoginPojo);
      FileUtil.alertMessageBySkip("提交成功！", "baiduLoginList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "baiduLoginList.do");
    }
    return null;
  }

  /**
   * 提交编辑的用户积分
   * 
   * @return
   */
  public String updateBaiduLoginOk() {
    try {
      baiduLoginService.updateBaiduLogin(baiduLoginPojo);
      FileUtil.alertMessageBySkip("修改成功！", "baiduLoginList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改失败！", "baiduLoginList.do");
    }
    return null;
  }
}
