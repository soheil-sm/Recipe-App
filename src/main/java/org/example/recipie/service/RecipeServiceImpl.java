package org.example.recipie.service;

import org.example.recipie.domain.Recipe;
import org.example.recipie.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private  final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipesSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);

        return recipesSet;
    }
}
