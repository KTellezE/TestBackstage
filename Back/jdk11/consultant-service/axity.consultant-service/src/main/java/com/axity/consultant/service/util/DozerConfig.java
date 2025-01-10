package com.axity.consultant.service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;

/**
 * Dozer configuration class
 * 
 * @author KevinTellez@axity.com
 */
@Configuration
@Component
public class DozerConfig
{
  @Bean
  public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean( @Value("classpath*:mappings/*mappings.xml")
  Resource[] resources ) throws Exception
  {
    final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
    dozerBeanMapperFactoryBean.setMappingFiles( resources );
    return dozerBeanMapperFactoryBean;
  }
}
