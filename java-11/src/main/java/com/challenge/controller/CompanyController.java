package com.challenge.controller;

import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceInterface companyService;

    @GetMapping
    public ResponseEntity<List<Company>> listByAccelerationIdOrUserId(
            @RequestParam(name = "accelerationId", required = false) Optional<Long> accelerationId,
            @RequestParam(name = "userId", required = false) Optional<Long> userId
    ) {
        List<Company> result = Collections.emptyList();
        if (accelerationId.isPresent()) {
            result = this.companyService.findByAccelerationId(accelerationId.get());
        } else if (userId.isPresent()) {
            result = this.companyService.findByUserId(userId.get());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        Optional<Company> companyOptional = this.companyService.findById(id);
        return companyOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
