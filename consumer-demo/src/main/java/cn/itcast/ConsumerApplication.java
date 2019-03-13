package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient // 开启eureka 服务
public class ConsumerApplication {

    @Bean
    @LoadBalanced // 开启ribbon负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String []args){
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
