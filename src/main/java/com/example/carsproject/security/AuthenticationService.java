package com.example.carsproject.security;

import com.example.carsproject.entity.Verification;
import com.example.carsproject.exception.NotFoundUser;
import com.example.carsproject.exception.NotUniqueUser;
import com.example.carsproject.exception.NotVerified;
import com.example.carsproject.exception.WrongPassword;
import com.example.carsproject.entity.User;
import com.example.carsproject.repository.UserRepository;
import com.example.carsproject.repository.VerificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;
    private final VerificationRepository verificationRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findAll().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(request.getEmail()))) {
            throw new NotUniqueUser();
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .verified(request.verified)
                .build();

        var savedUser = repository.save(user);
        var verification = Verification.builder()
                .user(savedUser)
                .verificationCode(request.getVerificationCode())
                .build();
        verificationRepository.save(verification);
        var jwtToken = jwtService.generateToken(user);

        user.setEmail(request.getEmail());
        user.setVerified(false);
        verification.setVerificationCode(generateVerificationCode());
        sendVerificationCode(user.getId());


        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(null)
                .message("Successfully")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userByEmail = repository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundUser::new);
        if (!passwordEncoder.matches(request.getPassword(), userByEmail.getPassword())) {
            throw new WrongPassword();
        }
        if(!userByEmail.isVerified()){
            throw new NotVerified();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    @Transactional
    public User sendVerificationCode(Integer userId) {
        User user = repository.findById(userId).orElse(null);
        Verification verification = verificationRepository.findById(userId).orElse(null);
        if (user != null && !user.isVerified()) {
            sendEmail(user.getEmail(), verification.getVerificationCode());
        }
        return user;
    }
    @Transactional
    public VerifyResponse verifyUser(String email, String verificationCode) {
        User user = repository.findByEmail(email).orElse(null);
        Verification verification = verificationRepository.findByVerificationCode(verificationCode).orElse(null);
        if (user != null && verification.getVerificationCode().equals(verificationCode)) {
            user.setVerified(true);
            repository.save(user);
            verificationRepository.save(verification);
        }
        return VerifyResponse.builder()
                .token(null)
                .message("Verified")
                .verificationCode(verification.getVerificationCode())
                .verified(user.isVerified())
                .build();
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    private void sendEmail(String email, String verificationCode) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setSubject("Email Verification Code");
            helper.setText("Your verification code is: " + verificationCode);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}