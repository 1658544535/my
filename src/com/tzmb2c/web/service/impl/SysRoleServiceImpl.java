package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysRoleDao;
import com.tzmb2c.web.pojo.SysRolePojo;
import com.tzmb2c.web.service.SysRoleService;

public class SysRoleServiceImpl implements SysRoleService {
  @Autowired
  private SysRoleDao sysRoleDao;

  public void setSysRoleDao(SysRoleDao sysRoleDao) {
    this.sysRoleDao = sysRoleDao;
  }


  @Override
  public List<SysRolePojo> sysRoleAllService() throws SQLException {
    return sysRoleDao.getSysRoleAll();
  }



  @Override
  public void addSysRoleService(SysRolePojo sysRolePojo) throws SQLException {

    /*
     * if("0".equals(sysRolePojo.getSysRoleLevel())){ SysRolePojo rsSysRolePojo =
     * sysRoleDao.getSysRoleCountBySysRoleLevel(sysRolePojo.getLevel()); int sysRoleId =0;
     * if(rsSysRolePojo.getSysRoleCount() > -1 && rsSysRolePojo.getSysRoleCount() <= 9){ sysRoleId =
     * rsSysRolePojo.getSysRoleCount()+1; sysRolePojo.setSysRoleId("A0" + sysRoleId); }else
     * if(rsSysRolePojo.getSysRoleCount() > 9 && rsSysRolePojo.getSysRoleCount() <= 99){ sysRoleId =
     * rsSysRolePojo.getSysRoleCount()+1; sysRolePojo.setSysRoleId("A" +sysRoleId ); }
     * //sysRolePojo.setSysRolePath("#"); }else{ SysRolePojo rsSysRolePojo =
     * sysRoleDao.getSysRoleCountBySysRoleLevel(sysRolePojo.getLevel());
     * if(rsSysRolePojo.getSysRoleCount() > -1 && rsSysRolePojo.getSysRoleCount() <= 9){
     * sysRolePojo.setSysRoleId(sysRolePojo.getLevel()+ "B0" + (rsSysRolePojo.getSysRoleCount() +
     * 1)); }else if(rsSysRolePojo.getSysRoleCount() > 9 && rsSysRolePojo.getSysRoleCount() <= 99){
     * sysRolePojo.setSysRoleId(sysRolePojo.getLevel()+ "B" + (rsSysRolePojo.getSysRoleCount() +
     * 1)); } }
     */
    sysRoleDao.insertSysRole(sysRolePojo);
  }


  @Override
  public void insertSysRole(SysRolePojo sysRolePojo) throws SQLException {

    sysRoleDao.insertSysRole(sysRolePojo);
  }

  @Override
  public void updateSysRole(SysRolePojo sysRolePojo) throws SQLException {
    sysRoleDao.updateSysRole(sysRolePojo);

  }

  @Override
  public SysRolePojo getfindByIdSysRole(Long id) throws SQLException {
    return sysRoleDao.getfindByIdSysRole(id);

  }

  @Override
  public void deleteSysRole(Long id) throws SQLException {

    sysRoleDao.deleteSysRole(id);
  }

  @Override
  public int sysRoleAllCount(SysRolePojo sysRoleDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysRoleDaoPojo != null) {
      map.put("name", sysRoleDaoPojo.getName());
      /* map.put("ticketType", ticketRulePojo.getTicketType()); */
    }
    return sysRoleDao.sysRoleAllCount(map);
  }

  @Override
  public List<SysRolePojo> sysRoleAllList(SysRolePojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo.getName() != null && !ticketRulePojo.getName().equals("")) {
      map.put("name", ticketRulePojo.getName());
      // map.put("ticketType", ticketRulePojo.getTicketType());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SysRolePojo> list = sysRoleDao.sysRoleAllList(map);

    return list;
  }

  @Override
  public void sysRoleDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        sysRoleDao.delSysRole(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delSysRole(Long id) throws SQLException {
    sysRoleDao.delSysRole(id);
  }

  @Override
  public SysRolePojo findSysRoleById(Long id) throws SQLException {

    return sysRoleDao.findSysRoleById(id);

  }
}
