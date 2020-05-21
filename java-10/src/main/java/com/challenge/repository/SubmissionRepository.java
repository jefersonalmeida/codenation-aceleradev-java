package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {

    @Query("select coalesce(max(s.score), 0) from Submission s " +
            "where s.id.challenge.id = :id"
    )
    BigDecimal findHigherScoreByChallengeId(@Param("id") Long challengeId);

    @Query("select s from Submission s, Acceleration a " +
            "where s.id.challenge.id = :challengeId " +
            "and a.id = :accelerationId"
    )
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);
}
