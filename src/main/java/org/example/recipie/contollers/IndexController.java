package org.example.recipie.contollers;

import org.example.recipie.domain.Category;
import org.example.recipie.domain.UnitOfMeasure;
import org.example.recipie.repositories.CategoryRepository;
import org.example.recipie.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String index() {

        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<Category> american = categoryRepository.findByDescription("American");

        System.out.println("Cat id is: " + american.get().getId());
        System.out.println("Measure id is: " + tablespoon.get().getId());

        return "index";
    }
}
