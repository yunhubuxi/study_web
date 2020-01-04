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

    // 因为提供者定义了version，所以消费者必须定义version，否则引用不到
    @Reference(group = "gkyrq", version = "1.0.0", interfaceClass = CityDubboService.class)
    CityDubboService cityDubboService;

    public void printCity() {
        String cityName = "温岭";
        City city = cityDubboService.findCityByName(cityName);
        System.out.println(city.toString());
    }

    public CityDubboService getCityDubboService() {
        return cityDubboService;
    }
}
