package gkyrqy.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseDemo {
    @Value("baseTest")
    private String baseTest;

    public String getBaseTest() {
        return baseTest;
    }

    public void setBaseTest(String baseTest) {
        this.baseTest = baseTest;
    }
}
