package gkyrqy.springboot.config;

import gkyrqy.springboot.service.Person;
import gkyrqy.springboot.service.UnifiedMetaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationDemo1 {
    @Bean //向容器之中注入Bean组件
    public Person person() {
        Person person = new Person();
        person.setName("ConfigurationDemo1");
        person.setAge(11);
        return person;
    }
    @Bean("unifiedMetaProperties")
    public UnifiedMetaProperties metaBeanFactory() {
        return UnifiedMetaProperties.getInstance();
    }



}
