package gkyrqy.springboot.service;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DemoA implements Demo {
    public void sayHello() {
        System.out.println("hello, I an DemoA");
    }
}
