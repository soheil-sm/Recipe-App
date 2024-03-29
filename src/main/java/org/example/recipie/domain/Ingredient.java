package org.example.recipie.domain;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    private String description;
    private BigDecimal amount;
    @ManyToOne(cascade=CascadeType.MERGE)
    private UnitOfMeasure uom;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(Recipe recipe, String description, BigDecimal amount, UnitOfMeasure uom) {
        this.recipe = recipe;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return uom;
    }

    public void setUnitOfMeasure(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
