package org.example.recipe.service;

import lombok.extern.slf4j.Slf4j;
import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.converters.IngredientCommandToIngredient;
import org.example.recipe.converters.IngredientToIngredientCommand;
import org.example.recipe.domain.Ingredient;
import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.example.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());

        if (optionalRecipe.isEmpty()) {
            //todo toss error if not found
            log.error("recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = optionalRecipe.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(command.getDescription());
                ingredient.setAmount(command.getAmount());
                ingredient.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM Not Found"))); //todo address this
            } else {
//                add new ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                recipe.addIngredient(ingredient);
            }

            //todo check for fail
            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredient = savedRecipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if (savedIngredient.isEmpty()) {
                //not totally safe... But best guess
                log.debug("in the if statement");
                savedIngredient = savedRecipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }
            //to do check for fail

            return ingredientToIngredientCommand.convert(savedIngredient.get());
        }
    }
}
