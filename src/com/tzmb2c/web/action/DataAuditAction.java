package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.DataAuditPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysRolePojo;
import com.tzmb2c.web.service.DataAuditService;
import com.tzmb2c.web.service.SysRoleService;

/**
 * 
 * 视频统计相关操作
 * 
 * @author Lin
 */
public class DataAuditAction extends SuperAction {
  
  /*
   * POJO声明
   */
  private DataAuditPojo dataAuditPojo;
  
  /*
   * 相关List声明
   */
  private List<DataAuditPojo> dataAuditPojoList;
  private List<SysRolePojo> sysRolePojoList;
  
  /*
   * 相关Service声明
   */
  @Autowired
  private DataAuditService dataAuditService;
  @Autowired
  private SysRoleService sysRoleService;
  
  
  /*
   * 属性get/set方法
   */
  public DataAuditPojo getDataAuditPojo() {
    return dataAuditPojo;
  }

  public void setDataAuditPojo(DataAuditPojo dataAuditPojo) {
    this.dataAuditPojo = dataAuditPojo;
  }
  
  public List<DataAuditPojo> getDataAuditPojoList() {
    return dataAuditPojoList;
  }

  public void setDataAuditPojoList(List<DataAuditPojo> dataAuditPojoList) {
    this.dataAuditPojoList = dataAuditPojoList;
  }

  public List<SysRolePojo> getSysRolePojoList() {
    return sysRolePojoList;
  }

  public void setSysRolePojoList(List<SysRolePojo> sysRolePojoList) {
    this.sysRolePojoList = sysRolePojoList;
  }

  /**
   * 视频审核结果日志Count
   * 当无搜索条件时dataAuditPojo == null，直接查询视频记录
   * 当有搜索条件是dataAuditPojo ！= null，根据搜索条件查询视频记录 
   * 搜索条件“操作类型”解释：根据前端传递过来的OperationType的值进行判断。“del”值时判定查询删除操作。
   * @return
   * @throws Exception
   */
  public String dataAuditListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //如果是del时，查询视频被删除的记录，否则查询视频状态记录
        if("del".equals(dataAuditPojo.getOperationType())){
          map.put("isDelete","1");
          map.put("operationType", null);
        }else{
          map.put("operationType", dataAuditPojo.getOperationType());
        }
        //获取视频标题，去空格
        map.put("videoTitle", dataAuditPojo.getVideoTitle().trim());
        //获取操作用户id（保留字段，预防后期需要修改前端审核人下拉选择操作）
        map.put("operationUserId", dataAuditPojo.getOperationUserId());
        //获取视频URL，去空格
        map.put("url", dataAuditPojo.getUrl().trim());
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
        //获取审核人昵称，去空格
        map.put("operationUserName", dataAuditPojo.getOperationUserName().trim());
      }
      int i = dataAuditService.dataAuditCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 视频审核结果日志List
   * 当无搜索条件时dataAuditPojo == null，直接查询视频记录
   * 当有搜索条件是dataAuditPojo ！= null，根据搜索条件查询视频记录 
   * 搜索条件“操作类型”解释：根据前端传递过来的OperationType的值进行判断。“del”值时判定查询删除操作。
   * @return
   * @throws Exception
   */
  public String dataAuditListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      new SysDictPojo();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (dataAuditPojo != null) {
      //获取前端传递的操作类型
        //如果是del时，查询视频被删除的记录，否则查询视频状态记录
        if("del".equals(dataAuditPojo.getOperationType())){
          map.put("isDelete","1");
        }else{
          map.put("operationType", dataAuditPojo.getOperationType());
        }
        //获取视频标题，去空格
        map.put("videoTitle", dataAuditPojo.getVideoTitle().trim());
        //获取操作用户id（保留字段，预防后期需要修改前端审核人下拉选择操作）
        map.put("operationUserId", dataAuditPojo.getOperationUserId());
        //获取视频URL，去空格
        map.put("url", dataAuditPojo.getUrl().trim());
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
        //获取审核人昵称，去空格
        map.put("operationUserName", dataAuditPojo.getOperationUserName().trim());
      }
      //查询列表
      dataAuditPojoList = dataAuditService.dataAuditList(map);

      
      JSONArray json = JSONArray.fromObject(dataAuditPojoList);
      page.setRowCount(dataAuditPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 审核数据总计Count
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditTotalListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
      }
      int i = dataAuditService.dataAuditTotalCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 审核数据总计List
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditTotalListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      new SysDictPojo();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
      }
      //查询列表
      dataAuditPojoList = dataAuditService.dataAuditTotalList(map);
      JSONArray json = JSONArray.fromObject(dataAuditPojoList);
      page.setRowCount(dataAuditPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  /**
   * 审核数据统计（审核总量、审核通过、审核不通过）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditTotal() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
      }
      
      Map<String, Object> auditMap = new HashMap<String, Object>();
      Map<String, Object> auditDateMap = new HashMap<String, Object>();
      //上下文容器
      ActionContext ac = ActionContext.getContext();
      //查询审核总量
      auditDateMap.put("auditTotal", dataAuditService.countBy(auditMap));
      auditMap.clear();
      
      //查询审核通过总量(通过总量=审核通过+待编辑)
      auditMap.put("operationType", "1");
      int dataTotalTrue = dataAuditService.countBy(auditMap);
      auditMap.clear();
      auditMap.put("operationType", "3");
      int dataTotalEdit = dataAuditService.countBy(auditMap);
      auditMap.clear();
      auditDateMap.put("auditTotalTrue", dataTotalTrue+dataTotalEdit);
      
      //查询审核不通过总量
      auditMap.put("operationType", "2");
      auditDateMap.put("auditTotalFalse", dataAuditService.countBy(auditMap));
      auditMap.clear();
      
      ac.put("auditDateMap", auditDateMap);
      int i = dataAuditService.dataAuditTotalCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  /**
   * 该方法用来计算日均数量（日均总数量、日均通过量、日均不通过量）
   * 可修改其中参数，倘若需要修改品均7天的日审核量，则修改averageDay的值为7，修改day的值为6便可完成操作
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditAverageCount() throws Exception {
      int day = 4;       //前N天
      int averageDay = 0;//算法平均数
      boolean isMoreThanFiveDay;
      Map<String, Object> map = new HashMap<String, Object>();
      Map<String, Object> jsonMap = new HashMap<String, Object>();
      DecimalFormat df = new DecimalFormat("0.00");
      Double dataAuditCount = 0.0;
      //设置前五天日期
      map.put("operationDataStartStr", statrDayAndEndDay(day).get(1));
      //设置前一天日期
      map.put("operationDataEndStr", statrDayAndEndDay(day).get(0));
      if(dataAuditPojo != null){
        if(dataAuditPojo.getOperationType() != null && dataAuditPojo.getOperationType() != ""){
          //设置操作类型
          map.put("operationType", dataAuditPojo.getOperationType());
        }
        System.out.println(dataAuditPojo.getOperationUserId());
        if(dataAuditPojo.getOperationUserId() != null && dataAuditPojo.getOperationUserId().intValue() != 0){
        //设置操作类型
          map.put("operationUserId", dataAuditPojo.getOperationUserId().intValue());
        }
      }
      //计算数据中的日期的天数
      int dayNo = dataAuditService.IsMoreThanFiveDay(map);
      
      //如果等于5天则除数设置为5，如果少于5天则按照查询的时间进行计算，如果除数为0则弹框提示
      if(dayNo == 5){
        averageDay = 5;
      }if(dayNo == 0){
        FileUtil.alertMessageBySkip("您前5天无操作记录！", "dataAuditPeopleList.do");
      }else{
        averageDay = dayNo;
      }
      //设置算法平均数
      map.put("averageDay", averageDay);
      
      //获取相关的平均量
      dataAuditCount = dataAuditService.dataAuditAverageCount(map);
      jsonMap.put("dataAuditCount", df.format(dataAuditCount));
      JSONArray json = JSONArray.fromObject(jsonMap);
      if (page == null) {
        page = new Pager();
      }
      page.setResult(json.toString());
    return SUCCESS;
  }
  /**
   * 个人审核数据统计List
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditPeopleListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      new SysDictPojo();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
        //获取审核人ID
        map.put("operationUserId", dataAuditPojo.getOperationUserId());
      }
      //查询列表
      dataAuditPojoList = dataAuditService.dataAuditPeopleList(map);
      JSONArray json = JSONArray.fromObject(dataAuditPojoList);
      page.setRowCount(dataAuditPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  /**
   * 个人审核数据统计Count
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditPeopleListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
        //获取审核人ID
        map.put("operationUserId", dataAuditPojo.getOperationUserId());
      }
      int i = dataAuditService.dataAuditPeopleCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 该方法只要用来显示页面审核人下拉框的值
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String dataAuditPeople() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      if (dataAuditPojo != null) {
        //获取前端传递的操作类型
        //获取操作开始时间
        map.put("operationDataStartStr", dataAuditPojo.getOperationDataStartStr());
        //获取操作结束时间
        map.put("operationDataEndStr", dataAuditPojo.getOperationDataEndStr());
      //获取审核人ID
        map.put("operationUserId", dataAuditPojo.getOperationUserId());
      }     
      //查询用户权限中用户名有“杭州”字样的角色
      dataAuditPojoList = dataAuditService.findUserAsHangzhouList();

      int i = dataAuditService.dataAuditPeopleCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 该方法主要用来统计个人审核数据页面的审核数据（审核总量、审核通过、审核不通过）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String getAuditAverageCount() throws Exception {
    Map<String, Object> auditMap = new HashMap<String, Object>();
    Map<String, Object> auditDateMap = new HashMap<String, Object>();

    
    //查询审核总量
    auditMap.put("operationUserId",dataAuditPojo.getOperationUserId() );
    auditDateMap.put("auditTotal", dataAuditService.countBy(auditMap));
    auditMap.clear();
    
    //查询审核通过总量(通过总量=审核通过+待编辑)
    auditMap.put("operationType", "1");
    auditMap.put("operationUserId",dataAuditPojo.getOperationUserId() );
    int dataTotalTrue = dataAuditService.countBy(auditMap);
    auditMap.clear();
    auditMap.put("operationType", "3");
    auditMap.put("operationUserId",dataAuditPojo.getOperationUserId() );
    int dataTotalEdit = dataAuditService.countBy(auditMap);
    auditMap.clear();
    auditDateMap.put("auditTotalTrue", dataTotalTrue+dataTotalEdit);
    
    //查询审核不通过总量
    auditMap.put("operationType", "2");
    auditMap.put("operationUserId",dataAuditPojo.getOperationUserId() );
    auditDateMap.put("auditTotalFalse", dataAuditService.countBy(auditMap));
    auditMap.clear();

    JSONArray json =
        JSONArray.fromObject(auditDateMap);
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());

    return SUCCESS;
  }
  
  /**
   * 获取前一天的日期以及前五天的日期
   * 一天前的时间是：2016-06-29
   * 五天前的时间是：2016-06-25
   * @param day 前几天的值（计算前5天时，day=4）
   * @return
   * @throw
   * @return List<String>
   */
  public List<String> statrDayAndEndDay(int day) {
    List<String> list = new ArrayList<String>();
    Date dNow = new Date(); //当前时间
    Date dBeforeOne = new Date();
    Date dBeforeFive = new Date();
    Calendar calendar = Calendar.getInstance(); //得到日历
    calendar.setTime(dNow);//把当前时间赋给日历
    calendar.add(Calendar.DAY_OF_WEEK, -1); //获取一天前的日期
    dBeforeOne = calendar.getTime(); //获取一天前的日期
    calendar.add(Calendar.DAY_OF_WEEK, -day); //获取五天前的日期
    dBeforeFive = calendar.getTime(); //获取五天前的日期

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
    String defaultStartDate = sdf.format(dBeforeOne); //格式化前一天的时间
    String defaultEndDate = sdf.format(dBeforeFive); //格式化前五天时间
    list.add(defaultStartDate);
    list.add(defaultEndDate);
    return list;
  }
  
}
