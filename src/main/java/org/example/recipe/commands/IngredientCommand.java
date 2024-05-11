package org.example.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class IngredientCommand {
    private Long id;
    private Long recipeId; 
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
