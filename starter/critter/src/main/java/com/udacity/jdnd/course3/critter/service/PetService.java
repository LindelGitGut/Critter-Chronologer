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
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> findAllPetsById(Iterable<Long> ids) {
        return petRepository.findAllById(ids);
    }

    public Pet findPetById(Long id) {
        Optional<Pet> optPet = petRepository.findById(id);
        if (optPet.isPresent()) {
            return optPet.get();
        } else {
            throw new IllegalArgumentException("Pet with Provided id not found");
        }
    }

    public Pet save(Pet pet) {
        //check if id is null, if so save
        if (pet.getId() == null) {
            return petRepository.save(pet);
        }
        //check if Pet is found in DB if Not throw Exception else override
        else {
            Optional<Pet> optPet = petRepository.findById(pet.getId());
            if (optPet.isPresent()) {
                // pet is available in Database so overriding it
                return petRepository.save(pet);
            } else {
                throw new IllegalArgumentException("Pet with provided ID was not found in DB");
            }
        }
    }

}
