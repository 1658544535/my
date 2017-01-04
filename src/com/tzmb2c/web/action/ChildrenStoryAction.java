package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ChildrenStoryPojo;
import com.tzmb2c.web.service.ChildrenStoryService;

public class ChildrenStoryAction extends SuperAction {
  @Autowired
  private ChildrenStoryService childrenStoryService;
  private ChildrenStoryPojo childrenStoryPojo;
  private List<ChildrenStoryPojo> childrenStoryPojoList;
  private String[] tids;

  public List<ChildrenStoryPojo> getChildrenStoryPojoList() {
    return childrenStoryPojoList;
  }

  public void setChildrenStoryPojoList(List<ChildrenStoryPojo> childrenStoryPojoList) {
    this.childrenStoryPojoList = childrenStoryPojoList;
  }

  public ChildrenStoryPojo getChildrenStoryPojo() {
    return childrenStoryPojo;
  }

  public void setChildrenStoryPojo(ChildrenStoryPojo childrenStoryPojo) {
    this.childrenStoryPojo = childrenStoryPojo;
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
  public String childrenStoryListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (childrenStoryPojo != null) {
        map.put("title", childrenStoryPojo.getTitle());
        map.put("status", childrenStoryPojo.getStatus());
      }
      int i = childrenStoryService.findChildrenStoryCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String childrenStoryListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (childrenStoryPojo != null) {
        map.put("title", childrenStoryPojo.getTitle());
        map.put("status", childrenStoryPojo.getStatus());
      }
      childrenStoryPojoList = childrenStoryService.findChildrenStoryList(map);
      JSONArray json = JSONArray.fromObject(childrenStoryPojoList);
      page.setRowCount(childrenStoryService.findChildrenStoryCount(map));
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
  public String delChildrenStory() throws Exception {
    try {
      childrenStoryService.delChildrenStory(childrenStoryPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "childrenStory.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "childrenStory.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  public String delAllChildrenStory() throws Exception {
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要删除的故事！", "childrenStory.do");
        return null;
      } else {
        for (String id : tids) {
          childrenStoryService.delChildrenStory(Long.valueOf(id));
        }
        FileUtil.alertMessageBySkip("批量删除成功！", "childrenStory.do");
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("批量删除失败！", "childrenStory.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkChildrenStory() throws Exception {
    try {
      childrenStoryService.checkChildrenStory(childrenStoryPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "childrenStory.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "childrenStory.do");
    }
    return null;
  }

  /**
   * 根据id批量审核
   * 
   * @return
   */
  public String checkAllChildrenStory() throws Exception {
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要审核的故事！", "childrenStory.do");
        return null;
      } else {
        for (String id : tids) {
          childrenStoryService.checkChildrenStory(Long.valueOf(id));
        }
        FileUtil.alertMessageBySkip("批量审核成功！", "childrenStory.do");
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("批量审核失败！", "childrenStory.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckChildrenStory() throws Exception {
    try {
      childrenStoryService.uncheckChildrenStory(childrenStoryPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "childrenStory.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "childrenStory.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateChildrenStory() throws Exception {
    if (childrenStoryPojo != null) {
      childrenStoryPojo = childrenStoryService.findChildrenStoryById(childrenStoryPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Exception
   */
  public String updateChildrenStory() throws Exception {
    try {
      childrenStoryService.updateChildrenStory(childrenStoryPojo);
      FileUtil.alertMessageBySkip("提交成功！", "childrenStory.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "childrenStory.do");
    }
    return null;
  }

  /**
   * 新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddChildrenStory() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Exception
   */
  public String addChildrenStory() throws Exception {
    try {
      childrenStoryService.insertChildrenStory(childrenStoryPojo);
      FileUtil.alertMessageBySkip("提交成功！", "childrenStory.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "childrenStory.do");
    }
    return null;
  }
}
