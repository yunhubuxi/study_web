package gkyrqy.springboot.controller;

import gkyrqy.springboot.interface1.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class ControllerAutowiredTest {

    @Autowired(required = false)
    protected List<Demo> demoList;

    @Autowired(required = false)
    protected Map<String,Demo> demoMap;

    @RequestMapping("/autowried")
    public String test() {
        System.out.println(demoList.toString());
        System.out.println(demoMap.toString());
        return "aaa";
    }


}
