package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.NotesCommand;
import org.example.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Override
    public Notes convert(NotesCommand source) {
        final Notes notes = new Notes();

        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        return notes;
    }
}
