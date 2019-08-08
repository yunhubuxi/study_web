package gkyrqy.springboot.controller;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class ControllerTest {

    @Value("${test}")
    private String test;

    @Autowired
    private Demo demo;

    @RequestMapping("/aaa")
    public String test() {
        System.out.println(test+"ControllerTest");
        demo.sayHello();
        return "aaa";
    }
}
