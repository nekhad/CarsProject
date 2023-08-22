package com.example.carsproject.repository;

import com.example.carsproject.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification,String> {
    Optional<Verification> findByVerificationCode(String verificationCode);

//    @Query("select v.verificationCode from Verification v where v.status = 'A')
//            List<String> findVerificationCodesWithStatus()

    @Query("SELECT v.verificationCode FROM Verification v WHERE v.status = 'A'")
    String findVerificationCodesWithStatusA();

    @Query("SELECT v.verificationCode FROM Verification v WHERE v.status = 'A'")
    String findVerificationCodesWithStatusAB(String verificationCode);

    Optional<Verification> findById(Long id);

}
