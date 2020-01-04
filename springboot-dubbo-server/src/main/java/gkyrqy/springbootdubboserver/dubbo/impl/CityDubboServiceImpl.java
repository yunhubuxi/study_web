package gkyrqy.springbootdubboserver.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import gkyrqy.springbootdubboapi.api.CityDubboService;
import gkyrqy.springbootdubboapi.domain.City;
import org.springframework.stereotype.Component;

/**
 * 注册dubbo服务
 *
 * @Component注解和@Service注解必须都有
 */
@Component
@Service(version = "1.0.0", group = "gkyrq")
public class CityDubboServiceImpl implements CityDubboService {

    @Override
    public City findCityByName(String cityName) {
        return new City(1L, 2L, cityName, "是我的故乡");
    }
}
