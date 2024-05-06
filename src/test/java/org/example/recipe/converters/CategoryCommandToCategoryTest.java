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
class CategoryCommandToCategoryTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Description";
    @InjectMocks
    CategoryCommandToCategory converter;

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);

        Category converted = converter.convert(categoryCommand);

        assertEquals(ID, converted.getId());
        assertEquals(DESCRIPTION, converted.getDescription());
    }
}