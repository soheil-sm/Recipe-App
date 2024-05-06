package org.example.recipe.service;

import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.converters.RecipeToRecipeCommand;
import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String DESCRIPTION = "New Description";
    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand converter;

    @Test
    @Transactional
    public void saveRecipeCommand() throws Exception {
//        given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = converter.convert(testRecipe);

//        when
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

//        then
        assertNotNull(recipeCommand);
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(testRecipeCommand.getId(), recipeCommand.getId());
        assertEquals(testRecipeCommand.getCategories().size(), recipeCommand.getCategories().size());
        assertEquals(testRecipeCommand.getIngredients().size(), recipeCommand.getIngredients().size());
    }
}
