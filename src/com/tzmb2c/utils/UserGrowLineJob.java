package com.tzmb2c.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserGrowthLineService;

public class UserGrowLineJob {
  private static final Logger logger = Logger.getLogger("UserGrowLineJob");
  @Autowired
  UserGrowthLineService userGrowthLineService;

  public void updateGrowLine() {
    // 每月1号更新成长线
    Calendar cal = Calendar.getInstance();
    Date today = cal.getTime();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    Date month = cal.getTime();
    int i = 0;
    if (StringUtil.dateToString(today).equals(StringUtil.dateToString(month))) {
      i = userGrowthLineService.batchMonthUpdGrowLine();
      logger.info(">>>>>> 每月1号更新成长线：" + i + "条");
    }

    // 成长线临界点更新分数
    // 0~6个月转6~12月
    i = userGrowthLineService.batchAgeUpdGrowLine(1);
    logger.info(">>>>>> 0~6个月转6~12月：" + i + "条");
    // 6~12个月转1~3岁
    i = userGrowthLineService.batchAgeUpdGrowLine(2);
    logger.info(">>>>>> 6~12个月转1~3岁：" + i + "条");
    // 1~3岁转3~6岁
    i = userGrowthLineService.batchAgeUpdGrowLine(3);
    logger.info(">>>>>> 1~3岁转3~6岁：" + i + "条");
    // 3~6岁转6~12岁
    i = userGrowthLineService.batchAgeUpdGrowLine(4);
    logger.info(">>>>>> 3~6岁转6~12岁：" + i + "条");
  }

}
