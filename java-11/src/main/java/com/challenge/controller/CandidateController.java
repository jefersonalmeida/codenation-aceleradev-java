package com.challenge.controller;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateServiceInterface candidateService;
    @Autowired
    private CandidateMapper candidateMapper;


    @GetMapping
    public ResponseEntity<List<CandidateDTO>> listByAccelerationIdOrCompanyId(
            @RequestParam(name = "accelerationId", required = false) Optional<Long> accelerationId,
            @RequestParam(name = "companyId", required = false) Optional<Long> companyId
    ) {
        List<CandidateDTO> result = Collections.emptyList();
        if (accelerationId.isPresent()) {
            result = candidateMapper.map(this.candidateService.findByAccelerationId(accelerationId.get()));
        } else if (companyId.isPresent()) {
            result = candidateMapper.map(this.candidateService.findByCompanyId(companyId.get()));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(
            @PathVariable("userId") Long userId,
            @PathVariable("accelerationId") Long accelerationId,
            @PathVariable("companyId") Long companyId
    ) {
        Optional<Candidate> candidateOptional = candidateService.findById(userId, companyId, accelerationId);
        return ResponseEntity.ok(candidateMapper.map(candidateOptional.orElse(new Candidate())));
    }
}
