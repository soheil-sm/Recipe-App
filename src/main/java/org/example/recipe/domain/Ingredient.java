package org.example.recipe.domain;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    private String description;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(Recipe recipe, String description, BigDecimal amount, UnitOfMeasure uom) {
        this.recipe = recipe;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}
