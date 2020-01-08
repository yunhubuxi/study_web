package gkyrqy.springbootdubboclient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import gkyrqy.springbootdubboapi.api.CityDubboService;
import gkyrqy.springbootdubboclient.dubbo.CityDubboConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableDubbo
@SpringBootApplication
public class SpringbootDubboClientApplication {

    @Reference(group = "gkyrq",version = "1.0.0")
    CityDubboService cityDubboService;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootDubboClientApplication.class, args);

        // 通过本地service调用，依赖注入代理对象
        CityDubboConsumerService cityService = applicationContext.getBean(CityDubboConsumerService.class);
        cityService.printCity();

        // 可通过cityService.getCityDubboService()获取到代理对象
        CityDubboService cityDubboService = cityService.getCityDubboService();
        System.out.println(cityDubboService.findCityByName("铁岭"));

        // 直接获取代理对象，会失败，不能直接获取代理对象
        // CityDubboService cityDubboService = applicationContext.getBean(CityDubboService.class);
        // System.out.println(cityDubboService.findCityByName("铁岭").toString());


    }

}
