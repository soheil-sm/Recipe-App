package org.example.recipe.converters;

import lombok.Synchronized;
import org.example.recipe.commands.UnitOfMeasureCommand;
import org.example.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();

        uomCommand.setId(source.getId());
        uomCommand.setDescription(source.getDescription());

        return uomCommand;
    }
}
