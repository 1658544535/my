package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 年龄与能力连接表 -- age_skill_link
 */
public class AgeSkillLinkPojo extends SuperPojo {
    private static final long serialVersionUID = 1L;
    private Long id;		//id
    private Long ageId;		//年龄id
    private Long skillId;	//能力id
    private Long secondSkillId; //次能力id
    private Long productId; //商品id
    private String ageName;//年龄名称
    
    private String skillName;	//相应的能力名称
    private Long skillValue;	//相应能力的字典值
    
    private String secondSkillName;   //相应的次能力名称
    private Long secondSkillValue;    //相应次能力的字典值
    
    private String productName;   //相应的商品名称
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAgeId() {
		return ageId;
	}
	public void setAgeId(Long ageId) {
		this.ageId = ageId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Long getSkillValue() {
		return skillValue;
	}
	public void setSkillValue(Long skillValue) {
		this.skillValue = skillValue;
	}
    public Long getSecondSkillId() {
      return secondSkillId;
    }
    public void setSecondSkillId(Long secondSkillId) {
      this.secondSkillId = secondSkillId;
    }
    public Long getProductId() {
      return productId;
    }
    public void setProductId(Long productId) {
      this.productId = productId;
    }
    public String getSecondSkillName() {
      return secondSkillName;
    }
    public void setSecondSkillName(String secondSkillName) {
      this.secondSkillName = secondSkillName;
    }
    public Long getSecondSkillValue() {
      return secondSkillValue;
    }
    public void setSecondSkillValue(Long secondSkillValue) {
      this.secondSkillValue = secondSkillValue;
    }
    public String getProductName() {
      return productName;
    }
    public void setProductName(String productName) {
      this.productName = productName;
    }
    public String getAgeName() {
      return ageName;
    }
    public void setAgeName(String ageName) {
      this.ageName = ageName;
    }
	
	
	



    
  
  
}
