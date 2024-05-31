package org.example.recipe.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    private String  id = UUID.randomUUID().toString();
    private String description;
    private BigDecimal amount;

    @DBRef
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}
