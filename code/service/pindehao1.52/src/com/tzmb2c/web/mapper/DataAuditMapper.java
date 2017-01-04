/*
 * 文 件 名:  DataAuditMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-29
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.DataAuditPojo;

public interface DataAuditMapper {
    
    DataAuditPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<DataAuditPojo> listPage(Map<String, Object> params);
    
    int insert(DataAuditPojo dataAudit);
    
    int update(DataAuditPojo dataAudit);
    
    int deleteById(Long id);
    
    /*
     * 视频审核结果日志Count
     */
    int dataAuditCount(Map<String, Object> params);
    
    /*
     * 视频审核结果日志List
     */
    List<DataAuditPojo> dataAuditList(Map<String, Object> params);
     
    /*
     * 审核数据总计Count
     */
    int dataAuditTotalCount(Map<String, Object> params);
    
    /*
     * 视审核数据总计List
     */
    List<DataAuditPojo> dataAuditTotalList(Map<String, Object> params);
    
    /*
     * 平均量Countt
     */
    double dataAuditAverageCount(Map<String, Object> params);
    
    /*
     * 个人审核数据Count
     */
    int dataAuditPeopleCount(Map<String, Object> params);
    
    /*
     * 个人审核数据List
     */
    List<DataAuditPojo> dataAuditPeopleList(Map<String, Object> params);
    /*
     * 查询杭州账号List
     */
    List<DataAuditPojo> findUserAsHangzhouList();
    
    /*
     * 计算数据库中日均量数据是否超过5天
     */
    int IsMoreThanFiveDay(Map<String, Object> params);
}