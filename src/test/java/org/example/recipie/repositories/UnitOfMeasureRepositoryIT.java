package org.example.recipie.repositories;

import org.example.recipie.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
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