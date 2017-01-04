package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.service.LabelService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.YourFavouritesDetailService;
import com.tzmb2c.web.service.YourFavouritesService;

public class FavouritesLabelAction extends SuperAction {

  @Autowired
  private YourFavouritesService yourFavouritesService;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private LabelService labelService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private YourFavouritesDetailService yourFavouritesDetailService;

  /**
   * serialVersionUID:TODO<用一句话描述这个变量表示什么>
   */
  private static final long serialVersionUID = 1L;
  private YourFavouritesPojo yourFavouritesPojo;
  private File upfile;
  private String[] ageTypes;
  private UserCirclePostPojo userCirclePostPojo;
  private List<LabelPojo> labelPojoList;
  private List<SysDictPojo> statusSysDictList;
  private YourFavouritesDetailPojo yourFavouritesDetailPojo;
  private String[] tids;
  private String result;
  private String ageType;

  public String getAgeType() {
    return ageType;
  }

  public void setAgeType(String ageType) {
    this.ageType = ageType;
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

  public YourFavouritesDetailPojo getYourFavouritesDetailPojo() {
    return yourFavouritesDetailPojo;
  }

  public void setYourFavouritesDetailPojo(YourFavouritesDetailPojo yourFavouritesDetailPojo) {
    this.yourFavouritesDetailPojo = yourFavouritesDetailPojo;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public List<LabelPojo> getLabelPojoList() {
    return labelPojoList;
  }

  public void setLabelPojoList(List<LabelPojo> labelPojoList) {
    this.labelPojoList = labelPojoList;
  }

  public UserCirclePostPojo getUserCirclePostPojo() {
    return userCirclePostPojo;
  }

  public void setUserCirclePostPojo(UserCirclePostPojo userCirclePostPojo) {
    this.userCirclePostPojo = userCirclePostPojo;
  }

  public String[] getAgeTypes() {
    return ageTypes;
  }

  public void setAgeTypes(String[] ageTypes) {
    this.ageTypes = ageTypes;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public YourFavouritesPojo getYourFavouritesPojo() {
    return yourFavouritesPojo;
  }

  public void setYourFavouritesPojo(YourFavouritesPojo yourFavouritesPojo) {
    this.yourFavouritesPojo = yourFavouritesPojo;
  }

  /**
   * 
   * 查询全部条数
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String favouritesLabelListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("isDelete", 0);
      if (yourFavouritesPojo != null) {
        params.put("name", yourFavouritesPojo.getName());
      }
      int i = yourFavouritesService.countBy(params);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 查询全部内容
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String favouritesLabelListAll() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("pageSize", 10);
      params.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      params.put("isDelete", 0);
      params.put("orderBy", "update_date desc,create_date desc");
      if (yourFavouritesPojo != null) {
        params.put("name", yourFavouritesPojo.getName());
      }
      List<YourFavouritesPojo> list = yourFavouritesService.listPage(params);
      JSONArray json = JSONArray.fromObject(list);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 查询全部内容Select
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String favouritesLabelListAllSelect() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("isDelete", 0);
      params.put("orderBy", "update_date desc,create_date desc");
      if (ageType != null) {
        params.put("ageType", ageType);
      }
      List<YourFavouritesPojo> list = yourFavouritesService.listPage(params);
      JSONArray json = JSONArray.fromObject(list);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 根据id删除
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String delFavouritesLabel() throws Exception {
    try {
      if (yourFavouritesPojo != null) {
        yourFavouritesService.delete(yourFavouritesPojo.getId());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("favId", yourFavouritesPojo.getId());
        List<YourFavouritesDetailPojo> list = yourFavouritesDetailService.listPage(params);
        if (list.size() > 0) {
          for (YourFavouritesDetailPojo y : list) {
            yourFavouritesDetailService.delete(y.getId());
          }
        }
        FileUtil.alertMessageBySkip("删除成功！", "favouritesLabelList.do");
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "favouritesLabelList.do");
    }
    return null;
  }

  /**
   * 
   * 跳转编辑标签页面
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String goUpdateFavouritesLabel() throws Exception {
    if (yourFavouritesPojo != null) {
      yourFavouritesPojo = yourFavouritesService.getById(yourFavouritesPojo.getId());
      if (yourFavouritesPojo.getAgeType() != null && yourFavouritesPojo.getAgeType().length() > 0) {
        String[] temp =
            yourFavouritesPojo.getAgeType().substring(1, yourFavouritesPojo.getAgeType().length())
                .split(":");
        if (temp != null && temp.length > 0 && temp[0].length() > 0) {
          ageTypes = temp;
        } else {
          ageTypes = null;
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 编辑标签
   * 
   * @return
   * @throws Throwable
   * @throw
   * @return String
   */
  public String updateFavouritesLabel() throws Throwable {
    try {
      if (yourFavouritesPojo != null) {
        // Map<String, Object> params = new HashMap<String, Object>();
        // params.put("name", yourFavouritesPojo.getName());
        // int n = yourFavouritesService.countBy(params);
        // if (n > 0) {
        // FileUtil.alertMessageBySkip("标签名重复，请重新赋值！",
        // "goUpdateFavouritesLabel.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId());
        // return null;
        // } else {
        if (ageTypes != null && ageTypes.length > 0 && ageTypes[0].length() > 0) {
          String ageType = ":";

          for (String a : ageTypes) {
            ageType += a + ":";
          }

          yourFavouritesPojo.setAgeType(ageType);
        } else {
          yourFavouritesPojo.setAgeType("");
        }
        if (upfile != null && upfile.length() > 0) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/favouritesLabel")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/favouritesLabel/", upfile);

          yourFavouritesPojo.setImage(file_name);
        }
        int i = yourFavouritesService.update(yourFavouritesPojo);
        if (i > 0) {
          FileUtil.alertMessageBySkip("编辑成功！", "favouritesLabelList.do");
        } else {
          FileUtil.alertMessageBySkip("编辑失败！", "favouritesLabelList.do");
        }
      }
      // }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "favouritesLabelList.do");
    }
    return null;
  }

  /**
   * 
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String goAddFavouritesLabel() throws Exception {
    return SUCCESS;
  }

  /**
   * 
   * 新增标签
   * 
   * @return
   * @throws Throwable
   * @throw
   * @return String
   */
  public String addFavouritesLabel() throws Throwable {
    try {
      if (yourFavouritesPojo != null) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session.get("yourFavouritesPojo") != null) {
          session.remove("yourFavouritesPojo");
        }
        if (session.get("ageTypes") != null) {
          session.remove("ageTypes");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", yourFavouritesPojo.getName());
        int n = yourFavouritesService.countBy(params);
        if (n > 0) {
          session.put("yourFavouritesPojo", yourFavouritesPojo);
          session.put("ageTypes", ageTypes);
          FileUtil.alertMessageBySkip("标签名重复，请重新赋值！", "goAddFavouritesLabel.do");
          // session.remove("yourFavouritesPojo");
          // session.remove("ageTypes");
          return null;
        } else {
          if (ageTypes != null && ageTypes.length > 0 && ageTypes[0].length() > 0) {
            String ageType = ":";

            for (String a : ageTypes) {
              ageType += a + ":";
            }

            yourFavouritesPojo.setAgeType(ageType);
          }
          if (upfile != null && upfile.length() > 0) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/favouritesLabel")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/favouritesLabel/", upfile);

            yourFavouritesPojo.setImage(file_name);
          }
          int i = yourFavouritesService.add(yourFavouritesPojo);
          if (i > 0) {
            FileUtil.alertMessageBySkip("新增成功！", "favouritesLabelList.do");
          } else {
            FileUtil.alertMessageBySkip("新增失败！", "favouritesLabelList.do");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "favouritesLabelList.do");
    }
    return null;
  }

  /**
   * 
   * 有你喜欢-笔记列表页面/条数
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String userCirclePostFavourites() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    if (yourFavouritesPojo != null) {
      map.put("type", yourFavouritesPojo.getContentType());
      map.put("favId", yourFavouritesPojo.getId());
      if (yourFavouritesPojo.getContentType() == 1) {
        int i = yourFavouritesDetailService.getProductFavouritesCount(map);
        page.setRowCount(i);
        return "product";
      } else if (yourFavouritesPojo.getContentType() == 3) {
        int i = yourFavouritesDetailService.getProductTypeFavouritesCount(map);
        page.setRowCount(i);
        return "producttype";
      }
    }
    if (userCirclePostPojo != null) {
      map.put("title", userCirclePostPojo.getTitle());
      map.put("status", userCirclePostPojo.getStatus());
      map.put("ageType", userCirclePostPojo.getAgeType());
      map.put("skillType", userCirclePostPojo.getSkillType());
      map.put("secSkillType", userCirclePostPojo.getSecSkillType());
      map.put("productType", userCirclePostPojo.getProductType());
      map.put("createDateStartStr", userCirclePostPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userCirclePostPojo.getCreateDateEndStr());
      map.put("userName", userCirclePostPojo.getUserName());
    }
    int i = yourFavouritesDetailService.getUserCirclePostFavouritesCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  /**
   * 
   * 有你喜欢-商品条数
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String userCirclePostFavouritesProCount() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    if (yourFavouritesPojo != null) {
      map.put("type", yourFavouritesPojo.getContentType());
      map.put("favId", yourFavouritesPojo.getId());
      int i = yourFavouritesDetailService.getProductFavouritesCount(map);
      page.setRowCount(i);
    }
    return SUCCESS;
  }

  /**
   * 
   * 有你喜欢-产品分类条数
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String userCirclePostFavouritesProTypeCount() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    if (yourFavouritesPojo != null) {
      map.put("type", yourFavouritesPojo.getContentType());
      map.put("favId", yourFavouritesPojo.getId());
      int i = yourFavouritesDetailService.getProductTypeFavouritesCount(map);
      page.setRowCount(i);
    }
    return SUCCESS;
  }

  /**
   * 
   * 有你喜欢-笔记列表
   * 
   * @return
   * @throw
   * @return String
   */
  public String userCirclePostFavouritesList() {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (yourFavouritesPojo != null) {
      map.put("favId", yourFavouritesPojo.getId());
      map.put("type", yourFavouritesPojo.getContentType());
      map.put("orderBy", "yfd.update_date desc,yfd.create_date desc");
      if (yourFavouritesPojo.getContentType() == 1) {
        List<ProductPojo> list = yourFavouritesDetailService.getProductFavouritesList(map);
        JSONArray json = JSONArray.fromObject(list);
        page.setResult(json.toString());
        return SUCCESS;
      } else if (yourFavouritesPojo.getContentType() == 3) {
        List<ProductPojo> list = yourFavouritesDetailService.getProductTypeFavouritesList(map);
        JSONArray json = JSONArray.fromObject(list);
        page.setResult(json.toString());
        return SUCCESS;
      }
    }
    if (userCirclePostPojo != null) {
      map.put("title", userCirclePostPojo.getTitle());
      map.put("status", userCirclePostPojo.getStatus());
      map.put("ageType", userCirclePostPojo.getAgeType());
      map.put("skillType", userCirclePostPojo.getSkillType());
      map.put("secSkillType", userCirclePostPojo.getSecSkillType());
      map.put("productType", userCirclePostPojo.getProductType());
      map.put("createDateStartStr", userCirclePostPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userCirclePostPojo.getCreateDateEndStr());
      map.put("userName", userCirclePostPojo.getUserName());
    }
    List<UserCirclePostPojo> list =
        yourFavouritesDetailService.getUserCirclePostFavouritesList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 
   * 根据id批量删除
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String delFavouritesDetailLabel() throws Exception {
    try {
      if (tids != null && tids.length > 0) {
        for (String id : tids) {
          Long idLong = new Long(id);
          yourFavouritesDetailService.delete(idLong);
        }
        FileUtil.alertMessageBySkip("取消成功！",
            "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
                + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
      } else {
        FileUtil.alertMessageBySkip("未选取任何笔记！",
            "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
                + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
        return null;
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取消失败！",
          "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
              + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
    }
    return null;
  }

  /**
   * 
   * 根据id删除
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String delFavouritesDetailLabelOne() throws Exception {
    try {
      if (yourFavouritesDetailPojo != null) {
        yourFavouritesDetailService.delete(yourFavouritesDetailPojo.getId());
      }
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 
   * 有你喜欢-笔记列表添加
   * 
   * @return
   * @throw
   * @return Srting
   */
  public String doUserCirclePostFavouritesAdd() {
    try {
      if (tids != null && tids.length > 0 && yourFavouritesPojo != null) {
        int count = 0, num = 0;
        for (String id : tids) {
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("favId", yourFavouritesPojo.getId());
          params.put("type", 2);
          params.put("postId", id);
          count = yourFavouritesDetailService.countBy(params);
          if (count > 0) {
            num++;
          }
        }
        if (num > 0) {
          FileUtil.alertMessageBySkip("其中有" + num + "条笔记已添加过！",
              "userCirclePostFavouritesAdd.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
                  + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
          return null;
        }
        for (String id : tids) {
          Long idLong = new Long(id);
          YourFavouritesDetailPojo yourFavouritesDetail = new YourFavouritesDetailPojo();
          yourFavouritesDetail.setFavId(yourFavouritesPojo.getId());
          yourFavouritesDetail.setType(2);;
          yourFavouritesDetail.setPostId(idLong);
          yourFavouritesDetailService.add(yourFavouritesDetail);
        }
        FileUtil.alertMessageBySkip("选取成功！",
            "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
                + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
      } else {
        FileUtil.alertMessageBySkip("未选取任何笔记！",
            "userCirclePostFavouritesAdd.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
                + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
        return null;
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("选取失败！",
          "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
              + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
    }
    return null;
  }

  /**
   * 
   * 有你喜欢-商品列表添加
   * 
   * @return
   * @throw
   * @return String
   */
  public String doProductFavouritesAdd() {
    try {
      if (yourFavouritesPojo != null && yourFavouritesDetailPojo != null) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("favId", yourFavouritesPojo.getId());
        params.put("type", 1);
        params.put("productId", yourFavouritesDetailPojo.getProductId());
        params.put("activityId", yourFavouritesDetailPojo.getActivityId());
        int count = yourFavouritesDetailService.countBy(params);
        if (count > 0) {
          FileUtil.alertMessageBySkip("该商品已添加过！", "productFavouritesAdd.do?yourFavouritesPojo.id="
              + yourFavouritesPojo.getId() + "&yourFavouritesPojo.contentType="
              + yourFavouritesPojo.getContentType());
          return null;
        }
        yourFavouritesDetailPojo.setFavId(yourFavouritesPojo.getId());
        yourFavouritesDetailPojo.setType(1);
        yourFavouritesDetailService.add(yourFavouritesDetailPojo);
        FileUtil.alertMessageBySkip("选取成功！",
            "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
                + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
        // } else {
        // FileUtil.alertMessageBySkip("未选取任何商品！",
        // "productFavouritesAdd.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
        // + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
        // return null;
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("选取失败！",
          "userCirclePostFavourites.do?yourFavouritesPojo.id=" + yourFavouritesPojo.getId()
              + "&yourFavouritesPojo.contentType=" + yourFavouritesPojo.getContentType());
    }
    return null;
  }

  /**
   * 
   * 有你喜欢笔记查看-修改序号
   * 
   * @return
   * @throw
   * @return String
   */
  public String doPostFavouritesUpdateSorting() {
    try {
      if (yourFavouritesDetailPojo != null) {
        yourFavouritesDetailService.update(yourFavouritesDetailPojo);
        this.result = "1";
      } else {
        this.result = "0";
      }
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 
   * 添加产品分类ID
   * 
   * @return
   * @throw
   * @return String
   */
  public String doAddProductTypeId() {
    try {
      if (yourFavouritesDetailPojo != null) {
        yourFavouritesDetailPojo.setType(3);
        yourFavouritesDetailService.add(yourFavouritesDetailPojo);
        this.result = "1";
      } else {
        this.result = "0";
      }
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }
}
