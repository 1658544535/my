package com.tzmb2c.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.mapper.LoginRecMapper;
import com.tzmb2c.web.mapper.SysLoginLogMapper;
import com.tzmb2c.web.mapper.SysLoginMapper;
import com.tzmb2c.web.mapper.TempDataMapper;
import com.tzmb2c.web.mapper.UserInfoMapper;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TempDataPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.service.SysLoginService;

public class TaskJob {
  private static final Logger logger = Logger.getLogger("TaskJob");
  @Autowired
  private TempDataMapper tempDataMapper;
  @Autowired
  private SysLoginMapper sysLoginMapper;
  @Autowired
  private SysLoginLogMapper sysLoginLogMapper;
  @Autowired
  private UserInfoMapper userInfoMapper;
  @Autowired
  private LoginRecMapper loginRecMapper;
  @Autowired
  private SysLoginService sysLoginService;

  public void importConsumerJob() {
    try {
      // 获取编号最小的一条带插入记录
      TempDataPojo tdp = tempDataMapper.getSingleRecord();
      if (null != tdp) {
        // 检查手机号码是否已经存在
        int count = sysLoginMapper.getCountByLoginName(tdp.getPhone());
        Random rand = new Random();
        // String[] platforms = { "速卖通", "淘宝网", "实体店", "阿里巴巴", "微店" };
        Date now = new Date();
        if (count == 0) {
          // 插入登陆表
          SysLoginPojo login = new SysLoginPojo();
          login.setCreateBy((long) -1);
          login.setLoginname(tdp.getPhone());
          login.setPassword(MD5Util.MD5(tdp.getPhone()));
          login.setName(tdp.getRealName());
          login.setType("1");
          login.setStatus(1);
          login.setCreateDate(new Date(now.getTime() + rand.nextInt(60) * 1000));
          login.setUpdateDate(new Date(now.getTime() + (rand.nextInt(200) + 1) * 60000));
          login.setExternalSignCode(sysLoginService.genExternalSignCode(tdp.getPhone()));
          login.setInvitationCode(sysLoginService.genInvitationCode(tdp.getPhone()));
          sysLoginMapper.insertSysLoginReturnID(login);
          Long loginId = login.getId();
          LoginRecPojo loginRec = new LoginRecPojo();
          loginRec.setUserId(loginId);
          Date loginDate = new Date(now.getTime() + (rand.nextInt(3) + 1) * 60000);
          loginRec.setLoginDate(loginDate);
          loginRec.setLoginIp("127.0.0.1");
          loginRecMapper.addLoginRec(loginRec);
          logger.info("插入登陆表成功:" + tdp.getPhone());
          // 插入用户信息表
          UserInfoPojo up = new UserInfoPojo();
          up.setCreateBy((long) -1);
          up.setUserId(loginId);
          up.setPhone(tdp.getPhone());
          up.setAddress(tdp.getAddress());
          up.setStatus(1);
          int num = rand.nextInt(3); // 生成随机渠道
          up.setChannel(num);
          up.setCreateDate(new Date(now.getTime() + rand.nextInt(60) * 1000));
          up.setUpdateDate(new Date(now.getTime() + (rand.nextInt(200) + 1) * 60000));
          // cp.setContact(tdp.getRealName());
          // cp.setPlatform(platforms[rand.nextInt(5)]);
          // int mainc = rand.nextInt(5) + 1;
          // cp.setMainCategory(String.valueOf(mainc));
          userInfoMapper.insertUserInfo(up);
          logger.info("插入用户信息表成功:" + tdp.getPhone());
        } else {
          logger.info(tdp.getPhone() + "记录已存在，不插入");
        }
        tempDataMapper.deleteSingleRecord(tdp.getId());
      }
    } catch (SQLException e) {
      logger.error(e);
    }
  }
}
