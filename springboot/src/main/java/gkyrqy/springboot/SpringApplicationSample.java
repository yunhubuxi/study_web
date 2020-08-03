package gkyrqy.springboot;

import author.AuthorProperties;
import author.AuthorServer;
import gkyrqy.test.scan.Demo;
import gkyrqy.springboot.aop.TestBean;
import gkyrqy.springboot.service.BaseDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(
        scanBasePackages = {
                "gkyrqy.test.scan",
                "gkyrqy.springboot.importconfig",
                "gkyrqy.springboot.aop",
                "gkyrqy.springboot.service"})
public class SpringApplicationSample {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringApplicationSample.class);
        System.out.println(SpringApplicationSample.class.getResource("/"));
        applicationContext.getBean(TestBean.class).test();
        System.out.println(applicationContext.getBean(AuthorServer.class).getAuthor());
        System.out.println(applicationContext.getBean(AuthorProperties.class).toString());
        System.out.println(applicationContext.getBean(BaseDemo.class).getBaseTest());
        System.out.println(applicationContext.getBean(Demo.class).toString());
        for (String beanDefinitionName : applicationContext.getBeanFactory().getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }
}
