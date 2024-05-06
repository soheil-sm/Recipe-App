package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.CategoryCommand;
import org.example.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Override
    public CategoryCommand convert(Category source) {
        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }
}