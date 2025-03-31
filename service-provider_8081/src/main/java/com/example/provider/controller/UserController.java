package com.example.provider.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, String> userStore = new ConcurrentHashMap<>();

    // 1. 获取用户信息
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Integer id) {
        return userStore.containsKey(id) ?
                ResponseEntity.ok(userStore.get(id)) :
                ResponseEntity.status(404).body("用户不存在");
    }

    // 2. 创建用户
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> user) {
        Integer id = Integer.parseInt(user.get("id"));
        String name = user.get("name");
        userStore.put(id, name);
        return ResponseEntity.ok("创建成功: " + name);
    }

    // 3. 更新用户
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody Map<String, String> user) {
        if (!userStore.containsKey(id)) {
            return ResponseEntity.status(404).body("用户不存在");
        }
        userStore.put(id, user.get("name"));
        return ResponseEntity.ok("更新成功: " + user.get("name"));
    }

    // 4. 删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (!userStore.containsKey(id)) {
            return ResponseEntity.status(404).body("用户不存在");
        }
        userStore.remove(id);
        return ResponseEntity.ok("删除成功");
    }
}
