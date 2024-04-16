package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;



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

            Pet newPet = petRepository.save(pet);
            if (pet.getOwner() != null){
                Optional<Customer> optCustomer = customerRepository.findById(pet.getOwner().getId());
                if (optCustomer.isPresent()){
                    Customer customer = optCustomer.get();
                    customer.addPet(newPet);
                    customerRepository.save(customer);

                }
                else {throw new IllegalArgumentException("Could not find Owner with associated owner id for new Pet");}
            }
            return newPet;
        }
        //check if Pet is found in DB if Not throw Exception else override
        else {
            Optional<Pet> optPet = petRepository.findById(pet.getId());
            if (optPet.isPresent()) {

                Pet newPet = petRepository.save(pet);
                // pet is available in Database so overriding it
                if (pet.getOwner() != null){
                    Optional<Customer> optCustomer = customerRepository.findById(pet.getOwner().getId());
                    if (optCustomer.isPresent()){
                        Customer customer = optCustomer.get();
                        customer.addPet(newPet);
                        customerRepository.save(customer);
                    }
                    else {throw new IllegalArgumentException("Could not find Owner with associated owner id for new Pet");}
                }
                return newPet;
            } else {
                throw new IllegalArgumentException("Pet with provided ID was not found in DB");
            }
        }
    }

    public List<Pet> findPetByCustomerId(long ownerId) {
        return petRepository.getPetsByCustomerId(ownerId);
    }
}
