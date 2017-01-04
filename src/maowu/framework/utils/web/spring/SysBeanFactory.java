package maowu.framework.utils.web.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.tzmb2c.utils.BeanFacade;

/**
 * 
 * @Title:系统第三方bean工厂，用于获取容器中的实例
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月7日
 * @Version:1.1.0
 */
public class SysBeanFactory implements BeanFactoryAware {
  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    // TODO Auto-generated method stub
    BeanFacade.setBeanFactory(beanFactory);
  }

}
