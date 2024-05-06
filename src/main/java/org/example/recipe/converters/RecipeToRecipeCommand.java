package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter
            , IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
            source.getIngredients()
                    .forEach(ingredientCommand -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        if (source.getCategories() != null && !source.getCategories().isEmpty()) {
            source.getCategories()
                    .forEach(categoryCommand -> recipeCommand.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        return recipeCommand;
    }
}
