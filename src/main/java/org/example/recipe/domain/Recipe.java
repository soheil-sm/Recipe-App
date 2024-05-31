package org.example.recipe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class Recipe {

    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    @DBRef
    private Set<Category> categories = new HashSet<>();
    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
//            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
//        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
