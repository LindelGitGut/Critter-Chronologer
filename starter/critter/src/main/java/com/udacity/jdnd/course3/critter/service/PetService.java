package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    //TODO Implement


    //find all
    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }

}
