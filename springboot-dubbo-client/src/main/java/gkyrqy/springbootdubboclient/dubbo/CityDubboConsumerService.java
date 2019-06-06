package gkyrqy.springbootdubboclient.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import gkyrqy.springbootdubboapi.api.CityDubboService;
import gkyrqy.springbootdubboapi.domain.City;
import org.springframework.stereotype.Component;

/**
 * 城市 Dubbo 服务消费者
 * <p>
 * Created by bysocket on 28/02/2017.
 */
@Component
public class CityDubboConsumerService {

    @Reference(registry = "zookeeper://172.20.56.136:2181")
    CityDubboService cityDubboService;

    public void printCity() {
        String cityName = "温岭";
        City city = cityDubboService.findCityByName(cityName);
        System.out.println(city.toString());
    }
}
