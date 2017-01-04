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
 * 用户兑换码表; InnoDB free: 57344 kB
 * @version 1.0
 * @author
 */
public class UserRedeemCodePojo extends SuperPojo {

	/**
     * 兑换码
     */
	private String code;
	/**
     * 金额
     */
	private Double price;
	/**
     * 有效期开始时间
     */
	private Date validStime;
	/**
     * 有效期结束时间
     */
	private Date validEtime;
	/**
     * 审核状态（0-未审核1-已审核）
     */
	private Integer status;
	/**
     * 生成时间
     */
	private Date genTime;
	/**
     * 使用用户ID
     */
	private Long userId;
	/**
     * 使用状态（0-未使用1-已使用）
     */
	private Integer used;
	/**
     * 使用时间
     */
	private Date useTime;
	private String validStimeStr,validEtimeStr,genTimeStr,useTimeStr;
	private Integer num;
	private String loginname;
		
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setPrice(Double value) {
		this.price = value;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setValidStime(Date value) {
		this.validStime = value;
	}
	
	public Date getValidStime() {
		return this.validStime;
	}
	public void setValidEtime(Date value) {
		this.validEtime = value;
	}
	
	public Date getValidEtime() {
		return this.validEtime;
	}
	
	public String getValidStimeStr() {
	    if (validStime != null) {
	      return StringUtil.dateString(validStime);
	    }
	    return validStimeStr;
	  }

	  public void setValidStimeStr(String validStimeStr) {
	    this.validStimeStr = validStimeStr;
	  }

	  public String getValidEtimeStr() {
        if (validEtime != null) {
          return StringUtil.dateString(validEtime);
        }
        return validEtimeStr;
      }

      public void setValidEtimeStr(String validEtimeStr) {
        this.validEtimeStr = validEtimeStr;
      }
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setGenTime(Date value) {
		this.genTime = value;
	}
	
	public Date getGenTime() {
		return this.genTime;
	}
	
	public String getGenTimeStr() {
      if (genTime != null) {
        return StringUtil.dateString(genTime);
      }
      return genTimeStr;
    }

    public void setGenTimeStr(String genTimeStr) {
      this.genTimeStr = genTimeStr;
    }
	
	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setUsed(Integer value) {
		this.used = value;
	}
	
	public Integer getUsed() {
		return this.used;
	}
	public void setUseTime(Date value) {
		this.useTime = value;
	}
	
	public Date getUseTime() {
		return this.useTime;
	}
	
	public String getUseTimeStr() {
      if (useTime != null) {
        return StringUtil.dateString(useTime);
      }
      return useTimeStr;
    }

    public void setUseTimeStr(String useTimeStr) {
      this.useTimeStr = useTimeStr;
    }

    public Integer getNum() {
      return num;
    }

    public void setNum(Integer num) {
      this.num = num;
    }

    public String getLoginname() {
      return loginname;
    }

    public void setLoginname(String loginname) {
      this.loginname = loginname;
    }
}
