package com.example.carsproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "details_of_owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "birth_date")
    private String birthDate;

    @OneToMany(mappedBy = "fkOwnerId")
    Collection<Car> cars;
}
