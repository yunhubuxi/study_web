package gkyrqy.springbootdubboserver.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import gkyrqy.springbootdubboapi.api.TestProviderService;
import org.springframework.stereotype.Component;


/**
 * 注册dubbo服务
 *
 * @Component注解和@Service注解必须都有
 */
@Component
@Service(version = "1.0.0")
public class TestProviderServiceImpl implements TestProviderService {
    @Override
    public String Hello(String who) {
        return "Hello world ! Dear Programer " + who;
    }
}

