package gkyrqy.springboot.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class BaseDemo implements InitializingBean {

    @Value("${baseTest}")
    private String baseTest = "eee";

    public String getBaseTest() {
        return baseTest;
    }

    public void setBaseTest(String baseTest) {
        this.baseTest = baseTest;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        baseTest = "hhh";
    }
}
