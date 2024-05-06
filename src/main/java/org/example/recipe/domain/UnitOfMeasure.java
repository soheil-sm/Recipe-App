package org.example.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
