package consumer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.yonyoucloud.uretail.dao.Pager;
import model.People;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample {
    public static void main(String[] args) {
        Map map = new HashMap();

        User user1 = new User();
        user1.setName("张三1");
        user1.setAge(18);

        User user2 = new User();
        user2.setName("张三2");
        user2.setAge(18);

        User user3 = new User();
        user3.setName("张三3");
        user3.setAge(18);

        List<User> userList = Lists.newArrayList();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        map.put("user", user1);
        map.put("userList", userList);



        People people1 = new People();
        people1.setName("李四1");
        people1.setAge(18);

        People people2 = new People();
        people2.setName("李四2");
        people2.setAge(18);


        People people3 = new People();
        people3.setName("李四3");
        people3.setAge(18);

        List<People> peopleList = Lists.newArrayList();
        peopleList.add(people1);
        peopleList.add(people2);
        peopleList.add(people3);

        map.put("people", people1);
        map.put("peopleList", peopleList);

        Pager pager = new Pager();
        pager.setPageSize(10);
        pager.setPageIndex(2);
        map.put("pager", pager);


        String aaa = JSONObject.toJSONString(map);
        Object bbb = JSONObject.parseObject(aaa,Feature.IgnoreAutoType);
        System.out.println(bbb);
    }
}
