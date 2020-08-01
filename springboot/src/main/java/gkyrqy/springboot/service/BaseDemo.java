package gkyrqy.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class BaseDemo {
    @Value("${baseTest}")
    private String baseTest;
    private String baseTestTwo = "${bb}";


    public String getBaseTestTwo() {
        return baseTestTwo;
    }

    public void setBaseTestTwo(String baseTestTwo) {
        this.baseTestTwo = baseTestTwo;
    }

    public String getBaseTest() {
        return baseTest;
    }

    public void setBaseTest(String baseTest) {
        this.baseTest = baseTest;
    }
}
