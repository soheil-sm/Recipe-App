package org.example.recipe.service;

import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.converters.IngredientCommandToIngredient;
import org.example.recipe.converters.IngredientToIngredientCommand;
import org.example.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import org.example.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.example.recipe.domain.Ingredient;
import org.example.recipe.domain.Recipe;
import org.example.recipe.domain.UnitOfMeasure;
import org.example.recipe.repositories.RecipeRepository;
import org.example.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    public static final long INGREDIENT_ID = 1L;
    public static final long RECIPE_ID = 2L;
    public static final long NEW_UOM_ID = 2L;
    public static final String NEW_DESCRIPTION = "new Description";
    public static final BigDecimal NEW_AMOUNT = BigDecimal.valueOf(5);
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientService ingredientService;

    IngredientServiceImplTest() {
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientServiceImpl(recipeRepository,
                ingredientToIngredientCommand,
                unitOfMeasureRepository,
                ingredientCommandToIngredient);
    }

    @Test
    void findCommandByRecipeIdAndIngredientId() {
//        given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        recipe.addIngredient(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        recipe.addIngredient(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

//        when
        IngredientCommand ingredientCommand = ingredientService.findCommandByRecipeIdAndIngredientId(1L, 2L);

//        then
        assertNotNull(ingredientCommand);
        assertEquals(ingredient2.getId(), ingredientCommand.getId());
        assertEquals(ingredient2.getRecipe().getId(), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveIngredient() {
//        given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setRecipeId(RECIPE_ID);

        Optional<Recipe> optionalRecipe = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(RECIPE_ID);
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(INGREDIENT_ID);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);
//        when
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);
//        then
        assertNotNull(savedIngredient);
        assertEquals(INGREDIENT_ID, savedIngredient.getId());
        assertEquals(RECIPE_ID, savedIngredient.getRecipeId());
    }

    @Test
    void updateIngredient() {
//        given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription("old description");
        ingredient.setAmount(BigDecimal.valueOf(2));
        ingredient.setUom(unitOfMeasure);

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.addIngredient(ingredient);

        unitOfMeasure.setId(NEW_UOM_ID);
        ingredient.setUom(unitOfMeasure);
        ingredient.setDescription(NEW_DESCRIPTION);
        ingredient.setAmount(NEW_AMOUNT);

        Recipe savedRecipe = recipe;
        savedRecipe.addIngredient(ingredient);


        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(unitOfMeasure));
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

//        when
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientToIngredientCommand.convert(ingredient));

//        then
        assertEquals(INGREDIENT_ID, savedIngredient.getId());
        assertEquals(NEW_AMOUNT, savedIngredient.getAmount());
        assertEquals(NEW_DESCRIPTION, savedIngredient.getDescription());
        assertEquals(NEW_UOM_ID, savedIngredient.getUom().getId());
    }
}