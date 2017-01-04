package com.tzmb2c.web.pojo;

/**
 * 专场满减满折
 */
public class SpecialDiscountPojo implements Comparable<SpecialDiscountPojo> {
  /**
   * 满xx
   */
  private String om;
  /**
   * 减/折xx
   */
  private String m;

  public String getOm() {
    return om;
  }

  public void setOm(String om) {
    this.om = om;
  }

  public String getM() {
    return m;
  }

  public void setM(String m) {
    this.m = m;
  }

  @Override
  public int compareTo(SpecialDiscountPojo o) {
    if (this.om != null && o != null && o.getOm() != null) {
      return o.getOm().compareTo(this.om);
    }
    return 0;
  }
}
