package com.tzmb2c.utils;

import org.springframework.beans.factory.BeanFactory;

/**
 * 
 * @Title:获取容器中的实例
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月7日
 * @Version:1.1.0
 */
public class BeanFacade {
  public static BeanFactory beanFactory;

  public static void setBeanFactory(BeanFactory beanFactory) {
    BeanFacade.beanFactory = beanFactory;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getBean(Class<T> clazz) {
    return (T) beanFactory.getBean(getName(clazz));
  }

  private static String getName(Class<?> clazz) {
    String fullClassName = clazz.getName();
    return fullClassName.substring(fullClassName.lastIndexOf(".") + 1, fullClassName.length());
  }

}
