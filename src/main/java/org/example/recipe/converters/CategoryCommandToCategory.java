package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.CategoryCommand;
import org.example.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Synchronized
    @Override
    public Category convert(CategoryCommand source) {
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
