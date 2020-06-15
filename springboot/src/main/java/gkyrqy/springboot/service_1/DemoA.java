package gkyrqy.springboot.service_1;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.annotation.Value;

public class DemoA implements Demo {

    @Value("${test}")
    private String test;

    @Override
    public void sayHello() {
        System.out.println(this.getClass());
        System.out.println("hello, I an Person");
    }
}
