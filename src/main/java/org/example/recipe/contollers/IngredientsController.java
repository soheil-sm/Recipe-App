package org.example.recipe.contollers;

import lombok.extern.slf4j.Slf4j;
import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.commands.UnitOfMeasureCommand;
import org.example.recipe.domain.Recipe;
import org.example.recipe.service.IngredientService;
import org.example.recipe.service.RecipeService;
import org.example.recipe.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientsController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    public IngredientsController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(recipeId));

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipe);

        return "recipe/ingredients/lists";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId,
                                 @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findCommandByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));

        return "recipe/ingredients/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findCommandByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));
        model.addAttribute("uomList", uomService.listAllUoms());


        return "recipe/ingredients/ingredientform";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        //make sure we have a good id value
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipe.getId());
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("ingredient", ingredientCommand);

//      init Uom
        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredients/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId,
                               @ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("saved recipe id: " + savedIngredientCommand.getRecipeId());
        log.debug("saved ingredient id: " + savedIngredientCommand.getId());

        return "redirect:/recipe/" + savedIngredientCommand.getRecipeId() + "/ingredient/" + savedIngredientCommand.getId() + "/show";
    }
}
