package gkyrqy.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/bill")
public class ControllerTestA {
    private static Logger logger = LoggerFactory.getLogger(ControllerTestA.class);
    @RequestMapping("/bbb")
    public String test() {
        for (int i = 0; i < 1000; i++) {
            logger.info("ThreadName : "+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "bbb";
    }
}
