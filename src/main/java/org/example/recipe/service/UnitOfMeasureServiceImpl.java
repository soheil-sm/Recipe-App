package org.example.recipe.service;

import org.example.recipe.commands.UnitOfMeasureCommand;
import org.example.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.example.recipe.domain.UnitOfMeasure;
import org.example.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Iterable<UnitOfMeasure> all = unitOfMeasureRepository.findAll();

        return StreamSupport.stream(
                all.spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
