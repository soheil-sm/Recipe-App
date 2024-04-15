package org.example.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String description;
}
