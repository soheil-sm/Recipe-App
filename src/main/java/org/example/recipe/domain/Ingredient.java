package org.example.recipe.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    private String  id;
    private Recipe recipe;
    private String description;
    private BigDecimal amount;
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
