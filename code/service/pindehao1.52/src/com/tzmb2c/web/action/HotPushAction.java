package com.tzmb2c.web.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.HotPushPojo;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.service.HotPushService;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.UserMakerThemeService;

public class HotPushAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private HotPushService hotPushService;
  @Autowired
  private PlatformSpecialService platformSpecialService;
  @Autowired
  private UserMakerThemeService userMakerThemeService;
  private HotPushPojo hotPushPojo;
  private PlatformSpecialPojo platformSpecialPojo;
  private UserMakerThemePojo userMakerThemePojo;
  private List<HotPushPojo> hotPushList;
  private String[] tids;
  private String s;
  private String result;
  private String specialType;
  private Long id;
  private Integer type;
  private File upfile;

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public PlatformSpecialPojo getPlatformSpecialPojo() {
    return platformSpecialPojo;
  }

  public void setPlatformSpecialPojo(PlatformSpecialPojo platformSpecialPojo) {
    this.platformSpecialPojo = platformSpecialPojo;
  }

  public UserMakerThemePojo getUserMakerThemePojo() {
    return userMakerThemePojo;
  }

  public void setUserMakerThemePojo(UserMakerThemePojo userMakerThemePojo) {
    this.userMakerThemePojo = userMakerThemePojo;
  }

  public String getSpecialType() {
    return specialType;
  }

  public void setSpecialType(String specialType) {
    this.specialType = specialType;
  }

  public HotPushPojo getHotPushPojo() {
    return hotPushPojo;
  }

  public void setHotPushPojo(HotPushPojo hotPushPojo) {
    this.hotPushPojo = hotPushPojo;
  }

  public List<HotPushPojo> getHotPushList() {
    return hotPushList;
  }

  public void setHotPushList(List<HotPushPojo> hotPushList) {
    this.hotPushList = hotPushList;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  /**
   * 查询全部条数
   */
  public String hotPushListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (hotPushPojo != null) {
        if (hotPushPojo.getSpecialName() != null
            && StringUtils.isNotBlank(hotPushPojo.getSpecialName())) {
          map.put("specialName", hotPushPojo.getSpecialName());
        }
        if (hotPushPojo.getType() != null) {
          map.put("type", hotPushPojo.getType());
        }
      }
      //查询专题类别
      if (StringUtils.isNotBlank(specialType)) {
        String[] typeArr = specialType.split(",");
        for (int i = 1; i < typeArr.length; i++) {
          if (Integer.valueOf(typeArr[0]).intValue() == 1) {
            map.put("type", 1);
            if (Integer.valueOf(typeArr[1]).intValue() == 1) {
              map.put("specialType", 1);
            } else if (Integer.valueOf(typeArr[1]).intValue() == 2) {
              map.put("specialType", 2);
            }
          } else if (Integer.valueOf(typeArr[0]).intValue() == 2) {
            map.put("type", 2);
            if (Integer.valueOf(typeArr[1]).intValue() == 1) {
              map.put("specialType", 1);
            } else if (Integer.valueOf(typeArr[1]).intValue() == 2) {
              map.put("specialType", 2);
            } else if (Integer.valueOf(typeArr[1]).intValue() == 3) {
              map.put("specialType", 3);
            }
          }
        }
      }
      map.put("orderBy", "hotPush.sorting desc,hotPush.createDate desc ");
      int i = hotPushService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }


  /**
   * 查询全部内容
   */
  public String hotPushListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (hotPushPojo != null) {
        if (hotPushPojo.getSpecialName() != null
            && StringUtils.isNotBlank(hotPushPojo.getSpecialName())) {
          map.put("specialName", hotPushPojo.getSpecialName());
        }
        if (hotPushPojo.getType() != null) {
          map.put("type", hotPushPojo.getType());
        }
      }
      //查询专题类别
      if (StringUtils.isNotBlank(specialType)) {
        String[] typeArr = specialType.split(",");
        for (int i = 1; i < typeArr.length; i++) {
          if (Integer.valueOf(typeArr[0]).intValue() == 1) {
            map.put("type", 1);
            if (Integer.valueOf(typeArr[1]).intValue() == 1) {
              map.put("specialType", 1);
            } else if (Integer.valueOf(typeArr[1]).intValue() == 2) {
              map.put("specialType", 2);
            }
          } else if (Integer.valueOf(typeArr[0]).intValue() == 2) {
            map.put("type", 2);
            if (Integer.valueOf(typeArr[1]).intValue() == 1) {
              map.put("specialType", 1);
            } else if (Integer.valueOf(typeArr[1]).intValue() == 2) {
              map.put("specialType", 2);
            } else if (Integer.valueOf(typeArr[1]).intValue() == 3) {
              map.put("specialType", 3);
            }
          }
        }
      }
      map.put("orderBy", "hotPush.sorting desc,hotPush.createDate desc ");
      hotPushList = hotPushService.listPage(map);
      JSONArray json = JSONArray.fromObject(hotPushList);
      page.setRowCount(hotPushList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑话题
   */
  public String goHotPush() throws Exception {
    if (id != null) {
      hotPushPojo = hotPushService.getById(id);
      if (hotPushPojo != null) {
        int type = hotPushPojo.getType();
        if (type == 1) {
          // 平台专题
          PlatformSpecialPojo plat =
              platformSpecialService.getById(hotPushPojo.getSpecialId().intValue());
          if (plat != null) {
            hotPushPojo.setSpecialName(plat.getTitle());
            hotPushPojo.setSketch(plat.getSketch());
          }
        } else if (type == 2) {
          UserMakerThemePojo theme =
              userMakerThemeService.getUserMakerThemeById(hotPushPojo.getSpecialId());
          if (theme != null) {
            hotPushPojo.setSpecialName(theme.getSpecialName());
            hotPushPojo.setSketch(theme.getSketch());
          }
        }
      }
    }
    return SUCCESS;
  }


  /**
   * 编辑提交
   * 
   * @return
   */
  public String editHotPushSubmit() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && hotPushPojo != null && hotPushPojo.getId() != null) {
      HotPushPojo hotpush = new HotPushPojo();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/hotpush")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/hotpush/", file_name, 0, 0, false);
        hotpush.setBanner(file_name);
      }
      hotpush.setSorting(hotPushPojo.getSorting());
      hotpush.setId(hotPushPojo.getId());
      int i = hotPushService.update(hotpush);
      if (i > 0) {
        FileUtil.alertMessageBySkip("提交成功！", "hotPushList.do");
      } else {
        FileUtil.alertMessageBySkip("更新失败！", "goHotPush.do?id=" + hotPushPojo.getId());
      }
    }
    return null;
  }

  /**
   * 设置排序
   * 
   * @return
   */
  public String setSorting() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null) {
      try {
        if (hotPushPojo != null && hotPushPojo.getId() != 0) {
          hotPushService.update(hotPushPojo);
          result = "1";
        }
      } catch (Exception e) {
        result = "0";
        e.printStackTrace();
      }
    } else {
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String delhotPush() throws Exception {
    try {
      if (hotPushPojo != null && hotPushPojo.getType() != null && hotPushPojo.getId() != null
          && hotPushPojo.getSpecialId() != null) {
        if (hotPushPojo.getType() == 1) {
          //平台专题
          platformSpecialPojo = new PlatformSpecialPojo();
          platformSpecialPojo.setId(hotPushPojo.getSpecialId());
          platformSpecialPojo.setIsHomePage(0);
          platformSpecialService.update(platformSpecialPojo);
          //删除热门推荐
          hotPushService.delete(hotPushPojo.getId());
          FileUtil.alertMessageBySkip("取消成功！", "hotPushList.do");
        } else if (hotPushPojo.getType() == 2) {
          //创客专题
          userMakerThemePojo = new UserMakerThemePojo();
          userMakerThemePojo.setId(hotPushPojo.getSpecialId());
          userMakerThemePojo.setIsHomePage(0);
          userMakerThemeService.updateUserMakerTheme(userMakerThemePojo);
          //删除热门推荐
          hotPushService.delete(hotPushPojo.getId());
          FileUtil.alertMessageBySkip("取消成功！", "hotPushList.do");
        }
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取消失败！", "hotPushList.do");
    }
    return null;
  }
}
