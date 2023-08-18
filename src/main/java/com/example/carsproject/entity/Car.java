package com.example.carsproject.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "model")
    String model;

    @Column(name = "colour")
    String colour;

    @Column(name = "year")
    long year;

    @Column(name = "power_of_motor")
    String powerOfMotor;

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
