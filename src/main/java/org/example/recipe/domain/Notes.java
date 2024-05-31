package org.example.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    @Id
    private String id;
    private String recipeNotes;
}