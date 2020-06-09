package gkyrqy.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerThreadLocal {
    static ThreadLocal<Integer> c = ThreadLocal.withInitial(() -> 0);
    void add () throws InterruptedException {
        Thread.sleep(100);
        c.set(c.get() + 1);
    }

    @RequestMapping("/stat")
    public Integer stat() {
        return c.get();
    }


}
