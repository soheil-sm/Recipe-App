package org.example.recipe.converters;

import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RecipeToRecipeCommandTest {

    public static final long RECIPE_ID = 1L;
    public static final String SOURCE = "Source";
    public static final String URL = "Url";
    public static final int SERVINGS = 5;
    public static final Difficulty DIFFICULTY = Difficulty.HARD;
    public static final String DIRECTIONS = "directions";
    public static final int PREP_TIME = 19;
    public static final int COOK_TIME = 14;
    public static final String DESCRIPTION = "Description";
    public static final long NOTES_ID = 2L;
    public static final long INGREDIENT_ID = 3L;
    public static final long CATEGOR_ID = 4L;
    public static final long UOM_ID = 5L;
    RecipeToRecipeCommand converter;
    UnitOfMeasureToUnitOfMeasureCommand uomCommand;
    @BeforeEach
    void setUp() {
         uomCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(), new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(uomCommand));
    }

    @Test
    void convert() {
//        given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setServings(SERVINGS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(DESCRIPTION);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);
        recipe.getIngredients().add(ingredient);

        Category category = new Category();
        category.setId(CATEGOR_ID);
        recipe.getCategories().add(category);

//        when
        RecipeCommand converted = converter.convert(recipe);
//        then
        assertNotNull(converted);
        assertEquals(RECIPE_ID, converted.getId());
        assertEquals(DIFFICULTY, converted.getDifficulty());
        assertEquals(DIRECTIONS, converted.getDirections());
        assertEquals(NOTES_ID, converted.getNotes().getId());
        assertEquals(SERVINGS, converted.getServings());
        assertEquals(SOURCE, converted.getSource());
        assertEquals(DESCRIPTION, converted.getDescription());
        assertEquals(URL, converted.getUrl());
        assertEquals(COOK_TIME, converted.getCookTime());
        assertEquals(PREP_TIME, converted.getPrepTime());
        assertEquals(1, converted.getIngredients().size());
        assertEquals(1, converted.getCategories().size());
    }
}