package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.mapper.TaskSettingMapper;
import com.tzmb2c.web.pojo.TaskPojo;
import com.tzmb2c.web.pojo.TaskSettingPojo;
import com.tzmb2c.web.service.TaskSettingService;

public class TaskSettingAction extends SuperAction {
  @Autowired
  private TaskSettingService taskSettingService;
  @Autowired
  private TaskSettingMapper taskSettingMapper;
  private TaskSettingPojo taskSettingPojo;
  private List<TaskSettingPojo> taskSettingPojoList;
  private Long age;
  private String taskDateStr;
  private TaskPojo taskPojo;
  private String[] tids;

  public TaskPojo getTaskPojo() {
    return taskPojo;
  }

  public void setTaskPojo(TaskPojo taskPojo) {
    this.taskPojo = taskPojo;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public TaskSettingPojo getTaskSettingPojo() {
    return taskSettingPojo;
  }

  public List<TaskSettingPojo> getTaskSettingPojoList() {
    return taskSettingPojoList;
  }

  public void setTaskSettingPojoList(List<TaskSettingPojo> taskSettingPojoList) {
    this.taskSettingPojoList = taskSettingPojoList;
  }

  public void setTaskSettingPojo(TaskSettingPojo taskSettingPojo) {
    this.taskSettingPojo = taskSettingPojo;
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

  /**
   * 1层查询全部条数
   */
  public String taskSettingListCount1() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (age != null) {
        map.put("age", age);
      }
      int i = taskSettingService.findTaskSettingCount1(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 1层查询全部内容
   */
  public String taskSettingListAll1() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (age != null) {
        map.put("age", age);
      }
      taskSettingPojoList = taskSettingService.findTaskSettingList1(map);
      JSONArray json = JSONArray.fromObject(taskSettingPojoList);
      page.setRowCount(taskSettingService.findTaskSettingCount1(map));
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 2层查询全部条数
   */
  public String taskSettingListCount2() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (taskDateStr != null) {
        map.put("taskDateStr", taskDateStr);
      }
      if (age != null) {
        map.put("age", age);
      }
      int i = taskSettingService.findTaskSettingCount2(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 2层查询全部内容
   */
  public String taskSettingListAll2() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (taskDateStr != null) {
        map.put("taskDateStr", taskDateStr);
      }
      if (age != null) {
        map.put("age", age);
      }
      taskSettingPojoList = taskSettingService.findTaskSettingList2(map);
      for (TaskSettingPojo taskSetting : taskSettingPojoList) {
        taskSetting.setTaskTypeAllMessage(taskSetting.getTaskTypeMessage() + "--"
            + taskSetting.getTaskTypeLinkMessage());
      }
      JSONArray json = JSONArray.fromObject(taskSettingPojoList);
      page.setRowCount(taskSettingService.findTaskSettingCount2(map));
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除（逻辑）
   * 
   * @return
   */
  public String delTaskSetting() throws Exception {
    try {
      taskSettingService.delTaskSetting(taskSettingPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "goTaskSetting.do?age=" + age
          + "&taskDateStr=" + taskDateStr);
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "goTaskSetting.do?age=" + age
          + "&taskDateStr=" + taskDateStr);
    }
    return null;
  }

  /**
   * 跳转1层增加日程页面
   * 
   * @return
   * @throws Exception
   */

  public String goAddTaskSettingDate() throws Exception {
    return SUCCESS;
  }

  /**
   * 1层增加日程
   * 
   * @return
   * @throws Throwable
   */
  public String addTaskSettingDate() throws Throwable {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (taskSettingPojo != null) {
        if (taskSettingPojo.getTaskDateStr() != null) {
          map.put("age", taskSettingPojo.getTaskAgeType());
          map.put("taskDateStr", taskSettingPojo.getTaskDateStr());
          int n = taskSettingMapper.findTaskSettingCountDate(map);
          if (n == 0) {
            Date taskdate = new Date();
            taskdate = StringUtil.stringToDate(taskSettingPojo.getTaskDateStr());
            taskSettingPojo.setTaskDate(taskdate);
          } else {
            FileUtil.alertMessageBySkip("该日程已在该年龄分层存在，请重新设置！", "goAddTaskSettingDate.do?age="
                + taskSettingPojo.getTaskAgeType());
            return null;
          }
        }
      }
      taskSettingPojo.setTaskId(0L);
      taskSettingService.insertTaskSetting(taskSettingPojo);
      FileUtil.alertMessageBySkip("新增成功！",
          "taskSettingList1.do?age=" + taskSettingPojo.getTaskAgeType());
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！",
          "taskSettingList1.do?age=" + taskSettingPojo.getTaskAgeType());
    }
    return null;
  }

  /**
   * 2层增加任务
   * 
   * @return
   * @throws Throwable
   */
  public String addTaskSetting() throws Throwable {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      taskSettingPojo = new TaskSettingPojo();
      if (taskPojo != null && age != null && !"".equals(taskDateStr)) {
        map.put("taskId", taskPojo.getId());
        map.put("age", age);
        map.put("taskDateStr", taskDateStr);
        int j = taskSettingService.findTaskSettingCount2(map);
        if (j > 0) {
          FileUtil.alertMessageBySkip(
              "不可在同个年龄阶层同日程中设置两个相同的任务，请重新选择！",
              "goAddTaskSetting.do?age=" + age + "&taskDateStr="
                  + taskDateStr);
          return null;
        } else {
          taskSettingPojo.setTaskId(taskPojo.getId());
          taskSettingPojo.setTaskDate(StringUtil
              .stringToDate(taskDateStr));
          taskSettingPojo.setTaskAgeType(age);
        }
      } else {
        FileUtil.alertMessageBySkip("此任务已删除或日期、年龄分层中存在错误，请重新选择！",
            "goAddTaskSetting.do?age=" + age + "&taskDateStr="
                + taskDateStr);
        return null;
      }
      taskSettingService.insertTaskSetting(taskSettingPojo);
      FileUtil.alertMessageBySkip("新增成功！", "goTaskSetting.do?age=" + age
          + "&taskDateStr=" + taskDateStr);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "goTaskSetting.do?age=" + age
          + "&taskDateStr=" + taskDateStr);
    }
    return null;
  }

  /**
   * 根据tids批量增加任务
   * 
   * @return
   */
  public String addTaskSettingAll() {
    try {
      String alarm = "";
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要增加的任务！", "goAddTaskSetting.do?age=" + age
            + "&taskDateStr=" + taskDateStr);
        return null;
      } else {
        for (String id : tids) {
          Map<String, Object> map = new HashMap<String, Object>();
          taskSettingPojo = new TaskSettingPojo();
          map.put("taskId", id);
          map.put("age", age);
          map.put("taskDateStr", taskDateStr);
          int j = taskSettingService.findTaskSettingCount2(map);
          if (j == 0) {
            taskSettingPojo.setTaskId(Long.valueOf(id));
            taskSettingPojo.setTaskDate(StringUtil.stringToDate(taskDateStr));
            taskSettingPojo.setTaskAgeType(age);
            taskSettingService.insertTaskSetting(taskSettingPojo);
          } else {
            alarm = "其中有重复添加的任务，请您在添加任务时注意是否重复添加。";
          }
        }
        FileUtil.alertMessageBySkip("批量增加成功！" + alarm, "goTaskSetting.do?age=" + age
            + "&taskDateStr=" + taskDateStr);
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量增加失败！", "goTaskSetting.do?age=" + age + "&taskDateStr="
          + taskDateStr);
    }
    return null;
  }
}
