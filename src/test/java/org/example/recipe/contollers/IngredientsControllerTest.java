package org.example.recipe.contollers;

import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.commands.RecipeCommand;
import org.example.recipe.commands.UnitOfMeasureCommand;
import org.example.recipe.service.IngredientService;
import org.example.recipe.service.RecipeService;
import org.example.recipe.service.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @Mock
    UnitOfMeasureService uomService;
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

    @Test
    void updateIngredientForm() throws Exception {
//        given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
//        when
        when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(uomService.listAllUoms()).thenReturn(new HashSet<>());
//        then
        mockMvc.perform(get("/recipe/2/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    void newIngredientForm() throws Exception {
        UnitOfMeasureCommand uom1 = new UnitOfMeasureCommand();
        uom1.setId(1L);
        UnitOfMeasureCommand uom2 = new UnitOfMeasureCommand();
        uom2.setId(2L);
        UnitOfMeasureCommand uom3 = new UnitOfMeasureCommand();
        uom3.setId(3L);
        Set<UnitOfMeasureCommand> uomList = new HashSet<>();
        uomList.add(uom1);
        uomList.add(uom2);
        uomList.add(uom3);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(4L);

        when(uomService.listAllUoms()).thenReturn(uomList);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/2/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    void deleteIngredient() throws Exception {

        mockMvc.perform(get("/recipe/2/ingredient/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

        verify(ingredientService, times(1)).deleteIngredientById(anyLong(), anyLong());
    }

    @Test
    void saveOrUpdate() throws Exception {
//        given
        IngredientCommand ingredientCommand =  new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(2L);
//        when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);
//        then
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some String")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/1/show"));
    }
}