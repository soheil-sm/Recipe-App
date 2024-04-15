package org.example.recipe.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;

//    @Lob : Large Objects, Clob : character large object, Blob : binary large object
    @Lob
    private String recipeNotes;

}