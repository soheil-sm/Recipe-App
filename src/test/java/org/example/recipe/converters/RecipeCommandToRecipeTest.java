package org.example.recipe.converters;

import org.example.recipe.commands.CategoryCommand;
import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.commands.NotesCommand;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.Difficulty;
import org.example.recipe.domain.Notes;
import org.example.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final long RECIPE_ID = 1L;
    public static final String DESCRIPTION = "Description";
    public static final String DIRECTION = "Direction";
    public static final long NOTES_ID = 5L;
    public static final int SERVINGS = 5;
    public static final String URL = "Url";
    public static final String SOURCE = "Source";
    public static final int PREP_TIME = 12;
    public static final int COOK_TIME = 34;
    public static final long CAT_ID1 = 1L;
    public static final long CAT_ID2 = 2L;
    public static final long INGRED_ID1 = 3L;
    public static final long INGRED_ID2 = 4L;
    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    void convert() {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDirections(DIRECTION);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        recipeCommand.setNotes(notesCommand);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setUrl(URL);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID2);

        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGRED_ID1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGRED_ID2);

        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.getIngredients().add(ingredientCommand2);

        // when
        Recipe converted = converter.convert(recipeCommand);

        // then
        assertNotNull(converted);
        assertEquals(RECIPE_ID, converted.getId());
        assertEquals(DESCRIPTION, converted.getDescription());
        assertEquals(DIFFICULTY, converted.getDifficulty());
        assertEquals(DIRECTION, converted.getDirections());
        assertEquals(NOTES_ID, converted.getNotes().getId());
        assertEquals(SERVINGS, converted.getServings());
        assertEquals(URL, converted.getUrl());
        assertEquals(SOURCE, converted.getSource());
        assertEquals(PREP_TIME, converted.getPrepTime());
        assertEquals(COOK_TIME, converted.getCookTime());
        assertEquals(2, converted.getIngredients().size());
        assertEquals(2, converted.getCategories().size());
    }
}