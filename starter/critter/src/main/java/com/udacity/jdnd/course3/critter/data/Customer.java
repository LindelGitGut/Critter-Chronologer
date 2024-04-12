package com.udacity.jdnd.course3.critter.data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer{

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Long id;
    @Nationalized
    private String name;

    //Customer has phoneNumber
    private String phoneNumber;

    //Customer can have Notes
    private String notes;


    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pet> pets;


    public void addPet(Pet pet){
        if (this.pets == null){
            this.pets = new ArrayList<>();
        }
        pets.add(pet);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
