package org.example.recipe.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile multipartFile) throws IOException;
}
