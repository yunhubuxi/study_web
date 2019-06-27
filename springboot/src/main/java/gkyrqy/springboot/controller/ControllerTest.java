package gkyrqy.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class ControllerTest {

    @RequestMapping("/aaa")
    public String test() {
        return "aaa";
    }
}
