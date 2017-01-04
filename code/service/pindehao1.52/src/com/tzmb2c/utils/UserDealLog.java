package com.tzmb2c.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserPindekeInfoService;

public class UserDealLog {
  private static final Logger logger = Logger.getLogger("UserDealLog");
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private UserDealLogService userDealLogService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  private UserDealLogPojo userDealLogPojo;

  public void addUserDealLogTask() throws SQLException {
    try {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("invitationUserIdStr", " gar.invitation_user_id != 0");
      // param.put("status", 1);
      param.put("activityType", 2);
      param.put("recordStatusStr", " gar.status != 0");
      param.put("groupBy", " gur.attend_id");
      param.put("pdkStatus", 1);
      param.put("isRebate", 0);
      List<GrouponUserRecordPojo> grouponUserRecordPojos =
          grouponUserRecordService.listPage2(param);
      if (grouponUserRecordPojos.size() > 0) {
        for (GrouponUserRecordPojo gur : grouponUserRecordPojos) {
          userDealLogPojo.setUserId(gur.getUserId());
          userDealLogPojo.setDealDate(new Date());
          userDealLogPojo.setDealType(1);
          // userDealLogPojo.setStatus(0);
          userDealLogPojo.setGroupId(gur.getId());
          userDealLogPojo.setRemark(1);
          if (gur.getRecordStatus() == 1) {
            userDealLogPojo.setDealAmount(1.5);
            userDealLogService.add(userDealLogPojo);
          } else if (gur.getRecordStatus() == 2 && gur.getCounts() >= 2) {
            userDealLogPojo.setDealAmount(0.3);
            userDealLogService.add(userDealLogPojo);
          }

          GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
          grouponActivityRecord.setId(gur.getId());
          grouponActivityRecord.setIsRebate(1);
          grouponActivityRecord.setRebateTime(new Date());
          grouponActivityRecordService.update(grouponActivityRecord);

          UserPindekeInfoPojo userPindekeInfo = new UserPindekeInfoPojo();
          userPindekeInfo.setId(gur.getPdkId());
          if (gur.getRecordStatus() == 1) {
            userPindekeInfo.setRebatePriceStr(" rebate_price + 1.5");
            userPindekeInfo.setSurpluPriceStr(" surplu_price + 1.5");
            userPindekeInfoService.update(userPindekeInfo);
          } else if (gur.getRecordStatus() == 2 && gur.getCounts() >= 2) {
            userPindekeInfo.setRebatePriceStr(" rebate_price + 0.3");
            userPindekeInfo.setSurpluPriceStr(" surplu_price + 0.3");
            userPindekeInfoService.update(userPindekeInfo);
          }
        }
      }
      logger.info("返佣成功!");
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("返佣失败!");
    }
  }
}
