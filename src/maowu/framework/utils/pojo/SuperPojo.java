package maowu.framework.utils.pojo;

import java.io.Serializable;
import java.util.Date;

import com.tzmb2c.web.pojo.SysLoginPojo;

public class SuperPojo implements Serializable {

  // 版本（0：中文；1：英文；）
  public static final Integer CN = 0;
  public static final Integer ENG = 1;

  protected String remarks; // 备注
  protected Long createBy; // 创建者
  protected Date createDate;// 创建日期
  protected Long updateBy; // 更新者
  protected Date updateDate;// 更新日期
  protected Integer version; // 版本

  public SuperPojo() {
    super();
    this.version = CN;
  }

  public void prePersist(SysLoginPojo loginPojo) {
    this.updateBy = loginPojo.getId();
    this.updateDate = new Date();
    this.createBy = this.updateBy;
    this.createDate = this.updateDate;
  }

  public void preUpdate(SysLoginPojo loginPojo) {
    this.updateBy = loginPojo.getId();
    this.updateDate = new Date();
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Long getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

}
