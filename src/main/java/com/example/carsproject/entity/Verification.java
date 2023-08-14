package com.example.carsproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verifications")
public class Verification {

    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Column(name = "verification_code")
    private String verificationCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}