package org.example.recipe.service;

import lombok.extern.slf4j.Slf4j;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.converters.RecipeCommandToRecipe;
import org.example.recipe.converters.RecipeToRecipeCommand;
import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Im in the service layer");

        Set<Recipe> recipesSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);

        return recipesSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isEmpty()) {
            throw new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeID: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
