package org.example.recipe.service;

import lombok.extern.slf4j.Slf4j;
import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.converters.IngredientToIngredientCommand;
import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findCommandByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (recipe.isEmpty()) {
            //todo impl error handling
            log.debug("Recipe id not found. Id: " + recipeId);
        }

        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert)
                .findFirst();

        if (ingredientCommand.isEmpty()) {
            //todo impl error handling
            log.debug("Ingredient id not found. Id: " + ingredientId);
        }

        return ingredientCommand.get();
    }
}
