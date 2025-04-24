package com.example.consumer;

import com.example.consumer.config.LoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  // 启用 Eureka 客户端
@EnableFeignClients  // 启用 OpenFeign
@LoadBalancerClient(name = "SERVICE-PROVIDER", configuration = LoadBalancerConfig.class)
public class ServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced  // 启用负载均衡支持，自动使用服务发现机制
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
