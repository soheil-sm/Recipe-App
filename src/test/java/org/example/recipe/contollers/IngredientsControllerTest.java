package org.example.recipe.contollers;

import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.service.IngredientService;
import org.example.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientsControllerTest {

    public static final long ID = 2L;
    @InjectMocks
    IngredientsController ingredientsController;

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientsController).build();
    }

    @Test
    void listIngredients() throws Exception {
//        given
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(ID);

//        when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipe);

//        then
        mockMvc.perform(get("/recipe/2/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/lists"))
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void showIngredient() throws Exception {
//        given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
//        when
        when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
//        then
        mockMvc.perform(get("/recipe/2/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
}