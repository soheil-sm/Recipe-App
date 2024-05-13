package org.example.recipe.service;

import lombok.extern.slf4j.Slf4j;
import org.example.recipe.domain.Recipe;
import org.example.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) throws IOException {

        log.debug("Received a file");
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

            Byte[] image = new Byte[multipartFile.getBytes().length];

            int i = 0;
            for (byte b : multipartFile.getBytes()) {
                image[i] = b;
                i++;
            }

            Recipe recipe = null;
            if (recipeOptional.isPresent())
                recipe = recipeOptional.get();

            recipe.setImage(image);
            recipeRepository.save(recipe);
        } catch (IOException ioException) {
            log.error("Error occurred", ioException);

            ioException.printStackTrace();
        }

    }
}
