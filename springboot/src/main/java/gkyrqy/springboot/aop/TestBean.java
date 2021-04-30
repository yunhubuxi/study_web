package gkyrqy.springboot.aop;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

    private String testStr = "testStr";

    public void test() {
        System.out.println("test_test");
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
}
