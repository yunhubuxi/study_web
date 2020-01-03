package gkyrqy.springbootdubboclient;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import gkyrqy.springbootdubboclient.dubbo.CityDubboConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
@EnableDubbo
public class SpringbootDubboClientApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootDubboClientApplication.class, args);
        CityDubboConsumerService cityService = applicationContext.getBean(CityDubboConsumerService.class);
        cityService.printCity();
    }

}
