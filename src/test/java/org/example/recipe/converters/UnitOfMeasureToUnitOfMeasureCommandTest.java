package org.example.recipe.converters;

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
class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "description";

    @InjectMocks
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @BeforeEach
    void setUp() {
    }

    void testEmptyObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand converted = uomConverter.convert(unitOfMeasure);

        // then
        assertEquals(ID, converted.getId());
        assertEquals(DESCRIPTION, converted.getDescription());
    }
}