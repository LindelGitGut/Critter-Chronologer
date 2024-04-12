package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    //should return Pet by ownerId
    @Query("SELECT p FROM Pet p join p.owner o WHERE o.id = :ownerId ")
    List<Pet> getPetsByCustomerId(Long ownerId);

}
