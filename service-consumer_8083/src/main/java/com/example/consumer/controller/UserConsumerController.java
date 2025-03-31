package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/consumer/users")
public class UserConsumerController {

    private static final String PROVIDER_SERVICE_NAME = "service-provider";
    private static final String BASE_URL = "http://" + PROVIDER_SERVICE_NAME + "/users";

    @Autowired
    private RestTemplate restTemplate;

    // 1. GET - 获取用户
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable int id) {
        try {
            return ResponseEntity.ok(restTemplate.getForObject(BASE_URL + "/" + id, String.class));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("获取用户失败: " + e.getMessage());
        }
    }

    // 2. POST - 创建用户
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> user) {
        try {
            return ResponseEntity.ok(restTemplate.postForObject(BASE_URL, user, String.class));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("创建用户失败: " + e.getMessage());
        }
    }

    // 3. PUT - 更新用户
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody Map<String, String> user) {
        try {
            restTemplate.put(BASE_URL + "/" + id, user);
            return ResponseEntity.ok("用户更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("更新用户失败: " + e.getMessage());
        }
    }

    // 4. DELETE - 删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            restTemplate.delete(BASE_URL + "/" + id);
            return ResponseEntity.ok("用户删除成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("删除用户失败: " + e.getMessage());
        }
    }
}
