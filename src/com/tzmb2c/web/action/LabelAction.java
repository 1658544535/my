package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.service.LabelService;

public class LabelAction extends SuperAction {
  @Autowired
  private LabelService labelService;

  private LabelPojo labelPojo;
  private List<LabelPojo> labelPojoList;
  private Long all;


  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }

  public LabelPojo getLabelPojo() {
    return labelPojo;
  }

  public List<LabelPojo> getLabelPojoList() {
    return labelPojoList;
  }

  public void setLabelPojoList(List<LabelPojo> labelPojoList) {
    this.labelPojoList = labelPojoList;
  }

  public void setLabelPojo(LabelPojo labelPojo) {
    this.labelPojo = labelPojo;
  }

  /**
   * 查询全部条数
   */
  public String labelListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (labelPojo != null) {
        map.put("name", labelPojo.getName());
      }
      int i = labelService.findLabelCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String labelListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      if (all == null) {
        map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      }
      if (labelPojo != null) {
        map.put("name", labelPojo.getName());
      }
      labelPojoList = labelService.findLabelList(map);
      JSONArray json = JSONArray.fromObject(labelPojoList);
      // page.setRowCount(labelService.findLabelCount(map));
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
  public String delLabel() throws Exception {
    try {
      labelService.delLabel(labelPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "labelList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "labelList.do");
    }
    return null;
  }

  /**
   * 跳转编辑标签页面
   * 
   * @return
   * @throws Throwable
   */
  public String goUpdateLabel() throws Exception {
    if (labelPojo != null) {
      labelPojo = labelService.findLabelById(labelPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 编辑标签
   * 
   * @return
   * @throws Throwable
   */
  public String updateLabel() throws Throwable {
    try {
      if (!"".equals(labelPojo.getName())) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("names", labelPojo.getName());
        int n = labelService.findLabelCount(map);
        if (n != 0) {
          FileUtil.alertMessageBySkip("标签名重复，请重新赋值！",
              "goUpdateLabel.do?labelPojo.id="
                  + labelPojo.getId());
          return null;
        }
      }
      labelService.updateLabel(labelPojo);
      FileUtil.alertMessageBySkip("编辑成功！", "labelList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "labelList.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */

  public String goAddLabel() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增标签
   * 
   * @return
   * @throws Throwable
   */
  public String addLabel() throws Throwable {
    try {
      if (!"".equals(labelPojo.getName())) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("names", labelPojo.getName());
        int n = labelService.findLabelCount(map);
        if (n != 0) {
          FileUtil.alertMessageBySkip("标签名重复，请重新赋值！", "goAddLabel.do");
          return null;
        }
      }
      labelService.insertLabel(labelPojo);
      FileUtil.alertMessageBySkip("新增成功！", "labelList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "labelList.do");
    }
    return null;
  }

  /*  *//**
   * 根据id审核
   * 
   * @return
   */
  /*
   * public String checkLabel() throws Exception { try { labelService.checkLabel(labelPojo.getId());
   * FileUtil.alertMessageBySkip("审核成功！", "labelList.do"); } catch (Exception e) {
   * FileUtil.alertMessageBySkip("审核失败！", "labelList.do"); } return null; }
   *//**
   * 根据id取审
   * 
   * @return
   */
  /*
   * public String uncheckLabel() throws Exception { try {
   * labelService.uncheckLabel(labelPojo.getId()); FileUtil.alertMessageBySkip("取审成功！",
   * "labelList.do"); } catch (Exception e) { FileUtil.alertMessageBySkip("取审失败！", "labelList.do");
   * } return null; }
   */
}
