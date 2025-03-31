package com.example.consumer.controller;

import com.example.consumer.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/consumer/users")
public class UserConsumerController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userClient.getUser(id));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> user) {
        return ResponseEntity.ok(userClient.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody Map<String, String> user) {
        return ResponseEntity.ok(userClient.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(userClient.deleteUser(id));
    }
}
