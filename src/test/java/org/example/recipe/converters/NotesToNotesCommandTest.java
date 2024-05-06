package org.example.recipe.converters;

import org.example.recipe.commands.NotesCommand;
import org.example.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final String RECIPE_NOTES = "Notes";
    public static final long NOTES_ID = 1L;
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void convert() {
        // given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        // when
        NotesCommand converted = converter.convert(notes);

        // then
        assertNotNull(converted);
        assertEquals(NOTES_ID, converted.getId());
        assertEquals(RECIPE_NOTES, converted.getRecipeNotes());
    }
}