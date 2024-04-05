package org.example.recipie.contollers;

import lombok.extern.slf4j.Slf4j;
import org.example.recipie.domain.Recipe;
import org.example.recipie.service.RecipeService;
import org.example.recipie.service.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index(Model model) {
        log.debug("Im in the Index Controller.");

        Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);

        return "index";
    }
}
