package org.example.recipie.contollers;

import org.example.recipie.domain.Recipe;
import org.example.recipie.service.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    private final RecipeServiceImpl recipeService;

    public IndexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index(Model model) {
        Set<Recipe> recipes = recipeService.getRecipes();

        System.out.println(recipes.size());
        model.addAttribute("recipes", recipes);

        return "index";
    }
}
