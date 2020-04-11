package gkyrqy.springboot.config;

import gkyrqy.springboot.service.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationDemo2 {

    @Bean //向容器之中注入Bean组件
    public Person person1() {
        Person person = new Person();
        person.setName("ConfigurationDemo2");
        person.setAge(12);
        return person;
    }

}
