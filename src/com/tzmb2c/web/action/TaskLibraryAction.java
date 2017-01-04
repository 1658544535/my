package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.TaskPojo;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.TaskLibraryService;

/**
 * 任务库
 * @author LinJianhong
 *
 */
public class TaskLibraryAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private TaskLibraryService taskLibraryService;
  @Autowired
  private SysDictService sysDictService;

  private TaskPojo taskPojo;
  private List<TaskPojo> taskPojoList;
  private Long age;
  private String taskDateStr;
  private Long ageId;
  public TaskPojo getTaskPojo() {
    return taskPojo;
  }

  public void setTaskPojo(TaskPojo taskPojo) {
    this.taskPojo = taskPojo;
  }

  public List<TaskPojo> getTaskPojoList() {
    return taskPojoList;
  }

  public void setTaskPojoList(List<TaskPojo> taskPojoList) {
    this.taskPojoList = taskPojoList;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getTaskDateStr() {
    return taskDateStr;
  }

  public void setTaskDateStr(String taskDateStr) {
    this.taskDateStr = taskDateStr;
  }

  public Long getAgeId() {
    return ageId;
  }

  public void setAgeId(Long ageId) {
    this.ageId = ageId;
  }

  /**
   * 任务数目
   * 
   * @return
   * @throws Exception
   */
  public String taskLibraryListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isdelete", "0");
      if (taskPojo != null) {
        map.put("taskTitle", taskPojo.getTaskTitle().trim());
        map.put("taskScore", taskPojo.getTaskScore());
        map.put("ability", taskPojo.getAbility());
        map.put("taskType", taskPojo.getTaskType());
        map.put("taskTypeLink", taskPojo.getTaskTypeLink());
        map.put("taskAge", taskPojo.getTaskAge());
      }
      if (age != null) {
        map.put("taskAge", age);
      }
      int i = taskLibraryService.findTaskLibraryCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 任务列表
   * 
   * @return
   * @throws Exception
   */
  public String taskLibraryListAll() throws Exception {
    /*
     * HttpServletRequest request = ServletActionContext.getRequest();
     * request.setAttribute("ageId", 555);
     */
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      map.put("isdelete", "0");
      if (taskPojo != null) {
        map.put("taskTitle", taskPojo.getTaskTitle().trim());
        map.put("taskScore", taskPojo.getTaskScore());
        map.put("ability", taskPojo.getAbility());
        map.put("taskType", taskPojo.getTaskType());
        map.put("taskTypeLink", taskPojo.getTaskTypeLink());
        map.put("taskAge", taskPojo.getTaskAge());
        map.put("option", "desc");
      }
      if (age != null) {
        map.put("taskAge", age);
        ActionContext.getContext().getSession().put("ageId", age);
      }
      taskPojoList = taskLibraryService.findTaskLibraryList(map);
      for (TaskPojo t : taskPojoList) {
        t.setTaskTypeAllMessage(t.getTaskTypeMessage() + "--" + t.getTaskTypeLinkMessage());
      }
      JSONArray json = JSONArray.fromObject(taskPojoList);
      page.setRowCount(taskPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据ID删除任务
   * @return
   * @throws Exception
   */
  public String delTaskLibrary() throws Exception {
    try {
      taskLibraryService.delTaskLibrary(taskPojo.getId());

      FileUtil.alertMessageBySkip("删除成功！", "taskLibraryList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "taskLibraryList.do");
    }
    return null;
  }

  /**
   * 跳转至编辑任务页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateTaskLibrary() throws Exception {
    try {
      taskPojo = taskLibraryService.findTaskLibraryById(taskPojo.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑任务
   * 
   * @return
   * @throws Throwable
   */
  public String updateTaskLibrary() throws Throwable {
    try {

      if (taskPojo != null) {
        taskLibraryService.updateTaskLibrary(taskPojo);
        FileUtil.alertMessageBySkip("提交成功！", "taskLibraryList.do");
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "taskLibraryList.do");
    }
    return null;
  }

  /**
   * 跳转至新增任务页面
   * 
   * @return
   */
  public String goAddTaskLibraryList() {
    return SUCCESS;
  }

  /**
   * 提交新增任务
   * @return
   */
  public String addTaskLibraryList() {

    try {
      if (taskPojo != null) {
        taskLibraryService.insertTaskLibrary(taskPojo);
        FileUtil.alertMessageBySkip("新增成功！", "taskLibraryList.do");
      }


    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "taskLibraryList.do");
    }
    return null;
  }


}
