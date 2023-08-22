package com.example.carsproject.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cars_details")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @NotBlank(message = "Car's name cannot be empty")
    @Column(name = "name")
    String name;

    @NotBlank(message = "Car's model cannot be empty")
    @Column(name = "model")
    String model;

    @NotBlank(message = "Car's colour cannot be empty")
    @Column(name = "colour")
    String colour;

    @Column(name = "year")
    long year;

    @NotBlank(message = "PowerOfMotor cannot be empty")
    @Column(name = "power_of_motor")
    String powerOfMotor;

    @NotBlank(message = "Car's price cannot be empty")
    @Column(name = "price")
    String price;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "fkCarId")
    List<FileEntity> fileEntities;

    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToStringExclude
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
}
