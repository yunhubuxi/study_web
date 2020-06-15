package gkyrqy.springboot.controller;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Scope("prototype")
@RestController
@RequestMapping("/bill")
public class ControllerTest {

    @Value("${test}")
    private String test;

    @Autowired
    private Demo demo;

    @RequestMapping("/aaa")
    public String test() {
        System.out.println(ControllerTest.class.toString());
        System.out.println(demo.hashCode());
        demo.sayHello();
        return "aaa";
    }
}
