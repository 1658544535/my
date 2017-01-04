package com.tzmb2c.web.action;

import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.web.pojo.SysRoleMenuPojo;
import com.tzmb2c.web.service.MenuService;
import com.tzmb2c.web.service.SysRoleMenuService;
import com.tzmb2c.web.service.SysRoleService;

public class SysRoleMenuAction extends SuperAction {

  @Autowired
  private SysRoleMenuService sysRoleMenuService;
  @Autowired
  private MenuService menuService;
  @Autowired
  private SysRoleService sysRoleService;

  private SysRoleMenuPojo menuRolePojo;


  private String jsonStr;



  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public SysRoleMenuPojo getMenuRolePojo() {
    return menuRolePojo;
  }

  public void setMenuRolePojo(SysRoleMenuPojo menuRolePojo) {
    this.menuRolePojo = menuRolePojo;
  }

  public void setRoleService(SysRoleService sysRoleService) {
    this.sysRoleService = sysRoleService;
  }

  public void setMenuService(MenuService menuService) {
    this.menuService = menuService;
  }

  public void setMenuRoleService(SysRoleMenuService sysRoleMenuService) {
    this.sysRoleMenuService = sysRoleMenuService;
  }

  @Override
  public String execute() throws Exception {

    ActionContext actionContext = ActionContext.getContext();

    actionContext.put("menuroleList", sysRoleMenuService.menuRoleAllService());
    actionContext.put("roleAllList", sysRoleService.sysRoleAllService());

    return SUCCESS;
  }

  public String goAddMenuRole() throws Exception {
    ActionContext actionContext = ActionContext.getContext();

    // 获得所有主菜单
    actionContext.put("menuFatherList", menuService.menuLevelService(0l));

    actionContext.put("roleAllList", sysRoleService.sysRoleAllService());
    this.addRequest("menuRole", menuRolePojo);

    return SUCCESS;
  }

  public void addMenuRole() throws Exception {

    sysRoleMenuService.deleMenuRoleByRoleId(menuRolePojo.getRoleId());

    String[] temporary = menuRolePojo.getMenuIds().split(",");

    for (int i = 0; i < temporary.length; i++) {
      SysRoleMenuPojo mrp = new SysRoleMenuPojo();
      mrp.setMenuId(Long.valueOf(temporary[i]));
      mrp.setRoleId(menuRolePojo.getRoleId());
      sysRoleMenuService.addMenuRoleService(mrp);
    }

    alertMessageBySkip("添加成功！", "roleMenuManage.do?menuRolePojo.roleId=" + menuRolePojo.getRoleId());
  }

  public String deleMenuRole() throws Exception {
    sysRoleMenuService.deleMenuRoleByMenuidAndRoleId(menuRolePojo);
    alertMessageBySkip("删除成功！", "roleMenuManage.do?menuRolePojo.roleId=" + menuRolePojo.getRoleId());
    return null;
  }


  public String getMenuRoleByRoleid() throws Exception {

    List<SysRoleMenuPojo> menuRoleIdList =
        sysRoleMenuService.getMenuRoleByRoleid(menuRolePojo.getRoleId());
    JSONObject jsonObj = new JSONObject();
    JSONArray jsonList = new JSONArray();
    for (SysRoleMenuPojo mrp : menuRoleIdList) {
      jsonObj.put("menuId", mrp.getMenuId());
      if (mrp.getLevel() == 0) {
        jsonObj.put("menuName", mrp.getMenuName() + "(一级)");
      } else {
        jsonObj.put("menuName", mrp.getMenuName());
      }
      jsonList.add(jsonObj);
    }
    jsonStr = jsonList.toString();
    return SUCCESS;
  }


  /**
   * 查询
   * 
   * @return
   * @throws Exception
   */
  public String queryMenuRole() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    actionContext.put("roleAllList", sysRoleService.sysRoleAllService());

    if (menuRolePojo.getRoleId() == 0) {
      actionContext.put("menuroleList", sysRoleMenuService.menuRoleAllService());
    } else {
      actionContext.put("menuroleList",
          sysRoleMenuService.getMenuRoleByRoleid(menuRolePojo.getRoleId()));
    }
    actionContext.put("menuRolePojo", menuRolePojo);
    return SUCCESS;
  }

}
