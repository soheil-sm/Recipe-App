package org.example.recipie.service;

import org.example.recipie.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
