package org.example.recipe.converters;

import org.example.recipe.commands.NotesCommand;
import org.example.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotesCommandToNotesTest {

    public static final long NOTES_ID = 1L;
    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void convert() {
        // given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes converted = converter.convert(notesCommand);

        // then
        assertNotNull(converted);
        assertEquals(NOTES_ID, converted.getId());
        assertEquals(RECIPE_NOTES, converted.getRecipeNotes());
    }
}