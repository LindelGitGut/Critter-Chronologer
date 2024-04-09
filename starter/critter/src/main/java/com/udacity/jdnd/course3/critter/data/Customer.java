package com.udacity.jdnd.course3.critter.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Customer extends User{

    //Customer has phoneNumber
    private String phoneNumber;

    //Customer can have Notes
    private String notes;

    //One Customer can have more than one Pet, in DTO Only ID if pet is Provided/Recived
    //TODO Check if Fetchtype.Lazy is the right prop for oneToMany
    @OneToMany(fetch = FetchType.LAZY)
    private List<Pet> pets;


}
