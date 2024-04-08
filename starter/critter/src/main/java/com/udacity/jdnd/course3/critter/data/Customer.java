package com.udacity.jdnd.course3.critter.data;

import javax.persistence.OneToMany;
import java.util.List;

public class Customer extends User{


    //One Customer can have more than one Pet
    @OneToMany
    private List<Pet> pets;

}
