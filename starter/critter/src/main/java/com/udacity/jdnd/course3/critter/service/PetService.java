package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    //TODO Implement


    //find all
    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> findAllPetsById(Iterable<Long> ids){
        return petRepository.findAllById(ids);
    }

    public Pet findPetById(Long id){
        Optional<Pet> optPet = petRepository.findById(id);
        if (optPet.isPresent()){return optPet.get();}
        else {throw new IllegalArgumentException("Pet with Provided id not found");}
    }



}
