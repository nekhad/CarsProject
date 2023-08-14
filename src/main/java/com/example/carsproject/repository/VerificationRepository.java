package com.example.carsproject.repository;

import com.example.carsproject.entity.User;
import com.example.carsproject.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification,Integer> {
    Optional<Verification> findByVerificationCode(String verificationCode);
}
