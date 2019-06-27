package gkyrqy.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class ControllerTestA {
    @RequestMapping("/bbb")
    public String test() {
        return "bbb";
    }
}
