package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysUserRoleDao;
import com.tzmb2c.web.pojo.SysUserRolePojo;
import com.tzmb2c.web.service.SysUserRoleService;

public class SysUserRoleServiceImpl implements SysUserRoleService {
  @Autowired
  private SysUserRoleDao sysUserRoleDao;

  public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
    this.sysUserRoleDao = sysUserRoleDao;
  }


  @Override
  public List<SysUserRolePojo> sysUserRoleAllService() throws SQLException {
    return sysUserRoleDao.getSysUserRoleAll();
  }



  @Override
  public void addSysUserRoleService(SysUserRolePojo sysUserRolePojo) throws SQLException {

    /*
     * if("0".equals(sysUserRolePojo.getSysUserRoleLevel())){ SysUserRolePojo rsSysUserRolePojo =
     * sysUserRoleDao.getSysUserRoleCountBySysUserRoleLevel(sysUserRolePojo.getLevel()); int
     * sysUserRoleId =0; if(rsSysUserRolePojo.getSysUserRoleCount() > -1 &&
     * rsSysUserRolePojo.getSysUserRoleCount() <= 9){ sysUserRoleId =
     * rsSysUserRolePojo.getSysUserRoleCount()+1; sysUserRolePojo.setSysUserRoleId("A0" +
     * sysUserRoleId); }else if(rsSysUserRolePojo.getSysUserRoleCount() > 9 &&
     * rsSysUserRolePojo.getSysUserRoleCount() <= 99){ sysUserRoleId =
     * rsSysUserRolePojo.getSysUserRoleCount()+1; sysUserRolePojo.setSysUserRoleId("A"
     * +sysUserRoleId ); } //sysUserRolePojo.setSysUserRolePath("#"); }else{ SysUserRolePojo
     * rsSysUserRolePojo =
     * sysUserRoleDao.getSysUserRoleCountBySysUserRoleLevel(sysUserRolePojo.getLevel());
     * if(rsSysUserRolePojo.getSysUserRoleCount() > -1 && rsSysUserRolePojo.getSysUserRoleCount() <=
     * 9){ sysUserRolePojo.setSysUserRoleId(sysUserRolePojo.getLevel()+ "B0" +
     * (rsSysUserRolePojo.getSysUserRoleCount() + 1)); }else
     * if(rsSysUserRolePojo.getSysUserRoleCount() > 9 && rsSysUserRolePojo.getSysUserRoleCount() <=
     * 99){ sysUserRolePojo.setSysUserRoleId(sysUserRolePojo.getLevel()+ "B" +
     * (rsSysUserRolePojo.getSysUserRoleCount() + 1)); } }
     */
    sysUserRoleDao.insertSysUserRole(sysUserRolePojo);
  }


  @Override
  public void insertSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException {

    sysUserRoleDao.insertSysUserRole(sysUserRolePojo);
  }

  @Override
  public void updateSysUserRole(SysUserRolePojo sysUserRolePojo) throws SQLException {
    sysUserRoleDao.updateSysUserRole(sysUserRolePojo);

  }

  @Override
  public SysUserRolePojo getfindByIdSysUserRole(Long id) throws SQLException {
    return sysUserRoleDao.getfindByIdSysUserRole(id);

  }

  @Override
  public void deleteSysUserRole(Long id) throws SQLException {

    sysUserRoleDao.deleteSysUserRole(id);
  }

  @Override
  public int sysUserRoleAllCount(SysUserRolePojo sysUserRoleDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    // if(sysUserRoleDaoPojo!=null){
    // map.put("name", sysUserRoleDaoPojo.getName());
    // /* map.put("ticketType", ticketRulePojo.getTicketType());*/
    // }
    return sysUserRoleDao.sysUserRoleAllCount(map);
  }

  @Override
  public List<SysUserRolePojo> sysUserRoleAllList(SysUserRolePojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    // if(ticketRulePojo.getName()!=null&&!ticketRulePojo.getName().equals("")){
    // map.put("name", ticketRulePojo.getName());
    // // map.put("ticketType", ticketRulePojo.getTicketType());
    // }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SysUserRolePojo> list = sysUserRoleDao.sysUserRoleAllList(map);

    return list;
  }

  @Override
  public void sysUserRoleDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        sysUserRoleDao.delSysUserRole(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delSysUserRole(Long id) throws SQLException {
    sysUserRoleDao.delSysUserRole(id);
  }

  @Override
  public SysUserRolePojo findSysUserRoleById(Long id) throws SQLException {

    return sysUserRoleDao.findSysUserRoleById(id);

  }


  @Override
  public SysUserRolePojo getSysUserRoleByUid(Long uid) throws SQLException {
    return sysUserRoleDao.getSysUserRoleByUid(uid);
  }
}
