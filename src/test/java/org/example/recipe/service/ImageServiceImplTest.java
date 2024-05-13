package org.example.recipe.service;

import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
    class ImageServiceImplTest {

    public static final long RECIPE_ID = 1L;
    @InjectMocks
    ImageServiceImpl imageService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveImageFile() throws IOException {
//        given
        MockMultipartFile file = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

//        when
        imageService.saveImageFile(recipe.getId(), file);

//        then
        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());
        Recipe recipeValue = recipeArgumentCaptor.getValue();
        assertEquals(file.getBytes().length,  recipeValue.getImage().length);
    }
}