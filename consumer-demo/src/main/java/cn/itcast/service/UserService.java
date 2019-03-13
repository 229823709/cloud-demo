package cn.itcast.service;

import cn.itcast.pojo.User;
import cn.itcast.web.UserDao;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
/*
    public List<User> getUser (List<Long> ids ){

        List<User> userList =  new ArrayList<>();
        for (Long id : ids) {
            userList.add(userDao.queryUserById(id));
        }

        return  userList;
    }*/


    @Autowired
    private DiscoveryClient discoveryClient;// Eureka客户端，可以获取到服务实例信息

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getUser (List<Long> ids ){
        ArrayList<User> users = new ArrayList<>();

        List<ServiceInstance> instances = discoveryClient.getInstances("USER-SERVICE");

        // 因为只有一个UserService,因此我们直接get(0)获取
        ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息
        String baseUrl = "http://"+instance.getHost() + ":" + instance.getPort()+"/user/";
        ids.forEach(id -> {
            // 我们测试多次查询，
            users.add(this.restTemplate.getForObject(baseUrl + id, User.class));
            // 每次间隔500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return users;
    }
}
