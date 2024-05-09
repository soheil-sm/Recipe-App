package org.example.recipe.contollers;

import lombok.extern.slf4j.Slf4j;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.Recipe;
import org.example.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientsController {
    private final RecipeService recipeService;

    public IngredientsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(recipeId));

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipe);

        return "recipe/ingredients/lists";
    }
}
