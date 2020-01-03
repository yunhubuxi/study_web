package provider;

import api.DemoService;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        name = "aaa";
        return "Hello " + name;
    }

    @Override
    public Map sayHello(Map map) {
        System.out.println(map.toString());
        System.out.println(JSONObject.toJSONString(map));
        return map;
    }
}
