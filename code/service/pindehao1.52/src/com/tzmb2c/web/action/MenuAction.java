package com.tzmb2c.web.action;

import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.MenuService;

public class MenuAction extends SuperAction {

  private MenuService menuService;
  private Long id;
  private String name;
  private String nameEn;
  private String icon;
  private Long level;
  private String levelStr;
  private String path;
  private Integer sorting;
  private Integer status;
  private String jsonStr;
  private MenuPojo menuObj;

  public String goErrorPage() {
    return SUCCESS;
  }

  @Override
  public String execute() throws Exception {

    ActionContext actionContext = ActionContext.getContext();
    List<MenuPojo> menuList = menuService.menuAllService();

    actionContext.put("menuList", menuList);

    return SUCCESS;

  }

  /**
   * 跳到添加子菜单
   * 
   * @return
   * @throws Exception
   */
  public String goSonAddPage() throws Exception {
    MenuPojo menuObj = menuService.getfindByIdMenu(id);

    this.addRequest("menuObj", menuObj);
    return SUCCESS;
  }

  /**
   * 跳到修改子菜单
   * 
   * @return
   * @throws Exception
   */
  public String goSonUpdateMenu() throws Exception {
    MenuPojo menuObj = menuService.getfindByIdMenu(id);

    this.addRequest("menuObj", menuObj);
    this.addRequest("hostMenuObj", menuService.getfindByIdMenu(menuObj.getLevel()));
    this.addRequest("fatherMenuList", menuService.menuLevelService(0l));
    return SUCCESS;
  }

  /**
   * 跳到添加菜单
   * 
   * @return
   * @throws Exception
   */
  public String goAddPage() throws Exception {

    ActionContext actionContext = ActionContext.getContext();
    menuObj = menuService.getfindByIdMenu(id);
    actionContext.put("menuObj", menuObj);
    return SUCCESS;

  }

  /**
   * 添加菜单
   * 
   * @return
   * @throws Exception
   */
  public String addMenu() throws Exception {


    MenuPojo menuPojo = new MenuPojo();
    menuPojo.setName(name);
    menuPojo.setNameEn(nameEn);
    menuPojo.setPath(path);
    menuPojo.setIcon(icon);
    if (level == null) {
      menuPojo.setLevel(0l);
    } else {
      menuPojo.setLevel(level);
    }
    menuPojo.setSorting(sorting);
    menuPojo.setStatus(status);
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      menuPojo.prePersist(loginPojo);
    }
    menuService.addMenuService(menuPojo);
    alertMessageBySkip("添加成功！", "menuManage.do");
    return null;
  }

  /**
   * 获得所有父菜单
   * 
   * @return
   */
  public String findMenuByFatherAll() throws Exception {
    ActionContext actionContext = ActionContext.getContext();

    actionContext.put("menuFatherList", menuService.menuLevelService(0l));

    return SUCCESS;
  }


  /**
   * 获得子菜单
   * 
   * @return
   */
  public String findMenuBySon() throws Exception {
    List<MenuPojo> sonMenuList = menuService.menuLevelService(level);
    JSONArray jsonStrs = new JSONArray();
    JSONObject jsonObj = new JSONObject();

    for (MenuPojo mp : sonMenuList) {
      jsonObj.put("menName", mp.getName());

      jsonObj.put("menuPath", mp.getPath());

      jsonObj.put("menuId", mp.getId());

      jsonStrs.add(jsonObj);
    }


    jsonStr = jsonStrs.toString();
    return SUCCESS;

  }

  public String findMenulesect() throws Exception {

    ActionContext actionContext = ActionContext.getContext();

    actionContext.put("menuFatherList", menuService.menuLevelService(0l));


    return SUCCESS;
  }


  /**
   * 修改菜单
   * 
   * @return
   * @throws Exception
   */
  public String updateMenuByHost() throws Exception {
    MenuPojo menuPojo = new MenuPojo();
    menuPojo.setName(name);
    menuPojo.setNameEn(nameEn);
    menuPojo.setPath(path);
    menuPojo.setIcon(icon);
    menuPojo.setId(id);
    if (levelStr == null) {
      if (level == null) {
        menuPojo.setLevel(0l);
      } else {
        menuPojo.setLevel(level);
      }
    } else {
      menuPojo.setLevel(Long.valueOf(levelStr));
    }

    menuPojo.setSorting(sorting);
    menuPojo.setStatus(status);
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      menuPojo.preUpdate(loginPojo);
    }
    menuService.updateMenu(menuPojo);


    return SUCCESS;
  }

  /**
   * 跳到修改菜单获取菜单信息
   * 
   * @return
   * @throws Exception
   */
  public String findByIdMenu() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    menuObj = menuService.getfindByIdMenu(id);
    actionContext.put("menuObj", menuObj);
    return SUCCESS;
  }

  /**
   * 删除
   * 
   * @return
   * @throws Exception
   */
  public String deleteMenu() throws Exception {
    menuService.deleteMenu(id);
    return SUCCESS;
  }



  public MenuPojo getMenuObj() {
    return menuObj;
  }

  public void setMenuObj(MenuPojo menuObj) {
    this.menuObj = menuObj;
  }

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getNameEn() {
    return nameEn;
  }


  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }


  public String getIcon() {
    return icon;
  }


  public void setIcon(String icon) {
    this.icon = icon;
  }


  public Integer getSorting() {
    return sorting;
  }


  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }


  public Integer getStatus() {
    return status;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }


  public Long getLevel() {
    return level;
  }


  public void setLevel(Long level) {
    this.level = level;
  }


  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public void setMenuService(MenuService menuService) {
    this.menuService = menuService;
  }

  public String getLevelStr() {
    return levelStr;
  }

  public void setLevelStr(String levelStr) {
    this.levelStr = levelStr;
  }

  public MenuService getMenuService() {
    return menuService;
  }

}
