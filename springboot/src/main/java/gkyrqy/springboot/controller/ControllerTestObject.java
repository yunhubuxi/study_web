package gkyrqy.springboot.controller;

import gkyrqy.springboot.service.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/object")
public class ControllerTestObject {
    @RequestMapping("/user")
    public Person test() {
        System.out.println(ControllerTest.class.toString());
        Person person = new Person();
        person.setAge(4);
        person.setName("sdf");
        return person;
    }
}
