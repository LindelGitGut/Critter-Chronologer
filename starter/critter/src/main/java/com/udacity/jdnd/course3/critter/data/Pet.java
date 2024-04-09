package com.udacity.jdnd.course3.critter.data;


import com.udacity.jdnd.course3.critter.pet.PetType;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Nationalized
    private String name;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    private LocalDate birthDate;
    private String notes;

    //Pet has one Owner (Customer)
    @ManyToOne
    private Customer owner;


    //TODO add Lombok Annotation for Getter Setter instead of Boilerplate
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
