package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.NotesCommand;
import org.example.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Override
    public NotesCommand convert(Notes source) {
        final NotesCommand notesCommand = new NotesCommand();

        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());

        return notesCommand;
    }
}
