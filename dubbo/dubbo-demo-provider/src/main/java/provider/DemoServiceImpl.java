package provider;

import api.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        name = "aaa";
        return "Hello " + name;
    }
}
