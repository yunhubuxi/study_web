package gkyrqy.springboot;

import org.springframework.beans.factory.Aware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        AbstractApplicationContext abstractApplicationContext;
        Aware aware;
        ApplicationContextAware applicationContextAware;
        ApplicationListener applicationListener;
    }

}
