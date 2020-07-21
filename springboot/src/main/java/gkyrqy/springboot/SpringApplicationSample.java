package gkyrqy.springboot;

import gkyrqy.springboot.aop.TestBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApplicationSample {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringApplicationSample.class);
        System.out.println(SpringApplicationSample.class.getResource("/"));
        applicationContext.getBean(TestBean.class).test();

    }
}
