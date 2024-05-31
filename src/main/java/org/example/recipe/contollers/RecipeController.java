package org.example.recipe.contollers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.domain.Recipe;
import org.example.recipe.exceptions.NotFoundException;
import org.example.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    public static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.findById(id);

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        log.debug("creating new recipe");

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("/recipe/")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString()));

            return RECIPE_RECIPEFORM_URL;
        }
        if (command.getIngredients().isEmpty() && command.getCategories().isEmpty()) {
            RecipeCommand commandById = recipeService.findCommandById(command.getId());
            command.setIngredients(commandById.getIngredients());
            command.setCategories(commandById.getCategories());
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(id);

        log.debug("recipe with id: " + id + " deleted");

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
