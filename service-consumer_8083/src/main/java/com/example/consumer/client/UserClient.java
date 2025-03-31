package com.example.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "service-provider")  // 指定服务提供者
public interface UserClient {

    @GetMapping("/users/{id}")
    String getUser(@PathVariable("id") int id);

    @PostMapping("/users")
    String createUser(@RequestBody Map<String, String> user);

    @PutMapping("/users/{id}")
    String updateUser(@PathVariable("id") int id, @RequestBody Map<String, String> user);

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable("id") int id);
}
