/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import com.tzmb2c.utils.StringUtil;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * DataAudit
 * @version 1.0
 * @author
 */
public class DataAuditPojo extends SuperPojo {

	/**
     * id
     */
	private Integer id;
	/**
     * 日志记录类型(1:视频)
     */
	private String type;
	/**
     * 对应链接表的名称
     */
	private String tableName;
	/**
     * 对应链接表的id
     */
	private Integer tableLinkId;
	/**
     * 操作人ID
     */
	private Integer operationUserId;
	/**
     * 操作类型（1->审核成功；2->审核失败;3->待编辑）
     */
	private String operationType;
	/**
     * 操作时间（格式：2016-06-28 17:18:00）
     */
	private Date operationData;
	/**
     * 操作时间str（格式：2016-06-28 17:18:00）
     */
    private String operationDataStr;
	/**
     * 操作时间（格式：2016-06-28）
     */
	private String simpleData;
	/**
     * 视频标题
     */
	private String videoTitle;
	/**
     * 开始时间
     */
	private String operationDataStartStr;
	/**
     * 结束时间
     */
	private String operationDataEndStr;
	/**
     * 视频链接
     */
	private String url;
	/**
     * 审核人
     */
	private String operationUserName;
	/**
     * 是否删除（视频）
     */
	private String isDelete;
	/**
	 * 日审人数
	 */
	private String operationPeopleNo;
	/**
     * 日审核总量
     */
	private String operationTotalByDay;
	/**
     * 日审核通过量
     */
    private String operationTypeTrueByDay;
    /**
     * 日审核不通过量
     */
    private String operationTypeFalseByDay;
    /**
     * 日均平均数日期
     */
    private String averageDay;
		
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setType(String value) {
		this.type = value;
	}
	
	public String getType() {
		return this.type;
	}
	public void setTableName(String value) {
		this.tableName = value;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	public void setTableLinkId(Integer value) {
		this.tableLinkId = value;
	}
	
	public Integer getTableLinkId() {
		return this.tableLinkId;
	}
	public void setOperationUserId(Integer value) {
		this.operationUserId = value;
	}
	
	public Integer getOperationUserId() {
		return this.operationUserId;
	}
	public void setOperationType(String value) {
		this.operationType = value;
	}
	
	public String getOperationType() {
		return this.operationType;
	}
	public void setOperationData(Date value) {
		this.operationData = value;
		if (this.operationData != null) {
		  operationDataStr = StringUtil.dateString(this.operationData);
	      }
	}
	
	public Date getOperationData() {
		return this.operationData;
	}
	public void setSimpleData(String value) {
		this.simpleData = value;
	}
	
	public String getSimpleData() {
		return this.simpleData;
	}

    public String getVideoTitle() {
      return videoTitle;
    }
  
    public void setVideoTitle(String videoTitle) {
      this.videoTitle = videoTitle;
    }
  
    public String getOperationDataStartStr() {
      return operationDataStartStr;
    }
  
    public void setOperationDataStartStr(String operationDataStartStr) {
      this.operationDataStartStr = operationDataStartStr;
    }
  
    public String getOperationDataEndStr() {
      return operationDataEndStr;
    }
  
    public void setOperationDataEndStr(String operationDataEndStr) {
      this.operationDataEndStr = operationDataEndStr;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getOperationUserName() {
      return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
      this.operationUserName = operationUserName;
    }

    public String getIsDelete() {
      return isDelete;
    }

    public void setIsDelete(String isDelete) {
      this.isDelete = isDelete;
    }

    public String getOperationDataStr() {
      return operationDataStr;
    }

    public void setOperationDataStr(String operationDataStr) {
      this.operationDataStr = operationDataStr;
    }

    public String getOperationPeopleNo() {
      return operationPeopleNo;
    }

    public void setOperationPeopleNo(String operationPeopleNo) {
      this.operationPeopleNo = operationPeopleNo;
    }

    public String getOperationTotalByDay() {
      return operationTotalByDay;
    }

    public void setOperationTotalByDay(String operationTotalByDay) {
      this.operationTotalByDay = operationTotalByDay;
    }

    public String getOperationTypeTrueByDay() {
      return operationTypeTrueByDay;
    }

    public void setOperationTypeTrueByDay(String operationTypeTrueByDay) {
      this.operationTypeTrueByDay = operationTypeTrueByDay;
    }

    public String getOperationTypeFalseByDay() {
      return operationTypeFalseByDay;
    }

    public void setOperationTypeFalseByDay(String operationTypeFalseByDay) {
      this.operationTypeFalseByDay = operationTypeFalseByDay;
    }

    public String getAverageDay() {
      return averageDay;
    }

    public void setAverageDay(String averageDay) {
      this.averageDay = averageDay;
    }
    
    
    
	
}
