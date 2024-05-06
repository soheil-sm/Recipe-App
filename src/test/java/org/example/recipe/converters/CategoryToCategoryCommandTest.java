package org.example.recipe.converters;

import org.example.recipe.commands.CategoryCommand;
import org.example.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CategoryToCategoryCommandTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Description";
    @InjectMocks
    CategoryToCategoryCommand converter;

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void convert() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand converted = converter.convert(category);

        assertEquals(ID, converted.getId());
        assertEquals(DESCRIPTION, converted.getDescription());
    }
}