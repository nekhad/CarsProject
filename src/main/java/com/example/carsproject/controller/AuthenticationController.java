package com.example.carsproject.controller;

import com.example.carsproject.dto.request.AuthenticationRequest;
import com.example.carsproject.dto.request.RegisterRequest;
import com.example.carsproject.dto.request.SendCodeAgainRequest;
import com.example.carsproject.dto.request.VerifyRequest;
import com.example.carsproject.dto.response.AuthenticationResponse;
import com.example.carsproject.dto.response.VerifyResponse;
import com.example.carsproject.entity.Token;
import com.example.carsproject.exception.NotFoundUser;
import com.example.carsproject.exception.NotUniqueUser;
import com.example.carsproject.exception.WrongPassword;
import com.example.carsproject.repository.TokenRepository;
import com.example.carsproject.service.impl.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final TokenRepository tokenRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (NotUniqueUser ex) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new AuthenticationResponse(null, "This email is already exist", null, null));
        }
    }
    @PostMapping("/afterRegister")
    public ResponseEntity<AuthenticationResponse> sendCodeToEmail(@RequestBody SendCodeAgainRequest sendCodeAgainRequest) {
        try {
            return ResponseEntity.ok(service.sendCodeToEmail(sendCodeAgainRequest));
        } catch (NotUniqueUser ex) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new AuthenticationResponse(null, "+++++++++++++++++++", null, null));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (WrongPassword ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthenticationResponse(null, "Wrong password", null, null));
        } catch (NotFoundUser ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthenticationResponse(null, "No such e-mail address was found", null, null));
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<VerifyResponse> verify(@RequestBody VerifyRequest request) {
        try {
            return ResponseEntity.ok(service.verifyUser(request.getEmail(),request.getVerificationCode()));
        } catch (WrongPassword ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new VerifyResponse(null, "Incorrect verification code", request.getVerificationCode(), request.isVerified(),request.getEmail()));
        }
    }


    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Optional<Token> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            Token token2 = tokenOptional.get();
            if (token2.isExpired() && token2.isRevoked()) {

            } else {
                token2.setExpired(true);
                token2.setRevoked(true);
                tokenRepository.save(token2);
                SecurityContextHolder.clearContext();
            }
        }
    }

}