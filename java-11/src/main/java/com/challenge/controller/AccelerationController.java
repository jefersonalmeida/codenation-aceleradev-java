package com.challenge.controller;

import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationServiceInterface accelerationService;

    @GetMapping("{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id) {
        Optional<Acceleration> accelerationOptional = accelerationService.findById(id);
        return accelerationOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> listByCompany(@RequestParam(name = "companyId") Long companyId) {
        return ResponseEntity.ok(accelerationService.findByCompanyId(companyId));
    }
}
