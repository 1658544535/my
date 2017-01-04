package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-19 Info表 行业资讯、市场动态
 * 
 * @author creazylee
 */

public class InfoPojo extends SuperPojo {

  private Long id;// 编号id
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private Integer displayOrder;// 排序
  private Integer type;// 类型(取业务字典：1行业资讯2.市场动态3.消息)
  private String typeName;// 类型字符
  private String title;// 标题
  private String content;// 内容
  private String image;// 图片路径
  private String author;// 图片路径
  private Date createDate;// 创建日期
  private String createDateStr;
  private Long nextId;// 文章下一篇id
  private Long preId;// 文章上一篇id
  private String nextTittle;// 文章下一篇标题
  private String preTittle;// 文章上一篇标题
  private String create_date;
  private int is_reader;

  public Long getNextId() {
    return nextId;
  }

  public void setNextId(Long nextId) {
    this.nextId = nextId;
  }

  public Long getPreId() {
    return preId;
  }

  public void setPreId(Long preId) {
    this.preId = preId;
  }

  public String getNextTittle() {
    return nextTittle;
  }

  public void setNextTittle(String nextTittle) {
    this.nextTittle = nextTittle;
  }

  public String getPreTittle() {
    return preTittle;
  }

  public void setPreTittle(String preTittle) {
    this.preTittle = preTittle;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.createDateStr = StringUtil.dateToString(this.createDate);
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getCreate_date() {
    return create_date;
  }

  public void setCreate_date(String create_date) {
    this.create_date = create_date;
  }

  public int getIs_reader() {
    return is_reader;
  }

  public void setIs_reader(int is_reader) {
    this.is_reader = is_reader;
  }


}
