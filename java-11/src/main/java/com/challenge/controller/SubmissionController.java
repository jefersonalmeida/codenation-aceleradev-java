package com.challenge.controller;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionServiceInterface submissionService;

    @Autowired
    private SubmissionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(
            @RequestParam(name = "challengeId") Long challengeId,
            @RequestParam(name = "accelerationId") Long accelerationId
    ) {
        return ResponseEntity.ok(mapper.map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId)));
    }

}
