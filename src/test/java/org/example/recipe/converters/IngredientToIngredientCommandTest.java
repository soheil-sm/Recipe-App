package org.example.recipe.converters;

import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.commands.UnitOfMeasureCommand;
import org.example.recipe.domain.Ingredient;
import org.example.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.zip.DeflaterInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IngredientToIngredientCommandTest {

    public static final long INGREDIENT_ID = 1L;
    public static final String DESCRIPTION = "Cheeseburger";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final long UOM_ID = 1L;
    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void convert() {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        // when
        IngredientCommand converted = converter.convert(ingredient);

        // then
        assertNotNull(converted);
        assertEquals(INGREDIENT_ID, converted.getId());
        assertEquals(DESCRIPTION, converted.getDescription());
        assertEquals(AMOUNT, converted.getAmount());
        assertEquals(UOM_ID, converted.getUom().getId());
    }
}