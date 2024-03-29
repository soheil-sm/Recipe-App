package org.example.recipie.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String description;

    public UnitOfMeasure(String description) {
        this.description = description;
    }

    public UnitOfMeasure() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
