package com.udacity.jdnd.course3.critter.data;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Customer {

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


    public void addPet(Pet pet) {
        if (this.pets == null) {
            this.pets = new ArrayList<>();
        }
        pets.add(pet);
    }

    public void removePet(Pet pet) {
        if (pets != null) {
            pets.remove(pet);
        }
    }


}
