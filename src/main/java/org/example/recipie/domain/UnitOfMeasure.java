package org.example.recipie.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String description;
}
