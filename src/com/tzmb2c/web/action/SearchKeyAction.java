package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.dao.SearchKeyDao;
import com.tzmb2c.web.pojo.SearchKeyPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SearchKeyService;
import com.tzmb2c.web.service.SysDictService;

public class SearchKeyAction extends SuperAction {

  @Autowired
  private SearchKeyService searchKeyService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SearchKeyDao searchKeyDao;
  private SearchKeyPojo searchKey;
  private String result;
  private String[] tids;
  private String os;


  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public SearchKeyPojo getSearchKey() {
    return searchKey;
  }

  public void setSearchKey(SearchKeyPojo searchKey) {
    this.searchKey = searchKey;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getSearchKeyCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(searchKeyService.searchKeyAllCount(searchKey));
    ActionContext ac = ActionContext.getContext();
    ac.put("type", sysDictService.getSysDictListByType("search_key_type"));
    return SUCCESS;
  }

  public String searchKeyAllList() {
    JSONArray json = JSONArray.fromObject(searchKeyService.searchKeyAllList(searchKey, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String goFindSearchKey() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("searchKeyPojo", searchKeyService.getfindByIdSearchKey(searchKey.getId()));
    ac.put("type", sysDictService.getSysDictListByType("search_key_type"));
    return SUCCESS;
  }

  public String updateSearchKey() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      searchKey.preUpdate(loginPojo);
    }
    searchKeyService.updateSearchKey(searchKey);
    FileUtil.alertMessageBySkip("修改成功！", "searchKey.do");

    return null;
  }

  public String searchKeyDeleteId() {
    if (tids != null) {
      searchKeyService.searchKeyDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "searchKey.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "searchKey.do");
    }

    return null;
  }

  public String deleSearchKey() throws SQLException {
    try {
      searchKeyService.delSearchKey(searchKey.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  // //////////////////////////////////////////////分割线///////////////////////////////////////
  // 热门排行记录
  public String getSearchKeyCountHot() throws Exception {
    if (page == null) {
      page = new Pager();
    }

    Map<String, Object> map = new HashMap<String, Object>();
    if (searchKey != null) {
      map.put("keyword", searchKey.getKeyword());
    }
    if (os != null && !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    List<SearchKeyPojo> list = searchKeyDao.searchKeyAllListHot(map);
    page.setRowCount(list.size());
    return SUCCESS;
  }

  public String searchKeyAllListHot() {
    JSONArray json =
        JSONArray.fromObject(searchKeyService.searchKeyAllListHot(searchKey, page, os));
    page.setResult(json.toString());
    return SUCCESS;
  }

}
