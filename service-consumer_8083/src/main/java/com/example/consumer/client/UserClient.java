package com.example.consumer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserClient {
    @Autowired
    private RestTemplate restTemplate;

    // 使用服务名称（负载均衡）
    private static final String PROVIDER_URL = "http://service-provider/users";

    // GET 请求
    public String getUser(int id) {
        return restTemplate.getForObject(PROVIDER_URL + "/" + id, String.class);
    }

    // POST 请求
    public String createUser(String name) {
        Map<String, String> request = new HashMap<>();
        request.put("name", name);
        return restTemplate.postForObject(PROVIDER_URL, request, String.class);
    }

    // PUT 请求
    public String updateUser(int id, String name) {
        Map<String, String> request = new HashMap<>();
        request.put("name", name);
        restTemplate.put(PROVIDER_URL + "/" + id, request);
        return "用户更新成功";
    }

    // DELETE 请求
    public String deleteUser(int id) {
        restTemplate.delete(PROVIDER_URL + "/" + id);
        return "用户删除成功";
    }
}
