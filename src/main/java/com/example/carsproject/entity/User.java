package com.example.carsproject.entity;


import com.example.carsproject.security.Role;
import com.example.carsproject.security.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {


    @Id
    @GeneratedValue
    Integer id;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 20, message = "Username can be a minimum of 2 and a maximum of 20 characters")
    String firstname;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 20, message = "Username can be a minimum of 2 and a maximum of 20 characters")
    String lastname;

    @NotBlank(message = "E-mail cannot be empty")
    @Email(message = "Invalid e-mail address")
    String email;

    @Column(name = "password")
    String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
