package com.example.carsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "_files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String filename;
    private String contentType;

    @Lob
    @Column(length = 10485760)
    private byte[] data;

    @JoinColumn(name = "fk_car_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    Car fkCarId;
}

