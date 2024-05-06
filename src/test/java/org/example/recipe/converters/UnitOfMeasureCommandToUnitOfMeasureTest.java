package org.example.recipe.converters;

import org.example.recipe.commands.IngredientCommand;
import org.example.recipe.commands.UnitOfMeasureCommand;
import org.example.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "description";

    @InjectMocks
    UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(uomConverter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID);
        uomCommand.setDescription(DESCRIPTION);

        UnitOfMeasure converted = uomConverter.convert(uomCommand);

        assertEquals(ID, converted.getId());
        assertEquals(DESCRIPTION, converted.getDescription());
    }
}