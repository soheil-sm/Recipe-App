package org.example.recipe.service;

import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.converters.IngredientToIngredientCommand;
import org.example.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.example.recipe.domain.Ingredient;
import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
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
}