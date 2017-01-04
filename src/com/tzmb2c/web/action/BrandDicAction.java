package com.tzmb2c.web.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.service.BrandDicService;

public class BrandDicAction extends SuperAction {
  @Autowired
  private BrandDicService brandDicService;
  private BrandDicPojo brandDicPojo;
  private List<BrandDicPojo> brandDicPojoList;
  private File upfile;
  private Integer option;// 用于判断操作类型

  public List<BrandDicPojo> getBrandDicList() {
    return brandDicPojoList;
  }

  public void setBrandDicList(List<BrandDicPojo> brandDicPojoList) {
    this.brandDicPojoList = brandDicPojoList;
  }

  public BrandDicPojo getBrandDicPojo() {
    return brandDicPojo;
  }

  public void setBrandDicPojo(BrandDicPojo brandDicPojo) {
    this.brandDicPojo = brandDicPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 查询全部条数
   */
  public String brandDicListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (brandDicPojo != null) {
        map.put("brand", brandDicPojo.getBrand());
        map.put("status", brandDicPojo.getStatus());
        if (option != null) {
          map.put("option", option);
        }
      }
      int i = brandDicService.findBrandDicCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String brandDicListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (brandDicPojo != null) {
        map.put("brand", brandDicPojo.getBrand());
        map.put("status", brandDicPojo.getStatus());
        if (option != null) {
          map.put("option", option);
        }
      }
      brandDicPojoList = brandDicService.findBrandDicList(map);
      JSONArray json = JSONArray.fromObject(brandDicPojoList);
      page.setRowCount(brandDicPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  public Integer getOption() {
    return option;
  }

  public void setOption(Integer option) {
    this.option = option;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delBrandDic() throws Exception {
    try {
      brandDicService.delBrandDic(brandDicPojo);
      FileUtil.alertMessageBySkip("删除成功！", "brandDicList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "brandDicList.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateBrandDic() throws Exception {
    if (brandDicPojo != null) {
      brandDicPojo = brandDicService.findBrandDicById(brandDicPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 编辑百度用户浏览记录
   * 
   * @return
   * @throws Throwable
   */
  public String updateBrandDic() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext()
                .getRealPath("/upfiles/businessCenter/brandDic") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic", upfile);
        brandDicPojo.setLogo(file_name);
      }
      brandDicService.updateBrandDic(brandDicPojo);
      FileUtil.alertMessageBySkip("提交成功！", "brandDicList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "brandDicList.do");
    }
    return null;
  }

  /**
   * 提交编辑的用户积分
   * 
   * @return
   */
  public String updateBrandDicOk() {
    try {
      brandDicService.updateBrandDic(brandDicPojo);
      FileUtil.alertMessageBySkip("修改成功！", "brandDicList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改失败！", "brandDicList.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String addBrandDic() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String addBrandDicOK() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext()
                .getRealPath("/upfiles/businessCenter/brandDic") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic", upfile);
        brandDicPojo.setLogo(file_name);
      }
      brandDicService.insertBrandDic(brandDicPojo);
      FileUtil.alertMessageBySkip("新增成功！", "brandDicList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "brandDicList.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkBrandDic() throws Exception {
    try {
      brandDicService.checkBrandDic(brandDicPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "brandDicList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "brandDicList.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckBrandDic() throws Exception {
    try {
      brandDicService.uncheckBrandDic(brandDicPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "brandDicList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "brandDicList.do");
    }
    return null;
  }
}
