package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter
            , IngredientCommandToIngredient ingredientConverter
            , NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        final Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && !source.getCategories().isEmpty()) {
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
