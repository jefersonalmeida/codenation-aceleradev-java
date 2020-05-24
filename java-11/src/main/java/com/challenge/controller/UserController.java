package com.challenge.controller;

import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable("userId") Long userId) {
        Optional<User> userOptional = this.userService.findById(userId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> listByAccelerationNameOrCompanyId(
            @RequestParam(name = "accelerationName", required = false) Optional<String> accelerationName,
            @RequestParam(name = "companyId", required = false) Optional<Long> companyId
    ) {
        List<User> result = Collections.emptyList();
        if (accelerationName.isPresent()) {
            result = this.userService.findByAccelerationName(accelerationName.get());
        } else if (companyId.isPresent()) {
            result = this.userService.findByCompanyId(companyId.get());
        }
        return ResponseEntity.ok(result);
    }
}
