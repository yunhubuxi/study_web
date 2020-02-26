package gkyrqy.springbootdubboserver;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用springboot的EnableDubboConfiguration注解
 * 如果使用dubbo中的EnableDubbo注解，则不行
 * EnableDubboConfig和EnableDubboConfiguration是不同的包中的不同的注解，作用也不一样
 * 使用EnableDubbo是使用注解驱动，但不是自动装配，需要自己制定扫描的包，怎样装配application，registry还没有搞清楚
 */

@SpringBootApplication
@EnableDubboConfiguration
public class SpringbootDubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboServerApplication.class, args);
    }

}
