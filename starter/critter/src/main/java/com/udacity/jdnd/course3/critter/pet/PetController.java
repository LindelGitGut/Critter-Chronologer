package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertToPetDto(petService.save(convertFromPetDto(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertToPetDto(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<PetDTO> allPetsDto = new ArrayList<>();
        List<Pet> allPets = petService.findAllPets();
        for (Pet pet: allPets
             ) {
            allPetsDto.add(convertToPetDto(pet));
        }
        return allPetsDto;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetByCustomerId(ownerId);
        List<PetDTO> petsDto = new ArrayList<>();
        for (Pet pet:pets
             ) {
            petsDto.add(convertToPetDto(pet));
        }
       return petsDto;
    }


    private PetDTO convertToPetDto(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        //TODO check if further conversion is needed
        return petDTO;
    }

    private Pet convertFromPetDto(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        //TODO check if further conversion is needed
        return pet;
    }
}
