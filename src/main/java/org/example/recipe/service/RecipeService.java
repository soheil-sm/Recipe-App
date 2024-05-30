package org.example.recipe.service;

import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(String id);
}
