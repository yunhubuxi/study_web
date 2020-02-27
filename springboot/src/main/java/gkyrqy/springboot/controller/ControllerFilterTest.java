package gkyrqy.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
public class ControllerFilterTest {
    @RequestMapping("/aaa")
    public String test() {
        System.out.println(ControllerFilterTest.class.toString());
        return "filter";
    }
}
