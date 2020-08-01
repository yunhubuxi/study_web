package gkyrqy.springboot;

import author.AuthorProperties;
import author.AuthorServer;
import gkyrqy.springboot.aop.TestBean;
import gkyrqy.springboot.service.BaseDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApplicationSample {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringApplicationSample.class);
        System.out.println(SpringApplicationSample.class.getResource("/"));
        applicationContext.getBean(TestBean.class).test();
        System.out.println(applicationContext.getBean(BaseDemo.class).getBaseTest());
        System.out.println(applicationContext.getBean(BaseDemo.class).getBaseTestTwo());
        System.out.println(applicationContext.getBean(AuthorServer.class).getAuthor());
        System.out.println(applicationContext.getBean(AuthorProperties.class).toString());
    }
}
