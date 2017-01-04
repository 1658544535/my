package com.tzmb2c.utils.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Title:分页工具类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月12日
 * @Version:1.1.0
 */
public class Pagination<T> implements Serializable {

  /**
   * 序列化ID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 每页默认10条数据
   */
  protected int pageSize = 10;

  /**
   * 当前页
   */
  protected int currentPage = 1;

  /**
   * 总页数
   */
  protected int totalPages = 0;

  /**
   * 总数据数
   */
  protected int totalRows = 0;

  /**
   * 每页的起始行数
   */
  protected int pageStartRow = 0;

  /**
   * 每页显示数据的终止行数
   */
  protected int pageEndRow = 0;

  /**
   * 是否有下一页
   */
  boolean next = false;

  /**
   * 是否有前一页
   */
  boolean previous = false;

  private List<T> content = new ArrayList<T>();

  public Pagination() {

  }

  public Pagination(int rows) {

    this.init(rows, this.pageSize);
  }

  public Pagination(int rows, int pageSize) {

    this.init(rows, pageSize);
  }

  /**
   * 初始化分页参数:需要先设置totalRows
   */
  public void init(int rows, int pageSize) {

    this.pageSize = pageSize;

    this.totalRows = rows;

    if (totalRows % pageSize == 0) {
      totalPages = totalRows / pageSize;
    } else {
      totalPages = totalRows / pageSize + 1;
    }

  }

  public void init(int rows, int pageSize, int currentPage) {

    this.pageSize = pageSize;

    this.totalRows = rows;

    if (totalRows % pageSize == 0) {
      totalPages = totalRows / pageSize;
    } else {
      totalPages = totalRows / pageSize + 1;
    }
    if (currentPage != 0) {
      gotoPage(currentPage);
    }
  }

  /**
   * 计算当前页的取值范围：pageStartRow和pageEndRow
   */
  private void calculatePage() {

    previous = currentPage - 1 > 0;

    next = currentPage < totalPages;

    if (currentPage * pageSize < totalRows) { // 判断是否为最后一页
      pageEndRow = currentPage * pageSize;
      pageStartRow = pageEndRow - pageSize;
    } else {
      pageEndRow = totalRows;
      pageStartRow = pageSize * (totalPages - 1);
    }

  }

  /**
   * 直接跳转到指定页数的页面
   * 
   * @param page
   */
  public void gotoPage(int page) {

    currentPage = page;

    calculatePage();

    // debug1();
  }

  public String debugString() {

    return "共有数据数:" + totalRows + "共有页数:" + totalPages + "当前页数为:" + currentPage + "是否有前一页:"
        + previous + "是否有下一页:" + next + "开始行数:" + pageStartRow + "终止行数:" + pageEndRow;

  }

  public int getCurrentPage() {

    return currentPage;
  }

  public boolean isNext() {

    return next;
  }

  public boolean isPrevious() {

    return previous;
  }

  public int getPageEndRow() {

    return pageEndRow;
  }

  public int getPageSize() {

    return pageSize;
  }

  public int getPageStartRow() {

    return pageStartRow;
  }

  public int getTotalPages() {

    return totalPages;
  }

  public int getTotalRows() {

    return totalRows;
  }

  public void setTotalPages(int i) {

    totalPages = i;
  }

  public void setCurrentPage(int i) {

    currentPage = i;
  }

  public void setNext(boolean b) {

    next = b;
  }

  public void setPrevious(boolean b) {

    previous = b;
  }

  public void setPageEndRow(int i) {

    pageEndRow = i;
  }

  public void setPageSize(int i) {

    pageSize = i;
  }

  public void setPageStartRow(int i) {

    pageStartRow = i;
  }

  public void setTotalRows(int i) {

    totalRows = i;
  }

  public List<T> getContent() {

    return content;
  }

  public void setContent(List<T> content) {

    this.content = content;
  }

}
