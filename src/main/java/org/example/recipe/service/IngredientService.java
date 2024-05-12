package org.example.recipe.service;

import org.example.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findCommandByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
