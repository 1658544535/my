/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.DataAuditPojo;

/**
 * @version 1.0
 * @author 
 */
public interface DataAuditDao {
	
	public int add(DataAuditPojo dataAudit);

	public int update(DataAuditPojo dataAudit);
    
	public int delete(Long id);

	public DataAuditPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<DataAuditPojo> listPage(Map<String, Object> params);
	
	/*
     * 视频审核结果日志Count
     */
	public Integer dataAuditCount(Map<String, Object> params);

	/*
     * 视频审核结果日志List
     */
    public List<DataAuditPojo> dataAuditList(Map<String, Object> params);
    
    /*
     * 审核数据总计Count
     */
    public Integer dataAuditTotalCount(Map<String, Object> params);

    /*
     * 视审核数据总计List
     */
    public List<DataAuditPojo> dataAuditTotalList(Map<String, Object> params);
    
    /*
     * 平均量Count
     */
    public Double dataAuditAverageCount(Map<String, Object> params);
    
    /*
     * 个人审核数据Count
     */
    public Integer dataAuditPeopleCount(Map<String, Object> params);

    /*
     * 个人审核数据List
     */
    public List<DataAuditPojo> dataAuditPeopleList(Map<String, Object> params);
    /*
     * 查询杭州账号List
     */
    public List<DataAuditPojo> findUserAsHangzhouList();
    /*
     * 计算数据库中日均量数据是否超过5天
     */
    public Integer IsMoreThanFiveDay(Map<String, Object> params);

}
