package org.example.recipe.repositories;

import org.example.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository uomRepository;


    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {

        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Tablespoon");

        assertEquals("Tablespoon", uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {

        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Cups");

        assertEquals("Cups", uomOptional.get().getDescription());
    }
}